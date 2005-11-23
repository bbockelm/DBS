#!/usr/bin/env python
#
# $Id: dbsConfigManager.py,v 1.3 2005/11/07 21:40:02 sveseli Exp $
#
# Config manager class. 
#

###############################################################################
# DBS Web Service configuration parameters.

# Server parameters.
ServerConfigDict_ = {

  # Host.
  "Host" : "localhost.localdomain",

  # Port.
  "Port" : 27981,
  
  # Setting this to 1 will turn on soap messages.
  "SoapDebugFlag" : 1,

  # Number of servants.
  "NumberOfServants" : 5,

  # API class name.
  "ApiClassName" : "dbsCgiApi.DbsCgiApi",

  # API class init string.
  "ApiArgumentsString" : "cgiUrl='http://cern.ch/cms-dbs/cgi-bin'",

  }

# Logging configuration parameters.
LogConfigDict_ = {

  # Log directory.
  "LogDir" : ".",
  
  # Log file name.
  "LogFileName" : "wsLog",
  
  # Log file name suffix format.
  "LogFileNameSuffixFormat" : "__%m_%d_%y",
  
  # If true, log the output to stdout.
  "WriteToStdOut" : True,


  # These are the log levels used as masks. 
  #LOG_LEVEL_QUIET_ = 0     # no output
  #LOG_LEVEL_INFO_ = 1      # messages intended for users
  #LOG_LEVEL_TRACE_ = 2     # execution trace
  #LOG_LEVEL_DEBUG_ = 4     # debugging
  #LOG_LEVEL_WARNING_ = 8   # warnings
  #LOG_LEVEL_ERROR_ = 16    # errors
  #LOG_LEVEL_ALL_ = 255     # all messages
  #DEFAULT_LOG_LEVEL_ = LOG_LEVEL_INFO_
  "LogLevel" : 255,

  }

# DatasetService configuration parameters.
DatasetServiceConfigDict_ = {

  }

