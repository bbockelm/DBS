function init() {
  if(!first) {
      first = 1;
      _dbs  = null;
      _app  = null;
      _prim = null;
      _tier = null;
      _site = null;
  }
}
function updateSelection(selector, newOptions, firstElement) {
  if(firstElement) {
    var optionElement=document.createElement("option")
    optionElement.value = firstElement
    //optionElement.text  = firstElement
    optionElement.appendChild(document.createTextNode(firstElement))
    selector.appendChild(optionElement)
  }
  for ( index=0; index != newOptions.length;++index) {
    var name = newOptions[index]
    var optionElement=document.createElement("option")
    if( name==firstElement ) {
       continue
    }
    optionElement.value = name
    //optionElement.text  = name
    optionElement.appendChild(document.createTextNode(name))
    selector.appendChild(optionElement)
  }
}
function updateLayer(dbsInst) {
  var app = document.getElementById("appHolder")
  if( app.lastChild ) {
      app.removeChild(app.lastChild)
  }
  var selectElement = document.createElement("select")

  //get the choice made
  appObj = navDict.n[ dbsInst.value ]

  //redraw the menu for layer1
  updateSelection(selectElement, appObj.l, _app )
  app.appendChild(selectElement)
  //selectElement.setAttribute("onChange",'updateLayer1(this)');
  // The following structure seems to work with Firefox, Safari, IE
  selectElement.onchange=new Function("return updateLayer1(this);");
  selectElement.setAttribute("id",'appSelector');
  selectElement.setAttribute("name",'app');
  updateLayer1(selectElement)
}
function updateLayer0(selector) {
  var app = document.getElementById("appHolder")
  if( app.lastChild ) {
      app.removeChild(app.lastChild)
  }
  var selectElement = document.createElement("select")

  //get the choice made
  appObj = navDict.n[ selector.options[selector.selectedIndex].value ]

  //redraw the menu for layer1
  updateSelection(selectElement, appObj.l, _app)
  app.appendChild(selectElement)
  //selectElement.setAttribute("onChange",'updateLayer1(this)');
  selectElement.onchange=new Function("return updateLayer1(this);");
  selectElement.setAttribute("id",'appSelector');
  selectElement.setAttribute("name",'app');
  updateLayer1(selectElement)
}

function updateLayer1(selector) {
  var prim = document.getElementById("primHolder")
  if( prim.lastChild ) {
      prim.removeChild(prim.lastChild)
  }
  var selectElement = document.createElement("select")

  primObj = appObj.n[ selector.options[selector.selectedIndex].value ]
  updateSelection(selectElement, primObj.l,_prim)
  prim.appendChild(selectElement)
  //selectElement.setAttribute("onChange",'updateLayer2(this)');
  selectElement.onchange=new Function("return updateLayer2(this);");
  selectElement.setAttribute("id",'primSelector');
  selectElement.setAttribute("name",'primD');
  updateLayer2(selectElement)
}
function updateLayer2(selector) {
  var tier = document.getElementById("tierHolder")
  if( tier.lastChild ) {
      tier.removeChild(tier.lastChild)
  }
  var selectElement = document.createElement("select")

  tierObj = primObj.n[ selector.options[selector.selectedIndex].value ]
  updateSelection(selectElement, tierObj.l,_tier)
  selectElement.setAttribute("id",'tierSelector');
  selectElement.setAttribute("name",'tier');
  tier.appendChild(selectElement)
  init()
}
function updateSites(selector) {
  var site = document.getElementById("form2_siteHolder");
  if( site.lastChild ) {
      site.removeChild(site.lastChild);
  }
  var selectElement = document.createElement("select");

  siteObj = siteDict.n[ selector.options[selector.selectedIndex].value ];
  updateSelection(selectElement, siteObj.l, _site);
  site.appendChild(selectElement);
  selectElement.setAttribute("id",'form2_siteSelector');
  selectElement.setAttribute("name",'site');
  init();
}
