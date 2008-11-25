from RunSeqWSImplService_client import RunSeqWSImplServiceLocator
from ZSI.schema import GED

getCurrRunNumber = GED("http://service.dm.rss.fnal.gov/", "getCurrRunNumber").pyclass
getCurrRunNumberResponse = GED("http://service.dm.rss.fnal.gov/", "getCurrRunNumberResponse").pyclass
getNextRunNumber = GED("http://service.dm.rss.fnal.gov/", "getNextRunNumber").pyclass
getNextRunNumberResponse = GED("http://service.dm.rss.fnal.gov/", "getNextRunNumberResponse").pyclass
createRunSequence = GED("http://service.dm.rss.fnal.gov/", "createRunSequence").pyclass
createRunSequenceResponse = GED("http://service.dm.rss.fnal.gov/", "createRunSequenceResponse").pyclass

class API:
	def __init__(self, url=None):
		self.stub = RunSeqWSImplServiceLocator().getRunSeqWSImplPort(url=url)
		
	def createRunSequence(self, name, startNumber=0, endNumber=0):
		toPass = createRunSequence()
		toPass._arg0 = name
		toPass._arg1 = startNumber
		toPass._arg2 = endNumber
		self.stub.createRunSequence(toPass)
	def getNextRunNumber(self, name):
		toPass = getNextRunNumber()
		toPass._arg0 = name
		return self.stub.getNextRunNumber(toPass)._return
	def getCurrRunNumber(self, name):
		toPass = getCurrRunNumber()
		toPass._arg0 = name
		return self.stub.getCurrRunNumber(toPass)._return
