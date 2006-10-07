<?xml version="1.0" encoding="ISO-8859-1"?>
<!-- Edited with XML Spy v2006 (http://www.altova.com) -->
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method='html' version='1.0' encoding='UTF-8' indent='yes'/>
<xsl:template match="/">
  <html>
  <body   BGCOLOR="#E9E9F4" >

  <h2>Application</h2>
    <table border="1" bgcolor="#ffffcc">

      <tr bgcolor="#9acd32">

        <th align="left">Family</th>
        <th align="left">Executable</th>
        <th align="left">Version</th>
      </tr>
      <xsl:for-each select="dbs/application">
      <tr>	
	<td><a>
	<xsl:attribute name="href">javascript:void(0)</xsl:attribute>
	<xsl:attribute name="target">_top</xsl:attribute>
	<xsl:attribute name="onClick">parent.document.myform2.pattern.value='/<xsl:value-of select="@version"/>/<xsl:value-of select="@family"/>/<xsl:value-of select="@executable"/>';parent.submitform('api=listDatasetsFromApp&amp;pattern='+parent.document.myform2.pattern.value)</xsl:attribute>
	<xsl:value-of select="@family"/></a></td>
        <td><a>
	<xsl:attribute name="href">javascript:void(0)</xsl:attribute>
	<xsl:attribute name="target">_top</xsl:attribute>
	<xsl:attribute name="onClick">parent.document.myform2.pattern.value='/<xsl:value-of select="@version"/>/<xsl:value-of select="@family"/>/<xsl:value-of select="@executable"/>';parent.submitform('api=listDatasetsFromApp&amp;pattern='+parent.document.myform2.pattern.value)</xsl:attribute>
	<xsl:value-of select="@executable"/></a></td>
        <td><a>
	<xsl:attribute name="href">javascript:void(0)</xsl:attribute>
	<xsl:attribute name="target">_top</xsl:attribute>
	<xsl:attribute name="onClick">parent.document.myform2.pattern.value='/<xsl:value-of select="@version"/>/<xsl:value-of select="@family"/>/<xsl:value-of select="@executable"/>';parent.submitform('api=listDatasetsFromApp&amp;pattern='+parent.document.myform2.pattern.value)</xsl:attribute>
	<xsl:value-of select="@version"/></a></td>
      </tr>

      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>
