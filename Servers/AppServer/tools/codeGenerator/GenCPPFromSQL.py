import os
import commands
import string

from processSQL import processSQL
from writeTablesIntoCpp import writeTablesIntoCpp

from createMultiTableViewObjects import createMultiTableViewObjects


if __name__== '__main__':
   
   print "Processing SQL.."
   #sqlFilename = 'DBS-DB.sql'
   #sqlFilename = 'OracleCMS.sql'
   sqlFilename = 'DBSOracle.sql' 
   #sqlFilename = 'DBSOracleHistory.sql'

   # CPP Implementation Generator
   cppGenerator = writeTablesIntoCpp()

   print "Processing SQL File: ", sqlFilename
   print "And Generating Single Tables.." 
   sqlProcessor= processSQL(sqlFilename)
   sqlProcessor.run()
    
   #sqlProcessor.cppClasses contains CPP Representation of SQL Tables 
   cppGenerator.cppClasses.extend(sqlProcessor.cppClasses)
 
   #sqlProcessor.cppClasses are used to generated views defined in createMultiTableViewObjects
   print "Generating Multi Tables.."  
   multiViewCreator = createMultiTableViewObjects()
   multiViewCreator.initializeAllSchema(sqlProcessor.cppClasses)
   multiViewCreator.buildMultiTableViewObjects()

   #Add the Multi View Classes too to cpp classes
   #multiViewCreator.multiTableViewObjects contains the Multi View CPP representatioons
   cppGenerator.cppClasses.extend(multiViewCreator.multiTableViewObjects)


   print "Writting code.."

   # Write the name mapper class
   print "Generating  NameMap...."
   mapStrList=[]
   mapStrListSingle = cppGenerator.generateNameMap(sqlProcessor.cppClasses, 1)
   mapStrListMulti = cppGenerator.generateNameMap(multiViewCreator.multiTableViewObjects, 0)
   mapStrList.extend(mapStrListSingle)
   mapStrList.extend(mapStrListMulti)
   cppGenerator.writeNameMaper(mapStrList)  

   #cppGenerator.writeClientDataStructure()
   cppGenerator.writeHppHeaderFile()
   cppGenerator.writeCppImplFile()
   cppGenerator.writeRowNSchemaBindingImpl()
   cppGenerator.writeTableFactoryImpl()

   # Generate the Template Instances
   cppGenerator.writeTemplateInstances()
         
 
