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
		<rich:tabPanel id="tierAddPanel">
			<rich:tab label="Data Tier Add">
				<rich:toolTip followMouse="true" direction="top-right" delay="500" style="width:250px" value="To add a new Tier and Tier order, enter the tier name in the form tier1-tier2-tier3 and click Add Tier button. This will add tier1, tier2, tier3 as 3 separate tiers and tier1-tier2-tier3 as a new tier order"/>
				<h:panelGrid columns="3" columnClasses="gridContent" id="pg">
					<h:outputText value="Tier Name (tier1-tier2)" style="font-size:small;"/>
					<h:inputText id="tierInputText"  required="true" binding="#{TierAdd.tierInputText}"/>
					<rich:message for="tierInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/html/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/html/images/error.gif" />   
						</f:facet>
					</rich:message>
					
					<f:facet name="footer">
						<a4j:commandButton id="tierAddButton" value="Add Tier" action="#{TierAdd.addTierAction}">
						</a4j:commandButton>
					</f:facet>
				</h:panelGrid>				
				<rich:message for="pg" binding="#{TierAdd.generalInputMessage}">
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
