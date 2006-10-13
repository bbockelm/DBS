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
  ajaxEngine.registerAjaxElement('results');
}

function registerAjaxGetBlocksFromSiteCalls() {
  ajaxEngine.registerRequest('ajaxGetBlocksFromSite','getBlocksFromSiteHelper');
  ajaxEngine.registerAjaxElement('siteBlocksHandler');
}
function ajaxGetBlocksFromSite() {
  ajaxEngine.sendRequest('ajaxGetBlocksFromSite');
}

function registerAjaxGetDetailsForPrimDatasetCalls() {
  ajaxEngine.registerRequest('ajaxGetDetailsForPrimDataset','getDetailsForPrimDataset');
  ajaxEngine.registerAjaxElement('results');
}
function ajaxGetDetailsForPrimDataset(dbsInst,primDataset) {
  var id=document.getElementById("results");
  id.className="show_cell";
  ajaxEngine.sendRequest('ajaxGetDetailsForPrimDataset',"dbsInst="+dbsInst,"primDataset="+primDataset);
}
function registerAjaxSummaryCalls() {
  ajaxEngine.registerRequest('getSummary','summary');
  ajaxEngine.registerAjaxElement('summary');
}
function getSummary() {
  var id=document.getElementById("summary");
  id.className="show_inline_off";
  ajaxEngine.sendRequest('getSummary');
}

function getPrimDatasets() {
  ajaxEngine.sendRequest('getPrimDatasets');
}
function getProvenance(id) {
  // in order to replace all occurence of pattern in a string we need to use regular expression
  // see http://www.i2d.com.au/members/~benmann/javascriptreplace.html
  dataset=id.replace(/___/g,"/");
  ajaxEngine.sendRequest('getProvenance',"dataset="+dataset);
}

