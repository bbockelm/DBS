/***
 * $Id: PrimaryDatasets.java,v 1.2 2009/10/20 15:32:46 afaq Exp $
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
    /** 
     * Returns a full representation for a given variant. 
     */

    @Override
    public Representation represent(Variant variant) throws ResourceException {

        Representation representation = null;

        try {
		/**	
		JSONObject jj=new JSONObject();
                jj.put("abc", 1);

                representation = new StringRepresentation(
                        jj.toString(), MediaType.TEXT_PLAIN);
		**/


                DBSApis api = new DBSApis();
                PrimaryDataset cd = new PrimaryDataset();

		if (this.primaryDatasetName != null) {
			cd.setPrimaryDSName( this.primaryDatasetName );
		}
		else {
			cd.setPrimaryDSName("%");
		}

		//FIXME: 
		//AA-For testing purpose list ALL primary datasets
		//cd.setPrimaryDSName("%");
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

}
