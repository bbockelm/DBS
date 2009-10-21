/***
 * $Id: PrimaryDatasets.java,v 1.3 2009/10/20 16:40:55 afaq Exp $
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

import cms.dbs.dataobjs.PrimaryDataset;
import cms.dbs.dataobjs.PrimaryDSType;

import cms.dbs.apis.DBSApis;

public class PrimaryDatasets extends Resource {

    String primaryDatasetName;

    public PrimaryDatasets(Context context, Request request, Response response) {
	
        super(context, request, response);

	this.primaryDatasetName = (String)request.getAttributes().get("PRIMARY_DS_NAME");

         // Allow modifications of this resource via POST/PUT/DELETE requests.  
         setModifiable(true);

        // This resource has only one type of representation.  
        getVariants().add(new Variant(MediaType.TEXT_PLAIN));
    }

    //GET  http://.../PrimaryDatasets/
    //GET  http://.../PrimaryDatasets/{PRIMARY_DS_NAME}
    /** 
     * Returns a full representation for a given variant. 
     */

    @Override
    public Representation represent(Variant variant) throws ResourceException {

        Representation representation = null;

        try {
                DBSApis api = new DBSApis();
                PrimaryDataset cd = new PrimaryDataset();

		if (this.primaryDatasetName != null) {
			cd.setPrimaryDSName( this.primaryDatasetName );
		}
		else {
			cd.setPrimaryDSName("%");
		}

                JSONObject retn = api.DBSApiFindPrimaryDatasets(cd);

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


