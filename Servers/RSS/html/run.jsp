<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<f:view>
	<h:form binding="#{runBean.form1}" id="form1">
		<h:outputText value="Run Sequence Service" style="font-size:xx-large;"/>
		<h:panelGrid columns="4" columnClasses="gridContent" id="pg">
				<h:outputText value="Sequence Name"/>
				<h:inputText size="50" binding="#{runBean.nameCreateInputText}"/>
				<h:commandButton id="createRunSequenceButton" value="createRunSequence" action="#{runBean.createRunSequenceAction}"/>
				<h:outputText value="" binding="#{runBean.createRunSequenceText}" style="color:red;"/>

				<h:outputText value="Sequence Name"/>
				<h:inputText size="50" binding="#{runBean.nameNextInputText}"/>
				<h:commandButton id="getNextRunNumberButton" value="getNextRunNumber" action="#{runBean.getNextRunNumberAction}"/>
				<h:outputText value="" binding="#{runBean.nextRunNumberText}" style="color:red;"/>

				<h:outputText value="Sequence Name"/>
				<h:inputText size="50" binding="#{runBean.nameCurrInputText}"/>
				<h:commandButton id="getCurrRunNumberButton" value="getCurrRunNumber" action="#{runBean.getCurrRunNumberAction}"/>
				<h:outputText value="" binding="#{runBean.currRunNumberText}" style="color:red;"/>

		</h:panelGrid>
	</h:form>
</f:view>
