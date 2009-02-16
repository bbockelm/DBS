function _footerMenuText(host,mode){
    // always check provided host and append / at the end
    if (host[host.length]!='/') {
        host=host+'/';
    }
    return [
{label: "Home", link: host+"_advanced?userMode="+mode, title: "DBS Data Discovery home page"},
{label: "aSearch", link: host+"_advanced?userMode="+mode, title: "Advanced keyword search"},
{label: "Navigator", link: host+"_navigator?userMode="+mode, title: "Navigator: menu-driven search"},
//{label: "Finder", link: "_finder?userMode="+mode, title: "Finder: query-driven search"},
//{label: "Analysis", link: "_analysis?userMode="+mode, title: "Search analysis datasets"},
{label: "RSS", link: host+"_rss?userMode="+mode, title: "RSS Feeds publish frequently updates about your data"},
//{label: "Sites", link: host+"_siteSearch?userMode="+mode, title: "Site search: site-based search"},
{label: "Status", link: host+"_status?userMode="+mode, title: "DBS instances status page"},
{label: "Runs", link: host+"_runs?userMode="+mode, title: "Run search: run-based search"},
//{label: "Pages", link: "_pages?userMode="+mode, title: "Data discovery world-wide pages"},
{label: "Admin", link: host+"_admin?userMode=expert", title: "Administrate dataset/block/LFNs in DBS"},
{label: "Tools", link: host+"_tools?userMode="+mode, title: "DBS user-friendly tools"},
{label: "Help", link: host+"_help?userMode="+mode, title: "Help: DBS glossary, terms, feedback form"},
{label: "Contact", link: host+"_contact?userMode="+mode, title: "Contact DBS support team"},
{label: "View", id:"", link: "javascript:ChangeView()", title: "Define the data view", position: "right"}
           ]
}
