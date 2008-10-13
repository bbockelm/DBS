from MSSessionEJBBeanService_client import MSSessionEJBBeanServiceLocator
from ZSI.schema import GED

MSSessionEJBWS_addRequest = GED("http://session.dm.ms.fnal.gov/", "addRequest").pyclass
MSSessionEJBWS_addRequestResponse = GED("http://session.dm.ms.fnal.gov/", "addRequestResponse").pyclass
MSSessionEJBWS_deleteRequest = GED("http://session.dm.ms.fnal.gov/", "deleteRequest").pyclass
MSSessionEJBWS_deleteRequestResponse = GED("http://session.dm.ms.fnal.gov/", "deleteRequestResponse").pyclass
MSSessionEJBWS_getRequestById = GED("http://session.dm.ms.fnal.gov/", "getRequestById").pyclass
MSSessionEJBWS_getRequestByIdResponse = GED("http://session.dm.ms.fnal.gov/", "getRequestByIdResponse").pyclass
MSSessionEJBWS_getRequestByStatus = GED("http://session.dm.ms.fnal.gov/", "getRequestByStatus").pyclass
MSSessionEJBWS_getRequestByStatusResponse = GED("http://session.dm.ms.fnal.gov/", "getRequestByStatusResponse").pyclass
MSSessionEJBWS_getRequestByUser = GED("http://session.dm.ms.fnal.gov/", "getRequestByUser").pyclass
MSSessionEJBWS_getRequestByUserResponse = GED("http://session.dm.ms.fnal.gov/", "getRequestByUserResponse").pyclass

class API:
	def __init__(self, url=None):
		self.stub = MSSessionEJBBeanServiceLocator().getMSSessionEJBBeanPort(url=url)
	def getRequestByUser(self, dn):
		toPass = MSSessionEJBWS_getRequestByUser()
		toPass._dn = dn
		return  self.stub.getRequestByUser(toPass)._return

	def getRequestById(self, id):
		toPass = MSSessionEJBWS_getRequestById()
		toPass._id = id
		return  self.stub.getRequestById(toPass)._return

	def getRequestByStatus(self, status):
		toPass = MSSessionEJBWS_getRequestByStatus()
		toPass._status = status
		return  self.stub.getRequestByStatus(toPass)._return
	
	def deleteRequest(self, srcUrl, dstUrl, path):
		toPass = MSSessionEJBWS_deleteRequest()
		toPass._srcUrl = srcUrl
		toPass._dstUrl = dstUrl
		toPass._path = path
		return  self.stub.deleteRequest(toPass)
	
	def addRequest(self, srcUrl, dstUrl, path, dn = 'pyclient', force = 'y', parents = 'y', notify = None):
		toPass = MSSessionEJBWS_addRequest()
		toPass._srcUrl = srcUrl
		toPass._dstUrl = dstUrl
		toPass._path = path
		toPass._dn = dn
		toPass._withParents = parents
		toPass._withForce = force
		toPass._notify = notify
		return  self.stub.addRequest(toPass)._return



					
