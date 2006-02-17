import os
import commands
import string
from rowRepresentation import rowRepresentation
import pdb
import sys
import traceback


def formatExceptionInfo(maxTBlevel=5):
    cla, exc, trbk = sys.exc_info()
    excName = cla.__name__
    try:
        excArgs = exc.__dict__["args"]
    except KeyError:
        excArgs = "<no args>"
    excTb = traceback.format_tb(trbk, maxTBlevel)
    return (excName, excArgs, excTb)

class processSQL :
   """Class that processes a SQL file and extracts all information
        in form of rowRepresentation classes
   """
   def __init__(self, sqlfilename) :
      """Constructor takes a sql file name as input"""
      file = file=open(sqlfilename,'r')
      self.alllines=file.readlines()
      file.close()

   def findClass(self, tablename):
               
       #for aClass in self.cppClasses : print aClass.tableName()
  
       for aClass in self.cppClasses :
          if aClass.tableName() == tablename :
            return aClass
       #pdb.set_trace() 
       print "SERIOUS ERROR: Table Not Yet Defined in SQL, Cannot Alter it"
       return None
 
   def run(self):     
      """Process the input sql file"""
      for line in self.alllines:
       try:
         #print line
         line = line.strip()
         if line.startswith('/*') and line.find('*/') != -1 :
            #pdb.set_trace()
            after=line.find('*/')+2 
            line=line[after:].strip()

         if line.find('/*') != -1 :
            #pdb.set_trace()
            indxb4comment=line.find('/*')
            lineb4comment=line[:indxb4comment]
            indxbAftercomment=line.find('*/')+2
            lineAftercomment=line[indxbAftercomment:]
            line=lineb4comment+' '+lineAftercomment
            #print line

         if line.find("create sequence") != -1 :
            continue 
         if line == "":
            continue
         if line.startswith('--') :
            continue  
         if line.find('add constraint') != -1:
            continue
         if line.find('using index') != -1 :
            continue
         if line.find('tablespace') != -1 :
            continue
         if line.startswith('on '):
            continue
         if line.startswith("alter table") :
            tablename = line.split()[2]
            newCurrentClass = self.findClass(tablename)
            continue
         if line.find('=') != -1:
            continue
         if (line.find('CREATE TABLE') != -1) or \
                  (line.find('create table') != -1):
            currentClassBaseName=line.split()[2]
            currentClassName = currentClassBaseName+'Row'
            #print "CREATING TABLE", currentClassName
            newCurrentClass = rowRepresentation(currentClassName)
            continue
         if line.startswith("create") :
            continue
         if line.startswith('('):
            line = line.split('(')[1]
            self.readParam(line, newCurrentClass)
            continue
            #');'
         if (line.find('CHECK') != -1) or (line.find('check') != -1):
            #if line.split()[0].endswith('check') == True:
            continue

         #if line == '(':
         #   continue
         #if line == ');':
         #   self.cppClasses.append(newCurrentClass)
         #   continue
         if (line.find('CHECK') != -1) or (line.find('check') != -1):
            #if line.split()[0].endswith('check') == True: 
            continue   
         #if line.find('is_primary') != -1 : 
         #   self.readParam(line, newCurrentClass)
            # well yes this is ahack to avoid 'is_primary variable'
            continue
         if line.find('primary') != -1 and line.find('is_primary') == -1:
            if line.split()[0].endswith('primary') == True:
              token1=string.split(line, '(')[1]
              paramName=string.split(token1, ')')[0]
              constraint={}
              constraint[paramName] = "PRIMARYKEY"
              newCurrentClass.primarykeys.append(paramName)
              continue
         if line.find('foreign') != -1:
            token1=string.split(line, 'foreign key (')[1]
            paramName=string.split(token1, ')')[0]
            constraint={}
            constraint[paramName] =  "FOREIGNKEY"
            newCurrentClass.foreignkeys.append(paramName)
            token3=string.split(line, 'references')[1].strip()
            referencedTable=string.split(token3, '(')[0].strip()
            token4=string.split(token3, '(')[1]
            referencedParam=string.split(token4, ')')[0]
            reference={}
            reference[paramName] = referencedTable+'.'+referencedParam
            newCurrentClass.references.append(reference)
            if line.find('on update') != -1 :
               updaterule=string.split(line, "on update")[1].split()[0].strip()
               constraint={}
               constraint[paramName] = "UPDATERULE:"+updaterule
               newCurrentClass.constraints.append(constraint)
            if line.find('on update') != -1 :
               updaterule=string.split(line, "on update")[1].split()[0].strip()
               constraint={}
               constraint[paramName] = "UPDATERULE:"+updaterule
               newCurrentClass.constraints.append(constraint)
            if line.find('on delete') != -1 :
               deleterule=string.split(line, "on delete")[1].split()[0].strip(',')
               constraint={} 
               constraint[paramName] ="DELETERULE:"+ deleterule
               newCurrentClass.constraints.append(constraint)
            continue
         if line.find('unique') != -1:
            token1=string.split(line,'(')[1]
            token2=string.split(token1,')')[0]
            params=string.split(token2,',')
            uniq = [] 
            for aparam in params:
               paramName=string.strip(aparam)
               uniq.append(paramName)
            newCurrentClass.uniquekeys.append(uniq)
            continue   
         #if line.find('unique') != -1:
         #   pdb.set_trace() 
         #   token1=string.split(line,'(')[1]
         #   token2=string.split(token1,')')[0] 
         #   params=string.split(token2,',')
         #   uniq = []
         #   for aparam in params:
         #      paramName=string.strip(aparam)
         #      uniq.append(paramName)
         #   newCurrentClass.uniquekeys.append(uniq)
         #   continue
#
         #print "HERE: ",line
         #paramsDesc = string.split(line)
         #paramName = paramsDesc[0]
         #paramType = string.strip(paramsDesc[1],',')
         #paramType = paramType.split(')')[0]
         #if not paramType in self.sqlcpptypemap.keys():
         #   continue
         #print paramType
         #if paramType.find('varchar') != -1:
         #   paramType = 'varchar'
         #if paramType.find('char') != -1:
         #   if paramType.find('varchar') == -1:
         #      paramType = 'char'
         #if paramType.find('numeric') != -1:
         #   paramType = 'numeric'
         #paramType =    self.sqlcpptypemap[paramType]
         #attribute={}
         #attribute[paramName]=paramType
         #newCurrentClass.schema.append(attribute)
      
         #if line.find('not null') != -1 :
         #   constraint={}
         #   constraint[paramName] = "NOTNULL"
         #   newCurrentClass.notnulls.append(paramName)
         #if line.find('unique') != -1:
         #   uniq = []
         #   uniq.append(paramName)
         #   newCurrentClass.uniquekeys.append(uniq)
         #   continue
         if line.find(');') != -1 :
            #pdb.set_trace() 
            line = line.split(');')[0]
            if (line.strip() != '') :
               self.readParam(line, newCurrentClass)
            self.cppClasses.append(newCurrentClass)
            continue
         self.readParam(line, newCurrentClass)
       except:
         #pdb.set_trace()
         print formatExceptionInfo()
         if line.find('/*') != -1 or line.find('*/') != -1 :
            print "SERIOUS WARNING: A Comment Line found cannount parse" 
         print line
         pass  
          
   def readParam(self, line, newCurrentClass):
         #print "LINE: ", line
         paramsDesc = string.split(line)
         paramName = paramsDesc[0]
         paramType = string.strip(paramsDesc[1],',')
         if paramType.find('varchar') != -1:
            paramType = 'varchar'
         if paramType.find('char') != -1:
            if paramType.find('varchar') == -1:
               paramType = 'char'
         if paramType.find('numeric') != -1:
            paramType = 'numeric'
         if paramType.find(')') != -1 :
            paramType = paramType.split(')')[0]
         paramType =    self.sqlcpptypemap[paramType]
         attribute={}
         attribute[paramName]=paramType
         newCurrentClass.schema.append(attribute)

         if line.find('not null') != -1 :
            constraint={}
            constraint[paramName] = "NOTNULL"
            newCurrentClass.notnulls.append(paramName)
         if line.find('unique') != -1:
            uniq = []
            uniq.append(paramName)
            newCurrentClass.uniquekeys.append(uniq)

   cppClasses=[]
   sqlcpptypemap = {'int':'INTEGER', 'integer':'INTEGER', \
                    'varchar': 'STRING','float':'FLOAT', \
                    'date':'STRING', 'numeric':'INTEGER',   \
                    'smallint':'INTEGER', 'char':'CHARACTER', }



