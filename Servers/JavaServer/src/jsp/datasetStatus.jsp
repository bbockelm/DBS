<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<f:view>
	<jsp:include page="menu.jsp"/>
	<h:form id="form1">
		<rich:tabPanel id="datasetStatusPanel">
			<rich:tab label="Dataset Status">
				<rich:toolTip followMouse="true" direction="top-right" delay="2500" style="width:250px" value="To change the status of the dataset, enter the dataset path, select status and click Change Dataset Status button"/>
				<h:panelGrid columns="4" columnClasses="gridContent" id="pg">
					<h:outputText value="Dataset Path" style="font-size:small;"/>
					<h:inputText id="datasetInputText"  required="true" binding="#{DatasetStatus.datasetInputText}"/>
					<h:selectOneMenu value="Dataset Status" binding="#{DatasetStatus.status}">
						<f:selectItem itemValue="VALID" itemLabel="Valid"/>
						<f:selectItem itemValue="INVALID" itemLabel="Invalid"/>
						<f:selectItem itemValue="IMPORTED" itemLabel="Imported"/>
						<f:selectItem itemValue="EXPORTED" itemLabel="Exported"/>
						<f:selectItem itemValue="RO" itemLabel="Read only"/>
					</h:selectOneMenu>
				
					<rich:message for="datasetInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/jsp/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/jsp/images/error.gif" />   
						</f:facet>
					</rich:message>
					<f:facet name="footer">
						<a4j:commandButton id="datasetStatusButton" value="Change Dataset Status" action="#{DatasetStatus.changeStatusAction}">
						</a4j:commandButton>
					</f:facet>
				</h:panelGrid>				
				<rich:message for="pg" binding="#{DatasetStatus.generalInputMessage}">
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
