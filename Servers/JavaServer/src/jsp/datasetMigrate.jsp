<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<f:view>
	<jsp:include page="menu.jsp"/>
	<rich:dragIndicator id="indicator" />
	<h:form id="form1">
		<rich:tabPanel id="datasetMigratePanel">
			<rich:tab label="Dataset Migrate">
				<rich:toolTip followMouse="true" direction="top-right" delay="2500" style="width:250px" value="To migrate a dataset first drap and drop the Local or Global DBS to source or destination DBS. This will populate the list of processed dataset in the selected source DBS. Then drag and drop any processed dataset from the source to destination DBS to start the migration. To reset the form click on Reset button"/>
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
								<rich:dropSupport id="srcInstance" acceptedTypes="instance" dropListener="#{DatasetMigrate.processSrcInstanceDrop}" reRender="srcInstanceOutputText,datasetSrcTable"/>
								<h:outputText id="srcInstanceOutputText" value="" binding="#{DatasetMigrate.srcInstanceNameOutputText}" style="font-size:xx-small;"/>
							</rich:panel>
							
							<rich:panel bodyClass="inpanelBody">
								<rich:dataTable columnClasses="column-index"
									id="datasetSrcTable"
									rendered="true"
									rowClasses="list-row3"
									rows="20"
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
								<rich:dropSupport id="dstInstance" acceptedTypes="instance" dropListener="#{DatasetMigrate.processDstInstanceDrop}" reRender="dstInstanceOutputText, datasetDstTable"/>
								<h:outputText id="dstInstanceOutputText" value="" binding="#{DatasetMigrate.dstInstanceNameOutputText}" style="font-size:xx-small;"/>
							</rich:panel>


							<rich:panel styleClass="dropTargetPanel">
								<rich:dropSupport id="dstPath" acceptedTypes="path" dropListener="#{DatasetMigrate.processDstPathDrop}" reRender="datasetDstTable"/>
	
								<rich:dataTable columnClasses="column-index"
									id="datasetDstTable"
									rendered="true"
									rowClasses="list-row3"
									rows="20"
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
							
				</h:panelGrid>
				<rich:message for="pg" binding="#{DatasetMigrate.generalInputMessage}">
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
