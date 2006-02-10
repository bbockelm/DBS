#!/bin/python
import sys
import testCaseInterface
import testCreatePrimaryDS
import testCreateProcessedDS
import testCreateBlock
import testGetDatasetBlocks
import testCreateEventCollection
import testGetDatasetContents

if __name__ == "__main__":
   
   #testObj = testCreatePrimaryDS.testCreatePrimaryDS()
   #testObj.run()

   testObj = testCreateProcessedDS.testCreateProcessedDS()
   testObj.run()
   """
   testObj = testCreateBlock.testCreateBlock()
   testObj.run()

   testObj = testGetDatasetBlocks.testGetDatasetBlocks()
   testObj.run()
   
   testObj = testCreateEventCollection.testCreateEventCollection()
   testObj.run()
   """ 
   #testObj = testGetDatasetContents.testGetDatasetContents()
   #testObj.run() 

   #sys.exit(0) 
