<?xml version="1.0" encoding="ISO-8859-1"?>
<!-- Edited with XML Spy v2006 (http://www.altova.com) -->
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method='html' version='1.0' encoding='UTF-8' indent='yes'/>
<xsl:template match="/">
  <html>
  <body   BGCOLOR="#E9E9F4" >

  <h2>Processed Dataset</h2>
    <table border="1" bgcolor="#ffffcc">

      <tr bgcolor="#9acd32">

        <th align="left">Id</th>
        <th align="left">Path</th>
      </tr>
      <xsl:for-each select="dbs/processed-dataset">
      <tr>
        <td><xsl:value-of select="@id"/></td>
        <td><xsl:value-of select="@path"/></td>
      </tr>

      </xsl:for-each>
    </table>

  <h2>Parent</h2>
    <table border="1" bgcolor="#ffffcc">

      <tr bgcolor="#9acd32">

        <th align="left">Path</th>
        <th align="left">Tier</th>
        <th align="left">Type</th>
        <th align="left">Id</th>

      </tr>
      <xsl:for-each select="dbs/processed-dataset/parent">
      <tr>
        <td><xsl:value-of select="@path"/></td>
        <td><xsl:value-of select="@tier"/></td>
        <td><xsl:value-of select="@type"/></td>
        <td><xsl:value-of select="@id"/></td>
      </tr>

      </xsl:for-each>
    </table>

  </body>
  </html>
</xsl:template>
</xsl:stylesheet>
