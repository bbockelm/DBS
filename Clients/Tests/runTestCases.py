#!/bin/python
import testCaseInterface
import testCreatePrimaryDS
import testCreateProcessedDS
import testCreateBlock

if __name__ == "__main__":

   testObj = testCreatePrimaryDS.testCreatePrimaryDS()
   testObj.run()
   testObj = testCreateProcessedDS.testCreateProcessedDS()
   testObj.run()
   testObj = testCreateBlock.testCreateBlock()
   testObj.run()
