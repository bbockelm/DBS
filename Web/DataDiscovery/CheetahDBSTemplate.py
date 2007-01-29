#!/usr/bin/env python

"""
List of templates used by DBS data discovery server.
"""
templateTime="""
<hr class="dbs" />
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
#### FIXME, I need to pass here dbsInst
#### <th align="left"><a href="javascript:ajaxGetDataDescription('MCGlobal/Writer','$dataset')">$dataset</a></th>
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

templateTop = """
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title>DBS data discovery page</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
####<link rel="alternate" type="application/rss+xml" title="Data discovery RSS" href="rss" />
<link rel="stylesheet" type="text/css" href="css/dbs.css" />
<!-- set non-visible display content by default -->
<style type="text/css">div.normalcontent { display:none }</style>
<!-- if JavaScripts enables, turn visiable content on -->

<!-- YUI and WEBTOOLS -->
<script type="text/javascript" src="yui/build/yahoo/yahoo.js"></script>
<script type="text/javascript" src="yui/build/dom/dom.js"></script>
<script type="text/javascript" src="yui/build/event/event.js"></script> 
<script type="text/javascript" src="yui/build/container/container_core.js"></script>
<script type="text/javascript" src="WEBTOOLS/Common/js/masthead.js"></script>
<script type="text/javascript" src="WEBTOOLS/Common/js/footer.js"></script>

<!-- DBS and RICO -->
<script type="text/javascript" src="js/userName.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/sorttable.js"></script>
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/rico.js"></script>
<script type="text/javascript" src="js/ajax_init.js"></script>

<!-- TreeView from YUI -->
<script type="text/javascript" src="yui/build/yahoo/yahoo.js" ></script>
<script type="text/javascript" src="yui/build/event/event.js" ></script>
<script type="text/javascript" src="yui/build/treeview/treeview.js" ></script>
<link rel="stylesheet" type="text/css" href="yui/examples/treeview/css/local/tree.css" />

<!--
<link rel="stylesheet" type="text/css" href="css/masthead_add.css" />
-->
<link rel="stylesheet" type="text/css" href="WEBTOOLS/Common/css/dmwt_main.css" />

</head>
####<body onload="setGreeting();ajaxInit();insertMastHead('dbs');insertFooter('dbs');insertSiteMasthead()" id="content">
<body onload="setGreeting();ajaxInit('$dbsGlobal');insertMastHead('dbs');insertFooter('dbs')" id="content">
###<body onload="setGreeting();ajaxInit('$dbsGlobal');" id="content">

<noscript>
<h1 class="box_red">Warning:</h1>
<table width="50%" class="main">
<tr>
<td>
<div class="sectionhead_tight">DBS/DLS discovery page
is AJAX enabled and require that your browser have scripting 
enabled and JavaScript support. Your browser either does not support JavaScript, 
or it has JavaScript support disabled. Please enable JavaScript support or 
use a different browser with JavaScript support to use this page.
</div>
</td>
</tr>
</table>
</noscript>

#import time
<div id="footer">
<div class="bd">

<table cellspacing="0" cellpadding="0" width="100%" id='end' style="margin-top:8px;">
<tr>
<td class="small"align="left">
<em>
CMS data discovery: $time.asctime() 
</em>
</td>
<td class="small" align="right">
<em>
Contact: <a href="mailto:vk@mail.NOSPAM.lns.cornell.edu">Valentin Kuznetsov</a>.
</em>
</td>
</tr>
</table>

</div>
</div>

#*
<hr class="dbs" />
<table width="99%" align="center">
<tr>
<td align="left" class="td33">
<table><tr>
<td align="left">
<img src="images/CMSLogo.jpg" alt="CMS logo" />
</td>
<td align="left" valign="center">
<span class="sectionhead_tight">
DBS/DLS DATA DISCOVERY PAGE
</span>
</td>
</tr></table>
</td>
<td align="center" class="td33">
<span id="Greeting" />
</td>
<td align="right" valign="center" class="td33">
Home page: <a href="$host/">users</a>
<a href="$host/expert">experts</a>
</td>
</tr>
</table>
<hr class="dbs" />
*#

<div id="main" class="hide">
<script type="text/javascript">SetMain()</script>

"""

templateHistory="""
#####<table><tr><td align="left">$date</td><td align="left">$time</td><td align="left">&#8212;</td><td align="left">$action</td></tr></table>
<table><tr><td align="left">$date</td><td align="left">$time</td><td align="left">--</td><td align="left">$action</td></tr></table>
"""

templateAjaxInit="""
#*
<script type="text/javascript">
function registerAjaxPrimDatasetsCalls() {
    ajaxEngine.registerRequest('getPrimDatasets','getAllPrimaryDatasets');
#for name in $dbsNames
    ajaxEngine.registerAjaxElement('datasets'+'$name');
#end for
}
//registerAjaxGetDetailsForPrimDatasetCalls();
// put all AJAX registration calls here
registerAjaxObjectCalls();
registerAjaxPrimaryDatasetsCalls();
registerAjaxSummaryCalls();
registerAjaxHistoryCalls();
registerAjaxProvenanceCalls();
registerAjaxProvenanceGraphCalls();
registerAjaxAppConfigsCalls();
</script>
*#
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

templateKeywords="""
<table>
<tr>
<td>
Keywords
</td>
<td>
Description
</td>
</tr>

<tr>
<td>
run
</td>
<td>
run number
</td>
</tr>

<tr>
<td>
prim
</td>
<td>
Primary dataset name
</td>
</tr>

<tr>
<td>
proc
</td>
<td>
Processed dataset name
</td>
</tr>

<tr>
<td>
release
</td>
<td>
Software release, e.g. CMSSW_1_2_0
</td>
</tr>

</table>
"""

templateSearchTable="""
###<form action="javascript:ajaxSearch();" method="get">
<form action="$searchFunction" method="get">
<p>
Use this form to search in particular DBS instance by keywords.
<br />
The search is case insensitive and the following special symbols are supported:
<span class="box">'(', ')', 'and', 'or' and 'not'</span>.
<br />
You may use boolean expressions, e.g.,
<span class="box">( word1 or (word3 and word4) and not word2 )</span>
</p>

<hr class="dbs" />
#*
<p>
Choose DBS instance:
<select name="keywordsSearch_dbsInst" id="keywordSearch_dbsSelector">
#for dbs in $dbsList
#if $dbs==$firstDBS
<option value="$dbs" selected="selected">$dbs</option>
#else
<option value="$dbs">$dbs</option>
#end if
#end for
<option value="All">All</option>
</select>
Keywords:
<select id="keywordSearch_keywordsSelector" onchange="PutKeyword(this.value);ClearKeywordsSelector()">
<option value="None" selected="selected">...</option>
#for key in $keyList
<option value="$key">$key</option>
#end for
</select>
</p>

<p>
<input type="text" name="keywords" size="60" id="keywordSelector" />
###<textarea rows="5" cols="100" id="keywordSelector"></textarea>
</p>
<script type="text/javascript">ClearKeywordsSelector();ClearKeywordsInputValue()</script>
<hr class="dbs" />
*#

<table>
<tr>
<td align="right">DBS instance:</td>
<td><span class="gray_box" onMouseOver="KeywordHelp('DBS description')" onMouseOut="ClearTag('kw_help')">?</span></td>
<td>
<select name="keywordsSearch_dbsInst" id="keywordSearch_dbsSelector">
#for dbs in $dbsList
#if $dbs==$firstDBS
<option value="$dbs" selected="selected">$dbs</option>
#else
<option value="$dbs">$dbs</option>
#end if
#end for
<option value="All">All</option>
</select>
</td>

<td rowspan="8" valign="top"><span id="kw_help"></span></td>
</tr>

<tr>
<td><hr class="dbs" /></td>
<td></td>
<td></td>
</tr>

<tr>
<td align="right">Software release:</td>
<td><span class="gray_box" onMouseOver="KeywordHelp('software description')" onMouseOut="ClearTag('kw_help')">?</span></td>
<td><input type="text" size="60" name="kw_app" id="kw_algoSelector" /></td>
</tr>

<tr>
<td align="right">Primary dataset:</td>
<td><span class="gray_box" onMouseOver="KeywordHelp('Primary dataset description')" onMouseOut="ClearTag('kw_help')">?</span></td>
<td><input type="text" size="60" name="kw_prim" id="kw_primSelector" /></td>
</tr>

<tr>
<td align="right">Data Type:</td>
<td><span class="gray_box" onMouseOver="KeywordHelp('Data type description')" onMouseOut="ClearTag('kw_help')">?</span></td>
<td><input type="text" size="60" name="kw_tier" id="kw_tierSelector" /></td>
</tr>

<tr>
<td align="right">Run:</td>
<td><span class="gray_box" onMouseOver="KeywordHelp('Run description')" onMouseOut="ClearTag('kw_help')">?</span></td>
<td><input type="text" size="60" name="kw_run" id="kw_runsSelector" /></td>
</tr>

<tr>
<td><hr class="dbs" /></td>
<td></td>
<td></td>
</tr>

<tr>
<td align="right">Processed dataset:</td>
<td><span class="gray_box" onMouseOver="KeywordHelp('Processed dataset description')" onMouseOut="ClearTag('kw_help')">?</span></td>
<td><input type="text" size="60" name="kw_proc" id="kw_procSelector" /></td>
</tr>


<tr>
<td></td>
<td></td>
<td>
<input type="submit" value="Search" id="submit-button" onclick="javascript:showWaitingMessage();" />
&nbsp;
<input type="reset" value="Clear form" id="kw_clear_button"  />
###<input type="reset" value="Clear form" id="kw_clear_button" onclick="javascript:ClearKeywordForm();" />
</td>
</tr>
</table>

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
// it is going to be inserted here by AJAX call
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
<form action="javascript:ResetAllResults();ajaxSiteSearch()" method="get">
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
###<table id="siteBlocks" class="sortable">
<table id="siteBlocks">
<tr>
    <th>Block name</th>
    <th align="center">LFN list</th>
</tr>
#for name in $bList
#set bName=$name.replace('#','%23')
<tr>
    <td>$name</td>
    <td align="center">
    <a href="javascript:popUp('$host/getLFN_cfg?dbsInst=$dbsInst&amp;blockName=$bName',1000)">cff</a>,
    <a href="javascript:popUp('$host/getLFN_txt?dbsInst=$dbsInst&amp;blockName=$bName',1000)">txt</a>,
    <a href="javascript:popUp('$host/getLFNlist?dbsInst=$dbsInst&amp;blockName=$bName',1000)">details</a>
    </td>
</tr>
#end for
</table>
"""
templateDataFromKeywordSelection="""
#if len($oList)
<script type="text/javascript">
ajaxGetData('$dbs','all','*','*','*','$oList');
ajaxGetDbsData('$dbs','all','*','*','*','$oList');
ajaxGetRuns('$dbs','all','*','*','*','$oList');
</script>
#else
No match found for your request, please refine your search.
#end if
"""

templateDataFromSelection="""
<p><b>
Based on your search criteria:
<em>$keywords</em>
</b>
</p>

#if len($oList)
<form action="" method="get">
#if len($oList)>15
<table><tr valign="bottom"><td>
Please make your selection from the table below.
To submit your request click:
</td><td>
<table class="table_box"><tr><td>
<a href="javascript:ajaxGenParentsGraphFromSelection();ajaxGetDataFromSelection();showWaitingMessage();">Find</a>
</td></tr></table>
</td></tr></table>
#end if
<p><span id="SelectionHandler" name="SelectionHandler"></span></p>
<script type="text/javascript">UnSelectAll()</script>
<table id="search1" class="sortable" cellspacing="0" cellpadding="0" border="1">
<tr class="sortable_gray" align="center">
<td></td>
#if not $userMode:
<td>DBS instances</td>
#end if
<td>Primary dataset</td>
<td>Data tier</td>
<td>software version</td>
<td>family</td>
<td>executable</td>
</tr>
#for item in $oList
#set dbsInst=$item[0]
#set dbsId=$dbsInst.replace('/','_')
#set prim=$item[1]
#set tier=$item[2]
#set ver =$item[3]
#set fam =$item[4]
#set exe =$item[5]
<tr class="sortable_yellow" align="center">
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
<br />
<table><tr valign="center"><td>
Please make your selection from the table above.
To submit your request click:
</td><td>
<table class="table_box"><tr><td>
<a href="javascript:ajaxGenParentsGraphFromSelection();ajaxGetDataFromSelection();showWaitingMessage();">Find</a>
</td></tr></table>
</td></tr></table>
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

templateJSForm_new_allmenus_ajax="""
<!-- begin outer most table -->
<table class="lfn">
<tr valign="top">
<td valign="top">

####<form action="javascript:replace('navSelector');ajaxGetData();ajaxGenParentsGraph();ajaxGenAppConfigs();" method="get">
<form action="javascript:replace('navSelector');submitNavRequest()" method="get">
<!-- begin menu table -->
<table class="small" cellspacing="5">
<tr>
<td align="right">
<b>DBS instance</b>
</td>
<td>
<select id="dbsSelector" onchange="javascript:replace('navSelector');showLoadingMessage('selectApps');replace('selectPrim','to be defined');replace('selectTier','to be defined');ajaxSelectApps()">
<option value="">Select</option>
#for dbs in $dbsList
<option value="$dbs">$dbs</option>
#end for
</select>
<script type="text/javascript">resetNavSelection()</script>
</td>
</tr>

<tr>
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

<tr>
<td align="right">
<b>Application</b>
</td>
<td>
<span id="selectApps">to be defined</span>
</td>
</tr>
<tr>
<td align="right">
<b>Primary datasets</b>
</td>
<td>
<span id="selectPrim">to be defined</span>
</td>
</tr>
<td align="right">
<b>Data tier</b>
</td>
<td>
<span id="selectTier">to be defined</span>
</td>
</tr>
<tr>
<td>
</td>
<td>
###<input type="submit" value="Find" onclick="javascript:checkNavSelection()" />
<input type="submit" value="Find" />
</td>
</tr>
</table>
<table>
<tr>
<td>
<span id="navSelector"></span>
</td>
</tr>
</table>

<!-- end of menu table -->
</form>
</td>
</tr>
</table>
<!-- end of outer most table -->
"""

templateLeftBar="""
<table width="100%">
<tr>
<td align="left">$leftBar</td>
</tr>
</table>
"""

templateNextBar="""
#if $tot>1
<script type="text/javascript">
BuildBar(1,$step,$tot,$step,'$dbsInst','$site','$app','$prim','$tier','$proc');
UpdateResultIndex(1,$tot);
Choose('cell_1');
</script>
#end if
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

<form action="javascript:ResetAllResults();MakeNavBar();ajaxGetData();ajaxGetDbsData();ajaxGetRuns();ajaxGenParentsGraph();ajaxGenAppConfigs();" method="get">
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
<td><div class="yellow_box" onMouseOver="KeywordHelp('dbsDesc_tag','$dbsDesc')" onMouseOut="ClearTag('dbsDesc_tag')">?</div><span id="dbsDesc_tag"></span></td>
<td>
####<select name="dbsInst" onchange="updateLayer0(this)" id="dbsSelector">
<select name="dbsInst" onchange="ajaxGenNavigatorMenuDict()" id="dbsSelector">
#for dbs in $dbsList:
#if $dbs==$firstDBS
<option value="$dbs" selected="selected">$dbs</option>
#else
<option value="$dbs">$dbs</option>
#end if
#end for
</select>
</td>
</tr>

#end if

<tr valign="top">
<td align="right"><b>Tier sites</b>
</td>
<td><div class="yellow_box" onMouseOver="KeywordHelp('siteDesc_tag','$siteDesc')" onMouseOut="ClearTag('siteDesc_tag')">?</div><span id="siteDesc_tag"></span></td>
<td>
<select name="site" id="siteSelector">
<option value="All" selected="selected">All</option>
#for site in $sList:
<option value="$site">$site</option>
#end for
</select>
</td>
</tr>

<tr valign="top">
<td align="right"><b>Application</b>
</td>
<td><div class="yellow_box" onMouseOver="KeywordHelp('appDesc_tag','$appDesc')" onMouseOut="ClearTag('appDesc_tag')">?</div><span id="appDesc_tag"></span></td>
<td>
<div id="appHolder"></div>
</td>
</tr>

<tr valign="top">
<td align="right"><b>Primary dataset</b>
</td>
<td><div class="yellow_box" onMouseOver="KeywordHelp('primDesc_tag','$primDesc')" onMouseOut="ClearTag('primDesc_tag')">?</div><span id="primDesc_tag"></span></td>
<td>
<div id="primHolder"></div>
</td>
</tr>

<tr valign="top">
<td align="right"><b>Data tier</b>
</td>
<td><div class="yellow_box" onMouseOver="KeywordHelp('tierDesc_tag','$tierDesc')" onMouseOut="ClearTag('tierDesc_tag')">?</div><span id="tierDesc_tag"></span></td>
<td>
<div id="tierHolder"></div>
</td>
</tr>

<tr>
<td></td>
<td></td>
<td></td>
</tr>

<tr>
<td></td>
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
</td>
</tr>
</table>
<!-- end of menu table -->

</form>


</td>
</tr>

</table>
<!-- end of outer most table -->

<table>
<tr>
<td>
<span id="navSelector"></span>
</td>
</tr>
</table>
"""

templateLFN = """
#from DBSUtil import sizeFormat, colorSizeHTMLFormat
Block name: <b>$blockName</b>
<!-- Main table -->
###<table id="lfn_table" class="sortable" cellspacing="0" border="1">
<table id="lfn_table" cellspacing="0" border="1">
<tr class="sortable_gray">

<td>status</td>
<td>type</td>
<td>events</td>
<td>size</td>
<td>name</td>
</tr>
#for item in $lfnList:
#set name=item[0]
#set size=item[1]
#set stat=item[2]
#set type=item[3]
#set evts=item[4]
<tr class="sortable_yellow">
<td>$stat</td>
<td>$type</td>
<td>$evts</td>
<td>$colorSizeHTMLFormat($size)</td>
<td>
$name<br />
<table>
<tr>
<td><a href="$host/getLFN_Branches?dbsInst=$dbsInst&lfn=$name">ROOT branches</a></td>
<td>|</td>
<td><a href="$host/getLFN_Lumis?dbsInst=$dbsInst&lfn=$name">Lumis</a></td>
###<td><a href="javascript:ajaxGetLumis('$dbsInst','$name')">Lumis</a></td>
<td>|</td>
<td><a href="$host/getLFN_Algos?dbsInst=$dbsInst&lfn=$name">Algorithms</a></td>
###<td><a href="javascript:ajaxGetAlgos('$dbsInst','$name')">Algorithms</a></td>
<td>|</td>
<td><a href="$host/getLFN_Tiers?dbsInst=$dbsInst&lfn=$name">Tiers</a></td>
<td>|</td>
<td><a href="$host/getLFN_Parents?dbsInst=$dbsInst&lfn=$name">Parents</a></td>
</tr>
</table>
</td>
</tr>
#end for
</table>
<!-- end of main table -->
###<script type="text/javascript">sortables_init();</script>
"""

templateTable="""
<table cellspacing="0" border="1">
<tr class="sortable_gray">
#for item in $header
<td>$item</td>
#end for
</tr>
$content
</table>
"""

templateTableBody="""
<tr class="sortable_yellow">
#for item in $branch
<td>$item</td>
#end for
</tr>
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
<td align="right">DBS instantse:</td>
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
###<hr class="dbs" />
###<p>
For a list of processed datasets within this request click 
<a href="javascript:popUp('$host/showProcDatasets?dbsInst=$dbsInst&amp;site=$site&amp;app=$app&amp;primD=$primD&amp;tier=$tier',1000)">
here</a>
###</p>
"""

templateBlockList = """
<hr class="dbs" />
#if len($blockList)
Processed datasets:
<table class="offset_left">
#for item in $blockList
#set path=item[0]
#set idPath=$path.replace("/","___")
#set evts=item[1]
<tr>
<td align="left">
<b>$path</b>
</td>
<td align="center">
(<a href="javascript:popUp('$host/getDataDescription?dbsInst=$dbsInst&amp;processedDataset=$path',1000)">Description,</a>
</td>
<td align="center">
<a href="javascript:popUp('$host/crabCfg?dataset=$path&amp;totEvt=$evts',1000)">crab.cfg</a>)
</td>
</tr>
#end for
</table>
#end if
"""

templateBlockList_old = """
<hr class="dbs" />
#if len($blockList)
<table>
#for item in $blockList
#set path=item[0]
#set idPath=$path.replace("/","___")
#set evts=item[1]
<tr>
<td align="left">
<a href="javascript:showResMenu('parents')">$path</a>
</td>
<td align="center">
(<a href="javascript:popUp('$host/crabCfg?dataset=$path&amp;totEvt=$evts',1000)">crab.cfg</a>)
</td>
</tr>
#end for
</table>
#end if
"""

templateRunsInfo="""
Processed dataset:<br /> 
<span class="offset_left">
<b>$proc</b>
</span>
###<table id="$tableId" class="sortable" cellspacing="0" cellpadding="0" border="1">
<table id="$tableId" cellspacing="0" cellpadding="0" border="1">
<tr valign="top" align="center" id="tr$tableId" name="tr$tableId" class="sortable_gray">
<td>Run</td>
<td>Events</td>
<td>Lumi sec.</td>
<td>Total L</td>
<td>Store</td>
<td>StartOfRun</td>
<td>EndOfRun</td>
<td>Created by</td>
<td>Creation time</td>
<td>Modified by</td>
<td>Modifiction time</td>
</tr>
#for dbsDict in $runList
<tr valign="top" bgcolor="#FFFADC" name="dbs_row_sumInfo" id="dbs_row_sumInfo">
<td align="right">$dbsDict['RunNumber']</td>
<td align="right">$dbsDict['NumberOfEvents']</td>
<td align="center">$dbsDict['NumberOfLumiSections']</td>
<td align="right">$dbsDict['TotalLuminosity']</td>
<td align="left">$dbsDict['StoreNumber']</td>
<td align="right">$dbsDict['StartOfRun']</td>
<td align="center">$dbsDict['EndOfRun']</td>
<td align="right">$dbsDict['CreatedBy']</td>
<td align="center">$dbsDict['CreationDate']</td>
<td align="right">$dbsDict['LastModifiedBy']</td>
<td align="center">$dbsDict['LastModificationDate']</td>
</tr>
#end for
</table>
<hr class="dbs" />
"""

templateDbsInfo="""
Processed dataset:<br /> 
<span class="offset_left">
<b>$proc</b>
</span>
###<table id="$tableId" class="sortable" cellspacing="0" cellpadding="0" border="1">
<table id="$tableId" cellspacing="0" cellpadding="0" border="1">
<tr valign="top" align="center" id="tr$tableId" name="tr$tableId" class="sortable_gray">
<td>Block name</td>
<td>Events</td>
<td>Files</td>
<td>Size</td>
<td>LFNs</td>
<td>Status</td>
<td>Created by</td>
<td>Creation time</td>
<td>Modified by</td>
<td>Modifiction time</td>
</tr>
#from DBSUtil import sizeFormat, colorSizeHTMLFormat
#from DBSUtil import splitString 
#for dbsDict in $dbsList
#set bName=$dbsDict['Name']
#set sName=$splitString($dbsDict['Name'],30,"\\n")
<tr valign="top" bgcolor="#FFFADC" name="dbs_row_sumInfo" id="dbs_row_sumInfo">
<td align="left" class="td20">$sName</td>
<td align="right">$dbsDict['NumberOfEvents']</td>
<td align="right">$dbsDict['NumberOfFiles']</td>
<td align="right">$colorSizeHTMLFormat($dbsDict['BlockSize'])</td>
<td align="center">
<a href="javascript:popUp('$host/getLFN_cfg?dbsInst=$dbsInst&amp;blockName=$bName&amp;dataset=$proc',1000)" alt="cff format">cff</a>, 
<a href="javascript:popUp('$host/getLFN_txt?dbsInst=$dbsInst&amp;blockName=$bName&amp;dataset=$proc',900)" alt="file list format">txt</a>,
<a href="javascript:popUp('$host/getLFNlist?dbsInst=$dbsInst&amp;blockName=$bName&amp;dataset=$proc',1000)">details</a>
</td>
#if int($dbsDict['OpenForWriting'])==1
<td align="center" style="background-color:AliceBlue;">OPEN</td>
#else
<td align="center" style="background-color:AntiqueWhite;">CLOSED</td>
#end if
<td align="right">$dbsDict['CreatedBy']</td>
<td align="center">$dbsDict['CreationDate']</td>
<td align="right">$dbsDict['LastModifiedBy']</td>
<td align="center">$dbsDict['LastModificationDate']</td>
</tr>
#end for
</table>
###<script type="text/javascript">SplitBlockName()</script>
<hr class="dbs" />
"""

templateDbsInfoTableEntry="""
#from DBSUtil import sizeFormat, colorSizeHTMLFormat
#from DBSUtil import splitString 
#for dbsDict in $dbsList
#set bName=$dbsDict['Name']
#set sName=$splitString($dbsDict['Name'],30,"\\n")
<tr valign="top" bgcolor="#FFFADC" name="dbs_row_sumInfo" id="dbs_row_sumInfo">
<td align="left" class="td20">$sName</td>
<td align="right">$dbsDict['NumberOfEvents']</td>
<td align="right">$dbsDict['NumberOfFiles']</td>
<td align="right">$colorSizeHTMLFormat($dbsDict['BlockSize'])</td>
<td align="center">
<a href="javascript:popUp('$host/getLFN_cfg?dbsInst=$dbsInst&amp;blockName=$bName',1000)" alt="cff format">cff</a>, 
<a href="javascript:popUp('$host/getLFN_txt?dbsInst=$dbsInst&amp;blockName=$bName',900)" alt="file list format">txt</a>,
<a href="javascript:popUp('$host/getLFNlist?dbsInst=$dbsInst&amp;blockName=$bName',1000)">details</a>
</td>
#if int($dbsDict['OpenForWriting'])==1
<td align="center" style="background-color:AliceBlue;">OPEN</td>
#else
<td align="center" style="background-color:AntiqueWhite;">CLOSED</td>
#end if
<td align="right">$dbsDict['CreatedBy']</td>
<td align="center">$dbsDict['CreationDate']</td>
<td align="right">$dbsDict['LastModifiedBy']</td>
<td align="center">$dbsDict['LastModificationDate']</td>
</tr>
#end for
"""

templateSnapshotParameters="""
<span id="snapshot_dbsInst" class="hide">$dbsInst</span>
<span id="snapshot_site"    class="hide">$site</span>
<span id="snapshot_app"     class="hide">$app </span>
<span id="snapshot_prim"    class="hide">$prim</span>
<span id="snapshot_tier"    class="hide">$tier</span>
<span id="snapshot_proc"    class="hide">$proc</span>
"""

templateLFB = """
###<hr class="dbs" />
#from DBSUtil import sizeFormat, colorSizeHTMLFormat
#set tot=len($blockDict.keys())
#set idPath=$path.replace("/","___")

###<p>
contains $nEvents events, $totFiles files, $totSize. 
###</p>
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
<td>

<table id="Sum_$tableId" class="td_underline">
<tr>
<td>
<a href="javascript:HideTag('det_$tableId');ShowTag('sum_$tableId');switchLink('Sum','$tableId')">
Summary
</a>
</td>
</tr>
</table>

</td>
<td>

<table id="Det_$tableId" class="td_plain">
<tr>
<td>
<a href="javascript:ShowTag('det_$tableId');HideTag('sum_$tableId');switchLink('Det','$tableId')">
Details
</a>
</td>
</tr>
</table>

</td>
</tr>
</table>
<script type="text/javascript">switchLink('Sum','$tableId')</script>

<!-- Summary -->
<div id="sum_$tableId">
<table cellspacing="0" cellpadding="0" border="1">
  <tr valign="top" align="center">
     <th>Location</th>
     <th>Events</th>
     <th>Files</th>
     <th>size</th>
     <th>LFNs</th>
  </tr>
#for site in $siteList
#set siteTotEvt=0
#set siteTotFiles=0
#set siteTotSize=0
## HERE blockDict[blockName]=(nEvt,blockStatus,nFiles,blockSize,hostList)
##
## Sum total events,files,size for given site
##
#for bName in $blockDict.keys()
#if $blockDict[$bName][4:].count($site)
#set siteTotEvt+=$blockDict[$bName][0]
#set siteTotFiles+=$blockDict[$bName][2]
#set siteTotSize+=$blockDict[$bName][3]
#end if
#end for
  <tr valign="top" bgcolor="#FFFADC" name="row_sumInfo" id="row_sumInfo">
     <td><div class="dbs_cell">$site</div></td>
     <td align="right"><div class="dbs_cell">$siteTotEvt</div></td>
     <td align="right"><div class="dbs_cell">$siteTotFiles</div></td>
     <td align="right"><div class="dbs_cell">$colorSizeHTMLFormat($siteTotSize)</div></td>
     <td align="center">
     <a href="javascript:popUp('$host/getLFNsForSite?dbsInst=$dbsInst&amp;site=$site',1000)">All</a>
     </td>
     <td align="center" name="blockInfo" id="blockInfo" class="hide">
     </td>
     <td align="center" name="blockInfo" id="blockInfo" class="hide">
     <a href="javascript:popUp('$host/getBlocksForSite?site=$site',1000)">all blocks</a>
     </td>
  </tr>
#end for
</table>
</div>
<!-- End of Summary table -->

<!-- Detailed -->
<div id="det_$tableId" class="hide">
<table cellspacing="0" cellpadding="0" border="1">
  <tr valign="top" align="center">
     <td>Location</td>
     <td>Events</td>
     <td>Files</td>
     <td>size</td>
     <td>LFNs</td>
     <td>status</td>
     <td>Block name</td>
  </tr>
## HERE blockDict[blockName]=(nEvt,blockStatus,nFiles,blockSize,hostList)
#for name in $blockDict.keys()
#set item    = $blockDict[$name]
#set bName   = $name.replace('#','%23')
#set escName = $name.replace('#','\#')
#set htmlName = $name.replace('#','&#35;')
#set nEvt    = $item[0]
#set bStatus = $item[1]
#set nFiles  = $item[2]
#set size    = $item[3]
#set hostList= $item[4:]
  <tr valign="top" bgcolor="#F0F0F0">
     <td><div class="dbs_cell">
#for site in $hostList
      $site
      #if $site!=$hostList[-1]
      <br />
      #end if
#end for
     </div></td>
     <td align="right"><div class="dbs_cell">$nEvt</div></td>
     <td align="right"><div class="dbs_cell">$nFiles</div></td>
     <td align="right"><div class="dbs_cell">$colorSizeHTMLFormat($size)</div></td>
     <td align="center"><div class="dbs_cell">
     <a href="javascript:popUp('$host/getLFN_cfg?dbsInst=$dbsInst&amp;blockName=$bName&amp;dataset=$path',1000)" alt="cff format">cff</a>, 
     <a href="javascript:popUp('$host/getLFN_txt?dbsInst=$dbsInst&amp;blockName=$bName&amp;dataset=$path',900)" alt="file list format">txt</a>,
     <a href="javascript:popUp('$host/getLFNlist?dbsInst=$dbsInst&amp;blockName=$bName&amp;dataset=$path',1000)">details</a>
     </div>
     </td>
#if $bStatus=="OPEN" or $bStatus==1
     <td align="center" style="background-color:AliceBlue;"><div class="dbs_cell">OPEN</div></td>
#else
     <td align="center" style="background-color:AntiqueWhite;"><div class="dbs_cell">CLOSED</div></td>
#end if
     <td align="left" class="td20">
#from DBSUtil import splitString 
#set sName=$splitString($name,30,"\\n")
     $sName
     </td>
  </tr>
#end for
</table>
</div>
<!-- End of Detailed table -->

<br />
##
#end if
##
"""

templateResults="""
<span id="floatDataDescription"></span>
<table id="results_menu" class="hide" cellspacing="0" cellpadding="0" width="100%">
###<table id="results_menu" cellspacing="0" cellpadding="0" width="100%">

<tr>

<td style="width:1%;background-color:#E0E0E0;" align="center">
<span class="yellow_box" onMouseOver="KeywordHelp('ResultsViewDesc_tag','Found data groupped into several tabs. Summary view collects information from DBS and DLS system. Block and run info are DBS only information. Parents tab shows data relationship and appConfigs retrieves information about configuration files associated with found data.')" onMouseOut="ClearTag('ResultsViewDesc_tag')">?</span><span id="ResultsViewDesc_tag"></span>
</td>

<td class="td_menu_gray_box" id="_results">
<table width="100%"><tr><td>
<div id="__results"><table class="image"><tr><td></td></tr></table></div>
</td><td align="center">
<a href="javascript:showResMenu('results')">
Summary
</a>
</td><td><table class="image"><tr><td></td></tr></table>
</td></tr></table>
</td>

<td class="td_menu_gray_box" id="_results_dbs">
<table width="100%"><tr><td>
<div id="__results_dbs"><table class="image"><tr><td></td></tr></table></div>
</td><td align="center">
<a href="javascript:showResMenu('results_dbs')">
Block info
</a>
</td><td><table class="image"><tr><td></td></tr></table>
</td></tr></table>
</td>

<td class="td_menu_gray_box" id="_runs">
<table width="100%"><tr><td>
<div id="__runs"><table class="image"><tr><td></td></tr></table></div>
</td><td align="center">
<a href="javascript:showResMenu('runs')">
Run info
</a>
</td><td><table class="image"><tr><td></td></tr></table>
</td></tr></table>
</td>

<td class="td_menu_gray_box" id="_parents">
<table width="100%"><tr><td>
<div id="__parents"><table class="image"><tr><td></td></tr></table></div>
</td><td align="center">
<a href="javascript:showResMenu('parents')">
Parents
</a>
</td><td><table class="image"><tr><td></td></tr></table>
</td></tr></table>
</td>

<td class="td_menu_gray_box" id="_appConfigs">
<table width="100%"><tr><td>
<div id="__appConfigs"><table class="image"><tr><td></td></tr></table></div>
</td><td align="center">
<a href="javascript:showResMenu('appConfigs')">
App configs
</a>
</td><td><table class="image"><tr><td></td></tr></table>
</td></tr></table>
</td>

</tr>
</table>

<br />
<div id="navBar" class="hide"></div>
<div id="results_index"></div>
<div id="results_waiting"></div>
<div id="results_kw"></div>
<div id="results_dbs"></div>
<div id="results_site"></div>
<div id="runs"></div>
<div id="parents"><br /></div>
<div id="appConfigs"><br /></div>
<div id="results"></div>
<p>
<div id="progressBar"></div>
</p>
"""

templateBottom="""
</div> <!-- end of div with class="main" -->
</body>
</html>
"""

templateFrontPage_test="""
<table width="100%" cellspacing="0" cellpadding="0">
<tr>
<!-- menu -->
<td id="menu_td_fixed" class="menu_td_gray_fixed" valign="top">
Menu
</td>
<!-- menu content, accordion -->
<td valign="top">

#*
<table class="table_box_white" border="1" width="100%">
<tr valign="top">
<td class="box_darkblue">
Navigator menu
</td>
</tr>
<tr valign="top">
<td>
Test
</td>
</tr>
</table>
*#

<span id="NavigatorDiv" class="hide">
<table class="table_box_white" border="1" width="100%">
<tr valign="top">
<td class="box_darkblue">
Navigator menu
</td>
</tr>
<tr valign="top">
<td>
$navigatorForm
</td>
</tr>
</table>
</span>


</td>
</tr>
</table>
"""


templateFrontPage="""
<script type="text/javascript">
var DBSDD='$DBSDD';
var DBSDD_EXPERT='$DBSDD/expert';
var GLOBAL_STEP=$step
</script>
<table width="100%" cellspacing="0" cellpadding="0">
<tr>
<!-- menu -->
<td id="menu_td_fixed" class="menu_td_gray_fixed" valign="top">

<table class="menu" width="100%">
<tr>
<td>
<span class="yellow_box" 
onMouseOver="KeywordHelp('NavDesc_tag','Discovery page provides a three ways to find your data.\
<p>Navigator menu based search is designed to guide you through available data in CMS.</p>\
<p>Selection search is arbitrary based search of data based on given data tag selection, e.g. Software release, data types, etc.</p>\
<p>Site search is used to find out data which are located at specified site. Data search does not based on any data classification and it is plain lookup of what is available on a site through DLS service.</p>\
')" onMouseOut="ClearTag('NavDesc_tag')">?</span><span id="NavDesc_tag"></span>
<b>Find data:</b>
</td>
</tr>
<tr>
<td class="td_gray_box" id="Navigator_Menu">
<a href="javascript:showMenu('Navigator')">
<table><tr><td><b>Navigator</b></td></tr></table>
</a></td>
</tr>
<tr>
<td class="td_gray_box" id="Search_Menu">
<a href="javascript:showMenu('Search');ajaxGetKWFields()">
<table><tr><td><b>Selections</b></td></tr></table>
</a>
</td>
</tr>
#if not $userMode
<tr>
<td class="td_gray_box" id="Site_Menu">
<a href="javascript:showMenu('Site')">
<table><tr><td><b>Site</b></td></tr></table>
</a>
</td>
</tr>
###<tr><td><br /></td></tr>
<tr><td>
<span class="yellow_box" onMouseOver="KeywordHelp('DBSInfoDesc_tag','Here you will find a full list of available applications, primary and processed datasets from DBS.')" onMouseOut="ClearTag('DBSInfoDesc_tag')">?</span><span id="DBSInfoDesc_tag"></span>
<b>List data:</b>
</td></tr>
<tr>
<td class="td_gray_box" id="DBSinfo_Menu">
<a href="javascript:showMenu('DBSinfo');getDbsInfo('$dbsGlobal',$dbsNames);">
<table><tr><td><b>DBS info</b></td></tr></table>
</a>
  <table id="dbsInst_table" class="hide">
#for iName in $dbsNames
#set name=iName.replace("/","___")
      <tr><td>&\#187;</td><td id="dbsInst_$name"><a href="javascript:getDbsInfo('$iName',$dbsNames);">$iName</a></td></tr>
#end for
  </table>       
</td>
</tr>
#end if
###<tr><td><br /></td></tr>
<tr><td>
<span class="yellow_box" onMouseOver="KeywordHelp('RssDesc_tag','You may subscribe to specific chunk of data and being notified every time when your data is updated. RSS is a family of web feed formats used to publish frequently updated digital content. In case of discovery data it has collection of links which you can subscribe to and monitor Live of your data appearence.')" onMouseOut="ClearTag('RssDesc_tag')">?</span><span id="RssDesc_tag"></span>
<b>Subscribe to:</b>
</td></tr>
<tr>
<td class="td_gray_box" id="Rss_Menu">
<a href="javascript:showMenu('Rss');ajaxGetRss();">
<table><tr><td><b>RSS list</b></td></tr></table>
</a>
</td>
</tr>
<tr><td><br /></td></tr>
#*
<tr>
<td class="td_gray_box" id="SessionHistory_Menu">
<a href="javascript:GetSessionHistory();">
<table><tr><td><b>Session History</b></td></tr></table>
</a>
###<span class="yellow_box" onMouseOver="KeywordHelp('SessDesc_tag','Session history is a quick way to find out your previous actions on a web page')" onMouseOut="ClearTag('SessDesc_tag')">?</span><span id="SessDesc_tag"></span>
</td>
</tr>
*#
<tr>
#set hList=['user','auth','search']
#set hDict={'user':'Session','auth':'Authentication','search':'Search'}
########<td class="td_gray_box" id="History_Menu"><a href="javascript:GetSessionHistory();">History</a>
<td class="td_gray_box" id="History_Menu">
###<span class="yellow_box" onMouseOver="KeywordHelp('HistDesc_tag','History menu keep track of what you have done on discovery page. The <em>Back</em> button does not work due to used technology and instead all of your history are collected here.')" onMouseOut="ClearTag('HistDesc_tag')">?</span><span id="HistDesc_tag"></span>
<a href="javascript:showMenu('History');showHistoryMenu('$hList[0]',$hList);ajaxGetHistory();">
<table><tr><td><b>History</b></td></tr></table>
</a>
  <table id="history_table" class="hide">
#for name in $hList
      <tr><td>&\#187;</td><td id="_${name}History"><a href="javascript:showHistoryMenu('$name',$hList);AdjustToDate()">$hDict[$name]</a></td></tr>
#end for
  </table>       
</td>
</tr>
<tr>
<td class="td_gray_box" id="Help_Menu">
<a href="javascript:showMenu('Help');showHelpContent();">
<table><tr><td><b>Help</b></td></tr></table>
</a>
</td>
</tr>
<tr><td><br /></td></tr>
<tr>
<td class="td_gray_box" id="Hide_Menu">
<a href="javascript:HidePanel('$host')">
<table><tr><td><b>Hide panel</b></td></tr></table>
</a>
</td>
</tr>
<tr><td><br /><em class="tiny_purple_on_gray">Tip: $tip</em></td></tr>
</table>

</td>
<!-- menu content, accordion -->
<td valign="top" class="td_top_bottom">

<!-- NavigatorDiv -->
<div id="NavigatorDiv" class="hide">
<table class="table_box_white" border="0" width="100%">
<tr valign="top">
<td class="box_darkblue">
Navigator menu
</td>
</tr>
<tr valign="top">
<td>
$navigatorForm
</td>
</tr>
</table>
</div>
<!-- END NavigatorDiv -->

#*
<!-- SearchDiv -->
<div id="SearchDiv" class="hide">
<table class="table_box_white" border="0" width="100%">
<tr valign="top">
<td class="box_darkblue">
Keyword search menu
</td>
</tr>
<tr valign="top">
<td>
$searchForm
</td>
</tr>
</table>
</div>
<!-- END SearchDiv -->
*#

<!-- SearchDiv -->
#set menuArr=['kw_mc','kw_data']
<div id="SearchDiv" class="hide"> 
<!-- outermost table in searchdiv -->
<table class="table_box_white" border="0" width="100%">
<tr valign="top">
<td class="box_darkblue">
Selection search menu
</td>
</tr>
<tr valign="top">
<td>

<form action="" method="get">

<table width="100%">
<tr>
<td align="left">
DBS instance: <select name="keywordsSearch_dbsInst" id="kw_dbsSelector">
###<option value="All">All</option>
#for dbs in $dbsList
<option value="$dbs">$dbs</option>
#end for
</select>
</td>
<td align="right">
<input type="submit" name="Submit" id="kw-submit-button" />
</td>
</tr>
</table>

<div id="kw_mc" class="show_inline">
<table class="kw" width="100%" cellspacing="0" cellpadding="0">
<tr>
<th class="gray">Releases</th>
<th class="gray">Generators/<br />Trigger Lines</th>
<th class="gray">Data types</th>
<th class="gray">ROOT branches</th>
</tr>
<tr>
<td class="border"><div id="kw_release"></div></td>
<td class="border"><div id="kw_prim"></div></td>
<td class="border"><div id="kw_tier"></div></td>
<td class="normal"><div id="kw_branch"></div></td>
</tr>
</table>
</div>

</form>

</td>
</tr>
</table> <!-- end of outermost table -->
</div>
<!-- END SearchDiv -->

<!-- SiteDiv -->
<div id="SiteDiv" class="hide">
<table class="table_box_white" border="0" width="100%">
<tr valign="top">
<td class="box_darkblue">
Site search menu
</td>
</tr>
<tr valign="top">
<td>
$siteForm
</td>
</tr>
</table>
</div>
<!--END SiteDiv -->

<!-- DBSinfoDiv -->
###### Replacement for accordion datasets
#set menuArr=['dbs_prim','dbs_proc','dbs_apps']
<div id="DBSinfoDiv" class="hide"> 

<table id="dbs_info" class="hide" cellspacing="0" cellpadding="0" width="100%">
<tr>

<td class="td_menu_gray_box" id="_dbs_prim">
<table width="100%"><tr><td>
<div id="__dbs_prim"><table class="image"><tr><td></td></tr></table></div>
</td><td align="center">
<a href="javascript:showResMenu('dbs_prim',$menuArr)">Primary datasets</a>
</td><td><table class="image"><tr><td></td></tr></table>
</td></tr></table>
</td>

<td class="td_menu_gray_box" id="_dbs_proc">
<table width="100%"><tr><td>
<div id="__dbs_proc"><table class="image"><tr><td></td></tr></table></div>
</td><td align="center">
<a href="javascript:showResMenu('dbs_proc',$menuArr)">Processed datasets</a>
</td><td><table class="image"><tr><td></td></tr></table>
</td></tr></table>
</td>

<td class="td_menu_gray_box" id="_dbs_apps">
<table width="100%"><tr><td>
<div id="__dbs_apps"><table class="image"><tr><td></td></tr></table></div>
</td><td align="center">
<a href="javascript:showResMenu('dbs_apps',$menuArr)">Applications</a>
</td><td><table class="image"><tr><td></td></tr></table>
</td></tr></table>
</td>


</tr>
</table>

<span id="dbs_prim" class="hide"></span>
<span id="dbs_proc" class="hide"></span>
<span id="dbs_apps" class="hide"></span>

</div>
<!-- END DBSinfoDiv -->

<!-- HistoryDiv -->
###### Replacement for accordion history
<div id="HistoryDiv" class="hide"> 

<!-- userHistory -->
<div id="userHistory" class="hide">

<table class="table_round_box" border="0" width="100%">
<tr valign="top">
<td class="box_darkblue">

#set menuArr=['sessionHistory','allHistory']
<table id="userHistory_info" class="show_table" cellspacing="1" width="50%">
<tr>
<td class="td_menu_white_box" align="center" id="_sessionHistory"><a href="javascript:showResMenu('sessionHistory',$menuArr)">Current session</a></td>
<td class="td_menu_gray_box" align="center" id="_allHistory"><a href="javascript:showResMenu('allHistory',$menuArr)">Last 100</a></td>
</tr>
</table>

</td>
</tr>
</table>

<div class="div_scroll">
<span id="sessionHistory" class="show_inline"></span>
<span id="allHistory" class="hide"></span>
</div>

</div>
<!-- END userHistory -->

<!-- authHistory -->
<div id="authHistory" class="hide">
      <table class="table_box_white" border="0" width="100%">
      <tr valign="top">
      <td class="box_darkblue">
      DBS/DLS data discovery authentication
      </td>
      </tr>
      <tr valign="top">
      <td>
      <div class="text">To be able keep your session history you need to authenticate yourself.<br/>
      This information is NOT associated with either your login name to any CMS nodes<br />
      or grid certificates and used solely for your authentication with DBS/DLS discovery page.<br />
      This authentication is completely optional and used to provide persistent history searches.
      <p>Please use provided form below:</p>
      </div>

      <form action="javascript:ajaxCheckUser()" method="get">
      <div id="formInputName"></div>
      <script type="text/javascript">formRequest()</script>
      <p>
      <input type="submit" value="Authenticate" onclick="set_name(this.form)" />
      </p>
      <p></p>
      <div id="formAuthResults"></div>
      </form>

      </td></tr></table>
      ##</div>
</div>
<!-- END authHistory -->

<!-- searchHistory -->
<div id="searchHistory"  class="hide">
      <table class="table_box_white" border="0" width="100%">
      <tr valign="top">
      <td class="box_darkblue">
      DBS/DLS data discovery history search
      </td>
      </tr>
      <tr>
      <td>
      <div class="div_scroll">
      <p></p>
      <form action="javascript:ajaxHistorySearch();" method="get">
      <table>
      <tr>
      <td>
      From:
      </td>
      <td>
      <select name="in_hSearch_year" id="in_hSearch_year" onchange="AdjustToDate();CheckToDate()">
#for year in xrange(2006,2016)
      <option value="$year">$year</option>
#end for
      </select>
#set mArr=['Jan','Feb','Mar','May','Apr','Jun','Jul','Aug','Sep','Oct','Nov','Dec']
      <select name="in_hSearch_month" id="in_hSearch_month" onchange="AdjustToDate();CheckToDate()">
#for month in $mArr
      <option value="$month">$month</option>
#end for
      </select>
      </td>
      <td>
      to:
      </td>
      <td>
      <select name="out_hSearch_year" id="out_hSearch_year" onchange="CheckToDate()">
#for year in xrange(2006,2016)
      <option value="$year">$year</option>
#end for
      </select>
#set mArr=['Jan','Feb','Mar','May','Apr','Jun','Jul','Aug','Sep','Oct','Nov','Dec']
      <select name="out_hSearch_month" id="out_hSearch_month" onchange="CheckToDate()">
#for month in $mArr
      <option value="$month">$month</option>
#end for
      </select>
      </td>
      <td>
      <input type="submit" value="Find" onclick="javascript:showLoadingMessage('historySearchResults');" />
      </td>
      </tr>
      </table>
      </form>
      <hr class="dbs" />
      <span id="historySearchResults">Here we will provide a basic search of user commands</span>

      </div>
      </td>
      </tr>
      </table>
</div>
<!-- END searchHistory -->

</div>
<!-- END HistoryDiv -->


<!-- HelpDiv -->
###### Replacement for accordion help
#set menuArr=['help_intro','help_glossary','help_resources','help_feedback','help_refs']
<div id="HelpDiv" class="hide"> 


<table class="table_round_box" border="0" width="100%">
<tr valign="top">
<td class="box_darkblue">


<table id="help_info" cellspacing="1" width="70%">
<tr>
<td class="td_menu_white_box" align="center" id="_help_intro"><a href="javascript:showResMenu('help_intro',$menuArr)">Introduction</a></td>
<td class="td_menu_gray_box" align="center" id="_help_glossary"><a href="javascript:showResMenu('help_glossary',$menuArr)">DBS glossary</a></td>
<td class="td_menu_gray_box" align="center" id="_help_resources"><a href="javascript:showResMenu('help_resources',$menuArr)">DBS resources</a></td>
<td class="td_menu_gray_box" align="center" id="_help_feedback"><a href="javascript:showResMenu('help_feedback',$menuArr)">Feedback</a></td>
<td class="td_menu_gray_box" align="center" id="_help_refs"><a href="javascript:showResMenu('help_refs',$menuArr)">References</a></td>
</tr>
</table>

</td>
</tr>
</table>

<div id="help_intro" class="hide">
<div class="div_scroll">
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

<div id="help_glossary" class="hide"><div class="div_scroll">$glossary</div></div>

<div id="help_resources" class="hide">
<div class="div_scroll">
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

<div id="help_feedback" class="hide">
<div class="div_scroll">
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

<div id="help_refs" class="hide">
<div class="div_scroll">
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

</div>
<!-- END HelpDiv -->

<!-- RssDiv -->
<div id="RssDiv" class="hide">
<table class="table_box_white" border="0" width="100%">
<tr valign="top">
<td class="box_darkblue">
RSS feeds
</td>
</tr>

<tr>
<td>

<div class="div_scroll">
<div id="rss_list"></div>
</div>
</div>

</td>
</tr>
</table>
<!--END RssDiv -->

</td>
</tr>
</table>

<script type="text/javascript">
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
<pre>
###### For more information please consult
###### http://www.uscms.org/SoftwareComputing/UserComputing/Tutorials/Crab.html
[CRAB]

jobtype                 = cmssw
scheduler               = glitecoll

[CMSSW]

datasetpath             = $dataset
##### Please provide PSet configuration file below
pset                    = 
total_number_of_events  = $totEvt
events_per_job          = 1000
##### Please provide output_file, e.g. FrameworkJobReport.xml
##### or a list of output files separated by comma, for example
##### output.root, output.txt, FrameworkJobReport.xml
output_file             = 

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
</pre>
"""

templateAppConfigs="""
For given application <b>$appPath</b> we found the following application configs:

<table class="intro">
#for config in $configList
#set name=$config[0]
#set value=$config[1]
<tr><td>$name</td><td>$value</td></tr>
#end for
</table>

<div class="box_red">
In a future those values will be resolved into real files.
</div>
"""

templateDatasetDetails="""
<div class="myList">
#for dataset in $dList:
<ul>
   <li><b>Processed name:</b> $dataset['Name']
   <li><b>Primary name:</b> $dataset['PrimaryDataset']['Name']
   <li><b>Tiers:</b>
       <ul>
       #for tier in dataset['TierList']
       <li>$tier
       #end for
       </ul>
   <li><b>Algorithms:</b>
       <ul>
       #for alg in dataset['AlgoList']
       <li>/$alg['ApplicationFamily']/$alg['ApplicationVersion']/$alg['ExecutableName']
       #end for
       </ul>
   <li><b>Last modified by:</b> $dataset['LastModifiedBy'] on <em>$dataset['LastModificationDate']</em>
   <li><b>Created by:</b> $dataset['CreatedBy'] on <em>$dataset['CreationDate']</em>
</ul>
#end for
</div>
"""

templateFloatBox="""
<table class="$className" width="100%">
<tr valign="top">
<td>

<table width="100%">
<tr>
<td align="left">
<span class="sectionhead">
$title
</span>
</td>
<td align="right">
<a href="javascript:HideTag('floatDataDescription')">close &#8855;</a>
</td>
</tr>
</table>
<hr class="dbs" />
<table>
<tr>
<td>
<div class="div_scroll">
$description
</div>
</td>
</tr>
</table>

</td>
</tr>
</table>
"""

templateDescription="""
###<table class="float_box" width="100%">
<table width="100%">
<tr valign="top">
<td>

<table width="100%">
<tr>
<td align="left">
<span class="sectionhead">
DATA DESCRIPTION
</span>
</td>
#*
<td align="right">
<a href="javascript:HideTag('floatDataDescription')">close &#8855;</a>
</td>
*#
</tr>
</table>
<hr class="dbs" />
<table>
<tr>
<td>
###<div class="div_scroll">
$description
###</div>
</td>
</tr>
</table>

</td>
</tr>
</table>
"""

templateRssList="""
<table>
#if not $userMode
<tr>
<td><span class="sectionhead_tight">$dbs</span></td>
</tr>
#end if
#for item in $rssList
<tr>
#set l=$item.split("/")
#set app=$l[2].replace("___","/")
#set prim=$l[3]
<td><a href="$host/$item"><img src="images/rss_blue.gif" alt="RSS" /></a> <b>Application:</b>$app, <b>Primary dataset:</b>$prim</td>
</tr>
#end for
</table>
<hr class="dbs" />
"""

templateRSS="""
#import time
<?xml version="1.0" ?>
<rss version="2.0">

<channel>

<title>$title</title>
<description>$title_description</description>
<link>$title_link</link>
<language>en-us</language>
<pubDate>$time.strftime("%a, %d %b %Y %H:%M:%S GMT",$time.gmtime())</pubDate>
<lastBuildDate>$time.strftime("%a, %d %b %Y %H:%M:%S GMT",$time.gmtime())</lastBuildDate>
<docs>http://en.wikipedia.org/wiki/RSS_(file_format)</docs>
<generator>DBS discovery page</generator>
<managingEditor>vk@mail.lns.cornell.edu</managingEditor>
<webMaster>vk@mail.lns.cornell.edu</webMaster>

#for item in $pList
#set path=item
#set evt=0
#set link='http://localhost'
#*
#set path=$item[0]
#set evt=$item[1]
#set link=$item[2]
*#
<item>
<title>$path</title>
<description>Number of events: $evt</description>
<link>$link</link>
<pubDate>$time.strftime("%a, %d %b %Y %H:%M:%S GMT",$time.gmtime())</pubDate>
<guid>$link</guid>
</item>
#end for

</channel>
</rss>
"""

templateSelectList="""
<div class="div_scroll">
<table class="plain">
<tr>
<td>All</td><td><input type="checkbox" name="$name" value="All" /></td>
</tr>
#for item in $iList
<tr>
<td>$item</td><td><input type="checkbox" name="$name" value="$item" /></td>
</tr>
#end for
</table>
</div>
"""

DBSInstanceDesc="""\
<p><b>DBS instance</b></p>\
<p>\
A unique self-consistent instance of DBS (Data Bookkeeping System) which used for particular task.\
For instance, data can be produce on site on regular basis, for that purpose site keeps\
its own DBS (instance). Once data are ready for users they published to Global DBS instance\
which is CMS default.\
</p>\
<p><b>Examples:</b> MCGlobal/Writer</p>\
"""
TierSiteDesc="""<p><b>Tier site</b></p>\
<p>A fully domain qualified name for a site (cern.ch) which produce data.</p>\
<p><b>Examples:</b> castorgrid.cern.ch</p>\
"""
ApplicationDesc="""<p><b>Application</b></p>\
<p>\
An application consists of three parts: version, family and executable. For simplicity\
we follow path convension, e.g.\
/version/family/executable. The version is a software release name, e.g. CMSSW_1_1_1\
family is data types which produced by software release, e.g. SIM-DIG-RECO,\
please note here that notation describe that software release used to produce\
three types of data. And finally, executable is a name of executable used to produce\
this data.\
</p>\
<p><b>Examples:</b> /CMSSW_1_2_0/DIGI-RECO/cmsRun</p>\
"""
PrimaryDatasetDesc="""<p><b>Primary dataset</b></p>\
<p>\
Data at all levels of processing pertaining to a given trigger or common MC \
production criteria. For data from the experiment, the primary dataset is \
determined by the HLT event classification (trigger line). \
For Monte Carlo data, primary \
datasets comprise data generated with the same parameters (MC generator).\
</p>\
<p><b>Examples:</b> mc-onsel-120_PU_Zee</p>\
"""
DataTierDesc="""<p><b>Data tier</b></p>\
<p>\
Data tier or data type \
A set of ob jects to be grouped togethered in the output files of the processing step \
producing the objects. The list of objects comprising a Data Tier is determined \
by the release configuration files. Additional objects not part of the Data Tier \
may be included in the same output file.\
</p>\
<p><b>Examples:</b> DSIM, DIGI, RECO</p>\
"""
ProcessedDatasetDesc="""<p><b>Processed dataset</b></p>\
<p>\
A slice of data from a Primary Dataset defined by the processing history applied to it.\ 
A processed dataset will correspond to a large production of data with a single major \
software release version, but may include multiple minor versions for small bug fixes \
and also may contain the output of multiple processings of some given input data.\
</p>\
<p><b>Examples:</b> /mc-onsel-120_PU_Zee/DIGI/CMSSW_1_2_0-NoPU-DIGI-1169220692</p>\
"""
AnalysisDatasetDesc="""<p><b>Analysis dataset</b></p>\
<p>\
A subset of a Processed Dataset representing a coherent sample for physics analysis, specified (conceptually) by running an analysis query on a Processed Dataset at a particular instant of time. An Analysis Dataset must not contain the output of multiple processings of any given input data.\
</p>\
<p><b>Examples:</b> to be defined</p>\
"""

templateBarNavigator="""
<span class="td_underline" style="padding: 3px 3px 3px 3px;">
<b>Menu:Navigator</b></span> &#187; 
<b>DBS instance:</b>$dbsInst &#187; 
<b>Site:</b>$site &#187; 
<b>Application:</b>$app &#187; 
<b>Primary Dataset:</b>$prim &#187; 
<b>Data tier:</b>$tier
<hr class="dbs" />
"""

templateDummy="""
<table><tr valign="bottom"><td>
Choose from the table below and 
</td><td>
<table class="table_box"><tr><td>
<a href="javascript:ajaxGenParentsGraphFromSelection();ajaxGetDataFromSelection();showWaitingMessage();">Find your data</a>
</td></tr></table>
</td></tr></table>
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

