
import sys
import math

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

def cal_avg(list_run):
	sum=0.0
	sum2=0.0
	N=len(list_run)
	for i in list_run:
		sum=sum+i
		sum2=sum2+i*i
	av=sum/N
	sigma=math.sqrt((sum2-N*av*av)/(N-1.0))
	return (av,sigma)


host_dict={}
host_count=1
list_run=[]

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
	if(int(el[6])>2000):
		continue
	host_id=0
	if(host_dict.has_key(el[0])):
		host_id=host_dict[el[0]]
	else:
		host_dict[el[0]]=host_count
		host_id=host_count
		host_count=host_count+1
	list_run.append(float(el[9]))
	if(len(list_run)>100):
		del list_run[0]
	av,sigma=(0.0,0.0)
	if(len(list_run)==100):
		av,sigma=cal_avg(list_run)
	print str(host_id)+","+el[3].split()[0]+","+el[9]+","+el[6]+","+str(av)+","+str(sigma)


