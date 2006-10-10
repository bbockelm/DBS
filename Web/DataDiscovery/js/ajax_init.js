function ajaxGetData() {
    var dbs=document.getElementById('dbsSelector').value;
    var site=document.getElementById('siteSelector').value;
    var app=document.getElementById('appSelector').value;
    var primD=document.getElementById('primSelector').value;
    var tier=document.getElementById('tierSelector').value;
    
//    alert("I got getData iDBS="+dbs+' site='+site+' app='+app+' prim='+primD+' tier='+tier);
    ajaxEngine.sendRequest('ajaxGetData',"dbsInst="+dbs,"site="+site,"app="+app,"primD="+primD,"tier="+tier);
//    ajaxEngine.sendRequest('ajaxGetData');
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
function getProvenance(dataset) {
  var id=document.getElementById(dataset);
  id.className="show_inline_off";
  ajaxEngine.sendRequest('getProvenance',"dataset="+dataset);
}

