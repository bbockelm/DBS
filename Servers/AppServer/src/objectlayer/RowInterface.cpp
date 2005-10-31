#include <iostream>
#include "RowInterface.hpp"
using namespace std;

  string RowInterface::toUpper(string strToConvert) {//change each element of the string to upper case
   for(unsigned int i=0;i<strToConvert.length();i++) {
      strToConvert[i] = toupper(strToConvert[i]);
   }
   return strToConvert;//return the converted string
  }

 void* RowInterface::getConstituentRow(string table, string fkkey){
  string key;
	//cout<<"in RowInterface::getConstituentRow table "<<table<<" fkkey "<<fkkey<<endl;
    if (fkkey.compare("") == 0) {
        key = table;
    }
    else {
        key = fkkey;
    }
    for (int i=0; i<constituentObjects.size(); i++) {
       if(constituentObjects.at(i).name == key ){
		//cout<<"I am returnning a valid value"<<endl;
           return constituentObjects.at(i).obj;
       }
    }
	//cout<<"retunoing NULL CRAP!!"<<endl;
}


 vector<string> RowInterface::getConstituentList(void) {
    vector<string> retList; 
    for (int i=0; i<constituentObjects.size(); i++) {
        retList.push_back(constituentObjects.at(i).name);
    }
    return retList;
 }



/* RowInterface& RowInterface::getConstituentRow(string table, string fkkey){
    string key;
    if (fkkey.compare("") == 0) {
        key = table;
    }
    else {
        key = fkkey;
    }
	//cout<<"key is "<<key<<endl;
    for (multimap<string, RowInterface>::iterator i=this->constituentObjects.begin();
          i != this->constituentObjects.end(); i++ ){
		//cout<<"start of for loop "<<endl;
		//cout<<" I first is "<<i->first<<endl;
		//cout<<" I second "<<i->second.getTableName()<<endl;
    if (key.compare(i->first) == 0) {
           //check table name here ???????????????????????????????
           return i->second;
        }
    }
 }
*/
