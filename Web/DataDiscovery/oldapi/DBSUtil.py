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
import os, string, sys, time, types, logging, traceback, random, difflib

logging.basicConfig(level=logging.DEBUG,
                format='%(asctime)s - %(name)s - %(levelname)s - %(message)s',
                filename='/tmp/DataDiscovery_debug.log',
                filemode='w')
logging.getLogger('sqlalchemy.engine').setLevel(logging.NOTSET)
logging.getLogger('sqlalchemy.orm.unitofwork').setLevel(logging.NOTSET)
logging.getLogger('sqlalchemy.pool').setLevel(logging.NOTSET)

# import DBS modules
import DBSOptions

SENDMAIL = "/usr/sbin/sendmail" # sendmail location
RES_PER_PAGE=5 # number of results per page shown
GLOBAL_STEP =5 # number of iterators shown in Results bar

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

def tip():
    idx = random.randint(0,len(TIPS)-1)
    return TIPS[idx]

SYMBOLS_LIST=[('+','__pl__'),('-','__mi__'),('/','__sl__'),('#','__po__')]

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
    num=long(i)
    for x in ['','KB','MB','GB','TB','PB']:
        if num<1024.:
            return "%3.1f%s" % (num, x)
        num /=1024.

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
    msg = """
    <table>
    <tr>
    <td align="right"><b>Exception type:</b></td>
    <td><em>%s</em></td>
    </tr>
    <tr>
    <td align="right"><b>Exception value:</b></td>
    <td><em>%s</em></td>
    </tr>
    </table>
    """%(exp[0],exp[1])
    return msg

def getExcept():
    """
       return exception type, value and traceback in a message
    """
    msg ="Exception type: \n%s\n\n"%sys.exc_info()[0]
    msg+="Exception value: \n%s\n\n"%sys.exc_info()[1]
    msg+="Traceback: \n"
    for m in traceback.format_tb(sys.exc_info()[2]):
        msg+=m
    msg+="\n\n"
    return msg

def constructExpression(s,listName):
    """
       For given string 's' and list name construct the expression statement.
       For instance,
       word1 or (word2 and word3)
       converted to
       listName.count(word1) or (listName.count(word2) and listName.count(word3))
       Such expression statement is further used by eval in search method of DBSHelper. 
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

def monthId(month):
    d={'jan':1,'feb':2,'mar':3,'apr':4,'may':5,'jun':6,'jul':7,'aug':8,'sep':9,'oct':10,'nov':11,'dec':12}
    return d[string.lower(month)[:3]]

def sendEmail(msg):
    """
       Send an Email with given message
    """
    p = os.popen("%s -t" % SENDMAIL, "w")
    p.write("To: vk@mail.lns.cornell.edu\n")
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
  def __init__(self,name="Logger",verbose=0):
      """
         Logger constructor. 
         @type  name: string
         @param name: name of the logger, default "Logger"
         @type  verbose: boolean or int
         @param : level of verbosity 
         @rtype : none
         @return: none
      """
      if verbose==1:
         self.logLevel = logging.INFO
      elif verbose==2:
         self.logLevel = logging.DEBUG
      else:
         self.logLevel = logging.NOTSET
      self.name = name
      self.setLogger()

  def setLevel(self,level):
      self.logLevel=level

  def writeLog(self,msg):
      """
          Write given message to the logger
          @type  msg: string
          @param msg: message
          @rtype : none
          @return: none
      """
      if self.verbose==1:
         self.logger.info(msg)
      elif self.verbose>=2:
         self.logger.debug(msg)
      else:
         pass

  def setLogger(self):
      """
         Set logger settings, style, format, verbosity.
         @type  self: class object
         @param self: none 
         @rtype : none
         @return: none
      """
      # set up logging to file
      print "\n\nlog level",self.logLevel,self.name
      logging.getLogger('sqlalchemy.engine').setLevel(self.logLevel)
      logging.getLogger('sqlalchemy.orm.unitofwork').setLevel(self.logLevel)
      logging.getLogger('sqlalchemy.pool').setLevel(self.logLevel)

      self.logger = logging.getLogger(self.name)
      self.logger.setLevel(self.logLevel)
      ch = logging.StreamHandler()
      ch.setLevel(logging.INFO)
      formatter = logging.Formatter("%(asctime)s - %(name)s - %(levelname)s - %(message)s")
      ch.setFormatter(formatter)
      self.logger.addHandler(ch)


def removeEmptyLines(s):
    return ''.join(line for line in s.splitlines(1) if not line.isspace())

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
#
# main
#
if __name__ == "__main__":
#   print formattingListPrint([1,2,3,4,5,6,7,8,9,10])
#   print formattingDictPrint({'test':[1,2,3,4,5,6,7,8,9,10],'vk':[21,22,23,24]})
#   print getDictOfSites()
#   print convertListToString([1,2,3,4,5,6,7,8,9,10])
   print tip()
