#!/usr/bin/env python
# $Id: $
"""
Load the logging configuration file.
"""
__author__ = "Andrew J. Dolgert <ajd27@cornell.edu>"
__revision__ = "$Revision$"

import logging
import logging.config

def ConfigureLog():
    logging.config.fileConfig("logging.conf")
    
    #create logger
    logger = logging.getLogger("ConstructQuery")
