#!/usr/bin/env python
#
# Revision: 1.3 $"
# Id: DBSXMLParser.java,v 1.3 2006/10/26 18:26:04 afaq Exp $"
#
#
import sys
from DBSAPI.dbsApi import DbsApi
from DBSAPI.dbsException import *
from DBSAPI.dbsApiException import *
from DBSAPI.dbsRun import DbsRun
from DBSAPI.dbsOptions import DbsOptionParser

optManager  = DbsOptionParser()
(opts,args) = optManager.getOpt()
api = DbsApi(opts.__dict__)

try:

    api.insertTier ('HCAL')
    api.insertTier ('GEN-SIM')
    api.insertTier ('GEN-SIM-DIGI')
    api.insertTier ('GEN-SIM-RAW')
    api.insertTier ('GEN-SIM-DIGI-RAW')
    api.insertTier ('GEN-SIM-DIGI-HLTDEBUG')
    api.insertTier ('GEN-SIM-RAW-HLTDEBUG')
    api.insertTier ('GEN-SIM-DIGI-RAW-HLTDEBUG')
    api.insertTier ('GEN-SIM-RECO')
    api.insertTier ('GEN-SIM-RAW-RECO')
    api.insertTier ('GEN-SIM-DIGI-RAW-RECO')
    api.insertTier ('GEN-SIM-DIGI-HLTDEBUG-RECO')
    api.insertTier ('GEN-SIM-RAW-HLTDEBUG-RECO')
    api.insertTier ('GEN-SIM-DIGI-RAW-HLTDEBUG-RECO')

except DbsApiException, ex:
  print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  if ex.getErrorCode() not in (None, ""):
    print "DBS Exception Error Code: ", ex.getErrorCode()

print "Done"

