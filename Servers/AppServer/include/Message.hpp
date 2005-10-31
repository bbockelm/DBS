#ifndef _Message_hpp_included_
#define _Message_hpp_included_


#include <string>
#include <vector>
#include <map>

#include "Util.hpp"
class Element {
	private:
		std::string key;
		std::string value;
		std::string type;
	public:
		Element(std::string key, std::string value, std::string type) {
			this->key = key;
			this->value = value;
			this->type = type;
		}
		std::string getKey() {
			return key;
		}	
		std::string getValue() {
			return value;
		}
		std::string getType() {
			return type;
		}
};

typedef std::vector<Element*> Data;
typedef Data::iterator DataIter;
typedef std::vector<Data*> VecData;
typedef VecData::iterator VecDataIter;
typedef std::map<std::string, VecData*> Map;
typedef Map::iterator MapIter;
typedef Map::value_type MsgEntry;
typedef std::vector<std::string>::iterator StrIter;
//typedef std::vector<Map*> VecMap;
//typedef VecMap::iterator VecMapIter;

class  Message {
	private:
	//public:
		std::string name;
		std::string exception;
		
                Util util;
	
		Data data;
		Map myMap;
		typedef std::vector<Message*> VecMsg;
		VecMsg vecMsg;
		//VecMap vecMap;
		
		int serializeData(std::string & message, DataIter b, DataIter e);
		bool getKVT(std::string& key, std::string& value, std::string& type, std::vector<std::string>& tempTokens, StrIter i);
		bool validateKVT(std::string& key, std::string& value, std::string& type);
	public:
		Message();
		~Message();
		void dispose();
		void setName(std::string name);
		void setException(std::string exception);
		std::string getName();
		std::string getException();
		void addVecOfElement(Data* d);
		void addElement(Element* e);
		void addMsg(Message* m);
		//void addNewElement(Element* e);
		void appendToVec(Message& inMsg,std:: string listName);
		void addVecOfVecOfElement(VecData* vd, std::string name);
		Element* getElement(int index);
                std::string getElementValue(std::string);
		std::string getElementValue(std::string paramName, std::string listName, int index);
		MapIter getMapIterBegin();
		MapIter getMapIterEnd();
		int getNoOfElements();
		//std::string serialize();
		int serialize(std::string & message);
		int serializeOne(std::string & message);
		//int deserialize(std::string message);
		int deserialize(std::string & message);
		int deserializeOne(std::string & message);
		void tokenize(const std::string& str, 
				std::vector<std::string>& tokens, 
				const std::string& delimiters);
};

typedef std::vector<Message*> VecMsg;
typedef VecMsg::iterator VecMsgIter;

#endif

