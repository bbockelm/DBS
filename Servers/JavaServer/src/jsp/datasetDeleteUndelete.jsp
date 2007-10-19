<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<f:view>
	<jsp:include page="menu.jsp"/>
	<h:form id="form1">
		<rich:tabPanel id="datasetDeleteUndeletePanel">
			<rich:tab label="Dataset Delete Undelete">
				<rich:toolTip followMouse="true" direction="top-right" delay="2500" style="width:250px" value="To delete or undelete a dataset , enter the dataset path, select operation and click Submit button. The path should be of the format /primary/processed/tier. Deleting a dataset moves it into recycle bin and undeleting a dataset recovers the dataset from the recycle bin"/>
				<h:panelGrid columns="4" columnClasses="gridContent" id="pg">
					<h:outputText value="Dataset Path" style="font-size:small;"/>
					<h:inputText id="datasetInputText"  required="true" binding="#{DatasetDeleteUndelete.datasetInputText}"/>
					<h:selectOneMenu value="Dataset Operation" binding="#{DatasetDeleteUndelete.op}">
						<f:selectItem itemValue="DELETE" itemLabel="Delete"/>
						<f:selectItem itemValue="UNDELETE" itemLabel="Undelete"/>
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
						<a4j:commandButton id="datasetSubmitButton" value="Submit" action="#{DatasetDeleteUndelete.deleteUndeleteAction}">
						</a4j:commandButton>
					</f:facet>
				</h:panelGrid>				
				<rich:message for="pg" binding="#{DatasetDeleteUndelete.generalInputMessage}">
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
