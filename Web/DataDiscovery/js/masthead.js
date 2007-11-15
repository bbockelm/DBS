function insertMastHead (page, title){
	doStyle(page);
	/* Would be better to add this as the first child */
	document.body.appendChild(buildMenu(page, title));
}			

function buildMenu(page,title){
	var navbar = document.createElement ("div");
	YAHOO.util.Dom.addClass(navbar, "masthead");
	var navbarL = document.createElement ("div");
	YAHOO.util.Dom.addClass(navbarL, "mastheadLeft");

	sites = menuText();
	var mainUlL = document.createElement ("ul");

	var logo  = document.createElement ("img");
	logo.setAttribute ("src","css/Common/images/logomini.png");
	logo.setAttribute ("height","16");
	logo.setAttribute ("width","16");
	logo.setAttribute ("alt","CMS - Compact Muon Solenoid");
	YAHOO.util.Dom.addClass(logo, "mastheadLogo");

	var imgitem = document.createElement ("li");
	imgitem.appendChild(logo);
	mainUlL.appendChild(imgitem);
	
	for ( i = 0; i < sites.length; i++)
 			{
		var item = document.createElement ("li");
		item.setAttribute ("id",sites[i].id);
		var a = document.createElement ("a");
		a.setAttribute ("href", sites[i].link);
		a.setAttribute ("title", sites[i].title);
		changeText (a, sites[i].label);
		item.appendChild (a);
		mainUlL.appendChild (item);
	}
	navbarL.appendChild(mainUlL);
	
	navbar.appendChild(navbarL);
	
	var navbarR = document.createElement ("div");
	YAHOO.util.Dom.addClass(navbarR ,"mastheadRight");
	var mainUlR = document.createElement ("ul");
	var item = document.createElement ("li");
	changeText (item, getLoginObject());
	mainUlR.appendChild(item);
	navbarR.appendChild(mainUlR);
	navbar.appendChild(navbarR);
	if (title != undefined) {
		navbar.appendChild(createSiteMasthead(title))
	}
	return navbar;
}

function getLoginObject(){
	// Login will be an object containing name, role(s), and login type (cert/HN)
	// Some call to Security Module, page should be enough to work out role
	// Will show "You are logged in as Simon Metson | more"
	// Clicking on "more" will bring up a panel with information about your login (cert/HN, roles) and a logout button.
	// If not logged in will show "Click here to login"
	//return "You are logged in as Simon Metson | more";
	return "You are not logged in";
}

function doStyle(page){
//put into loop
	var cssNode = document.createElement('link');
	cssNode.setAttribute('rel', 'stylesheet');
	cssNode.setAttribute('type', 'text/css');
	cssNode.setAttribute('href', 'css/Common/css/dmwt_main.css');
	document.getElementsByTagName('head')[0].appendChild(cssNode); 
	
	cssNode = document.createElement('link');
	cssNode.setAttribute('rel', 'stylesheet');
	cssNode.setAttribute('type', 'text/css');
	var ie = (document.all) ? true : false;
	if (ie) {
		cssNode.setAttribute('href', 'css/Common/css/dmwt_masthead_ie.css');
	} else { 
		cssNode.setAttribute('href', 'css/Common/css/dmwt_masthead.css');
	}	
	document.getElementsByTagName('head')[0].appendChild(cssNode); 
	
	cssNode = document.createElement('link');
	cssNode.setAttribute('rel', 'stylesheet');
	cssNode.setAttribute('type', 'text/css');
	cssNode.setAttribute('href', 'css/Common/css/dmwt_masthead_' + page + '.css');
	document.getElementsByTagName('head')[0].appendChild(cssNode); 
}
	
function menuText(){
	//One day this will come from a database or something...
	return [/*{title: "ASAP - Job submission and monitoring. Currently not available.", link: "na", label: "ASAP"}, */
	{title: "CMS Dashboard - Monitorring of jobs, transfers, IO rate, Tier 0.", link: "http://arda-dashboard.cern.ch/cms/", label: "Dashboard", id: "dashboard"}, 
	{title: "DBS/DLS - Data set book keeping and location.", link: "http://cmsdbs.cern.ch/DBS2_discovery/", label: "DBS/DLS", id:"dbs"},
	{title: "ProdRequest - Request large scale official production of samples.", link: "https://cmsweb.cern.ch/prodrequest/", label: "ProdRequest", id:"prodrequest"},
	{title: "PhEDEx - Data placement, transfer monitoring", link: "http://cmsdoc.cern.ch/cms/aprom/phedex/", label: "PhEDEx", id:"phedex"},
	{title: "SiteDB - Site information and aggregate monitoring", link: "https://cmsweb.cern.ch/sitedb/sitelist", label: "SiteDB", id: "sitedb"},
	{title: "CondDB - Conditions Database", link: "https://cmsweb.cern.ch/conddb/", label: "CondDB", id: "conddb"}]	
}
function createSiteMasthead(title){
	YAHOO.namespace("cms.dmwt");
	var footer = document.createElement ("div");
	YAHOO.util.Dom.addClass(footer, "mastheadfooter");
	var navbarRight = document.createElement ("div");
	YAHOO.util.Dom.addClass(navbarRight ,"mhfootright");
	var navbarLeft = document.createElement ("div");
	YAHOO.util.Dom.addClass(navbarLeft ,"mhfootleft");
	var leftUl = document.createElement ("ul");
	var rightUl = document.createElement ("ul");
	sites = footerMenuText();
	var item = document.createElement ("li");
	changeText (item, title);
	leftUl.appendChild (item);
	var l = 0;
	var r = 0;
	for ( i = 0; i < sites.length; i++){
		var item = document.createElement("li");
		var a = document.createElement("a");
		a.setAttribute ("href", sites[i].link);
		a.setAttribute ("title", sites[i].title);
		changeText (a, sites[i].label);
		if (sites[i].position == 'right'){
			if ( r > 0 ){
				changeText (item, " - ");
			}
			item.appendChild (a);
			rightUl.appendChild (item);
			r++;			
		}else{
			if ( l > 0 ){
				changeText (item, " - ");
			}
			item.appendChild (a);		
			leftUl.appendChild (item);
			l++;
		}
	}
	navbarLeft.appendChild(leftUl);
	footer.appendChild(navbarLeft);
	navbarRight.appendChild(rightUl);
	footer.appendChild(navbarRight);
	return footer
}

function changeText (elem, text){
	var hasInnerText = (document.getElementsByTagName("body")[0].innerText != undefined) ? true : false;
	if (!hasInnerText)
	{
		elem.textContent = text;
	}
	else
	{
		elem.innerText = text;
	}
}
