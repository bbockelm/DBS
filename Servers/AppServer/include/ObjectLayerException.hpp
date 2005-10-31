#ifndef _ObjectLayerExcepption_hpp_included_
#define _ObjectLayerExcepption_hpp_included_
#include <string>
//File containing Exception(s) for Object layer.
class ObjectLayerException {
public:
        ObjectLayerException();
        ObjectLayerException(std::string);
        std::string report(void);
private:
        std::string message;

};
#endif
