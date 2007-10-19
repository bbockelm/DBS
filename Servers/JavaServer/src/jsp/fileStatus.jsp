<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
    
<f:view>
	<jsp:include page="menu.jsp"/>
	<h:form id="form1">
		<rich:tabPanel id="fileStatusPanel">
			<rich:tab label="File Status">
				<rich:toolTip followMouse="true" direction="top-right" delay="2500" style="width:250px" value="To change the status of the file, enter the logical file name, select status and click Change File Status button"/>
				<h:panelGrid columns="4" columnClasses="gridContent" id="pg">
					<h:outputText value="Logical File Name" style="font-size:small;"/>
					<h:inputText id="lfnInputText" required="true" binding="#{FileStatus.lfnInputText}"/>
					<h:selectOneMenu value="File Status" binding="#{FileStatus.status}">
						<f:selectItem itemValue="VALID" itemLabel="Valid"/>
						<f:selectItem itemValue="INVALID" itemLabel="Invalid"/>
						<f:selectItem itemValue="MERGED" itemLabel="Merged"/>
						<f:selectItem itemValue="IMPORTED" itemLabel="Imported"/>
						<f:selectItem itemValue="EXPORTED" itemLabel="Exported"/>
					</h:selectOneMenu>
					<rich:message for="lfnInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/jsp/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/jsp/images/error.gif" />   
						</f:facet>
					</rich:message>
					<f:facet name="footer">
						<a4j:commandButton id="fileStatusButton" value="Change File Status" action="#{FileStatus.changeStatusAction}">
						</a4j:commandButton>
					</f:facet>
				</h:panelGrid>
				<rich:message for="pg" binding="#{FileStatus.generalInputMessage}">
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
