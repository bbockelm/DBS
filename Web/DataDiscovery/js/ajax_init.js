// Class which capture ajax response and handle it. I want to add sorting capability
// once response arrived. We put our reponse to "results" tag id found on internal HTML.
var GetDataUpdater=Class.create();
GetDataUpdater.prototype = {
   initialize: function(tag,mode,nores) {
      if(tag) {
         this.tag=tag;
      } else {
         this.tag='results';
      }
      if(mode && mode=='update') {
         this.mode=mode;
      } else {
         this.mode='replace';
      }
      if(nores) {
         this.nores=1;
      } else {
         this.nores=0;
      }
   },
   ajaxUpdate: function(ajaxResponse) {
     var responseHTML=RicoUtil.getContentAsString(ajaxResponse);
     var copyHTML=responseHTML;
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
     if(!this.nores) {
        showResultsMenu();
     }
     var t=document.getElementById(this.tag);
     if(this.mode=='update') {
        t.innerHTML+=responseHTML;
     } else {
        t.innerHTML=responseHTML;
     }
     // parse response and search for any JavaScript code there, if found execute it.
     var jsCode = SearchForJSCode(responseHTML);
     if(jsCode) {
        eval(jsCode);
     }
//     underlineLink('Summary');
     //sortables_init();
//     if(responseHTML.search("checkbox")) {
//        UnSelectAll();
//     }
     HideWheel("__"+this.tag);
     if(this.tag=='results') {
        hideWaitingMessage();
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
function GetSessionHistory() {
  var id=document.getElementById('sessionHistory');
  if (id) {
      ajaxFloatBox('Session history',id.innerHTML,'float_help_box');
  }
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
function getDataFromSelectors(_dbs,_site,_app,_primD,_tier) {
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
  var arr = new Array(dbs,site,app,primD,tier);
  return arr;
}
// AJAX registration 
function ajaxGetRuns(_dbs,_site,_app,_primD,_tier,proc) {
  ShowWheel("__runs");
//  showLoadingMessage('runs');
  var arr  = getDataFromSelectors(_dbs,_site,_app,_primD,_tier)
  if(!arr) return;
  var dbs  = arr[0];
  var site = arr[1];
  var app  = arr[2];
  var primD= arr[3];
  var tier = arr[4];
  if(!proc) {proc="*";}
  ajaxEngine.sendRequest('ajaxGetRuns',"dbsInst="+dbs,"site="+site,"app="+app,"primD="+primD,"tier="+tier,"proc="+proc);
  var action='<a href="javascript:ResetAllResults();ajaxGetRuns(\''+dbs+'\',\''+site+'\',\''+app+'\',\''+primD+'\',\''+tier+'\',\''+proc+'\')">Navigator ('+dbs+','+site+','+app+','+primD+','+tier+','+proc+')</a>';
  ajaxHistory(action);
}
// AJAX registration 
function ajaxGetDbsData(_dbs,_site,_app,_primD,_tier,proc) {
  ShowWheel("__results_dbs");
//  showLoadingMessage('results_dbs');
  var arr  = getDataFromSelectors(_dbs,_site,_app,_primD,_tier)
  if(!arr) return;
  var dbs  = arr[0];
  var site = arr[1];
  var app  = arr[2];
  var primD= arr[3];
  var tier = arr[4];
  if(!proc) {proc="*";}
  ajaxEngine.sendRequest('ajaxGetDbsData',"dbsInst="+dbs,"site="+site,"app="+app,"primD="+primD,"tier="+tier,"proc="+proc);
  var action='<a href="javascript:ResetAllResults();ajaxGetDbsData(\''+dbs+'\',\''+site+'\',\''+app+'\',\''+primD+'\',\''+tier+'\',\''+proc+'\')">Navigator ('+dbs+','+site+','+app+','+primD+','+tier+','+proc+')</a>';
  ajaxHistory(action);
}
// AJAX registration
function SendAjaxCalls(dbs,site,app,prim,tier,proc) {
  ajaxGetData(dbs,site,app,prim,tier,proc);
  ajaxGetDbsData(dbs,site,app,prim,tier,proc);
  ajaxGetRuns(dbs,site,app,prim,tier,proc);
}
function ajaxGetData(_dbs,_site,_app,_primD,_tier,proc) {
  ShowWheel("__results");
//  showLoadingMessage('results','Wait');
  var arr  = getDataFromSelectors(_dbs,_site,_app,_primD,_tier)
  if(!arr) return;
  var dbs  = arr[0];
  var site = arr[1];
  var app  = arr[2];
  var primD= arr[3];
  var tier = arr[4];
  if(!proc) {proc="*";}
  // Set Cookies about current snapshot of data
  SetCookie('dbsInst',dbs);
  SetCookie('site',site);
  SetCookie('app',app);
  SetCookie('primD',primD);
  SetCookie('tier',tier);
  SetCookie('proc',proc);

  ajaxEngine.sendRequest('ajaxGetData',"dbsInst="+dbs,"site="+site,"app="+app,"primD="+primD,"tier="+tier,"proc="+proc,'hist='+GetTagContent('navBar'));
  var action='<a href="javascript:ResetAllResults();ajaxGetData(\''+dbs+'\',\''+site+'\',\''+app+'\',\''+primD+'\',\''+tier+'\',\''+proc+'\')">Navigator ('+dbs+','+site+','+app+','+primD+','+tier+')</a>';
  ajaxHistory(action);
  // invoke next chunk of data
  ajaxNextGetData(dbs,site,app,primD,tier,proc,1);
}
function ajaxNextGetData(dbs,site,app,primD,tier,proc,idx) {
  var id=document.getElementById('results_response'+idx);
  if(!id) {
//  alert('ajaxGetData idx='+idx);
  ajaxEngine.sendRequest('ajaxGetData',"dbsInst="+dbs,"site="+site,"app="+app,"primD="+primD,"tier="+tier,"proc="+proc,'_idx='+idx);
  }

  var id=document.getElementById('results_dbs_response'+idx);
  if(!id) {
//  alert('ajaxGetiDbsData idx='+idx);
  ajaxEngine.sendRequest('ajaxGetDbsData',"dbsInst="+dbs,"site="+site,"app="+app,"primD="+primD,"tier="+tier,"proc="+proc,'_idx='+idx);
  }

  var id=document.getElementById('runs_response'+idx);
  if(!id) {
//  alert('ajaxGetRuns idx='+idx);
  ajaxEngine.sendRequest('ajaxGetRuns',"dbsInst="+dbs,"site="+site,"app="+app,"primD="+primD,"tier="+tier,"proc="+proc,'_idx='+idx);
  }
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
      }
  }
}

// AJAX registration for search
function ajaxKeywordSearch(_dbs) {
  var dbsInst;
  if(_dbs) {
      dbsInst=_dbs;
  } else {
      dbsInst=document.getElementById('keywordSearch_dbsSelector').value;
  }
  var keywords='';
  var algo =GetValue('kw_algoSelector');
  var prim =GetValue('kw_primSelector');
  var proc =GetValue('kw_procSelector');
  var tier =GetValue('kw_tierSelector');
  var runs =GetValue('kw_runsSelector');
  if(algo) {keywords=keywords+'algo:'+algo+'___';}
  if(prim) {keywords=keywords+'prim:'+prim+'___';}
  if(proc) {keywords=keywords+'proc:'+proc+'___';}
  if(tier) {keywords=keywords+'tier:'+tier+'___';}
  if(runs) {keywords=keywords+'runs:'+runs+'___';}
  ajaxEngine.sendRequest('ajaxKeywordSearch',"dbsInst="+dbsInst,"keywords="+keywords);
  var action='<a href="javascript:ResetAllResults();ajaxKeywordSearch(\''+dbsInst+'\',\''+keywords+'\')">Keyword search ('+dbsInst+','+keywords+')</a>';
  ajaxHistory(action);
}

function ajaxSearch(_dbs,iWords) {
  var dbsInst;
  if(_dbs) {
      dbsInst=_dbs;
  } else {
      dbsInst=document.getElementById('keywordSearch_dbsSelector').value;
  }
  var keywords;
  if(iWords) {
      keywords=iWords;
  } else {
      keywords=document.getElementById('keywordSelector').value;
  }
  ajaxEngine.sendRequest('ajaxSearch',"dbsInst="+dbsInst,"keywords="+keywords);
  var action='<a href="javascript:ResetAllResults();ajaxSearch(\''+dbsInst+'\',\''+keywords+'\')">Keyword search ('+dbsInst+','+keywords+')</a>';
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
  var action='<a href="javascript:ResetAllResults();ajaxSiteSearch(\''+dbsInst+'\',\''+site+'\')">site search ('+dbsInst+','+site+')</a>';
  ajaxHistory(action);
}
function registerAjaxObjectCalls() {
    getDataUpdater = new GetDataUpdater('results','update');
    ajaxEngine.registerRequest('ajaxGetData','getData');
    ajaxEngine.registerRequest('ajaxSearch','search');
    ajaxEngine.registerRequest('ajaxGetDataFromSelection','getDataFromSelection');
    ajaxEngine.registerRequest('ajaxGetDummyData','dummy');
    ajaxEngine.registerAjaxObject('results',getDataUpdater);

    ajaxEngine.registerRequest('ajaxKeywordSearch','search');
    kwUpdater = new GetDataUpdater('results_kw','replace','noResultsMenu');
    ajaxEngine.registerAjaxObject('results_kw',kwUpdater);

    ajaxEngine.registerRequest('ajaxSiteSearch','getFileBlocks');
    siteUpdater = new GetDataUpdater('results','replace','noResultsMenu');
    ajaxEngine.registerAjaxObject('results_site',siteUpdater);

    ajaxEngine.registerRequest('ajaxGetDbsData','getDbsData');
    getDbsDataUpdater = new GetDataUpdater('results_dbs','update');
    ajaxEngine.registerAjaxObject('results_dbs',getDbsDataUpdater);

    ajaxEngine.registerRequest('ajaxGetRuns','getRuns');
    getRunsUpdater = new GetDataUpdater('runs','update');
    ajaxEngine.registerAjaxObject('runs',getRunsUpdater);

    ajaxEngine.registerRequest('ajaxGetRss','getRss');
    ajaxEngine.registerAjaxElement('rss_list');

    ajaxEngine.registerRequest('ajaxGetReleases','getSoftwareReleases');
    ajaxEngine.registerAjaxElement('kw_release');
    ajaxEngine.registerRequest('ajaxGetTriggerLines','getTriggerLines');
    ajaxEngine.registerAjaxElement('kw_prim');
    ajaxEngine.registerRequest('ajaxGetTiers','getTiers');
    ajaxEngine.registerAjaxElement('kw_tier');
    ajaxEngine.registerRequest('ajaxGetBranches','getBranches');
    ajaxEngine.registerAjaxElement('kw_branch');
}
function registerAjaxLucene() {
    ajaxEngine.registerRequest('ajaxGetLucene','getLucene');
    ajaxEngine.registerAjaxElement('webSearchStats');
    ajaxEngine.registerRequest('ajaxGetLuceneParams','getLucene');
    ajaxEngine.registerAjaxElement('parameterNameList');
    updater_grid = new GetDataUpdater('webSearchResultsGrid_updater','replace');
    ajaxEngine.registerAjaxObject('webSearchResultsGrid_updater',updater_grid);
    updater = new GetDataUpdater('configureWebSearchRows','replace');
    ajaxEngine.registerAjaxObject('configureWebSearchRows',updater);
    errUpdater = new GetDataUpdater('errorResponse','replace');
    ajaxEngine.registerAjaxObject('errorResponse',errUpdater);
}
function ajaxGetLucene() {
  ajaxEngine.sendRequest('ajaxGetLucene','term=block.subsys.NumberParameter129='+$('searchInput').value,'method=lookup');
}
function ajaxGetLuceneParams() {
  ajaxEngine.sendRequest('ajaxGetLucene','method=parameters');
}
function ajaxGetRss() {
  ajaxEngine.sendRequest('ajaxGetRss');
}
function ajaxGetKWFields() {
  ajaxGetReleases();
  ajaxGetTriggerLines();
  ajaxGetTiers();
  ajaxGetBranches();
}
function getDBS_kw(_dbs) {
  var dbs;
  if(_dbs) {
      dbs=_dbs;
  } else {
      dbs=document.getElementById('kw_dbsSelector').value;
  }
  return dbs;
}
function ajaxGetReleases(_dbs) {
  dbs=getDBS_kw(_dbs);
  ajaxEngine.sendRequest('ajaxGetReleases','dbsInst='+dbs);
}
function ajaxGetTriggerLines(_dbs) {
  dbs=getDBS_kw(_dbs);
  ajaxEngine.sendRequest('ajaxGetTriggerLines','dbsInst='+dbs);
}
function ajaxGetTiers(_dbs) {
  dbs=getDBS_kw(_dbs);
  ajaxEngine.sendRequest('ajaxGetTiers','dbsInst='+dbs);
}
function ajaxGetBranches(_dbs) {
  dbs=getDBS_kw(_dbs);
  ajaxEngine.sendRequest('ajaxGetBranches','dbsInst='+dbs);
}



function registerAjaxGetBlocksFromSiteCalls() {
  ajaxEngine.registerRequest('ajaxGetBlocksFromSite','getBlocksFromSiteHelper');
  ajaxEngine.registerAjaxElement('siteBlocksHandler');
}
function ajaxGetBlocksFromSite() {
  ajaxEngine.sendRequest('ajaxGetBlocksFromSite');
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
  registerAjaxPrimaryDatasetsCalls();
  registerAjaxProcessedDatasetsCalls();
  registerAjaxApplicationsCalls();
  for(i=0;i<dbsArr.length;i++) {
      var sName=dbsArr[i].replace(/\//g,"___")
      var id=document.getElementById('dbsInst_'+sName);
      if (dbsArr[i]==dbsInst) {
          id.className="td_right";
      } else {
          id.className="show_cell";
      }
  }
  var id=document.getElementById("dbsInst_table");
  id.className="show_table";
  var id=document.getElementById("dbs_info");
  id.className="show_table";
  ajaxGetPrimaryDatasets(dbsInst);
  ajaxGetProcessedDatasets(dbsInst);
  ajaxGetApplications(dbsInst);
}
function ajaxGetPrimaryDatasets(dbsInst) {
  ShowWheel("__dbs_prim");
  ajaxEngine.sendRequest('ajaxGetPrimaryDatasets',"dbsInst="+dbsInst);
  var action='<a href="javascript:ResetAllResults();showMenu(\'DBSinfo\');ajaxGetPrimaryDatasets(\''+dbsInst+'\')">Get all primary datasets (\''+dbsInst+'\')</a>';
  ajaxHistory(action);
}
function registerAjaxPrimaryDatasetsCalls() {
    dbsInfoUpdater = new GetDataUpdater("dbs_prim",'replace','noResultsMenu');
    ajaxEngine.registerRequest('ajaxGetPrimaryDatasets','getPrimaryDatasets');
    ajaxEngine.registerAjaxObject('dbs_prim',dbsInfoUpdater);
}
function ajaxGetProcessedDatasets(dbsInst) {
    ShowWheel("__dbs_proc");
    ajaxEngine.sendRequest('ajaxGetProcessedDatasets',"dbsInst="+dbsInst);
    var action='<a href="javascript:ResetAllResults();showMenu(\'DBSinfo\');ajaxGetProcessedDatasets(\''+dbsInst+'\')">Get all processed datasets (\''+dbsInst+'\')</a>';
    ajaxHistory(action);
}
function registerAjaxProcessedDatasetsCalls() {
    dbsInfoUpdater = new GetDataUpdater("dbs_proc",'replace','noResultsMenu');
    ajaxEngine.registerRequest('ajaxGetProcessedDatasets','getProcessedDatasets');
    ajaxEngine.registerAjaxObject('dbs_proc',dbsInfoUpdater);
}
function ajaxGetApplications(dbsInst) {
    ShowWheel("__dbs_apps");
    ajaxEngine.sendRequest('ajaxGetApplications',"dbsInst="+dbsInst);
    var action='<a href="javascript:ResetAllResults();showMenu(\'DBSinfo\');ajaxGetApplications(\''+dbsInst+'\')">Get all applications (\''+dbsInst+'\')</a>';
    ajaxHistory(action);
}
function registerAjaxApplicationsCalls() {
    dbsInfoUpdater = new GetDataUpdater("dbs_apps",'replace','noResultsMenu');
    ajaxEngine.registerRequest('ajaxGetApplications','getApplications');
    ajaxEngine.registerAjaxObject('dbs_apps',dbsInfoUpdater);
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
     EnableSel("appSelector");
     EnableSel("primSelector");
     EnableSel("tierSelector");
     var t=document.getElementById("navSelector");
     t.innerHTML="";
   }
}
function registerAjaxGenNavigatorMenuDictCalls() {
  navigatorUpdater = new NavigatorMenuDictUpdater();
  ajaxEngine.registerRequest('ajaxGenNavigatorMenuDict','genNavigatorMenuDict');
  ajaxEngine.registerAjaxObject('navigatorDict',navigatorUpdater);
}
function ajaxGenNavigatorMenuDict(_dbs) {
  var dbsInst;
  if(_dbs) { dbsInst=_dbs; }
  var sel=document.getElementById("dbsSelector");
  if(sel) {
     for(i=0;i<sel.length;i++) {
         if(sel[i].selected) {
            dbsInst=sel[i].value;
            break;
         }
     }
  }
  // de-activate underneath menues (will be activated back once AJAX will arrive
  DisableSel("appSelector");
  DisableSel("primSelector");
  DisableSel("tierSelector");
  showLoadingMessage('navSelector');
  ajaxEngine.sendRequest('ajaxGenNavigatorMenuDict','dbsInst='+dbsInst);
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
function ajaxInit(_dbs) {
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
//  registerAjaxGetDataDescriptionCalls();
  registerAjaxGetFloatBoxCalls();
  registerAjaxGetLumisCalls();
  registerAjaxGetAlgosCalls();

  ajaxGenNavigatorMenuDict(_dbs);
  registerAjaxLucene();
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
     // parse response and search for any JavaScript code there, if found execute it.
     var jsCode = SearchForJSCode(responseHTML);
     if(jsCode) {
        eval(jsCode);
     }
   }
}
function registerAjaxProvenanceGraphCalls() {
  ajaxEngine.registerRequest('ajaxGenParentsGraph','getProvenanceForAllDatasets');
  updater = new GetDataUpdater('parents','update');
  ajaxEngine.registerAjaxObject('parents',updater);
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
function ajaxGenParentsGraph(_dbs,_site,_app,_primD,_tier,proc) {
  ShowWheel("__parents");
  var arr  = getDataFromSelectors(_dbs,_site,_app,_primD,_tier)
  if(!arr) return;
  var dbs  = arr[0];
  var site = arr[1];
  var app  = arr[2];
  var primD= arr[3];
  var tier = arr[4];
  if(!proc) {proc="*";}
  ajaxEngine.sendRequest('ajaxGenParentsGraph',"dbsInst="+dbs,"site="+site,"app="+app,"primD="+primD,"tier="+tier,'proc='+proc);
  var action='<a href="javascript:ResetAllResults();ajaxGenParentsGraph(\''+dbs+'\',\''+site+'\',\''+app+'\',\''+primD+'\',\''+tier+'\')">ParentGraph ('+dbs+','+site+','+app+','+primD+','+tier+','+proc+')</a>';
  ajaxHistory(action);
}
function ajaxNextGenParentsGraph(dbs,site,app,primD,tier,proc,idx) {
  ajaxEngine.sendRequest('ajaxGenParentsGraph',"dbsInst="+dbs,"site="+site,"app="+app,"primD="+primD,"tier="+tier,"proc="+proc,"_idx="+idx);
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
  ajaxEngine.registerRequest('ajaxGenAppConfigs','getAppConfigs');
  updater = new GetDataUpdater('appConfigs','replace');
  ajaxEngine.registerAjaxObject('appConfigs',updater);
}
function ajaxGenAppConfigs(_app) {
  ShowWheel("__appConfigs");
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
  var action='<a href="javascript:ResetAllResults();ajaxGenAppConfigs(\''+app+'\')">AppConfigs ('+app+')</a>';
  ajaxHistory(action);
}
function registerAjaxGetFloatBoxCalls() {
    ajaxEngine.registerRequest('ajaxFloatBox','getFloatBox');
    ajaxEngine.registerAjaxElement('floatDataDescription');
}
function ajaxFloatBox(title,desc,className) {
    var c=className;
    if(!className) {
        c='float_box';
    }
    ajaxEngine.sendRequest('ajaxFloatBox','title='+title,'description='+desc,'className='+c);
    ShowTag('floatDataDescription');
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
function ajaxGetDummyData() {
  arr='test1,test2';
  ajaxEngine.sendRequest('ajaxGetDummyData',"arr="+arr);
}

function registerAjaxGetLumisCalls() {
    ajaxEngine.registerRequest('ajaxGetLumis','getLFN_Lumis');
    ajaxEngine.registerAjaxElement('floatDataDescription');
}
function ajaxGetLumis(dbs,lfn) {
    ajaxEngine.sendRequest('ajaxGetLumis','dbsInst='+dbs,'lfn='+lfn,'ajax=1');
    ShowTag('floatDataDescription');
}
function registerAjaxGetAlgosCalls() {
    ajaxEngine.registerRequest('ajaxGetAlgos','getLFN_Algos');
    ajaxEngine.registerAjaxElement('floatDataDescription');
}
function ajaxGetAlgos(dbs,lfn) {
    ajaxEngine.sendRequest('ajaxGetAlgos','dbsInst='+dbs,'lfn='+lfn,'ajax=1');
    ShowTag('floatDataDescription');
}
