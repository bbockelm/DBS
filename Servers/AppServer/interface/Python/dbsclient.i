//dbsclient.i - SWIG interface
	%module dbsclient
	%{
		#include "../../include/common.hpp"
		#include "../../include/DBSClient.hpp"
		#include "../../include/BaseSchemaNConstratints.hpp"
		#include "../../include/RowInterface.hpp"
		#include "../../include/Util.hpp"
		#include "../../include/RowNSchemaBinding.hpp"
		#include "../../include/ObjectLayerTables.hpp"
		#include "../../include/MultiTableInterface.hpp"
		#include "../../include/TableTemplate.hpp"
		#include "../../include/TableInterface.hpp"
	%}

        %import ../../include/ 
 
// Parse the original header file
	%include "../../include/common.hpp"
	%include "../../include/DBSClient.hpp"
	%include "../../include/ObjectLayerTables.hpp"
	%include "../../include/BaseSchemaNConstratints.hpp"
	%include "../../include/RowInterface.hpp"
	%include "../../include/Util.hpp"
	%include "../../include/RowNSchemaBinding.hpp"
	%include "../../include/MultiTableInterface.hpp"
	%include "../../include/TableTemplate.hpp"
	%include "../../include/TableInterface.hpp"
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

	%template(PrimarydatasetMultiTableTemplate) TableTemplate<Primarydatasetmultirow>;
        %template(PrimarydatasetMultiTable) MultiTableInterface<Primarydatasetmultirow>;
	%template(PrimarydatasetVector) std::vector<Primarydatasetmultirow*>;

	%template(ProcessingpathMultiTableTemplate) TableTemplate<Processingpathmultirow>;
        %template(ProcessingpathMultiTable) MultiTableInterface<Processingpathmultirow>;
	%template(ProcessingpathVector) std::vector<Processingpathmultirow*>;

	%template(EvcollviewMultiTableTemplate) TableTemplate<Evcollviewmultirow>;
        %template(EvcollviewMultiTable) MultiTableInterface<Evcollviewmultirow>;
	%template(EvcollviewVector) std::vector<Evcollviewmultirow*>;

	%template(FileviewMultiTableTemplate) TableTemplate<Fileviewmultirow>;
        %template(FileviewMultiTable) MultiTableInterface<Fileviewmultirow>;
	%template(FileVector) std::vector<Fileviewmultirow*>;

	%template(BlockviewMultiTableTemplate) TableTemplate<Blockviewmultirow>;
        %template(BlockviewMultiTable) MultiTableInterface<Blockviewmultirow>;
	%template(BlockviewVector) std::vector<Blockviewmultirow*>;

	%template(PdblockviewMultiTableTemplate) TableTemplate<Pdblockviewmultirow>;
        %template(PdblockviewMultiTable) MultiTableInterface<Pdblockviewmultirow>;
	%template(PdblockviewVector) std::vector<Pdblockviewmultirow*>;

	//%template(DatasetprovenenceevparentMultiTableTemplate) TableTemplate<Datasetprovenenceevparentmultirow>;
        //%template(DatasetprovenenceevparentMultiTable) MultiTableInterface<Datasetprovenenceevparentmultirow>;
	//%template(DatasetprovenenceevparentVector) std::vector<Datasetprovenenceevparentmultirow*>;

	//%template(DatasetprovenenceevchildMultiTableTemplate) TableTemplate<Datasetprovenenceevchildmultirow>;
        //%template(DatasetprovenenceevchildMultiTable) MultiTableInterface<Datasetprovenenceevchildmultirow>;
	//%template(DatasetprovenenceevchildVector) std::vector<Datasetprovenenceevchildmultirow*>;

	%template(CrabevcollviewMultiTableTemplate) TableTemplate<Crabevcollviewmultirow>;
        %template(CrabevcollviewMultiTable) MultiTableInterface<Crabevcollviewmultirow>;
	%template(CrabevcollviewVector) std::vector<Crabevcollviewmultirow*>;



	%extend DBSClient {
		static std::string str(char* s){
			std::string mystr(s);
			return mystr;
		}
	};
 
