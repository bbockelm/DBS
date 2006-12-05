BASE=$PWD/..
if [ "${JAVA_HOME}" = "" ]; then
	echo "Error! Please set your JAVA_HOME variable"
	exit 1
fi
savePWD=$PWD
CLASSPATH=$CLASSPATH:$PWD/WEB-INF/classes/
cd $BASE/lib
CLASSPATH=.:$PWD/ojdbc14_g.jar:$PWD/mysql-connector-java-5.0.3-bin.jar:$PWD/servlet-api.jar:$JAVA_HOME/jre/lib/rt.jar
cd $BASE/src
		
javadoc -d ../doc -windowtitle "DBS API Documenation"  -header '<a name="Data_Set_Bookeeping_System"></a> <font size=5 color=#ff0000><b>Data Set Bookeeping System</b></font><br><p /> 
The CMS Dataset Bookkeeping System (DBS) is a database and user API that indexes event-data data for the CMS Collaboration.  The primary functionality is to provide cataloging by production and analysis operations and allow for data discovery by CMS physicists.  The place of the DBS within the Data Management Project is described in Chapter 4 of the <a href="http://cmsdoc.cern.ch/cms/cpt/tdr/" target="_top">Computing TDR</a>.
<p />
<p />
' -bottom '<a name="Contact_Information"></a> <font size=5 color=#ff0000><b>Contact Information </b></font>

<p />
Problems reported to <a href="mailto:cms-dbs-support@cernNOPAMSMAN.ch">cms-dbs-support@cern.ch</a> 
<p />
<table cellspacing="1" cellpadding="1" class="twikiTable" border="0"><tr><td bgcolor="#eaeaea" class="twikiFirstCol"> Shifter </td><td bgcolor="#eaeaea"> Location </td><td bgcolor="#eaeaea"> Work Phone </td><td bgcolor="#eaeaea"> Home/Cell/Pager </td><td bgcolor="#eaeaea"> email </td></tr>
<tr><td bgcolor="#ffffff" class="twikiFirstCol"> Lueking </td><td bgcolor="#ffffff"> FNAL </td><td bgcolor="#ffffff"> 1.630.840.8236 </td><td bgcolor="#ffffff"> 1.630.761.8346/-/1.630.218.8920 </td><td bgcolor="#ffffff"> <a href="mailto:lueking@fnalNOPAMSMAN.gov">lueking@fnal.gov</a> </td></tr>

<tr><td bgcolor="#eaeaea" class="twikiFirstCol"> Afaq </td><td bgcolor="#eaeaea"> FNAL </td><td bgcolor="#eaeaea"> 1.630.840.6856 </td><td bgcolor="#eaeaea"> 1.630.803.3951 </td><td bgcolor="#eaeaea"> <a href="mailto:anzar@fnalNOPAMSMAN.gov">anzar@fnal.gov</a> </td></tr>
<tr><td bgcolor="#ffffff" class="twikiFirstCol"> Sekhri </td><td bgcolor="#ffffff"> FNAL </td><td bgcolor="#ffffff"> 1.630.840.6507 </td><td bgcolor="#ffffff"> 1.630.551.0286/1.630.301.0986 </td><td bgcolor="#ffffff"> <a href="mailto:sekhri@fnalNOPAMSMAN.gov">sekhri@fnal.gov</a> </td></tr>

<tr><td bgcolor="#eaeaea" class="twikiFirstCol"> Blumenfeld </td><td bgcolor="#eaeaea"> JHU- Baltimore </td><td bgcolor="#eaeaea"> 1.410.516.7358 </td><td bgcolor="#eaeaea"> 1.410.252.5814 </td><td bgcolor="#eaeaea"> <a href="mailto:Barry.Blumenfeld@jhuNOPAMSMAN.edu">Barry.Blumenfeld@jhu.edu</a> </td></tr>
<tr><td bgcolor="#ffffff" class="twikiFirstCol"> Kuznetsov </td><td bgcolor="#ffffff"> Cornell - Ithaca </td><td bgcolor="#ffffff"> 1.607.254.2776 </td><td bgcolor="#ffffff"> 1.607.256.9644/1.607.229.0803/- </td><td bgcolor="#ffffff"> <a href="mailto:vk@mailNOPAMSMAN.lns.cornell.edu">vk@mail.lns.cornell.edu</a> </td></tr>

<tr><td bgcolor="#eaeaea" class="twikiFirstCol"> Maletic </td><td bgcolor="#eaeaea"> CERN </td><td bgcolor="#eaeaea"> 74131, 74641 </td><td bgcolor="#eaeaea"> . </td><td bgcolor="#eaeaea"> <a href="mailto:Dimitrije.Maletic@cernNOPAMSMAN.ch">Dimitrije.Maletic@cern.ch</a> </td></tr>
<tr><td bgcolor="#ffffff" class="twikiFirstCol"> Teodoro </td><td bgcolor="#ffffff"> CERN </td><td bgcolor="#ffffff"> 71606 </td><td bgcolor="#ffffff"> . </td><td bgcolor="#ffffff"> <a href="mailto:Douglas.Teodoro@cernNOPAMSMAN.ch">Douglas.Teodoro@cern.ch</a> </td></tr>

</table>
<p />' -footer '<a name="Project_Organization"></a> <font size=5 color=#ff0000><b>Project Organization </b></font> 
<p /> <ul>
<li> <b>Project Leader</b>: Lee Lueking <br>
</li> <li> <b>Design</b>: Dan Riley, Drew Dolgert, Chris Jones, Vijay Sekhri, Anzar Afaq, Valentin Kuznetsov <br>
</li> <li> <b>Developers</b>: Vijay Sekhri, Anzar Afaq, Valentin Kuznetsov <br>
</li> <li> <b>Operations Team</b>: Vijay Sekhri, Anzar Afaq, Valentin Kuznetsov, Barry Blumenfeld, Lee Lueking, Douglas Teodora, Dimitrije Maletic <br>

</li></ul>'  -subpackages java:javax.swing -classpath $CLASSPATH db  dbs  xml dbs.api dbs.sql dbs.util -linksource -link http://java.sun.com/j2se/1.4.2/docs/api/ -link http://java.sun.com/j2ee/sdk_1.3/techdocs/api/ -link http://jakarta.apache.org/commons/dbcp/apidocs/

cd $savePWD
