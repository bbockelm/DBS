/*
* @author anzar
 $Revision: 1.16 $"
 $Id: MSConfig.java,v 1.16 2007/11/15 21:02:31 sekhri Exp $"
*
A singleton that reads a config file from $MS_HOME/etc
and creates a hash tables of k,v pairs there in.
The configuration file is read only ONCE, 
for any changes in the configuration, the server must restart.
Also the server reads what it wants to read, for example
database parameters, supported schema and client etc., 
and ignores other parameters from config file, if any.
*/

package ms.util;

import xml.DBSXMLParser;
import xml.Element;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.System;
import java.lang.CloneNotSupportedException;
import java.util.Hashtable;
import java.util.Vector;
import ms.MSException;
import ms.util.MSUtil;

public class MSConfig {

        //Doesn't make sence to put values in a Hashtable, as you need to know what you want
        private String dbUserName;
        private String dbUserPasswd;
        private String dbDriver;
        private String dbURL;
        private String supportedSchemaVersion;
        private String supportedClientVersions;
        private long maxBlockSize = 100000;
        private long maxBlockFiles = 100;

        private static MSConfig ref;


        //Having two different CTORs we can manage how the MS is Configured, From an XML file
        // Or from a parameterized CTOR 
        //CURRENTLY NOT-USED 
        public static synchronized MSConfig getInstance(String schemaVersion, String clientVersions)
        throws MSException
         {
		 //System.out.println("get instance called schemaVersion, clientVersions");
           if (ref == null)
               ref = new MSConfig(schemaVersion, clientVersions);
           return ref;
         }

        private MSConfig(String schemaVersion, String clientVersions)
        throws MSException
          {
		// System.out.println("New MSConfig is beig intiitialzed.... schemaVersion, clientVersions");
           supportedSchemaVersion = schemaVersion;
           supportedClientVersions = clientVersions;
          }



        public static synchronized MSConfig getInstance(String conf)
        throws MSException
         {
		// System.out.println("get instance called conf");
           if (ref == null)
               ref = new MSConfig(conf);
           return ref;
         }




        public static synchronized MSConfig getInstance()
        throws MSException
         {
		 //System.out.println("get instance called emtpy ");
           if (ref == null)
               ref = new MSConfig(null);
           return ref;
         }

        private MSConfig(String confPath)
        throws MSException 
          {
		 //System.out.println("New MSConfig is beig intiitialzed... ");

                String ms_config = null;
                if ( confPath != null ) {
                   ms_config = confPath;
                }
                else { 
                     // See if MS_SERVER_CONFIG is provided when invoking JAVA
                     ms_config = System.getProperty("MS_SERVER_CONFIG");
                     //ms_config = System.getenv("MS_SERVER_CONFIG");
                }      
                if (ms_config == null || ms_config.equals("") ) {
                        throw new MSException("Configuration Error", "1050", "Environment variable MS_SERVER_CONFIG not set. OR MS/META-INF/context.xml is missing or invalid in case you are running under TOMCAT ?");
                }
                FileInputStream property_file = null;

                try { 
                    //property_file = new FileInputStream(config_file);   
                    //Properties props = new Properties();
                    //props.load(property_file);

                    DBSXMLParser msParser = new DBSXMLParser();
                    msParser.parseFile(ms_config);  
                    Vector allElement = msParser.getElements();
                    //Atleaste Resource, SupportedSchemaVersion, SupportedClientVersions nneds to be in MS_SERVER_CONFIG
                    if (allElement.size() < 3) {
                       throw new MSException("Configuration Error", "1060", "MS_SERVER_CONFIG doesnot have all required parameters ?");  
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

                       if ( name.equals("MSBlockConfig") ){
               
                          String maxBlkSize = (String)atribs.get("maxBlockSize");
                          if (maxBlkSize == null ) {
                             throw new MSException("Configuration Error", "1058", "maxBlockSize not found in Config File");
                          }
                          MSUtil.writeLog("maxBlockSize: "+maxBlkSize);
                          maxBlockSize = Long.parseLong(maxBlkSize);

                          String maxBlkFiles = (String)atribs.get("maxBlockFiles");
                          if (maxBlkFiles == null ) {
                          throw new MSException("Configuration Error", "1059", "maxBlockFiles not found in Config File");
                          }
                          MSUtil.writeLog("maxBlkFiles: "+maxBlkFiles);
                          maxBlockFiles = Long.parseLong(maxBlkFiles);

                       }

                    } //for loop
                    //Check to see if all parameters are read if not throw exception
   
                    if (dbUserName == null ) {
                      throw new MSException("Configuration Error", "1052", "Database USERID not found in Config File");  
                    }
                    if (dbUserPasswd == null ) {
                      throw new MSException("Configuration Error", "1053", "Database PASSWORD not found in Config File");
                    }
                    if (dbDriver == null ) {
                      throw new MSException("Configuration Error", "1054", "Database DRIVER not found in Config File");
                    }
                     if (dbURL == null ) {
                      throw new MSException("Configuration Error", "1055", "Database URL not found in Config File");
                    }                
                    if (supportedSchemaVersion == null ) {
                      throw new MSException("Configuration Error", "1056", "Database SCHEMA_VERSION not found in Config File");
                    }
                    if (supportedClientVersions == null ) {
                      throw new MSException("Configuration Error", "1057", "Supported CLIENT_VERSIONS not found in Config File");
                    }

                    MSUtil.writeLog("dbUserName: "+dbUserName);
                    //Lets NOT Print the Password even in the Log
                    //MSUtil.writeLog("dbUserPasswd: "+dbUserPasswd);
                    MSUtil.writeLog("dbDriver: "+dbDriver);
                    MSUtil.writeLog("dbURL: "+dbURL);
                    MSUtil.writeLog("supportedSchemaVersion: "+supportedSchemaVersion);
                    MSUtil.writeLog("supportedClientVersions: "+supportedClientVersions);

                } catch (Exception ex) {
                  throw new MSException("Configuration Error", "1051","Unable to read configuration file ms.config"+
                                                                                                              ex.getMessage()); 
                }
                finally {
                  if (property_file!=null) {
                      try {
                        property_file.close();
                      } catch (IOException ex) {
                        throw new MSException("Serious Error", "1061","Unable to close configuration file ms.config "+
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

        public long getMaxBlockSize() {
            return maxBlockSize;
        }
        
        public long getMaxBlockFiles() {
            return maxBlockFiles; 
        }

        public static void main(String args[])
        {
           try{
                 MSConfig config = MSConfig.getInstance();

              }catch (MSException ex) {
                System.out.println("EXECPTION RAISED: "+ex.getMessage()+" "+ex.getDetail()+" "+ex.getCode());
              }catch (Exception ex) {
                System.out.println("EXECPTION RAISED: "+ex.getMessage());
              }  
        } 
        
}

