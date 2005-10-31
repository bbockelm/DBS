/* DBManagement.cpp

*/

#include "DBManagement.hpp"
#include "DBException.hpp"
#include <iostream.h>
#include <string>

void testResults1(DBManagement * dbm, string sql) {
	ResultSet* rs = dbm->executeQueryWithResults(sql);
	int noOfRows = rs->getNoOfRows();
	int noOfCols = rs->getNoOfCols();
	cout<<"noOfRows: "<<noOfRows<<"\tnoOfCols: "<<noOfCols<<endl;
	for(int i = 0; i< noOfRows; i++) {
		for(int j = 0; j < noOfCols; j++) {
			//cout<<"\t["<<i<<","<<j<<"] : "<<rs->getElement(i,j)<<endl;
			cout<<"NAME "<<rs->getColName(j);
			cout<<"\t["<<i<<","<<j<<"] : "<<rs->getElement(i,j)<<endl;
		}
		cout<<endl;
	}
	delete rs;

}

int main(int argc,char *argv[]) {
	DBManagement * dbm;
	try {
		dbm =  new DBManagement("mysqldsn", "sekhri", "");
		//dbm =  new DBManagement("mydsn", "sekhri", "");
		//dbm  =  new DBManagement("mydsnvijay", "ggraham", "");
		dbm->open();
		//while(true){
			//testResults1(dbm,"SELECT count(*) FROM mytable");
			testResults1(dbm,(string)"SELECT id,name,lastname,batch FROM mytable");
		//cout<<"query is "<<query.c_str()<<endl;	
		//testResults1(dbm,(char*)query.c_str());			
//testResults1(dbm,"SELECT analysiscollectiondata.analysiscollectiondataid AS \"analysiscollectiondata.analysiscollectiondataid\" FROM analysiscollectiondata WHERE (analysiscollectiondata.analysiscollectionstatus = analysiscollectionstatus.analysiscollectionstatusid ) AND (analysiscollectiondata.analysisdatasetid = analysisdataset.analysisdatasetid ) AND (analysiscollectiondata.eventcollectionid = eventcollection.eventcollectionid ) AND (analysiscollectiondata.otherqueryablemetadata = parameterset.parametersetid ) AND (analysiscollectiondata.validationstatus = validationstatus.validationstatusid ) AND (evcollfile.evcollid = eventcollection.eventcollectionid ) AND (evcollfile.fileid = file.fileid ) AND (eventcollection.processeddatasetid = processeddataset.processeddatasetid ) AND (file.filestatus = filestatus.filestatusid ) AND (file.filetype = filetype.filetypeid )  AND (collectiontype.collectiontype = 'CMKIN') AND (primarydataset.cobradatasetname = 'mu03c_hzz_4mu_550')");
			//testResults(dbm,"SELECT id,name,lastname,batch FROM mytable");
			//testResults(dbm,"SELECT id,name FROM qqq");
		//}
		/*dbm->beginTransection();
		dbm->executeQuery("insert into mytable(id,name,lastname,batch) values(1111,'hhhh','kkkkk',1111)");
		dbm->executeQuery("insert into mytable(id,name,lastname,batch) values(3333,'hhhh','kkkkk',3333)");
		dbm->rollback();
		dbm->executeQuery("insert into mytable(id,name,lastname,batch) values(4444,'hhhh','kkkkk',4444)");
		dbm->executeQuery("insert into mytable(id,name,lastname,batch) values(5555,'hhhh','kkkkk',5555)");
		dbm->commit();
		dbm->executeQuery("insert into mytable(id,name,lastname,batch) values(6666,'hhhh','kkkkk',6666)");
		dbm->rollback();
		dbm->executeQuery("insert into mytable(id,name,lastname,batch) values(7777,'hhhh','kkkkk',7777)");
		dbm->commit();
		dbm->endTransection();
		dbm->executeQuery("insert into mytable(id,name,lastname,batch) values(8888,'hhhh','kkkkk',8888)");
		dbm->close();
		delete dbm;
*/
	//throw (string)"shdhshdklsd";
		
	} catch (char * str) {
		cout<<"Exception char : "<<str<<endl;
		//dbm->close();
		//cout<<"Return code is "<<dbm->returnCode<<endl;
		//delete dbm;
	} catch (int i) {
		 cout<<"Exception int : "<<i<<endl;
	} catch (string s ) {
		cout<<" Exception str: "<<s<<endl;
	} catch (DBException &e) {
		cout<<" Exception DBLayerException: "<<e.report()<<endl;
	}

	
	return 0;
	

}
