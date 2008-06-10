import sys
import _mysql
import os.path
#import pdb
#pdb.set_trace()


if(len(sys.argv)<3):
	print sys.argv[0],"path, index_file1, [index_file2, ...]"
	sys.exit(1)
	


methlist=("double","int32","string","InputTag","bool","uint32","vdouble","vstring","vint32","VPSet","FileInPath","VInputTag","vuint32")


class CfgFile:
	def __init__(self,cfg_path,fn):
		self.cfg_path=cfg_path
		self.fname=fn
		cfgname=os.path.basename(self.fname)
		cfgname=cfgname[:-len(".index")]
		if(self.cfg_path[-1]!='/'):
			self.cfg_path=self.cfg_path+'/'
		self.cfgname=self.cfg_path+cfgname
           
		self.cfgfile=open(self.fname,"r")
		# reading PSetHash first
		s=self.cfgfile.readline()
		if(not s):
			raise "File is empty?"
		s=s.strip()
		if(not s.startswith("PSetHash")):
			raise "Bad file format, expected PSetHash, got ["+s+"]"
		s=s.split("=")
		if(len(s)!=2):
			raise "Bad file format: expected equal sign in line ["+s+"]"
		md5=s[1]
		if(md5[0]!='"' or md5[-1]!='"'):
			raise "Bad PSetHash line, md5 is not enclosed in \": ["+md5+"]"
		md5=md5[1:-1]
		if(len(md5)!=32):
			raise"Bad md5 ["+md5+"]"
		self.md5=md5
		print "md5 ["+self.md5+"]"
		
		self.dv_double={}
		self.dv_string={}
		self.dv_bool={}
		
		
	def parse(self):
		global methlist
		while(1):
			s=self.cfgfile.readline()
			if(not s):
				break
			s=s.strip()
			processed=0
			for m in methlist:
				sm=m+" ";
				if(s.startswith(sm)):
					pline=s[len(sm):]
					mn="doparam_"+m
					meth=getattr(self,mn,None)
					if(not meth):
						print "Unsupported type in line ["+s+"]"
					meth(pline)
					processed=1
					break
			if(not processed):
				print "Unknown type in line ["+s+"]"
			
			
	def doparam_double(self,pline):
		p=pline.split("=")
		if(len(p)!=2):
			print "Bad double pline ["+pline+"]"
			return
		pv=float(p[1])
		if(self.dv_double.has_key(p[0])):
			vv=self.dv_double[p[0]]
			vv.append(pv)
		else:
			self.dv_double[p[0]]=[pv]

	def doparam_int32(self,pline):
		self.doparam_double(pline)

	def doparam_uint32(self,pline):
		self.doparam_double(pline)

	def doparam_string(self,pline):
		p=pline.split("=")
		if(len(p)!=2):
			print "Bad string pline ["+pline+"]"
			return
		pv=p[1]
		if(self.dv_string.has_key(p[0])):
			vv=self.dv_string[p[0]]
			vv.append(pv)
		else:
			self.dv_string[p[0]]=[pv]

	def doparam_InputTag(self,pline):
		self.doparam_string(pline)
		
	def doparam_bool(self,pline):
		pass
	
	def doparam_vdouble(self,pline):
		pass
	
	def doparam_vstring(self,pline):
		p=pline.split("=")
		if(len(p)!=2):
			print "Bad string pline ["+pline+"]"
			return
		pv=p[1]
		if(self.dv_string.has_key(p[0])):
			vv=self.dv_string[p[0]]
			vv.append(pv)
		else:
			self.dv_string[p[0]]=[pv]

	def doparam_vint32(self,pline):
		pass

	def doparam_VPSet(self,pline):
		pass
	
	def doparam_FileInPath(self,pline):
		self.doparam_string(pline)
		
	def doparam_VInputTag(self,pline):
		pass

	def doparam_vuint32(self,pline):
		pass
	
	
	def clear_db(self,db):
		db.query("""delete from cfgfile where md5='%s'"""%(self.md5,))
		db.query("""delete from cfgfile where filename='%s'"""%(self.cfgname,))
		db.query("""delete from par_double where md5='%s'"""%(self.md5,))
		db.query("""delete from par_string where md5='%s'"""%(self.md5,))
	
	def store(self,db):
		self.clear_db(db)
		q="""insert into cfgfile(filename,md5) values('%s','%s')"""%(self.cfgname,self.md5)
		print q
		db.query(q)
		# insert double values
		for p in self.dv_double.keys():
			pl=self.dv_double[p]
			for pv in pl:
				db.query("""insert into par_double(md5,pname,pval) values('%s','%s',%f)"""%(self.md5,p,pv))				
		# insert string values
		for p in self.dv_string.keys():
			pl=self.dv_string[p]
			for pv in pl:
				if(pv.startswith('"') or pv.startswith("'")):
					pv=pv[1:]
				if(pv.endswith('"') or pv.endswith("'")):
					pv=pv[:-1]
				pv=self.clear_string(pv)
				if(pv):
					db.query("""insert into par_string(md5,pname,pval) values('%s','%s','%s')"""%(self.md5,p,pv))				
				
	def clear_string(self,v):
		res=""
		for i in v:
			if(i=='"' or i=="'"):
				res=res+"\\"+i
			else:
				res=res+i
		res=res.strip()
		return res	


db=_mysql.connect("edge.fnal.gov","dbs","dbs2006Z","dbs")
#db.query("use dbs")
db.autocommit(0)
for i in sys.argv[2:]:
	print "Parsing file ["+i+"]"
	cf=CfgFile(sys.argv[1],i)
	cf.parse()
	cf.store(db)
	db.commit()


