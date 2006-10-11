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
import string, sys, time, types, logging, traceback

# import DBS modules
import dbsException
import DBSOptions
from   dbsApi import DbsApi, DbsApiException, InvalidDataTier

# file created by crontab job, see getDLSsites.sh
DLS_INFO='dls.all'

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
       return string.replace(n,'PB','<span class="box_red">TB</span>')
    # TB are in blue
    elif string.find(n,'TB')!=-1:
       return string.replace(n,'TB','<span class="box_blue">TB</span>')
    # GB are in block
    # MB are in green
    elif string.find(n,'MB')!=-1:
       return string.replace(n,'MB','<span class="box_green">TB</span>')
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

def printExcept(msg=""):
    """
       print exception type, value and traceback on stderr
       @type  msg: string
       @param msg: message
       @rtype : none
       @return: none
    """
    if msg:
       print msg
    sys.excepthook(sys.exc_info()[0],sys.exc_info()[1],sys.exc_info()[2])

def constructExpression(s,listName):
    """
       For given string 's' and list name construct the expression statement.
       For instance,
       word1 or (word2 and word3)
       converted to
       listName.count(word1) or (listName.count(word2) and listName.count(word3))
       Such expression statement is further used by eval in search method of DBSHelper. 
    """
    specialSymbols=["(",")","and","or"]
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
           oList.append("%s.count('%s')"%(listName,elem))
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

class DbsPatternError(DbsApiException):
  """
     DBS pattern error handler class
  """
  def __init__ (self, **kwargs):
      """
         @type  kwargs: dict
         @param kwargs: input dict 
         @rtype : 
         @return: 
      """
      DbsApiException.__init__(self, **kwargs)

class DbsDatabaseError(DbsApiException):
  """
      DBS error handler class
  """
  def __init__ (self, **kwargs):
      """
         @type  kwargs: dict
         @param kwargs: input dict
         @rtype : none
         @return: none
      """
      printExcept()
      DbsApiException.__init__(self, **kwargs)

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
#def addToDict(iDict,key,value,unique="False"):
#    """Add value as a list to the dictionary for given key. If dictionary contains such key, update
#    its list with given value. Return dictionary itself."""
#    if iDict.has_key(key):
#       if unique:
#          if not iDict[key].count(value):
#             iList = iDict[key]+[value]
#             iDict[key] = iList
#       else:
#          iList = iDict[key]+[value]
#          iDict[key] = iList
#    else:
#       if type(value) is types.ListType:
#          iDict[key]=value
#       else:
#          iDict[key]=[value]
#    return iDict

class DBSLogger:
  """
     DBSLogger class
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
      else:
         self.logLevel = logging.CRITICAL
      self.name = name
      self.setLogger()

  def writeLog(self,msg):
      """
          Write given message to the logger
          @type  msg: string
          @param msg: message
          @rtype : none
          @return: none
      """
      if self.verbose:
         self.logger.info(msg)

  def setLogger(self):
      """
         Set logger settings, style, format, verbosity.
         @type  self: class object
         @param self: none 
         @rtype : none
         @return: none
      """
      self.logger = logging.getLogger(self.name)
      self.logger.setLevel(self.logLevel)
      ch = logging.StreamHandler()
      ch.setLevel(self.logLevel)
      formatter = logging.Formatter("%(asctime)s - %(name)s - %(levelname)s - %(message)s")
      ch.setFormatter(formatter)
      self.logger.addHandler(ch)

#
# main
#
if __name__ == "__main__":
#   print formattingListPrint([1,2,3,4,5,6,7,8,9,10])
#   print formattingDictPrint({'test':[1,2,3,4,5,6,7,8,9,10],'vk':[21,22,23,24]})
   print getDictOfSites()
