// Class which capture ajax response and handle it. I want to add sorting capability
// once response arrived. We put our reponse to "results" tag id found on internal HTML.
var GetDataUpdater=Class.create();
GetDataUpdater.prototype = {
   initialize: function() {
   },
   ajaxUpdate: function(ajaxResponse) {
     var responseHTML=RicoUtil.getContentAsString(ajaxResponse);
     /* we catch string from results with matching pattern, it contains JS code which to
      * register a new TreeView for parents. Once we found it we evaluate it to perform
      * java script action.
      */
/*
     if(responseHTML.search("<!-- parents")) {
        var p=responseHTML.split("<!-- parents");
        if(p[1].search("-->")) {
           var js = p[1].split("-->");
           eval(js[0]); 
        }
     }
*/
     showResultsMenu();
     hideWaitingMessage();
     var t=document.getElementById("results");
     t.innerHTML+=responseHTML;
     sortables_init();
     underlineLink('Both');
     if(responseHTML.search("checkbox")) {
        UnSelectAll();
     }
   }
}
var UserRegistrationUpdater=Class.create();
UserRegistrationUpdater.prototype = {
   initialize: function() {
   },
   ajaxUpdate: function(ajaxResponse) {
     var responseHTML=RicoUtil.getContentAsString(ajaxResponse);
     var t=document.getElementById('formAuthResults');
     if(responseHTML=='fail') {
        t.innerHTML='<p><span class="box_red">Ups, this login name is already in use.</span> Please choose another name.</p>';
     } else if(responseHTML=='wrong password') {
        t.innerHTML='<p><span class="box_red">Wrong password!!!</span> Please try again.</p>';
     } else {
        t.innerHTML='<p><span class="box_blue">Welcome '+responseHTML+'! Your authentication is completed. You may use your history at any time.</span></p>';
        setGreeting();
     }
   }
}
var HistoryUpdater=Class.create();
HistoryUpdater.prototype = {
   initialize: function(tab) {
     this.tab=tab
   },
   ajaxUpdate: function(ajaxResponse) {
     var responseHTML=RicoUtil.getContentAsString(ajaxResponse);
     var t=document.getElementById(this.tab+'History');
     if(this.tab=='session') {
        t.innerHTML=t.innerHTML+responseHTML;
     } else {
        t.innerHTML=responseHTML;
     }
   }
}
function registerAjaxHistoryCalls() {
  userRegUpdater = new UserRegistrationUpdater();
  ajaxEngine.registerRequest('ajaxCheckUser','checkUser');
  ajaxEngine.registerAjaxObject('historyUserName',userRegUpdater);
  sessionHistoryUpdater = new HistoryUpdater('session');
  ajaxEngine.registerRequest('ajaxHistory','history');
  ajaxEngine.registerAjaxObject('sessionHistory',sessionHistoryUpdater);
  ajaxEngine.registerRequest('ajaxHistorySearch','historySearch');
  ajaxEngine.registerAjaxElement('historySearchResults');
//  ajaxEngine.registerAjaxObject('historySearchResults',sessionHistoryUpdater);
  allHistoryUpdater = new HistoryUpdater('all');
  ajaxEngine.registerRequest('ajaxGetHistory','getHistory');
  ajaxEngine.registerAjaxObject('allHistory',allHistoryUpdater);
}
function ajaxCheckUser() {
  var user = GetCookie("DBSDD_username");
  var pass = GetCookie("DBSDD_password");
  if(user!='guest') {
     ajaxEngine.sendRequest('ajaxCheckUser','userName='+user,'password='+pass);
  }
}
function getUserName(iUser) {
  var userName;
  if(iUser) {
     userName=iUser;
  } else {
    // get user name and password from cookie
    userName=GetCookie("DBSDD_username");
  }
  return userName;
}
function getPassword(iPass) {
  var password;
  if(iPass) {
     password=iPass;
  } else {
     password=GetCookie("DBSDD_password");
  }
  return password
}
function ajaxHistory(action,iUser,iPass) {
  ajaxEngine.sendRequest('ajaxHistory','actionString='+action,'userName='+getUserName(iUser),'password='+getPassword(iPass));
}
function ajaxGetHistory(iUser,iPass,iLimit) {
  var limit;
  if(iLimit) {
     limit=iLimit;
  } else {
     limit=100;
  }
  ajaxEngine.sendRequest('ajaxGetHistory','userName='+getUserName(iUser),'password='+getPassword(iPass),'limit='+limit);
}
function getSelectedOption(iArr) {
  for(i=0;i<iArr.length;i++) {
     if(iArr[i].selected) {
        return iArr[i].value;
     }
  }
  return null;
}
function ajaxHistorySearch(iUser,iPass) {
  var iMonth = getSelectedOption(document.getElementById('in_hSearch_month'));
  var iYear  = getSelectedOption(document.getElementById('in_hSearch_year'));
  var oMonth = getSelectedOption(document.getElementById('out_hSearch_month'));
  var oYear  = getSelectedOption(document.getElementById('out_hSearch_year'));
  ajaxEngine.sendRequest('ajaxHistorySearch','iYear='+iYear,'iMonth='+iMonth,'oYear='+oYear,'oMonth='+oMonth,'userName='+getUserName(iUser),'password='+getPassword(iPass));
}
// AJAX registration for getDataHelper
function ajaxGetData(_dbs,_site,_app,_primD,_tier) {
  var sel;
  var dbs;
  if(_dbs) {
      dbs=_dbs;
  } else {
      sel=document.getElementById('dbsSelector');
      if(!sel) return;
      dbs=sel.value;
  }
  var site;
  if(_site) {
      site=_site;
  } else {
      sel=document.getElementById('siteSelector');
      if(!sel) return;
      site=sel.value;
  }
  var app;
  if(_app) {
      app=_app;
  } else {
      sel=document.getElementById('appSelector');
      if(!sel) return;
      app=sel.value;
  }
  var primD;
  if(_primD) {
      primD=_primD;
  } else {
      sel=document.getElementById('primSelector');
      if(!sel) return;
      primD=sel.value;
  }
  var tier;
  if(_tier) {
      tier=_tier;
  } else {
      sel=document.getElementById('tierSelector');
      if(!sel) return;
      tier=sel.value;
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
         ajaxEngine.sendRequest('ajaxGetDataFromSelection',"userSelection="+selList[i]);
         var actionHistory='<a href="javascript:showWaitingMessage();ajaxGetDataFromSelection(\''+selList[i]+'\')">data selection ('+selList[i]+')</a>';
         ajaxHistory(actionHistory);
//         action=action+selList[i]+',';
      }
  }
/*
  ajaxEngine.sendRequest('ajaxGetDataFromSelection',"userSelection="+selList);
  var parameters= action.substr(0,action.length-1);
  var parString = parameters.replace(",",", ");
  var actionHistory='<a href="javascript:showWaitingMessage();ajaxGetDataFromSelection(\''+parameters+'\')">data selection ('+parString+')</a>';
  ajaxHistory(actionHistory);
*/
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

function ajaxGetDetailsForPrimDataset(dbsInst,primDataset) {
  showResultsMenu();
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
//  var r=document.getElementById("results_menu");
//  r.className="show_inline";
  showResultsMenu();
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
     enableSel("appSelector");
     enableSel("primSelector");
     enableSel("tierSelector");
     var t=document.getElementById("navSelector");
     t.innerHTML="";
   }
}
function registerAjaxGenNavigatorMenuDictCalls() {
  navigatorUpdater = new NavigatorMenuDictUpdater();
  ajaxEngine.registerRequest('ajaxGenNavigatorMenuDict','genNavigatorMenuDict');
  ajaxEngine.registerAjaxObject('navigatorDict',navigatorUpdater);
}
function ajaxGenNavigatorMenuDict() {
  var dbsInst;
  var sel=document.getElementById("dbsSelector");
  if(sel) {
     for(i=0;i<sel.length;i++) {
         if(sel[i].selected) {
            dbsInst=sel[i].value;
            break;
         }
     }
     if(!dbsInst) {
         dbsInst="MCGlobal/Writer"; // default dbs instance
     }
  } else {
    dbsInst="MCGlobal/Writer"; // default dbs instance
  }
  // de-activate underneath menues (will be activated back once AJAX will arrive
  disableSel("appSelector");
  disableSel("primSelector");
  disableSel("tierSelector");
  showLoadingMessage('navSelector');
  ajaxEngine.sendRequest('ajaxGenNavigatorMenuDict','dbsInst='+dbsInst);
// original
//  ajaxEngine.sendRequest('ajaxGenNavigatorMenuDict');
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
  registerAjaxSelectAppsCalls();
  registerAjaxSelectPrimCalls();
  registerAjaxSelectTierCalls();
  registerAjaxObjectCalls();
  registerAjaxPrimaryDatasetsCalls();
  registerAjaxSummaryCalls();
  registerAjaxHistoryCalls();
  registerAjaxProvenanceCalls();
  registerAjaxProvenanceGraphCalls();
  registerAjaxAppConfigsCalls();
  registerAjaxGenNavigatorMenuDictCalls();
  registerAjaxGetDataDescriptionCalls();

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
     t.innerHTML+=responseHTML;
     // additional action can come here
   }
}
function registerAjaxProvenanceGraphCalls() {
  parentsGraphUpdater = new ParentsGraphUpdater();
  ajaxEngine.registerRequest('ajaxGenParentsGraph','getProvenanceForAllDatasets');
  ajaxEngine.registerAjaxObject('parents',parentsGraphUpdater);
}
function ajaxGenParentsGraphFromSelection() {
  uSelection=document.getElementsByName('userSelection');
  for(i=0;i<uSelection.length;i++) {
      if(uSelection[i].checked) {
         var val=uSelection[i].value;
         uSel = val.split("___");
         dbs  = uSel[0];
         primD= uSel[1];
         tier = uSel[2];
         app  = "/"+uSel[3]+"/"+uSel[4]+"/"+uSel[5]; // /ver/family/exe
         site = "*";
         ajaxGenParentsGraph(dbs,site,app,primD,tier);
      }
  }
}
function ajaxGenParentsGraph(_dbs,_site,_app,_primD,_tier) {
  var sel;
  var dbs;
  if(_dbs) {
      dbs=_dbs;
  } else {
      sel=document.getElementById('dbsSelector');
      if(!sel) return;
      dbs=sel.value;
  }
  var site;
  if(_site) {
      site=_site;
  } else {
      sel=document.getElementById('siteSelector');
      if(!sel) return;
      site=sel.value;
  }
  var app;
  if(_app) {
      app=_app;
  } else {
      sel=document.getElementById('appSelector');
      if(!sel) return;
      app=sel.value;
  }
  var primD;
  if(_primD) {
      primD=_primD;
  } else {
      sel=document.getElementById('primSelector');
      if(!sel) return;
      primD=sel.value;
  }
  var tier;
  if(_tier) {
      tier=_tier;
  } else {
      sel=document.getElementById('tierSelector');
      if(!sel) return;
      tier=sel.value;
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
      sel=document.getElementById('appSelector');
      if(!sel) return;
      app=sel.value
      // now we need to convert app to /family/executable/version
      parts = app.split('/'); // we got back /family/version/executable
      app='/'+parts[2]+'/'+parts[3]+'/'+parts[1];
  }
  ajaxEngine.sendRequest('ajaxGenAppConfigs',"appPath="+app);
  var action='<a href="javascript:showWaitingMessage();ajaxGenAppConfigs(\''+app+'\')">AppConfigs ('+app+')</a>';
  ajaxHistory(action);
}
function registerAjaxGetDataDescriptionCalls() {
    ajaxEngine.registerRequest('ajaxGetDataDescription','getDataDescription');
    ajaxEngine.registerAjaxElement('floatDataDescription');
}
function ajaxGetDataDescription(dbsInst,procD) {
    ajaxEngine.sendRequest('ajaxGetDataDescription','dbsInst='+dbsInst,'processedDataset='+procD)
    showTag('floatDataDescription');
}
function registerAjaxSelectAppsCalls() {
    ajaxEngine.registerRequest('ajaxSelectApps','selectApplications');
    ajaxEngine.registerAjaxElement('selectApps');
}
function ajaxSelectApps() {
    var sel=document.getElementById("dbsSelector");
    for(i=0;i<sel.length;i++) {
        if(sel[i].selected) {
           ajaxEngine.sendRequest('ajaxSelectApps','dbsInst='+sel[i].value);
           return;
        }
    }
}
function registerAjaxSelectPrimCalls() {
    ajaxEngine.registerRequest('ajaxSelectPrim','selectPrimaryDatasets');
    ajaxEngine.registerAjaxElement('selectPrim');
}
function ajaxSelectPrim() {
    var sel=document.getElementById("dbsSelector");
    var dbsInst;
    for(i=0;i<sel.length;i++) {
        if(sel[i].selected) {
           dbsInst=sel[i].value;
           break;
        }
    }
    var sel=document.getElementById("appSelector");
    var app;
    for(i=0;i<sel.length;i++) {
        if(sel[i].selected) {
           app=sel[i].value;
           ajaxEngine.sendRequest('ajaxSelectPrim','dbsInst='+dbsInst,'app='+app);
           return;
        }
    }
}
function registerAjaxSelectTierCalls() {
    ajaxEngine.registerRequest('ajaxSelectTier','selectDataTiers');
    ajaxEngine.registerAjaxElement('selectTier');
}
function ajaxSelectTier() {
    var sel=document.getElementById("dbsSelector");
    var dbsInst;
    for(i=0;i<sel.length;i++) {
        if(sel[i].selected) {
           dbsInst=sel[i].value;
           break;
        }
    }
    var sel=document.getElementById("appSelector");
    var app;
    for(i=0;i<sel.length;i++) {
        if(sel[i].selected) {
           app=sel[i].value;
           break;
        }
    }
    var sel=document.getElementById("primSelector");
    var prim;
    for(i=0;i<sel.length;i++) {
        if(sel[i].selected) {
           prim=sel[i].value;
           ajaxEngine.sendRequest('ajaxSelectTier','dbsInst='+dbsInst,'app='+app,'prim='+prim);
           return;
        }
    }
}
/*
  Below you can find tree updater class and associative AJAX methods.
  AJAX returns pure JavaScript code which add a new node to existing tree.
  The registerTreeView needs to be called everytime when new TreeView has to be created.
  The tree will be placed to "treeDiv" tag in HTML code.
*/
var TreeNode=Class.create();
TreeNode.prototype = {
   initialize: function(id,name) {
     this.id=id;
     this.name=name;
   }
}
var TreeUpdater=Class.create();
TreeUpdater.prototype = {
   initialize: function() {
     this.tree = new YAHOO.widget.TreeView("treeDiv");
     this.root = this.tree.getRoot();
     this.nodes = new Array();
   },
   ajaxUpdate: function(ajaxResponse) {
     var responseHTML=RicoUtil.getContentAsString(ajaxResponse);
     eval(responseHTML);
     this.tree.draw();
     this.tree.expandAll();
   }
}
function ajaxAddTreeElement(parent,node) {
   //alert('from ajaxAddTreeElement node='+node+' parent='+parent);
   ajaxEngine.sendRequest('ajaxAddTreeElement','parent='+parent,'node='+node);
}
function registerTreeView() {
   var id = document.getElementById("parents");
   id.innerHTML='Parents tree. Please click on a node to see its parents.<div id="treeDiv"></div>';
   ajaxEngine.registerRequest('ajaxAddTreeElement','addTreeElement');
   var updater = new TreeUpdater();
   ajaxEngine.registerAjaxObject('treeViewInfo',updater);
}

