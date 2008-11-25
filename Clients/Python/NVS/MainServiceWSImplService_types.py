##################################################
# file: MainServiceWSImplService_types.py
#
# schema types generated by "ZSI.generate.wsdl2python.WriteServiceModule"
#    /home/sekhri/zsi/ZSI-2.1-a1/scripts/wsdl2py MainServiceWSImplService.wsdl
#
##################################################

import ZSI
import ZSI.TCcompound
from ZSI.schema import LocalElementDeclaration, ElementDeclaration, TypeDefinition, GTD, GED

##############################
# targetNamespace
# http://service.dm.nvs.fnal.gov/
##############################

class ns1:
    targetNamespace = "http://service.dm.nvs.fnal.gov/"

    class validate_Def(ZSI.TCcompound.ComplexType, TypeDefinition):
        schema = "http://service.dm.nvs.fnal.gov/"
        type = (schema, "validate")
        def __init__(self, pname, ofwhat=(), attributes=None, extend=False, restrict=False, **kw):
            ns = ns1.validate_Def.schema
            TClist = [ZSI.TC.String(pname="arg0", aname="_arg0", minOccurs=0, maxOccurs=1, nillable=False, typed=False, encoded=kw.get("encoded")), ZSI.TC.String(pname="arg1", aname="_arg1", minOccurs=0, maxOccurs=1, nillable=False, typed=False, encoded=kw.get("encoded"))]
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
                    return
            Holder.__name__ = "validate_Holder"
            self.pyclass = Holder

    class validateResponse_Def(ZSI.TCcompound.ComplexType, TypeDefinition):
        schema = "http://service.dm.nvs.fnal.gov/"
        type = (schema, "validateResponse")
        def __init__(self, pname, ofwhat=(), attributes=None, extend=False, restrict=False, **kw):
            ns = ns1.validateResponse_Def.schema
            TClist = [GTD("http://service.dm.nvs.fnal.gov/","nameObject",lazy=False)(pname="return", aname="_return", minOccurs=0, maxOccurs="unbounded", nillable=False, typed=False, encoded=kw.get("encoded"))]
            self.attribute_typecode_dict = attributes or {}
            if extend: TClist += ofwhat
            if restrict: TClist = ofwhat
            ZSI.TCcompound.ComplexType.__init__(self, None, TClist, pname=pname, inorder=0, **kw)
            class Holder:
                typecode = self
                def __init__(self):
                    # pyclass
                    self._return = []
                    return
            Holder.__name__ = "validateResponse_Holder"
            self.pyclass = Holder

    class nameObject_Def(ZSI.TCcompound.ComplexType, TypeDefinition):
        schema = "http://service.dm.nvs.fnal.gov/"
        type = (schema, "nameObject")
        def __init__(self, pname, ofwhat=(), attributes=None, extend=False, restrict=False, **kw):
            ns = ns1.nameObject_Def.schema
            TClist = [ZSI.TC.String(pname="name", aname="_name", minOccurs=0, maxOccurs=1, nillable=False, typed=False, encoded=kw.get("encoded")), ZSI.TCnumbers.FPdouble(pname="similar", aname="_similar", minOccurs=0, maxOccurs=1, nillable=False, typed=False, encoded=kw.get("encoded"))]
            self.attribute_typecode_dict = attributes or {}
            if extend: TClist += ofwhat
            if restrict: TClist = ofwhat
            ZSI.TCcompound.ComplexType.__init__(self, None, TClist, pname=pname, inorder=0, **kw)
            class Holder:
                typecode = self
                def __init__(self):
                    # pyclass
                    self._name = None
                    self._similar = None
                    return
            Holder.__name__ = "nameObject_Holder"
            self.pyclass = Holder

    class ValidateException_Def(ZSI.TCcompound.ComplexType, TypeDefinition):
        schema = "http://service.dm.nvs.fnal.gov/"
        type = (schema, "ValidateException")
        def __init__(self, pname, ofwhat=(), attributes=None, extend=False, restrict=False, **kw):
            ns = ns1.ValidateException_Def.schema
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
            Holder.__name__ = "ValidateException_Holder"
            self.pyclass = Holder

    class ValidateException_Dec(ElementDeclaration):
        literal = "ValidateException"
        schema = "http://service.dm.nvs.fnal.gov/"
        substitutionGroup = None
        def __init__(self, **kw):
            kw["pname"] = ("http://service.dm.nvs.fnal.gov/","ValidateException")
            kw["aname"] = "_ValidateException"
            if ns1.ValidateException_Def not in ns1.ValidateException_Dec.__bases__:
                bases = list(ns1.ValidateException_Dec.__bases__)
                bases.insert(0, ns1.ValidateException_Def)
                ns1.ValidateException_Dec.__bases__ = tuple(bases)

            ns1.ValidateException_Def.__init__(self, **kw)
            if self.pyclass is not None: self.pyclass.__name__ = "ValidateException_Dec_Holder"

    class validate_Dec(ElementDeclaration):
        literal = "validate"
        schema = "http://service.dm.nvs.fnal.gov/"
        substitutionGroup = None
        def __init__(self, **kw):
            kw["pname"] = ("http://service.dm.nvs.fnal.gov/","validate")
            kw["aname"] = "_validate"
            if ns1.validate_Def not in ns1.validate_Dec.__bases__:
                bases = list(ns1.validate_Dec.__bases__)
                bases.insert(0, ns1.validate_Def)
                ns1.validate_Dec.__bases__ = tuple(bases)

            ns1.validate_Def.__init__(self, **kw)
            if self.pyclass is not None: self.pyclass.__name__ = "validate_Dec_Holder"

    class validateResponse_Dec(ElementDeclaration):
        literal = "validateResponse"
        schema = "http://service.dm.nvs.fnal.gov/"
        substitutionGroup = None
        def __init__(self, **kw):
            kw["pname"] = ("http://service.dm.nvs.fnal.gov/","validateResponse")
            kw["aname"] = "_validateResponse"
            if ns1.validateResponse_Def not in ns1.validateResponse_Dec.__bases__:
                bases = list(ns1.validateResponse_Dec.__bases__)
                bases.insert(0, ns1.validateResponse_Def)
                ns1.validateResponse_Dec.__bases__ = tuple(bases)

            ns1.validateResponse_Def.__init__(self, **kw)
            if self.pyclass is not None: self.pyclass.__name__ = "validateResponse_Dec_Holder"

# end class ns1 (tns: http://service.dm.nvs.fnal.gov/)

##############################
# targetNamespace
# http://impl.service.dm.nvs.fnal.gov/
##############################

class ns0:
    targetNamespace = "http://impl.service.dm.nvs.fnal.gov/"

# end class ns0 (tns: http://impl.service.dm.nvs.fnal.gov/)