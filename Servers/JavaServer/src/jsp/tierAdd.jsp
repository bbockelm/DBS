<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<f:view>
	<jsp:include page="menu.jsp"/>
	<h:form id="form1">
		<rich:tabPanel id="tierAddPanel">
			<rich:tab label="Data Tier Add">
				<rich:toolTip followMouse="true" direction="top-right" delay="500" style="width:250px" value="To add a new Tier and Tier order, enter the tier name in the form tier1-tier2-tier3 and click Add Tier button. This will add tier1, tier2, tier3 as 3 separate tiers and tier1-tier2-tier3 as a new tier order"/>
				<h:panelGrid columns="3" columnClasses="gridContent" id="pg">
					<h:outputText value="Tier Name (tier1-tier2)" style="font-size:small;"/>
					<h:inputText id="tierInputText"  required="true" binding="#{TierAdd.tierInputText}"/>
					<rich:message for="tierInputText">
						<f:facet name="passedMarker">
							<h:graphicImage  value="/jsp/images/passed.gif" /> 
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/jsp/images/error.gif" />   
						</f:facet>
					</rich:message>
					
					<f:facet name="footer">
						<a4j:commandButton id="tierAddButton" value="Add Tier" action="#{TierAdd.addTierAction}">
						</a4j:commandButton>
					</f:facet>
				</h:panelGrid>				
				<rich:message for="pg" binding="#{TierAdd.generalInputMessage}">
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
