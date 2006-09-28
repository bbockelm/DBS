function ShowBlockInfo(){
  var t=document.getElementsByName("BlockInfoText");
  for(var i=0;i<t.length;i++) {
      t[i].innerHTML='Show block <a href="javascript:HideBlockInfo()">info</a>';
  }
  var elem=document.getElementsByName("blockInfo");
  for(var i=0;i<elem.length;i++) {
      elem[i].style.display="none";
      elem[i].style.visibilty="hidden";
  }
}
function HideBlockInfo(){
  var t=document.getElementsByName("BlockInfoText");
  for(var i=0;i<t.length;i++) {
      t[i].innerHTML='Hide block <a href="javascript:ShowBlockInfo()">info</a>';
  }
  var elem=document.getElementsByName("blockInfo");
  for(var i=0;i<elem.length;i++) {
      elem[i].style.display="inline";
      elem[i].style.visibility="visible";
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
// PRELOADING IMAGES
/*
function Hide() {
  t.innerHTML='Hide block <a href="javascript:ShowBlockInfo('+tot+')">info</a>';
  var elem=document.getElementById("blockInfo");
  elem.style.display="inline";
  elem.style.visibility="visible"
  for(var i=1;i<=tot;i++) {
      var e = document.getElementById("tdBlockInfo"+i);
      e.style.display="inline";
      e.style.visibility="visible"
  }
}
if (document.images) {
 img_on =new Image();  img_on.src ="img/1.gif"; 
 img_off=new Image();  img_off.src="img/2.gif"; 
}

function handleOver() { 
  if (document.images) document.imgName.src=img_on.src;
}

function handleOut() {
  if (document.images) document.imgName.src=img_off.src;
}
<script type="text/javascript">
<!--
function showBlockInfo() {
blockWindow = window.open("", "block info", 'toolbar=1,scrollbars=1,location=0,statusbar=1,menubar=1,resizable=1,width=1024,height=480,left=90,top=120');
blockWindow.document.open();
blockWindow.document.writeln('<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">');
blockWindow.document.writeln('<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">');
blockWindow.document.writeln('<head><title>Block info</title>');
blockWindow.document.writeln('<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />');
blockWindow.document.writeln('<link rel="stylesheet" type="text/css" href="css/dbs.css" />');
blockWindow.document.writeln('</head><body>');
blockWindow.document.writeln('<table><tr><th>Location</th><th>Block name</th></tr>');
#for key in $locDict:
#for item in $locDict[$key]
blockWindow.document.writeln('<tr><td>$key</td>');
#set name    = $item[1]
#if $name
#set bName= $name.replace('#','%23')
#else
#set bName= $name
#end if
blockWindow.document.writeln('<td><a href=\"$host/getLFNlist?blockName=$bName&dataset=$path\">$name</a></td>');
#end for
blockWindow.document.writeln('</tr>');
#end for
blockWindow.document.writeln('</table>');
blockWindow.document.writeln('<p><div align=\"right\"><a href="javascript:self.close()">close window</a></div></p>');
blockWindow.document.writeln('</body></html>');
blockWindow.document.close();
}
//-->
</script>
Show block <a href="javascript:showBlockInfo()">info</a>.
*/
