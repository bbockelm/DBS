import os
import commands
import string
from rowRepresentation import rowRepresentation

import pdb
class processSQL :
   """Class that processes a SQL file and extracts all information
        in form of rowRepresentation classes
   """
   def __init__(self, sqlfilename) :
      """Constructor takes a sql file name as input"""
      file = file=open(sqlfilename,'r')
      self.alllines=file.readlines()
      file.close()
 
   def run(self):     
      """Process the input sql file"""
      for line in self.alllines:
         line = line.strip()
         if line == "":
            continue
         if line.find('=') != -1:
            continue
         if (line.find('CREATE TABLE') != -1) or \
                  (line.find('create table') != -1):
            currentClassBaseName=line.split()[2]
            currentClassName = currentClassBaseName+'Row'
            newCurrentClass = rowRepresentation(currentClassName)
            continue
         if line == '(':
            continue
         if line == ');':
            self.cppClasses.append(newCurrentClass)
            continue
         if (line.find('CHECK') != -1) or (line.find('check') != -1) and (line.find('checksum') == -1):
            #if line.split()[0].endswith('check') == True: 
            continue   
         #if line.find('is_primary') != -1 : 
         #   # well yes this is ahack to avoid 'is_primary variable'
         #   continue
               
         if line.find('primary') != -1 and line.find('is_primary') == -1 :
            if line.split()[0].endswith('primary') == True:
              token1=string.split(line)[1]
              token2=string.split(token1, '(')[1] 
              paramNames=string.split(token2, ')')[0]
              possiblekeys=paramNames.split(',')
              for paramName in possiblekeys :
                 constraint={}
                 constraint[paramName] = "PRIMARYKEY"
                 newCurrentClass.primarykeys.append(paramName)
              continue
         if line.find('foreign') != -1:
            token1=string.split(line)[1]
            token2=string.split(token1, '(')[1]
            paramName=string.split(token2, ')')[0]
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
            if line.find('on delete') != -1 :
               deleterule=string.split(line, "on delete")[1].split()[0].strip(',')
               constraint={} 
               constraint[paramName] ="DELETERULE:"+ deleterule
               newCurrentClass.constraints.append(constraint)
            continue
         if line.find('unique(') != -1:
            token1=string.split(line,'(')[1]
            token2=string.split(token1,')')[0]
            params=string.split(token2,',')
            uniq = [] 
            for aparam in params:
               paramName=string.strip(aparam)
               uniq.append(paramName)
            newCurrentClass.uniquekeys.append(uniq)
            continue   

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



