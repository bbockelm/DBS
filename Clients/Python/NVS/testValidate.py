from Wrapper import API
try :
	api = API()
	#api = API(url='http://plasma.dhcp.fnal.gov:8080/nvswebapp/nvservice')
	result = api.validate('rec', 'Tier')
	for i in result:
		print i._name
		print i._similar
except Exception, ex:
	print ex
	#print ex.fault.string

