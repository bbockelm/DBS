#include "BaseSchemaNConstratints.hpp"

BaseSchemaNConstraintsBinding::BaseSchemaNConstraintsBinding() {
};
BaseSchemaNConstraintsBinding::~BaseSchemaNConstraintsBinding() {
};

Dictionary* BaseSchemaNConstraintsBinding::getSchema(void){
  return &(this->Schema);
}

Dictionary* BaseSchemaNConstraintsBinding::getConstraints(void){
  return &(this->Constraints);
}

Dictionary* BaseSchemaNConstraintsBinding::getReferences(void){
  return &(this->References);
}

Dictionary* BaseSchemaNConstraintsBinding::getMultiReferences(void){
  return &(this->MultiReferences);
}

Keys* BaseSchemaNConstraintsBinding::getPrimaryKeys(void){
  return &(this->PrimaryKeys);
}
Keys* BaseSchemaNConstraintsBinding::getForeignKeys(void){
  return &(this->ForeignKeys);
}
ListOfLists* BaseSchemaNConstraintsBinding::getUniqueKeys(void){
  return &(this->UniqueKeys);
}
Keys* BaseSchemaNConstraintsBinding::getNotNullKeys(void) {
  return &(this->NotNullKeys);
}
Keys* BaseSchemaNConstraintsBinding::getSchemaOrder(void) {
  return &(this->SchemaOrder);
}

