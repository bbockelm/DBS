
import os, re, string, socket, xml.sax, xml.sax.handler
import base64
from xml.sax.saxutils import escape
from cStringIO import StringIO

from dbsException import DbsException
from dbsApiException import *

import logging
import inspect

from dbsLogger import *

from dbsUtil import *

def dbsApiImplCreateAnalysisDatasetDefinition(self, analysisDatasetDefinition ):

    """
    Creates a new analysis dataset definition from a combinition of following parameters:
          Name, List-of-lumis, list-of-runs, processed-dataset, tier, run, run-range, 
          lumi-range
    Beside the Name, all other parameters are Optional.

    params: 
         analysisDatasetDefinition: DbsAnalysisDatasetDefinition object

    Note:    
    In case the definition already exists an EXCEPTION will be raised by the Server
    This is to avoid the case that User might use an already existing Definition silently 
    as happens for other tables. By raising exception we are telling user that definition 
    was already created by him/someone with blah blah criteria, so he/she can compare what 
    alraedy defined.

    """

    defName = analysisDatasetDefinition.get('Name')
 
    if defName in ("", None):
       raise DbsApiException(args="You must provide a name for AnalysisDatasetDefinition")
       return
    
    funcInfo = inspect.getframeinfo(inspect.currentframe())
    ###logging.log(DBSDEBUG, "Api call invoked %s" % str(funcInfo[2]))

    xmlinput  = "<?xml version='1.0' standalone='yes'?>"
    xmlinput += "<dbs>"
    xmlinput += "<analysis_dataset_definition analysisds_def_name='"+ defName +"'"
    #xmlinput += " status='"+ analysisdataset.get('Status', '') +"'"
    #xmlinput += " physics_group_name='"+ analysisdataset.get('PhysicsGroup', '') +"'"
    xmlinput += " path='"+analysisDatasetDefinition.get('ProcessedDatasetPath', '')+"'"
    xmlinput += " created_by='"+analysisDatasetDefinition.get('CreationDate', '')+"'"
    xmlinput += " creation_date='"+analysisDatasetDefinition.get('CreationDate', '')+"'"
    xmlinput += " user_cut='"+analysisDatasetDefinition.get('UserCut', '')+"'"
    xmlinput += " description='"+analysisDatasetDefinition.get('Description', '')+"'"
    xmlinput += "/>"

    for aLumi in analysisDatasetDefinition.get('LumiList', []):
       xmlinput += " <lumi_section lumi_section_number='"+aLumi+"'/>"

    for lfn in analysisDatasetDefinition.get('FileList', []):
       xmlinput += " <file lfn='"+lfn+"'/>"

    for analysisds in  analysisDatasetDefinition.get('AnalysisDSList', []):
        xmlinput += "<analysis_dataset analysis_dataset_name='"+analysisds+"'/>"

    for algorithm in analysisDatasetDefinition.get('AlgoList',[]):
        xmlinput += "<algorithm app_version='"+algorithm.get('ApplicationVersion', "")+"'"
        xmlinput += " app_family_name='"+algorithm.get('ApplicationFamily', "")+"'"
        xmlinput += " app_executable_name='"+algorithm.get('ExecutableName', "")+"'"
        pset = algorithm.get('ParameterSetID')
        if pset != None:
           xmlinput += " ps_hash='"+pset.get('Hash', "")+"'"
        xmlinput += "/>"

    for run in analysisDatasetDefinition.get('RunsList',[]):
        xmlinput += "<run run_number='"+str(get_run(run))+"'/>"

    for alumiRange in analysisDatasetDefinition.get('LumiRangeList', []):
        xmlinput += "<lumi_section lumi_section_range='"+ alumiRange[0] +','+alumiRange[1]+"'/>"

    for arunRange in analysisDatasetDefinition.get('RunRangeList', []):
        xmlinput += "<run run_range='"+ arunRange[0]+','+arunRange[1]+"'/>"

    xmlinput += "</dbs>"

    ###logging.log(DBSDEBUG, xmlinput)
    #print xmlinput

    if self.verbose():
       print "createAnalysisDatasetDefinition, xmlinput",xmlinput

    data = self._server._call ({ 'api' : 'createAnalysisDatasetDefinition',
                         'xmlinput' : xmlinput }, 'POST')


    #Just return the name of definition if everything went fine.  
    return defName

    ###logging.log(DBSDEBUG, data)


  # ------------------------------------------------------------
  #def remap(self, eventCollections, outEventCollection, dataset):
