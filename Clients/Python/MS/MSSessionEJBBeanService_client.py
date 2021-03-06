##################################################
# file: MSSessionEJBBeanService_client.py
# 
# client stubs generated by "ZSI.generate.wsdl2python.WriteServiceModule"
#     /home/sekhri/zsi/ZSI-2.1-a1/scripts/wsdl2py MSSessionEJB.wsdl
# 
##################################################

from MSSessionEJBBeanService_types import *
import urlparse, types
from ZSI.TCcompound import ComplexType, Struct
from ZSI import client
from ZSI.schema import GED, GTD
import ZSI

# Locator
class MSSessionEJBBeanServiceLocator:
    #MSSessionEJBBeanPort_address = "http://venom.dhcp.fnal.gov:8080/ms-msejb/MSSessionEJB"
    MSSessionEJBBeanPort_address = "http://cmsdbssrv.cern.ch/ms-msejb/MSSessionEJB"
    def getMSSessionEJBBeanPortAddress(self):
        return MSSessionEJBBeanServiceLocator.MSSessionEJBBeanPort_address
    def getMSSessionEJBBeanPort(self, url=None, **kw):
        return MSSessionEJBWSBindingSOAP(url or MSSessionEJBBeanServiceLocator.MSSessionEJBBeanPort_address, **kw)

# Methods
class MSSessionEJBWSBindingSOAP:
    def __init__(self, url, **kw):
        kw.setdefault("readerclass", None)
        kw.setdefault("writerclass", None)
        # no resource properties
        self.binding = client.Binding(url=url, **kw)
        # no ws-addressing

    # op: addRequest
    def addRequest(self, request, **kw):
        if isinstance(request, MSSessionEJBWS_addRequest) is False:
            raise TypeError, "%s incorrect request type" % (request.__class__)
        # no input wsaction
        self.binding.Send(None, None, request, soapaction="", **kw)
        # no output wsaction
        response = self.binding.Receive(MSSessionEJBWS_addRequestResponse.typecode)
        return response

    # op: deleteRequest
    def deleteRequest(self, request, **kw):
        if isinstance(request, MSSessionEJBWS_deleteRequest) is False:
            raise TypeError, "%s incorrect request type" % (request.__class__)
        # no input wsaction
        self.binding.Send(None, None, request, soapaction="", **kw)
        # no output wsaction
        response = self.binding.Receive(MSSessionEJBWS_deleteRequestResponse.typecode)
        return response

    # op: getRequestById
    def getRequestById(self, request, **kw):
        if isinstance(request, MSSessionEJBWS_getRequestById) is False:
            raise TypeError, "%s incorrect request type" % (request.__class__)
        # no input wsaction
        self.binding.Send(None, None, request, soapaction="", **kw)
        # no output wsaction
        response = self.binding.Receive(MSSessionEJBWS_getRequestByIdResponse.typecode)
        return response

    # op: getRequestByStatus
    def getRequestByStatus(self, request, **kw):
        if isinstance(request, MSSessionEJBWS_getRequestByStatus) is False:
            raise TypeError, "%s incorrect request type" % (request.__class__)
        # no input wsaction
        self.binding.Send(None, None, request, soapaction="", **kw)
        # no output wsaction
        response = self.binding.Receive(MSSessionEJBWS_getRequestByStatusResponse.typecode)
        return response

    # op: getRequestByUser
    def getRequestByUser(self, request, **kw):
        if isinstance(request, MSSessionEJBWS_getRequestByUser) is False:
            raise TypeError, "%s incorrect request type" % (request.__class__)
        # no input wsaction
        self.binding.Send(None, None, request, soapaction="", **kw)
        # no output wsaction
        response = self.binding.Receive(MSSessionEJBWS_getRequestByUserResponse.typecode)
        return response

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
