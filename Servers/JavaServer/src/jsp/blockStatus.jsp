<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<f:view>
	<jsp:include page="menu.jsp"/>
	<h:form id="form1">
		<rich:tabPanel id="blockStatusPanel">
			<rich:tab label="Block Status">
				<rich:toolTip followMouse="true" direction="top-right" delay="2500" style="width:250px" value="To change the status of the block, enter the block name, select status and click Change Block Status button"/>
				<h:panelGrid columns="4" columnClasses="gridContent" id="pg">
					<h:outputText value="Block Name" style="font-size:small;"/>
					<h:inputText id="blockInputText" required="true" binding="#{BlockStatus.blockInputText}"/>
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
