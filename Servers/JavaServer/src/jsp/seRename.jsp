<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<f:view>
	<jsp:include page="menu.jsp"/>
	<h:form id="form1">
		<rich:tabPanel id="seRenamePanel">
			<rich:tab label="Storage Element Rename">
				<rich:toolTip followMouse="true" direction="top-right" delay="2500" style="width:250px" value="To rename a storage element, enter the old Storage element name, the new Storage element name and click Rename Storage Element button"/>
				<h:panelGrid columns="3" columnClasses="gridContent" id="pg">
					<h:outputText value="Old Storage Element Name" style="font-size:small;"/>
					<h:inputText id="seOldInputText"  required="true" binding="#{StorageElementRename.seOldInputText}"/>
					<rich:message for="seOldInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/jsp/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/jsp/images/error.gif" />   
						</f:facet>
					</rich:message>
	
					<h:outputText value="New Storage Element Name" style="font-size:small;"/>
					<h:inputText id="seNewInputText"  required="true" binding="#{StorageElementRename.seNewInputText}"/>
					<rich:message for="seNewInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/jsp/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/jsp/images/error.gif" />   
						</f:facet>
					</rich:message>
				
					<f:facet name="footer">
						<a4j:commandButton id="seRenameButton" value="Rename Storage Element" action="#{StorageElementRename.renameAction}">
						</a4j:commandButton>
					</f:facet>
				</h:panelGrid>				
				<rich:message for="pg" binding="#{StorageElementRename.generalInputMessage}">
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
