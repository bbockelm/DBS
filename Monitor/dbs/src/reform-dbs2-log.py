#!/usr/bin/env python

# Reformat the DBS-2 server log files and spit out 
# just the information that is needed.

# Author: Lee Lueking added 
#
# example of usage
# ./reform-dbs2-log.py --input=infile.log --output=outfile.log

#
import sys
#import urllib2
#from xml.dom.minidom import parseString
#import base64
#import zlib 
import string
#import curses.ascii
import time
import os.path

def usage():
  progName = os.path.basename(sys.argv[0])
  print "Usage:"
  print "  %s --input=<infile> --output=<outfile>" %progName
  print " "

def timeInMillis(t):
  return long(1000*t)

                              

input = None
output = None

for a in sys.argv[1:]:
  arg = string.split(a, "=")
  if arg[0] == "--input":
    infile = arg[1]
  elif arg[0] == "--output":
    outfile = string.join(arg[1:], "=")
  else:
    print "Ignoring unrecognized argument: %s" % a

if infile is None or outfile is None:
  usage()
  sys.exit(1)    
  
log=open(infile,"r")
out=open(outfile,"w")
#
# Month map
#
month={"Jan":"01","Feb":"02","Mar":"03","Apr":"04","May":"05","Jun":"06","Jul":"07","Aug":"08","Sep":"09","Oct":"10","Nov":"11","Dec":"12"}

node_lookup={}
i=0
while 1:
     i=i+1  
     logline=log.readline()
     #print logline
     if logline == "":
        break
# parse the string
     instance="unknown"
     dbs_api="unknown"
     dbs_vsn="unknown"
     connect_type="unknown"
     connect_dn="unknown"
     part=string.split(logline," ")
# hack to take care of extra spaces in dn, find parts relative to '"GET'
     n=0
     for word in part:
       if (word != '"GET') and (word != '"POST'):
         n=n+1
       else:
         n=n-5
         break
     try:
        connect_ip=part[0]
        connect_dn=part[2].replace('"','')
        #if connect_dn=="-":print part
        connect_date=part[n+3].replace("[","")
        time_zone=part[4].replace("]","")
        os.putenv("TZ",time_zone)
        connect_time=time.mktime(time.strptime(connect_date,"%d/%b/%Y:%H:%M:%S"))
        connect_type=part[n+5].replace('"','')
        connect_string=part[n+6]
        return_size=part[n+9]
        return_status=part[n+10]
     except:
        print logline+"\n"
        continue
     try:
       return_duration=part[n+12].replace("\n","")
       if return_duration == "(Red":
         return_duration=part[n+15].replace("\n","")
     except IndexError:
       return_duration="-99"
# look up the name and domain of requesting node.
     #print connect_ip
     try:
      if node_lookup.has_key(connect_ip):
        connect_node=node_lookup[connect_ip]
      else: #add the node
        #print connect_ip
        #connect_node=os.popen("nslookup %s"%connect_ip).readlines()[3].replace("Name:    ","").replace("\r\n","")
        connect_node=string.split(os.popen("host %s"%connect_ip).readline(),"pointer ")[1].replace(".\n","")
        #print connect_node
        node_lookup[connect_ip]=connect_node
      part=string.split(connect_node,".")
      connect_domain=connect_node.replace(part[0]+".","")
      connect_nodename=part[0]
      #print connect_node
     except:
      connect_domain="unknown"
      connect_nodename="unknown"
      print "bad ip address:",logline,connect_node+"\n"
# parse connect string
     try:
       part=string.split(connect_string,"?")
       #print n,part
       instanceinfo=part[0]
       queryinfo=part[1]
       part=string.split(instanceinfo,"/")
       instance=part[1].replace("cms_dbs_","")
       for element in string.split(queryinfo,"&"):
          if element.find("apiversion=") == 0:
             dbs_vsn=element.replace("apiversion=","")
             #print "dbs_vsn:",dbs_vsn
          if element.find("api=") == 0 :
             dbs_api=element.replace("api=","")
             #print "dbs_api:",dbs_api
       part=string.split(connect_dn,"/")
       dn_short=part[len(part)-1]
     except IndexError:
       print logline+"\n"
       #print part,"\n"

#
#   print encQuery
     out.write('%s %s %s %s %s %s %s %s %s %s %s\n'%(connect_ip,connect_nodename,connect_domain,connect_time,return_size,return_duration,instance,dbs_api,dbs_vsn,connect_type,connect_dn))
log.close()
out.close()
print "Number of entries parsed: ",i
print 'EOF'  
