<?xml version="1.0" encoding="ISO-8859-1"?>
<!-- Edited with XML Spy v2006 (http://www.altova.com) -->
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method='html' version='1.0' encoding='UTF-8' indent='yes'/>

<xsl:template match="/">
  <html>
  <body  BGCOLOR="#E9E9F4" >

  <h2>Processed Dataset</h2>
    <table border="1" bgcolor="#ffffcc">
      <tr bgcolor="#9acd32">

        <th align="left">Path</th>
      </tr>
      <xsl:for-each select="dbs/processed-dataset">
      <tr>
	<td><a>
	<xsl:attribute name="href">javascript:void(0)</xsl:attribute>
	<xsl:attribute name="target">_top</xsl:attribute>
	<xsl:attribute name="onClick">parent.document.myform5.path.value='<xsl:value-of select="@path"/>';parent.document.myform8.path.value='<xsl:value-of select="@path"/>';parent.submitform('api=getDatasetContents&amp;path='+parent.document.myform5.path.value)</xsl:attribute>
	<xsl:value-of select="@path"/> 
	</a></td>
      </tr>

      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>
