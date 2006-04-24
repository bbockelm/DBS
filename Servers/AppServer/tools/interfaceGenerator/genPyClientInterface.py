import string
import pdb
import os

class pyClassRep:
     def __init__(self, name, parent):
        self.className = name 
        self.parentClass = parent
          
        self.variables = []   # Tuple of variable Name/Type.

     def reArrangeVars(self):
        
        """ Puts the Default values varibales at last in the list """
        #pdb.set_trace()
        newVars = []
        for aVar, varType, mustHave in self.variables :
            if mustHave in (None, '0'):
               newVars.append((aVar, '0'))          
            else:
               newVars.insert(0, (aVar, '1'))
        return newVars

     def genPyListRep(self):
        """Writes a LIST Class for the Class Type"""
        self.reArrangeVars()         
        listClassName = self.className+'List'
        listName = listClassName.lower()
        output =  '\n\nclass '+listClassName+':'
        output += '\n   """a LIST Class for the Python Class Type '+self.className+'"""'
        output += '\n   def __init__(self, '+listName+'=[]):'
        output += '\n     self.'+listName+' = []'
        output += '\n     for anObj in '+listName+':'
        output += '\n       newListObj = self.__createListObj(anObj)'
        output += '\n       self.'+listName+'.append(newListObj)\n\n'
        output += '\n   def __createListObj(self, inObj):'
        output += '\n     """ Create new file object. """'
        output += '\n     try:'
        output += '\n       newObj = inObj'
        output += '\n       if isinstance(newObj, '+self.className+'):'
        output += '\n         # this is ok'
        output += '\n         return newObj'
        output += '\n     except Exception, ex:'
        output += '\n       raise dbsException.InvalidArgument(exception=ex)\n'
        output += '\n   def append(self, thisObj):'
        output += '\n     """ Append new  object to the list """'
        output += '\n     newObj = self.__createFile(thisObj)'
        output += '\n     self.'+listName+'.append(newObj)\n'
        return output

     def genPyRep(self):
        
        """Writes Python Representation"""
        #pdb.set_trace()
        output = '\n\nclass  '+self.className+'(DbsBase):'
        output += '\n   """ '
	output += '\n   Class for '+self.className[3:]
	output += '\n   """'
        output += '\n   def __init__(self, **args):'
        #output += '\n   def __init__(self'
        #reVars = self.reArrangeVars()
        #for aVar, varVal in reVars:
        #   if varVal == '1':
        #      output += ', '+ aVar 
        #   else:
        #      output += ', '+ aVar + ' = None' 
        #for aVar, varType, mustHave in self.variables :
        #    if mustHave not in (None, '0'): 
        #       output += aVar + ', '
        #       continue   
        #    output += aVar + ' = None, '
        #output += '):'
        output += '\n      DbsBase.__init__(self)'
        for aVar, varType, mustHave in self.variables :
           output += '\n      self.setDefault("'+ aVar + '", None)'
	output += '\n      self.update(args)'
	output += '\n      if imported_ZSI:'
	output += '\n          self.writeWsRep()'
	#write web service rep
	output += '\n\n   def writeWsRep(self):'
        output += '\n      """ Utility function for setting up Web Service object """'
        output += '\n      try:'
	output += '\n           '+self.className+'.__bases__ = (DbsBase, '+self.parentClass+')'
        output += '\n           '+self.parentClass+'.__init__(self)'
        for aVar, varType, mustHave in self.variables :
           output += "\n           if self['"+aVar+"'] != None : self._"+ aVar + " = self['"+aVar+"']"
	output += '\n      except Exception, e:'
	output += '\n          raise dbsApi.DbsApiException(exception=e)'
        output += '\n      except ZSI.FaultException, ex:'
        output += '\n          raise dbsApi.DbsApiException(exception=ex)'

        #print output
        return output

class funcRep:
   """ Class that represents an API Call, will generate a warpper """
   def __init__(self, apiName, argsTupleList = [], returnVar = None):
     self.apiName = apiName
     self.genericArgs = [] 
     self.typeArgs = []
     for aVar, varType in argsTupleList :
         if varType in ['int*', 'int', 'long*', 'long', \
                        'string*', 'string', 'std::string*', 'std::string', \
                        'char*', 'char', 'bool', 'bool*' \
                       ] :
            self.genericArgs.append((aVar, varType))
         else:
            self.typeArgs.append((aVar, varType))

     self.returnVar = returnVar

   def genAPIWrapper(self): 
     """ Generates a General Wrapper for User Interface """
     output = '\n\n   def '+self.apiName+'(self'
     for aVar, varType in self.genericArgs:
         output += ', '+aVar
     for aVar, varType in self.typeArgs:
         output += ', '+aVar
     output += '):\n'
     #output += '\n    loc = ServiceLocator()' 
     #output += '\n    portType = loc.getServicePortType()'
     output += '\n     try:'
     output += '\n       request = '+self.apiName+'RequestWrapper()'
     for aVar, varType in self.genericArgs:
         output += '\n       request._'+aVar + ' = '+aVar
     for aVar, varType in self.typeArgs:
         output += '\n       request._'+aVar+ ' = '+aVar
     output += '\n       response = self.portType.'+self.apiName+'(request)'
     output += '\n       return response._'+self.returnVar[0]
     output += '\n     except ZSI.FaultException, ex:'
     #output += '\n     except dbsWsClient.DbsWsClientException, ex:'
     output += '\n       raise dbsApi.DbsApiException(exception=ex)'
     return output

   def genSoapAPI(self):
     """ Generates a General Wrapper for User Interface """
     output = '\n\nint DBS__'+self.apiName+'(struct soap* soap'
     params = ""
     for aVar, varType in self.genericArgs:
         output += ',' + varType + ' '+ aVar
         params += aVar + ','
     for aVar, varType in self.typeArgs:
         output += ', ' + varType + ' ' +aVar
         params += aVar + ','
     output += ','+self.returnVar[1] + ' '+ self.returnVar[0]
     params += self.returnVar[0]
     output += ') {\n'
     output += '\n\ttry{'
     output += '\n\t\tSoapDBSApi dbsapi(soap);'
     output += '\n\t\tdbsapi.'+ self.apiName +'('+ params +');'
     output += '\n\t} catch (BizLayerException e) {'
     output += '\n\t\treturn soap_receiver_fault(soap, e.report().c_str() , NULL);'
     output += '\n\t}'
     output += '\n\treturn SOAP_OK;'
     output += '\n}'
     return output

   def genInterfaceHpp(self):
     """ Generates a Interface.hpp for used by the C++ Server """
     output = '\nint '+self.apiName+'('
     for aVar, varType in self.genericArgs:
         output +=  varType + ' '+aVar + ','
     for aVar, varType in self.typeArgs:
         output +=  varType + ' ' +aVar + ','
     output = output[:-1]
     output += ','+self.returnVar[1] + ' '+ self.returnVar[0] + ');'
     #print output
     return output

class writeAPIClass:
    """ Class that generates dbsWSAPI.py"""
    def __init__(self, apiList=[]):
        self.apiList = apiList

    def genAPIClass(self):
        output  = 'from Service_services import *'
        output += '\nimport dbsException\n'
        output += '\nimport dbsApi\n'
        output += '\nimport ZSI\n'
        output += '\nclass DbsWsApi(dbsApi.DbsApi):\n'
        output += '\n   def __init__(self):'    
        output += '\n       self.loc = ServiceLocator()'
        output += '\n       self.portType = self.loc.getServicePortType()'
        for anAPI in self.apiList:
            output += anAPI.genAPIWrapper()  
        return output


class writeSoapAPIImpl:
    """ Class that generates SoapApiImpl.cpp"""
    def __init__(self, apiList=[]):
        self.apiList = apiList

    def genSoapAPIImpl(self):
        output  = '#include "Interface.hpp"'
        output += '\n#include <iostream>'
        output += '\n#include "BizLayerException.hpp"'
        output += '\n#include <exception>'
        output += '\n#include "SoapDBSApi.hpp"'
        output += '\n\nusing namespace std;'
        output += '\n'
        for anAPI in self.apiList:
            output += anAPI.genSoapAPI()
	#print output
        return output

class writeInterfaceHpp:
    """ Class that generates Interface.hpp"""
    def __init__(self, apiList=[]):
        self.apiList = apiList

    def writeInterfaceHpp(self):
        output  = '#include "soapH.h"'
        output += '\n#include <vector>'
        output += '\n#include <string>'
        output += '\n'
        for anAPI in self.apiList:
            output += anAPI.genInterfaceHpp()
	#print output
        return output

     
class processInterface :
   """Class that processes a Interface file and extracts all information
        in form of rowRepresentation classes
   """
   def __init__(self, intfcfilename) :
      """Constructor takes a Interface file name as input"""
      file = open(intfcfilename,'r')
      self.allLines = file.readlines()
      file.close()
      self.pyClassList =  []
      self.apiList = []
      self.parentNameSpect = 'ns1'
     

   def variableMap(self, variableType):
       """ Determines the type of variable in Generated Python Code """

       if variableType in ['int*', 'int', 'long*', 'long', \
                           'string*', 'string', 'std::string*', 'std::string', \
                           'char*', 'char', 'bool', 'bool*' \
                          ] :
          return 'GENERIC'

       if variableType.find('vector') != -1 :
          return 'LIST'
       if variableType.find('__') != -1 :
          if variableType.endswith('*') or variableType.endswith('&'):
             return variableType[:len(variableType)-1] 
          else :
             return variableType
      
   def run(self):
      """Process the input sql file"""
      for line in self.allLines:
         line = line.strip()
         if line.startswith('/*')and line.find('*/') != -1 :
            after=line.find('*/')+2
            line=line[after:].strip()
         if line == "":
            continue
         if line.startswith('#'):
            continue 
         if line.startswith('public:'):
            continue
         if line.startswith('//') :
            continue
	 if (line.find('class') != -1) and \
                line.endswith(';'):  # Take care of forward declarations
	    #print "Forward declaration found"
	    continue
         if line.find('(') != -1: # Function definition line encountered
            #pdb.set_trace() 
            funcName = line.split('__')[1].split('(')[0]
            #funcName = 'dbs'+funcName[0].upper()+funcName[1:]
            params = line.split('(')[1].split(')')[0].split(',')
            argsTupleList = []
            for aParamDesc in params:
                type = aParamDesc.split()[0]
                param = aParamDesc.split()[1]
                argsTupleList.append((param, type)) 
                #print param, self.variableMap(type)
            returnVar = argsTupleList[len(argsTupleList)-1]  # Last variable from the list is the return type
            argsTupleList.pop()   # Remove the last element
            newApiCall = funcRep(funcName, argsTupleList, returnVar)
            self.apiList.append(newApiCall)  
            #print funcName
            #int DBS__getDatasetContents(std::string datasetPathName, bool listFiles, std::vector<DBS__Block*>& blockList);
            continue 
         if (line.find('class') != -1) and \
		not line.endswith(';'):  # Take care of forward declarations
            currentClassBaseName = line.split()[1]
            currentClassName = 'Dbs'+currentClassBaseName.split('__')[1]
            currentClassParent = self.parentNameSpect+'.'+currentClassBaseName.split('__')[1]+'_Def'
            #print "CREATING ", currentClassName
            newCurrentClass = pyClassRep(currentClassName, currentClassParent)
            self.pyClassList.append(newCurrentClass)
            continue
         if line.endswith('};'):
            continue 
         variableInfo = line.split()
         variableType = self.variableMap(variableInfo[0].strip())
         variableName = variableInfo[1]
         mustHave = None 
         if len(variableInfo) > 2:
            mustHave = variableInfo[2].split(';')[0].strip()
         #print variableName,    variableType, mustHave
         newCurrentClass.variables.append((variableName, variableType, mustHave))

def writeIntoFile(output, outFile, outPath=os.getcwd()) :
      """ Write the contents of output into a file""" 
      filepath = os.path.join(outPath, outFile)
      outfile = open(filepath, 'w')
      outfile.writelines(output)
      outfile.write('\n\n\n')
      outfile.close()


if __name__== '__main__':

      processMyFile = processInterface("/home/sekhri/work/dm/DMSCurr/DBS/Servers/AppServer/interface/Cpp/Interface.hpp")
      #processMyFile = processInterface("../../interface/Cpp/Interface.hpp")
      processMyFile.run()
      output = 'import dbsException\n'
      output += 'import exceptions\n'
      output += 'try:\n'
      output += '  from Service_services import *\n'
      output += '  imported_ZSI = True\n'
      output += 'except:\n'
      output += '  imported_ZSI = False\n'
      output += '\nclass DbsBase(dict):\n'
      output += '     """ Base Class for all DBS Client Data Types """\n'
      output += '     def __init__(self, **args):\n'
      output += '           dict.__init__(self)\n'

      for aClass in processMyFile.pyClassList:
        output += aClass.genPyRep()
        #if aClass.className in ['DbsFile', 'DbsFileBlock', 'DbsEventCollection']:
        #   output += aClass.genPyListRep()
      writeIntoFile(output=output, outFile='dbsClientDatastructures.py') 

      apiGenerator = writeAPIClass(processMyFile.apiList)
      output = apiGenerator.genAPIClass()
      writeIntoFile(output=output, outFile='dbsWsApi.py')
      apiGenerator = writeSoapAPIImpl(processMyFile.apiList)
      output = apiGenerator.genSoapAPIImpl()
      writeIntoFile(output=output, outFile='SoapApiImpl.cpp')
      apiGenerator = writeInterfaceHpp(processMyFile.apiList)
      output = apiGenerator.writeInterfaceHpp()
      writeIntoFile(output=output, outFile='Interface.hpp')


       
