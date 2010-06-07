#include "NameMaper.hpp"
NameMaper::NameMaper(){
        NameMap.insert(Entry("t_person.id", "tp.id"));
        NameMap.insert(Entry("t_person.name", "tp.name"));
        NameMap.insert(Entry("t_person.distinguished_name", "tp.dn"));
        NameMap.insert(Entry("t_person.contact_info", "tp.ci"));
        NameMap.insert(Entry("t_object_history.object_type", "toh.ot"));
        NameMap.insert(Entry("t_object_history.object_id", "toh.oi"));
        NameMap.insert(Entry("t_object_history.operation", "toh.operation"));
        NameMap.insert(Entry("t_object_history.at", "toh.at"));
        NameMap.insert(Entry("t_object_history.person", "toh.person"));
        NameMap.insert(Entry("t_object_history.mediator", "toh.mediator"));
        NameMap.insert(Entry("t_parameter_set.id", "tps.id"));
        NameMap.insert(Entry("t_parameter_set.hash", "tps.hash"));
        NameMap.insert(Entry("t_parameter_set.content", "tps.content"));
        NameMap.insert(Entry("t_app_family.id", "taf.id"));
        NameMap.insert(Entry("t_app_family.name", "taf.name"));
        NameMap.insert(Entry("t_application.id", "ta.id"));
        NameMap.insert(Entry("t_application.executable", "ta.executable"));
        NameMap.insert(Entry("t_application.app_version", "ta.av"));
        NameMap.insert(Entry("t_application.app_family", "ta.af"));
        NameMap.insert(Entry("t_app_config.id", "tac.id"));
        NameMap.insert(Entry("t_app_config.application", "tac.application"));
        NameMap.insert(Entry("t_app_config.parameter_set", "tac.ps"));
        NameMap.insert(Entry("t_data_tier.id", "tdt.id"));
        NameMap.insert(Entry("t_data_tier.name", "tdt.name"));
        NameMap.insert(Entry("t_parentage_type.id", "tpt.id"));
        NameMap.insert(Entry("t_parentage_type.name", "tpt.name"));
        NameMap.insert(Entry("t_evcoll_status.id", "tes.id"));
        NameMap.insert(Entry("t_evcoll_status.name", "tes.name"));
        NameMap.insert(Entry("t_primary_dataset.id", "tpd.id"));
        NameMap.insert(Entry("t_primary_dataset.name", "tpd.name"));
        NameMap.insert(Entry("t_processing_name.id", "tpn.id"));
        NameMap.insert(Entry("t_processing_name.name", "tpn.name"));
        NameMap.insert(Entry("t_processing.id", "tp.id1"));
        NameMap.insert(Entry("t_processing.primary_dataset", "tp.pd"));
        NameMap.insert(Entry("t_processing.app_config", "tp.ac"));
        NameMap.insert(Entry("t_processing.name", "tp.name2"));
        NameMap.insert(Entry("t_processing.is_open", "tp.io"));
        NameMap.insert(Entry("t_processing.input", "tp.input"));
        NameMap.insert(Entry("t_processed_dataset.id", "tpd.id3"));
        NameMap.insert(Entry("t_processed_dataset.primary_dataset", "tpd.pd"));
        NameMap.insert(Entry("t_processed_dataset.data_tier", "tpd.dt"));
        NameMap.insert(Entry("t_processed_dataset.name", "tpd.name4"));
        NameMap.insert(Entry("t_processed_dataset.input", "tpd.input"));
        NameMap.insert(Entry("t_event_collection.id", "tec.id"));
        NameMap.insert(Entry("t_event_collection.processed_dataset", "tec.pd"));
        NameMap.insert(Entry("t_event_collection.name", "tec.name"));
        NameMap.insert(Entry("t_event_collection.events", "tec.events"));
        NameMap.insert(Entry("t_event_collection.status", "tec.status"));
        NameMap.insert(Entry("t_evcoll_parentage.id", "tep.id"));
        NameMap.insert(Entry("t_evcoll_parentage.parent", "tep.parent"));
        NameMap.insert(Entry("t_evcoll_parentage.child", "tep.child"));
        NameMap.insert(Entry("t_evcoll_parentage.type", "tep.type"));
        NameMap.insert(Entry("t_block_status.id", "tbs.id"));
        NameMap.insert(Entry("t_block_status.name", "tbs.name"));
        NameMap.insert(Entry("t_block.id", "tb.id"));
        NameMap.insert(Entry("t_block.processing", "tb.processing"));
        NameMap.insert(Entry("t_block.status", "tb.status"));
        NameMap.insert(Entry("t_block.files", "tb.files"));
        NameMap.insert(Entry("t_block.bytes", "tb.bytes"));
        NameMap.insert(Entry("t_file_status.id", "tfs.id"));
        NameMap.insert(Entry("t_file_status.name", "tfs.name"));
        NameMap.insert(Entry("t_file_type.id", "tft.id"));
        NameMap.insert(Entry("t_file_type.name", "tft.name"));
        NameMap.insert(Entry("t_file.id", "tf.id"));
        NameMap.insert(Entry("t_file.guid", "tf.guid"));
        NameMap.insert(Entry("t_file.logical_name", "tf.ln"));
        NameMap.insert(Entry("t_file.checksum", "tf.checksum"));
        NameMap.insert(Entry("t_file.filesize", "tf.filesize"));
        NameMap.insert(Entry("t_file.status", "tf.status"));
        NameMap.insert(Entry("t_file.type", "tf.type"));
        NameMap.insert(Entry("t_file.inblock", "tf.inblock"));
        NameMap.insert(Entry("t_evcoll_file.id", "tef.id"));
        NameMap.insert(Entry("t_evcoll_file.evcoll", "tef.evcoll"));
        NameMap.insert(Entry("t_evcoll_file.fileid", "tef.fileid"));
        NameMap.insert(Entry("t_processing.primary_dataset.t_block.processing", "tpdb"));
        NameMap.insert(Entry("t_processing.input.t_block.processing", "tpb"));
        NameMap.insert(Entry("t_processing.app_config.t_block.processing", "tpcb"));
        NameMap.insert(Entry("t_processing.is_open.t_block.processing", "tpob"));
        NameMap.insert(Entry("t_processing.name.t_block.processing", "tpb405"));
        NameMap.insert(Entry("t_processing.id.t_block.processing", "tpb417"));
}