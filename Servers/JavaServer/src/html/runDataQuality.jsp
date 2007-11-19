<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
xmlns:ui="http://java.sun.com/jsf/facelets"
xmlns:h="http://java.sun.com/jsf/html"
xmlns:f="http://java.sun.com/jsf/core"
xmlns:a4j="http://richfaces.org/a4j"
xmlns:rich="http://richfaces.org/rich"><body>

<f:view>
	<ui:include src="menu.jsp"/>
	<h:form id="form1">
		<rich:tabPanel id="runDQPanel">
			<rich:tab label="Run Data Quality Under Construction">
				<h:graphicImage  value="/html/images/construction-cone-256x256.png" /> 	
				<h:graphicImage  value="/html/images/lightning-sign-256x256.png" /> 
			</rich:tab>
		</rich:tabPanel>
	</h:form>
</f:view>
</body></html>
