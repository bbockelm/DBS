import os
import commands
import string

from processSQL import processSQL
from writeSingleTablesCpp import writeSingleTablesCpp

from createMultiTableViewObjects import createMultiTableViewObjects

import pdb

def getShort(long):
    short=''
     
    totay=string.split(long, '_') 
    
    if long.find('_') != -1:
      for aTota in totay:
         short+=aTota[0]
    for i in range(len(long)):
       if long[i].isupper(): 
          short+=long[i] 
     
    return short

mapList = []
def writeNameMap(cppClasses, tbl=1):
   mapStrList = []
   global_unique_vars = []
   global_unique_counter = 1 
   #mapStr = "#ifndef _namemap_included_"
   #mapStr += "\n#define _namemap_included_"
   #mapStr +=  "\n#include <string>"
   #mapStr += '\n#include "common.hpp"'
   #mapStr += '\nclass NameMaper {'
   #mapStr += '\npublic:'
   #mapStr += '\n    NameMaper(){'
   mapStr = ""
   newTmp = [] 
   for eachClass in cppClasses:
       #print eachClass.tableName()  
       table = eachClass.tableName()
       stable = getShort(table)
       schemaDict = eachClass.getSchema()
       if type(schemaDict) == type({}) :
          for adict in schemaDict.keys():
             tmp = {}   
             tmp[adict] = schemaDict[adict]    
             newTmp.append(tmp)      
          schemaDict = newTmp
       #print "\n\n"
       #print schemaDict  
       for adict in schemaDict :
         param = adict.keys()[0]
         if param.find('_') != -1 :
           var = getShort(adict.keys()[0])
         else : 
           var = param
         global_var = stable+'.'+var
         if global_var in global_unique_vars :
              var = "%s%d" % (var, global_unique_counter)
              global_unique_counter = global_unique_counter + 1
         global_unique_vars.append(global_var)
         if tbl == 1 :
            entry = table+'.'+param  
            mapStr = '\n        NameMap.insert(Entry("'+table+'.'+param+'", "'+stable+'.'+var+'"));'
            if entry not in mapList:
               mapStrList.append(mapStr) 
               mapList.append(entry)
         else :
            mapStr = '\n        NameMap.insert(Entry("'+param+'", "'+var+'"));'
            if param not in mapList:
               mapList.append(param)
               mapStrList.append(mapStr) 
           #print param, "   ", param 
   #mapStr += '\n }'    
   #mapStr += '\npublic:'
   #mapStr += '\n    Dictionary NameMap;'
   #mapStr += '\n};'    
   #mapStr += "\n#endif"

   return mapStrList

   #print "Generating  NameMaper.hpp....."
   #filepath = "NameMaper.hpp"
   #cppimpl = open(filepath, 'w')
   #cppimpl.writelines(mapStr)
   #cppimpl.write('\n')
   #cppimpl.close()
# 
   #print mapStr

if __name__== '__main__':
   
   print "Processing SQL.."
   #sqlFilename = 'DBS-DB.sql'
   #sqlFilename = 'OracleCMS.sql'
   sqlFilename = 'DBSOracle.sql' 
   sqlProcessor= processSQL(sqlFilename)
   sqlProcessor.run()
   
   print "Generating Single Tables.." 
   singleCppGen = writeSingleTablesCpp()
   singleCppGen.cppClasses = sqlProcessor.cppClasses
 
   #print "JUST FOR TESTING........."
   #singleCppGen.writeHppHeaderFile()

   print "Generating Multi Tables.."  
   multiViewCreator = createMultiTableViewObjects()
   multiViewCreator.initializeAllSchema(sqlProcessor.cppClasses)
   multiViewCreator.buildMultiTableViewObjects()


   mapStr = "#ifndef _namemap_included_"
   mapStr += "\n#define _namemap_included_"
   mapStr +=  "\n#include <string>"
   mapStr += '\n#include "common.hpp"'
   mapStr += '\nclass NameMaper {'
   mapStr += '\npublic:'
   mapStr += '\n    NameMaper(){'

   mapStrList = writeNameMap(singleCppGen.cppClasses, 1)
   for anItem in mapStrList:
      mapStr += anItem


   singleCppGen.cppClasses.extend(multiViewCreator.multiTableViewObjects)
   #mapStrList=[]
   mapStrList = writeNameMap(multiViewCreator.multiTableViewObjects, 0)
   for anItem in mapStrList:
      mapStr += anItem

   mapStr += '\n }'
   mapStr += '\npublic:'
   mapStr += '\n    Dictionary NameMap;'
   mapStr += '\n};'
   mapStr += "\n#endif"

   print "Generating  NameMaper.hpp....."
   filepath = "NameMaper.hpp"
   cppimpl = open(filepath, 'w')
   cppimpl.writelines(mapStr)
   cppimpl.write('\n')
   cppimpl.close()


   print "Writting code.."

   singleCppGen.writeClientDataStructure()


   singleCppGen.writeHppHeaderFile()
   singleCppGen.writeCppImplFile()
   singleCppGen.writeRowNSchemaBindingImpl()
   singleCppGen.writeTableFactoryImpl()
   tmpTxt = singleCppGen.forOtherImpls()
   tmpHandle = open ("OtherImpls.code", 'w')
   tmpHandle.writelines(tmpTxt)
   tmpHandle.write('\n') 

   #print tmpTxt
   print "\n\n********************************************"
   print "Some code also saved in OtherImpls.code, HAS To be used"
   print "********************************************\n\n"
   tmpHandle.close()

   #multiViewCreator = createMultiTableViewObjects()
   #multiViewCreator.initializeAllSchema(sqlProcessor.cppClasses)
   #multiViewCreator.buildMultiTableViewObjects()
   
   #for amultiView in multiViewCreator.multiTableViewObjects:
      #print "\n>>>>>>>>JOIN CONDITIONS>>>>>"+amultiView.className()+">>>>>>>>>>>>>>>>>>"
      #print amultiView.getConditions()

      #print "\nCLASS DEF\n" 
      #print amultiView.writeClassDef()
      #print "\n\nCLASS IMPL"
      #print amultiView.writeClassImpl()

        
      #print "\nTABLES: ", amultiView.names()
      #print "\nPRIMARY KEY: ", amultiView.primaryKey()
      #print "\nUNIQUE KEY: ", amultiView.uniqueKeys()
      #print "\nFOREIGN KEY: ", amultiView.foreignKeys()
      #print "\nNOT NULL KEY: ", amultiView.notNull()
      #print "\nAttributes :", amultiView.attributes()
      #print "\nSCHEMA ORDER: ", amultiView.schemaOrder()[0].names()[0]
      #print "\nSCHEMA INFO: ", amultiView.schemaInfo()
      #print "\nSCHEMAname2SchemaMap: ", amultiView.schemaNameToSchemaMap()

#   sqlProcessor= processSQL(sqlFilename)
#   sqlProcessor.run()
#   for table in sqlProcessor.cppClasses :
#       print table.classname
#       print table.primarykeys
#       print table.foreignkeys
#       print table.uniquekeys
#       print table.notnulls
#       print table.schema
#       print table.constraints
#       print table.references
         
 
