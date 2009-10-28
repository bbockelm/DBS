/***
 * $Id: Datasets.java,v 1.2 2009/10/26 11:33:21 afaq Exp $
 * DBS Server side APIs .
 ***/

package cms.dbs.apis;

import org.restlet.Context;
import org.restlet.data.MediaType;
import org.restlet.data.Request;
import org.restlet.data.Response;
import org.restlet.resource.Representation;
import org.restlet.resource.Resource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.StringRepresentation;
import org.restlet.resource.Variant;

import org.json.JSONObject;

import cms.dbs.dataobjs.Dataset;
import cms.dbs.dataobjs.PrimaryDataset;
import cms.dbs.dataobjs.PrimaryDSType;
import cms.dbs.dataobjs.Dataset;
import cms.dbs.dataobjs.ProcessedDataset;
import cms.dbs.dataobjs.DataTier;
import cms.dbs.dataobjs.DatasetType;
import cms.dbs.dataobjs.AcquisitionEra;
import cms.dbs.dataobjs.ProcessingEra;
import cms.dbs.dataobjs.PhysicsGroup;

import cms.dbs.apis.DBSApis;

public class Datasets extends Resource {

    String dataset;

    public Datasets(Context context, Request request, Response response) {
	
        super(context, request, response);

	this.dataset = (String)request.getAttributes().get("DATASET");

         // Allow modifications of this resource via POST/PUT/DELETE requests.  
         setModifiable(true);

        // This resource has only one type of representation.  
        getVariants().add(new Variant(MediaType.TEXT_PLAIN));
    }

    //GET  http://.../Datasets/
    //--NOT YET :: GET  http://.../{PRIMARY_DS_NAME}/
    //--NOT YET :: GET  http://.../{PRIMARY_DS_NAME}/{PROCESSED_DS_NAME}/
    //GET  http://.../{PRIMARY_DS_NAME}/{PROCESSED_DS_NAME}/{DATA_TIER}
    /** 
     * Returns a full representation for a given variant. 
     */

    @Override
    public Representation represent(Variant variant) throws ResourceException {

        Representation representation = null;

        try {
                DBSApis api = new DBSApis();
                Dataset cd = new Dataset();
		//FIXME: WE SHOULD NOT ahve to setDATASET_ID = 0 here
		cd.setDatasetID(0);

		if (this.dataset != null) {
			cd.setDataset( this.dataset );
		}
		else {
			cd.setDataset("%");
		}

                JSONObject retn = api.DBSApiFindDatasets(cd);

                representation = new StringRepresentation(
                        retn.toString(), MediaType.TEXT_PLAIN); 

        } catch(Exception ex) {
		System.out.println("Exception raised :" + ex.getMessage() );
		//throw new ResourceException(ex.getMessage());
		throw new ResourceException(100);
        }

        return representation;
    }

    //AA -- POST
    /** 
     * Handle POST requests: create a new item. (insert primrat datasets)
     */
    @Override
    public void acceptRepresentation(Representation entity)
            throws ResourceException {

        try{
		//Seems like you can only read ONCE from the entity (is it a stream?)
                JSONObject json_req = new JSONObject(entity.getText());
		//System.out.println("json_req:::"+json_req);
		/*  We should put some checks in here
		String primaryDSName = null;
                if (!JSONObject.NULL.equals(json_req.getString("PRIMARY_DS_NAME"))) {
                        primaryDSName =  json_req.getString("PRIMARY_DS_NAME");
                }*/

		PrimaryDSType PT = new PrimaryDSType(0, json_req.getString("PRIMARY_DS_TYPE"));
		PrimaryDataset PD = new PrimaryDataset(0, json_req.getString("PRIMARY_DS_NAME"), PT, 0, "");
		ProcessedDataset PROC = new ProcessedDataset(0, json_req.getString("PROCESSSED_DATASED_NAME"));
		DataTier DT = new DataTier(0, json_req.getString("DATA_TIER"));
		DatasetType DST = new DatasetType(0, json_req.getString("DATASET_TYPE"));				
		PhysicsGroup PG = new PhysicsGroup(0, json_req.getString("PHYSICS_GROUP"));
		AcquisitionEra AQ = new AcquisitionEra(0, json_req.getString("ACQUISITION_ERA_NAME"), 0, "", "");
		ProcessingEra PE = new ProcessingEra(0, json_req.getString("PROCESSING_VERSION"), 0, "", "");

		Dataset DS = new Dataset();
		DS.setDataset(json_req.getString("DATASET"));
		DS.setPrimaryDSDO(PD);
		DS.setProcessedDSDO(PROC);
		DS.setDataTierDO(DT);
		DS.setDatasetTypeDO(DST);
		DS.setPhysicsGroupDO(PG);
		DS.setAcquisitionEraDO(AQ);
		DS.setProcessingEraDO(PE);
		DS.setXtcrosssection((float)json_req.getDouble("XTCROSSSECTION"));
		DS.setGlobalTag(json_req.getString("GLOBAL_TAG"));
		
		DBSApis api = new DBSApis();	

            	api.DBSApiInsertDataset(DS);

        } catch(Exception ex){
            	System.out.println("Exception raised :" + ex.getMessage() + ". " );
        }

    }

}


