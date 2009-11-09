/***
 * $Id: Datasets.java,v 1.6 2009/11/09 21:15:13 afaq Exp $
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

import cms.dbs.dataobjs.File;

import cms.dbs.apis.DBSApis;

import cms.dbs.commons.exceptions.DBSException;
import org.restlet.data.Status;

import cms.dbs.commons.utils.DBSSrvcUtil;

public class Files extends Resource {

    String dataset;
    String lfn;
    String block;

    public Files(Context context, Request request, Response response) {
	
        super(context, request, response);

        String req=(String) request.getResourceRef().toString();
        java.util.HashMap kvalues = DBSSrvcUtil.getUrlParams(req);
	//A file search can be based on any of the following
        this.dataset = (String)kvalues.get("dataset");
        this.lfn = (String)kvalues.get("lfn");
        this.block = (String)kvalues.get("block");

	//FIXME: We should check if comple path is provided here or NOT

         // Allow modifications of this resource via POST/PUT/DELETE requests.  
         setModifiable(true);

        // This resource has only one type of representation.  
        getVariants().add(new Variant(MediaType.TEXT_PLAIN));

//response.setEntity("TEST", MediaType.TEXT_PLAIN);

    }

    //GET  http://.../files
    //GET  http://.../files?dataset=/{PRIMARY_DATASET_NAME}/{PROCESSED_DATASET_NAME}/{DATA_TIER}
    //GET  http://.../files?lfn=/store/...
    //GET  http://.../files?block=/{PRIMARY_DATASET_NAME}/{PROCESSED_DATASET_NAME}/{DATA_TIER}#GUID
    /** 
     * Returns a full representation for a given variant. 
     */

    @Override
    public Representation represent(Variant variant) throws ResourceException {

        Representation representation = null;

        try {
                DBSApis api = new DBSApis();
                File cd = new File();
		//FIXME: WE SHOULD NOT have to set FILE_ID = 0 here
		cd.setFileID(0);

		if (this.lfn!= null) {
			cd.setLogicalFileName( this.lfn );
		}
		else {
			cd.setLogicalFileName("%");
			//THROW and exception here, want to list all files, thats crazy !
		}

                JSONObject retn = api.DBSApiFindFiles(cd);

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

