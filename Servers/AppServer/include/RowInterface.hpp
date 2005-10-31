#ifndef _RowInterface_hpp_included_
#define _RowInterface_hpp_included_
///This class defines Interface class for the Rows of a Table
#include <string>
#include <map>
#include <vector>
#include <iostream>

class RowMap {
	public:
	std::string name;
	void* obj;
	RowMap(){};
	void set(std::string name ,void* obj){
		//this->RowMap::name = name;
		this->name = name;
		this->obj = obj;
	}
};

class RowInterface {

public:
  RowInterface(){};  
  virtual void* getValue(std::string){};//cout<<"INSIDE getValue RowInterface"<<endl; 

  virtual void setValue(std::string, void*){};//cout<<"INSIDE setValue RowInterface"<<endl;};
  //virtual void setValue(string, void*)=0;
  std::string toUpper(std::string);
  //virtual string* getTableName(void){};
  //RowInterface& getConstituentRow(string, string);
  void* getConstituentRow(std::string, std::string);
  std::vector<std::string> getConstituentList(void);
protected:
   //multimap<string, RowInterface> constituentObjects;
   std::vector<RowMap> constituentObjects;
   RowMap rowMap;
};


typedef std::multimap<std::string, RowInterface> ObjMap;
typedef std::multimap<std::string, RowInterface>::value_type ObjEntry;
typedef std::multimap<std::string, RowInterface>::iterator ObjMap_iter;

#endif

