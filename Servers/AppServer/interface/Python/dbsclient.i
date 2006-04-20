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


        %extend DBSClient {
                static std::string str(char* s){
                        std::string mystr(s);
                        return mystr;
                }
        };

        %template(DatasetPathMultiTableTemplate) TableTemplate<Datasetpathmultirow>;
        %template(DatasetPathMultiTable) MultiTableInterface<Datasetpathmultirow>;
        %template(DatasetPathVector) std::vector<Datasetpathmultirow*>;

        %template(EvCollViewMultiTableTemplate) TableTemplate<Evcollviewmultirow>;
        %template(EvCollViewMultiTable) MultiTableInterface<Evcollviewmultirow>;
        %template(EvCollViewVector) std::vector<Evcollviewmultirow*>;

        %template(EvCollViewNoParentMultiTableTemplate) TableTemplate<Evcollviewnoparentmultirow>;
        %template(EvCollViewNoParentMultiTable) MultiTableInterface<Evcollviewnoparentmultirow>;
        %template(EvCollViewNoParentVector) std::vector<Evcollviewnoparentmultirow*>;

        %template(FileViewMultiTableTemplate) TableTemplate<Fileviewmultirow>;
        %template(FileViewMultiTable) MultiTableInterface<Fileviewmultirow>;
        %template(FileViewVector) std::vector<Fileviewmultirow*>;

        %template(PDBlockViewMultiTableTemplate) TableTemplate<Pdblockviewmultirow>;
        %template(PDBlockViewMultiTable) MultiTableInterface<Pdblockviewmultirow>;
        %template(PDBlockViewVector) std::vector<Pdblockviewmultirow*>;

        %template(BlockViewMultiTableTemplate) TableTemplate<Blockviewmultirow>;
        %template(BlockViewMultiTable) MultiTableInterface<Blockviewmultirow>;
        %template(BlockViewVector) std::vector<Blockviewmultirow*>;

        %template(PrimaryDatasetMultiTableTemplate) TableTemplate<Primarydatasetmultirow>;
        %template(PrimaryDatasetMultiTable) MultiTableInterface<Primarydatasetmultirow>;
        %template(PrimaryDatasetVector) std::vector<Primarydatasetmultirow*>;

        %template(ProcessingPathMultiTableTemplate) TableTemplate<Processingpathmultirow>;
        %template(ProcessingPathMultiTable) MultiTableInterface<Processingpathmultirow>;
        %template(ProcessingPathVector) std::vector<Processingpathmultirow*>;

        %template(CrabEvCollFileViewMultiTableTemplate) TableTemplate<Crabevcollfileviewmultirow>;
        %template(CrabEvCollFileViewMultiTable) MultiTableInterface<Crabevcollfileviewmultirow>;
        %template(CrabEvCollFileViewVector) std::vector<Crabevcollfileviewmultirow*>;

        %template(CrabEvCollViewMultiTableTemplate) TableTemplate<Crabevcollviewmultirow>;
        %template(CrabEvCollViewMultiTable) MultiTableInterface<Crabevcollviewmultirow>;
        %template(CrabEvCollViewVector) std::vector<Crabevcollviewmultirow*>;

        %template(EvCollFileViewMultiTableTemplate) TableTemplate<Evcollfileviewmultirow>;
        %template(EvCollFileViewMultiTable) MultiTableInterface<Evcollfileviewmultirow>;
        %template(EvCollFileViewVector) std::vector<Evcollfileviewmultirow*>;

        %template(EvCollParentageViewMultiTableTemplate) TableTemplate<Evcollparentageviewmultirow>;
        %template(EvCollParentageViewMultiTable) MultiTableInterface<Evcollparentageviewmultirow>;
        %template(EvCollParentageViewVector) std::vector<Evcollparentageviewmultirow*>;

        %template(EvCollStatusViewMultiTableTemplate) TableTemplate<Evcollstatusviewmultirow>;
        %template(EvCollStatusViewMultiTable) MultiTableInterface<Evcollstatusviewmultirow>;
        %template(EvCollStatusViewVector) std::vector<Evcollstatusviewmultirow*>;

        %template(EvCollSingleViewMultiTableTemplate) TableTemplate<Evcollsingleviewmultirow>;
        %template(EvCollSingleViewMultiTable) MultiTableInterface<Evcollsingleviewmultirow>;
        %template(EvCollSingleViewVector) std::vector<Evcollsingleviewmultirow*>;


