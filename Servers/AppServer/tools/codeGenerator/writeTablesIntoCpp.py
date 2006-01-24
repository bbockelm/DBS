#!/usr/bin/env python
import os
import commands
import string
from rowRepresentation import rowRepresentation

class writeTablesIntoCpp :

   def __init__(self) :
      """Constructor"""
      self.cppClasses=[]
      self.mapList = []

   def getShort(self, long):
       short=''

       totay=string.split(long, '_')

       if long.find('_') != -1:
         for aTota in totay:
            short+=aTota[0]
       for i in range(len(long)):
          if long[i].isupper():
             short+=long[i]

       return short

   def generateNameMap(self, cppClasses, tbl=1):
      mapStrList = []
      global_unique_vars = []
      global_unique_counter = 1
      mapStr = ""
      newTmp = []
      for eachClass in cppClasses:
          #print eachClass.tableName()  
          table = eachClass.tableName()
          stable = self.getShort(table)
          schemaDict = eachClass.getSchema()
          if type(schemaDict) == type({}) :
             for adict in schemaDict.keys():
                tmp = {}
                tmp[adict] = schemaDict[adict]
                newTmp.append(tmp)
             schemaDict = newTmp
          for adict in schemaDict :
            param = adict.keys()[0]
            if param.find('_') != -1 :
              var = self.getShort(adict.keys()[0])
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
               if entry not in self.mapList:
                  mapStrList.append(mapStr)
                  self.mapList.append(entry)
            else :
               mapStr = '\n        NameMap.insert(Entry("'+param+'", "'+var+'"));'
               if param not in self.mapList:
                  self.mapList.append(param)
                  mapStrList.append(mapStr)

      return mapStrList

   def makeHppHeader(self) :
      """ Returns the Header for c++ .hpp file"""
      output = "\n#ifndef _a_h_included_" + \
               "\n#define _a_h_included_" + \
               "\n/// This file contains Classes representing Rows of each table" + \
               "\n///Generated from SQL" + \
               "\n#include <iostream.h>" + \
               "\n#include <vector>" + \
               "\n#include <string>" + \
               '\n#include "common.hpp"' + \
               '\n#include "BaseSchemaNConstratints.hpp"' + \
               '\n#include "SingleTableInterface.hpp"' + \
               '\n#include "RowInterface.hpp"' + \
               '\n#include "MultiTableInterface.hpp"\n\n'  
      return output
      
   def makeHppBottom(self) :
      """ Returns any ending statements for a .hpp file"""
      output = "\n#endif\n\n"
      return output 

   def writeHppHeaderFile(self, headerOutPath=os.getcwd(),  \
                      outputHeader="ObjectLayerTables.hpp") :
      """ Write a C++ .hpp file"""
      output = self.makeHppHeader()
      for eachClass in self.cppClasses:
         output += eachClass.writeClassDef()
      for eachClass in self.cppClasses:
         output +=  eachClass.writeTypeDef()
      output +=  self.makeHppBottom()

      filepath = os.path.join(headerOutPath, outputHeader)
      header = open(filepath, 'w')
      header.writelines(output)
      header.write('\n')
      header.close()
      
   def writeTypeDefs(self) :
      """Write all type defs"""
      output = "\n"
      for eachClass in cppClasses:
         output += eachClass.writeClassTypeDef()
      return output

   def makeCppHeader(self, hppFileName) :
      output = '\n#include "'+hppFileName+'"\n' 
      return output   
         
   def writeCppImplFile(self,  cppOutPath=os.getcwd(), \
                cppFile = "ObjectLayerTables.cpp", hppFile="ObjectLayerTables.hpp") :
      """ function that generates CPP classes header and Impl files
      from an input list of cppRepresentation classes
      Constructor Takes a list of cppRepresentation class list
      Names and Paths of Cpp and Header files are defaulted
      """
      output = self.makeCppHeader(hppFile)
      for eachClass in self.cppClasses:
         output += eachClass.writeClassImpl()
         
      filepath = os.path.join(cppOutPath, cppFile)
      cppimpl = open(filepath, 'w')
      cppimpl.writelines(output)
      cppimpl.write('\n')
      cppimpl.close() 

   def writeTemplateInstances(self,  cppOutPath=os.getcwd(), \
                cppFile = "TemplateInstances.cpp") :
      """General utility, returns text to be added to Table Template
      and SingleTableInterface.cpp files"""
      
      #output = "\n\n\nADD FOLLOWING TO TableTemplate.cpp file at Bottom\n"
      output='#include "TableTemplate.hpp"\n'
      output+='#include "ObjectLayerTables.hpp"\n'
      output+='#include "SingleTableInterface.hpp"\n'
      output+='#include "MultiTableInterface.hpp"\n'

      for eachClass in self.cppClasses:
          output += eachClass.forTableTemplate()

      #output += "\n\n\nADD FOLLOWING TO SingleTableInterface.cpp"
      #output += "\nand MultiTableInterface.cpp Respectively at Bottom\n\n"  
      output += '\n'
      for eachClass in self.cppClasses: 
          output += eachClass.forTableInterface()
      filepath = os.path.join(cppOutPath, cppFile)
      cppimpl = open(filepath, 'w')
      cppimpl.writelines(output)
      cppimpl.write('\n')
      cppimpl.close()

      return


   def writeRowNSchemaBindingImpl(self, cppOutPath=os.getcwd(), \
                cppFile = "RowNSchemaBinding.cpp", hppOutPath=os.getcwd(),  \
                hppFile="RowNSchemaBinding.hpp") :
      """Writes RowNSchemaBinding.cpp file"""
      output = '#include "'+hppFile+'"\n'
      output += '\nRowNSchemaBinding::RowNSchemaBinding() {'
      for eachClass in self.cppClasses:
          objName = eachClass.rownschemabinding+'Obj'
          output += '\n      BaseSchemaNConstraintsBinding* '+objName+' = (BaseSchemaNConstraintsBinding*) new '+eachClass.rownschemabinding+'();'
          output += '\n      this->RowNSchemaBindingMap.insert(SchemaMapEntry("'+eachClass.className().lower()+'", '+objName+'));'
      output += '\n' + '}'

      output += '\nRowNSchemaBinding::~RowNSchemaBinding() {'
      output += '\n      for (SchemaMap_iter i = this->RowNSchemaBindingMap.begin(); i != this->RowNSchemaBindingMap.end(); i++) {'
      output += '\n          delete i->second;\n'
      output += '\n' + '      }'
      output += '\n' + '}' 
      output += '\n'
      output += '\nBaseSchemaNConstraintsBinding* RowNSchemaBinding::getSchemaObject(string tableName) {'
      output += '\n    for (SchemaMap_iter i = this->RowNSchemaBindingMap.begin(); i != this->RowNSchemaBindingMap.end(); i++) {'
      output += '\n       if (tableName.compare(i->first)  == 0) {'
      output += '\n          return i->second;'
      output += '\n       }'
      output += '\n    }'
      output += '\n}'

      filepath = os.path.join(cppOutPath, cppFile)
      cppimpl = open(filepath, 'w')
      cppimpl.writelines(output)
      cppimpl.write('\n')
      cppimpl.close()


   def writeTableFactoryImpl(self, cppOutPath=os.getcwd(), \
                cppFile = "TableFactory.cpp", hppOutPath=os.getcwd(),  \
                hppFile="TableFactory.hpp") :
      """writes Table factory Implementation"""

      output = '#include "'+hppFile+'"\n'
      output += '\nTableFactory::TableFactory() {'
      output += '\n' + '}'
      #
      output += '\nTableInterface* TableFactory::getTableObject(string tableName) {'
      for eachClass in self.cppClasses:
          lClassName = eachClass.className().lower()
          tClassName = eachClass.className().title()
          output += '\n      if ( tableName.compare("'+lClassName+'") == 0 ) {'
          if lClassName.find("multirow") != -1:
             #output += '\n         TableInterface* tmpPtr = new TableTemplate<'+tClassName+'>;'
             output += '\n         TableInterface* tmpPtr = new MultiTableInterface<'+tClassName+'>;'
          else: 
             output += '\n         TableInterface* tmpPtr = new SingleTableInterface<'+tClassName+'>;'
             #output += '\n         TableInterface* tmpPtr = new TableTemplate<'+tClassName+'>;'
          output += '\n         return tmpPtr;'
          output += '\n      }'
      output += '\n' + '}'
      #
      output += '\nTableFactory::~TableFactory() {'
      output += '\n' + '}'

      filepath = os.path.join(cppOutPath, cppFile)
      cppimpl = open(filepath, 'w')
      cppimpl.writelines(output)
      cppimpl.write('\n')
      cppimpl.close()
 
   def foundTables(self):
      return self.cppClasses
         

   def removeDot(self, key):
      return key.replace('.','_') 


   def writeNameMaper(self, mapStrList, cppOutPath=os.getcwd(), \
                cppFile = "NameMaper.cpp"):
       """ Function that writes NameMapper.cpp file"""

       mapStr = '#include "NameMaper.hpp"'
       mapStr += '\nNameMaper::NameMaper(){'
       for anItem in mapStrList:
          mapStr += anItem
       mapStr += '\n}'
       filepath = os.path.join(cppOutPath, cppFile)
       cppimpl = open(filepath, 'w')
       cppimpl.writelines(mapStr)
       cppimpl.write('\n')
       cppimpl.close()

 
   def writeClientDataStructure(self, cppOutPath=os.getcwd(), \
                cppFile = "ClientAPIData.cpp", hppOutPath=os.getcwd(),  \
                hppFile="ClientAPIData.hpp" ):
      """ writeClientDataStructure for Multi Row Classes """

      localmap = {'INTEGER':'int', 'STRING':'string',   \
                    'INTEGER':'int','CHARACTER':'char', \
                    'FLOAT':'float',   
                     }

      testCppOut = ""
      utestCppOut = "#include <iostream>"
      utestCppOut += '\n#include "DBSClient.hpp"'
      utestCppOut += '\n#include <exception>'
      utestCppOut += '\n#include "ClientAPIData.hpp"'
      utestCppOut += '\nusing namespace std;'
      utestCppOut += '\n'
      utestCppOut += '\nint main() {\n'
      utestCppOut += '\n  DBSClient* dbsclient = new DBSClient();\n'
      utestCppOut += 'try {'

      houtput = '#ifndef _ClientTest_hpp_\n'
      houtput += '#define _ClientTest_hpp_\n'
      houtput += '#include "ClientDataStructure.hpp"\n'

      ioutput = '\n#include "ClientAPIData.hpp"\n'

      for eachClass in self.cppClasses:
         lClassName = eachClass.className().lower()
         outclassName = lClassName.split("multirow")[0].title()+'_ClientAPIData'
 
         if lClassName.find("multirow") != -1:   
            schemaDict = eachClass.getSchema()
            paramDict = {}

            #paramDict = schemaDict
            for anitem in schemaDict.keys():
                paramName = anitem.split('.')[1]
                if paramName in ('created_at', 'created_by', 'modified_by', 'modified_at'):
                   continue 
                #paramDict[paramName] = schemaDict[anitem]
                paramDict[anitem] = schemaDict[anitem]
            # Write Class Definition
            houtput += "\nclass "+outclassName+'  : public ClientDataStructure {'
            houtput += "\npublic:"
            for aparam in paramDict.keys() :
               houtput += "\n    "+paramDict[aparam]+"  "+self.removeDot(aparam)+";" 
            houtput += "\npublic:"
            houtput += "\n     "+outclassName+"();"
            houtput += "\n"
            houtput += "\n     virtual int makeMessage(Message& messageOut);"
            houtput += "\n     virtual int readInMessage(Message& messageIn, string lisName, int index);\n"
            houtput += "\n};"

            # Write Class Implementation
            #write the constructor of class
            ioutput += "\n"+outclassName+'::'+outclassName+"(){"
            ioutput += eachClass.writeDict(paramDict, "Schema")
            ioutput += '\n\n}\n'
            # makeMessage
            #ioutput += "\nMessage* "+outclassName+"::makeMessage(Message& messageOut) {"
            ioutput += "\nint "+outclassName+"::makeMessage(Message& messageOut) {"
            #ioutput += '\n    if ( messageNotCreated ) {'
            for aparam in paramDict.keys() :
                paramType = paramDict[aparam]
                paramMap =  localmap[paramType]
                ioutput += '\n       if ( ('+paramMap+'*)(&('+self.removeDot(aparam)+'.getValue())) != NULL ) {'
                if paramType == "CHARACTER" :
                   ioutput += '\n          messageOut.addElement(new Element((string)"'+aparam+'", (string)' + \
                     "(util.ctoa("+self.removeDot(aparam)+'.getValue())), (string)"'+paramType+'"));'
                   ioutput += "\n      }" 
                   continue
                if paramType == "INTEGER" :
                   ioutput += '\n          messageOut.addElement(new Element((string)"'+aparam+'", (string)' + \
                     "(util.itoa("+self.removeDot(aparam)+'.getValue())), (string)"'+paramType+'"));'
                   ioutput += "\n      }"
                   continue
                if paramType == "STRING" :
                   ioutput += '\n          messageOut.addElement(new Element((string)"'+aparam+'", (string)' + \
                      self.removeDot(aparam)+'.getValue(), (string)"'+paramType+'"));'
                   ioutput += "\n      }"
                   continue
                if paramType == "FLOAT" :
                   ioutput += '\n          messageOut.addElement(new Element((string)"'+aparam+'", (string)' + \
                      "(util.ftoa("+self.removeDot(aparam)+'.getValue())), (string)"'+paramType+'"));'
                   ioutput += "\n      }"
                   continue

            #ioutput += '\n    messageNotCreated = true;'
            #ioutput += "\n    }"
            #ioutput += "\n\n    return &messageOut;"
            ioutput += "\n\n    return 1;" 
            ioutput += '\n\n}\n'
            objectName = 'obj_'+outclassName.lower()
            testCppOut += '\n'+outclassName+'  get'+objectName+'() {'
            testCppOut += '\n\n       '+outclassName+'  '+objectName+';\n'
            # readInMessage
            ioutput += "\nint "+outclassName+"::readInMessage(Message& messageIn, string lisName, int index) {\n"
            ioutput += '\n    string value;'
            for aparam in paramDict.keys() : 
               paramType = paramDict[aparam]
               #paramMap =  localmap[paramType]
               ioutput += '\n    value = messageIn.getElementValue("'+aparam+'", lisName, index);'
               ioutput += '\n    if ( value != "NOTFOUND" ) {'
               if paramType == "STRING" : 
                    ioutput += '\n        string strValue = (string) value;'
                    ioutput += '\n        '+self.removeDot(aparam)+' = strValue;'                               
                    testCppOut += '\n       '+objectName+'.'+self.removeDot(aparam)+' = (string)"test_value_'+aparam+'";'
               if paramType == "CHARACTER" :
                    ioutput += '\n        char charValue = *(value.c_str());'
                    ioutput += '\n        '+self.removeDot(aparam)+' =  charValue;' 
                    testCppOut += '\n       '+objectName+'.'+self.removeDot(aparam)+' = '+"(char)'c';" 
               if paramType == "INTEGER" :
                    ioutput += '\n        int intValue  = atoi(value.c_str());'
                    testCppOut += '\n       '+objectName+'.'+self.removeDot(aparam)+' = '+"(int)123;" 
                    ioutput += '\n        '+self.removeDot(aparam)+' =  intValue;'
               if paramType == "FLOAT" :
                    ioutput += '\n        float floatValue  = atof(value.c_str());'
                    testCppOut += '\n       '+objectName+'.'+self.removeDot(aparam)+' = '+"(float)123;"
                    ioutput += '\n        '+self.removeDot(aparam)+' =  floatValue;'
 
               ioutput += '\n    }'
            ioutput += '\n\n}\n' 

            testCppOut += '\n\n       return '+objectName+';'
            testCppOut += '\n }\n'
            utestCppOut += '\n       dbsclient-><<<<<<<<APICALL>>>>>>>(get'+objectName+'());'
      #utestCppOut += utestCppOut
      utestCppOut += '\n       } catch (exception &ex) {'
      utestCppOut += '\n               cout << ex.what() << endl;'
      utestCppOut += '\n    }'
      utestCppOut += '\n    delete dbsclient;'
      utestCppOut += '\n }\n'
       
      #testCppOut += utestCppOut
      testCppOut = utestCppOut + testCppOut
      testCpppath = 'GeneratedClientTest.cpp'
      testh = open(testCpppath, 'w')
      testh.writelines(testCppOut)
      testh.close()

      houtput += '\n#endif'
      hfilepath = os.path.join(hppOutPath, hppFile)
      hpp = open(hfilepath, 'w')
      hpp.writelines(houtput)
      hpp.write('\n')
      hpp.close()

      ifilepath = os.path.join(cppOutPath, cppFile)
      cppimpl = open(ifilepath, 'w')
      cppimpl.writelines(ioutput)
      cppimpl.write('\n')
      cppimpl.close() 


