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

        <th align="left">Path</th>
      </tr>
      <xsl:for-each select="dbs/processed-dataset">
      <tr>
        <td><xsl:value-of select="@path"/></td>
      </tr>

      </xsl:for-each>
    </table>

  <h2>Block</h2>
    <table border="1" bgcolor="#ffffcc">

      <tr bgcolor="#9acd32">

        <th align="left">Id</th>
        <th align="left">Name</th>
        <th align="left">Status</th>

      </tr>
      <xsl:for-each select="dbs/processed-dataset/block">
      <tr>
        <td><xsl:value-of select="@id"/></td>
        <td><xsl:value-of select="@name"/></td>
        <td><xsl:value-of select="@status"/></td>
      </tr>

      </xsl:for-each>
    </table>

  <h2>Event Collection</h2>
    <table border="1" bgcolor="#ffffcc">

      <tr bgcolor="#9acd32">

        <th align="left">Name</th>
        <th align="left">Events</th>
        <th align="left">Status</th>
        <th align="left">InBlock</th>
        <th align="left">GUID</th>
        <th align="left">Logical FileName</th>
        <th align="left">File Status</th>
        <th align="left">Checksum</th>
        <th align="left">Size</th>

      </tr>
      <xsl:for-each select="dbs/processed-dataset/block/event-collection">
      <tr>
        <td><xsl:value-of select="@name"/></td>
        <td><xsl:value-of select="@events"/></td>
        <td><xsl:value-of select="@status"/></td>
        <td><xsl:value-of select="@inblock"/></td>
        <td><xsl:value-of select="@guid"/></td>
        <td><xsl:value-of select="@lfn"/></td>
        <td><xsl:value-of select="@fstatus"/></td>
        <td><xsl:value-of select="@checksum"/></td>
        <td><xsl:value-of select="@size"/></td>
      </tr>

      </xsl:for-each>
    </table>

  </body>
  </html>
</xsl:template>
</xsl:stylesheet>
