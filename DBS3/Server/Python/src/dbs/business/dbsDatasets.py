#!/usr/bin/env python
"""
This class holds business implementation for the Dataset entities
"""

__revision__ = "$Id: $"
__version__ = "$Revision: $"

class Datasets:
        """
        This class holds business implementation for the listDatasets API
        """

        def listDatasets(self):
		"""
		Business logic for the list Datasets API.
		"""

		dsObj={} # set the required parameters here
		datasetsDAO=self.daofactory(classname="Dataset.List")
		conn=self.dbi.connection()
		try:
			datasets = datasetsDAO.execute(dsObj, conn, True)
			assert len(datasets)==1, "No matching Datasets found"
			tran.commit()
			return datasets #JSON encoding should be done in Model class ?

        	except Exception, e:
            		tran.rollback()
            		self.logger.exception(e)
            		raise e
        	finally:
            		conn.close()

