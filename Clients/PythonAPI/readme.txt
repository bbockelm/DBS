1) Phedex tools have to be working for CLI-based API.

2) Basic python API usage is something like

  import dbsCliApi
  api=dbsCliApi.DbsCliApi(
    phedexDir=<phedex directory name>,
    phedexDbSectionString=<db section>,
    phedexDbConnectFile=<db connect file>)
  fileBlockList = api.getDatasetContents(<datasetPath>)
  parentList = api.getDatasetProvenance(<datasetPath>, <dataTierList>)

3) Examples of usage are in dbsCliApi.py.
