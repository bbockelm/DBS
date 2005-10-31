#ifndef _common_hpp_included_
#define _common_hpp_included_
#include <string>
#include <list>
#include <map>

using namespace std;

typedef multimap<string, string, less<string>  > Dictionary;
typedef multimap<string, string, less<string> >::value_type Entry;
typedef multimap<string, string, less<string> >::iterator Dictionary_iter;

typedef list<string> Keys;
typedef list<string>::iterator Keys_iter;

typedef list<Keys> ListOfLists;
typedef list<Keys>::iterator ListOfLists_iter;

typedef multimap<string, Keys, less<string>  > DictionaryOfList;

template <class T>
class VALUEDEF {
  
public:
	VALUEDEF(){
		//cout<<"Inside () constructor"<<endl;
		this->isSet = false;
		//this->myNull=NULL;
	}
	VALUEDEF (const T& val) {
		//cout<<"Inside const constructor value is "<<val<<endl;
		this->value=val;
		this->isSet = true;
	}

	T& getValue() {
		//cout << "inside getValue()" << endl;     
		if (this->isSet) {
			return this->value;
		}
		//cout<<"returnning null";
		return *((T*)NULL);
	}

private:
	T value;
	bool isSet;
};
  
typedef VALUEDEF<int> INTEGER;
typedef VALUEDEF<string> STRING;
typedef VALUEDEF<char> CHARACTER;
typedef VALUEDEF<float> FLOAT;


#endif

