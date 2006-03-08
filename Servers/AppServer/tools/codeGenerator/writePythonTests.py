#!/usr/bin/env python
import os
import commands
import string
from rowRepresentation import rowRepresentation

class writePythonTests :

   """write dbsclient.i file fow SWIG compilations"""
   def __init__(self, inCppClasses) :
       """Constructor"""
       self.cppClasses = inCppClasses
       self.fileheader = "from SWIGPyTestIncludes import *\n"

   def getParamDict(self, aClass) :
       """ Returns a processed dict of Schema elements instead of Dictionary"""
       paramDict = {}

       schemaDict = aClass.getSchema()
       for anitem in schemaDict.keys():
           if anitem.endswith('.id'):
              continue 
           paramName = anitem.split('.')[1]
           if paramName in ('created_at', 'created_by', 'modified_by', 'modified_at'):
              continue
           paramDict[anitem] = schemaDict[anitem]
       return paramDict 

   def genReadViewPyTest(self, aClass):
       """ Generates the Read Operations Test for Multi Table Objects """
       output = ""
       funcName = ""
       if aClass.className().find("multirow") != -1:
          rowName = aClass.className()
          tabelName = aClass.multitablename + 'MultiTable'
          objName = aClass.multitablename

          funcName = 'readTestFor'+tabelName
          output += '\n\ndef '+funcName+'():'
          output += '\n    """ Generated Test For Read Operation to '+tabelName+' Table """'
          output += '\n    table = dbsclient.'+tabelName+'()'
          output += '\n    aRow = dbsclient.'+rowName+'()'

          paramDict = self.getParamDict(aClass)
          for aparam in paramDict.keys() :
             paramType = paramDict[aparam]
             #paramMap =  localmap[paramType]
             if paramType == "STRING" :
                  output += '\n    setStrValue(aRow, "'+aparam+'", "test_value_'+aparam+'")'
             if paramType == "CHARACTER" :
                  output += '\n    setChrValue(aRow, "'+aparam+'", "y")'
             if paramType == "INTEGER" :
                  output += '\n    setIntValue(aRow, "'+aparam+'", "1234")'
          output += '\n\n    client.READAPI(aRow, table)'
          output += '\n\n    noOfRows = table.getNoOfRows()'
          output += '\n    print "no of Rows ",noOfRows'
          output += '\n    for j in range(noOfRows) :'
          for aparam in paramDict.keys() : 
              output += '\n        print "'+aparam+'", getStrValue(table, "'+aparam+'", j)'     
          
       return output, funcName 


   def genWriteViewPyTest(self, aClass) :

       """Writes python test for a MultiView Class """

       output = ""
       funcName = ""
       if aClass.className().find("multirow") != -1:
          rowName = aClass.className()
          tabelName = aClass.multitablename + 'MultiTable'
          objName = aClass.multitablename

          funcName = 'writeTestFor'+tabelName 
          output += '\n\ndef '+funcName+'():'
          output += '\n    """ Generated Test For Write Operation to '+tabelName+' Table """'
          output += '\n    table = dbsclient.'+tabelName+'()'
          output += '\n    aRow = dbsclient.'+rowName+'()'
          vecName = rowName.lower()+'Vector'
          output += '\n    '+vecName+' = dbsclient.'+objName+'Vector()\n'

          paramDict = self.getParamDict(aClass) 
          for aparam in paramDict.keys() :
             paramType = paramDict[aparam]
             #paramMap =  localmap[paramType]
             if paramType == "STRING" :
                  output += '\n    setStrValue(aRow, "'+aparam+'", "test_value_'+aparam+'")'
             if paramType == "CHARACTER" :
                  output += '\n    setChrValue(aRow, "'+aparam+'", "y")'
             if paramType == "INTEGER" :
                  output += '\n    setIntValue(aRow, "'+aparam+'", "1234")' 
             #if paramType == "FLOAT" :
          output += '\n    '+vecName+'.push_back(aRow)\n'
       return output, funcName
 
   def write(self, outPath=os.getcwd(), \
                outFile = "SwigTest.py") :
      """ writes out the actual file """
      writeTestFuncs = [] 
      readTestFuncs = []
      output = self.fileheader
      for eachClass in self.cppClasses:
         rowName = eachClass.className()
         # Write TESTs
         test_output, test_funcName = self.genWriteViewPyTest(eachClass)
         output += test_output 
         writeTestFuncs.append(test_funcName)
         #########Could have a predefined MAP of API Calls  
         output += '\n    client.WRITEAPICALL('+rowName.lower()+'Vector, table)\n'
         ####READ Tests
         test_output, test_funcName = self.genReadViewPyTest(eachClass)
         output += test_output
         readTestFuncs.append(test_funcName)  

      output += "\n\nif __name__== '__main__':\n\n"
      
      for eachFunc in writeTestFuncs:
         output += '\n    '+eachFunc+'()'
      for eachFunc in readTestFuncs:
         output += '\n    '+eachFunc+'()'
 
      filepath = os.path.join(outPath, outFile)
      testfile = open(filepath, 'w')
      testfile.writelines(output)
      testfile.write('\n\n\n')
      testfile.close()

