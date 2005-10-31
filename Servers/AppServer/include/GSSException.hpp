#ifndef _GSSException_hpp_included_
#define _GSSException_hpp_included_
#include <string>
class GSSException {
public:
	GSSException();
	GSSException(std::string);
	std::string report(void);
private:
	std::string message;

};
#endif
