
import dbsclient
Fileapidata = dbsclient.Fileview_ClientAPIData()
Genapidata = dbsclient.Genparametersets_ClientAPIData()
ECapidata = dbsclient.Evcollview_ClientAPIData()



Fileapidata.size = dbsclient.ASTR("test_value_size");
Fileapidata.filestatus = dbsclient.AINT(123);
Fileapidata.filetypeid = dbsclient.AINT(123);
Fileapidata.evcollfileid = dbsclient.AINT(123);
Fileapidata.evcollid = dbsclient.AINT(123);
Fileapidata.filetype = dbsclient.ASTR("test_value_filetype");
Fileapidata.filestatusid = dbsclient.AINT(123);
Fileapidata.logicalfilename = dbsclient.ASTR("test_value_logicalfilename");
Fileapidata.fileid = dbsclient.AINT(123);


Genapidata.composite = dbsclient.ACHR('y');
Genapidata.qpsname = dbsclient.ASTR("test_value_qpsname");
Genapidata.parametervalue = dbsclient.ASTR("test_value_parametervalue");
Genapidata.qpsannotation = dbsclient.ASTR("test_value_qpsannotation");
Genapidata.parametername = dbsclient.ASTR("test_value_parametername");
Genapidata.gpgid = dbsclient.AINT(123);
Genapidata.queryableparametersetid = dbsclient.AINT(123);
Genapidata.qpsversion = dbsclient.ASTR("test_value_qpsversion");
Genapidata.externaldatatype = dbsclient.ASTR("test_value_externaldatatype");
Genapidata.parameterbindingid = dbsclient.AINT(123);


ECapidata.numberofevents = dbsclient.AINT(123);
ECapidata.eventcollectionid = dbsclient.AINT(123);
ECapidata.eventcollectionstatusid = dbsclient.AINT(123);
ECapidata.childecid = dbsclient.AINT(123);
ECapidata.parentecid = dbsclient.AINT(123);
ECapidata.otherqueryablemetadata = dbsclient.AINT(123);
ECapidata.lasteventnumber = dbsclient.AINT(123);
ECapidata.validationstatusid = dbsclient.AINT(123);
ECapidata.eventcollectiondataid = dbsclient.AINT(123);
ECapidata.firsteventnumber = dbsclient.AINT(123);
ECapidata.processeddatasetid = dbsclient.AINT(123);
ECapidata.estimatedluminosity = dbsclient.ASTR("test_value_estimatedluminosity");
ECapidata.eventcollectionindex = dbsclient.AINT(123);
ECapidata.validationstatus = dbsclient.ASTR("test_value_validationstatus");
ECapidata.primaryeventcollection = dbsclient.ACHR('y');
ECapidata.eventcollectionstatus = dbsclient.AINT(123);
ECapidata.analysiscollectionstatus = dbsclient.ASTR("test_value_analysiscollectionstatus");
ECapidata.cobracollectionname = dbsclient.ASTR("test_value_cobracollectionname");


gen = dbsclient.Genparametersets()
gen.push_back(Genapidata)

fil = dbsclient.Fileview()
fil.push_back(Fileapidata)


ECapidata.extraParams = gen
ECapidata.fileParams = fil

client = dbsclient.DBSClient()

client.insertEventCollections(ECapidata)

#s = dbsclient.DBSClient.str("mydataset")
