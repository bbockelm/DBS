<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<f:view>
	<jsp:include page="menu.jsp"/>
	<h:form id="form1">
		<rich:tabPanel id="runDQPanel">
			<rich:tab label="Run Data Quality Under Construction">
				<h:graphicImage  value="/jsp/images/construction-cone-256x256.png" /> 	
				<h:graphicImage  value="/jsp/images/lightning-sign-256x256.png" /> 
			</rich:tab>
		</rich:tabPanel>
	</h:form>
</f:view>
