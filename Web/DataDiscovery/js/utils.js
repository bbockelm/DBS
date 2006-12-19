function setMain() {
  var id=document.getElementById("main");
  if (id) {
      id.className="main";
  }
}
function hideTag(tag) {
  var id=document.getElementById(tag);
  if (id) {
      id.className="hide";
  }
}
function showTag(tag) {
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
_ids[1]='parents';
_ids[2]='appConfigs';
_ids[3]='dataDescription';
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
//      t.className="show_inline";
      t.className="table_round_box";
   }
   var t=document.getElementById("hr_results_menu");
   if(t) {
      t.className="dbs";
   }
}
function showMenu(menu) {
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
  underlineLink("Blocks");
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
function checkSel(sel) {
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
  var dbs=checkSel(sel);
  if(!dbs) {
     id.innerHTML='<span class="box_red">Please select <b>DBS instance</b></span>';
     return null;
  }
  var sel=document.getElementById("appSelector");
  var app=checkSel(sel);
  if(!app) {
     id.innerHTML='<span class="box_red">Please select <b>Application</b></span>';
     return null;
  }
  var sel=document.getElementById("primSelector");
  var prim=checkSel(sel);
  if(!prim) {
     id.innerHTML='<span class="box_red">Please select <b>Primary dataset</b></span>';
     return null;
  }
  var sel=document.getElementById("tierSelector");
  var tier=checkSel(sel);
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

function getMonthIdx(month) {
  var mArr = new Array('Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec');
  for(i=0;i<mArr.length;i++) {
      if(mArr[i]==month) {
         return i;
      }
  }
}
function adjustToDate() {
  var iSel_y= document.getElementById("in_hSearch_year");
  var iYear = checkSel(iSel_y);
  var iSel_m= document.getElementById("in_hSearch_month");
  var iMonth= checkSel(iSel_m);
   
  var oSel_y= document.getElementById("out_hSearch_year");
  var oYear = checkSel(oSel_y);
  var oSel_m= document.getElementById("out_hSearch_month");
  var oMonth= checkSel(oSel_m);

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
      if(getMonthIdx(oSel_m[o_idx].value)<getMonthIdx(iSel_m[i_idx].value)) {
         oSel_m[o_idx].selected=null;
         oSel_m[i_idx].selected="selected";
      }
  }
}
function checkToDate() {
  var iSel_y= document.getElementById("in_hSearch_year");
  var iYear = checkSel(iSel_y);
  var iSel_m= document.getElementById("in_hSearch_month");
  var iMonth= checkSel(iSel_m);
   
  var oSel_y= document.getElementById("out_hSearch_year");
  var oYear = checkSel(oSel_y);
  var oSel_m= document.getElementById("out_hSearch_month");
  var oMonth= checkSel(oSel_m);
  
  var id=document.getElementById("historySearchResults");
  if(oYear<iYear || getMonthIdx(oMonth)<getMonthIdx(iMonth) ) {
     id.innerHTML='<span class="box_red">You choose wrong date order, "From" date should be >= then "To" date</span>';
  } else {
    if(id.innerHTML.search("You choose wrong date order")) {
       id.innerHTML="";
    }
  }
}
function disableSel(tag) {
  var sel=document.getElementById(tag);
  if(sel) {
     sel.disabled="disabled";
  }
}
function enableSel(tag) {
  var sel=document.getElementById(tag);
  if(sel) {
     sel.disabled="";
  }
}
