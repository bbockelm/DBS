#include "Managers.hpp"
#include "Util.hpp"
#include "ManagerImpls.hpp"
#include "BizLayerException.hpp"

PrimaryDatasetAPIManager::PrimaryDatasetAPIManager() {
}

int PrimaryDatasetAPIManager::write(Primarydatasetmultirow* aRow, PrimarydatasetMultiTable* table) {
        PrimaryDatasetManager pdsMgr;

        vector<Primarydatasetmultirow*> pdsVect;
        pdsVect.push_back(aRow);
	int ret =0;
	//try {
        	ret = pdsMgr.write(pdsVect, table);
	/*} catch (BizLayerException &e)  {
		cout<<"INSIDE int PrimaryDatasetAPIManager::writ (BizLayerException &e"<<endl;
		throw  BizLayerException(e.report());
	} */

        return ret;


}

int PrimaryDatasetAPIManager::read(Primarydatasetmultirow* aRow, PrimarydatasetMultiTable* table) {
    int ret =0; 
    try {
        PrimaryDatasetManager pdsMgr;

        ret = pdsMgr.read(aRow, table);
   
       
    } catch (BizLayerException &e)  {
                throw BizLayerException(e.report());
    }

     return ret;

}


PrimaryDatasetAPIManager::~PrimaryDatasetAPIManager() {
}

