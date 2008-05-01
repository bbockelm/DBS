function _footerMenuText(mode){
    return [
{label: "Home", link: "_advanced?userMode="+mode, title: "DBS Data Discovery home page"},
{label: "aSearch", link: "_advanced?userMode="+mode, title: "Advanced keyword search"},
{label: "Navigator", link: "_navigator?userMode="+mode, title: "Navigator: menu-driven search"},
//{label: "Finder", link: "_finder?userMode="+mode, title: "Finder: query-driven search"},
//{label: "Analysis", link: "_analysis?userMode="+mode, title: "Search analysis datasets"},
{label: "RSS", link: "_rss?userMode="+mode, title: "RSS Feeds publish frequently updates about your data"},
{label: "Sites", link: "_siteSearch?userMode="+mode, title: "Site search: site-based search"},
{label: "Runs", link: "_runs?userMode="+mode, title: "Run search: run-based search"},
//{label: "Pages", link: "_pages?userMode="+mode, title: "Data discovery world-wide pages"},
{label: "Help", link: "_help?userMode="+mode, title: "Help: DBS glossary, terms, feedback form"},
{label: "Contact", link: "_contact?userMode="+mode, title: "Contact DBS support team"},
{label: "View", id:"", link: "javascript:ChangeView()", title: "Define the data view", position: "right"}
           ]
}
