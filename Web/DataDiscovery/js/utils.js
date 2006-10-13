function showLoadingMessage(idTag,iMsg) {
  var tag, msg;
  if(idTag) {
    tag=idTag;
  } else {
    tag="results";
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
  showLoadingMessage("results");
  var hr=document.getElementById("results_hr");
  if(hr) {
     hr.className="dbs";
  }
}
function hideWaitingMessage() {
  var res=document.getElementById("results");
  if(res) {
     res.className="hide";
  }
  var hr=document.getElementById("results_hr");
  if(hr) {
     hr.className="hide";
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
   var url='Home page: <a href="'+link+'/">users</a>, <a href="'+link+'/expert">experts</a>';
   msg='<table width="100%"><tr><td><span class="sectionhead_tight">HIDE NAVIGATION <a href="javascript:HidePanel(\''+link+'\')">PANEL</a></span></td><td align="right">'+url+'</td></tr></table>';
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
function HidePanel(link) {
   var url='Home page: <a href="'+link+'/">users</a>, <a href="'+link+'/expert">experts</a>';
   msg='<table width="100%"><tr><td><span class="sectionhead_tight">SHOW NAVIGATION <a href="javascript:ShowPanel(\''+link+'\')">PANEL</a></span></td><td align="right">'+url+'</td></tr></table>';
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
function showMenu(menu) {
   hideWaitingMessage();
   var menuArr = new Array();
   menuArr[0]='Navigator';
   menuArr[1]='Search';
   menuArr[2]='Site';
   menuArr[3]='Summary';
   menuArr[4]='Datasets';
   menuArr[5]='About';
   for(var i=0;i<menuArr.length;i++) {
       var c=document.getElementById(menuArr[i]+'_Menu');
       if (c) {
           c.className="td_gray_box";
       }
       var t=document.getElementById(menuArr[i]+'Div');
       if (t) {
           if(menuArr[i]==menu) {
               t.className="show";
           } else {
               t.className="hide";
           }
       }
   }
   var t=document.getElementById(menu+"_Menu");
   if (t) {
       t.className="td_blue_box"
   }
}
function ShowBlockInfo(tableId){
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

