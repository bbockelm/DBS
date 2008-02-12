#
# Revision: 1.3 $"
# Id: NVSXMLParser.java,v 1.3 2006/10/26 18:26:04 afaq Exp $"
#
import os, re, string, httplib, urllib, urllib2, gzip, time
from nvsException import NvsException

import os, re, string, xml.sax, xml.sax.handler
from xml.sax.saxutils import escape
from xml.sax import SAXParseException
try:
  from socket import ssl, sslerror, error
except:
  print "Unable to support HTTPS"
  pass

import urlparse

import logging
from nvsLogger import *

class NvsHttpService:

  """Provides Server connectivity through HTTP"""

  def __init__(self, Url, ApiVersion, Args={}):

	    self.ApiVersion = ApiVersion
            self.Url = Url

            spliturl = urlparse.urlparse(Url)
            callType = spliturl[0]
            if callType not in ['http', 'https']:
                raise NvsException("HttpError, BAD URL: %s" %Url, "200")
            hostport=urllib2.splitport(spliturl[1])
            self.Host=hostport[0]
            self.Port=hostport[1]
            if self.Port in [None, ""]:
                self.Port = "80"
            self.Servlet=spliturl[2]
            if self.Servlet in ['None', '']:
                raise NvsException ("HttpError, BAD URL: %s  Missing Servlet Path" %Url, "200")
            if callType == 'https':
               ##Make a secure connection       
               self.Secure = True
            else:
               self.Secure = False

  def setDebug(self, on=1):
    """ Set low-level debugging. """
    httplib.HTTPConnection.debuglevel = on

  def getKeyCert(self):
   """
   Gets the User Proxy if it exists otherwise THROWs an exception
   """

   # First presendence to HOST Certificate, RARE
   if os.environ.has_key('X509_HOST_CERT'):
        proxy = os.environ['X509_HOST_CERT']
        key = os.environ['X509_HOST_KEY']
   
   # Second preference to User Proxy, very common
   elif os.environ.has_key('X509_USER_PROXY'):
	proxy = os.environ['X509_USER_PROXY']
	key = proxy

   # Third preference to User Cert/Proxy combinition
   elif os.environ.has_key('X509_USER_CERT'):
   	proxy = os.environ['X509_USER_CERT']
   	key = os.environ['X509_USER_KEY']

   # Worst case, look for proxy at default location /tmp/x509up_u$uid
   else :
	uid = os.getuid()
   	proxy = '/tmp/x509up_u'+str(uid)
	key = proxy

   #Set but not found
   if not os.path.exists(proxy) or not os.path.exists(key):
	raise NvsException("Required Proxy for Secure Call \n("+self.Url+") not found for user '%s'" %os.getlogin(), "9999")

   # All looks OK, still doesn't gurantee proxy's validity etc.
   return key, proxy
   


  """
  def _call(self, args, typ, repeat = 3, delay = 2 ):
	  try:
		  return self._callOriginal(args, typ)
	  except NvsConnectionError ,  ex:
		  return self.callAgain(args, typ, repeat, delay)
	  except NvsProxyNotFound , ex:
		  return self.callAgain(args, typ, repeat, delay)
	  except NvsExecutionError , ex:
		  return self.callAgain(args, typ, repeat, delay)
	  except NvsBadXMLData , ex:
		  return self.callAgain(args, typ, repeat, delay)
		  
  def callAgain(self, args, typ, repeat, delay):
	  print "I will retry in %s seconds" % delay
	  if(repeat!=0):
		  repeat -= 1
		  time.sleep(delay)
		  delay += 1
		  return self._call(args, typ, repeat, delay*10)

  """
  #def _callOriginal (self, args, typ):
  def _call (self, args, typ):
    """
    Make a call to the server, either a remote HTTP request (the
    URL is of the form http:*), or invoke as a local executable
    (the URL is of the form file:*).
    
    For the HTTP case, we build a request object, add the form data
    to as multipart/form-data, then fetch the result.  The output is
    compressed so we decompress it, parse out the NVS special HTTP
    headers for status code and error information, and raise errors
    as exceptions.  If all went well, we return the output to caller.

    The local execution is similar except we pass call details via
    environment variables and form data on standard input to the CGI
    script.  The output isn't compressed.  (FIXME: Not implemented!)
    """

    try:
       request_string = self.Servlet+'?apiversion='+self.ApiVersion

       for key, value in args.items():
          if key == 'api':
             request_string += '&'+key+'='+value
          else: 
             if typ != 'POST':
               request_string += '&'+key+'='+urllib.quote(value) 
             continue 
       if self.Secure != True :
		self.conto = "\n\nhttp://" + self.Host + ":" + self.Port  + request_string + "\n\n" 	
       		self.conn = httplib.HTTPConnection(self.Host, int(self.Port))
       else:
       		self.conto = "\n\nhttps://" + self.Host + ":" + self.Port  + request_string + "\n\n"
		key, cert = self.getKeyCert()
		self.conn = httplib.HTTPSConnection(self.Host, int(self.Port), key, cert)

       logging.log(NVSINFO, self.conto)	
 
       params = urllib.urlencode(args)
       headers = {"Content-type": "application/x-www-form-urlencoded", "Accept": "text/plain"} 


       if typ == 'POST':
          result = self.conn.request(typ, request_string, params, headers)  
       else:
          result = self.conn.request(typ, request_string )

       logging.log(NVSINFO, request_string)

       response = self.conn.getresponse() 

       # See if HTTP call succeeded 
       responseCode = int(response.status)
       if responseCode != 200:
         exmsg = "\nCall to NVS Server ("+self.Url+") failed"
         statusResponse = response.read()
         try:
           statusDetail = statusResponse.split('<body>')[1].split('</body>')[0].split('description')[1]  
	   statusDetail = statusDetail.split('<u>')[1].split('</u>')[0]
         except:
           statusDetail=statusResponse

         exmsg += "\nHTTP ERROR Status '%s', \nStatus detail: '%s'" % (responseCode, statusDetail)
	 raise NvsException (exmsg, responseCode)
 
       # HTTP Call was presumly successful, and went throught to NVS Server 
       data = response.read()
       logging.log(NVSDEBUG, data)

    except sslerror, ex:
	msg  = "HTTPS Error, Unable to make API call"
	msg += "\nUnable to connect %s (Please verify!)" % self.Url
	msg += "\nMost probably your Proxy is not valid"
        raise NvsException (msg, "505")   

    except error, ex:
	msg  = "HTTP(S) Error, Unable to make API call"
        msg += "\nUnable to connect %s (Please verify!)" % self.Url
        msg += "\nMost probably url (host, port) specified is incorrect, or using http instead of https"
        msg += "\nError Message: %s" % ex
        raise NvsException (msg, "505")   
  
    except (urllib2.URLError, httplib.HTTPException), ex:
        msg = "HTTP ERROR, Unable to make API call"
        msg += "  \nVerify URL %s" % self.Url
        msg += "  \nError Message: %s" % ex
        raise NvsException (msg, "505")   

    except Exception, ex:
        if (isinstance(ex,NvsException)):
		raise ex
	msg = "HTTP ERROR, Unable to make API call"
	msg += "\n         Verify URL %s" % self.Url
	if str(ex) == "(-2, 'Name or service not known')":
		msg += "\n   Error Message: %s" %ex.args[1]
	else: msg += "\n     Error Message: %s" %ex
	raise NvsException (msg, "505")            

    try:
      # Error message would arrive in XML, if any
      class Handler (xml.sax.handler.ContentHandler):
           def startElement(self, name, attrs):
             if name == 'error':
                statusCode = 1001
                exmsg = "NVS Server Raised An Error: %s" \
                                 %(attrs['message'])
                
                raise NvsException (exmsg, statusCode)


      #print data
      xml.sax.parseString (data, Handler ())
      # All is ok, return the data
      return data

    except SAXParseException, ex:
      msg = "Unable to parse XML response from NVS Server"
      msg += "\n   Verify URL %s" % self.Url
      raise NvsException (msg, "5999")	


