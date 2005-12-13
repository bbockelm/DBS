
#include "ClientAPIData.hpp"

Insertapps_ClientAPIData::Insertapps_ClientAPIData(){

    Schema.insert(Entry("t_application.output_type", "INTEGER"));
    Schema.insert(Entry("t_app_family.name", "STRING"));
    Schema.insert(Entry("t_collection_type.name.t_application.output_type", "STRING"));
    Schema.insert(Entry("t_app_config.parameter_set", "STRING"));
    Schema.insert(Entry("t_app_config.application", "INTEGER"));
    Schema.insert(Entry("t_collection_type.id.t_application.output_type", "INTEGER"));
    Schema.insert(Entry("t_app_config.id", "INTEGER"));
    Schema.insert(Entry("t_collection_type.id.t_application.input_type", "INTEGER"));
    Schema.insert(Entry("t_application.app_version", "STRING"));
    Schema.insert(Entry("t_app_config.conditions_version", "STRING"));
    Schema.insert(Entry("t_application.app_family", "INTEGER"));
    Schema.insert(Entry("t_application.id", "INTEGER"));
    Schema.insert(Entry("t_app_family.id", "INTEGER"));
    Schema.insert(Entry("t_application.input_type", "INTEGER"));
    Schema.insert(Entry("t_collection_type.name.t_application.input_type", "STRING"));
    Schema.insert(Entry("t_application.executable", "STRING"));

}

int Insertapps_ClientAPIData::makeMessage(Message& messageOut) {
       if ( (int*)(&(t_application_output_type.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_application.output_type", (string)(util.itoa(t_application_output_type.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_app_family_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_app_family.name", (string)t_app_family_name.getValue(), (string)"STRING"));
      }
       if ( (string*)(&(t_collection_type_name_t_application_output_type.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_collection_type.name.t_application.output_type", (string)t_collection_type_name_t_application_output_type.getValue(), (string)"STRING"));
      }
       if ( (string*)(&(t_app_config_parameter_set.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_app_config.parameter_set", (string)t_app_config_parameter_set.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_app_config_application.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_app_config.application", (string)(util.itoa(t_app_config_application.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_collection_type_id_t_application_output_type.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_collection_type.id.t_application.output_type", (string)(util.itoa(t_collection_type_id_t_application_output_type.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_app_config_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_app_config.id", (string)(util.itoa(t_app_config_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_collection_type_id_t_application_input_type.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_collection_type.id.t_application.input_type", (string)(util.itoa(t_collection_type_id_t_application_input_type.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_application_app_version.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_application.app_version", (string)t_application_app_version.getValue(), (string)"STRING"));
      }
       if ( (string*)(&(t_app_config_conditions_version.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_app_config.conditions_version", (string)t_app_config_conditions_version.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_application_app_family.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_application.app_family", (string)(util.itoa(t_application_app_family.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_application_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_application.id", (string)(util.itoa(t_application_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_app_family_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_app_family.id", (string)(util.itoa(t_app_family_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_application_input_type.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_application.input_type", (string)(util.itoa(t_application_input_type.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_collection_type_name_t_application_input_type.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_collection_type.name.t_application.input_type", (string)t_collection_type_name_t_application_input_type.getValue(), (string)"STRING"));
      }
       if ( (string*)(&(t_application_executable.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_application.executable", (string)t_application_executable.getValue(), (string)"STRING"));
      }

    return 1;

}

int Insertapps_ClientAPIData::readInMessage(Message& messageIn, string lisName, int index) {

    string value;
    value = messageIn.getElementValue("t_application.output_type", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_application_output_type =  intValue;
    }
    value = messageIn.getElementValue("t_app_family.name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_app_family_name = strValue;
    }
    value = messageIn.getElementValue("t_collection_type.name.t_application.output_type", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_collection_type_name_t_application_output_type = strValue;
    }
    value = messageIn.getElementValue("t_app_config.parameter_set", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_app_config_parameter_set = strValue;
    }
    value = messageIn.getElementValue("t_app_config.application", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_app_config_application =  intValue;
    }
    value = messageIn.getElementValue("t_collection_type.id.t_application.output_type", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_collection_type_id_t_application_output_type =  intValue;
    }
    value = messageIn.getElementValue("t_app_config.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_app_config_id =  intValue;
    }
    value = messageIn.getElementValue("t_collection_type.id.t_application.input_type", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_collection_type_id_t_application_input_type =  intValue;
    }
    value = messageIn.getElementValue("t_application.app_version", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_application_app_version = strValue;
    }
    value = messageIn.getElementValue("t_app_config.conditions_version", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_app_config_conditions_version = strValue;
    }
    value = messageIn.getElementValue("t_application.app_family", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_application_app_family =  intValue;
    }
    value = messageIn.getElementValue("t_application.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_application_id =  intValue;
    }
    value = messageIn.getElementValue("t_app_family.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_app_family_id =  intValue;
    }
    value = messageIn.getElementValue("t_application.input_type", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_application_input_type =  intValue;
    }
    value = messageIn.getElementValue("t_collection_type.name.t_application.input_type", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_collection_type_name_t_application_input_type = strValue;
    }
    value = messageIn.getElementValue("t_application.executable", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_application_executable = strValue;
    }

}

Person_ClientAPIData::Person_ClientAPIData(){

    Schema.insert(Entry("t_person.distinguised_name", "STRING"));
    Schema.insert(Entry("t_person.id", "INTEGER"));
    Schema.insert(Entry("t_person.name", "STRING"));
    Schema.insert(Entry("t_person.contactinfo", "STRING"));

}

int Person_ClientAPIData::makeMessage(Message& messageOut) {
       if ( (string*)(&(t_person_distinguised_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_person.distinguised_name", (string)t_person_distinguised_name.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_person_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_person.id", (string)(util.itoa(t_person_id.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_person_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_person.name", (string)t_person_name.getValue(), (string)"STRING"));
      }
       if ( (string*)(&(t_person_contactinfo.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_person.contactinfo", (string)t_person_contactinfo.getValue(), (string)"STRING"));
      }

    return 1;

}

int Person_ClientAPIData::readInMessage(Message& messageIn, string lisName, int index) {

    string value;
    value = messageIn.getElementValue("t_person.distinguised_name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_person_distinguised_name = strValue;
    }
    value = messageIn.getElementValue("t_person.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_person_id =  intValue;
    }
    value = messageIn.getElementValue("t_person.name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_person_name = strValue;
    }
    value = messageIn.getElementValue("t_person.contactinfo", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_person_contactinfo = strValue;
    }

}

Physicsgroup_ClientAPIData::Physicsgroup_ClientAPIData(){

    Schema.insert(Entry("t_person.id.t_physics_group.convener", "INTEGER"));
    Schema.insert(Entry("t_person.distinguised_name.t_physics_group.convener", "STRING"));
    Schema.insert(Entry("t_person.name.t_physics_group.convener", "STRING"));
    Schema.insert(Entry("t_physics_group.id", "INTEGER"));
    Schema.insert(Entry("t_person.contactinfo.t_physics_group.convener", "STRING"));
    Schema.insert(Entry("t_physics_group.name", "STRING"));
    Schema.insert(Entry("t_physics_group.convener", "INTEGER"));

}

int Physicsgroup_ClientAPIData::makeMessage(Message& messageOut) {
       if ( (int*)(&(t_person_id_t_physics_group_convener.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_person.id.t_physics_group.convener", (string)(util.itoa(t_person_id_t_physics_group_convener.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_person_distinguised_name_t_physics_group_convener.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_person.distinguised_name.t_physics_group.convener", (string)t_person_distinguised_name_t_physics_group_convener.getValue(), (string)"STRING"));
      }
       if ( (string*)(&(t_person_name_t_physics_group_convener.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_person.name.t_physics_group.convener", (string)t_person_name_t_physics_group_convener.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_physics_group_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_physics_group.id", (string)(util.itoa(t_physics_group_id.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_person_contactinfo_t_physics_group_convener.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_person.contactinfo.t_physics_group.convener", (string)t_person_contactinfo_t_physics_group_convener.getValue(), (string)"STRING"));
      }
       if ( (string*)(&(t_physics_group_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_physics_group.name", (string)t_physics_group_name.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_physics_group_convener.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_physics_group.convener", (string)(util.itoa(t_physics_group_convener.getValue())), (string)"INTEGER"));
      }

    return 1;

}

int Physicsgroup_ClientAPIData::readInMessage(Message& messageIn, string lisName, int index) {

    string value;
    value = messageIn.getElementValue("t_person.id.t_physics_group.convener", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_person_id_t_physics_group_convener =  intValue;
    }
    value = messageIn.getElementValue("t_person.distinguised_name.t_physics_group.convener", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_person_distinguised_name_t_physics_group_convener = strValue;
    }
    value = messageIn.getElementValue("t_person.name.t_physics_group.convener", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_person_name_t_physics_group_convener = strValue;
    }
    value = messageIn.getElementValue("t_physics_group.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_physics_group_id =  intValue;
    }
    value = messageIn.getElementValue("t_person.contactinfo.t_physics_group.convener", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_person_contactinfo_t_physics_group_convener = strValue;
    }
    value = messageIn.getElementValue("t_physics_group.name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_physics_group_name = strValue;
    }
    value = messageIn.getElementValue("t_physics_group.convener", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_physics_group_convener =  intValue;
    }

}

Evcollview_ClientAPIData::Evcollview_ClientAPIData(){

    Schema.insert(Entry("t_info_evcoll.validation_status", "INTEGER"));
    Schema.insert(Entry("t_info_evcoll.name", "STRING"));
    Schema.insert(Entry("t_info_evcoll.status", "INTEGER"));
    Schema.insert(Entry("t_evcoll_status.name", "STRING"));
    Schema.insert(Entry("t_info_evcoll.events", "INTEGER"));
    Schema.insert(Entry("t_event_collection.id", "INTEGER"));
    Schema.insert(Entry("t_event_collection.is_primary", "CHARACTER"));
    Schema.insert(Entry("t_validation_status.name", "STRING"));
    Schema.insert(Entry("t_event_collection.processed_dataset", "INTEGER"));
    Schema.insert(Entry("t_evcoll_status.id", "INTEGER"));
    Schema.insert(Entry("t_info_evcoll.event_collection", "INTEGER"));
    Schema.insert(Entry("t_validation_status.id", "INTEGER"));
    Schema.insert(Entry("t_event_collection.collection_index", "INTEGER"));
    Schema.insert(Entry("t_info_evcoll.estimated_luminosity", "STRING"));

}

int Evcollview_ClientAPIData::makeMessage(Message& messageOut) {
       if ( (int*)(&(t_info_evcoll_validation_status.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_info_evcoll.validation_status", (string)(util.itoa(t_info_evcoll_validation_status.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_info_evcoll_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_info_evcoll.name", (string)t_info_evcoll_name.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_info_evcoll_status.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_info_evcoll.status", (string)(util.itoa(t_info_evcoll_status.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_evcoll_status_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_evcoll_status.name", (string)t_evcoll_status_name.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_info_evcoll_events.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_info_evcoll.events", (string)(util.itoa(t_info_evcoll_events.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_event_collection_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_event_collection.id", (string)(util.itoa(t_event_collection_id.getValue())), (string)"INTEGER"));
      }
       if ( (char*)(&(t_event_collection_is_primary.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_event_collection.is_primary", (string)(util.ctoa(t_event_collection_is_primary.getValue())), (string)"CHARACTER"));
      }
       if ( (string*)(&(t_validation_status_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_validation_status.name", (string)t_validation_status_name.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_event_collection_processed_dataset.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_event_collection.processed_dataset", (string)(util.itoa(t_event_collection_processed_dataset.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_evcoll_status_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_evcoll_status.id", (string)(util.itoa(t_evcoll_status_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_info_evcoll_event_collection.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_info_evcoll.event_collection", (string)(util.itoa(t_info_evcoll_event_collection.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_validation_status_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_validation_status.id", (string)(util.itoa(t_validation_status_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_event_collection_collection_index.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_event_collection.collection_index", (string)(util.itoa(t_event_collection_collection_index.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_info_evcoll_estimated_luminosity.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_info_evcoll.estimated_luminosity", (string)t_info_evcoll_estimated_luminosity.getValue(), (string)"STRING"));
      }

    return 1;

}

int Evcollview_ClientAPIData::readInMessage(Message& messageIn, string lisName, int index) {

    string value;
    value = messageIn.getElementValue("t_info_evcoll.validation_status", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_info_evcoll_validation_status =  intValue;
    }
    value = messageIn.getElementValue("t_info_evcoll.name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_info_evcoll_name = strValue;
    }
    value = messageIn.getElementValue("t_info_evcoll.status", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_info_evcoll_status =  intValue;
    }
    value = messageIn.getElementValue("t_evcoll_status.name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_evcoll_status_name = strValue;
    }
    value = messageIn.getElementValue("t_info_evcoll.events", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_info_evcoll_events =  intValue;
    }
    value = messageIn.getElementValue("t_event_collection.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_event_collection_id =  intValue;
    }
    value = messageIn.getElementValue("t_event_collection.is_primary", lisName, index);
    if ( value != "NOTFOUND" ) {
        char charValue = *(value.c_str());
        t_event_collection_is_primary =  charValue;
    }
    value = messageIn.getElementValue("t_validation_status.name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_validation_status_name = strValue;
    }
    value = messageIn.getElementValue("t_event_collection.processed_dataset", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_event_collection_processed_dataset =  intValue;
    }
    value = messageIn.getElementValue("t_evcoll_status.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_evcoll_status_id =  intValue;
    }
    value = messageIn.getElementValue("t_info_evcoll.event_collection", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_info_evcoll_event_collection =  intValue;
    }
    value = messageIn.getElementValue("t_validation_status.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_validation_status_id =  intValue;
    }
    value = messageIn.getElementValue("t_event_collection.collection_index", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_event_collection_collection_index =  intValue;
    }
    value = messageIn.getElementValue("t_info_evcoll.estimated_luminosity", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_info_evcoll_estimated_luminosity = strValue;
    }

}

Fileview_ClientAPIData::Fileview_ClientAPIData(){

    Schema.insert(Entry("t_evcoll_file.fileid", "INTEGER"));
    Schema.insert(Entry("t_file.type", "INTEGER"));
    Schema.insert(Entry("t_file_type.id", "INTEGER"));
    Schema.insert(Entry("t_block.processed_dataset", "INTEGER"));
    Schema.insert(Entry("t_file.inblock", "INTEGER"));
    Schema.insert(Entry("t_file_status.name", "STRING"));
    Schema.insert(Entry("t_block.status", "INTEGER"));
    Schema.insert(Entry("t_file.guid", "STRING"));
    Schema.insert(Entry("t_file.logical_name", "STRING"));
    Schema.insert(Entry("t_file_status.id", "INTEGER"));
    Schema.insert(Entry("t_file.id", "INTEGER"));
    Schema.insert(Entry("t_block.files", "INTEGER"));
    Schema.insert(Entry("t_evcoll_file.evcoll", "INTEGER"));
    Schema.insert(Entry("t_file_type.name", "STRING"));
    Schema.insert(Entry("t_block.id", "INTEGER"));
    Schema.insert(Entry("t_file.filesize", "STRING"));
    Schema.insert(Entry("t_block_status.name", "STRING"));
    Schema.insert(Entry("t_block.bytes", "INTEGER"));
    Schema.insert(Entry("t_file.status", "INTEGER"));
    Schema.insert(Entry("t_block_status.id", "INTEGER"));
    Schema.insert(Entry("t_evcoll_file.id", "INTEGER"));

}

int Fileview_ClientAPIData::makeMessage(Message& messageOut) {
       if ( (int*)(&(t_evcoll_file_fileid.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_evcoll_file.fileid", (string)(util.itoa(t_evcoll_file_fileid.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_file_type.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_file.type", (string)(util.itoa(t_file_type.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_file_type_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_file_type.id", (string)(util.itoa(t_file_type_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_block_processed_dataset.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_block.processed_dataset", (string)(util.itoa(t_block_processed_dataset.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_file_inblock.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_file.inblock", (string)(util.itoa(t_file_inblock.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_file_status_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_file_status.name", (string)t_file_status_name.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_block_status.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_block.status", (string)(util.itoa(t_block_status.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_file_guid.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_file.guid", (string)t_file_guid.getValue(), (string)"STRING"));
      }
       if ( (string*)(&(t_file_logical_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_file.logical_name", (string)t_file_logical_name.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_file_status_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_file_status.id", (string)(util.itoa(t_file_status_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_file_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_file.id", (string)(util.itoa(t_file_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_block_files.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_block.files", (string)(util.itoa(t_block_files.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_evcoll_file_evcoll.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_evcoll_file.evcoll", (string)(util.itoa(t_evcoll_file_evcoll.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_file_type_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_file_type.name", (string)t_file_type_name.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_block_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_block.id", (string)(util.itoa(t_block_id.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_file_filesize.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_file.filesize", (string)t_file_filesize.getValue(), (string)"STRING"));
      }
       if ( (string*)(&(t_block_status_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_block_status.name", (string)t_block_status_name.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_block_bytes.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_block.bytes", (string)(util.itoa(t_block_bytes.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_file_status.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_file.status", (string)(util.itoa(t_file_status.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_block_status_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_block_status.id", (string)(util.itoa(t_block_status_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_evcoll_file_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_evcoll_file.id", (string)(util.itoa(t_evcoll_file_id.getValue())), (string)"INTEGER"));
      }

    return 1;

}

int Fileview_ClientAPIData::readInMessage(Message& messageIn, string lisName, int index) {

    string value;
    value = messageIn.getElementValue("t_evcoll_file.fileid", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_evcoll_file_fileid =  intValue;
    }
    value = messageIn.getElementValue("t_file.type", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_file_type =  intValue;
    }
    value = messageIn.getElementValue("t_file_type.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_file_type_id =  intValue;
    }
    value = messageIn.getElementValue("t_block.processed_dataset", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_block_processed_dataset =  intValue;
    }
    value = messageIn.getElementValue("t_file.inblock", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_file_inblock =  intValue;
    }
    value = messageIn.getElementValue("t_file_status.name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_file_status_name = strValue;
    }
    value = messageIn.getElementValue("t_block.status", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_block_status =  intValue;
    }
    value = messageIn.getElementValue("t_file.guid", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_file_guid = strValue;
    }
    value = messageIn.getElementValue("t_file.logical_name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_file_logical_name = strValue;
    }
    value = messageIn.getElementValue("t_file_status.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_file_status_id =  intValue;
    }
    value = messageIn.getElementValue("t_file.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_file_id =  intValue;
    }
    value = messageIn.getElementValue("t_block.files", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_block_files =  intValue;
    }
    value = messageIn.getElementValue("t_evcoll_file.evcoll", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_evcoll_file_evcoll =  intValue;
    }
    value = messageIn.getElementValue("t_file_type.name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_file_type_name = strValue;
    }
    value = messageIn.getElementValue("t_block.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_block_id =  intValue;
    }
    value = messageIn.getElementValue("t_file.filesize", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_file_filesize = strValue;
    }
    value = messageIn.getElementValue("t_block_status.name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_block_status_name = strValue;
    }
    value = messageIn.getElementValue("t_block.bytes", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_block_bytes =  intValue;
    }
    value = messageIn.getElementValue("t_file.status", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_file_status =  intValue;
    }
    value = messageIn.getElementValue("t_block_status.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_block_status_id =  intValue;
    }
    value = messageIn.getElementValue("t_evcoll_file.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_evcoll_file_id =  intValue;
    }

}

Primarydataset_ClientAPIData::Primarydataset_ClientAPIData(){

    Schema.insert(Entry("t_desc_mc.id", "INTEGER"));
    Schema.insert(Entry("t_desc_mc.description", "STRING"));
    Schema.insert(Entry("t_primary_dataset.description", "INTEGER"));
    Schema.insert(Entry("t_primary_dataset.name", "STRING"));
    Schema.insert(Entry("t_desc_mc.decay_chain", "STRING"));
    Schema.insert(Entry("t_physics_group.id", "INTEGER"));
    Schema.insert(Entry("t_desc_primary.id", "INTEGER"));
    Schema.insert(Entry("t_desc_primary.trigger_path", "INTEGER"));
    Schema.insert(Entry("t_desc_trigger.description", "STRING"));
    Schema.insert(Entry("t_primary_dataset.physics_group", "INTEGER"));
    Schema.insert(Entry("t_desc_trigger.id", "INTEGER"));
    Schema.insert(Entry("t_desc_mc.production", "STRING"));
    Schema.insert(Entry("t_physics_group.name", "STRING"));
    Schema.insert(Entry("t_desc_primary.mc_channel", "INTEGER"));
    Schema.insert(Entry("t_primary_dataset.id", "INTEGER"));
    Schema.insert(Entry("t_desc_primary.is_mc_data", "CHARACTER"));
    Schema.insert(Entry("t_physics_group.convener", "INTEGER"));

}

int Primarydataset_ClientAPIData::makeMessage(Message& messageOut) {
       if ( (int*)(&(t_desc_mc_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_desc_mc.id", (string)(util.itoa(t_desc_mc_id.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_desc_mc_description.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_desc_mc.description", (string)t_desc_mc_description.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_primary_dataset_description.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_primary_dataset.description", (string)(util.itoa(t_primary_dataset_description.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_primary_dataset_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_primary_dataset.name", (string)t_primary_dataset_name.getValue(), (string)"STRING"));
      }
       if ( (string*)(&(t_desc_mc_decay_chain.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_desc_mc.decay_chain", (string)t_desc_mc_decay_chain.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_physics_group_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_physics_group.id", (string)(util.itoa(t_physics_group_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_desc_primary_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_desc_primary.id", (string)(util.itoa(t_desc_primary_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_desc_primary_trigger_path.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_desc_primary.trigger_path", (string)(util.itoa(t_desc_primary_trigger_path.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_desc_trigger_description.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_desc_trigger.description", (string)t_desc_trigger_description.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_primary_dataset_physics_group.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_primary_dataset.physics_group", (string)(util.itoa(t_primary_dataset_physics_group.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_desc_trigger_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_desc_trigger.id", (string)(util.itoa(t_desc_trigger_id.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_desc_mc_production.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_desc_mc.production", (string)t_desc_mc_production.getValue(), (string)"STRING"));
      }
       if ( (string*)(&(t_physics_group_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_physics_group.name", (string)t_physics_group_name.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_desc_primary_mc_channel.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_desc_primary.mc_channel", (string)(util.itoa(t_desc_primary_mc_channel.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_primary_dataset_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_primary_dataset.id", (string)(util.itoa(t_primary_dataset_id.getValue())), (string)"INTEGER"));
      }
       if ( (char*)(&(t_desc_primary_is_mc_data.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_desc_primary.is_mc_data", (string)(util.ctoa(t_desc_primary_is_mc_data.getValue())), (string)"CHARACTER"));
      }
       if ( (int*)(&(t_physics_group_convener.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_physics_group.convener", (string)(util.itoa(t_physics_group_convener.getValue())), (string)"INTEGER"));
      }

    return 1;

}

int Primarydataset_ClientAPIData::readInMessage(Message& messageIn, string lisName, int index) {

    string value;
    value = messageIn.getElementValue("t_desc_mc.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_desc_mc_id =  intValue;
    }
    value = messageIn.getElementValue("t_desc_mc.description", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_desc_mc_description = strValue;
    }
    value = messageIn.getElementValue("t_primary_dataset.description", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_primary_dataset_description =  intValue;
    }
    value = messageIn.getElementValue("t_primary_dataset.name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_primary_dataset_name = strValue;
    }
    value = messageIn.getElementValue("t_desc_mc.decay_chain", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_desc_mc_decay_chain = strValue;
    }
    value = messageIn.getElementValue("t_physics_group.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_physics_group_id =  intValue;
    }
    value = messageIn.getElementValue("t_desc_primary.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_desc_primary_id =  intValue;
    }
    value = messageIn.getElementValue("t_desc_primary.trigger_path", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_desc_primary_trigger_path =  intValue;
    }
    value = messageIn.getElementValue("t_desc_trigger.description", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_desc_trigger_description = strValue;
    }
    value = messageIn.getElementValue("t_primary_dataset.physics_group", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_primary_dataset_physics_group =  intValue;
    }
    value = messageIn.getElementValue("t_desc_trigger.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_desc_trigger_id =  intValue;
    }
    value = messageIn.getElementValue("t_desc_mc.production", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_desc_mc_production = strValue;
    }
    value = messageIn.getElementValue("t_physics_group.name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_physics_group_name = strValue;
    }
    value = messageIn.getElementValue("t_desc_primary.mc_channel", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_desc_primary_mc_channel =  intValue;
    }
    value = messageIn.getElementValue("t_primary_dataset.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_primary_dataset_id =  intValue;
    }
    value = messageIn.getElementValue("t_desc_primary.is_mc_data", lisName, index);
    if ( value != "NOTFOUND" ) {
        char charValue = *(value.c_str());
        t_desc_primary_is_mc_data =  charValue;
    }
    value = messageIn.getElementValue("t_physics_group.convener", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_physics_group_convener =  intValue;
    }

}

Processingpath_ClientAPIData::Processingpath_ClientAPIData(){

    Schema.insert(Entry("t_processed_dataset.name", "STRING"));
    Schema.insert(Entry("t_collection_type.name.t_application.output_type", "STRING"));
    Schema.insert(Entry("t_application.app_version", "STRING"));
    Schema.insert(Entry("t_app_config.conditions_version", "STRING"));
    Schema.insert(Entry("t_application.id", "INTEGER"));
    Schema.insert(Entry("t_application.output_type", "INTEGER"));
    Schema.insert(Entry("t_primary_dataset.description", "INTEGER"));
    Schema.insert(Entry("t_data_tier.id", "INTEGER"));
    Schema.insert(Entry("t_processing_path.parent", "INTEGER"));
    Schema.insert(Entry("t_app_config.id", "INTEGER"));
    Schema.insert(Entry("t_collection_type.id.t_application.input_type", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.processing_path", "INTEGER"));
    Schema.insert(Entry("t_processing_path.data_tier", "INTEGER"));
    Schema.insert(Entry("t_application.app_family", "INTEGER"));
    Schema.insert(Entry("t_primary_dataset.id", "INTEGER"));
    Schema.insert(Entry("t_data_tier.name", "STRING"));
    Schema.insert(Entry("t_app_family.name", "STRING"));
    Schema.insert(Entry("t_app_config.parameter_set", "STRING"));
    Schema.insert(Entry("t_processed_dataset.is_open", "CHARACTER"));
    Schema.insert(Entry("t_processing_path.app_config", "INTEGER"));
    Schema.insert(Entry("t_app_config.application", "INTEGER"));
    Schema.insert(Entry("t_application.executable", "STRING"));
    Schema.insert(Entry("t_collection_type.name.t_application.input_type", "STRING"));
    Schema.insert(Entry("t_primary_dataset.physics_group", "INTEGER"));
    Schema.insert(Entry("t_processing_path.full_path", "STRING"));
    Schema.insert(Entry("t_processing_path.id", "INTEGER"));
    Schema.insert(Entry("t_application.input_type", "INTEGER"));
    Schema.insert(Entry("t_primary_dataset.name", "STRING"));
    Schema.insert(Entry("t_processed_dataset.primary_dataset", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.id", "INTEGER"));
    Schema.insert(Entry("t_collection_type.id.t_application.output_type", "INTEGER"));
    Schema.insert(Entry("t_app_family.id", "INTEGER"));

}

int Processingpath_ClientAPIData::makeMessage(Message& messageOut) {
       if ( (string*)(&(t_processed_dataset_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processed_dataset.name", (string)t_processed_dataset_name.getValue(), (string)"STRING"));
      }
       if ( (string*)(&(t_collection_type_name_t_application_output_type.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_collection_type.name.t_application.output_type", (string)t_collection_type_name_t_application_output_type.getValue(), (string)"STRING"));
      }
       if ( (string*)(&(t_application_app_version.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_application.app_version", (string)t_application_app_version.getValue(), (string)"STRING"));
      }
       if ( (string*)(&(t_app_config_conditions_version.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_app_config.conditions_version", (string)t_app_config_conditions_version.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_application_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_application.id", (string)(util.itoa(t_application_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_application_output_type.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_application.output_type", (string)(util.itoa(t_application_output_type.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_primary_dataset_description.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_primary_dataset.description", (string)(util.itoa(t_primary_dataset_description.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_data_tier_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_data_tier.id", (string)(util.itoa(t_data_tier_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_processing_path_parent.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processing_path.parent", (string)(util.itoa(t_processing_path_parent.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_app_config_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_app_config.id", (string)(util.itoa(t_app_config_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_collection_type_id_t_application_input_type.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_collection_type.id.t_application.input_type", (string)(util.itoa(t_collection_type_id_t_application_input_type.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_processed_dataset_processing_path.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processed_dataset.processing_path", (string)(util.itoa(t_processed_dataset_processing_path.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_processing_path_data_tier.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processing_path.data_tier", (string)(util.itoa(t_processing_path_data_tier.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_application_app_family.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_application.app_family", (string)(util.itoa(t_application_app_family.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_primary_dataset_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_primary_dataset.id", (string)(util.itoa(t_primary_dataset_id.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_data_tier_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_data_tier.name", (string)t_data_tier_name.getValue(), (string)"STRING"));
      }
       if ( (string*)(&(t_app_family_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_app_family.name", (string)t_app_family_name.getValue(), (string)"STRING"));
      }
       if ( (string*)(&(t_app_config_parameter_set.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_app_config.parameter_set", (string)t_app_config_parameter_set.getValue(), (string)"STRING"));
      }
       if ( (char*)(&(t_processed_dataset_is_open.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processed_dataset.is_open", (string)(util.ctoa(t_processed_dataset_is_open.getValue())), (string)"CHARACTER"));
      }
       if ( (int*)(&(t_processing_path_app_config.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processing_path.app_config", (string)(util.itoa(t_processing_path_app_config.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_app_config_application.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_app_config.application", (string)(util.itoa(t_app_config_application.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_application_executable.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_application.executable", (string)t_application_executable.getValue(), (string)"STRING"));
      }
       if ( (string*)(&(t_collection_type_name_t_application_input_type.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_collection_type.name.t_application.input_type", (string)t_collection_type_name_t_application_input_type.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_primary_dataset_physics_group.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_primary_dataset.physics_group", (string)(util.itoa(t_primary_dataset_physics_group.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_processing_path_full_path.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processing_path.full_path", (string)t_processing_path_full_path.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_processing_path_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processing_path.id", (string)(util.itoa(t_processing_path_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_application_input_type.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_application.input_type", (string)(util.itoa(t_application_input_type.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_primary_dataset_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_primary_dataset.name", (string)t_primary_dataset_name.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_processed_dataset_primary_dataset.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processed_dataset.primary_dataset", (string)(util.itoa(t_processed_dataset_primary_dataset.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_processed_dataset_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processed_dataset.id", (string)(util.itoa(t_processed_dataset_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_collection_type_id_t_application_output_type.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_collection_type.id.t_application.output_type", (string)(util.itoa(t_collection_type_id_t_application_output_type.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_app_family_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_app_family.id", (string)(util.itoa(t_app_family_id.getValue())), (string)"INTEGER"));
      }

    return 1;

}

int Processingpath_ClientAPIData::readInMessage(Message& messageIn, string lisName, int index) {

    string value;
    value = messageIn.getElementValue("t_processed_dataset.name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_processed_dataset_name = strValue;
    }
    value = messageIn.getElementValue("t_collection_type.name.t_application.output_type", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_collection_type_name_t_application_output_type = strValue;
    }
    value = messageIn.getElementValue("t_application.app_version", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_application_app_version = strValue;
    }
    value = messageIn.getElementValue("t_app_config.conditions_version", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_app_config_conditions_version = strValue;
    }
    value = messageIn.getElementValue("t_application.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_application_id =  intValue;
    }
    value = messageIn.getElementValue("t_application.output_type", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_application_output_type =  intValue;
    }
    value = messageIn.getElementValue("t_primary_dataset.description", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_primary_dataset_description =  intValue;
    }
    value = messageIn.getElementValue("t_data_tier.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_data_tier_id =  intValue;
    }
    value = messageIn.getElementValue("t_processing_path.parent", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_processing_path_parent =  intValue;
    }
    value = messageIn.getElementValue("t_app_config.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_app_config_id =  intValue;
    }
    value = messageIn.getElementValue("t_collection_type.id.t_application.input_type", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_collection_type_id_t_application_input_type =  intValue;
    }
    value = messageIn.getElementValue("t_processed_dataset.processing_path", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_processed_dataset_processing_path =  intValue;
    }
    value = messageIn.getElementValue("t_processing_path.data_tier", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_processing_path_data_tier =  intValue;
    }
    value = messageIn.getElementValue("t_application.app_family", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_application_app_family =  intValue;
    }
    value = messageIn.getElementValue("t_primary_dataset.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_primary_dataset_id =  intValue;
    }
    value = messageIn.getElementValue("t_data_tier.name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_data_tier_name = strValue;
    }
    value = messageIn.getElementValue("t_app_family.name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_app_family_name = strValue;
    }
    value = messageIn.getElementValue("t_app_config.parameter_set", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_app_config_parameter_set = strValue;
    }
    value = messageIn.getElementValue("t_processed_dataset.is_open", lisName, index);
    if ( value != "NOTFOUND" ) {
        char charValue = *(value.c_str());
        t_processed_dataset_is_open =  charValue;
    }
    value = messageIn.getElementValue("t_processing_path.app_config", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_processing_path_app_config =  intValue;
    }
    value = messageIn.getElementValue("t_app_config.application", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_app_config_application =  intValue;
    }
    value = messageIn.getElementValue("t_application.executable", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_application_executable = strValue;
    }
    value = messageIn.getElementValue("t_collection_type.name.t_application.input_type", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_collection_type_name_t_application_input_type = strValue;
    }
    value = messageIn.getElementValue("t_primary_dataset.physics_group", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_primary_dataset_physics_group =  intValue;
    }
    value = messageIn.getElementValue("t_processing_path.full_path", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_processing_path_full_path = strValue;
    }
    value = messageIn.getElementValue("t_processing_path.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_processing_path_id =  intValue;
    }
    value = messageIn.getElementValue("t_application.input_type", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_application_input_type =  intValue;
    }
    value = messageIn.getElementValue("t_primary_dataset.name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_primary_dataset_name = strValue;
    }
    value = messageIn.getElementValue("t_processed_dataset.primary_dataset", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_processed_dataset_primary_dataset =  intValue;
    }
    value = messageIn.getElementValue("t_processed_dataset.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_processed_dataset_id =  intValue;
    }
    value = messageIn.getElementValue("t_collection_type.id.t_application.output_type", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_collection_type_id_t_application_output_type =  intValue;
    }
    value = messageIn.getElementValue("t_app_family.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_app_family_id =  intValue;
    }

}

Analysisdataset_ClientAPIData::Analysisdataset_ClientAPIData(){

    Schema.insert(Entry("t_dataset_status.name", "STRING"));
    Schema.insert(Entry("t_info_anads.analysis_dataset", "INTEGER"));
    Schema.insert(Entry("t_validation_status.name", "STRING"));
    Schema.insert(Entry("t_anads_data.is_primary", "CHARACTER"));
    Schema.insert(Entry("t_anads_data.event_collection", "INTEGER"));
    Schema.insert(Entry("t_info_anads.estimated_luminiosity", "STRING"));
    Schema.insert(Entry("t_analysis_dataset.name", "STRING"));
    Schema.insert(Entry("t_info_anads.validation_status", "INTEGER"));
    Schema.insert(Entry("t_anads_data.id", "INTEGER"));
    Schema.insert(Entry("t_dataset_status.id", "INTEGER"));
    Schema.insert(Entry("t_anads_data.analysis_dataset", "INTEGER"));
    Schema.insert(Entry("t_info_anads.status", "INTEGER"));
    Schema.insert(Entry("t_analysis_dataset.id", "INTEGER"));
    Schema.insert(Entry("t_validation_status.id", "INTEGER"));
    Schema.insert(Entry("t_analysis_dataset.processed_dataset", "INTEGER"));
    Schema.insert(Entry("t_info_anads.events", "INTEGER"));

}

int Analysisdataset_ClientAPIData::makeMessage(Message& messageOut) {
       if ( (string*)(&(t_dataset_status_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_dataset_status.name", (string)t_dataset_status_name.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_info_anads_analysis_dataset.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_info_anads.analysis_dataset", (string)(util.itoa(t_info_anads_analysis_dataset.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_validation_status_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_validation_status.name", (string)t_validation_status_name.getValue(), (string)"STRING"));
      }
       if ( (char*)(&(t_anads_data_is_primary.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_anads_data.is_primary", (string)(util.ctoa(t_anads_data_is_primary.getValue())), (string)"CHARACTER"));
      }
       if ( (int*)(&(t_anads_data_event_collection.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_anads_data.event_collection", (string)(util.itoa(t_anads_data_event_collection.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_info_anads_estimated_luminiosity.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_info_anads.estimated_luminiosity", (string)t_info_anads_estimated_luminiosity.getValue(), (string)"STRING"));
      }
       if ( (string*)(&(t_analysis_dataset_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_analysis_dataset.name", (string)t_analysis_dataset_name.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_info_anads_validation_status.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_info_anads.validation_status", (string)(util.itoa(t_info_anads_validation_status.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_anads_data_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_anads_data.id", (string)(util.itoa(t_anads_data_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_dataset_status_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_dataset_status.id", (string)(util.itoa(t_dataset_status_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_anads_data_analysis_dataset.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_anads_data.analysis_dataset", (string)(util.itoa(t_anads_data_analysis_dataset.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_info_anads_status.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_info_anads.status", (string)(util.itoa(t_info_anads_status.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_analysis_dataset_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_analysis_dataset.id", (string)(util.itoa(t_analysis_dataset_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_validation_status_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_validation_status.id", (string)(util.itoa(t_validation_status_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_analysis_dataset_processed_dataset.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_analysis_dataset.processed_dataset", (string)(util.itoa(t_analysis_dataset_processed_dataset.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_info_anads_events.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_info_anads.events", (string)(util.itoa(t_info_anads_events.getValue())), (string)"INTEGER"));
      }

    return 1;

}

int Analysisdataset_ClientAPIData::readInMessage(Message& messageIn, string lisName, int index) {

    string value;
    value = messageIn.getElementValue("t_dataset_status.name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_dataset_status_name = strValue;
    }
    value = messageIn.getElementValue("t_info_anads.analysis_dataset", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_info_anads_analysis_dataset =  intValue;
    }
    value = messageIn.getElementValue("t_validation_status.name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_validation_status_name = strValue;
    }
    value = messageIn.getElementValue("t_anads_data.is_primary", lisName, index);
    if ( value != "NOTFOUND" ) {
        char charValue = *(value.c_str());
        t_anads_data_is_primary =  charValue;
    }
    value = messageIn.getElementValue("t_anads_data.event_collection", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_anads_data_event_collection =  intValue;
    }
    value = messageIn.getElementValue("t_info_anads.estimated_luminiosity", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_info_anads_estimated_luminiosity = strValue;
    }
    value = messageIn.getElementValue("t_analysis_dataset.name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_analysis_dataset_name = strValue;
    }
    value = messageIn.getElementValue("t_info_anads.validation_status", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_info_anads_validation_status =  intValue;
    }
    value = messageIn.getElementValue("t_anads_data.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_anads_data_id =  intValue;
    }
    value = messageIn.getElementValue("t_dataset_status.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_dataset_status_id =  intValue;
    }
    value = messageIn.getElementValue("t_anads_data.analysis_dataset", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_anads_data_analysis_dataset =  intValue;
    }
    value = messageIn.getElementValue("t_info_anads.status", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_info_anads_status =  intValue;
    }
    value = messageIn.getElementValue("t_analysis_dataset.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_analysis_dataset_id =  intValue;
    }
    value = messageIn.getElementValue("t_validation_status.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_validation_status_id =  intValue;
    }
    value = messageIn.getElementValue("t_analysis_dataset.processed_dataset", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_analysis_dataset_processed_dataset =  intValue;
    }
    value = messageIn.getElementValue("t_info_anads.events", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_info_anads_events =  intValue;
    }

}

Datasetprovenenceevchild_ClientAPIData::Datasetprovenenceevchild_ClientAPIData(){

    Schema.insert(Entry("t_processed_dataset.name", "STRING"));
    Schema.insert(Entry("t_event_collection.id", "INTEGER"));
    Schema.insert(Entry("t_processing_path.id", "INTEGER"));
    Schema.insert(Entry("t_event_collection.is_primary", "CHARACTER"));
    Schema.insert(Entry("t_event_collection.processed_dataset", "INTEGER"));
    Schema.insert(Entry("t_primary_dataset.description", "INTEGER"));
    Schema.insert(Entry("t_data_tier.id", "INTEGER"));
    Schema.insert(Entry("t_processing_path.parent", "INTEGER"));
    Schema.insert(Entry("t_evcoll_parentage.type", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.processing_path", "INTEGER"));
    Schema.insert(Entry("t_parentage_type.name", "STRING"));
    Schema.insert(Entry("t_processing_path.data_tier", "INTEGER"));
    Schema.insert(Entry("t_primary_dataset.id", "INTEGER"));
    Schema.insert(Entry("t_event_collection.collection_index", "INTEGER"));
    Schema.insert(Entry("t_data_tier.name", "STRING"));
    Schema.insert(Entry("t_parentage_type.id", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.is_open", "CHARACTER"));
    Schema.insert(Entry("t_processing_path.app_config", "INTEGER"));
    Schema.insert(Entry("t_primary_dataset.physics_group", "INTEGER"));
    Schema.insert(Entry("t_processing_path.full_path", "STRING"));
    Schema.insert(Entry("t_primary_dataset.name", "STRING"));
    Schema.insert(Entry("t_evcoll_parentage.parent", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.primary_dataset", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.id", "INTEGER"));
    Schema.insert(Entry("t_evcoll_parentage.child", "INTEGER"));

}

int Datasetprovenenceevchild_ClientAPIData::makeMessage(Message& messageOut) {
       if ( (string*)(&(t_processed_dataset_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processed_dataset.name", (string)t_processed_dataset_name.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_event_collection_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_event_collection.id", (string)(util.itoa(t_event_collection_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_processing_path_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processing_path.id", (string)(util.itoa(t_processing_path_id.getValue())), (string)"INTEGER"));
      }
       if ( (char*)(&(t_event_collection_is_primary.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_event_collection.is_primary", (string)(util.ctoa(t_event_collection_is_primary.getValue())), (string)"CHARACTER"));
      }
       if ( (int*)(&(t_event_collection_processed_dataset.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_event_collection.processed_dataset", (string)(util.itoa(t_event_collection_processed_dataset.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_primary_dataset_description.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_primary_dataset.description", (string)(util.itoa(t_primary_dataset_description.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_data_tier_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_data_tier.id", (string)(util.itoa(t_data_tier_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_processing_path_parent.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processing_path.parent", (string)(util.itoa(t_processing_path_parent.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_evcoll_parentage_type.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_evcoll_parentage.type", (string)(util.itoa(t_evcoll_parentage_type.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_processed_dataset_processing_path.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processed_dataset.processing_path", (string)(util.itoa(t_processed_dataset_processing_path.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_parentage_type_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_parentage_type.name", (string)t_parentage_type_name.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_processing_path_data_tier.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processing_path.data_tier", (string)(util.itoa(t_processing_path_data_tier.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_primary_dataset_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_primary_dataset.id", (string)(util.itoa(t_primary_dataset_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_event_collection_collection_index.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_event_collection.collection_index", (string)(util.itoa(t_event_collection_collection_index.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_data_tier_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_data_tier.name", (string)t_data_tier_name.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_parentage_type_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_parentage_type.id", (string)(util.itoa(t_parentage_type_id.getValue())), (string)"INTEGER"));
      }
       if ( (char*)(&(t_processed_dataset_is_open.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processed_dataset.is_open", (string)(util.ctoa(t_processed_dataset_is_open.getValue())), (string)"CHARACTER"));
      }
       if ( (int*)(&(t_processing_path_app_config.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processing_path.app_config", (string)(util.itoa(t_processing_path_app_config.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_primary_dataset_physics_group.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_primary_dataset.physics_group", (string)(util.itoa(t_primary_dataset_physics_group.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_processing_path_full_path.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processing_path.full_path", (string)t_processing_path_full_path.getValue(), (string)"STRING"));
      }
       if ( (string*)(&(t_primary_dataset_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_primary_dataset.name", (string)t_primary_dataset_name.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_evcoll_parentage_parent.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_evcoll_parentage.parent", (string)(util.itoa(t_evcoll_parentage_parent.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_processed_dataset_primary_dataset.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processed_dataset.primary_dataset", (string)(util.itoa(t_processed_dataset_primary_dataset.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_processed_dataset_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processed_dataset.id", (string)(util.itoa(t_processed_dataset_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_evcoll_parentage_child.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_evcoll_parentage.child", (string)(util.itoa(t_evcoll_parentage_child.getValue())), (string)"INTEGER"));
      }

    return 1;

}

int Datasetprovenenceevchild_ClientAPIData::readInMessage(Message& messageIn, string lisName, int index) {

    string value;
    value = messageIn.getElementValue("t_processed_dataset.name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_processed_dataset_name = strValue;
    }
    value = messageIn.getElementValue("t_event_collection.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_event_collection_id =  intValue;
    }
    value = messageIn.getElementValue("t_processing_path.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_processing_path_id =  intValue;
    }
    value = messageIn.getElementValue("t_event_collection.is_primary", lisName, index);
    if ( value != "NOTFOUND" ) {
        char charValue = *(value.c_str());
        t_event_collection_is_primary =  charValue;
    }
    value = messageIn.getElementValue("t_event_collection.processed_dataset", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_event_collection_processed_dataset =  intValue;
    }
    value = messageIn.getElementValue("t_primary_dataset.description", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_primary_dataset_description =  intValue;
    }
    value = messageIn.getElementValue("t_data_tier.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_data_tier_id =  intValue;
    }
    value = messageIn.getElementValue("t_processing_path.parent", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_processing_path_parent =  intValue;
    }
    value = messageIn.getElementValue("t_evcoll_parentage.type", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_evcoll_parentage_type =  intValue;
    }
    value = messageIn.getElementValue("t_processed_dataset.processing_path", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_processed_dataset_processing_path =  intValue;
    }
    value = messageIn.getElementValue("t_parentage_type.name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_parentage_type_name = strValue;
    }
    value = messageIn.getElementValue("t_processing_path.data_tier", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_processing_path_data_tier =  intValue;
    }
    value = messageIn.getElementValue("t_primary_dataset.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_primary_dataset_id =  intValue;
    }
    value = messageIn.getElementValue("t_event_collection.collection_index", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_event_collection_collection_index =  intValue;
    }
    value = messageIn.getElementValue("t_data_tier.name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_data_tier_name = strValue;
    }
    value = messageIn.getElementValue("t_parentage_type.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_parentage_type_id =  intValue;
    }
    value = messageIn.getElementValue("t_processed_dataset.is_open", lisName, index);
    if ( value != "NOTFOUND" ) {
        char charValue = *(value.c_str());
        t_processed_dataset_is_open =  charValue;
    }
    value = messageIn.getElementValue("t_processing_path.app_config", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_processing_path_app_config =  intValue;
    }
    value = messageIn.getElementValue("t_primary_dataset.physics_group", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_primary_dataset_physics_group =  intValue;
    }
    value = messageIn.getElementValue("t_processing_path.full_path", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_processing_path_full_path = strValue;
    }
    value = messageIn.getElementValue("t_primary_dataset.name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_primary_dataset_name = strValue;
    }
    value = messageIn.getElementValue("t_evcoll_parentage.parent", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_evcoll_parentage_parent =  intValue;
    }
    value = messageIn.getElementValue("t_processed_dataset.primary_dataset", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_processed_dataset_primary_dataset =  intValue;
    }
    value = messageIn.getElementValue("t_processed_dataset.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_processed_dataset_id =  intValue;
    }
    value = messageIn.getElementValue("t_evcoll_parentage.child", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_evcoll_parentage_child =  intValue;
    }

}

Datasetprovenenceevparent_ClientAPIData::Datasetprovenenceevparent_ClientAPIData(){

    Schema.insert(Entry("t_processed_dataset.name", "STRING"));
    Schema.insert(Entry("t_event_collection.id", "INTEGER"));
    Schema.insert(Entry("t_processing_path.id", "INTEGER"));
    Schema.insert(Entry("t_event_collection.is_primary", "CHARACTER"));
    Schema.insert(Entry("t_event_collection.processed_dataset", "INTEGER"));
    Schema.insert(Entry("t_primary_dataset.description", "INTEGER"));
    Schema.insert(Entry("t_data_tier.id", "INTEGER"));
    Schema.insert(Entry("t_processing_path.parent", "INTEGER"));
    Schema.insert(Entry("t_evcoll_parentage.type", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.processing_path", "INTEGER"));
    Schema.insert(Entry("t_parentage_type.name", "STRING"));
    Schema.insert(Entry("t_processing_path.data_tier", "INTEGER"));
    Schema.insert(Entry("t_primary_dataset.id", "INTEGER"));
    Schema.insert(Entry("t_event_collection.collection_index", "INTEGER"));
    Schema.insert(Entry("t_data_tier.name", "STRING"));
    Schema.insert(Entry("t_parentage_type.id", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.is_open", "CHARACTER"));
    Schema.insert(Entry("t_processing_path.app_config", "INTEGER"));
    Schema.insert(Entry("t_primary_dataset.physics_group", "INTEGER"));
    Schema.insert(Entry("t_processing_path.full_path", "STRING"));
    Schema.insert(Entry("t_primary_dataset.name", "STRING"));
    Schema.insert(Entry("t_evcoll_parentage.parent", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.primary_dataset", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.id", "INTEGER"));
    Schema.insert(Entry("t_evcoll_parentage.child", "INTEGER"));

}

int Datasetprovenenceevparent_ClientAPIData::makeMessage(Message& messageOut) {
       if ( (string*)(&(t_processed_dataset_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processed_dataset.name", (string)t_processed_dataset_name.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_event_collection_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_event_collection.id", (string)(util.itoa(t_event_collection_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_processing_path_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processing_path.id", (string)(util.itoa(t_processing_path_id.getValue())), (string)"INTEGER"));
      }
       if ( (char*)(&(t_event_collection_is_primary.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_event_collection.is_primary", (string)(util.ctoa(t_event_collection_is_primary.getValue())), (string)"CHARACTER"));
      }
       if ( (int*)(&(t_event_collection_processed_dataset.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_event_collection.processed_dataset", (string)(util.itoa(t_event_collection_processed_dataset.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_primary_dataset_description.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_primary_dataset.description", (string)(util.itoa(t_primary_dataset_description.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_data_tier_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_data_tier.id", (string)(util.itoa(t_data_tier_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_processing_path_parent.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processing_path.parent", (string)(util.itoa(t_processing_path_parent.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_evcoll_parentage_type.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_evcoll_parentage.type", (string)(util.itoa(t_evcoll_parentage_type.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_processed_dataset_processing_path.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processed_dataset.processing_path", (string)(util.itoa(t_processed_dataset_processing_path.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_parentage_type_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_parentage_type.name", (string)t_parentage_type_name.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_processing_path_data_tier.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processing_path.data_tier", (string)(util.itoa(t_processing_path_data_tier.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_primary_dataset_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_primary_dataset.id", (string)(util.itoa(t_primary_dataset_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_event_collection_collection_index.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_event_collection.collection_index", (string)(util.itoa(t_event_collection_collection_index.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_data_tier_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_data_tier.name", (string)t_data_tier_name.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_parentage_type_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_parentage_type.id", (string)(util.itoa(t_parentage_type_id.getValue())), (string)"INTEGER"));
      }
       if ( (char*)(&(t_processed_dataset_is_open.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processed_dataset.is_open", (string)(util.ctoa(t_processed_dataset_is_open.getValue())), (string)"CHARACTER"));
      }
       if ( (int*)(&(t_processing_path_app_config.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processing_path.app_config", (string)(util.itoa(t_processing_path_app_config.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_primary_dataset_physics_group.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_primary_dataset.physics_group", (string)(util.itoa(t_primary_dataset_physics_group.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_processing_path_full_path.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processing_path.full_path", (string)t_processing_path_full_path.getValue(), (string)"STRING"));
      }
       if ( (string*)(&(t_primary_dataset_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_primary_dataset.name", (string)t_primary_dataset_name.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_evcoll_parentage_parent.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_evcoll_parentage.parent", (string)(util.itoa(t_evcoll_parentage_parent.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_processed_dataset_primary_dataset.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processed_dataset.primary_dataset", (string)(util.itoa(t_processed_dataset_primary_dataset.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_processed_dataset_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processed_dataset.id", (string)(util.itoa(t_processed_dataset_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_evcoll_parentage_child.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_evcoll_parentage.child", (string)(util.itoa(t_evcoll_parentage_child.getValue())), (string)"INTEGER"));
      }

    return 1;

}

int Datasetprovenenceevparent_ClientAPIData::readInMessage(Message& messageIn, string lisName, int index) {

    string value;
    value = messageIn.getElementValue("t_processed_dataset.name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_processed_dataset_name = strValue;
    }
    value = messageIn.getElementValue("t_event_collection.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_event_collection_id =  intValue;
    }
    value = messageIn.getElementValue("t_processing_path.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_processing_path_id =  intValue;
    }
    value = messageIn.getElementValue("t_event_collection.is_primary", lisName, index);
    if ( value != "NOTFOUND" ) {
        char charValue = *(value.c_str());
        t_event_collection_is_primary =  charValue;
    }
    value = messageIn.getElementValue("t_event_collection.processed_dataset", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_event_collection_processed_dataset =  intValue;
    }
    value = messageIn.getElementValue("t_primary_dataset.description", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_primary_dataset_description =  intValue;
    }
    value = messageIn.getElementValue("t_data_tier.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_data_tier_id =  intValue;
    }
    value = messageIn.getElementValue("t_processing_path.parent", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_processing_path_parent =  intValue;
    }
    value = messageIn.getElementValue("t_evcoll_parentage.type", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_evcoll_parentage_type =  intValue;
    }
    value = messageIn.getElementValue("t_processed_dataset.processing_path", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_processed_dataset_processing_path =  intValue;
    }
    value = messageIn.getElementValue("t_parentage_type.name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_parentage_type_name = strValue;
    }
    value = messageIn.getElementValue("t_processing_path.data_tier", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_processing_path_data_tier =  intValue;
    }
    value = messageIn.getElementValue("t_primary_dataset.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_primary_dataset_id =  intValue;
    }
    value = messageIn.getElementValue("t_event_collection.collection_index", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_event_collection_collection_index =  intValue;
    }
    value = messageIn.getElementValue("t_data_tier.name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_data_tier_name = strValue;
    }
    value = messageIn.getElementValue("t_parentage_type.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_parentage_type_id =  intValue;
    }
    value = messageIn.getElementValue("t_processed_dataset.is_open", lisName, index);
    if ( value != "NOTFOUND" ) {
        char charValue = *(value.c_str());
        t_processed_dataset_is_open =  charValue;
    }
    value = messageIn.getElementValue("t_processing_path.app_config", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_processing_path_app_config =  intValue;
    }
    value = messageIn.getElementValue("t_primary_dataset.physics_group", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_primary_dataset_physics_group =  intValue;
    }
    value = messageIn.getElementValue("t_processing_path.full_path", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_processing_path_full_path = strValue;
    }
    value = messageIn.getElementValue("t_primary_dataset.name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_primary_dataset_name = strValue;
    }
    value = messageIn.getElementValue("t_evcoll_parentage.parent", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_evcoll_parentage_parent =  intValue;
    }
    value = messageIn.getElementValue("t_processed_dataset.primary_dataset", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_processed_dataset_primary_dataset =  intValue;
    }
    value = messageIn.getElementValue("t_processed_dataset.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_processed_dataset_id =  intValue;
    }
    value = messageIn.getElementValue("t_evcoll_parentage.child", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_evcoll_parentage_child =  intValue;
    }

}

Crabevcollview_ClientAPIData::Crabevcollview_ClientAPIData(){

    Schema.insert(Entry("t_info_evcoll.validation_status", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.name", "STRING"));
    Schema.insert(Entry("t_info_evcoll.name", "STRING"));
    Schema.insert(Entry("t_block.status", "INTEGER"));
    Schema.insert(Entry("t_primary_dataset.physics_group", "INTEGER"));
    Schema.insert(Entry("t_processing_path.id", "INTEGER"));
    Schema.insert(Entry("t_event_collection.is_primary", "CHARACTER"));
    Schema.insert(Entry("t_event_collection.processed_dataset", "INTEGER"));
    Schema.insert(Entry("t_info_evcoll.estimated_luminosity", "STRING"));
    Schema.insert(Entry("t_primary_dataset.description", "INTEGER"));
    Schema.insert(Entry("t_block.bytes", "INTEGER"));
    Schema.insert(Entry("t_data_tier.id", "INTEGER"));
    Schema.insert(Entry("t_processing_path.parent", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.processing_path", "INTEGER"));
    Schema.insert(Entry("t_block.files", "INTEGER"));
    Schema.insert(Entry("t_processing_path.data_tier", "INTEGER"));
    Schema.insert(Entry("t_primary_dataset.id", "INTEGER"));
    Schema.insert(Entry("t_event_collection.collection_index", "INTEGER"));
    Schema.insert(Entry("t_data_tier.name", "STRING"));
    Schema.insert(Entry("t_block.processed_dataset", "INTEGER"));
    Schema.insert(Entry("t_info_evcoll.events", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.is_open", "CHARACTER"));
    Schema.insert(Entry("t_processing_path.app_config", "INTEGER"));
    Schema.insert(Entry("t_block.id", "INTEGER"));
    Schema.insert(Entry("t_event_collection.id", "INTEGER"));
    Schema.insert(Entry("t_processing_path.full_path", "STRING"));
    Schema.insert(Entry("t_primary_dataset.name", "STRING"));
    Schema.insert(Entry("t_info_evcoll.status", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.primary_dataset", "INTEGER"));
    Schema.insert(Entry("t_info_evcoll.event_collection", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.id", "INTEGER"));

}

int Crabevcollview_ClientAPIData::makeMessage(Message& messageOut) {
       if ( (int*)(&(t_info_evcoll_validation_status.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_info_evcoll.validation_status", (string)(util.itoa(t_info_evcoll_validation_status.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_processed_dataset_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processed_dataset.name", (string)t_processed_dataset_name.getValue(), (string)"STRING"));
      }
       if ( (string*)(&(t_info_evcoll_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_info_evcoll.name", (string)t_info_evcoll_name.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_block_status.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_block.status", (string)(util.itoa(t_block_status.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_primary_dataset_physics_group.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_primary_dataset.physics_group", (string)(util.itoa(t_primary_dataset_physics_group.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_processing_path_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processing_path.id", (string)(util.itoa(t_processing_path_id.getValue())), (string)"INTEGER"));
      }
       if ( (char*)(&(t_event_collection_is_primary.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_event_collection.is_primary", (string)(util.ctoa(t_event_collection_is_primary.getValue())), (string)"CHARACTER"));
      }
       if ( (int*)(&(t_event_collection_processed_dataset.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_event_collection.processed_dataset", (string)(util.itoa(t_event_collection_processed_dataset.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_info_evcoll_estimated_luminosity.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_info_evcoll.estimated_luminosity", (string)t_info_evcoll_estimated_luminosity.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_primary_dataset_description.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_primary_dataset.description", (string)(util.itoa(t_primary_dataset_description.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_block_bytes.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_block.bytes", (string)(util.itoa(t_block_bytes.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_data_tier_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_data_tier.id", (string)(util.itoa(t_data_tier_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_processing_path_parent.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processing_path.parent", (string)(util.itoa(t_processing_path_parent.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_processed_dataset_processing_path.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processed_dataset.processing_path", (string)(util.itoa(t_processed_dataset_processing_path.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_block_files.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_block.files", (string)(util.itoa(t_block_files.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_processing_path_data_tier.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processing_path.data_tier", (string)(util.itoa(t_processing_path_data_tier.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_primary_dataset_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_primary_dataset.id", (string)(util.itoa(t_primary_dataset_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_event_collection_collection_index.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_event_collection.collection_index", (string)(util.itoa(t_event_collection_collection_index.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_data_tier_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_data_tier.name", (string)t_data_tier_name.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_block_processed_dataset.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_block.processed_dataset", (string)(util.itoa(t_block_processed_dataset.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_info_evcoll_events.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_info_evcoll.events", (string)(util.itoa(t_info_evcoll_events.getValue())), (string)"INTEGER"));
      }
       if ( (char*)(&(t_processed_dataset_is_open.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processed_dataset.is_open", (string)(util.ctoa(t_processed_dataset_is_open.getValue())), (string)"CHARACTER"));
      }
       if ( (int*)(&(t_processing_path_app_config.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processing_path.app_config", (string)(util.itoa(t_processing_path_app_config.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_block_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_block.id", (string)(util.itoa(t_block_id.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_event_collection_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_event_collection.id", (string)(util.itoa(t_event_collection_id.getValue())), (string)"INTEGER"));
      }
       if ( (string*)(&(t_processing_path_full_path.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processing_path.full_path", (string)t_processing_path_full_path.getValue(), (string)"STRING"));
      }
       if ( (string*)(&(t_primary_dataset_name.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_primary_dataset.name", (string)t_primary_dataset_name.getValue(), (string)"STRING"));
      }
       if ( (int*)(&(t_info_evcoll_status.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_info_evcoll.status", (string)(util.itoa(t_info_evcoll_status.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_processed_dataset_primary_dataset.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processed_dataset.primary_dataset", (string)(util.itoa(t_processed_dataset_primary_dataset.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_info_evcoll_event_collection.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_info_evcoll.event_collection", (string)(util.itoa(t_info_evcoll_event_collection.getValue())), (string)"INTEGER"));
      }
       if ( (int*)(&(t_processed_dataset_id.getValue())) != NULL ) {
          messageOut.addElement(new Element((string)"t_processed_dataset.id", (string)(util.itoa(t_processed_dataset_id.getValue())), (string)"INTEGER"));
      }

    return 1;

}

int Crabevcollview_ClientAPIData::readInMessage(Message& messageIn, string lisName, int index) {

    string value;
    value = messageIn.getElementValue("t_info_evcoll.validation_status", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_info_evcoll_validation_status =  intValue;
    }
    value = messageIn.getElementValue("t_processed_dataset.name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_processed_dataset_name = strValue;
    }
    value = messageIn.getElementValue("t_info_evcoll.name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_info_evcoll_name = strValue;
    }
    value = messageIn.getElementValue("t_block.status", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_block_status =  intValue;
    }
    value = messageIn.getElementValue("t_primary_dataset.physics_group", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_primary_dataset_physics_group =  intValue;
    }
    value = messageIn.getElementValue("t_processing_path.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_processing_path_id =  intValue;
    }
    value = messageIn.getElementValue("t_event_collection.is_primary", lisName, index);
    if ( value != "NOTFOUND" ) {
        char charValue = *(value.c_str());
        t_event_collection_is_primary =  charValue;
    }
    value = messageIn.getElementValue("t_event_collection.processed_dataset", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_event_collection_processed_dataset =  intValue;
    }
    value = messageIn.getElementValue("t_info_evcoll.estimated_luminosity", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_info_evcoll_estimated_luminosity = strValue;
    }
    value = messageIn.getElementValue("t_primary_dataset.description", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_primary_dataset_description =  intValue;
    }
    value = messageIn.getElementValue("t_block.bytes", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_block_bytes =  intValue;
    }
    value = messageIn.getElementValue("t_data_tier.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_data_tier_id =  intValue;
    }
    value = messageIn.getElementValue("t_processing_path.parent", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_processing_path_parent =  intValue;
    }
    value = messageIn.getElementValue("t_processed_dataset.processing_path", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_processed_dataset_processing_path =  intValue;
    }
    value = messageIn.getElementValue("t_block.files", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_block_files =  intValue;
    }
    value = messageIn.getElementValue("t_processing_path.data_tier", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_processing_path_data_tier =  intValue;
    }
    value = messageIn.getElementValue("t_primary_dataset.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_primary_dataset_id =  intValue;
    }
    value = messageIn.getElementValue("t_event_collection.collection_index", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_event_collection_collection_index =  intValue;
    }
    value = messageIn.getElementValue("t_data_tier.name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_data_tier_name = strValue;
    }
    value = messageIn.getElementValue("t_block.processed_dataset", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_block_processed_dataset =  intValue;
    }
    value = messageIn.getElementValue("t_info_evcoll.events", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_info_evcoll_events =  intValue;
    }
    value = messageIn.getElementValue("t_processed_dataset.is_open", lisName, index);
    if ( value != "NOTFOUND" ) {
        char charValue = *(value.c_str());
        t_processed_dataset_is_open =  charValue;
    }
    value = messageIn.getElementValue("t_processing_path.app_config", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_processing_path_app_config =  intValue;
    }
    value = messageIn.getElementValue("t_block.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_block_id =  intValue;
    }
    value = messageIn.getElementValue("t_event_collection.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_event_collection_id =  intValue;
    }
    value = messageIn.getElementValue("t_processing_path.full_path", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_processing_path_full_path = strValue;
    }
    value = messageIn.getElementValue("t_primary_dataset.name", lisName, index);
    if ( value != "NOTFOUND" ) {
        string strValue = (string) value;
        t_primary_dataset_name = strValue;
    }
    value = messageIn.getElementValue("t_info_evcoll.status", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_info_evcoll_status =  intValue;
    }
    value = messageIn.getElementValue("t_processed_dataset.primary_dataset", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_processed_dataset_primary_dataset =  intValue;
    }
    value = messageIn.getElementValue("t_info_evcoll.event_collection", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_info_evcoll_event_collection =  intValue;
    }
    value = messageIn.getElementValue("t_processed_dataset.id", lisName, index);
    if ( value != "NOTFOUND" ) {
        int intValue  = atoi(value.c_str());
        t_processed_dataset_id =  intValue;
    }

}

