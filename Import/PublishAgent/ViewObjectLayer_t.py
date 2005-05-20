import ViewObjectLayer
import unittest
import copy

def listCompare(tester, list1, list2) : 
    for item in list1 : 
        tester.failUnless(item in list2) 
    for item in list2 : 
        tester.failUnless(item in list1) 

class SchemaInterfaceTest(unittest.TestCase) : 

    def testParseKey(self) : 
        for testKey in ["table.attribute", " table.attribute", \
                  " table . attribute "] : 
            tb, at, fk = ViewObjectLayer.SchemaInterface._ParseKey(testKey)
            self.assertEqual("table", tb)
            self.assertEqual("attribute", at)
            self.assertEqual(None, fk)
        for testKey in ["table.attribute(fkey)", " table.attribute (fkey)", \
                  " table . attribute ( fkey ) "] : 
            tb, at, fk = ViewObjectLayer.SchemaInterface._ParseKey(testKey)
            self.assertEqual("table", tb)
            self.assertEqual("attribute", at)
            self.assertEqual("fkey", fk)

    def testParseCondition(self) : 
        condition = " bling.bling = bam.bam(fork) " 
        lhs, rhs = ViewObjectLayer.SchemaInterface._ParseCondition(condition)
        self.assertEqual("bling.bling", lhs)
        self.assertEqual("bam.bam(fork)", rhs)
        condition = " bling.bling = bam.bam (fork) " 
        lhs, rhs = ViewObjectLayer.SchemaInterface._ParseCondition(condition)
        self.assertEqual("bling.bling", lhs)
        self.assertEqual("bam.bam (fork)", rhs)

    def testAbstractClass(self) : 
        a = ViewObjectLayer.SchemaInterface(1, 2, 3, 4, 5, 6, 7, 8)
        for method in ViewObjectLayer.SchemaInterface._interfaceMethods : 
            expr = "a."+method+"()"
            try : 
                exec(expr)
            except ViewObjectLayer.AbstractMethodCall, e : 
                pass

class SingleSchemaTest(unittest.TestCase) : 

    def testInterface(self) : 
        schemaName = "singleschema"
        attributes = ["a", "b", "c", "d"]
        types = {"a":"string", "b":"int", "c":"string", "d":"int"}
        nonNull = ["b", "c"]
        pk = ["b"]
        uk = [["a", "c"], ["d"]]
        fk = {"c": "ss2.c", "d":"ss3:f", "b" : "singleschema.b"}
        vc = [" d < 2 ", " a = c "]
        singleschema1 = ViewObjectLayer.SingleSchema(schemaName, \
            attributes, types, pk, uk, fk, nonNull, [], \
            vc)
        singleschema2 = ViewObjectLayer.SingleSchema(copy.deepcopy(schemaName), \
            copy.deepcopy(attributes), copy.deepcopy(types), copy.deepcopy(pk), \
            copy.deepcopy(uk), copy.deepcopy(fk), copy.deepcopy(nonNull), [], \
            copy.deepcopy(vc))

        for singleschema in [singleschema1, singleschema2] : 

            self.assertEqual(schemaName, singleschema.names()[0])
            listCompare(self, attributes, singleschema.attributes())
            self.assertEqual("string", singleschema.types("a"))
            self.assertEqual("string", singleschema.types("c"))
            self.assertEqual("int", singleschema.types("b"))
            self.assertEqual("int", singleschema.types("d"))
            listCompare(self, pk, singleschema.primaryKey())
            listCompare(self, uk, singleschema.uniqueKeys())
            for k, v in singleschema.foreignKeys(0).items() : 
                self.assertEqual(fk[k], v)
            self.failIf("b" in singleschema.foreignKeys(1)) 
            listCompare(self, vc, singleschema.viewConditions())
            listCompare(self, nonNull, singleschema.notNull())
        

class SmartSchemaTest(unittest.TestCase) : 

    def setUp(self) : 

        self.name1 =  'person'
        self.attributes1 =  ['personid', 'name', 'distinguishedname', \
        'contactinfo', 'creationdate', 'createdby', 'lastmodificationdate', \
        'lastmodifiedby']
        self.types1 =  {'distinguishedname': 'string', 'creationdate': 'string', \
        'name': 'string', 'personid': 'int', 'lastmodifiedby': 'int', \
        'createdby': 'int', 'contactinfo':'string', 'lastmodificationdate': 'string'}
        self.primaryKey1 =  ['personid']
        self.uniqueKey1 =  [['distinguishedname']]
        self.foreignKeys1 =  {'lastmodifiedby': 'person.personid', 'createdby': 'person.personid'}
        self.nonNulls1 =  ['distinguishedname', 'contactinfo']
        self.sq1 = ['personid']
        self.personSchema = ViewObjectLayer.SingleSchema(self.name1, self.attributes1, self.types1, \
            self.primaryKey1, self.uniqueKey1, self.foreignKeys1, self.nonNulls1, self.sq1)

        self.name2 =  'role'
        self.attributes2 =  ['roleid', 'rolename', 'roledescription', 'creationdate', \
        'createdby', 'lastmodificationdate', 'lastmodifiedby']
        self.types2 =  {'roleid': 'int', 'lastmodifiedby': 'int', 'roledescription': 'string', \
        'createdby': 'int', 'rolename': 'string', 'creationdate': 'string', \
        'lastmodificationdate': 'string'}
        self.primaryKey2 =  ['roleid']
        self.uniqueKey2 =  [['rolename']]
        self.foreignKeys2 =  {'lastmodifiedby': 'person.personid', 'createdby': 'person.personid'}
        self.nonNulls2 =  ['rolename', 'roledescription']
        self.sq2 = ['roleid']
        self.roleSchema = ViewObjectLayer.SingleSchema(self.name2, self.attributes2, self.types2, \
            self.primaryKey2, self.uniqueKey2, self.foreignKeys2, self.nonNulls2, self.sq2)

        self.name3 =  'assignedrole'
        self.attributes3 =  ['assignedroleid', 'personid', 'roleid', 'creationdate', \
        'createdby', 'lastmodificationdate', 'lastmodifiedby']
        self.types3 =  {'personid': 'int', 'roleid': 'int', 'lastmodifiedby': 'int', \
        'createdby': 'int', 'assignedroleid': 'int', 'creationdate': 'string', \
        'lastmodificationdate': 'string'}
        self.primaryKey3 =  ['assignedroleid']
        self.uniqueKey3 =  [['personid', 'roleid']]
        self.foreignKeys3 =  {'personid': 'person.personid', 'roleid': 'role.roleid', \
        'lastmodifiedby': 'person.personid', 'createdby': 'person.personid'}
        self.nonNulls3 =  ['personid', 'roleid']
        self.sq3 = ['assignedroleid']
        self.assignedRoleSchema = ViewObjectLayer.SingleSchema(self.name3, self.attributes3, self.types3, \
            self.primaryKey3, self.uniqueKey3, self.foreignKeys3, self.nonNulls3, self.sq3)

        self.name4 =  'physicsgroup'
        self.attributes4 =  ['physicsgroupid', 'physicsgroupname', 'physicsgroupconvener', \
        'creationdate', 'createdby', 'lastmodificationdate', 'lastmodifiedby']
        self.types4 =  {'physicsgroupconvener': 'int', 'lastmodifiedby': 'int', \
        'createdby': 'int', 'physicsgroupname': 'string', 'physicsgroupid': 'int', \
        'creationdate': 'string', 'lastmodificationdate': 'string'}
        self.primaryKey4 =  ['physicsgroupid']
        self.uniqueKey4 =  [['physicsgroupname']]
        self.foreignKeys4 =  {'lastmodifiedby': 'person.personid', \
        'createdby': 'person.personid', 'physicsgroupconvener': 'person.personid'}
        self.nonNulls4 =  ['physicsgroupname']
        self.sq4 = ['physicsgroupid']
        self.groupSchema = ViewObjectLayer.SingleSchema(self.name4, self.attributes4, self.types4, \
            self.primaryKey4, self.uniqueKey4, self.foreignKeys4, self.nonNulls4, self.sq4)

    def tearDown(self) : 
        pass

    def testInterface(self) : 
        smart1 = ViewObjectLayer.SmartSchema(['createdby','lastmodifiedby'])
        smart1.addSchema(self.personSchema)
        smart1.addSchema(self.assignedRoleSchema)
        smart1.addSchema(self.roleSchema)
        smart1.addConditions(["assignedrole.personid = person.personid", "assignedrole.roleid = role.roleid"])
        names = ['role', 'person', 'assignedrole']
        listCompare(self, names, smart1.names())
        attlist = map(lambda x : self.name1 + '.' + x, self.attributes1) + \
                  map(lambda x : self.name2 + '.' + x, self.attributes2) + \
                  map(lambda x : self.name3 + '.' + x, self.attributes3) 
        for att in attlist : 
            self.failUnless(len(smart1._MatchAttribute(att)) > 0)
        listCompare(self, smart1.primaryKey(), ['assignedrole.assignedroleid'])
        for att, typ in self.types1.items() : 
            self.assertEqual(smart1.types(self.name1+'.'+att),typ)
        for att, typ in self.types2.items() : 
            self.assertEqual(smart1.types(self.name2+'.'+att),typ)
        for att, typ in self.types3.items() : 
            self.assertEqual(smart1.types(self.name3+'.'+att),typ)
        attlist = map(lambda x : self.name1 + '.' + x, self.nonNulls1) + \
                  map(lambda x : self.name2 + '.' + x, self.nonNulls2) + \
                  map(lambda x : self.name3 + '.' + x, self.nonNulls3) 
        for att in attlist : 
            self.failUnless(smart1._MatchAttribute(att)[0] in smart1.notNull())

        smart2 = ViewObjectLayer.SmartSchema(['createdby','lastmodifiedby','personid','roleid'])
        smart2.addSchema(self.personSchema)
        smart2.addSchema(self.assignedRoleSchema)
        smart2.addSchema(self.roleSchema)
        listCompare(self, smart2.primaryKey(), ['assignedrole.assignedroleid', \
              'person.personid', 'role.roleid'])
        smart2.addConditions(['assignedrole.roleid = role.roleid'])
        listCompare(self, smart2.primaryKey(), ['assignedrole.assignedroleid', \
              'person.personid'])
        smart2.addConditions(['assignedrole.personid = person.personid'])
        listCompare(self, smart2.primaryKey(), ['assignedrole.assignedroleid'])
   
    def testRows(self) : 
        smart1 = ViewObjectLayer.SmartSchema(['createdby','lastmodifiedby'])
        smart1.addSchema(self.personSchema)
        smart1.addSchema(self.assignedRoleSchema)
        smart1.addSchema(self.roleSchema)
        smart1.addConditions(["assignedrole.personid = person.personid", "assignedrole.roleid = role.roleid"])
        row = smart1.newRow()
        listCompare(self, row.possibleKeys(), smart1.attributes())
        row['person.distinguishedname'] = 'greg'
        row['role.rolename'] = 'admin'
        listCompare(self, row.keys(), ['person.distinguishedname(assignedrole.personid)', 'role.rolename(assignedrole.roleid)'])
        row['person.personid'] = 3
        row['role.roleid'] = 4
        row._FixRow(smart1.foreignKeys(5), [])
        self.assertEqual(row['person.personid'], row['assignedrole.personid'])        
        self.assertEqual(row['role.roleid'], row['assignedrole.roleid'])        
        self.assertEqual(row['person.personid'], 3)        
        self.assertEqual(row['role.roleid'], 4)
        row['assignedrole.roleid'] = 10
        row['assignedrole.personid'] = 20
        row._FixRow(smart1.foreignKeys(5), [])
        self.assertEqual(row['person.personid'], row['assignedrole.personid'])        
        self.assertEqual(row['role.roleid'], row['assignedrole.roleid'])        
        self.assertEqual(row['person.personid'], 3)        
        self.assertEqual(row['role.roleid'], 4)
        row = smart1.newRow()
        row['assignedrole.roleid'] = 10
        row['assignedrole.personid'] = 20
        row._FixRow(smart1.foreignKeys(5), [])
        self.assertRaises(KeyError, row.__getitem__, 'person.personid')
        self.assertRaises(KeyError, row.__getitem__, 'role.roleid')
        row = smart1.newRow()
        row['person.personid'] = 3
        row['role.roleid'] = 4
        row._FixRow({}, smart1.viewConditions())
        self.assertEqual(row['person.personid'], row['assignedrole.personid'])        
        self.assertEqual(row['role.roleid'], row['assignedrole.roleid'])        
        self.assertEqual(row['person.personid'], 3)        
        self.assertEqual(row['role.roleid'], 4)
        row = smart1.newRow()
        row['assignedrole.roleid'] = 10
        row['assignedrole.personid'] = 20
        row._FixRow({}, smart1.viewConditions())
        self.assertEqual(row['person.personid'], row['assignedrole.personid'])        
        self.assertEqual(row['role.roleid'], row['assignedrole.roleid'])        
        self.assertEqual(row['person.personid'], 20)        
        self.assertEqual(row['role.roleid'], 10)
        row = smart1.newRow()
        row['assignedrole.roleid'] = 10
        row['assignedrole.personid'] = 20
        row['person.personid'] = 3
        row['role.roleid'] = 4
        self.assertRaises(ViewObjectLayer.ViewObjectLayerException, row._FixRow, {}, smart1.viewConditions())
        

class SmartTableTest(unittest.TestCase) : 

    def setUp(self) : 

        self.name1 =  'person'
        self.attributes1 =  ['personid', 'name', 'distinguishedname', \
        'contactinfo', 'creationdate', 'createdby', 'lastmodificationdate', \
        'lastmodifiedby']
        self.types1 =  {'distinguishedname': 'string', 'creationdate': 'string', \
        'name': 'string', 'personid': 'int', 'lastmodifiedby': 'int', \
        'createdby': 'int', 'contactinfo':'string', 'lastmodificationdate': 'string'}
        self.primaryKey1 =  ['personid']
        self.uniqueKey1 =  [['distinguishedname']]
        self.foreignKeys1 =  {'lastmodifiedby': 'person.personid', 'createdby': 'person.personid'}
        self.nonNulls1 =  ['distinguishedname', 'contactinfo']
        self.sq1 = ['personid']
        self.personSchema = ViewObjectLayer.SingleSchema(self.name1, self.attributes1, self.types1, \
            self.primaryKey1, self.uniqueKey1, self.foreignKeys1, self.nonNulls1, self.sq1)

        self.name2 =  'role'
        self.attributes2 =  ['roleid', 'rolename', 'roledescription', 'creationdate', \
        'createdby', 'lastmodificationdate', 'lastmodifiedby']
        self.types2 =  {'roleid': 'int', 'lastmodifiedby': 'int', 'roledescription': 'string', \
        'createdby': 'int', 'rolename': 'string', 'creationdate': 'string', \
        'lastmodificationdate': 'string'}
        self.primaryKey2 =  ['roleid']
        self.uniqueKey2 =  [['rolename']]
        self.foreignKeys2 =  {'lastmodifiedby': 'person.personid', 'createdby': 'person.personid'}
        self.nonNulls2 =  ['rolename', 'roledescription']
        self.sq2 = ['roleid']
        self.roleSchema = ViewObjectLayer.SingleSchema(self.name2, self.attributes2, self.types2, \
            self.primaryKey2, self.uniqueKey2, self.foreignKeys2, self.nonNulls2, self.sq2)

        self.name3 =  'assignedrole'
        self.attributes3 =  ['assignedroleid', 'personid', 'roleid', 'creationdate', \
        'createdby', 'lastmodificationdate', 'lastmodifiedby']
        self.types3 =  {'personid': 'int', 'roleid': 'int', 'lastmodifiedby': 'int', \
        'createdby': 'int', 'assignedroleid': 'int', 'creationdate': 'string', \
        'lastmodificationdate': 'string'}
        self.primaryKey3 =  ['assignedroleid']
        self.uniqueKey3 =  [['personid', 'roleid']]
        self.foreignKeys3 =  {'personid': 'person.personid', 'roleid': 'role.roleid', \
        'lastmodifiedby': 'person.personid', 'createdby': 'person.personid'}
        self.nonNulls3 =  ['personid', 'roleid']
        self.sq3 = ['assignedroleid']
        self.assignedRoleSchema = ViewObjectLayer.SingleSchema(self.name3, self.attributes3, self.types3, \
            self.primaryKey3, self.uniqueKey3, self.foreignKeys3, self.nonNulls3, self.sq3)

        self.name4 =  'physicsgroup'
        self.attributes4 =  ['physicsgroupid', 'physicsgroupname', 'physicsgroupconvener', \
        'creationdate', 'createdby', 'lastmodificationdate', 'lastmodifiedby']
        self.types4 =  {'physicsgroupconvener': 'int', 'lastmodifiedby': 'int', \
        'createdby': 'int', 'physicsgroupname': 'string', 'physicsgroupid': 'int', \
        'creationdate': 'string', 'lastmodificationdate': 'string'}
        self.primaryKey4 =  ['physicsgroupid']
        self.uniqueKey4 =  [['physicsgroupname']]
        self.foreignKeys4 =  {'lastmodifiedby': 'person.personid', \
        'createdby': 'person.personid', 'physicsgroupconvener': 'person.personid'}
        self.nonNulls4 =  ['physicsgroupname']
        self.sq4 = ['physicsgroupid']
        self.groupSchema = ViewObjectLayer.SingleSchema(self.name4, self.attributes4, self.types4, \
            self.primaryKey4, self.uniqueKey4, self.foreignKeys4, self.nonNulls4, self.sq4)


    def testSmartTable(self) : 
        pass

def testSuite() : 
    suite1 = unittest.makeSuite(SchemaInterfaceTest, "test")
    suite2 = unittest.makeSuite(SingleSchemaTest, "test")
    suite3 = unittest.makeSuite(SmartSchemaTest, "test")
    suite4 = unittest.makeSuite(SmartTableTest, "test")
    return unittest.TestSuite([suite1, suite2, suite3, suite4])

if __name__ == "__main__" : 
    unittest.main()
