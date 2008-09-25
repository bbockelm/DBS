from Wrapper import API

def printReg(reg):
	print "_____________________________________________________________________"
	print "URL :%s" %reg._url
	print "Alias :%s" %reg._alias
	print "CreationDate :%s" %reg._creationDate
	print "LastModificationDate :%s" %reg._lastModificationDate
	print "Critical :%s" %reg._critical
	print "DBName :%s" %reg._dbName
	print "DBPort :%s" %reg._dbPort
	print "ID :%s" %reg._id
	print "NodeName :%s" %reg._nodeName
	print "Person :%s" %reg._person._distinguishedName
	print "PhysicalLocation :%s" %reg._physicalLocation
	print "SchemaVersion :%s" %reg._schemaVersion
	print "ServerVersion :%s" %reg._serverVersion
	print "_____________________________________________________________________"
	
try :
	api = API()
	#result = api.queryRegistrationFindAll()
	#for i in result:
	#	printReg(i)
	
	result = api.queryRegistrationFindByAlias("Prod_Global")
	for i in result:
		printReg(i)
except Exception, ex:
	print "Exception "
	print ex
	print ex.fault.string
			
