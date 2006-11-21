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

  def _encode(self, args):
    """
    Encode form (name, value) elements into multi-part/form-data.
    We don't actually need to know what we are uploading here, so
    just claim it's all text/plain.
    """

    boundary = '----------=_DBS_BOUNDARY_=-----------'
    (body, crlf) = ('', '\r\n')
    for key, value in args.items():
      body += '--' + boundary + crlf
      body += ('Content-disposition: form-data; name="%s"' % key) + crlf
      body += crlf + value + crlf
    body += '--' + boundary + '--' + crlf + crlf
    return ('multipart/form-data; boundary=' + boundary, body)

  def _marshall(self, args, request):
    """
    Marshalls the arguments to the server as multi-part/form-data,
    not the default application/x-www-form-url-encoded.  This improves
    the transfer of the large inputs and eases command line invocation
    of the server script.
    """

    (type, body) = self._encode(args)
    request.add_header ('Content-type', type)
    request.add_header ('Content-length', str(len(body)))
    request.add_data (body)
    #print "body ", body


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
           
       print request_string  

       params = urllib.urlencode(args)
       headers = {"Content-type": "application/x-www-form-urlencoded", "Accept": "text/plain"} 

       conn = httplib.HTTPConnection(self.Host, self.Port)

       if type == 'POST':
          result = conn.request(type, request_string, params, headers)  
          #result = conn.request(type, request_string, xmlinput)  
       else:
          result = conn.request(type, request_string)

       response = conn.getresponse() 
 
       # See if HTTP call succeeded 
       if int(response.status) != 200:
          raise DbsToolError (args = response.reason)
 
       """
       statusCode = int(response.getheader('Dbs-status-code'))
       statusMessage = response.getheader('Dbs-status-message')
       statusDetail = response.getheader('Dbs-status-detail')
       print "statusCode ", statusCode
       print "statusMessage ", statusMessage 
       print "statusDetail ", statusDetail
       
       # If there was a server-side error, raise an appropriate exception
       if statusCode != 100:
         exmsg = "Status message: '%s', Status detail: '%s'" % (statusMessage, statusDetail)
	 if statusCode == 200: raise DbsBadRequest (args=exmsg)
         elif statusCode == 300: raise DbsBadData (args=exmsg)
         elif statusCode == 301: raise InvalidDataTier (args=exmsg)
         elif statusCode == 302: raise DbsNoObject (args=exmsg)
         elif statusCode == 303: raise DbsObjectExists (args=exmsg)
         elif statusCode == 400: raise DbsExecutionError (args=exmsg)
         elif statusCode == 401: raise DbsConnectionError (args=exmsg)
         elif statusCode == 402: raise DbsDatabaseError (args=exmsg)
         else: raise DbsToolError (args=exmsg)
       """

       data = response.read()
       print data

       # Error message would arrive in XML, if any
       class Handler (xml.sax.handler.ContentHandler):
        def startElement(self, name, attrs):
          if name == 'exception':
             statusCode = attrs['code']
               
             """
             if statusCode == 200: raise DbsBadRequest (args=exmsg)
             elif statusCode == 300: raise DbsBadData (args=exmsg)
             elif statusCode == 301: raise InvalidDataTier (args=exmsg)
             elif statusCode == 302: raise DbsNoObject (args=exmsg)
             elif statusCode == 303: raise DbsObjectExists (args=exmsg)
             elif statusCode == 400: raise DbsExecutionError (args=exmsg)
             elif statusCode == 401: raise DbsConnectionError (args=exmsg)
             elif statusCode == 402: raise DbsDatabaseError (args=exmsg)
             else: raise DbsToolError (args=exmsg)
             """

             print "Error Message: " + attrs['message']
             print "Error Details: " + attrs['detail'] 
             print "\n\n\n"
             # For now I am just raising an exception this must be done with proper codes
             raise DbsException(args=attrs['detail'])

          if name == 'warning':
             print "Waring Message: " + attrs['message']
             print "Warning Detail: " + attrs['detail']
             print "\n\n\n"
             #print attrs['detail']
 
       xml.sax.parseString (data, Handler ())


       #f = open("out.txt", "w")
       #print "\n\ndata ->>>>>>>>", data
       if response.getheader ('Content-encoding', '') == 'gzip':
         data = gzip.GzipFile (fileobj=StringIO(data)).read ()
       #f.write(data);
       #f.close();

       # All is ok, return the data
       return data

    except DbsException, ex:
      # One of our own errors, re-raise
      raise ex
    except Exception, ex:
      # URL access failed, raise an exception
      raise DbsToolError (exception=ex)


