 <html xmlns="http://www.w3.org/1999/xhtml"
xmlns:ui="http://java.sun.com/jsf/facelets"
xmlns:h="http://java.sun.com/jsf/html"
xmlns:f="http://java.sun.com/jsf/core"
xmlns:rich="http://richfaces.org/rich"
xmlns:a4j="http://richfaces.org/a4j">
<body>
	<style>
		.label{font-size:xx-small;
		}
	</style>

	<rich:simpleTogglePanel switchType="client" label="#{header}">
		<h:panelGrid columns="2">
			<h:outputText value="Acquired Jobs" styleClass="label"/>
			<h:outputText value="#{aJobVal}" styleClass="label"/>
			<h:outputText value="Successful Jobs" styleClass="label"/>
			<h:outputText value="#{sJobVal}" styleClass="label"/>
			<h:outputText value="Failed Jobs" styleClass="label"/>
			<h:outputText value="#{fJobVal}" styleClass="label"/>
		</h:panelGrid>
	</rich:simpleTogglePanel>

</body>
</html>
