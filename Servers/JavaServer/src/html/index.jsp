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
		<rich:tabPanel id="indexPanel">
			<rich:tab label="Introduction">
				<h:panelGrid columns="1" columnClasses="gridContent" id="pg">
					<f:verbatim>	
					This is the first prototype of DBS web Administration tool. It now loads together with the DBS servlet. It invokes the DBS Servlet API for any requested operation.  For purely local DBS deployments , this will serve as a interface to remotely administer the service. This prototype has limited capabilities. The user can do the following<br/><br/>


						a) Change the status of a file (Valid, Invalid,  etc)<br/>
	b) Edit the meta data of a file<br/>
	c) Open or Close a block<br/>
	d) Add a new Tier and Tier Order <br/>
	e) Delete or Undelete a dataset. (This is still in testing phase. A lot more details and issues needs to be resolved here. It just moves a dataset to RecycleBin and restores it back for the time being)<br/>
	f) Change the status of a dataset<br/>
	g) Rename an old storage element to new storage element<br/>
	h) Updates the Lumi section information<br/>
	i) Update the Run information<br/><br/><br/>

							

	The following is under construction<br/>
	a) Deleting a file<br/>
	b) Migrating a dataset (Transfer of the dataset will have a flexible drag drop capabilty. The user would be able to drag a block or a dataset from the source DBS to the destination DBS, that will trigger the migration. Further a notification mechanism will be in place that the user can pool to check the status % complete of the migration)<br/>
	c) Updating data quality<br/>
	d) Integration with the discovery page<br/><br/><br/>



	This version of the DBSWeb Admin tool operates under insecure http, therefore it hardcodes a user (DN) information. Upcoming release will operate under secure https (gridmap authorized) and the user information will be fetched from the browser. The user will need to upload the certificate to their browser to use this web interface.<br/><br/>

	Further, the user can do the following from the interface itself<br/>
	a) Change the themes or skins of the entire web tool.<br/>
	b) Refresh the displayed page<br/>
	c) Print the content on the page<br/>
	d) Contact the dbs support via email<br/>
	
					</f:verbatim>
				</h:panelGrid>
			</rich:tab>
		</rich:tabPanel>
	</h:form>
	    
</f:view>
</body></html>
