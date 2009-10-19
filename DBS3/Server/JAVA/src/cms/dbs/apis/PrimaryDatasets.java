/***
 * $Id: $
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
import cms.dbs.apis.DBSApis;

public class PrimaryDatasets extends Resource {

    public PrimaryDatasets(Context context, Request request, Response response) {
	
        super(context, request, response);

         // Allow modifications of this resource via POST/PUT/DELETE requests.  
         setModifiable(true);

        // This resource has only one type of representation.  
        getVariants().add(new Variant(MediaType.TEXT_PLAIN));
    }


    //GET  http://.../DBSPrimaryDatasets/
    /** 
     * Returns a full representation for a given variant. 
     */
    @Override
    public Representation represent(Variant variant) throws ResourceException {

        Representation representation = null;
        try{
                DBSApis api = new DBSApis();
		
                PrimaryDataset cd = new PrimaryDataset();
		//FIXME: 
		//AA-For testing purpose list ALL primary datasets
		cd.setPrimaryDSName("%");
                JSONObject retn = api.DBSApiFindPrimaryDatasets(cd);

                representation = new StringRepresentation(
                        retn.toString(), MediaType.TEXT_PLAIN);

        } catch(Exception ex){
            System.out.println("Exception raised :" + ex.getMessage() + ". " );
        }

        return representation;
    }

}
