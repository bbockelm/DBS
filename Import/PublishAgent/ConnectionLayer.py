"""
This is the Python connection layer for the DBS import 
agent.  This connection layer is not thread safe nor
useful for concurrent accesses to DBS of any kind.  

Typical usage: 

(1) Get a database connection object

    dbConn = ConnectionLayer.getConnection(**cParms) 

where cParms are keywords understood by your database driver.

(2) Use the Connection clas sinterface on dbConn.

    Queries: 
        queryExecute(sql)
        resultSet = queryExecuteWithResults(sql)

    Transactions:  
        commit()/rollback()  OR  begin/end/abortTransaction()

    Connection: 
        open()/close()

(3) The database driver is hardcoded postgresql for the moment. 
      (a) To change drivers, import your driver instead of 
          pgdb "as databaseDriver" below.         
      (b) Change the list of _Properties in the Connection.__init__()
          method to reflect your driver.  The list should also 
          contin the item "alias" for system reasons.
      (c) Your driver must conform to Python DB API 2.0

Gregory Edwin Graham
21-Apr-2005

GEG 25-Apr-2005 : Removed "SQL" style methods from Connection class.

GEG 27-Apr-2005 : Cleaned up the comments and exceptions.
"""

__revision__ = "$Revision: 1.7 $"
__version__ = "$Id: ConnectionLayer.py,v 1.7 2005/05/20 04:47:54 ggraham Exp $"


import pgdb as databaseDriver
import genException
import exceptions
import os

# Module level default connection parameters.
# Try to read from a file someday.
# These defaults are for postgres/pgdb module.
_defaultConnectionParms = {'database':'ggtest', \
                          'user':'ggraham'}
# Module debug flag
_ConnectionLayer_Debug = 1


class JacuzziException(genException.genException) : 
    """
    Exception class for the ConnectionJacuzzi.
    Takes an error message argument and keywords.
    """
    def __init__(self, msgText = None, **kwargs) : 
        genException.genException.__init__(self, msgText, **kwargs)
        self._Name = self.__class__.__name__ 

class ConnectionException(genException.genException) : 
    """
    Exception class for the Connection class.
    Takes an error message argument and keywords.
    """
    def __init__(self, msgText = None, **kwargs) : 
        genException.genException.__init__(self, msgText, **kwargs)
        self._Name = self.__class__.__name__ 

class TransactionException(ConnectionException) : 
    """
    Exception for Transactions within a Connection.
    Takes an error message argument and keywords.
    """
    def __init__(self, msgText = None, **kwargs) : 
        ConnectionException.__init__(self, msgText, **kwargs)
        self._Name = self.__class__.__name__ 

class JacuzziSingleton(exceptions.Exception) : 
    """
    Singleton handler for the ConnectionJacuzzi class.
    Takes an instance argument.
    """
    def __init__(self, instance):
        exceptions.Exception.__init__(self)
        self._instance = instance
    def instance(self):
        """
        Returns the instance contained in the exception.
        """
        return self._instance

def getConnectionJacuzzi(initMax = 10) : 
    """ 
    Module level function to get the Jacuzzi.  The Jacuzzi 
    itself is a singleton. The maximum number of entities 
    in the Jacuzzi is given in the initMax argument.  This
    defaults to 10, and only has an effect on the first call 
    that instantiates the Jacuzzi.  On subsequent calls 
    initMax is ignored.
    """
    try : 
        single = ConnectionJacuzzi(initMax)
    except JacuzziSingleton, jacuzzi : 
        single = jacuzzi.instance()
    return single

def getConnection(**cParms) : 
    """
    Module level function to get a default connection.  The 
    connection parameters are given as keyword arguments.
    """
    if len(cParms.keys()) == 0 : 
        return getConnectionJacuzzi().newConnection(**_defaultConnectionParms)
    else : 
        return getConnectionJacuzzi().newConnection(**cParms)


class ConnectionJacuzzi: 
    """
    Maintains a "pool" of named connections.  Connections are 
    created by instantiating the Connection class at which
    time the connection is added to the pool. Since these
    are not anonymous connections, and they all have known
    propoerties derivable from their names, we call this a 
    Jacuzzi rather than a pool.
    """

    __singleton = None

    def __init__(self, maxInJacuzzi = 10) :
        """
        Constructor takes max size of Jacuzzi as sole argument.
        It defaults to 10.  maxInJacuzzi only has an effect on 
        the first call.  On subsequent calls the maxInJacuzzi 
        parameter has no effect.
        """
        # Check is singleton is set
        if ConnectionJacuzzi.__singleton != None : 
            raise JacuzziSingleton(ConnectionJacuzzi.__singleton)
        # If not, set the class singleton entry        
        ConnectionJacuzzi.__singleton = self

        # Define the propoerties of connections that we want to track
        # These propoerties are for the pgdb module for postgres.
        self._Properties = ['database', 'user', 'host', 'alias'] 
        self._Connections = {}
        self._MaxSize = maxInJacuzzi

    def _NameFromParameters(self, **cParms) : 
        """
        Creates the unique name of the connection from the parameters.
        """
        # Get the properties and defaults
        return '/' + '/'.join(map (lambda x : cParms.get(x, 'null'), \
                                   self._Properties))

    def newConnection(self, **cParms) : 
        """
        Returns an existing connection matching the given connection  
        parameters.  The connection parameters are given as keyword 
        arguments consistent with the properties defined in the constructor
        for the specific database driver.  An optional parameter alias can be 
        given with the keywords to support multiple connections with the 
        same (otherwise) connection parameters. If a connection is not already
        in the Jacuzzi as determined by uniqueness of the connection 
        parameters plus alias, a new connection is created and added to  
        the Jacuzzi.  If there are too many in the Jacuzzi, an exception 
        is raised. 
        """
        # Get the connection name
        cName = self._NameFromParameters(**cParms)
        # Create it if it is not already in the Jacuzzi
        if not self._Connections.has_key(cName) : 
            # Check for room in the Jacuzzi
            if len(self._Connections.keys()) >= self._MaxSize : 
                # No room left.
                newEx = JacuzziException("Maximum Jacuzzi size exceeded. " + \
                       " Close another connection first.", \
                       Module = "ConnectionLayer", \
                       Class = "ConnectionJacuzzi", \
                       Method = "newConnection", \
                       MaxSize = self._MaxSize)
                raise newEx
            # Try to open a connection to the database
            try : 
                # The db will not understand "alias" parameter, so save 
                # it for later.
                tmpAlias = None
                if cParms.has_key('alias') : 
                    tmpAlias = cParms['alias']
                    del cParms['alias']
                # open connection
                tmpConnection = databaseDriver.connect(**cParms) 
                # restore alias to the keywords
                if (tmpAlias) : 
                    cParms['alias'] = tmpAlias
            except exceptions.Exception, ex : 
                # The connection process failed.
                tmpDict = {"Module":"ConnectionLayer", 
                           "Class":"ConnectionJacuzzi", \
                           "Method":"newConnection", \
                           "OriginalException":ex.__str__()}
                tmpDict.update(cParms)
                newEx = JacuzziException("Failed to get connection.", **tmpDict)
                raise newEx            
            # Wrap connection and put it in the pool
            self._Connections[cName] = Connection(tmpConnection, cName) 
        return self._Connections[cName]

    def removeConnection(self, connection) : 
        """
        Removes a connection from the Jacuzzi.  The connection 
        knows how to remove itself. Good night!
        """
        cName = connection.connectionName()
        # raise an exception if the connection is not found.
        if not self._Connections.has_key(cName) : 
            # This could be an error since the given cName could 
            # be wrong and connection resources are involved.
            newEx = JacuzziException("Tried to remove non-existant connection. " + \
                       " Could be a memory leak.", \
                       Module = "ConnectionLayer", \
                       Method = "removeConnection", \
                       Class = "ConnectionJacuzzi", \
                       ConnectionName = cName)
            raise newEx
        # Close the connection if not already done so
        if self._Connections[cName]._IsConnectionOpen() : 
            self._Connections[cName].close()
        # Mark it for deletion
        del self._Connections[cName]
 
class Connection: 
    """
    Interface class to database.  This maintains both the Python DB API 2.0
    cursor and connection internally and provides a simpler public interface
    that has connection and transaction sematics.  The connection is asusmed
    open upon object creation and cannot be reopened once closed.  Many 
    transactions can be executed on the connection while it is open.
    """
    def __init__(self, connection, cName) : 
        """
        Constructor takes a Python DB API 2.0 connection, and the name for
        the connection in the jacuzzi so that we can unlink it later when 
        the connection is closed. 
        """         
        self._Connection = connection
        self._ConnectionName = cName
        # The connection is implicitly considered open and 
        # transaction implicitly begun immediately.
        self._ConnectionOpen = 1
        self._TransactionOpen = 1
        # Internal cursor object
        self._Cursor = self._Connection.cursor()

    #  //
    # // Private methods
    #//
    def _IsConnectionOpen(self) : 
        """
        Flag returns true if connection is open
        """
        return self._ConnectionOpen == 1

    def _InTransaction(self) : 
        """
        Flag returns true if a transaction is open
        """
        return self._TransactionOpen == 1

    def _RecycleCursor(self) : 
        """
        Gets a fresh cursor.  This does nothing to the transaction, and 
        is intended for internal use only.  Using it inside of a transaction 
        results in undefined behavior.
        """
        if not self._IsConnectionOpen() : 
            newEx = ConnectionException("Tried to refresh Cursor with closed connection." + \
                        " Get new connection from the Jacuzzi.", \
                        Module = "ConnectionLayer", \
                        Class = "Connection", \
                        Method = "_RecycleCursor")
            raise newEx
        self._Cursor.close()
        self._Cursor = self._Connection.cursor()        
 
    def _Commit(self) : 
        """
        Commits any pending transactions on the connection.  For internal use only.
        """
        # Throw an exception if connection is marked as closed.
        if not self._IsConnectionOpen() :           
            newEx = ConnectionException("Connection closed while " + \
                        "trying to commit.", \
                        Module = "ConnectionLayer", \
                        Class = "Connection", \
                        Method = "_Commit")
            raise newEx
        self._Connection.commit()

    def _Rollback(self) : 
        """
        Rolls back any pending transactions on the connection. For internal use only.
        """
        # Throw an exception if connection is marked as closed.
        if not self._IsConnectionOpen() :           
            newEx = ConnectionException("Connection closed while " + \
                        "trying to roll back.", \
                        Module = "ConnectionLayer", \
                        Class = "Connection", \
                        Method = "_Rollback")
            raise newEx
        self._Connection.rollback()

    #  //
    # // Public interface
    #//
    def connectionName(self) : 
        """
        Returns the connection name as given in the constructor. 
        This contains the encoded connection parameters.
        """
        return self._ConnectionName

    def open(self) : 
        """
        Opens the connection.  This is already done in the constructor.
        But we forbid reopening of connections since it potentially 
        circumvents the Jacuzzi.
        """
        if self._IsConnectionOpen() : 
            pass
        else : 
            newEx = ConnectionException("Tried to reopen closed connection." + \
                        " Use the Jacuzzi to get new connections.", \
                        Module = "ConnectionLayer", \
                        Class = "Connection", \
                        Method = "open")
            raise newEx

    def close(self) : 
        """
        Close the connection.  It cannot be reopened.  Closing 
        the connection will abort any pending transactions.
        """
        # flush any pending transactions
        if self._InTransaction() : 
            self.abortTransaction()
        # close the connection
        self._Connection.close()
        # reset the flag
        self._ConnectionOpen = 0
        # take us out of the jacuzzi
        jacuzzi = getConnectionJacuzzi()
        jacuzzi.removeConnection(self)         

    def beginTransaction(self, recycle = None) : 
        """
        Provides transaction semantics. Opens a transaction 
        and enables queries.  This is done automatically by the 
        constructor, but you can also use this method to open 
        a new transaction on the connection.  If called when a
        transaction is already open, an implicit rollback is done. 
        If the recycle parameter is given, then a new cursor 
        is obtained.
        """
        if self._InTransaction() : 
            # This should rollback pending transactions
            self._Rollback()
        else : 
            if not self._IsConnectionOpen() :           
                newEx = genConnectionLayerException("Connection closed while " + \
                               "trying to begin transaction.", \
                               Module = "ConnectionLayer", \
                               Class = "Connection", \
                               Method = "beginTransaction")
                raise newEx
            self._TransactionOpen = 1
        # recycle if you are nervous about your Python DB API 
        # implementation
        if recycle : 
            self._RecycleCursor()

    def endTransaction(self) : 
        """
        Provides transaction semantics. Closes a transaction 
        and disables queries.  If the transaction is open, then 
        an implicit commit is done. 
        """
        if not self._InTransaction() : 
            # This should do nothing
            pass
        else : 
            self._Commit()
            self._TransactionOpen = 0


    def abortTransaction(self) : 
        """
        Provides transaction semantics.  Closes a transaction 
        and disables queries.  If the transaction is open, then 
        an implicit rollback is done.
        """
        if not self._InTransaction() : 
            # This should do nothing
            pass
        else : 
            self._Rollback()
            self._TransactionOpen = 0

    def commit(self, recycle = None) : 
        """
        This method does endTransaction() and beginTransaction().   
        It is an alternate to the other xxxTransaction() methods.
        """
        self.endTransaction()
        self.beginTransaction(recycle)

    def rollback(self, recycle = None) : 
        """
        This method does abortTransaction() and beginTransaction().   
        It is an alternate to the other xxxTransaction() methods.
        """
        self.abortTransaction()
        self.beginTransaction(recycle)

    def queryExecute(self, qString) : 
        """
        Executes a single query without fetching results from the cursor.
        """
        if _ConnectionLayer_Debug == 1 : 
            print qString

        # Restrict that transaction and connection are in effect
        if not self._InTransaction() : 
            newEx = TransactionException("Tried to execute query outside of transaction." + \
                  " Use beginTransaction() to start one.", \
                  Module = "ConnectionLayer", \
                  Class = "Connection", \
                  Method = "queryExecute", \
                  Query = qString)
            raise newEx

        if not self._IsConnectionOpen() : 
            newEx = CponnectionException("Tried to execute query with closed connection." + \
                  " Get new connection from the Jacuzzi.", \
                  Module = "ConnectionLayer", \
                  Class = "Connection", \
                  Method = "queryExecute", \
                  Query = qString)
            raise newEx

        self._Cursor.execute(qString)

    def queryExecuteWithResults(self, qString) : 
        """
        Executes a query and fetches the result from the cursor.
        Returns the column description of the result with result  
        rows appended.
        """
        self.queryExecute(qString) 
        return [self._Cursor.description] + self._Cursor.fetchall()        

if __name__ == "__main__" : 

    #  Get a connection Jacuzzi
    j = getConnectionJacuzzi()

    #  Get a connection
    conn = j.newConnection(user = 'ggraham', database = 'ggtest', \
                  host = 'cmssrv18', alias = "blah") 

    # Start with connection, transaction open
    print conn.queryExecuteWithResults("select name from Person where PersonID = 1;")
    conn.queryExecute("update Person set name = 'ggraham' where PersonID = 1;")
    conn.endTransaction()

    # Start a new transaction - Yoinks! Abort!
    conn.beginTransaction()
    conn.queryExecute("update Person set name = 'Yoinks!' where PersonID = 1;")
    conn.abortTransaction()

    # Start a new transaction
    conn.beginTransaction()
    print conn.queryExecuteWithResults('select name from Person where PersonID = 1;')
    conn.close()





