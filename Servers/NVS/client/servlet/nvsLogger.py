#!/usr/bin/env python
# This class controls NVS's logging behaviour
#
# Revision: 1.3 $"

import logging
from nvsException import *

# NVS Defined Log Levels
NVSDEBUG=1
NVSINFO=2
NVSWARNING=3

class NvsLogger:

  # Default LOG Level is ERROR
  def __init__(self, userloglevel="ERROR", logout="STDOUT"):
    #
    # Setup Proper logging
    #
    # NVS Supported Log Levels
    #  Standard
    #CRITICAL   50
    #ERROR      40
    #NOTSET     0
    #  NVS Defined
    #NVSWARNING 3
    #NVSINFO    2
    #NVSDEBUG   1
    logging.addLevelName(NVSDEBUG, 'NVSDEBUG')
    logging.addLevelName(NVSINFO, 'NVSINFO')
    logging.addLevelName(NVSWARNING, 'NVSWARNING')

    if userloglevel in ("", None, "NVSDEBUG"):
       loglevel=NVSDEBUG
    elif userloglevel == 'NVSINFO':
       loglevel=NVSINFO
    elif userloglevel == 'NVSWARNING':
       loglevel=NVSWARNING
    elif userloglevel == 'ERROR':
       loglevel=logging.ERROR
    elif userloglevel == 'CRITICAL':
       loglevel=logging.CRITICAL
    elif userloglevel == 'NOTSET':
       loglevel=logging.NOTSET
    # If something else is specified, raise error
    else :
       raise NvsException("Unknow Log 'LEVEL'=%s, please choose a valid level" % str(userloglevel) , 3000)

    # User want to write in a file ?
    if logout not in ("", None, "STDOUT"):
            print "Writing log to ", logout
            logging.basicConfig(level=loglevel,
                    format='%(asctime)s %(levelname)s %(message)s',
                    filename=logout,
                    filemode='w+')
    # By default write it on stdout
    else:
            logging.basicConfig(level=loglevel,
                    format='%(asctime)s %(levelname)s %(message)s')

