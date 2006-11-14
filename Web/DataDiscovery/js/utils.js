function showMenu(menu) {
   var menuArr = new Array();
   menuArr[0]='Navigator';
   menuArr[1]='Search';
   menuArr[2]='Site';
   for(var i=0;i<menuArr.length;i++) {
       var t=document.getElementById(menuArr[i]+'Div');
       if(menuArr[i]==menu) {
           t.className="show";
       } else {
           t.className="hide";
       }
   }
   var t=document.getElementById("Introduction");
   t.className="hide"
}
function showIntro() {
   var t=document.getElementById("Introduction");
   t.className="show"
}
function ShowProcDatasetsText(){
  var t=document.getElementById("ShowAllProcDatasets");
  t.innerHTML='Show all <a href="javascript:ShowProcDatasets()">datasets</a>'
}
function ShowProcDatasets(){
  var t=document.getElementById("ShowAllProcDatasets");
  var content="";
  var p=document.getElementsByName("procDataset");
  for(var i=0;i<t.length;i++) {
      content=content+'<td>'+p.innerHTML+'</td>'
  }
  t.innerHTML='<table><tr>'+content+'</tr></table>'
}

function ShowBlockInfo(tableId){
  var t=document.getElementsByName("BlockInfoText");
  for(var i=0;i<t.length;i++) {
      t[i].innerHTML='<span class="box_gray">Show block <a href="javascript:HideBlockInfo(\''+tableId+'\')">info</a></span>';
  }
  var elem=document.getElementsByName("blockInfo");
  for(var i=0;i<elem.length;i++) {
      elem[i].className="hide";
  }
  var elem=document.getElementsByName("row_blockInfo");
  for(var i=0;i<elem.length;i++) {
      elem[i].className="hide";
  }
}
function HideBlockInfo(tableId){
  var t=document.getElementsByName("BlockInfoText");
  for(var i=0;i<t.length;i++) {
      t[i].innerHTML='<span class="box_gray">Hide block <a href="javascript:ShowBlockInfo(\''+tableId+'\')">info</a></span>';
  }
  var elem=document.getElementsByName("row_blockInfo");
  for(var i=0;i<elem.length;i++) {
      elem[i].className="show_row";
  }
  var elem=document.getElementsByName("blockInfo");
  for(var i=0;i<elem.length;i++) {
      elem[i].className="show_cell";
  }
}
function ShowSumInfo(tableId) {
  var t=document.getElementsByName("SumInfoText");
  for(var i=0;i<t.length;i++) {
      t[i].innerHTML='<span class="box_light">Show summary <a href="javascript:HideSumInfo(\''+tableId+'\')">info</a></span>';
  }
  var elem=document.getElementsByName("row_sumInfo");
  for(var i=0;i<elem.length;i++) {
      elem[i].className="hide";
  }
}
function HideSumInfo(tableId) {
  var t=document.getElementsByName("SumInfoText");
  for(var i=0;i<t.length;i++) {
      t[i].innerHTML='<span class="box_light">Hide summary <a href="javascript:ShowSumInfo(\''+tableId+'\')">info</a></span>';
  }
  var elem=document.getElementsByName("row_sumInfo");
  for(var i=0;i<elem.length;i++) {
      elem[i].className="show_row";
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

function ShowProcDatasets() {
  var t=document.getElementsByName("procDataset");
  for(var i=0;i<t.length;i++) {
      t[i].innerHTML=''
  }
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
function popUp(URL) {
  day = new Date();
  id = day.getTime();
  eval("page" + id + " = window.open(URL, '" + id + "', 'toolbar=0,scrollbars=1,location=0,statusbar=0,menubar=0,resizable=1,width=640,height=480,left = 290,top = 220');");
}
