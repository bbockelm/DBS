#!/usr/bin/env python

"""
List of templates used by DBS data discovery server.
"""

templateERROR="""
The server encountered an unexpected condition which prevented it from fulfilling the request.
<p></p>
#if $msg
$msg
#end if
<p></p>
We captured your request
<p>
<table>
<tr><td align="right">Host</td><td>${host}:${port}</td></tr>
<tr><td align="right">URL</td><td>$url</td></tr>
</table>
</p>
and submitted to the <a href="mailto:vk@mail.lns.cornell.edu">maintainer</a> 
of this page and to DBS team.
<p></p>
We're sorry for inconvenience and 
please try later.
"""

templateSummary="""
<div class="sectionhead_tight">$dbsInst</div>
<table class="small">
#for key in $sumDict
<tr>
<td align="right">$key</td><td>$sumDict[$key]</td>
</tr>
#end for
</table>
"""

templatePrintList="""
<hr class="dbs" />
<div class="sectionhead">$msg</div>
<table class="small">
#for d in $dList
<tr>
<td>$d</td>
</tr>
#end for
</table>
"""

templateTop = """
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title>DBS data discovery page</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" type="text/css" href="css/dbs.css" />
<script type="text/javascript" src="js/sorttable.js"></script>
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/rico.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<!-- set non-visible display content by default -->
<style type="text/css">div.normalcontent { display:none }</style>
<!-- if JavaScripts enables, turn visiable content on -->
<script type="text/javascript" src="js/setcontent.js"></script>
</head>
<body>

<noscript>
<h1>You have disabled Javascript in your browser. This page requires
Javascript.</h1>
</noscript>
<div class="normalcontent">

<table class="small" width="100%">
<tr>
<td align="left">
<a href="https://twiki.cern.ch/twiki/bin/view/CMS/WebHome">CMS Home</a> |
<a href="https://twiki.cern.ch/twiki/bin/view/CMS/DBS-TDR">DBS Home</a> |
<a href="https://https://twiki.cern.ch/twiki/bin/view/CMS/DBS-TDR">DBS glossary</a> |
<a href="http://cmsdoc.cern.ch/~sekhri/Html/mc.htm">More info</a> |
<a href="https://twiki.cern.ch/twiki/bin/view/CMS/DLS">DLS Home</a> |
<a href="$host/Documentation/index.html">API doc</a> |
<a href="$host/TODO.html">TODO</a> |
<a href="mailto:hn-cms-dmDevelopment@cern.ch">Contact</a>
</td>
<td align="right">
<!--
#if $userMode
<a href="$host/expert"><span class="box_red">To Expert page</span></a>
#else
<a href="$host"><span class="box_red">To User page</span></a>
#end if
-->
Home page:
<a href="$host"><span class="box_red">users</span></a>, &nbsp;
<a href="$host/expert"><span class="box_red">experts</span></a>
</td>
</tr>
</table>
<hr class="dbs" />
"""

templateIntro="""
<div class="sectionhead">DATA DISCOVERY PAGE</div>
<p>
The purpose of this page to help you navigate through CMS data in
Data Bookeeping System (DBS).
</p>
<p>
The search results are usually presented in a form of sortable tables, where you can
move your mouse over the column name and click on it to sort entries.
</p>
<hr class="dbs" />
"""

templateSearchTable="""
<form action="search" method="get">
<p>
Please specify any keywords for your search, e.g. 
<span class="box">CMSSW Higgs</span>
in provided text box.
<!--
<span class="box_red">
Please note, this part is still under development.
</span>
-->
</p>
<p>
Any keywords:
<input type="text" name="keywords"/>
<input type="submit" value="Search" id="submit-button"/>
</p>
</form>
"""

templateLine_OR="""
<table width="100%">
<tr align="center">
<td class="td34"><hr class="dbs" /></td>
<td class="td5"><span class="box">OR</span></td>
<td><hr class="dbs" /></td>
</tr>
</table>
"""

templateSiteForm="""
<script type="text/javascript">
obj2=$siteDict
var siteObj = null
var first   = null
#if $firstDBS
  var _dbs  = "$firstDBS"
#else
  var _dbs  = null
#end if
#if $firstSite
  var _site = "$firstSite"
#else
  var _site = null
#end if
</script>
<script type="text/javascript" src="js/updates.js"></script>

Use this form to show detailed information about particular site.
<p></p>
<span class="box">
NOTE: the DLS queries may take a lot of time, since they go through LFC.
</span>

<form action="getBlocksFromSite" method="get">
<table>
<tr>
<td>Choose DBS instance</td>
<td>
<select name="dbsInst" onChange="updateSites(this)" id="form2_dbsSelector">
#for dbs in $dbsList
#if $dbs==$firstDBS
<option value="$dbs" selected="selected">$dbs</option>
#else
<option value="$dbs">$dbs</option>
#end if
#end for
</select>
</td>
<td>
</td>
</tr>
<tr valign="top">
<td align="right">Please select a site:
</td>
<td>
<div id="form2_siteHolder"></div>
</td>
<td>
<input type="submit" value="Search" id="form2_submit-button"/>
</td>
</tr>
</table>
</form>
<script type="text/javascript">
  var dbs = document.getElementById("form2_dbsSelector")
  updateSites(dbs)
</script>

"""

templateFileBlocksFromSite="""
<p></p>
<table id="siteBlocks" class="sortable">
<tr>
    <th>Block name</th>
    <th align="center">LFN list</th>
</tr>
#for name in $bList
#set bName=$name.replace('#','%23')
<tr>
    <td>
    <a href="$host/getLFNlist?blockName=$bName">$name</a>
    </td>
    <td align="center">
    <a href="$host/getLFN_cfg?blockName=$bName" alt="cff format">cff</a>, &nbsp;
    <a href="$host/getLFN_txt?blockName=$bName" alt="file list format">plain</a>
    </td>
</tr>
#end for
</table>
"""

templateDataFromSelection="""
#if len($oList)
<form action="getDataFromSelection" method="get">
<span class="box">
NOTE:
all columns are sortable, move your mouse over the column name and click on it.
</span>
<p>
#if len($oList)>15
<b>
Please make your selection and submit for further processing:
</b>
<input type="submit" value="Find" id="submit-button2a"/>
#end if
<p><span id="SelectionHandler" name="SelectionHandler"></span></p>
<script type="text/javascript">UnSelectAll()</script>
<table id="search1" class="sortable">
<tr>
<th></th>
#if not $userMode:
<th>DBS instances</th>
#end if
<th>Primary dataset</th>
<th>Data tier</th>
<th>software version</th>
<th>family</th>
<th>executable</th>
</tr>
#for item in $oList
#set dbsInst=$item[0]
#set prim=$item[1]
#set tier=$item[2]
#set ver =$item[3]
#set fam =$item[4]
#set exe =$item[5]
<tr>
<td><input type="checkbox" value=${dbsInst}___${prim}___${tier}___${ver}___${fam}___${exe} name="userSelection"></td>
#if not $userMode:
<td>$dbsInst</td>
#end if
<td>$prim</td>
<td>$tier</td>
<td>$ver</td>
<td>$fam</td>
<td>$exe</td>
</tr>
#end for
</table>
<b>
Please make your selection and submit for further processing:
<input type="submit" value="Find" id="submit-button2"/>
</b>
#else
#if not $firstSearch
No match found for your request, please refine your search.
#end if
</form>
#end if
"""

templateUserHelp="""
<div class="sectionhead">DATA DISCOVERY PAGE</div>
The purpose of this page to help you navigate through CMS data in
Data Bookeeping System (DBS).
<p>
<b>Application</b> refers to set of software version, e.g. CMSSW_0_8_1,
family type, e.g. Merged, RECO, and program used to produce this data, e.g. cmsRun.
</p><p>
<b>Primary dataset</b> identifies the data origin, e.g.
common MC production criteria or trigger line for real data.
</p><p>
<b>Data tier</b> is a data type, e.g. RAW, RECO, DIGI, etc.
</p>
"""

templateExpertHelp="""
<div class="sectionhead">DATA DISCOVERY PAGE</div>
Here you'll find more detailed information about data, including:
<ul>
<li> Summary <a href="$host/summary">page</a>
<li> List of <a href="$host/getDatasets">datasets</a>
<li> List of <a href="TODO.html">runs/lumi-sections</a>
<li> List of <a href="TODO.html">data types</a>
<li> List of <a href="TODO.html">applications</a>
<li> Find <a href="TODO.html">parentage</a>
</ul>
If more information needed please send request to
<a href="mailto:hn-cms-dmDevelopment@cern.ch">DBS group</a>
"""

# templateJS defines three menus:
# DBS instance, Application, PrimTier
# dict1= {A:["AA","AB","AC"], B:["BA","BB","BC"],C:["CA","CB","CC"],D:["DD"]}
# dict2= {AA:["AAA"], AB:["AB"], AC:["AC"], BA:["BA"], BB:["BB"], BC:["BC"], CA:"CA", CB:"CB", CC:"CC", DD:"DD"}
#templateJS=templateTop+"""
templateJS="""
<script type="text/javascript">

// pythonDict = { DBSInst: { dbs:appDict={ app:primDict={ primD:tierDict={ tier:null } } }, } }

obj=$dict
var appObj  = null
var primObj = null
var tierObj = null
var first   = null
#if $firstDBS
  var _dbs  = "$firstDBS"
#else
  var _dbs  = null
#end if
#if $firstApp
  var _app  = "$firstApp"
#else
  var _app  = null
#end if
#if $firstPrim
  var _prim = "$firstPrim"
#else
  var _prim = null
#end if
#if $firstTier
  var _tier = "$firstTier"
#else
  var _tier = null
#end if
</script>
<script type="text/javascript" src="js/updates.js"></script>
"""

templateJSForm="""
<table class="lfn">
<tr valign="top">

<td valign="top" class="td35">
$msg
</td>


<!-- vertical line -->
#if $frontPage
<td align="center">
<table class="box_gray"><tr><td><br></br></td></tr><tr><td><br></br></td></tr><tr><td><br></br></td></tr></table>
<div class="box_white">OR</div>
<table class="box_gray"><tr><td><br></br></td></tr><tr><td><br></br></td></tr><tr><td><br></br></td></tr></table>
#else
<td class="box_gray">
<br></br>
#end if
</td>


<td valign="top">

<form action="getData" method="get">
<!-- menu table -->
#if $userMode ##---- User menu
<div>
<input type="hidden" name="dbsInst" value="$dbsList[0]" id="dbsSelector" />
</div>
<table class="small" cellspacing="5">
#else  ##----- Expert menu
<table class="small" cellspacing="5">
<tr valign="top">
<td align="right">&nbsp;<b>DBS instances</b>
&nbsp;<a href="javascript:popUp('glossary.html')"><span class="box">?</span></a>
</td>
<td>
<select name="dbsInst" onChange="updateLayer0(this)" id="dbsSelector">
#for dbs in $dbsList:
#if $dbs==$firstDBS
<option value="$dbs" selected="selected">$dbs</option>
#else
<option value="$dbs">$dbs</option>
#end if
#end for
</select>
</td></tr>
#end if

<tr valign="top">
<td align="right">&nbsp;<b>Tier sites</b>
<sup><a href="javascript:popUp('glossary.html')"><span class="box">?</span></a></sup>
</td>
<td>
<select name="site" id="siteSelector">
<option value="All" selected="selected">All</option>
#for site in $sList:
<option value="$site">$site</option>
#end for
</select>
</td></tr>

<tr valign="top">
<td align="right">&nbsp;<b>Application</b>
<sup><a href="javascript:popUp('glossary.html')"><span class="box">?</span></a></sup>
</td>
<td>
<div id="appHolder"></div>
</td></tr>

<tr valign="top">
<td align="right">&nbsp;<b>Primary dataset</b>
<sup><a href="javascript:popUp('glossary.html')"><span class="box">?</span></a></sup>
</td>
<td>
<div id="primHolder"></div>
</td></tr>

<tr valign="top">
<td align="right">&nbsp;<b>Data tier</b>
<sup><a href="javascript:popUp('glossary.html')"><span class="box">?</span></a></sup>
</td>
<td>
<div id="tierHolder"></div>
</td></tr>
<tr>
<td></td>
<td></td>
</tr>
<tr>
<td></td>
<td>
<input type="submit" value="Find" />
</td></tr>
</table>
<!-- end of menu table -->

</form>

<script type="text/javascript">
  var dbs = document.getElementById("dbsSelector")
#if $userMode
  updateLayer(dbs)
#else
  updateLayer0(dbs)
#end if
</script>

</td>
</tr>

</table>
<!-- end of outer most table -->

"""

templateLFN = """
Block name: <b>$blockName</b>
<!-- Main table -->
<table class="lfn" border=1>
<!--
<table class="sortable" id="table1">
-->
<tr>

<td bgcolor="#CCCCFF">&nbsp; status &nbsp;</td>
<td bgcolor="#CCCCFF">&nbsp; type &nbsp;</td>
<td bgcolor="#CCCCFF">&nbsp; size &nbsp;</td>
<td bgcolor="#CCCCFF">&nbsp; name &nbsp;</td>
<!--
<th>status</th>
<th>type</th>
<th>size</th>
<th>name</th>
-->
</tr>
#for item in $lfnList:
#set name=item[0]
#set size=item[1]
#set stat=item[2]
#set type=item[3]
<tr>
<td>&nbsp; $stat &nbsp;</td>
<td>&nbsp; $type &nbsp;</td>
<td>&nbsp; $size &nbsp;</td>
<td>&nbsp; $name &nbsp;</td>
</tr>
#end for
</table>
<!-- end of main table -->
"""

templateSnapshot="""
#if $site=="*"
#set site="All"
#end if
#if $tier=="*"
#set tier="All"
#end if
<div class="sectionhead">RESULTS FOR:</div>
<table class="small" bgcolor="#DDDDDD">
#if not $userMode
<tr>
<td>DBS instantes:</td>
<td>$dbsInst</td>
</tr>
#end if
<tr>
<td align="right"><b>Tier site:</b>
<sup><a href="javascript:popUp('glossary.html')"><span class="box">?</span></a></sup>
</td>
<td>$site</td>
</tr>
<tr>
<td align="right"><b>Application:</b>
<sup><a href="javascript:popUp('glossary.html')"><span class="box">?</span></a></sup>
</td>
<td>$app</td>
</tr>
<tr>
<td align="right"><b>Primary dataset:</b>
<sup><a href="javascript:popUp('glossary.html')"><span class="box">?</span></a></sup>
</td>
<td>$primD</td>
</tr>
<tr>
<td align="right"><b>Data tier:</b>
<sup><a href="javascript:popUp('glossary.html')"><span class="box">?</span></a></sup>
</td>
<td>$tier</td>
</tr>
</table>
<br>
"""

templateSeparator = """
<hr class="dbs" />
#if $firstSearch
<span class="box">
NOTE:
all columns are sortable, move your mouse over the column name and click on it.
</span>
#end if
<p></p>
"""

templateProcDatasets="""
<p>
Processed datasets (plain 
<a href="$host/showProcDatasets?dbsInst=$dbsInst&site=$site&app=$app&primD=$primD&tier=$tier">
view</a>):
</p>
"""

templateLFB = """
#from DBSUtil import fmt3
#set tot=len($blockDict.keys())
<div id="procDataset" name="procDataset" class="off"><b>$path</b></div>
contains $nEvents events, $totFiles files, $totSize. 
<!--
<div id="accordionDiv">
    <div id="summaryPanel">
         <div id="summaryHeader">Summary</div>
         <div id="summaryContent">Summary content</div>
    </div>
    <div id="detailPanel">
         <div id="detailHeader">Deatail view</div>
         <div id="detailedContent">Detailed content</div>
    </div>
    <div id="combinedPanel">
         <div id="combinedHeader">Combined view</div>
         <div id="combinedContent">Combined content</div>
    </div>
</div>
<script type="text/javascript">new Rico.Accordion( \$('accordionDiv'), {panelHeight:300} );</script>
-->
<table>
<tr>
<td><span name="BlockInfoText" id="BlockInfoText"></span></td>
<td><br></br></td>
<td><span name="SumInfoText" id="SumInfoText"></span></td>
</tr>
</table>
#set tableId="table_"+str($tid)
<!-- Main table -->
<table id="$tableId" name="$tableId" class="sortable" cellspacing="0" cellpadding="0" border=1>
  <tr valign="top" align="center" id="tr$tableId" name="tr$tableId" class="sortable_gray">
     <th>row</td>
     <th>Location</th>
     <th>Events</th>
     <th>Files</th>
     <th>size</th>
     <th name="blockInfo" id="blockInfo" class="hide">
     LFN list
     </th>
     <th name="blockInfo" id="blockInfo" class="hide">
     Block name
     </th>
  </tr>
## HERE blockDict[blockName]=(nEvt,blockStatus,nFiles,blockSize,hostList)
## HERE   locDict[siteName]=blockList
#set i=0
#set imax=len($locDict.keys())
#for site in $locDict.keys()
#set i+=1
#if $imax<10
#set print_i="%01d"%$i
#elif $imax<100
#set print_i="%02d"%$i
#elif $imax<1000
#set print_i="%03d"%$i
#end if
#set blockList=$locDict[$site]
#set siteTotEvt=0
#set siteTotFiles=0
#set siteTotSize=0
##
## Sum total events,files,size for given site
##
#for bName in $blockList
#set siteTotEvt+=$blockDict[$bName][0]
#set siteTotFiles+=$blockDict[$bName][2]
#set siteTotSize+=$blockDict[$bName][3]
#end for
  <tr valign="top" bgcolor="#FFFADC" name="row_sumInfo" id="row_sumInfo">
     <td>$print_i</td>
     <td><div class="dbs_cell">$site</div></td>
     <td align="right"><div class="dbs_cell">$siteTotEvt</div></td>
     <td align="right"><div class="dbs_cell">$siteTotFiles</div></td>
     <td align="right"><div class="dbs_cell">$fmt3($siteTotSize)</div></td>
     <td align="center" name="blockInfo" id="blockInfo" class="hide">
     <a href="$host/getLFNsForSite?site=$site">All</a>
     </td>
     <td align="center" name="blockInfo" id="blockInfo" class="hide">
     <a href="$host/getBlocksForSite?site=$site">All</a>
     </td>
  </tr>
#set j=0
#set jmax=len($blockList)
#for name in $blockList
#set j+=1
#if $jmax<10
#set print_j="%s_%01d"%($print_i,$j)
#elif $jmax<100
#set print_j="%s_%02d"%($print_i,$j)
#elif $jmax<1000
#set print_j="%s_%03d"%($print_i,$j)
#elif $jmax<10000
#set print_j="%s_%04d"%($print_i,$j)
#end if
#set item    = $blockDict[$name]
#set bName   = $name.replace('#','%23')
#set nEvt    = $item[0]
#set bStatus = $item[1]
#set nFiles  = $item[2]
#set size    = $item[3]
#set hostList= $item[4]
  <tr valign="top" name="row_blockInfo" id="row_blockInfo" bgcolor="#F0F0F0" class="hide">
     <td>$print_j</td>
     <td><div class="dbs_cell">$site</div></td>
     <td align="right"><div class="dbs_cell">$nEvt</div></td>
     <td align="right"><div class="dbs_cell">$nFiles</div></td>
     <td align="right"><div class="dbs_cell">$fmt3($size)</div></td>
     <td align="center"><div class="dbs_cell">
     <a href="$host/getLFN_cfg?blockName=$bName&dataset=$path" alt="cff format">cff</a>, &nbsp;
     <a href="$host/getLFN_txt?blockName=$bName&dataset=$path" alt="file list format">plain</a>
     </div>
     </td>
     <td align="left">
     <div class="dbs_cell">
     <a href="$host/getLFNlist?blockName=$bName&dataset=$path">$name</a>
     </div>
     </td>
  </tr>
#end for
#end for
</table>
<!-- End of Main table -->
<script type="text/javascript">
ShowBlockInfo("$tableId");HideSumInfo("$tableId");</script>
<!--
ShowBlockInfo("$tableId");HideSumInfo("$tableId");MakeSortable("$tableId");</script>
<script type="text/javascript">ShowBlockInfo("$tableId")</script>
<script type="text/javascript">HideSumInfo("$tableId")</script>
<script type="text/javascript">MakeUnSortable("$tableId")</script>
-->
<p></p>
"""

templateBottom="""
<hr class="dbs" />
<table>
<tr align="left">
<!--
<td>
<a href="http://jigsaw.w3.org/css-validator/">
  <img style="border:0;width:88px;height:31px"
       src="http://jigsaw.w3.org/css-validator/images/vcss" 
       alt="Valid CSS!" />
</a>
<a href="http://validator.w3.org/check?uri=referer"><img
        src="http://www.w3.org/Icons/valid-xhtml10"
        alt="Valid XHTML 1.0 Strict" height="31" width="88" /></a>
</td>
-->
<td>
<em class="small">
<!-- Copyright &copy; 2006 CMS. All rights reserved. -->
CMS data discovery. Author: <a href="mailto:vk@mail.lns.cornell.edu">Valentin Kuznetsov</a>.
<br></br>
This page was generated at: $localtime 
</em>
</td>
</tr>
</table>


</div> <!-- end of noscript check -->
</body>
</html>
"""

templateFrontPage="""
<table width="100%">
<tr valign="top">
<!-- menu -->
<td class="td5">
<table class="box_gray" width="100%">
<tr valign="top">
<td><a href="javascript:showMenu('Navigator')">Navigator</a></td>
</tr>
<tr>
<td><a href="javascript:showMenu('Search')">Search</a></td>
</tr>
#if not $userMode
<tr>
<td><a href="javascript:showMenu('Site')">Site</a></td>
</tr>
#end if
</table>
</td>

<!-- menu content, accordion -->
<td>
<div id="Introduction" name="Introduction" class="show">
<div class="sectionhead">DATA DISCOVERY PAGE</div>
<p>
The purpose of this page to help you navigate through CMS data in
Data Bookeeping System (DBS).
</p>
<p>
At the moment, we provide
#if #userMode
two
#else
three
#endif
orthogonal search methods to discovery your favorite data.
<ul>
<li>Navigator is a menu driven method, where navigator menu guide you in available data hierarchy.
To use this method please choose 
<a href="javascript:showMenu('Navigator')">Navigator</a> menu on a left.
<li>Search is a keyword search method, e.g. you provide a set of keywords, for instance
CMSSW Higgs, and we look up your data. To use this method, please choose
<a href="javascript:showMenu('Search')">Search</a> menu
on a left.
#if not $userMode
<li>Site is a site driven method, where you look for data by choosing specific site.
To use this method please choose <a href="javascript:showMenu('Site')">Site</a> menu on a left.
#end if
</ul>
</p>
<p>
The search results are usually presented in a form of sortable tables, where you can
move your mouse over the column name and click on it to sort entries.
</p>
</div>
<div id="NavigatorDiv" name="NavigatorDiv" class="hide">
   <div id="Panel1">
     <div id="Header1">
       <span class="dbs_cell">
       Navigator menu
       </span>
      </div>
      <div id="Content1">
       ... content navigator ...
      </div>
   </div>

   <div id="Panel2">
     <div id="Header2">
       <span class="dbs_cell">
       Method description
       </span>
      </div>
      <div id="Content2">
       ... content text navigator...
      </div>
   </div>
</div>
<div id="SearchDiv" name="SearchDiv" class="hide">
   <div id="Panel1">
     <div id="Header1">
       <span class="dbs_cell">
       Data keyword search
       </span>
      </div>
      <div id="Content1">
       ... content search ...
      </div>
   </div>

   <div id="Panel2">
     <div id="Header2">
       <span class="dbs_cell">
       Advanced search
       </span>
      </div>
      <div id="Content2">
       ... To be implemented soon ...
      </div>
   </div>
</div>
<div id="SiteDiv" name="SiteDiv" class="hide">
   <div id="Panel1">
     <div id="Header1">
       <span class="dbs_cell">
       Site search
       </span>
      </div>
      <div id="Content1">
       ... content site ...
      </div>
   </div>

   <div id="Panel2">
     <div id="Header2">
       <span class="dbs_cell">
       Description
       </span>
      </div>
      <div id="Content2">
       ... content site ...
      </div>
   </div>
</div>
</td>
<!--       -->

</tr>
</table>
<script type="text/javascript">new Rico.Accordion( $('NavigatorDiv'), {panelHeight:300} );</script>
<script type="text/javascript">new Rico.Accordion( $('SearchDiv'), {panelHeight:300} );</script>
<script type="text/javascript">new Rico.Accordion( $('SiteDiv'), {panelHeight:300} );</script>
"""

#
# main
#
if __name__ == "__main__":
   import time
   from Cheetah.Template import Template

   nameSpace = {'title': 'DBS Data Discovery Page', 
                'dbsInstance' : 'DBSIntance', 
                'dlsInstance':'DLSInstance', 
                'dList': ['test1','test2'], 
                'appList' : ['CMSSW','CMSSW_0_9_0'], 'localtime': time.asctime() }
   t = Template(templateDef, searchList=[nameSpace])
   print t

