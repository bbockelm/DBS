import ConnectionLayer
import genException
import pdb
"""
Module to implement an View Object layer between a connection layer
and an object layer.  This layer should manage the constraints of the 
attributes in a consistent manner and do the elemental queries without 
aggregating into a solid database object (as done in the object layer.) 

Gregory Edwin Graham
22-Apr-2005

GEG 22-Apr-2005: Added "smart" insert method to try to do an insert and return 
                 the primary key.  If insertion fails because of unique 
                 constraints the primary key of the existing record is 
                 returned.

GEG 26-Apr-2005: Added CompositeTable implementation.  The CompositeTable 
                 object is a representation of a denormalized set of tables
                 with all of the usual methods.

GEG 11-May-2005: Restructured the implementation so that schemas and not 
                 tables were being combined.  This has the advantage of  
                 better encapsulation. 
"""
__revision__ = "$Revision: 1.9 $"
__version__ = "$Id: ViewObjectLayer.py,v 1.9 2005/05/18 19:13:36 ggraham Exp $"

class ViewObjectLayerException(genException.genException) : 
    """
    Exception class for SQL side of object layer.
    """
    def __init__(self, msgText, **kwargs) : 
        """
        Constructor
        """
        genException.genException.__init__(self, msgText, **kwargs)  
        self._Name = self.__class__.__name__


class AbstractMethodException(genException.genException) : 
    """
    Exception class for creating interfaces
    """
    def __init__(self, **kwargs) : 
        """
        Constructor
        """
        genException.genException.__init__(self, \
                       "An abstract method was called.", **kwargs)
        self._Name = self.__class__.__name__

def raiseAbstractException(module, Class, method) : 
    """
    General purpose exception raiser for abstract exceptions.
    This will raise the AbstractMethodException with the 
    call parameters.
    """
    raise AbstractMethodException(Module = module, \
                  Class = Class, \
                  Method = method)

class SchemaInterface: 
    """
    This interface defines methods to access and manipulate relational 
    database constraints on a single table or multi-table view.  
    """
    #  //
    # //  Public interface methods
    #//
    _interfaceMethods = ['names', \
                         'attributes', \
                         'types', \
                         'primaryKey', \
                         'uniqueKeys', \
                         'foreignKeys', 
                         'notNull', \
                         'defaults', \
                         'sequences', \
                         'addSeq', \
                         'getNextSeq', \
                         'setSeq', \
                         'addCondition', \
                         'getConditions', \
                         'info', \
                         'subSchema', \
                         'matchAttribute' ]
    #  //
    # //  For abstract class, public interface methods should cause exception
    #//
    for method in _interfaceMethods : 
        # Curry the arguments of the execption raiser and define 
        # method as an unboound attribute
        expr = "%s = lambda self, x='ViewObjectLayer', " % method + \
               "y='SchemaInterface', z='%s' " % method + \
               ": raiseAbstractException(x, y, z)"
        exec(expr)
        # include documentation for the help() viewer
        expr = "%s.__doc__ = '\\n   Abstract definition " % method + \
               "for interface method %s()   \\n'" % method
        exec(expr)


    #  //
    # //  Static methods for helping with key parsing and conditions parsing
    #//
    def _ParseKey(key) :
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
    _ParseKey = staticmethod(_ParseKey)

    def _ParseCondition(condition) : 
        """
        Parses a theta condition into left hand side and right hand side.
        Strips leading or trailing whitespace.
        """
        theta = ['>=', '<=', '=', '>', '<']
        for item in theta : 
            if condition.find(item) >= 0 :
                lhs = condition.split(item)[0].strip()
                rhs = condition.split(item)[1].strip()
                return (lhs, rhs, item)
        return None
    _ParseCondition = staticmethod(_ParseCondition)

    #  //
    # //  Constructor
    #//
    def __init__(self) : 
        """
        Constructor - Check that method definitions declared in 
        the public interface are actually defined.
        """
        for method in self._interfaceMethods : 
            # Check that method is defined
            if method in self.__dict__.keys() : 
                raise ViewObjectLayerException(\
                     "Missing method in interface implementation.", \
                     Method = "__init__", \
                     Module = "ViewObjectLayer", \
                     Interface = "SchemaInterface", \
                     Class = self.__class__.__name__)


class SingleSchema(SchemaInterface) : 
    """
    Class to represent schema from a single database table.
    """

    def __init__(self, name, attributes, types, pk, uk, fk, \
                       nn, df, sq, conditions = []) : 
        """
        This implementation of SchemaInterface is for a single table definition.
        """
        SchemaInterface.__init__(self)
        self._Name = name              # table name  (string)
        self._Attributes = attributes  # attribute elements (list) 
        self._Types = types            # attribute types  (dict) 
        self._PrimaryKey = pk          # primary key (list) 
        self._UniqueKeys = uk          # unique keys (list of lists) 
        self._ForeignKeys = fk         # foreign keys (dict) 
        self._NonNull = nn             # not null columns (list) 
        self._Defaults = df            # dict

        self._Sequences = {}           # These keep track of a numeric 
                                       # value that increments all the time.
                                       # Good for automatic primary keys.
        for item in sq : 
            self._Sequences[item] = -1

        self._ViewConditions = conditions  # View conditions 

    #  //
    # //  Public interface
    #//
    def names(self) : 
        """
        Accessor to the table name.  Returns a list of string names.
        For a SingleSchema, this list always has one element in it.
        Implements SchemaInterface::names().
        """
        return [self._Name]

    def attributes(self) : 
        """
        Accessor to table attribtes.  Returns all attrbutes in a list.
        Implements SchemaInterface::attributes()
        """
        # Note this is returned in plain format, not tableName.attrName 
        # format!
        return self._Attributes    

    def types(self, attribute) : 
        """
        Accessor to attribute types. Will return a string describing the 
        type of the given attribute. Implements SchemaInterface::types().
        """
        return self._Types[attribute]

    def defaults(self, attribute) : 
        """
        Accessor to attribute defaults. Will return a string describing the 
        default value of the given attribute, or None if it was not defined. 
        Implements SchemaInterface::types().
        """
        return self._Defaults.get(attribute, None)

    def primaryKey(self) : 
        """
        Accessor to primary key attributes.  This will return a list 
        of the attributes. Implements SchemaInterface::primaryKey()
       """
        return self._PrimaryKey

    def foreignKeys(self, flag = 1) : 
        """
        Accessor to foreign key definitions.  Will return a dictionary 
        of all foreign key definitions by default.  If the first bit is set, 
        then foreign keys that refer to the same table are excluded from 
        the list. The output format is {attr1 : table2:attr2} where 
        the sense is that attr1 references table2:attr2. (table1 is 
        understood to be this table.) Implements SchemaInterface::foreignKeys()
        """
        retval = {}
        for attr in self._ForeignKeys.keys() :  
            addAttr = 1
            if flag == 0 :
                pass
            elif flag & 1 == 1 : 
                tb, at, fk = SchemaInterface._ParseKey(self._ForeignKeys[attr])
                if self._Name == tb : 
                    addAttr = 0
            if addAttr == 1 : 
                retval[attr] = self._ForeignKeys[attr]
        return retval

    def uniqueKeys(self) : 
        """
        Accessor to list of unique keys.  Returns a list of all unique 
        keys defined on the table, each of which is a list of attributes.
        Implements SchemaInterface::uniqueKeys().
        """
        return self._UniqueKeys

    def notNull(self) : 
        """
        Accessor returns 1 (true) if the given attribute is not nullable
        or 0 (false) if it is allowed to be null.  Implements 
        SchemaInterface::notNull().
        """
        return self._NonNull

    def addCondition(self, condition) : 
        """
        Adds a list of conditions to the schema.  Implements 
        SchemaInterface::addCondition()
        """
        self._ViewConditions.append(condition)

    def getConditions(self) : 
        """
        Accessor returns list of view conditions. Implements 
        SchemaInterface::getConditions()
        """
        return self._ViewConditions

    def sequences(self) : 
        """
        This returns the existing attributes with sequencers defined.
        Implements SchemaInterface::sequences().
        """
        return self._Sequences.keys()

    def getNextSeq(self, item) : 
        """
        For the given attribute, this increments the associated sequence  
        counter and returns its value.  If no counter was defined, then 
        it raises an error.  Implements SchemaInterface::getNextSeq().
        """
        if item in self._Sequences.keys() :  
            self._Sequences[item] += 1
            return self._Sequences[item]
        else : 
            raise ViewObjectLayerException(\
                 "Tried to get sequence value of non-sequence attribute.", \
                 Class = "SingleSchema", \
                 Module = "ViewObjectLayer", \
                 Method = "getNextSeq", \
                 Attribute = item)

    def setSeq(self, item, val = -1) : 
        """
        Sets the value of a sequencer.  Implements SchemaInterface::setSeq().
        """
        if item in self._Sequences.keys() :  
            self._Sequences[item] = val
        else : 
            raise ViewObjectLayerException(\
                 "Tried to set sequence value of non-sequence attribute.", \
                 Class = "SingleSchema", \
                 Module = "ViewObjectLayer", \
                 Method = "getNextSeq", \
                 Attribute = item)

    def addSeq(self, item, value = -1) : 
        """
        Declares a sequencer on attribute.  Does not reset existing values.  Implements 
        SchemaInterface::addSeq().
        """ 
        if not item in self._Sequences.keys() : 
            self._Sequences[item] = value


    def matchAttribute(self, attr) : 
        """
        Maps attribute to table.attribute format.  For SingleSchema, 
        this always returns a unique attribute.  Implements 
        SchemaInterface::matchAttribute().
        """
        tb, at, fk = SchemaInterface._ParseKey(attr) 
        if tb == None : 
            return [self._Name + '.' + attr]
        else : 
            return [attr]

    def subSchema(self, selector) : 
        """
        For SingleSchema, this checks that the subschema asked for 
        is the same as self, and then returns self.  Implements 
        SchemaInterface::subSchema().
        """
        # If it is not asking for this schema, this could be an error.
        if selector != self._Name : 
            raise ViewObjectLayerException(\
                   "Tried to select a non-existing sub-schema.", \
                   Class = "SingleSchema", \
                   Method = "subSchema", \
                   Module = "ViewObjectLayer", \
                   RequestedSchema = selector, \
                   ExistingSchema = self._Name)
        return self

    def info(self, selector, field) :  
        """
        Returns information about the selected subschema in selector.
        If field is "FKList", a list of foreign keys exported by the
        subschema into the existing schema is returned.  If field is 
        "Nary", a 0 or 1 is returned is the relationship expected is 
        one to one or one to many respectively.  If field is something 
        else, None is returned.  Implements SchemaInterface::info().
        """
        # If it is not asking for this schema, this could be an error.
        if selector != self._Name : 
            raise ViewObjectLayerException(\
                   "Tried to select a non-existing sub-schema.", \
                   Class = "SingleSchema", \
                   Method = "subSchema", \
                   Module = "ViewObjectLayer", \
                   RequestedSchema = selector, \
                   ExistingSchema = self._Name)
        # Choose information to return
        if field == "FKList" : 
            # Always empty for a single table 
            return []
        elif field == "Nary" : 
            # Always 0 for a single table.
            return 0
        else : 
            return None

class MultiSchema(SchemaInterface) : 
    """
    This class represents the product of composing together schema 
    representing different tables.  
    """
    def __init__(self, fkExcl = []) : 
        """
        Constructor
        """
        SchemaInterface.__init__(self) 

        self._SchemaNames = []  # Names of attached schemas in FK order
        self._SchemaOrder = []  # Attached schemas in FK order
        self._SchemaInfo = {}   # Nary = 0 or 1, FKlist = []
        self._SchemaNameToSchemaMap = {}  # Map of name to schema
        
        self._Attributes = []   # All independent attributes in tb.at(fk) 
                                # format where tb is a table name, at is  
                                # an attribute name, and fk is a possible 
                                # foreign key target

        self._PrimaryKey = []   # List of attributes in the primary key
        self._ForeignKeys = {}  # Dictionary of all foreign key relationships
                                # of all schemas attached.

        self._ForeignKeyExclusionList = fkExcl  # When building attributes 
                                # and row structures, these are foriegn key 
                                # target attribute names to ignore.  This is
                                # be useful in some cases to exclude 
                                # bookkeeping information from semantically
                                # interesting relationships.

        self._ViewConditions = []  # View or join conditions

    #  //
    # //  Private helper methods
    #//
    def _FixSchemaInfo(self) : 
        """
        This searches for join conditions which may kick out a 
        foreign key from the foreign key list.
        """
        ctuples = map(lambda x : SchemaInterface._ParseCondition(x), \
                      self.getConditions() )
        ftuples = self.foreignKeys(5).items()

        for name in self.names() : 
            newl = []
            fktargets = self._SchemaInfo[name]['FKList']
            for fkt in fktargets : 
                if fkt in map(lambda x : x[0], ctuples) or \
                   fkt in map(lambda x : x[1], ctuples) : 
                    ffound = []
                    cfound = []
                    for ft in ftuples : 
                        if ft[0] == fkt : 
                            ffound.append(ft[1])
                    for cd in ctuples : 
                        if cd[0] == fkt : 
                            cfound.append(cd[1]) 
                        elif cd[1] == fkt : 
                            cfound.append(cd[0])  
                    for item in ffound : 
                        if item not in cfound :  
                            newl.append(fkt)
                else : 
                    newl.append(fkt)
            self._SchemaInfo[name]['FKList'] = newl
             
    def _MakeAttributes(self) : 
        """
        This recreates the attributes.
        """
        self._Attributes = []
        for schema in self._SchemaOrder : 
            schemaName = schema.names()[0]
            if len(self._SchemaInfo[schemaName]["FKList"]) == 0 : 
                self._Attributes = self._Attributes + \
                    map(lambda x : schemaName + '.' + x, \
                    schema.attributes())
            else : 
                for fkey in self._SchemaInfo[schemaName]["FKList"] :          
                    self._Attributes = self._Attributes + \
                        map(lambda x : schemaName + '.' + x + '(' + fkey + ')', \
                        schema.attributes())


    def _MakePrimaryKey(self) : 
        """
        Designates a primary key from those in the attached
        ComponentTables. In a pure Cartesian product, a primary 
        key is the cartesian product of the component primary keys.
        Starting with this as the primary key, attributes are
        thrown out if they appear in a join condition or internalized 
        foreign key.
        """        
        # Find a primary key of the cartesian product by direct product.
        # Attributes are added here in full Table.Attribute format.
        cartesianSuperkey = []
        for schema in self._SchemaOrder :
            schemaName = schema.names()[0] 
            if len(self._SchemaInfo[schemaName]['FKList']) == 0 : 
                for item in map(lambda x : schemaName + '.' + x, \
                                              schema.primaryKey()) : 
                    cartesianSuperkey.append(item)
            else:
                for fkey in self._SchemaInfo[schemaName]['FKList'] : 
                    for item in map(lambda x : schemaName + '.' + x + \
                            '(' + fkey + ')', schema.primaryKey()) : 
                        cartesianSuperkey.append(item)

        # Remove attributes from the primary key if they appear in a
        # join condition or foreign key condition.  This is conjugate to FixRow.
        retval = []
        for item in cartesianSuperkey :
            keep = 1 
            fks = self.foreignKeys(5) # Only internal non-loop foreign keys
            if item in map(lambda x : fks[x] + '(' + x + ')', fks.keys()): 
                keep = 0
            else : 
                for condition in self._ViewConditions : 
                    # Attributes in conditions are also expected to be in 
                    # full Table.Attribute format as well.
                    if condition.find(item) > 0 : 
                        # Find out if the item was left or right hand side
                        # We need to keep one side and not the other
                        lhs, rhs, th = SchemaInterface._ParseCondition(condition)
                        if item == lhs : 
                            otherTable = SchemaInterface._ParseKey(rhs)[0] 
                        else : 
                            otherTable = SchemaInterface._ParseKey(lhs)[0] 
                        # If the table is in the list of tables, then 
                        if otherTable in self._SchemaNames : 
                            keep = 0
            if keep == 1 :
                retval.append(item) 
        self._PrimaryKey = retval

    def _BuildUKList(self, tableList) : 
        """
        Utility function to build a list of unique keys
        by finding all possible direct products.  Recursive.
        """
        if len(tableList) == 0 : 
            # This condition should not happen.
            return [[]]
        elif len(tableList) == 1 : 
            # This should return the first (only) unique key in the table.
            return tableList[0]
        else : 
            # This should retrun a list consisting of the unique keys
            # from the head of the table direct product with all the others.
            retval = []
            for item1 in tableList[0] :  
                for item2 in self._BuildUKList(tableList[1:]) : 
                    newUK = item1 + item2
                    retval.append(newUK)
            return retval            

    #  //
    # //  Public interface methods
    #//
    def names(self) : 
        """
        Returns the list of Component table names.  
        This is empty until ComponentTables are added.
        Implements SchemaInterface::names()
        """
        return self._SchemaNames

    def attributes(self) : 
        """
        Returns the attributes in the CompositeTable.
        All attributes of components by table name are
        included.  Implements SchemaInterface::attributes().
        """
        return self._Attributes

    def types(self, attribute) : 
        """
        Returns the type of an attribute. The attribute should be given 
        in table.attribute format, but it will attempt to find a match if not.
        Implements SchemaInterface::types().
        """
        match = self.matchAttribute(attribute)
        if len(match) == 0 : 
            raise ViewObjectLayerException("Attribute not found.", \
                Method = "types", \
                Module = "ViewObjectLayer", \
                Class = "MultiSchema", \
                Hint = "Did you prepend the table name?", \
                RequestedAttribute = attribute)
        elif len(match) > 1 : 
            raise ViewObjectLayerException("Too many matching attributes found.", \
                Method = "types", \
                Module = "ViewObjectLayer", \
                Class = "MultiSchema", \
                Hint = "Did you append a foreign key target?", \
                RequestedAttribute = attribute, \
                MatchingAttributes = match.__str__())
        else : 
            tb, at, fk = SchemaInterface._ParseKey(match[0])
            return self._SchemaNameToSchemaMap[tb].types(at)

    def defaults(self, attribute) : 
        """
        Returns the default value of an attribute. The attribute should be given 
        in table.attribute format, but it will attempt to find a match if not.
        Implements SchemaInterface::defaults().
        """
        match = self.matchAttribute(attribute)
        if len(match) == 0 : 
            raise ViewObjectLayerException("Attribute not found.", \
                Method = "types", \
                Module = "ViewObjectLayer", \
                Class = "MultiSchema", \
                Hint = "Did you prepend the table name?", \
                RequestedAttribute = attribute)
        elif len(match) > 1 : 
            raise ViewObjectLayerException("Too many matching attributes found.", \
                Method = "types", \
                Module = "ViewObjectLayer", \
                Class = "MultiSchema", \
                Hint = "Did you append a foreign key target?", \
                RequestedAttribute = attribute, \
                MatchingAttributes = match.__str__())
        else : 
            tb, at, fk = SchemaInterface._ParseKey(match[0])
            return self._SchemaNameToSchemaMap[tb].defaults(at)

    def primaryKey(self) : 
        """
        Returns the primary key attributes.  Implements 
        SchemaInterface::primaryKey().
        """
        return self._PrimaryKey

    def uniqueKeys(self) : 
        """
        This function does not find all of the unique keys.
        Rather, we look for tables tht appear in the primary key 
        attributes and list direct products of unique keys from 
        those tables only on the strength that a unique key is
        functionally dependent on the primary key.  Implements
        SchemaInterface::uniqueKeys().
        """
        #  Find out how many tables are in the primary key
        schemasInPK = []
        for key in self.primaryKey() : 
            schemaName = SchemaInterface._ParseKey(key)[0]
            if schemaName not in schemasInPK : 
                schemasInPK.append(schemaName) 
        # Get lists of unique keys by table
        ukListByTable = {}
        for schemaName in schemasInPK : 
            ukListByTable[schemaName] = \
                   map(lambda x : map(lambda y : schemaName + '.' + y, x), \
                   self._SchemaNameToSchemaMap[schemaName].uniqueKeys()) + \
                   [self._SchemaNameToSchemaMap[schemaName].primaryKey()]
        # call the recursive function to get all of the unique keys
        return self._BuildUKList(ukListByTable.values())        

    def foreignKeys(self, flag = 1) : 
        """
        Accessor to foreign key definitions.  Will return a dictionary 
        of all foreign key definitions by default.  If the first bit is set, 
        then foreign keys that refer to the same table are excluded from 
        the list. If the second bit is set, then foreign keys that refer to 
        defferent tables but are still within the composite table are excluded.
        If the third bit is set then external references are excluded.
        If the fourth bit is set, foreign keys that are also in the join 
        conditions are excluded. The output format is 
        {table1.attr1 : table2:attr2} where the sense is that table1:attr1 
        references table2:attr2. Implements SchemaInterface::foreignKeys().
        """
        retval = {}
        for key, val in self._ForeignKeys.items() :  
            tb1, at1, fk1 = SchemaInterface._ParseKey(key)
            tb2, at2, fk2 = SchemaInterface._ParseKey(val)
            addAttr = 1
            if flag & 1 == 1 : 
                if tb1 == tb2 : 
                    addAttr = 0
            if flag & 2 == 2 : 
                if tb2 in self.names() : 
                    addAttr = 0
            if flag & 4 == 4 : 
                if tb2 not in self.names() : 
                    addAttr = 0
            if flag & 8 == 8 : 
                for condition in self.getConditions() : 
                    lhs, rhs, th = SchemaInterface._ParseCondition(condition)
                    if key == lhs and val == rhs :
                        addAttr = 0
                    if key == rhs and val == lhs :
                        addAttr = 0
            if addAttr == 1 : 
                retval[key] = val
        return retval

    def notNull(self) : 
        """
        Returns the defined not null constraints, 
        format is TableName.AttributeName. Implements
        SchemaInterface::notNull.
        """
        retval = []
        for schema in self._SchemaOrder : 
            schemaName = schema.names()[0]
            if len(self._SchemaInfo[schemaName]['FKList']) == 0 : 
                retval = retval + map(lambda x : schemaName + '.' + x, \
                    schema.notNull())
            else :  
                for fkey in self._SchemaInfo[schemaName]['FKList'] : 
                    retval = retval + map(lambda x : schemaName + '.' + x + \
                        '(' + fkey + ')', schema.notNull())
        return retval

    def addCondition(self, condition) : 
        """
        Adds a list of conditions to the schema.  Implements 
        SchemaInterface::addCondition()
        """
        self._ViewConditions.append(condition)
        self._MakePrimaryKey()
        self._MakeAttributes()
        self._FixSchemaInfo()

    def getConditions(self) : 
        """
        Accessor returns list of view conditions
        Implements SchemaInterface::viewConditions
        """
        return self._ViewConditions
 
    def sequences(self) : 
        """
        This returns the existing attributes with sequencers defined.
        Implements SchemaInterface::sequences().
        """
        retval = []
        for schema in self._SchemaOrder :  
            schemaName = schema.names()[0]
            retval = retval + map(lambda x : schemaName + '.' + x, \
                    schema.sequences())
        return retval

    def getNextSeq(self, attribute) : 
        """
        Gets the next integer in sequence for attribute is it
        has a sequencer defined. Uses TableName.Attribute name 
        for the attribute.  Implements SchemaInterface::getNextSeq().
        """
        match = self.matchAttribute(attribute)
        if len(match) == 0 : 
            raise ViewObjectLayerException("Attribute not found.", \
                Method = "getNextSeq", \
                Module = "ViewObjectLayer", \
                Class = "MultiSchema", \
                Hint = "Did you prepend the table name?", \
                RequestedAttribute = attribute)
        elif len(match) > 1 : 
            raise ViewObjectLayerException("Too many matching attributes found.", \
                Method = "getNextSeq", \
                Module = "ViewObjectLayer", \
                Class = "MultiSchema", \
                Hint = "Did you append a foreign key target?", \
                RequestedAttribute = attribute, \
                MatchingAttributes = match.__str__())
        else : 
            tb, at, fk = SchemaInterface._ParseKey(match[0])
            schema = self._SchemaNameToSchemaMap[tb]
            return schema.getNextSeq(at)

    def setSeq(self, attribute, val) :  
        """
        Sets the value of a sequencer.  Implements SchemaInterface::setSeq().
        """
        match = self.matchAttribute(attribute)
        if len(match) == 0 : 
            raise ViewObjectLayerException("Attribute not found.", \
                Method = "setSeq", \
                Module = "ViewObjectLayer", \
                Class = "MultiSchema", \
                Hint = "Did you prepend the table name?", \
                RequestedAttribute = attribute)
        elif len(match) > 1 : 
            raise ViewObjectLayerException("Too many matching attributes found.", \
                Method = "setSeq", \
                Module = "ViewObjectLayer", \
                Class = "MultiSchema", \
                Hint = "Did you append a foreign key target?", \
                RequestedAttribute = attribute, \
                MatchingAttributes = match.__str__())
        else : 
            tb, at, fk = SchemaInterface._ParseKey(match[0])
            schema = self._SchemaNameToSchemaMap[tb]
            return schema.setSeq(at, val)

    def addSeq(self, attribute, val = -1) : 
        """
        Declares a sequencer on attribute.  Does not reset existing values.  Implements 
        SchemaInterface::addSeq().
        """ 
        match = self.matchAttribute(attribute)
        if len(match) == 0 : 
            raise ViewObjectLayerException("Attribute not found.", \
                Method = "setSeq", \
                Module = "ViewObjectLayer", \
                Class = "MultiSchema", \
                Hint = "Did you prepend the table name?", \
                RequestedAttribute = attribute)
        elif len(match) > 1 : 
            raise ViewObjectLayerException("Too many matching attributes found.", \
                Method = "setSeq", \
                Module = "ViewObjectLayer", \
                Class = "MultiSchema", \
                Hint = "Did you append a foreign key target?", \
                RequestedAttribute = attribute, \
                MatchingAttributes = match.__str__())
        else : 
            tb, at, fk = SchemaInterface._ParseKey(match[0])
            schema = self._SchemaNameToSchemaMap[tb]
            return schema.addSeq(at, val)

    def subSchema(self, selector) : 
        """
        For MultiSchema, this returns the subschema named by the selector.
        If selector equals None, it then returns self.  Implements 
        SchemaInterface::subSchema().
        """
        if selector == None : 
            return self
        else : 
            return self._SchemaNameToSchemaMap[selector]

    def info(self, subselect, field) : 
        """
        Returns information about the selected subschema in subselect.
        If field is "FKList", a list of foreign keys exported by the
        subschema into the existing schema is returned.  If field is 
        "Nary", a 0 or 1 is returned is the relationship expected is 
        one to one or one to many respectively.  If field is something 
        else, None is returned.  Implements SchemaInterface::info().
        """
        return self._SchemaInfo[subselect].get(field, None)

    def matchAttribute(self, attr) : 
        """
        Maps attribute to table.attribute(fkey) format.  For MultiSchema, 
        this may not always return a unique attribute.  Implements 
        SchemaInterface::matchAttribute().
        """
        retval = []
        for item in self.attributes() :  
            tb, at, fk = SchemaInterface._ParseKey(item)
            if item == attr :  
                retval.append(item) 
            elif at == attr :
                retval.append(item)
            elif tb + '.' + at == attr :
                retval.append(item)
        return retval

    def addSchema(self, schema, nary = 0) : 
        """
        Adds a simple schema to the MultiSchema.
        Takes an instance of SimpleSchmea, and a flag to indicate if 
        many to one is desired. 
        """
        # Get the name of the schema to be added
        schemaName = schema.names()[0]
        # Test if this table is added yet
        if schemaName in self._SchemaNames : 
            raise ViewObjectLayerException("Tried to add existing schema.", \
               Module = "ViewObjectLayer", \
               Class = "MultiSchema", \
               Method = "addSchema")

        # Create info block
        self._SchemaInfo[schemaName] = {}
        self._SchemaInfo[schemaName]["Nary"] = nary
        self._SchemaInfo[schemaName]["FKList"] = []

        # Insert new table into the table order
        # maintaining foreign-key order.
        newOrder = []
        for exSchema in self._SchemaOrder : 
            exSchemaName = exSchema.names()[0]
            # Does the existing table depend on this one? 
            for fk, fv in exSchema.foreignKeys(1).items() :  
                if fv.find(schemaName) >= 0 :
                    newOrder.append(schema)
                    break
            newOrder.append(exSchema)  
        if len(self._SchemaOrder) == len(newOrder) : 
            newOrder.append(schema)
        self._SchemaOrder = newOrder        
        self._SchemaNameToSchemaMap[schemaName] = schema

        # Rebuild the name            
        self._SchemaNames = map(lambda x : x.names()[0], self._SchemaOrder)

        # Fix the foreign keys and schema info 
        for fkey, fval in schema.foreignKeys(0).items() : 
            fkeyAttr = SchemaInterface._ParseKey(fkey)[1]
            if not fkeyAttr in self._ForeignKeyExclusionList : 
                newKey = schemaName + '.' + fkey
                self._ForeignKeys[newKey] = fval
        for schemaName in self._SchemaNames : 
            self._SchemaInfo[schemaName]["FKList"] = []
        for fkey, fval in self.foreignKeys(5).items() : 
            newKey = SchemaInterface._ParseKey(fval)[0]  
            self._SchemaInfo[newKey]["FKList"].append(fkey)

        self._FixSchemaInfo()

        self._MakeAttributes() 

        self._MakePrimaryKey()


class RowInterface : 
    """
    Interface definition for a row class.  The row should be a dict, but 
    it should also have methods to handle special cases.
    """
    #  //
    # //  Public (and semi-public) interface methods
    #//
    _interfaceMethods = ['parentSchema', \
                         'possibleKeys', \
                         'availableSchemas', \
                         'schemaInfo', \
                         'getData', \
                         'newData', \
                         'setSkipOnEmpty', \
                         'skipOnEmpty', \
                         'matchAttribute', \
                         'fixRow' ]
    #  //
    # //  For abstract class, public interface methods should cause exception
    #//
    for method in _interfaceMethods : 
        # Curry the arguments of the execption raiser
        expr = "%s = lambda self, x='ViewObjectLayer', "%method + \
               "y='RowInterface', z='%s' "%method + \
               ": raiseAbstractException(x, y, z)"
        exec(expr)
        expr = "%s.__doc__ = '\\n   Abstract definition " % method + \
               "for interface method %s()   \\n'" % method
        exec(expr)

    #  //
    # //  Constructor
    #//
    def __init__(self) : 
        """
        Constructor - Abstract class definition
        Checks that methods declared in the public interface are actually defined.
        """
        for method in self._interfaceMethods : 
            # Check that method is defined
            if method in self.__dict__.keys() : 
                raise ViewObjectLayerException(\
                     "Missing method in interface implementation.", \
                     Method = "__init__", \
                     Module = "ViewObjectLayer", \
                     Interface = "RowInterface", \
                     Class = self.__class__.__name__)


class Row(RowInterface, dict) : 
    """
    Row object for the CompositeTable class. 
    In order to satisfy the one to many relationships and 
    possible multiple foreign key references to the same table,
    the structure is more complicated than a simple one table
    row object.
    """
    def __init__(self, parSchema) : 
        """
        Constructor takes parent table reference as argument.
        An optional list of attributes to ignore when finding 
        foreign keys can be given.
        """
        dict.__init__(self)
        RowInterface.__init__(self)
        # Keep parent table
        self._ParentSchema = parSchema
        # List of subrows 
        self._SubRows = {}
        # List of allowed n-ary subrows
        self._NarySubRows = {}
        # List of rows that may be empty and no excpetion
        # should be thrown
        self._skipOnEmpty = []

        # Build the row structure
        for schemaName in parSchema.names() : 
            if len(parSchema.info(schemaName, 'FKList')) == 0 : 
                self._SubRows[schemaName] = [{}]
            else :  
                self._SubRows[schemaName] = {}
                for fkey in parSchema.info(schemaName, 'FKList') : 
                    self._SubRows[schemaName][fkey] = {}

            self._NarySubRows[schemaName] = \
                    parSchema.info(schemaName, 'Nary')

    def setSkipOnEmpty(self, schemaName = None) : 
        """
        Adds a schema name to the list of schemas that are allowed 
        to have empty data.  Insert algrithms should not throw an 
        exception in that case.  Implements RowInterface::skipOnEmpty().
        """
        if schemaName != None : 
            self._skipOnEmpty.append(schemaName)
        else : 
            self._skipOnEmpty = self._SubRows.keys()
 
    def skipOnEmpty(self, schemaName) : 
        """
        Returns 0 or 1 if the row cannot be skipped (or can) in case 
        of empty row.   Insert algrithms should not throw an 
        exception in that case.  Implements RowInterface::skipOnEmpty().
        """
        if schemaName in self._skipOnEmpty : 
            return 1 
        else : 
            return 0
 
    def parentSchema(self) : 
        """
        Accessor to the SchemaInterface corresponding
        to this row.  Implements RowInterface::parentSchema().
        """
        return self._ParentSchema

    def fixRow(self, fKeyConditions, joinConditions, reverse = 0) : 
        """
        Fixes up a row according to conditions
            (a) Foreign key conditions are fixed in the direction 
                refered --> referer.  This overwrites any previous 
                value in referer.  
            (b) Join conditions are fixed in the direction of  
                non-None value to None value.  If both sides are None, 
                nothing happens.  If both side are not None and unequal,
                an exception is thrown.
        Implements RowInterface::fixRow()
        """
        for item in self.keys() : 
            tb, at, fk = SchemaInterface._ParseKey(item)
            for fkey, fval in fKeyConditions.items() : 
                if reverse == 0 : 
                    if fk == fkey and fval == tb + '.' + at : 
                        self.__setitem__(fkey,self[item],1)
                else : 
                    if fkey == tb + '.' + at : 
                        revattr = fval + '(' + fkey + ')'
                        self.__setitem__(revattr,self[item])
        for cond in joinConditions : 
            lhs, rhs, th = SchemaInterface._ParseCondition(cond)
            lha = self.matchAttribute(lhs)
            rha = self.matchAttribute(rhs)
            if len(lha) == 1 and len(rha) == 1 : 
                if rha[0] in self.keys() and lha[0] in self.keys() : 
                    if self[rha[0]] != self[lha[0]] :
                        raise ViewObjectLayerException(\
                             "Data inconsistency in equi-condition.", \
                             Module = "VieObjectLayer", \
                             Class = "Row", \
                             Method = "fixRow", \
                             LHS = lha[0], \
                             RHS = rha[0])
                elif rha[0] in self.keys() and lha[0] not in self.keys() : 
                    self.__setitem__(lha[0],self[rha[0]],1)
                elif rha[0] not in self.keys() and lha[0] in self.keys() : 
                    self.__setitem__(rha[0],self[lha[0]],1)
            elif len(lha) == 0 or len(rha) == 0 : 
                raise ViewObjectLayerException(\
                      "Missing attribute in join condition.", \
                      Module = "ViewObjectLayer", \
                      Class = "Row", \
                      Method = "fixRow", \
                      LHS = lhs, \
                      RHS = rhs)
            else : 
                # Check if the join condition is natural
                if len(lha) == 1 : 
                    useL = lha[0]
                    useR = None
                    for rhaItem in rha : 
                        tb, at, fk = SchemaInterface._ParseKey(rhaItem)
                        if lha[0] == fk : 
                            useR = rhaItem
                            break
                elif len(rha) == 1 : 
                    useR = rha[0]
                    useL = None
                    for lhaItem in lha : 
                        tb, at, fk = SchemaInterface._ParseKey(lhaItem)
                        if lha[0] == fk : 
                            useL = lhaItem
                            break
                else : 
                    raise ViewObjectLayerException(\
                          "Double ambiguous attribute in join condition.", \
                          Module = "ViewObjectLayer", \
                          Class = "Row", \
                          Method = "fixRow", \
                          LHS = lha.__str__(), \
                          RHS = rha.__str__())

                if useR == None or useL == None : 
                    raise ViewObjectLayerException(\
                          "Single ambiguous attribute in join condition.", \
                          Module = "ViewObjectLayer", \
                          Class = "Row", \
                          Method = "fixRow", \
                          LHS = lha.__str__(), \
                          RHS = rha.__str__())
                else : 
                    if useR in self.keys() and useL in self.keys() : 
                        if self[useR] != self[useL] :
                            raise ViewObjectLayerException(\
                                     "Data inconsistency in equi-condition.", \
                                 Module = "VieObjectLayer", \
                                 Class = "Row", \
                                 Method = "fixRow", \
                                 LHS = useL, \
                                 RHS = useR)
                    elif useR in self.keys() and useL not in self.keys() : 
                        self.__setitem__(useL,self[useR],1)
                    elif useR not in self.keys() and useL in self.keys() : 
                        self.__setitem__(useR,self[useL],1)
          

    def schemaInfo(self, selector) : 
        """
        Returns info on one to one/many relationships and foreign 
        key target lists.  (See SchemaInterface::info().) 
        Implements RowInterface::schemaInfo()
        """
        retval = {}
        retval['Nary'] = self._ParentSchema.info(selector, 'Nary')
        retval['FKList'] = self._ParentSchema.info(selector, 'FKList')
        return retval

    def availableSchemas(self) : 
        """
        Returns a list of available schemas.  Each schema in 
        this list corresponds to exactly one table.  
        Implements RowInterface::availableSchemas().
        """
        return self._SubRows.keys()

    def getData(self, schemaName, fkey = None) : 
        """
        For the given schemaName in RowInterface::availableSchemas(), 
        return data corresponding only to that schema.  Implements
        RowInterface::getData().  
        """
        if fkey != None : 
            return [self._SubRows[schemaName][fkey]]
        else : 
            if isinstance(self._SubRows[schemaName], dict) : 
                return self._SubRows[schemaName].values() 
            else : 
                return self._SubRows[schemaName]

    def newData(self, schemaName) : 
        """
        If schemaInfo(schemaName)['Nary'] > 0, then new data can 
        be added to the schema corresponding to schemaName without 
        creating a new row.  Implements RowInterface::newData().
        """
        if not schemaName in self._NarySubRows.keys() : 
            raise ViewObjectLayerException("Tried to add data to singular schema.", \
                     Module = "ViewObjectLayer", \
                     Method = "newData", \
                     Class = "Row", \
                     SchemaName = schemaName)
        self._SubRows[schemaName].append({})

    def matchAttribute(self, attr) : 
        """
        Convenience method to access SchemaInterface::matchAttribute().
        """
        return self._ParentSchema.matchAttribute(attr)

    def possibleKeys(self) : 
        """
        Convenience method to access a list of all possible attributes  
        of the Row.  Implements RowInterface::possibleKeys().
        """
        return self._ParentSchema.attributes()

    def keys(self) : 
        """
        Overrides dict::keys() to restrict domain to possibleKeys().
        """
        retval = []
        psKeys = self.possibleKeys()
        for i in range(len(psKeys)) :
            addIt = 1
            item = psKeys[i] 
            tb, at, fk = SchemaInterface._ParseKey(item)
            if isinstance(self._SubRows[tb], list) :
                if not self._SubRows[tb][-1].has_key(at) :
                    addIt = 0
            elif isinstance(self._SubRows[tb], dict) :
                if not self._SubRows[tb][fk].has_key(at) :
                    addIt = 0
            if addIt == 1 : 
                retval.append(item)
        return retval

    def __setitem__(self, key, value, allFlag = 0) : 
        """
        Overrides dict::__setitem__() to restrict domain to 
        possibleKeys().  If key points to an n-ary list  
        of subrows, the last row only is set.  If allFlag is 1, 
        then all rows in an n-ary list get set.
        """
        match = self.matchAttribute(key)
        if len(match) == 0 : 
            raise ViewObjectLayerException("Attribute not found.", \
                Method = "__setitem__", \
                Module = "ViewObjectLayer", \
                Class = "Row", \
                Hint = "Did you prepend the table name?", \
                RequestedAttribute = key)
        elif len(match) > 1 : 
            raise ViewObjectLayerException("Too many matching attributes found.", \
                Method = "__setitem__", \
                Module = "ViewObjectLayer", \
                Class = "Row", \
                Hint = "Did you append a foreign key target?", \
                RequestedAttribute = key, \
                MatchingAttributes = match.__str__())
        else : 
            tb, at, fk = SchemaInterface._ParseKey(match[0])
            # If is an n-ary or ordinary row...
            if isinstance(self._SubRows[tb], list) :
                if allFlag == 0 :
                    self._SubRows[tb][-1][at] = value
                else :
                    for subr in self._SubRows[tb] : 
                        subr[at] = value
            elif isinstance(self._SubRows[tb], dict) :
                 self._SubRows[tb][fk][at] = value

    def __delitem__(self, key, allFlag = 0) : 
        """
        Overrides dict::__delitem__() to restrict domain to 
        possibleKeys().  If key points to an n-ary list  
        of subrows, the last row only is deleted.  If allFlag is 1, 
        then all rows in an n-ary list get deleted.
        """
        match = self.matchAttribute(key)
        if len(match) == 0 : 
            raise ViewObjectLayerException("Attribute not found.", \
                Method = "__delitem__", \
                Module = "ViewObjectLayer", \
                Class = "Row", \
                Hint = "Did you prepend the table name?", \
                RequestedAttribute = key)
        elif len(match) > 1 : 
            raise ViewObjectLayerException("Too many matching attributes found.", \
                Method = "__delitem__", \
                Module = "ViewObjectLayer", \
                Class = "Row", \
                Hint = "Did you append a foreign key target?", \
                RequestedAttribute = key, \
                MatchingAttributes = match.__str__())
        else : 
            tb, at, fk = SchemaInterface._ParseKey(match[0])
            # If is an n-ary or ordinary row...
            if isinstance(self._SubRows[tb], list) :
                for subr in self._SubRows[tb] : 
                    del subr[at] 
            elif isinstance(self._SubRows[tb], dict) :
                 del self._SubRows[tb][fk][at]

    def __getitem__(self, key, index = None) : 
        """
        Overrides dict::__getitem__() to restrict domain to 
        possibleKeys().  If key points to an n-ary list  
        of subrows, the last row only is used to get the value.
        Optional index can be given to get data from the ith subrow.
        """
        match = self.matchAttribute(key)
        if len(match) == 0 : 
            raise ViewObjectLayerException("Attribute not found.", \
                Method = "__getitem__", \
                Module = "ViewObjectLayer", \
                Class = "Row", \
                Hint = "Did you prepend the table name?", \
                RequestedAttribute = key)
        elif len(match) > 1 : 
            raise ViewObjectLayerException("Too many matching attributes found.", \
                Method = "__getitem__", \
                Module = "ViewObjectLayer", \
                Class = "Row", \
                Hint = "Did you append a foreign key target?", \
                RequestedAttribute = key, \
                MatchingAttributes = match.__str__())
        else : 
            tb, at, fk = SchemaInterface._ParseKey(match[0])
            if isinstance(self._SubRows[tb], list) :
                if index == None : 
                    return self._SubRows[tb][-1][at]
                else : 
                    return self._SubRows[tb][index][at]
            elif isinstance(self._SubRows[tb], dict) :
                return self._SubRows[tb][fk][at]


class TableInterface: 
    """
    This interface is a light interface to a database table. It contains simple
    commit and rollback methods, a Row class definition and a factory method for
    making new Rows, sequencers for tables with artificial integer primary keys, 
    and simple SQL manipulation methods insert, update, delete, select, and modify.
    """
    #  //
    # //  Public (and semi-public) interface methods
    #//
    _interfaceMethods = ['schema', \
                         'initializeSequencers', \
                         'insert', \
                         'smartInsert', \
                         'select', \
                         'smartSelect', \
                         'update', \
                         'delete', \
                         'modify', \
                         'resetTransaction', \
                         'resetConnection', \
                         'getConnection', \
                         'saveTransaction' ]
    #  //
    # //  For abstract class, public interface methods should cause exception
    #//
    for method in _interfaceMethods : 
        # Curry the arguments of the execption raiser
        expr = "%s = lambda self, x='ViewObjectLayer', "%method + \
               "y='TableInterface', z='%s' "%method + \
               ": raiseAbstractException(x, y, z)"
        exec(expr)
        expr = "%s.__doc__ = '\\n   Abstract definition " % method + \
               "for interface method %s()   \\n'" % method
        exec(expr)

    #  //
    # //  Constructor
    #//
    def __init__(self, schema, cParms = None) : 
        """
        Constructor - Abstract class definition
        Checks that methods declared in the public 
        interface are actually defined.
        """
        for method in self._interfaceMethods : 
            # Check that method is defined
            if method in self.__dict__.keys() : 
                raise ViewObjectLayerException(\
                     "Missing method in interface implementation.", \
                     Method = "__init__", \
                     Module = "ViewObjectLayer", \
                     Interface = "TableInterface", \
                     Class = self.__class__.__name__)


class Table(TableInterface) : 
    """
    This class represents a database view with schema, 
    elementary operations, and a physical connection.  The 
    schema can be either any object that implements the 
    SchemaInterface.  Data is exchanged with the Table in 
    objects that implement the RowInterface.
    """

    class SingleTable(TableInterface) : 
        """
        This class represents a single database table using the 
        same interface as Table.  This is meant for internal use
        only and is just an implementation mechanism.  
        """
        def __init__(self, parTable, subSchema) : 
            """
            Constructor
            """
            self._parTable = parTable  # Reference to parent Table
            self._subSchema = subSchema  # Reference to SchemaInterface
           
            # Check that the schema interface only has one schema in it
            if len(self._subSchema.names()) > 1 : 
                raise ViewObjectLayerException(\
                    "Too many schemas managed by SingleTable object.", \
                    Module = "ViewObjectLayer", \
                    Method = "__init__", \
                    Class = "SingleTable", \
                    ManagedSchema = subSchema.names().__str__(), \
                    Hint = "SingleTable class is for single schemas only.\n" + \
                           "       Try using Table class instead.") 

        def schema(self) : 
            """ 
            This returns a reference to the schema on which this table is defined.
            Implements TableInterface::schema()
            """
            return self._subSchema

        def resetTransaction(self) : 
            """ 
            This clears the transaction with implicit rollback.
            Implements TableInterface::resetTransaction
            """
            self._parTable.resetTransaction()

        def getConnection(self) : 
            """ 
            Returns the connection object.
            Implements TableInterface::getConnections
            """
            return self._parTable.getConnection()

        def saveTransaction(self) : 
            """ 
            This clears the transaction with implicit commit.
            Implements TableInterface::saveTransaction
            """
            self._parTable.saveTransaction()

        def resetConnection(self, **cParms) : 
            """ 
            This closes the existing connection and opens a new one using the 
            given parameters.  If none are given, then existing parameters are used.
            Implements TableInterface::resetConnection.
            """
            self._parTable.resetConnection(**cParms)

        def initializeSequencers(self) : 
            """
            This function initializes sequencers by appealing to the database table. 
            The initial value is set to the max() of the corresponding column.
            Implements TableInterface::initializeSequencers().
            """
            sequencers = self._subSchema.sequences()
            for attribute in sequencers : 
                result = self._parTable._Connection.queryExecuteWithResults(\
                               self._SqlSelect(['max('+attribute+')'], []))
                value = result[1][0]
                if value == None : 
                    value = 0
                self._subSchema.setSeq(attribute, value)

        #  //
        # //  Private helper methods
        #//
        def _SqlInsert(self, row, colList = []) : 
            """
            Builds an SQL insert statement from the given RowInterface.Row object
            and list of columns.
            """
            tableName = self._subSchema.names()[0]
            keyList = []
            fmtList = []
            valList = []
            if colList == [] : 
                colList = self.attributes()
            for attribute in colList : 
                keyList.append(attribute)
                if row[attribute] == None : 
                    fmtList.append("%s")
                    valList.append("NULL") 
                elif self._subSchema.types(attribute) == 'int' : 
                    fmtList.append("%d")
                    valList.append(row[attribute]) 
                elif self._subSchema.types(attribute) == 'string' : 
                    fmtList.append("'%s'")
                    valList.append(row[attribute]) 
            keys = "(" + ','.join(keyList) + ")" 
            values = "(" + ','.join(fmtList) % tuple(valList) + ")"
            query = "insert into " + tableName + " " + keys + " values " + \
                     values + ";"
            return query

        def _SqlUpdate(self, colList, valList, conditions) : 
            """
            Builds an SQL update statement from a list of columns, values, 
            and boolean conditions in conjunctive normal form.
            """     
            tableName = self._subSchema.names()[0]
            keyList = []
            fmtList = {}
            valList = {}
            for i in range(len(colList)) :
                attribute = colList[i] 
                keyList.append(attribute)
                if row[attribute] == None : 
                    fmtList[attribute] = "%s"
                    valList[attribute] = "NULL" 
                elif self._subSchema.types(attribute) == 'int' : 
                    fmtList[attribute] = "%d"
                    valList[attribute] = row[attribute] 
                elif self._subSchema.types(attribute) == 'string' : 
                    fmtList[attribute] = "'%s'"
                    valList[attribute] = row[attribute] 
            keyvalList = map (lambda x : x + " = " + fmtList[x] % valList[x], keyList)
            values = ', '.join(keyvalList) 
            conds = ' and '.join(conditions)
            query = "update " + tableName + " set " + values 
            if len(conds) > 0 : 
                query += " where " + conds + ";"
            else : 
                query += ";"
            return query

        def _SqlSelect(self, colList, conditions) : 
            """
            Builds and SQL select statement from a list of columns and
            boolean conditions in conjunctive normal form.
            """
            tableName = self._subSchema.names()[0]
            query = "select "+','.join(colList) + " from " + tableName 
            if len(conditions) > 0 : 
                query += " where " + " and ".join(conditions) + ";"
            else : 
                query += ";"
            return query

        def _SqlDelete(self, conditions) : 
            """
            Builds an SQL delete statment from boolean conditions in 
            conjunctive normal form.
            """
            tableName = self._subSchema.names()[0]
            query = "delete from " + tableName
            if len(conditions) > 0 : 
                query += " where " + " and ".join(conditions) + ";"
            else : 
                query += ";"
            return query


        def _GetPrimaryKey(self, colList) : 
            """
            This checks is the primary key is contained in the given column
            list.  If yes, then the primary key is returned.  If no, then 
            an empty list is returned.
            """
            # Check if primary key is contained in the colList
            retval = self._subSchema.primaryKey()
            for attribute in self._subSchema.primaryKey() : 
                if attribute not in colList : 
                    retval = []
            return retval

        def _GetUniqueKeys(self, colList) : 
            """
            This finds all unique keys that are covered by the 
            given column list.  These are returned as a list of 
            unique keys, each of which is a list of columns.
            """
            retval = []
            for uk in self._subSchema.uniqueKeys() : 
                subUK = uk
                for attribute in uk : 
                    if attribute not in colList :  
                        subUK = []
                if len(subUK) > 0 :
                    retval.append(subUK)
            return retval

        def _GetKeyForInsert(self, colList) : 
            """
            Utility method to return a key that can be used for 
            insert.  First it checks if the primary key is 
            present, and then it will look for the first unique key.
            It returns a tuple of (retval, key) where retval is 0 
            if no primary key was present and 1.  Return codes: 
              Primary Key present  retval = 1, len(key) > 0
              Unique Key present   retval = 0, len(key) > 0     
              No key found         retval = 0, len(key) = 0
            """
            keyForInsert = self._GetPrimaryKey(colList)
            # retval is set to 1 or 0 if PK is present or not respectively
            retval = len(keyForInsert)
            if len(keyForInsert) == 0 : 
                uniqueKeyList = self._GetUniqueKeys(colList)
                if len(uniqueKeyList) > 0 : 
                    keyForInsert = uniqueKeyList[0]
            return (retval, keyForInsert)

        def _AddPKSequencers(self, row, colList) : 
            """
            Adds a primary key attribute to the column list and row
            and sets a value of None in the row for this attribute 
            if there is a sequencer defined on the primary key attribute.
            """
            for attribute in self._subSchema.primaryKey() :  
                if not attribute in colList and attribute in self._subSchema.sequences() : 
                    colList.append(attribute) 
                    row[attribute] = None
            return row, colList

        def _SetPKSequencers(self, row, colList) : 
            """ 
            Sets value of Primary Key attributes in the row if they have
            sequences defined on them. It will blow away previous value.
            """
            for attribute in self._subSchema.primaryKey() :  
                if attribute in self._subSchema.sequences() and attribute in row.keys() : 
                    row[attribute] = self._subSchema.getNextSeq(attribute)
            return row, colList

        def _BuildIdemConditions(self, row, colList) :
            """
            Utility method given a row and a list of column names
            produces a list of boolean equiconditions using columns
            from the list and values from the row. 
            """
            result = []
            for attribute in colList : 
                if row[attribute] == None :  
                    result.append(attribute + " is %s"%'NULL')
                elif self._subSchema.types(attribute) == 'int' :  
                    result.append(attribute + " = %d"%row[attribute])
                elif self._subSchema.types(attribute) == 'string' :  
                    result.append(attribute + " = '%s'"%row[attribute])
            return result

        def _PrepInsert(self, row, colList) :
            """
            Prepare a row for insertion.  First it checks for an 
            appropriate key to use for insert.  If the primary key 
            is not present, it will check if there are any sequences 
            in the primary key and includes them in the row.
            """
            pkFlag, keyForInsert = self._GetKeyForInsert(colList)
            if len(keyForInsert) == 0 : 
                raise ViewObjectLayerException(\
                    "Could not do insert because no keys were provided.", \
                         Class = "SingleTable", \
                         Method = "_PrepInsert", \
                         Module = "ViewObjectLayer", \
                         ColumnList = colList.__str__())
            if pkFlag != 1 : 
                row, colList = self._AddPKSequencers(row, colList) 
                row, colList = self._SetPKSequencers(row, colList) 
            return row, colList

        def smartInsert(self, row, colList = [], **options) :
            """
            Smart version of insert() method.  This method will check for 
            an existing record in the database matching the given one and 
            return the primary key.  If no match is found, it will do the 
            insert and return the new primary key.
            Implements TableInterface::smartInsert()
            """
            skipOnEmpty = options.get('skipOnEmpty', 0)
            useList = colList
            if len(useList) == 0 : 
                useList = row.keys()
            # Check for a primary key from the column list
            key = self._GetPrimaryKey(useList)
            if len(key) > 0 : 
                # Select record on primary key
                result = self.select(useList, \
                              self._BuildIdemConditions(row, self._subSchema.primaryKey()))
                if len(result) == 1 : 
                    result = result[0]
                    for attribute in row.keys() : 
                        if row[attribute] != result[attribute] : 
                            # Testing for this inconsistency may be too strict.
                            raise ViewObjectLayerException(\
                               "Data inconsistency.", \
                               Module = "ViewObjectLayer", \
                               Class = "SingleTable", \
                               Method = "smartInsert", \
                               Attribute = attribute, \
                               CurrentValue = row[attribute].__str__(), \
                               DatabaseValue = result[attribute].__str__(), \
                               Hint = "Please contact developer.")
                elif len(result) > 1 : 
                        raise ViewObjectLayerException(\
                           "Corrupted data model: primary key returns multiple records.", \
                           Module = "ViewObjectLayer", \
                           Class = "SingleTable", \
                           Method = "smartInsert", \
                           PrimaryKey = key.__str__(), \
                           Hint = "Please contact developer.")
                else : 
                    self.insert(row, useList)
                retval = map (lambda x : row[x], self._subSchema.primaryKey())
            else : 
                # No primary key given
                # Select on a unique key
                keyList = self._GetUniqueKeys(useList) 
                if len(keyList) > 0 : 
                    uKey = keyList[0]
                    result = self.select(self._subSchema.attributes(), \
                              self._BuildIdemConditions(row, uKey))
                    if len(result) == 1 : 
                        result = result[0]
                        # propagate primary key
                        for key in self._subSchema.primaryKey() : 
                            value = result._SubRows[self._subSchema.names()[0]][0][key]
                            row[key] = value
                        # Check consistency
                        for key in self._subSchema.attributes() : 
                            if key in row.keys() : 
                                value = result._SubRows[self._subSchema.names()[0]][0][key]
                                if row[key] != value : 
                                    raise ViewObjectLayerException(\
                                       "Data inconsistency.", \
                                       Module = "ViewObjectLayer", \
                                       Class = "SingleTable", \
                                       Method = "smartInsert", \
                                       Attribute = key, \
                                       CurrentValue = row[key].__str__(), \
                                       DatabaseValue = value.__str__(), \
                                       Hint = "Please contact developer.")
         
                        retval = map (lambda x : result[x], self._subSchema.primaryKey())
                    elif len(result) > 1 : 
                        raise ViewObjectLayerException(\
                           "Corrupted data model: unique key returns multiple records.", \
                           Module = "ViewObjectLayer", \
                           Class = "SingleTable", \
                           Method = "smartInsert", \
                           UsedUniqueKey = uKey.__str__(), \
                           Hint = "Please contact developer.")
                    else : 
                        row, useList = self._PrepInsert(row, useList) 
                        retval = map (lambda x : row[x], self._subSchema.primaryKey())
                        self.insert(row, useList)
                else : 
                    if skipOnEmpty == 0 : 
                        tmpDict = {'Module':"ViewObjectLayer", 'Class':"SingleTable", \
                            'Method':"smartInsert", "UsingColumns":useList.__str__()}
                        raise ViewObjectLayerException(\
                            "Could not do inserts because no keys were provided.", \
                             **tmpDict)
                    else : 
                        retval = None
            return retval

        def insert(self, row, colList = []) :
            """
            insert method, just dumbly tries to do insert.
            """
            # 
            useList = colList
            if len(useList) == 0 : 
                useList = row.keys()
            row, useList = self._PrepInsert(row, useList)
            # Does the PK for this row contain nulls? 
            NOTNULLSTATUS = 1
            for attribute in self._subSchema.primaryKey() :
                if row[attribute] == None : 
                    NOTNULLSTATUS = 0
            if NOTNULLSTATUS != 1 : 
                # Do not insert this row, but do not raise exception.
                print "Null value in PK: Not inserting row", row, colList   
            else : 
                self._parTable._Connection.queryExecute(self._SqlInsert(row, useList))       

        def update(self, colList, valList, conditions) : 
            """
            Performs a database update given a list of columns, values, and conditions 
            """
            self._parTable._Connection.queryExecute(self._SqlUpdate(colList, valList, conditions))

        def select(self, colList, conditions) : 
            """
            Performs a select on the table given the list of columns and conditions, returns
            a list of TableInterface.Row objects for the corresponding table.
            """   
            useList = colList
            if len(useList) == 0 : 
                useList = self.attributes()
            results = self._parTable._Connection.queryExecuteWithResults(self._SqlSelect(useList, conditions))
            objResult = []
            for row in results[1:] : 
                wRow = Row(self._subSchema)
                for i in range(len(useList)) : 
                    wRow[useList[i]] = row[i]
                objResult.append(wRow)
            return objResult
 
        def delete(self, conditions) : 
            """
            Performs a delete with the given conditions.
            """
            self._parTable._Connection.queryExecute(self._SqlDelete(conditions))       
            

    def __init__(self, schema, cParms = {}) : 
        """
        Constructor for the Table class
        """

        self._SubTables = []     # list of SingleTable objects
        self._SubSchemaNameToSubTableMap = {}
        self._Schema = schema

        # Build the sub table list.  This will be in foreign key order.
        # since SchemaInterface::names() returns the schema names in 
        # that order.
        for subSchemaName in self._Schema.names() :
            subSchema = self._Schema.subSchema(subSchemaName) 
            self._SubTables.append(Table.SingleTable(self, subSchema))
            self._SubSchemaNameToSubTableMap[subSchemaName] = self._SubTables[-1]

        # Get the connection
        self._Connection = ConnectionLayer.getConnection(**cParms)        

    def schema(self) : 
        """ 
        This returns a reference to the schema on which this table is defined.
        Implements TableInterface::schema()
        """
        return self._Schema

    def initializeSequencers(self) : 
        """
        This function initializes sequencers by appealing to the database table. 
        The initial value is set to the max() of the corresponding column.
        Implements TableInterface::initializeSequencers().
        """
        # Let the sequencers be handled by the sub tables.
        for table in self._SubTables : 
            table.initializeSequencers()
 
    def getConnection(self) : 
        """ 
        Returns the connection object.
        Implements TableInterface::getConnections
        """
        return self._Connection

    def resetConnection(self, **cParms) : 
        """ 
        This closes the existing connection and opens a new one using the 
        given parameters.  If none are given, then existing parameters are used.
        Implements TableInterface::resetConnection.
        """
        parms = cParms
        if len(parms.keys()) == 0 : 
            parms = self._Connection._GetParms()
        self._Connection.close()
        self._Connection = ConnectionLayer.getConnection(**parms)

    def resetTransaction(self) : 
        """ 
        This clears the transaction with implicit rollback.
        Implements TableInterface::resetTransaction
        """
        self._Connection.rollback()

    def saveTransaction(self) : 
        """ 
        This clears the transaction with implicit commit.
        Implements TableInterface::saveTransaction
        """
        self._Connection.commit()

    def insert(self, row, colList = []) : 
        """
        Does an insert on component tables.  At least 
        one unique key per comp table is required for insert.
        (There should be a test insert function.) 
        """
        useList = colList
        if len(useList) == 0 : 
            useList = row.keys()
        row.fixRow(self._Schema.foreignKeys(5), self._Schema.getConditions())
        for subSchemaName in self._Schema.names() :
            subTable = self._SubSchemaNameToSubTableMap[subSchemaName]
            if subSchemaName in row.availableSchemas() : 
                    for item in row.getData(subSchemaName) : 
                        subTable.insert(item, item.keys())

    def smartInsert(self, row, colList = []) : 
        """
        Does a smartInsert on the multi tables.
        """ 
        useList = colList
        if len(useList) == 0 : 
            useList = row.keys()
        row.fixRow(self._Schema.foreignKeys(5), \
                    self._Schema.getConditions())
        for subSchemaName in self._Schema.names() :
            subTable = self._SubSchemaNameToSubTableMap[subSchemaName]
            if subSchemaName in row.availableSchemas() : 
                for item in row.getData(subSchemaName) : 
                    if len(item.keys()) > 0 : 
                        pk = subTable.smartInsert(item, item.keys(), \
                            skipOnEmpty = row.skipOnEmpty(subSchemaName))
            row.fixRow(self._Schema.foreignKeys(5), self._Schema.getConditions())

    def select(self, colList, conditions) : 
        """
        Does a multi table select.  colList should be in 
        TableName.AttributeName format.  What gets returned
        is more or less just a flat row, and the attributes 
        in the column list and conditions are flattened as
        well.
        """
        useList = colList
        if len(useList) == 0 : 
            useList = self.attributes()
        # Flatten out the fk references in the attributes
        useList2 = []
        for item in useList : 
            tb, at, fk = SchemaInterface._ParseKey(item)
            tryKey = tb + '.' + at
            if not tryKey in useList2 : 
                useList2.append(tryKey)
        useList = useList2

        # Flatten out the fk references in the given conditions
        newConditions = [] 
        for cond in conditions : 
            lhs, rhs, theta = SchemaInterface._ParseCondition(cond)
            if len(self.schema().matchAttribute(lhs)) > 0 : 
                tb, at, fk = SchemaInterface._ParseKey(lhs)
                if tb != None : 
                    nlhs = tb + '.' + at
                else : 
                    nlhs = at
            else : 
                nlhs = lhs
            tb, at, fk = SchemaInterface._ParseKey(rhs)
            if len(self.schema().matchAttribute(rhs)) > 0 : 
                tb, at, fk = SchemaInterface._ParseKey(rhs)
                if tb != None : 
                    nrhs = tb + '.' + at
                else : 
                    nrhs = at
            else : 
                nrhs = rhs
            ncond = nlhs + ' ' + theta + ' ' + nrhs
            newConditions.append(ncond)

        # Do the query
        query = self._SqlSelect(useList, newConditions)
        results = self._Connection.queryExecuteWithResults(query)
        schemaReturned = results[0]
        objResult = []
        for row in results[1:] : 
            wRow = Row(self._Schema)
            for i in range(len(useList)) : 
                # This may be ambiguous now
                wRow[useList[i]] = row[i]
            objResult.append(wRow)
        return objResult

    def smartSelect(self, colList, conditions) : 
        """
        Does a multi-table select that completely retrieves
        the objects.
        """
        pass

    def _SqlSelect(self, colList, conditions) : 
        """
        Writes SQL for select. 
        """
        query = "select"
        query += " " + ', '.join(colList) + " "
        query += "from"
        query += " " + \
                 ", ".join(self._Schema.names())
        allConditions = self._Schema.getConditions() + conditions
        if len(allConditions) > 0 : 
            query += " where "
            query += " AND ".join(allConditions)
        query += ";"
        return query

    def update(self, colList, values, conditions) : 
        """
        Update function not implemented.
        """
        raise ViewObjectLayerException("There are no view update policies." + \
                  "  This function is therefore not implemented.", \
                  Module = "ViewObjectLayer", \
                  Class = "CompositeTable", \
                  Method = "update")

    def delete(self, conditions) : 
        """
        Delete function not implemented.
        """
        raise ViewObjectLayerException("There are no view delete policies." + \
                  "  This function is therefore not implemented.", \
                  Module = "ViewObjectLayer", \
                  Class = "CompositeTable", \
                  Method = "delete")
