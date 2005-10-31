#!/usr/bin/env python
import os
import commands
import string

import pdb

class aRow :
   """  Class representing a SQL Table Row"""

   def __init__(self, name) :

      #These are simple lists
      self.primarykeys=[]
      self.foreignkeys=[]
      self.notnulls=[]

      # List of list     
      self.uniquekeys=[]
       
      # These are List of dictionaries  [{'a','b'}, {'c','d'}..]
      self.schema=[]
      self.constraints=[]
      self.references=[]
       
      # output for RowNSchemaBinding MAP
      self.rownschemabinding =""
      # Name of the table for which this row is being created.
      self.tablename = name
   
   def getSchema(self):
      return self.schema
 
   def className(self):
     if self.tablename.find("Row") == -1: 
        return self.tablename.title() + 'Row'
     return self.tablename.title()

   def tableName(self):
      if self.tablename.find("Row") != -1:
         return self.tablename.split('Row')[0].lower()

class rowRepresentation(aRow) :
   """  Class generates C++ class for a SQL Table Row"""
   
   def __init__(self, name) :
      aRow.__init__(self, name)
    
      self.cppsqltypemap = {'INTEGER':'int', 'STRING':'string',   \
                    'INTEGER':'int','CHARACTER':'char', \
                    'FLOAT':'float',
                     }


   def writeClassImpl(self) :
      """Write the class definition"""

      output =  "\n\n"+self.className()+"::"+self.className()+"(){" 
      output += '\n    this->rowMap.set("'+self.className().lower()+'", (void*)this);'
      output += '\n    this->constituentObjects.push_back(this->rowMap);'
      #output += '\n    this->constituentObjects.insert(ObjEntry((string)"'+ \
      #          self.tablename+'", (RowInterface) *this));'
      output += "\n}"  
      output += self.writeSetValueImpl()
      output += self.writeGetValueImpl()
      output += self.writeDBBindingImpl()
      return output 

   def writeClassDef(self) :
      """Write the class definition"""
      output = "\nclass "+self.className()+'  : public RowInterface {'
      output += "\npublic:"
      output += "\n     "+self.className()+"();"
      ###output += "\n     "+self.tablename+"(){};"
      output += "\n"
      output += "\n\n     virtual void* getValue(string key);"
      output += "\n     virtual void setValue(string key, void* value);\n"
      output +=  self.writeParamsDef()
      output += "\n};"
      output +=  self.writeDBBindingDef()
      output += self.writeRowSchemaNConstraintsBindingDef()
      return output
      
   def writeGetValueImpl(self):
      """Write the getValue member function for the class variables"""
      output   = '\n\nvoid* '+self.className()+'::getValue(string key) {'
      
      for aParamDict in self.schema :
         paramName = aParamDict.keys()[0]
         output += '\n   if( key.compare("'+self.tableName()+'.'+paramName.lower()+'") == 0) {'
         output += '\n       return (&this->'+paramName+'.getValue());'
         output += '\n    }'
      output +='\n}'
      return output

   def writeSetValueImpl(self):
      """Writes the set value function for the class varibles"""
      output   = '\n\nvoid '+self.className()+ \
                  '::setValue(string key, void* value) {'
      for aParamDict in self.schema :
         paramName = aParamDict.keys()[0]
         paramType = aParamDict.values()[0]
         paramMap =  self.cppsqltypemap[paramType]
         output += '\n    if( key.compare("'+self.tableName()+'.'+paramName.lower()+'") == 0) {'
         output += '\n         this->'+paramName+' = *(('+paramMap+'*) value) ;' 
         output += '\n    }'
      output +='\n}'
      return output

   def writeDict(self, inDict, dictName) :
      """Writes a dictionary as C++ map"""
      output = "\n"
      for akey in inDict.keys():
         param = akey
         value = inDict[akey]
         output += '\n    '+dictName+'.insert(Entry("'+param+'", "'+ value +'"));'
      return output


   def writeDictList(self, inDictList, mapName) :
      """Writes a List of dcitionary as C++ map"""
      output = "\n"
      for aDict in inDictList:
         param = aDict.keys()[0]
         value = aDict.values()[0]
         output += '\n    '+mapName+'.insert(Entry("'+param+'", "'+ value +'"));'
      return output

   def writeListOfList(self, inlList, llistName) :
      """Writes a List of dcitionary as C++ map"""
      output = "\n"
      output += '\n    list<string> tmplist;' 
      for anItemlist in inlList:
         for anItem in anItemlist:
            output += '\n    tmplist.push_back("'+anItem+'");'
         output += '\n    '+llistName+'.push_back(tmplist);'
         output += '\n    tmplist.clear();'
      return output

   def writeList(self, inList, listName) :
      """Writes a List of dcitionary as C++ map"""
      output = "\n"
      for anItem in inList:
         output += '\n    '+listName+'.push_back("'+anItem+'");'
      return output
 
   def writeParamsDef(self):
      """ write parameters in C++ format"""
      output = "\nprivate:"
      for aParamDict in self.schema:
           paramName = aParamDict.keys()[0]
           paramType =aParamDict.values()[0]
           output += '\n' + '     '+   paramType + ' ' +  paramName + ';'
      return output

   def writeDBBindingDef(self):
      """write the Def for DB Binding Classes"""
      bindingclassname = self.className()+'_DB_BINDING'
      output = '\n\nclass  '+bindingclassname+\
               ' : public BaseSchemaNConstraintsBinding {'
      output += '\n    public:'
      output += '\n      '+self.className()+'_DB_BINDING(); '
      output += '\n      virtual string* getTableName(void);'
      output += '\n    private:'
      output += '\n string TableName;' 
      output += '\n' + '};'
      self.rownschemabinding=bindingclassname
      return output
  
   def addTableNameToParamDictList(self, inDictList):
       outList=[]
       for aParamDict in inDictList:
          tmpDict={}
          paramName = self.tableName() +'.'+aParamDict.keys()[0].lower()
          paramType =aParamDict.values()[0]
          tmpDict[paramName]=paramType
          outList.append(tmpDict)
       return outList
 
   def addTableNameToParamList(self, inList):
       outList=[]
       for anitem in inList:
          outList.append(self.tableName() +'.'+anitem.lower())
       return outList

   def addTableNameToParamListOfList(self, inList):
       outList=[]
       for aList in inList: 
         newList=[] 
         for anitem in aList:
           newList.append(self.tableName() + '.'+anitem.lower())
         outList.append(newList) 
       return outList

   def writeDBBindingImpl(self):
      """write the Impl for DB Binding Classes"""
      output = '\n\n'+self.className()+'_DB_BINDING::'+self.className()+'_DB_BINDING() {'
      output += '\n    TableName = "'+self.tableName()+'";'
      
      output += self.writeDictList(self.addTableNameToParamDictList(self.schema), "Schema")
      output += self.writeList(self.addTableNameToParamList(self.primarykeys), "PrimaryKeys")
      #### No Need in Single Table# output += self.writeList(self.addTableNameToParamList(self.foreignkeys), "ForeignKeys")
      output += '\n\n    ///Unique Keys being written as Set of list' 
      output += '\n\n    ///List of Lists in C++'
      output += self.writeListOfList(self.addTableNameToParamListOfList(self.uniquekeys), "UniqueKeys")
      output += self.writeList(self.addTableNameToParamList(self.notnulls), "NotNullKeys")
      output += self.writeDictList(self.addTableNameToParamDictList(self.constraints), "Constraints")
      #### No Need in Single Table# output += self.writeDictList(self.addTableNameToParamDictList(self.references), "References")

      schameOrder = []
      schameOrder.append(self.tableName())
      output += self.writeList(schameOrder, "SchemaOrder")

      output += '\n' + '}\n'
      output += '\nstring* '+self.className()+'_DB_BINDING::getTableName(void) {'
      output += '\n      return &this->TableName;'
      output += '\n' + '}\n'
      return output
  
   def writeRowSchemaNConstraintsBindingDef(self):
      """Create Schema/Constraint Class Template Specialization"""
      output = '\n\ntemplate<>'
      output += '\nclass RowSchemaNConstraintsBinding<' 
      output += self.className()+'> {'
      output += '\n   public:'
      output += '\n '+self.className()+'_DB_BINDING  schemaNconstraints;'
      output += '\n' + '};' +'\n\n//##############\n'
      return output
   
   def writeTypeDef(self):
      #typedef = "typedef TableTemplate<"+self.className()+">  "+\
      typedef = "typedef SingleTableInterface<"+self.className()+">  "+\
          self.tableName().title()+"Table;\n"
      return typedef
  
   def forTableTemplate(self): 
      #ttline = "\ntemplate SingleTableInterface<"+self.className()+">;"
      ttline = "\ntemplate TableTemplate<"+self.className()+">;"
      return ttline

   def forTableInterface(self):
      stint = "\ntemplate SingleTableInterface<"+self.className()+">;"
      return stint

