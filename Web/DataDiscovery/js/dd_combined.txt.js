/*['js/masthead_footer.js\n', 'js/userName.js\n', 'js/utils.js\n', 'js/yui_utils.js\n', 'js/prototype.js\n', 'js/rico.js\n', 'js/ajax_init.js\n', 'js/dhtml_history/dhtmlHistory.js\n']*/function _footerMenuText(host,mode){
    // always check provided host and append / at the end
    if (host[host.length]!='/') {
        host=host+'/';
    }
    return [
{label: "Home", link: host+"_advanced?userMode="+mode, title: "DBS Data Discovery home page"},
{label: "aSearch", link: host+"_advanced?userMode="+mode, title: "Advanced keyword search"},
{label: "Navigator", link: host+"_navigator?userMode="+mode, title: "Navigator: menu-driven search"},
//{label: "Finder", link: "_finder?userMode="+mode, title: "Finder: query-driven search"},
//{label: "Analysis", link: "_analysis?userMode="+mode, title: "Search analysis datasets"},
{label: "RSS", link: host+"_rss?userMode="+mode, title: "RSS Feeds publish frequently updates about your data"},
//{label: "Sites", link: host+"_siteSearch?userMode="+mode, title: "Site search: site-based search"},
{label: "Status", link: host+"_status?userMode="+mode, title: "DBS instances status page"},
{label: "Runs", link: host+"_runs?userMode="+mode, title: "Run search: run-based search"},
//{label: "Pages", link: "_pages?userMode="+mode, title: "Data discovery world-wide pages"},
{label: "Admin", link: host+"_admin?userMode=expert", title: "Administrate dataset/block/LFNs in DBS"},
{label: "Tools", link: host+"_tools?userMode="+mode, title: "DBS user-friendly tools"},
{label: "Help", link: host+"_help?userMode="+mode, title: "Help: DBS glossary, terms, feedback form"},
{label: "Contact", link: host+"_contact?userMode="+mode, title: "Contact DBS support team"},
{label: "TinyURL", link: "_tinyurl?url="+encodeURIComponent(location.href), title: "Create tiny URL to this web page"},
{label: "View", id:"", link: "javascript:ChangeView()", title: "Define the data view", position: "right"}
           ]
}
function setGreeting() {
   var cookie_user=GetCookie("DBSDD_username");
   var greeting;
   if (cookie_user=='guest') {
       // to be freindly I made a link to history menu as I did in CheetahTemplate.py
       // see set hList
       cookie_user='<a href="javascript:showMenu(\'History\');showHistoryMenu(\'auth\',[\'user\',\'auth\',\'search\'])">'+cookie_user+'</a>'
       greeting='Hello '+cookie_user+'.';
   } else {
       greeting='Welcome <span class="box_blue">'+cookie_user+'</span>!';
   }
   msg='<span class="sectionhead_tight">'+greeting+'</span>';
   var t=document.getElementById("Greeting");
   if (t) {
       t.innerHTML=msg;
   }
}
function formRequest() {
   var cookie_user=GetCookie("DBSDD_username");
   if (cookie_user=="guest") {
       cookie_user="";
   }
   var cookie_pass=GetCookie("DBSDD_password");
   var pass_value='';
   if (cookie_pass) {
       pass_value='value="'+cookie_pass+'"';
   }
   var t=document.getElementById("formInputName");
   if (t) {
       var user='<input type="text" name="nameinput" value="'+cookie_user+'" />';
       var pass='<input type="password" name="passinput" '+pass_value+' />';
       t.innerHTML='<table><tr><td align="right">Login</td><td>'+user+'</td></tr><tr><td align="right">Password</td><td>'+pass+'</td></tr></table>';
   }
}

function getCookieVal (offset) {
  var endstr = document.cookie.indexOf (";", offset);
  if (endstr == -1)
  endstr = document.cookie.length;
  return unescape(document.cookie.substring(offset, endstr));
}

function GetCookie (name) {
  var arg = name + "=";
  var alen = arg.length;
  var clen = document.cookie.length;
  var i = 0;
  while (i < clen) {
    var j = i + alen;
    if (document.cookie.substring(i, j) == arg)
    return getCookieVal (j);
    i = document.cookie.indexOf(" ", i) + 1;
    if (i == 0) break;
  }
  if(name=='DBSDD_username') {
     return "guest";
  }
  return null;
}

function SetCookie (name, value) {
  var argv = SetCookie.arguments;
  var argc = SetCookie.arguments.length;
  var expires = (argc > 2) ? argv[2] : null;
  var path = (argc > 3) ? argv[3] : null;
  var domain = (argc > 4) ? argv[4] : null;
  var secure = (argc > 5) ? argv[5] : false;
  document.cookie = name + "=" + escape (value) +
   ((expires == null) ? "" : ("; expires=" + expires.toGMTString())) +
   ((path == null) ? "" : ("; path=" + path)) +
   ((domain == null) ? "" : ("; domain=" + domain)) +
   ((secure == true) ? "; secure" : "");
}

function set_name(form) {
  var expdate = new Date ();
  // set expiration = 1 year
  // 1 year = 365 days
  // 1 day  = 24 hours
  // 1 hour = 60 min
  // 1 min  = 60 sec
  // 1 sec  = 1000 msec
  var expTime= 1000*60*60*24*365;
  expdate.setTime (expdate.getTime() + expTime);
  var username = form.nameinput.value
  var password = form.passinput.value
  if (username != "") {
      SetCookie ("DBSDD_username", username, expdate);
      SetCookie ("DBSDD_password", password, expdate);
//      window.history.go(0);
  } else {
    alert("Geez, at least enter something, entering nothing will cause an error.");
  }
//  var id=document.getElementById("formAuthResults");
//  msg='<p><div class="box_blue">Thank you '+username+'! Your authentication is stored and you may use your history at any time.</span></p>'; 
//  return msg;
}
// Global variables
var GLOBAL_CELL='cell_1';
//var GLOBAL_STEP=5;
//var DBSDD='http://cmsdbs.cern.ch/discovery/';
//var DBSDD_EXPERT=DBSDD+'expert';

function loadMasthead() {
  try {
    insertMastHead('dbs','')
  } catch(err) {
//    txt="There was an error during masthead loading.\n\n";
//    txt+="Error description: " + err.description + "\n\n";
//    txt+="Click OK to continue.\n\n";
//    alert(txt);
  }
}

// See http://www.javascriptkit.com/javatutors/navigator.shtml
function CheckBrowser() {
  var browserName=navigator.appName; 
  var browserVer=parseInt(navigator.appVersion); 
  var browserAgent=navigator.userAgent;
  return browserName+' '+browserVer+' '+browserAgent;
}

function SetMain() {
  var id=document.getElementById("main");
  if (id) {
      id.className="main";
  }
}
function HideTag(tag) {
  var id=document.getElementById(tag);
  if (id) {
      id.className="hide";
  }
}
function ClearTag(tag) {
  var id=document.getElementById(tag);
  if (id) {
      id.innerHTML="";
  }
}
function ShowTag(tag) {
  var id=document.getElementById(tag);
  if (id) {
      id.className="show_inline";
  }
}
function ChangeNameTags(tag,className) {
  var sel=document.getElementsByName(tag);
  for(i=0;i<sel.length;i++) {
      if (sel[i]) {
          sel[i].className=className;
      }
  }
}
function ShowProcDetails()  {
   browser=CheckBrowser();
   var procNames=new Array;
   if(browser.match('IE')) {
      var tags=document.getElementsByTagName('div');
      for(i=0;i<tags.length;i++) {
          if(tags[i].name=='detailsTables') {
             tags[i].className='show_inline';
          }
          if(tags[i].name=='summaryTables') {
             tags[i].className='hide';
          }
      }
      var tags=document.getElementsByTagName('table');
      for(i=0;i<tags.length;i++) {
          if(tags[i].name=='_detailsTable') {
             tags[i].className='td_underline';
          }
          if(tags[i].name=='_summaryTable') {
             tags[i].className='td_plain';
          }
      }
   }  else {
      ChangeNameTags('detailsTables','show_inline');
      ChangeNameTags('summaryTables','hide');
      ChangeNameTags('_summaryTable','td_plain');
      ChangeNameTags('_detailsTable','td_underline');
   }
}
function HideProcDetails()  {
   browser=CheckBrowser();
   var procNames=new Array;
   if(browser.match('IE')) {
      var tags=document.getElementsByTagName('div');
      for(i=0;i<tags.length;i++) {
          if(tags[i].name=='detailsTables') {
             tags[i].className='hide';
          }
          if(tags[i].name=='summaryTables') {
             tags[i].className='show_inline';
          }
      }
      var tags=document.getElementsByTagName('table');
      for(i=0;i<tags.length;i++) {
          if(tags[i].name=='_detailsTable') {
             tags[i].className='td_plain';
          }
          if(tags[i].name=='_summaryTable') {
             tags[i].className='td_underline';
          }
      }
   }  else {
      ChangeNameTags('detailsTables','hide');
      ChangeNameTags('summaryTables','show_inline');
      ChangeNameTags('_summaryTable','td_underline');
      ChangeNameTags('_detailsTable','td_plain');
   }
}
function ShowSumTablesDetails()  {
   browser=CheckBrowser();
   var procNames=new Array;
   if(browser.match('IE')) {
      var tags=document.getElementsByTagName('div');
      for(i=0;i<tags.length;i++) {
          if(tags[i].name=='summaryTables') {
             tags[i].className='show_inline';
          }
      }
   }  else {
      ChangeNameTags('summaryTables','show_inline');
   }
}
function HideSumTablesDetails()  {
   browser=CheckBrowser();
   var procNames=new Array;
   if(browser.match('IE')) {
      var tags=document.getElementsByTagName('div');
      for(i=0;i<tags.length;i++) {
          if(tags[i].name=='summaryTables') {
             tags[i].className='hide';
          }
      }
   }  else {
      ChangeNameTags('summaryTables','hide');
   }
}
function ResetTag(tag) {
  ClearTag(tag);
  ShowTag(tag);
}
function ResetAllResults() {
  ClearTag('progressBar');
  ResetTag('results_index');
  ResetTag('results');
  ResetTag('results_waiting');

  ClearTag('results_kw');
  ClearTag('results_dbs');
  ClearTag('results_site');
  ClearTag('results_finder');
  ClearTag('runs');
  ClearTag('parents');
  ClearTag('appConfigs');

  HideTag('results_kw');
  HideTag('results_dbs');
  HideTag('results_site');
  HideTag('runs');
  HideTag('parents');
  HideTag('appConfigs');
//  showWaitingMessage('results');
}
function showFinderMenu(name,histArr) {
  var id=document.getElementById("finder_table");
  id.className="show_table";
  for(i=0;i<histArr.length;i++) {
      var _id=document.getElementById('_'+histArr[i]+'Finder'); // menu's
      var  id=document.getElementById(histArr[i]+'Finder');     // content's
      if (histArr[i]==name) {
          _id.className="td_right";
           id.className="show_inline";
      } else {
          _id.className="show_cell";
           id.className="hide";
      }
  }
}
function showHistoryMenu(name,histArr) {
  var id=document.getElementById("history_table");
  id.className="show_table";
  for(i=0;i<histArr.length;i++) {
      var _id=document.getElementById('_'+histArr[i]+'History'); // menu's
      var  id=document.getElementById(histArr[i]+'History');     // content's
      if (histArr[i]==name) {
          _id.className="td_right";
           id.className="show_inline";
      } else {
          _id.className="show_cell";
           id.className="hide";
      }
  }
}
function showHelpContent() {
  var t=document.getElementById("help_intro");
  t.className="show_inline";
}
// Menu array
var _ids = new Array();
_ids[0]='results';
_ids[1]='results_dbs';
_ids[2]='runs';
_ids[3]='parents';
_ids[4]='appConfigs';
_ids[5]='dataDescription';
function showResMenu(id,ids) {
   if(!ids) {
      ids=_ids;
   }
   for(var i=0;i<ids.length;i++) {
      if(ids[i]==id) {
        var t=document.getElementById("_"+id);
        if(t) {
           t.className="td_menu_white_box";
        }
        var r=document.getElementById(id);
        if(r) {
           r.className="show_inline";
        }
      } else {
        var t=document.getElementById("_"+ids[i]);
        if(t) {
           t.className="td_menu_gray_box";
        }
        var r=document.getElementById(ids[i]);
        if(r) {
           r.className="hide";
        }
      }
   }
}
function hideResMenu(ids) {
   if(!ids) {
      ids=_ids;
   }
   for(var i=0;i<ids.length;i++) {
      /*
      var t=document.getElementById("_"+ids[i]);
      if(t) {
         t.className="hide";
      }
      */
      var r=document.getElementById(ids[i]);
      if(r) {
         r.className="hide";
      }
   }
}
function clearResMenu(ids) {
   if(!ids) {
      ids=_ids;
   }
   for(var i=0;i<ids.length;i++) {
      var t=document.getElementById("_"+ids[i]);
      if(t) {
         if(ids[i]=="results") {
           t.className="td_menu_white_box";
         } else {
           t.className="td_menu_gray_box";
         }
      }
      var r=document.getElementById(ids[i]);
      if(r) {
         r.innerHTML="";
      }
   }
}

function showLoadingMessage(idTag,iMsg) {
  var tag, msg;
  if(idTag) {
    tag=idTag;
  } else {
    tag="results_waiting";
  }
  if(iMsg) {
    msg=iMsg;
  } else {
    msg="Please wait, while we retrieve your data"
  }
  var res=document.getElementById(tag);
  if(res) {
//     res.className="show_table";
     res.innerHTML='<table><tr><td><img src="images/loading.gif" /></td><td>'+msg+'</td></tr></table>';
  }
}
function showWaitingMessage() {
  clearResMenu();
  showLoadingMessage("results_waiting");
  var hr=document.getElementById("results_hr");
  if(hr) {
     hr.className="hr_end";
  }
}
function clearWaitingMessage() {
  var res=document.getElementById("results_waiting");
  if(res) {
     res.innerHTML="";
  }
}
function hideWaitingMessage() {
  var res=document.getElementById("results_waiting");
  if(res) {
     res.className="hide";
  }
}
function ShowWheel(tag) {
  var t=document.getElementById(tag);
  if (t) {
      t.innerHTML='<table class="image"><tr><td><img src="images/loading.gif" /></td></tr></table>';
  }
}
function HideWheel(tag) {
  var t=document.getElementById(tag);
  if (t) {
      t.innerHTML='<table class="image"><tr><td></td></tr></table>';
  }
}
function HideParents(id) {
//   var t=document.getElementById(id);
   var t=document.getElementById('parentGraph');
   if(t) {
      t.className="hide";
   }
}
function ShowPanel(link) {
   var t=document.getElementById("HiddenPanel");
   if(t) {
      t.className="hide";
   } else {
      alert("Fail to find HiddenPanel");
   }
   var t=document.getElementById("menu_td_fixed");
   t.className="menu_td_gray_fixed";
   var p=document.getElementById("GlobalPanel");
   if(p) {
      p.className="show";
   } else {
      alert("Fail to find GlobalPanel");
   }
}
function HidePanel(link) {
   msg='<table width="100%"><tr><td class="menu_td_gray"><table width="100%"><tr><td class="td_gray_box_100"><a href="javascript:ShowPanel(\''+link+'\')">show panel</a></td><td></td></tr></table> </td></tr></table>';
   var t=document.getElementById("HiddenPanel");
   if(t) {
      t.className="show";
      t.innerHTML=msg;
   } else {
      alert("Fail to find HiddenPanel");
   }
   var t=document.getElementById("menu_td_fixed");
   t.className="menu_td_gray";
   var p=document.getElementById("GlobalPanel");
   if(p) {
      p.className="hide";
   } else {
      alert("Fail to find GlobalPanel");
   }
}
function ShowPanel_old(link) {
   var url='Home page: <a href="'+link+'/">users</a>, <a href="'+link+'/expert">experts</a>';
   msg='<table width="100%"><tr><td><span class="sectionhead_tight">HIDE NAVIGATION <a href="javascript:HidePanel(\''+link+'\')">PANEL</a></span></td><td align="center"><span class="sectionhead_tight">DBS/DLS DATA DISCOVERY PAGE</span></td><td align="right">'+url+'</td></tr></table>';
   var t=document.getElementById("HiddenPanel");
   if(t) {
      t.innerHTML=msg;
   } else {
      alert("Fail to find HiddenPanel");
   }
   var p=document.getElementById("GlobalPanel");
   if(p) {
      p.className="show";
   } else {
      alert("Fail to find GlobalPanel");
   }
}
function HidePanel_old(link) {
   var url='Home page: <a href="'+link+'/">users</a>, <a href="'+link+'/expert">experts</a>';
   msg='<table width="100%"><tr><td><span class="sectionhead_tight">SHOW NAVIGATION <a href="javascript:ShowPanel(\''+link+'\')">PANEL</a></span></td><td align="center"><span class="sectionhead_tight">DBS/DLS DATA DISCOVERY PAGE</span></td><td align="right">'+url+'</td></tr></table>';
   var t=document.getElementById("HiddenPanel");
   if(t) {
      t.innerHTML=msg;
   } else {
      alert("Fail to find HiddenPanel");
   }
   var p=document.getElementById("GlobalPanel");
   if(p) {
      p.className="hide";
   } else {
      alert("Fail to find GlobalPanel");
   }
}
function hideResultsMenu() {
   var t=document.getElementById("results_menu");
   if(t) {
      t.className="hide";
   }
   var t=document.getElementById("hr_results_menu");
   if(t) {
      t.className="hide";
   }
   var t=document.getElementById("results_hr");
   if(t) {
      t.className="hr_end";
   }
   clearResMenu();
}
function showResultsMenu() {
   var t=document.getElementById("results_menu");
   if(t) {
      t.className="show_table";
//      t.className="show_inline";
//      t.className="table_round_box";
   }
   var t=document.getElementById("hr_results_menu");
   if(t) {
      t.className="dbs";
   }
}
function showMenu(menu) {
   ClearTag('progressBar');
   ClearTag('navBar');
   HideTag('results_finder');
   hideWaitingMessage();
   hideResultsMenu();

   var menuArr = new Array();
   menuArr[0]='Navigator';
   menuArr[1]='Search';
   menuArr[2]='Site';
   menuArr[3]='Summary';
   menuArr[4]='DBSinfo';
   menuArr[5]='History';
   menuArr[6]='Help';
   menuArr[7]='Rss';
   menuArr[8]='Lucene';
   menuArr[9]='SQL';
   menuArr[10]='Hide';
   for(var i=0;i<menuArr.length;i++) {
       var c=document.getElementById(menuArr[i]+'_Menu');
       if (c) {
//           c.className="td_gray_box";
           c.className="";
           if(menuArr[i]=='DBSinfo') {
              var id=document.getElementById("dbsInst_table");
              id.className="hide";
           }
           if(menuArr[i]=='History') {
              var id=document.getElementById("history_table");
              id.className="hide";
           }
           if(menuArr[i]=='Search') {
              var id=document.getElementById("finder_table");
              id.className="hide";
           }
       }
       var t=document.getElementById(menuArr[i]+'Div');
       if (t) {
           if(menuArr[i]==menu) {
               t.className="show_inline";
           } else {
               t.className="hide";
           }
       }
   }
   var t=document.getElementById(menu+"_Menu");
   if (t) {
//       t.className="td_select_box"
       t.className="selected";
   }
//   hideResMenu();
}
function underlineLink(tag) {
  var tagArr = new Array();
  tagArr[0]="Blocks";
  tagArr[1]="Summary";
  tagArr[2]="Both";
  for(i=0;i<tagArr.length; i++) {
      var id=document.getElementsByName("td_"+tagArr[i]);
      for(var j=0;j<id.length;j++) {
          if(tagArr[i]==tag) {
             id[j].className="td_underline";
          } else {
             id[j].className="td_plain"
          }
      }
  }
}
function switchLink(tag,tableId) {
  var _tag;
  if(tag=='_det') {_tag='_sum'}
  if(tag=='_sum') {_tag='_det'}

  var id=document.getElementById(tag+'_'+tableId);
  if (id) {
      id.className="td_underline";
  }
  var id=document.getElementById(_tag+'_'+tableId);
  if (id) {
      id.className="td_plain";
  }
}
function SwitchToSummary(id) {
   HideTag('det_table_'+id);
   ShowTag('sum_table_'+id);
   switchLink('_sum','table_'+id);
}
function SwitchToDetails(id) {
   ShowTag('det_table_'+id);
   HideTag('sum_table_'+id);
   switchLink('_det','table_'+id);
}
function whereUsers() {
  var tagArr = new Array('homeUser','homeExpert','homeDBS');
  href=window.location.href;
  var tag='homeUser';
  if (href.indexOf('expert')>0) {
      tag='homeExpert';
  }
  if (href.indexOf('dbsExpert')>0) {
      tag='homeDBS';
  }
  for(i=0;i<tagArr.length;i++) {
      var id=document.getElementById(tagArr[i]);
      if (id) {
          if (tag==tagArr[i]) {
              id.className="td_underline_pad";
          } else {
              id.className="td_plain";
          }
      }
  }
}
/*
function ShowBlockInfo(tableId){
  underlineLink("Both");
  var elem=document.getElementsByName("blockInfo");
  for(var i=0;i<elem.length;i++) {
      elem[i].className="show_cell";
  }
  var elem=document.getElementsByName("row_blockInfo");
  for(var i=0;i<elem.length;i++) {
      elem[i].className="show_row";
  }
}
function HideBlockInfo(tableId){
  var elem=document.getElementsByName("blockInfo");
  for(var i=0;i<elem.length;i++) {
      elem[i].className="hide";
  }
  var elem=document.getElementsByName("row_blockInfo");
  for(var i=0;i<elem.length;i++) {
      elem[i].className="hide";
  }
}
function ShowSumInfo(tableId) {
  underlineLink("Summary");
  var elem=document.getElementsByName("row_sumInfo");
  for(var i=0;i<elem.length;i++) {
      elem[i].className="show_row";
  }
}
function HideSumInfo(tableId) {
  var elem=document.getElementsByName("row_sumInfo");
  for(var i=0;i<elem.length;i++) {
      elem[i].className="hide";
  }
}
*/
function MakeUnSortable(tableId) {
  var t=document.getElementsByName("MakeSortableText");
  for(var i=0;i<t.length;i++) {
      t[i].innerHTML='Make table <a href="javascript:MakeSortable(\''+tableId+'\')">sortable</a>';
  }
  var tbl=document.getElementById(tableId)
  tbl.className="unsortable"
  ts_makeUnSortable(tbl)
}
function MakeSortable(tableId) {
  var t=document.getElementsByName("MakeSortableText");
  for(var i=0;i<t.length;i++) {
      t[i].innerHTML='Make table <a href="javascript:MakeUnSortable(\''+tableId+'\')">unsortable</a>';
  }
  var tbl=document.getElementById(tableId)
  tbl.className="sortable"
  var tbl_tr=document.getElementById("tr"+tableId)
  tbl_tr.className="sortable_gray"
  ts_makeSortable(tbl)
}
function UnSelectAll(){
  var t=document.getElementById("SelectionHandler");
  t.innerHTML='Select <a href="javascript:SelectAll()">all</a>'
  var elem=document.getElementsByName("userSelection");
  for(var i=0;i<elem.length;i++) {
      elem[i].checked=false;
  }
}
function SelectAll(){
  var t=document.getElementById("SelectionHandler");
  t.innerHTML='Clear <a href="javascript:UnSelectAll()">selection</a>'
  var elem=document.getElementsByName("userSelection");
  for(var i=0;i<elem.length;i++) {
      elem[i].checked=true;
  }
}
function popUp(URL,WIDTH,HEIGHT) {
  var day = new Date();
  var id = day.getTime();
  var w=640;
  var h=480;
  if(WIDTH) {
     w=WIDTH;
  }
  if(HEIGHT) {
     h=HEIGHT;
  }
  // we need to replace in URL the # sign since it's part of blockName
  var url=URL.replace('#','%23');
//  eval("page" + id + " = window.open(url, '" + id + "', 'toolbar=0,scrollbars=1,location=0,statusbar=0,menubar=0,resizable=1,width='+w+',height='+h+',left = 190,top = 220');");
  var winName='page'+id;
  var page = window.open(url,winName, 'toolbar=1,scrollbars=1,location=0,statusbar=0,menubar=0,resizable=1,width='+w+',height='+h+',left = 190,top = 220');
  var j = page.document.getElementById('_snapshot');
  if (j) {
      j.innerHTML='test';
  }
}
function formPopUpCall(h,f,dbs,site,app,prim,tier) {
  var url=h+'/'+f+'?dbsInst='+dbs+'&site='+site+'&app='+app+'&primD='+prim+'&tier='+tier;
  popUp(url);
}
function replace(tag,msg) {
  var id=document.getElementById(tag);
  if(tag) {
     if(msg) {
       id.innerHTML=msg;
     } else {
       id.innerHTML='';
     }
  }
}
function resetNavSelection() {
  var sel=document.getElementById("dbsSelector");
  for(i=0;i<sel.length;i++) {
      if(!sel[i].value) {
         sel[i].selected="selected";
         return;
      }
  }
}
function resetPhysGroups() {
  var id=document.getElementById('kw_group');
  for(i=0;i<id.length;i++) {
     if(id[i].value=='Select') {
        id[i].selected="selected";
     }
  }
}
function CheckSel(sel) {
  var opt=null;
  if(!sel) {return opt;}
  for(i=0;i<sel.length;i++) {
      if(sel[i].selected) {
         opt=sel[i].value;
         return opt;
      }
  }
  return opt;
}
function checkNavSelection() {
  var id=document.getElementById("navSelector");
  var sel=document.getElementById("dbsSelector");
  var dbs=CheckSel(sel);
  if(!dbs) {
     id.innerHTML='<span class="box_red">Please select <b>DBS instance</b></span>';
     return null;
  }
  var sel=document.getElementById("appSelector");
  var app=CheckSel(sel);
  if(!app) {
     id.innerHTML='<span class="box_red">Please select <b>Application</b></span>';
     return null;
  }
  var sel=document.getElementById("primSelector");
  var prim=CheckSel(sel);
  if(!prim) {
     id.innerHTML='<span class="box_red">Please select <b>Primary dataset</b></span>';
     return null;
  }
  var sel=document.getElementById("tierSelector");
  var tier=CheckSel(sel);
  if(!tier) {
     id.innerHTML='<span class="box_red">Please select <b>Data tier</b></span>';
     return null;
  }
  showResMenu('results');
  showWaitingMessage();
  return 1;
}
/*
function submitNavRequest() {
  if(checkNavSelection()==1) {
     ajaxGetData();
     ajaxGenParentsGraph();
     ajaxGenAppConfigs();
  }
}
*/
function GetMonthIdx(month) {
  var mArr = new Array('Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec');
  for(i=0;i<mArr.length;i++) {
      if(mArr[i]==month) {
         return i;
      }
  }
}
function AdjustToDate() {
  var iSel_y= document.getElementById("in_hSearch_year");
  var iYear = CheckSel(iSel_y);
  var iSel_m= document.getElementById("in_hSearch_month");
  var iMonth= CheckSel(iSel_m);
   
  var oSel_y= document.getElementById("out_hSearch_year");
  var oYear = CheckSel(oSel_y);
  var oSel_m= document.getElementById("out_hSearch_month");
  var oMonth= CheckSel(oSel_m);

  // adjust 'To: year/month' from the input 'From: year/month'
  if(iSel_y && oSel_y) {
      for(i=0;i<iSel_y.length;i++) {
          if(iSel_y[i].selected) {
             oSel_y[i].selected="selected";
          }
      }
  }
  if(iSel_m && oSel_m) {
      var i_idx, o_idx;
      for(i=0;i<iSel_m.length;i++) {
          if(iSel_m[i].selected) {
             i_idx=i;
             break;
          }
      }
      for(i=0;i<oSel_m.length;i++) {
          if(oSel_m[i].selected) {
             o_idx=i;
             break;
          }
      }
      if(GetMonthIdx(oSel_m[o_idx].value)<GetMonthIdx(iSel_m[i_idx].value)) {
         oSel_m[o_idx].selected=null;
         oSel_m[i_idx].selected="selected";
      }
  }
}
function CheckToDate() {
  var iSel_y= document.getElementById("in_hSearch_year");
  var iYear = CheckSel(iSel_y);
  var iSel_m= document.getElementById("in_hSearch_month");
  var iMonth= CheckSel(iSel_m);
   
  var oSel_y= document.getElementById("out_hSearch_year");
  var oYear = CheckSel(oSel_y);
  var oSel_m= document.getElementById("out_hSearch_month");
  var oMonth= CheckSel(oSel_m);
  
  var id=document.getElementById("historySearchResults");
  if(oYear<iYear || GetMonthIdx(oMonth)<GetMonthIdx(iMonth) ) {
     id.innerHTML='<span class="box_red">You choose wrong date order, "From" date should be >= then "To" date</span>';
  } else {
    if(id.innerHTML.search("You choose wrong date order")) {
       id.innerHTML="";
    }
  }
}
function DisableSel(tag) {
  var sel=document.getElementById(tag);
  if(sel) {
     sel.disabled="disabled";
  }
}
function EnableSel(tag) {
  var sel=document.getElementById(tag);
  if(sel) {
     sel.disabled="";
  }
}
function CoverOver(tag) {
   var id=document.getElementById(tag);
   if(id) {
      if(id.className!="choosen") {
         id.className="over";
      }
   }
}
function CoverOut(tag) {
   var id=document.getElementById(tag);
   if(id) {
      if(id.className!="choosen") {
         id.className="fixed";
      }
   }
}
function ClearCellTag(tag,i,idx) {
      if( i==idx ) { 
          var id=document.getElementById(tag);
          if(id) {
             id.className="show_inline";
          }
      } else {
          var id=document.getElementById(tag);
          if(id) {
             id.className="hide";
          }
      }
}
function ClearCells(idx,total) {
   for(i=0;i<=total;i++) {
      var id=document.getElementById('cell_'+i);
      if(id) {
         id.className="fixed";
      }
      var tagArr = new Array('results_response_'+i,'results_dbs_response_'+i,'runs_response_'+i,'parents_response_'+i,'appConfig_response_'+i);
      for(j=0;j<tagArr.length;j++) {
          tag=tagArr[j];
          ClearCellTag(tag,i,idx)
      }
   }
   Choose('cell_'+(idx+1));
}
/*
 * JumpToResult accept idx which runs from 1-max on a web page
 * but it should send idx-1 to getData since all response are from 0-max-1
 */
function JumpToResult(idx,total,dbs,site,group,app,prim,tier,proc) {
   UpdateResultIndex(idx,total);
   // invoke next chunk of data
   advanceResults((idx-1),total,dbs,site,group,app,prim,tier,proc);
   var found=0;
   for(i=0;i<total;i++) {
      var id=document.getElementById('cell_'+i);
      if (id) {
          id.className="fixed";
      }
      var tagArr = new Array('results_response_'+i,'results_dbs_response_'+i,'runs_response_'+i,'parents_response_'+i,'appConfig_response_'+i);
      for(j=0;j<tagArr.length;j++) {
          tag=tagArr[j];
          if( i==(idx-1) ) { 
              var id=document.getElementById(tag);
              if (id) {
                 found=1;
                 ClearCells((idx-1),total)
                 Choose('cell_'+idx);
                 break;
              }
          }
      }
      if(found) {break;}

   }
   Choose('cell_'+idx);
   if(!found) {
      ShowWheel("__results");
      ShowWheel("__results_dbs");
      ShowWheel("__runs");
      ajaxNextGetData(dbs,site,group,app,prim,tier,proc,idx-1);
   }
}
function advanceResults(idx,total,dbs,site,group,app,prim,tier,proc) {
   if (idx+1!=total) {
       var id=document.getElementById('results_response_'+(idx+1));
       if (!id) {
           ShowWheel("__results");
           ShowWheel("__results_dbs");
           ShowWheel("__runs");
           ajaxNextGetData(dbs,site,group,app,prim,tier,proc,idx+1);;
       }
   }
}
function nPages(tot,max) {
    if(tot%max) {
       return (tot-tot%max)/max+1;
    }
    return tot/max
}
function GetCurrentIndexPosition(total) {
   return GLOBAL_CELL.split('cell_')[1];
}
function BuildBar(from,to,total,GLOBAL_STEP,dbs,site,group,app,prim,tier,proc) {
   args=',\''+dbs+'\',\''+site+'\',\''+group+'\',\''+app+'\',\''+prim+'\',\''+tier+'\',\''+proc+'\'';
   var t='<table class="cell"><tr><td>Result pages:</td>';
   var td='<td class="fixed" id="cell_start" onMouseOver="CoverOver(\'cell_start\')" onMouseOut="CoverOut(\'cell_start\')"><a href="javascript:JumpToResult('+1+','+total+args+');BuildBar(1,'+GLOBAL_STEP+','+total+','+GLOBAL_STEP+args+')">start</a></td>';
   t=t+td;
   if(to>total) {
      to=total;
   }
   if(from<1) {
      from=1;
      if(to<GLOBAL_STEP) {
         to=GLOBAL_STEP;
      }
   }
   if(from!=1) {
      var backFrom=to-GLOBAL_STEP;
      var backTo=to-1;
      var td='<td class="fixed" id="cell_less" onMouseOver="CoverOver(\'cell_less\')" onMouseOut="CoverOut(\'cell_less\')"><a href="javascript:BuildBar('+backFrom+','+backTo+','+total+','+GLOBAL_STEP+args+');JumpToResult('+backFrom+','+total+args+')">&#171;</a></td>';
      t=t+td;
   } else {
      var td='<td class="fixed">&#32;</td>';
      t=t+td;
   }
   for(i=from;i<=to;i++) {
      var className='class="fixed"';
      if(('cell_'+i)==GLOBAL_CELL) {
         className='class="choosen"';
      }
      var td='<td '+className+' id="cell_'+i+'" onMouseOver="CoverOver(\'cell_'+i+'\')" onMouseOut="CoverOut(\'cell_'+i+'\')"><a href="javascript:JumpToResult('+i+','+total+args+')">'+i+'</a></td>';
      t=t+td;
   }
   if(to!=total) {
      var nextFrom=from+1;
      var nextTo=from+GLOBAL_STEP;
      var td='<td class="fixed" id="cell_more" onMouseOver="CoverOver(\'cell_more\')" onMouseOut="CoverOut(\'cell_more\')"><a href="javascript:BuildBar('+nextFrom+','+nextTo+','+total+','+GLOBAL_STEP+args+');JumpToResult('+nextFrom+','+total+args+')">&#187;</a></td>';
      t=t+td;
   } else {
      var td='<td class="fixed">&#32;</td>';
      t=t+td;
   }
   var td='<td class="fixed" id="cell_end" onMouseOver="CoverOver(\'cell_end\')" onMouseOut="CoverOut(\'cell_end\')"><a href="javascript:JumpToResult('+total+','+total+args+');BuildBar('+(total-GLOBAL_STEP)+','+total+','+total+','+GLOBAL_STEP+args+')">end</a></td>';
   t=t+td;
   t=t+'</tr></table>';
   var id=document.getElementById('progressBar');
   if(id) {
      id.innerHTML='<hr class="dbs" />'+t;
   }
}
function Choose(tag) {
   GLOBAL_CELL=tag;
   var id=document.getElementById(tag);
   if (id) {
      id.className="choosen";
   }
}
function UpdateResultIndex(idx,tot) {
   var id=document.getElementById('results_index');
   if (id) {
      id.className=idx+"_"+tot;
   }
}
function ShowPageResults() {
   var id=document.getElementById('results_index');
   if (id) {
       var s = id.className; 
       var arr = s.split("_");
       var idx = arr[0];
       var tot = arr[1];
       ClearCells(idx-1,tot);
   }
}
function BuildBar_orig(from,to,total,ref) {
   var t='<table class="cell"><tr><td>Result pages:</td>';
   if(to>total) {
      to=total;
   }
   if(from<1) {
      from=1;
      if(to<5) {
         to=5;
      }
   }
   if(from!=1) {
      var backFrom=from-5;
      var backTo=from-1;
      var td='<td id="cell_less" onMouseOver="CoverOver(\'cell_less\')" onMouseOut="CoverOut(\'cell_less\')"><a href="javascript:BuildBar('+backFrom+','+backTo+','+total+')">&#171;</a></td>';
      t=t+td;
   }
   for(i=from;i<=to;i++) {
      var td='<td id="cell_'+i+'" onMouseOver="CoverOver(\'cell_'+i+'\')" onMouseOut="CoverOut(\'cell_'+i+'\')">'+refForBar(ref,i)+i+'</a></td>';
      t=t+td;
   }
   if(to!=total) {
      var nextFrom=to+1;
      var nextTo=to+5;
      var td='<td id="cell_more" onMouseOver="CoverOver(\'cell_more\')" onMouseOut="CoverOut(\'cell_more\')"><a href="javascript:BuildBar('+nextFrom+','+nextTo+','+total+')">&#187;</a></td>';
      t=t+td;
   }
   t=t+'</tr></table>';
   var id=document.getElementById('progressBar');
   if(id) {
      id.innerHTML=t;
   }
}
function SearchForJSCode(text) {
   var pattern1='<script type="text\/javascript">';
   var pattern2='<script type=\'text\/javascript\'>';
   var end='<\/script>';
   var foundCode=SearchForCode(text,pattern1,end);
   foundCode=foundCode+SearchForCode(text,pattern2,end);
   return foundCode;
}
function SearchForCode(text,begPattern,endPattern) {
   var foundCode='';
   while( text && text.search(begPattern) ) {
       var p=text.split(begPattern);
       for(i=1;i<p.length;i++) {
           var n=p[i].split(endPattern);
           foundCode=foundCode+n[0]+';\n';
       }
       return foundCode;
   }
   return foundCode;
}
function DivideName(name) {
  var n='';
  while(1) {
      if(name.length>30) {
         n=n+name.substring(0,30)+' ';
         name=name.substring(31,name.length);
      } else {
        break;
      }
  }
  return n;
}
function SplitBlockName() {
  var arr=document.getElementsByName("fullBlockName");
  for(i=0;i<arr.length;i++) {
      var bName = arr[i].innerHTML;
      arr[i].innerHTML=divideName(bName);
  }
}
function PutKeyword(key) {
  var id=document.getElementById('keywordSelector');
  if(id) {
     var v = id.value;
     if(v!='...') {
        id.value=v+key+':\"<value>\" AND ';
     }
  }
}
function ClearKeywordsSelector() {
  var id=document.getElementById('keywordSearch_keywordsSelector');
  if (id) {
      var childrens=id.childNodes;
      for(i=0;i<id.childNodes.length;i++) {
          if(id.childNodes[i].value=='...') {
             id.childNodes[i].selected="selected";
          } else {
             id.childNodes[i].selected="";
          }
      } 
  } 
}
function ClearKeywordsInputValue() {
  var id=document.getElementById('keywordSelector');
  if(id) {
     id.value="";
  }
}
function KeywordHelp(tag,help,_cName) {
  var className='float_yellow_box';
  if(_cName) {
      className=_cName;
  }
  var id=document.getElementById(tag);
  if(id) {
     id.innerHTML='<div class="'+className+'">'+help+'</div>';
  }
}
function GetValue(tag) {
  var id=document.getElementById(tag);
  if(id) {
     return id.value;
  }
  return;
}
function ClearTextInForm(tag) {
  var id=document.getElementById(tag);
  if (id) {
      id.text="";
  }
}
function ClearKeywordForm() {
  ClearTextInForm('kw_algoSelector');
  ClearTextInForm('kw_primSelector');
  ClearTextInForm('kw_procSelector');
  ClearTextInForm('kw_tierSelector');
  ClearTextInForm('kw_runsSelector');
}
function GenSnapshot() {
  var msg='<div class="sectionhead">RESULTS FOR:</div>';
  msg=msg+'<table class="small" bgcolor="#DDDDDD">';
  var id=document.getElementById('snapshot_dbsInst');
  if (id) {
      msg=msg+'<tr><td align="right"><b>DBS instance:</b></td><td>'+id.value+'</td></tr>';
  }
  var id=document.getElementById('snapshot_site');
  if (id) {
      msg=msg+'<tr><td align="right"><b>Site: </b></td><td>'+id.value+'</td></tr>';
  }
  var id=document.getElementById('snapshot_app');
  if (id) {
      msg=msg+'<tr><td align="right"><b>Application: </b></td><td>'+id.value+'</td></tr>';
  }
  var id=document.getElementById('snapshot_prim');
  if (id) {
      msg=msg+'<tr><td align="right"><b>Primary dataset: </b></td><td>'+id.value+'</td></tr>';
  }
  var id=document.getElementById('snapshot_tier');
  if (id) {
      msg=msg+'<tr><td align="right"><b>Data tier: </b></td><td>'+id.value+'</td></tr>';
  }
  var id=document.getElementById('snapshot_proc');
  if (id) {
      msg=msg+'<tr><td align="right"><b>Processed dataset: </b></td><td>'+id.value+'</td></tr>';
  }
  msg=msg+'</table>';
  alert('GenSnapshot msg='+msg);
//  var id=document.getElementById('_snapshot');
//  if (id) {
//alert('Found');
//      id.innerHTML=msg;
//  }
  return msg;
}
function MakeNavBar() {
  var arr  = getDataFromSelectors();
  if(!arr) return;
  var dbs  = arr[0];
  var site = arr[1];
  var app  = arr[2];
  var prim = arr[3];
  var tier = arr[4];
  var group= arr[5];
  var id=document.getElementById('navBar');
  if (id) {
      id.innerHTML='<span class="td_underline" style="padding: 3px 3px 3px 3px;"><b>Menu:Navigator</b></span> &#187; <b>DBS instance:</b>'+dbs+' &#187; <b>Site:</b>'+site+' &#187; <b>Application:</b>'+app+' &#187; <b>Primary Dataset:</b>'+prim+' &#187; <b>Data tier:</b>'+tier;
  }
}
function MakeUserNavBar() {
  var dbs  =$('kw_dbsInstSelector').value
  var group=$('kw_group').value;
  var type =$('kw_tier').value;
  var prim =$('kw_prim').value;
  var rels =$('kw_release').value;
  var site =$('kw_site').value;
  var id=document.getElementById('navBar');
  if (id) {
      id.innerHTML='<span class="td_underline" style="padding: 3px 3px 3px 3px;"><b>Menu:Navigator</b></span> &#187; <b>Physics group:</b>'+group+' &#187; <b>Data types:</b>'+type+' &#187; <b>Software releases:</b>'+rels+' &#187; <b>Trigger Line/MC gens:</b>'+prim+' &#187; <b>Sites:</b>'+site;
  }
}
function MakeNavBarPrimDS(dbs,prim) {
  var id=document.getElementById('navBar');
  if (id) {
      id.innerHTML='<span class="td_underline" style="padding: 3px 3px 3px 3px;"><b>Menu:DBS info</b>:'+dbs+'</span> &#187; <b>Primary dataset:</b>'+prim;
  } 
}
function MakeNavBarProcDS(dbs,proc) {
  var id=document.getElementById('navBar');
  if (id) {
      id.innerHTML='<span class="td_underline" style="padding: 3px 3px 3px 3px;"><b>Menu:DBS info</b>:'+dbs+'</span> &#187; <b>Processed dataset:</b>'+proc;
  } 
}
function MakeNavBarApp(dbs,app) {
  var id=document.getElementById('navBar');
  if (id) {
      id.innerHTML='<span class="td_underline" style="padding: 3px 3px 3px 3px;"><b>Menu:DBS info:</b>'+dbs+'</span> &#187; <b>Application:</b>'+app;
  } 
}
function GetParentNavBar() {
  var id=opener.document.getElementById('navBar');
  if (id) {
      var j=document.getElementById('_snapshot');
      if (j) {
          j.innerHTML=id.innerHTML;
      }
  }
}
function GetTagContent(tag) {
  var j=document.getElementById(tag);
  if (j) {
      return j.innerHTML;
  }
  return '';
}
function ChangeField(tag) {
  var id=document.getElementById(tag);
  if (id) {
      var sel=document.getElementById('sel_'+tag);
      if(sel.value=='Select') {
         id.className="hide";
         return;
      } else {
         id.className="show_inline";
         return;
      }
  }
}
function ChangeWhere(selTag,tag) {
   if($(selTag).value=='None') {
        $(tag).className='hide';
   } else {
        $(tag).className='show_inline';
   }
}
function SelectAll(tag) {
   var sel=document.getElementsByName(tag);
   for(i=0;i<sel.length;i++) {
       sel[i].checked="checked";
   }
}
function CompareAppConfigs(host,dbsInst,rel,fName) {
   // get file name
   var sel=document.getElementById("config_"+fName);
   for(i=0;i<sel.length;i++) {
       if(sel[i].selected) {    
          return popUp(host+'/compareAppConfigs?dbsInst='+dbsInst+'&appConfig='+fName+'&iRel='+rel+'&oRel='+sel[i].value,1000);
       }
   }
}
function ResetAllSelects(dbsInst) {
   ResetNavigator(dbsInst);
   ResetFinder(dbsInst);
   ResetSiteSearch(dbsInst);
   ResetDbsExpert(dbsInst);
}
function ResetSelect(tag,value) {
   var sel=$(tag);
   if (sel) {
       for(i=0;i<sel.length;i++) {
           if(sel[i].value==value) {
              sel[i].selected="selected";
           }  else {
              sel[i].selected="";
           }
       }
   }
}
function ResetNavigator(dbsInst) {
   ResetSelect('kw_dbsInstSelector',dbsInst);
   ajaxGetKWFields();
   ResetSelect('kw_group','Any');
   ResetSelect('kw_tier','Any');
   ResetSelect('kw_release','Any');
   ResetSelect('kw_prim','Any');
   ResetSelect('kw_site','Any');
}
function ResetFinder(dbsInst) {
   ResetSelect('finder_dbsSelector',dbsInst);
   ResetSelect('selSection_1','Algorithm');
   ChangeTables(1);
   ChangeCols(1);
   $('where_1').value="";
}
function ResetSiteSearch(dbsInst) {
   ResetSelect('form2_dbsSelector',dbsInst);
   ajaxGetSites('','form2_dbsSelector','form2_siteHolder','form2_siteSelector');
}
function ResetDbsExpert(dbsInst) {
   ResetSelect('dbsExpert_dbsSelector',dbsInst);
   $('queryText').value="";
}

function AddConfigParameter() {
   
   var sel=$('selectcfgparam');
   var p_name,p_type;
   for(i=0;i<sel.options.length;i++) {
          if(sel.options[i].selected) {
             p_name = '<td valign="middle"><span name="p_name">'+sel.options[i].id+'</span></td>';
             p_type = '<td><span name="p_type" class="hide">'+sel.options[i].value+'</span></td>';
          }
   }
   var op='<td valign="middle"><span name="p_op">'+$('parameterListOperators').value+'</span></td>';
   var val='<td valign="middle"><span name="p_val">'+$('searchInput').value+'</span></td>';
   var pSpace=$('parameterSpace');
   var oldId=$('parameterCounter').value;
   var id='param_'+oldId;
   var minus='<td valign="middle" style="width:30px"><a href="javascript:ClearTag(\''+id+'\')"><img src="images/minus.png" alt="remove" style="border:none" align="top" /></a></td>'
   pSpace.innerHTML=pSpace.innerHTML+'<tr id="'+id+'">'+minus+p_name+p_type+op+val+'</tr>\n';
   $('parameterCounter').value=parseInt(oldId)+1;
}
function CheckOperator() {
   var params=$('selectcfgparam');
   var p_type=0;
   for(i=0;i<params.length;i++) {
       if(params[i].selected) {
          p_type=params[i].value;
          break;
       }
   }
   var sel=$('parameterListOperators');
   for(i=0;i<sel.length;i++) {
      if(p_type==1) {
         if(sel[i].value.match('like')) {
            sel[i].disabled='true';
         } else {
            sel[i].disabled='';
         }
      } else {
         if(sel[i].value.match('like')) {
            sel[i].disabled='';
         } else {
            sel[i].disabled='true';
         }
      } 
   }
}
function load(url) {
  window.location.href=url;
}
function LoadSelected(sel_id) {
   var rval=document.getElementById(sel_id);
   load(rval.options[rval.selectedIndex].value);
}
function LoadGetData(dbsInst,site,group,app,prim,tier,proc,primType,date,idx,ajax,userMode,pagerId,moreParams) {
   var pagerStep=$('pagerStep'+pagerId).value;
   var url='getData?dbsInst='+dbsInst+'&site='+site+'&group='+group+'&app='+app+'&primD='+prim+'&tier='+tier+'&proc='+proc+'&primType='+primType+'&_idx='+idx+'&ajax='+ajax+'&userMode='+userMode+'&pagerStep='+pagerStep+moreParams;
   load(url);
}
function LoadAnalysisDS(dbsInst,idx,ajax,userMode,pagerId) {
   var pagerStep=$('pagerStep'+pagerId).value;
   var url='findAnalysisDS?dbsInst='+dbsInst+'&_idx='+idx+'&ajax='+ajax+'&userMode='+userMode+'&pagerStep='+pagerStep;
   load(url);
}
function LoadGetFileBlocks(dbsInst,site,ajax,userMode,idx,pagerId) {
   var pagerStep=$('pagerStep'+pagerId).value;
   var url='getFileBlocks?dbsInst='+dbsInst+'&site='+site+'&ajax='+ajax+'&userMode='+userMode+'&idx='+idx+'&pagerStep='+pagerStep;
   load(url);
}
function LoadGetRunsFromRange(dbsInst,dataset,minRun,maxRun,idx,ajax,userMode,pagerId) {
   var pagerStep=$('pagerStep'+pagerId).value;
   var url='getRunsFromRange?dbsInst='+dbsInst+'&dataset='+dataset+'&minRun='+minRun+'&maxRun='+maxRun+'&_idx='+idx+'&ajax='+ajax+'&userMode='+userMode+'&pagerStep='+pagerStep;
   load(url);
}
function LoadGetRuns(dbsInst,dataset,idx,ajax,userMode,pagerId) {
   var pagerStep=$('pagerStep'+pagerId).value;
   var url='getRuns?dbsInst='+dbsInst+'&dataset='+dataset+'&_idx='+idx+'&ajax='+ajax+'&userMode='+userMode+'&pagerStep='+pagerStep;
   load(url);
}
function LoadASearch(dbsInst,userMode,fromRow,idx,pagerId,userInput) {
   var pagerStep=$('pagerStep'+pagerId).value;
   var view=GetCookie('DBSDD_view');
   var grid=0;
   if(view=='grid') { grid=1; }
   var sortName=GetCookie('DBSDD_sortName');
   var sortOrder=GetCookie('DBSDD_sortOrder');
   var caseSensitive=GetCookie('DBSDD_caseSensitive');
   var url='aSearch?dbsInst='+dbsInst+'&userMode='+userMode+'&fromRow='+fromRow+'&_idx='+idx+'&pagerStep='+pagerStep+'&userInput='+userInput+'&sortName='+sortName+'&sortOrder='+sortOrder+'&caseSensitive='+caseSensitive+'&grid='+grid;
   load(url);
}
function Disable2EnableTag(tag) {
   if($(tag).disabled) {
      $(tag).disabled='';
      $(tag).setAttribute("class","enabled");
   } else {
      $(tag).disabled='disabled';
      $(tag).setAttribute("class","disabled");
   }
}
function Disable2Enable(tag1,tag2,tag3) {
   if($(tag1).disabled) {
      $(tag1).disabled='';
      $(tag1).setAttribute("class","enabled");
      $(tag2).disabled='disabled';
      $(tag2).setAttribute("class","disabled");
      if(tag3) {
          $(tag3).disabled='disabled';
          $(tag3).setAttribute("class","disabled");
      }
   } else {
      $(tag2).disabled='';
      $(tag2).setAttribute("class","enabled");
      if(tag3) {
          $(tag3).disabled='';
          $(tag3).setAttribute("class","enabled");
      }
      $(tag1).disabled='disabled';
      $(tag1).setAttribute("class","disabled");
   }
}
function D2E_tiers() {
   Disable2Enable('kw_tier','kw_cTier');
}
function D2E_dates() {
   Disable2Enable('kw_date','kw_cDate1','kw_cDate2');
}
function resetUserNav() {
   var val=GetCookie('DBSDD_dbsInst');
   if (val) {
       ResetSelect('kw_dbsInstSelector',val);
   }
   if($('kw_tier')) {
      $('kw_tier').disabled='';
   }
   if($('kw_cTier')) {
      $('kw_cTier').disabled='disabled';
   }
   if($('tierSelector')) {
      $('tierSelector').checked='';
   }
   ajaxGetKWFields();
}
function ExpandADSOpts() {
   ChangeNameTags('adsOpts','show_row');
   $('adsExpander').innerHTML='<a href="javascript:CollapseADSOpts()"><img src="images/downTriangle.png" alt="expand" style="border:none" /></a>';
   $('adsExpanderText').innerHTML='collapse options';
}
function CollapseADSOpts() {
   ChangeNameTags('adsOpts','hide');
   $('adsExpander').innerHTML='<a href="javascript:ExpandADSOpts()"><img src="images/leftTriangle.png" alt="expand" style="border:none" /></a>';
   $('adsExpanderText').innerHTML='expand options';
}
function ReloadMethod(host,method,userMode) {
   var dbsInst=$('dbsInst').value;
   var url=host+'/'+method+'?dbsInst='+dbsInst+'&userMode='+userMode;
   window.location.href=url;
}
function GetProdRequestOutput(id,prim)  {
   $(id).className='show_inline';
   if(!$(id).innerHTML) {
       ajaxEngine.registerAjaxElement(id);
       ajaxGetProdRequest(prim,id);
   }
}
function ShowProdRequestOutput()  {
   browser=CheckBrowser();
   var procNames=new Array;
   if(browser.match('IE')) {
       var tags=document.getElementsByTagName('div');
       for(i=0;i<tags.length;i++) {
           if(tags[i].name=='ProdRequestOutput') {
              tags[i].className="show_inline";
               if(!tags[i].innerHTML) {
                   var id=tags[i].id;
                   var arr=id.split("___");
                   var prim=arr[1]; // PrimaryDataset name
                   ajaxEngine.registerAjaxElement(id);
                   ajaxGetProdRequest(prim,id);
               }
           }
       }
   } else {
       procNames=document.getElementsByName('ProdRequestOutput');
       for(i=0;i<procNames.length;i++) {
           procNames[i].className="show_inline";
           if(!procNames[i].innerHTML) {
               var id=procNames[i].id;
               var arr=id.split("___");
               var prim=arr[1]; // PrimaryDataset name
               ajaxEngine.registerAjaxElement(id);
               ajaxGetProdRequest(prim,id);
           }
       }
   }
}
function HideProdRequestOutput()  {
   browser=CheckBrowser();
   var procNames=new Array;
   if(browser.match('IE')) {
       var tags=document.getElementsByTagName('div');
       for(i=0;i<tags.length;i++) {
           if(tags[i].name=='ProdRequestOutput') {
              tags[i].className="hide";
           }
       }
   } else {
       var procNames=document.getElementsByName('ProdRequestOutput');
       for(i=0;i<procNames.length;i++) {
           procNames[i].className="hide";
       }
   }
}
function ShowPhedexStatusOutput() {
//   var names=document.getElementsByName('phedex_transfer_status');
   var names=document.getElementsByName('phedexStatusField');
   for(i=0;i<names.length;i++) {
      names[i].className="show_block";
   }
   getPhedexStatusForAllDatasets();
}
function HidePhedexStatusOutput() {
//   var names=document.getElementsByName('phedex_transfer_status');
   var names=document.getElementsByName('phedexStatusField');
   for(i=0;i<names.length;i++) {
      names[i].className="hide";
   }
}
function getPhedexStatusForAllDatasets() {
  // this function will be written in templateSnapshot.tmpl
}
function AutoTurnOn() {
   var myUrl=window.location.href;
   var url;
   if(myUrl.search("auto")>0) {
     url=myUrl.replace(/auto=off/g,'auto=on');
   } else {
     if(myUrl.search(/\?/)>0) {
       url=myUrl+"&auto=on";
     } else {
       url=myUrl+"?auto=on";
     }
   }
   SetCookie('DBSDD_AutoCompletion','on');
   load(url);
}
function AutoTurnOff() {
   var myUrl=window.location.href;
   var url;
   if(myUrl.search("auto")>0) {
     url=myUrl.replace(/auto=on/g,'auto=off');
   } else {
     if(myUrl.search(/\?/)>0) {
       url=myUrl+"&auto=off";
     } else {
       url=myUrl+"?auto=off";
     }
   }
   SetCookie('DBSDD_AutoCompletion','off');
   load(url);
}
function CaseSensitiveOn() {
   SetCookie('DBSDD_caseSensitive','on');
   var id=$('caseSensitive');
   id.value='on';
   id=$('caseOn');
   if (id) {
       id.className='td_underline_pad';
   }
   id=$('caseOff');
   if (id) {
       id.className='';
   }
}
function CaseSensitiveOff() {
   SetCookie('DBSDD_caseSensitive','off');
   var id=$('caseSensitive');
   id.value='off';
   id=$('caseOff');
   if (id) {
       id.className='td_underline_pad';
   }
   id=$('caseOn');
   if (id) {
       id.className='';
   }
}
function SortDesc() {
   SetCookie('DBSDD_sortOrder','desc');
   var id=$('sortOrder');
   id.value='desc';
   id=$('sortDesc');
   id.className='td_underline_pad';
   id=$('sortAsc');
   id.className='';
}
function SortAsc() {
   SetCookie('DBSDD_sortOrder','asc');
   var id=$('sortOrder');
   id.value='asc';
   id=$('sortAsc');
   id.className='td_underline_pad';
   id=$('sortDesc');
   id.className='';
}
function SetAutoCompletion() {
   var c=GetCookie('DBSDD_AutoCompletion');
   if(c) {
      if(c=='on') {
         AutoTurnOn();
      } 
      if(c=='off') {
         AutoTurnOff();
      } 
   } else {
      AutoTurnOff(); // default
   }
}
function resetRunNav() {
  showLoadingMessage('kw_prim_holder');
  ajaxGetTriggerLines('','ajaxGetRunRange()');// we skip first parameter which is dbsInst
  showLoadingMessage('kw_primType_holder');
  ajaxGetPrimDSTypes('','ajaxGetRunRange();ajaxUpdatePrimaryDatasets(\'\',\'ajaxGetRunRange()\')'); // we skip first parameter which is dbsInst
  showLoadingMessage('kw_runRange_holder');
  ajaxGetRunRange();
}
function ChangeView() {
   var tag=$("view_menu");
   if(tag.className=="hide") {
      tag.className="show_inline";
   } else {
      tag.className="hide";
   }
}
function ChangeUserMode(mode) {
  var url=window.location.href;
  var newurl;
  if(url.search("userMode")>0) {
     if(url.search("userMode=user")>0) {
        newurl=url.replace(/userMode=user/g,'userMode='+mode);
     }
     if(url.search("userMode=expert")>0) {
        newurl=url.replace(/userMode=expert/g,'userMode='+mode);
     }
     if(url.search("userMode=runManager")>0) {
        newurl=url.replace(/userMode=runManager/g,'userMode='+mode);
     }
     if(url.search("userMode=dbsExpert")>0) {
        newurl=url.replace(/userMode=dbsExpert/g,'userMode='+mode);
     }
  } else {
    if(url.search(/\?/)>0) {
       newurl=url+"&userMode="+mode;
    } else {
       newurl=url+"?userMode="+mode;
    }
  } 
  load(newurl);
}
function GetValueFromSelect(tag) {
  var elem=null;
  var sel=document.getElementById(tag);
  for(i=0;i<sel.length;i++) {
      if(sel[i].selected) {
         return sel[i].value;
      }
  }
  return elem;
}

function ChangeDbsInst() {
  var url=window.location.href;
  var dbsInst=GetValueFromSelect('dbsInst');
  var newurl;
  if(url.search("dbsInst")>0) {
     newurl=url.replace(/dbsInst=([^&]+)/g,'dbsInst='+dbsInst);
  } else {
    if(url.search(/\?/)>0) {
       newurl=url+"&dbsInst="+dbsInst;
    } else {
       newurl=url+"?dbsInst="+dbsInst;
    }
  } 
  load(newurl);
}
function SaveADS(name)
{
//  str = document.forms[0].ads.value;
  var id = document.getElementById('ads');
  var str = id.value;
  s0=str.replace(/</g,'&lt;');
  s1=s0.replace(/>/g,'&gt;');
//alert('SaveADS='+str+" s="+s1);
  mydoc = document.open();
  mydoc.write(s1);
  mydoc.execCommand("saveAs",true,name);
  mydoc.close();
}
function SetTagValue(cName,tag) {
   var cookie=GetCookie(cName);
   var id=document.getElementById(tag);
   if(cookie) {
      id.value=cookie;
   }
}
function SetTagCookie(cName,tag) {
   var id=document.getElementById(tag);
   if (id) {
       SetCookie(cName,id.value);
   }
}
function SetDefaultPageMessage(cVal) {
   var cookie=GetCookie('DBSDD_defaultPage');
   if(!cookie) {
      var id=document.getElementById('defaultPage');
      id.innerHTML='<span class="box_red">To make this page as default, click <a href="javascript:PrintDefaultPageMessage(\''+cVal+'\')">here</a></span>';
   }
}
function PrintDefaultPageMessage(cVal) {
   SetCookie('DBSDD_defaultPage',cVal);
   var id=document.getElementById('defaultPage');
   id.innerHTML='<span class="box_blue">Your default page is set to '+cVal+'. To change this setting clear up DBSDD_defaultPage cookie in your browser.</span>';
}
function MakeTooltip(_tag,_ctx,_text,autodismissdelay,width) {
  var delay=5000;
  if(autodismissdelay) {
     delay=autodismissdelay;
  }
  _width='400px';
  if(width) {
     _width=width;
  }
  //t = new YAHOO.widget.Tooltip(_tag, { context: _ctx, text: '<div class="tooltip">'+_text+'</div>', width:_width, autodismissdelay:delay, zIndex:9999 } );
  t = new YAHOO.widget.Tooltip(_tag, { context: _ctx, text: _text, width:_width, autodismissdelay:delay, zIndex:9999 } );
}

function PanelInit(myvar,tagName) {
    var panel_one;
    YAHOO.example.panels = function() {
            panel_one = new YAHOO.widget.Panel("panel_one", 
                    { 
                            close:true,  
                            visible:false,  
                            draggable:false
                    } 
            ); 
            panel_one.render();
    };
    YAHOO.util.Event.addListener(window,'load',YAHOO.example.panels);
}
/*
function insertSiteMasthead(view){
        YAHOO.namespace("cms.dmwt");
        var sdbfooter = document.createElement ("div");
        YAHOO.util.Dom.addClass(sdbfooter, "mastheadfooter");
        var mainUl = document.createElement ("ul");
        // create menu items
        var item = document.createElement ("li");
        changeText (item, "Menu :: ");
        mainUl.appendChild (item);

        if (view=='expert') {
            sites = footerExpertMenuText();
        } else if (view=='dbsExpert') {
            sites = footerDBSMenuText();
        } else {
            sites = footerUserMenuText();
        }
        for ( i = 0; i < sites.length; i++){
                var item = document.createElement("li");
                var a = document.createElement("a");
                a.setAttribute ("href", sites[i].link);
                a.setAttribute ("id", sites[i].label);
                a.setAttribute ("title", sites[i].title);
                if(sites[i].onclick) {
                   a.setAttribute("onclick", sites[i].onclick);
                }
                changeText (a, sites[i].label);
                if ( i > 0 ){
                        changeText (item, " - ");
                }
                item.appendChild (a);
                mainUl.appendChild (item);
        }

        // add spacer
        var item = document.createElement ("li");
        var s = document.createElement("spacer");
        s.setAttribute ("type", "block");
        s.setAttribute ("width", "50");
        changeText (s, "");
        item.appendChild(s)
        mainUl.appendChild (item);
        var item = document.createElement ("li");
        changeText (item, "Views :: ");
        mainUl.appendChild (item);

        // Create DBS views
        var item = document.createElement("li");
        var a = document.createElement("a");
        a.setAttribute ("href", "index?userMode=user");
        a.setAttribute ("id", "expertView");
        a.setAttribute ("title", "Physicists view on DBS");
        changeText (a, "Physicists ");
        item.appendChild(a);
        mainUl.appendChild (item);
        var item = document.createElement("li");
        var a = document.createElement("a");
        a.setAttribute ("href", "index?userMode=expert");
        a.setAttribute ("id", "userView");
        a.setAttribute ("title", "Production (more detailed) view on DBS, ");
        changeText (a, "- Production");
        item.appendChild(a);
        mainUl.appendChild (item);

        // add footer
        sdbfooter.appendChild(mainUl);
        YAHOO.cms.dmwt.masthead.appendToFooter(sdbfooter);
        YAHOO.cms.dmwt.masthead.render(document.body);
        YAHOO.cms.dmwt.masthead.show();
}
function footerUserMenuText(){
    return [
            {label: "Navigator", link: "_navigator?userMode=user", title: "Navigator: menu-driven search"},
            {label: "Finder", link: "_finder?userMode=user", title: "Finder: query-driven search"},
//            {label: "Config", link: "_config?userMode=user", title: "Config search: parameter set search"},
            {label: "RSS", link: "_rss?userMode=user", title: "RSS Feeds publish frequently updates about your data"},
//            {label: "History", link: "_history?userMode=user", title: "History: persistent history of user queries"},
            {label: "Help", link: "_help?userMode=user", title: "Help: DBS glossary, terms, feedback form"}
           ]
}
function footerExpertMenuText(){
    return [
            {label: "Navigator", link: "_navigator?userMode=expert", title: "Navigator: menu-driven search"},
            {label: "Finder", link: "_finder?userMode=expert", title: "Finder: query-driven search"},
            {label: "Config", link: "_config?userMode=expert", title: "Config search: parameter set search"},
            {label: "RSS", link: "_rss?userMode=expert", title: "RSS Feeds publish frequently updates about your data"},
            {label: "Site Search", link: "_siteSearch?userMode=expert", title: "Site search: site-based search"},
            {label: "History", link: "_history?userMode=expert", title: "History: persistent history of user queries"},
            {label: "Help", link: "_help?userMode=expert", title: "Help: DBS glossary, terms, feedback form"}
           ]
}
function footerDBSMenuText() {
    return [{label: "DBS experts", link: "_dbsExpert?userMode=dbsExpert", title: "DBS experts"}]
}

*/

/*
YAHOO.example.onMenuBarReady = function() {
    
    // "beforerender" event handler for the menu bar

    function onMenuBarBeforeRender(p_sType, p_sArgs, p_oMenu) {

        var oSubmenuData = {
        
            "communication": [ 
            
                { text: "360", url: "http://360.yahoo.com" },
                { text: "Alerts", url: "http://alerts.yahoo.com" },
                { text: "Avatars", url: "http://avatars.yahoo.com" },
                { text: "Groups", url: "http://groups.yahoo.com " },
                { text: "Internet Access", url: "http://promo.yahoo.com/broadband" },
            ],

            "shopping": [

                { text: "Auctions", url: "http://auctions.shopping.yahoo.com" },
                { text: "Autos", url: "http://autos.yahoo.com" },
                { text: "Classifieds", url: "http://classifieds.yahoo.com" },
            ]
        };


        this.getItem(0).cfg.setProperty("submenu", { id:"communication", itemdata: oSubmenuData["communication"] });
        this.getItem(1).cfg.setProperty("submenu", { id:"shopping", itemdata: oSubmenuData["shopping"] });
    }


    // Instantiate and render the menu bar
    var oMenuBar = new YAHOO.widget.MenuBar("productsandservices", { autosubmenudisplay:true, showdelay:250, hidedelay:750, lazyload:true });
    // Subscribe to the "beforerender" event
    oMenuBar.beforeRenderEvent.subscribe(onMenuBarBeforeRender, oMenuBar, true);
    // Render the menu bar
    oMenuBar.render();
};

// Initialize and render the menu bar when it is available in the DOM
YAHOO.util.Event.onContentReady("productsandservices", YAHOO.example.onMenuBarReady);
*/


//YAHOO.widget.MenuBarItem.prototype.IMG_ROOT = "YUI/menu/assets/";
//YAHOO.widget.MenuBarItem.prototype.SUBMENU_INDICATOR_IMAGE_PATH = "menuarorght8_nrm_1.gif";
//YAHOO.widget.MenuBarItem.prototype.SELECTED_SUBMENU_INDICATOR_IMAGE_PATH = "menuarorght8_hov_1.gif";
//YAHOO.widget.MenuBarItem.prototype.DISABLED_SUBMENU_INDICATOR_IMAGE_PATH = "menuarorght8_dim_1.gif";
/*
function commonMenu(tag,iMenu) {
    if($(tag)) {
        $(tag).innerHTML='';
        var oMenu = new YAHOO.widget.MenuBar("mymenubar", 
                    { trigger:document,
                      submenualignment:['tl','tr'],
                      autosubmenudisplay:true,
                      showdelay:250,
                      hidedelay:750, 
                      lazyload:true
                    } );
        oMenu.addItems(iMenu);
        oMenu.render(tag);
    }
}
*/


function AddNode(subList,name,node,admin) {
    var text, flag, rval;
    try {
        var nList = subList[name]
        for(var j=0;j<nList.length;j++) {
            //var text=nList[j][0]+' ('+nList[j][1]+')';
            flag=nList[j][0];
            rval=nList[j][1];
            if(admin==1) {
                text='<input type="text" name="'+flag+'" size="5"/> - <span class="tiny">'+flag+'</span>';
            } else {
                if (rval=='GOOD') {
                   text='<img src="images/choice-yes.gif" alt="GOOD" />-<span class="tiny">'+flag+'</span>';
                } else {
                   text='<img src="images/choice-no.gif" alt="'+rval+'" />-<span class="tiny">'+flag+'</span>';
                }
            }
            var nodeName = new YAHOO.widget.TextNode(text,node,false);
            AddNode(subList,nList[j][0],nodeName);
        }
    } catch(err) {
    }
}
function BuildNode(tree,name,tableList,subList,admin) {
    var tList = tableList[name];
    var text,flag,rval,subnode;
    for(var i=0;i<tList.length;i++) {
        flag=tList[i][0];
        rval=tList[i][1];
        if (admin==1) {
            text='<input type="text" name="'+flag+'" size="5"/> - <span class="tiny">'+flag+'</span>';
        } else {
            if (rval=='GOOD') {
               text='<img src="images/choice-yes.gif" alt="GOOD" />-<span class="tiny">'+flag+'</span>';
            } else {
               text='<img src="images/choice-no.gif" alt="'+rval+'" />-<span class="tiny">'+flag+'</span>';
            }
        }
        subnode = new YAHOO.widget.TextNode(text, tree.getRoot(), false);
        AddNode(subList,flag,subnode,admin);
    } 
}
function BuildNodeTree(tag,name,tableList,subList,admin) {
    var tree = new YAHOO.widget.TreeView(tag);
    BuildNode(tree,name,tableList,subList,admin);
    tree.draw();
}
function ExpandView(tag) {
   $(tag).innerHTML='<a href="javascript:CollapseView()">collapse</a>'
   tree.expandAll();
}
function CollapseView() {
   $(tag).innerHTML='<a href="javascript:ExpandView()">expand</a>'
   tree.collapseAll();
}
/*  Prototype JavaScript framework, version 1.4.0
 *  (c) 2005 Sam Stephenson <sam@conio.net>
 *
 *  Prototype is freely distributable under the terms of an MIT-style license.
 *  For details, see the Prototype web site: http://prototype.conio.net/
 *
/*--------------------------------------------------------------------------*/

var Prototype = {
  Version: '1.4.0',
  ScriptFragment: '(?:<script.*?>)((\n|\r|.)*?)(?:<\/script>)',

  emptyFunction: function() {},
  K: function(x) {return x}
}

var Class = {
  create: function() {
    return function() {
      this.initialize.apply(this, arguments);
    }
  }
}

var Abstract = new Object();

Object.extend = function(destination, source) {
  for (property in source) {
    destination[property] = source[property];
  }
  return destination;
}

Object.inspect = function(object) {
  try {
    if (object == undefined) return 'undefined';
    if (object == null) return 'null';
    return object.inspect ? object.inspect() : object.toString();
  } catch (e) {
    if (e instanceof RangeError) return '...';
    throw e;
  }
}

Function.prototype.bind = function() {
  var __method = this, args = $A(arguments), object = args.shift();
  return function() {
    return __method.apply(object, args.concat($A(arguments)));
  }
}

Function.prototype.bindAsEventListener = function(object) {
  var __method = this;
  return function(event) {
    return __method.call(object, event || window.event);
  }
}

Object.extend(Number.prototype, {
  toColorPart: function() {
    var digits = this.toString(16);
    if (this < 16) return '0' + digits;
    return digits;
  },

  succ: function() {
    return this + 1;
  },

  times: function(iterator) {
    $R(0, this, true).each(iterator);
    return this;
  }
});

var Try = {
  these: function() {
    var returnValue;

    for (var i = 0; i < arguments.length; i++) {
      var lambda = arguments[i];
      try {
        returnValue = lambda();
        break;
      } catch (e) {}
    }

    return returnValue;
  }
}

/*--------------------------------------------------------------------------*/

var PeriodicalExecuter = Class.create();
PeriodicalExecuter.prototype = {
  initialize: function(callback, frequency) {
    this.callback = callback;
    this.frequency = frequency;
    this.currentlyExecuting = false;

    this.registerCallback();
  },

  registerCallback: function() {
    setInterval(this.onTimerEvent.bind(this), this.frequency * 1000);
  },

  onTimerEvent: function() {
    if (!this.currentlyExecuting) {
      try {
        this.currentlyExecuting = true;
        this.callback();
      } finally {
        this.currentlyExecuting = false;
      }
    }
  }
}

/*--------------------------------------------------------------------------*/

function $() {
  var elements = new Array();

  for (var i = 0; i < arguments.length; i++) {
    var element = arguments[i];
    if (typeof element == 'string')
      element = document.getElementById(element);

    if (arguments.length == 1)
      return element;

    elements.push(element);
  }

  return elements;
}
Object.extend(String.prototype, {
  stripTags: function() {
    return this.replace(/<\/?[^>]+>/gi, '');
  },

  stripScripts: function() {
    return this.replace(new RegExp(Prototype.ScriptFragment, 'img'), '');
  },

  extractScripts: function() {
    var matchAll = new RegExp(Prototype.ScriptFragment, 'img');
    var matchOne = new RegExp(Prototype.ScriptFragment, 'im');
    return (this.match(matchAll) || []).map(function(scriptTag) {
      return (scriptTag.match(matchOne) || ['', ''])[1];
    });
  },

  evalScripts: function() {
    return this.extractScripts().map(eval);
  },

  escapeHTML: function() {
    var div = document.createElement('div');
    var text = document.createTextNode(this);
    div.appendChild(text);
    return div.innerHTML;
  },

  unescapeHTML: function() {
    var div = document.createElement('div');
    div.innerHTML = this.stripTags();
    return div.childNodes[0] ? div.childNodes[0].nodeValue : '';
  },

  toQueryParams: function() {
    var pairs = this.match(/^\??(.*)$/)[1].split('&');
    return pairs.inject({}, function(params, pairString) {
      var pair = pairString.split('=');
      params[pair[0]] = pair[1];
      return params;
    });
  },

  toArray: function() {
    return this.split('');
  },

  camelize: function() {
    var oStringList = this.split('-');
    if (oStringList.length == 1) return oStringList[0];

    var camelizedString = this.indexOf('-') == 0
      ? oStringList[0].charAt(0).toUpperCase() + oStringList[0].substring(1)
      : oStringList[0];

    for (var i = 1, len = oStringList.length; i < len; i++) {
      var s = oStringList[i];
      camelizedString += s.charAt(0).toUpperCase() + s.substring(1);
    }

    return camelizedString;
  },

  inspect: function() {
    return "'" + this.replace('\\', '\\\\').replace("'", '\\\'') + "'";
  }
});

String.prototype.parseQuery = String.prototype.toQueryParams;

var $break    = new Object();
var $continue = new Object();

var Enumerable = {
  each: function(iterator) {
    var index = 0;
    try {
      this._each(function(value) {
        try {
          iterator(value, index++);
        } catch (e) {
          if (e != $continue) throw e;
        }
      });
    } catch (e) {
      if (e != $break) throw e;
    }
  },

  all: function(iterator) {
    var result = true;
    this.each(function(value, index) {
      result = result && !!(iterator || Prototype.K)(value, index);
      if (!result) throw $break;
    });
    return result;
  },

  any: function(iterator) {
    var result = true;
    this.each(function(value, index) {
      if (result = !!(iterator || Prototype.K)(value, index))
        throw $break;
    });
    return result;
  },

  collect: function(iterator) {
    var results = [];
    this.each(function(value, index) {
      results.push(iterator(value, index));
    });
    return results;
  },

  detect: function (iterator) {
    var result;
    this.each(function(value, index) {
      if (iterator(value, index)) {
        result = value;
        throw $break;
      }
    });
    return result;
  },

  findAll: function(iterator) {
    var results = [];
    this.each(function(value, index) {
      if (iterator(value, index))
        results.push(value);
    });
    return results;
  },

  grep: function(pattern, iterator) {
    var results = [];
    this.each(function(value, index) {
      var stringValue = value.toString();
      if (stringValue.match(pattern))
        results.push((iterator || Prototype.K)(value, index));
    })
    return results;
  },

  include: function(object) {
    var found = false;
    this.each(function(value) {
      if (value == object) {
        found = true;
        throw $break;
      }
    });
    return found;
  },

  inject: function(memo, iterator) {
    this.each(function(value, index) {
      memo = iterator(memo, value, index);
    });
    return memo;
  },

  invoke: function(method) {
    var args = $A(arguments).slice(1);
    return this.collect(function(value) {
      return value[method].apply(value, args);
    });
  },

  max: function(iterator) {
    var result;
    this.each(function(value, index) {
      value = (iterator || Prototype.K)(value, index);
      if (value >= (result || value))
        result = value;
    });
    return result;
  },

  min: function(iterator) {
    var result;
    this.each(function(value, index) {
      value = (iterator || Prototype.K)(value, index);
      if (value <= (result || value))
        result = value;
    });
    return result;
  },

  partition: function(iterator) {
    var trues = [], falses = [];
    this.each(function(value, index) {
      ((iterator || Prototype.K)(value, index) ?
        trues : falses).push(value);
    });
    return [trues, falses];
  },

  pluck: function(property) {
    var results = [];
    this.each(function(value, index) {
      results.push(value[property]);
    });
    return results;
  },

  reject: function(iterator) {
    var results = [];
    this.each(function(value, index) {
      if (!iterator(value, index))
        results.push(value);
    });
    return results;
  },

  sortBy: function(iterator) {
    return this.collect(function(value, index) {
      return {value: value, criteria: iterator(value, index)};
    }).sort(function(left, right) {
      var a = left.criteria, b = right.criteria;
      return a < b ? -1 : a > b ? 1 : 0;
    }).pluck('value');
  },

  toArray: function() {
    return this.collect(Prototype.K);
  },

  zip: function() {
    var iterator = Prototype.K, args = $A(arguments);
    if (typeof args.last() == 'function')
      iterator = args.pop();

    var collections = [this].concat(args).map($A);
    return this.map(function(value, index) {
      iterator(value = collections.pluck(index));
      return value;
    });
  },

  inspect: function() {
    return '#<Enumerable:' + this.toArray().inspect() + '>';
  }
}

Object.extend(Enumerable, {
  map:     Enumerable.collect,
  find:    Enumerable.detect,
  select:  Enumerable.findAll,
  member:  Enumerable.include,
  entries: Enumerable.toArray
});
var $A = Array.from = function(iterable) {
  if (!iterable) return [];
  if (iterable.toArray) {
    return iterable.toArray();
  } else {
    var results = [];
    for (var i = 0; i < iterable.length; i++)
      results.push(iterable[i]);
    return results;
  }
}

Object.extend(Array.prototype, Enumerable);

Array.prototype._reverse = Array.prototype.reverse;

Object.extend(Array.prototype, {
  _each: function(iterator) {
    for (var i = 0; i < this.length; i++)
      iterator(this[i]);
  },

  clear: function() {
    this.length = 0;
    return this;
  },

  first: function() {
    return this[0];
  },

  last: function() {
    return this[this.length - 1];
  },

  compact: function() {
    return this.select(function(value) {
      return value != undefined || value != null;
    });
  },

  flatten: function() {
    return this.inject([], function(array, value) {
      return array.concat(value.constructor == Array ?
        value.flatten() : [value]);
    });
  },

  without: function() {
    var values = $A(arguments);
    return this.select(function(value) {
      return !values.include(value);
    });
  },

  indexOf: function(object) {
    for (var i = 0; i < this.length; i++)
      if (this[i] == object) return i;
    return -1;
  },

  reverse: function(inline) {
    return (inline !== false ? this : this.toArray())._reverse();
  },

  shift: function() {
    var result = this[0];
    for (var i = 0; i < this.length - 1; i++)
      this[i] = this[i + 1];
    this.length--;
    return result;
  },

  inspect: function() {
    return '[' + this.map(Object.inspect).join(', ') + ']';
  }
});
var Hash = {
  _each: function(iterator) {
    for (key in this) {
      var value = this[key];
      if (typeof value == 'function') continue;

      var pair = [key, value];
      pair.key = key;
      pair.value = value;
      iterator(pair);
    }
  },

  keys: function() {
    return this.pluck('key');
  },

  values: function() {
    return this.pluck('value');
  },

  merge: function(hash) {
    return $H(hash).inject($H(this), function(mergedHash, pair) {
      mergedHash[pair.key] = pair.value;
      return mergedHash;
    });
  },

  toQueryString: function() {
    return this.map(function(pair) {
      return pair.map(encodeURIComponent).join('=');
    }).join('&');
  },

  inspect: function() {
    return '#<Hash:{' + this.map(function(pair) {
      return pair.map(Object.inspect).join(': ');
    }).join(', ') + '}>';
  }
}

function $H(object) {
  var hash = Object.extend({}, object || {});
  Object.extend(hash, Enumerable);
  Object.extend(hash, Hash);
  return hash;
}
ObjectRange = Class.create();
Object.extend(ObjectRange.prototype, Enumerable);
Object.extend(ObjectRange.prototype, {
  initialize: function(start, end, exclusive) {
    this.start = start;
    this.end = end;
    this.exclusive = exclusive;
  },

  _each: function(iterator) {
    var value = this.start;
    do {
      iterator(value);
      value = value.succ();
    } while (this.include(value));
  },

  include: function(value) {
    if (value < this.start)
      return false;
    if (this.exclusive)
      return value < this.end;
    return value <= this.end;
  }
});

var $R = function(start, end, exclusive) {
  return new ObjectRange(start, end, exclusive);
}

var Ajax = {
  getTransport: function() {
    return Try.these(
      function() {return new ActiveXObject('Msxml2.XMLHTTP')},
      function() {return new ActiveXObject('Microsoft.XMLHTTP')},
      function() {return new XMLHttpRequest()}
    ) || false;
  },

  activeRequestCount: 0
}

Ajax.Responders = {
  responders: [],

  _each: function(iterator) {
    this.responders._each(iterator);
  },

  register: function(responderToAdd) {
    if (!this.include(responderToAdd))
      this.responders.push(responderToAdd);
  },

  unregister: function(responderToRemove) {
    this.responders = this.responders.without(responderToRemove);
  },

  dispatch: function(callback, request, transport, json) {
    this.each(function(responder) {
      if (responder[callback] && typeof responder[callback] == 'function') {
        try {
          responder[callback].apply(responder, [request, transport, json]);
        } catch (e) {}
      }
    });
  }
};

Object.extend(Ajax.Responders, Enumerable);

Ajax.Responders.register({
  onCreate: function() {
    Ajax.activeRequestCount++;
  },

  onComplete: function() {
    Ajax.activeRequestCount--;
  }
});

Ajax.Base = function() {};
Ajax.Base.prototype = {
  setOptions: function(options) {
    this.options = {
      method:       'post',
      asynchronous: true,
      parameters:   ''
    }
    Object.extend(this.options, options || {});
  },

  responseIsSuccess: function() {
    return this.transport.status == undefined
        || this.transport.status == 0
        || (this.transport.status >= 200 && this.transport.status < 300);
  },

  responseIsFailure: function() {
    return !this.responseIsSuccess();
  }
}

Ajax.Request = Class.create();
Ajax.Request.Events =
  ['Uninitialized', 'Loading', 'Loaded', 'Interactive', 'Complete'];

Ajax.Request.prototype = Object.extend(new Ajax.Base(), {
  initialize: function(url, options) {
    this.transport = Ajax.getTransport();
    this.setOptions(options);
    this.request(url);
  },

  request: function(url) {
    var parameters = this.options.parameters || '';
    if (parameters.length > 0) parameters += '&_=';

    try {
      this.url = url;
      if (this.options.method == 'get' && parameters.length > 0)
        this.url += (this.url.match(/\?/) ? '&' : '?') + parameters;

      Ajax.Responders.dispatch('onCreate', this, this.transport);

      this.transport.open(this.options.method, this.url,
        this.options.asynchronous);

      if (this.options.asynchronous) {
        this.transport.onreadystatechange = this.onStateChange.bind(this);
        setTimeout((function() {this.respondToReadyState(1)}).bind(this), 10);
      }

      this.setRequestHeaders();

      var body = this.options.postBody ? this.options.postBody : parameters;
      this.transport.send(this.options.method == 'post' ? body : null);

    } catch (e) {
      this.dispatchException(e);
    }
  },

  setRequestHeaders: function() {
    var requestHeaders =
      ['X-Requested-With', 'XMLHttpRequest',
       'X-Prototype-Version', Prototype.Version];

    if (this.options.method == 'post') {
      requestHeaders.push('Content-type',
        'application/x-www-form-urlencoded');

      /* Force "Connection: close" for Mozilla browsers to work around
       * a bug where XMLHttpReqeuest sends an incorrect Content-length
       * header. See Mozilla Bugzilla #246651.
       */
      if (this.transport.overrideMimeType)
        requestHeaders.push('Connection', 'close');
    }

    if (this.options.requestHeaders)
      requestHeaders.push.apply(requestHeaders, this.options.requestHeaders);

    for (var i = 0; i < requestHeaders.length; i += 2)
      this.transport.setRequestHeader(requestHeaders[i], requestHeaders[i+1]);
  },

  onStateChange: function() {
    var readyState = this.transport.readyState;
    if (readyState != 1)
      this.respondToReadyState(this.transport.readyState);
  },

  header: function(name) {
    try {
      return this.transport.getResponseHeader(name);
    } catch (e) {}
  },

  evalJSON: function() {
    try {
      return eval(this.header('X-JSON'));
    } catch (e) {}
  },

  evalResponse: function() {
    try {
      return eval(this.transport.responseText);
    } catch (e) {
      this.dispatchException(e);
    }
  },

  respondToReadyState: function(readyState) {
    var event = Ajax.Request.Events[readyState];
    var transport = this.transport, json = this.evalJSON();

    if (event == 'Complete') {
      try {
        (this.options['on' + this.transport.status]
         || this.options['on' + (this.responseIsSuccess() ? 'Success' : 'Failure')]
         || Prototype.emptyFunction)(transport, json);
      } catch (e) {
        this.dispatchException(e);
      }

      if ((this.header('Content-type') || '').match(/^text\/javascript/i))
        this.evalResponse();
    }

    try {
      (this.options['on' + event] || Prototype.emptyFunction)(transport, json);
      Ajax.Responders.dispatch('on' + event, this, transport, json);
    } catch (e) {
      this.dispatchException(e);
    }

    /* Avoid memory leak in MSIE: clean up the oncomplete event handler */
    if (event == 'Complete')
      this.transport.onreadystatechange = Prototype.emptyFunction;
  },

  dispatchException: function(exception) {
    (this.options.onException || Prototype.emptyFunction)(this, exception);
    Ajax.Responders.dispatch('onException', this, exception);
  }
});

Ajax.Updater = Class.create();

Object.extend(Object.extend(Ajax.Updater.prototype, Ajax.Request.prototype), {
  initialize: function(container, url, options) {
    this.containers = {
      success: container.success ? $(container.success) : $(container),
      failure: container.failure ? $(container.failure) :
        (container.success ? null : $(container))
    }

    this.transport = Ajax.getTransport();
    this.setOptions(options);

    var onComplete = this.options.onComplete || Prototype.emptyFunction;
    this.options.onComplete = (function(transport, object) {
      this.updateContent();
      onComplete(transport, object);
    }).bind(this);

    this.request(url);
  },

  updateContent: function() {
    var receiver = this.responseIsSuccess() ?
      this.containers.success : this.containers.failure;
    var response = this.transport.responseText;

    if (!this.options.evalScripts)
      response = response.stripScripts();

    if (receiver) {
      if (this.options.insertion) {
        new this.options.insertion(receiver, response);
      } else {
        Element.update(receiver, response);
      }
    }

    if (this.responseIsSuccess()) {
      if (this.onComplete)
        setTimeout(this.onComplete.bind(this), 10);
    }
  }
});

Ajax.PeriodicalUpdater = Class.create();
Ajax.PeriodicalUpdater.prototype = Object.extend(new Ajax.Base(), {
  initialize: function(container, url, options) {
    this.setOptions(options);
    this.onComplete = this.options.onComplete;

    this.frequency = (this.options.frequency || 2);
    this.decay = (this.options.decay || 1);

    this.updater = {};
    this.container = container;
    this.url = url;

    this.start();
  },

  start: function() {
    this.options.onComplete = this.updateComplete.bind(this);
    this.onTimerEvent();
  },

  stop: function() {
    this.updater.onComplete = undefined;
    clearTimeout(this.timer);
    (this.onComplete || Prototype.emptyFunction).apply(this, arguments);
  },

  updateComplete: function(request) {
    if (this.options.decay) {
      this.decay = (request.responseText == this.lastText ?
        this.decay * this.options.decay : 1);

      this.lastText = request.responseText;
    }
    this.timer = setTimeout(this.onTimerEvent.bind(this),
      this.decay * this.frequency * 1000);
  },

  onTimerEvent: function() {
    this.updater = new Ajax.Updater(this.container, this.url, this.options);
  }
});
document.getElementsByClassName = function(className, parentElement) {
  var children = ($(parentElement) || document.body).getElementsByTagName('*');
  return $A(children).inject([], function(elements, child) {
    if (child.className.match(new RegExp("(^|\\s)" + className + "(\\s|$)")))
      elements.push(child);
    return elements;
  });
}

/*--------------------------------------------------------------------------*/

if (!window.Element) {
  var Element = new Object();
}

Object.extend(Element, {
  visible: function(element) {
    return $(element).style.display != 'none';
  },

  toggle: function() {
    for (var i = 0; i < arguments.length; i++) {
      var element = $(arguments[i]);
      Element[Element.visible(element) ? 'hide' : 'show'](element);
    }
  },

  hide: function() {
    for (var i = 0; i < arguments.length; i++) {
      var element = $(arguments[i]);
      element.style.display = 'none';
    }
  },

  show: function() {
    for (var i = 0; i < arguments.length; i++) {
      var element = $(arguments[i]);
      element.style.display = '';
    }
  },

  remove: function(element) {
    element = $(element);
    element.parentNode.removeChild(element);
  },

  update: function(element, html) {
    $(element).innerHTML = html.stripScripts();
    setTimeout(function() {html.evalScripts()}, 10);
  },

  getHeight: function(element) {
    element = $(element);
    return element.offsetHeight;
  },

  classNames: function(element) {
    return new Element.ClassNames(element);
  },

  hasClassName: function(element, className) {
    if (!(element = $(element))) return;
    return Element.classNames(element).include(className);
  },

  addClassName: function(element, className) {
    if (!(element = $(element))) return;
    return Element.classNames(element).add(className);
  },

  removeClassName: function(element, className) {
    if (!(element = $(element))) return;
    return Element.classNames(element).remove(className);
  },

  // removes whitespace-only text node children
  cleanWhitespace: function(element) {
    element = $(element);
    for (var i = 0; i < element.childNodes.length; i++) {
      var node = element.childNodes[i];
      if (node.nodeType == 3 && !/\S/.test(node.nodeValue))
        Element.remove(node);
    }
  },

  empty: function(element) {
    return $(element).innerHTML.match(/^\s*$/);
  },

  scrollTo: function(element) {
    element = $(element);
    var x = element.x ? element.x : element.offsetLeft,
        y = element.y ? element.y : element.offsetTop;
    window.scrollTo(x, y);
  },

  getStyle: function(element, style) {
    element = $(element);
    var value = element.style[style.camelize()];
    if (!value) {
      if (document.defaultView && document.defaultView.getComputedStyle) {
        var css = document.defaultView.getComputedStyle(element, null);
        value = css ? css.getPropertyValue(style) : null;
      } else if (element.currentStyle) {
        value = element.currentStyle[style.camelize()];
      }
    }

    if (window.opera && ['left', 'top', 'right', 'bottom'].include(style))
      if (Element.getStyle(element, 'position') == 'static') value = 'auto';

    return value == 'auto' ? null : value;
  },

  setStyle: function(element, style) {
    element = $(element);
    for (name in style)
      element.style[name.camelize()] = style[name];
  },

  getDimensions: function(element) {
    element = $(element);
    if (Element.getStyle(element, 'display') != 'none')
      return {width: element.offsetWidth, height: element.offsetHeight};

    // All *Width and *Height properties give 0 on elements with display none,
    // so enable the element temporarily
    var els = element.style;
    var originalVisibility = els.visibility;
    var originalPosition = els.position;
    els.visibility = 'hidden';
    els.position = 'absolute';
    els.display = '';
    var originalWidth = element.clientWidth;
    var originalHeight = element.clientHeight;
    els.display = 'none';
    els.position = originalPosition;
    els.visibility = originalVisibility;
    return {width: originalWidth, height: originalHeight};
  },

  makePositioned: function(element) {
    element = $(element);
    var pos = Element.getStyle(element, 'position');
    if (pos == 'static' || !pos) {
      element._madePositioned = true;
      element.style.position = 'relative';
      // Opera returns the offset relative to the positioning context, when an
      // element is position relative but top and left have not been defined
      if (window.opera) {
        element.style.top = 0;
        element.style.left = 0;
      }
    }
  },

  undoPositioned: function(element) {
    element = $(element);
    if (element._madePositioned) {
      element._madePositioned = undefined;
      element.style.position =
        element.style.top =
        element.style.left =
        element.style.bottom =
        element.style.right = '';
    }
  },

  makeClipping: function(element) {
    element = $(element);
    if (element._overflow) return;
    element._overflow = element.style.overflow;
    if ((Element.getStyle(element, 'overflow') || 'visible') != 'hidden')
      element.style.overflow = 'hidden';
  },

  undoClipping: function(element) {
    element = $(element);
    if (element._overflow) return;
    element.style.overflow = element._overflow;
    element._overflow = undefined;
  }
});

var Toggle = new Object();
Toggle.display = Element.toggle;

/*--------------------------------------------------------------------------*/

Abstract.Insertion = function(adjacency) {
  this.adjacency = adjacency;
}

Abstract.Insertion.prototype = {
  initialize: function(element, content) {
    this.element = $(element);
    this.content = content.stripScripts();

    if (this.adjacency && this.element.insertAdjacentHTML) {
      try {
        this.element.insertAdjacentHTML(this.adjacency, this.content);
      } catch (e) {
        if (this.element.tagName.toLowerCase() == 'tbody') {
          this.insertContent(this.contentFromAnonymousTable());
        } else {
          throw e;
        }
      }
    } else {
      this.range = this.element.ownerDocument.createRange();
      if (this.initializeRange) this.initializeRange();
      this.insertContent([this.range.createContextualFragment(this.content)]);
    }

    setTimeout(function() {content.evalScripts()}, 10);
  },

  contentFromAnonymousTable: function() {
    var div = document.createElement('div');
    div.innerHTML = '<table><tbody>' + this.content + '</tbody></table>';
    return $A(div.childNodes[0].childNodes[0].childNodes);
  }
}

var Insertion = new Object();

Insertion.Before = Class.create();
Insertion.Before.prototype = Object.extend(new Abstract.Insertion('beforeBegin'), {
  initializeRange: function() {
    this.range.setStartBefore(this.element);
  },

  insertContent: function(fragments) {
    fragments.each((function(fragment) {
      this.element.parentNode.insertBefore(fragment, this.element);
    }).bind(this));
  }
});

Insertion.Top = Class.create();
Insertion.Top.prototype = Object.extend(new Abstract.Insertion('afterBegin'), {
  initializeRange: function() {
    this.range.selectNodeContents(this.element);
    this.range.collapse(true);
  },

  insertContent: function(fragments) {
    fragments.reverse(false).each((function(fragment) {
      this.element.insertBefore(fragment, this.element.firstChild);
    }).bind(this));
  }
});

Insertion.Bottom = Class.create();
Insertion.Bottom.prototype = Object.extend(new Abstract.Insertion('beforeEnd'), {
  initializeRange: function() {
    this.range.selectNodeContents(this.element);
    this.range.collapse(this.element);
  },

  insertContent: function(fragments) {
    fragments.each((function(fragment) {
      this.element.appendChild(fragment);
    }).bind(this));
  }
});

Insertion.After = Class.create();
Insertion.After.prototype = Object.extend(new Abstract.Insertion('afterEnd'), {
  initializeRange: function() {
    this.range.setStartAfter(this.element);
  },

  insertContent: function(fragments) {
    fragments.each((function(fragment) {
      this.element.parentNode.insertBefore(fragment,
        this.element.nextSibling);
    }).bind(this));
  }
});

/*--------------------------------------------------------------------------*/

Element.ClassNames = Class.create();
Element.ClassNames.prototype = {
  initialize: function(element) {
    this.element = $(element);
  },

  _each: function(iterator) {
    this.element.className.split(/\s+/).select(function(name) {
      return name.length > 0;
    })._each(iterator);
  },

  set: function(className) {
    this.element.className = className;
  },

  add: function(classNameToAdd) {
    if (this.include(classNameToAdd)) return;
    this.set(this.toArray().concat(classNameToAdd).join(' '));
  },

  remove: function(classNameToRemove) {
    if (!this.include(classNameToRemove)) return;
    this.set(this.select(function(className) {
      return className != classNameToRemove;
    }).join(' '));
  },

  toString: function() {
    return this.toArray().join(' ');
  }
}

Object.extend(Element.ClassNames.prototype, Enumerable);
var Field = {
  clear: function() {
    for (var i = 0; i < arguments.length; i++)
      $(arguments[i]).value = '';
  },

  focus: function(element) {
    $(element).focus();
  },

  present: function() {
    for (var i = 0; i < arguments.length; i++)
      if ($(arguments[i]).value == '') return false;
    return true;
  },

  select: function(element) {
    $(element).select();
  },

  activate: function(element) {
    element = $(element);
    element.focus();
    if (element.select)
      element.select();
  }
}

/*--------------------------------------------------------------------------*/

var Form = {
  serialize: function(form) {
    var elements = Form.getElements($(form));
    var queryComponents = new Array();

    for (var i = 0; i < elements.length; i++) {
      var queryComponent = Form.Element.serialize(elements[i]);
      if (queryComponent)
        queryComponents.push(queryComponent);
    }

    return queryComponents.join('&');
  },

  getElements: function(form) {
    form = $(form);
    var elements = new Array();

    for (tagName in Form.Element.Serializers) {
      var tagElements = form.getElementsByTagName(tagName);
      for (var j = 0; j < tagElements.length; j++)
        elements.push(tagElements[j]);
    }
    return elements;
  },

  getInputs: function(form, typeName, name) {
    form = $(form);
    var inputs = form.getElementsByTagName('input');

    if (!typeName && !name)
      return inputs;

    var matchingInputs = new Array();
    for (var i = 0; i < inputs.length; i++) {
      var input = inputs[i];
      if ((typeName && input.type != typeName) ||
          (name && input.name != name))
        continue;
      matchingInputs.push(input);
    }

    return matchingInputs;
  },

  disable: function(form) {
    var elements = Form.getElements(form);
    for (var i = 0; i < elements.length; i++) {
      var element = elements[i];
      element.blur();
      element.disabled = 'true';
    }
  },

  enable: function(form) {
    var elements = Form.getElements(form);
    for (var i = 0; i < elements.length; i++) {
      var element = elements[i];
      element.disabled = '';
    }
  },

  findFirstElement: function(form) {
    return Form.getElements(form).find(function(element) {
      return element.type != 'hidden' && !element.disabled &&
        ['input', 'select', 'textarea'].include(element.tagName.toLowerCase());
    });
  },

  focusFirstElement: function(form) {
    Field.activate(Form.findFirstElement(form));
  },

  reset: function(form) {
    $(form).reset();
  }
}

Form.Element = {
  serialize: function(element) {
    element = $(element);
    var method = element.tagName.toLowerCase();
    var parameter = Form.Element.Serializers[method](element);

    if (parameter) {
      var key = encodeURIComponent(parameter[0]);
      if (key.length == 0) return;

      if (parameter[1].constructor != Array)
        parameter[1] = [parameter[1]];

      return parameter[1].map(function(value) {
        return key + '=' + encodeURIComponent(value);
      }).join('&');
    }
  },

  getValue: function(element) {
    element = $(element);
    var method = element.tagName.toLowerCase();
    var parameter = Form.Element.Serializers[method](element);

    if (parameter)
      return parameter[1];
  }
}

Form.Element.Serializers = {
  input: function(element) {
    switch (element.type.toLowerCase()) {
      case 'submit':
      case 'hidden':
      case 'password':
      case 'text':
        return Form.Element.Serializers.textarea(element);
      case 'checkbox':
      case 'radio':
        return Form.Element.Serializers.inputSelector(element);
    }
    return false;
  },

  inputSelector: function(element) {
    if (element.checked)
      return [element.name, element.value];
  },

  textarea: function(element) {
    return [element.name, element.value];
  },

  select: function(element) {
    return Form.Element.Serializers[element.type == 'select-one' ?
      'selectOne' : 'selectMany'](element);
  },

  selectOne: function(element) {
    var value = '', opt, index = element.selectedIndex;
    if (index >= 0) {
      opt = element.options[index];
      value = opt.value;
      if (!value && !('value' in opt))
        value = opt.text;
    }
    return [element.name, value];
  },

  selectMany: function(element) {
    var value = new Array();
    for (var i = 0; i < element.length; i++) {
      var opt = element.options[i];
      if (opt.selected) {
        var optValue = opt.value;
        if (!optValue && !('value' in opt))
          optValue = opt.text;
        value.push(optValue);
      }
    }
    return [element.name, value];
  }
}

/*--------------------------------------------------------------------------*/

var $F = Form.Element.getValue;

/*--------------------------------------------------------------------------*/

Abstract.TimedObserver = function() {}
Abstract.TimedObserver.prototype = {
  initialize: function(element, frequency, callback) {
    this.frequency = frequency;
    this.element   = $(element);
    this.callback  = callback;

    this.lastValue = this.getValue();
    this.registerCallback();
  },

  registerCallback: function() {
    setInterval(this.onTimerEvent.bind(this), this.frequency * 1000);
  },

  onTimerEvent: function() {
    var value = this.getValue();
    if (this.lastValue != value) {
      this.callback(this.element, value);
      this.lastValue = value;
    }
  }
}

Form.Element.Observer = Class.create();
Form.Element.Observer.prototype = Object.extend(new Abstract.TimedObserver(), {
  getValue: function() {
    return Form.Element.getValue(this.element);
  }
});

Form.Observer = Class.create();
Form.Observer.prototype = Object.extend(new Abstract.TimedObserver(), {
  getValue: function() {
    return Form.serialize(this.element);
  }
});

/*--------------------------------------------------------------------------*/

Abstract.EventObserver = function() {}
Abstract.EventObserver.prototype = {
  initialize: function(element, callback) {
    this.element  = $(element);
    this.callback = callback;

    this.lastValue = this.getValue();
    if (this.element.tagName.toLowerCase() == 'form')
      this.registerFormCallbacks();
    else
      this.registerCallback(this.element);
  },

  onElementEvent: function() {
    var value = this.getValue();
    if (this.lastValue != value) {
      this.callback(this.element, value);
      this.lastValue = value;
    }
  },

  registerFormCallbacks: function() {
    var elements = Form.getElements(this.element);
    for (var i = 0; i < elements.length; i++)
      this.registerCallback(elements[i]);
  },

  registerCallback: function(element) {
    if (element.type) {
      switch (element.type.toLowerCase()) {
        case 'checkbox':
        case 'radio':
          Event.observe(element, 'click', this.onElementEvent.bind(this));
          break;
        case 'password':
        case 'text':
        case 'textarea':
        case 'select-one':
        case 'select-multiple':
          Event.observe(element, 'change', this.onElementEvent.bind(this));
          break;
      }
    }
  }
}

Form.Element.EventObserver = Class.create();
Form.Element.EventObserver.prototype = Object.extend(new Abstract.EventObserver(), {
  getValue: function() {
    return Form.Element.getValue(this.element);
  }
});

Form.EventObserver = Class.create();
Form.EventObserver.prototype = Object.extend(new Abstract.EventObserver(), {
  getValue: function() {
    return Form.serialize(this.element);
  }
});
if (!window.Event) {
  var Event = new Object();
}

Object.extend(Event, {
  KEY_BACKSPACE: 8,
  KEY_TAB:       9,
  KEY_RETURN:   13,
  KEY_ESC:      27,
  KEY_LEFT:     37,
  KEY_UP:       38,
  KEY_RIGHT:    39,
  KEY_DOWN:     40,
  KEY_DELETE:   46,

  element: function(event) {
    return event.target || event.srcElement;
  },

  isLeftClick: function(event) {
    return (((event.which) && (event.which == 1)) ||
            ((event.button) && (event.button == 1)));
  },

  pointerX: function(event) {
    return event.pageX || (event.clientX +
      (document.documentElement.scrollLeft || document.body.scrollLeft));
  },

  pointerY: function(event) {
    return event.pageY || (event.clientY +
      (document.documentElement.scrollTop || document.body.scrollTop));
  },

  stop: function(event) {
    if (event.preventDefault) {
      event.preventDefault();
      event.stopPropagation();
    } else {
      event.returnValue = false;
      event.cancelBubble = true;
    }
  },

  // find the first node with the given tagName, starting from the
  // node the event was triggered on; traverses the DOM upwards
  findElement: function(event, tagName) {
    var element = Event.element(event);
    while (element.parentNode && (!element.tagName ||
        (element.tagName.toUpperCase() != tagName.toUpperCase())))
      element = element.parentNode;
    return element;
  },

  observers: false,

  _observeAndCache: function(element, name, observer, useCapture) {
    if (!this.observers) this.observers = [];
    if (element.addEventListener) {
      this.observers.push([element, name, observer, useCapture]);
      element.addEventListener(name, observer, useCapture);
    } else if (element.attachEvent) {
      this.observers.push([element, name, observer, useCapture]);
      element.attachEvent('on' + name, observer);
    }
  },

  unloadCache: function() {
    if (!Event.observers) return;
    for (var i = 0; i < Event.observers.length; i++) {
      Event.stopObserving.apply(this, Event.observers[i]);
      Event.observers[i][0] = null;
    }
    Event.observers = false;
  },

  observe: function(element, name, observer, useCapture) {
    var element = $(element);
    useCapture = useCapture || false;

    if (name == 'keypress' &&
        (navigator.appVersion.match(/Konqueror|Safari|KHTML/)
        || element.attachEvent))
      name = 'keydown';

    this._observeAndCache(element, name, observer, useCapture);
  },

  stopObserving: function(element, name, observer, useCapture) {
    var element = $(element);
    useCapture = useCapture || false;

    if (name == 'keypress' &&
        (navigator.appVersion.match(/Konqueror|Safari|KHTML/)
        || element.detachEvent))
      name = 'keydown';

    if (element.removeEventListener) {
      element.removeEventListener(name, observer, useCapture);
    } else if (element.detachEvent) {
      element.detachEvent('on' + name, observer);
    }
  }
});

/* prevent memory leaks in IE */
Event.observe(window, 'unload', Event.unloadCache, false);
var Position = {
  // set to true if needed, warning: firefox performance problems
  // NOT neeeded for page scrolling, only if draggable contained in
  // scrollable elements
  includeScrollOffsets: false,

  // must be called before calling withinIncludingScrolloffset, every time the
  // page is scrolled
  prepare: function() {
    this.deltaX =  window.pageXOffset
                || document.documentElement.scrollLeft
                || document.body.scrollLeft
                || 0;
    this.deltaY =  window.pageYOffset
                || document.documentElement.scrollTop
                || document.body.scrollTop
                || 0;
  },

  realOffset: function(element) {
    var valueT = 0, valueL = 0;
    do {
      valueT += element.scrollTop  || 0;
      valueL += element.scrollLeft || 0;
      element = element.parentNode;
    } while (element);
    return [valueL, valueT];
  },

  cumulativeOffset: function(element) {
    var valueT = 0, valueL = 0;
    do {
      valueT += element.offsetTop  || 0;
      valueL += element.offsetLeft || 0;
      element = element.offsetParent;
    } while (element);
    return [valueL, valueT];
  },

  positionedOffset: function(element) {
    var valueT = 0, valueL = 0;
    do {
      valueT += element.offsetTop  || 0;
      valueL += element.offsetLeft || 0;
      element = element.offsetParent;
      if (element) {
        p = Element.getStyle(element, 'position');
        if (p == 'relative' || p == 'absolute') break;
      }
    } while (element);
    return [valueL, valueT];
  },

  offsetParent: function(element) {
    if (element.offsetParent) return element.offsetParent;
    if (element == document.body) return element;

    while ((element = element.parentNode) && element != document.body)
      if (Element.getStyle(element, 'position') != 'static')
        return element;

    return document.body;
  },

  // caches x/y coordinate pair to use with overlap
  within: function(element, x, y) {
    if (this.includeScrollOffsets)
      return this.withinIncludingScrolloffsets(element, x, y);
    this.xcomp = x;
    this.ycomp = y;
    this.offset = this.cumulativeOffset(element);

    return (y >= this.offset[1] &&
            y <  this.offset[1] + element.offsetHeight &&
            x >= this.offset[0] &&
            x <  this.offset[0] + element.offsetWidth);
  },

  withinIncludingScrolloffsets: function(element, x, y) {
    var offsetcache = this.realOffset(element);

    this.xcomp = x + offsetcache[0] - this.deltaX;
    this.ycomp = y + offsetcache[1] - this.deltaY;
    this.offset = this.cumulativeOffset(element);

    return (this.ycomp >= this.offset[1] &&
            this.ycomp <  this.offset[1] + element.offsetHeight &&
            this.xcomp >= this.offset[0] &&
            this.xcomp <  this.offset[0] + element.offsetWidth);
  },

  // within must be called directly before
  overlap: function(mode, element) {
    if (!mode) return 0;
    if (mode == 'vertical')
      return ((this.offset[1] + element.offsetHeight) - this.ycomp) /
        element.offsetHeight;
    if (mode == 'horizontal')
      return ((this.offset[0] + element.offsetWidth) - this.xcomp) /
        element.offsetWidth;
  },

  clone: function(source, target) {
    source = $(source);
    target = $(target);
    target.style.position = 'absolute';
    var offsets = this.cumulativeOffset(source);
    target.style.top    = offsets[1] + 'px';
    target.style.left   = offsets[0] + 'px';
    target.style.width  = source.offsetWidth + 'px';
    target.style.height = source.offsetHeight + 'px';
  },

  page: function(forElement) {
    var valueT = 0, valueL = 0;

    var element = forElement;
    do {
      valueT += element.offsetTop  || 0;
      valueL += element.offsetLeft || 0;

      // Safari fix
      if (element.offsetParent==document.body)
        if (Element.getStyle(element,'position')=='absolute') break;

    } while (element = element.offsetParent);

    element = forElement;
    do {
      valueT -= element.scrollTop  || 0;
      valueL -= element.scrollLeft || 0;
    } while (element = element.parentNode);

    return [valueL, valueT];
  },

  clone: function(source, target) {
    var options = Object.extend({
      setLeft:    true,
      setTop:     true,
      setWidth:   true,
      setHeight:  true,
      offsetTop:  0,
      offsetLeft: 0
    }, arguments[2] || {})

    // find page position of source
    source = $(source);
    var p = Position.page(source);

    // find coordinate system to use
    target = $(target);
    var delta = [0, 0];
    var parent = null;
    // delta [0,0] will do fine with position: fixed elements,
    // position:absolute needs offsetParent deltas
    if (Element.getStyle(target,'position') == 'absolute') {
      parent = Position.offsetParent(target);
      delta = Position.page(parent);
    }

    // correct by body offsets (fixes Safari)
    if (parent == document.body) {
      delta[0] -= document.body.offsetLeft;
      delta[1] -= document.body.offsetTop;
    }

    // set position
    if(options.setLeft)   target.style.left  = (p[0] - delta[0] + options.offsetLeft) + 'px';
    if(options.setTop)    target.style.top   = (p[1] - delta[1] + options.offsetTop) + 'px';
    if(options.setWidth)  target.style.width = source.offsetWidth + 'px';
    if(options.setHeight) target.style.height = source.offsetHeight + 'px';
  },

  absolutize: function(element) {
    element = $(element);
    if (element.style.position == 'absolute') return;
    Position.prepare();

    var offsets = Position.positionedOffset(element);
    var top     = offsets[1];
    var left    = offsets[0];
    var width   = element.clientWidth;
    var height  = element.clientHeight;

    element._originalLeft   = left - parseFloat(element.style.left  || 0);
    element._originalTop    = top  - parseFloat(element.style.top || 0);
    element._originalWidth  = element.style.width;
    element._originalHeight = element.style.height;

    element.style.position = 'absolute';
    element.style.top    = top + 'px';;
    element.style.left   = left + 'px';;
    element.style.width  = width + 'px';;
    element.style.height = height + 'px';;
  },

  relativize: function(element) {
    element = $(element);
    if (element.style.position == 'relative') return;
    Position.prepare();

    element.style.position = 'relative';
    var top  = parseFloat(element.style.top  || 0) - (element._originalTop || 0);
    var left = parseFloat(element.style.left || 0) - (element._originalLeft || 0);

    element.style.top    = top + 'px';
    element.style.left   = left + 'px';
    element.style.height = element._originalHeight;
    element.style.width  = element._originalWidth;
  }
}

// Safari returns margins on body which is incorrect if the child is absolutely
// positioned.  For performance reasons, redefine Position.cumulativeOffset for
// KHTML/WebKit only.
if (/Konqueror|Safari|KHTML/.test(navigator.userAgent)) {
  Position.cumulativeOffset = function(element) {
    var valueT = 0, valueL = 0;
    do {
      valueT += element.offsetTop  || 0;
      valueL += element.offsetLeft || 0;
      if (element.offsetParent == document.body)
        if (Element.getStyle(element, 'position') == 'absolute') break;

      element = element.offsetParent;
    } while (element);

    return [valueL, valueT];
  }
}/**
  *
  *  Copyright 2005 Sabre Airline Solutions
  *
  *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
  *  file except in compliance with the License. You may obtain a copy of the License at
  *
  *         http://www.apache.org/licenses/LICENSE-2.0
  *
  *  Unless required by applicable law or agreed to in writing, software distributed under the
  *  License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
  *  either express or implied. See the License for the specific language governing permissions
  *  and limitations under the License.
  **/


//-------------------- rico.js
var Rico = {
  Version: '1.1.2',
  prototypeVersion: parseFloat(Prototype.Version.split(".")[0] + "." + Prototype.Version.split(".")[1])
}

if((typeof Prototype=='undefined') || Rico.prototypeVersion < 1.3)
      throw("Rico requires the Prototype JavaScript framework >= 1.3");

Rico.ArrayExtensions = new Array();

if (Object.prototype.extend) {
   Rico.ArrayExtensions[ Rico.ArrayExtensions.length ] = Object.prototype.extend;
}else{
  Object.prototype.extend = function(object) {
    return Object.extend.apply(this, [this, object]);
  }
  Rico.ArrayExtensions[ Rico.ArrayExtensions.length ] = Object.prototype.extend;
}

if (Array.prototype.push) {
   Rico.ArrayExtensions[ Rico.ArrayExtensions.length ] = Array.prototype.push;
}

if (!Array.prototype.remove) {
   Array.prototype.remove = function(dx) {
      if( isNaN(dx) || dx > this.length )
         return false;
      for( var i=0,n=0; i<this.length; i++ )
         if( i != dx )
            this[n++]=this[i];
      this.length-=1;
   };
  Rico.ArrayExtensions[ Rico.ArrayExtensions.length ] = Array.prototype.remove;
}

if (!Array.prototype.removeItem) {
   Array.prototype.removeItem = function(item) {
      for ( var i = 0 ; i < this.length ; i++ )
         if ( this[i] == item ) {
            this.remove(i);
            break;
         }
   };
  Rico.ArrayExtensions[ Rico.ArrayExtensions.length ] = Array.prototype.removeItem;
}

if (!Array.prototype.indices) {
   Array.prototype.indices = function() {
      var indexArray = new Array();
      for ( index in this ) {
         var ignoreThis = false;
         for ( var i = 0 ; i < Rico.ArrayExtensions.length ; i++ ) {
            if ( this[index] == Rico.ArrayExtensions[i] ) {
               ignoreThis = true;
               break;
            }
         }
         if ( !ignoreThis )
            indexArray[ indexArray.length ] = index;
      }
      return indexArray;
   }
  Rico.ArrayExtensions[ Rico.ArrayExtensions.length ] = Array.prototype.indices;
}

// Create the loadXML method and xml getter for Mozilla
if ( window.DOMParser &&
	  window.XMLSerializer &&
	  window.Node && Node.prototype && Node.prototype.__defineGetter__ ) {

   if (!Document.prototype.loadXML) {
      Document.prototype.loadXML = function (s) {
         var doc2 = (new DOMParser()).parseFromString(s, "text/xml");
         while (this.hasChildNodes())
            this.removeChild(this.lastChild);

         for (var i = 0; i < doc2.childNodes.length; i++) {
            this.appendChild(this.importNode(doc2.childNodes[i], true));
         }
      };
	}

	Document.prototype.__defineGetter__( "xml",
	   function () {
		   return (new XMLSerializer()).serializeToString(this);
	   }
	 );
}

document.getElementsByTagAndClassName = function(tagName, className) {
  if ( tagName == null )
     tagName = '*';

  var children = document.getElementsByTagName(tagName) || document.all;
  var elements = new Array();

  if ( className == null )
    return children;

  for (var i = 0; i < children.length; i++) {
    var child = children[i];
    var classNames = child.className.split(' ');
    for (var j = 0; j < classNames.length; j++) {
      if (classNames[j] == className) {
        elements.push(child);
        break;
      }
    }
  }

  return elements;
}


//-------------------- ricoAccordion.js
Rico.Accordion = Class.create();

Rico.Accordion.prototype = {

   initialize: function(container, options) {
      this.container            = $(container);
      this.lastExpandedTab      = null;
      this.accordionTabs        = new Array();
      this.setOptions(options);
      this._attachBehaviors();
      if(!container) return;

      this.container.style.borderBottom = '1px solid ' + this.options.borderColor;
      // validate onloadShowTab
       if (this.options.onLoadShowTab >= this.accordionTabs.length)
        this.options.onLoadShowTab = 0;

      // set the initial visual state...
      for ( var i=0 ; i < this.accordionTabs.length ; i++ )
      {
        if (i != this.options.onLoadShowTab){
         this.accordionTabs[i].collapse();
         this.accordionTabs[i].content.style.display = 'none';
        }
      }
      this.lastExpandedTab = this.accordionTabs[this.options.onLoadShowTab];
      if (this.options.panelHeight == 'auto'){
          var tabToCheck = (this.options.onloadShowTab === 0)? 1 : 0;
          var titleBarSize = parseInt(RicoUtil.getElementsComputedStyle(this.accordionTabs[tabToCheck].titleBar, 'height'));
          if (isNaN(titleBarSize))
            titleBarSize = this.accordionTabs[tabToCheck].titleBar.offsetHeight;
          
          var totalTitleBarSize = this.accordionTabs.length * titleBarSize;
          var parentHeight = parseInt(RicoUtil.getElementsComputedStyle(this.container.parentNode, 'height'));
          if (isNaN(parentHeight))
            parentHeight = this.container.parentNode.offsetHeight;
          
          this.options.panelHeight = parentHeight - totalTitleBarSize-2;
      }
      
      this.lastExpandedTab.content.style.height = this.options.panelHeight + "px";
      this.lastExpandedTab.showExpanded();
      this.lastExpandedTab.titleBar.style.fontWeight = this.options.expandedFontWeight;

   },

   setOptions: function(options) {
      this.options = {
         expandedBg          : '#63699c',
         hoverBg             : '#63699c',
         collapsedBg         : '#6b79a5',
         expandedTextColor   : '#ffffff',
         expandedFontWeight  : 'bold',
         hoverTextColor      : '#ffffff',
         collapsedTextColor  : '#ced7ef',
         collapsedFontWeight : 'normal',
         hoverTextColor      : '#ffffff',
         borderColor         : '#1f669b',
         panelHeight         : 200,
         onHideTab           : null,
         onShowTab           : null,
         onLoadShowTab       : 0
      }
      Object.extend(this.options, options || {});
   },

   showTabByIndex: function( anIndex, animate ) {
      var doAnimate = arguments.length == 1 ? true : animate;
      this.showTab( this.accordionTabs[anIndex], doAnimate );
   },

   showTab: function( accordionTab, animate ) {
     if ( this.lastExpandedTab == accordionTab )
        return;

      var doAnimate = arguments.length == 1 ? true : animate;

      if ( this.options.onHideTab )
         this.options.onHideTab(this.lastExpandedTab);

      this.lastExpandedTab.showCollapsed(); 
      var accordion = this;
      var lastExpandedTab = this.lastExpandedTab;

      this.lastExpandedTab.content.style.height = (this.options.panelHeight - 1) + 'px';
      accordionTab.content.style.display = '';

      accordionTab.titleBar.style.fontWeight = this.options.expandedFontWeight;

      if ( doAnimate ) {
         new Rico.Effect.AccordionSize( this.lastExpandedTab.content,
                                   accordionTab.content,
                                   1,
                                   this.options.panelHeight,
                                   100, 10,
                                   { complete: function() {accordion.showTabDone(lastExpandedTab)} } );
         this.lastExpandedTab = accordionTab;
      }
      else {
         this.lastExpandedTab.content.style.height = "1px";
         accordionTab.content.style.height = this.options.panelHeight + "px";
         this.lastExpandedTab = accordionTab;
         this.showTabDone(lastExpandedTab);
      }
   },

   showTabDone: function(collapsedTab) {
      collapsedTab.content.style.display = 'none';
      this.lastExpandedTab.showExpanded();
      if ( this.options.onShowTab )
         this.options.onShowTab(this.lastExpandedTab);
   },

   _attachBehaviors: function() {
      var panels = this._getDirectChildrenByTag(this.container, 'DIV');
      for ( var i = 0 ; i < panels.length ; i++ ) {

         var tabChildren = this._getDirectChildrenByTag(panels[i],'DIV');
         if ( tabChildren.length != 2 )
            continue; // unexpected

         var tabTitleBar   = tabChildren[0];
         var tabContentBox = tabChildren[1];
         this.accordionTabs.push( new Rico.Accordion.Tab(this,tabTitleBar,tabContentBox) );
      }
   },

   _getDirectChildrenByTag: function(e, tagName) {
      var kids = new Array();
      var allKids = e.childNodes;
      for( var i = 0 ; i < allKids.length ; i++ )
         if ( allKids[i] && allKids[i].tagName && allKids[i].tagName == tagName )
            kids.push(allKids[i]);
      return kids;
   }

};

Rico.Accordion.Tab = Class.create();

Rico.Accordion.Tab.prototype = {

   initialize: function(accordion, titleBar, content) {
      this.accordion = accordion;
      this.titleBar  = titleBar;
      this.content   = content;
      this._attachBehaviors();
   },

   collapse: function() {
      this.showCollapsed();
      this.content.style.height = "1px";
   },

   showCollapsed: function() {
      this.expanded = false;
      this.titleBar.style.backgroundColor = this.accordion.options.collapsedBg;
      this.titleBar.style.color           = this.accordion.options.collapsedTextColor;
      this.titleBar.style.fontWeight      = this.accordion.options.collapsedFontWeight;
      this.content.style.overflow = "hidden";
   },

   showExpanded: function() {
      this.expanded = true;
      this.titleBar.style.backgroundColor = this.accordion.options.expandedBg;
      this.titleBar.style.color           = this.accordion.options.expandedTextColor;
      this.content.style.overflow         = "auto";
   },

   titleBarClicked: function(e) {
      if ( this.accordion.lastExpandedTab == this )
         return;
      this.accordion.showTab(this);
   },

   hover: function(e) {
		this.titleBar.style.backgroundColor = this.accordion.options.hoverBg;
		this.titleBar.style.color           = this.accordion.options.hoverTextColor;
   },

   unhover: function(e) {
      if ( this.expanded ) {
         this.titleBar.style.backgroundColor = this.accordion.options.expandedBg;
         this.titleBar.style.color           = this.accordion.options.expandedTextColor;
      }
      else {
         this.titleBar.style.backgroundColor = this.accordion.options.collapsedBg;
         this.titleBar.style.color           = this.accordion.options.collapsedTextColor;
      }
   },

   _attachBehaviors: function() {
      this.content.style.border = "1px solid " + this.accordion.options.borderColor;
      this.content.style.borderTopWidth    = "0px";
      this.content.style.borderBottomWidth = "0px";
      this.content.style.margin            = "0px";

      this.titleBar.onclick     = this.titleBarClicked.bindAsEventListener(this);
      this.titleBar.onmouseover = this.hover.bindAsEventListener(this);
      this.titleBar.onmouseout  = this.unhover.bindAsEventListener(this);
   }

};


//-------------------- ricoAjaxEngine.js
Rico.AjaxEngine = Class.create();

Rico.AjaxEngine.prototype = {

   initialize: function() {
      this.ajaxElements = new Array();
      this.ajaxObjects  = new Array();
      this.requestURLS  = new Array();
      this.options = {};
   },

   registerAjaxElement: function( anId, anElement ) {
      if ( !anElement )
         anElement = $(anId);
      this.ajaxElements[anId] = anElement;
   },

   registerAjaxObject: function( anId, anObject ) {
      this.ajaxObjects[anId] = anObject;
   },

   registerRequest: function (requestLogicalName, requestURL) {
      this.requestURLS[requestLogicalName] = requestURL;
   },

   sendRequest: function(requestName, options) {
      // Allow for backwards Compatibility
      if ( arguments.length >= 2 )
       if (typeof arguments[1] == 'string')
         options = {parameters: this._createQueryString(arguments, 1)};
      this.sendRequestWithData(requestName, null, options);
   },

   sendRequestWithData: function(requestName, xmlDocument, options) {
      var requestURL = this.requestURLS[requestName];
      if ( requestURL == null )
         return;

      // Allow for backwards Compatibility
      if ( arguments.length >= 3 )
        if (typeof arguments[2] == 'string')
          options.parameters = this._createQueryString(arguments, 2);

      new Ajax.Request(requestURL, this._requestOptions(options,xmlDocument));
   },

   sendRequestAndUpdate: function(requestName,container,options) {
      // Allow for backwards Compatibility
      if ( arguments.length >= 3 )
        if (typeof arguments[2] == 'string')
          options.parameters = this._createQueryString(arguments, 2);

      this.sendRequestWithDataAndUpdate(requestName, null, container, options);
   },

   sendRequestWithDataAndUpdate: function(requestName,xmlDocument,container,options) {
      var requestURL = this.requestURLS[requestName];
      if ( requestURL == null )
         return;

      // Allow for backwards Compatibility
      if ( arguments.length >= 4 )
        if (typeof arguments[3] == 'string')
          options.parameters = this._createQueryString(arguments, 3);

      var updaterOptions = this._requestOptions(options,xmlDocument);

      new Ajax.Updater(container, requestURL, updaterOptions);
   },

   // Private -- not part of intended engine API --------------------------------------------------------------------

   _requestOptions: function(options,xmlDoc) {
      var requestHeaders = ['X-Rico-Version', Rico.Version ];
      var sendMethod = 'post';
      if ( xmlDoc == null )
        if (Rico.prototypeVersion < 1.4)
        requestHeaders.push( 'Content-type', 'text/xml' );
      else
          sendMethod = 'get';
      (!options) ? options = {} : '';

      if (!options._RicoOptionsProcessed){
      // Check and keep any user onComplete functions
        if (options.onComplete)
             options.onRicoComplete = options.onComplete;
        // Fix onComplete
        if (options.overrideOnComplete)
          options.onComplete = options.overrideOnComplete;
        else
          options.onComplete = this._onRequestComplete.bind(this);
        options._RicoOptionsProcessed = true;
      }

     // Set the default options and extend with any user options
     this.options = {
                     requestHeaders: requestHeaders,
                     parameters:     options.parameters,
                     postBody:       xmlDoc,
                     method:         sendMethod,
                     onComplete:     options.onComplete
                    };
     // Set any user options:
     Object.extend(this.options, options);
     return this.options;
   },

   _createQueryString: function( theArgs, offset ) {
      var queryString = ""
      for ( var i = offset ; i < theArgs.length ; i++ ) {
          if ( i != offset )
            queryString += "&";

          var anArg = theArgs[i];

          if ( anArg.name != undefined && anArg.value != undefined ) {
            queryString += anArg.name +  "=" + escape(anArg.value);
          }
          else {
             var ePos  = anArg.indexOf('=');
             var argName  = anArg.substring( 0, ePos );
             var argValue = anArg.substring( ePos + 1 );
             queryString += argName + "=" + escape(argValue);
          }
      }
      return queryString;
   },

   _onRequestComplete : function(request) {
      if(!request)
          return;
      // User can set an onFailure option - which will be called by prototype
      if (request.status != 200)
        return;

      var response = request.responseXML.getElementsByTagName("ajax-response");
      if (response == null || response.length != 1)
         return;
      this._processAjaxResponse( response[0].childNodes );
      
      // Check if user has set a onComplete function
      var onRicoComplete = this.options.onRicoComplete;
      if (onRicoComplete != null)
          onRicoComplete();
   },

   _processAjaxResponse: function( xmlResponseElements ) {
      for ( var i = 0 ; i < xmlResponseElements.length ; i++ ) {
         var responseElement = xmlResponseElements[i];

         // only process nodes of type element.....
         if ( responseElement.nodeType != 1 )
            continue;

         var responseType = responseElement.getAttribute("type");
         var responseId   = responseElement.getAttribute("id");

         if ( responseType == "object" )
            this._processAjaxObjectUpdate( this.ajaxObjects[ responseId ], responseElement );
         else if ( responseType == "element" )
            this._processAjaxElementUpdate( this.ajaxElements[ responseId ], responseElement );
         else
            alert('unrecognized AjaxResponse type : ' + responseType );
      }
   },

   _processAjaxObjectUpdate: function( ajaxObject, responseElement ) {
      ajaxObject.ajaxUpdate( responseElement );
   },

   _processAjaxElementUpdate: function( ajaxElement, responseElement ) {
      ajaxElement.innerHTML = RicoUtil.getContentAsString(responseElement);
   }

}

var ajaxEngine = new Rico.AjaxEngine();


//-------------------- ricoColor.js
Rico.Color = Class.create();

Rico.Color.prototype = {

   initialize: function(red, green, blue) {
      this.rgb = { r: red, g : green, b : blue };
   },

   setRed: function(r) {
      this.rgb.r = r;
   },

   setGreen: function(g) {
      this.rgb.g = g;
   },

   setBlue: function(b) {
      this.rgb.b = b;
   },

   setHue: function(h) {

      // get an HSB model, and set the new hue...
      var hsb = this.asHSB();
      hsb.h = h;

      // convert back to RGB...
      this.rgb = Rico.Color.HSBtoRGB(hsb.h, hsb.s, hsb.b);
   },

   setSaturation: function(s) {
      // get an HSB model, and set the new hue...
      var hsb = this.asHSB();
      hsb.s = s;

      // convert back to RGB and set values...
      this.rgb = Rico.Color.HSBtoRGB(hsb.h, hsb.s, hsb.b);
   },

   setBrightness: function(b) {
      // get an HSB model, and set the new hue...
      var hsb = this.asHSB();
      hsb.b = b;

      // convert back to RGB and set values...
      this.rgb = Rico.Color.HSBtoRGB( hsb.h, hsb.s, hsb.b );
   },

   darken: function(percent) {
      var hsb  = this.asHSB();
      this.rgb = Rico.Color.HSBtoRGB(hsb.h, hsb.s, Math.max(hsb.b - percent,0));
   },

   brighten: function(percent) {
      var hsb  = this.asHSB();
      this.rgb = Rico.Color.HSBtoRGB(hsb.h, hsb.s, Math.min(hsb.b + percent,1));
   },

   blend: function(other) {
      this.rgb.r = Math.floor((this.rgb.r + other.rgb.r)/2);
      this.rgb.g = Math.floor((this.rgb.g + other.rgb.g)/2);
      this.rgb.b = Math.floor((this.rgb.b + other.rgb.b)/2);
   },

   isBright: function() {
      var hsb = this.asHSB();
      return this.asHSB().b > 0.5;
   },

   isDark: function() {
      return ! this.isBright();
   },

   asRGB: function() {
      return "rgb(" + this.rgb.r + "," + this.rgb.g + "," + this.rgb.b + ")";
   },

   asHex: function() {
      return "#" + this.rgb.r.toColorPart() + this.rgb.g.toColorPart() + this.rgb.b.toColorPart();
   },

   asHSB: function() {
      return Rico.Color.RGBtoHSB(this.rgb.r, this.rgb.g, this.rgb.b);
   },

   toString: function() {
      return this.asHex();
   }

};

Rico.Color.createFromHex = function(hexCode) {
  if(hexCode.length==4) {
    var shortHexCode = hexCode; 
    var hexCode = '#';
    for(var i=1;i<4;i++) hexCode += (shortHexCode.charAt(i) + 
shortHexCode.charAt(i));
  }
   if ( hexCode.indexOf('#') == 0 )
      hexCode = hexCode.substring(1);
   var red   = hexCode.substring(0,2);
   var green = hexCode.substring(2,4);
   var blue  = hexCode.substring(4,6);
   return new Rico.Color( parseInt(red,16), parseInt(green,16), parseInt(blue,16) );
}

/**
 * Factory method for creating a color from the background of
 * an HTML element.
 */
Rico.Color.createColorFromBackground = function(elem) {

   var actualColor = RicoUtil.getElementsComputedStyle($(elem), "backgroundColor", "background-color");

   if ( actualColor == "transparent" && elem.parentNode )
      return Rico.Color.createColorFromBackground(elem.parentNode);

   if ( actualColor == null )
      return new Rico.Color(255,255,255);

   if ( actualColor.indexOf("rgb(") == 0 ) {
      var colors = actualColor.substring(4, actualColor.length - 1 );
      var colorArray = colors.split(",");
      return new Rico.Color( parseInt( colorArray[0] ),
                            parseInt( colorArray[1] ),
                            parseInt( colorArray[2] )  );

   }
   else if ( actualColor.indexOf("#") == 0 ) {
      return Rico.Color.createFromHex(actualColor);
   }
   else
      return new Rico.Color(255,255,255);
}

Rico.Color.HSBtoRGB = function(hue, saturation, brightness) {

   var red   = 0;
	var green = 0;
	var blue  = 0;

   if (saturation == 0) {
      red = parseInt(brightness * 255.0 + 0.5);
	   green = red;
	   blue = red;
	}
	else {
      var h = (hue - Math.floor(hue)) * 6.0;
      var f = h - Math.floor(h);
      var p = brightness * (1.0 - saturation);
      var q = brightness * (1.0 - saturation * f);
      var t = brightness * (1.0 - (saturation * (1.0 - f)));

      switch (parseInt(h)) {
         case 0:
            red   = (brightness * 255.0 + 0.5);
            green = (t * 255.0 + 0.5);
            blue  = (p * 255.0 + 0.5);
            break;
         case 1:
            red   = (q * 255.0 + 0.5);
            green = (brightness * 255.0 + 0.5);
            blue  = (p * 255.0 + 0.5);
            break;
         case 2:
            red   = (p * 255.0 + 0.5);
            green = (brightness * 255.0 + 0.5);
            blue  = (t * 255.0 + 0.5);
            break;
         case 3:
            red   = (p * 255.0 + 0.5);
            green = (q * 255.0 + 0.5);
            blue  = (brightness * 255.0 + 0.5);
            break;
         case 4:
            red   = (t * 255.0 + 0.5);
            green = (p * 255.0 + 0.5);
            blue  = (brightness * 255.0 + 0.5);
            break;
          case 5:
            red   = (brightness * 255.0 + 0.5);
            green = (p * 255.0 + 0.5);
            blue  = (q * 255.0 + 0.5);
            break;
	    }
	}

   return { r : parseInt(red), g : parseInt(green) , b : parseInt(blue) };
}

Rico.Color.RGBtoHSB = function(r, g, b) {

   var hue;
   var saturation;
   var brightness;

   var cmax = (r > g) ? r : g;
   if (b > cmax)
      cmax = b;

   var cmin = (r < g) ? r : g;
   if (b < cmin)
      cmin = b;

   brightness = cmax / 255.0;
   if (cmax != 0)
      saturation = (cmax - cmin)/cmax;
   else
      saturation = 0;

   if (saturation == 0)
      hue = 0;
   else {
      var redc   = (cmax - r)/(cmax - cmin);
    	var greenc = (cmax - g)/(cmax - cmin);
    	var bluec  = (cmax - b)/(cmax - cmin);

    	if (r == cmax)
    	   hue = bluec - greenc;
    	else if (g == cmax)
    	   hue = 2.0 + redc - bluec;
      else
    	   hue = 4.0 + greenc - redc;

    	hue = hue / 6.0;
    	if (hue < 0)
    	   hue = hue + 1.0;
   }

   return { h : hue, s : saturation, b : brightness };
}


//-------------------- ricoCorner.js
Rico.Corner = {

   round: function(e, options) {
      var e = $(e);
      this._setOptions(options);

      var color = this.options.color;
      if ( this.options.color == "fromElement" )
         color = this._background(e);

      var bgColor = this.options.bgColor;
      if ( this.options.bgColor == "fromParent" )
         bgColor = this._background(e.offsetParent);

      this._roundCornersImpl(e, color, bgColor);
   },

   _roundCornersImpl: function(e, color, bgColor) {
      if(this.options.border)
         this._renderBorder(e,bgColor);
      if(this._isTopRounded())
         this._roundTopCorners(e,color,bgColor);
      if(this._isBottomRounded())
         this._roundBottomCorners(e,color,bgColor);
   },

   _renderBorder: function(el,bgColor) {
      var borderValue = "1px solid " + this._borderColor(bgColor);
      var borderL = "border-left: "  + borderValue;
      var borderR = "border-right: " + borderValue;
      var style   = "style='" + borderL + ";" + borderR +  "'";
      el.innerHTML = "<div " + style + ">" + el.innerHTML + "</div>"
   },

   _roundTopCorners: function(el, color, bgColor) {
      var corner = this._createCorner(bgColor);
      for(var i=0 ; i < this.options.numSlices ; i++ )
         corner.appendChild(this._createCornerSlice(color,bgColor,i,"top"));
      el.style.paddingTop = 0;
      el.insertBefore(corner,el.firstChild);
   },

   _roundBottomCorners: function(el, color, bgColor) {
      var corner = this._createCorner(bgColor);
      for(var i=(this.options.numSlices-1) ; i >= 0 ; i-- )
         corner.appendChild(this._createCornerSlice(color,bgColor,i,"bottom"));
      el.style.paddingBottom = 0;
      el.appendChild(corner);
   },

   _createCorner: function(bgColor) {
      var corner = document.createElement("div");
      corner.style.backgroundColor = (this._isTransparent() ? "transparent" : bgColor);
      return corner;
   },

   _createCornerSlice: function(color,bgColor, n, position) {
      var slice = document.createElement("span");

      var inStyle = slice.style;
      inStyle.backgroundColor = color;
      inStyle.display  = "block";
      inStyle.height   = "1px";
      inStyle.overflow = "hidden";
      inStyle.fontSize = "1px";

      var borderColor = this._borderColor(color,bgColor);
      if ( this.options.border && n == 0 ) {
         inStyle.borderTopStyle    = "solid";
         inStyle.borderTopWidth    = "1px";
         inStyle.borderLeftWidth   = "0px";
         inStyle.borderRightWidth  = "0px";
         inStyle.borderBottomWidth = "0px";
         inStyle.height            = "0px"; // assumes css compliant box model
         inStyle.borderColor       = borderColor;
      }
      else if(borderColor) {
         inStyle.borderColor = borderColor;
         inStyle.borderStyle = "solid";
         inStyle.borderWidth = "0px 1px";
      }

      if ( !this.options.compact && (n == (this.options.numSlices-1)) )
         inStyle.height = "2px";

      this._setMargin(slice, n, position);
      this._setBorder(slice, n, position);
      return slice;
   },

   _setOptions: function(options) {
      this.options = {
         corners : "all",
         color   : "fromElement",
         bgColor : "fromParent",
         blend   : true,
         border  : false,
         compact : false
      }
      Object.extend(this.options, options || {});

      this.options.numSlices = this.options.compact ? 2 : 4;
      if ( this._isTransparent() )
         this.options.blend = false;
   },

   _whichSideTop: function() {
      if ( this._hasString(this.options.corners, "all", "top") )
         return "";

      if ( this.options.corners.indexOf("tl") >= 0 && this.options.corners.indexOf("tr") >= 0 )
         return "";

      if (this.options.corners.indexOf("tl") >= 0)
         return "left";
      else if (this.options.corners.indexOf("tr") >= 0)
          return "right";
      return "";
   },

   _whichSideBottom: function() {
      if ( this._hasString(this.options.corners, "all", "bottom") )
         return "";

      if ( this.options.corners.indexOf("bl")>=0 && this.options.corners.indexOf("br")>=0 )
         return "";

      if(this.options.corners.indexOf("bl") >=0)
         return "left";
      else if(this.options.corners.indexOf("br")>=0)
         return "right";
      return "";
   },

   _borderColor : function(color,bgColor) {
      if ( color == "transparent" )
         return bgColor;
      else if ( this.options.border )
         return this.options.border;
      else if ( this.options.blend )
         return this._blend( bgColor, color );
      else
         return "";
   },


   _setMargin: function(el, n, corners) {
      var marginSize = this._marginSize(n);
      var whichSide = corners == "top" ? this._whichSideTop() : this._whichSideBottom();

      if ( whichSide == "left" ) {
         el.style.marginLeft = marginSize + "px"; el.style.marginRight = "0px";
      }
      else if ( whichSide == "right" ) {
         el.style.marginRight = marginSize + "px"; el.style.marginLeft  = "0px";
      }
      else {
         el.style.marginLeft = marginSize + "px"; el.style.marginRight = marginSize + "px";
      }
   },

   _setBorder: function(el,n,corners) {
      var borderSize = this._borderSize(n);
      var whichSide = corners == "top" ? this._whichSideTop() : this._whichSideBottom();
      if ( whichSide == "left" ) {
         el.style.borderLeftWidth = borderSize + "px"; el.style.borderRightWidth = "0px";
      }
      else if ( whichSide == "right" ) {
         el.style.borderRightWidth = borderSize + "px"; el.style.borderLeftWidth  = "0px";
      }
      else {
         el.style.borderLeftWidth = borderSize + "px"; el.style.borderRightWidth = borderSize + "px";
      }
      if (this.options.border != false)
        el.style.borderLeftWidth = borderSize + "px"; el.style.borderRightWidth = borderSize + "px";
   },

   _marginSize: function(n) {
      if ( this._isTransparent() )
         return 0;

      var marginSizes          = [ 5, 3, 2, 1 ];
      var blendedMarginSizes   = [ 3, 2, 1, 0 ];
      var compactMarginSizes   = [ 2, 1 ];
      var smBlendedMarginSizes = [ 1, 0 ];

      if ( this.options.compact && this.options.blend )
         return smBlendedMarginSizes[n];
      else if ( this.options.compact )
         return compactMarginSizes[n];
      else if ( this.options.blend )
         return blendedMarginSizes[n];
      else
         return marginSizes[n];
   },

   _borderSize: function(n) {
      var transparentBorderSizes = [ 5, 3, 2, 1 ];
      var blendedBorderSizes     = [ 2, 1, 1, 1 ];
      var compactBorderSizes     = [ 1, 0 ];
      var actualBorderSizes      = [ 0, 2, 0, 0 ];

      if ( this.options.compact && (this.options.blend || this._isTransparent()) )
         return 1;
      else if ( this.options.compact )
         return compactBorderSizes[n];
      else if ( this.options.blend )
         return blendedBorderSizes[n];
      else if ( this.options.border )
         return actualBorderSizes[n];
      else if ( this._isTransparent() )
         return transparentBorderSizes[n];
      return 0;
   },

   _hasString: function(str) { for(var i=1 ; i<arguments.length ; i++) if (str.indexOf(arguments[i]) >= 0) return true; return false; },
   _blend: function(c1, c2) { var cc1 = Rico.Color.createFromHex(c1); cc1.blend(Rico.Color.createFromHex(c2)); return cc1; },
   _background: function(el) { try { return Rico.Color.createColorFromBackground(el).asHex(); } catch(err) { return "#ffffff"; } },
   _isTransparent: function() { return this.options.color == "transparent"; },
   _isTopRounded: function() { return this._hasString(this.options.corners, "all", "top", "tl", "tr"); },
   _isBottomRounded: function() { return this._hasString(this.options.corners, "all", "bottom", "bl", "br"); },
   _hasSingleTextChild: function(el) { return el.childNodes.length == 1 && el.childNodes[0].nodeType == 3; }
}


//-------------------- ricoDragAndDrop.js
Rico.DragAndDrop = Class.create();

Rico.DragAndDrop.prototype = {

   initialize: function() {
      this.dropZones                = new Array();
      this.draggables               = new Array();
      this.currentDragObjects       = new Array();
      this.dragElement              = null;
      this.lastSelectedDraggable    = null;
      this.currentDragObjectVisible = false;
      this.interestedInMotionEvents = false;
      this._mouseDown = this._mouseDownHandler.bindAsEventListener(this);
      this._mouseMove = this._mouseMoveHandler.bindAsEventListener(this);
      this._mouseUp = this._mouseUpHandler.bindAsEventListener(this);
   },

   registerDropZone: function(aDropZone) {
      this.dropZones[ this.dropZones.length ] = aDropZone;
   },

   deregisterDropZone: function(aDropZone) {
      var newDropZones = new Array();
      var j = 0;
      for ( var i = 0 ; i < this.dropZones.length ; i++ ) {
         if ( this.dropZones[i] != aDropZone )
            newDropZones[j++] = this.dropZones[i];
      }

      this.dropZones = newDropZones;
   },

   clearDropZones: function() {
      this.dropZones = new Array();
   },

   registerDraggable: function( aDraggable ) {
      this.draggables[ this.draggables.length ] = aDraggable;
      this._addMouseDownHandler( aDraggable );
   },

   clearSelection: function() {
      for ( var i = 0 ; i < this.currentDragObjects.length ; i++ )
         this.currentDragObjects[i].deselect();
      this.currentDragObjects = new Array();
      this.lastSelectedDraggable = null;
   },

   hasSelection: function() {
      return this.currentDragObjects.length > 0;
   },

   setStartDragFromElement: function( e, mouseDownElement ) {
      this.origPos = RicoUtil.toDocumentPosition(mouseDownElement);
      this.startx = e.screenX - this.origPos.x
      this.starty = e.screenY - this.origPos.y
      //this.startComponentX = e.layerX ? e.layerX : e.offsetX;
      //this.startComponentY = e.layerY ? e.layerY : e.offsetY;
      //this.adjustedForDraggableSize = false;

      this.interestedInMotionEvents = this.hasSelection();
      this._terminateEvent(e);
   },

   updateSelection: function( draggable, extendSelection ) {
      if ( ! extendSelection )
         this.clearSelection();

      if ( draggable.isSelected() ) {
         this.currentDragObjects.removeItem(draggable);
         draggable.deselect();
         if ( draggable == this.lastSelectedDraggable )
            this.lastSelectedDraggable = null;
      }
      else {
         this.currentDragObjects[ this.currentDragObjects.length ] = draggable;
         draggable.select();
         this.lastSelectedDraggable = draggable;
      }
   },

   _mouseDownHandler: function(e) {
      if ( arguments.length == 0 )
         e = event;

      // if not button 1 ignore it...
      var nsEvent = e.which != undefined;
      if ( (nsEvent && e.which != 1) || (!nsEvent && e.button != 1))
         return;

      var eventTarget      = e.target ? e.target : e.srcElement;
      var draggableObject  = eventTarget.draggable;

      var candidate = eventTarget;
      while (draggableObject == null && candidate.parentNode) {
         candidate = candidate.parentNode;
         draggableObject = candidate.draggable;
      }
   
      if ( draggableObject == null )
         return;

      this.updateSelection( draggableObject, e.ctrlKey );

      // clear the drop zones postion cache...
      if ( this.hasSelection() )
         for ( var i = 0 ; i < this.dropZones.length ; i++ )
            this.dropZones[i].clearPositionCache();

      this.setStartDragFromElement( e, draggableObject.getMouseDownHTMLElement() );
   },


   _mouseMoveHandler: function(e) {
      var nsEvent = e.which != undefined;
      if ( !this.interestedInMotionEvents ) {
         //this._terminateEvent(e);
         return;
      }

      if ( ! this.hasSelection() )
         return;

      if ( ! this.currentDragObjectVisible )
         this._startDrag(e);

      if ( !this.activatedDropZones )
         this._activateRegisteredDropZones();

      //if ( !this.adjustedForDraggableSize )
      //   this._adjustForDraggableSize(e);

      this._updateDraggableLocation(e);
      this._updateDropZonesHover(e);

      this._terminateEvent(e);
   },

   _makeDraggableObjectVisible: function(e)
   {
      if ( !this.hasSelection() )
         return;

      var dragElement;
      if ( this.currentDragObjects.length > 1 )
         dragElement = this.currentDragObjects[0].getMultiObjectDragGUI(this.currentDragObjects);
      else
         dragElement = this.currentDragObjects[0].getSingleObjectDragGUI();

      // go ahead and absolute position it...
      if ( RicoUtil.getElementsComputedStyle(dragElement, "position")  != "absolute" )
         dragElement.style.position = "absolute";

      // need to parent him into the document...
      if ( dragElement.parentNode == null || dragElement.parentNode.nodeType == 11 )
         document.body.appendChild(dragElement);

      this.dragElement = dragElement;
      this._updateDraggableLocation(e);

      this.currentDragObjectVisible = true;
   },

   /**
   _adjustForDraggableSize: function(e) {
      var dragElementWidth  = this.dragElement.offsetWidth;
      var dragElementHeight = this.dragElement.offsetHeight;
      if ( this.startComponentX > dragElementWidth )
         this.startx -= this.startComponentX - dragElementWidth + 2;
      if ( e.offsetY ) {
         if ( this.startComponentY > dragElementHeight )
            this.starty -= this.startComponentY - dragElementHeight + 2;
      }
      this.adjustedForDraggableSize = true;
   },
   **/

   _leftOffset: function(e) {
	   return e.offsetX ? document.body.scrollLeft : 0
	},

   _topOffset: function(e) {
	   return e.offsetY ? document.body.scrollTop:0
	},

		
   _updateDraggableLocation: function(e) {
      var dragObjectStyle = this.dragElement.style;
      dragObjectStyle.left = (e.screenX + this._leftOffset(e) - this.startx) + "px"
      dragObjectStyle.top  = (e.screenY + this._topOffset(e) - this.starty) + "px";
   },

   _updateDropZonesHover: function(e) {
      var n = this.dropZones.length;
      for ( var i = 0 ; i < n ; i++ ) {
         if ( ! this._mousePointInDropZone( e, this.dropZones[i] ) )
            this.dropZones[i].hideHover();
      }

      for ( var i = 0 ; i < n ; i++ ) {
         if ( this._mousePointInDropZone( e, this.dropZones[i] ) ) {
            if ( this.dropZones[i].canAccept(this.currentDragObjects) )
               this.dropZones[i].showHover();
         }
      }
   },

   _startDrag: function(e) {
      for ( var i = 0 ; i < this.currentDragObjects.length ; i++ )
         this.currentDragObjects[i].startDrag();

      this._makeDraggableObjectVisible(e);
   },

   _mouseUpHandler: function(e) {
      if ( ! this.hasSelection() )
         return;

      var nsEvent = e.which != undefined;
      if ( (nsEvent && e.which != 1) || (!nsEvent && e.button != 1))
         return;

      this.interestedInMotionEvents = false;

      if ( this.dragElement == null ) {
         this._terminateEvent(e);
         return;
      }

      if ( this._placeDraggableInDropZone(e) )
         this._completeDropOperation(e);
      else {
         this._terminateEvent(e);
         new Rico.Effect.Position( this.dragElement,
                              this.origPos.x,
                              this.origPos.y,
                              200,
                              20,
                              { complete : this._doCancelDragProcessing.bind(this) } );
      }

     Event.stopObserving(document.body, "mousemove", this._mouseMove);
     Event.stopObserving(document.body, "mouseup",  this._mouseUp);
   },

   _retTrue: function () {
      return true;
   },

   _completeDropOperation: function(e) {
      if ( this.dragElement != this.currentDragObjects[0].getMouseDownHTMLElement() ) {
         if ( this.dragElement.parentNode != null )
            this.dragElement.parentNode.removeChild(this.dragElement);
      }

      this._deactivateRegisteredDropZones();
      this._endDrag();
      this.clearSelection();
      this.dragElement = null;
      this.currentDragObjectVisible = false;
      this._terminateEvent(e);
   },

   _doCancelDragProcessing: function() {
      this._cancelDrag();

        if ( this.dragElement != this.currentDragObjects[0].getMouseDownHTMLElement() && this.dragElement)
           if ( this.dragElement.parentNode != null )
              this.dragElement.parentNode.removeChild(this.dragElement);


      this._deactivateRegisteredDropZones();
      this.dragElement = null;
      this.currentDragObjectVisible = false;
   },

   _placeDraggableInDropZone: function(e) {
      var foundDropZone = false;
      var n = this.dropZones.length;
      for ( var i = 0 ; i < n ; i++ ) {
         if ( this._mousePointInDropZone( e, this.dropZones[i] ) ) {
            if ( this.dropZones[i].canAccept(this.currentDragObjects) ) {
               this.dropZones[i].hideHover();
               this.dropZones[i].accept(this.currentDragObjects);
               foundDropZone = true;
               break;
            }
         }
      }

      return foundDropZone;
   },

   _cancelDrag: function() {
      for ( var i = 0 ; i < this.currentDragObjects.length ; i++ )
         this.currentDragObjects[i].cancelDrag();
   },

   _endDrag: function() {
      for ( var i = 0 ; i < this.currentDragObjects.length ; i++ )
         this.currentDragObjects[i].endDrag();
   },

   _mousePointInDropZone: function( e, dropZone ) {

      var absoluteRect = dropZone.getAbsoluteRect();

      return e.clientX  > absoluteRect.left + this._leftOffset(e) &&
             e.clientX  < absoluteRect.right + this._leftOffset(e) &&
             e.clientY  > absoluteRect.top + this._topOffset(e)   &&
             e.clientY  < absoluteRect.bottom + this._topOffset(e);
   },

   _addMouseDownHandler: function( aDraggable )
   {
       htmlElement  = aDraggable.getMouseDownHTMLElement();
      if ( htmlElement  != null ) { 
         htmlElement.draggable = aDraggable;
         Event.observe(htmlElement , "mousedown", this._onmousedown.bindAsEventListener(this));
         Event.observe(htmlElement, "mousedown", this._mouseDown);
      }
   },

   _activateRegisteredDropZones: function() {
      var n = this.dropZones.length;
      for ( var i = 0 ; i < n ; i++ ) {
         var dropZone = this.dropZones[i];
         if ( dropZone.canAccept(this.currentDragObjects) )
            dropZone.activate();
      }

      this.activatedDropZones = true;
   },

   _deactivateRegisteredDropZones: function() {
      var n = this.dropZones.length;
      for ( var i = 0 ; i < n ; i++ )
         this.dropZones[i].deactivate();
      this.activatedDropZones = false;
   },

   _onmousedown: function () {
     Event.observe(document.body, "mousemove", this._mouseMove);
     Event.observe(document.body, "mouseup",  this._mouseUp);
   },

   _terminateEvent: function(e) {
      if ( e.stopPropagation != undefined )
         e.stopPropagation();
      else if ( e.cancelBubble != undefined )
         e.cancelBubble = true;

      if ( e.preventDefault != undefined )
         e.preventDefault();
      else
         e.returnValue = false;
   },


	   initializeEventHandlers: function() {
	      if ( typeof document.implementation != "undefined" &&
	         document.implementation.hasFeature("HTML",   "1.0") &&
	         document.implementation.hasFeature("Events", "2.0") &&
	         document.implementation.hasFeature("CSS",    "2.0") ) {
	         document.addEventListener("mouseup",   this._mouseUpHandler.bindAsEventListener(this),  false);
	         document.addEventListener("mousemove", this._mouseMoveHandler.bindAsEventListener(this), false);
	      }
	      else {
	         document.attachEvent( "onmouseup",   this._mouseUpHandler.bindAsEventListener(this) );
	         document.attachEvent( "onmousemove", this._mouseMoveHandler.bindAsEventListener(this) );
	      }
	   }
	}

	var dndMgr = new Rico.DragAndDrop();
	dndMgr.initializeEventHandlers();


//-------------------- ricoDraggable.js
Rico.Draggable = Class.create();

Rico.Draggable.prototype = {

   initialize: function( type, htmlElement ) {
      this.type          = type;
      this.htmlElement   = $(htmlElement);
      this.selected      = false;
   },

   /**
    *   Returns the HTML element that should have a mouse down event
    *   added to it in order to initiate a drag operation
    *
    **/
   getMouseDownHTMLElement: function() {
      return this.htmlElement;
   },

   select: function() {
      this.selected = true;

      if ( this.showingSelected )
         return;

      var htmlElement = this.getMouseDownHTMLElement();

      var color = Rico.Color.createColorFromBackground(htmlElement);
      color.isBright() ? color.darken(0.033) : color.brighten(0.033);

      this.saveBackground = RicoUtil.getElementsComputedStyle(htmlElement, "backgroundColor", "background-color");
      htmlElement.style.backgroundColor = color.asHex();
      this.showingSelected = true;
   },

   deselect: function() {
      this.selected = false;
      if ( !this.showingSelected )
         return;

      var htmlElement = this.getMouseDownHTMLElement();

      htmlElement.style.backgroundColor = this.saveBackground;
      this.showingSelected = false;
   },

   isSelected: function() {
      return this.selected;
   },

   startDrag: function() {
   },

   cancelDrag: function() {
   },

   endDrag: function() {
   },

   getSingleObjectDragGUI: function() {
      return this.htmlElement;
   },

   getMultiObjectDragGUI: function( draggables ) {
      return this.htmlElement;
   },

   getDroppedGUI: function() {
      return this.htmlElement;
   },

   toString: function() {
      return this.type + ":" + this.htmlElement + ":";
   }

}


//-------------------- ricoDropzone.js
Rico.Dropzone = Class.create();

Rico.Dropzone.prototype = {

   initialize: function( htmlElement ) {
      this.htmlElement  = $(htmlElement);
      this.absoluteRect = null;
   },

   getHTMLElement: function() {
      return this.htmlElement;
   },

   clearPositionCache: function() {
      this.absoluteRect = null;
   },

   getAbsoluteRect: function() {
      if ( this.absoluteRect == null ) {
         var htmlElement = this.getHTMLElement();
         var pos = RicoUtil.toViewportPosition(htmlElement);

         this.absoluteRect = {
            top:    pos.y,
            left:   pos.x,
            bottom: pos.y + htmlElement.offsetHeight,
            right:  pos.x + htmlElement.offsetWidth
         };
      }
      return this.absoluteRect;
   },

   activate: function() {
      var htmlElement = this.getHTMLElement();
      if (htmlElement == null  || this.showingActive)
         return;

      this.showingActive = true;
      this.saveBackgroundColor = htmlElement.style.backgroundColor;

      var fallbackColor = "#ffea84";
      var currentColor = Rico.Color.createColorFromBackground(htmlElement);
      if ( currentColor == null )
         htmlElement.style.backgroundColor = fallbackColor;
      else {
         currentColor.isBright() ? currentColor.darken(0.2) : currentColor.brighten(0.2);
         htmlElement.style.backgroundColor = currentColor.asHex();
      }
   },

   deactivate: function() {
      var htmlElement = this.getHTMLElement();
      if (htmlElement == null || !this.showingActive)
         return;

      htmlElement.style.backgroundColor = this.saveBackgroundColor;
      this.showingActive = false;
      this.saveBackgroundColor = null;
   },

   showHover: function() {
      var htmlElement = this.getHTMLElement();
      if ( htmlElement == null || this.showingHover )
         return;

      this.saveBorderWidth = htmlElement.style.borderWidth;
      this.saveBorderStyle = htmlElement.style.borderStyle;
      this.saveBorderColor = htmlElement.style.borderColor;

      this.showingHover = true;
      htmlElement.style.borderWidth = "1px";
      htmlElement.style.borderStyle = "solid";
      //htmlElement.style.borderColor = "#ff9900";
      htmlElement.style.borderColor = "#ffff00";
   },

   hideHover: function() {
      var htmlElement = this.getHTMLElement();
      if ( htmlElement == null || !this.showingHover )
         return;

      htmlElement.style.borderWidth = this.saveBorderWidth;
      htmlElement.style.borderStyle = this.saveBorderStyle;
      htmlElement.style.borderColor = this.saveBorderColor;
      this.showingHover = false;
   },

   canAccept: function(draggableObjects) {
      return true;
   },

   accept: function(draggableObjects) {
      var htmlElement = this.getHTMLElement();
      if ( htmlElement == null )
         return;

      n = draggableObjects.length;
      for ( var i = 0 ; i < n ; i++ )
      {
         var theGUI = draggableObjects[i].getDroppedGUI();
         if ( RicoUtil.getElementsComputedStyle( theGUI, "position" ) == "absolute" )
         {
            theGUI.style.position = "static";
            theGUI.style.top = "";
            theGUI.style.top = "";
         }
         htmlElement.appendChild(theGUI);
      }
   }
}


//-------------------- ricoEffects.js

Rico.Effect = {};

Rico.Effect.SizeAndPosition = Class.create();
Rico.Effect.SizeAndPosition.prototype = {

   initialize: function(element, x, y, w, h, duration, steps, options) {
      this.element = $(element);
      this.x = x;
      this.y = y;
      this.w = w;
      this.h = h;
      this.duration = duration;
      this.steps    = steps;
      this.options  = arguments[7] || {};

      this.sizeAndPosition();
   },

   sizeAndPosition: function() {
      if (this.isFinished()) {
         if(this.options.complete) this.options.complete(this);
         return;
      }

      if (this.timer)
         clearTimeout(this.timer);

      var stepDuration = Math.round(this.duration/this.steps) ;

      // Get original values: x,y = top left corner;  w,h = width height
      var currentX = this.element.offsetLeft;
      var currentY = this.element.offsetTop;
      var currentW = this.element.offsetWidth;
      var currentH = this.element.offsetHeight;

      // If values not set, or zero, we do not modify them, and take original as final as well
      this.x = (this.x) ? this.x : currentX;
      this.y = (this.y) ? this.y : currentY;
      this.w = (this.w) ? this.w : currentW;
      this.h = (this.h) ? this.h : currentH;

      // how much do we need to modify our values for each step?
      var difX = this.steps >  0 ? (this.x - currentX)/this.steps : 0;
      var difY = this.steps >  0 ? (this.y - currentY)/this.steps : 0;
      var difW = this.steps >  0 ? (this.w - currentW)/this.steps : 0;
      var difH = this.steps >  0 ? (this.h - currentH)/this.steps : 0;

      this.moveBy(difX, difY);
      this.resizeBy(difW, difH);

      this.duration -= stepDuration;
      this.steps--;

      this.timer = setTimeout(this.sizeAndPosition.bind(this), stepDuration);
   },

   isFinished: function() {
      return this.steps <= 0;
   },

   moveBy: function( difX, difY ) {
      var currentLeft = this.element.offsetLeft;
      var currentTop  = this.element.offsetTop;
      var intDifX     = parseInt(difX);
      var intDifY     = parseInt(difY);

      var style = this.element.style;
      if ( intDifX != 0 )
         style.left = (currentLeft + intDifX) + "px";
      if ( intDifY != 0 )
         style.top  = (currentTop + intDifY) + "px";
   },

   resizeBy: function( difW, difH ) {
      var currentWidth  = this.element.offsetWidth;
      var currentHeight = this.element.offsetHeight;
      var intDifW       = parseInt(difW);
      var intDifH       = parseInt(difH);

      var style = this.element.style;
      if ( intDifW != 0 )
         style.width   = (currentWidth  + intDifW) + "px";
      if ( intDifH != 0 )
         style.height  = (currentHeight + intDifH) + "px";
   }
}

Rico.Effect.Size = Class.create();
Rico.Effect.Size.prototype = {

   initialize: function(element, w, h, duration, steps, options) {
      new Rico.Effect.SizeAndPosition(element, null, null, w, h, duration, steps, options);
  }
}

Rico.Effect.Position = Class.create();
Rico.Effect.Position.prototype = {

   initialize: function(element, x, y, duration, steps, options) {
      new Rico.Effect.SizeAndPosition(element, x, y, null, null, duration, steps, options);
  }
}

Rico.Effect.Round = Class.create();
Rico.Effect.Round.prototype = {

   initialize: function(tagName, className, options) {
      var elements = document.getElementsByTagAndClassName(tagName,className);
      for ( var i = 0 ; i < elements.length ; i++ )
         Rico.Corner.round( elements[i], options );
   }
};

Rico.Effect.FadeTo = Class.create();
Rico.Effect.FadeTo.prototype = {

   initialize: function( element, opacity, duration, steps, options) {
      this.element  = $(element);
      this.opacity  = opacity;
      this.duration = duration;
      this.steps    = steps;
      this.options  = arguments[4] || {};
      this.fadeTo();
   },

   fadeTo: function() {
      if (this.isFinished()) {
         if(this.options.complete) this.options.complete(this);
         return;
      }

      if (this.timer)
         clearTimeout(this.timer);

      var stepDuration = Math.round(this.duration/this.steps) ;
      var currentOpacity = this.getElementOpacity();
      var delta = this.steps > 0 ? (this.opacity - currentOpacity)/this.steps : 0;

      this.changeOpacityBy(delta);
      this.duration -= stepDuration;
      this.steps--;

      this.timer = setTimeout(this.fadeTo.bind(this), stepDuration);
   },

   changeOpacityBy: function(v) {
      var currentOpacity = this.getElementOpacity();
      var newOpacity = Math.max(0, Math.min(currentOpacity+v, 1));
      this.element.ricoOpacity = newOpacity;

      this.element.style.filter = "alpha(opacity:"+Math.round(newOpacity*100)+")";
      this.element.style.opacity = newOpacity; /*//*/;
   },

   isFinished: function() {
      return this.steps <= 0;
   },

   getElementOpacity: function() {
      if ( this.element.ricoOpacity == undefined ) {
         var opacity = RicoUtil.getElementsComputedStyle(this.element, 'opacity');
         this.element.ricoOpacity = opacity != undefined ? opacity : 1.0;
      }
      return parseFloat(this.element.ricoOpacity);
   }
}

Rico.Effect.AccordionSize = Class.create();

Rico.Effect.AccordionSize.prototype = {

   initialize: function(e1, e2, start, end, duration, steps, options) {
      this.e1       = $(e1);
      this.e2       = $(e2);
      this.start    = start;
      this.end      = end;
      this.duration = duration;
      this.steps    = steps;
      this.options  = arguments[6] || {};

      this.accordionSize();
   },

   accordionSize: function() {

      if (this.isFinished()) {
         // just in case there are round errors or such...
         this.e1.style.height = this.start + "px";
         this.e2.style.height = this.end + "px";

         if(this.options.complete)
            this.options.complete(this);
         return;
      }

      if (this.timer)
         clearTimeout(this.timer);

      var stepDuration = Math.round(this.duration/this.steps) ;

      var diff = this.steps > 0 ? (parseInt(this.e1.offsetHeight) - this.start)/this.steps : 0;
      this.resizeBy(diff);

      this.duration -= stepDuration;
      this.steps--;

      this.timer = setTimeout(this.accordionSize.bind(this), stepDuration);
   },

   isFinished: function() {
      return this.steps <= 0;
   },

   resizeBy: function(diff) {
      var h1Height = this.e1.offsetHeight;
      var h2Height = this.e2.offsetHeight;
      var intDiff = parseInt(diff);
      if ( diff != 0 ) {
         this.e1.style.height = (h1Height - intDiff) + "px";
         this.e2.style.height = (h2Height + intDiff) + "px";
      }
   }

};


//-------------------- ricoLiveGrid.js
// Rico.LiveGridMetaData -----------------------------------------------------

Rico.LiveGridMetaData = Class.create();

Rico.LiveGridMetaData.prototype = {

   initialize: function( pageSize, totalRows, columnCount, options ) {
      this.pageSize  = pageSize;
      this.totalRows = totalRows;
      this.setOptions(options);
      this.ArrowHeight = 16;
      this.columnCount = columnCount;
   },

   setOptions: function(options) {
      this.options = {
         largeBufferSize    : 7.0,   // 7 pages
         nearLimitFactor    : 0.2    // 20% of buffer
      };
      Object.extend(this.options, options || {});
   },

   getPageSize: function() {
      return this.pageSize;
   },

   getTotalRows: function() {
      return this.totalRows;
   },

   setTotalRows: function(n) {
      this.totalRows = n;
   },

   getLargeBufferSize: function() {
      return parseInt(this.options.largeBufferSize * this.pageSize);
   },

   getLimitTolerance: function() {
      return parseInt(this.getLargeBufferSize() * this.options.nearLimitFactor);
   }
};

// Rico.LiveGridScroller -----------------------------------------------------

Rico.LiveGridScroller = Class.create();

Rico.LiveGridScroller.prototype = {

   initialize: function(liveGrid, viewPort) {
      this.isIE = navigator.userAgent.toLowerCase().indexOf("msie") >= 0;
      this.liveGrid = liveGrid;
      this.metaData = liveGrid.metaData;
      this.createScrollBar();
      this.scrollTimeout = null;
      this.lastScrollPos = 0;
      this.viewPort = viewPort;
      this.rows = new Array();
   },

   isUnPlugged: function() {
      return this.scrollerDiv.onscroll == null;
   },

   plugin: function() {
      this.scrollerDiv.onscroll = this.handleScroll.bindAsEventListener(this);
   },

   unplug: function() {
      this.scrollerDiv.onscroll = null;
   },

   sizeIEHeaderHack: function() {
      if ( !this.isIE ) return;
      var headerTable = $(this.liveGrid.tableId + "_header");
      if ( headerTable )
         headerTable.rows[0].cells[0].style.width =
            (headerTable.rows[0].cells[0].offsetWidth + 1) + "px";
   },

   createScrollBar: function() {
      var visibleHeight = this.liveGrid.viewPort.visibleHeight();
      // create the outer div...
      this.scrollerDiv  = document.createElement("div");
      var scrollerStyle = this.scrollerDiv.style;
      scrollerStyle.borderRight = this.liveGrid.options.scrollerBorderRight;
      scrollerStyle.position    = "relative";
      scrollerStyle.left        = this.isIE ? "-6px" : "-3px";
      scrollerStyle.width       = "19px";
      scrollerStyle.height      = visibleHeight + "px";
      scrollerStyle.overflow    = "auto";

      // create the inner div...
      this.heightDiv = document.createElement("div");
      this.heightDiv.style.width  = "1px";

      this.heightDiv.style.height = parseInt(visibleHeight *
                        this.metaData.getTotalRows()/this.metaData.getPageSize()) + "px" ;
      this.scrollerDiv.appendChild(this.heightDiv);
      this.scrollerDiv.onscroll = this.handleScroll.bindAsEventListener(this);

     var table = this.liveGrid.table;
     table.parentNode.parentNode.insertBefore( this.scrollerDiv, table.parentNode.nextSibling );
  	  var eventName = this.isIE ? "mousewheel" : "DOMMouseScroll";
	  Event.observe(table, eventName, 
	                function(evt) {
	                   if (evt.wheelDelta>=0 || evt.detail < 0) //wheel-up
	                      this.scrollerDiv.scrollTop -= (2*this.viewPort.rowHeight);
	                   else
	                      this.scrollerDiv.scrollTop += (2*this.viewPort.rowHeight);
	                   this.handleScroll(false);
	                }.bindAsEventListener(this), 
	                false);
     },

   updateSize: function() {
      var table = this.liveGrid.table;
      var visibleHeight = this.viewPort.visibleHeight();
      this.heightDiv.style.height = parseInt(visibleHeight *
                                  this.metaData.getTotalRows()/this.metaData.getPageSize()) + "px";
   },

   rowToPixel: function(rowOffset) {
      return (rowOffset / this.metaData.getTotalRows()) * this.heightDiv.offsetHeight
   },
   
   moveScroll: function(rowOffset) {
      this.scrollerDiv.scrollTop = this.rowToPixel(rowOffset);
      if ( this.metaData.options.onscroll )
         this.metaData.options.onscroll( this.liveGrid, rowOffset );
   },

   handleScroll: function() {
     if ( this.scrollTimeout )
         clearTimeout( this.scrollTimeout );

    var scrollDiff = this.lastScrollPos-this.scrollerDiv.scrollTop;
    if (scrollDiff != 0.00) {
       var r = this.scrollerDiv.scrollTop % this.viewPort.rowHeight;
       if (r != 0) {
          this.unplug();
          if ( scrollDiff < 0 ) {
             this.scrollerDiv.scrollTop += (this.viewPort.rowHeight-r);
          } else {
             this.scrollerDiv.scrollTop -= r;
          }
          this.plugin();
       }
    }
    var contentOffset = parseInt(this.scrollerDiv.scrollTop / this.viewPort.rowHeight);
    this.liveGrid.requestContentRefresh(contentOffset);
    this.viewPort.scrollTo(this.scrollerDiv.scrollTop);

    if ( this.metaData.options.onscroll )
       this.metaData.options.onscroll( this.liveGrid, contentOffset );

    this.scrollTimeout = setTimeout(this.scrollIdle.bind(this), 1200 );
    this.lastScrollPos = this.scrollerDiv.scrollTop;

   },

   scrollIdle: function() {
      if ( this.metaData.options.onscrollidle )
         this.metaData.options.onscrollidle();
   }
};

// Rico.LiveGridBuffer -----------------------------------------------------

Rico.LiveGridBuffer = Class.create();

Rico.LiveGridBuffer.prototype = {

   initialize: function(metaData, viewPort) {
      this.startPos = 0;
      this.size     = 0;
      this.metaData = metaData;
      this.rows     = new Array();
      this.updateInProgress = false;
      this.viewPort = viewPort;
      this.maxBufferSize = metaData.getLargeBufferSize() * 2;
      this.maxFetchSize = metaData.getLargeBufferSize();
      this.lastOffset = 0;
   },

   getBlankRow: function() {
      if (!this.blankRow ) {
         this.blankRow = new Array();
         for ( var i=0; i < this.metaData.columnCount ; i++ ) 
            this.blankRow[i] = "&nbsp;";
     }
     return this.blankRow;
   },

   loadRows: function(ajaxResponse) {
      var rowsElement = ajaxResponse.getElementsByTagName('rows')[0];
      this.updateUI = rowsElement.getAttribute("update_ui") == "true"
      var newRows = new Array()
      var trs = rowsElement.getElementsByTagName("tr");
      for ( var i=0 ; i < trs.length; i++ ) {
         var row = newRows[i] = new Array(); 
         var cells = trs[i].getElementsByTagName("td");
         for ( var j=0; j < cells.length ; j++ ) {
            var cell = cells[j];
            var convertSpaces = cell.getAttribute("convert_spaces") == "true";
            var cellContent = RicoUtil.getContentAsString(cell);
            row[j] = convertSpaces ? this.convertSpaces(cellContent) : cellContent;
            if (!row[j]) 
               row[j] = '&nbsp;';
         }
      }
      return newRows;
   },
      
   update: function(ajaxResponse, start) {
     var newRows = this.loadRows(ajaxResponse);
      if (this.rows.length == 0) { // initial load
         this.rows = newRows;
         this.size = this.rows.length;
         this.startPos = start;
         return;
      }
      if (start > this.startPos) { //appending
         if (this.startPos + this.rows.length < start) {
            this.rows =  newRows;
            this.startPos = start;//
         } else {
              this.rows = this.rows.concat( newRows.slice(0, newRows.length));
            if (this.rows.length > this.maxBufferSize) {
               var fullSize = this.rows.length;
               this.rows = this.rows.slice(this.rows.length - this.maxBufferSize, this.rows.length)
               this.startPos = this.startPos +  (fullSize - this.rows.length);
            }
         }
      } else { //prepending
         if (start + newRows.length < this.startPos) {
            this.rows =  newRows;
         } else {
            this.rows = newRows.slice(0, this.startPos).concat(this.rows);
            if (this.rows.length > this.maxBufferSize) 
               this.rows = this.rows.slice(0, this.maxBufferSize)
         }
         this.startPos =  start;
      }
      this.size = this.rows.length;
   },
   
   clear: function() {
      this.rows = new Array();
      this.startPos = 0;
      this.size = 0;
   },

   isOverlapping: function(start, size) {
      return ((start < this.endPos()) && (this.startPos < start + size)) || (this.endPos() == 0)
   },

   isInRange: function(position) {
      return (position >= this.startPos) && (position + this.metaData.getPageSize() <= this.endPos()); 
             //&& this.size()  != 0;
   },

   isNearingTopLimit: function(position) {
      return position - this.startPos < this.metaData.getLimitTolerance();
   },

   endPos: function() {
      return this.startPos + this.rows.length;
   },
   
   isNearingBottomLimit: function(position) {
      return this.endPos() - (position + this.metaData.getPageSize()) < this.metaData.getLimitTolerance();
   },

   isAtTop: function() {
      return this.startPos == 0;
   },

   isAtBottom: function() {
      return this.endPos() == this.metaData.getTotalRows();
   },

   isNearingLimit: function(position) {
      return ( !this.isAtTop()    && this.isNearingTopLimit(position)) ||
             ( !this.isAtBottom() && this.isNearingBottomLimit(position) )
   },

   getFetchSize: function(offset) {
      var adjustedOffset = this.getFetchOffset(offset);
      var adjustedSize = 0;
      if (adjustedOffset >= this.startPos) { //apending
         var endFetchOffset = this.maxFetchSize  + adjustedOffset;
         if (endFetchOffset > this.metaData.totalRows)
            endFetchOffset = this.metaData.totalRows;
         adjustedSize = endFetchOffset - adjustedOffset;  
			if(adjustedOffset == 0 && adjustedSize < this.maxFetchSize){
			   adjustedSize = this.maxFetchSize;
			}
      } else {//prepending
         var adjustedSize = this.startPos - adjustedOffset;
         if (adjustedSize > this.maxFetchSize)
            adjustedSize = this.maxFetchSize;
      }
      return adjustedSize;
   }, 

   getFetchOffset: function(offset) {
      var adjustedOffset = offset;
      if (offset > this.startPos)  //apending
         adjustedOffset = (offset > this.endPos()) ? offset :  this.endPos(); 
      else { //prepending
         if (offset + this.maxFetchSize >= this.startPos) {
            var adjustedOffset = this.startPos - this.maxFetchSize;
            if (adjustedOffset < 0)
               adjustedOffset = 0;
         }
      }
      this.lastOffset = adjustedOffset;
      return adjustedOffset;
   },

   getRows: function(start, count) {
      var begPos = start - this.startPos
      var endPos = begPos + count

      // er? need more data...
      if ( endPos > this.size )
         endPos = this.size

      var results = new Array()
      var index = 0;
      for ( var i=begPos ; i < endPos; i++ ) {
         results[index++] = this.rows[i]
      }
      return results
   },

   convertSpaces: function(s) {
      return s.split(" ").join("&nbsp;");
   }

};


//Rico.GridViewPort --------------------------------------------------
Rico.GridViewPort = Class.create();

Rico.GridViewPort.prototype = {

   initialize: function(table, rowHeight, visibleRows, buffer, liveGrid) {
      this.lastDisplayedStartPos = 0;
      this.div = table.parentNode;
      this.table = table
      this.rowHeight = rowHeight;
      this.div.style.height = (this.rowHeight * visibleRows) + "px";
      this.div.style.overflow = "hidden";
      this.buffer = buffer;
      this.liveGrid = liveGrid;
      this.visibleRows = visibleRows + 1;
      this.lastPixelOffset = 0;
      this.startPos = 0;
   },

   populateRow: function(htmlRow, row) {
      for (var j=0; j < row.length; j++) {
         htmlRow.cells[j].innerHTML = row[j]
      }
   },
   
   bufferChanged: function() {
      this.refreshContents( parseInt(this.lastPixelOffset / this.rowHeight));
   },
   
   clearRows: function() {
      if (!this.isBlank) {
         this.liveGrid.table.className = this.liveGrid.options.loadingClass;
         for (var i=0; i < this.visibleRows; i++)
            this.populateRow(this.table.rows[i], this.buffer.getBlankRow());
         this.isBlank = true;
      }
   },
   
   clearContents: function() {   
      this.clearRows();
      this.scrollTo(0);
      this.startPos = 0;
      this.lastStartPos = -1;   
   },
   
   refreshContents: function(startPos) {
      if (startPos == this.lastRowPos && !this.isPartialBlank && !this.isBlank) {
         return;
      }
      if ((startPos + this.visibleRows < this.buffer.startPos)  
          || (this.buffer.startPos + this.buffer.size < startPos) 
          || (this.buffer.size == 0)) {
         this.clearRows();
         return;
      }
      this.isBlank = false;
      var viewPrecedesBuffer = this.buffer.startPos > startPos
      var contentStartPos = viewPrecedesBuffer ? this.buffer.startPos: startPos; 
      var contentEndPos = (this.buffer.startPos + this.buffer.size < startPos + this.visibleRows) 
                                 ? this.buffer.startPos + this.buffer.size
                                 : startPos + this.visibleRows;
      var rowSize = contentEndPos - contentStartPos;
      var rows = this.buffer.getRows(contentStartPos, rowSize ); 
      var blankSize = this.visibleRows - rowSize;
      var blankOffset = viewPrecedesBuffer ? 0: rowSize;
      var contentOffset = viewPrecedesBuffer ? blankSize: 0;

      for (var i=0; i < rows.length; i++) {//initialize what we have
        this.populateRow(this.table.rows[i + contentOffset], rows[i]);
      }
      for (var i=0; i < blankSize; i++) {// blank out the rest 
        this.populateRow(this.table.rows[i + blankOffset], this.buffer.getBlankRow());
      }
      this.isPartialBlank = blankSize > 0;
      this.lastRowPos = startPos;

       this.liveGrid.table.className = this.liveGrid.options.tableClass;
       // Check if user has set a onRefreshComplete function
       var onRefreshComplete = this.liveGrid.options.onRefreshComplete;
       if (onRefreshComplete != null)
           onRefreshComplete();
   },

   scrollTo: function(pixelOffset) {      
      if (this.lastPixelOffset == pixelOffset)
         return;

      this.refreshContents(parseInt(pixelOffset / this.rowHeight))
      this.div.scrollTop = pixelOffset % this.rowHeight        
      
      this.lastPixelOffset = pixelOffset;
   },
   
   visibleHeight: function() {
      return parseInt(RicoUtil.getElementsComputedStyle(this.div, 'height'));
   }

};


Rico.LiveGridRequest = Class.create();
Rico.LiveGridRequest.prototype = {
   initialize: function( requestOffset, options ) {
      this.requestOffset = requestOffset;
   }
};

// Rico.LiveGrid -----------------------------------------------------

Rico.LiveGrid = Class.create();

Rico.LiveGrid.prototype = {

   initialize: function( tableId, visibleRows, totalRows, url, options, ajaxOptions ) {

     this.options = {
                tableClass:           $(tableId).className,
                loadingClass:         $(tableId).className,
                scrollerBorderRight: '1px solid #ababab',
                bufferTimeout:        20000,
                sortAscendImg:        'images/sort_asc.gif',
                sortDescendImg:       'images/sort_desc.gif',
                sortImageWidth:       9,
                sortImageHeight:      5,
                ajaxSortURLParms:     [],
                onRefreshComplete:    null,
                requestParameters:    null,
                inlineStyles:         true
                };
      Object.extend(this.options, options || {});

      this.ajaxOptions = {parameters: null};
      Object.extend(this.ajaxOptions, ajaxOptions || {});

      this.tableId     = tableId; 
      this.table       = $(tableId);

      this.addLiveGridHtml();

      var columnCount  = this.table.rows[0].cells.length;
      this.metaData    = new Rico.LiveGridMetaData(visibleRows, totalRows, columnCount, options);
      this.buffer      = new Rico.LiveGridBuffer(this.metaData);

      var rowCount = this.table.rows.length;
      this.viewPort =  new Rico.GridViewPort(this.table, 
                                            this.table.offsetHeight/rowCount,
                                            visibleRows,
                                            this.buffer, this);
      this.scroller    = new Rico.LiveGridScroller(this,this.viewPort);
      this.options.sortHandler = this.sortHandler.bind(this);

      if ( $(tableId + '_header') )
         this.sort = new Rico.LiveGridSort(tableId + '_header', this.options)

      this.processingRequest = null;
      this.unprocessedRequest = null;

      this.initAjax(url);
      if ( this.options.prefetchBuffer || this.options.prefetchOffset > 0) {
         var offset = 0;
         if (this.options.offset ) {
            offset = this.options.offset;            
            this.scroller.moveScroll(offset);
            this.viewPort.scrollTo(this.scroller.rowToPixel(offset));            
         }
         if (this.options.sortCol) {
             this.sortCol = options.sortCol;
             this.sortDir = options.sortDir;
         }
         this.requestContentRefresh(offset);
      }
   },

   addLiveGridHtml: function() {
     // Check to see if need to create a header table.
     if (this.table.getElementsByTagName("thead").length > 0){
       // Create Table this.tableId+'_header'
       var tableHeader = this.table.cloneNode(true);
       tableHeader.setAttribute('id', this.tableId+'_header');
       tableHeader.setAttribute('class', this.table.className+'_header');

       // Clean up and insert
       for( var i = 0; i < tableHeader.tBodies.length; i++ ) 
       tableHeader.removeChild(tableHeader.tBodies[i]);
       this.table.deleteTHead();
       this.table.parentNode.insertBefore(tableHeader,this.table);
     }

    new Insertion.Before(this.table, "<div id='"+this.tableId+"_container'></div>");
    this.table.previousSibling.appendChild(this.table);
    new Insertion.Before(this.table,"<div id='"+this.tableId+"_viewport' style='float:left;'></div>");
    this.table.previousSibling.appendChild(this.table);
   },


   resetContents: function() {
      this.scroller.moveScroll(0);
      this.buffer.clear();
      this.viewPort.clearContents();
   },
   
   sortHandler: function(column) {
	   if(!column) return ;
      this.sortCol = column.name;
      this.sortDir = column.currentSort;

      this.resetContents();
      this.requestContentRefresh(0) 
   },

   adjustRowSize: function() {
	  
	},
	
   setTotalRows: function( newTotalRows ) {
      this.resetContents();
      this.metaData.setTotalRows(newTotalRows);
      this.scroller.updateSize();
   },

   initAjax: function(url) {
      ajaxEngine.registerRequest( this.tableId + '_request', url );
      ajaxEngine.registerAjaxObject( this.tableId + '_updater', this );
   },

   invokeAjax: function() {
   },

   handleTimedOut: function() {
      //server did not respond in 4 seconds... assume that there could have been
      //an error or something, and allow requests to be processed again...
      this.processingRequest = null;
      this.processQueuedRequest();
   },

   fetchBuffer: function(offset) {
      if ( this.buffer.isInRange(offset) &&
         !this.buffer.isNearingLimit(offset)) {
         return;
         }
      if (this.processingRequest) {
          this.unprocessedRequest = new Rico.LiveGridRequest(offset);
         return;
      }
      var bufferStartPos = this.buffer.getFetchOffset(offset);
      this.processingRequest = new Rico.LiveGridRequest(offset);
      this.processingRequest.bufferOffset = bufferStartPos;   
      var fetchSize = this.buffer.getFetchSize(offset);
      var partialLoaded = false;
      
      var queryString
      if (this.options.requestParameters)
         queryString = this._createQueryString(this.options.requestParameters, 0);

        queryString = (queryString == null) ? '' : queryString+'&';
        queryString  = queryString+'id='+this.tableId+'&page_size='+fetchSize+'&offset='+bufferStartPos;
        if (this.sortCol)
            queryString = queryString+'&sort_col='+escape(this.sortCol)+'&sort_dir='+this.sortDir;

        this.ajaxOptions.parameters = queryString;

       ajaxEngine.sendRequest( this.tableId + '_request', this.ajaxOptions );

       this.timeoutHandler = setTimeout( this.handleTimedOut.bind(this), this.options.bufferTimeout);

   },

   setRequestParams: function() {
      this.options.requestParameters = [];
      for ( var i=0 ; i < arguments.length ; i++ )
         this.options.requestParameters[i] = arguments[i];
   },

   requestContentRefresh: function(contentOffset) {
      this.fetchBuffer(contentOffset);
   },

   ajaxUpdate: function(ajaxResponse) {
      try {
         clearTimeout( this.timeoutHandler );
         this.buffer.update(ajaxResponse,this.processingRequest.bufferOffset);
         this.viewPort.bufferChanged();
      }
      catch(err) {}
      finally {this.processingRequest = null; }
      this.processQueuedRequest();
   },

   _createQueryString: function( theArgs, offset ) {
      var queryString = ""
      if (!theArgs)
          return queryString;

      for ( var i = offset ; i < theArgs.length ; i++ ) {
          if ( i != offset )
            queryString += "&";

          var anArg = theArgs[i];

          if ( anArg.name != undefined && anArg.value != undefined ) {
            queryString += anArg.name +  "=" + escape(anArg.value);
          }
          else {
             var ePos  = anArg.indexOf('=');
             var argName  = anArg.substring( 0, ePos );
             var argValue = anArg.substring( ePos + 1 );
             queryString += argName + "=" + escape(argValue);
          }
      }
      return queryString;
   },

   processQueuedRequest: function() {
      if (this.unprocessedRequest != null) {
         this.requestContentRefresh(this.unprocessedRequest.requestOffset);
         this.unprocessedRequest = null
      }
   }
};


//-------------------- ricoLiveGridSort.js
Rico.LiveGridSort = Class.create();

Rico.LiveGridSort.prototype = {

   initialize: function(headerTableId, options) {
      this.headerTableId = headerTableId;
      this.headerTable   = $(headerTableId);
      this.options = options;
      this.setOptions();
      this.applySortBehavior();

      if ( this.options.sortCol ) {
         this.setSortUI( this.options.sortCol, this.options.sortDir );
      }
   },

   setSortUI: function( columnName, sortDirection ) {
      var cols = this.options.columns;
      for ( var i = 0 ; i < cols.length ; i++ ) {
         if ( cols[i].name == columnName ) {
            this.setColumnSort(i, sortDirection);
            break;
         }
      }
   },

   setOptions: function() {
      // preload the images...
      new Image().src = this.options.sortAscendImg;
      new Image().src = this.options.sortDescendImg;

      this.sort = this.options.sortHandler;
      if ( !this.options.columns )
         this.options.columns = this.introspectForColumnInfo();
      else {
         // allow client to pass { columns: [ ["a", true], ["b", false] ] }
         // and convert to an array of Rico.TableColumn objs...
         this.options.columns = this.convertToTableColumns(this.options.columns);
      }
   },

   applySortBehavior: function() {
      var headerRow   = this.headerTable.rows[0];
      var headerCells = headerRow.cells;
      for ( var i = 0 ; i < headerCells.length ; i++ ) {
         this.addSortBehaviorToColumn( i, headerCells[i] );
      }
   },

   addSortBehaviorToColumn: function( n, cell ) {
      if ( this.options.columns[n].isSortable() ) {
         cell.id            = this.headerTableId + '_' + n;
         cell.style.cursor  = 'pointer';
         cell.onclick       = this.headerCellClicked.bindAsEventListener(this);
         cell.innerHTML     = cell.innerHTML + '<span id="' + this.headerTableId + '_img_' + n + '">'
                           + '&nbsp;&nbsp;&nbsp;</span>';
      }
   },

   // event handler....
   headerCellClicked: function(evt) {
      var eventTarget = evt.target ? evt.target : evt.srcElement;
      var cellId = eventTarget.id;
      var columnNumber = parseInt(cellId.substring( cellId.lastIndexOf('_') + 1 ));
      var sortedColumnIndex = this.getSortedColumnIndex();
      if ( sortedColumnIndex != -1 ) {
         if ( sortedColumnIndex != columnNumber ) {
            this.removeColumnSort(sortedColumnIndex);
            this.setColumnSort(columnNumber, Rico.TableColumn.SORT_ASC);
         }
         else
            this.toggleColumnSort(sortedColumnIndex);
      }
      else
         this.setColumnSort(columnNumber, Rico.TableColumn.SORT_ASC);

      if (this.options.sortHandler) {
         this.options.sortHandler(this.options.columns[columnNumber]);
      }
   },

   removeColumnSort: function(n) {
      this.options.columns[n].setUnsorted();
      this.setSortImage(n);
   },

   setColumnSort: function(n, direction) {
   	if(isNaN(n)) return ;
      this.options.columns[n].setSorted(direction);
      this.setSortImage(n);
   },

   toggleColumnSort: function(n) {
      this.options.columns[n].toggleSort();
      this.setSortImage(n);
   },

   setSortImage: function(n) {
      var sortDirection = this.options.columns[n].getSortDirection();

      var sortImageSpan = $( this.headerTableId + '_img_' + n );
      if ( sortDirection == Rico.TableColumn.UNSORTED )
         sortImageSpan.innerHTML = '&nbsp;&nbsp;';
      else if ( sortDirection == Rico.TableColumn.SORT_ASC )
         sortImageSpan.innerHTML = '&nbsp;&nbsp;<img width="'  + this.options.sortImageWidth    + '" ' +
                                                     'height="'+ this.options.sortImageHeight   + '" ' +
                                                     'src="'   + this.options.sortAscendImg + '"/>';
      else if ( sortDirection == Rico.TableColumn.SORT_DESC )
         sortImageSpan.innerHTML = '&nbsp;&nbsp;<img width="'  + this.options.sortImageWidth    + '" ' +
                                                     'height="'+ this.options.sortImageHeight   + '" ' +
                                                     'src="'   + this.options.sortDescendImg + '"/>';
   },

   getSortedColumnIndex: function() {
      var cols = this.options.columns;
      for ( var i = 0 ; i < cols.length ; i++ ) {
         if ( cols[i].isSorted() )
            return i;
      }

      return -1;
   },

   introspectForColumnInfo: function() {
      var columns = new Array();
      var headerRow   = this.headerTable.rows[0];
      var headerCells = headerRow.cells;
      for ( var i = 0 ; i < headerCells.length ; i++ )
         columns.push( new Rico.TableColumn( this.deriveColumnNameFromCell(headerCells[i],i), true ) );
      return columns;
   },

   convertToTableColumns: function(cols) {
      var columns = new Array();
      for ( var i = 0 ; i < cols.length ; i++ )
         columns.push( new Rico.TableColumn( cols[i][0], cols[i][1] ) );
      return columns;
   },

   deriveColumnNameFromCell: function(cell,columnNumber) {
      var cellContent = cell.innerText != undefined ? cell.innerText : cell.textContent;
      return cellContent ? cellContent.toLowerCase().split(' ').join('_') : "col_" + columnNumber;
   }
};

Rico.TableColumn = Class.create();

Rico.TableColumn.UNSORTED  = 0;
Rico.TableColumn.SORT_ASC  = "ASC";
Rico.TableColumn.SORT_DESC = "DESC";

Rico.TableColumn.prototype = {
   initialize: function(name, sortable) {
      this.name        = name;
      this.sortable    = sortable;
      this.currentSort = Rico.TableColumn.UNSORTED;
   },

   isSortable: function() {
      return this.sortable;
   },

   isSorted: function() {
      return this.currentSort != Rico.TableColumn.UNSORTED;
   },

   getSortDirection: function() {
      return this.currentSort;
   },

   toggleSort: function() {
      if ( this.currentSort == Rico.TableColumn.UNSORTED || this.currentSort == Rico.TableColumn.SORT_DESC )
         this.currentSort = Rico.TableColumn.SORT_ASC;
      else if ( this.currentSort == Rico.TableColumn.SORT_ASC )
         this.currentSort = Rico.TableColumn.SORT_DESC;
   },

   setUnsorted: function(direction) {
      this.setSorted(Rico.TableColumn.UNSORTED);
   },

   setSorted: function(direction) {
      // direction must by one of Rico.TableColumn.UNSORTED, .SORT_ASC, or .SORT_DESC...
      this.currentSort = direction;
   }

};


//-------------------- ricoUtil.js
var RicoUtil = {

   getElementsComputedStyle: function ( htmlElement, cssProperty, mozillaEquivalentCSS) {
      if ( arguments.length == 2 )
         mozillaEquivalentCSS = cssProperty;

      var el = $(htmlElement);
      if ( el.currentStyle )
         return el.currentStyle[cssProperty];
      else
         return document.defaultView.getComputedStyle(el, null).getPropertyValue(mozillaEquivalentCSS);
   },

   createXmlDocument : function() {
      if (document.implementation && document.implementation.createDocument) {
         var doc = document.implementation.createDocument("", "", null);

         if (doc.readyState == null) {
            doc.readyState = 1;
            doc.addEventListener("load", function () {
               doc.readyState = 4;
               if (typeof doc.onreadystatechange == "function")
                  doc.onreadystatechange();
            }, false);
         }

         return doc;
      }

      if (window.ActiveXObject)
          return Try.these(
            function() { return new ActiveXObject('MSXML2.DomDocument')   },
            function() { return new ActiveXObject('Microsoft.DomDocument')},
            function() { return new ActiveXObject('MSXML.DomDocument')    },
            function() { return new ActiveXObject('MSXML3.DomDocument')   }
          ) || false;

      return null;
   },

   getContentAsString: function( parentNode ) {
      return parentNode.xml != undefined ? 
         this._getContentAsStringIE(parentNode) :
         this._getContentAsStringMozilla(parentNode);
   },

  _getContentAsStringIE: function(parentNode) {
     var contentStr = "";
     for ( var i = 0 ; i < parentNode.childNodes.length ; i++ ) {
         var n = parentNode.childNodes[i];
         if (n.nodeType == 4) {
             contentStr += n.nodeValue;
         }
         else {
           contentStr += n.xml;
       }
     }
     return contentStr;
  },

  _getContentAsStringMozilla: function(parentNode) {
     var xmlSerializer = new XMLSerializer();
     var contentStr = "";
     for ( var i = 0 ; i < parentNode.childNodes.length ; i++ ) {
          var n = parentNode.childNodes[i];
          if (n.nodeType == 4) { // CDATA node
              contentStr += n.nodeValue;
          }
          else {
            contentStr += xmlSerializer.serializeToString(n);
        }
     }
     return contentStr;
  },

   toViewportPosition: function(element) {
      return this._toAbsolute(element,true);
   },

   toDocumentPosition: function(element) {
      return this._toAbsolute(element,false);
   },

   /**
    *  Compute the elements position in terms of the window viewport
    *  so that it can be compared to the position of the mouse (dnd)
    *  This is additions of all the offsetTop,offsetLeft values up the
    *  offsetParent hierarchy, ...taking into account any scrollTop,
    *  scrollLeft values along the way...
    *
    * IE has a bug reporting a correct offsetLeft of elements within a
    * a relatively positioned parent!!!
    **/
   _toAbsolute: function(element,accountForDocScroll) {

      if ( navigator.userAgent.toLowerCase().indexOf("msie") == -1 )
         return this._toAbsoluteMozilla(element,accountForDocScroll);

      var x = 0;
      var y = 0;
      var parent = element;
      while ( parent ) {

         var borderXOffset = 0;
         var borderYOffset = 0;
         if ( parent != element ) {
            var borderXOffset = parseInt(this.getElementsComputedStyle(parent, "borderLeftWidth" ));
            var borderYOffset = parseInt(this.getElementsComputedStyle(parent, "borderTopWidth" ));
            borderXOffset = isNaN(borderXOffset) ? 0 : borderXOffset;
            borderYOffset = isNaN(borderYOffset) ? 0 : borderYOffset;
         }

         x += parent.offsetLeft - parent.scrollLeft + borderXOffset;
         y += parent.offsetTop - parent.scrollTop + borderYOffset;
         parent = parent.offsetParent;
      }

      if ( accountForDocScroll ) {
         x -= this.docScrollLeft();
         y -= this.docScrollTop();
      }

      return { x:x, y:y };
   },

   /**
    *  Mozilla did not report all of the parents up the hierarchy via the
    *  offsetParent property that IE did.  So for the calculation of the
    *  offsets we use the offsetParent property, but for the calculation of
    *  the scrollTop/scrollLeft adjustments we navigate up via the parentNode
    *  property instead so as to get the scroll offsets...
    *
    **/
   _toAbsoluteMozilla: function(element,accountForDocScroll) {
      var x = 0;
      var y = 0;
      var parent = element;
      while ( parent ) {
         x += parent.offsetLeft;
         y += parent.offsetTop;
         parent = parent.offsetParent;
      }

      parent = element;
      while ( parent &&
              parent != document.body &&
              parent != document.documentElement ) {
         if ( parent.scrollLeft  )
            x -= parent.scrollLeft;
         if ( parent.scrollTop )
            y -= parent.scrollTop;
         parent = parent.parentNode;
      }

      if ( accountForDocScroll ) {
         x -= this.docScrollLeft();
         y -= this.docScrollTop();
      }

      return { x:x, y:y };
   },

   docScrollLeft: function() {
      if ( window.pageXOffset )
         return window.pageXOffset;
      else if ( document.documentElement && document.documentElement.scrollLeft )
         return document.documentElement.scrollLeft;
      else if ( document.body )
         return document.body.scrollLeft;
      else
         return 0;
   },

   docScrollTop: function() {
      if ( window.pageYOffset )
         return window.pageYOffset;
      else if ( document.documentElement && document.documentElement.scrollTop )
         return document.documentElement.scrollTop;
      else if ( document.body )
         return document.body.scrollTop;
      else
         return 0;
   }

};
// Class which capture ajax response and handle it. I want to add sorting capability
// once response arrived. We put our reponse to "results" tag id found on internal HTML.
var GetDataUpdater=Class.create();
GetDataUpdater.prototype = {
   initialize: function(tag,mode,nores) {
      if(tag) {
         this.tag=tag;
      } else {
         this.tag='results';
      }
      if(mode && mode=='update') {
         this.mode=mode;
      } else {
         this.mode='replace';
      }
      if(nores) {
         this.nores=1;
      } else {
         this.nores=0;
      }
   },
   ajaxUpdate: function(ajaxResponse) {
     var responseHTML=RicoUtil.getContentAsString(ajaxResponse);
     if(!this.nores) {
        showResultsMenu();
     }
     var t=document.getElementById(this.tag);
     if(this.mode=='update') {
        t.innerHTML+=responseHTML;
     } else {
        t.innerHTML=responseHTML;
     }
     // parse response and search for any JavaScript code there, if found execute it.
     var jsCode = SearchForJSCode(responseHTML);
     if(jsCode) {
        eval(jsCode);
     }
     HideWheel("__"+this.tag);
     if(this.tag=='results') {
        hideWaitingMessage();
     }
   }
}
var UserRegistrationUpdater=Class.create();
UserRegistrationUpdater.prototype = {
   initialize: function() {
   },
   ajaxUpdate: function(ajaxResponse) {
     var responseHTML=RicoUtil.getContentAsString(ajaxResponse);
     var t=document.getElementById('formAuthResults');
     if(responseHTML=='fail') {
        t.innerHTML='<p><span class="box_red">Ups, this login name is already in use.</span> Please choose another name.</p>';
     } else if(responseHTML=='wrong password') {
        t.innerHTML='<p><span class="box_red">Wrong password!!!</span> Please try again.</p>';
     } else {
        t.innerHTML='<p><span class="box_blue">Welcome '+responseHTML+'! Your authentication is completed. You may use your history at any time.</span></p>';
        setGreeting();
     }
   }
}
var HistoryUpdater=Class.create();
HistoryUpdater.prototype = {
   initialize: function(tab) {
     this.tab=tab
   },
   ajaxUpdate: function(ajaxResponse) {
     var responseHTML=RicoUtil.getContentAsString(ajaxResponse);
     var t=document.getElementById(this.tab+'History');
     if(this.tab=='session') {
        t.innerHTML=t.innerHTML+responseHTML;
     } else {
        t.innerHTML=responseHTML;
     }
   }
}
var PhedexUpdater=Class.create();
PhedexUpdater.prototype = {
   initialize: function(tab,mode) {
      this.tab=tab
      // keep always in update mode
      if(mode && mode=='replace') {
         this.mode=mode;
      } else {
         this.mode='update';
      }
   },
   ajaxUpdate: function(ajaxResponse) {
     var responseHTML=RicoUtil.getContentAsString(ajaxResponse);
     var t=document.getElementById(this.tab);
     if(t.innerHTML.indexOf('PhEDEx')>-1||t.innerHTML.indexOf('Phedex')>-1||this.mode=='replace') {
        t.innerHTML=responseHTML;
     } else {
        t.innerHTML=t.innerHTML+responseHTML;
     }
   }
}
var IdUpdater=Class.create();
IdUpdater.prototype = {
   initialize: function(tab,mode) {
      this.tab=tab
      // keep always in update mode
      if(mode && mode=='replace') {
         this.mode=mode;
      } else {
         this.mode='update';
      }
   },
   ajaxUpdate: function(ajaxResponse) {
     var responseHTML=RicoUtil.getContentAsString(ajaxResponse);
     var t=document.getElementById(this.tab);
     if (t) {
        if (this.mode=='update') {
            t.innerHTML=t.innerHTML+responseHTML;
        } else {
            t.innerHTML=responseHTML;
        }
     }
     // parse response and search for any JavaScript code there, if found execute it.
     var jsCode = SearchForJSCode(responseHTML);
     if(jsCode) {
        eval(jsCode);
     }
   }
}
var NameUpdater=Class.create();
NameUpdater.prototype = {
   initialize: function(tab) {
      this.tab=tab
   },
   ajaxUpdate: function(ajaxResponse) {
     var responseHTML=RicoUtil.getContentAsString(ajaxResponse);
     var t=document.getElementsByName(this.tab);
     for(i=0;i<t.length;i++) {
        if (this.mode=='update') {
            t[i].innerHTML=t[i].innerHTML+responseHTML;
        } else {
            t[i].innerHTML=responseHTML;
        }
     }
     // parse response and search for any JavaScript code there, if found execute it.
     var jsCode = SearchForJSCode(responseHTML);
     if(jsCode) {
        eval(jsCode);
     }
   }
}
function registerAjaxHistoryCalls() {
  userRegUpdater = new UserRegistrationUpdater();
  ajaxEngine.registerRequest('ajaxCheckUser','checkUser');
  ajaxEngine.registerAjaxObject('historyUserName',userRegUpdater);
//  sessionHistoryUpdater = new HistoryUpdater('session');
  ajaxEngine.registerRequest('ajaxHistory','history');
//  ajaxEngine.registerAjaxObject('sessionHistory',sessionHistoryUpdater);
  ajaxEngine.registerRequest('ajaxHistorySearch','historySearch');
  ajaxEngine.registerAjaxElement('historySearchResults');
  allHistoryUpdater = new HistoryUpdater('all');
  ajaxEngine.registerRequest('ajaxGetHistory','getHistory');
  ajaxEngine.registerAjaxObject('allHistory',allHistoryUpdater);
}
function ajaxCheckUser() {
  var user = GetCookie("DBSDD_username");
//  var pass = GetCookie("DBSDD_password");
  if(user!='guest') {
     ajaxEngine.sendRequest('ajaxCheckUser','userId='+user);
  }
}
function getUserName(iUser) {
  var userName;
  if(iUser) {
     userName=iUser;
  } else {
    // get user name and password from cookie
    userName=GetCookie("DBSDD_username");
  }
  return userName;
}
function getPassword(iPass) {
  var password;
  if(iPass) {
     password=iPass;
  } else {
     password=GetCookie("DBSDD_password");
  }
  return password
}
function ajaxHistory(dbsInst,action,iUser) {
  ajaxEngine.sendRequest('ajaxHistory','dbsInst='+dbsInst,'userId='+getUserName(iUser),'actionString='+action);
}
function ajaxGetHistory(iUser,iLimit) {
  var limit;
  if(iLimit) {
     limit=iLimit;
  } else {
     limit=20;
  }
  ajaxEngine.sendRequest('ajaxGetHistory','userId='+getUserName(iUser),'limit='+limit);
}
function GetSessionHistory() {
  var id=document.getElementById('sessionHistory');
  if (id) {
      ajaxFloatBox('Session history',id.innerHTML,'float_help_box');
  }
}
function getSelectedOption(iArr) {
  for(i=0;i<iArr.length;i++) {
     if(iArr[i].selected) {
        return iArr[i].value;
     }
  }
  return null;
}
function ajaxHistorySearch(iUser) {
  var iMonth = getSelectedOption(document.getElementById('in_hSearch_month'));
  var iYear  = getSelectedOption(document.getElementById('in_hSearch_year'));
  var oMonth = getSelectedOption(document.getElementById('out_hSearch_month'));
  var oYear  = getSelectedOption(document.getElementById('out_hSearch_year'));
  ajaxEngine.sendRequest('ajaxHistorySearch','iYear='+iYear,'iMonth='+iMonth,'oYear='+oYear,'oMonth='+oMonth,'userId='+getUserName(iUser));
}
function getDataFromSelectors(_dbs,_site,_group,_app,_primD,_tier) {
  var sel;
  var dbs;
  if(_dbs) {
      dbs=_dbs;
  } else {
      sel=document.getElementById('dbsSelector');
      if(!sel) return;
      dbs=sel.value;
  }
  var site;
  if(_site) {
      site=_site;
  } else {
      sel=document.getElementById('siteSelector');
      if(!sel) return;
      site=sel.value;
  }
  var group;
  if(_group) {
      group=_group;
  } else {
      sel=document.getElementById('groupSelector');
      if(!sel) {
          group='';
      } else {
          group=sel.value;
      }
  }
  var app;
  if(_app) {
      app=_app;
  } else {
      sel=document.getElementById('appSelector');
      if(!sel) return;
      app=sel.value;
  }
  var primD;
  if(_primD) {
      primD=_primD;
  } else {
      sel=document.getElementById('primSelector');
      if(!sel) return;
      primD=sel.value;
  }
  var tier;
  if(_tier) {
      tier=_tier;
  } else {
      sel=document.getElementById('tierSelector');
      if(!sel) return;
      tier=sel.value;
  }
  var arr = new Array(dbs,site,app,primD,tier,group);
  return arr;
}
// AJAX registration 
function ajaxGetRuns(_dbs,_site,_group,_app,_primD,_tier,proc) {
  ShowWheel("__runs");
  var arr  = getDataFromSelectors(_dbs,_site,_group,_app,_primD,_tier)
  if(!arr) return;
  var dbs  = arr[0];
  var site = arr[1];
  var app  = arr[2];
  var primD= arr[3];
  var tier = arr[4];
  var group= arr[5];
  if(!proc) {proc="*";}
  ajaxEngine.sendRequest('ajaxGetRuns',"dbsInst="+dbs,"site="+site,"group="+group,"app="+app,"primD="+primD,"tier="+tier,"proc="+proc);
  var action='<a href="javascript:ResetAllResults();ajaxGetRuns(\''+dbs+'\',\''+site+'\',\''+group+'\',\''+app+'\',\''+primD+'\',\''+tier+'\',\''+proc+'\')">Navigator ('+dbs+','+site+','+group+','+app+','+primD+','+tier+','+proc+')</a>';
  ajaxHistory(dbs,action);
}
// AJAX registration 
function ajaxGetDbsData(_dbs,_site,_group,_app,_primD,_tier,proc) {
  ShowWheel("__results_dbs");
  var arr  = getDataFromSelectors(_dbs,_site,_group,_app,_primD,_tier)
  if(!arr) return;
  var dbs  = arr[0];
  var site = arr[1];
  var app  = arr[2];
  var primD= arr[3];
  var tier = arr[4];
  var group= arr[5];
  if(!proc) {proc="*";}
  ajaxEngine.sendRequest('ajaxGetDbsData',"dbsInst="+dbs,"site="+site,"group="+group,"app="+app,"primD="+primD,"tier="+tier,"proc="+proc);
//  var action='<a href="javascript:ResetAllResults();ajaxGetDbsData(\''+dbs+'\',\''+site+'\',\''+group+'\',\''+app+'\',\''+primD+'\',\''+tier+'\',\''+proc+'\')">Navigator ('+dbs+','+site+','+group+','+app+','+primD+','+tier+','+proc+')</a>';
  var ajaxCall='ResetAllResults();ajaxGetDbsData(\''+dbs+'\',\''+site+'\',\''+group+'\',\''+app+'\',\''+primD+'\',\''+tier+'\',\''+proc+'\')';
  var action='<a href="javascript:'+ajaxCall+'">Navigator ('+dbs+','+site+','+group+','+app+','+primD+','+tier+','+proc+')</a>';
  ajaxHistory(dbs,action);
// This is how we add back button support.
//  ajax_dhtmlHistory('ajaxGetDbsData',ajaxCall);
}
// AJAX registration
function SendAjaxCalls(dbs,site,group,app,prim,tier,proc) {
  ajaxGetData(dbs,site,group,app,prim,tier,proc);
//  ajaxGetDbsData(dbs,site,group,app,prim,tier,proc);
//  ajaxGetRuns(dbs,site,group,app,prim,tier,proc);
//  ajaxGenAppConfigs(dbs,site,group,app,prim,tier,proc);
}
function ajaxGetUserData() {
  var dbs  =$('kw_dbsInstSelector').value
  var group=$('kw_group').value;
  var type =$('kw_tier').value;
  var prim =$('kw_prim').value;
  var rels =$('kw_release').value;
  var site =$('kw_site').value;
  var app  ='/'+rels+'/*/*';
  var proc='*';
  SendAjaxCalls(dbs,site,group,app,prim,type,proc);
}
function ajaxGetData(_dbs,_site,_group,_app,_primD,_tier,proc) {
  ShowWheel('__results');
  var arr  = getDataFromSelectors(_dbs,_site,_group,_app,_primD,_tier);
  if(!arr) return;
  var dbs  = arr[0];
  var site = arr[1];
  var app  = arr[2];
  var primD= arr[3];
  var tier = arr[4];
  var group= arr[5];
  if(!proc) {proc="*";}
  // Set Cookies about current snapshot of data
//  SetCookie('dbsInst',dbs);
//  SetCookie('site',site);
//  SetCookie('app',app);
//  SetCookie('primD',primD);
//  SetCookie('tier',tier);
//  SetCookie('proc',proc);

  ajaxEngine.sendRequest('ajaxGetData',"dbsInst="+dbs,"site="+site,"group="+group,"app="+app,"primD="+primD,"tier="+tier,"proc="+proc,'hist='+GetTagContent('navBar'));
  var action='<a href="javascript:ResetAllResults();ajaxGetData(\''+dbs+'\',\''+site+'\',\''+group+'\',\''+app+'\',\''+primD+'\',\''+tier+'\',\''+proc+'\')">Navigator ('+dbs+','+site+','+group+','+app+','+primD+','+tier+')</a>';
  ajaxHistory(dbs,action);
  // invoke next chunk of data
  ajaxNextGetData(dbs,site,group,app,primD,tier,proc,1);
}
function ajaxNextGetData(dbs,site,group,app,primD,tier,proc,idx) {
  var id=document.getElementById('results_response'+idx);
  if(!id) {
//  alert('ajaxGetData idx='+idx);
  ajaxEngine.sendRequest('ajaxGetData','dbsInst='+dbs,'site='+site,'group='+group,'app='+app,'primD='+primD,'tier='+tier,'proc='+proc,'_idx='+idx);
  }
/*
  var id=document.getElementById('results_dbs_response'+idx);
  if(!id) {
//  alert('ajaxGetiDbsData idx='+idx);
  ajaxEngine.sendRequest('ajaxGetDbsData','dbsInst='+dbs,'site='+site,'group='+group,'app='+app,'primD='+primD,'tier='+tier,'proc='+proc,'_idx='+idx);
  }

  var id=document.getElementById('runs_response'+idx);
  if(!id) {
//  alert('ajaxGetRuns idx='+idx);
  ajaxEngine.sendRequest('ajaxGetRuns',"dbsInst="+dbs,"site="+site,"group="+group,"app="+app,"primD="+primD,"tier="+tier,"proc="+proc,'_idx='+idx);
  }
*/
}
/*
function ajaxGetDataFromSelection(iParamString) {
  var uSelection;
  if(iParamString) { // we can pass a string of parameters, e.g. A,B,C
      uSelection=iParamString.split(",");
  } else {
      uSelection=document.getElementsByName('userSelection');
  }
  var len=0;
  for(i=0;i<uSelection.length;i++) {
      if(uSelection[i].checked || iParamString) {
         len=len+1;
      }
  }
  var selList = new Array(len);
  var action = '';
  for(i=0;i<uSelection.length;++i) {
      if(uSelection[i].checked || iParamString) {
         if(iParamString) {
            selList[i]=uSelection[i];
         } else {
            selList[i]=uSelection[i].value;
         }
         ajaxEngine.sendRequest('ajaxGetDataFromSelection','userSelection='+selList[i]);
         var actionHistory='<a href="javascript:showWaitingMessage();ajaxGetDataFromSelection(\''+selList[i]+'\')">data selection ('+selList[i]+')</a>';
         ajaxHistory(actionHistory);
      }
  }
}
*/

// AJAX registration for search
function ajaxKeywordSearch(_dbs) {
  var dbsInst;
  if(_dbs) {
      dbsInst=_dbs;
  } else {
      dbsInst=document.getElementById('keywordSearch_dbsSelector').value;
  }
  var keywords='';
  var algo =GetValue('kw_algoSelector');
  var prim =GetValue('kw_primSelector');
  var proc =GetValue('kw_procSelector');
  var tier =GetValue('kw_tierSelector');
  var runs =GetValue('kw_runsSelector');
  if(algo) {keywords=keywords+'algo:'+algo+'___';}
  if(prim) {keywords=keywords+'prim:'+prim+'___';}
  if(proc) {keywords=keywords+'proc:'+proc+'___';}
  if(tier) {keywords=keywords+'tier:'+tier+'___';}
  if(runs) {keywords=keywords+'runs:'+runs+'___';}
  ajaxEngine.sendRequest('ajaxKeywordSearch',"dbsInst="+dbsInst,"keywords="+keywords);
  var action='<a href="javascript:ResetAllResults();ajaxKeywordSearch(\''+dbsInst+'\',\''+keywords+'\')">Keyword search ('+dbsInst+','+keywords+')</a>';
  ajaxHistory(dbsInst,action);
}

function ajaxSearch(_dbs,iWords) {
  var dbsInst;
  if(_dbs) {
      dbsInst=_dbs;
  } else {
      dbsInst=document.getElementById('keywordSearch_dbsSelector').value;
  }
  var keywords;
  if(iWords) {
      keywords=iWords;
  } else {
      keywords=document.getElementById('keywordSelector').value;
  }
  ajaxEngine.sendRequest('ajaxSearch',"dbsInst="+dbsInst,"keywords="+keywords);
  var action='<a href="javascript:ResetAllResults();ajaxSearch(\''+dbsInst+'\',\''+keywords+'\')">Keyword search ('+dbsInst+','+keywords+')</a>';
  ajaxHistory(dbsInst,action);
}

// AJAX registration for site search
/*
function ajaxGetSites(_dbs) {
  var dbsInst;
  if(_dbs) {
      dbsInst=_dbs;
  } else {
      dbsInst=document.getElementById('form2_dbsSelector').value;
  }
  ajaxEngine.sendRequest('ajaxGetSites','dbsInst='+dbsInst);
}
*/
function ajaxSiteSearch(_dbs,_site) {
  var dbsInst;
  if(_dbs) {
      dbsInst=_dbs;
  } else {
      dbsInst=document.getElementById('form2_dbsSelector').value;
  }
  var site;
  if(_site) {
      site=_site;
  } else {
      site=document.getElementById('form2_siteSelector').value;
  }
  ajaxEngine.sendRequest('ajaxSiteSearch',"dbsInst="+dbsInst,"site="+site);
  var action='<a href="javascript:ResetAllResults();ajaxSiteSearch(\''+dbsInst+'\',\''+site+'\')">site search ('+dbsInst+','+site+')</a>';
  ajaxHistory(dbsInst,action);
}
function registerAjaxObjectCalls() {
    getDataUpdater = new GetDataUpdater('results','update');
    ajaxEngine.registerRequest('ajaxGetData','getData');
    ajaxEngine.registerRequest('ajaxSearch','search');
//    ajaxEngine.registerRequest('ajaxGetDataFromSelection','getDataFromSelection');
    ajaxEngine.registerRequest('ajaxGetDummyData','dummy');
    ajaxEngine.registerAjaxObject('results',getDataUpdater);

    ajaxEngine.registerRequest('ajaxKeywordSearch','search');
    kwUpdater = new GetDataUpdater('results_kw','replace','noResultsMenu');
    ajaxEngine.registerAjaxObject('results_kw',kwUpdater);

//    ajaxEngine.registerRequest('ajaxGetSites','getSites');
//    ajaxEngine.registerAjaxElement('sitesHolder');

    ajaxEngine.registerRequest('ajaxSiteSearch','getFileBlocks');
    siteUpdater = new GetDataUpdater('results','replace','noResultsMenu');
    ajaxEngine.registerAjaxObject('results_site',siteUpdater);

    ajaxEngine.registerRequest('ajaxGetDbsData','getDbsData');
    getDbsDataUpdater = new GetDataUpdater('results_dbs','update');
    ajaxEngine.registerAjaxObject('results_dbs',getDbsDataUpdater);

    ajaxEngine.registerRequest('ajaxGetRuns','getRuns');
    getRunsUpdater = new GetDataUpdater('runs','update');
    ajaxEngine.registerAjaxObject('runs',getRunsUpdater);

    ajaxEngine.registerRequest('ajaxGetRss','getRss');
    ajaxEngine.registerAjaxElement('rss_list');

    ajaxEngine.registerRequest('ajaxMakeLine','makeLine');
    lineUpdater = new GetDataUpdater('makeMenu_1','replace','noResultsMenu');
    ajaxEngine.registerAjaxObject('makeMenu_1',lineUpdater);

//    ajaxEngine.registerRequest('ajaxGetTableColumns','getTableColumns');
    ajaxEngine.registerRequest('ajaxGetSectionTables','getSectionTables');
//    ajaxEngine.registerRequest('ajaxGetTableColumnsFromSection','getTableColumnsFromSection');

//    ajaxEngine.registerRequest('ajaxGetDbsSchema','getDbsSchema');
    ajaxEngine.registerRequest('ajaxExecuteQuery','executeSQLQuery');
    ajaxEngine.registerRequest('ajaxFinderSearch','finderSearch');
    ajaxEngine.registerRequest('ajaxFindDSFromFinder','findDSFromFinder');
    ajaxEngine.registerRequest('ajaxFinderStoreQuery','finderStoreQueryInXML');
    ajaxEngine.registerRequest('ajaxFinderResultStoreQuery','finderStoreQueryInXML');
    ajaxEngine.registerRequest('ajaxFinderSearchQuery','finderSearchQuery');
    finderUpdater = new GetDataUpdater('results_finder','replace','noResultsMenu');
    ajaxEngine.registerAjaxObject('results_finder',finderUpdater);
    finderQUpdater = new GetDataUpdater('myQueries','replace','noResultsMenu');
    ajaxEngine.registerAjaxObject('myQueries',finderQUpdater);

    ajaxEngine.registerRequest('ajaxGetRunDBInfo','getRunDBInfo');
    ajaxEngine.registerRequest('ajaxGetLFNs','getLFNs');
    ajaxEngine.registerRequest('ajaxGetLFNs_Runs','getLFNs_Runs');
    ajaxEngine.registerAjaxElement('blockLFNs');

    ajaxEngine.registerRequest('ajaxGetIntegratedLumi','getIntegratedLumi');
}
function registerAjaxUserMenuCalls() {
    ajaxEngine.registerRequest('ajaxGetPrimDSTypes','getPrimaryDSTypes');
    ptUpdater = new GetDataUpdater('kw_primType_holder','replace','noResultsMenu');
    ajaxEngine.registerAjaxObject('kw_primType_holder',ptUpdater);
    ajaxEngine.registerRequest('ajaxGetReleases','getSoftwareReleases');
    relUpdater = new GetDataUpdater('kw_release_holder','replace','noResultsMenu');
    ajaxEngine.registerAjaxObject('kw_release_holder',relUpdater);
    ajaxEngine.registerRequest('ajaxGetTriggerLines','getTriggerLines');
    primUpdater = new GetDataUpdater('kw_prim_holder','replace','noResultsMenu');
    ajaxEngine.registerAjaxObject('kw_prim_holder',primUpdater);
    ajaxEngine.registerRequest('ajaxGetTiers','getTiers');
    tierUpdater = new GetDataUpdater('kw_tier_holder','replace','noResultsMenu');
    ajaxEngine.registerAjaxObject('kw_tier_holder',tierUpdater);
    ajaxEngine.registerRequest('ajaxGetSites','getSites');
    siteUpdater = new GetDataUpdater('kw_site_holder','replace','noResultsMenu');
    ajaxEngine.registerAjaxObject('kw_site_holder',siteUpdater);
    ajaxEngine.registerAjaxElement('form2_siteHolder');
    ajaxEngine.registerRequest('ajaxGetGroups','getGroups');
    groupUpdater = new GetDataUpdater('kw_group_holder','replace','noResultsMenu');
    ajaxEngine.registerAjaxObject('kw_group_holder',groupUpdater);
    ajaxEngine.registerRequest('ajaxGetRunRange','getRunRange');
    ajaxEngine.registerAjaxElement('kw_runRange_holder');
}

function ajaxMultiSearch(dbsInst,userInput) {
   var id = document.getElementById('userInput');
   ajaxEngine.sendRequest('ajaxMultiSearch','userInput='+userInput,'dbsInst='+dbsInst,'ajax=1');
}
function ajaxGetDQInfo(dbsInst,dataset,run,dqid,admin) {
  ajaxEngine.sendRequest('ajaxGetDQInfo','dbsInst='+dbsInst,'dataset='+dataset,'run='+run,'dqid='+dqid,'admin='+admin);
}
function ajaxGetIntegratedLumi(dbsInst,dataset) {
  ajaxEngine.sendRequest('ajaxGetIntegratedLumi','dbsInst='+dbsInst,'dataset='+dataset);
}
function ajaxGetLFNs_Runs(dbsInst,blockName,ajaxId,blockId,js) {
  ajaxEngine.sendRequest('ajaxGetLFNs_Runs','dbsInst='+dbsInst,'blockName='+blockName,'ajaxId='+ajaxId,'blockId='+blockId,'onchange='+js);
}
function ajaxGetLFNs(dbsInst,blockName,ajaxId,blockId,js) {
  if(ajaxId && blockId && js) {
    ajaxEngine.sendRequest('ajaxGetLFNs','dbsInst='+dbsInst,'blockName='+blockName,'ajaxId='+ajaxId,'blockId='+blockId,'onchange='+js);
  } else {
    ajaxEngine.sendRequest('ajaxGetLFNs','dbsInst='+dbsInst,'blockName='+blockName);
  }
}
function ajaxGetRunDBInfo(run) {
  ajaxEngine.sendRequest('ajaxGetRunDBInfo','run='+run);
}
function ajaxMakeLine(id) {
  ajaxEngine.sendRequest('ajaxMakeLine','id='+id);
}
//function ajaxGetTableColumns(dbs,tableName,id) {
//  ajaxEngine.sendRequest('ajaxGetTableColumns','dbsInst='+dbs,'tableName='+tableName,'id='+id);
//}
//function ajaxGetTableColumnsFromSection(dbs,section,id) {
//  ajaxEngine.sendRequest('ajaxGetTableColumnsFromSection','dbsInst='+dbs,'section='+section,'id='+id);
//}
//function ajaxGetSectionTables(dbsInst,section,id) {
//    ajaxEngine.sendRequest('ajaxGetSectionTables','dbsInst='+dbsInst,'section='+section,'id='+id);
//}
//function ajaxFillLine(lineId) {
//    dbsInst='localhost';
//    var id=document.getElementById("finder_dbsSelector");
//    if (id) {
//        dbsInst=id.value;
//    }
//    var table=document.getElementById('sectionTables_'+lineId);
//    ajaxEngine.registerRequest('ajaxGetTableColumns','getTableColumns');
//    ajaxGetTableColumns(dbsInst,table.value,lineId);
//}
//function ChangeTables(lineId) {
//    dbsInst='localhost';
//    var id=document.getElementById("finder_dbsSelector");
//    if (id) {
//        dbsInst=id.value;
//    }
//    var id=document.getElementById("selSection_"+lineId);
//    if (id) {
//        var section = id.value;
//        ajaxGetSectionTables(dbsInst,section,lineId);
//        ajaxGetTableColumnsFromSection(dbsInst,section,lineId);
//    }
//}
//function ChangeCols(lineId,tag) {
//    dbsInst='localhost';
//    var id=document.getElementById("finder_dbsSelector");
//    if (id) {
//        dbsInst=id.value;
//    }
//    if(!tag) {
//        tag="sectionTables"
//    }
//    var id=document.getElementById(tag+"_"+lineId);
//    if (id) {
//        var tableName = id.value;
//        ajaxGetTableColumns(dbsInst,tableName,lineId);
//    }
//}
//function ajaxGetDbsSchema(dbsInst,table) {
//    ShowTag('results_finder');
//    if(!dbsInst) {
//        dbsInst='';
//        var dbsList=$('dbsExpert_dbsSelector');
//        for(i=0;i<dbsList.length;i++) {
//           if(dbsList[i].selected) {
//              dbsInst=dbsList[i].value;
//              break;
//           }
//        }
//    }
//    if(!table) {
//        table=$("kw_dbsTables").value
//    }
//    ajaxEngine.sendRequest('ajaxGetDbsSchema','dbsInst='+dbsInst,'table='+table);
//}
function registerAjaxLucene() {
    ajaxEngine.registerRequest('ajaxGetLucene','getLucene');
    updater_stats = new GetDataUpdater('webSearchStats','replace','noResultsMenu');
    ajaxEngine.registerAjaxObject('webSearchStats',updater_stats);

    ajaxEngine.registerRequest('ajaxGetLuceneParams','getLucene');
    updater_param = new GetDataUpdater('cfgparamlist','replace','noResultsMenu');
    ajaxEngine.registerAjaxObject('cfgparamlist',updater_param);
    updater_param_res = new GetDataUpdater('results','replace','noResulsMenu');
    ajaxEngine.registerAjaxObject('cfgindexlookup',updater_param_res);
// Lucene way to get parameters
//    updater_param = new GetDataUpdater('parameterNameList','replace','noResultsMenu');
//    ajaxEngine.registerAjaxObject('parameterNameList',updater_param);

    updater_grid = new GetDataUpdater('webSearchResultsGrid_updater','replace','noResultsMenu');
    ajaxEngine.registerAjaxObject('webSearchResultsGrid_updater',updater_grid);

    updater = new GetDataUpdater('configureWebSearchRows','replace','noResultsMenu');
    ajaxEngine.registerAjaxObject('configureWebSearchRows',updater);

    errUpdater = new GetDataUpdater('errorResponse','replace','noResultsMenu');
    ajaxEngine.registerAjaxObject('errorResponse',errUpdater);
}
function ajaxGetLucene() {
    // here we use prototype syntax $('param') means document.getElementById('param')
//    ajaxEngine.sendRequest('ajaxGetLucene','term='+$('parameterList').value+$('parameterListOperators').value+$('searchInput').value,'method=lookup','outputs=both');

/*
    var sel=document.getElementById('selectcfgparam');
    var p_name,p_type;
    for(i=0;i<sel.options.length;i++) {
          if(sel.options[i].selected) {
             p_name = sel.options[i].id;
             p_type = sel.options[i].value;
          }
     }

    ajaxEngine.sendRequest('ajaxGetLucene','method=query.jsp','num=1','ptype0='+p_type,'pname0='+p_name,'op0='+$('parameterListOperators').value,'val0='+$('searchInput').value);
*/
    var g_pname=document.getElementsByName('p_name');
    var g_ptype=document.getElementsByName('p_type');
    var g_op   =document.getElementsByName('p_op');
    var g_val  =document.getElementsByName('p_val');
    var num=g_pname.length;
    var url='num='+num;
    for(i=0;i<num;i++) {
        url=url+'&pname'+i+'='+g_pname[i].innerHTML;
        url=url+'&ptype'+i+'='+g_ptype[i].innerHTML;
        url=url+'&val'+i+'='  +g_val[i].innerHTML;
        url=url+'&op'+i+'='   +g_op[i].innerHTML;
    }
    ajaxEngine.sendRequest('ajaxGetLucene','method=query.jsp','params='+url);
}
function ajaxGetLuceneParams() {
//    ajaxEngine.sendRequest('ajaxGetLucene','method=parameters');
    ajaxEngine.sendRequest('ajaxGetLucene','method=paramlist.jsp');
}
function ajaxGetRss() {
    ajaxEngine.sendRequest('ajaxGetRss');
}
function ajaxExecuteQuery(iDbs,iQuery) {
    ShowTag('results_finder');
    var query;
    if(iQuery) {
        query=iQuery;
    } else {
        query=$('queryText').value
    }
    var dbsInst;
    if(iDbs) {
        dbsInst=iDbs;
    } else {
        var dbsList=$('dbsExpert_dbsSelector');
        for(i=0;i<dbsList.length;i++) {
           if(dbsList[i].selected) {
              dbsInst=dbsList[i].value;
              break;
           }
        }
    }
    ajaxEngine.sendRequest('ajaxExecuteQuery',"dbsInst="+dbsInst,"query="+query);

    var ajaxCall='ResetAllResults();ajaxExecuteQuery(\''+dbsInst+'\',\''+query+'\')';
    var action='<a href="javascript:'+ajaxCall+'">ExecuteQuery ('+query+')</a>';
    ajaxHistory(dbsInst,action);
// This is how we add back button support.
//  ajax_dhtmlHistory('ajaxGetDbsData',ajaxCall);
}
function ajaxFinderSearch(userMode,dbsInst,parameters,limit,offset) {
    if (dbsInst && parameters) {
        ajaxEngine.sendRequest('ajaxFinderSearch','userMode='+userMode,'dbsInst='+dbsInst,'limit='+limit,'offset='+offset,parameters);
    } else {
        ShowTag('results_finder');
        var dbsInst=$('finder_dbsSelector').value;
        var dbsList=$('finder_dbsSelector');
        for(i=0;i<dbsList.length;i++) {
           if(dbsList[i].selected) {
              dbsInst=dbsList[i].value;
              break;
           }
        }
        var sel=document.getElementsByName("sectionTables");
        var maxId=1;
        for(var i=0;i<sel.length;i++) {
            var sel_id = sel[i].id;
            var id=sel_id.split('_')[1];
            if(id>maxId) { maxId=id; }
        }
        var parameters='';
        for(var i=1;i<=maxId;i++) {
            table=$('sectionTables_'+i).value;
            column=$('tableColumns_'+i).value;
            operator=$('colSel_'+i).value;
            where=$('where_'+i).value;
    //        out=$('outCol_'+i).value;
    //        str=table+'__'+column+'__'+operator+'__'+where+'__'+out;
            limit=$('kw_limit').value
            str=table+'__'+column+'__'+operator+'__'+where+'__'+limit;
            if(!parameters) {
                parameters='params='+str;
            } else {
                parameters=parameters+'_newparam_'+str;
            }
        }
        ajaxEngine.sendRequest('ajaxFinderSearch','userMode='+userMode,'dbsInst='+dbsInst,parameters);
    }
}
function ajaxFindDSFromFinder(dbsInst,params,userMode) {
    ajaxEngine.sendRequest('ajaxFindDSFromFinder','dbsInst='+dbsInst,'params='+params,'userMode='+userMode);
}
function ajaxFinderStoreQuery(iUser) {
    var dbsInst=$('finder_dbsSelector').value;
    var sel=document.getElementsByName("tableColumnList");
    var parameters='';
    for(var i=0;i<sel.length;i++) {
        if (sel[i].checked) {
            if (!parameters) {
                 parameters='params='+sel[i].id;
            } else {
                 parameters=parameters+'_table_'+sel[i].id;
            }
        }
    }
    var where='where='+$('kw_where').value;
    var aName=$('kw_alias').value;

/*
    var sel=document.getElementsByName("sectionTables");
    var maxId=1;
    for(var i=0;i<sel.length;i++) {
        var sel_id = sel[i].id;
        var id=sel_id.split('_')[1];
        if(id>maxId) { maxId=id; }
    }
    var aName=$('kw_alias').value;
    var parameters='';
    for(var i=1;i<=maxId;i++) {
        table=$('sectionTables_'+i).value;
        column=$('tableColumns_'+i).value;
        operator=$('colSel_'+i).value;
        where=$('where_'+i).value;
        if(!parameters) {
        parameters='params='+table+'__'+column+'__'+operator+'__'+where;
        } else {
        parameters=parameters+'_newparam_'+table+'__'+column+'__'+operator+'__'+where;
        }
    }
    ajaxEngine.sendRequest('ajaxFinderStoreQuery','dbsInst='+dbsInst,'userId='+getUserName(iUser),'alias='+aName,parameters);
*/
    ajaxEngine.sendRequest('ajaxFinderStoreQuery','dbsInst='+dbsInst,'userId='+getUserName(iUser),'alias='+aName,parameters,where);
//    $('results_finder').innerHTML='Your query "'+aName+'" has been saved.';
    $('query_confirmation').innerHTML='<span class="box_gray">Your query "'+aName+'" has been saved.</span>';
    $('kw_alias').value='';
}
function ajaxFinderResultStoreQuery(iUser) {
    var query=$('queryXML').value;
    var dbsInst=$('dbsInst').value;
    var aName=$('kw_alias').value;
    ajaxEngine.sendRequest('ajaxFinderStoreQuery','dbsInst='+dbsInst,'userId='+getUserName(iUser),'alias='+aName,'queryXML='+query);
    $('query_confirmation').innerHTML='<span class="box_gray">Your query "'+aName+'" has been saved.</span>';
    $('kw_alias').value='';
}
function ajaxFinderSearchQuery(iUser) {
    ajaxEngine.sendRequest('ajaxFinderSearchQuery','userId='+getUserName(iUser),'alias='+$('kw_alias_lookup').value);
}
function ajaxGetKWFields() {
  showLoadingMessage('kw_group_holder');
  ajaxGetGroups();
  showLoadingMessage('kw_tier_holder');
  ajaxGetTiers();
  showLoadingMessage('kw_release_holder');
  ajaxGetReleases();
  showLoadingMessage('kw_prim_holder');
  ajaxGetTriggerLines();
  showLoadingMessage('kw_primType_holder');
  ajaxGetPrimDSTypes();
  showLoadingMessage('kw_site_holder');
  ajaxGetSites('','kw_dbsInstSelector','kw_site_holder','site');
//  var rel = $('kw_release').value;
//  var tier= $('kw_tier').value;
//  var group=$('kw_group').value;
//alert('rel='+rel+' tier='+tier+' group='+group);
//  if(rel!='Any' || tier!='Any' || group!='Any') {
//     ajaxUpdatePrimaryDatasets();
//  }
}

function getDBS_kw(_dbs) {
  var dbs;
  if(_dbs) {
      dbs=_dbs;
  } else {
      dbs=$('kw_dbsInstSelector').value;
  }
  return dbs;
}
function ajaxGetRunRange(_dbs) {
  var dbs=getDBS_kw(_dbs);
  var prim='any';
  if($('kw_prim')) {
      prim=$('kw_prim').value;
  }
  var primType='any';
  if($('kw_primType')) {
      primType=$('kw_primType').value;
  }
  ajaxEngine.sendRequest('ajaxGetRunRange','dbsInst='+dbs,'primD='+prim,'primType='+primType);
}
function ajaxGetReleases(_dbs) {
  var dbs=getDBS_kw(_dbs);
  ajaxEngine.sendRequest('ajaxGetReleases','dbsInst='+dbs);
}
function ajaxGetTriggerLines(_dbs,_cFunc) {
  var dbs=getDBS_kw(_dbs);
  if(_cFunc) {
      ajaxEngine.sendRequest('ajaxGetTriggerLines','dbsInst='+dbs,'changeFunction='+_cFunc);
      return;
  }
  var rel="*";
  var tier="*";
  var group="*";
  var dsType="*";
  var _rel=GetCookie('DBSDD_release');
  if(_rel) {
     rel=_rel;
  }
  var _group=GetCookie('DBSDD_group');
  if(_group) {
     group=_group;
  }
  var _tier=GetCookie('DBSDD_tier');
  if(_tier) {
     tier=_tier;
  }
  var _dsType=GetCookie('DBSDD_primType');
  if(_dsType) {
     dsType=_dsType;
  }
  ajaxEngine.sendRequest('ajaxGetTriggerLines','dbsInst='+dbs,'group='+group,'tier='+tier,'rel='+rel,'dsType='+dsType);
}
function ajaxGetPrimDSTypes(_dbs,_cFunc) {
  var dbs=getDBS_kw(_dbs);
  if(_cFunc) {
      ajaxEngine.sendRequest('ajaxGetPrimDSTypes','dbsInst='+dbs,'changeFunction='+_cFunc);
      return;
  }
  ajaxEngine.sendRequest('ajaxGetPrimDSTypes','dbsInst='+dbs);
}
/*
function ajaxGetTriggerLines(_dbs) {
  var dbs=getDBS_kw(_dbs);
  ajaxEngine.sendRequest('ajaxGetTriggerLines','dbsInst='+dbs);
}
function ajaxGetPrimDSTypes(_dbs) {
  var dbs=getDBS_kw(_dbs);
  ajaxEngine.sendRequest('ajaxGetPrimDSTypes','dbsInst='+dbs);
}
*/

function ajaxUpdatePrimaryDatasets(_dbs,_cFunc) {
  var dbs=getDBS_kw(_dbs);
  var rel = $('kw_release').value;
  var tier= $('kw_tier').value;
  var group=$('kw_group').value;
  var dsType=$('kw_primType').value;
//  showLoadingMessage('kw_prim_holder');
  var id = $('kw_prim');
  if (id) {
  $('kw_prim').disabled="disabled";
  }
  if(_cFunc) {
     ajaxEngine.sendRequest('ajaxGetTriggerLines','dbsInst='+dbs,'group='+group,'tier='+tier,'rel='+rel,'dsType='+dsType,'changeFunction='+_cFunc);
     return;
  }
  ajaxEngine.sendRequest('ajaxGetTriggerLines','dbsInst='+dbs,'group='+group,'tier='+tier,'rel='+rel,'dsType='+dsType);
}
function ajaxGetTiers(_dbs) {
  var dbs=getDBS_kw(_dbs);
  ajaxEngine.sendRequest('ajaxGetTiers','dbsInst='+dbs);
}
function ajaxGetSites(_dbs,dbsSel,siteSel,siteTag) {
  var dbs;
  if(dbsSel) {
     dbs=$(dbsSel).value;
  } else {
     getDBS_kw(_dbs);
  }
  if(siteSel) {
      var sTag='';
      if(siteTag) {sTag=siteTag;}
      ajaxEngine.sendRequest('ajaxGetSites','dbsInst='+dbs,'sel='+siteSel,'tag='+sTag);
  } else {
      ajaxEngine.sendRequest('ajaxGetSites','dbsInst='+dbs);
  }
}
function ajaxGetGroups(_dbs) {
  var dbs=getDBS_kw(_dbs);
  ajaxEngine.sendRequest('ajaxGetGroups','dbsInst='+dbs);
}
//function ajaxGetBranches(_dbs) {
//  dbs=getDBS_kw(_dbs);
//  ajaxEngine.sendRequest('ajaxGetBranches','dbsInst='+dbs);
//}


function registerAjaxGetBlocksFromSiteCalls() {
  ajaxEngine.registerRequest('ajaxGetBlocksFromSite','getBlocksFromSiteHelper');
  ajaxEngine.registerAjaxElement('siteBlocksHandler');
}
function ajaxGetBlocksFromSite() {
  ajaxEngine.sendRequest('ajaxGetBlocksFromSite');
}

function registerAjaxSummaryCalls() {
  ajaxEngine.registerRequest('getSummary','summary');
  ajaxEngine.registerAjaxElement('summary');
}
/*
function getSummary() {
  var id=document.getElementById("summary");
  id.className="show_inline_off";
  ajaxEngine.sendRequest('getSummary');
  ajaxHistory('Summary');
  var action='<a href="javascript:showMenu(\'Summary\');getSummary()">Summary</a>';
  ajaxHistory(action);
}

function getPrimDatasets_old() {
  ajaxEngine.sendRequest('getPrimDatasets');
  var action='<a href="javascript:showMenu(\'Datasets\');getPrimDatasets()">Get all primary datasets</a>';
  ajaxHistory(action);
}
*/
function getDbsInfo(dbsInst,dbsArr) {
  var arr = new Array();
  arr[0]='dbs_prim';
  arr[1]='dbs_proc';
  arr[2]='dbs_apps';
  for(i=0;i<arr.length;i++) {
      var id=document.getElementById(arr[i]);
      id.className="hide";
  }
  showResMenu('dbs_prim',arr);
  registerAjaxPrimaryDatasetsCalls();
  registerAjaxProcessedDatasetsCalls();
  registerAjaxApplicationsCalls();
  for(i=0;i<dbsArr.length;i++) {
      var sName=dbsArr[i].replace(/\//g,"___")
      var id=document.getElementById('dbsInst_'+sName);
      if (dbsArr[i]==dbsInst) {
          id.className="td_right";
      } else {
          id.className="show_cell";
      }
  }
  var id=document.getElementById("dbsInst_table");
  id.className="show_table";
  var id=document.getElementById("dbs_info");
  id.className="show_table";
  ajaxGetPrimaryDatasets(dbsInst);
  ajaxGetProcessedDatasets(dbsInst);
  ajaxGetApplications(dbsInst);
}
function ajaxGetPrimaryDatasets(dbsInst) {
  ShowWheel("__dbs_prim");
  ajaxEngine.sendRequest('ajaxGetPrimaryDatasets',"dbsInst="+dbsInst);
  var action='<a href="javascript:ResetAllResults();showMenu(\'DBSinfo\');ajaxGetPrimaryDatasets(\''+dbsInst+'\')">Get all primary datasets (\''+dbsInst+'\')</a>';
  ajaxHistory(dbsInst,action);
}
function registerAjaxPrimaryDatasetsCalls() {
    dbsInfoUpdater = new GetDataUpdater("dbs_prim",'replace','noResultsMenu');
    ajaxEngine.registerRequest('ajaxGetPrimaryDatasets','getPrimaryDatasets');
    ajaxEngine.registerAjaxObject('dbs_prim',dbsInfoUpdater);
}
function ajaxGetProcessedDatasets(dbsInst) {
    ShowWheel("__dbs_proc");
    ajaxEngine.sendRequest('ajaxGetProcessedDatasets',"dbsInst="+dbsInst);
    var action='<a href="javascript:ResetAllResults();showMenu(\'DBSinfo\');ajaxGetProcessedDatasets(\''+dbsInst+'\')">Get all processed datasets (\''+dbsInst+'\')</a>';
    ajaxHistory(dbsInst,action);
}
function registerAjaxProcessedDatasetsCalls() {
    dbsInfoUpdater = new GetDataUpdater("dbs_proc",'replace','noResultsMenu');
    ajaxEngine.registerRequest('ajaxGetProcessedDatasets','getProcessedDatasets');
    ajaxEngine.registerAjaxObject('dbs_proc',dbsInfoUpdater);
}
function ajaxGetApplications(dbsInst) {
    ShowWheel("__dbs_apps");
    ajaxEngine.sendRequest('ajaxGetApplications',"dbsInst="+dbsInst);
    var action='<a href="javascript:ResetAllResults();showMenu(\'DBSinfo\');ajaxGetApplications(\''+dbsInst+'\')">Get all applications (\''+dbsInst+'\')</a>';
    ajaxHistory(dbsInst,action);
}
function registerAjaxApplicationsCalls() {
    dbsInfoUpdater = new GetDataUpdater("dbs_apps",'replace','noResultsMenu');
    ajaxEngine.registerRequest('ajaxGetApplications','getApplications');
    ajaxEngine.registerAjaxObject('dbs_apps',dbsInfoUpdater);
}

function getProvenance(id) {
  // in order to replace all occurence of pattern in a string we need to use regular expression
  // see http://www.i2d.com.au/members/~benmann/javascriptreplace.html
  dataset=id.replace(/___/g,"/");
  ajaxEngine.sendRequest('getProvenance',"dataset="+dataset);
}

/*
var NavigatorMenuDictUpdater=Class.create();
NavigatorMenuDictUpdater.prototype = {
   initialize: function() {
   },
   ajaxUpdate: function(ajaxResponse) {
     var responseHTML=RicoUtil.getContentAsString(ajaxResponse);
     var t=document.getElementById("navigatorDict");
     eval(responseHTML);
     var dbs = document.getElementById("dbsSelector");
     updateLayer(dbs);
     EnableSel("appSelector");
     EnableSel("primSelector");
     EnableSel("tierSelector");
     var t=document.getElementById("navSelector");
     t.innerHTML="";
   }
}
function registerAjaxGenNavigatorMenuDictCalls() {
  navigatorUpdater = new NavigatorMenuDictUpdater();
  ajaxEngine.registerRequest('ajaxGenNavigatorMenuDict','genNavigatorMenuDict');
  ajaxEngine.registerAjaxObject('navigatorDict',navigatorUpdater);
}
function ajaxGenNavigatorMenuDict(_dbs) {
  var dbsInst;
  if(_dbs) { dbsInst=_dbs; }
  var sel=document.getElementById("dbsSelector");
  if(sel) {
     for(i=0;i<sel.length;i++) {
         if(sel[i].selected) {
            dbsInst=sel[i].value;
            break;
         }
     }
  }
  // de-activate underneath menues (will be activated back once AJAX will arrive
  DisableSel("appSelector");
  DisableSel("primSelector");
  DisableSel("tierSelector");
  showLoadingMessage('navSelector');
  ajaxEngine.sendRequest('ajaxGenNavigatorMenuDict','dbsInst='+dbsInst);
}
*/
/*
var SiteMenuDictUpdater=Class.create();
SiteMenuDictUpdater.prototype = {
   initialize: function() {
   },
   ajaxUpdate: function(ajaxResponse) {
     var responseHTML=RicoUtil.getContentAsString(ajaxResponse);
     var t=document.getElementById("siteMenuDict");
     eval(responseHTML);
     var dbs = document.getElementById("form2_dbsSelector");
     updateSites(dbs);
   }
}
function registerAjaxGenSiteMenuDictCalls() {
  siteDictUpdater = new SiteMenuDictUpdater();
  ajaxEngine.registerRequest('ajaxGenSiteMenuDict','genSiteMenuDict');
  ajaxEngine.registerAjaxObject('siteMenuDict',siteDictUpdater);
}
function ajaxGenSiteMenuDict() {
  ajaxEngine.sendRequest('ajaxGenSiteMenuDict');
}
*/

// method which should be called on page load, to initialize all AJAX calls
function ajaxInit(_dbs) {
  registerAjaxSelectAppsCalls();
  registerAjaxSelectPrimCalls();
  registerAjaxSelectTierCalls();
  registerAjaxObjectCalls();
  registerAjaxPrimaryDatasetsCalls();
  registerAjaxSummaryCalls();
  registerAjaxHistoryCalls();
  registerAjaxProvenanceCalls();
//  registerAjaxProvenanceGraphCalls();
  registerAjaxAppConfigsCalls();
//  registerAjaxGenNavigatorMenuDictCalls();
  registerAjaxGetFloatBoxCalls();
  registerAjaxGetLumisCalls();
  registerAjaxProdRequestCalls();
  registerAjaxPhedexCalls();
  registerAjaxRunSummaryCalls();

//  ajaxGenNavigatorMenuDict(_dbs);
  registerAjaxLucene();
  registerAjaxUserMenuCalls();
  initialize_dhtmlHistory();
  registerAjaxGetMoreInfoCalls();
}

function registerAjaxRunSummaryCalls() {
    ajaxEngine.registerRequest('ajaxRunSummary','getRunDBInfo');
    ajaxEngine.registerRequest('ajaxGetDQInfo','getDQInfo');
}
function ajaxRunSummary(runs) {
   ajaxEngine.sendRequest('ajaxRunSummary','runs='+runs);
}
function registerAjaxPhedexCalls() {
    ajaxEngine.registerRequest('ajaxPhedexStatus','phedexStatus');
}
function ajaxPhedexStatus(site,datasetPath,id_suffix) {
   if(!id_suffix) {
       id_suffix='';
   }
   ajaxEngine.sendRequest('ajaxPhedexStatus','site='+site,'datasetPath='+datasetPath,'id_suffix='+id_suffix);
}
function registerAjaxProdRequestCalls() {
    ajaxEngine.registerRequest('ajaxGetProdRequest','getProdRequest');
//    ajaxEngine.registerAjaxElement('id_ProdRequest');
}
function ajaxGetProdRequest(prim,id) {
  ajaxEngine.sendRequest('ajaxGetProdRequest','prim='+prim,'id='+id);
}

// Class which capture ajax response and handle it. 
// We put our reponse to "parents" tag id found on internal HTML.
var ParentsGraphUpdater=Class.create();
ParentsGraphUpdater.prototype = {
   initialize: function() {
   },
   ajaxUpdate: function(ajaxResponse) {
     var responseHTML=RicoUtil.getContentAsString(ajaxResponse);
     var r=document.getElementById("_parents");
     r.className="td_menu_lavender_box";
     var t=document.getElementById("parents");
     t.innerHTML+=responseHTML;
     // parse response and search for any JavaScript code there, if found execute it.
     var jsCode = SearchForJSCode(responseHTML);
     if(jsCode) {
        eval(jsCode);
     }
   }
}
//function registerAjaxProvenanceGraphCalls() {
//  ajaxEngine.registerRequest('ajaxGenParentsGraph','getProvenanceForAllDatasets');
//  updater = new GetDataUpdater('parents','update');
//  ajaxEngine.registerAjaxObject('parents',updater);
//}
function ajaxGenParentsGraphFromSelection() {
  uSelection=document.getElementsByName('userSelection');
  for(i=0;i<uSelection.length;i++) {
      if(uSelection[i].checked) {
         var val=uSelection[i].value;
         uSel = val.split("___");
         dbs  = uSel[0];
         primD= uSel[1];
         tier = uSel[2];
         app  = "/"+uSel[3]+"/"+uSel[4]+"/"+uSel[5]; // /ver/family/exe
         site = "*";
         ajaxGenParentsGraph(dbs,site,app,primD,tier);
      }
  }
}
function ajaxGenParentsGraph(_dbs,_site,_group,_app,_primD,_tier,proc) {
  ShowWheel("__parents");
//  showLoadingMessage('results_dbs');
  var arr  = getDataFromSelectors(_dbs,_site,_group,_app,_primD,_tier)
  if(!arr) return;
  var dbs  = arr[0];
  var site = arr[1];
  var app  = arr[2];
  var primD= arr[3];
  var tier = arr[4];
  var group= arr[5];
  if(!proc) {proc="*";}
  ajaxEngine.sendRequest('ajaxGenParentsGraph',"dbsInst="+dbs,"site="+site,"group="+group,"app="+app,"primD="+primD,"tier="+tier,'proc='+proc);
  var action='<a href="javascript:ResetAllResults();ajaxGenParentsGraph(\''+dbs+'\',\''+site+'\',\''+group+'\',\''+app+'\',\''+primD+'\',\''+tier+'\')">ParentGraph ('+dbs+','+site+','+app+','+primD+','+tier+','+proc+')</a>';
  ajaxHistory(dbs,action);
}
function ajaxNextGenParentsGraph(dbs,site,group,app,primD,tier,proc,idx) {
  ajaxEngine.sendRequest('ajaxGenParentsGraph',"dbsInst="+dbs,"site="+site,"group="+group,"app="+app,"primD="+primD,"tier="+tier,"proc="+proc,"_idx="+idx);
}
// keep this for first implementation of provenance calls
function registerAjaxProvenanceCalls() {
    ajaxEngine.registerRequest('getProvenance','getDatasetProvenance');
    ajaxEngine.registerAjaxElement('parentGraph');
}
// Class which capture ajax response and handle it. 
// We put our reponse to "appConfigs" tag id found on internal HTML.
var AppConfigsUpdater=Class.create();
AppConfigsUpdater.prototype = {
   initialize: function() {
   },
   ajaxUpdate: function(ajaxResponse) {
     var responseHTML=RicoUtil.getContentAsString(ajaxResponse);
     var r=document.getElementById("_appConfigs");
     r.className="td_menu_lavender_box";
     var t=document.getElementById("appConfigs");
     t.innerHTML=responseHTML;
     // additional action can come here
   }
}
function registerAjaxAppConfigsCalls() {
  ajaxEngine.registerRequest('ajaxGenAppConfigs','getAppConfigs');
  updater = new GetDataUpdater('appConfigs','replace');
  ajaxEngine.registerAjaxObject('appConfigs',updater);
}
function ajaxGenAppConfigs(_dbs,_site,_group,_app,_prim,_tier,proc) {
  ShowWheel("__appConfigs");
  var arr  = getDataFromSelectors(_dbs,_site,_group,_app,_prim,_tier)
  if(!arr) return;
  var dbs  = arr[0];
  var site = arr[1];
  var app  = arr[2];
  var primD= arr[3];
  var tier = arr[4];
  var group= arr[5];
  if(!proc) {proc="*";}
  ajaxEngine.sendRequest('ajaxGenAppConfigs',"dbsInst="+dbs,"appPath="+app);
  var action='<a href="javascript:ResetAllResults();ajaxGenAppConfigs(\''+app+'\')">AppConfigs ('+app+')</a>';
  ajaxHistory(dbs,action);
}
function registerAjaxGetFloatBoxCalls() {
    ajaxEngine.registerRequest('ajaxFloatBox','getFloatBox');
    ajaxEngine.registerAjaxElement('floatDataDescription');
}
function ajaxFloatBox(title,desc,className) {
    var c=className;
    if(!className) {
        c='float_box';
    }
    ajaxEngine.sendRequest('ajaxFloatBox','title='+title,'description='+desc,'className='+c);
    ShowTag('floatDataDescription');
}
function registerAjaxSelectAppsCalls() {
    ajaxEngine.registerRequest('ajaxSelectApps','selectApplications');
    ajaxEngine.registerAjaxElement('selectApps');
}
function ajaxSelectApps() {
    var sel=document.getElementById("dbsSelector");
    for(i=0;i<sel.length;i++) {
        if(sel[i].selected) {
           ajaxEngine.sendRequest('ajaxSelectApps','dbsInst='+sel[i].value);
           return;
        }
    }
}
function registerAjaxSelectPrimCalls() {
    ajaxEngine.registerRequest('ajaxSelectPrim','selectPrimaryDatasets');
    ajaxEngine.registerAjaxElement('selectPrim');
}
function ajaxSelectPrim() {
    var sel=document.getElementById("dbsSelector");
    var dbsInst;
    for(i=0;i<sel.length;i++) {
        if(sel[i].selected) {
           dbsInst=sel[i].value;
           break;
        }
    }
    var sel=document.getElementById("appSelector");
    var app;
    for(i=0;i<sel.length;i++) {
        if(sel[i].selected) {
           app=sel[i].value;
           ajaxEngine.sendRequest('ajaxSelectPrim','dbsInst='+dbsInst,'app='+app);
           return;
        }
    }
}
function registerAjaxSelectTierCalls() {
    ajaxEngine.registerRequest('ajaxSelectTier','selectDataTiers');
    ajaxEngine.registerAjaxElement('selectTier');
}
function ajaxSelectTier() {
    var sel=document.getElementById("dbsSelector");
    var dbsInst;
    for(i=0;i<sel.length;i++) {
        if(sel[i].selected) {
           dbsInst=sel[i].value;
           break;
        }
    }
    var sel=document.getElementById("appSelector");
    var app;
    for(i=0;i<sel.length;i++) {
        if(sel[i].selected) {
           app=sel[i].value;
           break;
        }
    }
    var sel=document.getElementById("primSelector");
    var prim;
    for(i=0;i<sel.length;i++) {
        if(sel[i].selected) {
           prim=sel[i].value;
           ajaxEngine.sendRequest('ajaxSelectTier','dbsInst='+dbsInst,'app='+app,'prim='+prim);
           return;
        }
    }
}
/*
  Below you can find tree updater class and associative AJAX methods.
  AJAX returns pure JavaScript code which add a new node to existing tree.
  The registerTreeView needs to be called everytime when new TreeView has to be created.
  The tree will be placed to "treeDiv" tag in HTML code.
*/
var TreeNode=Class.create();
TreeNode.prototype = {
   initialize: function(id,name) {
     this.id=id;
     this.name=name;
   }
}
var TreeUpdater=Class.create();
TreeUpdater.prototype = {
   initialize: function() {
     this.tree = new YAHOO.widget.TreeView("treeDiv");
     this.root = this.tree.getRoot();
     this.nodes = new Array();
   },
   ajaxUpdate: function(ajaxResponse) {
     var responseHTML=RicoUtil.getContentAsString(ajaxResponse);
     eval(responseHTML);
     this.tree.draw();
     this.tree.expandAll();
   }
}
function ajaxAddTreeElement(parent,node) {
   //alert('from ajaxAddTreeElement node='+node+' parent='+parent);
   ajaxEngine.sendRequest('ajaxAddTreeElement','parent='+parent,'node='+node);
}
function registerTreeView() {
   var id = document.getElementById("parents");
   id.innerHTML='Parents tree. Please click on a node to see its parents.<div id="treeDiv"></div>';
   ajaxEngine.registerRequest('ajaxAddTreeElement','addTreeElement');
   var updater = new TreeUpdater();
   ajaxEngine.registerAjaxObject('treeViewInfo',updater);
}
function ajaxGetDummyData() {
  arr='test1,test2';
  ajaxEngine.sendRequest('ajaxGetDummyData',"arr="+arr);
}

function registerAjaxGetLumisCalls() {
    ajaxEngine.registerRequest('ajaxGetLumis','getLFN_Lumis');
    ajaxEngine.registerAjaxElement('floatDataDescription');
}
function ajaxGetLumis(dbs,lfn) {
    ajaxEngine.sendRequest('ajaxGetLumis','dbsInst='+dbs,'lfn='+lfn,'ajax=1');
    ShowTag('floatDataDescription');
}
function registerAjaxGetAlgosCalls() {
    ajaxEngine.registerRequest('ajaxGetAlgos','getLFN_Algos');
    ajaxEngine.registerAjaxElement('floatDataDescription');
}
function ajaxGetAlgos(dbs,lfn) {
    ajaxEngine.sendRequest('ajaxGetAlgos','dbsInst='+dbs,'lfn='+lfn,'ajax=1');
    ShowTag('floatDataDescription');
}
/*
 dhtml stuff
 For more information see
 http://codinginparadise.org/projects/dhtml_history/
 http://www.onjava.com/pub/a/onjava/2005/10/26/ajax-handling-bookmarks-and-back-button.html
*/
function initialize_dhtmlHistory() {
    // initialize our DHTML history
    dhtmlHistory.initialize();
    // subscribe to DHTML history change events
    dhtmlHistory.addListener(historyChange);
}
function historyChange(newLocation, historyData) {
   if(newLocation.indexOf("moreInfo")>-1) {
     var id=newLocation.replace('moreInfo','');
     data=historyData.split(',');
     ajaxEngine.registerRequest('ajaxMoreInfo','getMoreInfo');
     ajaxEngine.registerAjaxElement(id);
     ajaxMoreInfo(data[0],data[1],data[2],data[3],data[4]);
   } else {
   eval(historyData);
   }
}
function ajax_dhtmlHistory(id,action) {
   browser=CheckBrowser();
   // DHTML framework doesn't work on Safari.
   if(!browser.match('Safari')) {
      dhtmlHistory.add(id,action);
   }
}
function registerAjaxGetMoreInfoCalls() {
    ajaxEngine.registerRequest('ajaxMoreInfo','getMoreInfo');
//    ajaxEngine.registerAjaxElement('floatDataDescription');
}
function ajaxMoreInfo(dbsInst,path,appPath,id,userMode) {
    ajaxEngine.sendRequest('ajaxMoreInfo','dbsInst='+dbsInst,'path='+path,'appPath='+appPath,'id='+id,'userMode='+userMode);
    ShowTag(id);
}
function ajaxWriteUserQuery() {
    var name=$('kw_alias').value;
    // I need to get from templateSearchTable.tmpl all values and store using alias name user query
}
//function ajaxGetUserNav() {
//    var dbsInst=$('kw_dbsSelector').value;
//    ajaxEngine.sendRequest('ajaxGetUserNav','dbsInst='+dbsInst,'ajax=1');
//}
function ajaxPrintXML(input,id) {
    ajaxEngine.registerRequest('ajaxPrintXML','printXML');
    ajaxEngine.registerAjaxElement(id);
    ajaxEngine.sendRequest('ajaxPrintXML','input='+input,'id='+id,'ajax=1');
    ShowTag(id);
}
//function ajaxConvertXMLTOTXT(input,id) {
//    ajaxEngine.registerRequest('ajaxConvertXMLTOTXT','convertXMLTOTXT');
//    ajaxEngine.registerAjaxElement(id);
//    ajaxEngine.sendRequest('ajaxConvertXMLTOTXT','input='+input,'id='+id,'ajax=1','html=1');
//    ShowTag(id);
//}
/** 
   Copyright (c) 2005, Brad Neuberg, bkn3@columbia.edu
   http://codinginparadise.org
   
   Permission is hereby granted, free of charge, to any person obtaining 
   a copy of this software and associated documentation files (the "Software"), 
   to deal in the Software without restriction, including without limitation 
   the rights to use, copy, modify, merge, publish, distribute, sublicense, 
   and/or sell copies of the Software, and to permit persons to whom the 
   Software is furnished to do so, subject to the following conditions:
   
   The above copyright notice and this permission notice shall be 
   included in all copies or substantial portions of the Software.
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, 
   EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES 
   OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
   IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY 
   CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT 
   OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR 
   THE USE OR OTHER DEALINGS IN THE SOFTWARE.
   
   The JSON class near the end of this file is
   Copyright 2005, JSON.org
*/
function isInternetExplorer() {
      var userAgent = navigator.userAgent.toLowerCase();
      if (document.all && userAgent.indexOf('msie')!=-1) {
         return true;
      }
      else {
         return false;
      }
}
var isIE=isInternetExplorer();

/** An object that provides DHTML history, history data, and bookmarking 
    for AJAX applications. */
window.dhtmlHistory = {
   /** Initializes our DHTML history. You should
       call this after the page is finished loading. */
   /** public */ initialize: function() {
      // only Internet Explorer needs to be explicitly initialized;
      // other browsers don't have its particular behaviors.
      // Basicly, IE doesn't autofill form data until the page
      // is finished loading, which means historyStorage won't
      // work until onload has been fired.
      if (isIE == false) {
         return;
      }
         
      // if this is the first time this page has loaded...
      if (historyStorage.hasKey("DhtmlHistory_pageLoaded") == false) {
         this.fireOnNewListener = false;
         this.firstLoad = true;
         historyStorage.put("DhtmlHistory_pageLoaded", true);
      }
      // else if this is a fake onload event
      else {
         this.fireOnNewListener = true;
         this.firstLoad = false;   
      }
   },
             
   /** Adds a history change listener. Note that
       only one listener is supported at this
       time. */
   /** public */ addListener: function(callback) {
      this.listener = callback;
      
      // if the page was just loaded and we
      // should not ignore it, fire an event
      // to our new listener now
      if (this.fireOnNewListener == true) {
         this.fireHistoryEvent(this.currentLocation);
         this.fireOnNewListener = false;
      }
   },
   
   /** public */ add: function(newLocation, historyData) {
      // most browsers require that we wait a certain amount of time before changing the
      // location, such as 200 milliseconds; rather than forcing external callers to use
      // window.setTimeout to account for this to prevent bugs, we internally handle this
      // detail by using a 'currentWaitTime' variable and have requests wait in line
      var self = this;
      var addImpl = function() {
         // indicate that the current wait time is now less
         if (self.currentWaitTime > 0)
            self.currentWaitTime = self.currentWaitTime - self.WAIT_TIME;
            
         // remove any leading hash symbols on newLocation
         newLocation = self.removeHash(newLocation);
         
         // IE has a strange bug; if the newLocation
         // is the same as _any_ preexisting id in the
         // document, then the history action gets recorded
         // twice; throw a programmer exception if there is
         // an element with this ID
         var idCheck = document.getElementById(newLocation);
         if (idCheck != undefined || idCheck != null) {
            var message = 
               "Exception: History locations can not have "
               + "the same value as _any_ id's "
               + "that might be in the document, "
               + "due to a bug in Internet "
               + "Explorer; please ask the "
               + "developer to choose a history "
               + "location that does not match "
               + "any HTML id's in this "
               + "document. The following ID "
               + "is already taken and can not "
               + "be a location: " 
               + newLocation;
               
            throw message; 
         }
         
         // store the history data into history storage
         historyStorage.put(newLocation, historyData);
         
         // indicate to the browser to ignore this upcomming 
         // location change
         self.ignoreLocationChange = true;
 
         // indicate to IE that this is an atomic location change
         // block
         this.ieAtomicLocationChange = true;
                 
         // save this as our current location
         self.currentLocation = newLocation;
         
         // change the browser location
         window.location.hash = newLocation;
         
         // change the hidden iframe's location if on IE
         if(isIE)
            self.iframe.src = "blank.html?" + newLocation;
            
         // end of atomic location change block
         // for IE
         this.ieAtomicLocationChange = false;
      };

      // now execute this add request after waiting a certain amount of time, so as to
      // queue up requests
      window.setTimeout(addImpl, this.currentWaitTime);
   
      // indicate that the next request will have to wait for awhile
      this.currentWaitTime = this.currentWaitTime + this.WAIT_TIME;
   },
   
   /** public */ isFirstLoad: function() {
      if (this.firstLoad == true) {
         return true;
      }
      else {
         return false;
      }
   },
   
   /** public */ isInternational: function() {
      return false;
   },
   
   /** public */ getVersion: function() {
      return "0.05";
   },
   
   /** Gets the current hash value that is in the browser's
       location bar, removing leading # symbols if they are present. */
   /** public */ getCurrentLocation: function() {
      var currentLocation = this.removeHash(window.location.hash);
         
      return currentLocation;
   },
   
   
   
   
   
   /** Our current hash location, without the "#" symbol. */
   /** private */ currentLocation: null,
   
   /** Our history change listener. */
   /** private */ listener: null,
   
   /** A hidden IFrame we use in Internet Explorer to detect history
       changes. */
   /** private */ iframe: null,
   
   /** Indicates to the browser whether to ignore location changes. */
   /** private */ ignoreLocationChange: null,
 
   /** The amount of time in milliseconds that we should wait between add requests. 
       Firefox is okay with 200 ms, but Internet Explorer needs 400. */
   /** private */ WAIT_TIME: 200,

   /** The amount of time in milliseconds an add request has to wait in line before being
       run on a window.setTimeout. */
   /** private */ currentWaitTime: 0,
   
   /** A flag that indicates that we should fire a history change event
       when we are ready, i.e. after we are initialized and
       we have a history change listener. This is needed due to 
       an edge case in browsers other than Internet Explorer; if
       you leave a page entirely then return, we must fire this
       as a history change event. Unfortunately, we have lost
       all references to listeners from earlier, because JavaScript
       clears out. */
   /** private */ fireOnNewListener: null,
   
   /** A variable that indicates whether this is the first time
       this page has been loaded. If you go to a web page, leave
       it for another one, and then return, the page's onload
       listener fires again. We need a way to differentiate
       between the first page load and subsequent ones.
       This variable works hand in hand with the pageLoaded
       variable we store into historyStorage.*/
   /** private */ firstLoad: null,
   
   /** A variable to handle an important edge case in Internet
       Explorer. In IE, if a user manually types an address into
       their browser's location bar, we must intercept this by
       continiously checking the location bar with an timer 
       interval. However, if we manually change the location
       bar ourselves programmatically, when using our hidden
       iframe, we need to ignore these changes. Unfortunately,
       these changes are not atomic, so we surround them with
       the variable 'ieAtomicLocationChange', that if true,
       means we are programmatically setting the location and
       should ignore this atomic chunked change. */
   /** private */ ieAtomicLocationChange: null,          
   
   /** Creates the DHTML history infrastructure. */
   /** private */ create: function() {
      // get our initial location
      var initialHash = this.getCurrentLocation();
      
      // save this as our current location
      this.currentLocation = initialHash;
      
      // write out a hidden iframe for IE and
      // set the amount of time to wait between add() requests
      if(isIE) {
         document.write("<iframe style='border: 0px; width: 1px; "
                               + "height: 1px; position: absolute; bottom: 0px; "
                               + "right: 0px; visibility: visible;' "
                               + "name='DhtmlHistoryFrame' id='DhtmlHistoryFrame' "
                               + "src='blank.html?" + initialHash + "'>"
                               + "</iframe>");
         // wait 400 milliseconds between history
         // updates on IE, versus 200 on Firefox
         this.WAIT_TIME = 400;
      }
      
      // add an unload listener for the page; this is
      // needed for Firefox 1.5+ because this browser caches all
      // dynamic updates to the page, which can break some of our 
      // logic related to testing whether this is the first instance
      // a page has loaded or whether it is being pulled from the cache
      var self = this;
      window.onunload = function() {
         self.firstLoad = null;
      };
      
      // determine if this is our first page load;
      // for Internet Explorer, we do this in 
      // this.iframeLoaded(), which is fired on
      // page load. We do it there because
      // we have no historyStorage at this point
      // in IE, which only exists after the page
      // is finished loading for that browser
      if (isIE==false) {
         if (historyStorage.hasKey("DhtmlHistory_pageLoaded") == false) {
            this.ignoreLocationChange = true;
            this.firstLoad = true;
            historyStorage.put("DhtmlHistory_pageLoaded", true);
         }
         else {
            // indicate that we want to pay attention
            // to this location change
            this.ignoreLocationChange = false;
            // For browser's other than IE, fire
            // a history change event; on IE,
            // the event will be thrown automatically
            // when it's hidden iframe reloads
            // on page load.
            // Unfortunately, we don't have any 
            // listeners yet; indicate that we want
            // to fire an event when a listener
            // is added.
            this.fireOnNewListener = true;
         }
      }
      else { // Internet Explorer
         // the iframe will get loaded on page
         // load, and we want to ignore this fact
         this.ignoreLocationChange = true;
      }
      
      if (isIE) {
            this.iframe = document.getElementById("DhtmlHistoryFrame");
      }                                                              

      // other browsers can use a location handler that checks
      // at regular intervals as their primary mechanism;
      // we use it for Internet Explorer as well to handle
      // an important edge case; see checkLocation() for
      // details
      var self = this;
      var locationHandler = function() {
         self.checkLocation();
      };
      setInterval(locationHandler, 100);
   },
   
   /** Notify the listener of new history changes. */
   /** private */ fireHistoryEvent: function(newHash) {
      // extract the value from our history storage for
      // this hash
      var historyData = historyStorage.get(newHash);

      // call our listener      
      this.listener.call(null, newHash, historyData);
   },
   
   /** Sees if the browsers has changed location.  This is the primary history mechanism
       for Firefox. For Internet Explorer, we use this to handle an important edge case:
       if a user manually types in a new hash value into their Internet Explorer location
       bar and press enter, we want to intercept this and notify any history listener. */
   /** private */ checkLocation: function() {
      // ignore any location changes that we made ourselves
      // for browsers other than Internet Explorer
      if (isIE==false
         && this.ignoreLocationChange == true) {
         this.ignoreLocationChange = false;
         return;
      }
      
      // if we are dealing with Internet Explorer
      // and we are in the middle of making a location
      // change from an iframe, ignore it
      if (isIE==false
          && this.ieAtomicLocationChange == true) {
         return;
      }
      
      // get hash location
      var hash = this.getCurrentLocation();
      
      // see if there has been a change
      if (hash == this.currentLocation)
         return;
         
      // on Internet Explorer, we need to intercept users manually
      // entering locations into the browser; we do this by comparing
      // the browsers location against the iframes location; if they
      // differ, we are dealing with a manual event and need to
      // place it inside our history, otherwise we can return
      this.ieAtomicLocationChange = true;
      
      if (isIE
          && this.getIFrameHash() != hash) {
         this.iframe.src = "blank.html?" + hash;
      }
      else if (isIE) {
         // the iframe is unchanged
         return;
      }
         
      // save this new location
      this.currentLocation = hash;
      
      this.ieAtomicLocationChange = false;
      
      // notify listeners of the change
      this.fireHistoryEvent(hash);
   },  

   /** Gets the current location of the hidden IFrames
       that is stored as history. For Internet Explorer. */
   /** private */ getIFrameHash: function() {
      // get the new location
      var historyFrame = document.getElementById("DhtmlHistoryFrame");
      var doc = historyFrame.contentWindow.document;
      var hash = new String(doc.location.search);

      if (hash.length == 1 && hash.charAt(0) == "?")
         hash = "";
      else if (hash.length >= 2 && hash.charAt(0) == "?")
         hash = hash.substring(1); 
    
    
      return hash;
   },          
   
   /** Removes any leading hash that might be on a location. */
   /** private */ removeHash: function(hashValue) {
      if (hashValue == null || hashValue == undefined)
         return null;
      else if (hashValue == "")
         return "";
      else if (hashValue.length == 1 && hashValue.charAt(0) == "#")
         return "";
      else if (hashValue.length > 1 && hashValue.charAt(0) == "#")
         return hashValue.substring(1);
      else
         return hashValue;     
   },          
   
   /** For IE, says when the hidden iframe has finished loading. */
   /** private */ iframeLoaded: function(newLocation) {
      // ignore any location changes that we made ourselves
      if (this.ignoreLocationChange == true) {
         this.ignoreLocationChange = false;
         return;
      }
      
      // get the new location
      var hash = new String(newLocation.search);
      if (hash.length == 1 && hash.charAt(0) == "?")
         hash = "";
      else if (hash.length >= 2 && hash.charAt(0) == "?")
         hash = hash.substring(1);
      
      // move to this location in the browser location bar
      // if we are not dealing with a page load event
      if (this.pageLoadEvent != true) {
         window.location.hash = hash;
      }

      // notify listeners of the change
      this.fireHistoryEvent(hash);
   }
};

/** An object that uses a hidden form to store history state 
    across page loads. The chief mechanism for doing so is using
    the fact that browser's save the text in form data for the
    life of the browser and cache, which means the text is still
    there when the user navigates back to the page. See
    http://codinginparadise.org/weblog/2005/08/ajax-tutorial-saving-session-across.html
    for full details. */
window.historyStorage = {
   /** If true, we are debugging and show the storage textfield. */
   /** public */ debugging: false,
   
   /** Our hash of key name/values. */
   /** private */ storageHash: new Object(),
   
   /** If true, we have loaded our hash table out of the storage form. */
   /** private */ hashLoaded: false, 
   
   /** public */ put: function(key, value) {
       this.assertValidKey(key);
       
       // if we already have a value for this,
       // remove the value before adding the
       // new one
       if (this.hasKey(key)) {
         this.remove(key);
       }
       
       // store this new key
       this.storageHash[key] = value;
       
       // save and serialize the hashtable into the form
       this.saveHashTable(); 
   },
   
   /** public */ get: function(key) {
      this.assertValidKey(key);
      
      // make sure the hash table has been loaded
      // from the form
      this.loadHashTable();
      
      var value = this.storageHash[key];

      if (value == undefined)
         return null;
      else
         return value; 
   },
   
   /** public */ remove: function(key) {
      this.assertValidKey(key);
      
      // make sure the hash table has been loaded
      // from the form
      this.loadHashTable();
      
      // delete the value
      delete this.storageHash[key];
      
      // serialize and save the hash table into the 
      // form
      this.saveHashTable();
   },
   
   /** Clears out all saved data. */
   /** public */ reset: function() {
      this.storageField.value = "";
      this.storageHash = new Object();
   },
   
   /** public */ hasKey: function(key) {
      this.assertValidKey(key);
      
      // make sure the hash table has been loaded
      // from the form
      this.loadHashTable();
      
      if (typeof this.storageHash[key] == "undefined")
         return false;
      else
         return true;
   },
   
   /** Determines whether the key given is valid;
       keys can only have letters, numbers, the dash,
       underscore, spaces, or one of the 
       following characters:
       !@#$%^&*()+=:;,./?|\~{}[] */
   /** public */ isValidKey: function(key) {
      // allow all strings, since we don't use XML serialization
      // format anymore
      return (typeof key == "string");
      
      /*
      if (typeof key != "string")
         key = key.toString();
      
      
      var matcher = 
         /^[a-zA-Z0-9_ \!\@\#\$\%\^\&\*\(\)\+\=\:\;\,\.\/\?\|\\\~\{\}\[\]]*$/;
                     
      return matcher.test(key);*/
   },
   
   
   
   
   /** A reference to our textarea field. */
   /** private */ storageField: null,
   
   /** private */ init: function() {
      // write a hidden form into the page
      var styleValue = "position: absolute; top: -1000px; left: -1000px;";
      if (this.debugging == true) {
         styleValue = "width: 30em; height: 30em;";
      }   
      
      var newContent =
         "<form id='historyStorageForm' " 
               + "method='GET' "
               + "style='" + styleValue + "'>"
            + "<textarea id='historyStorageField' "
                      + "style='" + styleValue + "'"
                              + "left: -1000px;' "
                      + "name='historyStorageField'></textarea>"
         + "</form>";
      document.write(newContent);
      
      this.storageField = document.getElementById("historyStorageField");
   },
   
   /** Asserts that a key is valid, throwing
       an exception if it is not. */
   /** private */ assertValidKey: function(key) {
      if (this.isValidKey(key) == false) {
         throw "Please provide a valid key for "
               + "window.historyStorage, key= "
               + key;
       }
   },
   
   /** Loads the hash table up from the form. */
   /** private */ loadHashTable: function() {
      if (this.hashLoaded == false) {
         // get the hash table as a serialized
         // string
         var serializedHashTable = this.storageField.value;
         
         if (serializedHashTable != "" &&
             serializedHashTable != null) {
            // destringify the content back into a 
            // real JavaScript object
            this.storageHash = eval('(' + serializedHashTable + ')');  
         }
         
         this.hashLoaded = true;
      }
   },
   
   /** Saves the hash table into the form. */
   /** private */ saveHashTable: function() {
      this.loadHashTable();
      
      // serialized the hash table
      var serializedHashTable = JSON.stringify(this.storageHash);
      
      // save this value
      this.storageField.value = serializedHashTable;
   }   
};










/** The JSON class is copyright 2005 JSON.org. */
Array.prototype.______array = '______array';

var JSON = {
    org: 'http://www.JSON.org',
    copyright: '(c)2005 JSON.org',
    license: 'http://www.crockford.com/JSON/license.html',

    stringify: function (arg) {
        var c, i, l, s = '', v;

        switch (typeof arg) {
        case 'object':
            if (arg) {
                if (arg.______array == '______array') {
                    for (i = 0; i < arg.length; ++i) {
                        v = this.stringify(arg[i]);
                        if (s) {
                            s += ',';
                        }
                        s += v;
                    }
                    return '[' + s + ']';
                } else if (typeof arg.toString != 'undefined') {
                    for (i in arg) {
                        v = arg[i];
                        if (typeof v != 'undefined' && typeof v != 'function') {
                            v = this.stringify(v);
                            if (s) {
                                s += ',';
                            }
                            s += this.stringify(i) + ':' + v;
                        }
                    }
                    return '{' + s + '}';
                }
            }
            return 'null';
        case 'number':
            return isFinite(arg) ? String(arg) : 'null';
        case 'string':
            l = arg.length;
            s = '"';
            for (i = 0; i < l; i += 1) {
                c = arg.charAt(i);
                if (c >= ' ') {
                    if (c == '\\' || c == '"') {
                        s += '\\';
                    }
                    s += c;
                } else {
                    switch (c) {
                        case '\b':
                            s += '\\b';
                            break;
                        case '\f':
                            s += '\\f';
                            break;
                        case '\n':
                            s += '\\n';
                            break;
                        case '\r':
                            s += '\\r';
                            break;
                        case '\t':
                            s += '\\t';
                            break;
                        default:
                            c = c.charCodeAt();
                            s += '\\u00' + Math.floor(c / 16).toString(16) +
                                (c % 16).toString(16);
                    }
                }
            }
            return s + '"';
        case 'boolean':
            return String(arg);
        default:
            return 'null';
        }
    },
    parse: function (text) {
        var at = 0;
        var ch = ' ';

        function error(m) {
            throw {
                name: 'JSONError',
                message: m,
                at: at - 1,
                text: text
            };
        }

        function next() {
            ch = text.charAt(at);
            at += 1;
            return ch;
        }

        function white() {
            while (ch != '' && ch <= ' ') {
                next();
            }
        }

        function str() {
            var i, s = '', t, u;

            if (ch == '"') {
outer:          while (next()) {
                    if (ch == '"') {
                        next();
                        return s;
                    } else if (ch == '\\') {
                        switch (next()) {
                        case 'b':
                            s += '\b';
                            break;
                        case 'f':
                            s += '\f';
                            break;
                        case 'n':
                            s += '\n';
                            break;
                        case 'r':
                            s += '\r';
                            break;
                        case 't':
                            s += '\t';
                            break;
                        case 'u':
                            u = 0;
                            for (i = 0; i < 4; i += 1) {
                                t = parseInt(next(), 16);
                                if (!isFinite(t)) {
                                    break outer;
                                }
                                u = u * 16 + t;
                            }
                            s += String.fromCharCode(u);
                            break;
                        default:
                            s += ch;
                        }
                    } else {
                        s += ch;
                    }
                }
            }
            error("Bad string");
        }

        function arr() {
            var a = [];

            if (ch == '[') {
                next();
                white();
                if (ch == ']') {
                    next();
                    return a;
                }
                while (ch) {
                    a.push(val());
                    white();
                    if (ch == ']') {
                        next();
                        return a;
                    } else if (ch != ',') {
                        break;
                    }
                    next();
                    white();
                }
            }
            error("Bad array");
        }

        function obj() {
            var k, o = {};

            if (ch == '{') {
                next();
                white();
                if (ch == '}') {
                    next();
                    return o;
                }
                while (ch) {
                    k = str();
                    white();
                    if (ch != ':') {
                        break;
                    }
                    next();
                    o[k] = val();
                    white();
                    if (ch == '}') {
                        next();
                        return o;
                    } else if (ch != ',') {
                        break;
                    }
                    next();
                    white();
                }
            }
            error("Bad object");
        }

        function num() {
            var n = '', v;
            if (ch == '-') {
                n = '-';
                next();
            }
            while (ch >= '0' && ch <= '9') {
                n += ch;
                next();
            }
            if (ch == '.') {
                n += '.';
                while (next() && ch >= '0' && ch <= '9') {
                    n += ch;
                }
            }
            if (ch == 'e' || ch == 'E') {
                n += 'e';
                next();
                if (ch == '-' || ch == '+') {
                    n += ch;
                    next();
                }
                while (ch >= '0' && ch <= '9') {
                    n += ch;
                    next();
                }
            }
            v = +n;
            if (!isFinite(v)) {
                error("Bad number");
            } else {
                return v;
            }
        }

        function word() {
            switch (ch) {
                case 't':
                    if (next() == 'r' && next() == 'u' && next() == 'e') {
                        next();
                        return true;
                    }
                    break;
                case 'f':
                    if (next() == 'a' && next() == 'l' && next() == 's' &&
                            next() == 'e') {
                        next();
                        return false;
                    }
                    break;
                case 'n':
                    if (next() == 'u' && next() == 'l' && next() == 'l') {
                        next();
                        return null;
                    }
                    break;
            }
            error("Syntax error");
        }

        function val() {
            white();
            switch (ch) {
                case '{':
                    return obj();
                case '[':
                    return arr();
                case '"':
                    return str();
                case '-':
                    return num();
                default:
                    return ch >= '0' && ch <= '9' ? num() : word();
            }
        }

        return val();
    }
};



/** Initialize all of our objects now. */
window.historyStorage.init();
window.dhtmlHistory.create();
