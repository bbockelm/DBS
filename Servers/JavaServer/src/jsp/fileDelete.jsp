<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<f:view>
	<jsp:include page="menu.jsp"/>
	<h:form id="form1">
		<rich:tabPanel id="fileDeletePanel">
			<rich:tab label="File Delete">
				<rich:toolTip followMouse="true" direction="top-right" delay="2500" style="width:250px" value="To delete a file, enter the locical file name click Delete File button"/>
				<h:panelGrid columns="3" columnClasses="gridContent" id="pg">
					<h:outputText value="Logical File Name" style="font-size:small;"/>
					<h:inputText id="lfnInputText"  required="true" binding="#{FileDelete.lfnInputText}"/>
			
					<rich:message for="lfnInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/jsp/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/jsp/images/error.gif" />   
						</f:facet>
					</rich:message>
					<f:facet name="footer">
						<a4j:commandButton id="fileDeleteButton" value="Delete File" action="#{FileDelete.deleteAction}">
						</a4j:commandButton>
					</f:facet>
				</h:panelGrid>
								
				<rich:message for="pg" binding="#{FileDelete.generalInputMessage}">
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
