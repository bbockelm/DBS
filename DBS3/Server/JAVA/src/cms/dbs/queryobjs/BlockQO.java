/***
 * $Id:$
 *
 * This is the class for Block query objects.
 * @author Y. Guo
 ***/
package cms.dbs.queryobjs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.json.JSONObject;
import org.json.JSONArray;
import cms.dbs.commons.db.DBManagement;
import cms.dbs.commons.db.SequenceManager;
import cms.dbs.commons.exceptions.DBSException;
import cms.dbs.commons.utils.DBSSrvcUtil;
import cms.dbs.dataobjs.PrimaryDataset;
import cms.dbs.dataobjs.ProcessedDataset;
import cms.dbs.dataobjs.DataTier;
import cms.dbs.dataobjs.DatasetType;
import cms.dbs.dataobjs.PhysicsGroup;
import cms.dbs.dataobjs.Dataset;
import cms.dbs.dataobjs.File;
import cms.dbs.dataobjs.Block;
import cms.dbs.dataobjs.Site;

public class BlockQO extends  DBSSimpleQueryObject{

    public BlockQO() throws Exception{
            super();
    }
    //insert a Block into DB. Will do it later.
    /*
    public int putFile(Connection conn, File cond) throws Exception{
	//System.out.println(cond);
	//Check for LFN
	String LFN = cond.getLogicalFileName ( );
	if(LFN == null || LFN=="") throw new DBSException("Input Data Error", "LogicalFileName is expected.");
	//Check is_file_valid
	int fileValid = cond.getIsFileValid( );
        if(fileValid == -1) throw new DBSException("Input Data Error", "Validation of File is expected.");
	//Check if Datset already in db
	Dataset ds = cond.getDatasetDO();
	//System.out.println(ds);
	if(ds == null)throw new DBSException("Input Data Error", "Dataset is expected.");
	if(ds.getDatasetID( ) == 0){
	    String dsName = ds.getDataset();
	    if(dsName == null || dsName="")throw new DBSException("Input Data Error", "Dataset name is missing");
	    JSONArray dss = (new DatasetQO()).listDatasets(conn,  ds);
	    if(dss.length() != 1)
		throw new DBSException("Input Data Error", "dataset name :" + dsName 
		+" is not found or more than one found in the db.");
	    else{ 
		ds.setDatasetID(((Dataset)dss.getJSONObject(0)).getDatasetID());
		//ds.setDataset(((Dataset)dss.getJSONObject(0)).getDataset());
	    }
	}
        //System.out.println(cond);
	//System.out.println("check if the Block already in the db, if not insert it\n");
	Block bk  = cond.getBlockDO();
	if(bk == null) throw new DBSException("Input Data Error", "Block is expected.");
	if(bk.getBlockID() == 0){
	    String bkName = bk.getBlockName();
	    if(bkName == null || bkName == "")throw new DBSException("Input Data Error", "Block name is missing");
	    JSONArray bks = (new BlockQO()).listBlocks(conn,  bk);
	    if(bks.length() != 1 )
		throw new DBSException("Input Data Error", "More than one or no Blocks  are found in the db with name: "
		 + bkName);
	    else {
		bk.setBlockID(((Block)bks.getJSONObject(0)).getBlockID());
		//System.out.println("Block : " + bk);
	    }
	}
	//
	//System.out.println("Check for file_type");
	FileType ft = cond.getFileTypeDO();
        if(ft == null)throw new DBSException("Input Data Error", "File type is expected.");
        if(ft.getFileTypeID( ) == 0){
            String ftName = dataTier.getFileType();
            if(ftName == null || ftName == "")throw new DBSException("Input Data Error", "File type is missing");
            JSONArray fts = (new FileTypeQO()).listFileType(conn,  ft);
            if(fts.length() != 1)
                throw new DBSException("Input Data Error", "File type :" + ftName
                +" is not found or more than one found in the db.");
            else
                ft.setFileTypeID(((FileType)fts.getJSONObject(0)).getFileTypeID());
        }
	//System.out.println("File type: " + ft);
	//check for Primary key
	int fileID = cond.getFileID ( );
	if(fileID == 0){
	    try{
		fileID = SequenceManager.getSequence(conn, "SEQ_FL");
		cond. setFileID(FileID);
	    }catch (SQLException ex) {
		throw ex;
	    }
	}
	//
	//System.out.println("Check for check-sum");
	String cs = cond.getCheckSum();
        if(cs == null || cs == "")throw new DBSException("Input Data Error", "File check-sum is expected.");
	//check for event_count
	if (cond.getEventCount() == -1) throw new DBSException("Input Data Error", "File event count is expected.");
	//check for file size
	if(cond.getFileSize() == -1) throw new DBSException("Input Data Error", "File size is expected.");
	//System.out.println("Check for creation_date and created_by. \n");
	long createDate = cond.getCreationDate( );
	String createdBy = cond.getCreateBy( );
	if(createDate == 0)cond.setCreationDate(DBSSrvcUtil.getEpoch());
        if(createdBy == null || createdBy=="")cond.setCreateBy("WeNeed2FindWhoDidIt");
	 	
	//Now we are ready to insert into the dataset
	//System.out.println(cond);
	insertTable(conn, cond, "FILES");
	return (cond.getFileID());
   }
*/
    //list only the Blocks that satisfied the condition.
    public JSONArray listBlocks(Connection conn, Block cond) throws Exception{
        this.result = new JSONArray();
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean blockID = false;
        String sql = "SELECT B.BLOCK_ID, B.BLOCK_NAME, B.DATASET_ID, B.OPEN_FOR_WRITING, B.ORIGIN_SITE, B.BLOCK_SIZE, "
		     + "  B.FILE_COUNT, B.CREATION_DATE, B.CREATE_BY, B.LAST_MODIFICATION_DATE, B.LAST_MODIFIED_BY, "
		     + " SI.SITE_NAME, DS.DATASET "
                     + " FROM " + schemaOwner + "BLOCKS B " 
                     + " JOIN " + schemaOwner + "DATASETS DS ON  DS.DATASET_ID = B.DATASET_ID "
		     + " JOIN " + schemaOwner + "SITE SI on SI.SITE_ID = B.ORIGIN_SITE"
                     + " WHERE ";
	if(cond.getBlockID() != 0){ 
	    sql += "B.BLOCK_ID = ? ";
	    blockID =true;
	}
        else if (cond.getBlockName() != null || cond.getBlockName() != ""){
	    if ( (cond.getBlockName()).indexOf('%') != -1) sql += "B.BLOCK_NAME like ?";
	    else sql += "B.BLOCK_NAME = ?";
	}
        else throw  new DBSException("Input Data Error", "Block name or ID have to be provided. ");

        ps = null;
        rs = null;
        try{
            ps = DBManagement.getStatement(conn, sql);
            //prepare statement index starting with 1, but JSONArray index starting with 0.
	    if(blockID)ps.setInt(1, cond.getBlockID());
	    else ps.setString(1, cond.getBlockName());
            //System.out.println(ps.toString());
            rs =  ps.executeQuery();
            while(rs.next()){
                String bkName = rs.getString("BLOCK_NAME");
                int bkID = rs.getInt("BLOCK_ID");
		int open = rs.getInt("OPEN_FOR_WRITING");
		Site org = new Site(rs.getInt("ORIGIN_SITE"), rs.getString("SITE_NAME"));
		int blockSite = rs.getInt("BLOCK_SIZE");
		int fileCnt =  rs.getInt(".FILE_COUNT");
		int cDate = rs.getInt("CREATION_DATE");
		String cBy =  rs.getString("CREATE_BY");
		int lDate = rs.getInt("LAST_MODIFICATION_DATE");
		String lBy = rs.getString("LAST_MODIFIED_BY");
		Dataset ds = new Dataset( rs.getInt("DATASET_ID"), rs.getString("DATASET"));
		this.result.put(new Block(bkID, bkName, ds, open, org, blockSite, fileCnt, cDate, cBy, lDate, lBy ));	
	    }
        }finally {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            }
            return this.result;
    }

}
