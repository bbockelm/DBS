#ifndef _Util_hpp_included_
#define _Util_hpp_included_
#include <string>
#include "ResultSet.hpp"
#include "RowInterface.hpp"
#include "common.hpp"

class Util {
  
public:
	Util();
	std::string getToken(std::string, int);
	std::string getTokenAt(std::string, int);
	std::string eraseEndChars(std::string, int);
	bool isSet(RowInterface*, std::string, std::string);
	bool isKeySet(RowInterface*, Keys_iter, Keys_iter);
	bool isKeySetCheckNull(RowInterface* aRow, Keys_iter bk, Keys_iter ek,  Keys* notNullKeys );
	//bool isListOfKeySet(RowInterface*, ListOfLists_iter, ListOfLists_iter);
	bool isListOfKeySet(RowInterface*, ListOfLists_iter, ListOfLists_iter, Keys* notNullKeys);
	bool isConsistant(RowInterface*, RowInterface*, std::string& message);
	bool isInMultiRef(std::string, Dictionary_iter, Dictionary_iter);
	bool toSetCol(std::string, Dictionary_iter, Dictionary_iter);
	ListOfLists_iter getListOfKey(RowInterface*, ListOfLists_iter, ListOfLists_iter);
	std::string getDataType(std::string);
	std::string getStrValue(RowInterface*, std::string, std::string);
	void setValue(RowInterface* aRow, std::string name, std::string dataType, std::string value);
	Dictionary_iter getMappedValue(std::string, Dictionary_iter, Dictionary_iter);
	//Keys_iter getKey(RowInterface*, Keys_iter, Keys_iter);
	void equatePKWithRef(RowInterface* aRow, Dictionary_iter b, Dictionary_iter e);
	void equatePKWithMultiRef(RowInterface* aRow, Dictionary_iter b, Dictionary_iter e);
	void copyRow(RowInterface* aRowTo, RowInterface* aRowFrom);
	void display(RowInterface* aRow);
	Keys getKey(RowInterface*, Keys_iter, Keys_iter);
	Keys_iter getNullKey(RowInterface*, Keys_iter, Keys_iter);
	Keys getPrimaryKeys(Keys_iter, Keys_iter, Dictionary_iter, Dictionary_iter);

	void setSchema(Dictionary*);
         
        // Simple convertion functions. 
	std::string itoa(int i);
        int atoi(std::string);

        std::string Util::ctoa(char c); 
        std::string Util::ftoa(float f); 
        float Util::atof(std::string); 

        void tokenize(const string& str, vector<string>& tokens, const string& delimiters = " ");
	long getTime();
private:
	Dictionary* schema;
	void fillPrimaryKeys(std::string, Keys &, Dictionary_iter, Dictionary_iter);
};


#endif

