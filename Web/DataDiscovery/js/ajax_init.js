function registerAjaxSummaryCalls() {
    ajaxEngine.registerRequest('getSummary','summary');
    ajaxEngine.registerAjaxElement('summary');
}
function registerAjaxPrimDatasetsCalls() {
    // second argument is DBSDataDiscoveryServer:getAllPrimaryDatasets method
    ajaxEngine.registerRequest('getPrimDatasets','getAllPrimaryDatasets');
    ajaxEngine.registerAjaxElement('primDatasets');
}
function getPrimDatasets() {
  var id0=document.getElementById("datasetsPanel0");
  id0.className="hide";
  var id=document.getElementById("primDatasets");
  id.className="show_inline_off";
  ajaxEngine.sendRequest('getPrimDatasets');
}
function getSummary() {
  var id=document.getElementById("summary");
  id.className="show_inline_off";
  ajaxEngine.sendRequest('getSummary');
}

function getProvenance(dataset) {
  var id=document.getElementById(dataset);
  id.className="show_inline_off";
  ajaxEngine.sendRequest('getProvenance',"dataset="+dataset);
}

