 <html xmlns="http://www.w3.org/1999/xhtml"
xmlns:ui="http://java.sun.com/jsf/facelets"
xmlns:h="http://java.sun.com/jsf/html"
xmlns:f="http://java.sun.com/jsf/core"
xmlns:rich="http://richfaces.org/rich"
xmlns:a4j="http://richfaces.org/a4j">
<body>

	<h:panelGrid columns="3">
		<rich:panel bodyClass="rich-laguna-panel-no-header">
			<ui:include src="singlejobstat.jsp">
				<ui:param name="id" value="mergedJobStatTable"/>
				<ui:param name="listFromBean" value="#{runDetailBean.mergedStats}"/>
				<ui:param name="header" value="Merged Job Stats"/>
			</ui:include>
		</rich:panel>
			<rich:panel bodyClass="rich-laguna-panel-no-header">
			<ui:include src="singlejobstat.jsp">
				<ui:param name="id" value="recoJobStatTable"/>
				<ui:param name="listFromBean" value="#{runDetailBean.recoStats}"/>
				<ui:param name="header" value="Reco Job Stats"/>
			</ui:include>
		</rich:panel>
		<rich:panel bodyClass="rich-laguna-panel-no-header">
			<ui:include src="singlejobstat.jsp">
				<ui:param name="id" value="alcaSkimJobStatTable"/>
				<ui:param name="listFromBean" value="#{runDetailBean.alcaSkimStats}"/>
				<ui:param name="header" value="AlcaSkim Stats"/>
			</ui:include>
		</rich:panel>
	</h:panelGrid>

</body>
</html>
