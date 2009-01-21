This package containes the unittests for dbs api's.

To run the test:

1. DBS environment must be set up before running the test. Particularly, $PYTHONPATH should include the directory where DBSAPI package is located.
2. Specify the dbs instance being tested in testconfig.py (don't want to get the default one from dbs.config as I need an admin instance also for deletion testing). If it's a mysql instance then give the same url for 'reader', 'writer' and 'admin'.
3. Make sure to have the tables empty before running this unittests. In other words, nothing must be inserted in Tables before running this unittests. If the unittest Inserts data into the tables, one must redeploy the schema before running the unittests again.
4. To run the unittests run './rundbsunittest.sh' from the command line. 
5. See output in unittest.output

Brief Description:
	dbs unittests contains the following test cases in the order specified below:

	1. TestDefineObjects defines all dbs objects and sets up the unittest variables. If this step fails no other test will be run.
	2. TestCheckServer checks the connection to the database through all three accounts('reader', 'writer' and 'admin') by issuing getServerInfo. If this Test fails no further tests are run.
	3. TestCheckDB checks if the tables are empty by issuing 'find dataset', 'find primds' etc. All queries must return an empty list. If this test fails no further tests are run.
	4. TestInsertData fills the tables with the specific unit test data. (defined in  testdata.py and TestDefineObjects). If this test fails no further tests are run.
	5. TestListData tests different list* api's. This test is based on the old validation test. Most of the testcases use list* api and then compare the result  with what was inserted in the previous step.
	6. TestQL tests the query language. It takes the queries from queries.txt, executes it and then compares the result with the predefined one from results.py (must be generated or filled manually in advance by the developer)
	7. TestDeleteData tests the delete api's. For now only a dataset.

Nearest Plans:
	1. Add more positive testcases.
	2. Add tests for ApiException (must raise excpetion when expected). 
	3. Add Migration Test. 
	4. Add Integration Test with CRAB.
Furhter Plans:
	Come up with the testcases for *global* instance.
	Problem: 
		1. I cannot delete everything that was inserted so the Tables will be filled with junk. e.g. I cannot even delete the primary dataset even if no dataset is associated with it.	
		2. For QL test I can only test for the exception(this is easy), not for assertion. 	
