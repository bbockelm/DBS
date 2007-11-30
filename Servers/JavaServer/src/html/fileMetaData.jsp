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
		<rich:tabPanel id="fileMetaDataPanel">
			<rich:tab label="File Meta Data">
				<rich:toolTip followMouse="true" direction="top-right" delay="2500" style="width:250px" value="To update the file meta data, enter the locical file name, enter the queryable meta data information and  click Update File Meta Data button"/>
				<h:panelGrid columns="3" columnClasses="gridContent" id="pg">
					<h:outputText value="Logical File Name" style="font-size:small;"/>
					<h:inputText id="lfnInputText" size="100" required="true" binding="#{FileMetaData.lfnInputText}" value="#{UrlParam.lfn}"/>
					<rich:message for="lfnInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/html/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/html/images/error.gif" />   
						</f:facet>
					</rich:message>
					
					<h:outputText value="Queryable Meta Data" style="font-size:small;"/>
					<h:inputText id="qmdInputText" size="100" required="true" binding="#{FileMetaData.qmdInputText}" value="#{UrlParam.qmd}"/>
					<rich:message for="qmdInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/html/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/html/images/error.gif" />   
						</f:facet>
					</rich:message>

					<f:facet name="footer">
						<a4j:commandButton id="fileUpdateMetaDataButton" value="Update File Meta Data" action="#{FileMetaData.updateMetaDataAction}">
						</a4j:commandButton>
					</f:facet>
				</h:panelGrid>				
				<rich:message for="pg" binding="#{FileMetaData.generalInputMessage}">
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
