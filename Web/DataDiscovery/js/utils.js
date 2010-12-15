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
