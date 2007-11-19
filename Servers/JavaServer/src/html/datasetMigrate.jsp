<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
xmlns:ui="http://java.sun.com/jsf/facelets"
xmlns:h="http://java.sun.com/jsf/html"
xmlns:f="http://java.sun.com/jsf/core"
xmlns:a4j="http://richfaces.org/a4j"
xmlns:rich="http://richfaces.org/rich"><body>

<f:view>
	<ui:include src="menu.jsp"/>
	<rich:dragIndicator id="indicator" />
	<h:form id="form1">
		<rich:tabPanel id="datasetMigratePanel">
			<rich:tab label="Dataset Migrate">
				<rich:toolTip followMouse="true" direction="top-right" delay="6000" style="width:250px" value="To migrate a dataset do the following in this order only. First select the check boxes (With Parents, etc), second drag and drop the Deployed DBS Instance  to source or destination DBS. This will populate the list of processed dataset in the selected source DBS.  Now you have two choices. Third (choice 1) drag and drop any processed dataset from the source to destination DBS to start the migration. Third (choice 2) manually input the dataset path in the text field and then click on migrateDataset Button"/>	

				<h:panelGrid columns="1" columnClasses="gridContent" id="pg">
					<rich:panel bodyClass="inpanelBody">
						<rich:dataTable columnClasses="column-index"
							id="instanceTable"
							rendered="true"
							rowClasses="list-row3"
							rows="20"
							styleClass="list-table1"
							title="DBS instances"
							width="40px"
							value="#{JavaServiceFacade.allDbsInstances}"
							var="dbsiVar">
     							<f:facet name="header">
								<rich:columnGroup>
									<rich:column><h:outputText value="Deployed DBS Instances" /></rich:column>
								</rich:columnGroup>
		    					</f:facet>
    							<rich:column>
								<a4j:outputPanel layout="block">
									<rich:dragSupport dragIndicator=":indicator" dragType="instance" dragValue="#{dbsiVar}">
										<a4j:actionparam value="#{dbsiVar.instanceName}" name="instanceNameLabel"/>
									</rich:dragSupport>
									<h:outputText value="#{dbsiVar.instanceName}"></h:outputText>
								</a4j:outputPanel>
    							</rich:column>
						</rich:dataTable>
					</rich:panel>


					
					<h:panelGrid columns="2" columnClasses="gridContent">

						<h:panelGrid columns="1" columnClasses="gridContent">
							<rich:panel styleClass="dropTargetPanel">
								<f:facet name="header">
									<h:outputText styleClass="headerText" value="Source DBS Instance" style="font-size:xx-small;"/>
								</f:facet>
								<rich:dropSupport id="srcInstance" acceptedTypes="instance" dropListener="#{DatasetMigrate.processSrcInstanceDrop}" reRender="srcInstanceOutputText, dstInstanceOutputText, datasetSrcTable, rm, datasetInputText" limitToList="true"/>
								<h:outputText id="srcInstanceOutputText" value="" binding="#{DatasetMigrate.srcInstanceNameOutputText}" style="font-size:xx-small;"/>
							</rich:panel>
							
							<rich:panel bodyClass="inpanelBody">
								<rich:dataTable columnClasses="column-index"
									id="datasetSrcTable"
									rendered="true"
									rowClasses="list-row3"
									rows="10"
									styleClass="list-table1"
									title="Source DBS Datasets"
									width="10px"
									columnsWidth="10px"
									value="#{DatasetMigrate.allSrcDatasets}"
									var="dbsdsVar">
     									<f:facet name="header">
										<rich:columnGroup>
											<rich:column><h:outputText value="Source Processed Datasets" /></rich:column>
										</rich:columnGroup>
		    							</f:facet>
    									<rich:column width="10px">
										<a4j:outputPanel layout="block">
											<rich:dragSupport dragIndicator=":indicator" dragType="path" dragValue="#{dbsdsVar}">
												<a4j:actionparam value="#{dbsdsVar.datasetName}" name="pathLabel"/>
											</rich:dragSupport>
											<h:outputText value="#{dbsdsVar.datasetName}" style="font-size:xx-small;"/>
										</a4j:outputPanel>
    									</rich:column>
									<f:facet name="footer">
										<rich:datascroller ajaxSingle="true"
											for="datasetSrcTable"
											reRender="datasetSrcTable"
    											maxPages="10"
				    							rendered="true"/>
		      							</f:facet>
		
								</rich:dataTable>
							</rich:panel>
							
						</h:panelGrid>

						<h:panelGrid columns="1" columnClasses="gridContent">
							<rich:panel styleClass="dropTargetPanel">
								<f:facet name="header">
									<h:outputText styleClass="headerText" value="Destination DBS Instance" style="font-size:xx-small;"/>
								</f:facet>
								<rich:dropSupport id="dstInstance" acceptedTypes="instance" dropListener="#{DatasetMigrate.processDstInstanceDrop}" reRender="dstInstanceOutputText, datasetDstTable, rm" limitToList="true"/>
								<h:outputText id="dstInstanceOutputText" value="" binding="#{DatasetMigrate.dstInstanceNameOutputText}" style="font-size:xx-small;"/>
							</rich:panel>


							<rich:panel styleClass="dropTargetPanel">
								<rich:dropSupport id="dstPath" acceptedTypes="path" dropListener="#{DatasetMigrate.processDstPathDrop}" reRender="srcInstanceOutputText, dstInstanceOutputText, datasetDstTable, rm, datasetInputText" limitToList="true" ajaxSingle="false" />
	
								<rich:dataTable columnClasses="column-index"
									id="datasetDstTable"
									rendered="true"
									rowClasses="list-row3"
									rows="10"
									styleClass="list-table1"
									title="Source DBS Datasets"
									width="10px"
									value="#{DatasetMigrate.allDstDatasets}"
									var="dbsdsVar">
     									<f:facet name="header">
										<rich:columnGroup>
											<rich:column><h:outputText value="Destination Processed Datasets" /></rich:column>
										</rich:columnGroup>
		    							</f:facet>
    									<rich:column width="10px">
										<h:outputText value="#{dbsdsVar.datasetName}" style="font-size:xx-small;"/>
    									</rich:column>
									<f:facet name="footer">
										<rich:datascroller ajaxSingle="true"
											for="datasetDstTable"
											reRender="datasetDstTable"
    											maxPages="10"
				    							rendered="true"/>
		      							</f:facet>
		
								</rich:dataTable>
							</rich:panel>
						</h:panelGrid>

					</h:panelGrid>
					<rich:panel bodyClass="inpanelBody">
						<h:panelGrid columns="2" columnClasses="gridContent">
							<h:outputText value="Dataset Path" style="font-size:small;"/>
							<h:inputText id="datasetInputText" size="100" required="true" binding="#{DatasetMigrate.datasetInputText}"/>
							<a4j:commandButton id="datasetMigrateButton" value="Migrate Dataset" action="#{DatasetMigrate.migrateDatasetAction}"/>
							<h:selectManyCheckbox binding="#{DatasetMigrate.parameter}">
								<f:selectItem itemValue="PARENTS" itemLabel="With Parents"/>
								<f:selectItem itemValue="FORCE" itemLabel="Forcefully Transfer"/>
								<f:selectItem itemValue="RO" itemLabel="Read Only Transfer"/>
							</h:selectManyCheckbox>
							<rich:message for="datasetInputText">
								<f:facet name="passedMarker">
									<h:graphicImage  value="/html/images/passed.gif" /> 
								</f:facet>
								<f:facet name="errorMarker">
									<h:graphicImage value="/html/images/error.gif" />   
								</f:facet>
							</rich:message>

						</h:panelGrid>
					</rich:panel>


				</h:panelGrid>
				<rich:message id="rm" for="pg" binding="#{DatasetMigrate.generalInputMessage}">
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
