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
		<rich:tabPanel id="datasetStatusPanel">
			<rich:tab label="Dataset Status">
				<rich:toolTip followMouse="true" direction="top-right" delay="2500" style="width:250px" value="To change the status of the dataset, enter the dataset path, select status and click Change Dataset Status button"/>
				<h:panelGrid columns="4" columnClasses="gridContent" id="pg">
					<h:outputText value="Dataset Path" style="font-size:small;"/>
					<h:inputText id="datasetInputText" size="100" required="true" binding="#{DatasetStatus.datasetInputText}" value="#{UrlParam.path}"/>
					<h:selectOneMenu value="Dataset Status" binding="#{DatasetStatus.status}">
						<f:selectItem itemValue="VALID" itemLabel="Valid"/>
						<f:selectItem itemValue="INVALID" itemLabel="Invalid"/>
						<f:selectItem itemValue="IMPORTED" itemLabel="Imported"/>
						<f:selectItem itemValue="EXPORTED" itemLabel="Exported"/>
						<f:selectItem itemValue="RO" itemLabel="Read only"/>
					</h:selectOneMenu>
				
					<rich:message for="datasetInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/html/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/html/images/error.gif" />   
						</f:facet>
					</rich:message>
					<f:facet name="footer">
						<a4j:commandButton id="datasetStatusButton" value="Change Dataset Status" action="#{DatasetStatus.changeStatusAction}">
						</a4j:commandButton>
					</f:facet>
				</h:panelGrid>				
				<rich:message for="pg" binding="#{DatasetStatus.generalInputMessage}">
					<f:facet name="passedMarker">
						<h:graphicImage  value="/html/images/passed.gif" /> 
					</f:facet>
					<f:facet name="errorMarker">
						<h:graphicImage value="/html/images/error.gif" />   
					</f:facet>
				</rich:message>


			</rich:tab>
    		</rich:tabPanel>
	    
	</h:form>
</f:view>
</body></html>
