//This is a test class to show how to use 
//Vector of multiRow, filled and accessed.

#include "test_multirow_vec.hpp"

vecMultiRow::vecMultiRow(){
   cout << "Constructor called" << endl;
}

vecMultiRow::~vecMultiRow(){
   cout << "Destructor called" << endl;
   for (int i=0; i != AppVec.size(); ++i) {
     delete AppVec[i];
   }
}

void vecMultiRow::addToVec(void){
 for(int i=0; i !=100; ++i){
   ApplicationsMultiRow* tmpApp =  new ApplicationsMultiRow();
   tmpApp->setValue("ApplicationConfiguration.LastModifiedBy", &i);
   AppVec.push_back(tmpApp);
 }
}

void vecMultiRow::readFromVec(void){
  cout << "The size of vector: " << AppVec.size() << endl;
  for (int i=0; i != AppVec.size(); ++i) {
    int* y = (int*)(AppVec[i]->getValue("ApplicationConfiguration.LastModifiedBy"));
    cout << *y << endl;
  }
}

int main(void) {

  cout << "Testing Starts Now.." << endl;
  vecMultiRow* vecMultiRowTest = new vecMultiRow();
  vecMultiRowTest->addToVec();
  vecMultiRowTest->readFromVec();
  delete vecMultiRowTest;
  cout << "Testing Done." << endl;



}
