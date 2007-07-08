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
var PhedexUpdater=Class.create();
PhedexUpdater.prototype = {
   initialize: function(tab) {
     this.tab=tab
   },
   ajaxUpdate: function(ajaxResponse) {
     var responseHTML=RicoUtil.getContentAsString(ajaxResponse);
     var t=document.getElementById(this.tab);
     var iVal=t.innerHTML;
     if(responseHTML.indexOf('%')) {
        t.innerHTML=responseHTML;
     } else {
        t.innerHTML=iVal+responseHTML;
     }
   }
}
function registerAjaxHistoryCalls() {
  userRegUpdater = new UserRegistrationUpdater();
  ajaxEngine.registerRequest('ajaxCheckUser','checkUser');
  ajaxEngine.registerAjaxObject('historyUserName',userRegUpdater);
//  sessionHistoryUpdater = new HistoryUpdater('session');
  ajaxEngine.registerRequest('ajaxHistory','history');
//  ajaxEngine.registerAjaxObject('sessionHistory',sessionHistoryUpdater);
  ajaxEngine.registerRequest('ajaxHistorySearch','historySearch');
  ajaxEngine.registerAjaxElement('historySearchResults');
  allHistoryUpdater = new HistoryUpdater('all');
  ajaxEngine.registerRequest('ajaxGetHistory','getHistory');
  ajaxEngine.registerAjaxObject('allHistory',allHistoryUpdater);
}
function ajaxCheckUser() {
  var user = GetCookie("DBSDD_username");
//  var pass = GetCookie("DBSDD_password");
  if(user!='guest') {
     ajaxEngine.sendRequest('ajaxCheckUser','userId='+user);
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
function ajaxHistory(dbsInst,action,iUser) {
  ajaxEngine.sendRequest('ajaxHistory','dbsInst='+dbsInst,'userId='+getUserName(iUser),'actionString='+action);
}
function ajaxGetHistory(iUser,iLimit) {
  var limit;
  if(iLimit) {
     limit=iLimit;
  } else {
     limit=20;
  }
  ajaxEngine.sendRequest('ajaxGetHistory','userId='+getUserName(iUser),'limit='+limit);
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
function ajaxHistorySearch(iUser) {
  var iMonth = getSelectedOption(document.getElementById('in_hSearch_month'));
  var iYear  = getSelectedOption(document.getElementById('in_hSearch_year'));
  var oMonth = getSelectedOption(document.getElementById('out_hSearch_month'));
  var oYear  = getSelectedOption(document.getElementById('out_hSearch_year'));
  ajaxEngine.sendRequest('ajaxHistorySearch','iYear='+iYear,'iMonth='+iMonth,'oYear='+oYear,'oMonth='+oMonth,'userId='+getUserName(iUser));
}
function getDataFromSelectors(_dbs,_site,_group,_app,_primD,_tier) {
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
  var group;
  if(_group) {
      group=_group;
  } else {
      sel=document.getElementById('groupSelector');
      if(!sel) {
          group='';
      } else {
          group=sel.value;
      }
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
  var arr = new Array(dbs,site,app,primD,tier,group);
  return arr;
}
// AJAX registration 
function ajaxGetRuns(_dbs,_site,_group,_app,_primD,_tier,proc) {
  ShowWheel("__runs");
  var arr  = getDataFromSelectors(_dbs,_site,_group,_app,_primD,_tier)
  if(!arr) return;
  var dbs  = arr[0];
  var site = arr[1];
  var app  = arr[2];
  var primD= arr[3];
  var tier = arr[4];
  var group= arr[5];
  if(!proc) {proc="*";}
  ajaxEngine.sendRequest('ajaxGetRuns',"dbsInst="+dbs,"site="+site,"group="+group,"app="+app,"primD="+primD,"tier="+tier,"proc="+proc);
  var action='<a href="javascript:ResetAllResults();ajaxGetRuns(\''+dbs+'\',\''+site+'\',\''+group+'\',\''+app+'\',\''+primD+'\',\''+tier+'\',\''+proc+'\')">Navigator ('+dbs+','+site+','+group+','+app+','+primD+','+tier+','+proc+')</a>';
  ajaxHistory(dbs,action);
}
// AJAX registration 
function ajaxGetDbsData(_dbs,_site,_group,_app,_primD,_tier,proc) {
  ShowWheel("__results_dbs");
  var arr  = getDataFromSelectors(_dbs,_site,_group,_app,_primD,_tier)
  if(!arr) return;
  var dbs  = arr[0];
  var site = arr[1];
  var app  = arr[2];
  var primD= arr[3];
  var tier = arr[4];
  var group= arr[5];
  if(!proc) {proc="*";}
  ajaxEngine.sendRequest('ajaxGetDbsData',"dbsInst="+dbs,"site="+site,"group="+group,"app="+app,"primD="+primD,"tier="+tier,"proc="+proc);
//  var action='<a href="javascript:ResetAllResults();ajaxGetDbsData(\''+dbs+'\',\''+site+'\',\''+group+'\',\''+app+'\',\''+primD+'\',\''+tier+'\',\''+proc+'\')">Navigator ('+dbs+','+site+','+group+','+app+','+primD+','+tier+','+proc+')</a>';
  var ajaxCall='ResetAllResults();ajaxGetDbsData(\''+dbs+'\',\''+site+'\',\''+group+'\',\''+app+'\',\''+primD+'\',\''+tier+'\',\''+proc+'\')';
  var action='<a href="javascript:'+ajaxCall+'">Navigator ('+dbs+','+site+','+group+','+app+','+primD+','+tier+','+proc+')</a>';
  ajaxHistory(dbs,action);
// This is how we add back button support.
//  ajax_dhtmlHistory('ajaxGetDbsData',ajaxCall);
}
// AJAX registration
function SendAjaxCalls(dbs,site,group,app,prim,tier,proc) {
  ajaxGetData(dbs,site,group,app,prim,tier,proc);
//  ajaxGetDbsData(dbs,site,group,app,prim,tier,proc);
//  ajaxGetRuns(dbs,site,group,app,prim,tier,proc);
//  ajaxGenAppConfigs(dbs,site,group,app,prim,tier,proc);
}
function ajaxGetUserData() {
  var dbs  =$('kw_dbsInstSelector').value
  var group=$('kw_group').value;
  var type =$('kw_tier').value;
  var prim =$('kw_prim').value;
  var rels =$('kw_release').value;
  var site =$('kw_site').value;
  var app  ='/'+rels+'/*/*';
  var proc='*';
  SendAjaxCalls(dbs,site,group,app,prim,type,proc);
}
function ajaxGetData(_dbs,_site,_group,_app,_primD,_tier,proc) {
  ShowWheel('__results');
  var arr  = getDataFromSelectors(_dbs,_site,_group,_app,_primD,_tier);
  if(!arr) return;
  var dbs  = arr[0];
  var site = arr[1];
  var app  = arr[2];
  var primD= arr[3];
  var tier = arr[4];
  var group= arr[5];
  if(!proc) {proc="*";}
  // Set Cookies about current snapshot of data
//  SetCookie('dbsInst',dbs);
//  SetCookie('site',site);
//  SetCookie('app',app);
//  SetCookie('primD',primD);
//  SetCookie('tier',tier);
//  SetCookie('proc',proc);

  ajaxEngine.sendRequest('ajaxGetData',"dbsInst="+dbs,"site="+site,"group="+group,"app="+app,"primD="+primD,"tier="+tier,"proc="+proc,'hist='+GetTagContent('navBar'));
  var action='<a href="javascript:ResetAllResults();ajaxGetData(\''+dbs+'\',\''+site+'\',\''+group+'\',\''+app+'\',\''+primD+'\',\''+tier+'\',\''+proc+'\')">Navigator ('+dbs+','+site+','+group+','+app+','+primD+','+tier+')</a>';
  ajaxHistory(dbs,action);
  // invoke next chunk of data
  ajaxNextGetData(dbs,site,group,app,primD,tier,proc,1);
}
function ajaxNextGetData(dbs,site,group,app,primD,tier,proc,idx) {
  var id=document.getElementById('results_response'+idx);
  if(!id) {
//  alert('ajaxGetData idx='+idx);
  ajaxEngine.sendRequest('ajaxGetData','dbsInst='+dbs,'site='+site,'group='+group,'app='+app,'primD='+primD,'tier='+tier,'proc='+proc,'_idx='+idx);
  }
/*
  var id=document.getElementById('results_dbs_response'+idx);
  if(!id) {
//  alert('ajaxGetiDbsData idx='+idx);
  ajaxEngine.sendRequest('ajaxGetDbsData','dbsInst='+dbs,'site='+site,'group='+group,'app='+app,'primD='+primD,'tier='+tier,'proc='+proc,'_idx='+idx);
  }

  var id=document.getElementById('runs_response'+idx);
  if(!id) {
//  alert('ajaxGetRuns idx='+idx);
  ajaxEngine.sendRequest('ajaxGetRuns',"dbsInst="+dbs,"site="+site,"group="+group,"app="+app,"primD="+primD,"tier="+tier,"proc="+proc,'_idx='+idx);
  }
*/
}
/*
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
         ajaxEngine.sendRequest('ajaxGetDataFromSelection','userSelection='+selList[i]);
         var actionHistory='<a href="javascript:showWaitingMessage();ajaxGetDataFromSelection(\''+selList[i]+'\')">data selection ('+selList[i]+')</a>';
         ajaxHistory(actionHistory);
      }
  }
}
*/

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
  ajaxHistory(dbsInst,action);
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
  ajaxHistory(dbsInst,action);
}

// AJAX registration for site search
/*
function ajaxGetSites(_dbs) {
  var dbsInst;
  if(_dbs) {
      dbsInst=_dbs;
  } else {
      dbsInst=document.getElementById('form2_dbsSelector').value;
  }
  ajaxEngine.sendRequest('ajaxGetSites','dbsInst='+dbsInst);
}
*/
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
  ajaxHistory(dbsInst,action);
}
function registerAjaxObjectCalls() {
    getDataUpdater = new GetDataUpdater('results','update');
    ajaxEngine.registerRequest('ajaxGetData','getData');
    ajaxEngine.registerRequest('ajaxSearch','search');
//    ajaxEngine.registerRequest('ajaxGetDataFromSelection','getDataFromSelection');
    ajaxEngine.registerRequest('ajaxGetDummyData','dummy');
    ajaxEngine.registerAjaxObject('results',getDataUpdater);

    ajaxEngine.registerRequest('ajaxKeywordSearch','search');
    kwUpdater = new GetDataUpdater('results_kw','replace','noResultsMenu');
    ajaxEngine.registerAjaxObject('results_kw',kwUpdater);

//    ajaxEngine.registerRequest('ajaxGetSites','getSites');
//    ajaxEngine.registerAjaxElement('sitesHolder');

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

    ajaxEngine.registerRequest('ajaxMakeLine','makeLine');
    lineUpdater = new GetDataUpdater('makeMenu_1','replace','noResultsMenu');
    ajaxEngine.registerAjaxObject('makeMenu_1',lineUpdater);

    ajaxEngine.registerRequest('ajaxGetTableColumns','getTableColumns');
    ajaxEngine.registerRequest('ajaxGetSectionTables','getSectionTables');
    ajaxEngine.registerRequest('ajaxGetTableColumnsFromSection','getTableColumnsFromSection');

    ajaxEngine.registerRequest('ajaxGetDbsSchema','getDbsSchema');
    ajaxEngine.registerRequest('ajaxExecuteQuery','executeSQLQuery');
    ajaxEngine.registerRequest('ajaxFinderSearch','finderSearch');
    ajaxEngine.registerRequest('ajaxFindDSFromFinder','findDSFromFinder');
    ajaxEngine.registerRequest('ajaxFinderStoreQuery','finderStoreQueryInXML');
    ajaxEngine.registerRequest('ajaxFinderResultStoreQuery','finderStoreQueryInXML');
    ajaxEngine.registerRequest('ajaxFinderSearchQuery','finderSearchQuery');
    finderUpdater = new GetDataUpdater('results_finder','replace','noResultsMenu');
    ajaxEngine.registerAjaxObject('results_finder',finderUpdater);
    finderQUpdater = new GetDataUpdater('myQueries','replace','noResultsMenu');
    ajaxEngine.registerAjaxObject('myQueries',finderQUpdater);

    ajaxEngine.registerRequest('ajaxGetRunDBInfo','getRunDBInfo');
//    ajaxEngine.registerRequest('ajaxGetUserNav','genUserNavigator');
//    ajaxEngine.registerAjaxElement('kw_userNavigator');
}
function registerAjaxUserMenuCalls() {
    ajaxEngine.registerRequest('ajaxGetPrimDSTypes','getPrimaryDSTypes');
    ajaxEngine.registerAjaxElement('kw_primType_holder');
    ajaxEngine.registerRequest('ajaxGetReleases','getSoftwareReleases');
    ajaxEngine.registerAjaxElement('kw_release_holder');
    ajaxEngine.registerRequest('ajaxGetTriggerLines','getTriggerLines');
    ajaxEngine.registerAjaxElement('kw_prim_holder');
    ajaxEngine.registerRequest('ajaxGetTiers','getTiers');
    ajaxEngine.registerAjaxElement('kw_tier_holder');
    ajaxEngine.registerRequest('ajaxGetSites','getSites');
    ajaxEngine.registerAjaxElement('kw_site_holder');
    ajaxEngine.registerAjaxElement('form2_siteHolder');
    ajaxEngine.registerRequest('ajaxGetGroups','getGroups');
    ajaxEngine.registerAjaxElement('kw_group_holder');

    ajaxEngine.registerRequest('ajaxGetRunRange','getRunRange');
    ajaxEngine.registerAjaxElement('kw_runRange_holder');

//    ajaxEngine.registerRequest('ajaxGetBranches','getBranches');
//    ajaxEngine.registerAjaxElement('kw_branch');
//    ajaxEngine.registerRequest('ajaxGetUserData','getUserData');

}

function ajaxGetRunDBInfo(run) {
  ajaxEngine.sendRequest('ajaxGetRunDBInfo','run='+run);
}

function ajaxMakeLine(id) {
  ajaxEngine.sendRequest('ajaxMakeLine','id='+id);
}
function ajaxGetTableColumns(dbs,tableName,id) {
  ajaxEngine.sendRequest('ajaxGetTableColumns','dbsInst='+dbs,'tableName='+tableName,'id='+id);
}
function ajaxGetTableColumnsFromSection(dbs,section,id) {
  ajaxEngine.sendRequest('ajaxGetTableColumnsFromSection','dbsInst='+dbs,'section='+section,'id='+id);
}
function ajaxGetSectionTables(dbsInst,section,id) {
    ajaxEngine.sendRequest('ajaxGetSectionTables','dbsInst='+dbsInst,'section='+section,'id='+id);
}
function ajaxFillLine(lineId) {
    dbsInst='localhost';
    var id=document.getElementById("finder_dbsSelector");
    if (id) {
        dbsInst=id.value;
    }
    var table=document.getElementById('sectionTables_'+lineId);
    ajaxEngine.registerRequest('ajaxGetTableColumns','getTableColumns');
    ajaxGetTableColumns(dbsInst,table.value,lineId);
}
function ChangeTables(lineId) {
    dbsInst='localhost';
    var id=document.getElementById("finder_dbsSelector");
    if (id) {
        dbsInst=id.value;
    }
    var id=document.getElementById("selSection_"+lineId);
    if (id) {
        var section = id.value;
        ajaxGetSectionTables(dbsInst,section,lineId);
        ajaxGetTableColumnsFromSection(dbsInst,section,lineId);
    }
}
function ChangeCols(lineId,tag) {
    dbsInst='localhost';
    var id=document.getElementById("finder_dbsSelector");
    if (id) {
        dbsInst=id.value;
    }
    if(!tag) {
        tag="sectionTables"
    }
    var id=document.getElementById(tag+"_"+lineId);
    if (id) {
        var tableName = id.value;
        ajaxGetTableColumns(dbsInst,tableName,lineId);
    }
}
function ajaxGetDbsSchema(dbsInst,table) {
    ShowTag('results_finder');
    if(!dbsInst) {
        dbsInst='';
        var dbsList=$('dbsExpert_dbsSelector');
        for(i=0;i<dbsList.length;i++) {
           if(dbsList[i].selected) {
              dbsInst=dbsList[i].value;
              break;
           }
        }
    }
    if(!table) {
        table=$("kw_dbsTables").value
    }
    ajaxEngine.sendRequest('ajaxGetDbsSchema','dbsInst='+dbsInst,'table='+table);
}
function registerAjaxLucene() {
    ajaxEngine.registerRequest('ajaxGetLucene','getLucene');
    updater_stats = new GetDataUpdater('webSearchStats','replace','noResultsMenu');
    ajaxEngine.registerAjaxObject('webSearchStats',updater_stats);

    ajaxEngine.registerRequest('ajaxGetLuceneParams','getLucene');
    updater_param = new GetDataUpdater('cfgparamlist','replace','noResultsMenu');
    ajaxEngine.registerAjaxObject('cfgparamlist',updater_param);
    updater_param_res = new GetDataUpdater('results','replace','noResulsMenu');
    ajaxEngine.registerAjaxObject('cfgindexlookup',updater_param_res);
// Lucene way to get parameters
//    updater_param = new GetDataUpdater('parameterNameList','replace','noResultsMenu');
//    ajaxEngine.registerAjaxObject('parameterNameList',updater_param);

    updater_grid = new GetDataUpdater('webSearchResultsGrid_updater','replace','noResultsMenu');
    ajaxEngine.registerAjaxObject('webSearchResultsGrid_updater',updater_grid);

    updater = new GetDataUpdater('configureWebSearchRows','replace','noResultsMenu');
    ajaxEngine.registerAjaxObject('configureWebSearchRows',updater);

    errUpdater = new GetDataUpdater('errorResponse','replace','noResultsMenu');
    ajaxEngine.registerAjaxObject('errorResponse',errUpdater);
}
function ajaxGetLucene() {
    // here we use prototype syntax $('param') means document.getElementById('param')
//    ajaxEngine.sendRequest('ajaxGetLucene','term='+$('parameterList').value+$('parameterListOperators').value+$('searchInput').value,'method=lookup','outputs=both');

/*
    var sel=document.getElementById('selectcfgparam');
    var p_name,p_type;
    for(i=0;i<sel.options.length;i++) {
          if(sel.options[i].selected) {
             p_name = sel.options[i].id;
             p_type = sel.options[i].value;
          }
     }

    ajaxEngine.sendRequest('ajaxGetLucene','method=query.jsp','num=1','ptype0='+p_type,'pname0='+p_name,'op0='+$('parameterListOperators').value,'val0='+$('searchInput').value);
*/
    var g_pname=document.getElementsByName('p_name');
    var g_ptype=document.getElementsByName('p_type');
    var g_op   =document.getElementsByName('p_op');
    var g_val  =document.getElementsByName('p_val');
    var num=g_pname.length;
    var url='num='+num;
    for(i=0;i<num;i++) {
        url=url+'&pname'+i+'='+g_pname[i].innerHTML;
        url=url+'&ptype'+i+'='+g_ptype[i].innerHTML;
        url=url+'&val'+i+'='  +g_val[i].innerHTML;
        url=url+'&op'+i+'='   +g_op[i].innerHTML;
    }
    ajaxEngine.sendRequest('ajaxGetLucene','method=query.jsp','params='+url);
}
function ajaxGetLuceneParams() {
//    ajaxEngine.sendRequest('ajaxGetLucene','method=parameters');
    ajaxEngine.sendRequest('ajaxGetLucene','method=paramlist.jsp');
}
function ajaxGetRss() {
    ajaxEngine.sendRequest('ajaxGetRss');
}
function ajaxExecuteQuery(iDbs,iQuery) {
    ShowTag('results_finder');
    var query;
    if(iQuery) {
        query=iQuery;
    } else {
        query=$('queryText').value
    }
    var dbsInst;
    if(iDbs) {
        dbsInst=iDbs;
    } else {
        var dbsList=$('dbsExpert_dbsSelector');
        for(i=0;i<dbsList.length;i++) {
           if(dbsList[i].selected) {
              dbsInst=dbsList[i].value;
              break;
           }
        }
    }
    ajaxEngine.sendRequest('ajaxExecuteQuery',"dbsInst="+dbsInst,"query="+query);

    var ajaxCall='ResetAllResults();ajaxExecuteQuery(\''+dbsInst+'\',\''+query+'\')';
    var action='<a href="javascript:'+ajaxCall+'">ExecuteQuery ('+query+')</a>';
    ajaxHistory(dbsInst,action);
// This is how we add back button support.
//  ajax_dhtmlHistory('ajaxGetDbsData',ajaxCall);
}
function ajaxFinderSearch(userMode,dbsInst,parameters,limit,offset) {
    if (dbsInst && parameters) {
        ajaxEngine.sendRequest('ajaxFinderSearch','userMode='+userMode,'dbsInst='+dbsInst,'limit='+limit,'offset='+offset,parameters);
    } else {
        ShowTag('results_finder');
        var dbsInst=$('finder_dbsSelector').value;
        var dbsList=$('finder_dbsSelector');
        for(i=0;i<dbsList.length;i++) {
           if(dbsList[i].selected) {
              dbsInst=dbsList[i].value;
              break;
           }
        }
        var sel=document.getElementsByName("sectionTables");
        var maxId=1;
        for(var i=0;i<sel.length;i++) {
            var sel_id = sel[i].id;
            var id=sel_id.split('_')[1];
            if(id>maxId) { maxId=id; }
        }
        var parameters='';
        for(var i=1;i<=maxId;i++) {
            table=$('sectionTables_'+i).value;
            column=$('tableColumns_'+i).value;
            operator=$('colSel_'+i).value;
            where=$('where_'+i).value;
    //        out=$('outCol_'+i).value;
    //        str=table+'__'+column+'__'+operator+'__'+where+'__'+out;
            limit=$('kw_limit').value
            str=table+'__'+column+'__'+operator+'__'+where+'__'+limit;
            if(!parameters) {
                parameters='params='+str;
            } else {
                parameters=parameters+'_newparam_'+str;
            }
        }
        ajaxEngine.sendRequest('ajaxFinderSearch','userMode='+userMode,'dbsInst='+dbsInst,parameters);
    }
}
function ajaxFindDSFromFinder(dbsInst,params,userMode) {
    ajaxEngine.sendRequest('ajaxFindDSFromFinder','dbsInst='+dbsInst,'params='+params,'userMode='+userMode);
}
function ajaxFinderStoreQuery(iUser) {
    var dbsInst=$('finder_dbsSelector').value;
    var sel=document.getElementsByName("tableColumnList");
    var parameters='';
    for(var i=0;i<sel.length;i++) {
        if (sel[i].checked) {
            if (!parameters) {
                 parameters='params='+sel[i].id;
            } else {
                 parameters=parameters+'_table_'+sel[i].id;
            }
        }
    }
    var where='where='+$('kw_where').value;
    var aName=$('kw_alias').value;

/*
    var sel=document.getElementsByName("sectionTables");
    var maxId=1;
    for(var i=0;i<sel.length;i++) {
        var sel_id = sel[i].id;
        var id=sel_id.split('_')[1];
        if(id>maxId) { maxId=id; }
    }
    var aName=$('kw_alias').value;
    var parameters='';
    for(var i=1;i<=maxId;i++) {
        table=$('sectionTables_'+i).value;
        column=$('tableColumns_'+i).value;
        operator=$('colSel_'+i).value;
        where=$('where_'+i).value;
        if(!parameters) {
        parameters='params='+table+'__'+column+'__'+operator+'__'+where;
        } else {
        parameters=parameters+'_newparam_'+table+'__'+column+'__'+operator+'__'+where;
        }
    }
    ajaxEngine.sendRequest('ajaxFinderStoreQuery','dbsInst='+dbsInst,'userId='+getUserName(iUser),'alias='+aName,parameters);
*/
    ajaxEngine.sendRequest('ajaxFinderStoreQuery','dbsInst='+dbsInst,'userId='+getUserName(iUser),'alias='+aName,parameters,where);
//    $('results_finder').innerHTML='Your query "'+aName+'" has been saved.';
    $('query_confirmation').innerHTML='<span class="box_gray">Your query "'+aName+'" has been saved.</span>';
    $('kw_alias').value='';
}
function ajaxFinderResultStoreQuery(iUser) {
    var query=$('queryXML').value;
    var dbsInst=$('dbsInst').value;
    var aName=$('kw_alias').value;
    ajaxEngine.sendRequest('ajaxFinderStoreQuery','dbsInst='+dbsInst,'userId='+getUserName(iUser),'alias='+aName,'queryXML='+query);
    $('query_confirmation').innerHTML='<span class="box_gray">Your query "'+aName+'" has been saved.</span>';
    $('kw_alias').value='';
}
function ajaxFinderSearchQuery(iUser) {
    ajaxEngine.sendRequest('ajaxFinderSearchQuery','userId='+getUserName(iUser),'alias='+$('kw_alias_lookup').value);
}
function ajaxGetKWFields() {
  showLoadingMessage('kw_group_holder');
  ajaxGetGroups();
  showLoadingMessage('kw_tier_holder');
  ajaxGetTiers();
  showLoadingMessage('kw_release_holder');
  ajaxGetReleases();
  showLoadingMessage('kw_prim_holder');
  ajaxGetTriggerLines();
  showLoadingMessage('kw_primType_holder');
  ajaxGetPrimDSTypes();
  showLoadingMessage('kw_site_holder');
  ajaxGetSites('','kw_dbsInstSelector','kw_site_holder','site');
//  var rel = $('kw_release').value;
//  var tier= $('kw_tier').value;
//  var group=$('kw_group').value;
//alert('rel='+rel+' tier='+tier+' group='+group);
//  if(rel!='Any' || tier!='Any' || group!='Any') {
//     ajaxUpdatePrimaryDatasets();
//  }
}

function getDBS_kw(_dbs) {
  var dbs;
  if(_dbs) {
      dbs=_dbs;
  } else {
      dbs=$('kw_dbsInstSelector').value;
  }
  return dbs;
}
function ajaxGetRunRange(_dbs) {
  var dbs=getDBS_kw(_dbs);
  var prim='any';
  if($('kw_prim')) {
      prim=$('kw_prim').value;
  }
  var primType='any';
  if($('kw_primType')) {
      primType=$('kw_primType').value;
  }
  ajaxEngine.sendRequest('ajaxGetRunRange','dbsInst='+dbs,'primD='+prim,'primType='+primType);
}
function ajaxGetReleases(_dbs) {
  var dbs=getDBS_kw(_dbs);
  ajaxEngine.sendRequest('ajaxGetReleases','dbsInst='+dbs);
}
function ajaxGetTriggerLines(_dbs,_cFunc) {
  var dbs=getDBS_kw(_dbs);
  if(_cFunc) {
      ajaxEngine.sendRequest('ajaxGetTriggerLines','dbsInst='+dbs,'changeFunction='+_cFunc);
      return;
  }
  ajaxEngine.sendRequest('ajaxGetTriggerLines','dbsInst='+dbs);
}
function ajaxGetPrimDSTypes(_dbs,_cFunc) {
  var dbs=getDBS_kw(_dbs);
  if(_cFunc) {
      ajaxEngine.sendRequest('ajaxGetPrimDSTypes','dbsInst='+dbs,'changeFunction='+_cFunc);
      return;
  }
  ajaxEngine.sendRequest('ajaxGetPrimDSTypes','dbsInst='+dbs);
}
/*
function ajaxGetTriggerLines(_dbs) {
  var dbs=getDBS_kw(_dbs);
  ajaxEngine.sendRequest('ajaxGetTriggerLines','dbsInst='+dbs);
}
function ajaxGetPrimDSTypes(_dbs) {
  var dbs=getDBS_kw(_dbs);
  ajaxEngine.sendRequest('ajaxGetPrimDSTypes','dbsInst='+dbs);
}
*/

function ajaxUpdatePrimaryDatasets(_dbs,_cFunc) {
  var dbs=getDBS_kw(_dbs);
  var rel = $('kw_release').value;
  var tier= $('kw_tier').value;
  var group=$('kw_group').value;
  var dsType=$('kw_primType').value;
//  showLoadingMessage('kw_prim_holder');
  $('kw_prim').disabled="disabled";
  if(_cFunc) {
     ajaxEngine.sendRequest('ajaxGetTriggerLines','dbsInst='+dbs,'group='+group,'tier='+tier,'rel='+rel,'dsType='+dsType,'changeFunction='+_cFunc);
     return;
  }
  ajaxEngine.sendRequest('ajaxGetTriggerLines','dbsInst='+dbs,'group='+group,'tier='+tier,'rel='+rel,'dsType='+dsType);
}
function ajaxGetTiers(_dbs) {
  var dbs=getDBS_kw(_dbs);
  ajaxEngine.sendRequest('ajaxGetTiers','dbsInst='+dbs);
}
function ajaxGetSites(_dbs,dbsSel,siteSel,siteTag) {
  var dbs;
  if(dbsSel) {
     dbs=$(dbsSel).value;
  } else {
     getDBS_kw(_dbs);
  }
  if(siteSel) {
      var sTag='';
      if(siteTag) {sTag=siteTag;}
      ajaxEngine.sendRequest('ajaxGetSites','dbsInst='+dbs,'sel='+siteSel,'tag='+sTag);
  } else {
      ajaxEngine.sendRequest('ajaxGetSites','dbsInst='+dbs);
  }
}
function ajaxGetGroups(_dbs) {
  var dbs=getDBS_kw(_dbs);
  ajaxEngine.sendRequest('ajaxGetGroups','dbsInst='+dbs);
}
//function ajaxGetBranches(_dbs) {
//  dbs=getDBS_kw(_dbs);
//  ajaxEngine.sendRequest('ajaxGetBranches','dbsInst='+dbs);
//}


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
/*
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
*/
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
  ajaxHistory(dbsInst,action);
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
    ajaxHistory(dbsInst,action);
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
    ajaxHistory(dbsInst,action);
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

/*
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
*/
/*
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
*/

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
//  registerAjaxGenNavigatorMenuDictCalls();
  registerAjaxGetFloatBoxCalls();
  registerAjaxGetLumisCalls();
  registerAjaxProdRequestCalls();
  registerAjaxPhedexCalls();

//  ajaxGenNavigatorMenuDict(_dbs);
  registerAjaxLucene();
  registerAjaxUserMenuCalls();
  initialize_dhtmlHistory();
  registerAjaxGetMoreInfoCalls();
}

function registerAjaxPhedexCalls() {
    ajaxEngine.registerRequest('ajaxPhedexStatus','phedexStatus');
}
function ajaxPhedexStatus(site,datasetPath,id_suffix) {
   if(!id_suffix) {
       id_suffix='';
   }
   ajaxEngine.sendRequest('ajaxPhedexStatus','site='+site,'datasetPath='+datasetPath,'id_suffix='+id_suffix);
}
function registerAjaxProdRequestCalls() {
    ajaxEngine.registerRequest('ajaxGetProdRequest','getProdRequest');
//    ajaxEngine.registerAjaxElement('id_ProdRequest');
}
function ajaxGetProdRequest(prim,id) {
  ajaxEngine.sendRequest('ajaxGetProdRequest','prim='+prim,'id='+id);
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
function ajaxGenParentsGraph(_dbs,_site,_group,_app,_primD,_tier,proc) {
  ShowWheel("__parents");
//  showLoadingMessage('results_dbs');
  var arr  = getDataFromSelectors(_dbs,_site,_group,_app,_primD,_tier)
  if(!arr) return;
  var dbs  = arr[0];
  var site = arr[1];
  var app  = arr[2];
  var primD= arr[3];
  var tier = arr[4];
  var group= arr[5];
  if(!proc) {proc="*";}
  ajaxEngine.sendRequest('ajaxGenParentsGraph',"dbsInst="+dbs,"site="+site,"group="+group,"app="+app,"primD="+primD,"tier="+tier,'proc='+proc);
  var action='<a href="javascript:ResetAllResults();ajaxGenParentsGraph(\''+dbs+'\',\''+site+'\',\''+group+'\',\''+app+'\',\''+primD+'\',\''+tier+'\')">ParentGraph ('+dbs+','+site+','+app+','+primD+','+tier+','+proc+')</a>';
  ajaxHistory(dbs,action);
}
function ajaxNextGenParentsGraph(dbs,site,group,app,primD,tier,proc,idx) {
  ajaxEngine.sendRequest('ajaxGenParentsGraph',"dbsInst="+dbs,"site="+site,"group="+group,"app="+app,"primD="+primD,"tier="+tier,"proc="+proc,"_idx="+idx);
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
function ajaxGenAppConfigs(_dbs,_site,_group,_app,_prim,_tier,proc) {
  ShowWheel("__appConfigs");
  var arr  = getDataFromSelectors(_dbs,_site,_group,_app,_prim,_tier)
  if(!arr) return;
  var dbs  = arr[0];
  var site = arr[1];
  var app  = arr[2];
  var primD= arr[3];
  var tier = arr[4];
  var group= arr[5];
  if(!proc) {proc="*";}
  ajaxEngine.sendRequest('ajaxGenAppConfigs',"dbsInst="+dbs,"appPath="+app);
  var action='<a href="javascript:ResetAllResults();ajaxGenAppConfigs(\''+app+'\')">AppConfigs ('+app+')</a>';
  ajaxHistory(dbs,action);
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
/*
 dhtml stuff
 For more information see
 http://codinginparadise.org/projects/dhtml_history/
 http://www.onjava.com/pub/a/onjava/2005/10/26/ajax-handling-bookmarks-and-back-button.html
*/
function initialize_dhtmlHistory() {
    // initialize our DHTML history
    dhtmlHistory.initialize();
    // subscribe to DHTML history change events
    dhtmlHistory.addListener(historyChange);
}
function historyChange(newLocation, historyData) {
   if(newLocation.indexOf("moreInfo")>-1) {
     var id=newLocation.replace('moreInfo','');
     data=historyData.split(',');
     ajaxEngine.registerRequest('ajaxMoreInfo','getMoreInfo');
     ajaxEngine.registerAjaxElement(id);
     ajaxMoreInfo(data[0],data[1],data[2],data[3],data[4]);
   } else {
   eval(historyData);
   }
}
function ajax_dhtmlHistory(id,action) {
   browser=CheckBrowser();
   // DHTML framework doesn't work on Safari.
   if(!browser.match('Safari')) {
      dhtmlHistory.add(id,action);
   }
}
function registerAjaxGetMoreInfoCalls() {
    ajaxEngine.registerRequest('ajaxMoreInfo','getMoreInfo');
//    ajaxEngine.registerAjaxElement('floatDataDescription');
}
function ajaxMoreInfo(dbsInst,path,appPath,id,userMode) {
    ajaxEngine.sendRequest('ajaxMoreInfo','dbsInst='+dbsInst,'path='+path,'appPath='+appPath,'id='+id,'userMode='+userMode);
    ShowTag(id);
}
function ajaxWriteUserQuery() {
    var name=$('kw_alias').value;
    // I need to get from templateSearchTable.tmpl all values and store using alias name user query
}
//function ajaxGetUserNav() {
//    var dbsInst=$('kw_dbsSelector').value;
//    ajaxEngine.sendRequest('ajaxGetUserNav','dbsInst='+dbsInst,'ajax=1');
//}
function ajaxPrintXML(input,id) {
    ajaxEngine.registerRequest('ajaxPrintXML','printXML');
    ajaxEngine.registerAjaxElement(id);
    ajaxEngine.sendRequest('ajaxPrintXML','input='+input,'id='+id,'ajax=1');
    ShowTag(id);
}
function ajaxConvertXMLTOTXT(input,id) {
    ajaxEngine.registerRequest('ajaxConvertXMLTOTXT','convertXMLTOTXT');
    ajaxEngine.registerAjaxElement(id);
    ajaxEngine.sendRequest('ajaxConvertXMLTOTXT','input='+input,'id='+id,'ajax=1','html=1');
    ShowTag(id);
}
