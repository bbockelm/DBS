#
# Revision: 1.3 $"
# Id: DBSXMLParser.java,v 1.3 2006/10/26 18:26:04 afaq Exp $"
#
import os, re, string, httplib, urllib, urllib2, gzip
from dbsException import DbsException
from dbsApiException import *
from dbsExecHandler import DbsExecHandler

class DbsHttpService:

  """Provides Server connectivity through HTTP"""
  def __init__(self, ApiVersion, Url=None, Args={}):
    """ Constructor. """
    self.ApiVersion = ApiVersion
    print "\n\n\n******API VERSION NEEDS TO BE MADE PART OF CALLS TO SERVER*****\n\n\n"
    self._Url = Url
    self._Args = Args 
    if Url is None:
      self._Url = DEFAULT_URL

  def setUrl(self, Url):
    """ Set  script url. """
    self._Url = Url

  def setArgs(self, args):
    """ Set  script url. """
    self._Args = args

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


  def _call (self, args):
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

    # First apply forced options
    for k, v in self._Args.items(): args[k] = v
    # Fetch result from the CGI server
    try:
      #request = urllib2.Request (self._Url, urllib.urlencode(args))
      request = urllib2.Request (self._Url)
      request.add_header ('Accept-encoding', 'gzip')
      #print "args ",args
      self._marshall (args, request)
      #print "request ",request
      result = urllib2.build_opener (DbsExecHandler).open (request)
      #print "\n\nresult ->>>>>>>>", result
      #import pdb
      #pdb.set_trace()
      data = result.read()
      #f = open("out.txt", "w")
      #print "\n\ndata ->>>>>>>>", data
      if result.headers.get ('Content-encoding', '') == 'gzip':
        data = gzip.GzipFile (fileobj=StringIO(data)).read ()
      #f.write(data);
      #f.close();

      statusCode = int(result.headers.get ('Dbs-status-code'))
      statusMessage = result.headers.get ('Dbs-status-message')
      statusDetail = result.headers.get ('Dbs-status-detail')
      #print "statusCode ", statusCode
      #print "statusMessage ", statusMessage 
      #print "statusDetail ", statusDetail
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

      # All is ok, return the data
      return data

    except DbsException, ex:
      # One of our own errors, re-raise
      raise ex
    except Exception, ex:
      # URL access failed, raise an exception
      raise DbsToolError (exception=ex)


