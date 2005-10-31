#include "Message.hpp"
#include <iostream>

using namespace std;

int test1() {
	Message m;
	m.setName("Myname");
	Element* e1 = new Element("k1","v1","t1");
	Element* e2 = new Element("k2","v2","t2");
	m.addElement(e1);
	m.addElement(e2);
	Message m2;
	Element* e4 = new Element("k4","v4","t4");
	Element* e5 = new Element("k5","v5","t5");
	Element* e6 = new Element("k6","v6","t6");
	m2.addElement(e4);
	m2.addElement(e5);
	m2.addElement(e6);
	m.appendToVec(m2,"abc");
	m.appendToVec(m2,"abc");
	cout<<"serliized message is "<<endl;
	string x;
	m.serialize(x);
	cout<<x<<endl;
	return 1;
}


int test2() {
	typedef std::vector<Element*> Data;
	typedef Data::iterator DataIter;
	typedef std::vector<Data*> VecData;
	typedef VecData::iterator VecDataIter;
	VecData* vd1 = new VecData();
	VecData* vd2 = new VecData();

	Message m;
	m.setName("Myname");
	Element* e1 = new Element("k1","v1","t1");
	Element* e2 = new Element("k2","v2","t2");
	m.addElement(e1);
	m.addElement(e2);

	Data* d1 = new Data();
	Element* e4 = new Element("k4","v4","t4");
	Element* e5 = new Element("k5","v5","t5");
	d1->push_back(e4);
	d1->push_back(e5);
	
	Data* d2 = new Data();
	Element* e6 = new Element("k6","v6","t6");
	Element* e7 = new Element("k7","v7","t7");
	d2->push_back(e6);
	d2->push_back(e7);



	Data* d3 = new Data();
	Element* e8 = new Element("k8","v8","t8");
	Element* e9 = new Element("k9","v9","t9");
	d3->push_back(e8);
	d3->push_back(e9);
	
	Data* d4 = new Data();
	Element* e10 = new Element("k10","v10","t10");
	Element* e11 = new Element("k11","v11","t11");
	d4->push_back(e10);
	d4->push_back(e11);

	vd1->push_back(d1);
	vd1->push_back(d2);
	vd2->push_back(d3);
	vd2->push_back(d4);
	
	m.addVecOfVecOfElement(vd1,"VD1");
	m.addVecOfVecOfElement(vd2,"VD2");
	
	string message; 
	m.serialize(message);
	cout<<"Serialized MESSAGE is \n"<<message<<endl;
	return 1;

}


int test3() {
	Message m;
	string temp = "MESSAGE_BEGIN;NAME=Myname;EXCEPTION_OCCURED=FALSE;EXCEPTION=;DATA_BEGIN;KEY=k1:VALUE=v1:TYPE=t1;KEY=k2:VALUE=v2:TYPE=t2;KEY=VD1:VALUE=BEGIN:TYPE=VECOFVEC;KEY=NULL:VALUE=BEGIN:TYPE=VECOFDATA;KEY=k4:VALUE=v4:TYPE=t4;KEY=k5:VALUE=v5:TYPE=t5;KEY=NULL:VALUE=END:TYPE=VECOFDATA;KEY=NULL:VALUE=BEGIN:TYPE=VECOFDATA;KEY=k6:VALUE=v6:TYPE=t6;KEY=k7:VALUE=v7:TYPE=t7;KEY=NULL:VALUE=END:TYPE=VECOFDATA;KEY=VD1:VALUE=END:TYPE=VECOFVEC;KEY=VD2:VALUE=BEGIN:TYPE=VECOFVEC;KEY=NULL:VALUE=BEGIN:TYPE=VECOFDATA;KEY=k8:VALUE=v8:TYPE=t8;KEY=k9:VALUE=v9:TYPE=t9;KEY=NULL:VALUE=END:TYPE=VECOFDATA;KEY=NULL:VALUE=BEGIN:TYPE=VECOFDATA;KEY=k10:VALUE=v10:TYPE=t10;KEY=k11:VALUE=v11:TYPE=t11;KEY=NULL:VALUE=END:TYPE=VECOFDATA;KEY=VD2:VALUE=END:TYPE=VECOFVEC;DATA_END;MESSAGE_END";
	cout<<"Deserialing"<<endl;
	cout<<"deserialize returned  "<<m.deserialize(temp)<<endl;
	Element* e3 = new Element("k3","v3","t3");
	m.addElement(e3);
	string message;
	m.serialize(message);
	cout<<"Again Serialized MESSAGE is \n"<<message<<endl;
	return 1;

}
int test4() {
	Message* m = new Message();
	m->setName("Myname");
	Element* e1 = new Element("k1","v1","t1");
	Element* e2 = new Element("k2","v2","t2");
	m->addElement(e1);
	m->addElement(e2);

	Message* m2 = new Message();
	Element* e4 = new Element("k4","v4","t4");
	Element* e5 = new Element("k5","v5","t5");
	Element* e6 = new Element("k6","v6","t6");
	m2->addElement(e4);
	m2->addElement(e5);
	m2->addElement(e6);
	
	Message* m1 = new Message();
	Element* e7 = new Element("k7","v7","t7");
	Element* e8 = new Element("k8","v8","t8");
	Element* e9 = new Element("k9","v9","t9");
	m1->addElement(e7);
	m1->addElement(e8);
	m1->addElement(e9);

//	m.appendToVec(m2,"abc");
//	m.appendToVec(m2,"abc");

	Message m3;
	m3.addMsg(m);
	m3.addMsg(m2);
	m3.addMsg(m1);

	cout<<"serliized message is "<<endl;
	string x;
	m3.serialize(x);
	cout<<x<<endl;
	return 1;
}


int test5() {
	Message m;
	string temp ="MESSAGE_BEGIN;NAME=Myname;EXCEPTION_OCCURED=FALSE;EXCEPTION=;DATA_BEGIN;KEY=k1:VALUE=v1:TYPE=t1;KEY=k2:VALUE=v2:TYPE=t2;DATA_END;MESSAGE_ENDMESSAGE_BEGIN;NAME=;EXCEPTION_OCCURED=FALSE;EXCEPTION=;DATA_BEGIN;KEY=k4:VALUE=v4:TYPE=t4;KEY=k5:VALUE=v5:TYPE=t5;KEY=k6:VALUE=v6:TYPE=t6;DATA_END;MESSAGE_ENDMESSAGE_BEGIN;NAME=;EXCEPTION_OCCURED=FALSE;EXCEPTION=;DATA_BEGIN;KEY=k7:VALUE=v7:TYPE=t7;KEY=k8:VALUE=v8:TYPE=t8;KEY=k9:VALUE=v9:TYPE=t9;DATA_END;MESSAGE_END";
	cout<<"Deserialing"<<endl;
	cout<<"deserialize returned  "<<m.deserialize(temp)<<endl;
	string message;
	m.serialize(message);
	cout<<"Again Serialized MESSAGE is \n"<<message<<endl;
	return 1;

}

int main() {
	//test1();
	//test2();
	//test3();
	test4();
	test5();
}
