<?xml version="1.0" encoding="ISO-8859-1"?>
<!-- Edited with XML Spy v2006 (http://www.altova.com) -->
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method='html' version='1.0' encoding='UTF-8' indent='yes'/>

<xsl:template match="/">
  <html>
  <body   BGCOLOR="#E9E9F4" >

  <h2>Parameter Set</h2>
    <table border="1" bgcolor="#ffffcc">

      <tr bgcolor="#9acd32">

        <th align="left">Hash</th>
        <th align="left">Content</th>
      </tr>
      <xsl:for-each select="dbs/parameter-set">
      <tr>
        <td><xsl:value-of select="@hash"/></td>
        <td><xsl:value-of select="@content"/></td>
      </tr>

      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>
