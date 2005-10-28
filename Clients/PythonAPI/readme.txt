1) There are 2 API implementations at the moment: 
   dbsCliApi (based on command-line tools, requires working phedex tools) 
   dbsCgiApi (uses CGI scripts) 

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

4) API classes of interest for users:
   
   DbsFileBlock (dbsFileBlock.py)
   DbsEventCollection (dbsEventCollection.py)
   DbsDataset (dbsDataset.py)

   All of those should be easily extendible, and provide methods
   for retrieving/adding class attributes (e.g., 
   
   DbsFileBlock.getEventCollectionList()
   DbsFileBlock.addEventCollection(eventCollection):
