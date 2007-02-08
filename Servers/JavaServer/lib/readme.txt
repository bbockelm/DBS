The following 7 jar files are not used to build the JavaServer code. They are used for enabling the GSI suthentication mechanism in the tomcat web server. 

The instructions for enabling gridmap based authorization and authentication in tomcat 5.5.15

1) Copy the following 5 jar files into $TOMCAT_HOME/common/lib/
cog-jglobus.jar
bcprov.jar
puretls.jar
cryptix32.jar
cryptix-asn1.jar


2) Copy the following 2 jar files into $TOMCAT_HOME/server/lib
cog-tomcat.jar
cog-gridmap.jar

3) Insert the following tag in the $TOMCAT_HOME/conf/server.xml file near the connector tag for port 8080. Comment out the whole tag for port 8080 and insert these lines instead. Change the attributes  values for cert and key accordingly. These should reflect the hostcert and hostkey of your host.

   <Connector
            className="org.globus.tomcat.coyote.net.HTTPSConnector"
            port="8443"
            maxThreads="150" minSpareThreads="25" maxSpareThreads="75"
            enableLookups="false" disableUploadTimeout="true"
            acceptCount="100" debug="0" scheme="https" autoFlush="true"
            protocolHandlerClassName="org.apache.coyote.http11.Http11Protocol"
            socketFactory="org.globus.tomcat.catalina.net.BaseHTTPSServerSocketFactory"
            cert="/home/sekhri/tomcat/apache-tomcat-5.5.15/conf/hostcert.pem"
            key="/home/sekhri/tomcat/apache-tomcat-5.5.15/conf/hostkey.pem"
            cacertdir="/etc/grid-security/certificates"
            mode="ssl"
            />

4) Insert the following tag after the Engine tag. Comment out any other Valve tag. Steps 3 and 4 will enable the certificate based GSI authentication ove SSL

	<Valve className="org.globus.tomcat.coyote.valves.HTTPSValve55"/>


5) Now to enable the gridmap based authorization insert the following tag right after the Valve tag that you inserted in step 4. DO NOT comment the Valve tag that you inserted in step 4. Both these tag should be present in the server.xml file

           <Valve
                   className="org.globus.tomcat.coyote.valves.GridmapFilterValve55"
                   gridmapfile="/home/sekhri/tomcat/apache-tomcat-5.5.15/conf/gridmap-file"
                   allowUnescured="false"
                   meaningfulErrors="true"
                   passRequest="true"
                   />

6) Generate a gridmap file in the same location that you mentioned in step 5 for gridmapfile attribute in Valve tag. This file should exists before you restart the server.

7) Restart the tomcat server and look for any exception in the log file $TOMCAT_HOME/logs/catlina.out


To get the user DN information in the servlet code use the following code
	String dn = request.getAttribute("org.globus.gsi.authorized.user.dn");
	where request is javax.servlet.http.HttpServletRequest


To generate the role based gridmap file periodically

1) Checkout the GridmapAuthorization utility from CVS
cvs co DBS/Utils/GridmapAuthorization
2) Change into the directory
cd DBS/Utils/GridmapAuthorization
3) Edit setup.sh 
 change JAVA_HOME accordingly
4) Setup the environment
source setup.sh
5) Compile the code
javac VO2GridMap.java
6) Edit vo2gridmap to change the location where you want to generate the gridmap file. This location will be same as the one specified in tomcat. You can also edit the URL of the VOMS server and the group and role from where you want to generate the gridmap file server.xml file <br>
7) Execute the script 
./vo2gridmap<br>
Check if you gridmap file is generated
8) Put this script (./vo2gridmap) in the crontab to be updated every 30 minutes
crontab -e
30     *     *     *     *    /Location_of_this_script/vo2gridmap
