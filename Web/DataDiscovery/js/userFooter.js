function footerMenuText(){
    return [
{label: "Navigator", link: "_navigator?userMode=user", title: "Navigator: menu-driven search"},
{label: "Finder", link: "_finder?userMode=user", title: "Finder: query-driven search"},
{label: "Analysis", link: "_analysis?userMode=user", title: "Search analysis datasets"},
{label: "RSS", link: "_rss?userMode=user", title: "RSS Feeds publish frequently updates about your data"},
{label: "Site Search", link: "_siteSearch?userMode=user", title: "Site search: site-based search"},
{label: "Help", link: "_help?userMode=user", title: "Help: DBS glossary, terms, feedback form"},
{label: "Contact", link: "_contact?userMode=user", title: "Contact DBS support team"},
{title: "Physicist view of DBS", link: "index?userMode=user", label: "Physicist", position: "right"},
{title: "Production view of DBS, contains more detail for site admins and production operators.", link: "index?userMode=expert", label: "Production", position: "right"},
{title: "Run Manager view of DBS", link: "_runs?userMode=user", label: "RunMgr", position: "right"}
           ]
}
