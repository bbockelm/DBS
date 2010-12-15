#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Copyright 2006 Cornell University, Ithaca, NY 14853. All rights reserved.
#
# Author:  Valentin Kuznetsov, 2006
"""
Common utilities module used by DBS data discovery.
"""

# import system modules
import os, string, sys, time, types, traceback, random, difflib, httplib, urllib
import md5, re
import logging, logging.handlers
try:
    # Python 2.5
    import xml.etree.ElementTree as ET
except:
    # prior requires elementtree
    import elementtree.ElementTree as ET

# import DBS modules
import DDOptions

SENDMAIL = "/usr/sbin/sendmail" # sendmail location
#RES_PER_PAGE=25 # number of results per page shown
RES_PER_PAGE=10 # number of results per page shown
GLOBAL_STEP =10 # number of iterators shown in Results bar

# file created by crontab job, see getDLSsites.sh
DLS_INFO='dls.all'

# Tips
TIPS= [
"to save your history, open history menu and authenticate yourself",
"to find all data on particular site, use expert site search",
"DBS only information can be accessed from expert page",
"don't use 'back' button, use history menu instead",
"to send found data to your buddy, use 'bare URL' link at bottom of the page"
]

def singleList(iList):
    return [i[0] for i in iList]

def where_cond(dlist, tag, climit=50):
    clist = []
    cond = ""
    counter = 0
    for item in dlist:
        cond += ' or %s=%s' % (tag, item)
        if  counter == climit:
            clist.append(cond[3:])
            counter = 0
            cond = ""
        counter += 1
    clist.append(cond[3:])
    return clist

def findOutput(input):
    output = ''
    if  input.lower().find(" where ")!=-1:
        output=input.lower().split(" where ")[0].split("find")[1].strip()
    else:
        output = input.lower().split("find")[-1].strip()
    return output

def inputParser(input,keys):
    for key in keys:
        input=input.replace("%s:"%key,"_____%s:"%key)
    words=[]
    for w in input.split("_____"):
        if w:
           words.append(w.strip())
    return words

def convertTimeToEpoch(tStamp):
    #http://www.epochconverter.com/
    yyyymmdd=re.compile("^\d{8}$")
    if not yyyymmdd.match(tStamp):
       raise "convertTimeToEpoch: date should be supplied in a form of YYYYMMDD"
    return int(time.mktime(time.strptime(tStamp, '%Y%m%d')))

def convertDBS2DDTime(dbstime):
    # TODO: check that convertion is correct, example of DBS return
    # 2007-11-11 21:29:10 CET
    dstr, hhmmss, zone = dbstime.split()
    yy, mm, dd = dstr.split('-')
    months=['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec']
    mm = months[int(mm)-1]
    return "%s %s %s %s %s" % (dd, mm, yy, hhmmss, zone)

#    dbstime = dbstime.replace('CET', '').replace('CEST', '').strip()
#    cettime = time.strptime(dbstime, '%Y-%m-%d %H:%M:%S')
#    secepoch = time.mktime(cettime) + 3600 # +1 h for GMT
#    return time.strftime("%d %b %Y %H:%M:%S GMT",
#                time.gmtime(secepoch))

def findKeyInAList(_list,_key):
    for item in _list:
        idx=_key.find(item)
        if idx!=-1: return item
    return ""

def formatQuery(q):
    q=q.replace("(","\n(\n").replace(")","\n)\n")
    q=q.replace(" FROM ","\nFROM ").replace(" GROUP BY ","\nGROUP BY ")
    q=q.replace(" ORDER BY ","\nORDER BY ").replace(" WHERE ","\nWHERE ")
    q=q.replace(" ON ","\n    ON ").replace(",","\n  ,").replace(" LEFT ","\n  LEFT ")
    q=q.replace(" AND ","\n  AND ").replace(" OR ","\n  OR ")
    q=q.replace(" ESCAPE ","\n  ESCAPE ").replace(" IN ","\n  IN ")
    q=q.replace(" INTERSECT ","\nINTERSECT\n").replace(" UNION ","\nUNION\n")
    qList=q.split("\n")
    sep=""
    nList=[]
    for idx in xrange(0,len(qList)):
        line=qList[idx]
        if line.find("(")!=-1:
           sep+=" "*3
        if line.find(" JOIN ")!=-1 and line.find("LEFT ")==-1:
           line=line.replace(" JOIN ","\n  %s"%sep+"JOIN ")
        if line:
           nList.append(sep+line)
        if line.find(")")!=-1:
           sep=sep[:-3]
    return '\n'.join(nList)

def getArg(kwargs,key,default):
    arg=default
    if kwargs.has_key(key):
       try:
          arg=kwargs[key]
          if type(default) is types.IntType:
             arg=int(arg)
       except:
          pass
    return arg

def getKeyValue(dict,key):
    if dict.has_key(key): return dict[key]
    return ""

def genMartList(kwargs,key,extra_idx=-1):
    idx=0
    oList=[]
    if kwargs.has_key(key):
       idx=0
       if  type(kwargs[key]) is types.ListType:
           for o in kwargs[key]:
               if extra_idx!=-1:
                  oList.append((idx,o,extra_idx))
               else:
                  oList.append((idx,o))
               idx+=1
       else:
           if extra_idx!=-1:
               oList.append((idx,kwargs[key],extra_idx))
           else:
               oList.append((idx,kwargs[key]))
    return oList

def swapDict(original_dict):
    return dict([(v, k) for (k, v) in original_dict.iteritems()])
def swapDict2(d):
    new = {}
    for key, value in d.items():
        if value in new.keys():
            new[value].append(key)
        else:
            new[value] = [key]
    return new

def normUrl(url):
    if  url.find("http://") == -1 or url.find("https://") == -1:
        return url
    try:
        path = url.split("http://")[1]
        return "http://"+os.path.normpath(path)
    except:
        try:
            path = url.split("https://")[1]
            return "https://"+os.path.normpath(path)
        except:
            raise "Fail to normalize URL",url
    
def parseKeywordInput(input,tableCol,keyword='like',valList=['like','and','or','not','(',')'],type='oracle'):
    oDict={}
    c=0
    s=""
    sList = input.replace("("," ( ").replace(")"," ) ").split()
    for item in sList:
        if item.lower().find(keyword)!=-1:
           k,v=item.split(":")
           bindKey=":p%s"%c
           if v.find("*")!=-1: val=v.replace('*','%')
           elif v.find("%")!=-1: val=v
           else: val="%%%s%%"%v
           oDict[bindKey[1:]]=val
           c+=1
           s+=" %s %s "%(keyword,bindKey) 
        else: 
           if not valList.count(item.lower()):
              raise "Invalid input keyword='%s'"%item
           s+=" %s "%item
    s=' '.join(s.split())
    # insert tableCol in proper place of the statement
    s=s.replace("like","%s like"%tableCol)
    # now replace not tableCol to be tableCol not
    s=s.replace("not %s"%tableCol,"%s not"%tableCol)
    if type=='oracle':
       s+=" ESCAPE '\\'"
    return s,oDict

def tip():
    idx = random.randint(0,len(TIPS)-1)
    return TIPS[idx]

SYMBOLS_LIST=[('+','__pl__'),('-','__mi__'),('/','__sl__'),('#','__po__')]

def timeGMT(iTime):
    try:
        return time.strftime("%d %b %Y %H:%M:%S GMT",time.gmtime(long(iTime)))
    except:
        try:
            return convertDBS2DDTime(iTime)
        except:
            return iTime
#            return "Unknown time format, iTime=%s"%iTime

def timeGMTshort(iTime):
    try:
       return time.strftime("%d/%m/%y",time.gmtime(long(iTime)))
    except:
       return "Unknown time format, iTime=%s"%iTime

def parseBLOBdata(data):
    return str(data).replace(",",", ").replace(";","; ")

### Natural sorting, taken from,http://aspn.activestate.com/ASPN/Cookbook/Python/Recipe/285264
digitsre=re.compile(r'\d+')         # finds groups of digits
D_LEN=3

def _decor(s):
    '''decorate function for sorting alphanumeric strings naturally'''
    return digitsre.sub(lambda s: str(len(s.group())).zfill(D_LEN)+s.group(),s)

def _rem_len(s):
    '''sub function for undecor - removes leading length digits'''
    return s.group()[D_LEN:]

def _undecor(s):
    '''undecorate function for sorting alpha strings naturally'''
    return digitsre.sub(_rem_len,s)

def natsort(list_):
    '''sort a list in natural order'''
    tmp=[_decor(s) for s in list_]
    tmp.sort()
    return [_undecor(s) for s in tmp]

def natsort24(list_):
    '''Python 2.4 version'''
    return [_undecor(s) for s in sorted([_decor(s) for s in list_])]

###

def parseCreatedBy(input):
    if input and type(input) is types.StringType and input.find('/CN'):
       try:
           dnList=input.split('/')
           nameList=[]
           for item in dnList:
               if item[:2]=="CN":
                  for elem in item[3:].split():
                      if not re.match('[0-9]',elem):
                         nameList.append(elem)
           return ' '.join(nameList)
       except:
           pass
    return input

def encode(dataset):
    for s in SYMBOLS_LIST:
        dataset=string.replace(dataset,s[0],s[1])
    return dataset

def decode(dataset):
    for s in SYMBOLS_LIST:
        dataset=string.replace(dataset,s[1],s[0])
    return dataset

def nPages(tot,max):
    if tot%max:
       return (tot-tot%max)/max+1
    return tot/max

def findRssFiles(dir):
    oList=[]
    for item in os.walk(dir):
        if item[2] and len(item[2])==1 and item[2][0]=='rss.xml':
           oList.append('%s/%s'%(item[0],item[2][0]))
    return oList

def uniqueList(alist):
    set = {}
    map(set.__setitem__, alist, [])
    return set.keys()

def convertListToString(iList):
    s="["
    for item in iList:
        s+="'%s'"%item+","
    s=s[:-1]+"]"
    return s

def sortSitesByDomain(siteList):
    siteDict={}
    for site in siteList:
        components=site.split(".")
        domain=components[-1]
        if siteDict.has_key(domain):
           siteDict[domain]+=[site]
        else:
           siteDict[domain]=[site]
    return siteDict

def getListOfSites(dbsInst='all'):
    """
       Generats list of DLS sites out given DBS instance and DLS_INFO (dls.all) file.
    """
    fName = DLS_INFO
    f=open(fName,'r')
    sList=[]
    for item in f.readlines():
        try:
           dbs,site=string.split(item)
        except:
           pass
           continue
        if string.lower(dbsInst)=='all':
           if not sList.count(site):
              sList.append(site)
        else:
           if dbs==dbsInst:
              if not sList.count(site):
                 sList.append(site)
    f.close()
    sList.sort()
    return sList

def colorSizeHTMLFormat(i):
    n = sizeFormat(i)
    # PB are in red
    if string.find(n,'PB')!=-1:
       return string.replace(n,'PB','<span class="box_red">PB</span>')
    # TB are in blue
    elif string.find(n,'TB')!=-1:
       return string.replace(n,'TB','<span class="box_blue">TB</span>')
    # GB are in block
    # MB are in green
    elif string.find(n,'MB')!=-1:
       return string.replace(n,'MB','<span class="box_green">MB</span>')
    # KB are in lavender
    elif string.find(n,'KB')!=-1:
       return string.replace(n,'KB','<span class="box_lavender">KB</span>')
    else:
       return n
    
def sizeFormat(i):
    """
       Format file size utility, it converts file size into KB, MB, GB, TB, PB units
    """
    try:
       num=long(i)
    except:
       return "N/A"
    for x in ['','KB','MB','GB','TB','PB']:
#        if num<1024.:
#            return "%3.1f%s" % (num, x)
#        num /=1024.
        if num<1000.:
            return "%3.1f%s" % (num, x)
        num /=1000.

def formatLumi(int_lumi,html=1):
    try:
       lumi= int_lumi['integrated_luminosity']
       err = int_lumi['error']
       if type(lumi) is types.StringType or type(lumi) is types.UnicodeType:
          lumi=eval(lumi)
       if type(err) is types.StringType or type(err) is types.UnicodeType:
          err =eval(err)
    except:
       pass
       return "N/A"
    if html:
       return """<span>%.2e &#177; %.2f</span>"""%(lumi,err)
    else:
       return "%.2e +- %.2f"%(lumi,err)

def splitString(s,size,separator=' '):
    _size=size
    # take care of HTML symbols, like &amp; by looking around for & and ;
    if len(s)>size:
       if string.find(s[:size],"&")!=-1:
          n=len(s)
          if n>size+5: n = size+5
          pos = string.find(s[0:n],";")
          if pos!=-1: size=pos+1
       return s[0:size]+separator+splitString(s[size:],_size,separator)
    else:
       return s
def splitString_orig(s,size,separator=' '):
    if len(s)>size:
       return s[0:size]+separator+splitString(s[size:],size)
    else:
       return s

def findInString(s,pat1,pat2,oList=[]):
    idx1 = s.find(pat1)
    idx2 = s.find(pat2)
    if idx1==-1:
       return
    if idx2==-1:
       oList.append(s[idx1+len(pat1):])
       return
    oList.append(s[idx1+len(pat1):idx2])
    return findInString(s[idx2+len(pat2):],pat1,pat2,oList)

def createImage():
    import Image, ImageDraw, ImageFont
    i = Image.new("RGB", (50,50))
    d = ImageDraw.Draw(i)
    #f = ImageFont.truetype("Arial.ttf", 8)
    #d.text((0,0), "hello world", font=f)
    d.text((0,0), "hello world")
    i.save(open("helloworld.png", "wb"), "PNG")


def printDictForJS(dict,space=""):
    """
Print the dictionary
pythonDict = { DBSInst: { dbs:appDict={ app:primDict={ primD:tierDict={ tier:null } } }, } }
For instance,

::
    {'dbs2': {'a20': {'p21': {'t23': []}, 'p20': {'t21': [], 't22': []}}, 'a21': {'p21': {'t22': []}}}, 'dbs1': {'a10': {'p10': {'t11': [], 't12': []}, 'p12': {'t13': []}}, 'a12': {'p10': {'t12': []}}}}

    { menuList: ["dbs2","dbs1"],
      nextObj : {"dbs2": { menuList: ["a20","a21"],
                           nextObj : {"a20": { menuList: ["p21","p20"],
                                               nextObj : {"p21": { menuList: ["t23"], nextObj:null}
                                                         ,"p20": { menuList: ["t21","t22"], nextObj:null}
                                                         }

                                             }
                                     ,"a21": { menuList: ["p21"],
                                               nextObj : {"p21": { menuList: ["t22"], nextObj:null}
                                                         }
                                             }
                                     }
                         }
                ,"dbs1": { menuList: ["a10","a12"],
                           nextObj : {"a10": { menuList: ["p10","p12"],
                                               nextObj : {"p10": { menuList: ["t11","t12"], nextObj:null}
                                                         ,"p12": { menuList: ["t13"], nextObj:null}
                                                         }
                                             }
                                     ,"a12": { menuList: ["p10"],
                                               nextObj : {"p10": { menuList: ["t12"], nextObj:null}
                                                         }
                                             }
                                     }
                         }
                }
    }
@type   dict: dictionary
@param  dict: input dictionary which we want to printout
@type space: string
@param space: space separator
@rtype  : dictionary
@return : { DBSInst: { dbs:appDict={ app:primDict={ primD:tierDict={ tier:null } } }, } }
    """
    ads = len("{ menuList: ")
    s = "\n"+space
    s+= "{ menuList: ["
    keyList=dict.keys()
    keyList.sort()
    if type(dict[keyList[0]]) is types.DictType:
       keyList.reverse()
    deadEnd=0
    for key in keyList:
        s+='\"%s\"'%key
        if key!=keyList[-1]: s+=","
        if type(dict[key]) is not types.DictType: deadEnd=1
    s+="],\n"
    if  deadEnd:
        s+=space+"  nextObj:null"
    else:
        s+=space+"  nextObj : {"
        for key in keyList:
            s+='\"%s\": '%key
            if type(dict[key]) is types.DictType: 
    #           s+=printDictForJS(dict[key],space+" "*ads)
               s+=printDictForJS(dict[key])
            else:
               s+="\"%s\":null"%key
               if key!=keyList[-1]: s+=","
            if key!=keyList[-1]: s+=","
        s+=space+"}\n" # end of nextObj
    s+=space+"}\n" # end of menuList
    return s

def printMsg(msg):
    """
       @type  msg: string
       @param msg: input message
       @rtype : string
       @return: formatted output
    """
    print msg
    s = ""
    for i in xrange(0,len(msg)):
        s+="-"
    print s

def getExceptionInHTML():
    """
       returns exception type/value in HTML form
    """
    exp = sys.exc_info()
    e1  = str(exp[0]).replace('<','&lt;').replace('>','&gt;')
    e2  = str(exp[1]).replace('<','&lt;').replace('>','&gt;')
#    e1  = str(exp[0]).replace('\n',' ').replace('<','&lt;').replace('>','&gt;')
#    e2  = str(exp[1]).replace('\n',' ').replace('<','&lt;').replace('>','&gt;')
    msg = """
    <table>
    <tr>
    <td align="right" valign="top"><b>Exception type:</b></td>
    <td><pre>%s</pre></td>
    </tr>
    <tr>
    <td align="right" valign="top"><b>Exception value:</b></td>
    <td><pre>%s</pre></td>
    </tr>
    </table>
    """%(e1,e2)
    return msg

def getExcept(_msg=None):
    """
       return exception type, value and traceback in a message
    """
    msg = ""
    if _msg:
       msg=_msg
    msg += getExcMessage()
#    msg+="Exception type: \n%s\n\n"%sys.exc_info()[0]
#    msg+="Exception value: \n%s\n\n"%sys.exc_info()[1]
#    msg+="Traceback: \n"
#    for m in traceback.format_tb(sys.exc_info()[2]):
#        msg+=m
#    msg+="\n\n"
    return msg

def printExcMessage():
    counter=0
    for m in  traceback.format_exc().split("\n"):
        if m.find(" raise ")!=-1:
           counter=1
           continue
        if counter: print m

def getExcMessage(userMode='user'):
    exStr="%s"%sys.exc_type
    if userMode=='dbsExpert': return traceback.format_exc()
    if  exStr.find(".")==-1: 
        ex = "raise "
    else:
        ex=exStr.split(".")[-1]
        ex = ex.replace("'>", "")
    counter=0
    msg=""
    for m in  traceback.format_exc().split("\n"):
#        if m.find(ex)!=-1:
#           counter=1
#           if ex!="raise ":
#              msg+="%s\n"%m
#           continue
#        if counter: msg+="%s\n"%m
        idx = m.rfind(ex)
        if  idx!=-1:
            counter=1
            if  ex!="raise ":
                msg+="%s\n"%m[idx:]
            continue
        if  counter:
            if  idx!=-1:
                msg+="%s\n"%m[idx:]
            else:
                msg+="%s\n"%m
    if not msg: return traceback.format_exc()
    return msg

def findLastBindVar(s):
    sList=s.replace(")","").replace("(","").split()
    sList.reverse()
    for item in sList:
        if item[0]==":":
           return item[1:]

def constructExpression(s,listName):
    """
       For given string 's' and list name construct the expression statement.
       For instance,
       word1 or (word2 and word3)
       converted to
       listName.count(word1) or (listName.count(word2) and listName.count(word3))
       Such expression statement is further used by eval in search method of DDHelper. 
    """
    specialSymbols=["(",")","and","or","not"]
    oList = []
    ss = string.lower(s)
    for elem in specialSymbols:
        item = " %s "%elem
        ss=ss.replace(elem,item)
#    print "looking for",ss
    for elem in string.split(ss):
        if specialSymbols.count(elem):
           oList.append(elem)
        else:
#           oList.append("%s.count('%s')"%(listName,elem))
           oList.append("[s for s in %s if s.find('%s')!=-1]"%(listName,elem))
    result = ' '.join(oList)
#    print "construct",result
    return result

def validator(s):
    """ 
        Evaluate given string 's' and validate if it has correct number of "(" and ")". 
        For instance it check if expression
        (word1 or (test1 and test2) and (w1 or w2) )
        has correct number of open and cloased brackets.
    """
    if  s.count("(")!=s.count(")"):
        return False
    open=0
    for char in s:
        if char=="(" or char==")":
           if char=="(": open+=1
           if char==")": open-=1
    return (not open)


def demanglePattern(pattern):
    """
       Demangle given pattern into elements. Return a list of elements, 
       e.g. /path1/path2/path3 will produce a list ['path1','path2','path3']
       @type  pattern: string
       @param pattern: input pattern
       @rtype : list
       @return: list of compoenents
    """
    if pattern=="*" or not pattern: return ['','','']
    components = string.split(os.path.normpath(pattern),'/')
    if pattern[0]!='/':
       msg = "path pattern '%s' doesn't start with /"%pattern
       raise DbsPatternError(args=msg)
    # replace '*' with '' in pattern, since later we'll skip such where clause
    for idx in xrange(0,len(components)):
        if components[idx]=="*": components[idx]=''
    return components[1:]
        
def list2String(iList,mode="txt"):
    if not len(iList): return ""
    iList.sort()
    s=iList[0]
    for item in iList[1:]:
        if mode=="html":
           s+="<br /> %s"%item
        else:
           s+=", %s"%item
    return s

def printListElements(iList,msg=""):
    """
       Loop over elements in a list and print one in a time on stdout
       @type  iList: list
       @param iList: input list
       @type  msg: string
       @param msg: input message
       @rtype : none
       @return: none
    """
    if msg:
       print
       print "### %s:"%msg
    for item in iList:
        print item 
        
def printDict(iDict):
    for key in iDict.keys():
        print key
        print iDict[key]

def formattingDictPrint(iDict):
    """
       print dictionary in formated way, e.g.
       ::
           {
            'key1': []
            'key2': []
           }
       @type  iDict: dictionary
       @param iDict: input dictionary
       @rtype :  string
       @return:  return formatted representation of dictionary
    """
    s="{\n"
    for key in iDict.keys():
        s+="\"%s\": "%key
        s+=formattingListPrint(iDict[key])
        if iDict.keys()[-1]!=key:
           s+=",\n"
    s+="\n}\n"
    return s
    
def toLower(iList):
    oList=[]
    for i in iList:
        if  type(i) is not types.NoneType:
            try:
               oList.append(string.lower(str(i)))
            except:
               print iList
               raise "fail at lowering '%s'"%i
    return oList

def tupleToList(x):
    """fully copies trees of tuples to a tree of lists.
       deep_list( (1,2,(3,4)) ) returns [1,2,[3,4]]"""
    if type(x)!=type( () ):
        return x
    return map(tupleToList,x)

def formattingListPrint(iList,n=3):
    """
       print list in formated way, e.g.
       ::
           [
            item1,item2,item3,
            item4,item5,item6
           ]
       n provides number of items per line
       @type  iList: list
       @param iList: input list
       @type  n: int
       @param n: number of items per line
       @rtype : string
       @return: return a printed list
    """
    iList.sort()
    iList.reverse()
    s="[\n"
    count = 0
    for idx in xrange(0,len(iList)):
        item = iList[idx]
        if idx!=len(iList)-1:
           s+=" \"%s\","%item 
        else:
           s+=" \"%s\""%item+"\n"
        count+=1
        if count==n:
           s+="\n"
           count=0
    s+="]"
    return s

def sortedDictValues(adict):
    """
       Sort values in a dictinoary
       @type  adict: dictinoary
       @param adict: input dictionary
       @rtype : list
       @return: sorted list of values
    """
    keys = adict.keys()
    keys.sort()
    return map(adict.get, keys)

def addToDict(iDict,key,value):
    """
       Add value as a list to the dictionary for given key.
       @type  key: key type
       @param key: key
       @type  value: value type
       @param value: value
       @rtype : none
       @return: none
    """
    iDict.setdefault(key,[]).append(value)

def getParams(kwargs):
    moreParams=""
    if kwargs:
       for k in kwargs.keys():
           moreParams+="&amp;%s=%s"%(k,urllib.quote(kwargs[k]))
    return moreParams

def monthId(month):
    d={'jan':1,'feb':2,'mar':3,'apr':4,'may':5,'jun':6,'jul':7,'aug':8,'sep':9,'oct':10,'nov':11,'dec':12}
    return d[string.lower(month)[:3]]

def sendEmail(msg):
    """
       Send an Email with given message
    """
    p = os.popen("%s -t" % SENDMAIL, "w")
    p.write("To: vk@mail.lns.cornell.edu\n")
    p.write("From: DBS Data Discovery <cmsdbs@mail.cern.ch>\n")
    p.write("Subject: DBS DD error\n")
    p.write("\n") # blank line separating headers from body
    p.write("\n"+msg+"\n\n\n")
    sts = p.close()
    if sts != 0:
        print "mail exit status", sts

class DDLogger:
  """
     DDLogger class
  """
  def __init__(self, verbose=0):
      """
         Logger constructor. 
      """
      self.verbose = verbose

  def writeLog(self,msg):
      """
          Write given message to the logger
          @type  msg: string
          @param msg: message
          @rtype : none
          @return: none
      """
      if  self.verbose:
          print msg

def setSQLAlchemyLogger(hdlr,logLevel):
    # set up logging for SQLAlchemy
    logging.getLogger('sqlalchemy.engine').setLevel(logLevel)
    logging.getLogger('sqlalchemy.orm.unitofwork').setLevel(logLevel)
    logging.getLogger('sqlalchemy.pool').setLevel(logLevel)

    logging.getLogger('sqlalchemy.engine').addHandler(hdlr)
    logging.getLogger('sqlalchemy.orm.unitofwork').addHandler(hdlr)
    logging.getLogger('sqlalchemy.pool').addHandler(hdlr)

def setCherryPyLogger(hdlr,logLevel):
    # set up logging for SQLAlchemy
    logging.getLogger('cherrypy.error').setLevel(logLevel)
    logging.getLogger('cherrypy.access').setLevel(logLevel)

    logging.getLogger('cherrypy.error').addHandler(hdlr)
    logging.getLogger('cherrypy.access').addHandler(hdlr)

def removeEmptyLines(s):
    return ''.join(line for line in s.splitlines(1) if not line.isspace())

def genkey(query):
    """
    Generate a new key for a given query. We use md5 hash for the
    query and key is just hex representation of this hash.
    """
    keyhash = md5.new()
    keyhash.update(query)
    return keyhash.hexdigest()

def textDiff(a, b, th_a="", th_b="", title=""):
    """Takes in strings a and b and returns a human-readable HTML diff."""
    out = []
    a = removeEmptyLines(a).splitlines(1)
    b = removeEmptyLines(b).splitlines(2)
    s = difflib.SequenceMatcher(None, a, b)
    out.append('<p><b>%s</b>\n'%title)
    out.append('<table class="table_diff">\n')
    if title:
       out.append('<tr><th><b>%s</b></th><th></th><th><b>%s</b></th></tr>\n'%(th_a,th_b))
    for e in s.get_opcodes():
        if e[0] == "replace":
           old=''.join(a[e[1]:e[2]])
           new=''.join(b[e[3]:e[4]])
           sep='&hArr;'
           tdOld="from"
           tdNew="to"
        elif e[0] == "delete":
           old=''.join(a[e[1]:e[2]])
           new=''
           sep='&8212;'
           tdOld="delete"
           tdNew=""
        elif e[0] == "insert":
           old=''
           new=''.join(b[e[3]:e[4]])
           sep='+'
           tdOld=""
           tdNew="insert"
        elif e[0] == "equal":
           old=new=''.join(b[e[3]:e[4]])
           sep=tdOld=tdNew=''
        else: 
           out="""<div class="box_red">Unable to diff a file '%s' in '%s' '%s'"""%(title,th_a,th_b)
           return out
        old=string.replace(old,'\n','<br>')
        new=string.replace(new,'\n','<br>')
        s="""<tr><td class="%s">%s</td><td>%s</td><td class="%s">%s</td></tr>\n"""%(tdOld,old,sep,tdNew,new)
        out.append(s)
    out.append('</table></p>\n')
    return ''.join(out)

def getCMSNames():
    """ https://cmsweb.cern.ch/sitedb/sitedb/reports/showXMLReport/?reportid=se_cmsname_map.ini """
    conn     = httplib.HTTPSConnection("cmsweb.cern.ch:443")
    path     = "/sitedb/sitedb/reports/showXMLReport"
    params   = urllib.urlencode({'reportid':'se_cmsname_map.ini'})
    headers  = {"Content-type": "application/x-www-form-urlencoded","Accept": "text/plain"}
    conn.request("POST", path, params, headers)
    response = conn.getresponse()
    data     = response.read()
    oDict    = {}
    if response.status==200:
       elem  = ET.fromstring(data)
       for i in elem:
           if i.tag=="result":
              for j in i:
                  for k in j.getchildren():
                      if k.tag=='se':
                         key=k.text
                      elif k.tag=='name':
                         val=k.text
                         oDict[key]=val
    conn.close()
    oDict['time']=time.time()
    return oDict

def parseDBSQuery(i):
    sql=""
    bDict={}
    val=re.compile("[0-9]")
    for k in i.getchildren():
        name=""
        rval=""
        if k.tag=="sql":
           sql=k.text
        if k.tag=="bindparams":
           for j in k:
               if val.match(j.text) and j.text.find(".")==-1:
                  bDict[j.tag]=int(j.text)
               else:
                  bDict[j.tag]=j.text
    return sql,bDict
def getDBSQuery(data,tag="python_query"):
    elem  = ET.fromstring(data)
    sql   = ""
    bDict = {}
    count_sql = ""
    count_bDict = {}
    for i in elem:
        if i.tag=="python_query":
           sql,bDict=parseDBSQuery(i)
        elif i.tag=="count_query":
           count_sql,count_bDict=parseDBSQuery(i)
    return sql,bDict,count_sql,count_bDict

def getDQInfo(data):
    sysDict = {}
    subDict = {}
    elem  = ET.fromstring(data)
    dot = """
digraph prof {
        size="10,8"; ratio = fill;
        node [style=filled];
"""
    colorGood="[color=\"#00FF00\"]"
    colorBad="[color=\"#FF0000\"]"
    color=""
    for i in elem:
        if i.tag=="run":
           for j in i:
               if j.attrib['value']=="GOOD":
                  color=colorGood
               else:
                  color=colorBad
               if j.tag=="dq_sub_system":
                  addToDict(sysDict,j.attrib['parent'],[j.attrib['name'],j.attrib['value']])
               if j.tag=="dq_sub_subsys":
                  addToDict(subDict,j.attrib['parent'],[j.attrib['name'],j.attrib['value']])
    node=""
    idx=0
    return sysDict,subDict
#
# main
#
if __name__ == "__main__":
#   print formattingListPrint([1,2,3,4,5,6,7,8,9,10])
#   print formattingDictPrint({'test':[1,2,3,4,5,6,7,8,9,10],'vk':[21,22,23,24]})
#   print getDictOfSites()
#   print convertListToString([1,2,3,4,5,6,7,8,9,10])
#   print tip()

#   data=getCMSNames()
#   cmsNames=data.keys()
#   cmsNames.sort()
#   for item in cmsNames: print item

   input="""<?xml version='1.0' standalone='yes'?>
<!-- DBS Version 1 -->
<dbs>
<userinput>
<input>
find dataset where dataset like *
</input>
<timeStamp>Tue May 20 17:38:06 CEST 2008</timeStamp>
</userinput>
<java_query>
<sql>
SELECT DISTINCT 
    Block.Path
FROM
    CMS_DBS_PROD_LOCAL_03.Block

WHERE
    Block.ID  IN ( 
SELECT 
    Block.ID FROM CMS_DBS_PROD_LOCAL_03.Block   WHERE 
    Block.Path LIKE ?)
</sql>
<bp>%</bp>
</java_query>
<python_query>
<sql>
SELECT DISTINCT 
    Block.Path
FROM
    CMS_DBS_PROD_LOCAL_03.Block

WHERE
    Block.ID  IN ( 
SELECT 
    Block.ID FROM CMS_DBS_PROD_LOCAL_03.Block   WHERE 
    Block.Path LIKE :p0)
</sql>
<bindparams>
<p0>%</p0>
</bindparams>
</python_query>
<count_query>
<sql>
SELECT COUNT(*) FROM
    CMS_DBS_PROD_LOCAL_03.Block

WHERE
    Block.ID  IN ( 
SELECT 
    Block.ID FROM CMS_DBS_PROD_LOCAL_03.Block   WHERE 
    Block.Path LIKE ?)
</sql>
<bindparams>
<p0>%</p0>
</bindparams>
</count_query>
<SUCCESS/>
</dbs>
"""
   sql,bindDict,count_sql,count_bindDict=getDBSQuery(input)
   print "Found query",sql,bindDict,count_sql,count_bindDict
