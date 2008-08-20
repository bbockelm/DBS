#!/usr/bin/env python

# reformat the squid log files and spit out just the information that is needed.

# Author: Lee Lueking  28 Sept. 2007
#
# example of usage
# ./reform-squid-log.py --input=infile.log --output=outfile.log
#      --offset=byteoffset
#
# if the offset is provided, start parsing at that location of the file.
# when the EOF is reached, the current bytelocation is recorded in file byte-offset.dat
import sys
import urllib2
from xml.dom.minidom import parseString
import base64
import zlib 
import string
import curses.ascii
import time
import os

def usage():
  progName = os.path.basename(sys.argv[0])
  print "Usage:"
  print "  %s --input=<infile> --output=<outfile> [--offset=byteoffset]" %progName
  print " "

def timeInMillis(t):
  return long(1000*t)
  
input = None
output = None
offset=0

for a in sys.argv[1:]:
  arg = string.split(a, "=")
  if arg[0] == "--input":
    infile = arg[1]
  elif arg[0] == "--output":
    outfile = string.join(arg[1:], "=")
  elif arg[0] == "--offset":
    offset = string.atoi(arg[1])
  else:
    print "Ignoring unrecognized argument: %s" % a

if infile is None or outfile is None:
  usage()
  sys.exit(1)    
  
log=open(infile,"r")
out=open(outfile,"w")
#
# find the offset 
#
log.seek(offset)
print "Begin at byte offset ",offset
node_lookup={}
i=0
logline="start"
#while i <1000:
while i >-1:   
   oldline=logline
   logline=log.readline()
   #print "offset: ",log.tell()
   #print logline
   i=i+1
   if logline =="":
     print 'Input finished'
     break
   try:
     #print logline
# parse the string
     part=string.split(logline," ")
     #print part
     connect_ip=part[0]
     connect_date=part[3].replace("[","")
     #print connect_date
     time_zone=part[4].replace("]","")
     os.putenv("TZ",time_zone)
     connect_time=time.mktime(time.strptime(connect_date,"%d/%b/%Y:%H:%M:%S"))
     #print connect_time
     connect_string=part[6]
     return_size=part[9]
     return_status=part[10]
     return_duration=part[11].replace("\n","")
#
     part=string.split(return_status,":")
     return_cache_status=part[0]
     return_cache_origin=part[1]
#
     part=string.split(connect_string,"/")
     if string.find(part[3],"?") >=0:
       instance="malformed" #malformed connect string
     else:
       instance=part[3]
#
     part=string.split(connect_string,"=")
     #print part
     encoding=part[2].replace("&p1","")
     #print connect_string
     if string.find(part[3],"&") >= 0:
        encQuery=string.split(part[3],"&")[0]
     else:
        #print connect_string
        l=len(connect_string)
        if connect_string[l-2:] =="==":
           # Special case where encoded string ends in "=="
           encQuery=part[3]+"=="
        elif connect_string[l-1:] =="=":
           # Special case where encoded string ends in "="
           encQuery=part[3]+"="
        else:
           encQuery=part[3]
   except:  
     print "Problem parsing: ",i,logline
     continue
   try:
     decQuery="Decoding problem entry %d"%i
     decQuery=zlib.decompress(base64.binascii.a2b_base64(encQuery.replace(".","+").replace("-","/").replace("_","=")))
# Parse out the owner and table name form the decoded string
     l_owner=False
     l_table=False
     l_name=False
     #print decQuery
     for phrase in string.split(decQuery):
        if string.find(phrase,"=") >= 0:
          if string.find(phrase,"OWNER") >= 0:
             pair=string.split(phrase,"=")
             owner=pair[1].replace("'","")
             l_owner=True
          elif string.find(phrase,"TABLE_NAME") >= 0:
             pair=string.split(phrase,"=")
             table=pair[1].replace("'","")
             l_table=True
        elif string.find(phrase,'"NAME"') >= 0:
          if string.find(phrase,"=") >= 0:
             #print "TOKEN:",decQuery
             pair=string.split(decQuery,"=")
             tagname=pair[1].replace("'","").replace(" ","").replace("\n","")
             l_name=True
# If there was not an owner, try something else
     if not l_owner:
        #print decQuery
        if string.find(decQuery,"FROM") >= 0:
          owner=string.split(string.split(decQuery,"FROM")[1])[0].replace('"',"")
          if string.find(owner,"(SELECT") >=0:
             owner=string.split(string.split(string.split(decQuery,"FROM")[2])[0].replace('"',""),".")[0]
             table=string.split(string.split(string.split(decQuery,"FROM")[2])[0].replace('"',""),".")[1]
             l_table=True
          elif string.find(owner,".") >=0:
             owner=string.split(string.split(string.split(decQuery,"FROM")[1])[0].replace('"',""),".")[0]
             table=string.split(string.split(string.split(decQuery,"FROM")[1])[0].replace('"',""),".")[1]
             l_table=True
        elif string.find(decQuery,"from") >= 0:
          owner=string.split(string.split(decQuery,"from")[1])[0].replace('"',"")
        else:
          owner="unknown"
     if not l_table:
        table="unknown"
     if not l_name:
        tagname="na"
# look up the name and domain of requesting node.
     #print connect_ip
     if node_lookup.has_key(connect_ip):
        connect_node=node_lookup[connect_ip]
     else: #add the node
        #print connect_ip
        #connect_node=os.popen("nslookup %s"%connect_ip).readlines()[3].replace("Name:    ","").replace("\r\n","")
        #connect_node=string.split(os.popen("host %s"%connect_ip).readline(),"pointer ")[1].replace(".\n","")
        response=os.popen("host %s"%connect_ip).readlines()
        for line in response:
  	   if line.find("pointer") > 0:
    	      connect_node=string.split(line,"pointer ")[1].replace(".\n","")
        #print connect_node
        node_lookup[connect_ip]=connect_node
     part=string.split(connect_node,".")
     connect_domain=connect_node.replace(part[0]+".","")
     #print connect_node
     connect_nodename=part[0]
     out.write('%s %s %s %s %s %s %s %s %s %s %s %s %s\n'%(connect_ip,connect_nodename,connect_domain,connect_time,return_size,return_duration,owner,table,instance,encoding,return_cache_status,tagname,return_cache_origin))
     #print connect_ip,connect_node,connect_domain,connect_time,return_size,return_duration,owner,table,instance,encoding,return_cache_status,return_cache_origin,tagname
     #print i,"---------------------------------------"
   except:
     print "Problem log entry",logline 
     print "decoded query",decQuery
offset=log.tell()
off=open("offset.dat","w+")
off.write("%i"%offset)
print "Last line: ",oldline
print "End at byte offset ",offset
off.close()
log.close()
out.close()
print "Number of entries parsed: ",i
print 'EOF'  
