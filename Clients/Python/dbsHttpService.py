#
# Revision: 1.3 $"
# Id: DBSXMLParser.java,v 1.3 2006/10/26 18:26:04 afaq Exp $"
#
import os, re, string, httplib, urllib, urllib2, gzip
from dbsException import DbsException
from dbsApiException import *
from dbsExecHandler import DbsExecHandler

import os, re, string, xml.sax, xml.sax.handler
from xml.sax.saxutils import escape

import logging

class DbsHttpService:

  """Provides Server connectivity through HTTP"""
  def __init__(self, Host, Port, Applet, ApiVersion, Args={}):
    """ Constructor. """
    
    self.Host = Host
    self.Port = Port
    #if type(Port) ==  type(int(1)):
    #   raise DbsException(args=
    #         "Validation Error: Port must be a integer type, give type is %s " \
    #                        %type(Port) )
    self.Applet = Applet
    self.ApiVersion = ApiVersion
    
  def setDebug(self, on=1):
    """ Set low-level debugging. """
    httplib.HTTPConnection.debuglevel = on


  def _call (self, args, type):
    """
    Make a call to the server, either a remote HTTP request (the
    URL is of the form http:*), or invoke as a local executable
    (the URL is of the form file:*).
    
    For the HTTP case, we build a request object, add the form data
    to as multipart/form-data, then fetch the result.  The output is
    compressed so we decompress it, parse out the DBS special HTTP
    headers for status code and error information, and raise errors
    as exceptions.  If all went well, we return the output to caller.

    The local execution is similar except we pass call details via
    environment variables and form data on standard input to the CGI
    script.  The output isn't compressed.  (FIXME: Not implemented!)
    """

    try:

       request_string = self.Applet+'?apiversion='+self.ApiVersion

       for key, value in args.items():
          if key == 'api':
             request_string += '&'+key+'='+value
          else: 
             if type != 'POST':
               request_string += '&'+key+'='+value 
             continue 
           
       conto = "\n\nhttp://" + self.Host + ":" + self.Port  + request_string + "\n\n"
       logging.info(conto); 
        
       params = urllib.urlencode(args)
       headers = {"Content-type": "application/x-www-form-urlencoded", "Accept": "text/plain"} 

       conn = httplib.HTTPConnection(self.Host, self.Port)

       if type == 'POST':
          result = conn.request(type, request_string, params, headers)  
       else:
          result = conn.request(type, request_string)

       response = conn.getresponse() 

       # See if HTTP call succeeded 
       responseCode = int(response.status)
       if responseCode != 200:
         statusDetail = "HTTP Call to server failed"
         #statusDetail = response.read()
         #statusDetail = response.read().split('<body>')[1].split('</body>')[0].split('description')[1]  
         exmsg = "HTTP ERROR Status '%s', Status detail: '%s'" % (responseCode, statusDetail)
         if responseCode == 200: raise DbsBadRequest (args=exmsg, code=responseCode)
         elif responseCode == 300: raise DbsBadData (args=exmsg, code=responseCode)
         elif responseCode == 302: raise DbsNoObject (args=exmsg, code=responseCode)
         elif responseCode == 400: raise DbsExecutionError (args=exmsg, code=responseCode)
         elif responseCode == 401: raise DbsConnectionError (args=exmsg, code=responseCode)
         else: raise DbsToolError (args=exmsg, code=responseCode)
 
       # HTTP Call was presumly successful, and went throught to DBS Server 
       data = response.read()
       #loggin.info(data)

    except Exception, ex:
      msg = "HTTP ERROR, Unable to make API call: %s" % conto
      raise DbsConnectionError (args=msg, code="401")            


    # Error message would arrive in XML, if any
    class Handler (xml.sax.handler.ContentHandler):
           def startElement(self, name, attrs):
             if name == 'exception':
                statusCode = attrs['code']
                exmsg = "DBS Server Raised An Error: %s, %s" \
                                 %(attrs['message'], attrs['detail'])
                
                if (int(statusCode) < 2000 and  int(statusCode) > 1000 ): 
                   raise DbsBadRequest (args=exmsg, code=statusCode)

                if (int(statusCode) < 3000 and  int(statusCode) > 2000 ):
                   raise DbsDatabaseError (args=exmsg, code=statusCode) 
             
                if (int(statusCode) < 4000 and  int(statusCode) > 3000 ):
                   raise DbsBadXMLData (args=exmsg, code=statusCode)

                else: raise DbsExecutionError (args=exmsg, code=statusCode)

             if name == 'warning':
                warn  = "DBS Raised a warning message"
                warn += "Waring Message: " + attrs['message']
                warn += "Warning Detail: " + attrs['detail']+"\n"
                logging.warning(warn)

    xml.sax.parseString (data, Handler ())
    # All is ok, return the data
    return data


