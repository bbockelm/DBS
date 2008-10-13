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
	#result = api.getRequestByStatus('InProgress')
	result = api.getRequestByStatus('Queued')
	for i in result:
		printRequest(i)
	#print  api.deleteRequest('https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_03_writer/servlet/DBSServlet', 'http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet', '/QCD_800-1000/CMSSW_1_7_4-CSA07-3776/RAW')
	#print  api.deleteRequest('https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_03_writer/servlet/DBSServlet', 'http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet', '/PhEDEx_MV_Commissioning_2/CMSSW_1_4_6-CSA07-3578a/GEN-SIM')
	#print  api.deleteRequest('https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_10_writer/servlet/DBSServlet', 'http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet', '/QCDpt470/Summer08_IDEAL_V9_v1-unmerged/GEN-SIM-RAW')
	#req = api.addRequest('https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_03_writer/servlet/DBSServlet', 'http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet', '/PhEDEx_MV_Commissioning_2/CMSSW_1_4_6-CSA07-3578a/GEN-SIM', notify='sekhri@fnal.gov')
	#req = api.addRequest('https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_03_writer/servlet/DBSServlet', 'http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet', '/QCD_800-1000/CMSSW_1_7_4-CSA07-3776/RAW', notify='sekhri@fnal.gov')
	#printRequest(req)
	#req = api.addRequest('https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_10_writer/servlet/DBSServlet', 'http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet', '/QCDpt470/Summer08_IDEAL_V9_v1-unmerged/GEN-SIM-RAW', notify='sekhri@fnal.gov')
	#printRequest(req)
	#"""
	print  api.deleteRequest('https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_10_writer/servlet/DBSServlet', 'http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet', '/DiPion_E1to10_Eta25/Summer08_IDEAL_V9_v1/GEN-SIM-RECO')
	print  api.deleteRequest('https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_10_writer/servlet/DBSServlet', 'http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet', '/DiPion_E10to50_Eta25/Summer08_IDEAL_V9_v1-unmerged/GEN-SIM-RECO')
	
	print  api.deleteRequest('https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_10_writer/servlet/DBSServlet', 'http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet', '/QCDDiJetPt0to15/Summer08_IDEAL_V9_v1-unmerged/GEN-SIM-RAW')
	print  api.deleteRequest('https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_10_writer/servlet/DBSServlet', 'http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet', '/QCDDiJetPt230to300/Summer08_IDEAL_V9_v1/GEN-SIM-RAW')
	print  api.deleteRequest('https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_10_writer/servlet/DBSServlet', 'http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet', '/QCDDiJetPt30to50/Summer08_IDEAL_V9_v1-unmerged/GEN-SIM-RECO')
	print  api.deleteRequest('https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_10_writer/servlet/DBSServlet', 'http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet', '/datasetTest2_PD_2/BUNKACQUISITIONERA-v1/RAW')
	print  api.deleteRequest('https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_10_writer/servlet/DBSServlet', 'http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet', '/BBbar50to80/CMSSW_1_2_0-LowLumiPU-1166809258/GEN-SIM-DIGI-RECO')
	print  api.deleteRequest('https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_10_writer/servlet/DBSServlet', 'http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet', '/BJets_Pt_50_120/CMSSW_1_8_0_pre8-RelVal-1202408126/RECO')
	print  api.deleteRequest('https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_10_writer/servlet/DBSServlet', 'http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet', '/BJets_Pt_50_120/CMSSW_1_8_0_pre8-RelVal-1202408126-HLT-SpecialTracking/GEN-SIM-DIGI-RECO')
	print  api.deleteRequest('https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_10_writer/servlet/DBSServlet', 'http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet', '/QCDDiJetPt15to20/Summer08_IDEAL_V9_v1-unmerged/GEN-SIM-RECO')
	#"""
	req = api.addRequest('https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_10_writer/servlet/DBSServlet', 'http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet', '/DiPion_E1to10_Eta25/Summer08_IDEAL_V9_v1/GEN-SIM-RECO', notify='sekhri@fnal.gov')
	#"""
	req = api.addRequest('https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_10_writer/servlet/DBSServlet', 'http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet', '/DiPion_E10to50_Eta25/Summer08_IDEAL_V9_v1-unmerged/GEN-SIM-RECO', notify='sekhri@fnal.gov')
	req = api.addRequest('https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_10_writer/servlet/DBSServlet', 'http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet', '/QCDDiJetPt0to15/Summer08_IDEAL_V9_v1-unmerged/GEN-SIM-RAW', notify='sekhri@fnal.gov')
	req = api.addRequest('https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_10_writer/servlet/DBSServlet', 'http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet', '/QCDDiJetPt230to300/Summer08_IDEAL_V9_v1/GEN-SIM-RAW', notify='sekhri@fnal.gov')
	req = api.addRequest('https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_10_writer/servlet/DBSServlet', 'http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet', '/QCDDiJetPt30to50/Summer08_IDEAL_V9_v1-unmerged/GEN-SIM-RECO', notify='sekhri@fnal.gov')
	req = api.addRequest('https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_10_writer/servlet/DBSServlet', 'http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet', '/datasetTest2_PD_2/BUNKACQUISITIONERA-v1/RAW', notify='sekhri@fnal.gov')
	req = api.addRequest('https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_10_writer/servlet/DBSServlet', 'http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet', '/BBbar50to80/CMSSW_1_2_0-LowLumiPU-1166809258/GEN-SIM-DIGI-RECO', notify='sekhri@fnal.gov')
	req = api.addRequest('https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_10_writer/servlet/DBSServlet', 'http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet', '/BJets_Pt_50_120/CMSSW_1_8_0_pre8-RelVal-1202408126/RECO', notify='sekhri@fnal.gov')
	req = api.addRequest('https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_10_writer/servlet/DBSServlet', 'http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet', '/BJets_Pt_50_120/CMSSW_1_8_0_pre8-RelVal-1202408126-HLT-SpecialTracking/GEN-SIM-DIGI-RECO', notify='sekhri@fnal.gov')
	req = api.addRequest('https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_10_writer/servlet/DBSServlet', 'http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet', '/QCDDiJetPt15to20/Summer08_IDEAL_V9_v1-unmerged/GEN-SIM-RECO', notify='sekhri@fnal.gov')
	#"""
except Exception, ex:
	print ex
	#print ex.fault.string
			
