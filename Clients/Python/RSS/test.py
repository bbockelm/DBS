from Wrapper import API
try :
	api = API()
	#api = API(url='http://cmssrv48.fnal.gov:8080/rss/RSWebService')
	#api.createRunSequence('Test4Seq')
	#api.createRunSequence('Test3Seq', 10)
	#api.createRunSequence('Test4Seqa', 4, 2)
	print api.getNextRunNumber('test1')
	#print api.getCurrRunNumber('Test4Seq')
except Exception, ex:
	print ex
	#print ex.fault.string

