This directory contains a simple test case implementation and execution framework.

The test cases are written as One Class per API call, and one can add as many methods to the class for whatever deemed necessary for testing (test cases).

The testCase implementation class inherits for testCaseInterface classs (testCaseInterface.py). Each method of Implementation calss that needs to be executed as test case should be registered with the framework using addTestCase() interface method. A success must return "0" and failure returns "1".

To run test cases, each testcase class Object is added to runTestCases.py, and
then run framework invokes all test cases.

Some example positive and negative test cases are written here for each API
call.

To execute run,

python runTestCases.py


One can manullay edit runTestCases.py to turn on/off a particular testcase or
make changes to the test case implementation itself.

Please DO NOT update CVS with your changed test cases, before coordinating
with developers.






