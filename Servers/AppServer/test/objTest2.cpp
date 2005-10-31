#include "Util.hpp"
#include "DBManagement.hpp"
#include "ObjectLayerTables.hpp"
#include "BaseSchemaNConstratints.hpp"
#include "RowNSchemaBinding.hpp"
#include "ObjectLayerException.hpp"
#include "DBException.hpp"
#include "SingleTableInterface.hpp"
#include "TableFactory.hpp"
#include "TableInterface.hpp"

int main() {

  try {
        cout << " >> Main Started << " << endl;

        cout << " >> Connecting to DBManager << " << endl; 
        DBManagement* dbManager  =  new DBManagement("anzar", "ggraham", "");
        dbManager->open();

        cout << " >> Creating a Multi Object << " << endl;
        InsertappsMultiTable * aMultiTable = new InsertappsMultiTable(dbManager);

        cout << " >> Deleting Multi Object << " << endl;
        delete aMultiTable;

        cout << " >> Disconnecting to DBManager << " << endl;
        dbManager->close();
        return 0;
 
 
  } catch (ObjectLayerException &e) {
        cout<<"Exception "<<e.report()<<endl;
  } catch (DBException &e) {
        cout<<"Exception "<<e.report()<<endl;
  }

}
