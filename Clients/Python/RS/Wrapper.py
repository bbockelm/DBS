from RSSessionEJBBeanService_client import RSSessionEJBBeanServiceLocator
from ZSI.schema import GED

RSSessionEJBWebService_addRegistration = GED("http://session.dm.rs.fnal.gov/", "addRegistration").pyclass
RSSessionEJBWebService_addRegistrationResponse = GED("http://session.dm.rs.fnal.gov/", "addRegistrationResponse").pyclass
RSSessionEJBWebService_queryRegistrationFindAll = GED("http://session.dm.rs.fnal.gov/", "queryRegistrationFindAll").pyclass
RSSessionEJBWebService_queryRegistrationFindAllResponse = GED("http://session.dm.rs.fnal.gov/", "queryRegistrationFindAllResponse").pyclass
RSSessionEJBWebService_queryRegistrationFindByAlias = GED("http://session.dm.rs.fnal.gov/", "queryRegistrationFindByAlias").pyclass
RSSessionEJBWebService_queryRegistrationFindByAliasResponse = GED("http://session.dm.rs.fnal.gov/", "queryRegistrationFindByAliasResponse").pyclass
RSSessionEJBWebService_queryRegistrationFindByURL = GED("http://session.dm.rs.fnal.gov/", "queryRegistrationFindByURL").pyclass
RSSessionEJBWebService_queryRegistrationFindByURLResponse = GED("http://session.dm.rs.fnal.gov/", "queryRegistrationFindByURLResponse").pyclass
RSSessionEJBWebService_removeRegistration = GED("http://session.dm.rs.fnal.gov/", "removeRegistration").pyclass
RSSessionEJBWebService_removeRegistrationResponse = GED("http://session.dm.rs.fnal.gov/", "removeRegistrationResponse").pyclass

class API:
	def __init__(self, url=None):
		self.stub = RSSessionEJBBeanServiceLocator().getRSSessionEJBBeanPort(url=url)
	def queryRegistrationFindAll(self):
		toPass = RSSessionEJBWebService_queryRegistrationFindAll()
		#toPass._dn = dn
		return  self.stub.queryRegistrationFindAll(toPass)._return
	def queryRegistrationFindByAlias(self, alias):
		toPass = RSSessionEJBWebService_queryRegistrationFindByAlias()
		toPass._alias = alias
		return  self.stub.queryRegistrationFindByAlias(toPass)._return


					
