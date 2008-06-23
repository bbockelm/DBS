#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Copyright 2008 Cornell University, Ithaca, NY 14853. All rights reserved.
#
# Author:  Valentin Kuznetsov, 2008
"""
Common network-related utilities module used by DBS data discovery.
"""
import os, sys, httplib, urllib, urllib2, socket

# Since RunInfo were slow I need customized HTTP connection allowing per-connection timeout.
# Code taken from 
# http://blog.tiinker.com/2007/11/23/timing-out-in-pythons-urllib/
class MyHTTPConnection(httplib.HTTPConnection):
    """A customised HTTPConnection allowing a per-connection
    timeout, specified at construction."""

    def __init__(self, host, port=None, strict=None,
                timeout=None): # timeout in seconds
        httplib.HTTPConnection.__init__(self, host, port,
                strict)
        self.timeout = timeout

    def connect(self):
        """Override HTTPConnection.connect to connect to
        host/port specified in __init__."""

        msg = "getaddrinfo returns an empty list"
        for res in socket.getaddrinfo(self.host, self.port,
                0, socket.SOCK_STREAM):
            af, socktype, proto, canonname, sa = res
            try:
                self.sock = socket.socket(af, socktype, proto)
                if self.timeout:   # this is the new bit
                    self.sock.settimeout(self.timeout)
                self.sock.connect(sa)
            except socket.error, msg:
                if self.sock:
                    self.sock.close()
                self.sock = None
                continue
            break
        if not self.sock:
            raise socket.error, msg

class MyHTTPHandler(urllib2.HTTPHandler):
    """A customised HTTPHandler which times out connection
    after the duration specified at construction."""

    def __init__(self, timeout=None):
        urllib2.HTTPHandler.__init__(self)
        self.timeout = timeout

    def http_open(self, req):
        """Override http_open."""

        def makeConnection(host, port=None, strict=None):
            return MyHTTPConnection(host, port, strict,
                    timeout = self.timeout)

        #print "MyHTTPHandler opening", req.get_full_url()
        return self.do_open(makeConnection, req)

#
# main
#
if __name__ == "__main__":
    iParams={}
    iParams['XML']=1
    iParams['RUN']="47041,47042,47011,46972,46986,46992,46998,46999,47064,47068"
    url = "http://cmsmon.cern.ch/cmsdb/servlet/RunSummary"
    http_handler = MyHTTPHandler(timeout = 15)
    opener = urllib2.build_opener(http_handler)
    req = urllib2.Request(url,urllib.urlencode(iParams,doseq=True))
    data = opener.open(req).read()
    print data

    # Now let's fetch a URL
#    http_handler = MyHTTPHandler(timeout = 15)
#    opener = urllib2.build_opener(http_handler)
#    req = urllib2.Request("http://tiinker.com")
#    try:
#        file = opener.open(req)
#    except Exception, e:
#        print e # your error handling here
