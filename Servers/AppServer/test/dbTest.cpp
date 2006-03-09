/* DBManagement.cpp

*/

#include "DBManagement.hpp"
#include "DBException.hpp"
#include <iostream.h>
#include <string>
using namespace std;
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
		//dbm =  new DBManagement("anzar", "cms_dbs_afaq", "Me1tabOlia6s");
		dbm =  new DBManagement("ProdRO", "cms_dbs_reader", "mi2sbe5stOWu");
                //dbm  =  new DBManagement("sveseli", "cms_dbs_sveseli", "kIek8ie8ethN");
		//dbm =  new DBManagement("mydsn", "sekhri", "");
		//dbm  =  new DBManagement("mydsnvijay", "ggraham", "");
		
		dbm->open();
		//testResults1(dbm,"SELECT * from t_event_collection WHERE processed_dataset=1");
		/*while(true){
			//testResults1(dbm,"SELECT * FROM t_data_tier,t_processing_path,t_primary_dataset,t_processed_dataset,t_event_collection,t_block,t_info_evcoll WHERE (t_block.processed_dataset = t_processed_dataset.id ) AND (t_event_collection.processed_dataset = t_processed_dataset.id ) AND (t_info_evcoll.event_collection = t_event_collection.id ) AND (t_processed_dataset.primary_dataset = t_primary_dataset.id ) AND (t_processed_dataset.processing_path = t_processing_path.id ) AND (t_processing_path.data_tier = t_data_tier.id )  AND t_processed_dataset.name='bt_DST8713_2x1033PU_g133_CMS' AND t_data_tier.name='DST' AND t_primary_dataset.name='bt03_gg_bbh200_2taujmu'");
			testResults1(dbm,"SELECT t_event_collection.collection_index AS \"tec.ci\",t_event_collection.id AS \"tec.id\",t_event_collection.is_primary AS \"tec.ip\",t_event_collection.processed_dataset AS \"tec.pd\" FROM t_event_collection WHERE (t_event_collection.processed_dataset=14) AND (t_event_collection.collection_index=160800001)");
			testResults1(dbm,"SELECT * from t_event_collection");
		}*/
		testResults1(dbm,"SELECT * from t_event_collection where t_event_collection.id=12324");
		dbm->close();
		//while(true){
			//testResults1(dbm,"SELECT count(*) FROM mytable");
			//testResults1(dbm,(string)"SELECT id,name,lastname,batch FROM mytable");
                        //testResults1(dbm,"SELECT count(*) FROM t_file"); 
                        //testResults1(dbm,"SELECT count(*) FROM t_event_collection"); 
		//}
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
