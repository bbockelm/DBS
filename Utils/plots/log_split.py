
import sys

def lsp(s):
	res=[]
	cur=""
	quote=""
	for i in s:
		if(i==" " and not quote):
			res.append(cur)
			cur=""
			continue
		if(i==" " and quote=="remove"):
			quote=""
			continue
		if(quote and i!=quote):
			cur=cur+i
			continue
		if(quote and i==quote):
			res.append(cur)
			cur=""
			quote="remove"
			continue
		if(i=='['):
			quote=']'
			continue
		if(i=='"'):
			quote='"'
			continue
		cur=cur+i
	if(cur):
		res.append(cur)
	return res


host_dict={}
host_count=1
while(1):
	f=sys.stdin.readline()
	if(not f):
		print host_dict
		break
	f=f.strip()
	#print f
	el=lsp(f)
	#print el
	#if(int(el[9])>300000):
	#	print el[4],el[8],el[6], el[9]
	host_id=0
	if(host_dict.has_key(el[0])):
		host_id=host_dict[el[0]]
	else:
		host_dict[el[0]]=host_count
		host_id=host_count
		host_count=host_count+1
	print str(host_id)+","+el[3].split()[0]+","+el[9]+","+el[6]


