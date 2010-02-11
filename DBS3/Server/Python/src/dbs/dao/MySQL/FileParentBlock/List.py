#!/usr/bin/env python
"""
This module provides FileParentBlock.List data access object.

Given the ID of a File, returns a LIST of the dicts containing IDs 
[{block_id, dataset_id},....] of the Parent BLOCK of the 
Block containing THIS file.
"""
__revision__ = "$Id: List.py,v 1.1 2010/02/05 21:00:45 afaq Exp $"
__version__ = "$Revision: 1.1 $"

from dbs.dao.Oracle.FileParentBlock.List import List as OraFileParentBlockList

class List(OraFileParentBlockList):
        pass

