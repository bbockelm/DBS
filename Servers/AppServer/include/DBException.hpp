#ifndef _DBException_hpp_included_
#define _DBException_hpp_included_
#include <string>
class DBException {
public:
        DBException();
        DBException(std::string);
        std::string report(void);
private:
        std::string message;

};
#endif
