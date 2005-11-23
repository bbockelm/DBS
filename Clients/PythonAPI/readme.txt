1) There are 3 API implementations at the moment: 
   dbsCliApi (based on command-line tools, requires working phedex tools) 
   dbsCgiApi (uses CGI scripts) 
   dbsWsApi  (uses web services)

2) Basic CLI API usage:

  import dbsCliApi
  api=dbsCliApi.DbsCliApi(
    phedexDir=<phedex directory name>,
    phedexDbSectionString=<db section>,
    phedexDbConnectFile=<db connect file>)
  fileBlockList = api.getDatasetContents(<datasetPath>)
  parentList = api.getDatasetProvenance(<datasetPath>, <dataTierList>)

  Actual code examples are in dbsCliApi.py.

3) Basic CGI API usage:

  import dbsCliApi
  datasetPath = "bt03_B0sJPsiX/Hit/bt_Hit245_2_g133"
  api = dbsCgiApi.DbsCgiApi(cgiUrl="http://cern.ch/cms-dbs/cgi-bin")
  fileBlockList = api.getDatasetContents(datasetPath)
  dataTierList = [ "Hit", "Digi" ]
  parentList = api.getDatasetProvenance(datasetPath, dataTierList)

  Actual code examples are in dbsCgiApi.py.

4) Basic Web Services API usage:

  import dbsWsApi
  datasetPath = "bt03_B0sJPsiX/Hit/bt_Hit245_2_g133"
  api = dbsWsApi.DbsWsApi(wsdlUrl="./DbsDatasetService.wsdl.xml")
  fileBlockList = api.getDatasetContents(datasetPath)
  dataTierList = [ "Hit", "Digi" ]
  parentList = api.getDatasetProvenance(datasetPath, dataTierList)

  Actual code examples are in dbsWsApi.py. In general, the wsdl argument
  is actual url pointing to the wsdl file.

5) API classes of interest for users:
   
   DbsFileBlock (dbsFileBlock.py)
   DbsEventCollection (dbsEventCollection.py)
   DbsDataset (dbsDataset.py)

   All of those should be easily extendible, and provide methods
   for retrieving/adding class attributes (e.g., 
   
   DbsFileBlock.getEventCollectionList()
   DbsFileBlock.addEventCollection(eventCollection):

5) Web Service Notes

   - example web service container configuration file is 
     dbs_web_service_config.py
   - wsdl used to describe service is  DbsDatasetService.wsdl.xml; this
       file is the only thing one needs to describe a web service; since
       python processes wsdl at run time, no stubs/skeletons are needed
       for deployment, and one only needs to have the wsdl file accessible
       via http
   - in order to run the service container or use dbsWsApi one has to
       source setups.(c)sh file; this sets up python path properly for SOAPpy
   - to start web service container: 
       python dbsWebServices.py --config-file=dbs_web_services_config
   - to test web service with dbs WS API:
       python dbsWsApi.py 
