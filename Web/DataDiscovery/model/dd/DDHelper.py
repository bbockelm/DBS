#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
# Author:  Valentin Kuznetsov, 2008

import time
import traceback
from   utils.DDUtil import singleList, parseCreatedBy, timeGMT
from   utils.DDUtil import sizeFormat, addToDict, convertDBS2DDTime, where_cond
try:
    # Python 2.5
    import xml.etree.ElementTree as ET
except:
    # prior requires elementtree
    import elementtree.ElementTree as ET

class DDHelper(): 
    """
      Helper class to retrieve DBS information
    """
    def __init__(self, dbsmgr, ddconfig, verbose=0):
        """
           DDHelper constructor
        """
        self.dbsmgr      = dbsmgr
        self.verbose     = verbose

    def queryDBS(self, dbsInst, query, fromRow = 0, limit = 0):
        """
            execute given query using DBS manager
        """
        if  limit:
            q_start = fromRow
            q_end   = fromRow+limit
        else:
            q_start = ""
            q_end   = ""
        try:
            res, titles = self.dbsmgr.exe(dbsInst, query, q_start, q_end)
        except:
            time.sleep(1)
            try:
                res, titles = self.dbsmgr.exe(dbsInst, query, q_start, q_end)
            except:
                traceback.print_exc()
                raise Exception("Fail to query DBS '%s' with query '%s'" \
                    % (dbsInst, query))
        if  not len(res):
            return []
        elif  len(res[0]) == 1:
            return singleList(res)
        return res

    def getBlocksInfo(self, dbsInst, dataset):
        # (name,long(blkSize),long(nFiles),long(nEvts),status,cBy,cDate,mBy,mDate,[se])
        query = 'find block.name, block.size, block.numfiles, block.numevents, block.status, block.createby, block.createdate, block.modby, block.moddate, site where dataset = %s' % dataset
        res   = self.queryDBS(dbsInst, query)
        olist = []
        for item in res:
            blk, blksize, nfiles, nevts, status, cby, cdate, mby, mdate, site = item
            sites = [site]
            if  olist:
                last = olist[-1]
                if  blk == last[0]:
                    sites += last[-1]
                    olist.remove(last)
            olist.append((blk, blksize, nfiles, nevts, status, cby, cdate, mby, mdate, sites))
        return olist

    def getSiteList(self, dbsInst, dataset):
        query = 'find site where dataset = %s ' % dataset
        res = self.queryDBS(dbsInst, query)
        res.sort()
        return res

    def nDatasets(self, dbsInst):
        query = 'find dataset where dataset like *'
        return self.dbsmgr.count(dbsInst, query)

    def numberOfEvents(self,datasetPath):
        query = 'find dataset where dataset = %s' % datasetPath
        return self.dbsmgr.count(dbsInst, query)

    def datasetSummary4Sites_v1(self, dbsInst, dataset):
        sitedict = {}
        query = 'find site, sum(block.numevents), sum(block.numfiles), sum(block.size) where dataset = %s ' % dataset
        res   = self.queryDBS(dbsInst, query)
        for row in res:
            site, nevts, nfiles, size = row
            sitedict[site] = (nevts, nfiles, size)
        return sitedict

    def datasetSummary4Sites(self, dbsInst, dlist):
        """
        Retrieve site summary for datasets, use query splitting for provided dataset list
        returns: {'dataset':[(site, nevts, nfiles, size),...]}
        """
        sitedict = {}
        clist = where_cond(dlist, 'dataset')
        query = 'find dataset,site,sum(block.numevents),sum(block.numfiles),sum(block.size)'
        for cond in clist:
            if  cond:
                newquery = query + ' where ' + cond
                res = self.queryDBS(dbsInst, newquery)
                for row in res:
                    dataset, site, nevts, nfiles, size = row
                    addToDict(sitedict, dataset, (site, nevts, nfiles, size))
        return sitedict

    def datasetSummary_json2(self, dbsInst, dlist):
        """
          should query dataset summary view, until it exists, we will do several queries
          to retrieve summary info about dataset
        """
        clist = where_cond(dlist, 'dataset')
        query1 = 'find dataset, count(site) where '
        query2 = 'find dataset, dataset.createdate, dataset.createby, sum(block.size), sum(block.numfiles), sum(block.numevents), count(block) where '
        rdict  = {}
        for cond in clist:
            newquery1 = query1 + cond
            newquery2 = query2 + cond
            res1 = self.queryDBS(dbsInst, newquery1)
            res2 = self.queryDBS(dbsInst, newquery2)
            sitesdict = {}
            for dataset, nsites in res1:
                sitesdict[dataset] = nsites
            for item in res2:
                dataset, pdate, cby, size, files, evts, blks = item
                nsites = 0
                if  sitesdict.has_key(dataset):
                    nsites = sitesdict[dataset]
                if  rdict.has_key(dataset):
                    pdate, cby, totsize, totblks, totfiles, totevts, nsites = rdict[dataset]
                    totsize  += long(size)
                    totfiles += long(files)
                    totevts  += long(evts)
                    totblks  += long(blks)
                    rdict[dataset] = \
                        [pdate, cby, totsize, totblks, totfiles, totevts, nsites]
                else:
                    rdict[dataset] = \
                        [pdate, cby, long(size), long(blks), long(files), long(evts), nsites]

#            for item in res2:
#                dataset, pdate, cby, totsize, totfiles, totevts, totblks = item
#                nsites = 0
#                if  sitesdict.has_key(dataset):
#                    nsites = sitesdict[dataset]
#                rdict[dataset] = [pdate, cby, totsize, totblks, totfiles, totevts] + [nsites]
        result = [[dataset]+rdict[dataset] for dataset in dlist]
        return result

    def datasetSummary_json(self, dbsInst, dataset):
        """
          should query dataset summary view, until it exists, we will do several queries
          to retrieve summary info about dataset
        """
        # TODO: once DBS-QL will fix empty site, I can remove one query and add site look-up
        # at second query.
        query = 'find site where dataset = %s' % dataset
        sites = self.queryDBS(dbsInst, query)
        query = 'find dataset.createdate, dataset.createby, sum(block.size), sum(block.numfiles), sum(block.numevents), count(block) where dataset = %s' % dataset
        res   = self.queryDBS(dbsInst, query)
        pdate, cby, totsize, totfiles, totevts, totblks = res[0]
        pdate = convertDBS2DDTime(pdate)
        cby = parseCreatedBy(cby)
        results = {'created':pdate,'creator':cby,
                   'blocks':totblks,
                   'size':totsize,
                   'files':totfiles,
                   'evts':totevts,
                   'sites':sites,
                   'dataset':dataset}
        return results

    def datasetSummary_viewBlockName(self, dbsInst, dataset):
        """
          should query dataset summary view, until it exists, we will do several queries
          to retrieve summary info about dataset
        """
        query = 'find dataset.createdate, dataset.createby, block, block.size, block.numfiles, block.numevents, site, count(block) where dataset = %s' % dataset
        res   = self.queryDBS(dbsInst, query)
        oDict = {}
        bDict = {}
        pdate = ""
        cby = ""
        for item in res:
            pdate, cby, block, blksize, nfiles, nevts, site, nblks = item
            # convert pdate to GMT format, DBS returns CET
            pdate = convertDBS2DDTime(pdate)
            cby = parseCreatedBy(cby)
            bDict[block] = (long(blksize), long(nfiles), long(nevts))
            val = (pdate, cby, long(nblks), long(blksize), long(nfiles), long(nevts))
            if  oDict.has_key(site):
                pd, cb, nb, bs, nf, ne = oDict[site]
                oDict[site] = (pdate, cby, long(nblks)+nb, long(blksize)+bs, 
                                           long(nfiles)+nf, long(nevts)+ne)
            else:
                oDict[site] = val
        totsize  = 0
        totfiles = 0
        totevts  = 0
        totblks  = 0
        if  not bDict: 
            # DBS-QL returns empty result if one of the field is empty, e.g. site
            # TODO: fix DBS-QL
            query = 'find dataset.createdate, dataset.createby, block.size, block.numfiles, block.numevents, count(block) where dataset = %s' % dataset
            res   = self.queryDBS(dbsInst, query)
            for row in res:
                pdate, cby, blksize, nfiles, nevts, nblks = row
                pdate = convertDBS2DDTime(pdate)
                cby = parseCreatedBy(cby)
                totsize  += long(blksize)
                totfiles += long(nfiles)
                totevts  += long(nevts)
                totblks  += int(nblks)
        else:
            totblks  = len(bDict.keys())
            for blksize, nfiles, nevts in bDict.values():
                totsize  += long(blksize)
                totfiles += long(nfiles)
                totevts  += long(nevts)
        mDict = {'all' : (pdate, cby, totblks, totsize, totfiles, totevts)}
        return oDict, mDict

    def datasetSummary(self, dbsInst, dataset):
        """
          should query dataset summary view, until it exists, we will do several queries
          to retrieve summary info about dataset
        """

        query = 'find dataset.createdate, dataset.createby, count(block), sum(block.size) where dataset = %s' % dataset
        res   = self.queryDBS(dbsInst, query)
        pdate, cby, totblk, totblksize = res[0]
        pdate = convertDBS2DDTime(pdate)
        cby = parseCreatedBy(cby)
        # get total number of files and events for dataset
        query = 'find count(file), sum(file.numevents) where dataset = %s' \
                  % dataset
        res   = self.queryDBS(dbsInst, query)
        totfiles = long(res[0][0])
        totevts  = long(res[0][1])
        
        # now query site by site
        query = 'find site where dataset = %s' % dataset
        res   = self.queryDBS(dbsInst, query)
        oDict = {}
        mDict = {}
        for site in res:
            query = 'find count(block), sum(block.size) where dataset = %s and site = %s' \
                      % (dataset, site)
            res   = self.queryDBS(dbsInst, query)
            nblks, blksize = res[0]
            query = 'find count(file), sum(file.numevents) where dataset = %s and site = %s' \
                      % (dataset, site)
            res   = self.queryDBS(dbsInst, query)
            nfiles, nevts = res[0]
            val = (pdate, cby, long(nblks), long(blksize), long(nfiles), long(nevts))
            oDict[site] = val
            if  long(nblks) == long(totblk) and long(blksize) == long(totblksize):
                mDict[site] = val
        if  not mDict:
            mDict = {'all' : (pdate, cby, long(totblk), long(totblksize), 
                                          long(totfiles), long(totevts)) }
        return oDict, mDict

    def getPhysicsGroups(self, dbsInst):
        query = 'find phygrp where phygrp like *'
        res = self.queryDBS(dbsInst, query)
        res.sort()
        return res

    def getSites(self, dbsInst):
        query = 'find site where site like *'
        res = self.queryDBS(dbsInst, query)
        res.sort()
        return res

    def getDataTiers(self, dbsInst):
        query = 'find tier where tier like *'
        res = self.queryDBS(dbsInst, query)
        res.sort()
        return res

    def getSoftwareReleases(self, dbsInst):
  #      query = 'find release where release like * order by release'
        query = 'find release where release like *'
        res = self.queryDBS(dbsInst, query)
        res.sort()
        return res

    def getPrimaryDSTypes(self, dbsInst):
        query = 'find datatype where datatype like *'
        res = self.queryDBS(dbsInst, query)
        res.sort()
        return res

    def getPrimaryDatasets(self, dbsInst, group="*", tier="*", rel="*", dsType="mc"):
        query = 'find primds where '
        if group.lower()  == "any": group="*"
        if tier.lower()   == "any": tier="*"
        if rel.lower()    == "any": rel="*"
        if dsType.lower() == "any": dsType="*"
        cond = ""
        if  group and group != "*":
            cond = "and phygrp = %s" % group
        if  tier and tier != "*":
            cond = "and tier = %s " % tier
        if  rel and rel != "*":
            cond = "and release = %s " % rel
        if  dsType and dsType != "*":
            cond = "and datatype = %s " % dsType
        if  cond:
            query += cond[4:]
        else:
            query += " primds like *"
        query += " order by primds"
        res = self.queryDBS(dbsInst, query)
        res.sort()
        return res

    def getLFNsFromSite(self, dbsInst, site, datasetPath, run = '*', lfn = '*'):
        query = 'find file where dataset = %s ' % datasetPath
        cond  = ''
        if  site.lower()  == 'any' or site.lower() == 'all':
            site = '*'
        if  site != '*':
            cond += ' and site = %s ' % site
        if  run and run != '*':
            cond += ' and run = %s ' % run
        if  lfn and lfn != '*':
            cond += ' and file = %s ' % file
        if  cond:
            query += cond
        res = self.queryDBS(dbsInst, query)
        return res

#    def getNEvt(self, dbsInst, dlist):
#        query = "find dataset, run, sum(file.numevents) where "
#        clist = []
#        cond = ""
#        counter = 0
#        for dataset, run in dlist:
#            cond += ' or (dataset=%s and run=%s' % (dataset, run)
#            if  counter == climit:
#                clist.append(cond[3:])
#                counter = 0
#                cond = ""
#            counter += 1
#        clist.append(cond[3:])
#        dr_dict = {}
#        for cond in clist:
#            newquery = query+cond
#            res = self.queryDBS(dbsInst, newquery)
#            for row in res:
#                dataset, run , nevts = row
#                dr_dict[(dataset, run)] = nevts
#        return dr_dict
        
    def getRuns(self, dbsInst, dataset, primD="*", primType="*", minRun="*", maxRun="*", fromRow=0, limit=0, count=0, userMode="user"):
        if primD.lower() == "any": primD = "*"
        if primType.lower() == "any": primType = "*"
        if str(minRun).lower() == "any": minRun = "*"
        if str(maxRun).lower() == "any": maxRun = "*"
        if dataset.lower() == "any": dataset = "*"
        if not dataset: dataset = "*"
        cond = ""
        if  dataset and dataset != '*':
            cond += " and dataset = %s" % dataset
        if  primD and primD != "*":
            cond += " and primds = %s" % primD
        if  primType and primType != "*":
            cond += " and datatype = %s" % primType
        if  minRun and minRun != "*":
            cond += " and run >= %s" % minRun
        if  maxRun and maxRun != "*":
            cond += " and run <= %s" % maxRun
        cond += " and dataset.status like VALID*"

        if  count:
#            if  dataset and dataset != '*':
#                query = 'find count(run), min(run), max(run) where dataset = %s' % dataset
#                if  cond:
#                    query += cond
#                res = self.queryDBS(dbsInst, query)[0]
#                return int(res[0]), int(res[1]), int(res[2])
#            elif minRun and maxRun and minRun != '*' and maxRun != '*':
#                query = 'find count(dataset) where run>=%s and run<=%s' % (minRun, maxRun)
#                res = self.queryDBS(dbsInst, query)[0]
#                return nRuns, minRun, maxRun
#            else:
            query = 'find count(run)'
            if  cond:
                query += ' where ' + cond[4:]
            res = self.queryDBS(dbsInst, query)
            counter = int(res[0])
            query = 'find run, dataset '
            if  cond:
                query += ' where ' + cond[4:]
            res = self.queryDBS(dbsInst, query)
            min = 99999999999999
            max = 0
            for run, dataset in res:
                run = int(run)
                if  run > max:
                    max = run
                if  run < min:
                    min = run
            return counter, min, max
                
    
#        query = 'find site where dataset = %s' % dataset
        query = 'find site'
        if  cond:
#            query += cond
            query += ' where ' + cond[4:]
        res = self.queryDBS(dbsInst, query)
        selist = [se for se in res]

#        query  = 'find datatype, dataset, run.number, run.numevents, run.numlss, run.totlumi, run.store, run.starttime, run.endtime, run.createby, run.createdate, run.modby, run.moddate, count(file), sum(file.size), sum(file.numevents) where dataset = %s' % dataset
        query  = 'find datatype, dataset, run.number, run.numevents, run.numlss, run.totlumi, run.store, run.starttime, run.endtime, run.createby, run.createdate, run.modby, run.moddate, count(file), sum(file.size), sum(file.numevents) '
        if  cond:
#            query += cond
            query += ' where ' + cond[4:]
        query += ' order by run.number'
        res = self.queryDBS(dbsInst, query, fromRow, limit)
        oList = []
        for item in res:
            dsType, path, run, nEvts, nLumis, totLumi, store, \
            sRun, eRun, cBy, cDate, mBy, mDate, nFiles, fSize, fNevts = item

            if  not run:
                continue
            run = int(run)

            if  not fSize:
                fSize=0

            cDate = timeGMT(cDate)
            mDate = timeGMT(mDate)
            if  sRun:
                sRun = timeGMT(sRun)
            if  eRun:
                eRun = timeGMT(eRun)
            cBy   = parseCreatedBy(cBy)
            mBy   = parseCreatedBy(mBy)

            nEvts = fNevts

            oList.append( [run,nEvts,nLumis,totLumi,store,sRun,eRun,cBy,cDate,mBy,mDate,dsType,path,selist,fSize,nFiles] )
        runDBInfoDict = {}
        if  userMode != "user":
            try:
                runDBInfoDict=self.getRunSummaryTIF(runs)
            except:
                pass
        oList.sort()
#        oList.reverse()
        return oList, runDBInfoDict

    def getRunSummaryTIF(self,run):
        """ I need to make the following query
              http://cmsmon.cern.ch/cmsdb/servlet/RunSummaryTIF?RUN=8757,8762&DB=cms_pvss_tk&XML=1
        """
        runDBDict={}
        conn = httplib.HTTPConnection("cmsmon.cern.ch")
        if type(run) is types.ListType:
           runUrl=""
           for r in run:
               runUrl+="%s,"%r
           conn.request("GET", "/cmsdb/servlet/RunSummaryTIF?RUN=%s&DB=cms_pvss_tki&XML=1"%runUrl[:-1])
        else:
           conn.request("GET", "/cmsdb/servlet/RunSummaryTIF?RUN=%s&DB=cms_pvss_tki&XML=1"%run)
        r1 = conn.getresponse()
        if int(r1.status)==200:
           data=r1.read()
           elem=ET.fromstring(data)
           for i in elem:
               if i.tag=="query":
                  query_data=i # get query
                  for j in query_data:
                      if  j.tag=="row":
                          run=0
                          runmode=""
                          system=""
                          for k in j.getchildren():
                              if k.tag.lower()=="run":     run=int(k.text)
                              if k.tag.lower()=="runmode": runmode=k.text
                              if k.tag.lower()=="system":  system=k.text
                          if run and not runDBDict.has_key(run):
                             runDBDict[run]=(runmode,system)
        return runDBDict

    def listApplicationConfigsContent(self, dbsInst, appPath, dataset):
        query = 'find release, config.name, config.content, config.version, config.type, config.annotation, config.createdate, config.createby, config.moddate, config.modby where dataset = %s' % dataset
        cond  = ""
        if  appPath and appPath != "*":
            empty, ver, fam, exe = appPath.split("/")
            if ver.lower() == 'any': ver = "*"
            if fam.lower() == 'any': fam = "*"
            if exe.lower() == 'any': exe = "*"
            if  ver and ver != '*':
                cond += " and app.version = %s" % ver
            if  fam and fam != '*':
                cond += " and app.family = %s" % fam
            if  exe and exe != '*':
                cond += " and app.executable = %s" % exe
        if  cond:
            query += cond
        print "listApplicationConfigsContent", query
        result = self.queryDBS(dbsInst, query)
        oList  = []
        for item in result:
            softRel, name, content, ver, psType, ann, cDate, cBy, mDate, mBy=item
            if not name: continue
            content = str(content) # since content is LOB object
            cDate = timeGMT(cDate)
            mDate = timeGMT(mDate)
            cBy = parseCreatedBy(cBy)
            mBy = parseCreatedBy(mBy)
            if  name and \
            not oList.count((softRel, name, content, ver, psType, ann, cDate, cBy, mDate, mBy)):
               oList.append((softRel, name, content, ver, psType, ann, cDate, cBy, mDate, mBy))
        return oList

    def getLFNs(self, dbsInst, blockName, dataset, run="*", lfn="*"):
        query = "find file, file.size, file.status, file.type, file.numevents, file.checksum where "
        cond  = ""
        if  blockName:
            cond  += " and block = %s" % blockName
        if  dataset:
            cond  += " and dataset = %s" % dataset
        if  run and run != "*":
            cond  += " and run = %s" % run
        if  lfn and lfn != "*":
            cond  += " and file = %s" % lfn
        if  cond:
            query += cond[4:] # remove first and
        result = self.queryDBS(dbsInst, query)
        olist = []
        for item in result:
            name, size, status, ftype, nevts, checksum = item
            olist.append((name, size, status, ftype, nevts, checksum))
        return olist

    def getLFN_Lumis(self, dbsInst, lfn, userMode='user'):
        if  userMode != 'user':
            tlist=['LumiSectionNumber','RunNumber','StartEventNumber','EndEventNumber','LumiStartTime','LumiEndTime','CreatedBy','CreationDate','LastModifiedBy','LastModificationDate']
            query = "find lumi, run, lumi.startevnum, lumi.endevnum, lumi.starttime, lumi.endtime, lumi.createby, lumi.createdate, lumi.modby, lumi.moddate"
        else:
            tlist=['LumiSectionNumber','RunNumber','StartEventNumber','EndEventNumber','LumiStartTime','LumiEndTime']
            query = "find lumi, run, lumi.startevnum, lumi.endevnum, lumi.starttime, lumi.endtime"
        query += " where file = %s" % lfn
        result = self.queryDBS(dbsInst, query)
        olist  = []
        for item in result:
            if  userMode != 'user':
                lumi, run, sev, eev, stime, etime, cby , cdate, mBy, mDate = item
                if not name: continue
                cdate = timeGMT(cdate)
                mdate = timeGMT(mdate)
                cby   = parseCreatedBy(cby)
                mby   = parseCreatedBy(mby)
                olist.append((lumi, run, sev, eev, stime, etime, cby , cdate, mby, mdate))
            else:
                lumi, run, sev, eev, stime, etime = item
                olist.append((lumi, run, sev, eev, stime, etime))
        return tlist, olist


    def getLFN_Branches(self, dbsInst, lfn, userMode='user'):
        if  userMode != 'user':
            tlist = ['Name','CreatedBy','CreationDate','LastModifiedBy','LastModificationDate']
            sel   = "branch, branch.createby, branch.createdate, branch.modby, branch.moddate"
        else:
            tlist = ['Name']
            sel   = "branch"
        query  = "find " + sel + " where file = %s" % lfn
        result = self.queryDBS(dbsInst, query)
        olist  = []
        for item in result:
            if  userMode != 'user':
                name, cby , cdate, mBy, mDate = item
                if not name: continue
                cdate = timeGMT(cdate)
                mdate = timeGMT(mdate)
                cby   = parseCreatedBy(cby)
                mby   = parseCreatedBy(mby)
                olist.append((name, cby , cdate, mby, mdate))
            else:
                olist.append(item)
        return tlist, olist

    def getDatasetProvenance(self, dbsInst, dataset):
        query = "find dataset.parent where dataset=%s" % dataset
        res = self.queryDBS(dbsInst, query)
        return res

    def getDatasetChildren(self, dbsInst, dataset):
        # TODO: DBS does not implement yet dataset.child
        query = "find dataset.child where dataset=%s" % dataset
        res = self.queryDBS(dbsInst, query)
        return res

    def getDataDescription(self, dbsInst, dataset):
        query = "find datatype where dataset=%s" % dataset
        res = self.queryDBS(dbsInst, query)
        print "getDataDescription", query, res
        datatype = ""
        if  res and len(res) == 1:
            datatype = res[0]
        else:
            return []
        if  datatype == 'mc':
            query = "find mcdesc.name, mcdesc.def, mcdesc.parent, mcdesc.createdate, mcdesc.createby, mcdesc.moddate, mcdesc.modby where dataset=%s" % dataset
        else:
            query = "find trigdesc.name, trigdesc.createdate, trigdesc.createby, trigdesc.moddate, trigdesc.modby where dataset=%s" % dataset
        print "desc query", query
        res = self.queryDBS(dbsInst, query)
        return res

    def getRunRangeForDataset(self, dbsInst, dataset):
        query = "find run where dataset=%s" % dataset
        res = self.queryDBS(dbsInst, query)
        res.sort()
        rmin = res[0]
        rmax = res[-1]
        return rmin, rmax

    def getLFNs_Runs(self, dbsInst, blockName):
        query = "find file, run where block=%s" % blockName
        res = self.queryDBS(dbsInst, query)
        oDict = {}
        for item in res:
            if not item[0]: continue
            lfn, run  = item
            run = int(run)
            if oDict.has_key(lfn):
               _list  = oDict[lfn]
               _list += [run]
               oDict[lfn] = _list
            else:
               oDict[lfn] = [run]
        return oDict

    def getPrimDetailsForRSS(self, dbsInst, prim="*"):
        if prim.lower() == 'any': prim ="*"
        # TODO: DBS-QL needs to implement primds.annotation
#        query = "find primds.createdate, primds.annotation where primds = %s" % prim
        query = "find primds.createdate where primds = %s" % prim
        res = self.queryDBS(dbsInst, query)
        cdate = ""
        annot = ""
        for item in res:
#            cdate, annot = item
            cdate = item
            cdate = timeGMT(cdate)
        return cdate, annot

    def getProcDSForRss(self, dbsInst, prim="*", rel="*"):
        if prim.lower() == 'any': prim ="*"
        if rel.lower() == 'any': rel ="*"
        query  = "find dataset, block.size, block.numfiles, block.numevents, block.status, block.createdate, trigdesc.name, mcdesc.name, mcdesc.def, mcdesc.parent where primds = %s" % prim
        if  rel and rel != '*':
            query += " and release=%s" % rel
        result = self.queryDBS(dbsInst, query)
        oList  = []
        for item in result:
            if not (item and item[0]): continue
            path, bSize, nFiles, nEvents, status,
            cDate, trigDesc, mcChannelDesc, mcProd, mcDecay=item
            if not path: continue
            cDate=timeGMT(cDate)
            if status:
               status="OPEN"
            else:
               status="CLOSED"
            desc="""
Block size:         %s
Number of files:    %s
Number of events:   %s
Status:             %s
TriggerDescription: %s
MCDescription:      %s
  """%(sizeFormat(bSize),nFiles,nEvents,status,trigDesc,mcChannelDesc)
            elem=(path,desc,cDate)
            if not oList.count(elem): oList.append(elem)
        return oList

    def getRunsForPrimary(self, dbsInst, primD="*", primType="*"):
        if primD.lower() == 'any': primD = '*'
        if primType.lower() == 'any': primType = '*'
        query = "find run"
        cond = ""
        if  primD and primD != '*':
            cond += " and primds = %s" % primD
        if  primType and primType != '*':
            cond += " and datatype = %s" % primType
        if  cond:
            query += ' where '+ cond[4:] # removes first and
        query_min = query.replace("find run", "find min(run)")
        query_max = query.replace("find run", "find max(run)")
        rmin = self.queryDBS(dbsInst, query_min)[0]
        rmax = self.queryDBS(dbsInst, query_max)[0]
#        res = self.queryDBS(dbsInst, query)
#        result = [int(run) for run in res]
#        result.sort()
#        rmin = result[0]
#        rmax = result[-1]
        return rmin, rmax

    def getNRuns(self, dbsInst, minRun, maxRun):
        query = "find run where run>=%s and run<=%s" % (minRun, maxRun)
        return self.dbsmgr.count(dbsInst, query)

    def findDatasetsFromLFN(self, dbsInst, lfn):
        query = "find dataset where file=%s" % (minRun, lfn)
        res = self.queryDBS(dbsInst, query)
        return res

    def getBlockInfoForSite(self, dbsInst, site, fromRow, limit):
        query = "find dataset, block.name, block.size, block.numfiles, block.numevents, block.status, block.createdate, block.createby, block.moddate, block.modby where site=%s" % site
        res = self.queryDBS(dbsInst, query, fromRow, limit)
        aList = []
        for item in res:
            dataset, block, bsize, nfiles, nevts, status, cby, cdate, mby, mdate = item
            cdate = timeGMT(cdate)
            mdate = timeGMT(mdate)
            cby   = parseCreatedBy(cby)
            mby   = parseCreatedBy(mby)
            aDict={'ProcDSName':dataset,'Name':block,'BlockSize':bsize,'NumberOfFiles':nfiles,'NumberOfEvents':nevts,'OpenForWriting':status,'CreatedBy':cby,'CreationDate':cdate,'LastModifiedBy':mby,'LastModificationDate':mdate}
            aList.append(aDict)
        return aList

    def getBlocksFromSite(self, dbsInst, site, datasetPath=None):
        query = "find block.name where site=%s" % site
        if  datasetPath:
            query += " and dataset=%s" % datasetPath
        res = self.queryDBS(dbsInst, query)
        return res

    def getConfigContent(self, dbsInst, id):
        query = "find config.content where config.id=%s" % id
        res = self.queryDBS(dbsInst, query)
        return res

    def getConfigContentByName(self, dbsInst, name, rel=""):
        if  rel.lower() == 'any': rel = '*'
        query = "find config.content where config.name=%s" % name
        if  rel and rel != '*':
            query += " and release=%s" % rel
        res = self.queryDBS(dbsInst, query)
        return res

    def getLFN_Branches(self, dbsInst, lfn, userMode='user'):
        if userMode!='user':
            tList=['Name','CreatedBy','CreationDate','LastModifiedBy','LastModificationDate']
        else:
            tList=['Name']
        query = "find branch.name, branch.createby, branch.createdate, branch.modby, branch.moddate where file=%s" % lfn
        res = self.queryDBS(dbsInst, query)
        oList = []
        for item in res:
            name, cby, cdate, mby, mdate = item
            cdate = timeGMT(cdate)
            mdate = timeGMT(mdate)
            cby   = parseCreatedBy(cby)
            mby   = parseCreatedBy(mby)
            if userMode != 'user':
                oList.append((name, cby, cdate, mby, mdate))
            else:
                oList.append(name)
        return tList, oList

    def getLFN_Lumis(self, dbsInst, lfn, userMode='user'):
        if userMode!='user':
            tList=['LumiSectionNumber','RunNumber','StartEventNumber','EndEventNumber','LumiStartTime','LumiEndTime','CreatedBy','CreationDate','LastModifiedBy','LastModificationDate']
        else:
           tList=['LumiSectionNumber','RunNumber','StartEventNumber','EndEventNumber','LumiStartTime','LumiEndTime']
        query = "find lumi, run, lumi.startevnum, lumi.endevnum, lumi.starttime, lumi.endtime, lumi.createby, lumi.createdate, lumi.modby, lumi.moddate where file=%s" % lfn
        res = self.queryDBS(dbsInst, query)
        oList = []
        for item in res:
            lumi, run, startev, endev, starttime, endtime, cby, cdate, mby, mdate = item
            cdate = timeGMT(cdate)
            mdate = timeGMT(mdate)
            cby   = parseCreatedBy(cby)
            mby   = parseCreatedBy(mby)
            if userMode != 'user':
                oList.append((lumi, run, startev, endev, starttime, endtime, cby, cdate, mby, mdate))
            else:
                oList.append((lumi, run, startev, endev, starttime, endtime))
        return tList, oList

    def getLFNParents(self, dbsInst, lfns):
        query = "find file.parent where file in (%s)" \
            % str(lfns).replace('[','').replace(']','')
        res = self.queryDBS(dbsInst, query)
        return res

    def getLFNsFromRunRanges(self, dbsInst, dataset, runRanges):
        query = "find file where dataset = %s" % dataset
        if  runRanges:
            cond = ""
            for item in runRanges.split(","):
                minR,maxR=item.split("-")
                cond += ' or (run>=%s and run<=%s)' % (minR, maxR)
            query += ' and ' + cond[4:] 
        res = self.queryDBS(dbsInst, query)
        return res

    def getPathSEs(self, dbsInst, run):
        query = "find datatype, dataset, site where run = %s" % run
        res = self.queryDBS(dbsInst, query)
        pDict = {}
        for item in res:
            dsType,path,se=item
            addToDict(pDict,(dsType,path),se)
        return pDict

    def getRunDBInfo(self, dbsInst, runs):
        """ I need to make the following query
              http://cmsmon.cern.ch/cmsdb/servlet/RunSummary?RUN=12024,12222&XML=1
        """
        runInfoDict={}
        runInfoList=[]
        iParams={}
        iParams['XML']=1
        iParams['RUN']=run
        url = "http://cmsmon.cern.ch/cmsdb/servlet/RunSummary"
        if type(run) is types.ListType:
           runUrl=""
           for r in run:
               runUrl+="%s,"%r
           iParams['RUN']=runUrl[:-1]
        http_handler = MyHTTPHandler(timeout = 60) # timeout in seconds
        opener = urllib2.build_opener(http_handler)
        req    = urllib2.Request(url,urllib.urlencode(iParams,doseq=True))
        data   = opener.open(req).read()
        elem   = ET.fromstring(data)
        for i in elem:
            if i.tag=="runInfo":
               query_data=i # get query
               runInfoDict={}
               for j in query_data:
                   if j.tag=="runNumber": run=j.text
                   runInfoDict[j.tag]=j.text
               runInfoList.append((run,runInfoDict))
        return runInfoList


