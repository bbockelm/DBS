function MakeTooltip(_tag,_ctx,_text,autodismissdelay) {
  var delay=5000;
  if(autodismissdelay) {
     delay=autodismissdelay;
  }
  t = new YAHOO.widget.Tooltip(_tag, { context: _ctx, text: '<div class="tiny">'+_text+'</div>', width:'400px', autodismissdelay:delay } );
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


