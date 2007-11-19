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
		<rich:tabPanel id="seRenamePanel">
			<rich:tab label="Storage Element Rename">
				<rich:toolTip followMouse="true" direction="top-right" delay="2500" style="width:250px" value="To rename a storage element, enter the old Storage element name, the new Storage element name and click Rename Storage Element button"/>
				<h:panelGrid columns="3" columnClasses="gridContent" id="pg">
					<h:outputText value="Old Storage Element Name" style="font-size:small;"/>
					<h:inputText id="seOldInputText"  required="true" binding="#{StorageElementRename.seOldInputText}"/>
					<rich:message for="seOldInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/html/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/html/images/error.gif" />   
						</f:facet>
					</rich:message>
	
					<h:outputText value="New Storage Element Name" style="font-size:small;"/>
					<h:inputText id="seNewInputText"  required="true" binding="#{StorageElementRename.seNewInputText}"/>
					<rich:message for="seNewInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/html/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/html/images/error.gif" />   
						</f:facet>
					</rich:message>
				
					<f:facet name="footer">
						<a4j:commandButton id="seRenameButton" value="Rename Storage Element" action="#{StorageElementRename.renameAction}">
						</a4j:commandButton>
					</f:facet>
				</h:panelGrid>				
				<rich:message for="pg" binding="#{StorageElementRename.generalInputMessage}">
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
