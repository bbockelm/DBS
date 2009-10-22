/***
 * $Id: FileBO.java,v 1.2 2009/10/20 19:55:58 yuyi Exp $
 *
 * This is the class for File business objects.
 * @author Y. Guo  Oct-20-09
 ***/
package cms.dbs.bizobjs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.json.JSONObject;
import org.json.JSONArray;
import cms.dbs.commons.exceptions.DBSException;
import cms.dbs.dataobjs.Dataset;
import cms.dbs.queryobjs.DatasetQO;
import cms.dbs.dataobjs.File;
import cms.dbs.dataobjs.Block;
import cms.dbs.dataobjs.FileParent;
import cms.dbs.dataobjs.FileLumi;
import cms.dbs.queryobjs.FileLumiQO;
import cms.dbs.queryobjs.FileQO;
import cms.dbs.queryobjs.BlockQO;
import cms.dbs.queryobjs.FileParentQO;

public class FileBO extends DBSBusinessObject{
    
    public FileBO() throws Exception {
	super();
    }

    public JSONArray getFiles(Connection conn, File cond) throws Exception{
	FileQO file = new  FileQO();
	return file.listFiles(conn, cond);
	
    }

    public JSONArray getFiles(Connection conn, JSONArray cond) throws Exception{
        FileQO file = new  FileQO();
        return file.listFiles(conn, cond);
        
    }

    public void insertFile(Connection conn, File cond, JSONArray fps, JSONArray fls) throws Exception{
	FileQO fileQO = new  FileQO();
	fileQO.putFile(conn, cond);
	FileParentQO fileParentQO = new FileParentQO(); 
	//
	JSONArray p  = new JSONArray();
	for (int i =0; i<fps.length(); i++){
	    p.put(((FileParent)(fps.getJSONObject(i))).getParentFileDO());
	}
	//System.out.println("\n **** list bared file ****");
	JSONArray ps = fileQO.listBaredFiles(conn, p);
	if((ps.length() == 0) || (ps.length() != fps.length()))throw new DBSException("Input data error", "No parent file found as  "+ fps);
	//System.out.println("\nInsert Parantages");
	for (int i =0; i<fps.length(); i++){
	    fps.put(i, new FileParent(0, cond, (File)ps.getJSONObject(i)));
	}
	fileParentQO.putFileParentBatch(conn, fps);
	
	//System.out.println("\nInsert Lumi");
	FileLumiQO flQO = new FileLumiQO();
	for(int i=0; i<fls.length();i++){
	    ((FileLumi)fls.getJSONObject(i)).setFileDO(cond);
	}
	flQO.putFileLumiBatch(conn, fls);
	//
	conn.commit(); 
   }

    public JSONArray getFileLumis(Connection conn, File file) throws Exception{
	FileLumiQO flQO = new FileLumiQO();	
	return flQO.listFileLumis(conn, file);
   }

    public JSONArray getFileParents(Connection conn, File file) throws Exception{
	FileParentQO fpQO = new FileParentQO();
	    return fpQO.listParentFiles(conn, file);
	}
   
}
