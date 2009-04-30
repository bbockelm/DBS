
<html xmlns="http://www.w3.org/1999/xhtml"
xmlns:ui="http://java.sun.com/jsf/facelets"
xmlns:h="http://java.sun.com/jsf/html"
xmlns:f="http://java.sun.com/jsf/core"
xmlns:rich="http://richfaces.org/rich"
xmlns:a4j="http://richfaces.org/a4j">
<script language="javascript">
	function jsPrint() {
		window.print();
	}
	function timedRefresh(timeoutPeriod) {
		setTimeout("location.reload(true);",timeoutPeriod);
	}
</script>

<body onload="JavaScript:timedRefresh(120000);">




<h:form id="formM">
<rich:toolBar itemSeparator="grid">
		<rich:toolBarGroup location="left">
			<h:outputText value="Tier 0 Monitoring" style="font-size:xx-large;"/>
		</rich:toolBarGroup>
		<rich:toolBarGroup location="right">
			<h:graphicImage id="skin" height="32" width="32" url="/html/images/colors-32x32.png" />
			<rich:dropDownMenu value="Themes">
				<rich:menuItem submitMode="server">
				<h:commandLink value="Default">
					<a4j:actionparam name="skin" value="DEFAULT" assignTo="#{skinBean.skin}"/>
				</h:commandLink>
			</rich:menuItem>
			<rich:menuItem submitMode="server">
				<h:commandLink value="Plain">
					<a4j:actionparam name="skin" value="plain" assignTo="#{skinBean.skin}"/>
				</h:commandLink>
			</rich:menuItem>
			<rich:menuItem submitMode="server">
				<h:commandLink value="Emerald Town">
					<a4j:actionparam name="skin" value="emeraldTown" assignTo="#{skinBean.skin}"/>
				</h:commandLink>
			</rich:menuItem>
			<rich:menuItem submitMode="server">
				<h:commandLink value="Wine">
					<a4j:actionparam name="skin" value="wine" assignTo="#{skinBean.skin}"/>
				</h:commandLink>
			</rich:menuItem>
			<rich:menuItem submitMode="server">
				<h:commandLink value="Japan Cherry">
					<a4j:actionparam name="skin" value="japanCherry" assignTo="#{skinBean.skin}"/>
				</h:commandLink>
			</rich:menuItem>
			<rich:menuItem submitMode="server">
				<h:commandLink value="Ruby">
					<a4j:actionparam name="skin" value="ruby" assignTo="#{skinBean.skin}"/>
				</h:commandLink>
			</rich:menuItem>
			<rich:menuItem submitMode="server">
				<h:commandLink value="Classic">
					<a4j:actionparam name="skin" value="classic" assignTo="#{skinBean.skin}"/>
				</h:commandLink>
			</rich:menuItem>
			<rich:menuItem submitMode="server">
				<h:commandLink value="DeepMarine">
					<a4j:actionparam name="skin" value="deepMarine" assignTo="#{skinBean.skin}"/>
				</h:commandLink>
			</rich:menuItem>
			<rich:menuItem submitMode="server">
				<h:commandLink value="BlueSky" >
					<a4j:actionparam name="skin" value="blueSky" assignTo="#{skinBean.skin}"/>
				</h:commandLink>
			</rich:menuItem>
		</rich:dropDownMenu>

	</rich:toolBarGroup>
	<rich:toolBarGroup location="right">                       
		<h:graphicImage id="refresh" height="32" width="32" url="/html/images/refresh-32x32.png" onclick="window.location.reload(true)"/>
		<h:outputLabel value="Refresh" for="refresh" onclick="window.location.reload(true)"/>
	</rich:toolBarGroup>

	<rich:toolBarGroup location="right">                       
		<h:graphicImage id="print" height="32" width="32" url="/html/images/print-32x32.png" onclick="jsPrint()"/>
		<h:outputLabel value="Print" for="print" onclick="jsPrint()"/>
	</rich:toolBarGroup>
	<rich:toolBarGroup location="right">    
		<h:graphicImage id="contact" height="32" width="32" url="/html/images/mail-32x32.png" onclick="parent.location='mailto:sekhri@fnal.gov?subject=TOM Web Interface'"/>
		<h:outputLabel value="Contact" for="contact" onclick="parent.location='mailto:sekhri@fnal.gov?subject=Tom Web interface'"/>
	</rich:toolBarGroup>
																																																																					
</rich:toolBar>

</h:form>
</body>
</html>
