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
		<rich:tabPanel id="blockStatusPanel">
			<rich:tab label="Block Status">
				<rich:toolTip followMouse="true" direction="top-right" delay="2500" style="width:250px" value="To change the status of the block, enter the block name, select status and click Change Block Status button"/>
				<h:panelGrid columns="4" columnClasses="gridContent" id="pg">
					<h:outputText value="Block Name" style="font-size:small;"/>
					<h:inputText id="blockInputText" size="100" required="true" binding="#{BlockStatus.blockInputText}"/>
					<h:selectOneMenu value="Block Status" binding="#{BlockStatus.status}">
						<f:selectItem itemValue="OPEN" itemLabel="Open"/>
						<f:selectItem itemValue="CLOSE" itemLabel="Close"/>
					</h:selectOneMenu>
					<rich:message for="blockInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/jsp/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/jsp/images/error.gif" />   
						</f:facet>
					</rich:message>
				
					<f:facet name="footer">
						<a4j:commandButton id="blockStatusButton" value="Change Block Status" action="#{BlockStatus.changeStatusAction}">
						</a4j:commandButton>
					</f:facet>
				</h:panelGrid>
				
				<rich:message for="pg" binding="#{BlockStatus.generalInputMessage}">
					<f:facet name="passedMarker">
						<h:graphicImage  value="/jsp/images/passed.gif" /> 
					</f:facet>
					<f:facet name="errorMarker">
						<h:graphicImage value="/jsp/images/error.gif" />   
					</f:facet>
				</rich:message>

			</rich:tab>
    		</rich:tabPanel>
		
	    
	</h:form>
</f:view>
</body></html>

