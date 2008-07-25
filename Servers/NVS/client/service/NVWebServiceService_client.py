##################################################
# file: NVWebServiceService_client.py
# 
# client stubs generated by "ZSI.generate.wsdl2python.WriteServiceModule"
#     ./wsdl2py nvservice.wsdl
# 
##################################################

from NVWebServiceService_types import *
import urlparse, types
from ZSI.TCcompound import ComplexType, Struct
from ZSI import client
from ZSI.schema import GED, GTD
import ZSI

# Locator
class NVWebServiceServiceLocator:
    NVWebServicePort_address = "http://128.142.202.42:8282/nvswebapp/nvservice"
    def getNVWebServicePortAddress(self):
        return NVWebServiceServiceLocator.NVWebServicePort_address
    def getNVWebServicePort(self, url=None, **kw):
        return NVWebServiceBindingSOAP(url or NVWebServiceServiceLocator.NVWebServicePort_address, **kw)

# Methods
class NVWebServiceBindingSOAP:
    def __init__(self, url, **kw):
        kw.setdefault("readerclass", None)
        kw.setdefault("writerclass", None)
        # no resource properties
        self.binding = client.Binding(url=url, **kw)
        # no ws-addressing

    # op: validateWS
    def validateWS(self, request, **kw):
        if isinstance(request, NVWebService_validateWS) is False:
            raise TypeError, "%s incorrect request type" % (request.__class__)
        # no input wsaction
        self.binding.Send(None, None, request, soapaction="", **kw)
        # no output wsaction
        response = self.binding.Receive(NVWebService_validateWSResponse.typecode)
        return response

NVWebService_validateWS = GED("http://service.web.gov.fnal/", "validateWS").pyclass
NVWebService_validateWSResponse = GED("http://service.web.gov.fnal/", "validateWSResponse").pyclass

	