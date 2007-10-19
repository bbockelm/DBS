<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<f:view>
	<jsp:include page="menu.jsp"/>
	<h:form id="form1">
		<rich:tabPanel id="lumiUpdatePanel">
			<rich:tab label="Lumi Section Update">
				<rich:toolTip followMouse="true" direction="top-right" delay="2500" style="width:250px" value="To update a lumi section, enter the Lumi section number, Run number, optional start_event_number, optional end_event_number, optional lumi_start_time, optional lumi_end_time and click Update Lumi Section button"/>
				<h:panelGrid columns="3" columnClasses="gridContent" id="pg">
					<h:outputText value="Lumi Section Number" style="font-size:small;"/>
					<h:inputText id="lumiInputText"  required="true" binding="#{LumiUpdate.lumiInputText}">
						<f:convertNumber integerOnly="true"/>
					</h:inputText>
					<rich:message for="lumiInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/jsp/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/jsp/images/error.gif" />   
						</f:facet>
					</rich:message>
		
					<h:outputText value="Run Number" style="font-size:small;"/>
					<h:inputText id="runInputText"  required="true" binding="#{LumiUpdate.runInputText}">				
						<f:convertNumber integerOnly="true"/>
					</h:inputText>
	
					<rich:message for="runInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/jsp/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/jsp/images/error.gif" />   
						</f:facet>
					</rich:message>
	
			
					<h:outputText value="Start Event Number" style="font-size:small;"/>
					<h:inputText id="startEventInputText" binding="#{LumiUpdate.startEventInputText}">
						<f:convertNumber integerOnly="true"/>
					</h:inputText>
	
					<rich:message for="startEventInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/jsp/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/jsp/images/error.gif" />   
						</f:facet>
					</rich:message>
			
		
					<h:outputText value="End Event Number" style="font-size:small;"/>
					<h:inputText id="endEventInputText" binding="#{LumiUpdate.endEventInputText}">	
						<f:convertNumber integerOnly="true"/>
					</h:inputText>
	
					<rich:message for="endEventInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/jsp/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/jsp/images/error.gif" />   
						</f:facet>
					</rich:message>
					
					<h:outputText value="Lumi Start Time" style="font-size:small;"/>
					<h:inputText id="startLumiTimeInputText" binding="#{LumiUpdate.startLumiTimeInputText}">
						<f:convertNumber integerOnly="true"/>
					</h:inputText>
	
					<rich:message for="startLumiTimeInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/jsp/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/jsp/images/error.gif" />   
						</f:facet>
					</rich:message>
		
					<h:outputText value="Lumi End Time" style="font-size:small;"/>
					<h:inputText id="endLumiTimeInputText" binding="#{LumiUpdate.endLumiTimeInputText}">
						<f:convertNumber integerOnly="true"/>
					</h:inputText>
	
					<rich:message for="endLumiTimeInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/jsp/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/jsp/images/error.gif" />   
						</f:facet>
					</rich:message>
	
					<f:facet name="footer">
						<a4j:commandButton id="lumiUpdateButton" value="Update Lumi Section" action="#{LumiUpdate.updateLumiAction}">
						</a4j:commandButton>
					</f:facet>
				</h:panelGrid>	
				<rich:message for="pg" binding="#{LumiUpdate.generalInputMessage}">
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
