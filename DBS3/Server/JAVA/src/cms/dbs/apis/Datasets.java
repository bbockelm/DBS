/***
 * $Id: Datasets.java,v 1.4 2009/10/21 17:51:42 afaq Exp $
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

    String primaryDatasetName;

    public Datasets(Context context, Request request, Response response) {
	
        super(context, request, response);

	this.primaryDatasetName = (String)request.getAttributes().get("PRIMARY_DS_NAME");

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
		//Incoming object has BOTH type and name of Primary dataset, 
		//{"PRIMARY_DS_TYPE":"test","PRIMARY_DS_NAME":"TEST9"}
           	PrimaryDSType PT = new PrimaryDSType(0, json_req.getString("PRIMARY_DS_TYPE"));

		/*  We should put some checks in here
		String primaryDSName = null;
                if (!JSONObject.NULL.equals(json_req.getString("PRIMARY_DS_NAME"))) {
                        primaryDSName =  json_req.getString("PRIMARY_DS_NAME");
                }*/
            	PrimaryDataset PD = new PrimaryDataset(0, json_req.getString("PRIMARY_DS_NAME"), PT, 0, "");

		DBSApis api = new DBSApis();	
            	api.DBSApiInsertPrimaryDataset((PrimaryDataset)PD);

        } catch(Exception ex){
            	System.out.println("Exception raised :" + ex.getMessage() + ". " );
        }

    }


}


