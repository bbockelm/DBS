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
function ajaxGetData(_dbs,_site,_app,_primD,_tier) {
  var dbs;
  if(_dbs) {
      dbs=_dbs;
  } else {
      dbs=document.getElementById('dbsSelector').value;
  }
  var site;
  if(_site) {
      site=_site;
  } else {
      site=document.getElementById('siteSelector').value;
  }
  var app;
  if(_app) {
      app=_app;
  } else {
      app=document.getElementById('appSelector').value;
  }
  var primD;
  if(_primD) {
      primD=_primD;
  } else {
      primD=document.getElementById('primSelector').value;
  }
  var tier;
  if(_tier) {
      tier=_tier;
  } else {
      tier=document.getElementById('tierSelector').value;
  }
  ajaxEngine.sendRequest('ajaxGetData',"dbsInst="+dbs,"site="+site,"app="+app,"primD="+primD,"tier="+tier);
  var action='<a href="javascript:showWaitingMessage();ajaxGetData(\''+dbs+'\',\''+site+'\',\''+app+'\',\''+primD+'\',\''+tier+'\')">Navigator ('+dbs+','+site+','+app+','+primD+','+tier+')</a>';
  ajaxHistory(action);
}
function ajaxGetDataFromSelection(iParamString) {
  var uSelection;
  if(iParamString) { // we can pass a string of parameters, e.g. A,B,C
      uSelection=iParamString.split(",");
  } else {
      uSelection=document.getElementsByName('userSelection');
  }
  var len=0;
  for(i=0;i<uSelection.length;i++) {
      if(uSelection[i].checked || iParamString) {
         len=len+1;
      }
  }
  var selList = new Array(len);
  var action = '';
  for(i=0;i<uSelection.length;++i) {
      if(uSelection[i].checked || iParamString) {
         if(iParamString) {
            selList[i]=uSelection[i];
         } else {
            selList[i]=uSelection[i].value;
         }
         action=action+selList[i]+',';
      }
  }
  ajaxEngine.sendRequest('ajaxGetDataFromSelection',"userSelection="+selList);
  var parameters= action.substr(0,action.length-1);
  var parString = parameters.replace(",",", ");
  var actionHistory='<a href="javascript:showWaitingMessage();ajaxGetDataFromSelection(\''+parameters+'\')">data selection ('+parString+')</a>';
  ajaxHistory(actionHistory);
}

// AJAX registration for search
function ajaxSearch(iWords) {
  var keywords;
  if(iWords) {
      keywords=iWords;
  } else {
      keywords=document.getElementById('keywordSelector').value;
  }
  ajaxEngine.sendRequest('ajaxSearch',"keywords="+keywords);
  var action='<a href="javascript:showWaitingMessage();ajaxSearch(\''+keywords+'\')">Keyword search ('+keywords+')</a>';
  ajaxHistory(action);
}

// AJAX registration for site search
function ajaxSiteSearch(_dbs,_site) {
  var dbsInst;
  if(_dbs) {
      dbsInst=_dbs;
  } else {
      dbsInst=document.getElementById('form2_dbsSelector').value;
  }
  var site;
  if(_site) {
      site=_site;
  } else {
      site=document.getElementById('form2_siteSelector').value;
  }
  ajaxEngine.sendRequest('ajaxSiteSearch',"dbsInst="+dbsInst,"site="+site);
  var action='<a href="javascript:showWaitingMessage();ajaxSiteSearch(\''+dbsInst+'\',\''+site+'\')">site search ('+dbsInst+','+site+')</a>';
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
