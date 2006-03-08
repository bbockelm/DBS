#!/usr/bin/env python
import os
import commands
import string
from rowRepresentation import rowRepresentation

class writeSWIGInterface :
   """write dbsclient.i file fow SWIG compilations"""
   def __init__(self, inCppClasses) :
       """Constructor"""
       self.cppClasses = inCppClasses
 
       self.fileheader = """//dbsclient.i - SWIG interface 
        %module dbsclient
        %{
                #include "../../include/common.hpp"
                #include "../../include/DBSClient.hpp"
                #include "../../include/BaseSchemaNConstratints.hpp"
                #include "../../include/RowInterface.hpp"
                #include "../../include/Util.hpp"
                #include "../../include/RowNSchemaBinding.hpp"
                #include "../../include/ObjectLayerTables.hpp"
                #include "../../include/MultiTableInterface.hpp"
                #include "../../include/TableTemplate.hpp"
                #include "../../include/TableInterface.hpp"
        %}

        %import ../../include/ 

        // Parse the original header file

        %include "../../include/common.hpp"
        %include "../../include/DBSClient.hpp"
        %include "../../include/ObjectLayerTables.hpp"
        %include "../../include/BaseSchemaNConstratints.hpp"
        %include "../../include/RowInterface.hpp"
        %include "../../include/Util.hpp"
        %include "../../include/RowNSchemaBinding.hpp"
        %include "../../include/MultiTableInterface.hpp"
        %include "../../include/TableTemplate.hpp"
        %include "../../include/TableInterface.hpp"
        %include "std_vector.i"
        %include "std_string.i"
        %include "cpointer.i"

        using namespace std ;
        %template(ACHR) VALUEDEF<char>;
        %template(AINT) VALUEDEF<int>;
        %template(ASTR) VALUEDEF<string>;
        %template(AFLT) VALUEDEF<float>;


        %pointer_functions(char, charp);
        %pointer_functions(float, floatp);
        %pointer_functions(int, intp);
        %pointer_functions(string, stringp);


        %extend DBSClient {
                static std::string str(char* s){
                        std::string mystr(s);
                        return mystr;
                }
        };"""

   def swigTemplDefs(self):
       """ write Defs """

       output = ''
       for eachClass in self.cppClasses:
           if eachClass.className().find("multirow") != -1:
              rowName = eachClass.className()
              tabelName = eachClass.multitablename + 'MultiTable'
              objName = eachClass.multitablename
              output += '\n\n        %template('+tabelName+'Template) TableTemplate<'+rowName+'>;'
              output += '\n        %template('+tabelName+') MultiTableInterface<'+rowName+'>;'
              output += '\n        %template('+objName+'Vector) std::vector<'+rowName+'*>;'
       return output

        
   def write(self, outPath=os.getcwd(), \
                outFile = "dbsclient.i") :
      """ writes out the actual file """
 
      ioutput = self.fileheader
      ioutput += self.swigTemplDefs()                
      ifilepath = os.path.join(outPath, outFile)
      intfc = open(ifilepath, 'w')
      intfc.writelines(ioutput)
      intfc.write('\n\n\n')
      intfc.close() 
       




