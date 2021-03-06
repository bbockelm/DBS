#!/bin/python
import sys
import testCaseInterface
import testCreatePrimaryDS
import testCreateProcessedDS
import testCreateBlock
import testGetDatasetBlocks
import testCreateEventCollection
import testMergeEventCollection
import testGetDatasetContents
import testListDataset
import datetime
import time


if __name__ == "__main__":
   startTime = time.mktime(datetime.datetime.now().timetuple())
   
   
   testObj = testCreatePrimaryDS.testCreatePrimaryDS()
   testObj.run()
        
   testObj = testCreateProcessedDS.testCreateProcessedDS()
   testObj.run()
   
   testObj = testCreateBlock.testCreateBlock()
   testObj.run()
   
   #testObj = testGetDatasetBlocks.testGetDatasetBlocks()
   #testObj.run()
   
      
   testObj = testCreateEventCollection.testCreateEventCollection()
   testObj.run()
   
   #testObj = testMergeEventCollection.testMergeEventCollection()
   #testObj.run()
   
    
   testObj = testGetDatasetContents.testGetDatasetContents()
   testObj.run() 
   
   testObj = testListDataset.testListDataset()
   testObj.run() 
   
   endTime = time.mktime(datetime.datetime.now().timetuple())
   timeDiff = endTime - startTime
   print "TIME ELAPSED ",timeDiff
   #sys.exit(0) 
