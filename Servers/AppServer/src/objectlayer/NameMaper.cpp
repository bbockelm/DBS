#include "NameMaper.hpp"
NameMaper::NameMaper(){
        NameMap.insert(Entry("t_person.id", "tp.id"));
        NameMap.insert(Entry("t_person.name", "tp.name"));
        NameMap.insert(Entry("t_person.distinguished_name", "tp.dn"));
        NameMap.insert(Entry("t_person.contact_info", "tp.ci"));
        NameMap.insert(Entry("t_physics_group.id", "tpg.id"));
        NameMap.insert(Entry("t_physics_group.name", "tpg.name"));
        NameMap.insert(Entry("t_physics_group.convenor", "tpg.convenor"));
        NameMap.insert(Entry("t_object_history.object_type", "toh.ot"));
        NameMap.insert(Entry("t_object_history.object_id", "toh.oi"));
        NameMap.insert(Entry("t_object_history.operation", "toh.operation"));
        NameMap.insert(Entry("t_object_history.at", "toh.at"));
        NameMap.insert(Entry("t_object_history.person", "toh.person"));
        NameMap.insert(Entry("t_object_history.mediator", "toh.mediator"));
        NameMap.insert(Entry("t_collection_type.id", "tct.id"));
        NameMap.insert(Entry("t_collection_type.name", "tct.name"));
        NameMap.insert(Entry("t_app_family.id", "taf.id"));
        NameMap.insert(Entry("t_app_family.name", "taf.name"));
        NameMap.insert(Entry("t_application.id", "ta.id"));
        NameMap.insert(Entry("t_application.executable", "ta.executable"));
        NameMap.insert(Entry("t_application.app_version", "ta.av"));
        NameMap.insert(Entry("t_application.app_family", "ta.af"));
        NameMap.insert(Entry("t_application.input_type", "ta.it"));
        NameMap.insert(Entry("t_application.output_type", "ta.ot"));
        NameMap.insert(Entry("t_app_config.id", "tac.id"));
        NameMap.insert(Entry("t_app_config.application", "tac.application"));
        NameMap.insert(Entry("t_app_config.parameter_set", "tac.ps"));
        NameMap.insert(Entry("t_app_config.conditions_version", "tac.cv"));
        NameMap.insert(Entry("t_desc_trigger.id", "tdt.id"));
        NameMap.insert(Entry("t_desc_trigger.description", "tdt.description"));
        NameMap.insert(Entry("t_desc_mc.id", "tdm.id"));
        NameMap.insert(Entry("t_desc_mc.description", "tdm.description"));
        NameMap.insert(Entry("t_desc_mc.production", "tdm.production"));
        NameMap.insert(Entry("t_desc_mc.decay_chain", "tdm.dc"));
        NameMap.insert(Entry("t_desc_primary.id", "tdp.id"));
        NameMap.insert(Entry("t_desc_primary.trigger_path", "tdp.tp"));
        NameMap.insert(Entry("t_desc_primary.mc_channel", "tdp.mc"));
        NameMap.insert(Entry("t_desc_primary.is_mc_data", "tdp.imd"));
        NameMap.insert(Entry("t_data_tier.id", "tdt.id1"));
        NameMap.insert(Entry("t_data_tier.name", "tdt.name"));
        NameMap.insert(Entry("t_primary_dataset.id", "tpd.id"));
        NameMap.insert(Entry("t_primary_dataset.name", "tpd.name"));
        NameMap.insert(Entry("t_primary_dataset.description", "tpd.description"));
        NameMap.insert(Entry("t_primary_dataset.physics_group", "tpd.pg"));
        NameMap.insert(Entry("t_processing_path.id", "tpp.id"));
        NameMap.insert(Entry("t_processing_path.parent", "tpp.parent"));
        NameMap.insert(Entry("t_processing_path.app_config", "tpp.ac"));
        NameMap.insert(Entry("t_processing_path.full_path", "tpp.fp"));
        NameMap.insert(Entry("t_processing_path.data_tier", "tpp.dt"));
        NameMap.insert(Entry("t_processed_dataset.id", "tpd.id2"));
        NameMap.insert(Entry("t_processed_dataset.primary_dataset", "tpd.pd"));
        NameMap.insert(Entry("t_processed_dataset.processing_path", "tpd.pp"));
        NameMap.insert(Entry("t_processed_dataset.name", "tpd.name3"));
        NameMap.insert(Entry("t_processed_dataset.is_open", "tpd.io"));
        NameMap.insert(Entry("t_event_collection.id", "tec.id"));
        NameMap.insert(Entry("t_event_collection.processed_dataset", "tec.pd"));
        NameMap.insert(Entry("t_event_collection.collection_index", "tec.ci"));
        NameMap.insert(Entry("t_event_collection.is_primary", "tec.ip"));
        NameMap.insert(Entry("t_analysis_dataset.id", "tad.id"));
        NameMap.insert(Entry("t_analysis_dataset.processed_dataset", "tad.pd"));
        NameMap.insert(Entry("t_analysis_dataset.name", "tad.name"));
        NameMap.insert(Entry("t_anads_data.id", "tad.id4"));
        NameMap.insert(Entry("t_anads_data.analysis_dataset", "tad.ad"));
        NameMap.insert(Entry("t_anads_data.event_collection", "tad.ec"));
        NameMap.insert(Entry("t_parentage_type.id", "tpt.id"));
        NameMap.insert(Entry("t_parentage_type.name", "tpt.name"));
        NameMap.insert(Entry("t_evcoll_parentage.id", "tep.id"));
        NameMap.insert(Entry("t_evcoll_parentage.parent", "tep.parent"));
        NameMap.insert(Entry("t_evcoll_parentage.child", "tep.child"));
        NameMap.insert(Entry("t_evcoll_parentage.type", "tep.type"));
        NameMap.insert(Entry("t_block_status.id", "tbs.id"));
        NameMap.insert(Entry("t_block_status.name", "tbs.name"));
        NameMap.insert(Entry("t_block.id", "tb.id"));
        NameMap.insert(Entry("t_block.processed_dataset", "tb.pd"));
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
        NameMap.insert(Entry("t_file.filesize", "tf.filesize"));
        NameMap.insert(Entry("t_file.status", "tf.status"));
        NameMap.insert(Entry("t_file.type", "tf.type"));
        NameMap.insert(Entry("t_file.inblock", "tf.inblock"));
        NameMap.insert(Entry("t_evcoll_file.id", "tef.id"));
        NameMap.insert(Entry("t_evcoll_file.evcoll", "tef.evcoll"));
        NameMap.insert(Entry("t_evcoll_file.fileid", "tef.fileid"));
        NameMap.insert(Entry("t_validation_status.id", "tvs.id"));
        NameMap.insert(Entry("t_validation_status.name", "tvs.name"));
        NameMap.insert(Entry("t_dataset_status.id", "tds.id"));
        NameMap.insert(Entry("t_dataset_status.name", "tds.name"));
        NameMap.insert(Entry("t_evcoll_status.id", "tes.id"));
        NameMap.insert(Entry("t_evcoll_status.name", "tes.name"));
        NameMap.insert(Entry("t_info_anads.analysis_dataset", "tia.ad"));
        NameMap.insert(Entry("t_info_anads.events", "tia.events"));
        NameMap.insert(Entry("t_info_anads.estimated_luminosity", "tia.el"));
        NameMap.insert(Entry("t_info_anads.status", "tia.status"));
        NameMap.insert(Entry("t_info_anads.validation_status", "tia.vs"));
        NameMap.insert(Entry("t_info_evcoll.event_collection", "tie.ec"));
        NameMap.insert(Entry("t_info_evcoll.events", "tie.events"));
        NameMap.insert(Entry("t_info_evcoll.estimated_luminosity", "tie.el"));
        NameMap.insert(Entry("t_info_evcoll.status", "tie.status"));
        NameMap.insert(Entry("t_info_evcoll.validation_status", "tie.vs"));
        NameMap.insert(Entry("t_info_evcoll.name", "tie.name"));
        NameMap.insert(Entry("t_collection_type.name.t_application.output_type", "tctat"));
        NameMap.insert(Entry("t_collection_type.id.t_application.output_type", "tctat1"));
        NameMap.insert(Entry("t_collection_type.id.t_application.input_type", "tctat2"));
        NameMap.insert(Entry("t_collection_type.name.t_application.input_type", "tctat7"));
}
