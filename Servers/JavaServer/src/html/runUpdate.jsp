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
		<rich:tabPanel id="runUpdatePanel">
			<rich:tab label="Run Update">
				<rich:toolTip followMouse="true" direction="top-right" delay="2500" style="width:250px" value="To update a run , enter the Run number, optional number_of_events, optional number_of_lumi_sections, optional total_luminosity, optional start_of_run, optional end_of_run and click Update Run button"/>
				<h:panelGrid columns="3" columnClasses="gridContent" id="pg">

					<h:outputText value="Run Number" style="font-size:small;"/>
					<h:inputText id="runInputText"  required="true" binding="#{RunUpdate.runInputText}" value="#{UrlParam.run}">
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
	
					<h:outputText value="Number of Events" style="font-size:small;"/>
					<h:inputText id="noEventsInputText" binding="#{RunUpdate.noEventsInputText}">
						<f:convertNumber integerOnly="true"/>
					</h:inputText>
	
					<rich:message for="noEventsInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/html/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/html/images/error.gif" />   
						</f:facet>
					</rich:message>
		
			
					<h:outputText value="Number of Lumi Sections" style="font-size:small;"/>
					<h:inputText id="noLumiInputText" binding="#{RunUpdate.noLumiInputText}">
						<f:convertNumber integerOnly="true"/>
					</h:inputText>
	
					<rich:message for="noLumiInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/html/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/html/images/error.gif" />   
						</f:facet>
					</rich:message>
			
		
					<h:outputText value="Total Luminosity" style="font-size:small;"/>
					<h:inputText id="luminosityInputText" binding="#{RunUpdate.luminosityInputText}">
						<f:convertNumber integerOnly="true"/>
					</h:inputText>
	
					<rich:message for="luminosityInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/html/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/html/images/error.gif" />   
						</f:facet>
					</rich:message>
					
					<h:outputText value="Run Start Time" style="font-size:small;"/>
					<h:inputText id="startRunInputText" binding="#{RunUpdate.startRunInputText}">
						<f:convertNumber integerOnly="true"/>
					</h:inputText>
	
					<rich:message for="startRunInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/html/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/html/images/error.gif" />   
						</f:facet>
					</rich:message>
		
					<h:outputText value="Run End Time" style="font-size:small;"/>
					<h:inputText id="endRunTimeInputText" binding="#{RunUpdate.endRunTimeInputText}">
						<f:convertNumber integerOnly="true"/>
					</h:inputText>
	
					<rich:message for="endRunTimeInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/html/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/html/images/error.gif" />   
						</f:facet>
					</rich:message>
	
					<f:facet name="footer">
						<a4j:commandButton id="runUpdateButton" value="Update Run" action="#{RunUpdate.updateRunAction}">
						</a4j:commandButton>
					</f:facet>
				</h:panelGrid>				
				<rich:message for="pg" binding="#{RunUpdate.generalInputMessage}">
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
