// Global variables
var GLOBAL_CELL='cell_1';
//var GLOBAL_STEP=5;
//var DBSDD='http://cmsdbs.cern.ch/discovery/';
//var DBSDD_EXPERT=DBSDD+'expert';

// Override masthead
YAHOO.namespace("cms.dmwt");
function insertSiteMasthead(){
        YAHOO.cms.dmwt.masthead.setFooter('<ul><li><a href="'+DBSDD+'">DBS/DLS::User Mode</a></li><li>|</li><li><a href="'+DBSDD_EXPERT+'">DBS/DLS::Expert Mode</a></li></ul>');
        YAHOO.cms.dmwt.masthead.render(document.body);
        YAHOO.cms.dmwt.masthead.show();
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
function showHistoryMenu(name,histArr) {
  var id=document.getElementById("history_table");
  id.className="show_inline";
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
//_ids[4]='validation';
//_ids[5]='parameterSet';
//_ids[6]='releaseSpec';
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
     res.className="show_table";
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
   msg='<table width="100%"><tr><td class="menu_td_gray"><table width="100%"><tr><td class="td_gray_box"><a href="javascript:ShowPanel(\''+link+'\')">show panel</a></td><td></td></tr></table> </td></tr></table>';
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
   hideWaitingMessage();
   var menuArr = new Array();
   menuArr[0]='Navigator';
   menuArr[1]='Search';
   menuArr[2]='Site';
   menuArr[3]='Summary';
   menuArr[4]='DBSinfo';
   menuArr[5]='History';
   menuArr[6]='Help';
   menuArr[7]='Hide';
   hideResultsMenu();
   for(var i=0;i<menuArr.length;i++) {
       var c=document.getElementById(menuArr[i]+'_Menu');
       if (c) {
           c.className="td_gray_box";
           if(menuArr[i]=='DBSinfo') {
              var id=document.getElementById("dbsInst_table");
              id.className="hide";
           }
           if(menuArr[i]=='History') {
              var id=document.getElementById("history_table");
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
       t.className="td_blue_box"
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
  day = new Date();
  id = day.getTime();
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
  eval("page" + id + " = window.open(url, '" + id + "', 'toolbar=0,scrollbars=1,location=0,statusbar=0,menubar=0,resizable=1,width='+w+',height='+h+',left = 190,top = 220');");
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
function submitNavRequest() {
  if(checkNavSelection()==1) {
     ajaxGetData();
     ajaxGenParentsGraph();
     ajaxGenAppConfigs();
  }
}

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
   for(i=0;i<total;i++) {
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
function JumpToResult(idx,total,dbs,site,app,prim,tier,proc) {
   UpdateResultIndex(idx,total);
   // invoke next chunk of data
   ajaxNextGetData(dbs,site,app,prim,tier,proc,idx);
   var found=0;
   for(i=0;i<total;i++) {
      var id=document.getElementById('cell_'+i);
      if(id) {
         id.className="fixed";
      }
      var tagArr = new Array('results_response_'+i,'results_dbs_response_'+i,'runs_response_'+i,'parents_response_'+i,'appConfig_response_'+i);
      for(j=0;j<tagArr.length;j++) {
          tag=tagArr[j];
          if( i==(idx-1) ) { 
              var id=document.getElementById(tag);
              if(id) {
                 found=1;
                 ClearCells((idx-1),total)
                 Choose('cell_'+idx);
                 return;
              }
          }
      }
   }
   Choose('cell_'+idx);
   if(!found) {
      ShowWheel('__results');
      ShowWheel('__results_dbs');
      ShowWheel('__runs');
      // not ready yet, FIXME, TODO
//      ShowWheel('__parents');
//      ShowWheel('__appConfigs');
      ajaxNextGetData(dbs,site,app,prim,tier,proc,idx-1);
   }
}
function nPages(tot,max) {
    if(tot%max) {
       return (tot-tot%max)/max+1;
    }
    return tot/max
}
function BuildBar(from,to,total,GLOBAL_STEP,dbs,site,app,prim,tier,proc) {
//alert('BuildBar to='+to+' tot='+total+' step='+GLOBAL_STEP);
//   var STEP=nPages(total,GLOBAL_STEP);
//   if(STEP>total) {
//      STEP=total;
//      if(to>STEP) {
//         to=STEP;
//      }
//   }
//alert('BuildBar to='+to+' tot='+total+' step='+STEP);
   args=',\''+dbs+'\',\''+site+'\',\''+app+'\',\''+prim+'\',\''+tier+'\',\''+proc+'\'';
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
function KeywordHelp(help) {
  var id=document.getElementById('kw_help');
  if(id) {
     id.innerHTML='<div class="float_gray_box">'+help+'</div>';
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
