<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<f:view>
	<jsp:include page="menu.jsp"/>
    <h:form id="form">
	 <rich:separator height="1" style="padding:10px"/>
		 <a4j:mediaOutput element="img" cacheable="false" session="true"
		 createContent="#{mediaBean.paint}"  mimeType="text/plain" />
	 <rich:separator height="1" style="padding:10px"/>
		 

    </h:form>	
</f:view>
