from NVWebServiceService_client import NVWebServiceServiceLocator
from ZSI.schema import GED

NVWebService_validateWS = GED("http://service.web.gov.fnal/", "validateWS").pyclass


class API:
	def __init__(self, url=None):
		self.stub = NVWebServiceServiceLocator().getNVWebServicePort(url=url)
		
	def validate(self, name, type):
		toPass = NVWebService_validateWS()
		toPass._name = name
		toPass._type = type
		return  self.stub.validateWS(toPass)._return
