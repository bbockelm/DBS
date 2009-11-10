/***
 * $Id: BlockBO.java,v 1.1 2009/10/20 16:28:52 yuyi Exp $
 *
 * This is the class for Block business objects.
 * @author Y. Guo  Oct-20-09
 ***/
package cms.dbs.bizobjs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.json.JSONObject;
import org.json.JSONArray;
import cms.dbs.dataobjs.Block;
import cms.dbs.queryobjs.BlockQO;

public class BlockBO extends DBSBusinessObject{
    
    public BlockBO() throws Exception {
	super();
    }

    public JSONArray getBlocks(Connection conn, Block cond) throws Exception{
	BlockQO bk = new  BlockQO();
	return bk.listBlocks(conn, cond);
	
    }

   public void insertBlock(Connection conn, Block cond) throws Exception{
	BlockQO bk = new BlockQO();
	bk.putBlock(conn, cond);
	conn.commit(); 
   }

}
