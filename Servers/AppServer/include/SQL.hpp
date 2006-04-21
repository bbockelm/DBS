#ifndef _SQL_hpp_included_
#define _SQL_hpp_included_
#include <string>
#include "RowInterface.hpp"
#include "common.hpp"
#include "Util.hpp"
class SQL {
  
public:
	SQL(Util*);
	SQL();
	virtual std::string makeInsertQuery(RowInterface*, std::string, std::string, Dictionary_iter, Dictionary_iter, Keys* primaryKeys);
	virtual std::string makeUpdateQuery(RowInterface*, std::string, std::string, Dictionary_iter, Dictionary_iter, Keys* primaryKeys);
	std::string makeClause(RowInterface*, Keys_iter, Keys_iter,Dictionary_iter, Dictionary_iter);
	std::string makeRefClause(Dictionary_iter, Dictionary_iter);
	std::string makeMultiRefClause(Dictionary_iter, Dictionary_iter);
	std::string makeSelectClause(Dictionary_iter, Dictionary_iter);
	std::string makeTableClause(Keys_iter, Keys_iter);
	std::string makeSeqQuery(std::string, std::string);
	std::string makeSeqQuery(std::string tableName, std::string colName, std::string clause);
protected:
	Util * util;
	std::string formatValue(std::string, std::string);
};

#endif

