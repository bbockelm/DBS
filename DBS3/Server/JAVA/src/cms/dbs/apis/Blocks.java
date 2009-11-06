/***
 * $Id: Blocks.java,v 1.1 2009/11/06 22:34:57 afaq Exp $
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

import cms.dbs.apis.DBSApis;
import cms.dbs.commons.exceptions.DBSException;
import org.restlet.data.Status;

import cms.dbs.dataobjs.Block;

public class Blocks extends Resource {

    String blockName;
    String dataset;
    String primary;
    String proc;
    String tier;

    public Blocks(Context context, Request request, Response response) {
	
        super(context, request, response);

        this.primary= (String)request.getAttributes().get("PRIMARY_DATASET_NAME");
        this.proc= (String)request.getAttributes().get("PROCESSED_DATASET_NAME");
	String tier_guid = (String)request.getAttributes().get("DATA_TIER_GUID"); //gives on tier
	String guid = request.getResourceRef().toString().split("#")[1];
	this.blockName = "/"+this.primary+"/"+this.proc+"/"+tier_guid+"#"+guid;

	System.out.println("this.blockName:::"+this.blockName);
	
	System.out.println("getResourceRef():::::"+request.getResourceRef());
	System.out.println("getRootRef:::::"+request.getRootRef());
	System.out.println("getMethod:::::"+request.getMethod());

/*
 	System.out.prinln("Resource URI  : " + request.getReference() + '\n' + "Root URI      : "  
             + request.getRootRef() + '\n' + "Routed part   : "  
             + request.getReference().getBaseRef() + '\n' + "Remaining part: "  
             + request.getReference().getRemainingPart()
		);

*/

	//String[] tier_guid = ((String)request.getAttributes().get("DATA_TIER_GUID")).split("#");
        //this.tier=tier_guid[0];
        //this.dataset = "/"+this.primary+"/"+this.proc+"/"+this.tier;
	//this.blockName = this.dataset + "#" + tier_guid[1];
        //FIXME: We should check if comple path is provided here or NOT

         // Allow modifications of this resource via POST/PUT/DELETE requests.  
         setModifiable(true);

        // This resource has only one type of representation.  
        getVariants().add(new Variant(MediaType.TEXT_PLAIN));
    }

    //GET  http://.../Blocks/
    /** 
     * Returns a full representation for a given variant. 
     */

    @Override
    public Representation represent(Variant variant) throws ResourceException {

        Representation representation = null;



        try {
                DBSApis api = new DBSApis();
		Block bk = new Block();	
		//FIXME: we should have to do following
		bk.setBlockID(0);

		if (this.blockName!= null) {
			bk.setBlockName ( this.blockName ) ;
		}
		else {
			throw new DBSException ("Incorrect parameter", "Cannot list all block, please specify a parameter");
		}

                JSONObject retn = api.DBSApiFindBlocks(bk);

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

/*    @Override
    public void acceptRepresentation(Representation entity)
            throws ResourceException {

        try{
		//Seems like you can only read ONCE from the entity (is it a stream?)
                JSONObject json_req = new JSONObject(entity.getText());
		System.out.println("json_req:::"+json_req);
		//Incoming object has BOTH type and name of Primary dataset, 
		//{"PRIMARY_DS_TYPE":"test","PRIMARY_DS_NAME":"TEST9"}
           	PrimaryDSType PT = new PrimaryDSType(0, json_req.getString("PRIMARY_DS_TYPE"));

            	PrimaryDataset PD = new PrimaryDataset(0, json_req.getString("PRIMARY_DS_NAME"), PT, 0, "");

		DBSApis api = new DBSApis();	
            	api.DBSApiInsertPrimaryDataset((PrimaryDataset)PD);

        } catch(Exception ex){
            	System.out.println("Exception raised :" + ex.getMessage() + ". " );
        }

    }
*/

}


