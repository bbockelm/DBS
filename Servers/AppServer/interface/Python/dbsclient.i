//dbsclient.i - SWIG interface
	%module dbsclient
	%{
		#include "../../include/common.hpp"
		#include "../../include/DBSClient.hpp"
		#include "../../include/ClientDataStructure.hpp"
		#include "../../include/ClientAPIData.hpp"
	%}
 
 
// Parse the original header file
	%include "../../include/common.hpp"
	%include "../../include/DBSClient.hpp"
	%include "../../include/ClientDataStructure.hpp"
	%include "../../include/ClientAPIData.hpp"
	%include "std_vector.i"
	%include "std_string.i"
	%include "cpointer.i"

	using namespace std ;
	%template(ACHR) VALUEDEF<char>;
	%template(AINT) VALUEDEF<int>;
	%template(ASTR) VALUEDEF<string>;
	%template(AFLT) VALUEDEF<float>;


	%pointer_functions(char, charp);
	%pointer_functions(float, floatp);
	%pointer_functions(int, intp);
	%pointer_functions(string, stringp);

	%template(EVCollVector) std::vector<Evcollview_ClientAPIData*>;
	%template(EVCollFileVector) std::vector<Fileview_ClientAPIData*>;
	%template(PriDSVector) std::vector<Primarydataset_ClientAPIData*>;
	%template(ProPathVector) std::vector<Processingpath_ClientAPIData*>;
        %template(DSProvParentVector) std::vector<Datasetprovenenceevparent_ClientAPIData*>;
        %template(DSProvChildVector) std::vector<Datasetprovenenceevchild_ClientAPIData*>;     
        %template(CrabEvcollVector) std::vector<Crabevcollview_ClientAPIData*>;


	%extend DBSClient {
		static std::string str(char* s){
			std::string mystr(s);
			return mystr;
		}
	};
 
