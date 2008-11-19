from MainServiceWSImplService_client import MainServiceWSImplServiceLocator
from ZSI.schema import GED


validate = GED("http://service.dm.nvs.fnal.gov/", "validate").pyclass
validateResponse = GED("http://service.dm.nvs.fnal.gov/", "validateResponse").pyclass

class API:
	def __init__(self, url=None):
		self.stub = MainServiceWSImplServiceLocator().getMainServiceWSImplPort(url=url)
		
	def validate(self, name, type):
		toPass = validate()
		toPass._arg0 = name
		toPass._arg1 = type
		return  self.stub.validate(toPass)._return
