// Class which capture ajax response and handle it. I want to add sorting capability
// once response arrived. We put our reponse to "results" tag id found on internal HTML.
var GetDataUpdater=Class.create();
GetDataUpdater.prototype = {
   initialize: function() {
   },
   ajaxUpdate: function(ajaxResponse) {
     var responseHTML=RicoUtil.getContentAsString(ajaxResponse);
     var r=document.getElementById("results_menu");
     r.className="show_table";
     var t=document.getElementById("results");
     t.innerHTML=responseHTML;
     sortables_init();
     underlineLink('Summary');
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
function registerAjaxHistoryCalls() {
    historyUpdater = new HistoryUpdater();
    ajaxEngine.registerRequest('ajaxHistory','history');
    ajaxEngine.registerAjaxObject('userHistory',historyUpdater);
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
function registerAjaxObjectCalls() {
    getDataUpdater = new GetDataUpdater();
    ajaxEngine.registerRequest('ajaxGetData','getData');
    ajaxEngine.registerRequest('ajaxSearch','search');
    ajaxEngine.registerRequest('ajaxSiteSearch','getBlocksFromSite');
    ajaxEngine.registerRequest('ajaxGetDataFromSelection','getDataFromSelection');

    // new stuff
    ajaxEngine.registerRequest('ajaxGetDetailsForPrimDataset','getDetailsForPrimDataset');
    ajaxEngine.registerRequest('ajaxGetDatasetContent','getDatasetContent');
    ajaxEngine.registerRequest('ajaxGetDatasetsFromApplication','getDatasetsFromApplication');

    ajaxEngine.registerAjaxObject('results',getDataUpdater);
}

function registerAjaxGetBlocksFromSiteCalls() {
  ajaxEngine.registerRequest('ajaxGetBlocksFromSite','getBlocksFromSiteHelper');
  ajaxEngine.registerAjaxElement('siteBlocksHandler');
}
function ajaxGetBlocksFromSite() {
  ajaxEngine.sendRequest('ajaxGetBlocksFromSite');
}

//function registerAjaxGetDetailsForPrimDatasetCalls() {
//  ajaxEngine.registerRequest('ajaxGetDetailsForPrimDataset','getDetailsForPrimDataset');
//  ajaxEngine.registerAjaxElement('results');
//}
function ajaxGetDetailsForPrimDataset(dbsInst,primDataset) {
  var r=document.getElementById("results_menu");
  r.className="show_inline";
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
  var action='<a href="javascript:showMenu(\'Summary\');getSummary()">Summary</a>';
  ajaxHistory(action);
}

function getPrimDatasets_old() {
  ajaxEngine.sendRequest('getPrimDatasets');
  var action='<a href="javascript:showMenu(\'Datasets\');getPrimDatasets()">Get all primary datasets</a>';
  ajaxHistory(action);
}
var DBSInfoUpdater=Class.create();
DBSInfoUpdater.prototype = {
   initialize: function(tag) {
     this.tag=tag;
   },
   ajaxUpdate: function(ajaxResponse) {
     var responseHTML=RicoUtil.getContentAsString(ajaxResponse);
     var r=document.getElementById("_"+this.tag);
     r.className="td_menu_lavender_box";
     var t=document.getElementById(this.tag);
     t.innerHTML=responseHTML;
     // additional action can come here
   }
}
function getDbsInfo(dbsInst,dbsArr) {
  var arr = new Array();
  arr[0]='dbs_prim';
  arr[1]='dbs_proc';
  arr[2]='dbs_apps';
  for(i=0;i<arr.length;i++) {
      var id=document.getElementById(arr[i]);
      id.className="hide";
  }
  showResMenu('dbs_prim',arr);
  showLoadingMessage('dbs_prim');
  registerAjaxPrimaryDatasetsCalls();
  registerAjaxProcessedDatasetsCalls();
  registerAjaxApplicationsCalls();
  // get short name for dbs instance
  var shortName=dbsInst.split("/")[0];
  if(dbsInst.match('fanfani')) {
    shortName = shortName+'_fanfani';
  }
  for(i=0;i<dbsArr.length;i++) {
      if (dbsArr[i]==shortName) {
          var id=document.getElementById('dbsInst_'+shortName);
          id.className="td_right";
      } else {
          var id=document.getElementById('dbsInst_'+dbsArr[i]);
          id.className="show_cell";
      }
  }
  var id=document.getElementById("dbsInst_table");
  id.className="show_inline";
  var id=document.getElementById("dbs_info");
  id.className="show_table";
  ajaxGetPrimaryDatasets(dbsInst);
  ajaxGetProcessedDatasets(dbsInst);
  ajaxGetApplications(dbsInst);
}
function ajaxGetPrimaryDatasets(dbsInst) {
  ajaxEngine.sendRequest('ajaxGetPrimaryDatasets',"dbsInst="+dbsInst);
  var action='<a href="javascript:showMenu(\'DBSinfo\');ajaxGetPrimaryDatasets(\''+dbsInst+'\')">Get all primary datasets (\''+dbsInst+'\')</a>';
  ajaxHistory(action);
}
function registerAjaxPrimaryDatasetsCalls() {
    ajaxEngine.registerRequest('ajaxGetPrimaryDatasets','getPrimaryDatasets');
    ajaxEngine.registerAjaxElement('dbs_prim');
}
function ajaxGetProcessedDatasets(dbsInst) {
    ajaxEngine.sendRequest('ajaxGetProcessedDatasets',"dbsInst="+dbsInst);
    var action='<a href="javascript:showMenu(\'DBSinfo\');ajaxGetProcessedDatasets(\''+dbsInst+'\')">Get all processed datasets (\''+dbsInst+'\')</a>';
    ajaxHistory(action);
}
function registerAjaxProcessedDatasetsCalls() {
    dbsInfoUpdater = new DBSInfoUpdater("dbs_proc");
    ajaxEngine.registerRequest('ajaxGetProcessedDatasets','getProcessedDatasets');
    ajaxEngine.registerAjaxObject('dbs_proc',dbsInfoUpdater);
}
function ajaxGetApplications(dbsInst) {
    ajaxEngine.sendRequest('ajaxGetApplications',"dbsInst="+dbsInst);
    var action='<a href="javascript:showMenu(\'DBSinfo\');ajaxGetApplications(\''+dbsInst+'\')">Get all applications (\''+dbsInst+'\')</a>';
    ajaxHistory(action);
}
function registerAjaxApplicationsCalls() {
    dbsInfoUpdater = new DBSInfoUpdater("dbs_apps");
    ajaxEngine.registerRequest('ajaxGetApplications','getApplications');
    ajaxEngine.registerAjaxObject('dbs_apps',dbsInfoUpdater);
}
function ajaxGetDatasetContent(dbsInst,dataset) {
    ajaxEngine.sendRequest('ajaxGetDatasetContent',"dbsInst="+dbsInst,"dataset="+dataset);
    var action='<a href="javascript:showMenu(\'DBSinfo\');ajaxGetDatasetContent(\''+dbsInst+'\')">Get dataset content (\''+dbsInst+'\',\''+dataset+'\')</a>';
    ajaxHistory(action);
}
//function registerAjaxDatasetContentCalls() {
//    ajaxEngine.registerRequest('ajaxGetDatasetContent','getDatasetContent');
//    ajaxEngine.registerAjaxElement('results');
//}
//function registerAjaxGetDatasetsFromApplicationCalls() {
//  ajaxEngine.registerRequest('ajaxGetDatasetsFromApplication','getDatasetsFromApplication');
//  ajaxEngine.registerAjaxElement('results');
//}
function ajaxGetDatasetsFromApplication(dbsInst,appPath) {
  var r=document.getElementById("results_menu");
  r.className="show_inline";
  var id=document.getElementById("results");
  id.className="show_cell";
  ajaxEngine.sendRequest('ajaxGetDatasetsFromApplication',"dbsInst="+dbsInst,"appPath="+appPath);
}

function getProvenance(id) {
  // in order to replace all occurence of pattern in a string we need to use regular expression
  // see http://www.i2d.com.au/members/~benmann/javascriptreplace.html
  dataset=id.replace(/___/g,"/");
  ajaxEngine.sendRequest('getProvenance',"dataset="+dataset);
}

var NavigatorMenuDictUpdater=Class.create();
NavigatorMenuDictUpdater.prototype = {
   initialize: function() {
   },
   ajaxUpdate: function(ajaxResponse) {
     var responseHTML=RicoUtil.getContentAsString(ajaxResponse);
     var t=document.getElementById("navigatorDict");
     eval(responseHTML);
     var dbs = document.getElementById("dbsSelector");
     updateLayer(dbs);
   }
}
function registerAjaxGenNavigatorMenuDictCalls() {
  navigatorUpdater = new NavigatorMenuDictUpdater();
  ajaxEngine.registerRequest('ajaxGenNavigatorMenuDict','genNavigatorMenuDict');
  ajaxEngine.registerAjaxObject('navigatorDict',navigatorUpdater);
}
function ajaxGenNavigatorMenuDict() {
  ajaxEngine.sendRequest('ajaxGenNavigatorMenuDict');
}
var SiteMenuDictUpdater=Class.create();
SiteMenuDictUpdater.prototype = {
   initialize: function() {
   },
   ajaxUpdate: function(ajaxResponse) {
     var responseHTML=RicoUtil.getContentAsString(ajaxResponse);
     var t=document.getElementById("siteMenuDict");
     eval(responseHTML);
     var dbs = document.getElementById("form2_dbsSelector");
     updateSites(dbs);
   }
}
function registerAjaxGenSiteMenuDictCalls() {
  siteDictUpdater = new SiteMenuDictUpdater();
  ajaxEngine.registerRequest('ajaxGenSiteMenuDict','genSiteMenuDict');
  ajaxEngine.registerAjaxObject('siteMenuDict',siteDictUpdater);
}
function ajaxGenSiteMenuDict() {
  ajaxEngine.sendRequest('ajaxGenSiteMenuDict');
}

// method which should be called on page load, to initialize all AJAX calls
function ajaxInit() {
  registerAjaxGenNavigatorMenuDictCalls();
  ajaxGenNavigatorMenuDict();
//  registerAjaxGenSiteMenuDictCalls();
//  ajaxGenSiteMenuDict();
}

// Class which capture ajax response and handle it. 
// We put our reponse to "parents" tag id found on internal HTML.
var ParentsGraphUpdater=Class.create();
ParentsGraphUpdater.prototype = {
   initialize: function() {
   },
   ajaxUpdate: function(ajaxResponse) {
     var responseHTML=RicoUtil.getContentAsString(ajaxResponse);
     var r=document.getElementById("_parents");
     r.className="td_menu_lavender_box";
     var t=document.getElementById("parents");
     t.innerHTML=responseHTML;
     // additional action can come here
   }
}
function registerAjaxProvenanceGraphCalls() {
  parentsGraphUpdater = new ParentsGraphUpdater();
  ajaxEngine.registerRequest('ajaxGenParentsGraph','getProvenanceForAllDatasets');
  ajaxEngine.registerAjaxObject('parents',parentsGraphUpdater);
}
function ajaxGenParentsGraph(iParam) {
  var dbs, _dbs, site, _site, app, _app, primD, _primD, tier, _tier;
  if(iParam) {
     uSelection=iParam.split(",");
     _dbs  = uSelection[0];
     _primD= uSelection[1];
     _tier = uSelection[2];
     _app  = "/"+uSelection[4]+"/"+uSelection[5]+"/"+uSelection[3]; // /family/exe/ver
     _site = "*";
  }
  if(_dbs) {
      dbs=_dbs;
  } else {
      dbs=document.getElementById('dbsSelector').value;
  }
  if(_site) {
      site=_site;
  } else {
      site=document.getElementById('siteSelector').value;
  }
  if(_app) {
      app=_app;
  } else {
      app=document.getElementById('appSelector').value;
  }
  if(_primD) {
      primD=_primD;
  } else {
      primD=document.getElementById('primSelector').value;
  }
  if(_tier) {
      tier=_tier;
  } else {
      tier=document.getElementById('tierSelector').value;
  }
  ajaxEngine.sendRequest('ajaxGenParentsGraph',"dbsInst="+dbs,"site="+site,"app="+app,"primD="+primD,"tier="+tier);
  var action='<a href="javascript:showWaitingMessage();ajaxGenParentsGraph(\''+dbs+'\',\''+site+'\',\''+app+'\',\''+primD+'\',\''+tier+'\')">ParentGraph ('+dbs+','+site+','+app+','+primD+','+tier+')</a>';
  ajaxHistory(action);
}
function ajaxGenParentsGraph_orig(_dbs,_site,_app,_primD,_tier) {
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
  ajaxEngine.sendRequest('ajaxGenParentsGraph',"dbsInst="+dbs,"site="+site,"app="+app,"primD="+primD,"tier="+tier);
  var action='<a href="javascript:showWaitingMessage();ajaxGenParentsGraph(\''+dbs+'\',\''+site+'\',\''+app+'\',\''+primD+'\',\''+tier+'\')">ParentGraph ('+dbs+','+site+','+app+','+primD+','+tier+')</a>';
  ajaxHistory(action);
}
// keep this for first implementation of provenance calls
function registerAjaxProvenanceCalls() {
    ajaxEngine.registerRequest('getProvenance','getDatasetProvenance');
    ajaxEngine.registerAjaxElement('parentGraph');
}
// Class which capture ajax response and handle it. 
// We put our reponse to "appConfigs" tag id found on internal HTML.
var AppConfigsUpdater=Class.create();
AppConfigsUpdater.prototype = {
   initialize: function() {
   },
   ajaxUpdate: function(ajaxResponse) {
     var responseHTML=RicoUtil.getContentAsString(ajaxResponse);
     var r=document.getElementById("_appConfigs");
     r.className="td_menu_lavender_box";
     var t=document.getElementById("appConfigs");
     t.innerHTML=responseHTML;
     // additional action can come here
   }
}
function registerAjaxAppConfigsCalls() {
  appConfigsUpdater = new AppConfigsUpdater();
  ajaxEngine.registerRequest('ajaxGenAppConfigs','getAppConfigs');
  ajaxEngine.registerAjaxObject('appConfigs',appConfigsUpdater);
}
function ajaxGenAppConfigs(_app) {
  var app;
  if(_app) {
      app=_app;
  } else {
      app=document.getElementById('appSelector').value;
      // now we need to convert app to /family/executable/version
      parts = app.split('/'); // we got back /family/version/executable
      app='/'+parts[2]+'/'+parts[3]+'/'+parts[1];
  }
  ajaxEngine.sendRequest('ajaxGenAppConfigs',"appPath="+app);
  var action='<a href="javascript:showWaitingMessage();ajaxGenAppConfigs(\''+app+'\')">AppConfigs ('+app+')</a>';
  ajaxHistory(action);
}
