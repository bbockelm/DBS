<?xml version="1.0" encoding="UTF-8"?>

<project version="2">
	<attribs>
		<attrib name="build">439</attrib>
		<attrib name="serial">285947</attrib>
	</attribs>
	<database>
		<attribs>
			<attrib name="name">DBS_NEW_ERA</attrib>
			<attrib name="preSql">use dbs_new_era_v01;</attrib>
			<attrib name="postSql"/>
			<attrib name="ddUseDDEquiv">false</attrib>
			<attrib name="codeUseBuild">false</attrib>
			<attrib name="summUseDDEquiv">false</attrib>
		</attribs>
		<revisions>
			<list>
				<header>
					<elem>version</elem>
					<elem>date</elem>
					<elem>descr</elem>
				</header>
				<row>
					<elem>0.2</elem>
					<elem>2006-10-04 13:03:48</elem>
					<elem>According tochanges suggested by Dan and other discussions</elem>
				</row>
				<row>
					<elem>0.3</elem>
					<elem>2006-10-11 09:08:04</elem>
					<elem>Making some corrections, adding details to Lumi and Run tables</elem>
				</row>
			</list>
		</revisions>
		<fieldAttribs>
			<list>
				<header>
					<elem>id</elem>
					<elem>name</elem>
					<elem>sqlName</elem>
					<elem>type</elem>
					<elem>scope</elem>
					<elem>useInDD</elem>
					<elem>useInSumm</elem>
					<elem>width</elem>
					<elem>descr</elem>
				</header>
				<row>
					<elem>15</elem>
					<elem>PrKey</elem>
					<elem>primary key</elem>
					<elem>b</elem>
					<elem>t</elem>
					<elem>false</elem>
					<elem>true</elem>
					<elem>60</elem>
					<elem>Primary key for a table</elem>
				</row>
				<row>
					<elem>16</elem>
					<elem>Unq</elem>
					<elem>unique</elem>
					<elem>b</elem>
					<elem>f</elem>
					<elem>false</elem>
					<elem>true</elem>
					<elem>60</elem>
					<elem>Unique for a single field</elem>
				</row>
				<row>
					<elem>17</elem>
					<elem>NotN</elem>
					<elem>not null</elem>
					<elem>b</elem>
					<elem>f</elem>
					<elem>false</elem>
					<elem>true</elem>
					<elem>60</elem>
					<elem/>
				</row>
				<row>
					<elem>338</elem>
					<elem>MUnq</elem>
					<elem>unique</elem>
					<elem>b</elem>
					<elem>t</elem>
					<elem>false</elem>
					<elem>true</elem>
					<elem>60</elem>
					<elem>Unique for several fields</elem>
				</row>
				<row>
					<elem>339</elem>
					<elem>Def</elem>
					<elem>default</elem>
					<elem>s</elem>
					<elem>f</elem>
					<elem>false</elem>
					<elem>true</elem>
					<elem>60</elem>
					<elem>Field's default value </elem>
				</row>
				<row>
					<elem>340</elem>
					<elem>Idx1</elem>
					<elem/>
					<elem>b</elem>
					<elem>i</elem>
					<elem>false</elem>
					<elem>true</elem>
					<elem>60</elem>
					<elem>An index</elem>
				</row>
			</list>
		</fieldAttribs>
		<vars>
			<list>
				<header>
					<elem>name</elem>
					<elem>type</elem>
					<elem>value</elem>
					<elem>descr</elem>
				</header>
			</list>
		</vars>
		<modsConfig>
			<DocsGenModule>
				<pdf>
					<output>/Users/anzar/DBSSchemaFridayThe13thOct2006/DBS.pdf</output>
				</pdf>
				<html>
					<output>/Users/anzar/DBSSchemaFridayThe13thOct2006/XHTML</output>
				</html>
			</DocsGenModule>
			<CodeGenModule>
				<cpp>
					<output>d:\dbs\cpp</output>
				</cpp>
				<java>
					<foreignKeyAsClassRef>Y</foreignKeyAsClassRef>
					<output>d:\dbs\java</output>
					<jdoFile>Y</jdoFile>
					<setter>Y</setter>
					<serializable>Y</serializable>
					<foreignKeyArray>Y</foreignKeyArray>
					<getter>Y</getter>
					<package>DBS</package>
					<useJdo>Y</useJdo>
				</java>
			</CodeGenModule>
			<SqlGenModule>
				<stdSql>
					<output>/Users/anzar/DBSSchemaFridayThe13thOct2006/DBS.sql</output>
					<order>s</order>
					<genComments>N</genComments>
					<genIndexes>N</genIndexes>
					<genTriggers>Y</genTriggers>
					<genDropStmt>N</genDropStmt>
					<genFunctions>N</genFunctions>
					<genRules>N</genRules>
					<genViews>N</genViews>
					<genProcedures>Y</genProcedures>
					<genInlineFKs>N</genInlineFKs>
					<genPreSQL>Y</genPreSQL>
					<genSequences>Y</genSequences>
				</stdSql>
				<oraSql>
					<output>/Users/anzar/DBSSchemaFridayThe13thOct2006/DBS_ORACLE.sql</output>
					<genComments>N</genComments>
					<genDropStmt>N</genDropStmt>
					<genViews>N</genViews>
					<genFunctions>Y</genFunctions>
					<genProcedures>N</genProcedures>
					<genInlineFKs>N</genInlineFKs>
				</oraSql>
				<pgSql>
					<output>/Users/anzar/DBSSchemaFridayThe13thOct2006/DBS_ORACLE_2.sql</output>
					<genInlineFKs>N</genInlineFKs>
					<genProcedures>N</genProcedures>
				</pgSql>
			</SqlGenModule>
			<SummaryGenModule>
				<ascii>
					<output>/Users/anzar/DBSSchema10032006/Summary.txt</output>
				</ascii>
			</SummaryGenModule>
		</modsConfig>
		<modsUsage>
			<module name="SummaryGenModule.ascii"/>
			<module name="DocsGenModule.html"/>
			<module name="DocsGenModule.pdf"/>
			<module name="SqlGenModule.stdSql"/>
			<module name="SqlGenModule.pgSql"/>
			<module name="SqlGenModule.oraSql"/>
			<module name="CodeGenModule.java"/>
			<module name="CodeGenModule.cpp"/>
		</modsUsage>
		<dataTypes>
			<constFolder>
				<type>
					<attribs>
						<attrib name="name">int</attrib>
						<attrib name="ddEquiv"/>
						<attrib name="id">5</attrib>
					</attribs>
					<domain>
						<attribs>
							<attrib name="minValue"/>
							<attrib name="outRange">false</attrib>
							<attrib name="maxValue"/>
							<attrib name="type">none</attrib>
						</attribs>
						<list>
							<header>
								<elem>value</elem>
							</header>
						</list>
					</domain>
					<aliases>
						<alias>
							<attribs>
								<attrib name="name">Serial</attrib>
								<attrib name="ddEquiv"/>
								<attrib name="id">8</attrib>
							</attribs>
							<domain>
								<attribs>
									<attrib name="minValue"/>
									<attrib name="outRange">false</attrib>
									<attrib name="maxValue"/>
									<attrib name="type">none</attrib>
								</attribs>
								<list>
									<header>
										<elem>value</elem>
									</header>
								</list>
							</domain>
						</alias>
					</aliases>
				</type>
				<type>
					<attribs>
						<attrib name="name">smallint</attrib>
						<attrib name="ddEquiv"/>
						<attrib name="id">6</attrib>
					</attribs>
					<domain>
						<attribs>
							<attrib name="minValue"/>
							<attrib name="outRange">false</attrib>
							<attrib name="maxValue"/>
							<attrib name="type">none</attrib>
						</attribs>
						<list>
							<header>
								<elem>value</elem>
							</header>
						</list>
					</domain>
					<aliases/>
				</type>
				<type>
					<attribs>
						<attrib name="name">varchar(100)</attrib>
						<attrib name="ddEquiv">varchar(100)</attrib>
						<attrib name="id">7</attrib>
					</attribs>
					<domain>
						<attribs>
							<attrib name="minValue"/>
							<attrib name="outRange">false</attrib>
							<attrib name="maxValue"/>
							<attrib name="type">none</attrib>
						</attribs>
						<list>
							<header>
								<elem>value</elem>
							</header>
						</list>
					</domain>
					<aliases/>
				</type>
				<type>
					<attribs>
						<attrib name="name">varchar(1000)</attrib>
						<attrib name="ddEquiv">varchar(1000)</attrib>
						<attrib name="id">136868</attrib>
					</attribs>
					<domain>
						<attribs>
							<attrib name="minValue"/>
							<attrib name="outRange">false</attrib>
							<attrib name="maxValue"/>
							<attrib name="type">none</attrib>
						</attribs>
						<list>
							<header>
								<elem>value</elem>
							</header>
						</list>
					</domain>
					<aliases/>
				</type>
				<type>
					<attribs>
						<attrib name="name">int</attrib>
						<attrib name="ddEquiv"/>
						<attrib name="id">238995</attrib>
					</attribs>
					<domain>
						<attribs>
							<attrib name="minValue"/>
							<attrib name="outRange">false</attrib>
							<attrib name="maxValue"/>
							<attrib name="type">none</attrib>
						</attribs>
						<list>
							<header>
								<elem>value</elem>
							</header>
						</list>
					</domain>
					<aliases>
						<alias>
							<attribs>
								<attrib name="name">Serial</attrib>
								<attrib name="ddEquiv"/>
								<attrib name="id">238996</attrib>
							</attribs>
							<domain>
								<attribs>
									<attrib name="minValue"/>
									<attrib name="outRange">false</attrib>
									<attrib name="maxValue"/>
									<attrib name="type">none</attrib>
								</attribs>
								<list>
									<header>
										<elem>value</elem>
									</header>
								</list>
							</domain>
						</alias>
					</aliases>
				</type>
				<type>
					<attribs>
						<attrib name="name">TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP</attrib>
						<attrib name="ddEquiv"/>
						<attrib name="id">239351</attrib>
					</attribs>
					<domain>
						<attribs>
							<attrib name="minValue"/>
							<attrib name="outRange">false</attrib>
							<attrib name="maxValue"/>
							<attrib name="type">none</attrib>
						</attribs>
						<list>
							<header>
								<elem>value</elem>
							</header>
						</list>
					</domain>
					<aliases/>
				</type>
				<type>
					<attribs>
						<attrib name="name">TIMESTAMP DEFAULT 0</attrib>
						<attrib name="ddEquiv"/>
						<attrib name="id">240057</attrib>
					</attribs>
					<domain>
						<attribs>
							<attrib name="minValue"/>
							<attrib name="outRange">false</attrib>
							<attrib name="maxValue"/>
							<attrib name="type">none</attrib>
						</attribs>
						<list>
							<header>
								<elem>value</elem>
							</header>
						</list>
					</domain>
					<aliases/>
				</type>
				<type>
					<attribs>
						<attrib name="name">varchar(500)</attrib>
						<attrib name="ddEquiv">varchar(100)</attrib>
						<attrib name="id">281403</attrib>
					</attribs>
					<domain>
						<attribs>
							<attrib name="minValue"/>
							<attrib name="outRange">false</attrib>
							<attrib name="maxValue"/>
							<attrib name="type">none</attrib>
						</attribs>
						<list>
							<header>
								<elem>value</elem>
							</header>
						</list>
					</domain>
					<aliases/>
				</type>
				<type>
					<attribs>
						<attrib name="name">int not null auto_increment</attrib>
						<attrib name="ddEquiv"/>
						<attrib name="id">285090</attrib>
					</attribs>
					<domain>
						<attribs>
							<attrib name="minValue"/>
							<attrib name="outRange">false</attrib>
							<attrib name="maxValue"/>
							<attrib name="type">none</attrib>
						</attribs>
						<list>
							<header>
								<elem>value</elem>
							</header>
						</list>
					</domain>
					<aliases>
						<alias>
							<attribs>
								<attrib name="name">Serial</attrib>
								<attrib name="ddEquiv"/>
								<attrib name="id">285091</attrib>
							</attribs>
							<domain>
								<attribs>
									<attrib name="minValue"/>
									<attrib name="outRange">false</attrib>
									<attrib name="maxValue"/>
									<attrib name="type">none</attrib>
								</attribs>
								<list>
									<header>
										<elem>value</elem>
									</header>
								</list>
							</domain>
						</alias>
					</aliases>
				</type>
			</constFolder>
			<varFolder>
				<type>
					<attribs>
						<attrib name="name">char</attrib>
					</attribs>
					<aliases>
						<alias>
							<attribs>
								<attrib name="name">Bool</attrib>
								<attrib name="ddEquiv"/>
								<attrib name="id">12</attrib>
								<attrib name="size">1</attrib>
							</attribs>
							<domain>
								<attribs>
									<attrib name="minValue"/>
									<attrib name="outRange">false</attrib>
									<attrib name="maxValue"/>
									<attrib name="type">set</attrib>
								</attribs>
								<list>
									<header>
										<elem>value</elem>
									</header>
									<row>
										<elem>y</elem>
									</row>
									<row>
										<elem>n</elem>
									</row>
								</list>
							</domain>
						</alias>
					</aliases>
				</type>
				<type>
					<attribs>
						<attrib name="name">numeric</attrib>
					</attribs>
					<aliases>
						<alias>
							<attribs>
								<attrib name="name">Quantity</attrib>
								<attrib name="ddEquiv"/>
								<attrib name="id">10</attrib>
								<attrib name="size">10,4</attrib>
							</attribs>
							<domain>
								<attribs>
									<attrib name="minValue"/>
									<attrib name="outRange">false</attrib>
									<attrib name="maxValue"/>
									<attrib name="type">none</attrib>
								</attribs>
								<list>
									<header>
										<elem>value</elem>
									</header>
								</list>
							</domain>
						</alias>
					</aliases>
				</type>
			</varFolder>
		</dataTypes>
		<queries/>
		<erViews>
			<erView>
				<attribs>
					<attrib name="fieldsStyle">b</attrib>
					<attrib name="nameSize">15</attrib>
					<attrib name="nameStyle">b</attrib>
					<attrib name="name">whole_schema</attrib>
					<attrib name="details">af</attrib>
					<attrib name="fieldsSize">12</attrib>
					<attrib name="fieldsFamily">Arial</attrib>
					<attrib name="nameFamily">Arial</attrib>
				</attribs>
				<modsConfig/>
				<docs>
					<p>
						<t>Shows whole schema view. Its hard to read and print everything from this view. So better to look at other simpler views.</t>
					</p>
				</docs>
				<legend>
					<attribs>
						<attrib name="name">-UnNamed-</attrib>
						<attrib name="fontStyle">p</attrib>
						<attrib name="fontFamily">#GungSeo</attrib>
						<attrib name="fontSize">15</attrib>
						<attrib name="location">-</attrib>
					</attribs>
					<color>
						<attribs>
							<attrib name="name">07282006</attrib>
							<attrib name="id">175671</attrib>
						</attribs>
						<values>
							<name red="0" green="0" blue="0"/>
							<nameBg red="180" green="230" blue="255"/>
							<text red="0" green="0" blue="0"/>
							<textBg red="230" green="230" blue="180"/>
							<bg red="255" green="255" blue="204"/>
							<border red="0" green="0" blue="0"/>
						</values>
					</color>
				</legend>
				<erEntity>
					<attribs>
						<attrib name="name">AnalysisDataset</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">928</attrib>
						<attrib name="details">pf</attrib>
						<attrib name="locX">692</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="134946"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">File</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">571</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">1799</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="134964"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">ProcessedDataset</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">616</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">672</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="134984"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">PrimaryDataset</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">913</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">323</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135014"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">DataTier</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">773</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">1147</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135049"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">Block</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">435</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">1147</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135503"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">FileTier</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">754</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">1577</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="143251"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">FileParentage</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">252</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">1799</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135482"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">ApplicationFamily</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">572</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">42</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135381"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">QueryableParameterSet</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">11</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">287</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135439"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">ParameterBinding</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">45</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">49</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135449"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">PrimaryDatasetDescription</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">1231</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">311</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135533"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">TriggerPathDescription</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">1512</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">329</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135555"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">MCDescription</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">1237</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">34</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135562"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">AppExecutable</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">532</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">301</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="188022"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">AppVersion</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">375</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">46</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="188008"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">ProcDSTier</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">765</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">923</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="189889"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">DatasetParentage</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">712</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">309</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="190550"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">OtherDescription</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">1510</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">37</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="194368"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">LumiSection</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">1362</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">1142</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135310"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">SchemaVersion</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">1487</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">1583</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="199109"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">AlgoritmConfig</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">25</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">670</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135418"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">AnalysisDatasetLumi</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">1223</attrib>
						<attrib name="details">af</attrib>
						<attrib name="locX">681</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="233534"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">FileLumi</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">1215</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">1579</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="272990"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">Runs</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">1011</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">1134</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135029"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">ProcDSRuns</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">1054</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">924</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="275516"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">ProcAlgoMap</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">318</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">672</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="280269"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">FileAlgoMap</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">39</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">921</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="279868"/>
					</tables>
				</erEntity>
			</erView>
			<erView>
				<attribs>
					<attrib name="fieldsStyle">p</attrib>
					<attrib name="nameSize">15</attrib>
					<attrib name="nameStyle">bi</attrib>
					<attrib name="name">admin_schema</attrib>
					<attrib name="details">af</attrib>
					<attrib name="fieldsSize">12</attrib>
					<attrib name="fieldsFamily">#GungSeo</attrib>
					<attrib name="nameFamily">#GungSeo</attrib>
				</attribs>
				<modsConfig/>
				<legend>
					<attribs>
						<attrib name="name">-UnNamed-</attrib>
						<attrib name="fontStyle">p</attrib>
						<attrib name="fontFamily">#GungSeo</attrib>
						<attrib name="fontSize">10</attrib>
						<attrib name="location">-</attrib>
					</attribs>
					<color>
						<attribs>
							<attrib name="name">-UnNamed-</attrib>
							<attrib name="id">202963</attrib>
						</attribs>
						<values>
							<name red="0" green="0" blue="0"/>
							<nameBg red="180" green="230" blue="255"/>
							<text red="0" green="0" blue="0"/>
							<textBg red="230" green="230" blue="180"/>
							<bg red="255" green="255" blue="200"/>
							<border red="0" green="0" blue="0"/>
						</values>
					</color>
				</legend>
				<erEntity>
					<attribs>
						<attrib name="name">Person</attrib>
						<attrib name="colorId">202963</attrib>
						<attrib name="locY">191</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">289</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="134523"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">Role</attrib>
						<attrib name="colorId">202963</attrib>
						<attrib name="locY">197</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">622</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="134532"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">AssignedRole</attrib>
						<attrib name="colorId">202963</attrib>
						<attrib name="locY">511</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">519</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="134540"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">PhysicsGroup</attrib>
						<attrib name="colorId">202963</attrib>
						<attrib name="locY">517</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">131</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="134548"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">SchemaVersion</attrib>
						<attrib name="colorId">202963</attrib>
						<attrib name="locY">208</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">5</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="199109"/>
					</tables>
				</erEntity>
			</erView>
			<erView>
				<attribs>
					<attrib name="fieldsStyle">b</attrib>
					<attrib name="nameSize">15</attrib>
					<attrib name="nameStyle">bi</attrib>
					<attrib name="name">algorithmconfig_schema</attrib>
					<attrib name="details">af</attrib>
					<attrib name="fieldsSize">12</attrib>
					<attrib name="fieldsFamily">#GungSeo</attrib>
					<attrib name="nameFamily">#GungSeo</attrib>
				</attribs>
				<modsConfig/>
				<legend>
					<attribs>
						<attrib name="name">-UnNamed-</attrib>
						<attrib name="fontStyle">p</attrib>
						<attrib name="fontFamily">#GungSeo</attrib>
						<attrib name="fontSize">10</attrib>
						<attrib name="location">-</attrib>
					</attribs>
					<color>
						<attribs>
							<attrib name="name">-UnNamed-</attrib>
							<attrib name="id">206826</attrib>
						</attribs>
						<values>
							<name red="0" green="0" blue="0"/>
							<nameBg red="180" green="230" blue="255"/>
							<text red="0" green="0" blue="0"/>
							<textBg red="230" green="230" blue="180"/>
							<bg red="255" green="255" blue="200"/>
							<border red="0" green="0" blue="0"/>
						</values>
					</color>
				</legend>
				<erEntity>
					<attribs>
						<attrib name="name">AlgoritmConfig</attrib>
						<attrib name="colorId">206826</attrib>
						<attrib name="locY">238</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">397</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135418"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">ApplicationFamily</attrib>
						<attrib name="colorId">206826</attrib>
						<attrib name="locY">620</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">401</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135381"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">AppVersion</attrib>
						<attrib name="colorId">206826</attrib>
						<attrib name="locY">480</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">130</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="188008"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">AppExecutable</attrib>
						<attrib name="colorId">206826</attrib>
						<attrib name="locY">698</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">172</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="188022"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">ParameterBinding</attrib>
						<attrib name="colorId">206826</attrib>
						<attrib name="locY">5</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">39</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135449"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">QueryableParameterSet</attrib>
						<attrib name="colorId">206826</attrib>
						<attrib name="locY">227</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">39</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135439"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">ProcessedDataset</attrib>
						<attrib name="colorId">206826</attrib>
						<attrib name="locY">740</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">616</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="134984"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">ProcAlgoMap</attrib>
						<attrib name="colorId">206826</attrib>
						<attrib name="locY">502</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">610</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="280269"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">FileAlgoMap</attrib>
						<attrib name="colorId">206826</attrib>
						<attrib name="locY">252</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">637</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="279868"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">File</attrib>
						<attrib name="colorId">206826</attrib>
						<attrib name="locY">448</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">848</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="134964"/>
					</tables>
				</erEntity>
			</erView>
			<erView>
				<attribs>
					<attrib name="fieldsStyle">b</attrib>
					<attrib name="nameSize">15</attrib>
					<attrib name="nameStyle">bi</attrib>
					<attrib name="name">analysisDS_schema</attrib>
					<attrib name="details">af</attrib>
					<attrib name="fieldsSize">12</attrib>
					<attrib name="fieldsFamily">#GungSeo</attrib>
					<attrib name="nameFamily">#GungSeo</attrib>
				</attribs>
				<modsConfig/>
				<legend>
					<attribs>
						<attrib name="name">-UnNamed-</attrib>
						<attrib name="fontStyle">p</attrib>
						<attrib name="fontFamily">#GungSeo</attrib>
						<attrib name="fontSize">15</attrib>
						<attrib name="location">-</attrib>
					</attribs>
					<color>
						<attribs>
							<attrib name="name">-UnNamed-</attrib>
							<attrib name="id">219752</attrib>
						</attribs>
						<values>
							<name red="0" green="0" blue="0"/>
							<nameBg red="180" green="230" blue="255"/>
							<text red="0" green="0" blue="0"/>
							<textBg red="230" green="230" blue="180"/>
							<bg red="255" green="255" blue="200"/>
							<border red="0" green="0" blue="0"/>
						</values>
					</color>
					<color>
						<attribs>
							<attrib name="name">07282006</attrib>
							<attrib name="id">175671</attrib>
						</attribs>
						<values>
							<name red="0" green="0" blue="0"/>
							<nameBg red="180" green="230" blue="255"/>
							<text red="0" green="0" blue="0"/>
							<textBg red="230" green="230" blue="180"/>
							<bg red="255" green="255" blue="204"/>
							<border red="0" green="0" blue="0"/>
						</values>
					</color>
				</legend>
				<erEntity>
					<attribs>
						<attrib name="name">AnalysisDataset</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">827</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">12</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="134946"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">ProcessedDataset</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">462</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">460</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="134984"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">Block</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">131</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">454</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135503"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">DatasetParentage</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">501</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">31</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="190550"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">LumiSection</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">743</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">478</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135310"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">ProcDSTier</attrib>
						<attrib name="colorId">219752</attrib>
						<attrib name="locY">255</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">44</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="189889"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">DataTier</attrib>
						<attrib name="colorId">219752</attrib>
						<attrib name="locY">16</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">67</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135049"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">AnalysisDatasetLumi</attrib>
						<attrib name="colorId">219752</attrib>
						<attrib name="locY">1068</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">487</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="233534"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">Status</attrib>
						<attrib name="colorId">219752</attrib>
						<attrib name="locY">1161</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">29</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135353"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">Type</attrib>
						<attrib name="colorId">219752</attrib>
						<attrib name="locY">1279</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">348</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="145372"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">ProcDSRuns</attrib>
						<attrib name="colorId">219752</attrib>
						<attrib name="locY">477</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">782</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="275516"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">Runs</attrib>
						<attrib name="colorId">219752</attrib>
						<attrib name="locY">732</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">782</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135029"/>
					</tables>
				</erEntity>
			</erView>
			<erView>
				<attribs>
					<attrib name="fieldsStyle">b</attrib>
					<attrib name="nameSize">15</attrib>
					<attrib name="nameStyle">bi</attrib>
					<attrib name="name">core_schema</attrib>
					<attrib name="details">af</attrib>
					<attrib name="fieldsSize">12</attrib>
					<attrib name="fieldsFamily">#GungSeo</attrib>
					<attrib name="nameFamily">#GungSeo</attrib>
				</attribs>
				<modsConfig/>
				<legend>
					<attribs>
						<attrib name="name">-UnNamed-</attrib>
						<attrib name="fontStyle">p</attrib>
						<attrib name="fontFamily">#GungSeo</attrib>
						<attrib name="fontSize">15</attrib>
						<attrib name="location">-</attrib>
					</attribs>
					<color>
						<attribs>
							<attrib name="name">-UnNamed-</attrib>
							<attrib name="id">220779</attrib>
						</attribs>
						<values>
							<name red="0" green="0" blue="0"/>
							<nameBg red="180" green="230" blue="255"/>
							<text red="0" green="0" blue="0"/>
							<textBg red="230" green="230" blue="180"/>
							<bg red="255" green="255" blue="200"/>
							<border red="0" green="0" blue="0"/>
						</values>
					</color>
					<color>
						<attribs>
							<attrib name="name">-UnNamed-</attrib>
							<attrib name="id">219752</attrib>
						</attribs>
						<values>
							<name red="0" green="0" blue="0"/>
							<nameBg red="180" green="230" blue="255"/>
							<text red="0" green="0" blue="0"/>
							<textBg red="230" green="230" blue="180"/>
							<bg red="255" green="255" blue="200"/>
							<border red="0" green="0" blue="0"/>
						</values>
					</color>
					<color>
						<attribs>
							<attrib name="name">07282006</attrib>
							<attrib name="id">175671</attrib>
						</attribs>
						<values>
							<name red="0" green="0" blue="0"/>
							<nameBg red="180" green="230" blue="255"/>
							<text red="0" green="0" blue="0"/>
							<textBg red="230" green="230" blue="180"/>
							<bg red="255" green="255" blue="204"/>
							<border red="0" green="0" blue="0"/>
						</values>
					</color>
				</legend>
				<erEntity>
					<attribs>
						<attrib name="name">File</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">426</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">962</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="134964"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">ProcessedDataset</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">471</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">498</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="134984"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">Block</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">263</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">716</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135503"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">DatasetParentage</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">501</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">31</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="190550"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">LumiSection</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">947</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">888</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135310"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">ProcDSTier</attrib>
						<attrib name="colorId">219752</attrib>
						<attrib name="locY">255</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">44</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="189889"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">DataTier</attrib>
						<attrib name="colorId">219752</attrib>
						<attrib name="locY">17</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">70</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135049"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">PrimaryDataset</attrib>
						<attrib name="colorId">220779</attrib>
						<attrib name="locY">778</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">15</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135014"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">AlgoritmConfig</attrib>
						<attrib name="colorId">220779</attrib>
						<attrib name="locY">43</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">475</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135418"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">ProcAlgoMap</attrib>
						<attrib name="colorId">220779</attrib>
						<attrib name="locY">214</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">274</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="280269"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">FileAlgoMap</attrib>
						<attrib name="colorId">220779</attrib>
						<attrib name="locY">61</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">881</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="279868"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">Runs</attrib>
						<attrib name="colorId">220779</attrib>
						<attrib name="locY">938</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">609</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135029"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">FileLumi</attrib>
						<attrib name="colorId">220779</attrib>
						<attrib name="locY">685</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">723</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="272990"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">ProcDSRuns</attrib>
						<attrib name="colorId">220779</attrib>
						<attrib name="locY">827</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">308</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="275516"/>
					</tables>
				</erEntity>
			</erView>
			<erView>
				<attribs>
					<attrib name="fieldsStyle">b</attrib>
					<attrib name="nameSize">15</attrib>
					<attrib name="nameStyle">bi</attrib>
					<attrib name="name">datatier_schema</attrib>
					<attrib name="details">af</attrib>
					<attrib name="fieldsSize">12</attrib>
					<attrib name="fieldsFamily">#GungSeo</attrib>
					<attrib name="nameFamily">#GungSeo</attrib>
				</attribs>
				<modsConfig/>
				<legend>
					<attribs>
						<attrib name="name">-UnNamed-</attrib>
						<attrib name="fontStyle">p</attrib>
						<attrib name="fontFamily">#GungSeo</attrib>
						<attrib name="fontSize">15</attrib>
						<attrib name="location">-</attrib>
					</attribs>
					<color>
						<attribs>
							<attrib name="name">-UnNamed-</attrib>
							<attrib name="id">222170</attrib>
						</attribs>
						<values>
							<name red="0" green="0" blue="0"/>
							<nameBg red="180" green="230" blue="255"/>
							<text red="0" green="0" blue="0"/>
							<textBg red="230" green="230" blue="180"/>
							<bg red="255" green="255" blue="200"/>
							<border red="0" green="0" blue="0"/>
						</values>
					</color>
					<color>
						<attribs>
							<attrib name="name">-UnNamed-</attrib>
							<attrib name="id">219752</attrib>
						</attribs>
						<values>
							<name red="0" green="0" blue="0"/>
							<nameBg red="180" green="230" blue="255"/>
							<text red="0" green="0" blue="0"/>
							<textBg red="230" green="230" blue="180"/>
							<bg red="255" green="255" blue="200"/>
							<border red="0" green="0" blue="0"/>
						</values>
					</color>
					<color>
						<attribs>
							<attrib name="name">07282006</attrib>
							<attrib name="id">175671</attrib>
						</attribs>
						<values>
							<name red="0" green="0" blue="0"/>
							<nameBg red="180" green="230" blue="255"/>
							<text red="0" green="0" blue="0"/>
							<textBg red="230" green="230" blue="180"/>
							<bg red="255" green="255" blue="204"/>
							<border red="0" green="0" blue="0"/>
						</values>
					</color>
				</legend>
				<erEntity>
					<attribs>
						<attrib name="name">File</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">521</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">777</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="134964"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">ProcessedDataset</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">566</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">397</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="134984"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">DatasetParentage</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">875</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">398</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="190550"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">ProcDSTier</attrib>
						<attrib name="colorId">219752</attrib>
						<attrib name="locY">581</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">52</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="189889"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">DataTier</attrib>
						<attrib name="colorId">219752</attrib>
						<attrib name="locY">285</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">52</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135049"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">FileTier</attrib>
						<attrib name="colorId">222170</attrib>
						<attrib name="locY">275</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">777</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="143251"/>
					</tables>
				</erEntity>
			</erView>
			<erView>
				<attribs>
					<attrib name="fieldsStyle">b</attrib>
					<attrib name="nameSize">15</attrib>
					<attrib name="nameStyle">bi</attrib>
					<attrib name="name">file_schema</attrib>
					<attrib name="details">af</attrib>
					<attrib name="fieldsSize">12</attrib>
					<attrib name="fieldsFamily">#GungSeo</attrib>
					<attrib name="nameFamily">#GungSeo</attrib>
				</attribs>
				<modsConfig/>
				<legend>
					<attribs>
						<attrib name="name">-UnNamed-</attrib>
						<attrib name="fontStyle">p</attrib>
						<attrib name="fontFamily">#GungSeo</attrib>
						<attrib name="fontSize">15</attrib>
						<attrib name="location">-</attrib>
					</attribs>
					<color>
						<attribs>
							<attrib name="name">-UnNamed-</attrib>
							<attrib name="id">222529</attrib>
						</attribs>
						<values>
							<name red="0" green="0" blue="0"/>
							<nameBg red="180" green="230" blue="255"/>
							<text red="0" green="0" blue="0"/>
							<textBg red="230" green="230" blue="180"/>
							<bg red="255" green="255" blue="200"/>
							<border red="0" green="0" blue="0"/>
						</values>
					</color>
					<color>
						<attribs>
							<attrib name="name">-UnNamed-</attrib>
							<attrib name="id">222170</attrib>
						</attribs>
						<values>
							<name red="0" green="0" blue="0"/>
							<nameBg red="180" green="230" blue="255"/>
							<text red="0" green="0" blue="0"/>
							<textBg red="230" green="230" blue="180"/>
							<bg red="255" green="255" blue="200"/>
							<border red="0" green="0" blue="0"/>
						</values>
					</color>
					<color>
						<attribs>
							<attrib name="name">-UnNamed-</attrib>
							<attrib name="id">219752</attrib>
						</attribs>
						<values>
							<name red="0" green="0" blue="0"/>
							<nameBg red="180" green="230" blue="255"/>
							<text red="0" green="0" blue="0"/>
							<textBg red="230" green="230" blue="180"/>
							<bg red="255" green="255" blue="200"/>
							<border red="0" green="0" blue="0"/>
						</values>
					</color>
					<color>
						<attribs>
							<attrib name="name">07282006</attrib>
							<attrib name="id">175671</attrib>
						</attribs>
						<values>
							<name red="0" green="0" blue="0"/>
							<nameBg red="180" green="230" blue="255"/>
							<text red="0" green="0" blue="0"/>
							<textBg red="230" green="230" blue="180"/>
							<bg red="255" green="255" blue="204"/>
							<border red="0" green="0" blue="0"/>
						</values>
					</color>
				</legend>
				<erEntity>
					<attribs>
						<attrib name="name">File</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">545</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">647</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="134964"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">ProcessedDataset</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">582</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">30</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="134984"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">ProcDSTier</attrib>
						<attrib name="colorId">219752</attrib>
						<attrib name="locY">298</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">25</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="189889"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">DataTier</attrib>
						<attrib name="colorId">219752</attrib>
						<attrib name="locY">175</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">336</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135049"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">FileTier</attrib>
						<attrib name="colorId">222170</attrib>
						<attrib name="locY">299</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">639</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="143251"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">Block</attrib>
						<attrib name="colorId">222529</attrib>
						<attrib name="locY">383</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">341</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135503"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">FileParentage</attrib>
						<attrib name="colorId">222529</attrib>
						<attrib name="locY">908</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">937</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135482"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">LumiSection</attrib>
						<attrib name="colorId">222529</attrib>
						<attrib name="locY">853</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">450</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135310"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">Status</attrib>
						<attrib name="colorId">222529</attrib>
						<attrib name="locY">365</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">947</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135353"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">Type</attrib>
						<attrib name="colorId">222529</attrib>
						<attrib name="locY">610</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">979</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="145372"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">FileLumi</attrib>
						<attrib name="colorId">222529</attrib>
						<attrib name="locY">880</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">667</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="272990"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">Runs</attrib>
						<attrib name="colorId">222529</attrib>
						<attrib name="locY">788</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">231</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135029"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">ProcDSRuns</attrib>
						<attrib name="colorId">222529</attrib>
						<attrib name="locY">827</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">0</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="275516"/>
					</tables>
				</erEntity>
			</erView>
			<erView>
				<attribs>
					<attrib name="fieldsStyle">p</attrib>
					<attrib name="nameSize">15</attrib>
					<attrib name="nameStyle">bi</attrib>
					<attrib name="name">primaryDS_schema</attrib>
					<attrib name="details">af</attrib>
					<attrib name="fieldsSize">10</attrib>
					<attrib name="fieldsFamily">#GungSeo</attrib>
					<attrib name="nameFamily">#GungSeo</attrib>
				</attribs>
				<modsConfig/>
				<legend>
					<attribs>
						<attrib name="name">-UnNamed-</attrib>
						<attrib name="fontStyle">p</attrib>
						<attrib name="fontFamily">#GungSeo</attrib>
						<attrib name="fontSize">10</attrib>
						<attrib name="location">-</attrib>
					</attribs>
					<color>
						<attribs>
							<attrib name="name">-UnNamed-</attrib>
							<attrib name="id">225031</attrib>
						</attribs>
						<values>
							<name red="0" green="0" blue="0"/>
							<nameBg red="180" green="230" blue="255"/>
							<text red="0" green="0" blue="0"/>
							<textBg red="230" green="230" blue="180"/>
							<bg red="255" green="255" blue="200"/>
							<border red="0" green="0" blue="0"/>
						</values>
					</color>
				</legend>
				<erEntity>
					<attribs>
						<attrib name="name">PrimaryDataset</attrib>
						<attrib name="colorId">225031</attrib>
						<attrib name="locY">247</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">90</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135014"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">ProcessedDataset</attrib>
						<attrib name="colorId">225031</attrib>
						<attrib name="locY">261</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">540</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="134984"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">PrimaryDatasetDescription</attrib>
						<attrib name="colorId">225031</attrib>
						<attrib name="locY">550</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">75</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135533"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">TriggerPathDescription</attrib>
						<attrib name="colorId">225031</attrib>
						<attrib name="locY">841</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">304</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135555"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">MCDescription</attrib>
						<attrib name="colorId">225031</attrib>
						<attrib name="locY">533</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">428</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135562"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">OtherDescription</attrib>
						<attrib name="colorId">225031</attrib>
						<attrib name="locY">855</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">30</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="194368"/>
					</tables>
				</erEntity>
			</erView>
			<erView>
				<attribs>
					<attrib name="fieldsStyle">b</attrib>
					<attrib name="nameSize">15</attrib>
					<attrib name="nameStyle">bi</attrib>
					<attrib name="name">processedDS_schema</attrib>
					<attrib name="details">af</attrib>
					<attrib name="fieldsSize">12</attrib>
					<attrib name="fieldsFamily">#GungSeo</attrib>
					<attrib name="nameFamily">#GungSeo</attrib>
				</attribs>
				<modsConfig/>
				<legend>
					<attribs>
						<attrib name="name">-UnNamed-</attrib>
						<attrib name="fontStyle">p</attrib>
						<attrib name="fontFamily">#GungSeo</attrib>
						<attrib name="fontSize">15</attrib>
						<attrib name="location">-</attrib>
					</attribs>
					<color>
						<attribs>
							<attrib name="name">-UnNamed-</attrib>
							<attrib name="id">227546</attrib>
						</attribs>
						<values>
							<name red="0" green="0" blue="0"/>
							<nameBg red="180" green="230" blue="255"/>
							<text red="0" green="0" blue="0"/>
							<textBg red="230" green="230" blue="180"/>
							<bg red="255" green="255" blue="200"/>
							<border red="0" green="0" blue="0"/>
						</values>
					</color>
					<color>
						<attribs>
							<attrib name="name">-UnNamed-</attrib>
							<attrib name="id">220779</attrib>
						</attribs>
						<values>
							<name red="0" green="0" blue="0"/>
							<nameBg red="180" green="230" blue="255"/>
							<text red="0" green="0" blue="0"/>
							<textBg red="230" green="230" blue="180"/>
							<bg red="255" green="255" blue="200"/>
							<border red="0" green="0" blue="0"/>
						</values>
					</color>
					<color>
						<attribs>
							<attrib name="name">-UnNamed-</attrib>
							<attrib name="id">219752</attrib>
						</attribs>
						<values>
							<name red="0" green="0" blue="0"/>
							<nameBg red="180" green="230" blue="255"/>
							<text red="0" green="0" blue="0"/>
							<textBg red="230" green="230" blue="180"/>
							<bg red="255" green="255" blue="200"/>
							<border red="0" green="0" blue="0"/>
						</values>
					</color>
					<color>
						<attribs>
							<attrib name="name">07282006</attrib>
							<attrib name="id">175671</attrib>
						</attribs>
						<values>
							<name red="0" green="0" blue="0"/>
							<nameBg red="180" green="230" blue="255"/>
							<text red="0" green="0" blue="0"/>
							<textBg red="230" green="230" blue="180"/>
							<bg red="255" green="255" blue="204"/>
							<border red="0" green="0" blue="0"/>
						</values>
					</color>
				</legend>
				<erEntity>
					<attribs>
						<attrib name="name">File</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">0</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">1226</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="134964"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">ProcessedDataset</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">420</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">805</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="134984"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">Block</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">31</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">803</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135503"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">DatasetParentage</attrib>
						<attrib name="colorId">175671</attrib>
						<attrib name="locY">517</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">324</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="190550"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">ProcDSTier</attrib>
						<attrib name="colorId">219752</attrib>
						<attrib name="locY">314</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">135</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="189889"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">DataTier</attrib>
						<attrib name="colorId">219752</attrib>
						<attrib name="locY">38</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">135</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135049"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">PrimaryDataset</attrib>
						<attrib name="colorId">220779</attrib>
						<attrib name="locY">696</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">490</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135014"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">AlgoritmConfig</attrib>
						<attrib name="colorId">220779</attrib>
						<attrib name="locY">24</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">378</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135418"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">Runs</attrib>
						<attrib name="colorId">227546</attrib>
						<attrib name="locY">663</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">1203</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135029"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">ProcDSRuns</attrib>
						<attrib name="colorId">227546</attrib>
						<attrib name="locY">715</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">819</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="275516"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">ProcAlgoMap</attrib>
						<attrib name="colorId">227546</attrib>
						<attrib name="locY">280</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">591</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="280269"/>
					</tables>
				</erEntity>
			</erView>
			<erView>
				<attribs>
					<attrib name="fieldsStyle">p</attrib>
					<attrib name="nameSize">15</attrib>
					<attrib name="nameStyle">bi</attrib>
					<attrib name="name">pset_schema</attrib>
					<attrib name="details">af</attrib>
					<attrib name="fieldsSize">10</attrib>
					<attrib name="fieldsFamily">#GungSeo</attrib>
					<attrib name="nameFamily">#GungSeo</attrib>
				</attribs>
				<modsConfig/>
				<legend>
					<attribs>
						<attrib name="name">-UnNamed-</attrib>
						<attrib name="fontStyle">p</attrib>
						<attrib name="fontFamily">#GungSeo</attrib>
						<attrib name="fontSize">10</attrib>
						<attrib name="location">-</attrib>
					</attribs>
					<color>
						<attribs>
							<attrib name="name">-UnNamed-</attrib>
							<attrib name="id">182466</attrib>
						</attribs>
						<values>
							<name red="0" green="0" blue="0"/>
							<nameBg red="180" green="230" blue="255"/>
							<text red="0" green="0" blue="0"/>
							<textBg red="230" green="230" blue="180"/>
							<bg red="255" green="255" blue="200"/>
							<border red="0" green="0" blue="0"/>
						</values>
					</color>
				</legend>
				<erEntity>
					<attribs>
						<attrib name="name">QueryableParameterSet</attrib>
						<attrib name="colorId">182466</attrib>
						<attrib name="locY">162</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">85</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135439"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">ParameterBinding</attrib>
						<attrib name="colorId">182466</attrib>
						<attrib name="locY">191</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">359</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135449"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">AlgoritmConfig</attrib>
						<attrib name="colorId">182466</attrib>
						<attrib name="locY">439</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">92</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135418"/>
					</tables>
				</erEntity>
			</erView>
			<erView>
				<attribs>
					<attrib name="fieldsStyle">p</attrib>
					<attrib name="nameSize">15</attrib>
					<attrib name="nameStyle">bi</attrib>
					<attrib name="name">runlumifile_schema</attrib>
					<attrib name="details">af</attrib>
					<attrib name="fieldsSize">10</attrib>
					<attrib name="fieldsFamily">#GungSeo</attrib>
					<attrib name="nameFamily">#GungSeo</attrib>
				</attribs>
				<modsConfig/>
				<legend>
					<attribs>
						<attrib name="name">-UnNamed-</attrib>
						<attrib name="fontStyle">p</attrib>
						<attrib name="fontFamily">#GungSeo</attrib>
						<attrib name="fontSize">10</attrib>
						<attrib name="location">-</attrib>
					</attribs>
					<color>
						<attribs>
							<attrib name="name">-UnNamed-</attrib>
							<attrib name="id">213282</attrib>
						</attribs>
						<values>
							<name red="0" green="0" blue="0"/>
							<nameBg red="180" green="230" blue="255"/>
							<text red="0" green="0" blue="0"/>
							<textBg red="230" green="230" blue="180"/>
							<bg red="255" green="255" blue="200"/>
							<border red="0" green="0" blue="0"/>
						</values>
					</color>
				</legend>
				<erEntity>
					<attribs>
						<attrib name="name">LumiSection</attrib>
						<attrib name="colorId">213282</attrib>
						<attrib name="locY">531</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">555</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135310"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">ProcessedDataset</attrib>
						<attrib name="colorId">213282</attrib>
						<attrib name="locY">65</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">298</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="134984"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">AnalysisDataset</attrib>
						<attrib name="colorId">213282</attrib>
						<attrib name="locY">58</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">42</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="134946"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">File</attrib>
						<attrib name="colorId">213282</attrib>
						<attrib name="locY">30</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">541</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="134964"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">FileLumi</attrib>
						<attrib name="colorId">213282</attrib>
						<attrib name="locY">328</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">548</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="272990"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">ProcDSRuns</attrib>
						<attrib name="colorId">213282</attrib>
						<attrib name="locY">298</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">298</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="275516"/>
					</tables>
				</erEntity>
				<erEntity>
					<attribs>
						<attrib name="name">Runs</attrib>
						<attrib name="colorId">213282</attrib>
						<attrib name="locY">528</attrib>
						<attrib name="details">-</attrib>
						<attrib name="locX">298</attrib>
					</attribs>
					<modsConfig/>
					<tables>
						<table id="135029"/>
					</tables>
				</erEntity>
			</erView>
		</erViews>
		<objects>
			<folder>
				<attribs>
					<attrib name="name">Administrative</attrib>
				</attribs>
				<modsConfig/>
				<docs>
					<p>
						<t>These tables contain information about people and physics groups who are allowed access to the database. The person has a DN, and it is assumed that this table will be filled automatically from the appropriate VO authentication databases with appropriate filtering. The person has roles which define access to groups of tables and/or database operations, and automatically generated persons will get some default &quot;readonly&quot; role. The other main purpose of the administrative tables are to provide an audit trail into the database based on the user's DN. The definitions are implemented in the application database server layer.</t>
					</p>
					<p>
						<t>There is also a table containing physics groups. The database does not track relationships between persons and physics groups. Rather, physics group is provided as a convenience category for organizing the data. The only person in the physics group is the convener or other designated contact person.</t>
					</p>
				</docs>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">Person</attrib>
						<attrib name="id">134523</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
							<pgSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="s">access</elem>
											<elem type="s">where</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>default</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</pgSql>
						</SqlGenModule>
					</modsConfig>
					<docs>
						<p>
							<t>This table contains information about the people who are allowed to access the database. This could be filled in automatically from the VO databases when a person is loaded.</t>
						</p>
					</docs>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">134524</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Primary key row id.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">true</attrib>
								<attrib name="340">true</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">Name</attrib>
							<attrib name="id">134525</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Database name or Unix nickname.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">true</attrib>
								<attrib name="340">true</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">DistinguishedName</attrib>
							<attrib name="id">134526</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Certified Distinguished Name as found in Grid credentials.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ContactInfo</attrib>
							<attrib name="id">134527</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Text field containing the name of the person's institute or affiliation, address, and office number. (TBD)</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">134528</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">134529</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">134530</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">134531</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">Role</attrib>
						<attrib name="id">134532</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
							<pgSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="s">access</elem>
											<elem type="s">where</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>default</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</pgSql>
						</SqlGenModule>
					</modsConfig>
					<docs>
						<p>
							<t>This table lists the roles defined on the database.</t>
						</p>
					</docs>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">134533</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Primary key row id.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">true</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">true</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">RoleName</attrib>
							<attrib name="id">134534</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>The name of a role pertaining to access on this database. The definition will be implemented in the application database server layer.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">RoleDescription</attrib>
							<attrib name="id">134535</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>A brief description of the role to aid debugging/designing.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">134536</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">134537</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">134538</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">134539</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">AssignedRole</attrib>
						<attrib name="id">134540</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
						</SqlGenModule>
					</modsConfig>
					<docs>
						<p>
							<t>A N-N relation between roles and people denoting when persons have been assigned a role. Each relation PersonID,RoleID is constrained to be unique so that while a person may hold several roles, (s)he may hold each distinct role only once.</t>
						</p>
					</docs>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">134541</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Primary key role id.
</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">true</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">PersonID</attrib>
							<attrib name="id">134542</attrib>
							<attrib name="onDelete">c</attrib>
							<attrib name="onUpdate">c</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>A valid person id number from the Person table.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">true</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134533</attrib>
							<attrib name="name">RoleID</attrib>
							<attrib name="id">134543</attrib>
							<attrib name="onDelete">c</attrib>
							<attrib name="onUpdate">c</attrib>
							<attrib name="refTable">134532</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>A valid role id number from the Role table.
</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">134544</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">134545</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">134546</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">134547</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">PhysicsGroup</attrib>
						<attrib name="id">134548</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
							<pgSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="s">access</elem>
											<elem type="s">where</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>default</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</pgSql>
						</SqlGenModule>
					</modsConfig>
					<docs>
						<p>
							<t>A table containing physics groups within the collaboration.</t>
						</p>
					</docs>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">134549</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Primary key row id.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">true</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">true</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">PhysicsGroupName</attrib>
							<attrib name="id">134550</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>The name of the physics group.  Should be something convenient to query upon.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">PhysicsGroupConvener</attrib>
							<attrib name="id">134551</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>A valid person id from the Person table who is the designated contact person for the physics group.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">134552</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">134553</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">134554</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">134555</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">SchemaVersion</attrib>
						<attrib name="id">199109</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
						</SqlGenModule>
					</modsConfig>
					<docs>
						<p>
							<t>This table lists the roles defined on the database.</t>
						</p>
					</docs>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">199110</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Primary key row id.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">true</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">SchemaVersion</attrib>
							<attrib name="id">199111</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>The name of a role pertaining to access on this database. The definition will be implemented in the application database server layer.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">199113</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">199114</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">199115</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">199116</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
			</folder>
			<folder>
				<attribs>
					<attrib name="name">CoreEntitiesAndRelations</attrib>
				</attribs>
				<modsConfig/>
				<docs>
					<p>
						<t>These are the core entities and relationships in the dataset bookkeeping service.</t>
					</p>
				</docs>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">AnalysisDataset</attrib>
						<attrib name="id">134946</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
							<pgSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="s">access</elem>
											<elem type="s">where</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>default</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</pgSql>
						</SqlGenModule>
					</modsConfig>
					<docs>
						<p>
							<t>AnalysisDataset: It is uniquely determined by Lumi Sections and ProcessedDataset. An AnalysisDataset is a subset of ProcessedDataset. From one ProcessedDataset many AnalysisDataset can be derived. Driving a new AnalysisDataset from an existing AnalysisDataset would mean to create a new ProcessedDataset. So essentially it will be a new AnalysisDataset from a ProcessedDataset again. Also AnalysisDataset can contain many Lumi Sections and same Lumi Sections can spawn multiple AnalysisDataset. Therefore we have AnalysisDSLumi table which represents many to many relationship among these entities.</t>
						</p>
					</docs>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">134947</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Primary key row id.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">true</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">Annotation</attrib>
							<attrib name="id">134948</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">136868</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Annotaion provided by the user describing the AnalysisDataset.
</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">true</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="name">Query</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="matchType">s</attrib>
							<attrib name="id">285485</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">136868</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Annotaion provided by the user describing the AnalysisDataset.
</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134985</attrib>
							<attrib name="name">ProcessedDS</attrib>
							<attrib name="id">187691</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134984</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">145373</attrib>
							<attrib name="name">Type</attrib>
							<attrib name="id">134949</attrib>
							<attrib name="onDelete">s</attrib>
							<attrib name="onUpdate">s</attrib>
							<attrib name="refTable">145372</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>A type selector for the subtype (if any) of AnalysisDataset.
</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">135354</attrib>
							<attrib name="name">Status</attrib>
							<attrib name="id">145964</attrib>
							<attrib name="onDelete">s</attrib>
							<attrib name="onUpdate">s</attrib>
							<attrib name="refTable">135353</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>A type selector for the subtype (if any) of AnalysisDataset.
</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134947</attrib>
							<attrib name="name">Parent</attrib>
							<attrib name="id">144528</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134946</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>The id of the parent processing path or null.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">134952</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">134953</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">134954</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">134955</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">Files</attrib>
						<attrib name="id">134964</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
							<pgSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="s">access</elem>
											<elem type="s">where</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>default</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</pgSql>
						</SqlGenModule>
					</modsConfig>
					<docs>
						<p>
							<t>File: A File can contain multiple runs. A run can contain multiple files. The event range in a file represents the lowest bound event number to the highest bound event number. For example in a run r with a File f has event range 19,130. This does not imply that all the events are contiguous . There might be some events that can be missing. For example in this file f , only events 19,20,25,130 are present and rest of then are bad or missing.</t>
						</p>
					</docs>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">134965</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Primary key row id.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">true</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">true</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LogicalFileName</attrib>
							<attrib name="id">134966</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">281403</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>The logical file name, asusmed unique, as it appears in the POOL catalog.
</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">true</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134985</attrib>
							<attrib name="name">Dataset</attrib>
							<attrib name="id">235108</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134984</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>User text desribing what is in the primary dataset.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">135504</attrib>
							<attrib name="name">Block</attrib>
							<attrib name="id">179299</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">135503</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">Checksum</attrib>
							<attrib name="id">134967</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>The MD5 checksum of the file.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="name">NumberOfEvents</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="matchType">s</attrib>
							<attrib name="id">285493</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">6</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">FileSize</attrib>
							<attrib name="id">134968</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">5</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>The filesize in bytes.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">135354</attrib>
							<attrib name="name">Status</attrib>
							<attrib name="id">134969</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">135353</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>A file status id.  Eg- OK, lost, corrupted, etc.
</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">145373</attrib>
							<attrib name="name">FileType</attrib>
							<attrib name="id">134970</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">145372</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>A legacy &quot;file type&quot; parameter to distinguish special files.  This is expected to become deprecated as the EDM gets rid of special files.  
</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">135354</attrib>
							<attrib name="name">ValidationStatus</attrib>
							<attrib name="id">135614</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">135353</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>The validation status of the EventCollection or Analysis Dataset.  This is the result of some physics validation run over the dataset.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">QueryableMetadata</attrib>
							<attrib name="id">135618</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">136868</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">134971</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">134972</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">134973</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">134974</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">ProcessedDataset</attrib>
						<attrib name="id">134984</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
							<pgSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="s">access</elem>
											<elem type="s">where</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>default</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</pgSql>
						</SqlGenModule>
					</modsConfig>
					<docs>
						<p>
							<t>ProcessedDataset: Is uniquely represented by the PrimaryDataset , AppConfig and the input ProcessedDataset if any. Also, a ProcessedDataset can contain various ppConfig (Different version of Application). This satusfy the use case of creating many files with same application but different version of the software used. It can contain multiple files and therefore has one to may relationship with file. Also it can contain multiple DataTier which itself can spawn multiple ProcessedDataset and therefore many to many relationship among them. The reason for this linkage is for optimization purposes only and is described in FileTier section</t>
						</p>
					</docs>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">134985</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Primary key row id.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">true</attrib>
								<attrib name="340">true</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">Name</attrib>
							<attrib name="id">149690</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">true</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">135015</attrib>
							<attrib name="name">PrimaryDataset</attrib>
							<attrib name="id">140694</attrib>
							<attrib name="onDelete">c</attrib>
							<attrib name="onUpdate">c</attrib>
							<attrib name="refTable">135014</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">OpenForWriting</attrib>
							<attrib name="id">134988</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">12</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Boolean if the processed dataset is open for writing.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134549</attrib>
							<attrib name="name">PhysicsGroup</attrib>
							<attrib name="id">135021</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134548</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>A valid Physics Group id from the PhysicsGroup table.  (Useful mainly for querying on datasets.) 
</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="name">Status</attrib>
							<attrib name="refField">135354</attrib>
							<attrib name="matchType">s</attrib>
							<attrib name="id">285481</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">135353</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>A file status id.  Eg- OK, lost, corrupted, etc.
</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">134990</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">134991</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">134992</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">134993</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">PrimaryDataset</attrib>
						<attrib name="id">135014</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
							<pgSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="s">access</elem>
											<elem type="s">where</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>default</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</pgSql>
						</SqlGenModule>
					</modsConfig>
					<docs>
						<p>
							<t>PrimaryDataset: is a placeholder for any type of data like raw or production. It is determined by the name and the description. Now this description can be of 3 different types: Monte Carlo type, Trigger Path type or some Generic Description. Therefore there are 3 tables representing each one. PrimaryDataset can contains raw data which means that it was not processed. To manage this within the schema, one can create a dummy ProcessedDataset with no link to any Application (AppConfig in this case). All the raw files can now be placed in this dummy ProcessedDataset</t>
						</p>
						<p>
							<t>This entity is defined as the unit of data of physics interest. It is defined by a trigger mask or by MC generation channel.</t>
						</p>
					</docs>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">135015</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Primary key row id.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">Annotation</attrib>
							<attrib name="id">135018</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">136868</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>User text desribing what is in the primary dataset.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">true</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">true</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">Name</attrib>
							<attrib name="id">185931</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>User text desribing what is in the primary dataset.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">135534</attrib>
							<attrib name="name">Description</attrib>
							<attrib name="id">186807</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">135533</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>User text desribing what is in the primary dataset.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">StartDate</attrib>
							<attrib name="id">135023</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Starting date for this primary dataset.
</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">EndDate</attrib>
							<attrib name="id">135024</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Ending date for this primary dataset.
</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">145373</attrib>
							<attrib name="name">Type</attrib>
							<attrib name="id">191891</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">145372</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>A legacy &quot;file type&quot; parameter to distinguish special files.  This is expected to become deprecated as the EDM gets rid of special files.  
</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">135025</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">135026</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">135027</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">135028</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">Runs</attrib>
						<attrib name="id">135029</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
							<pgSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="s">access</elem>
											<elem type="s">where</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>default</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</pgSql>
						</SqlGenModule>
					</modsConfig>
					<docs>
						<p>
							<t>FileRun</t>
						</p>
						<p>
							<t>This table will represent many to many relationship in file and run. Note that the event range is an attribute of the FileRun table and not just of the File Table. This is required when multiple files will be merged into one single file. Then a File can spawn multiple runs and can spawn multiple event ranges. To discover all the event in this file we need to make event range an attribute of FileRun table. For example say a file f1 in run r1 contains events 3,16 and file f2 in run r2 contains events 5,15 and file f3 in run r1 contains events 2,13 . After we can merge f1,f2 and f3 into a big file ff, now FileRun table would contain two entries for file ff and run r1 and run r2. First entry for ff,r1 would have event range 2,16 and another entry for ff,r2 would contain 5,15. Note that of the files to be merged are in the same run then the event range would be minimum of lower bounds to maximum of higher bounds min(3,2),max(16,1)</t>
						</p>
					</docs>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">135030</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">true</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">true</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">RunNumber</attrib>
							<attrib name="id">135031</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">6</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">NumberOfEvents</attrib>
							<attrib name="id">135033</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">6</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">NumberOfLumiSections</attrib>
							<attrib name="id">135034</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">6</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">TotalLuminosity</attrib>
							<attrib name="id">233965</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">6</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">true</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">StoreNumber</attrib>
							<attrib name="id">233967</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">6</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">StartOfRun</attrib>
							<attrib name="id">135035</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">EndOfRun</attrib>
							<attrib name="id">135036</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">135037</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">135038</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">135039</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">135040</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">DataTier</attrib>
						<attrib name="id">135049</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
							<pgSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="s">access</elem>
											<elem type="s">where</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>default</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</pgSql>
						</SqlGenModule>
					</modsConfig>
					<docs>
						<p>
							<t>DataTier: is infact related with the AppConfig via ParameterSet. But we need DataTier linked with Files also , since a physicists would require to know all the file in a dataset which has a certain DataTier. If DataTier would just be an ProcessedDataset Attribute, then the above query could not be executed. This is because a ProcessedDataset can spawn multiple DataTiers. If there is a restriction that a ProcessedDataset would only have a single DataTier then we would not required DataTier linkage with files at all. Also a File can have muliple DataTiers also, that iss why all the more reasons to link DataTier with File. Note that FileTier is enough for associating and determining the DataTier with in a file as well as within a ProcessedDataset. So why would we need linkage of DataTier with a ProcessedDataset. The reason is same as that of File Parentage and ProcessedDataset Parentage. This is just an optimization for determining the DataTier with in a DataSet. One has to traverse all the files otherwise to know the DataTiers.</t>
						</p>
					</docs>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">135054</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">true</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">true</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">Name</attrib>
							<attrib name="id">135055</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">135050</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">135051</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">135052</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">135053</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">LumiSection</attrib>
						<attrib name="id">135310</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
							<pgSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="s">access</elem>
											<elem type="s">where</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>default</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</pgSql>
						</SqlGenModule>
					</modsConfig>
					<docs>
						<p>
							<t>LumiSection: A file can contains many Lumi Sections and a Lumi Sections can be contained in many files. These Lumi Sections are used for describing an analysis dataset which is conceptually more or less not related with files.</t>
						</p>
					</docs>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">135311</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">true</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LumiSectionNumber</attrib>
							<attrib name="id">135312</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">6</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">true</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">135030</attrib>
							<attrib name="name">RunNumber</attrib>
							<attrib name="id">233979</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">135029</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">StartEventNumber</attrib>
							<attrib name="id">274777</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">6</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">EndEventNumber</attrib>
							<attrib name="id">274779</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">6</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LumiStartTime</attrib>
							<attrib name="id">233971</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LumiEndTime</attrib>
							<attrib name="id">233975</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">135318</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">135319</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">135320</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">135321</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">Block</attrib>
						<attrib name="id">135503</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
							<pgSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="s">access</elem>
											<elem type="s">where</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>default</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</pgSql>
						</SqlGenModule>
					</modsConfig>
					<docs>
						<p>
							<t>This is an analysis dataset subtype that aggregates Events based on the collective size of the created files.</t>
						</p>
						<p>
							<t>Block: This entity is entirely a concept of size and number of files. It can contain many files but if the collective size of those files reaches a certain limit (can be set by DBS admins), then it cannot accommodate any more files in it. In that case a block can be closed and a new block can be opened for adding new files into it. A block can be uniquely determined by its name that can also include dataset path along with its id.</t>
						</p>
					</docs>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">135504</attrib>
							<attrib name="onDelete">c</attrib>
							<attrib name="onUpdate">c</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Foreign key from Analysis Dataset is Primary key.  </t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">BlockSize</attrib>
							<attrib name="id">135505</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">5</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Collective size of the files in the block.  Guaranteed correct when closed.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">true</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">true</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">Name</attrib>
							<attrib name="id">185933</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>User text desribing what is in the primary dataset.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">true</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134985</attrib>
							<attrib name="name">Dataset</attrib>
							<attrib name="id">218374</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134984</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>User text desribing what is in the primary dataset.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">NumberOfFiles</attrib>
							<attrib name="id">197076</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">5</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Collective Checksum of the files in the block.  Guanteed correct when the block is closed.
</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">OpenForWriting</attrib>
							<attrib name="id">135507</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">12</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Boolean denoting if the block is open or closed for writing.  It should become closed to prevent the maximum blocksize from being exceeded.  (This is defined in the application layer.) 
</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">135508</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">135509</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">135510</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">135511</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
			</folder>
			<folder>
				<attribs>
					<attrib name="name">SupportTables</attrib>
				</attribs>
				<modsConfig/>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">Status</attrib>
						<attrib name="id">135353</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
							<pgSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="s">access</elem>
											<elem type="s">where</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>default</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</pgSql>
						</SqlGenModule>
					</modsConfig>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">135354</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">true</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">Status</attrib>
							<attrib name="id">135355</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">135356</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">135357</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">135358</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">135359</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">Type</attrib>
						<attrib name="id">145372</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
							<pgSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="s">access</elem>
											<elem type="s">where</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>default</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</pgSql>
						</SqlGenModule>
					</modsConfig>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">145373</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">true</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">Type</attrib>
							<attrib name="id">145374</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">145375</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">145376</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">145377</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">145378</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">Description</attrib>
						<attrib name="id">235514</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
							<pgSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="s">access</elem>
											<elem type="s">where</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>default</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</pgSql>
						</SqlGenModule>
					</modsConfig>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">235515</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">true</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">Status</attrib>
							<attrib name="id">235516</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">235517</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">235518</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">235519</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">235520</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">AnalysisDatasetLumi</attrib>
						<attrib name="id">233534</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
							<pgSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="s">access</elem>
											<elem type="s">where</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>default</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</pgSql>
						</SqlGenModule>
					</modsConfig>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">233535</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134947</attrib>
							<attrib name="name">AnalysisDataset</attrib>
							<attrib name="id">233536</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134946</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">135311</attrib>
							<attrib name="name">Lumi</attrib>
							<attrib name="id">233556</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">135310</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">233537</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">233538</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">233539</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">233540</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">TimeLog</attrib>
						<attrib name="id">285503</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
							<pgSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="s">access</elem>
											<elem type="s">where</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>default</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</pgSql>
						</SqlGenModule>
					</modsConfig>
					<docs>
						<p>
							<t>Log here what server did, i.e, what tables were involved and what operation was done and why.</t>
						</p>
						<p>
							<t>Inserted new processed dataset</t>
						</p>
						<p>
							<t>Inserted new 1000 files to processed dataset my Fav</t>
						</p>
						<p>
							<t>Closed file block damn_fat_fileblock_23</t>
						</p>
					</docs>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="name">ID</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="matchType">s</attrib>
							<attrib name="id">285504</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">true</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="name">LogEntry</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="matchType">s</attrib>
							<attrib name="id">285505</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">136868</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="name">CreationDate</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="matchType">s</attrib>
							<attrib name="id">285506</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="matchType">s</attrib>
							<attrib name="id">285507</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="matchType">s</attrib>
							<attrib name="id">285508</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="matchType">s</attrib>
							<attrib name="id">285509</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
			</folder>
			<folder>
				<attribs>
					<attrib name="name">Applications</attrib>
				</attribs>
				<modsConfig/>
				<docs>
					<p>
						<t>These tables contain detailed information about parameters and application invocations that make up a processing path. This may not be the complete provenance as the DBS may only contain parameters for which it is determined beforehand are interesting to query upon. Instead, some parameters may serve as references to external provenance information.</t>
					</p>
				</docs>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">AlgorithmConfig</attrib>
						<attrib name="id">135418</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
						</SqlGenModule>
					</modsConfig>
					<docs>
						<p>
							<t>An instance of running an application. AppConfig: A unique combination of an Application and a ParameterSet would represent an AppConfig. A file will be produced by a single AppConfig. That is why we have many to one relationship between AppConfig and File table. Note that a File has a relationship with ProcessedDataset, but that is not enough to determine the exact AppConfig used to produce the file. The reason being that a ProcessedDataset can spawn multiple AppConfig.</t>
						</p>
					</docs>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">135419</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Primary key row id.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">true</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">188023</attrib>
							<attrib name="name">ExecutableName</attrib>
							<attrib name="id">233512</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">188022</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>The name of the executable.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">true</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">188009</attrib>
							<attrib name="name">ApplicationVersion</attrib>
							<attrib name="id">233516</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">188008</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>The version of the software.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">true</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">135382</attrib>
							<attrib name="name">ApplicationFamily</attrib>
							<attrib name="id">233520</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">135381</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>The software family (eg- ORCA, CMKIN, etc.)</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">true</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">135440</attrib>
							<attrib name="name">ParameterSetID</attrib>
							<attrib name="id">135421</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">135439</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>A valid parameter set id from the ParameterSet table.
</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">135424</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">135425</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">135426</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">135427</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">AppFamily</attrib>
						<attrib name="id">135381</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
							<pgSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="s">access</elem>
											<elem type="s">where</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>default</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</pgSql>
						</SqlGenModule>
					</modsConfig>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">135382</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">true</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">true</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">FamilyName</attrib>
							<attrib name="id">135383</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">135384</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">135385</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">135386</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">135387</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">AppVersion</attrib>
						<attrib name="id">188008</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
							<pgSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="s">access</elem>
											<elem type="s">where</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>default</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</pgSql>
						</SqlGenModule>
					</modsConfig>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">188009</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">true</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">true</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">Version</attrib>
							<attrib name="id">188010</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">188011</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">188012</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">188013</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">188014</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">AppExecutable</attrib>
						<attrib name="id">188022</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
							<pgSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="s">access</elem>
											<elem type="s">where</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>default</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</pgSql>
						</SqlGenModule>
					</modsConfig>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">188023</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">true</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">true</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ExecutableName</attrib>
							<attrib name="id">188024</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">188025</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">188026</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">188027</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">188028</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
			</folder>
			<folder>
				<attribs>
					<attrib name="name">ParameterSets</attrib>
				</attribs>
				<modsConfig/>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">QueryableParameterSet</attrib>
						<attrib name="id">135439</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
							<pgSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="s">access</elem>
											<elem type="s">where</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>default</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</pgSql>
						</SqlGenModule>
					</modsConfig>
					<docs>
						<p>
							<t>A parameter set consisting of bound parameters and other parameter sets. Optionally, a data cards file can be given also.</t>
						</p>
						<p>
							<t>ParameterSet: is a collection of Parameters which are used to make a process. An entry in the ParameterSet can represent a single parameter or a composite parameter. For example a single parameter would contain name, value ,type and hash and a composite parameter would contain name, value ,type and hash with or without content. Also a composite parameter would contain lot of other single or composite parameters. This is represented by the ParameterBinding table. One can look at these as a hierarchical directory structure. A single parameter would be lowest level directory which does not contain any other directory. A composite parameter would be a directory that may or may not contain other directory and may or may not contain a file (content). Hash uniquely identifies a parameter (both single and composite)</t>
						</p>
					</docs>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">135440</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Primary key row id.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">Hash</attrib>
							<attrib name="id">193343</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">136868</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">true</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">Name</attrib>
							<attrib name="id">135441</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Name of the parameter set.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">Version</attrib>
							<attrib name="id">135442</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Version of the parameter set.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">Type</attrib>
							<attrib name="id">194358</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Version of the parameter set.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">Annotation</attrib>
							<attrib name="id">135443</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">136868</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>User provided comments as to the purpose and definition of the parameter set.
</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">Content</attrib>
							<attrib name="id">193339</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">136868</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">135445</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">135446</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">135447</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">135448</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">ParameterBinding</attrib>
						<attrib name="id">135449</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
							<pgSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="s">access</elem>
											<elem type="s">where</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>default</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</pgSql>
						</SqlGenModule>
					</modsConfig>
					<docs>
						<p>
							<t>A bound parmeter (key value pair.) Bound parameters are in a many to one relationship with ParameterSets.</t>
						</p>
					</docs>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">135450</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Primary key row id.
</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">true</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">135440</attrib>
							<attrib name="name">Self</attrib>
							<attrib name="id">135454</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">135439</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>A  valid parameter set id from the ParameterSet table.
</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">true</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">135440</attrib>
							<attrib name="name">Contains</attrib>
							<attrib name="id">194354</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">135439</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>A  valid parameter set id from the ParameterSet table.
</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">135455</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">135456</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">135457</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">135458</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
			</folder>
			<folder>
				<attribs>
					<attrib name="name">PrimaryDSDescTables</attrib>
				</attribs>
				<modsConfig/>
				<docs>
					<p>
						<t>These tables contain information that refer directly to rows in other tables.</t>
					</p>
				</docs>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">PrimaryDatasetDescription</attrib>
						<attrib name="id">135533</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
							<pgSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="s">access</elem>
											<elem type="s">where</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>default</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</pgSql>
						</SqlGenModule>
					</modsConfig>
					<docs>
						<p>
							<t>This table relates descriptions which help define a Primary Dataset,</t>
						</p>
					</docs>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">135534</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>AbstractDataset key row id.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">true</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">135556</attrib>
							<attrib name="name">TriggerDescriptionID</attrib>
							<attrib name="id">135535</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">135555</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>A valid trigger description id from the TriggerDescription table.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">true</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">135563</attrib>
							<attrib name="name">MCChannelDescriptionID</attrib>
							<attrib name="id">135536</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">135562</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>A valid MC description id from the MCDescription table.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">true</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">194369</attrib>
							<attrib name="name">OtherDescriptionID</attrib>
							<attrib name="id">194729</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">194368</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>A valid trigger description id from the TriggerDescription table.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">135538</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">135539</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">135540</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">135541</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">TriggerPathDescription</attrib>
						<attrib name="id">135555</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
							<pgSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="s">access</elem>
											<elem type="s">where</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>default</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</pgSql>
						</SqlGenModule>
					</modsConfig>
					<docs>
						<p>
							<t>This is a description of a trigger mask corresponding to the data in the primary dataset.</t>
						</p>
					</docs>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">135556</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Primary key row id.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">true</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">true</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">TriggerPathDescription</attrib>
							<attrib name="id">135557</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>This is a description of a trigger mask corresponding to the data in the primary dataset.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">135558</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">135559</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">135560</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">135561</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">MCDescription</attrib>
						<attrib name="id">135562</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
							<pgSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="s">access</elem>
											<elem type="s">where</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>default</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</pgSql>
						</SqlGenModule>
					</modsConfig>
					<docs>
						<p>
							<t>Monte Carlo description table.</t>
						</p>
					</docs>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">135563</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Primary key row id.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">true</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">MCChannelDescription</attrib>
							<attrib name="id">135564</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Short name for the MC Channel.
</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">true</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">MCProduction</attrib>
							<attrib name="id">135565</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>MC production process.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">true</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">MCDecayChain</attrib>
							<attrib name="id">135566</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>MC decay chain processes.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">135567</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">135568</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">135569</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">135570</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">OtherDescription</attrib>
						<attrib name="id">194368</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
							<pgSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="s">access</elem>
											<elem type="s">where</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>default</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</pgSql>
						</SqlGenModule>
					</modsConfig>
					<docs>
						<p>
							<t>This is a description of a trigger mask corresponding to the data in the primary dataset.</t>
						</p>
					</docs>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">194369</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Primary key row id.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">true</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">Description</attrib>
							<attrib name="id">194370</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">7</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>This is a description of a trigger mask corresponding to the data in the primary dataset.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">194371</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">194372</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">194373</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">194374</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
			</folder>
			<folder>
				<attribs>
					<attrib name="name">FileSupport</attrib>
				</attribs>
				<modsConfig/>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">FileTier</attrib>
						<attrib name="id">143251</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
							<pgSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="s">access</elem>
											<elem type="s">where</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>default</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</pgSql>
						</SqlGenModule>
					</modsConfig>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">143269</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Primary key row id.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">true</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134965</attrib>
							<attrib name="name">Fileid</attrib>
							<attrib name="id">143272</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134964</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">true</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">135054</attrib>
							<attrib name="name">DataTier</attrib>
							<attrib name="id">143535</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">135049</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">143253</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">143257</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">143261</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">143265</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">FileParentage</attrib>
						<attrib name="id">135482</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
							<pgSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="s">access</elem>
											<elem type="s">where</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>default</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</pgSql>
						</SqlGenModule>
					</modsConfig>
					<docs>
						<p>
							<t>FileParentage: This represents the lineage of the file. All the parents of the file are represented in this table. This file parentage is required at the file granularity. If it is not presents at this granularity , then there would be no way to correctly determining the exact parent of any particular file. Please note that this parentage is also specified at the ProcessedDataset level. The only reason for that is the optimization for discovery. One can easily find the parents of a dataset via traversing all the files and finding the parents of those files and then locating the datasets those files exists in. To optimize this discovery we need parentage at the dataset level too.</t>
						</p>
					</docs>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134965</attrib>
							<attrib name="name">ThisFile</attrib>
							<attrib name="id">135484</attrib>
							<attrib name="onDelete">s</attrib>
							<attrib name="onUpdate">s</attrib>
							<attrib name="refTable">134964</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>A valid event collection id from EventCollection.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134965</attrib>
							<attrib name="name">ItsParent</attrib>
							<attrib name="id">135483</attrib>
							<attrib name="onDelete">c</attrib>
							<attrib name="onUpdate">c</attrib>
							<attrib name="refTable">134964</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Foreign key from Event Collection is the primary key.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">135485</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">135486</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">135487</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">135488</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">FileLumi</attrib>
						<attrib name="id">272990</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
							<pgSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="s">access</elem>
											<elem type="s">where</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>default</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</pgSql>
						</SqlGenModule>
					</modsConfig>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">272991</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">true</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134965</attrib>
							<attrib name="name">Fileid</attrib>
							<attrib name="id">272992</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134964</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">true</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">135311</attrib>
							<attrib name="name">Lumi</attrib>
							<attrib name="id">272993</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">135310</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">272994</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">272995</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">272996</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">272997</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">FileAlgoMap</attrib>
						<attrib name="id">279868</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
							<pgSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="s">access</elem>
											<elem type="s">where</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>default</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</pgSql>
						</SqlGenModule>
					</modsConfig>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">279869</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Primary key row id.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134965</attrib>
							<attrib name="name">Fileid</attrib>
							<attrib name="id">279870</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134964</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">135419</attrib>
							<attrib name="name">Algorithm</attrib>
							<attrib name="id">279871</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">135418</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">279872</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">279873</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">279874</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">279875</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
			</folder>
			<folder>
				<attribs>
					<attrib name="name">ProcDSSupport</attrib>
				</attribs>
				<modsConfig/>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">ProcDSRuns</attrib>
						<attrib name="id">275516</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
						</SqlGenModule>
					</modsConfig>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">275517</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Primary key row id.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">true</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134985</attrib>
							<attrib name="name">Dataset</attrib>
							<attrib name="id">275518</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134984</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">true</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">135030</attrib>
							<attrib name="name">Run</attrib>
							<attrib name="id">275519</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">135029</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">275520</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">275521</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">275522</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">275523</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">ProcDSTier</attrib>
						<attrib name="id">189889</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
						</SqlGenModule>
					</modsConfig>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">189890</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Primary key row id.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">true</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134985</attrib>
							<attrib name="name">Dataset</attrib>
							<attrib name="id">189891</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134984</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">true</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">135054</attrib>
							<attrib name="name">DataTier</attrib>
							<attrib name="id">189892</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">135049</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">189893</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">189894</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">189895</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">189896</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">DatasetParentage</attrib>
						<attrib name="id">190550</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
							<pgSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="s">access</elem>
											<elem type="s">where</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>default</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</pgSql>
						</SqlGenModule>
					</modsConfig>
					<docs>
						<p>
							<t>This table defines a self relation that allows for aggregation of EventCollections. Each relation is unique.</t>
						</p>
					</docs>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134985</attrib>
							<attrib name="name">ThisDataset</attrib>
							<attrib name="id">190552</attrib>
							<attrib name="onDelete">s</attrib>
							<attrib name="onUpdate">s</attrib>
							<attrib name="refTable">134984</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>A valid event collection id from EventCollection.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134985</attrib>
							<attrib name="name">ItsParent</attrib>
							<attrib name="id">190551</attrib>
							<attrib name="onDelete">c</attrib>
							<attrib name="onUpdate">c</attrib>
							<attrib name="refTable">134984</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Foreign key from Event Collection is the primary key.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">190553</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">190554</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">190555</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">190556</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
				<table>
					<vars>
						<list>
							<header>
								<elem>name</elem>
								<elem>type</elem>
								<elem>value</elem>
								<elem>descr</elem>
							</header>
						</list>
					</vars>
					<triggers/>
					<rules/>
					<attribs>
						<attrib name="comment"/>
						<attrib name="name">ProcAlgoMap</attrib>
						<attrib name="id">280269</attrib>
						<attrib name="schema"/>
						<attrib name="tempPK"/>
						<attrib name="tempFK"/>
						<attrib name="tempOther"/>
						<attrib name="sqlCommands"/>
					</attribs>
					<modsConfig>
						<SqlGenModule>
							<stdSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
										</row>
									</list>
								</idx>
							</stdSql>
							<oraSql>
								<idx type="l">
									<list>
										<header>
											<elem type="i">id</elem>
											<elem type="s">index</elem>
											<elem type="s">name</elem>
											<elem type="i">ts</elem>
											<elem type="b">bitmap</elem>
											<elem type="b">stats</elem>
											<elem type="b">noSort</elem>
										</header>
										<row>
											<elem>340</elem>
											<elem>Idx1</elem>
											<elem/>
											<elem>0</elem>
											<elem>false</elem>
											<elem>false</elem>
											<elem>false</elem>
										</row>
									</list>
								</idx>
							</oraSql>
						</SqlGenModule>
					</modsConfig>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">true</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">ID</attrib>
							<attrib name="id">280270</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">238995</attrib>
						</attribs>
						<modsConfig/>
						<docs>
							<p>
								<t>Primary key row id.</t>
							</p>
						</docs>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134985</attrib>
							<attrib name="name">Dataset</attrib>
							<attrib name="id">280271</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134984</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">true</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">135419</attrib>
							<attrib name="name">Algorithm</attrib>
							<attrib name="id">280272</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">135418</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">CreationDate</attrib>
							<attrib name="id">280273</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">240057</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">CreatedBy</attrib>
							<attrib name="id">280274</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">0</attrib>
							<attrib name="name">LastModificationDate</attrib>
							<attrib name="id">280275</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">0</attrib>
							<attrib name="type">239351</attrib>
						</attribs>
						<modsConfig/>
					</field>
					<field>
						<fieldAttribs>
							<attribs>
								<attrib name="17">false</attrib>
								<attrib name="16">false</attrib>
								<attrib name="15">false</attrib>
								<attrib name="339"/>
								<attrib name="338">false</attrib>
								<attrib name="340">false</attrib>
							</attribs>
						</fieldAttribs>
						<attribs>
							<attrib name="comment"/>
							<attrib name="matchType">s</attrib>
							<attrib name="refField">134524</attrib>
							<attrib name="name">LastModifiedBy</attrib>
							<attrib name="id">280276</attrib>
							<attrib name="onDelete">n</attrib>
							<attrib name="onUpdate">n</attrib>
							<attrib name="refTable">134523</attrib>
							<attrib name="type">0</attrib>
						</attribs>
						<modsConfig/>
					</field>
				</table>
			</folder>
		</objects>
	</database>
</project>
