#include "Util.hpp"
#include "RowNSchemaBinding.hpp"
#include <iostream>
#include <sstream>
#include <time.h>

using namespace std;


Util::Util(){}
void Util::setSchema(Dictionary* schema) {
	this->schema = schema;
}
string Util::getToken(string data, int index) {
	int firstIndex = data.find(".",index);
	//cout<<"index is "<<index<<" firstIndex "<<firstIndex<<endl;
	int dataLen = data.length();
	if( (firstIndex < 0) && (index < dataLen) ) {
		return(data.substr(index, (dataLen - index )));
	}
	if(firstIndex < 0) {
		return("");
	}
	return(data.substr(index, (firstIndex - index )));
}


string Util::getTokenAt(string data, int index) {
	int location = 0;
	int dataLen = data.length();
	string token = "";
	for(int i = 0; i <= index; ++i) {
		if(dataLen <= location) {
			return("");
		}
		token = getToken(data,location);
		location = location + token.length() + 1;
	}
	return(token);

}


string Util::eraseEndChars(string data, int howMany) {
	int len = data.length() - howMany;
	if(len < 0) {
		return data;
	}
	data.erase(len,howMany);
	return(data);
}

Dictionary_iter Util::getMappedValue(string key,Dictionary_iter b, Dictionary_iter e) {
	Dictionary_iter mapIterator;
	for(mapIterator = b; mapIterator != e; mapIterator++) {
		//if( toUpper(name) == toUpper(key) ) {
		if( mapIterator->first == key ) {
			return(mapIterator);
		}
		int nameLen = (mapIterator->first).length();
		int keyLen = key.length();
		if( (keyLen < nameLen) && ( keyLen == 63 ) ) {
			//if( toUpper(name.substr(0,keyLen)) == toUpper(key) ) {
			if( (mapIterator->first).substr(0,keyLen) == key ) {
      				return(mapIterator);
			}
		}
	}
	return(mapIterator);
}


//string Util::getDataType(string key, Dictionary_iter bs, Dictionary_iter es) {
string Util::getDataType(string key) {
	Dictionary_iter retIterator = this->getMappedValue(key, schema->begin(), schema->end());
	return  retIterator == schema->end() ? "" : retIterator->second ;
} 
 


/*Keys_iter Util::getKey(RowInterface* aRow, Keys_iter bk, Keys_iter ek) {
	for(Keys_iter keyIterator = bk; keyIterator != ek; ++keyIterator) {
		if(this->isSet(aRow, *keyIterator, this->getDataType(*keyIterator) ) ) {
			cout<<"returnning "<<*keyIterator<<endl;
			return(keyIterator);
		}
	}
	cout<<"return end iterator"<<endl;
	return(ek);
}*/

Keys Util::getKey(RowInterface* aRow, Keys_iter bk, Keys_iter ek) {
	Keys toReturn;
	//cout<<"inside getKey"<<endl;
	for(Keys_iter i = bk; i != ek; ++i) {
		//cout<<"getKey i "<<*i<<endl;
		if(this->isSet(aRow, *i, this->getDataType(*i) ) ) {
			//cout<<"pushing back "<<*i<<endl;
			toReturn.push_back(*i);
		}
	}
	return(toReturn);
}

Keys_iter Util::getNullKey(RowInterface* aRow, Keys_iter bk, Keys_iter ek) {
	for(Keys_iter i = bk; i != ek; ++i) {
		if(!this->isSet(aRow, *i, this->getDataType(*i) ) ) {
			return(i);
		}
	}
	return(ek);
}

ListOfLists_iter Util::getListOfKey(RowInterface* aRow, ListOfLists_iter b, ListOfLists_iter e) {
 	for(ListOfLists_iter i = b; i != e; ++i) {
		if( this->isKeySet(aRow, (*i).begin(), (*i).end()) ) {
			return(i);
		}
	}
	return(e);
}


bool Util::isKeySet(RowInterface* aRow, Keys_iter bk, Keys_iter ek) {
	for(Keys_iter i = bk; i != ek; ++i) {
		//cout<<"checking isKeySet "<<*i<<endl;
		if(!this->isSet(aRow, *i, this->getDataType(*i) ) ) {
			//cout<<"retuning..................... false"<<endl;
			return(false);
		}
	}
	//cout<<"retuning.................. true"<<endl;
	return(true);
}

bool Util::isListOfKeySet(RowInterface* aRow, ListOfLists_iter b, ListOfLists_iter e) {
	for(ListOfLists_iter i = b; i != e; ++i) {
		if( this->isKeySet(aRow, (*i).begin(), (*i).end()) ) {
			//cout<<"returnning true from isUniqueKeySet"<<endl;
			return(true);
		}
	}
	//cout<<"returnning false from isUniqueKeySet"<<endl;
	return(false);
}


 bool Util::isSet(RowInterface* aRow, string name, string dataType) {
	//cout<<"inside isSet name "<<name<<" dataType "<<dataType<<endl;
	if(dataType.length() == 0) {
		return(false);
	}
   void* value;
   if(dataType == "STRING") {
     value = (string*)aRow->getValue(name);
   }
   if(dataType == "CHARACTER") {
     value = (char*)aRow->getValue(name);
   }
   if(dataType == "INTEGER") {
	//cout<<"INSIDE Util::isSet for INTEGER"<<endl;
     value = (int*)aRow->getValue(name);
   }
   if(dataType == "FLOAT") {
        //cout<<"INSIDE Util::isSet for FLOAT"<<endl;
     value = (float*)aRow->getValue(name);
   }

	//cout<<"value is "<<value<<endl;
	//cout<<"returnning from isSet"<<endl;
   if(value != NULL) {
     return(true);
   } else {
     return(false);
   }
 }



string Util::getStrValue(RowInterface* aRow, string name, string dataType) {
	//cout<<"NAME is "<<name<<" DATATYPE is "<<dataType<<endl;
	if(dataType == "CHARACTER") {
		char* value = (char*)aRow->getValue(name);
		char actualValue = *value;
		return this->ctoa(actualValue);
	}
	if(dataType == "INTEGER") {
                //cout << "getStrValue:name " << name << endl;
		int* value = (int*)aRow->getValue(name);
		int actualValue = *value;
		return this->itoa(actualValue);
	}
	if(dataType == "STRING") {
		string* value = (string*)aRow->getValue(name);
		string actualValue = *value;
		return(actualValue);
	}
        if(dataType == "FLOAT") {
                float* value = (float*)aRow->getValue(name);
                float actualValue = *value;
                //cout << "Util::getStrValue float " << actualValue << endl;
                return this->ftoa(actualValue);
        }

}


void Util::setValue(RowInterface* aRow, string name, string dataType, string value) {
	//cout<<"void Util::setValue value is "<<value<<endl;
	if( dataType == "STRING" ) {
                //cout << "dies here STRING"<< "dataType:name" << dataType << name << endl;
		string strValue = (string) value;
		cout<<" STRING "<<name<<" value is "<<strValue<<endl;
		aRow->setValue(name,&strValue);
	} 
	if( dataType == "CHARACTER" ) {
		char charValue = *(value.c_str());
		cout<<" CHARACTER "<<name<<" value is "<<charValue<<endl;
		aRow->setValue(name,&charValue );
	}
	if( dataType == "INTEGER" ) {
		int intValue  = atoi(value.c_str());
		cout<<" INTEGER "<<name<<" value is "<<intValue<<endl;
		aRow->setValue(name,&intValue );
	}
        if( dataType == "FLOAT" ) {
                float floatValue  = this->atof(value);
                cout<<" FLOAT "<<name<<" value is "<<floatValue<<endl;
                aRow->setValue(name,&floatValue );
        }

}


bool Util::isConsistant(RowInterface* aRowInDB, RowInterface* aRow) {
	for(Dictionary_iter i = schema->begin(); i != schema->end(); ++i) {
		if( this->isSet(aRow, i->first, i->second) ) {
			if( this->isSet(aRowInDB, i->first, i->second) ) {
				//cout<<"\nComparing "<<i->first<<"\naRow\t"<<this->getStrValue(aRow, i->first, i->second)<<"\naRowInDB\t"<<this->getStrValue(aRowInDB, i->first, i->second)<<endl;
				if ( this->getStrValue(aRow, i->first, i->second) != 
					this->getStrValue(aRowInDB, i->first, i->second) ) {
					return(false);
				}
			} else {
				cout<<"Data is  present in DB but coloumn "<<i->first<<" is NULL "<<endl;
				return(false);
			}
		} else if(this->isSet(aRowInDB, i->first, i->second) ) {
			string value = this->getStrValue(aRowInDB, i->first, i->second);
			this->setValue(aRow, i->first, i->second, value);
		}
	}
	return(true);
}
/*
bool Util::isInMultiRef(string tabelName, Dictionary_iter b, Dictionary_iter e) {
	for(Dictionary_iter i = b; i != e; ++i) {
		if(tabelName == this->getTokenAt(i->second,0) ) {
			return(true);
		}
	}
	return(false);
}
*/


bool Util::toSetCol(string name, Dictionary_iter b, Dictionary_iter e) {
	bool toSet = true;
	//cout<<"UTIL name is "<<name<<endl;
	string tableNameOneFromRs = this->getTokenAt(name ,0);
	int nameLen = name.length();
	if( tableNameOneFromRs.length() == nameLen) {
		return(true);
	}
	string nameOneFromRs = tableNameOneFromRs + "." + this->getTokenAt(name , 1);
	//cout<<"nameOneFromRs "<<nameOneFromRs<<" name "<<name<<endl;
	if( nameOneFromRs.length() < nameLen ) {	
		//cout<<"colNameOneIndex "<<colNameOneIndex<<endl;
		string nameTwoFromRs = this->getTokenAt(name , 2) + "." + this->getTokenAt(name , 3);
		//cout<<"nameTwoFromRs "<<nameTwoFromRs<<endl;
		for(Dictionary_iter i = b; i != e; ++i) {
			string nameOneFromSatisfy = i->first;
			//cout<<"nameOneFromSatisfy "<<i->first<<" tableNameOneFromRs "<<tableNameOneFromRs<<endl;
			if( tableNameOneFromRs == i->first ) {
				//cout<<"nameTwoFromSatisfy "<<nameTwoFromSatisfy<<"\nnameTwoFromSatisfy.substr(0,nameTwoFromRs.length() "<<i->second.substr(0,nameTwoFromRs.length())<<"\nnameTwoFromRs "<<nameTwoFromRs<<endl;
				if( nameLen = 63 ) {
					cout<<"len is 63"<<endl;
					if( nameTwoFromRs != i->second.substr(0,nameTwoFromRs.length()) ) {
						return(false);
					}
				} else if( i->second != nameTwoFromRs ) {
					return(false);
				}
			}
		}
	}
	//cout<<"Before retunning bool Util::toSetCol("<<endl;
	return(toSet);
}


void Util::fillPrimaryKeys(string tableName, Keys & toReturn, Dictionary_iter b, Dictionary_iter e) {//MultiRef
	//cout<<"RowNSchemaBinding init "<<endl;
	RowNSchemaBinding rowNSchemaBinding;
	cout<<"tableName "<<tableName<<endl;
	Keys* pKeys = (rowNSchemaBinding.getSchemaObject(tableName))->getPrimaryKeys();
	for(Keys_iter i = pKeys->begin(); i != pKeys->end(); ++i) {
		bool added = false;
		//cout<<"line 1"<<endl;
		for(Dictionary_iter j = b; j != e; ++j) {
			//cout<<"line 2"<<endl;
			if(j->second == *i) {
				//cout<<"line 3"<<endl;
				toReturn.push_back(*i + "." + j->first);
				//cout<<"line 4"<<endl;
				added = true;
			}
		}
		if(!added) {
			//cout<<"line 5"<<endl;
			toReturn.push_back(*i);
		}
		//cout<<"line 6"<<endl;
	}
	//cout<<"line 7"<<endl;
	//copy( pKeys->begin(), pKeys->end(), back_inserter(toReturn) );
}

//Keys Util::getPrimaryKeys(Keys_iter b, Keys_iter e) {//SchemaOrder
Keys Util::getPrimaryKeys(Keys_iter bk, Keys_iter ek, Dictionary_iter bd, Dictionary_iter ed) {//SchemaOrder & MultiRef
	Keys toReturn;
	for(Keys_iter i = bk; i != ek; ++i) {
		//this->fillPrimaryKeys((*i+"row"), toReturn);
		this->fillPrimaryKeys((*i+"row"), toReturn, bd, ed);
	}
	for(Keys_iter i = toReturn.begin(); i != toReturn.end(); ++i) {
		cout<<"Primary key is "<<*i<<endl;
	}
	return toReturn;
}

/*string Util::makeClause(RowInterface* aRow, Keys_iter b, Keys_iter e) {
	string clause="";
	for(Keys_iter i = b; i != e; ++i) {
		cout<<"key "<<*i<<" keyType "<<this->getDataType(*i)<<endl;
		clause += *i + "=" + 
		this->getStrValue(aRow, *i , this->getDataType(*i)) + " AND ";
	}
	clause = this->eraseEndChars(clause,5);
	cout<<"clause "<<clause<<endl;
	return clause;
}*/
bool Util::isInMultiRef(string key, Dictionary_iter b, Dictionary_iter e) {//Pass Key a.b.c.d or TableName and MultiRef
	if( b == e ) {
		return false;
	}
	for(Dictionary_iter i = b; i != e; ++i) {
		if (  (i->first == (string)(this->getTokenAt(key,2) + "." + this->getTokenAt(key,3)) ) &&
			(i->second == (string)(this->getTokenAt(key,0) + "." + this->getTokenAt(key,1)) ) ) {
			return true;
		}
		if(key == this->getTokenAt(i->second,0) ) {
			return(true);
		}
	}
	return false;
}

void Util::equatePKWithRef(RowInterface* aRow, Dictionary_iter b, Dictionary_iter e) {//Refrences
	//cout<<"insdie Util::equatePKWithRef"<<endl;
	for(Dictionary_iter r = b; r != e; ++r) {
		//cout<<" r->second "<<r->second<<endl;
		string dataType = this->getDataType(r->second);//DataType has to be same for both fields in refrences
		//cout<<"dataType "<<dataType<<endl;
		string value;
		string name;
		bool toSet = false;
		if(this->isSet(aRow, r->second, dataType) ) {
			value = this->getStrValue(aRow, r->second, dataType);
			name = r->first;
			toSet = true;
		} else if(this->isSet(aRow, r->first, dataType) ) {
			value = this->getStrValue(aRow, r->first, dataType);
			name = r->second;
			toSet = true;
		}
		if(toSet) {
			this->setValue(aRow, name, dataType, value);
		}
	}
}

void Util::equatePKWithMultiRef(RowInterface* aRow, Dictionary_iter b, Dictionary_iter e) {//Multi Refrences
	//cout<<"insdie Util::equatePKWithMultiRef"<<endl;
	for(Dictionary_iter m = b; m != e; ++m) {
		string key = m->second + "." + m->first;
		string dataType = this->getDataType(key);
		string value;
		bool toSet = false;
		//cout<<"cheking "<<key<<endl;
		if(this->isSet(aRow, key, dataType) ) {
			value = this->getStrValue(aRow, key, dataType);
			//cout<<"Value is "<<value<<endl;
			toSet = true;
		} 
		if(toSet) {
			//cout<<"inside toSet"<<endl;
			this->setValue(aRow, key , dataType, value);
		}
	}
}


int Util::atoi(string in) {
  std::istringstream istr(in);
  int i;
  istr >> i;
  return i;
}


string Util::itoa(int i){
	char temp[20];
	sprintf(temp,"%d",i);
	return((string)temp);
}

string Util::ctoa(char c){
        string s(1, c);
        return s;
}


std::string Util::ftoa(float f) {

  char temp[40];
  sprintf(temp,"%f",f);
  return((string)temp);    

}



float Util::atof(std::string fin) {
  std::istringstream istr(fin);
  float f;
  istr >> f;
  return f;
  //cout <<" NO IMPLEMENTATION Yet Util::atof";
}



void Util::tokenize(const string& str, vector<string>& tokens, const string& delimiters) {
        // Skip delimiters at beginning.
        string::size_type lastPos = str.find_first_not_of(delimiters, 0);
        // Find first "non-delimiter".
        string::size_type pos     = str.find_first_of(delimiters, lastPos);
        while (string::npos != pos || string::npos != lastPos) {
                // Found a token, add it to the vector.
                tokens.push_back(str.substr(lastPos, pos - lastPos));
                // Skip delimiters.  Note the "not_of"
                lastPos = str.find_first_not_of(delimiters, pos);
                // Find next "non-delimiter"
                pos = str.find_first_of(delimiters, lastPos);
        }
}

long Util::getTime() {
	time_t seconds = time (NULL);
	return((long) seconds);
}

