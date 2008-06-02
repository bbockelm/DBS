function MakeTooltip(_tag,_ctx,_text,autodismissdelay,width) {
  var delay=5000;
  if(autodismissdelay) {
     delay=autodismissdelay;
  }
  _width='400px';
  if(width) {
     _width=width;
  }
  //t = new YAHOO.widget.Tooltip(_tag, { context: _ctx, text: '<div class="tooltip">'+_text+'</div>', width:_width, autodismissdelay:delay, zIndex:9999 } );
  t = new YAHOO.widget.Tooltip(_tag, { context: _ctx, text: _text, width:_width, autodismissdelay:delay, zIndex:9999 } );
}

function PanelInit(myvar,tagName) {
    var panel_one;
    YAHOO.example.panels = function() {
            panel_one = new YAHOO.widget.Panel("panel_one", 
                    { 
                            close:true,  
                            visible:false,  
                            draggable:false
                    } 
            ); 
            panel_one.render();
    };
    YAHOO.util.Event.addListener(window,'load',YAHOO.example.panels);
}
/*
function insertSiteMasthead(view){
        YAHOO.namespace("cms.dmwt");
        var sdbfooter = document.createElement ("div");
        YAHOO.util.Dom.addClass(sdbfooter, "mastheadfooter");
        var mainUl = document.createElement ("ul");
        // create menu items
        var item = document.createElement ("li");
        changeText (item, "Menu :: ");
        mainUl.appendChild (item);

        if (view=='expert') {
            sites = footerExpertMenuText();
        } else if (view=='dbsExpert') {
            sites = footerDBSMenuText();
        } else {
            sites = footerUserMenuText();
        }
        for ( i = 0; i < sites.length; i++){
                var item = document.createElement("li");
                var a = document.createElement("a");
                a.setAttribute ("href", sites[i].link);
                a.setAttribute ("id", sites[i].label);
                a.setAttribute ("title", sites[i].title);
                if(sites[i].onclick) {
                   a.setAttribute("onclick", sites[i].onclick);
                }
                changeText (a, sites[i].label);
                if ( i > 0 ){
                        changeText (item, " - ");
                }
                item.appendChild (a);
                mainUl.appendChild (item);
        }

        // add spacer
        var item = document.createElement ("li");
        var s = document.createElement("spacer");
        s.setAttribute ("type", "block");
        s.setAttribute ("width", "50");
        changeText (s, "");
        item.appendChild(s)
        mainUl.appendChild (item);
        var item = document.createElement ("li");
        changeText (item, "Views :: ");
        mainUl.appendChild (item);

        // Create DBS views
        var item = document.createElement("li");
        var a = document.createElement("a");
        a.setAttribute ("href", "index?userMode=user");
        a.setAttribute ("id", "expertView");
        a.setAttribute ("title", "Physicists view on DBS");
        changeText (a, "Physicists ");
        item.appendChild(a);
        mainUl.appendChild (item);
        var item = document.createElement("li");
        var a = document.createElement("a");
        a.setAttribute ("href", "index?userMode=expert");
        a.setAttribute ("id", "userView");
        a.setAttribute ("title", "Production (more detailed) view on DBS, ");
        changeText (a, "- Production");
        item.appendChild(a);
        mainUl.appendChild (item);

        // add footer
        sdbfooter.appendChild(mainUl);
        YAHOO.cms.dmwt.masthead.appendToFooter(sdbfooter);
        YAHOO.cms.dmwt.masthead.render(document.body);
        YAHOO.cms.dmwt.masthead.show();
}
function footerUserMenuText(){
    return [
            {label: "Navigator", link: "_navigator?userMode=user", title: "Navigator: menu-driven search"},
            {label: "Finder", link: "_finder?userMode=user", title: "Finder: query-driven search"},
//            {label: "Config", link: "_config?userMode=user", title: "Config search: parameter set search"},
            {label: "RSS", link: "_rss?userMode=user", title: "RSS Feeds publish frequently updates about your data"},
//            {label: "History", link: "_history?userMode=user", title: "History: persistent history of user queries"},
            {label: "Help", link: "_help?userMode=user", title: "Help: DBS glossary, terms, feedback form"}
           ]
}
function footerExpertMenuText(){
    return [
            {label: "Navigator", link: "_navigator?userMode=expert", title: "Navigator: menu-driven search"},
            {label: "Finder", link: "_finder?userMode=expert", title: "Finder: query-driven search"},
            {label: "Config", link: "_config?userMode=expert", title: "Config search: parameter set search"},
            {label: "RSS", link: "_rss?userMode=expert", title: "RSS Feeds publish frequently updates about your data"},
            {label: "Site Search", link: "_siteSearch?userMode=expert", title: "Site search: site-based search"},
            {label: "History", link: "_history?userMode=expert", title: "History: persistent history of user queries"},
            {label: "Help", link: "_help?userMode=expert", title: "Help: DBS glossary, terms, feedback form"}
           ]
}
function footerDBSMenuText() {
    return [{label: "DBS experts", link: "_dbsExpert?userMode=dbsExpert", title: "DBS experts"}]
}

*/

/*
YAHOO.example.onMenuBarReady = function() {
    
    // "beforerender" event handler for the menu bar

    function onMenuBarBeforeRender(p_sType, p_sArgs, p_oMenu) {

        var oSubmenuData = {
        
            "communication": [ 
            
                { text: "360", url: "http://360.yahoo.com" },
                { text: "Alerts", url: "http://alerts.yahoo.com" },
                { text: "Avatars", url: "http://avatars.yahoo.com" },
                { text: "Groups", url: "http://groups.yahoo.com " },
                { text: "Internet Access", url: "http://promo.yahoo.com/broadband" },
            ],

            "shopping": [

                { text: "Auctions", url: "http://auctions.shopping.yahoo.com" },
                { text: "Autos", url: "http://autos.yahoo.com" },
                { text: "Classifieds", url: "http://classifieds.yahoo.com" },
            ]
        };


        this.getItem(0).cfg.setProperty("submenu", { id:"communication", itemdata: oSubmenuData["communication"] });
        this.getItem(1).cfg.setProperty("submenu", { id:"shopping", itemdata: oSubmenuData["shopping"] });
    }


    // Instantiate and render the menu bar
    var oMenuBar = new YAHOO.widget.MenuBar("productsandservices", { autosubmenudisplay:true, showdelay:250, hidedelay:750, lazyload:true });
    // Subscribe to the "beforerender" event
    oMenuBar.beforeRenderEvent.subscribe(onMenuBarBeforeRender, oMenuBar, true);
    // Render the menu bar
    oMenuBar.render();
};

// Initialize and render the menu bar when it is available in the DOM
YAHOO.util.Event.onContentReady("productsandservices", YAHOO.example.onMenuBarReady);
*/


//YAHOO.widget.MenuBarItem.prototype.IMG_ROOT = "YUI/menu/assets/";
//YAHOO.widget.MenuBarItem.prototype.SUBMENU_INDICATOR_IMAGE_PATH = "menuarorght8_nrm_1.gif";
//YAHOO.widget.MenuBarItem.prototype.SELECTED_SUBMENU_INDICATOR_IMAGE_PATH = "menuarorght8_hov_1.gif";
//YAHOO.widget.MenuBarItem.prototype.DISABLED_SUBMENU_INDICATOR_IMAGE_PATH = "menuarorght8_dim_1.gif";
/*
function commonMenu(tag,iMenu) {
    if($(tag)) {
        $(tag).innerHTML='';
        var oMenu = new YAHOO.widget.MenuBar("mymenubar", 
                    { trigger:document,
                      submenualignment:['tl','tr'],
                      autosubmenudisplay:true,
                      showdelay:250,
                      hidedelay:750, 
                      lazyload:true
                    } );
        oMenu.addItems(iMenu);
        oMenu.render(tag);
    }
}
*/


function AddNode(subList,name,node,admin) {
    var text, flag, rval;
    try {
        var nList = subList[name]
        for(var j=0;j<nList.length;j++) {
            //var text=nList[j][0]+' ('+nList[j][1]+')';
            flag=nList[j][0];
            rval=nList[j][1];
            if(admin==1) {
                text='<input type="text" name="'+flag+'" size="5"/> - <span class="tiny">'+flag+'</span>';
            } else {
                if (rval=='GOOD') {
                   text='<img src="images/choice-yes.gif" alt="GOOD" />-<span class="tiny">'+flag+'</span>';
                } else {
                   text='<img src="images/choice-no.gif" alt="'+rval+'" />-<span class="tiny">'+flag+'</span>';
                }
            }
            var nodeName = new YAHOO.widget.TextNode(text,node,false);
            AddNode(subList,nList[j][0],nodeName);
        }
    } catch(err) {
    }
}
function BuildNode(tree,name,tableList,subList,admin) {
    var tList = tableList[name];
    var text,flag,rval,subnode;
    for(var i=0;i<tList.length;i++) {
        flag=tList[i][0];
        rval=tList[i][1];
        if (admin==1) {
            text='<input type="text" name="'+flag+'" size="5"/> - <span class="tiny">'+flag+'</span>';
        } else {
            if (rval=='GOOD') {
               text='<img src="images/choice-yes.gif" alt="GOOD" />-<span class="tiny">'+flag+'</span>';
            } else {
               text='<img src="images/choice-no.gif" alt="'+rval+'" />-<span class="tiny">'+flag+'</span>';
            }
        }
        subnode = new YAHOO.widget.TextNode(text, tree.getRoot(), false);
        AddNode(subList,flag,subnode,admin);
    } 
}
function BuildNodeTree(tag,name,tableList,subList,admin) {
    var tree = new YAHOO.widget.TreeView(tag);
    BuildNode(tree,name,tableList,subList,admin);
    tree.draw();
}
function ExpandView(tag) {
   $(tag).innerHTML='<a href="javascript:CollapseView()">collapse</a>'
   tree.expandAll();
}
function CollapseView() {
   $(tag).innerHTML='<a href="javascript:ExpandView()">expand</a>'
   tree.collapseAll();
}
