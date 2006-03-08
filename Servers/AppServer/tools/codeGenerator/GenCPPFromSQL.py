import os
import commands
import string

from processSQL import processSQL
from writeTablesIntoCpp import writeTablesIntoCpp
from createMultiTableViewObjects import createMultiTableViewObjects
from writeSWIGInterface import writeSWIGInterface
from writePythonTests import writePythonTests


if __name__== '__main__':
   
   #sqlFilename = 'DBS-DB.sql'
   #sqlFilename = 'OracleCMS.sql'
   sqlFilename = 'DBSOracle.sql' 
 
   print "Processing DDL from ....", sqlFilename
   #sqlFilename = 'DBSOracleHistory.sql'

   # CPP Implementation Generator
   cppGenerator = writeTablesIntoCpp()

   print "Generating Single Tables.." 
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

   # Write the name mapper class
   print "Generating  NameMapper...."
   mapStrList=[]
   mapStrListSingle = cppGenerator.generateNameMap(sqlProcessor.cppClasses, 1)
   mapStrListMulti = cppGenerator.generateNameMap(multiViewCreator.multiTableViewObjects, 0)
   mapStrList.extend(mapStrListSingle)
   mapStrList.extend(mapStrListMulti)
   cppGenerator.writeNameMaper(mapStrList)  

   #cppGenerator.writeClientDataStructure()


   print "Writting Header Files"  
   cppGenerator.writeHppHeaderFile()
   print "Writting Cpp Implementation Files"
   cppGenerator.writeCppImplFile()
   cppGenerator.writeRowNSchemaBindingImpl()
   cppGenerator.writeTableFactoryImpl()

   # Generate the Template Instances
   cppGenerator.writeTemplateInstances()
   
   print "Writting Biz layer ManagerImpls for Query Objects"
   # Always call this AFTER ALLL Classes Single and Multi are addded to Generator      
   cppGenerator.writeManagerImpls() 
   cppGenerator.writeManagerHpp()

   print "Writting SWIG Interface for Query Objects"
   # Let us generate the SWIG Interface file  
   writeSWIGInterface(multiViewCreator.multiTableViewObjects).write()

   print "Writting Python tests for Query Objects"
   # Geenerate Python test in SwitTests.py file 
   writePythonTests(multiViewCreator.multiTableViewObjects).write() 

