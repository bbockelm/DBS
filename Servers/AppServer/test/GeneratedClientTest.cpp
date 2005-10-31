#include <iostream>
#include "DBSClient.hpp"
#include <exception>
#include "ClientAPIData.hpp"
using namespace std;
Applications_ClientAPIData  getobj_applications_clientapidata() {

       Applications_ClientAPIData  obj_applications_clientapidata;

       obj_applications_clientapidata.applicationversion = (string)"test_value_applicationversion";
       obj_applications_clientapidata.composite = (char)'y';
       obj_applications_clientapidata.applicationconfigurationid = (int)123;
       obj_applications_clientapidata.calibrationversiontag = (string)"test_value_calibrationversiontag";
       obj_applications_clientapidata.parametervalue = (string)"test_value_parametervalue";
       obj_applications_clientapidata.inputcollectiontype = (int)123;
       obj_applications_clientapidata.conditionsversiontag = (string)"test_value_conditionsversiontag";
       obj_applications_clientapidata.parametersetid = (int)123;
       obj_applications_clientapidata.qpsannotation = (string)"test_value_qpsannotation";
       obj_applications_clientapidata.parametername = (string)"test_value_parametername";
       obj_applications_clientapidata.gpgid = (int)123;
       obj_applications_clientapidata.queryableparametersetid = (int)123;
       obj_applications_clientapidata.applicationfamilyid = (int)123;
       obj_applications_clientapidata.collectiontype = (string)"test_value_collectiontype";
       obj_applications_clientapidata.executablename = (string)"test_value_executablename";
       obj_applications_clientapidata.qpsname = (string)"test_value_qpsname";
       obj_applications_clientapidata.applicationid = (int)123;
       obj_applications_clientapidata.parameterbindingid = (int)123;
       obj_applications_clientapidata.applicationfamilyname = (string)"test_value_applicationfamilyname";
       obj_applications_clientapidata.outputcollectiontype = (int)123;
       obj_applications_clientapidata.collectiontypeid = (int)123;
       obj_applications_clientapidata.qpsversion = (string)"test_value_qpsversion";
       obj_applications_clientapidata.externaldatatype = (string)"test_value_externaldatatype";
       obj_applications_clientapidata.applicationfamily = (int)123;

       return obj_applications_clientapidata;
 }

Insertapps_ClientAPIData  getobj_insertapps_clientapidata() {

       Insertapps_ClientAPIData  obj_insertapps_clientapidata;

       obj_insertapps_clientapidata.collectiontype = (string)"test_value_collectiontype";
       obj_insertapps_clientapidata.applicationversion = (string)"test_value_applicationversion";
       obj_insertapps_clientapidata.executablename = (string)"test_value_executablename";
       obj_insertapps_clientapidata.applicationconfigurationid = (int)123;
       obj_insertapps_clientapidata.calibrationversiontag = (string)"test_value_calibrationversiontag";
       obj_insertapps_clientapidata.applicationfamilyname = (string)"test_value_applicationfamilyname";
       obj_insertapps_clientapidata.outputcollectiontype = (int)123;
       obj_insertapps_clientapidata.conditionsversiontag = (string)"test_value_conditionsversiontag";
       obj_insertapps_clientapidata.collectiontypeid = (int)123;
       obj_insertapps_clientapidata.parametersetid = (int)123;
       obj_insertapps_clientapidata.inputcollectiontype = (int)123;
       obj_insertapps_clientapidata.applicationid = (int)123;
       obj_insertapps_clientapidata.applicationfamilyid = (int)123;
       obj_insertapps_clientapidata.applicationfamily = (int)123;

       return obj_insertapps_clientapidata;
 }

Administrative_ClientAPIData  getobj_administrative_clientapidata() {

       Administrative_ClientAPIData  obj_administrative_clientapidata;

       obj_administrative_clientapidata.distinguishedname = (string)"test_value_distinguishedname";
       obj_administrative_clientapidata.roledescription = (string)"test_value_roledescription";
       obj_administrative_clientapidata.name = (string)"test_value_name";
       obj_administrative_clientapidata.roleid = (int)123;
       obj_administrative_clientapidata.personid = (int)123;
       obj_administrative_clientapidata.rolename = (string)"test_value_rolename";
       obj_administrative_clientapidata.contactinfo = (string)"test_value_contactinfo";
       obj_administrative_clientapidata.assignedroleid = (int)123;

       return obj_administrative_clientapidata;
 }

Person_ClientAPIData  getobj_person_clientapidata() {

       Person_ClientAPIData  obj_person_clientapidata;

       obj_person_clientapidata.distinguishedname = (string)"atest_value_distinguishedname";
       //obj_person_clientapidata.personid = (int)123;
       obj_person_clientapidata.contactinfo = (string)"atest_value_contactinfo";
       obj_person_clientapidata.name = (string)"atest_value_name";

       return obj_person_clientapidata;
 }

Role_ClientAPIData  getobj_role_clientapidata() {

       Role_ClientAPIData  obj_role_clientapidata;

       obj_role_clientapidata.rolename = (string)"test_value_rolename";
       obj_role_clientapidata.roledescription = (string)"test_value_roledescription";
       obj_role_clientapidata.roleid = (int)123;

       return obj_role_clientapidata;
 }

Physicsgroup_ClientAPIData  getobj_physicsgroup_clientapidata() {

       Physicsgroup_ClientAPIData  obj_physicsgroup_clientapidata;

       obj_physicsgroup_clientapidata.distinguishedname = (string)"test_value_distinguishedname";
       obj_physicsgroup_clientapidata.name = (string)"test_value_name";
       obj_physicsgroup_clientapidata.physicsgroupname = (string)"test_value_physicsgroupname";
       obj_physicsgroup_clientapidata.personid = (int)123;
       obj_physicsgroup_clientapidata.physicsgroupid = (int)123;
       obj_physicsgroup_clientapidata.contactinfo = (string)"test_value_contactinfo";
       obj_physicsgroup_clientapidata.physicsgroupconvener = (int)123;

       return obj_physicsgroup_clientapidata;
 }

Genparametersets_ClientAPIData  getobj_genparametersets_clientapidata() {

       Genparametersets_ClientAPIData  obj_genparametersets_clientapidata;

       obj_genparametersets_clientapidata.composite = (char)'y';
       obj_genparametersets_clientapidata.qpsname = (string)"test_value_qpsname";
       obj_genparametersets_clientapidata.parametervalue = (string)"test_value_parametervalue";
       obj_genparametersets_clientapidata.qpsannotation = (string)"test_value_qpsannotation";
       obj_genparametersets_clientapidata.parametername = (string)"test_value_parametername";
       obj_genparametersets_clientapidata.gpgid = (int)123;
       obj_genparametersets_clientapidata.queryableparametersetid = (int)123;
       obj_genparametersets_clientapidata.qpsversion = (string)"test_value_qpsversion";
       obj_genparametersets_clientapidata.externaldatatype = (string)"test_value_externaldatatype";
       obj_genparametersets_clientapidata.parameterbindingid = (int)123;

       return obj_genparametersets_clientapidata;
 }

Eventcollections_ClientAPIData  getobj_eventcollections_clientapidata() {

       Eventcollections_ClientAPIData  obj_eventcollections_clientapidata;

       obj_eventcollections_clientapidata.otherqueryablemetadata = (int)123;
       obj_eventcollections_clientapidata.eventcollectionstatusid = (int)123;
       obj_eventcollections_clientapidata.childecid = (int)123;
       obj_eventcollections_clientapidata.parentecid = (int)123;
       obj_eventcollections_clientapidata.filetype = (string)"test_value_filetype";
       obj_eventcollections_clientapidata.filestatusid = (int)123;
       obj_eventcollections_clientapidata.eventcollectionid = (int)123;
       obj_eventcollections_clientapidata.eventcollectionstatus = (int)123;
       obj_eventcollections_clientapidata.processeddatasetid = (int)123;
       obj_eventcollections_clientapidata.filestatus = (int)123;
       obj_eventcollections_clientapidata.pointertoexternalparamsdb = (int)123;
       obj_eventcollections_clientapidata.analysiscollectionstatus = (string)"test_value_analysiscollectionstatus";
       obj_eventcollections_clientapidata.size = (string)"test_value_size";
       obj_eventcollections_clientapidata.numberofevents = (int)123;
       obj_eventcollections_clientapidata.validationstatusid = (int)123;
       obj_eventcollections_clientapidata.filetypeid = (int)123;
       obj_eventcollections_clientapidata.firsteventnumber = (int)123;
       obj_eventcollections_clientapidata.evcollid = (int)123;
       obj_eventcollections_clientapidata.lasteventnumber = (int)123;
       obj_eventcollections_clientapidata.estimatedluminosity = (string)"test_value_estimatedluminosity";
       obj_eventcollections_clientapidata.eventcollectiondataid = (int)123;
       obj_eventcollections_clientapidata.logicalfilename = (string)"test_value_logicalfilename";
       obj_eventcollections_clientapidata.primaryecid = (int)123;
       obj_eventcollections_clientapidata.eventcollectionindex = (int)123;
       obj_eventcollections_clientapidata.evcollfileid = (int)123;
       obj_eventcollections_clientapidata.validationstatus = (string)"test_value_validationstatus";
       obj_eventcollections_clientapidata.primaryeventcollection = (char)'c';
       obj_eventcollections_clientapidata.cobracollectionname = (string)"test_value_cobracollectionname";
       obj_eventcollections_clientapidata.fileid = (int)123;

       return obj_eventcollections_clientapidata;
 }

Evcollview_ClientAPIData  getobj_evcollview_clientapidata() {

       Evcollview_ClientAPIData  obj_evcollview_clientapidata;

       obj_evcollview_clientapidata.numberofevents = (int)123;
       obj_evcollview_clientapidata.eventcollectionid = (int)123;
       obj_evcollview_clientapidata.eventcollectionstatusid = (int)123;
       obj_evcollview_clientapidata.childecid = (int)123;
       obj_evcollview_clientapidata.parentecid = (int)123;
       obj_evcollview_clientapidata.otherqueryablemetadata = (int)123;
       obj_evcollview_clientapidata.lasteventnumber = (int)123;
       obj_evcollview_clientapidata.validationstatusid = (int)123;
       obj_evcollview_clientapidata.eventcollectiondataid = (int)123;
       obj_evcollview_clientapidata.firsteventnumber = (int)123;
       obj_evcollview_clientapidata.processeddatasetid = (int)123;
       obj_evcollview_clientapidata.estimatedluminosity = (string)"test_value_estimatedluminosity";
       obj_evcollview_clientapidata.eventcollectionindex = (int)123;
       obj_evcollview_clientapidata.validationstatus = (string)"test_value_validationstatus";
       obj_evcollview_clientapidata.primaryeventcollection = (char)'y';
       obj_evcollview_clientapidata.eventcollectionstatus = (int)123;
       obj_evcollview_clientapidata.analysiscollectionstatus = (string)"test_value_analysiscollectionstatus";
       obj_evcollview_clientapidata.cobracollectionname = (string)"test_value_cobracollectionname";

       return obj_evcollview_clientapidata;
 }

Fileview_ClientAPIData  getobj_fileview_clientapidata() {

       Fileview_ClientAPIData  obj_fileview_clientapidata;

       obj_fileview_clientapidata.size = (string)"test_value_size";
       obj_fileview_clientapidata.filestatus = (int)123;
       obj_fileview_clientapidata.filetypeid = (int)123;
       obj_fileview_clientapidata.evcollfileid = (int)123;
       obj_fileview_clientapidata.evcollid = (int)123;
       obj_fileview_clientapidata.filetype = (string)"test_value_filetype";
       obj_fileview_clientapidata.filestatusid = (int)123;
       obj_fileview_clientapidata.logicalfilename = (string)"test_value_logicalfilename";
       obj_fileview_clientapidata.fileid = (int)123;

       return obj_fileview_clientapidata;
 }

Eventcollectionswithcomplex_ClientAPIData  getobj_eventcollectionswithcomplex_clientapidata() {

       Eventcollectionswithcomplex_ClientAPIData  obj_eventcollectionswithcomplex_clientapidata;

       obj_eventcollectionswithcomplex_clientapidata.otherqueryablemetadata = (int)123;
       obj_eventcollectionswithcomplex_clientapidata.eventcollectionstatusid = (int)123;
       obj_eventcollectionswithcomplex_clientapidata.childecid = (int)123;
       obj_eventcollectionswithcomplex_clientapidata.parentecid = (int)123;
       obj_eventcollectionswithcomplex_clientapidata.filetype = (string)"test_value_filetype";
       obj_eventcollectionswithcomplex_clientapidata.filestatusid = (int)123;
       obj_eventcollectionswithcomplex_clientapidata.validationstatusid = (int)123;
       obj_eventcollectionswithcomplex_clientapidata.eventcollectionstatus = (int)123;
       obj_eventcollectionswithcomplex_clientapidata.processeddatasetid = (int)123;
       obj_eventcollectionswithcomplex_clientapidata.filestatus = (int)"123";
       obj_eventcollectionswithcomplex_clientapidata.pointertoexternalparamsdb = (int)123;
       obj_eventcollectionswithcomplex_clientapidata.analysiscollectionstatus = (string)"test_value_analysiscollectionstatus";
       obj_eventcollectionswithcomplex_clientapidata.size = (string)"test_value_size";
       obj_eventcollectionswithcomplex_clientapidata.numberofevents = (int)123;
       obj_eventcollectionswithcomplex_clientapidata.eventcollectionid = (int)123;
       obj_eventcollectionswithcomplex_clientapidata.filetypeid = (int)123;
       obj_eventcollectionswithcomplex_clientapidata.evcollid = (int)123;
       obj_eventcollectionswithcomplex_clientapidata.estimatedluminosity = (string)"test_value_estimatedluminosity";
       obj_eventcollectionswithcomplex_clientapidata.lasteventnumber = (int)123;
       obj_eventcollectionswithcomplex_clientapidata.firsteventnumber = (int)123;
       obj_eventcollectionswithcomplex_clientapidata.eventcollectiondataid = (int)123;
       obj_eventcollectionswithcomplex_clientapidata.cobracollectionname = (string)"test_value_cobracollectionname";
       obj_eventcollectionswithcomplex_clientapidata.primaryecid = (int)123;
       obj_eventcollectionswithcomplex_clientapidata.eventcollectionindex = (int)123;
       obj_eventcollectionswithcomplex_clientapidata.evcollfileid = (int)123;
       obj_eventcollectionswithcomplex_clientapidata.validationstatus = (string)"test_value_validationstatus";
       obj_eventcollectionswithcomplex_clientapidata.primaryeventcollection = (char)'c';
       obj_eventcollectionswithcomplex_clientapidata.logicalfilename = (string)"test_value_logicalfilename";
       obj_eventcollectionswithcomplex_clientapidata.fileid = (int)123;

       return obj_eventcollectionswithcomplex_clientapidata;
 }

Eventcollectionsforcrab_ClientAPIData  getobj_eventcollectionsforcrab_clientapidata() {

       Eventcollectionsforcrab_ClientAPIData  obj_eventcollectionsforcrab_clientapidata;

       obj_eventcollectionsforcrab_clientapidata.applicationversion = (string)"test_value_applicationversion";
       obj_eventcollectionsforcrab_clientapidata.validationstatus = (int)123;
       obj_eventcollectionsforcrab_clientapidata.startdate = (string)"test_value_startdate";
       obj_eventcollectionsforcrab_clientapidata.primarydatasetid = (int)123;
       obj_eventcollectionsforcrab_clientapidata.otherqueryablemetadata = (int)123;
       obj_eventcollectionsforcrab_clientapidata.abstractdatasetannotation = (string)"test_value_abstractdatasetannotation";
       obj_eventcollectionsforcrab_clientapidata.eventcollectionid = (int)123;
       obj_eventcollectionsforcrab_clientapidata.calibrationversiontag = (string)"test_value_calibrationversiontag";
       obj_eventcollectionsforcrab_clientapidata.processeddatasetid = (int)123;
       obj_eventcollectionsforcrab_clientapidata.estimatedluminosity = (string)"test_value_estimatedluminosity";
       obj_eventcollectionsforcrab_clientapidata.openforwriting = (char)'c';
       obj_eventcollectionsforcrab_clientapidata.abstractdatasetdescriptionid = (int)123;
       obj_eventcollectionsforcrab_clientapidata.parametersetid = (int)123;
       obj_eventcollectionsforcrab_clientapidata.inputcollectiontype = (int)123;
       obj_eventcollectionsforcrab_clientapidata.streamid = (int)123;
       obj_eventcollectionsforcrab_clientapidata.physicsgroupid = (int)123;
       obj_eventcollectionsforcrab_clientapidata.numberofevents = (int)123;
       obj_eventcollectionsforcrab_clientapidata.datatierid = (int)123;
       obj_eventcollectionsforcrab_clientapidata.collectiontype = (string)"test_value_collectiontype";
       obj_eventcollectionsforcrab_clientapidata.processingpathid = (int)123;
       obj_eventcollectionsforcrab_clientapidata.enddate = (string)"test_value_enddate";
       obj_eventcollectionsforcrab_clientapidata.parentprocessingpathid = (int)123;
       obj_eventcollectionsforcrab_clientapidata.executablename = (string)"test_value_executablename";
       obj_eventcollectionsforcrab_clientapidata.abstractdatasetname = (string)"test_value_abstractdatasetname";
       obj_eventcollectionsforcrab_clientapidata.lasteventnumber = (int)123;
       obj_eventcollectionsforcrab_clientapidata.firsteventnumber = (int)123;
       obj_eventcollectionsforcrab_clientapidata.eventcollectiondataid = (int)123;
       obj_eventcollectionsforcrab_clientapidata.outputcollectiontype = (int)123;
       obj_eventcollectionsforcrab_clientapidata.eventcollectionstatus = (int)123;
       obj_eventcollectionsforcrab_clientapidata.conditionsversiontag = (string)"test_value_conditionsversiontag";
       obj_eventcollectionsforcrab_clientapidata.aggregatedpath = (string)"test_value_aggregatedpath";
       obj_eventcollectionsforcrab_clientapidata.collectiontypeid = (int)123;
       obj_eventcollectionsforcrab_clientapidata.eventcollectionindex = (int)123;
       obj_eventcollectionsforcrab_clientapidata.applicationconfigurationid = (int)123;
       obj_eventcollectionsforcrab_clientapidata.primaryeventcollection = (char)'c';
       obj_eventcollectionsforcrab_clientapidata.cobradatasetname = (string)"test_value_cobradatasetname";
       obj_eventcollectionsforcrab_clientapidata.cobraownername = (string)"test_value_cobraownername";
       obj_eventcollectionsforcrab_clientapidata.applicationid = (int)123;
       obj_eventcollectionsforcrab_clientapidata.cobracollectionname = (string)"test_value_cobracollectionname";
       obj_eventcollectionsforcrab_clientapidata.applicationfamily = (int)123;

       return obj_eventcollectionsforcrab_clientapidata;
 }

Primarydataset_ClientAPIData  getobj_primarydataset_clientapidata() {

       Primarydataset_ClientAPIData  obj_primarydataset_clientapidata;

       obj_primarydataset_clientapidata.mcdecaychain = (string)"test_value_mcdecaychain";
       obj_primarydataset_clientapidata.mcchanneldescription = (string)"test_value_mcchanneldescription";
       obj_primarydataset_clientapidata.streamannotation = (string)"test_value_streamannotation";
       obj_primarydataset_clientapidata.primarydatasetid = (int)123;
       obj_primarydataset_clientapidata.abstractdatasetannotation = (string)"test_value_abstractdatasetannotation";
       obj_primarydataset_clientapidata.triggerdescriptionid = (int)123;
       obj_primarydataset_clientapidata.physicsgroupconvener = (int)123;
       obj_primarydataset_clientapidata.mcproduction = (string)"test_value_mcproduction";
       obj_primarydataset_clientapidata.abstractdatasetdescriptionid = (int)123;
       obj_primarydataset_clientapidata.streamid = (int)123;
       obj_primarydataset_clientapidata.physicsgroupid = (int)123;
       obj_primarydataset_clientapidata.triggerpathdescriptionid = (int)123;
       obj_primarydataset_clientapidata.startdate = (string)"test_value_startdate";
       obj_primarydataset_clientapidata.triggerpathdescription = (string)"test_value_triggerpathdescription";
       obj_primarydataset_clientapidata.enddate = (string)"test_value_enddate";
       obj_primarydataset_clientapidata.mcdescriptionid = (int)123;
       obj_primarydataset_clientapidata.mcchanneldescriptionid = (int)123;
       obj_primarydataset_clientapidata.streamname = (string)"test_value_streamname";
       obj_primarydataset_clientapidata.mcdataset = (char)'y';
       obj_primarydataset_clientapidata.openforwriting = (char)'y';
       obj_primarydataset_clientapidata.cobradatasetname = (string)"test_value_cobradatasetname";
       obj_primarydataset_clientapidata.physicsgroupname = (string)"test_value_physicsgroupname";
       obj_primarydataset_clientapidata.abstractdatasetname = (string)"test_value_abstractdatasetname";

       return obj_primarydataset_clientapidata;
 }

Processingpath_ClientAPIData  getobj_processingpath_clientapidata() {

       Processingpath_ClientAPIData  obj_processingpath_clientapidata;

       obj_processingpath_clientapidata.datatierid = (int)123;
       obj_processingpath_clientapidata.processingpathid = (int)123;
       obj_processingpath_clientapidata.enddate = (string)"test_value_enddate";
       obj_processingpath_clientapidata.parentprocessingpathid = (int)123;
       obj_processingpath_clientapidata.openforwriting = (char)'c';
       obj_processingpath_clientapidata.primarydatasetid = (int)123;
       obj_processingpath_clientapidata.abstractdatasetannotation = (string)"test_value_abstractdatasetannotation";
       obj_processingpath_clientapidata.streamid = (int)123;
       obj_processingpath_clientapidata.aggregatedpath = (string)"test_value_aggregatedpath";
       obj_processingpath_clientapidata.processeddatasetid = (int)123;
       obj_processingpath_clientapidata.startdate = (string)"test_value_startdate";
       obj_processingpath_clientapidata.abstractdatasetdescriptionid = (int)123;
       obj_processingpath_clientapidata.applicationconfigurationid = (int)123;
       obj_processingpath_clientapidata.cobradatasetname = (string)"test_value_cobradatasetname";
       obj_processingpath_clientapidata.physicsgroupid = (int)123;
       obj_processingpath_clientapidata.cobraownername = (string)"test_value_cobraownername";
       obj_processingpath_clientapidata.abstractdatasetname = (string)"test_value_abstractdatasetname";
       obj_processingpath_clientapidata.datatiername = (string)"test_value_datatiername";

       return obj_processingpath_clientapidata;
 }

Analysisdataset_ClientAPIData  getobj_analysisdataset_clientapidata() {

       Analysisdataset_ClientAPIData  obj_analysisdataset_clientapidata;

       obj_analysisdataset_clientapidata.analysisdatasetdataid = (int)123;
       obj_analysisdataset_clientapidata.openforwriting = (char)'c';
       obj_analysisdataset_clientapidata.eventcollectionid = (int)123;
       obj_analysisdataset_clientapidata.blockid = (int)123;
       obj_analysisdataset_clientapidata.analysisdatasetstatus = (int)123;
       obj_analysisdataset_clientapidata.auxilliarypoolcatalog = (string)"test_value_auxilliarypoolcatalog";
       obj_analysisdataset_clientapidata.otherqueryablemetadata = (int)123;
       obj_analysisdataset_clientapidata.analysisdatasetstatusid = (int)123;
       obj_analysisdataset_clientapidata.validationstatus = (int)123;
       obj_analysisdataset_clientapidata.numberofevents = (int)123;
       obj_analysisdataset_clientapidata.analysisdatasetannotation = (string)"test_value_analysisdatasetannotation";
       obj_analysisdataset_clientapidata.estimatedluminosity = (string)"test_value_estimatedluminosity";
       obj_analysisdataset_clientapidata.analysisdatasetid = (int)123;
       obj_analysisdataset_clientapidata.evcollandataid = (int)123;
       obj_analysisdataset_clientapidata.compositeanalysisdataset = (char)'c';
       obj_analysisdataset_clientapidata.cobraaccessorname = (string)"test_value_cobraaccessorname";
       obj_analysisdataset_clientapidata.size = (int)123;
       obj_analysisdataset_clientapidata.analysiscollectionstatus = (string)"test_value_analysiscollectionstatus";
       obj_analysisdataset_clientapidata.analysisdatasettypeid = (int)123;

       return obj_analysisdataset_clientapidata;
 }

Fileview_ClientAPIData*  getobj_fileview_clientapidataP() {

       Fileview_ClientAPIData*  obj_fileview_clientapidata = new Fileview_ClientAPIData();

       obj_fileview_clientapidata->size = (string)"test_value_size";
       obj_fileview_clientapidata->filestatus = (int)123;
       obj_fileview_clientapidata->filetypeid = (int)123;
       obj_fileview_clientapidata->evcollfileid = (int)123;
       obj_fileview_clientapidata->evcollid = (int)123;
       obj_fileview_clientapidata->filetype = (string)"test_value_filetype";
       obj_fileview_clientapidata->filestatusid = (int)123;
       obj_fileview_clientapidata->logicalfilename = (string)"test_value_logicalfilename";
       obj_fileview_clientapidata->fileid = (int)123;

       return obj_fileview_clientapidata;
 }

Genparametersets_ClientAPIData*  getobj_genparametersets_clientapidataP() {

       Genparametersets_ClientAPIData*  obj_genparametersets_clientapidata = new Genparametersets_ClientAPIData();

       obj_genparametersets_clientapidata->composite = (char)'y';
       obj_genparametersets_clientapidata->qpsname = (string)"test_value_qpsname";
       obj_genparametersets_clientapidata->parametervalue = (string)"test_value_parametervalue";
       obj_genparametersets_clientapidata->qpsannotation = (string)"test_value_qpsannotation";
       obj_genparametersets_clientapidata->parametername = (string)"test_value_parametername";
       obj_genparametersets_clientapidata->gpgid = (int)123;
       obj_genparametersets_clientapidata->queryableparametersetid = (int)123;
       obj_genparametersets_clientapidata->qpsversion = (string)"test_value_qpsversion";
       obj_genparametersets_clientapidata->externaldatatype = (string)"test_value_externaldatatype";
       obj_genparametersets_clientapidata->parameterbindingid = (int)123;

       return obj_genparametersets_clientapidata;
 }



int main() {
	DBSClient* dbsclient;
try {
	dbsclient = new DBSClient();
	//Person_ClientAPIData apiData =  getobj_person_clientapidata();
	//cout<<"personid "<<dbsclient->addPerson(apiData)<<endl;
	//Role_ClientAPIData apiData =  getobj_role_clientapidata();
	//cout<<"roleid "<<dbsclient->addRole(apiData)<<endl;
	//Administrative_ClientAPIData apiData =  getobj_administrative_clientapidata();
	//cout<<"assignedroleid "<<dbsclient->assignRole(apiData)<<endl;
	//Physicsgroup_ClientAPIData apiData =  getobj_physicsgroup_clientapidata();
	//cout<<"physiscsgroupid "<<dbsclient->assignPhysicsGroup(apiData)<<endl;
	//Primarydataset_ClientAPIData apiData =  getobj_primarydataset_clientapidata();
	//cout<<"primarydatasetid "<<dbsclient->createPrimaryDataset("",apiData)<<endl;
	/*Insertapps_ClientAPIData  apiData = getobj_insertapps_clientapidata();*/
	//cout<<"applicationconfigurationid "<<dbsclient->insertApps(apiData)<<endl;*/
	while(true) {
	Evcollview_ClientAPIData apiData = getobj_evcollview_clientapidata();
	Genparametersets_ClientAPIData* newGenParam = getobj_genparametersets_clientapidataP();
	Fileview_ClientAPIData* newFile = getobj_fileview_clientapidataP();
	apiData.extraParams.push_back(newGenParam);
	apiData.fileParams.push_back(newFile);
	cout<<"EVCollid "<<dbsclient->insertEventCollections(apiData)<<endl;
	delete newFile;
	delete newGenParam;
	}


	/*dbsclient-><<<<<<<<APICALL>>>>>>>(getobj_applications_clientapidata());
	dbsclient-><<<<<<<<APICALL>>>>>>>(getobj_insertapps_clientapidata());
	dbsclient-><<<<<<<<APICALL>>>>>>>(getobj_genparametersets_clientapidata());
	dbsclient-><<<<<<<<APICALL>>>>>>>(getobj_eventcollections_clientapidata());
	dbsclient-><<<<<<<<APICALL>>>>>>>(getobj_fileview_clientapidata());
	dbsclient-><<<<<<<<APICALL>>>>>>>(getobj_eventcollectionswithcomplex_clientapidata());
	dbsclient-><<<<<<<<APICALL>>>>>>>(getobj_eventcollectionsforcrab_clientapidata());
	dbsclient-><<<<<<<<APICALL>>>>>>>(getobj_processingpath_clientapidata());
	dbsclient-><<<<<<<<APICALL>>>>>>>(getobj_analysisdataset_clientapidata());*/
} catch (exception &ex) {
	cout << ex.what() << endl;
}
delete dbsclient;
}


