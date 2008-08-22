from Wrapper import API
def printRequest(req):
	if req._detail not in (None, ""):
		print 'detail   : ' + req._detail
	print 'dstUrl   : ' + req._dstUrl._url
	if req._id not in (None, ""):
		print 'id       : ' + str(req._id)
	if req._notify not in (None, ""):
		print 'notify   : ' + req._notify
	print 'path     : ' + req._path
	if req._person._distinguishedName not in (None, ""):
		print 'dn       : ' + req._person._distinguishedName
	if req._progress not in (None, ""):
		print 'progress : ' + str(req._progress)
	print 'srcUrl   : ' + req._srcUrl._url
	print 'status   : ' + req._status
	print 'force    : ' + req._withForce
	print 'parents  : ' + req._withParents
	print '--------------------------------------------------------------'

try :
	api = API()
	#api = API(url='http://plasma.dhcp.fnal.gov:8080/nvswebapp/nvservice')
	"""
	result = api.getRequestByUser('vijay')
	for i in result:
		printRequest(i)
	result = api.getRequestById(48)
	for i in result:
		printRequest(i)
	"""
	#result = api.getRequestByStatus('Finished')
	result = api.getRequestByStatus('InProgress')
	for i in result:
		printRequest(i)
	print  api.deleteRequest('https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_03_writer/servlet/DBSServlet', 'http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet', '/QCD_800-1000/CMSSW_1_7_4-CSA07-3776/RAW')
	#req = api.addRequest('https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_03_writer/servlet/DBSServlet', 'http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet', '/PhEDEx_MV_Commissioning_2/CMSSW_1_4_6-CSA07-3578a/GEN-SIM')
	req = api.addRequest('https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_03_writer/servlet/DBSServlet', 'http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet', '/QCD_800-1000/CMSSW_1_7_4-CSA07-3776/RAW', notify='sekhri@fnal.gov')
	printRequest(req)
	
except Exception, ex:
	print ex
	#print ex.fault.string
			
