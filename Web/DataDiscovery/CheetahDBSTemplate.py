#!/usr/bin/env python

"""
List of templates used by DBS data discovery server.
"""
templateTime="""
<p>
<table class="intro">
<caption>Response time:</caption>
<tr><td>DBS</td><td>DLS</td><td>HTML</td><td>Total</td></tr>
<tr>
<td>$dbsTime</td>
<td>$dlsTime</td>
<td>$htmlTime</td>
<td>$totalTime</td>
</tr>
</table>
</p>
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
#set URL=$url.replace('&','&amp;')
<tr><td align="right">URL</td><td>$URL</td></tr>
</table>
</p>
and submitted to the <a href="mailto:vk@mail.NOSPAM.lns.cornell.edu">maintainer</a> 
of this page and to DBS team.
<p></p>
We're sorry for inconvenience and 
please try later.
"""

templateSummary="""
<tr>
<td align="right" class="box_light">$dbsInst</td>
#for key in $sumDict
<td align="right" class="box_light">$sumDict[$key]</td>
#end for
</tr>
"""

templatePrintList="""
<table class="small">
<tr>
<td class="sectionhead_tight">$msg</td>
<td><span id="HiddenTitle" class="hide"></span></td>
</tr>
<tr>
<td>
    <table class="small">
    #for d in $dList
    <tr>
    <td>$d</td>
    </tr>
    ##<tr>
    ##<td><span id="primDatasetDetails" class="hide"></span></td>
    ##</tr>
    #end for
    </table>
</td>
<td><span id="HiddenContent" class="hide"></span></td>
</tr>
</table>
"""

templateProvenance="""
#set idPath=$dataset.replace("/","___")
    <p>
    <table border="0" cellspacing="0" cellpadding="0" width="100%">
    <tr>
    <th align="left">$dataset</th>
    </tr>
    #if not len($parentList)
    <tr>
    <td>
    No parents found
    </td>
    </tr>
    #else
    #for parent in $parentList
    <tr>
    <td align="left">
    <div class="indent">
    <img src="images/down_right_arrow.gif" alt="down_right_arrow" align="left" />
    $parent
    </div>
    </td>
    </tr>
    #end for
    #end if
    </table>
    </p>
"""

templateProvenance_orig="""
#set idPath=$dataset.replace("/","___")
    <p>
    <table border="0" cellspacing="0" cellpadding="0">
    <tr>
    <th align="left">Parent list (<a href="javascript:HideParents('parentGraph');">hide</a>):</th>
    </tr>
    #if not len($parentList)
    <tr>
    <td>
    No parents found
    </td>
    </tr>
    #else
    #for parent in $parentList
    <tr>
    <td align="left">
    $parent
    </td>
    </tr>
    #end for
    #end if
    </table>
    </p>
    <p></p>
"""

templateTop = """
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title>DBS data discovery page</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" type="text/css" href="css/dbs.css" />
<!-- set non-visible display content by default -->
<style type="text/css">div.normalcontent { display:none }</style>
<!-- if JavaScripts enables, turn visiable content on -->
<script type="text/javascript" src="js/setcontent.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/sorttable.js"></script>
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/rico.js"></script>
<script type="text/javascript" src="js/ajax_init.js"></script>
</head>
<body onload="ajaxInit();">

<noscript>
<h1>You have disabled Javascript in your browser. This page requires
Javascript in order to work properly.</h1>
</noscript>

<hr class="dbs" />
<table width="100%">
<tr>
<td align="left" class="td3">
<img src="images/CMSLogo.jpg" alt="CMS logo" />
</td>
<td align="left" valign="center">
<span class="sectionhead_tight">
DBS/DLS DATA DISCOVERY PAGE
</span>
</td>
<td align="right" valign="center">
Home page: <a href="$host/">users</a>
<a href="$host/expert">experts</a>
</td>
</tr>
</table>
<hr class="dbs" />

"""

templateHistory="""
<table width="100%"><tr align="left" valign="top"><td class="td12">$time</td><td class="td1">&#8212;</td><td>$action</td></tr></table>
"""

templateAjaxInit="""
<script type="text/javascript">
function registerAjaxPrimDatasetsCalls() {
    ajaxEngine.registerRequest('getPrimDatasets','getAllPrimaryDatasets');
#for name in $dbsNames
    ajaxEngine.registerAjaxElement('datasets'+'$name');
#end for
}
// put all AJAX registration calls here
registerAjaxPrimaryDatasetsCalls();
//registerAjaxGetDetailsForPrimDatasetCalls();
registerAjaxSummaryCalls();
registerAjaxHistoryCalls();
registerAjaxProvenanceCalls();
registerAjaxObjectCalls();
registerAjaxProvenanceGraphCalls();
registerAjaxAppConfigsCalls();
</script>
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
<!--
<form action="search" method="get">
<form action="javascript:registerAjaxSearchCalls();ajaxSearch();" method="get">
-->
<form action="javascript:ajaxSearch();" method="get">
<p>
The search is case insensitive and the following special symbols are supported:
<span class="box">'(', ')', 'and', 'or' and 'not'</span>.
</p>
<p>
You may use boolean expressions, e.g.,
<span class="box">( word1 or (word3 and word4) and not word2 )</span>
</p>
<p>
Any keywords:
<input type="text" name="keywords" size="60" id="keywordSelector" />
<input type="submit" value="Search" id="submit-button" onclick="javascript:showWaitingMessage();" />
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
// siteDict = { DBSInst: { siteDict={ site:null } } }, } }
// it's going to be inserted here by AJAX call
siteDict=$siteDict
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

<!--
<form action="getBlocksFromSite" method="get">
-->
<form action="javascript:ajaxSiteSearch()" method="get">
<table>
<tr>
<td>Choose DBS instance</td>
<td>
<select name="dbsInst" onchange="updateSites(this)" id="form2_dbsSelector">
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
<input type="submit" value="Search" id="form2_submit-button" onclick="javascript:showWaitingMessage()"/>
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
    <a href="javascript:popUp('$host/getLFNlist?dbsInst=$dbsInst&amp;blockName=$bName',1000)">$name</a>
    </td>
    <td align="center">
    <a href="javascript:popUp('$host/getLFN_cfg?dbsInst=$dbsInst&amp;blockName=$bName',1000)">cff</a>,
    <a href="javascript:popUp('$host/getLFN_txt?dbsInst=$dbsInst&amp;blockName=$bName',1000)">plain</a>
    </td>
</tr>
#end for
</table>
"""

templateDataFromSelection="""
<p><b>
Upon your search:
<em>$keywords</em>
</b>
</p>

<!--
<a href="javascript:ajaxGetDataFromSelection()">Test ajaxGetDataFromSelection</a>
<b>
Please make your selection from table below and <a href="javascript:ajaxGenParentsGraph();ajaxGetDataFromSelection();showWaitingMessage()">process your request</a>
</b>
-->
#if len($oList)
<form action="javascript:ajaxGetDataFromSelection();" method="get">
<!--
<form action="getDataFromSelection" method="get">
-->
#if len($oList)>15
<b>
Please make your selection from table below and <a href="javascript:ajaxGetDataFromSelection();showWaitingMessage()">process your request</a>
</b>
<!--
<b>
Please make your selection from table below and <a href="javascript:showWaitingMessage();ajaxGetDataFromSelection()">process your request</a>
</b>
Please make your selection and submit for further processing:
</b>
<input type="submit" value="Find" id="submit-button2a" onclick="javascript:showWaitingMessage();ajaxGetDataFromSelection();" />
-->
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
#set dbsId=$dbsInst.replace('/','_')
#set prim=$item[1]
#set tier=$item[2]
#set ver =$item[3]
#set fam =$item[4]
#set exe =$item[5]
<tr>
<td><input type="checkbox" value="${dbsInst}___${prim}___${tier}___${ver}___${fam}___${exe}" name="userSelection" /></td>
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
<!--
<b>
Please make your selection and submit for further processing:
<input type="submit" value="Find" id="submit-button2" onclick="javascript:showWaitingMessage();ajaxGetDataFromSelection();" />
</b>
-->
<b>
Please make your selection from table above and <a href="javascript:ajaxGenParentsGraph();ajaxGetDataFromSelection();showWaitingMessage();">process your request</a>
</b>
</form>
#else
#if not $firstSearch
No match found for your request, please refine your search.
#end if
##
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
// navDict = { DBSInst: { dbs:appDict={ app:primDict={ primD:tierDict={ tier:null } } }, } }
// it's going to be inserted here by AJAX call
####var navDict=$dict;
var appObj  = null;
var primObj = null;
var tierObj = null;
var first   = null;
#if $firstDBS
  var _dbs  = "$firstDBS";
#else
  var _dbs  = null;
#end if
#if $firstApp
  var _app  = "$firstApp";
#else
  var _app  = null;
#end if
#if $firstPrim
  var _prim = "$firstPrim";
#else
  var _prim = null;
#end if
#if $firstTier
  var _tier = "$firstTier";
#else
  var _tier = null;
#end if
</script>
<script type="text/javascript" src="js/updates.js"></script>
"""

templateJSForm="""
<table class="lfn">
<tr valign="top">

#if $msg
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
#end if

<td valign="top">

<form action="javascript:ajaxGetData();ajaxGenParentsGraph();ajaxGenAppConfigs();" method="get">
<!-- menu table -->
#if $userMode
<div>
<input type="hidden" name="dbsInst" value="$dbsList[0]" id="dbsSelector" />
</div>
<table class="small" cellspacing="5">
#else 
<table class="small" cellspacing="5">
<tr valign="top">
<td align="right">&nbsp;<b>DBS instances</b>
</td>
<td>
<select name="dbsInst" onchange="updateLayer0(this)" id="dbsSelector">
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
<td align="right"><b>Tier sites</b>
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
<td align="right"><b>Application</b>
</td>
<td>
<div id="appHolder"></div>
</td></tr>

<tr valign="top">
<td align="right"><b>Primary dataset</b>
</td>
<td>
<div id="primHolder"></div>
</td></tr>

<tr valign="top">
<td align="right"><b>Data tier</b>
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
#*
<script type="text/javascript">
  var dbs = document.getElementById("dbsSelector")
#if $userMode
  updateLayer(dbs)
#else
  updateLayer0(dbs)
#end if
</script>
*#
<input type="submit" value="Find" onclick="javascript:showResMenu('results');showWaitingMessage();" />
</td></tr>
</table>
<!-- end of menu table -->

</form>


</td>
</tr>

</table>
<!-- end of outer most table -->

"""

templateLFN = """
Block name: <b>$blockName</b>
<!-- Main table -->
<table class="lfn" border="1">
<tr>

<td bgcolor="#CCCCFF">&nbsp; status &nbsp;</td>
<td bgcolor="#CCCCFF">&nbsp; type &nbsp;</td>
<td bgcolor="#CCCCFF">&nbsp; size &nbsp;</td>
<td bgcolor="#CCCCFF">&nbsp; name &nbsp;</td>
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
</td>
<td>$site</td>
</tr>
<tr>
<td align="right"><b>Application:</b>
</td>
<td>$app</td>
</tr>
<tr>
<td align="right"><b>Primary dataset:</b>
</td>
<td>$primD</td>
</tr>
<tr>
<td align="right"><b>Data tier:</b>
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
<a href="javascript:popUp('$host/showProcDatasets?dbsInst=$dbsInst&amp;site=$site&amp;app=$app&amp;primD=$primD&amp;tier=$tier')">
view</a>):
</p>
"""

templateLFB = """
<hr class="dbs" />
#from DBSUtil import sizeFormat, colorSizeHTMLFormat
#set tot=len($blockDict.keys())
#set idPath=$path.replace("/","___")
<div>
<a href="javascript:showResMenu('parents')">$path</a>
<!--
<a href="javascript:showLoadingMessage('parentGraph');registerAjaxProvenanceCalls();getProvenance('$idPath')">$path</a>
-->
</div>
<div id="$idPath"></div>

<p>
contains $nEvents events, $totFiles files, $totSize. 
</p>
<p>
<span id="parentGraph"></span>
</p>
##
#if $nEvents
##
#set tableId="table_"+str($tid)
<table>
<tr>
<td>Show:</td>
<td id="td_Blocks" name="td_Blocks" class="td_plain">
<a href="javascript:HideSumInfo('$tableId');ShowBlockInfo('$tableId')">
Blocks
</a>
</td>
###<td><br></br></td>
<td id="td_Summary" name="td_Summary" class="td_plain">
<a href="javascript:HideBlockInfo('$tableId');ShowSumInfo('$tableId');">
Summary
</a>
</td>
###<td><br></br></td>
<td id="td_Both" name="td_Both" class="td_plain">
<a href="javascript:ShowSumInfo('$tableId');ShowBlockInfo('$tableId');underlineLink('Both')">
Both
</a>
</td>
</tr>
</table>
<!-- Main table -->
<table id="$tableId" class="sortable" cellspacing="0" cellpadding="0" border="1">
  <tr valign="top" align="center" id="tr$tableId" name="tr$tableId" class="sortable_gray">
     <th>row</th>
     <th>Location</th>
     <th>Events</th>
     <th>Files</th>
     <th name="blockInfo" id="blockInfo" class="hide">
     status
     </th>
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
     <td align="center" name="blockInfo" id="blockInfo" class="hide">
     </td>
     <td align="right"><div class="dbs_cell">$colorSizeHTMLFormat($siteTotSize)</div></td>
     <td align="center" name="blockInfo" id="blockInfo" class="hide">
     <a href="javascript:popUp('$host/getLFNsForSite?dbsInst=$dbsInst&amp;site=$site',1000)">All</a>
     </td>
     <td align="center" name="blockInfo" id="blockInfo" class="hide">
     <a href="javascript:popUp('$host/getBlocksForSite?site=$site',1000)">All</a>
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
#set escName = $name.replace('#','\#')
#set htmlName = $name.replace('#','&#35;')
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
     #if $bStatus!="OPEN"
     <td align="center" class="dbs_cell_r"><div class="dbs_cell_r">$bStatus</div></td>
     #else
     <td align="center"><div class="dbs_cell">$bStatus</div></td>
     #end if
     <td align="right"><div class="dbs_cell">$colorSizeHTMLFormat($size)</div></td>
     <td align="center"><div class="dbs_cell">
     <a href="javascript:popUp('$host/getLFN_cfg?dbsInst=$dbsInst&amp;blockName=$bName&amp;dataset=$path',1000)" alt="cff format">cff</a>, 
     <a href="javascript:popUp('$host/getLFN_txt?dbsInst=$dbsInst&amp;blockName=$bName&amp;dataset=$path',900)" alt="file list format">plain</a>
     </div>
     </td>
     <td align="left">
     <div class="dbs_cell">
     <a href="javascript:popUp('$host/getLFNlist?dbsInst=$dbsInst&amp;blockName=$bName&amp;dataset=$path',1000)">$name</a>
     </div>
     </td>
  </tr>
#end for
#end for
</table>
<!-- End of Main table -->
<br />
##
#end if
##
#*
#if not $last
<hr class="dbs" />
#end if
*#
"""

templateBottom="""
<hr class="dbs" />
<table id="results_menu" class="hide" cellspacing="1" width="70%">
<tr>
<td class="td_menu_white_box" align="center" id="_results"><a href="javascript:showResMenu('results')">Results</a></td>
<td class="td_menu_gray_box" align="center" id="_parents"><a href="javascript:showResMenu('parents')">Parents</a></td>
<td class="td_menu_gray_box" align="center" id="_appConfigs"><a href="javascript:showResMenu('appConfigs')">App configs</a></td>
<td class="td_menu_gray_box" align="center" id="_validation"><a href="javascript:showResMenu('validation')">Validation</a></td>
<td class="td_menu_gray_box" align="center" id="_parameterSet"><a href="javascript:showResMenu('parameterSet')">Parameter Set</a></td>
<td class="td_menu_gray_box" align="center" id="_releaseSpec"><a href="javascript:showResMenu('releaseSpec')">Release Specs</a></td>
</tr>
</table>
<br />
<span id="results" class="show_inline"></span>
<span id="parents" class="hide"><br /></span>
<span id="appConfigs" class="hide"><br /></span>
<span id="validation" class="hide"><br />... We plan to add some information about found data, e.g. plots, etc. This should be part of validation ...</span>
<span id="parameterSet" class="hide"><br />... We plan to introduce indexing system and lookup there parameter sets for found dataset ...</span>
<span id="releaseSpec" class="hide"><br />
... Once data been choosen by user we may add a link to release description which has been used to produce this data...
</span>
<hr id="results_hr" class="hide" />
<table>
<tr align="left">
<td>
<em class="small">
CMS data discovery. Author: <a href="mailto:vk@mail.NOSPAM.lns.cornell.edu">Valentin Kuznetsov</a>.
<br />
This page was generated at: $localtime 
</em>
</td>
</tr>
<tr>
</tr>
</table>


</body>
</html>
"""

templateFrontPage="""
#if $frontPage
<!--
<table width="100%">
<tr>
<td>
<div class="sectionhead_tight">WELCOME TO DBS DATA DISCOVERY PAGE</div>
</td>
<td align="right">
Home page: <a href="$host/">users</a>
<a href="$host/expert">experts</a>
</td>
</tr>
</table>
<hr class="dbs" />
-->
#end if

<table width="100%">
<tr valign="top">
<!-- menu -->
<td class="menu_td_gray">
<table width="100%">
<tr>
<td class="td_gray_box" id="Navigator_Menu"><a href="javascript:showMenu('Navigator')">Navigator</a></td>
</tr>
<tr>
<td class="td_gray_box" id="Search_Menu"><a href="javascript:showMenu('Search')">Keyword search</a></td>
</tr>
#if not $userMode
<tr>
<td class="td_gray_box" id="Site_Menu"><a href="javascript:showMenu('Site')">Site</a></td>
</tr>
<tr><td><br /></td></tr>
<tr>
<!--
<td class="td_gray_box" id="Datasets_Menu"><a href="javascript:showMenu('Datasets');registerAjaxPrimDatasetsCalls();getPrimDatasets();">Datasets</a>
-->
<td class="td_gray_box" id="DBSinfo_Menu"><a href="javascript:showMenu('DBSinfo');getDbsInfo('MCGlobal/Writer',$dbsShortNames);">DBS info</a>
  <table id="dbsInst_table" class="hide">
#for name in $dbsShortNames
#set fullName=$name+"/Writer"
#if $name=="Dev_fanfani"
#set fullName="Dev/fanfani"
#end if
      <tr><td>&\#187;</td><td id="dbsInst_$name"><a href="javascript:getDbsInfo('$fullName',$dbsShortNames);">$fullName</a></td></tr>
#end for
  </table>       
</td>
</tr>

<tr>
<td class="td_gray_box" id="Summary_Menu">
<a href="javascript:showMenu('Summary');registerAjaxSummaryCalls();getSummary();">Summary</a>
</td>
</tr>
#end if
<tr><td><br /></td></tr>
<tr>
<td class="td_gray_box" id="History_Menu"><a href="javascript:showMenu('History')">History</a></td>
</tr>
<tr>
<td class="td_gray_box" id="Help_Menu"><a href="javascript:showMenu('Help')">Help</a></td>
</tr>
<tr><td><br /></td></tr>
<tr>
<td class="td_gray_box" id="Hide_Menu"><a href="javascript:HidePanel('$host')">Hide panel</a></td>
</tr>
<!--
<tr>
<td><span id="time">Test timing</span></td>
</tr>
-->
</table>
</td>

<!-- menu content, accordion -->
<td valign="top">
<div id="NavigatorDiv" class="hide">
   <div id="navigationPanel1">
     <div id="navigationHeader1" class="accordionTabTitleBar">
       <span class="menu_title">
       Navigator menu
       </span>
      </div>
      <div id="navigationContent1">
      $navigatorForm
      <div id="navigatorDict"></div>
      </div>
   </div>
   <div id="navigationPanel2">
     <div id="navigationHeader2" class="accordionTabTitleBar">
       <span class="menu_title">
       DBS glossary
       </span>
      </div>
      <div id="navigationContent2">
      $glossary
      </div>
   </div>
</div>
<div id="SearchDiv" class="hide">
   <div id="searchPanel1">
     <div id="searchHeader1" class="accordionTabTitleBar">
       <span class="menu_title">
       Data keyword search
       </span>
      </div>
      <div id="searchContent1">
      $searchForm
      </div>
   </div>

   <div id="searchPanel2">
     <div id="searchHeader2" class="accordionTabTitleBar">
       <span class="menu_title">
       Advanced search
       </span>
      </div>
      <div id="searchContent2">
      Some topics I would like to cover in advance search are:
      <ul>
      <li>Search for details for given processed dataset</li>
      <li>search where particular LFN exists</li>
      </ul>
       ... To be implemented soon ...
       <br />
       We plan to extend search capabilites and allow people specify parameter set search keywords,
       introduce pattern search keywords, e.g. app:CMSSW for concrete search in application, etc.
       Your feedback are very appreciated.
      </div>
   </div>
</div>
<div id="SiteDiv" class="hide">
   <div id="sitePanel1">
     <div id="siteHeader1" class="accordionTabTitleBar">
       <span class="menu_title">
       Site search
       </span>
     </div>
     <div id="siteContent1">
      $siteForm
      <div id="siteMenuDict"></div>
     </div>
   </div>

   <div id="sitePanel2">
     <div id="siteHeader2" class="accordionTabTitleBar">
       <span class="menu_title">
       Description
       </span>
     </div>
     <div id="siteContent2">
     The site search is mostly dedicated for site admins. Here we a re mostly interesting in
     information which belong to given site, but not in detailes of data stored there. For last one
     you need to use either
     <a href="javascript:showMenu('Navigator')">Navigator</a> or 
     <a href="javascript:showMenu('Search')">Keyword search</a>.
     </div>
   </div>
</div>


#*
<div id="DatasetsDiv" class="hide"> 
$dbsContent
</div>
*#
###### Replacement for accordion datasets
#set menuArr=['dbs_prim','dbs_proc','dbs_apps']
<div id="DBSinfoDiv" class="hide"> 
<table id="dbs_info" class="hide" cellspacing="1" width="50%">
<tr>
<td class="td_menu_white_box" align="center" id="_dbs_prim"><a href="javascript:showResMenu('dbs_prim',$menuArr)">Primary datasets</a></td>
<td class="td_menu_gray_box" align="center" id="_dbs_proc"><a href="javascript:showResMenu('dbs_proc',$menuArr)">Processed datasets</a></td>
<td class="td_menu_gray_box" align="center" id="_dbs_apps"><a href="javascript:showResMenu('dbs_apps',$menuArr)">Applications</a></td>
</tr>
</table>
<span id="dbs_prim" class="hide"><br /></span>
<span id="dbs_proc" class="hide"><br /></span>
<span id="dbs_apps" class="hide"><br /></span>
</div>
########################

<div id="SummaryDiv" class="hide">
   <div id="summaryPanel1">
     <div id="summaryHeader1" class="accordionTabTitleBar">
       <span class="menu_title">
       DBS summary page
       </span>
      </div>
      <div id="summaryContent1">
      <!--
      <div id="summary">Please wait while we retrieve this information</div>
      -->
      <div id="summary"><script type="text/javascript">showLoadingMessage("summary");</script></div>
      </div>
   </div>
</div>

<!--
<div id="ReleasesDiv" class="hide">
   <div id="releasesPanel1">
     <div id="releasesHeader1" class="accordionTabTitleBar">
       <span class="menu_title">
       Release specifications
       </span>
      </div>
      <div id="releasesContent1">
      <span id="releaseSpec"></span>
      </div>
   </div>
</div>
-->

<div id="HistoryDiv" class="hide">
   <div id="historyPanel1">
     <div id="historyHeader1" class="accordionTabTitleBar">
       <span class="menu_title">
       Command history
       </span>
      </div>
      <div id="historyContent1">
      <span id="userHistory"></span>
      </div>
   </div>
</div>

<div id="HelpDiv" class="hide">
   <div id="helpPanel1">
     <div id="helpHeader1" class="accordionTabTitleBar">
       <span class="menu_title">
       Introduction
       </span>
     </div>
     <div id="helpContent1">
<div class="sectionhead">DATA DISCOVERY PAGE</div>
<p>
The purpose of this page to help you navigate through CMS data in
Data Bookeeping System (DBS).
</p>
<p>
At the moment, we provide
#if $userMode
two
#else
three
#end if
orthogonal search methods to discovery your favorite data.
</p>
<ul>
<li>Navigator is a menu driven method, where navigator menu guide you in available data hierarchy.
To use this method please choose 
<a href="javascript:showMenu('Navigator')">Navigator</a> menu on a left.
</li>
<li>Search is a keyword search method, e.g. you provide a set of keywords, for instance
CMSSW Higgs, and we look up your data. To use this method, please choose
<a href="javascript:showMenu('Search')">Keyword search</a> menu
on a left.
</li>
#if not $userMode
<li>Site is a site driven method, where you look for data by choosing specific site.
To use this method please choose <a href="javascript:showMenu('Site')">Site</a> menu on a left.
</li>
#end if
</ul>
<p>
The search results are usually presented in a form of sortable tables, where you can
move your mouse over the column name and click on it to sort entries.
</p>
<p>
All terms used on discovery page are defined in DBS glossary.
</p>
     </div>
   </div>

   <div id="helpPanel2">
     <div id="helpHeader2" class="accordionTabTitleBar">
       <span class="menu_title">
       DBS glossary
       </span>
     </div>
     <div id="helpContent2">
     $glossary
     </div>
   </div>
   <div id="helpPanel3">
     <div id="helpHeader3" class="accordionTabTitleBar">
       <span class="menu_title">
       DBS resources
       </span>
     </div>
     <div id="helpContent3">
          <table>
              <tr><td>&\#187;</td>
              <td><a href="https://twiki.cern.ch/twiki/bin/view/CMS/WebHome">CMS Home</a>
              your resource wizard in CMS land.
              </td>
              </tr>
              <tr><td>&\#187;</td>
              <td><a href="https://twiki.cern.ch/twiki/bin/view/CMS/DBS-TDR">DBS Home</a>
              provides full description of Data Bookeeping System (DBS) system.
              </td>
              </tr>
              <tr><td>&\#187;</td>
              <td><a href="https://https://twiki.cern.ch/twiki/bin/view/CMS/DBS-TDR">DBS glossary</a>
              provides a short term definitions used on DBS discovery page.
              </td>
              </tr>
              <tr><td>&\#187;</td>
              <td>DBS specific discovery
              <a href="http://cmsdoc.cern.ch/~sekhri/Html/mc.htm">page</a>
              The purpose of this page to provide a complete detailed information about
              data stored in DBS. Please keep in mind that format is mostly for real experts.
              </td>
              </tr>
              <tr><td>&\#187;</td>
              <td><a href="https://twiki.cern.ch/twiki/bin/view/CMS/DLS">DLS Home</a>
              provides full description of Data Location Service (DLS) system.
              </td>
              </tr>
              <tr><td>&\#187;</td>
              <td><a href="$host/Documentation/index.html">API doc</a>
              describes current API of DBS discovery page
              </td>
              </tr>
              <tr><td>&\#187;</td>
              <td><a href="$host/TODO.html">TODO</a>
              is my current list of task.
              </td>
              </tr>
          </table>
     </div>
   </div>
   <div id="helpPanel4">
     <div id="helpHeader4" class="accordionTabTitleBar">
       <span class="menu_title">
       Feedback form
       </span>
     </div>
     <div id="helpContent4">
         <p></p>
         <form action="sendFeedback" method="post">
         <p>
         Your Email:
         <input type="text" name="userEmail" />
         <br />
         Feedback form:
         <br />
         <textarea rows="5" cols="100" name="feedbackText">Put your text here</textarea>
         <br />
         <input type="submit" value="Submit" id="submit-button-form"/>
         </p>
         </form>
     </div>
   </div>
   <div id="helpPanel5">
     <div id="helpHeader5" class="accordionTabTitleBar">
       <span class="menu_title">
       Site resources
       </span>
     </div>
     <div id="helpContent5">
       <table>
         <tr><td>&\#187;</td>
         <td>The sort capabilities in tables provided by <a href="http://www.kryogenix.org/code/browser/sorttable/">sortable</a> package. I extended this package and include some additions (highlihting, new sort functions, etc.)</td>
         </tr>
         <tr><td>&\#187;</td>
         <td>Ajax functionality provided by <a href="http://www.openrico.org">Rico</a> framework</td>
         </tr>
         <tr><td>&\#187;</td>
         <td>The Rico core itself based on <a href="http://prototype.conio.net/">prototype</a> framework.</td>
         </tr>
         <tr><td>&\#187;</td>
         <td>The entire service is running under <a href="http://www.cherrypy.org/">CherryPy</a> framework</td>
         </tr>
         <tr><td>&\#187;</td>
         <td>It would be impossible to accomplish this task without using 
         <a href="http://www.cheetahtemplate.org">Cheetah</a> template framework</td>
         </tr>
         <tr><td>&\#187;</td>
         <td>Some of my work inspired by <a href="http://www.ajaxprojects.com/">Ajax</a> projects</td>
         </tr>
       </table>
     </div>
   </div>
#if not $userMode
   <div id="helpPanel6">
     <div id="helpHeader6" class="accordionTabTitleBar">
       <span class="menu_title">
       Method description
       </span>
     </div>
     <div id="helpContent6">
      At the moment all all menus are generated up-front and I use CSS tricks to hide/highlight
      part of the document. The menus entries are generated every 5 minutes for DBS and once an hour 
      for DLS information. I use AJAX technology in some part of DBS discovery page.
     </div>
   </div>
#end if
</div>
</td>

</tr>
</table>
<script type="text/javascript">
var accordionHeight=200;
new Rico.Accordion( \$('NavigatorDiv'), {panelHeight:accordionHeight} );
new Rico.Accordion( \$('SearchDiv'), {panelHeight:accordionHeight} );
new Rico.Accordion( \$('SiteDiv'), {panelHeight:accordionHeight} );
new Rico.Accordion( \$('SummaryDiv'), {panelHeight:accordionHeight} );
//new Rico.Accordion( \$('DatasetsDiv'), {panelHeight:accordionHeight} );
//new Rico.Accordion( \$('ReleasesDiv'), {panelHeight:accordionHeight} );
new Rico.Accordion( \$('HistoryDiv'), {panelHeight:accordionHeight} );
new Rico.Accordion( \$('HelpDiv'), {panelHeight:accordionHeight} );
#if $frontPage
showMenu('Navigator');
#end if
</script>
"""

templateDivEntries="""
   <div id="${dbs}Panel">
     <div id="${dbs}Header" class="accordionTabTitleBar">
       <span class="menu_title">
       $dbs
       </span>
     </div>
     <div id="${dbs}Content">
      $content
     </div>
   </div>
"""

templateHiddenPanel="""
<div id="GlobalPanel">
$panel
</div>
<div id="HiddenPanel"></div>
<--
<script type="text/javascript">HidePanel('$host')</script>
-->
<script type="text/javascript">
#if $view
showMenu('$view')
#end if
</script>
"""

templateVisiblePanel="""
<div id="GlobalPanel">
$panel
</div>
<div id="HiddenPanel"></div>
<!--
<script type="text/javascript">ShowPanel('$host')</script>
-->
<script type="text/javascript">
#if $view
showMenu('$view')
#end if
</script>
"""

templateGlossary="""
<div class="sectionhead">DATA DISCOVERY GLOSSARY PAGE</div>
#*
<b>Application</b> refers to set of software version, e.g. CMSSW_0_8_1,
family type, e.g. Merged, RECO, and program used to produce this data, e.g. cmsRun.
<p></p>
<b>Primary dataset</b> identifies the data origin, e.g.
common MC production criteria or trigger line for real data.
<p></p>
<b>Data tier</b> is a data type, e.g. RAW, RECO, DIGI, etc.
<p></p>
<b>cff</b> is a configuration file fragment
<p></p>
*#
<p>
The naming conventions used on discovery page are discussed
<a href="https://twiki.cern.ch/twiki/bin/view/CMS/DBS-TDR/">DBS roadmap</a> as well as in 
<a href="https://twiki.cern.ch/twiki/bin/view/CMS/CMST0DataManagement">CMST0DataManagement</a>
and
<a href="https://twiki.cern.ch/twiki/bin/view/CMS/ProdForCSA06">ProdForCSA06</a> pages
</p>

<div class="indent">
<table width="80%" class="intro">
<tr valign="top">
<td class="box_gray">
Dataset
</td>
<td class="box_gray">
a set of files representing a coherent sample. Datasets are defined primarily by processing 
history and event selection criteria. 
</td>
</tr>

<tr><td colspan="2"><hr class="dbs" /></td></tr>

<tr valign="top">
<td>
Primary Dataset
</td>
<td>
Data at all levels of processing pertaining to a given trigger or common MC production 
criteria. For data from the experiment, the primary dataset is determined by the HLT event 
classification. For Monte Carlo data, primary datasets comprise data generated with the same 
parameters; the granularity of this classification has yet to be decided. 
</td>
</tr>

<tr><td colspan="2"><hr class="dbs" /></td></tr>

<tr valign="top">
<td>
Processed Dataset
</td>
<td>
A slice of data from a Primary Dataset defined by the processing history applied to 
it. A processed dataset will correspond to a large production of data with a single ma jor software 
release version, but may include multiple minor versions for small bug fixes and also may contain 
the output of multiple processings of some given input data. 
</td>
</tr>

<tr><td colspan="2"><hr class="dbs" /></td></tr>

<tr valign="top">
<td>
Analysis Dataset
</td>
<td>
A subset of a Processed Dataset representing a coherent sample for physics analysis, 
specified (conceptually) by running an analysis query on a Processed Dataset at a particular instant 
of time. An Analysis Dataset must not contain the output of multiple processings of any given input 
data.
</td>
</tr>

<tr><td colspan="2"><hr class="dbs" /></td></tr>

<tr valign="top">
<td> 
Luminosity Section
</td>
<td>
a predefined period of data taking where the instantaneous luminosity can be 
considered constant. Files intended for physics analysis will begin and end on luminosity section 
boundaries.
</td>
</tr>

<tr valign="top">
<td> 
Data Tier
</td>
<td>
A set of ob jects to be grouped togethered in the output files of the processing step producing 
the ob jects. The list of ob jects comprising a Data Tier is determined by the release configuration 
files. Additional ob jects not part of the Data Tier may be included in the same output file. 
</td>
</tr>

<tr><td colspan="2"><hr class="dbs" /></td></tr>

<tr valign="top">
<td>
File Block
</td>
<td>
A slicing of a dataset into chunks of files likely to be accessed together. The File Block is a 
data management packaging unit for the convenience of the data location and transfer services. 
Logical File Name (LFN)-globally unique, site independent file specification suitable for use in job 
configuration. 
</td>
</tr>

<tr><td colspan="2"><hr class="dbs" /></td></tr>

<tr valign="top">
<td>
Physical File Name (PFN)
</td>
<td>
a site-dependent file specification which can be used to access a file at a 
particular site.
</td>
</tr>
</table>

</div>

"""

templateDbsCont="""
#for name in $dbsContList
#set fullName=$name+"/Writer"
#if $name=="Dev_fanfani"
#set fullName="Dev/fanfani"
#end if
   <div id="datasets${name}Panel">
     <div id="datasets${name}Header" class="accordionTabTitleBar">
       <span class="menu_title">
       $fullName
       </span>
     </div>
     <div id="datasets${name}Content">
      <p>
      <!--
      <span id="datasets${name}">Please wait while we retrieve this information</span>
      -->
      <span id="datasets${name}"><script type="text/javascript">showLoadingMessage("datasets${name}");</script></span>
      </p>
     </div>
   </div>
#end for
"""

templateCRAB="""
[CRAB]

jobtype                 = cmssw
scheduler               = glitecoll

[CMSSW]

datasetpath             = /CSA06-105-os-minbias19-0/RECO/CMSSW_1_0_5-RECO_H746ee88eddaa52306cd016b2f689e370
pset                    = robot.cfg
total_number_of_events  = 500000
events_per_job          = 1000
output_file             = FrameworkJobReport.xml

[USER]
return_data             = 1
use_central_bossDB      = 0
use_boss_rt             = 0

[EDG]
rb                      = CERN 
proxy_server            = myproxy.cern.ch 
virtual_organization    = cms
retry_count             = 0
lcg_catalog_type        = lfc
lfc_host                = lfc-cms-test.cern.ch
lfc_home                = /grid/cms
"""

templateAppConfigs="""
<p>
For given application: $appPath we got
<table>
#for config in $configList
#set content=$config['parameterSet']['content']
<tr><td>$content</td></tr>
#end for
</table>
</p>
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

