 <html xmlns="http://www.w3.org/1999/xhtml"
xmlns:ui="http://java.sun.com/jsf/facelets"
xmlns:h="http://java.sun.com/jsf/html"
xmlns:f="http://java.sun.com/jsf/core"
xmlns:rich="http://richfaces.org/rich"
xmlns:a4j="http://richfaces.org/a4j">
<body>
	<style>
		.label{font-weight:bold;
			font-size:x-small;
		}
	</style>

                <rich:tabPanel id="StatsPanel"><rich:tab label="Stats" id="statTab">
			<h:panelGrid columns="6">
				<rich:simpleTogglePanel switchType="client" label="Repack stats">
					<h:panelGrid columns="2">
						<h:outputText value="New Jobs" styleClass="label"/>
						<h:outputText value="0"/>
						<h:outputText value="Used Jobs" styleClass="label"/>
						<h:outputText value="0"/>
						<h:outputText value="Successful Jobs" styleClass="label"/>
						<h:outputText value="0"/>
						<h:outputText value="Failed Jobs" styleClass="label"/>
						<h:outputText value="0"/>
					</h:panelGrid>
				</rich:simpleTogglePanel>
					<rich:simpleTogglePanel switchType="client" label="Merged stats">
					<h:panelGrid columns="2">
						<h:outputText value="Acquired Jobs" styleClass="label"/>
						<h:outputText value="0"/>
						<h:outputText value="Successful Jobs" styleClass="label"/>
						<h:outputText value="0"/>
						<h:outputText value="Failed Jobs" styleClass="label"/>
						<h:outputText value="0"/>
					</h:panelGrid>
				</rich:simpleTogglePanel>
					<rich:simpleTogglePanel switchType="client" label="Reco stats">
					<h:panelGrid columns="2">
						<h:outputText value="Acquired Jobs" styleClass="label"/>
						<h:outputText value="0"/>
						<h:outputText value="Successful Jobs" styleClass="label"/>
						<h:outputText value="0"/>
						<h:outputText value="Failed Jobs" styleClass="label"/>
						<h:outputText value="0"/>
					</h:panelGrid>
				</rich:simpleTogglePanel>
					<rich:simpleTogglePanel switchType="client" label="Reco Merged stats">
					<h:panelGrid columns="2">
						<h:outputText value="Acquired Jobs" styleClass="label"/>
						<h:outputText value="0"/>
						<h:outputText value="Successful Jobs" styleClass="label"/>
						<h:outputText value="0"/>
						<h:outputText value="Failed Jobs" styleClass="label"/>
						<h:outputText value="0"/>
					</h:panelGrid>
				</rich:simpleTogglePanel>
					<rich:simpleTogglePanel switchType="client" label="AlcaSkim stats">
					<h:panelGrid columns="2">
						<h:outputText value="Acquired Jobs" styleClass="label"/>
						<h:outputText value="0"/>
						<h:outputText value="Successful Jobs" styleClass="label"/>
						<h:outputText value="0"/>
						<h:outputText value="Failed Jobs" styleClass="label"/>
						<h:outputText value="0"/>
					</h:panelGrid>
				</rich:simpleTogglePanel>

				<rich:simpleTogglePanel switchType="client" label="AlcaSkim Merged stats">
					<h:panelGrid columns="2">
						<h:outputText value="Acquired Jobs" styleClass="label"/>
						<h:outputText value="0"/>
						<h:outputText value="Successful Jobs" styleClass="label"/>
						<h:outputText value="0"/>
						<h:outputText value="Failed Jobs" styleClass="label"/>
						<h:outputText value="0"/>
					</h:panelGrid>
				</rich:simpleTogglePanel>
			</h:panelGrid>
		</rich:tab></rich:tabPanel>
</body>
</html>
