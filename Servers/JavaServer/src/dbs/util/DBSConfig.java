/*
* @author anzar
 $Revision: 1.6 $"
 $Id: DBSConfig.java,v 1.6 2006/12/10 01:53:50 afaq Exp $"
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
        private String supportedSchemaVersion;
        private String supportedClientVersions;
        private int maxBlockSize;
        private int maxBlockFiles;

        private static DBSConfig ref;

        public static synchronized DBSConfig getInstance()
        throws DBSException
         {
           if (ref == null)
               ref = new DBSConfig();
           return ref;
         }

        private DBSConfig()
        throws DBSException 
          {
                FileInputStream property_file = null;
                // See if DBS_HOME is set
                String dbs_config = null;
                dbs_config = System.getenv("DBS_SERVER_CONFIG");
                if (dbs_config == null || dbs_config.equals("") ) {
                   throw new DBSException("Configuration Error", "1050", "Environment variable DBS_SERVER_CONFIG not set ?");
                }

                try { 
                    //property_file = new FileInputStream(config_file);   
                    //Properties props = new Properties();
                    //props.load(property_file);

                    DBSXMLParser dbsParser = new DBSXMLParser();
                    dbsParser.parseFile(dbs_config);  
                    Vector allElement = dbsParser.getElements();
                    //Atleaste Resource, SupportedSchemaVersion, SupportedClientVersions nneds to be in DBS_SERVER_CONFIG
                    if (allElement.size() < 3) {
                       throw new DBSException("Configuration Error", "1050", "DBS_SERVER_CONFIG doesnot have all required parameters ?");  
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
                       if ( name.equals("SupportedClientVersions") ){ 
                          supportedClientVersions = (String)atribs.get("clientversions");
                       } 

                       if ( name.equals("DBSBlockConfig") ){
               
                          String maxBlkSize = (String)atribs.get("maxBlockSize");
                          if (maxBlkSize == null ) {
                             throw new DBSException("Configuration Error", "1058", "maxBlockSize not found in Config File");
                          }
                          maxBlockSize = Integer.parseInt(maxBlkSize);

                          String maxBlkFiles = (String)atribs.get("maxBlockFiles");
                          if (maxBlkFiles == null ) {
                          throw new DBSException("Configuration Error", "1059", "maxBlockFiles not found in Config File");
                          }
                          maxBlockFiles = Integer.parseInt(maxBlkFiles);

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
                      throw new DBSException("Configuration Error", "1056", "Database SChEMA_VERSION not found in Config File");
                    }
                    if (supportedClientVersions == null ) {
                      throw new DBSException("Configuration Error", "1057", "Supported CLIENT_VERSIONS not found in Config File");
                    }
                    /*if (maxBlockSize == null ) {
                      throw new DBSException("Configuration Error", "1058", "maxBlockSize not found in Config File");
                    }
                       if (maxBlockFiles == null ) {
                      throw new DBSException("Configuration Error", "1059", "maxBlockFiles not found in Config File");
                    }*/

                    DBSUtil.writeLog("dbUserName: "+dbUserName); 
                    DBSUtil.writeLog("dbUserName: "+dbUserName);
                    DBSUtil.writeLog("dbUserPasswd: "+dbUserPasswd);
                    DBSUtil.writeLog("dbDriver: "+dbDriver);
                    DBSUtil.writeLog("dbURL: "+dbURL);
                    DBSUtil.writeLog("supportedSchemaVersion: "+supportedSchemaVersion);
                    DBSUtil.writeLog("supportedClientVersions: "+supportedClientVersions);

                //} catch (IOException ex) {
                //  throw new DBSException("Configuration Error", "1051","Unable to read configuration file dbs.config "+  ex.getMessage()); 
                //}catch (DBSException ex) {
                //  throw new DBSException(ex.getMessage(), ex.getCode(), ex.getDetail());
                 
                } catch (Exception ex) {
                  throw new DBSException("Configuration Error", "1051","Unable to read configuration file dbs.config"+
                                                                                                              ex.getMessage()); 
                }
                finally {
                  if (property_file!=null) {
                      try {
                        property_file.close();
                      } catch (IOException ex) {
                        throw new DBSException("Serious Error", "1053","Unable to close configuration file dbs.config "+
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

        public String getSupportedClientVersions() {
            return supportedClientVersions;
        }

        public int getMaxBlockSize() {
            return maxBlockSize;
        }
        
        public int getMaxBlockFiles() {
            return maxBlockFiles; 
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

