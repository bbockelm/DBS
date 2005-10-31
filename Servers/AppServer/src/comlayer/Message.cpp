#include "Message.hpp"
#include <iostream>

using namespace std;

Message::Message() {
	name = "";
	exception = "";
}
Message::~Message() {
	this->dispose();
}
void Message::dispose() {
	for(DataIter i = this->data.begin(); i != this->data.end(); ++i) {
		delete *i;
	}
	data.clear();
	for(MapIter m = this->myMap.begin(); m != this->myMap.end(); ++m ) {
		VecData* vecData = (VecData*)(m->second);
		for(VecDataIter vd = vecData->begin(); vd != vecData->end(); ++vd ) {
			Data* d = *(vd);
			for(DataIter i = d->begin(); i != d->end(); ++i) {
				delete *i;//Element
			}
			d->clear();
			delete d;//Data
		}
		vecData->clear();
		delete vecData;//Vector of Data
	}
	for(VecMsgIter m = this->vecMsg.begin(); m != this->vecMsg.end(); ++m ) {
		delete *m;
	}
	this->vecMsg.clear();
	this->myMap.clear();
}

void Message::setName(string name) {
	this->name = name;
}

void Message::setException(string exception) {
	this->exception = exception;
}

string Message::getName() {
	return this->name;
}

string Message::getException() {
	return this->exception;
}

void Message::addElement(Element* e) {
	this->data.push_back((Element*)e);
}

void Message::addMsg(Message* m) {
	this->vecMsg.push_back(m);
}

/*
void Message::addMap(Map* m) {
	this->vecMap.push_back(m);
}
*/

void Message::addVecOfElement(Data* d) {
	for(DataIter i = d->begin(); i != d->end(); ++i) {
		Element* e = new Element((*i)->getKey(), (*i)->getValue(), (*i)->getType());
		this->addElement(e);
	}
}


void Message::appendToVec(Message& inMsg, string listName) {
	VecData* vd;
	Data* d = new Data();
	bool found = false;
	for(MapIter m = this->getMapIterBegin(); m != this->getMapIterEnd(); ++m ) {
		if(m->first == listName) {
			vd = (VecData*)(m->second);
			found = true;
		}
	}
	if(!found){
		vd = new VecData();
	} 
	//cout<<"No of elements is "<<inMsg.getNoOfElements()<<endl;
	for(int i = 0; i != inMsg.getNoOfElements(); ++i) {
		Element* e = inMsg.getElement(i);
		//cout<<"making a new element key "<<e->getKey()<<" value "<< e->getValue()<<" type "<< e->getType()<<endl;
		Element* eToInsert =  new Element(e->getKey(), e->getValue(), e->getType());
		d->push_back(eToInsert);
	}
	vd->push_back(d);
	if(!found){
		this->addVecOfVecOfElement(vd, listName);
	}
}

/*
void Message::addNewElement(Element* e) {
	Element* eToAdd = new Element(e->getKey(), e->getValue(), e->getType());
	this->addElement(eToAdd);
}
*/
void Message::addVecOfVecOfElement(VecData* vd, string name) {
	this->myMap.insert(MsgEntry(name, (VecData*)vd));
}

Element* Message::getElement(int index) {
	return(index < this->data.size() ? *(this->data.begin() + index) :  *(this->data.end() - 1) );
}

MapIter Message::getMapIterBegin() {
	return this->myMap.begin();
}
MapIter Message::getMapIterEnd() {
	return this->myMap.end();
}
/*
VecMapIter Message::getVecMapIterBegin() {
	return this->vecMap.begin();
}

VecMapIter Message::getVecMapIterEnd() {
	return this->vecMap.end();
}
*/
int Message::getNoOfElements() {
	return this->data.size();
}

string Message::getElementValue(string paramName) {
	for(int i = 0 ; i < this->getNoOfElements() ; ++i) {
		Element* e = this->getElement(i);
		if ( e->getKey() == paramName ) {
			return e->getValue();
		}
	}
	return "NOTFOUND";
}
/*
string Message::getElementValue(string paramName, string listName, int listIndex, int mapIndex) {
	int count = 0;
	int countMap = 0;
	for(MapIter m = this->myMap.begin(); m != this->myMap.end(); ++m ) {
		if(m->first == listName && mapIndex == countMap ) {
			
			VecData* vecData = (VecData*)(m->second);
			for(VecDataIter vd = vecData->begin(); vd != vecData->end(); ++vd ) {
				if(index == count) {
					Data* d = *(vd);
					for(DataIter i = d->begin(); i != d->end(); ++i) {
						if ( (*i)->getKey() == paramName ) {
							return (*i)->getValue();
						}
					}
				}
				++count;
			}
		}
		++countMap;
	}	
	return "NOTFOUND";

}*/
string Message::getElementValue(string paramName, string listName, int index) {
	int count = 0;
	for(MapIter m = this->myMap.begin(); m != this->myMap.end(); ++m ) {
		if(m->first == listName) {
			VecData* vecData = (VecData*)(m->second);
			for(VecDataIter vd = vecData->begin(); vd != vecData->end(); ++vd ) {
				if(index == count) {
					Data* d = *(vd);
					for(DataIter i = d->begin(); i != d->end(); ++i) {
						if ( (*i)->getKey() == paramName ) {
							return (*i)->getValue();
						}
					}
				}
				++count;
			}
		}
	}	
	return "NOTFOUND";
}


int Message::serializeData(string & message, DataIter b, DataIter e) {
	for(DataIter i = b; i != e; ++i) {
		message += "KEY=" + ((Element*)(*i))->getKey() + ":" +
		"VALUE=" + ((Element*)(*i))->getValue() + ":" +
		"TYPE=" + ((Element*)(*i))->getType() + ";";
	}
	return 0;
}

int Message::serialize(string & message) {
	message = "";
	if(this->vecMsg.size() > 0) {
		for(VecMsgIter m = this->vecMsg.begin(); m != this->vecMsg.end(); ++m ) {
			string temp;
			(*m)->serializeOne(temp);
			message += temp;
		}
	} else {
		this->serializeOne(message);
	}
	return 1;

}
int Message::serializeOne(string & message) {
	//string message = "MESSAGE_BEGIN;" + 
	message = "MESSAGE_BEGIN;" + 
	(string)"NAME=" + this->name + (string)";";
	if(this->exception.length() > 0 ) {
		message += "EXCEPTION_OCCURED=TRUE;";
	} else {
		message += "EXCEPTION_OCCURED=FALSE;";
	}
	message += "EXCEPTION=" + this->exception + ";" +
	(string)"DATA_BEGIN;";
	this->serializeData(message, this->data.begin(), this->data.end() );
	if (this->myMap.size() > 0) {
		for(MapIter i = this->myMap.begin(); i != this->myMap.end(); ++i) {
			message += "KEY=" +  i->first + ":VALUE=BEGIN:TYPE=VECOFVEC;";
			VecData* vd = (VecData*)i->second;
			for(VecDataIter j = vd->begin(); j != vd->end(); ++j) {
				message += "KEY=NULL:VALUE=BEGIN:TYPE=VECOFDATA;";
				this->serializeData(message, (*j)->begin(), (*j)->end() );
				message += "KEY=NULL:VALUE=END:TYPE=VECOFDATA;";
			}
			message += "KEY=" +  i->first + ":VALUE=END:TYPE=VECOFVEC;";
		}
	}
	message += "DATA_END;" +
	(string)"MESSAGE_END";
	return 1;
}

int Message::deserialize(string& message) {
	int pos = message.find("MESSAGE_END",0);
	int tempPos = pos + 11;
	if( tempPos == message.length()) { //11 is the length of MESSAGE_END
		deserializeOne(message);
	} else {
		int index = 0;
		int len = 0;
		string temp;
		
		//cout<<"pos "<<pos<<" tempPos "<<tempPos<<endl;
		while(tempPos != message.length()) {
			//cout<<"tempPos "<<tempPos<<"index "<<index<<endl;
			len = tempPos - index;
			//cout<<"len "<<len<<endl;
			temp = message.substr(index, len);
			Message* m = new Message();
			m->deserializeOne(temp);
			this->addMsg(m);
			index = tempPos;
			//cout<<"index "<<index<<endl;
			tempPos = message.find("MESSAGE_END",index) + 11;
		}
		len = tempPos - index;
		temp = message.substr(index, len);
		Message* m = new Message();
		m->deserializeOne(temp);
		this->addMsg(m);
	}
	return 1;
}


int Message::deserializeOne(string& message) {
	vector<string> tokens;
	vector<string> tempTokens;
	util.tokenize(message, tokens, ";");
	if(tokens.size() < 7 ) {
		return -1;
	}
	/*for( vector<string>::iterator i = tokens.begin(); i != tokens.end(); ++i ) {
		cout<<"Token----->"<<*i<<endl;
	}*/
	if( *(tokens.begin()) != "MESSAGE_BEGIN"  ||
		*(tokens.end() - 1) != "MESSAGE_END" ||
		*(tokens.begin() + 4) != "DATA_BEGIN" || 
		*(tokens.end() - 2 ) != "DATA_END" ) {
		return -1;
	}
	string temp = *(tokens.begin() + 1) ;//NAME=
	if( temp.substr(0,5) != "NAME=" ) {
		return -1;
	} else {
		this->setName(temp.substr(5));
	}
	temp = *(tokens.begin() + 2) ;//EXCEPTION_OCCURED=
	if( temp.substr(0,18) != "EXCEPTION_OCCURED=" ) {
		return -1;
	} 
	temp = *(tokens.begin() + 3) ;//EXCEPTION=
	if( temp.substr(0,10) != "EXCEPTION=" ) {
		return -1;
	} else {
		this->setException(temp.substr(10));
	}
	this->dispose();
	for( StrIter i = (tokens.begin() + 5) ; i != (tokens.end() - 2 ); ++i ) {//Between DATA_BEGIN & DATA_END
		string key,value,type;
		if(!this->getKVT(key, value, type, tempTokens, i) ) {
			return -1;
		}
		if(value.substr(6) == "BEGIN" &&
		type.substr(5) == "VECOFVEC") {
			VecData* vd = new VecData();
			string vecName = key.substr(4);
			for( StrIter j = i + 1 ; i != (tokens.end() - 2 ); ++j ) {
				if(!this->getKVT(key, value, type, tempTokens, j) ) {
					return -1;
				}
				if(value.substr(6) == "END" &&
				type.substr(5) == "VECOFVEC") {
					this->addVecOfVecOfElement(vd,vecName);
					i = j;
					break;
				}
				if(value.substr(6) == "BEGIN" &&
				type.substr(5) == "VECOFDATA") {
					Data* d = new Data();
					for( StrIter k = j + 1 ; k != (tokens.end() - 2 ); ++k ) {
						if(!this->getKVT(key, value, type, tempTokens, k) ) {
							return -1;
						}
						if(value.substr(6) == "END" &&
						type.substr(5) == "VECOFDATA") {
							vd->push_back(d);
							j = k;
							break;
						}
						Element* e = new Element(key.substr(4), value.substr(6), type.substr(5));
						d->push_back(e);
					}
				}
			}
		} else {
			Element* e = new Element(key.substr(4), value.substr(6), type.substr(5));
			this->addElement(e);
		}
	}
	return 1;
}

bool Message::getKVT(string& key, string& value, string& type, vector<string>& tempTokens, StrIter i) {
	tempTokens.clear();
	util.tokenize(*i, tempTokens, ":");
	if(tempTokens.size() != 3) {
		return false;
	}
	key =  *tempTokens.begin();
	value =  *(tempTokens.begin() + 1);
	type =  *(tempTokens.begin() + 2);
	
	if(!this->validateKVT(key, value, type)) {
		return false;
	}
	return true;
}

bool Message::validateKVT(string& key, string& value, string& type) {
	if( key.substr(0,4) != "KEY=" ||
		value.substr(0,6) != "VALUE=" ||
		type.substr(0,5) != "TYPE=" ) {
		return false;
	}
	return true;
}

/*int main() {
	Message m;
	m.setName("Myname");
	Element e1("k1","v1","t1");
	Element e2("k2","v2","t2");
	Element e3("k3","v3","t3");
	//m.addElement(e1);
	//m.addElement(e2);
	string message = m.serialize();
	cout<<"Serialized MESSAGE is \n"<<message<<endl;
	cout<<"deserialize returned  "<<m.deserialize(message)<<endl;
	
	m.addElement(e3);
	message = m.serialize();
	cout<<"Again Serialized MESSAGE is \n"<<message<<endl;
}*/
