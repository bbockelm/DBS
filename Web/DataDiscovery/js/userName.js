/*
 HTML code example:

    <form>
    Please enter your name: <input type="text" name="nameinput">
    <br><br>
    <input type="button" value="Save to Cookie" onclick="set_name(this.form)">
    </form>

or even better to make a greeting and remember form value

<html>
<script type="text/javascript" src="userName.js"></script>
<body onload="setGreeting()">

<span id="Greeting"></span>

    <form>
    Login: <span id="formInputName"></span>
    <script type="text/javascript">formRequest()</script>
    <br><br>
    <input type="button" value="Save to Cookie" onclick="set_name(this.form)">
    </form>

</body>
</html>
*/

function setGreeting() {
   var cookie_user=GetCookie("DBSDD_username");
   var greeting;
   if (cookie_user=='guest') {
       // to be freindly I made a link to history menu as I did in CheetahTemplate.py
       // see set hList
       cookie_user='<a href="javascript:showMenu(\'History\');showHistoryMenu(\'auth\',[\'user\',\'auth\',\'search\'])">'+cookie_user+'</a>'
       greeting='Hello '+cookie_user+'.';
   } else {
       greeting='Welcome <span class="box_blue">'+cookie_user+'</span>!';
   }
   msg='<span class="sectionhead_tight">'+greeting+'</span>';
   var t=document.getElementById("Greeting");
   if (t) {
       t.innerHTML=msg;
   }
}
function formRequest() {
   var cookie_user=GetCookie("DBSDD_username");
   if (cookie_user=="guest") {
       cookie_user="";
   }
   var cookie_pass=GetCookie("DBSDD_password");
   var pass_value='';
   if (cookie_pass) {
       pass_value='value="'+cookie_pass+'"';
   }
   var t=document.getElementById("formInputName");
   if (t) {
       var user='<input type="text" name="nameinput" value="'+cookie_user+'" />';
       var pass='<input type="password" name="passinput" '+pass_value+' />';
       t.innerHTML='<table><tr><td align="right">Login</td><td>'+user+'</td></tr><tr><td align="right">Password</td><td>'+pass+'</td></tr></table>';
   }
}

function getCookieVal (offset) {
  var endstr = document.cookie.indexOf (";", offset);
  if (endstr == -1)
  endstr = document.cookie.length;
  return unescape(document.cookie.substring(offset, endstr));
}

function GetCookie (name) {
  var arg = name + "=";
  var alen = arg.length;
  var clen = document.cookie.length;
  var i = 0;
  while (i < clen) {
    var j = i + alen;
    if (document.cookie.substring(i, j) == arg)
    return getCookieVal (j);
    i = document.cookie.indexOf(" ", i) + 1;
    if (i == 0) break;
  }
  if(name=='DBSDD_username') {
     return "guest";
  }
  return null;
}

function SetCookie (name, value) {
  var argv = SetCookie.arguments;
  var argc = SetCookie.arguments.length;
  var expires = (argc > 2) ? argv[2] : null;
  var path = (argc > 3) ? argv[3] : null;
  var domain = (argc > 4) ? argv[4] : null;
  var secure = (argc > 5) ? argv[5] : false;
  document.cookie = name + "=" + escape (value) +
   ((expires == null) ? "" : ("; expires=" + expires.toGMTString())) +
   ((path == null) ? "" : ("; path=" + path)) +
   ((domain == null) ? "" : ("; domain=" + domain)) +
   ((secure == true) ? "; secure" : "");
}

function set_name(form) {
  var expdate = new Date ();
  // set expiration = 1 year
  // 1 year = 365 days
  // 1 day  = 24 hours
  // 1 hour = 60 min
  // 1 min  = 60 sec
  // 1 sec  = 1000 msec
  var expTime= 1000*60*60*24*365;
  expdate.setTime (expdate.getTime() + expTime);
  var username = form.nameinput.value
  var password = form.passinput.value
  if (username != "") {
      SetCookie ("DBSDD_username", username, expdate);
      SetCookie ("DBSDD_password", password, expdate);
//      window.history.go(0);
  } else {
    alert("Geez, at least enter something, entering nothing will cause an error.");
  }
//  var id=document.getElementById("formAuthResults");
//  msg='<p><div class="box_blue">Thank you '+username+'! Your authentication is stored and you may use your history at any time.</span></p>'; 
//  return msg;
}
