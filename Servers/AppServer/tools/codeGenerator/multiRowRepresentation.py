import os
import commands
import string
from ViewObjectLayer import MultiSchema 
from rowRepresentation import rowRepresentation


import pdb
class multiRowRepresentation(MultiSchema, rowRepresentation) :
   
   def __init__(self, multitablename, infkExcl = []) :
      MultiSchema.__init__(self, fkExcl = infkExcl ) 
      #self.tablename = multitablename + 'MultiRow' 
      self.cppsqltypemap = {'INTEGER':'int', 'STRING':'string',   \
                            'INTEGER':'int','CHARACTER':'char',   \
                            'INTEGER':'smallint',   }
      self.FKTableMap = {}
      self.selectstmt = ''
      self.writeSetValueImpl_txt=''
      self.multitablename = multitablename

      self.singlereferences = {}
      self.multiplereferences = {}
      self.externalreferences = {}
      
      self.excludethisrelation = []
      self.excludethismultirelation = []
      self.addthisrelation = []
      self.addthismultirelation = []
  
   def ExcludeThisRelation(self, relation):
      self.excludethisrelation.append(relation) 

   def ExcludeThisMultiRelation(self, relation):
      self.excludethismultirelation.append(relation)

   def AddThisRelation(self, relation):
      self.addthisrelation.append(relation)

   def AddThisMultiRelation(self, relation):
      self.addthismultirelation.append(relation)

   def className(self):
     return (self.multitablename + 'MultiRow').title()

   def tableName(self):
      
      if self.multitablename.find("MultiRow") != -1:
         return self.tablename.split('MultiRow')[0].lower()
      return self.multitablename

   def writeSchemaInfo(self):
     thisSchemaInfo = self.schemaInfo()
     for aTableName in thisSchemaInfo.keys() :
         nary = []
         nary.append(thisSchemaInfo[aTableName]['Nary'])
         output += self.writeList(nary, 'tmpnarylist')
         fklist = thisSchemaInfo[aTableName]['FKList']
         output += self.writeList(fklist, 'tmpfklist')      

   def getSchema(self):
      newSchema = {}
      for aParam in self.attributes():
         newSchema[self.makeAttributeName(aParam)] = self.types(aParam)
      return newSchema
     
   def writeClassDef(self) :
      """Write the class definition"""

      self.setSchemaOrder()
      self.setReferences()

      output = "\nclass "+self.className()+'  : public RowInterface {'
      output += "\npublic:"
      output += "\n"
      output += "\n     "+self.className()+"();"
      output += "\n     ~"+self.className()+"();"
      output += "\n\n     virtual void* getValue(string key);"
      output += "\n     virtual void setValue(string key, void* value);\n"
      output += "\nprivate:"
      self.writeSetValueImpl_txt=self.writeSetValueImpl()

      output += self.writeConstituentTablesDef()
      output += "\n};"
      output +=  self.writeDBBindingDef()
      output += self.writeRowSchemaNConstraintsBindingDef()
      return output

   def writeClassImpl(self) :
      """Write the class definition"""
      output = ""
      output += self.writeSetValueImpl_txt
      output += self.writeGetValueImpl()
      output += self.writeConstituentTablesImpl()      

 
      output += self.writeDBBindingImpl()
      #output += self.writeGetSelectFunc() 
      return output

   def getFKlist(self) :
      foreignkeylist = []
      for akey in self.foreignKeys().keys():
         foreignkeylist.append(akey)
      return foreignkeylist

   def setReferences(self):

      allReferences = self.foreignKeys()

      #inverseRef = inverted_dict = dict([[v,k] for k,v in allReferences.items()])
      for anEntry in allReferences :
          tableName, paramName, junk = self._ParseKey(anEntry)
          foreignKey = allReferences[anEntry]
          for revEntry in allReferences :
            revtableName, revparamName, junk = self._ParseKey(revEntry)
            revforeignKey = allReferences[revEntry]
            if revforeignKey == foreignKey and tableName == revtableName and paramName != revparamName :
               #print anEntry, allReferences[anEntry] 
               #print revEntry, allReferences[revEntry]
               if not (self.multiplereferences.has_key(anEntry) and self.multiplereferences[anEntry] == allReferences[anEntry]):
                  #Check that the entry is not a foreign reference
                  tb, cl, fk = self._ParseKey(allReferences[anEntry])
                  if tb in self.schameOrder :
                     self.multiplereferences[anEntry] = allReferences[anEntry]
                  else:
                     self.externalreferences[revEntry] = allReferences[revEntry]
               if not (self.multiplereferences.has_key(revEntry) and self.multiplereferences[revEntry] == allReferences[revEntry]):
                  #Check that the entry is not a foreign reference
                  tb, cl, fk = self._ParseKey(allReferences[anEntry])
                  if tb in self.schameOrder :
                     self.multiplereferences[revEntry] = allReferences[revEntry]
                  else:
                     self.externalreferences[revEntry] = allReferences[revEntry]

      for anEntry in allReferences :
          if not self.singlereferences.has_key(anEntry) and not self.multiplereferences.has_key(anEntry):
             #Check that the entry is not a foreign reference
             tb, cl, fk = self._ParseKey(allReferences[anEntry])
             if tb in self.schameOrder :
                self.singlereferences[anEntry] = allReferences[anEntry]
             else:
                self.externalreferences[revEntry] = allReferences[revEntry]


   def setSchemaOrder(self):
      self.schameOrder = []
      for aSchemaOrder in self.schemaOrder():
         self.schameOrder.append(aSchemaOrder.names()[0])

   def writeDBBindingImpl(self):
      """write the Impl for DB Binding Classes"""
      
      output = '\n\n'+self.className()+'_DB_BINDING::'+self.className()+'_DB_BINDING() {'
      output += '\n    TableName = "'+self.tableName()+'";'
      #output += self.writeSchemaInfo()
      output += self.writeDict(self.getSchema(), "Schema")
      output += self.writeList(self.primaryKey(), "PrimaryKeys")
      output += self.writeList(self.getFKlist(), "ForeignKeys")
      output += self.writeListOfList(self.uniqueKeys(), "UniqueKeys")
      nn = []
      for ann in self.notNull() :
         nn.append(self.makeAttributeName(ann))
      output += self.writeList(nn, "NotNullKeys")

      output += self.writeList(self.schameOrder, "SchemaOrder")

      #### If user has defined any addditional relations to show up
      if len(self.addthisrelation) != 0:
         for arelkey in self.addthisrelation :
            key, value = arelkey.popitem() 
            self.singlereferences[key]=value
      #######If User want to exculude some relations from showing up 
      if len(self.excludethisrelation) != 0:
        exclist=[]
        for arel in self.excludethisrelation:
            key, value = arel.popitem()
            exclist.append(key)
        newsing={}
        for key,value in self.singlereferences.items() :
            if key not in exclist:
               newsing[key]=value
        self.singleplereferences = newsing

      output += self.writeDict(self.singlereferences, "References")
      #### If user has defined any addditional relations to show up
      if len(self.addthismultirelation) != 0:
         for arelkey in self.addthismultirelation :
             key, value = arelkey.popitem()
             self.multiplereferences[key]=self.addthismultirelation[value]
      #######If User want to exculude some relations from showing up
      if len(self.excludethismultirelation) != 0:
        exclist=[] 
        for arel in self.excludethismultirelation:
            key, value = arel.popitem()
            exclist.append(key)
        newmulti={}
        for key,value in self.multiplereferences.items() :
            if key not in exclist:
               newmulti[key]=value 
        self.multiplereferences = newmulti    
      output += self.writeDict(self.multiplereferences, "MultiReferences")
      output += self.writeDict(self.externalreferences, "ExternalReferences")
      #output += self.writeRealPKs()  
      output += '\n' + '}\n'
      output += '\nstring* '+self.className()+'_DB_BINDING::getTableName(void) {'
      output += '\n      return &this->TableName;'
      output += '\n' + '}\n'

      return output 
      
   def writeConstituentTablesDef(self):
      """write objects of constituent tables"""
      output = "\n"
      objList = []
      allConstituentTables = self.names()
      for aTable in allConstituentTables :
        if self.FKTableMap.values().count(aTable) > 0 :
           for akey in self.FKTableMap.keys() :
               if self.FKTableMap[akey] == aTable :
                  objName = self.makeAttributeName(akey).replace('.','_')+'Obj'
                  objName = objName.title()
                  if objName not in objList:
                   output += '\n    '+aTable.title()+ 'row* '+objName+ ';'
                  else :
                   nameCount = objList.count(objName) + 1
                   output += '\n    '+aTable.title()+ 'row* '+objName + nameCount+ ';'
                   objList.append(objName) 
        else:
           objName = aTable.replace('.','_')+'Obj'
           objName = objName.title()
           if objName not in objList:
              output += '\n    '+aTable.title()+ 'row* '+objName+ ';'
           else :
              nameCount = objList.count(objName) + 1
              output += '\n    '+aTable.title()+ 'row* '+objName+nameCount +';'
              objList.append(objName)
      return output


   def writeConstituentTablesImpl(self):
      """write objects of constituent tables"""
      
      #print self.FKTableMap
      output2 = "\n\n"+self.className()+"::~"+self.className()+"(){"
      output = "\n\n"+self.className()+"::"+self.className()+"(){"
      allConstituentTables = self.names() 
      objList = []
      for aTable in allConstituentTables :
        if self.FKTableMap.values().count(aTable) > 0 :
           for akey in self.FKTableMap.keys() :	 
               if self.FKTableMap[akey] == aTable :
                  objName = self.makeAttributeName(akey).replace('.','_')+'Obj'
                  if objName not in objList:
                    objName = objName
                  else :
                    nameCount = objList.count(objName) + 1
                    objName = objName + nameCount
                    objList.append(objName)
                  objName = objName.title() 
                  output += '\n    this->'+objName+ ' = new '+aTable.title()+ 'row();' 
                  output += '\n    this->rowMap.set("'+akey.lower()+'", (void*)this->'+objName+');'
                  output += '\n    this->constituentObjects.push_back(this->rowMap);'
                  output2 += '\n   delete this->'+objName+';'
        else:
           objName = aTable.replace('.','_')+'Obj'
           if objName not in objList:
              objName = objName
           else :
              nameCount = objList.count(objName) + 1
              objName = objName+nameCount
              objList.append(objName)
           objName = objName.title()
           output += '\n    this->'+objName+ ' = new '+aTable.title()+ 'row();'
           output += '\n    this->rowMap.set("'+aTable.lower() + 'row", (void*)this->'+objName.title()+');'
           output += '\n    this->constituentObjects.push_back(this->rowMap);'
           output2 += '\n   delete this->'+objName+';'
      output += "\n}"
      output2 += "\n}"

      output += output2
      return output

   def writeGetConstituentRow(self) :
     output  = "\n\nRowInterface& "+self.className()+"::getConstituentRow(string table, string fkkey){"
     output += "\n    if (fkkey.compare("") == 0) {"
     output += "\n        key = table;"
     output += "\n    }"
     output += "\n    else {"
     output += "\n        key = fkkey;"
     output += "\n    }"   
     output += "\n    for (multimapStringTable_Iter i=constituentObjects.begin();"
     output += "\n          i< constituentObjects.end(); i++ ){"
     output += "\n    if (key.compare(i->first) == 0) {"
     output += "\n           return i->second;"
     output += "\n        }"  
     output += "\n    }" 
     output += "\n}"
     return output

   def writeParamsDef(self):
      """ write parameters in C++ format"""
      output = "\nprivate:"
      for aParam in self.attributes():
         paramType = self.types(aParam)
         output += '\n' + '     '+ paramType + ' ' + self.makeAttributeName(aParam) + ';'
      return output

   def writeGetSelectFunc(self):
       output = '\n string '+self.className()
       output +=        '::getSelectStmt(void) {'
       stmt = 'SELECT '+self.selectstmt 
       stmt += '\n         FROM '
       allConstituentTables = self.names()
       ayya = 0
       for aTable in allConstituentTables :
        if ayya == 0 :  
          stmt += aTable
          ayya = 1     
        else : stmt += ', '+aTable
       curritr=0
       whereclause=" WHERE "
       for srefk in self.singlereferences.keys():
         if curritr!=0: whereclause += ' AND '
         whereclause += '\n         ('+srefk+'='+self.singlereferences[srefk] +') '  
         curritr=1
       curritr=0
       for mref in self.multiplereferences.keys():
          ctab, ccol, cfk = self._ParseKey(mref)
          if curritr == 0:
             whereclause += '\n         AND ('+mref+'='+self.multiplereferences[mref]
             curritr = 1
          else:
             if ctab == ptab:
                whereclause += '\n         OR '+mref+'='+self.multiplereferences[mref]+') '
             else:
                whereclause += '\n         AND ('+mref+'='+self.multiplereferences[mref] 
          ptab, pcol, pfk = self._ParseKey(mref)
       stmt += ' '+whereclause
       output += '\n     string stmt = "'+stmt+'";' 
       output += '\n     return stmt;'       
       output += '\n}'
       return output


   def writeRealPKs(self):
     allConstituentTables = self.names()

   def writeSetValueUtil(self, tableName, paramName, foreignKey):
       """A Utility function or SetValue""" 
       if foreignKey in ('""', None) : 
         const = '(string)"'+tableName+'row", "'
       else:
         const = '(string)"'+tableName+'row", "'+ foreignKey

       val =  tableName+'.'+paramName
       output = '\n         (('+tableName.title()+'row*)this->' 
       output += '\n          getConstituentRow('+const.lower() + '"))->'
       output += '\n           setValue((string)"'+val.lower()+ '", value);'
       return output


   def writeSetValueImpl(self):
      """Write the setValue member function for the class variables"""
      output   = '\n\nvoid '+self.className()+ \
                 '::setValue(string key, void* value) {'
      alltoignore = self.singlereferences.keys()
      alltoignore.extend(self.singlereferences.values())
      #alltoignore.extend(self.multiplereferences.keys())
      alltoignore.extend(self.multiplereferences.values())
 
      for aParam in self.attributes() :
         tableName, paramName, foreignKey = self._ParseKey(aParam)

         if foreignKey != None :
             if not self.FKTableMap.has_key(foreignKey) :
               self.FKTableMap[foreignKey] = tableName
             output += '\n    if( key.compare("'+ \
                  self.makeAttributeName(aParam).lower()+'") == 0) {'
             output += self.writeSetValueUtil(tableName, paramName, foreignKey)  

             ###FK Appears on RHS of the variable names, represents that its a MultiRef to Another Table 
             ### So the Other Appropriate Ref should also be set.

             if self.multiplereferences != {} :
                if self.multiplereferences.has_key(foreignKey):
                  if self.multiplereferences[foreignKey] == (tableName+'.'+paramName) :
                     fktb, fkparam, nofk = self._ParseKey(foreignKey)
                     output += self.writeSetValueUtil(fktb, fkparam, nofk)
             output += '\n    }'
             self.selectstmt += '\n         '+tableName.title()+'.'+paramName+' AS \\"'+self.makeAttributeName(aParam)+'\\"'
         else :
               
             ######## If param Appears in SingleReferences, then it got to be set in a composite SetValue.....
             if (tableName+'.'+paramName).lower() in alltoignore:
                continue
             foreignKey = '""'
             output += '\n    if( key.compare("'+ \
                    self.makeAttributeName(aParam).lower()+'") == 0) {'
             output += self.writeSetValueUtil(tableName, paramName, foreignKey)
             output += '\n    }'
             self.selectstmt += '\n         '+tableName.title()+'.'+paramName+' AS \\"'+self.makeAttributeName(aParam)+'\\"'

      for ref in self.singlereferences.keys():
         refed = self.singlereferences[ref]
         ############YaYA I have to make a small Utilityy function., will do. 
         output += '\n    if( key.compare("'+ \
               ref.lower()+'") == 0) {'
         tb, param, nofk = self._ParseKey(ref)
         output += self.writeSetValueUtil(tb, param, nofk)
         tb, param, nofk = self._ParseKey(refed)
         output += self.writeSetValueUtil(tb, param, nofk)
         output += '\n    }'
         output += '\n    if( key.compare("'+ \
               refed.lower()+'") == 0) {' 
         tb, param, nofk = self._ParseKey(ref)
         output += self.writeSetValueUtil(tb, param, nofk)
         tb, param, nofk = self._ParseKey(refed)
         output += self.writeSetValueUtil(tb, param, nofk)
         output += '\n    }'  
      output +='\n}'
      return output


   def writeGetValueImpl(self):
      """Write the getValue member function for the class variables"""
      output   = '\n\nvoid* '+self.className()+'::getValue(string key) {'
      for aParam in self.attributes() :
         tableName, paramName, foreignKey = self._ParseKey(aParam)

         output += '\n   if( key.compare("'+ \
             self.makeAttributeName(aParam).lower()+'") == 0) {'
         if foreignKey != None :
             output += '\n       return   (('+tableName.title()+'row*)this->'
             output += '\n          getConstituentRow("'+(tableName+'row", "'+ foreignKey).lower()+'"))->'
             output += '\n           getValue((string)"'+(tableName+'.'+paramName).lower()+ '");'
         else :
             foreignKey = '""'
             output += '\n       return  (('+tableName.title()+'row*)this->'
             output += '\n          getConstituentRow("'+(tableName+'row", '+ foreignKey).lower()+ '))->'
             output += '\n           getValue((string)"' +(tableName+'.'+paramName).lower()+ '");'
         output += '\n    }'
      output +='\n}'
      return output

   def makeAttributeName(self, inAttrib):
      tableName, attrName, fKeyLabel = self._ParseKey(inAttrib)
      output = tableName+'.'+attrName
      if fKeyLabel != None :
         fkTable, fkName, fizol = self._ParseKey(fKeyLabel)
         output += '.'+fkTable+'.'+fkName
      return output   

   def _ParseKey(self, key) :
        """
        Convenience method parses keys of the following structure: 
            attribute
            table.attribute
            table.attribute(reftable.refattribute)
        and returnes the tuple (table, attribute, reftable.refattribute)
        Strips leading or trailing whitespace.
        """
        tableName = None
        attrName = None
        fKeyLabel = None
        if not key.find('.') >= 0 :
            attrName = key.strip()
        else :
            tableName = key.split('.')[0].strip()
            if not key.find('(') >= 0 :
                attrName = key.split('.')[1].strip()
            else :
                attrName = key.split('.')[1].split('(')[0].strip()
                fKeyLabel = key.split('(')[1].split(')')[0].strip()
        return (tableName, attrName, fKeyLabel)

   def writeTypeDef(self):
      #typedef = "typedef TableTemplate<"+self.className()+">  "+\
      typedef = "typedef MultiTableInterface<"+self.className()+">  "+\
          self.tableName().title()+"MultiTable;\n"
      return typedef

   def forTableTemplate(self):
      #ttline = "\ntemplate MultiTableInterface<"+self.className()+">;"
      ttline = "\ntemplate TableTemplate<"+self.className()+">;"
      return ttline

   def forTableInterface(self):
      stint = "\ntemplate MultiTableInterface<"+self.className()+">;"
      return stint


   def writeSetValueImplOLD(self):
      """Writes the set value function for the class varibles"""
      output   = '\n\nvoid '+self.tableName()+ \
                 '::setValue(string key, void* value) {'
      for aParam in self.attributes() :
         tableName, paramName, foreignKey = self._ParseKey(aParam)
         paramType = self.types(aParam)
         paramMap = self.cppsqltypemap[paramType]
         # Try setting a.x part
         # is key is of type a.x(b.y)
         if foreignKey != None :
            if not self.FKTableMap.has_key(foreignKey) :
               self.FKTableMap[foreignKey] = tableName
               output += '\n    if( key.compare("'+ \
                    self.makeAttributeName(aParam)+'") == 0) {'
               const = tableName+'row", "'+ foreignKey
               val = tableName+'.'+paramName
               output += '\n         (('+tableName.title()+'row*)this->getConstituentRow("'+const.lower()+ \
                         '"))->setValue((string)"' + val.lower() + '", value);'
               self.selectstmt += '\n         '+tableName.title()+'.'+paramName+' AS \\"'+self.makeAttributeName(aParam)+'\\"'
               output += '\n    }'
            # Try setting b.y part if it not in fklist
            if foreignKey not in self.getFKlist():
               fkTable, fkName, fkJunk = self._ParseKey(foreignKey)
               fkJunk = '""'  # with an empty FK
               const = fkTable+'row", '+ fkJunk
               val = fkTable+'.'+fkName
               output += '\n         (('+tableName.title()+'row*)this->getConstituentRow("'+const.lower()+ \
                      '))->setValue((string)"'+val.lower()+ '",  value);'
               self.selectstmt += '\n         '+fkTable+'.'+fkName+' AS \\"'+self.makeAttributeName(aParam)+'\\"'
               output += '\n    }'
         # There is NO b.y part, so set a.x
         else :
            foreignKey = '""'
            output += '\n    if( key.compare("'+ \
                    self.makeAttributeName(aParam)+'") == 0) {'
            const = tableName+'row", '+ foreignKey
            val = tableName+'.'+paramName
            output += '\n         (('+tableName.title()+'row*)this->getConstituentRow("'+const.lower()+ \
                    '))->setValue((string)"'+ val.lower()+ '", value);'
            self.selectstmt += '\n         '+tableName.title()+'.'+paramName+' AS \\"'+self.makeAttributeName(aParam)+'\\"'
            output += '\n    }'
      output +='\n}'
      #print self.FKTableMap
      return output

