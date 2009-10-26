/***
 * $Id: Datasets.java,v 1.1 2009/10/26 11:04:55 afaq Exp $
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






	    ProcessedDataset processedDS = new  ProcessedDataset(0, "ProcessedDS-Yuyi4");
            Dataset ds = new Dataset(0, "/TEST10-Primary/ProcessedDS-Yuyi4/GEN-SIM-RAW", 1, PD, processedDS, new DataTier(0, "GEN-SIM-RAW"),
                        new DatasetType(0, "PRODUCTION"), null, null, new PhysicsGroup(6, "QCD"), 0.01, "Yuyi's Tag", 0, "");
            System.out.println(api.DBSApiInsertDataset(ds));







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

		//FIXME: An ugly contraption od Dataset object, we should have something cleaner for better error handlingg !!!
		Dataset ds = new Dataset(0, json_req.getString("DATASET"), 1, PD, new ProcessedDataset(0, json_req.getString("PROCESSSED_DATASED_NAME")), new DataTier(0, json_req.getString("DATA_TIER")), new DatasetType(0, json_req.getString("DATASET_TYPE"), null, null, new PhysicsGroup(0, json_req.getString("PHYSICS_GROUP"), json_req.getString("???", json_req.getString("????"), 0, json_req.getString("????") );
		
		DBSApis api = new DBSApis();	
            	api.DBSApiInsertDataset(DS);

        } catch(Exception ex){
            	System.out.println("Exception raised :" + ex.getMessage() + ". " );
        }

    }


}


