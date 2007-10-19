<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<f:view>
	<jsp:include page="menu.jsp"/>
	<h:form id="form1">
		<rich:tabPanel id="fileMetaDataPanel">
			<rich:tab label="File Meta Data">
				<rich:toolTip followMouse="true" direction="top-right" delay="2500" style="width:250px" value="To update the file meta data, enter the locical file name, enter the queryable meta data information and  click Update File Meta Data button"/>
				<h:panelGrid columns="3" columnClasses="gridContent" id="pg">
					<h:outputText value="Logical File Name" style="font-size:small;"/>
					<h:inputText id="lfnInputText"  required="true" binding="#{FileMetaData.lfnInputText}"/>
					<rich:message for="lfnInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/jsp/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/jsp/images/error.gif" />   
						</f:facet>
					</rich:message>
					
					<h:outputText value="Queryable Meta Data" style="font-size:small;"/>
					<h:inputText id="qmdInputText"  required="true" binding="#{FileMetaData.qmdInputText}"/>
					<rich:message for="qmdInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/jsp/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/jsp/images/error.gif" />   
						</f:facet>
					</rich:message>

					<f:facet name="footer">
						<a4j:commandButton id="fileUpdateMetaDataButton" value="Update File Meta Data" action="#{FileMetaData.updateMetaDataAction}">
						</a4j:commandButton>
					</f:facet>
				</h:panelGrid>				
				<rich:message for="pg" binding="#{FileMetaData.generalInputMessage}">
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
