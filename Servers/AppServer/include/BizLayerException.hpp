#ifndef _BizLayerExcepption_hpp_included_
#define _BizLayerExcepption_hpp_included_
#include <string>
//File containing Exception(s) for Biz layer.
class BizLayerException {
public:
        BizLayerException();
        BizLayerException(std::string);
        std::string report(void);
private:
        std::string message;

};
#endif
