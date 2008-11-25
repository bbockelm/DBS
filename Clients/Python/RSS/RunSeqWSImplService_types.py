##################################################
# file: RunSeqWSImplService_types.py
#
# schema types generated by "ZSI.generate.wsdl2python.WriteServiceModule"
#    /home/sekhri/zsi/ZSI-2.1-a1/scripts/wsdl2py RSWebService.wsdl
#
##################################################

import ZSI
import ZSI.TCcompound
from ZSI.schema import LocalElementDeclaration, ElementDeclaration, TypeDefinition, GTD, GED

##############################
# targetNamespace
# http://impl.service.dm.rss.fnal.gov/
##############################

class ns0:
    targetNamespace = "http://impl.service.dm.rss.fnal.gov/"

# end class ns0 (tns: http://impl.service.dm.rss.fnal.gov/)

##############################
# targetNamespace
# http://service.dm.rss.fnal.gov/
##############################

class ns1:
    targetNamespace = "http://service.dm.rss.fnal.gov/"

    class getNextRunNumber_Def(ZSI.TCcompound.ComplexType, TypeDefinition):
        schema = "http://service.dm.rss.fnal.gov/"
        type = (schema, "getNextRunNumber")
        def __init__(self, pname, ofwhat=(), attributes=None, extend=False, restrict=False, **kw):
            ns = ns1.getNextRunNumber_Def.schema
            TClist = [ZSI.TC.String(pname="arg0", aname="_arg0", minOccurs=0, maxOccurs=1, nillable=False, typed=False, encoded=kw.get("encoded"))]
            self.attribute_typecode_dict = attributes or {}
            if extend: TClist += ofwhat
            if restrict: TClist = ofwhat
            ZSI.TCcompound.ComplexType.__init__(self, None, TClist, pname=pname, inorder=0, **kw)
            class Holder:
                typecode = self
                def __init__(self):
                    # pyclass
                    self._arg0 = None
                    return
            Holder.__name__ = "getNextRunNumber_Holder"
            self.pyclass = Holder

    class getNextRunNumberResponse_Def(ZSI.TCcompound.ComplexType, TypeDefinition):
        schema = "http://service.dm.rss.fnal.gov/"
        type = (schema, "getNextRunNumberResponse")
        def __init__(self, pname, ofwhat=(), attributes=None, extend=False, restrict=False, **kw):
            ns = ns1.getNextRunNumberResponse_Def.schema
            TClist = [ZSI.TCnumbers.Ilong(pname="return", aname="_return", minOccurs=1, maxOccurs=1, nillable=False, typed=False, encoded=kw.get("encoded"))]
            self.attribute_typecode_dict = attributes or {}
            if extend: TClist += ofwhat
            if restrict: TClist = ofwhat
            ZSI.TCcompound.ComplexType.__init__(self, None, TClist, pname=pname, inorder=0, **kw)
            class Holder:
                typecode = self
                def __init__(self):
                    # pyclass
                    self._return = None
                    return
            Holder.__name__ = "getNextRunNumberResponse_Holder"
            self.pyclass = Holder

    class RunSeqException_Def(ZSI.TCcompound.ComplexType, TypeDefinition):
        schema = "http://service.dm.rss.fnal.gov/"
        type = (schema, "RunSeqException")
        def __init__(self, pname, ofwhat=(), attributes=None, extend=False, restrict=False, **kw):
            ns = ns1.RunSeqException_Def.schema
            TClist = [ZSI.TC.String(pname="message", aname="_message", minOccurs=0, maxOccurs=1, nillable=False, typed=False, encoded=kw.get("encoded"))]
            self.attribute_typecode_dict = attributes or {}
            if extend: TClist += ofwhat
            if restrict: TClist = ofwhat
            ZSI.TCcompound.ComplexType.__init__(self, None, TClist, pname=pname, inorder=0, **kw)
            class Holder:
                typecode = self
                def __init__(self):
                    # pyclass
                    self._message = None
                    return
            Holder.__name__ = "RunSeqException_Holder"
            self.pyclass = Holder

    class createRunSequence_Def(ZSI.TCcompound.ComplexType, TypeDefinition):
        schema = "http://service.dm.rss.fnal.gov/"
        type = (schema, "createRunSequence")
        def __init__(self, pname, ofwhat=(), attributes=None, extend=False, restrict=False, **kw):
            ns = ns1.createRunSequence_Def.schema
            TClist = [ZSI.TC.String(pname="arg0", aname="_arg0", minOccurs=0, maxOccurs=1, nillable=False, typed=False, encoded=kw.get("encoded")), ZSI.TCnumbers.Ilong(pname="arg1", aname="_arg1", minOccurs=1, maxOccurs=1, nillable=False, typed=False, encoded=kw.get("encoded")), ZSI.TCnumbers.Ilong(pname="arg2", aname="_arg2", minOccurs=1, maxOccurs=1, nillable=False, typed=False, encoded=kw.get("encoded"))]
            self.attribute_typecode_dict = attributes or {}
            if extend: TClist += ofwhat
            if restrict: TClist = ofwhat
            ZSI.TCcompound.ComplexType.__init__(self, None, TClist, pname=pname, inorder=0, **kw)
            class Holder:
                typecode = self
                def __init__(self):
                    # pyclass
                    self._arg0 = None
                    self._arg1 = None
                    self._arg2 = None
                    return
            Holder.__name__ = "createRunSequence_Holder"
            self.pyclass = Holder

    class createRunSequenceResponse_Def(ZSI.TCcompound.ComplexType, TypeDefinition):
        schema = "http://service.dm.rss.fnal.gov/"
        type = (schema, "createRunSequenceResponse")
        def __init__(self, pname, ofwhat=(), attributes=None, extend=False, restrict=False, **kw):
            ns = ns1.createRunSequenceResponse_Def.schema
            TClist = []
            self.attribute_typecode_dict = attributes or {}
            if extend: TClist += ofwhat
            if restrict: TClist = ofwhat
            ZSI.TCcompound.ComplexType.__init__(self, None, TClist, pname=pname, inorder=0, **kw)
            class Holder:
                typecode = self
                def __init__(self):
                    # pyclass
                    return
            Holder.__name__ = "createRunSequenceResponse_Holder"
            self.pyclass = Holder

    class DuplicateRunSeqException_Def(ZSI.TCcompound.ComplexType, TypeDefinition):
        schema = "http://service.dm.rss.fnal.gov/"
        type = (schema, "DuplicateRunSeqException")
        def __init__(self, pname, ofwhat=(), attributes=None, extend=False, restrict=False, **kw):
            ns = ns1.DuplicateRunSeqException_Def.schema
            TClist = [ZSI.TC.String(pname="message", aname="_message", minOccurs=0, maxOccurs=1, nillable=False, typed=False, encoded=kw.get("encoded"))]
            self.attribute_typecode_dict = attributes or {}
            if extend: TClist += ofwhat
            if restrict: TClist = ofwhat
            ZSI.TCcompound.ComplexType.__init__(self, None, TClist, pname=pname, inorder=0, **kw)
            class Holder:
                typecode = self
                def __init__(self):
                    # pyclass
                    self._message = None
                    return
            Holder.__name__ = "DuplicateRunSeqException_Holder"
            self.pyclass = Holder

    class getCurrRunNumber_Def(ZSI.TCcompound.ComplexType, TypeDefinition):
        schema = "http://service.dm.rss.fnal.gov/"
        type = (schema, "getCurrRunNumber")
        def __init__(self, pname, ofwhat=(), attributes=None, extend=False, restrict=False, **kw):
            ns = ns1.getCurrRunNumber_Def.schema
            TClist = [ZSI.TC.String(pname="arg0", aname="_arg0", minOccurs=0, maxOccurs=1, nillable=False, typed=False, encoded=kw.get("encoded"))]
            self.attribute_typecode_dict = attributes or {}
            if extend: TClist += ofwhat
            if restrict: TClist = ofwhat
            ZSI.TCcompound.ComplexType.__init__(self, None, TClist, pname=pname, inorder=0, **kw)
            class Holder:
                typecode = self
                def __init__(self):
                    # pyclass
                    self._arg0 = None
                    return
            Holder.__name__ = "getCurrRunNumber_Holder"
            self.pyclass = Holder

    class getCurrRunNumberResponse_Def(ZSI.TCcompound.ComplexType, TypeDefinition):
        schema = "http://service.dm.rss.fnal.gov/"
        type = (schema, "getCurrRunNumberResponse")
        def __init__(self, pname, ofwhat=(), attributes=None, extend=False, restrict=False, **kw):
            ns = ns1.getCurrRunNumberResponse_Def.schema
            TClist = [ZSI.TCnumbers.Ilong(pname="return", aname="_return", minOccurs=1, maxOccurs=1, nillable=False, typed=False, encoded=kw.get("encoded"))]
            self.attribute_typecode_dict = attributes or {}
            if extend: TClist += ofwhat
            if restrict: TClist = ofwhat
            ZSI.TCcompound.ComplexType.__init__(self, None, TClist, pname=pname, inorder=0, **kw)
            class Holder:
                typecode = self
                def __init__(self):
                    # pyclass
                    self._return = None
                    return
            Holder.__name__ = "getCurrRunNumberResponse_Holder"
            self.pyclass = Holder

    class DuplicateRunSeqException_Dec(ElementDeclaration):
        literal = "DuplicateRunSeqException"
        schema = "http://service.dm.rss.fnal.gov/"
        substitutionGroup = None
        def __init__(self, **kw):
            kw["pname"] = ("http://service.dm.rss.fnal.gov/","DuplicateRunSeqException")
            kw["aname"] = "_DuplicateRunSeqException"
            if ns1.DuplicateRunSeqException_Def not in ns1.DuplicateRunSeqException_Dec.__bases__:
                bases = list(ns1.DuplicateRunSeqException_Dec.__bases__)
                bases.insert(0, ns1.DuplicateRunSeqException_Def)
                ns1.DuplicateRunSeqException_Dec.__bases__ = tuple(bases)

            ns1.DuplicateRunSeqException_Def.__init__(self, **kw)
            if self.pyclass is not None: self.pyclass.__name__ = "DuplicateRunSeqException_Dec_Holder"

    class RunSeqException_Dec(ElementDeclaration):
        literal = "RunSeqException"
        schema = "http://service.dm.rss.fnal.gov/"
        substitutionGroup = None
        def __init__(self, **kw):
            kw["pname"] = ("http://service.dm.rss.fnal.gov/","RunSeqException")
            kw["aname"] = "_RunSeqException"
            if ns1.RunSeqException_Def not in ns1.RunSeqException_Dec.__bases__:
                bases = list(ns1.RunSeqException_Dec.__bases__)
                bases.insert(0, ns1.RunSeqException_Def)
                ns1.RunSeqException_Dec.__bases__ = tuple(bases)

            ns1.RunSeqException_Def.__init__(self, **kw)
            if self.pyclass is not None: self.pyclass.__name__ = "RunSeqException_Dec_Holder"

    class createRunSequence_Dec(ElementDeclaration):
        literal = "createRunSequence"
        schema = "http://service.dm.rss.fnal.gov/"
        substitutionGroup = None
        def __init__(self, **kw):
            kw["pname"] = ("http://service.dm.rss.fnal.gov/","createRunSequence")
            kw["aname"] = "_createRunSequence"
            if ns1.createRunSequence_Def not in ns1.createRunSequence_Dec.__bases__:
                bases = list(ns1.createRunSequence_Dec.__bases__)
                bases.insert(0, ns1.createRunSequence_Def)
                ns1.createRunSequence_Dec.__bases__ = tuple(bases)

            ns1.createRunSequence_Def.__init__(self, **kw)
            if self.pyclass is not None: self.pyclass.__name__ = "createRunSequence_Dec_Holder"

    class createRunSequenceResponse_Dec(ElementDeclaration):
        literal = "createRunSequenceResponse"
        schema = "http://service.dm.rss.fnal.gov/"
        substitutionGroup = None
        def __init__(self, **kw):
            kw["pname"] = ("http://service.dm.rss.fnal.gov/","createRunSequenceResponse")
            kw["aname"] = "_createRunSequenceResponse"
            if ns1.createRunSequenceResponse_Def not in ns1.createRunSequenceResponse_Dec.__bases__:
                bases = list(ns1.createRunSequenceResponse_Dec.__bases__)
                bases.insert(0, ns1.createRunSequenceResponse_Def)
                ns1.createRunSequenceResponse_Dec.__bases__ = tuple(bases)

            ns1.createRunSequenceResponse_Def.__init__(self, **kw)
            if self.pyclass is not None: self.pyclass.__name__ = "createRunSequenceResponse_Dec_Holder"

    class getCurrRunNumber_Dec(ElementDeclaration):
        literal = "getCurrRunNumber"
        schema = "http://service.dm.rss.fnal.gov/"
        substitutionGroup = None
        def __init__(self, **kw):
            kw["pname"] = ("http://service.dm.rss.fnal.gov/","getCurrRunNumber")
            kw["aname"] = "_getCurrRunNumber"
            if ns1.getCurrRunNumber_Def not in ns1.getCurrRunNumber_Dec.__bases__:
                bases = list(ns1.getCurrRunNumber_Dec.__bases__)
                bases.insert(0, ns1.getCurrRunNumber_Def)
                ns1.getCurrRunNumber_Dec.__bases__ = tuple(bases)

            ns1.getCurrRunNumber_Def.__init__(self, **kw)
            if self.pyclass is not None: self.pyclass.__name__ = "getCurrRunNumber_Dec_Holder"

    class getCurrRunNumberResponse_Dec(ElementDeclaration):
        literal = "getCurrRunNumberResponse"
        schema = "http://service.dm.rss.fnal.gov/"
        substitutionGroup = None
        def __init__(self, **kw):
            kw["pname"] = ("http://service.dm.rss.fnal.gov/","getCurrRunNumberResponse")
            kw["aname"] = "_getCurrRunNumberResponse"
            if ns1.getCurrRunNumberResponse_Def not in ns1.getCurrRunNumberResponse_Dec.__bases__:
                bases = list(ns1.getCurrRunNumberResponse_Dec.__bases__)
                bases.insert(0, ns1.getCurrRunNumberResponse_Def)
                ns1.getCurrRunNumberResponse_Dec.__bases__ = tuple(bases)

            ns1.getCurrRunNumberResponse_Def.__init__(self, **kw)
            if self.pyclass is not None: self.pyclass.__name__ = "getCurrRunNumberResponse_Dec_Holder"

    class getNextRunNumber_Dec(ElementDeclaration):
        literal = "getNextRunNumber"
        schema = "http://service.dm.rss.fnal.gov/"
        substitutionGroup = None
        def __init__(self, **kw):
            kw["pname"] = ("http://service.dm.rss.fnal.gov/","getNextRunNumber")
            kw["aname"] = "_getNextRunNumber"
            if ns1.getNextRunNumber_Def not in ns1.getNextRunNumber_Dec.__bases__:
                bases = list(ns1.getNextRunNumber_Dec.__bases__)
                bases.insert(0, ns1.getNextRunNumber_Def)
                ns1.getNextRunNumber_Dec.__bases__ = tuple(bases)

            ns1.getNextRunNumber_Def.__init__(self, **kw)
            if self.pyclass is not None: self.pyclass.__name__ = "getNextRunNumber_Dec_Holder"

    class getNextRunNumberResponse_Dec(ElementDeclaration):
        literal = "getNextRunNumberResponse"
        schema = "http://service.dm.rss.fnal.gov/"
        substitutionGroup = None
        def __init__(self, **kw):
            kw["pname"] = ("http://service.dm.rss.fnal.gov/","getNextRunNumberResponse")
            kw["aname"] = "_getNextRunNumberResponse"
            if ns1.getNextRunNumberResponse_Def not in ns1.getNextRunNumberResponse_Dec.__bases__:
                bases = list(ns1.getNextRunNumberResponse_Dec.__bases__)
                bases.insert(0, ns1.getNextRunNumberResponse_Def)
                ns1.getNextRunNumberResponse_Dec.__bases__ = tuple(bases)

            ns1.getNextRunNumberResponse_Def.__init__(self, **kw)
            if self.pyclass is not None: self.pyclass.__name__ = "getNextRunNumberResponse_Dec_Holder"

# end class ns1 (tns: http://service.dm.rss.fnal.gov/)