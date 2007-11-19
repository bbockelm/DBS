<html xmlns="http://www.w3.org/1999/xhtml"
xmlns:ui="http://java.sun.com/jsf/facelets"
xmlns:h="http://java.sun.com/jsf/html"
xmlns:f="http://java.sun.com/jsf/core"
xmlns:a4j="http://richfaces.org/a4j"
xmlns:rich="http://richfaces.org/rich">


<script language="javascript">
	function jsPrint() {
		window.print();
	}
</script>


<h:form id="formM">
<rich:toolBar itemSeparator="grid">
	<rich:toolBarGroup>
		<rich:dropDownMenu value="File">
			<rich:menuItem submitMode="server" value="Change Status" action="fileStatus">
			</rich:menuItem>
			<rich:menuItem submitMode="server" value="Delete File" action="fileDelete">
			</rich:menuItem>
			<rich:menuItem submitMode="server" value="Change Metadata" action="fileMetaData">
			</rich:menuItem>
		</rich:dropDownMenu>
		
		<rich:dropDownMenu value="Block">
			<rich:menuItem submitMode="server" value="Open/Close Block" action="blockStatus">
			</rich:menuItem>
		</rich:dropDownMenu>
	
		<rich:dropDownMenu value="Tier">
			<rich:menuItem submitMode="server" value="Add Tier and Tier Order" action="tierAdd">
			</rich:menuItem>
		</rich:dropDownMenu>
	
		<rich:dropDownMenu value="Dataset">
			<rich:menuItem submitMode="server" value="Delete/Undelete Dataset" action="datasetDeleteUndelete">
			</rich:menuItem>
			<rich:menuItem submitMode="server" value="Change Status" action="datasetStatus">
			</rich:menuItem>
			<rich:menuItem submitMode="server" value="Migrate Dataset" action="datasetMigrate">
			</rich:menuItem>
		</rich:dropDownMenu>
		
		<rich:dropDownMenu value="StorageElement">
			<rich:menuItem submitMode="server" value="Rename Storage Element" action="seRename">
			</rich:menuItem>
		</rich:dropDownMenu>
	
		<rich:dropDownMenu value="Lumi Secton">
			<rich:menuItem submitMode="server" value="Update Lumi" action="lumiUpdate">
			</rich:menuItem>
		</rich:dropDownMenu>
	
		<rich:dropDownMenu value="Run">
			<rich:menuItem submitMode="server" value="Update Run" action="runUpdate">
			</rich:menuItem>
			<rich:menuItem submitMode="server" value="Update DataQuality" action="runDataQuality">
			</rich:menuItem>
		</rich:dropDownMenu>
	</rich:toolBarGroup>
	<rich:toolBarGroup location="right">
		<h:graphicImage id="skin" height="32" width="32" url="/html/images/colors-32x32.png" />
		<rich:dropDownMenu value="Themes">
				<rich:menuItem submitMode="server">
				<h:commandLink value="Default">
					<a4j:actionparam name="skin" value="DEFAULT" assignTo="#{SkinBean.skin}"/>
				</h:commandLink>
			</rich:menuItem>
			<rich:menuItem submitMode="server">
				<h:commandLink value="Plain">
					<a4j:actionparam name="skin" value="plain" assignTo="#{SkinBean.skin}"/>
				</h:commandLink>
			</rich:menuItem>
			<rich:menuItem submitMode="server">
				<h:commandLink value="Emerald Town">
					<a4j:actionparam name="skin" value="emeraldTown" assignTo="#{SkinBean.skin}"/>
				</h:commandLink>
			</rich:menuItem>
			<rich:menuItem submitMode="server">
				<h:commandLink value="Wine">
					<a4j:actionparam name="skin" value="wine" assignTo="#{SkinBean.skin}"/>
				</h:commandLink>
			</rich:menuItem>
			<rich:menuItem submitMode="server">
				<h:commandLink value="Japan Cherry">
					<a4j:actionparam name="skin" value="japanCherry" assignTo="#{SkinBean.skin}"/>
				</h:commandLink>
			</rich:menuItem>
			<rich:menuItem submitMode="server">
				<h:commandLink value="Ruby">
					<a4j:actionparam name="skin" value="ruby" assignTo="#{SkinBean.skin}"/>
				</h:commandLink>
			</rich:menuItem>
			<rich:menuItem submitMode="server">
				<h:commandLink value="Classic">
					<a4j:actionparam name="skin" value="classic" assignTo="#{SkinBean.skin}"/>
				</h:commandLink>
			</rich:menuItem>
			<rich:menuItem submitMode="server">
				<h:commandLink value="DeepMarine">
					<a4j:actionparam name="skin" value="deepMarine" assignTo="#{SkinBean.skin}"/>
				</h:commandLink>
			</rich:menuItem>
			<rich:menuItem submitMode="server">
				<h:commandLink value="BlueSky" >
					<a4j:actionparam name="skin" value="blueSky" assignTo="#{SkinBean.skin}"/>
				</h:commandLink>
			</rich:menuItem>
		</rich:dropDownMenu>

	</rich:toolBarGroup>
	<rich:toolBarGroup location="right">                       
		<h:graphicImage id="print" height="32" width="32" url="/html/images/print-32x32.png" onclick="jsPrint()"/>
		<h:outputLabel value="Print" for="print" onclick="jsPrint()"/>
	</rich:toolBarGroup>
	<rich:toolBarGroup location="right">                       
		<h:graphicImage id="refresh" height="32" width="32" url="/html/images/refresh-32x32.png" onclick="window.location.reload(true)"/>
		<h:outputLabel value="Refresh" for="refresh" onclick="window.location.reload(true)"/>
	</rich:toolBarGroup>
	<rich:toolBarGroup location="right">    
		<h:graphicImage id="contact" height="32" width="32" url="/html/images/mail-32x32.png" onclick="parent.location='mailto:cms-dbs-support@cern.ch?subject=DBS Web Admin'"/>
		<h:outputLabel value="Contact" for="contact" onclick="parent.location='mailto:cms-dbs-support@cern.ch?subject=DBS Web Admin'"/>
	</rich:toolBarGroup>
																																																																					
</rich:toolBar>
</h:form>
</html>
