        fileList = ['aaa1122-0909-9767-8764aaa', 'aaa1122-0909-9767-8764bb']
         api.remapFiles (fileList, 'MyoutFile')
    """
    # Prepare XML description of the input

    funcInfo = inspect.getframeinfo(inspect.currentframe())
    logging.log(DBSDEBUG, "Api call invoked %s" % str(funcInfo[2]))

    xmlinput  = "<?xml version='1.0' standalone='yes'?>"
    xmlinput += "<dbs>"
    
    for afile in inFiles:
       xmlinput += " <in_file lfn='" + afile +"'/>"
       
    xmlinput += " <out_file lfn='" + outFile +"'/>"
    xmlinput += "</dbs>"

    logging.log(DBSDEBUG, xmlinput)
    if self.verbose():
       print "remapFiles, xmlinput",xmlinput

    # Call the method
    data = self._server._call ({ 'api' : 'remapFiles',
                         'xmlinput' : xmlinput }, 'POST')
    logging.log(DBSDEBUG, data)

  # ------------------------------------------------------------

