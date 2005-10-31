#ifndef _THIS_IS_TEST_
#define _THIS_IS_TEST_

#include<iostream>
#include<vector>
#include "ObjectLayerTables.hpp"


class vecMultiRow{

public:
   vecMultiRow();
   ~vecMultiRow();
   void addToVec(void);
   void readFromVec(void);

private:
  vector<ApplicationsMultiRow*> AppVec;

};

#endif
