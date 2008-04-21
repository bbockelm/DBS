/**
 $Revision: 1.10 $"
 $Id: DBSApiDQLogic.java,v 1.10 2008/04/16 20:19:14 afaq Exp $"
 *
 */

package dbs.api;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.io.Writer;
import java.util.Hashtable;
import dbs.sql.DBSSql;
import dbs.util.DBSUtil;
import dbs.DBSException;
import java.util.Vector;
import java.util.Date;

/**
* A class that has the core business logic of all the Primary dataset APIs.  The signature for the API is internal to DBS and is not exposed to the clients. There is another class <code>dbs.api.DBSApi</code> that has an interface for the clients. All these low level APIs are invoked from <code>dbs.api.DBSApi</code>. This class inherits from DBSApiLogic class.
* @author anzar
*/
public class DBSApiDQLogic extends DBSApiLogic {
		
	/**
	* Constructs a DBSApiLogic object that can be used to invoke several APIs.
	*/

	DBSApiPersonLogic personApi = null;
	DBSApiData data = null;
	public DBSApiDQLogic(DBSApiData data) {
		super(data);
		this.data = data;
		personApi = new DBSApiPersonLogic(data);
	}

	//Updates value of a FLAG
        public void updateDQFlag(Connection conn, Writer out, String runID, String lumiID,
                                                                String flag, String value,
                                                                String cbUserID, String creationDate,
                                                                String lmbUserID
                                                                ) throws Exception
        {
		//Find the flag
		//NO Need to Pass the value, this is the UPDATED Value actually
                String rowID =  getDQFlagID(conn, runID, lumiID, flag, "", true);

        	//INSERT into HISTORY table FIRST
                PreparedStatement ps = null;
                try {
                        ps = DBSSql.insertDQFlagHistory(conn, rowID);
                        ps.execute();
                } finally {
                        if (ps != null) ps.close();
                }


		//Update its value
        	ps = null;
                try {
                        ps = DBSSql.updateDQFlag(conn, rowID,
                                                        getID(conn, "QualityValues", "Value", value, true),
                                                        lmbUserID);
                        ps.executeUpdate();
                } finally {
                        if (ps != null) ps.close();
                }

        }

	//Insert a FLAG and returns its ID (Even if it already exists)	
	public String insertDQFlag(Connection conn, Writer out, String runID, String lumiID, 
								String flag, String value,
								String cbUserID, String creationDate,
								String lmbUserID
								) throws Exception 
	{
		//ONLY insert if its NOT already present, why the F*** the MySQL MUL-UQ doesn't work ?
		String flagID =  null;
		if ( ! isNull(flagID = getDQFlagID(conn, runID, lumiID, flag, "", false))) {
		//if ( ! isNull(flagID = getDQFlagID(conn, runID, lumiID, flag, value, false))) {
			DBSUtil.writeLog("FLAG:="+flag+" for This Run (LumiSection) already exixts with value:="+value);
			writeWarning(out, "Already Exists", "1020", "FLAG:="+flag+
							" for This Run (LumiSection) already exixts with value:="+value );
			return flagID;

		}

                PreparedStatement ps = null;
                try {
                        ps = DBSSql.insertDQFlag(conn, runID, lumiID, 
                                                        getIDNoCheck(conn, "SubSystem", "Name", flag, true),
                                                        getID(conn, "QualityValues", "Value", value, true),
                                                        cbUserID, lmbUserID, creationDate);
                        ps.execute();
                } finally {
                        if (ps != null) ps.close();
                }

		//ID of latest insert, going tobe used right now        
                flagID = getDQFlagID(conn, runID, lumiID, flag, value, true);
		return flagID;
        }
	

        /* In future we may like a functions like these
		private void insertDQFlag(Connection conn, String runNumber, String lumiNumber,
                                                                String flag, String value,
                                                                String cbUserID, String creationDate,
                                                                String lmbUserID
                                                                ) throws Exception {

	}
	

	public void getDQFlagID(Connection conn, String runNumber, String lumiNumber, String flag, String value)
		throws Exception 
	{
	}
	*/

        private String getDQFlagID(Connection conn, String runID, String lumiID, String flag, String value, boolean excep)
                throws Exception 
        {

		String id = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		//User of this method may chose to provide any of the values
		String flagid = null;
		String valueid = null;
		if (!isNull(flag)) flagid = getIDNoCheck(conn, "SubSystem", "Name", flag, true);
		if (!isNull(value)) valueid = getID(conn, "QualityValues", "Value", value, true);

                try {
                        ps = DBSSql.getDQFlag(conn, runID, lumiID, flagid,  valueid);
                        rs =  ps.executeQuery();

			if(!rs.next()) {
					if (excep)
						throw new DBSException("Unavailable data", "7002", "No DQ entry for RunID:" + runID );
					else return id;
                        }
                        id = get(rs, "ID");

                } finally {

                        if (ps != null) ps.close();
                        if (rs != null) rs.close();
                }
		//Return the ID of recently inserted row, comes VERY HANDY
		return id;	
	}

	
	public String getDQVerTimeStamp(Connection conn, String dqVersion) throws Exception {
	
		String timeStamp = null;
		PreparedStatement ps = null;
                ResultSet rs = null;
		try {
                        ps = DBSSql.getDQVerTimeStamp(conn, dqVersion);
			rs =  ps.executeQuery();

                        if(!rs.next()) {
                                        throw new DBSException("Unavailable data", "7008", "DQ Version:="+dqVersion+" do not exists in DBS" );
                        }
                        timeStamp = get(rs, "TIMESTAMP");

                } finally {

                        if (ps != null) ps.close();
                        if (rs != null) rs.close();
                }
                return timeStamp;


	}


        public void listDQVersions(Connection conn, Writer out) throws Exception {
                PreparedStatement ps = null;
                ResultSet rs =  null;

                try {
                        ps = DBSSql.listDQVersions(conn);
                        rs =  ps.executeQuery();
                        while(rs.next()) {
                                out.write( (String) "<dq_version version='"+get(rs, "DQ_VERSION")+"' time_stamp='"+get(rs, "TIME_STAMP")+"' />");
			}

                } finally {
                        if (rs != null) rs.close();
                        if (ps != null) ps.close();
                }
        }

        public void listSubSystems(Connection conn, Writer out) throws Exception {
		PreparedStatement ps = null;
                ResultSet rs =  null;

		try {
                        ps = DBSSql.listSubSystems(conn);
                        rs =  ps.executeQuery();
			while(rs.next()) {
				out.write( (String) "<sub_system name='"+get(rs, "SUBSYSTEM")+"' parent='"+get(rs, "PARENT")+"' />");
				
			} 
		} finally {
                        if (rs != null) rs.close();
                        if (ps != null) ps.close();
                }
	}


	public void listFilesForRunLumiDQ(Connection conn, Writer out, Vector runDQList, String timeStamp, String dqVersion) throws Exception {

		PreparedStatement ps = null;
                ResultSet rs =  null;

                if (!isNull(timeStamp) && !isNull(dqVersion)) {
                        throw new DBSException("Duplicate information supplied", "7006",
                                                                "You have provided both a TimeStamp:="+timeStamp+" and DQ Version:="+dqVersion);
		}

                if (!isNull(dqVersion)) {
                        //Get the time stamp from the version table
                        timeStamp = getDQVerTimeStamp(conn, dqVersion);
                }

                try {
                        ps = DBSSql.listFilesForRunLumiDQ(conn, runDQList, timeStamp);
                        rs =  ps.executeQuery();

                        while(rs.next()) {
				String lfn = get(rs, "LFN");
				//System.out.println("LFN: "+ lfn );
                                out.write( (String) "<file lfn='"+lfn+"' run_number='"+get(rs, "RUN")+"' />" ); 
			}
                } finally {
                        if (rs != null) rs.close();
                        if (ps != null) ps.close();
                }
	}


        public void listRunLumiDQ(Connection conn, Writer out, Vector runDQList, String timeStamp, String dqVersion) throws Exception {
                PreparedStatement ps = null;
                ResultSet rs =  null;

                if (!isNull(timeStamp) && !isNull(dqVersion)) {
			throw new DBSException("Duplicate information supplied", "7006",
                                                                "You have provided both a TimeStamp:="+timeStamp+" and DQ Version:="+dqVersion);

		}

		if (!isNull(dqVersion)) {
			//Get the time stamp from the version table
		 	timeStamp = getDQVerTimeStamp(conn, dqVersion);	
		}
		Vector alreadyGotID = new Vector();

		boolean first = true;
                try {
                        ps = DBSSql.listRunLumiDQ(conn, runDQList, timeStamp);
                        rs =  ps.executeQuery();

			String prevRun="";
			String prevLumi="";

			while(rs.next()) {
				String runNumber = get(rs, "RUN_NUMBER");
				String lumiNumber = get(rs, "LUMI_SECTION_NUMBER");
				if (!runNumber.equals(prevRun) || !lumiNumber.equals(prevLumi)) {
					if (!first) out.write( (String) "</run>");
					out.write( (String) "<run run_number='"+runNumber+"'" + 
									" lumi_section_number='"+lumiNumber+"' >");
					prevRun = runNumber;
					prevLumi = lumiNumber;
					first = false;
				}
				String parent = get(rs, "PARENT");
				String entryID = get(rs, "ID");
                                if(!alreadyGotID.contains(entryID)) {
					if ( parent.equals("CMS")) {
                                                out.write((String) "<dq_sub_system name='"+get(rs, "DQ_FLAG")+"'"+
                                                        " value='"+get(rs, "QVALUE")+"'");
                                        } else {
                                                out.write((String) "<dq_sub_subsys name='"+get(rs, "DQ_FLAG")+"'"+
                                                        " value='"+get(rs, "QVALUE")+"'");
                                        }
                                        out.write( (String) " parent='"+parent+"' />" );
					//AND STORE IT AS WELL
                                        alreadyGotID.add(entryID);
                                }
			}

                } finally {
                        if (rs != null) rs.close();
                        if (ps != null) ps.close();
                }
		if (!first) out.write( (String) "</run>");
        }

        public void updateRunLumiDQ(Connection conn, Writer out, Vector runDQList, Hashtable dbsUser) throws Exception {

                String cbUserID = null;
                String creationDate = null;
                String lmbUserID = personApi.getUserID(conn, dbsUser);

		DBSUtil.writeLog("Entering updateRunLumiDQ...");


 		for (int i = 0; i < runDQList.size() ; ++i) {
                        //Get the Run
                        Hashtable runDQ = (Hashtable) runDQList.get(i);

                        creationDate = getTime(runDQ, "creation_date", false);
                        cbUserID = personApi.getUserID(conn, get(runDQ, "created_by"), dbsUser );

                        //Get the Run Number
                        String runNumber = get(runDQ, "run_number", true);
                        DBSUtil.writeLog("runNumber: "+runNumber);

                        //See if Run Exists, It must, else throw error
                        String runID = getID(conn, "Runs", "RunNumber", runNumber, true);

                        //Get lumi_section_number
                        String lumiSecNumber = get(runDQ, "lumi_section_number", false);
                        //See if lumiSecNumber is sent by user and if yes, it must Exists in DBS
                        String lumiID = getID(conn, "LumiSection", "LumiSectionNumber", lumiSecNumber , false);

                        //Get the sub-system Vector
                        Vector subSys = DBSUtil.getVector(runDQ, "dq_sub_system");


			//Loop over each item
			for (int j = 0; j < subSys.size() ; ++j) {
				Hashtable dqFlag = (Hashtable) subSys.get(j);

                        	//Check for null yourself!      
                        	String name = DBSUtil.get(dqFlag, "name");
                        	String value = get(dqFlag, "value", true);
                        	DBSUtil.writeLog("Flag: Name..."+name);
                        	DBSUtil.writeLog("Flag: Value..."+value);

                        	//Let us UPDATE THIS FLAG
                        	updateDQFlag(conn, out, runID, lumiID,
                                                name, value,
                                                cbUserID, creationDate,
                        		                        lmbUserID);

                        	//It may have a sub-sub-system vector to itself 
                        	Vector subsubSys = DBSUtil.getVector(dqFlag, "dq_sub_subsys");
                        	for (int k = 0; k < subsubSys.size() ; ++k) {
                        		Hashtable dqsubFlag = (Hashtable) subsubSys.get(k);
					//Check for null yourself! 
					String subname = DBSUtil.get(dqsubFlag, "name");
                                	String subvalue = get(dqsubFlag, "value", true);
                                	DBSUtil.writeLog("      Flag: Sub Name..."+subname);
                                	DBSUtil.writeLog("      Flag: Sub Value..."+subvalue);

                                	//Let us update THIS FLAG
                                	updateDQFlag(conn, out, runID, lumiID,
                                                                subname, subvalue,
                                                                cbUserID, creationDate,
                                                                lmbUserID);
				}
			}
		}

	}


        public void versionDQ(Connection conn, Writer out, Hashtable vertable, Hashtable dbsUser) throws Exception {
		//Check to see if a version with same name already exists !

		String versionName =  get(vertable, "version", true);
                String descrp = get(vertable, "description", false);


		String versionID = getID(conn, "QualityVersion", "Version", versionName, false);
		if (!isNull(versionID)) {
			throw new DBSException("Version Already Exists", "7007",
							"Data Quality Version:="+versionName+" already exists in DBS, cannnot create again");
		}

		//String creationDate = Long.toString( (new Date()).getTime() / 1000 );
		String creationDate = getTime(vertable, "creation_date", false);
		String cbUserID = personApi.getUserID(conn, dbsUser);
		String lmbUserID = personApi.getUserID(conn, dbsUser);

                PreparedStatement ps = null;
                try {
			ps = DBSSql.insertDQVersion(conn, versionName, descrp, cbUserID, lmbUserID, creationDate);
                        ps.execute();

                } finally {
                        if (ps != null) ps.close();
                }
	
	}

        public void insertLumiRangeDQ(Connection conn, Writer out, String runNumber, String stratLumi, String endLumi,
                                                        Vector dqFlags, Hashtable dbsUser) throws Exception {

		String runID = getID(conn, "Runs", "RunNumber", runNumber, false);

		if (isNull (runID )){
			throw new DBSException("Run Do Not Exist", "7001",
				"RunNumber: "+ runNumber +", Do Not eixst in DBS, cannot add DQ Flags");
		}

		//The Run Information MUST already be in DBS to be able to add LumiSections to it !
		String runExists = getID(conn, "RunLumiQuality", "Run", runID, false);
	        if (isNull(runExists)) {
                      throw new DBSException("Run Quality Do Not Exist", "7005",
                                                                "RunNumber: "+runNumber +
                                                                " Do not have any DataQuality information in DBS, Run DQ goes before Lumi");
                }

		for (int i = Integer.parseInt(stratLumi); i <= Integer.parseInt(endLumi); ++i) {
			
			String lumiID = getID(conn, "LumiSection", "LumiSectionNumber", String.valueOf(i), false);
			if ( isNull(lumiID) ) {
                                throw new DBSException("Lumi Section Do Not Exist", "7004",
                                                        "Lumi Section "+String.valueOf(i)+", Do Not eixst in DBS RunNumber "+runNumber+
                                                        ", cannot add DQ Flags");

                        }	
			insertDQFlags(conn, out, runID, lumiID, dqFlags, dbsUser);
		}


	}

        public void insertSubSystem(Connection conn, Writer out, Hashtable subSys, Hashtable dbsUser) throws Exception {

		//Check for nulls ?
                String parent = DBSUtil.get(subSys, "parent");
                String name = DBSUtil.get(subSys, "name");

                String creationDate = getTime(subSys, "creation_date", false);
                String cbUserID = personApi.getUserID(conn, dbsUser);
                String lmbUserID = personApi.getUserID(conn, dbsUser);

                String checkID = getIDNoCheck(conn, "SubSystem", "Name", name, false);
                if ( !isNull (checkID )){
                        writeWarning(out, "Already Exist", "7009",
                                "SubSystem "+ name +", already eixst in DBS, cannot add again");
                        return;
                }

                PreparedStatement ps = null;

                try {
                        ps = DBSSql.insertSubSystem(conn, name, parent, cbUserID, lmbUserID, creationDate);
                        ps.execute();
                } finally {
                        if (ps != null) ps.close();
                }

        }


	public void insertRunRangeDQ(Connection conn, Writer out, String startRun, String endRun, 
							Vector dqFlags, Hashtable dbsUser) throws Exception {

		//We do not need lumiID
		String lumiID = null;

		for (int i = Integer.parseInt(startRun); i <= Integer.parseInt(endRun); ++i) {
			//See if Run Exists, It must, else throw error
			DBSUtil.writeLog("runNumber: "+i);
			String runID = getID(conn, "Runs", "RunNumber", String.valueOf(i), false);
			
                        if (isNull (runID )){
                                throw new DBSException("Run Do Not Exist", "7001",
                                                        "RunNumber: "+ i +", Do Not eixst in DBS, cannot add DQ Flags");
                        }

			insertDQFlags(conn, out, runID, lumiID, dqFlags, dbsUser);
			
		}	

	}
		
	public void insertRunLumiDQ(Connection conn, Writer out, 
							Vector runDQList, Hashtable dbsUser) throws Exception {

	        //String cbUserID = null;
                //String creationDate = null;
		//String lmbUserID = personApi.getUserID(conn, dbsUser);


		 DBSUtil.writeLog("Entering insertRunLumiDQ...");	
		 for (int i = 0; i < runDQList.size() ; ++i) {
			//Get the Run
                        Hashtable runDQ = (Hashtable) runDQList.get(i);

			//creationDate = getTime(runDQ, "creation_date", false);
			//cbUserID = personApi.getUserID(conn, get(runDQ, "created_by"), dbsUser );

			//Get the Run Number
			String runNumber = get(runDQ, "run_number", true);
		        DBSUtil.writeLog("runNumber: "+runNumber);	

                        //See if Run Exists, It must, else throw error
                        String runID = getID(conn, "Runs", "RunNumber", runNumber, false);

                        if (isNull (runID )){
                                throw new DBSException("Run Do Not Exist", "7001",
                                                        "RunNumber: "+runNumber+", Do Not eixst in DBS, cannot add DQ Flags");
                        }


			//Get lumi_section_number
			String lumiSecNumber = get(runDQ, "lumi_section_number", false);
			//See if lumiSecNumber is sent by user and if yes, it must Exists in DBS
			String lumiID = getID(conn, "LumiSection", "LumiSectionNumber", lumiSecNumber , false);

			if (!isNull(lumiSecNumber) && isNull(lumiID) ) {	
				throw new DBSException("Lumi Section Do Not Exist", "7004",
							"Lumi Section "+lumiSecNumber+", Do Not eixst in DBS RunNumber "+runNumber+
							", cannot add DQ Flags");

			}

			if (!isNull(lumiID) ){
				//IF LumiSection is provided then The associated Run MUST already be in DBS
				//This Biz. Rule can change, but for now it holds.
				//Throw exception if Run doesn't exists in RunLumiQuality Table
				String runExists = getID(conn, "RunLumiQuality", "Run", runID, false);
				if (isNull(runExists)) {
					throw new DBSException("Run Quality Do Not Exist", "7005",
								"RunNumber: "+runNumber +
								" Do not have any DataQuality information in DBS, Run DQ goes before Lumi");
				}
			}			

			//Get the sub-system Vector
			Vector subSys = DBSUtil.getVector(runDQ, "dq_sub_system");
			insertDQFlags(conn, out, runID, lumiID, subSys, dbsUser);
		}

	}


	private void insertDQFlags(Connection conn, Writer out, String runID, String lumiID,
                                                        Vector subSys, Hashtable dbsUser) throws Exception {


                String cbUserID = null;
                String creationDate = null;
                String lmbUserID = personApi.getUserID(conn, dbsUser);

		//Loop over each item
		for (int j = 0; j < subSys.size() ; ++j) {
			Hashtable dqFlag = (Hashtable) subSys.get(j);				

			//Check for null yourself!	
			String name = DBSUtil.get(dqFlag, "name");

			String value = get(dqFlag, "value", true);
			DBSUtil.writeLog("Flag: Name..."+name);
			DBSUtil.writeLog("Flag: Value..."+value);

                        creationDate = getTime(dqFlag, "creation_date", false);
                        cbUserID = personApi.getUserID(conn, get(dqFlag, "created_by"), dbsUser );

			//Let us insert THIS FLAG
			String subFlagID = insertDQFlag(conn, out, runID, lumiID,
                                                               name, value,
                                                               cbUserID, creationDate,
                                                               lmbUserID);

			//stow away the ID of latest insert for later use
			//String subFlagID = getDQFlagID(conn, runID, lumiID, name, value, true);

			//It may have a sub-sub-system vector to itself 
			Vector subsubSys = DBSUtil.getVector(dqFlag, "dq_sub_subsys");

			for (int k = 0; k < subsubSys.size() ; ++k) {
				Hashtable dqsubFlag = (Hashtable) subsubSys.get(k);
				//Check for null yourself! 
				String subname = DBSUtil.get(dqsubFlag, "name");

                               	String subvalue = get(dqsubFlag, "value", true);
                               	DBSUtil.writeLog("      Flag: Sub Name..."+subname);
                               	DBSUtil.writeLog("      Flag: Sub Value..."+subvalue);

				//Let us insert THIS FLAG
                               	String subsubID = insertDQFlag(conn, out, runID, lumiID,
                                                                subname, subvalue,
                                                                cbUserID, creationDate,
                                                                lmbUserID);

				//ID of latest insert, going tobe used right now	
				//String subsubID = getDQFlagID(conn, runID, lumiID, subname, subvalue, true);	
				//AND associate it to its parent 
				//(Thats done HARD CODED in SubSystem Table, So I do not need to do that anymore)
				//Afaq: 06/07/2007
				//insertMap(conn, out, "QFlagAssoc", "ThisFlag", "ItsAssoc",
                                //        subFlagID,
                                //        subsubID,
                                //        cbUserID, lmbUserID, creationDate);
			}
		}
	}
}
