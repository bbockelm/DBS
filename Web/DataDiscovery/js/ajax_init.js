// Class which capture ajax response and handle it. I want to add sorting capability
// once response arrived. We put our reponse to "results" tag id found on internal HTML.
var GetDataUpdater=Class.create();
GetDataUpdater.prototype = {
   initialize: function() {
   },
   ajaxUpdate: function(ajaxResponse) {
     var responseHTML=RicoUtil.getContentAsString(ajaxResponse);
     var r=document.getElementById("results_menu");
     r.className="show_inline";
     var t=document.getElementById("results");
     t.innerHTML=responseHTML;
     sortables_init();
     if(responseHTML.search("checkbox")) {
        UnSelectAll();
     }
   }
}
var HistoryUpdater=Class.create();
HistoryUpdater.prototype = {
   initialize: function() {
   },
   ajaxUpdate: function(ajaxResponse) {
     var responseHTML=RicoUtil.getContentAsString(ajaxResponse);
     var t=document.getElementById("userHistory");
     t.innerHTML=t.innerHTML+responseHTML;
   }
}
function ajaxHistory(action) {
  ajaxEngine.sendRequest('ajaxHistory','actionString='+action);
}
// AJAX registration for getDataHelper
function ajaxGetData() {
  var dbs=document.getElementById('dbsSelector').value;
  var site=document.getElementById('siteSelector').value;
  var app=document.getElementById('appSelector').value;
  var primD=document.getElementById('primSelector').value;
  var tier=document.getElementById('tierSelector').value;
    
  ajaxEngine.sendRequest('ajaxGetData',"dbsInst="+dbs,"site="+site,"app="+app,"primD="+primD,"tier="+tier);
  var action='Navigator ('+dbs+','+site+','+app+','+primD+','+tier+')';
  ajaxHistory(action);
}
function ajaxGetDataFromSelection() {
  var uSelection=document.getElementsByName('userSelection');
  var len=0;
  for(i=0;i<uSelection.length;i++) {
      if(uSelection[i].checked) {
         len=len+1;
      }
  }
  var selList = new Array(len);
  var action = 'Site (';
  for(i=0;i<uSelection.length;i++) {
      if(uSelection[i].checked) {
         selList[i]=uSelection[i].value;
         action=action+selList[i]+',';
      }
  }
  ajaxEngine.sendRequest('ajaxGetDataFromSelection',"userSelection="+selList);
  var action=action+')';
  ajaxHistory(action);
}
/*
function registerAjaxGetDataCalls() {
  getDataUpdater = new GetDataUpdater();
  ajaxEngine.registerRequest('ajaxGetData','getDataHelper');
//  ajaxEngine.registerAjaxElement('results');
  ajaxEngine.registerAjaxObject('results',getDataUpdater);
}
*/

// AJAX registration for search
function ajaxSearch() {
  var keywords=document.getElementById('keywordSelector').value;
  ajaxEngine.sendRequest('ajaxSearch',"keywords="+keywords);
  var action='Keyword search ('+keywords+')';
  ajaxHistory(action);
}
/*
function registerAjaxSearchCalls() {
  getDataUpdater = new GetDataUpdater();
  ajaxEngine.registerRequest('ajaxSearch','search');
//  ajaxEngine.registerAjaxElement('results');
  ajaxEngine.registerAjaxObject('results',getDataUpdater);
}
*/

// AJAX registration for site search
function ajaxSiteSearch() {
  var dbsInst=document.getElementById('form2_dbsSelector').value;
  var site=document.getElementById('form2_siteSelector').value;
  ajaxEngine.sendRequest('ajaxSiteSearch',"dbsInst="+dbsInst,"site="+site);
  var action='site search ('+dbsInst+','+site+')';
  ajaxHistory(action);
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
  ajaxHistory('Summary');
}

function getPrimDatasets() {
  ajaxEngine.sendRequest('getPrimDatasets');
  ajaxHistory('Datasets');
}
function getProvenance(id) {
  // in order to replace all occurence of pattern in a string we need to use regular expression
  // see http://www.i2d.com.au/members/~benmann/javascriptreplace.html
  dataset=id.replace(/___/g,"/");
  ajaxEngine.sendRequest('getProvenance',"dataset="+dataset);
}
