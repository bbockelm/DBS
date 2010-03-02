#!/usr/bin/env python
"""
This module provides StorageElement.ListBlockSE data access object.
"""
__revision__ = "$Id: ListBlockSE.py,v 1.1 2010/03/02 20:17:19 afaq Exp $"
__version__ = "$Revision: 1.1 $"

from WMCore.Database.DBFormatter import DBFormatter

class ListBlockSE(DBFormatter):
    """
    StorageElement List DAO class.
    """
    def __init__(self, logger, dbi, owner):
        """
        Add schema owner and sql.
        """
        DBFormatter.__init__(self, logger, dbi)
        self.owner = "%s." % owner if not owner in ("", "__MYSQL__") else ""
        self.sql = \
	"""
	SELECT SE.SE_NAME, B.BLOCK_NAME 
	FROM %sSTORAGE_ELEMENTS SE
		JOIN %sBLOCK_STORAGE_ELEMENTS BSE
			ON BSE.SE_ID = SE.SE_ID
		JOIN %sBLOCKS B
			ON B.BLOCK_ID = BSE.BLOCK_ID
	WHERE BLOCK_NAME = :block_name""" % ((self.owner,) * 3)

    def execute(self, block_name = "", conn = None, trans = False):
        """
        Lists all storage elements for the block.
        """

	if not conn:
		raise "database conection does not exist"
	sql = self.sql

	binds={ "block_name" : block_name }
	cursors = self.dbi.processData(sql, binds, conn, transaction=trans, returnCursor=True)
	result = self.formatCursor(cursors[0])
	return result






