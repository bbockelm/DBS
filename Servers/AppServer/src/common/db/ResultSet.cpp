#include <iostream.h>
#include "ResultSet.hpp"
using namespace std;

ResultSet::ResultSet(int noOfRows, int noOfCols) {
	this->ResultSet::noOfRows = noOfRows;
	this->ResultSet::noOfCols = noOfCols;
}



ResultSet::ResultSet() {

}
void ResultSet::setNoOfRows(int noOfRows) {
        this->ResultSet::noOfRows = noOfRows;
}

void ResultSet::setNoOfCols(int noOfCols){
        this->ResultSet::noOfCols = noOfCols;
}



ResultSet::~ResultSet(){
}

string ResultSet::getElement(int row, int col) {
	int index = row*noOfCols + col;
	return(index < data.size() ? *(data.begin() + index) : "");
}

void ResultSet::addElement(string value){
	data.push_back(value);
}


void ResultSet::addColName(string value){
	colName.push_back(value);
}
string ResultSet::getColName(int index){
	return(index < colName.size() ? *(colName.begin() + index) : "");
}
void ResultSet::setColName(int index, string value) {
	if( index < colName.size() ) {
		colName[index] = value;
	}
}

int ResultSet::getColIndex(string colNameIn) {
	int colNameInLen = colNameIn.length();
	int index = 0;
	for(ColIterator i = colName.begin(); 
			i != colName.end() ;
			++i ) {
		string name = *i;
		int nameLen = name.length();
		if( colNameIn == name ) {
			return(index);
		}
		if( (colNameInLen > nameLen ) && ( nameLen == 63 ) ) {
			if(colNameIn.substr(0,nameLen) == name) {
				return(index);
			}
		}
		++index;

	}
	return(-1);
}

int ResultSet::getNoOfRows(){
	return(noOfRows);
}

int ResultSet::getNoOfCols(){
	return(noOfCols);
}



/*int main(int argc,char *argv[]) {
	int maxDataLenInEachCol[3];
	for(int j = 0; j < 3; j++) {
		maxDataLenInEachCol[j]=j+5;
	}
	while(true){
	ResultSet* rs = new ResultSet(5,3,maxDataLenInEachCol);
	rs->setElement(0,0,"aaaa");
	rs->setElement(0,1,"bbbb");
	rs->setElement(0,2,"cccc");
	rs->setElement(1,0,"dddd");
	rs->setElement(1,1,"eeee");
	rs->setElement(1,2,"ffffff");
	rs->setElement(2,0,"gggg");
	rs->setElement(2,1,"hhhh");
	rs->setElement(2,2,"iiiii");
	rs->setElement(3,0,"jjjjj");
	rs->setElement(3,1,"kkkk");
	rs->setElement(3,2,"llll");
	rs->setElement(4,0,"mmmm");
	rs->setElement(4,1,"nnnn");
	rs->setElement(4,2,"oooooo");

	for(int i = 0; i< 5; i++) {
		for(int j = 0; j < 3; j++) {
			cout<<"\t["<<i<<","<<j<<"] : "<<rs->getElement(i,j);
		}
		cout<<endl;
	}
	delete rs;
	}

}*/

