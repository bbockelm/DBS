function ajaxGetData() {
  var dbs=document.getElementById('dbsSelector').value;
  var site=document.getElementById('siteSelector').value;
  var app=document.getElementById('appSelector').value;
  var primD=document.getElementById('primSelector').value;
  var tier=document.getElementById('tierSelector').value;
    
  ajaxEngine.sendRequest('ajaxGetData',"dbsInst="+dbs,"site="+site,"app="+app,"primD="+primD,"tier="+tier);
}
function registerAjaxGetDataCalls() {
  ajaxEngine.registerRequest('ajaxGetData','getDataHelper');
  ajaxEngine.registerAjaxElement('getDataHelperHandler');
}
function getSummary() {
  var id=document.getElementById("summary");
  id.className="show_inline_off";
  ajaxEngine.sendRequest('getSummary');
}
function registerAjaxSummaryCalls() {
  ajaxEngine.registerRequest('getSummary','summary');
  ajaxEngine.registerAjaxElement('summary');
}

function getPrimDatasets() {
  ajaxEngine.sendRequest('getPrimDatasets');
}
function getProvenance(id,dataset) {

  var i=document.getElementById(id);
  i.className="show_inline_off";
  ajaxEngine.sendRequest('getProvenance',"dataset="+dataset);

  var t=document.getElementById("tmp"+id);
  t.className="hide";
}
function genTmpParents(id) {
  var t=document.getElementById("tmp"+id);
  t.className="show_inline_off";
  t.innerHTML="Please wait, searching for parents"
  return;
}

