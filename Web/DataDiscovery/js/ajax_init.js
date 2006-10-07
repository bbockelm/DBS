function registerAjaxSummaryCalls() {
    ajaxEngine.registerRequest('getSummary','summary');
    ajaxEngine.registerAjaxElement('summary');
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

