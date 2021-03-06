/*
* @author anzar
 $Revision: 1.23 $"
 $Id: DBSConfig.java,v 1.23 2010/05/24 14:59:01 afaq Exp $"
*
A singleton that reads a config file from $DBS_HOME/etc
and creates a hash tables of k,v pairs there in.
The configuration file is read only ONCE, 
for any changes in the configuration, the server must restart.
Also the server reads what it wants to read, for example
database parameters, supported schema and client etc., 
and ignores other parameters from config file, if any.
*/

package dbs.util;

import xml.DBSXMLParser;
import xml.Element;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.System;
import java.lang.CloneNotSupportedException;
import java.util.Hashtable;
import java.util.Vector;
import dbs.DBSException;
import dbs.util.DBSUtil;

public class DBSConfig {

        //Doesn't make sence to put values in a Hashtable, as you need to know what you want
        private String dbUserName;
        private String dbUserPasswd;
        private String dbDriver;
        private String dbURL;
        //private String regServiceURL;
        //private String cfgServiceURL;
        private String siteServiceURL;
        private String hostcert;
        private String certpass;
        private String alias;
        private String adminEmail;
        private String adminDN;
        private String adminName;
        private String critical;
        private String phyLocation;
        private String supportedSchemaVersion;
        private String supportedClientVersions;
        private long maxBlockSize = 100000;
        private long maxBlockFiles = 100;
	private String schemaOwner;

        private static DBSConfig ref;


        //Having two different CTORs we can manage how the DBS is Configured, From an XML file
        // Or from a parameterized CTOR 
        //CURRENTLY NOT-USED 
        public static synchronized DBSConfig getInstance(String schemaVersion, String clientVersions)
        throws DBSException
         {
		 //System.out.println("get instance called schemaVersion, clientVersions");
           if (ref == null)
               ref = new DBSConfig(schemaVersion, clientVersions);
           return ref;
         }

        private DBSConfig(String schemaVersion, String clientVersions)
        throws DBSException
          {
		// System.out.println("New DBSConfig is beig intiitialzed.... schemaVersion, clientVersions");
           supportedSchemaVersion = schemaVersion;
           supportedClientVersions = clientVersions;
          }



        public static synchronized DBSConfig getInstance(String conf)
        throws DBSException
         {
		// System.out.println("get instance called conf");
           if (ref == null)
               ref = new DBSConfig(conf);
           return ref;
         }




        public static synchronized DBSConfig getInstance()
        throws DBSException
         {
		 //System.out.println("get instance called emtpy ");
           if (ref == null)
               ref = new DBSConfig(null);
           return ref;
         }

        private DBSConfig(String confPath)
        throws DBSException 
          {
		 //System.out.println("New DBSConfig is beig intiitialzed... ");

                String dbs_config = null;
                if ( confPath != null ) {
                   dbs_config = confPath;
                }
                else { 
                     // See if DBS_SERVER_CONFIG is provided when invoking JAVA
                     dbs_config = System.getProperty("DBS_SERVER_CONFIG");
                     //dbs_config = System.getenv("DBS_SERVER_CONFIG");
                }      
                if (dbs_config == null || dbs_config.equals("") ) {
                        throw new DBSException("Configuration Error", "1050", "Environment variable DBS_SERVER_CONFIG not set. OR DBS/META-INF/context.xml is missing or invalid in case you are running under TOMCAT ?");
                }
                FileInputStream property_file = null;

                try { 
                    //property_file = new FileInputStream(config_file);   
                    //Properties props = new Properties();
                    //props.load(property_file);

                    DBSXMLParser dbsParser = new DBSXMLParser();
                    dbsParser.parseFile(dbs_config);  
                    Vector allElement = dbsParser.getElements();
                    //Atleaste Resource, SupportedSchemaVersion, SupportedClientVersions nneds to be in DBS_SERVER_CONFIG
                    if (allElement.size() < 3) {
                       throw new DBSException("Configuration Error", "1060", "DBS_SERVER_CONFIG doesnot have all required parameters ?");  
                    }
                    
                    //Lets try to get all required parameters     
                    for (int i=0; i<allElement.size(); ++i) {
                       Element e = (Element)allElement.elementAt(i);
                       Hashtable atribs = e.attributes; 
                       String name = e.name; 
                       if ( name.equals("Resource") ) {   
                          dbUserName = (String)atribs.get("username");
                          dbUserPasswd = (String)atribs.get("password");
                          dbDriver = (String)atribs.get("driverClassName");
                          dbURL = (String)atribs.get("url");
                       }   

                       if ( name.equals("SupportedSchemaVersion") ) {
                          supportedSchemaVersion = (String)atribs.get("schemaversion");
                       }
		       /*if ( name.equals("Register") ) {
                          //regServiceURL = (String)atribs.get("service");
                          alias = (String)atribs.get("alias");
                          critical = (String)atribs.get("critical");
                          phyLocation = (String)atribs.get("phy_location");
			  adminEmail = (String)atribs.get("admin_email");
			  adminName = (String)atribs.get("admin_name");
			  adminDN = (String)atribs.get("admin_dn");
                       }*/
		//	if ( name.equals("Cfgindex") ) {
                //          cfgServiceURL = (String)atribs.get("service");
                //       }
		       if ( name.equals("Sitedb") ) {
                          siteServiceURL = (String)atribs.get("service");
                       }
		       if ( name.equals("Certificate") ) {
                          hostcert = (String)atribs.get("hostcert");
                          certpass = (String)atribs.get("certpass");
                       }




                       if ( name.equals("SupportedClientVersions") ){ 
                          supportedClientVersions = (String)atribs.get("clientversions");
                       } 

                       if ( name.equals("DBSBlockConfig") ){
               
                          String maxBlkSize = (String)atribs.get("maxBlockSize");
                          if (maxBlkSize == null ) {
                             throw new DBSException("Configuration Error", "1058", "maxBlockSize not found in Config File");
                          }
                          DBSUtil.writeLog("maxBlockSize: "+maxBlkSize);
                          maxBlockSize = Long.parseLong(maxBlkSize);

                          String maxBlkFiles = (String)atribs.get("maxBlockFiles");
                          if (maxBlkFiles == null ) {
                          throw new DBSException("Configuration Error", "1059", "maxBlockFiles not found in Config File");
                          }
                          DBSUtil.writeLog("maxBlkFiles: "+maxBlkFiles);
                          maxBlockFiles = Long.parseLong(maxBlkFiles);

                       }

                       if ( name.equals("SchemaOwner") ) {
                                schemaOwner = (String)atribs.get("schemaowner");
				if ( schemaOwner.equals("__MYSQL__") || schemaOwner.equals("") ) {
					schemaOwner = (String)("");
				} else {
					schemaOwner = schemaOwner+".";
				}
                       }
                    } //for loop
                    //Check to see if all parameters are read if not throw exception
  
                    if (dbUserName == null ) {
                      throw new DBSException("Configuration Error", "1052", "Database USERID not found in Config File");  
                    }
                    if (dbUserPasswd == null ) {
                      throw new DBSException("Configuration Error", "1053", "Database PASSWORD not found in Config File");
                    }
                    if (dbDriver == null ) {
                      throw new DBSException("Configuration Error", "1054", "Database DRIVER not found in Config File");
                    }
                     if (dbURL == null ) {
                      throw new DBSException("Configuration Error", "1055", "Database URL not found in Config File");
                    }                
                    if (supportedSchemaVersion == null ) {
                      throw new DBSException("Configuration Error", "1056", "Database SCHEMA_VERSION not found in Config File");
                    }
		    //if (regServiceURL == null ) {
                    //  throw new DBSException("Configuration Error", "1056", "Registration Service URL not found in Config File");
                    //}
		    //if (cfgServiceURL == null ) {
                    //  throw new DBSException("Configuration Error", "1056", "Config Index  Service URL not found in Config File");
                    //}
		    if (siteServiceURL == null ) {
                      throw new DBSException("Configuration Error", "1056", "Site DB  Service URL not found in Config File");
                    }
		    if (hostcert == null ) {
                      throw new DBSException("Configuration Error", "1056", "Host Certificate not found in Config File");
                    }
		    if (certpass == null ) {
                      throw new DBSException("Configuration Error", "1056", "Certificate Password not found in Config File");
                    }




		    //if (adminDN == null ) {
                     // throw new DBSException("Configuration Error", "1056", "Admin DN not found in Config File");
                    //}

                    if (supportedClientVersions == null ) {
                      throw new DBSException("Configuration Error", "1057", "Supported CLIENT_VERSIONS not found in Config File");
                    }
		    if (schemaOwner == null ) {
                      throw new DBSException("Configuration Error", "1058", "Database SchemaOwner not found in Config File");
                    } 

                    DBSUtil.writeLog("dbUserName: "+dbUserName);
                    //Lets NOT Print the Password even in the Log
                    //DBSUtil.writeLog("dbUserPasswd: "+dbUserPasswd);
                    DBSUtil.writeLog("dbDriver: "+dbDriver);
                    DBSUtil.writeLog("dbURL: "+dbURL);
                    DBSUtil.writeLog("supportedSchemaVersion: "+supportedSchemaVersion);
                    //DBSUtil.writeLog("regServiceURL: "+regServiceURL);
                    //DBSUtil.writeLog("cfgServiceURL: "+cfgServiceURL);
                    DBSUtil.writeLog("siteServiceURL: "+siteServiceURL);
                    DBSUtil.writeLog("hostcert: "+hostcert);
                    DBSUtil.writeLog("alias: "+alias);
                    DBSUtil.writeLog("supportedClientVersions: "+supportedClientVersions);
               	} catch (DBSException ex) {
		  throw ex;
                  //throw new DBSException("Configuration Error", "1051","Unable to read configuration file dbs.config"+
                  //                                                                                            ex.getM); 
               	} catch (Exception ex) {
                  throw new DBSException("Configuration Error", "1051","Unable to read configuration file dbs.config"+
                                                                                                              ex.getMessage());
		}
                finally {
                  if (property_file!=null) {
                      try {
                        property_file.close();
                      } catch (IOException ex) {
                        throw new DBSException("Serious Error", "1061","Unable to close configuration file dbs.config "+
                                                                                                              ex.getMessage());
                      }
                  }  
                }        
        }
        //Lets prevent the clones too.
        public Object clone()
	throws CloneNotSupportedException{

             throw new CloneNotSupportedException(); 
        }

        public String getDbUserName(){
           return dbUserName;
        }

        public String getDbUserPasswd() {
            return dbUserPasswd;
        }

        public String getDbDriver(){
            return dbDriver;
        }

        public String getDbURL() {
           return dbURL;
        }

        public String getSupportedSchemaVersion() {
            return supportedSchemaVersion;
        }

	//public String getRegServiceURL() {
        //    return regServiceURL;
        //}
	//public String getCfgServiceURL() {
        //    return cfgServiceURL;
        //}
	public String getSiteServiceURL() {
            return siteServiceURL;
        }
	public String getHostcert() {
            return hostcert;
        }
	public String getCertpass() {
            return certpass;
        }



	public String getAlias() {
            return alias;
        }
	public String getCritical() {
            return critical;
        }
	public String getPhyLocation() {
            return phyLocation;
        }

	public String getAdminEmail() {
            return adminEmail;
        }
	public String getAdminDN() {
            return adminDN;
        }
	public String getAdminName() {
            return adminName;
        }

        public String getSupportedClientVersions() {
            return supportedClientVersions;
        }

        public long getMaxBlockSize() {
            return maxBlockSize;
        }
        
        public long getMaxBlockFiles() {
            return maxBlockFiles; 
        }

	public String getSchemaOwner() {
	    return schemaOwner;
	}

        public static void main(String args[])
        {
           try{
                 DBSConfig config = DBSConfig.getInstance();

              }catch (DBSException ex) {
                System.out.println("EXECPTION RAISED: "+ex.getMessage()+" "+ex.getDetail()+" "+ex.getCode());
              }catch (Exception ex) {
                System.out.println("EXECPTION RAISED: "+ex.getMessage());
              }  
        } 
        
}

