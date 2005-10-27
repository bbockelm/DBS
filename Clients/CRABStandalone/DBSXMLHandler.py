#!/usr/bin/env python2

import xml.sax.handler
 
class Handler(xml.sax.handler.ContentHandler):
  def __init__(self):
    self.fileblock = None
    self.fileblocksList = []
    self.evcoll = {}
    self.totnevts = 0
    self.evcbyfileblock = {}
    self.parentsList = []
    self.parent ={}
    self._Handlers = {
            "FILEBLOCKS" : self._HandleBLOCK,
            "EVCOLL" : self._HandleEVC,
            "PARENT" : self._HandlePARENT,
            }
 
  def startElement(self, name, attrs):
    handler = self._Handlers.get(name, None)
    if handler == None:
            return
    handler(attrs)
    return
 
  def endElement(self, name ):
    if name == "opt":
     return

  def _HandleEVC(self, attrs):
    #print "block "+self.fileblock+" EVColl "+attrs.getValue('EVCEVTS')+" "+attrs.getValue('EVCNAME')
    ## map the event collection evcname->evcevts
    self.evcoll[attrs.getValue('EVCNAME')]=attrs.getValue('EVCEVTS')
    ## group the event collections by fileblock
    self.evcbyfileblock[self.fileblock]=self.evcoll
    ## total number of events
    self.totnevts=self.totnevts+int(attrs.getValue('EVCEVTS'))
    return

  def _HandleBLOCK(self, attrs):
    #print " FILEBLOCK "+attrs.getValue('BLOCKNAME')
    self.evcoll={} # reset the event collection dict in the block
    self.fileblock=attrs.getValue('BLOCKNAME')
    self.fileblocksList.append(attrs.getValue('BLOCKNAME'))
    return

  def _HandlePARENT(self, attrs):
    #print " PARENT "+attrs.getValue('name')
    self.parent[attrs.getValue('PATH')]=attrs.getValue('DATATIER')
    self.parentsList.append(attrs.getValue('name'))
    return


