<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{Page1.page1}" id="page1">
            <ui:html binding="#{Page1.html1}" id="html1">
                <ui:head binding="#{Page1.head1}" id="head1">
                    <ui:link binding="#{Page1.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{Page1.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{Page1.form1}" id="form1">
                        <ui:dropDown binding="#{Page1.primaryName}" id="primaryName"
                            items="#{Page1.primarydatasetDataProvider.options['PrimaryDataset.Name,PrimaryDataset.Name']}"
                            onChange="common_timeoutSubmitForm(this.form, 'primaryName');"
                            style="background-color: #ccccff; color: blue; font-size: 12px; left: 144px; top: 120px; position: absolute"
                            toolTip="Select Primary Dataset Name" valueChangeListener="#{Page1.primaryName_processValueChange}"/>
                        <ui:tabSet binding="#{Page1.tabSet1}" id="tabSet1" selected="tab1" style="height: 574px; left: 0px; top: 168px; position: absolute; width: 2302px">
                            <ui:tab action="#{Page1.tab1_action}" binding="#{Page1.tab1}" id="tab1" text="Dataset" toolTip="Displays the processed datasets and analysis datasets with all of their attributes">
                                <ui:panelLayout binding="#{Page1.layoutPanel1}" id="layoutPanel1" style="height: 800px; position: relative; width: 912px; -rave-layout: grid">
                                    <ui:tabSet binding="#{Page1.tabSet3}" id="tabSet3" lite="true" mini="true" selected="tab10" style="height: 622px; left: 24px; top: 0px; position: absolute; width: 2278px">
                                        <ui:tab action="#{Page1.tab9_action}" binding="#{Page1.tab9}" id="tab9" text="Processed Dataset" toolTip="Displays the processed dataset list with most of its attributes ">
                                            <ui:panelLayout binding="#{Page1.layoutPanel9}" id="layoutPanel9" style="height: 670px; position: relative; width: 2173px; -rave-layout: grid">
                                                <ui:table augmentTitle="false" binding="#{Page1.table2}" id="table2" paginateButton="true"
                                                    paginationControls="true" title="Table" width="1440">
                                                    <ui:tableRowGroup binding="#{Page1.tableRowGroup2}" id="tableRowGroup2" rows="10"
                                                        sourceData="#{Page1.processeddatasetDataProvider}" sourceVar="currentRow">
                                                        <ui:tableColumn binding="#{Page1.tableColumn1}" headerText="ID" id="tableColumn1" sort="ProcessedDataset.ID">
                                                            <ui:hyperlink action="#{Page1.hyperlink1_action}" binding="#{Page1.hyperlink1}" id="hyperlink1" text="#{currentRow.value['ProcessedDataset.ID']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn11}" headerText="Primary Name" id="tableColumn11" sort="PrimaryDataset.Name">
                                                            <ui:staticText binding="#{Page1.staticText13}" id="staticText13" text="#{currentRow.value['PrimaryDataset.Name']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn2}" headerText="Processed Name" id="tableColumn2" sort="ProcessedDataset.Name">
                                                            <ui:staticText binding="#{Page1.staticText2}" id="staticText2" text="#{currentRow.value['ProcessedDataset.Name']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn3}" headerText="Status" id="tableColumn3" sort="ProcDSStatus.Status">
                                                            <ui:staticText binding="#{Page1.staticText5}" id="staticText5" text="#{currentRow.value['ProcDSStatus.Status']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn4}" headerText="DistinguishedName" id="tableColumn4" sort="Person.DistinguishedName">
                                                            <ui:staticText binding="#{Page1.staticText6}" id="staticText6" text="#{currentRow.value['Person.DistinguishedName']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn7}" headerText="CreationDate" id="tableColumn7" sort="ProcessedDataset.CreationDate">
                                                            <ui:staticText binding="#{Page1.staticText9}" id="staticText9" text="#{currentRow.value['ProcessedDataset.CreationDate']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn9}" headerText="LastModificationDate" id="tableColumn9" sort="ProcessedDataset.LastModificationDate">
                                                            <ui:staticText binding="#{Page1.staticText11}" id="staticText11" text="#{currentRow.value['ProcessedDataset.LastModificationDate']}"/>
                                                        </ui:tableColumn>
                                                    </ui:tableRowGroup>
                                                </ui:table>
                                            </ui:panelLayout>
                                        </ui:tab>
                                        <ui:tab action="#{Page1.tab10_action}" binding="#{Page1.tab10}" id="tab10" text="Analysis Dataset" toolTip="Displays the analysis dataset , within the selected processed dataset , and all of its attributes ">
                                            <ui:panelLayout binding="#{Page1.layoutPanel10}" id="layoutPanel10" style="height: 828px; width: 1955px">
                                                <ui:table augmentTitle="false" binding="#{Page1.table4}" id="table4" paginateButton="true"
                                                    paginationControls="true" title="Table" width="910">
                                                    <ui:tableRowGroup binding="#{Page1.tableRowGroup4}" id="tableRowGroup4" rows="5"
                                                        sourceData="#{Page1.analysisdatasetDataProvider}" sourceVar="currentRow">
                                                        <ui:tableColumn binding="#{Page1.tableColumn24}" headerText="Name" id="tableColumn24" sort="AnalysisDataset.Name">
                                                            <ui:hyperlink action="#{Page1.hyperlink4_action}" binding="#{Page1.hyperlink4}" id="hyperlink4" text="#{currentRow.value['AnalysisDataset.Name']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn25}" headerText="Annotation" id="tableColumn25" sort="AnalysisDataset.Annotation">
                                                            <ui:staticText binding="#{Page1.staticText27}" id="staticText27" text="#{currentRow.value['AnalysisDataset.Annotation']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn27}" headerText="Definition" id="tableColumn27" sort="AnalysisDataset.Definition">
                                                            <ui:staticText binding="#{Page1.staticText45}" id="staticText45" text="#{currentRow.value['AnalysisDataset.Definition']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn28}" headerText="Type" id="tableColumn28" sort="AnalysisDataset.Type">
                                                            <ui:staticText binding="#{Page1.staticText46}" id="staticText46" text="#{currentRow.value['AnalysisDataset.Type']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn29}" headerText="Status" id="tableColumn29" sort="AnalysisDataset.Status">
                                                            <ui:staticText binding="#{Page1.staticText47}" id="staticText47" text="#{currentRow.value['AnalysisDataset.Status']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn51}" headerText="PhysicsGroup" id="tableColumn51" sort="AnalysisDataset.PhysicsGroup">
                                                            <ui:staticText binding="#{Page1.staticText49}" id="staticText49" text="#{currentRow.value['AnalysisDataset.PhysicsGroup']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn53}" headerText="CreationDate" id="tableColumn53" sort="AnalysisDataset.CreationDate">
                                                            <ui:staticText binding="#{Page1.staticText51}" id="staticText51" text="#{currentRow.value['AnalysisDataset.CreationDate']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn55}" headerText="LastModificationDate" id="tableColumn55" sort="AnalysisDataset.LastModificationDate">
                                                            <ui:staticText binding="#{Page1.staticText53}" id="staticText53" text="#{currentRow.value['AnalysisDataset.LastModificationDate']}"/>
                                                        </ui:tableColumn>
                                                    </ui:tableRowGroup>
                                                </ui:table>
                                            </ui:panelLayout>
                                        </ui:tab>
                                        <ui:tab binding="#{Page1.tab11}" id="tab11" text="Data Tiers" toolTip="Displays the list of teirs , within the selected processed dataset ">
                                            <ui:panelLayout binding="#{Page1.layoutPanel11}" id="layoutPanel11" style="height: 876px; width: 2219px">
                                                <ui:table augmentTitle="false" binding="#{Page1.table5}" id="table5" paginateButton="true"
                                                    paginationControls="true" title="Table" width="288">
                                                    <ui:tableRowGroup binding="#{Page1.tableRowGroup5}" id="tableRowGroup5" rows="10"
                                                        sourceData="#{Page1.datatierDataProvider}" sourceVar="currentRow">
                                                        <ui:tableColumn binding="#{Page1.tableColumn26}" headerText="Name" id="tableColumn26" sort="DataTier.Name">
                                                            <ui:staticText binding="#{Page1.staticText28}" id="staticText28" text="#{currentRow.value['DataTier.Name']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn56}" headerText="LastModificationDate" id="tableColumn56" sort="DataTier.LastModificationDate">
                                                            <ui:staticText binding="#{Page1.staticText54}" id="staticText54" text="#{currentRow.value['DataTier.LastModificationDate']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn57}" headerText="CreationDate" id="tableColumn57" sort="DataTier.CreationDate">
                                                            <ui:staticText binding="#{Page1.staticText55}" id="staticText55" text="#{currentRow.value['DataTier.CreationDate']}"/>
                                                        </ui:tableColumn>
                                                    </ui:tableRowGroup>
                                                </ui:table>
                                            </ui:panelLayout>
                                        </ui:tab>
                                        <ui:tab binding="#{Page1.tab12}" id="tab12" text="Runs" toolTip="Displays the list of runs  , within the selected processed dataset , and all of its attributes ">
                                            <ui:panelLayout binding="#{Page1.layoutPanel12}" id="layoutPanel12" style="height: 828px; width: 2219px">
                                                <ui:table augmentTitle="false" binding="#{Page1.table6}" id="table6" paginateButton="true"
                                                    paginationControls="true" title="Table" width="1048">
                                                    <ui:tableRowGroup binding="#{Page1.tableRowGroup6}" id="tableRowGroup6" rows="10"
                                                        sourceData="#{Page1.runsDataProvider}" sourceVar="currentRow">
                                                        <ui:tableColumn binding="#{Page1.tableColumn59}" headerText="RunNumber" id="tableColumn59" sort="Runs.RunNumber">
                                                            <ui:staticText binding="#{Page1.staticText57}" id="staticText57" text="#{currentRow.value['Runs.RunNumber']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn60}" headerText="NumberOfEvents" id="tableColumn60" sort="Runs.NumberOfEvents">
                                                            <ui:staticText binding="#{Page1.staticText58}" id="staticText58" text="#{currentRow.value['Runs.NumberOfEvents']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn61}" headerText="NumberOfLumiSections" id="tableColumn61" sort="Runs.NumberOfLumiSections">
                                                            <ui:staticText binding="#{Page1.staticText59}" id="staticText59" text="#{currentRow.value['Runs.NumberOfLumiSections']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn62}" headerText="TotalLuminosity" id="tableColumn62" sort="Runs.TotalLuminosity">
                                                            <ui:staticText binding="#{Page1.staticText60}" id="staticText60" text="#{currentRow.value['Runs.TotalLuminosity']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn63}" headerText="StoreNumber" id="tableColumn63" sort="Runs.StoreNumber">
                                                            <ui:staticText binding="#{Page1.staticText61}" id="staticText61" text="#{currentRow.value['Runs.StoreNumber']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn64}" headerText="StartOfRun" id="tableColumn64" sort="Runs.StartOfRun">
                                                            <ui:staticText binding="#{Page1.staticText62}" id="staticText62" text="#{currentRow.value['Runs.StartOfRun']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn65}" headerText="EndOfRun" id="tableColumn65" sort="Runs.EndOfRun">
                                                            <ui:staticText binding="#{Page1.staticText63}" id="staticText63" text="#{currentRow.value['Runs.EndOfRun']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn67}" headerText="CreationDate" id="tableColumn67" sort="Runs.CreationDate">
                                                            <ui:staticText binding="#{Page1.staticText65}" id="staticText65" text="#{currentRow.value['Runs.CreationDate']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn69}" headerText="LastModificationDate" id="tableColumn69" sort="Runs.LastModificationDate">
                                                            <ui:staticText binding="#{Page1.staticText67}" id="staticText67" text="#{currentRow.value['Runs.LastModificationDate']}"/>
                                                        </ui:tableColumn>
                                                    </ui:tableRowGroup>
                                                </ui:table>
                                            </ui:panelLayout>
                                        </ui:tab>
                                        <ui:tab binding="#{Page1.tab13}" id="tab13" text="Algorithms" toolTip="Displays the list of algorithms  , within the selected processed dataset , and all of its attributes ">
                                            <ui:panelLayout binding="#{Page1.layoutPanel13}" id="layoutPanel13" style="height: 852px; width: 2219px">
                                                <ui:table augmentTitle="false" binding="#{Page1.table7}" id="table7" paginateButton="true"
                                                    paginationControls="true" title="Table" width="438">
                                                    <ui:tableRowGroup binding="#{Page1.tableRowGroup7}" id="tableRowGroup7" rows="10"
                                                        sourceData="#{Page1.algorithmconfigDataProvider}" sourceVar="currentRow">
                                                        <ui:tableColumn binding="#{Page1.tableColumn23}" headerText="ExecutableName" id="tableColumn23" sort="AppExecutable.ExecutableName">
                                                            <ui:staticText binding="#{Page1.staticText1}" id="staticText1" text="#{currentRow.value['AppExecutable.ExecutableName']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn70}" headerText="FamilyName" id="tableColumn70" sort="AppFamily.FamilyName">
                                                            <ui:staticText binding="#{Page1.staticText68}" id="staticText68" text="#{currentRow.value['AppFamily.FamilyName']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn71}" headerText="Version" id="tableColumn71" sort="AppVersion.Version">
                                                            <ui:staticText binding="#{Page1.staticText69}" id="staticText69" text="#{currentRow.value['AppVersion.Version']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn72}" headerText="Name" id="tableColumn72" sort="QueryableParameterSet.Name">
                                                            <ui:staticText binding="#{Page1.staticText70}" id="staticText70" text="#{currentRow.value['QueryableParameterSet.Name']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn78}" headerText="CreationDate" id="tableColumn78" sort="AlgorithmConfig.CreationDate">
                                                            <ui:staticText binding="#{Page1.staticText76}" id="staticText76" text="#{currentRow.value['AlgorithmConfig.CreationDate']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn79}" headerText="LastModificationDate" id="tableColumn79" sort="AlgorithmConfig.LastModificationDate">
                                                            <ui:staticText binding="#{Page1.staticText77}" id="staticText77" text="#{currentRow.value['AlgorithmConfig.LastModificationDate']}"/>
                                                        </ui:tableColumn>
                                                    </ui:tableRowGroup>
                                                </ui:table>
                                            </ui:panelLayout>
                                        </ui:tab>
                                        <ui:tab binding="#{Page1.tab15}" id="tab15" text="Parent Dataset">
                                            <ui:panelLayout binding="#{Page1.layoutPanel15}" id="layoutPanel15" style="height: 996px; width: 2267px">
                                                <ui:table augmentTitle="false" binding="#{Page1.table13}" id="table13" paginateButton="true"
                                                    paginationControls="true" title="Table" width="308">
                                                    <ui:tableRowGroup binding="#{Page1.tableRowGroup13}" id="tableRowGroup13" rows="10"
                                                        sourceData="#{Page1.procdsparentDataProvider}" sourceVar="currentRow">
                                                        <ui:tableColumn binding="#{Page1.tableColumn15}" headerText="ID" id="tableColumn15" sort="ProcessedDataset.ID">
                                                            <ui:hyperlink action="#{Page1.hyperlink3_action}" binding="#{Page1.hyperlink3}" id="hyperlink3" text="#{currentRow.value['ProcessedDataset.ID']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn16}" headerText="Primary Name" id="tableColumn16" sort="PrimaryDataset.Name">
                                                            <ui:staticText binding="#{Page1.staticText21}" id="staticText21" text="#{currentRow.value['PrimaryDataset.Name']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn19}" headerText="Processed Name" id="tableColumn19" sort="ProcessedDataset.Name">
                                                            <ui:staticText binding="#{Page1.staticText22}" id="staticText22" text="#{currentRow.value['ProcessedDataset.Name']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn20}" headerText="CreationDate" id="tableColumn20" sort="ProcessedDataset.CreationDate">
                                                            <ui:staticText binding="#{Page1.staticText23}" id="staticText23" text="#{currentRow.value['ProcessedDataset.CreationDate']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn21}" headerText="LastModificationDate" id="tableColumn21" sort="ProcessedDataset.LastModificationDate">
                                                            <ui:staticText binding="#{Page1.staticText24}" id="staticText24" text="#{currentRow.value['ProcessedDataset.LastModificationDate']}"/>
                                                        </ui:tableColumn>
                                                    </ui:tableRowGroup>
                                                </ui:table>
                                            </ui:panelLayout>
                                        </ui:tab>
                                    </ui:tabSet>
                                </ui:panelLayout>
                            </ui:tab>
                            <ui:tab binding="#{Page1.tab2}" id="tab2" text="Blocks" toolTip="Displays the list of blocks and storage elements , within the selected processed dataset , and all of its attributes ">
                                <ui:panelLayout binding="#{Page1.layoutPanel2}" id="layoutPanel2" style="height: 128px; width: 693px">
                                    <ui:table augmentTitle="false" binding="#{Page1.table3}" id="table3" paginateButton="true" paginationControls="true"
                                        style="left: 24px; top: 24px; position: absolute; width: 360px" title="Blocks" width="198">
                                        <ui:tableRowGroup binding="#{Page1.tableRowGroup3}" id="tableRowGroup3" rows="10"
                                            sourceData="#{Page1.blockDataProvider}" sourceVar="currentRow">
                                            <ui:tableColumn binding="#{Page1.tableColumn17}" headerText="BlockName" id="tableColumn17" sort="Block.Name" width="10">
                                                <ui:staticText binding="#{Page1.staticText16}" id="staticText16" text="#{currentRow.value['Block.Name']}"/>
                                            </ui:tableColumn>
                                            <ui:tableColumn binding="#{Page1.tableColumn39}" headerText="SEName" id="tableColumn39" sort="StorageElement.SEName">
                                                <ui:staticText binding="#{Page1.staticText37}" id="staticText37" text="#{currentRow.value['StorageElement.SEName']}"/>
                                            </ui:tableColumn>
                                            <ui:tableColumn binding="#{Page1.tableColumn30}" headerText="BlockSize" id="tableColumn30" sort="Block.BlockSize">
                                                <ui:staticText binding="#{Page1.staticText29}" id="staticText29" text="#{currentRow.value['Block.BlockSize']}"/>
                                            </ui:tableColumn>
                                            <ui:tableColumn binding="#{Page1.tableColumn31}" headerText="NumberOfFiles" id="tableColumn31" sort="Block.NumberOfFiles">
                                                <ui:staticText binding="#{Page1.staticText30}" id="staticText30" text="#{currentRow.value['Block.NumberOfFiles']}"/>
                                            </ui:tableColumn>
                                            <ui:tableColumn binding="#{Page1.tableColumn32}" headerText="NumberOfEvents" id="tableColumn32" sort="Block.NumberOfEvents">
                                                <ui:staticText binding="#{Page1.staticText31}" id="staticText31" text="#{currentRow.value['Block.NumberOfEvents']}"/>
                                            </ui:tableColumn>
                                            <ui:tableColumn binding="#{Page1.tableColumn33}" headerText="OpenForWriting" id="tableColumn33" sort="Block.OpenForWriting">
                                                <ui:staticText binding="#{Page1.staticText32}" id="staticText32" text="#{currentRow.value['Block.OpenForWriting']}"/>
                                            </ui:tableColumn>
                                            <ui:tableColumn binding="#{Page1.tableColumn34}" headerText="CreatedBy" id="tableColumn34" sort="Block.CreatedBy">
                                                <ui:staticText binding="#{Page1.staticText33}" id="staticText33" text="#{currentRow.value['Block.CreatedBy']}"/>
                                            </ui:tableColumn>
                                            <ui:tableColumn binding="#{Page1.tableColumn35}" headerText="CreationDate" id="tableColumn35" sort="Block.CreationDate">
                                                <ui:staticText binding="#{Page1.staticText34}" id="staticText34" text="#{currentRow.value['Block.CreationDate']}"/>
                                            </ui:tableColumn>
                                            <ui:tableColumn binding="#{Page1.tableColumn36}" headerText="LastModifiedBy" id="tableColumn36" sort="Block.LastModifiedBy">
                                                <ui:staticText binding="#{Page1.staticText35}" id="staticText35" text="#{currentRow.value['Block.LastModifiedBy']}"/>
                                            </ui:tableColumn>
                                            <ui:tableColumn binding="#{Page1.tableColumn37}" headerText="LastModificationDate" id="tableColumn37" sort="Block.LastModificationDate">
                                                <ui:staticText binding="#{Page1.staticText36}" id="staticText36" text="#{currentRow.value['Block.LastModificationDate']}"/>
                                            </ui:tableColumn>
                                        </ui:tableRowGroup>
                                    </ui:table>
                                </ui:panelLayout>
                            </ui:tab>
                            <ui:tab action="#{Page1.tab3_action}" binding="#{Page1.tab3}" id="tab3" text="Files" toolTip="Displays the list of Files  , within the selected processed dataset , and all of its attribute">
                                <ui:panelLayout binding="#{Page1.layoutPanel3}" id="layoutPanel3" style="height: 320px; position: relative; width: 100%; -rave-layout: grid">
                                    <ui:tabSet binding="#{Page1.tabSet2}" id="tabSet2" lite="true" mini="true" selected="tab14" style="height: 574px; left: 24px; top: 0px; position: absolute; width: 2350px">
                                        <ui:tab binding="#{Page1.tab4}" id="tab4" text="Files">
                                            <ui:panelLayout binding="#{Page1.layoutPanel4}" id="layoutPanel4" style="height: 492px; width: 1883px">
                                                <ui:table augmentTitle="false" binding="#{Page1.table1}" id="table1" paginateButton="true"
                                                    paginationControls="true" style="left: 0px; top: 0px; position: absolute" title="Table" width="729">
                                                    <ui:tableRowGroup binding="#{Page1.tableRowGroup1}" id="tableRowGroup1" rows="10"
                                                        sourceData="#{Page1.filesDataProvider}" sourceVar="currentRow">
                                                        <ui:tableColumn binding="#{Page1.tableColumn92}" headerText="ID" id="tableColumn92" sort="Files.ID">
                                                            <ui:hyperlink action="#{Page1.hyperlink2_action}" binding="#{Page1.hyperlink2}" id="hyperlink2" text="#{currentRow.value['Files.ID']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn18}" headerText="LogicalFileName" id="tableColumn18" sort="Files.LogicalFileName">
                                                            <ui:staticText binding="#{Page1.staticText90}" id="staticText90" text="#{currentRow.value['Files.LogicalFileName']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn38}" headerText="Checksum" id="tableColumn38" sort="Files.Checksum">
                                                            <ui:staticText binding="#{Page1.staticText3}" id="staticText3" text="#{currentRow.value['Files.Checksum']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn40}" headerText="NumberOfEvents" id="tableColumn40" sort="Files.NumberOfEvents">
                                                            <ui:staticText binding="#{Page1.staticText4}" id="staticText4" text="#{currentRow.value['Files.NumberOfEvents']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn41}" headerText="FileSize" id="tableColumn41" sort="Files.FileSize">
                                                            <ui:staticText binding="#{Page1.staticText15}" id="staticText15" text="#{currentRow.value['Files.FileSize']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn42}" headerText="Type" id="tableColumn42" sort="FileType.Type">
                                                            <ui:staticText binding="#{Page1.staticText17}" id="staticText17" text="#{currentRow.value['FileType.Type']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn43}" headerText="Status" id="tableColumn43" sort="FileStatus.Status">
                                                            <ui:staticText binding="#{Page1.staticText38}" id="staticText38" text="#{currentRow.value['FileStatus.Status']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn45}" headerText="QueryableMetadata" id="tableColumn45" sort="Files.QueryableMetadata">
                                                            <ui:staticText binding="#{Page1.staticText40}" id="staticText40" text="#{currentRow.value['Files.QueryableMetadata']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn47}" headerText="CreationDate" id="tableColumn47" sort="Files.CreationDate">
                                                            <ui:staticText binding="#{Page1.staticText42}" id="staticText42" text="#{currentRow.value['Files.CreationDate']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn48}" headerText="LastModifiedBy" id="tableColumn48" sort="Files.LastModifiedBy">
                                                            <ui:staticText binding="#{Page1.staticText43}" id="staticText43" text="#{currentRow.value['Files.LastModifiedBy']}"/>
                                                        </ui:tableColumn>
                                                    </ui:tableRowGroup>
                                                </ui:table>
                                            </ui:panelLayout>
                                        </ui:tab>
                                        <ui:tab binding="#{Page1.tab5}" id="tab5" text="Parents" toolTip="Displays the list of parent file names , within the selected file , and all of its attribute">
                                            <ui:panelLayout binding="#{Page1.layoutPanel5}" id="layoutPanel5" style="height: 574px; width: 2387px">
                                                <ui:table augmentTitle="false" binding="#{Page1.table8}" id="table8" paginateButton="true"
                                                    paginationControls="true" title="Table" width="669">
                                                    <ui:tableRowGroup binding="#{Page1.tableRowGroup8}" id="tableRowGroup8" rows="10"
                                                        sourceData="#{Page1.filesParentDataProvider}" sourceVar="currentRow">
                                                        <ui:tableColumn binding="#{Page1.tableColumn74}" headerText="LogicalFileName" id="tableColumn74" sort="Files.LogicalFileName">
                                                            <ui:staticText binding="#{Page1.staticText72}" id="staticText72" text="#{currentRow.value['Files.LogicalFileName']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn73}" headerText="Status" id="tableColumn73" sort="FileStatus.Status">
                                                            <ui:staticText binding="#{Page1.staticText71}" id="staticText71" text="#{currentRow.value['FileStatus.Status']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn75}" headerText="Type" id="tableColumn75" sort="FileType.Type">
                                                            <ui:staticText binding="#{Page1.staticText73}" id="staticText73" text="#{currentRow.value['FileType.Type']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn81}" headerText="Checksum" id="tableColumn81" sort="Files.Checksum">
                                                            <ui:staticText binding="#{Page1.staticText79}" id="staticText79" text="#{currentRow.value['Files.Checksum']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn82}" headerText="NumberOfEvents" id="tableColumn82" sort="Files.NumberOfEvents">
                                                            <ui:staticText binding="#{Page1.staticText80}" id="staticText80" text="#{currentRow.value['Files.NumberOfEvents']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn83}" headerText="FileSize" id="tableColumn83" sort="Files.FileSize">
                                                            <ui:staticText binding="#{Page1.staticText81}" id="staticText81" text="#{currentRow.value['Files.FileSize']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn87}" headerText="QueryableMetadata" id="tableColumn87" sort="Files.QueryableMetadata">
                                                            <ui:staticText binding="#{Page1.staticText85}" id="staticText85" text="#{currentRow.value['Files.QueryableMetadata']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn93}" headerText="CreationDate" id="tableColumn93" sort="FileParentage.CreationDate">
                                                            <ui:staticText binding="#{Page1.staticText91}" id="staticText91" text="#{currentRow.value['FileParentage.CreationDate']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn94}" headerText="LastModifiedBy" id="tableColumn94" sort="FileParentage.LastModifiedBy">
                                                            <ui:staticText binding="#{Page1.staticText92}" id="staticText92" text="#{currentRow.value['FileParentage.LastModifiedBy']}"/>
                                                        </ui:tableColumn>
                                                    </ui:tableRowGroup>
                                                </ui:table>
                                            </ui:panelLayout>
                                        </ui:tab>
                                        <ui:tab binding="#{Page1.tab6}" id="tab6" text="Data Tiers" toolTip="Displays the list of data tiers  within the selected file ">
                                            <ui:panelLayout binding="#{Page1.layoutPanel6}" id="layoutPanel6" style="height: 492px; width: 2435px">
                                                <ui:table augmentTitle="false" binding="#{Page1.table9}" id="table9" paginateButton="true"
                                                    paginationControls="true" title="Table" width="240">
                                                    <ui:tableRowGroup binding="#{Page1.tableRowGroup9}" id="tableRowGroup9" rows="10"
                                                        sourceData="#{Page1.filetierDataProvider}" sourceVar="currentRow">
                                                        <ui:tableColumn binding="#{Page1.tableColumn89}" headerText="Name" id="tableColumn89" sort="DataTier.Name">
                                                            <ui:staticText binding="#{Page1.staticText87}" id="staticText87" text="#{currentRow.value['DataTier.Name']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn84}" headerText="CreationDate" id="tableColumn84" sort="FileTier.CreationDate">
                                                            <ui:staticText binding="#{Page1.staticText82}" id="staticText82" text="#{currentRow.value['FileTier.CreationDate']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn86}" headerText="LastModificationDate" id="tableColumn86" sort="FileTier.LastModificationDate">
                                                            <ui:staticText binding="#{Page1.staticText84}" id="staticText84" text="#{currentRow.value['FileTier.LastModificationDate']}"/>
                                                        </ui:tableColumn>
                                                    </ui:tableRowGroup>
                                                </ui:table>
                                            </ui:panelLayout>
                                        </ui:tab>
                                        <ui:tab binding="#{Page1.tab7}" id="tab7" text="Algorithms" toolTip="Displays the list of algirithms , within the selected file , and all of its attribute">
                                            <ui:panelLayout binding="#{Page1.layoutPanel7}" id="layoutPanel7" style="height: 492px; width: 779px">
                                                <ui:table augmentTitle="false" binding="#{Page1.table12}" id="table12" paginateButton="true"
                                                    paginationControls="true" title="Table" width="420">
                                                    <ui:tableRowGroup binding="#{Page1.tableRowGroup12}" id="tableRowGroup12" rows="10"
                                                        sourceData="#{Page1.filealgoDataProvider}" sourceVar="currentRow">
                                                        <ui:tableColumn binding="#{Page1.tableColumn115}" headerText="ExecutableName" id="tableColumn115" sort="AppExecutable.ExecutableName">
                                                            <ui:staticText binding="#{Page1.staticText113}" id="staticText113" text="#{currentRow.value['AppExecutable.ExecutableName']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn116}" headerText="FamilyName" id="tableColumn116" sort="AppFamily.FamilyName">
                                                            <ui:staticText binding="#{Page1.staticText114}" id="staticText114" text="#{currentRow.value['AppFamily.FamilyName']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn117}" headerText="Version" id="tableColumn117" sort="AppVersion.Version">
                                                            <ui:staticText binding="#{Page1.staticText115}" id="staticText115" text="#{currentRow.value['AppVersion.Version']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn118}" headerText="Name" id="tableColumn118" sort="QueryableParameterSet.Name">
                                                            <ui:staticText binding="#{Page1.staticText116}" id="staticText116" text="#{currentRow.value['QueryableParameterSet.Name']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn111}" headerText="CreationDate" id="tableColumn111" sort="FileAlgo.CreationDate">
                                                            <ui:staticText binding="#{Page1.staticText109}" id="staticText109" text="#{currentRow.value['FileAlgo.CreationDate']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn113}" headerText="LastModificationDate" id="tableColumn113" sort="FileAlgo.LastModificationDate">
                                                            <ui:staticText binding="#{Page1.staticText111}" id="staticText111" text="#{currentRow.value['FileAlgo.LastModificationDate']}"/>
                                                        </ui:tableColumn>
                                                    </ui:tableRowGroup>
                                                </ui:table>
                                            </ui:panelLayout>
                                        </ui:tab>
                                        <ui:tab binding="#{Page1.tab8}" id="tab8" text="Lumi Sections" toolTip="Displays the list of lumi sections , within the selected file , and all of its attribute">
                                            <ui:panelLayout binding="#{Page1.layoutPanel8}" id="layoutPanel8" style="height: 516px; width: 920px">
                                                <ui:table augmentTitle="false" binding="#{Page1.table11}" id="table11" paginateButton="true"
                                                    paginationControls="true" title="Table" width="924">
                                                    <ui:tableRowGroup binding="#{Page1.tableRowGroup11}" id="tableRowGroup11" rows="10"
                                                        sourceData="#{Page1.lumisectionDataProvider}" sourceVar="currentRow">
                                                        <ui:tableColumn binding="#{Page1.tableColumn90}" headerText="LumiSectionNumber" id="tableColumn90" sort="LumiSection.LumiSectionNumber">
                                                            <ui:staticText binding="#{Page1.staticText88}" id="staticText88" text="#{currentRow.value['LumiSection.LumiSectionNumber']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn91}" headerText="RunNumber" id="tableColumn91" sort="LumiSection.RunNumber">
                                                            <ui:staticText binding="#{Page1.staticText89}" id="staticText89" text="#{currentRow.value['LumiSection.RunNumber']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn101}" headerText="StartEventNumber" id="tableColumn101" sort="LumiSection.StartEventNumber">
                                                            <ui:staticText binding="#{Page1.staticText99}" id="staticText99" text="#{currentRow.value['LumiSection.StartEventNumber']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn102}" headerText="EndEventNumber" id="tableColumn102" sort="LumiSection.EndEventNumber">
                                                            <ui:staticText binding="#{Page1.staticText100}" id="staticText100" text="#{currentRow.value['LumiSection.EndEventNumber']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn103}" headerText="LumiStartTime" id="tableColumn103" sort="LumiSection.LumiStartTime">
                                                            <ui:staticText binding="#{Page1.staticText101}" id="staticText101" text="#{currentRow.value['LumiSection.LumiStartTime']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn104}" headerText="LumiEndTime" id="tableColumn104" sort="LumiSection.LumiEndTime">
                                                            <ui:staticText binding="#{Page1.staticText102}" id="staticText102" text="#{currentRow.value['LumiSection.LumiEndTime']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn106}" headerText="CreationDate" id="tableColumn106" sort="LumiSection.CreationDate">
                                                            <ui:staticText binding="#{Page1.staticText104}" id="staticText104" text="#{currentRow.value['LumiSection.CreationDate']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn107}" headerText="LastModifiedBy" id="tableColumn107" sort="LumiSection.LastModifiedBy">
                                                            <ui:staticText binding="#{Page1.staticText105}" id="staticText105" text="#{currentRow.value['LumiSection.LastModifiedBy']}"/>
                                                        </ui:tableColumn>
                                                    </ui:tableRowGroup>
                                                </ui:table>
                                            </ui:panelLayout>
                                        </ui:tab>
                                        <ui:tab binding="#{Page1.tab14}" id="tab14" text="Branch" toolTip="Displays the list of branch names , within the selected file ">
                                            <ui:panelLayout binding="#{Page1.layoutPanel14}" id="layoutPanel14" style="height: 540px; width: 2339px">
                                                <ui:table augmentTitle="false" binding="#{Page1.table10}" id="table10" paginateButton="true"
                                                    paginationControls="true" title="Table" width="240">
                                                    <ui:tableRowGroup binding="#{Page1.tableRowGroup10}" id="tableRowGroup10" rows="10"
                                                        sourceData="#{Page1.filebranchDataProvider}" sourceVar="currentRow">
                                                        <ui:tableColumn binding="#{Page1.tableColumn100}" headerText="Name" id="tableColumn100" sort="Branch.Name">
                                                            <ui:staticText binding="#{Page1.staticText98}" id="staticText98" text="#{currentRow.value['Branch.Name']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn96}" headerText="CreationDate" id="tableColumn96" sort="FileBranch.CreationDate">
                                                            <ui:staticText binding="#{Page1.staticText94}" id="staticText94" text="#{currentRow.value['FileBranch.CreationDate']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{Page1.tableColumn98}" headerText="LastModificationDate" id="tableColumn98" sort="FileBranch.LastModificationDate">
                                                            <ui:staticText binding="#{Page1.staticText96}" id="staticText96" text="#{currentRow.value['FileBranch.LastModificationDate']}"/>
                                                        </ui:tableColumn>
                                                    </ui:tableRowGroup>
                                                </ui:table>
                                            </ui:panelLayout>
                                        </ui:tab>
                                    </ui:tabSet>
                                </ui:panelLayout>
                            </ui:tab>
                        </ui:tabSet>
                        <ui:pageSeparator binding="#{Page1.pageSeparator1}" id="pageSeparator1" style="color: gray; height: 7px; left: 0px; top: 72px; position: absolute; width: 2376px"/>
                        <ui:label binding="#{Page1.label2}" id="label2" style="color: navy; left: 24px; top: 120px; position: absolute" text="Primary Dataset"/>
                        <ui:imageHyperlink binding="#{Page1.imageHyperlink1}" height="32" id="imageHyperlink1" imageURL="/resources/cms-logo.gif"
                            style="left: 48px; top: 48px; position: absolute" width="48"/>
                        <ui:staticText binding="#{Page1.staticText7}" id="staticText7"
                            style="color: gray; font-size: 18px; height: 24px; left: 120px; top: 48px; position: absolute; width: 264px" text="DBS DATA DISCOVERY PAGE"/>
                        <ui:pageSeparator binding="#{Page1.pageSeparator2}" id="pageSeparator2" style="color: gray; height: 7px; left: 0px; top: 0px; position: absolute; width: 2376px"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
