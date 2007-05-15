function footerMenuText(){
        return [
{label: "Navigator", link: "_navigator?userMode=expert", title: "Navigator: menu-driven search"},
{label: "Finder", link: "_finder?userMode=expert", title: "Finder: query-driven search"},
{label: "Config", link: "_config?userMode=expert", title: "Config search: parameter set search"},
{label: "Analysis", link: "_analysis?userMode=expert", title: "Search analysis datasets"},
{label: "RSS", link: "_rss?userMode=expert", title: "RSS Feeds publish frequently updates about your data"},
{label: "Site Search", link: "_siteSearch?userMode=expert", title: "Site search: site-based search"},
//    {label: "History", link: "_history?userMode=expert", title: "History: persistent history of user queries"},
{label: "Help", link: "_help?userMode=expert", title: "Help: DBS glossary, terms, feedback form"},
{label: "Contact", link: "_contact?userMode=expert", title: "Contact DBS support team"},
{title: "Physicist view of DBS", link: "index?userMode=user", label: "Physicist", position: "right"},
{title: "Production view of DBS, contains more detail for site admins and production operators.", link: "index?userMode=expert", label: "Production", position: "right"},
    ]
{title: "Run Manager view of DBS", link: "index?userMode=runManager", label: "RunMgr", position: "right"},
}
