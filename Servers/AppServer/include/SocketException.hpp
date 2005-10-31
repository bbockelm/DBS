#ifndef _SocketException_hpp_included_
#define _SocketException_hpp_included_
#include <string>
class SocketException {
public:
        SocketException();
        SocketException(std::string);
        std::string report(void);
private:
        std::string message;

};
#endif
