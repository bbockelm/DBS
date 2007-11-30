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
		<rich:tabPanel id="lumiUpdatePanel">
			<rich:tab label="Lumi Section Update">
				<rich:toolTip followMouse="true" direction="top-right" delay="2500" style="width:250px" value="To update a lumi section, enter the Lumi section number, Run number, optional start_event_number, optional end_event_number, optional lumi_start_time, optional lumi_end_time and click Update Lumi Section button"/>
				<h:panelGrid columns="3" columnClasses="gridContent" id="pg">
					<h:outputText value="Lumi Section Number" style="font-size:small;"/>
					<h:inputText id="lumiInputText"  required="true" binding="#{LumiUpdate.lumiInputText}" value="#{UrlParam.lumi}">
						<f:convertNumber integerOnly="true"/>
					</h:inputText>
					<rich:message for="lumiInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/html/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/html/images/error.gif" />   
						</f:facet>
					</rich:message>
		
					<h:outputText value="Run Number" style="font-size:small;"/>
					<h:inputText id="runInputText"  required="true" binding="#{LumiUpdate.runInputText}" value="#{UrlParam.run}">				
						<f:convertNumber integerOnly="true"/>
					</h:inputText>
	
					<rich:message for="runInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/html/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/html/images/error.gif" />   
						</f:facet>
					</rich:message>
	
			
					<h:outputText value="Start Event Number" style="font-size:small;"/>
					<h:inputText id="startEventInputText" binding="#{LumiUpdate.startEventInputText}">
						<f:convertNumber integerOnly="true"/>
					</h:inputText>
	
					<rich:message for="startEventInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/html/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/html/images/error.gif" />   
						</f:facet>
					</rich:message>
			
		
					<h:outputText value="End Event Number" style="font-size:small;"/>
					<h:inputText id="endEventInputText" binding="#{LumiUpdate.endEventInputText}">	
						<f:convertNumber integerOnly="true"/>
					</h:inputText>
	
					<rich:message for="endEventInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/html/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/html/images/error.gif" />   
						</f:facet>
					</rich:message>
					
					<h:outputText value="Lumi Start Time" style="font-size:small;"/>
					<h:inputText id="startLumiTimeInputText" binding="#{LumiUpdate.startLumiTimeInputText}">
						<f:convertNumber integerOnly="true"/>
					</h:inputText>
	
					<rich:message for="startLumiTimeInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/html/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/html/images/error.gif" />   
						</f:facet>
					</rich:message>
		
					<h:outputText value="Lumi End Time" style="font-size:small;"/>
					<h:inputText id="endLumiTimeInputText" binding="#{LumiUpdate.endLumiTimeInputText}">
						<f:convertNumber integerOnly="true"/>
					</h:inputText>
	
					<rich:message for="endLumiTimeInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/html/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/html/images/error.gif" />   
						</f:facet>
					</rich:message>
	
					<f:facet name="footer">
						<a4j:commandButton id="lumiUpdateButton" value="Update Lumi Section" action="#{LumiUpdate.updateLumiAction}">
						</a4j:commandButton>
					</f:facet>
				</h:panelGrid>	
				<rich:message for="pg" binding="#{LumiUpdate.generalInputMessage}">
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
