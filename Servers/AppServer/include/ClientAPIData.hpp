#ifndef _ClientTest_hpp_
#define _ClientTest_hpp_
#include "ClientDataStructure.hpp"

class Insertapps_ClientAPIData  : public ClientDataStructure {
public:
    INTEGER  t_application_output_type;
    STRING  t_app_family_name;
    STRING  t_collection_type_name_t_application_output_type;
    STRING  t_app_config_parameter_set;
    INTEGER  t_app_config_application;
    INTEGER  t_collection_type_id_t_application_output_type;
    INTEGER  t_app_config_id;
    INTEGER  t_collection_type_id_t_application_input_type;
    STRING  t_application_app_version;
    STRING  t_app_config_conditions_version;
    INTEGER  t_application_app_family;
    INTEGER  t_application_id;
    INTEGER  t_app_family_id;
    INTEGER  t_application_input_type;
    STRING  t_collection_type_name_t_application_input_type;
    STRING  t_application_executable;
public:
     Insertapps_ClientAPIData();

     virtual int makeMessage(Message& messageOut);
     virtual int readInMessage(Message& messageIn, string lisName, int index);

};
class Person_ClientAPIData  : public ClientDataStructure {
public:
    STRING  t_person_distinguised_name;
    INTEGER  t_person_id;
    STRING  t_person_name;
    STRING  t_person_contactinfo;
public:
     Person_ClientAPIData();

     virtual int makeMessage(Message& messageOut);
     virtual int readInMessage(Message& messageIn, string lisName, int index);

};
class Physicsgroup_ClientAPIData  : public ClientDataStructure {
public:
    INTEGER  t_person_id_t_physics_group_convener;
    STRING  t_person_distinguised_name_t_physics_group_convener;
    STRING  t_person_name_t_physics_group_convener;
    INTEGER  t_physics_group_id;
    STRING  t_person_contactinfo_t_physics_group_convener;
    STRING  t_physics_group_name;
    INTEGER  t_physics_group_convener;
public:
     Physicsgroup_ClientAPIData();

     virtual int makeMessage(Message& messageOut);
     virtual int readInMessage(Message& messageIn, string lisName, int index);

};
class Evcollview_ClientAPIData  : public ClientDataStructure {
public:
    INTEGER  t_info_evcoll_validation_status;
    STRING  t_info_evcoll_name;
    INTEGER  t_info_evcoll_status;
    STRING  t_evcoll_status_name;
    INTEGER  t_info_evcoll_events;
    INTEGER  t_event_collection_id;
    CHARACTER  t_event_collection_is_primary;
    STRING  t_validation_status_name;
    INTEGER  t_event_collection_processed_dataset;
    INTEGER  t_evcoll_status_id;
    INTEGER  t_info_evcoll_event_collection;
    INTEGER  t_validation_status_id;
    INTEGER  t_event_collection_collection_index;
    STRING  t_info_evcoll_estimated_luminosity;
public:
     Evcollview_ClientAPIData();

     virtual int makeMessage(Message& messageOut);
     virtual int readInMessage(Message& messageIn, string lisName, int index);

};
class Fileview_ClientAPIData  : public ClientDataStructure {
public:
    INTEGER  t_evcoll_file_fileid;
    INTEGER  t_file_type;
    INTEGER  t_file_type_id;
    INTEGER  t_block_processed_dataset;
    INTEGER  t_file_inblock;
    STRING  t_file_status_name;
    INTEGER  t_block_status;
    STRING  t_file_guid;
    STRING  t_file_logical_name;
    INTEGER  t_file_status_id;
    INTEGER  t_file_id;
    INTEGER  t_block_files;
    INTEGER  t_evcoll_file_evcoll;
    STRING  t_file_type_name;
    INTEGER  t_block_id;
    STRING  t_file_filesize;
    STRING  t_block_status_name;
    INTEGER  t_block_bytes;
    INTEGER  t_file_status;
    INTEGER  t_block_status_id;
    INTEGER  t_evcoll_file_id;
public:
     Fileview_ClientAPIData();

     virtual int makeMessage(Message& messageOut);
     virtual int readInMessage(Message& messageIn, string lisName, int index);

};
class Primarydataset_ClientAPIData  : public ClientDataStructure {
public:
    INTEGER  t_desc_mc_id;
    STRING  t_desc_mc_description;
    INTEGER  t_primary_dataset_description;
    STRING  t_primary_dataset_name;
    STRING  t_desc_mc_decay_chain;
    INTEGER  t_physics_group_id;
    INTEGER  t_desc_primary_id;
    INTEGER  t_desc_primary_trigger_path;
    STRING  t_desc_trigger_description;
    INTEGER  t_primary_dataset_physics_group;
    INTEGER  t_desc_trigger_id;
    STRING  t_desc_mc_production;
    STRING  t_physics_group_name;
    INTEGER  t_desc_primary_mc_channel;
    INTEGER  t_primary_dataset_id;
    CHARACTER  t_desc_primary_is_mc_data;
    INTEGER  t_physics_group_convener;
public:
     Primarydataset_ClientAPIData();

     virtual int makeMessage(Message& messageOut);
     virtual int readInMessage(Message& messageIn, string lisName, int index);

};
class Processingpath_ClientAPIData  : public ClientDataStructure {
public:
    STRING  t_processed_dataset_name;
    STRING  t_collection_type_name_t_application_output_type;
    STRING  t_application_app_version;
    STRING  t_app_config_conditions_version;
    INTEGER  t_application_id;
    INTEGER  t_application_output_type;
    INTEGER  t_primary_dataset_description;
    INTEGER  t_data_tier_id;
    INTEGER  t_processing_path_parent;
    INTEGER  t_app_config_id;
    INTEGER  t_collection_type_id_t_application_input_type;
    INTEGER  t_processed_dataset_processing_path;
    INTEGER  t_processing_path_data_tier;
    INTEGER  t_application_app_family;
    INTEGER  t_primary_dataset_id;
    STRING  t_data_tier_name;
    STRING  t_app_family_name;
    STRING  t_app_config_parameter_set;
    CHARACTER  t_processed_dataset_is_open;
    INTEGER  t_processing_path_app_config;
    INTEGER  t_app_config_application;
    STRING  t_application_executable;
    STRING  t_collection_type_name_t_application_input_type;
    INTEGER  t_primary_dataset_physics_group;
    STRING  t_processing_path_full_path;
    INTEGER  t_processing_path_id;
    INTEGER  t_application_input_type;
    STRING  t_primary_dataset_name;
    INTEGER  t_processed_dataset_primary_dataset;
    INTEGER  t_processed_dataset_id;
    INTEGER  t_collection_type_id_t_application_output_type;
    INTEGER  t_app_family_id;
public:
     Processingpath_ClientAPIData();

     virtual int makeMessage(Message& messageOut);
     virtual int readInMessage(Message& messageIn, string lisName, int index);

};
class Analysisdataset_ClientAPIData  : public ClientDataStructure {
public:
    STRING  t_dataset_status_name;
    INTEGER  t_info_anads_analysis_dataset;
    STRING  t_validation_status_name;
    CHARACTER  t_anads_data_is_primary;
    INTEGER  t_anads_data_event_collection;
    STRING  t_info_anads_estimated_luminiosity;
    STRING  t_analysis_dataset_name;
    INTEGER  t_info_anads_validation_status;
    INTEGER  t_anads_data_id;
    INTEGER  t_dataset_status_id;
    INTEGER  t_anads_data_analysis_dataset;
    INTEGER  t_info_anads_status;
    INTEGER  t_analysis_dataset_id;
    INTEGER  t_validation_status_id;
    INTEGER  t_analysis_dataset_processed_dataset;
    INTEGER  t_info_anads_events;
public:
     Analysisdataset_ClientAPIData();

     virtual int makeMessage(Message& messageOut);
     virtual int readInMessage(Message& messageIn, string lisName, int index);

};
class Datasetprovenenceevchild_ClientAPIData  : public ClientDataStructure {
public:
    STRING  t_processed_dataset_name;
    INTEGER  t_event_collection_id;
    INTEGER  t_processing_path_id;
    CHARACTER  t_event_collection_is_primary;
    INTEGER  t_event_collection_processed_dataset;
    INTEGER  t_primary_dataset_description;
    INTEGER  t_data_tier_id;
    INTEGER  t_processing_path_parent;
    INTEGER  t_evcoll_parentage_type;
    INTEGER  t_processed_dataset_processing_path;
    STRING  t_parentage_type_name;
    INTEGER  t_processing_path_data_tier;
    INTEGER  t_primary_dataset_id;
    INTEGER  t_event_collection_collection_index;
    STRING  t_data_tier_name;
    INTEGER  t_parentage_type_id;
    CHARACTER  t_processed_dataset_is_open;
    INTEGER  t_processing_path_app_config;
    INTEGER  t_primary_dataset_physics_group;
    STRING  t_processing_path_full_path;
    STRING  t_primary_dataset_name;
    INTEGER  t_evcoll_parentage_parent;
    INTEGER  t_processed_dataset_primary_dataset;
    INTEGER  t_processed_dataset_id;
    INTEGER  t_evcoll_parentage_child;
public:
     Datasetprovenenceevchild_ClientAPIData();

     virtual int makeMessage(Message& messageOut);
     virtual int readInMessage(Message& messageIn, string lisName, int index);

};
class Datasetprovenenceevparent_ClientAPIData  : public ClientDataStructure {
public:
    STRING  t_processed_dataset_name;
    INTEGER  t_event_collection_id;
    INTEGER  t_processing_path_id;
    CHARACTER  t_event_collection_is_primary;
    INTEGER  t_event_collection_processed_dataset;
    INTEGER  t_primary_dataset_description;
    INTEGER  t_data_tier_id;
    INTEGER  t_processing_path_parent;
    INTEGER  t_evcoll_parentage_type;
    INTEGER  t_processed_dataset_processing_path;
    STRING  t_parentage_type_name;
    INTEGER  t_processing_path_data_tier;
    INTEGER  t_primary_dataset_id;
    INTEGER  t_event_collection_collection_index;
    STRING  t_data_tier_name;
    INTEGER  t_parentage_type_id;
    CHARACTER  t_processed_dataset_is_open;
    INTEGER  t_processing_path_app_config;
    INTEGER  t_primary_dataset_physics_group;
    STRING  t_processing_path_full_path;
    STRING  t_primary_dataset_name;
    INTEGER  t_evcoll_parentage_parent;
    INTEGER  t_processed_dataset_primary_dataset;
    INTEGER  t_processed_dataset_id;
    INTEGER  t_evcoll_parentage_child;
public:
     Datasetprovenenceevparent_ClientAPIData();

     virtual int makeMessage(Message& messageOut);
     virtual int readInMessage(Message& messageIn, string lisName, int index);

};
class Crabevcollview_ClientAPIData  : public ClientDataStructure {
public:
    INTEGER  t_info_evcoll_validation_status;
    STRING  t_processed_dataset_name;
    STRING  t_info_evcoll_name;
    INTEGER  t_block_status;
    INTEGER  t_primary_dataset_physics_group;
    INTEGER  t_processing_path_id;
    CHARACTER  t_event_collection_is_primary;
    INTEGER  t_event_collection_processed_dataset;
    STRING  t_info_evcoll_estimated_luminosity;
    INTEGER  t_primary_dataset_description;
    INTEGER  t_block_bytes;
    INTEGER  t_data_tier_id;
    INTEGER  t_processing_path_parent;
    INTEGER  t_processed_dataset_processing_path;
    INTEGER  t_block_files;
    INTEGER  t_processing_path_data_tier;
    INTEGER  t_primary_dataset_id;
    INTEGER  t_event_collection_collection_index;
    STRING  t_data_tier_name;
    INTEGER  t_block_processed_dataset;
    INTEGER  t_info_evcoll_events;
    CHARACTER  t_processed_dataset_is_open;
    INTEGER  t_processing_path_app_config;
    INTEGER  t_block_id;
    INTEGER  t_event_collection_id;
    STRING  t_processing_path_full_path;
    STRING  t_primary_dataset_name;
    INTEGER  t_info_evcoll_status;
    INTEGER  t_processed_dataset_primary_dataset;
    INTEGER  t_info_evcoll_event_collection;
    INTEGER  t_processed_dataset_id;
public:
     Crabevcollview_ClientAPIData();

     virtual int makeMessage(Message& messageOut);
     virtual int readInMessage(Message& messageIn, string lisName, int index);

};
#endif
