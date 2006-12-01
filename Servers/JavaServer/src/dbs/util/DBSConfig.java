/*
* @author anzar
 $Revision: 1.6 $"
 $Id: DBSConfig.java,v 1.6 2006/11/30 16:29:37 anzar Exp $"
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

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.System;
import java.lang.CloneNotSupportedException;
import dbs.DBSException;

public class DBSConfig {

        //Doesn't make sence to put values in a Hashtable, as you need to know what you want
        private String dbUserName;
        private String dbUserPasswd;
        private String dbDriver;
        private String dbURL;
        private String supportedSchemaVersion;
        private String supportedClientVersions;
 
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
                String dbs_home = null;
                dbs_home = System.getenv("DBS_HOME");
                if (dbs_home == null || dbs_home.equals("") ) {
                   throw new DBSException("Configuration Error", "1050", "Environment variable DBS_HOME not set ?");
                }

                String config_file = dbs_home + "/etc/dbs.config";
                try { 
                    property_file = new FileInputStream(config_file);   
                    Properties props = new Properties();
                    props.load(property_file);
                    dbUserName = props.getProperty("USERID", "");
                    if (dbUserName.equals("")) {
                      throw new DBSException("Configuration Error", "1052", "Database USERID not found in Config File");  
                    }

                    dbUserPasswd = props.getProperty("PASSWORD", "");
                    if (dbUserPasswd.equals("")) {
                      throw new DBSException("Configuration Error", "1053", "Database PASSWORD not found in Config File");
                    }

                    dbDriver = props.getProperty("DRIVER", "");
                    if (dbDriver.equals("")) {
                      throw new DBSException("Configuration Error", "1054", "Database DRIVER not found in Config File");
                    }

                    dbURL = props.getProperty("URL", "");
                    if (dbURL.equals("")) {
                      throw new DBSException("Configuration Error", "1055", "Database URL not found in Config File");
                    }

                    supportedSchemaVersion = props.getProperty("SCHEMA_VERSION", "");
                    if (supportedSchemaVersion.equals("")) {
                      throw new DBSException("Configuration Error", "1056", "Database SChEMA_VERSION not found in Config File");
                    }

                    supportedClientVersions = props.getProperty("CLIENT_VERSIONS", "");
                    if (supportedClientVersions.equals("")) {
                      throw new DBSException("Configuration Error", "1057", "Supported CLIENT_VERSIONS not found in Config File");
                    }
                     
                    System.out.println(dbUserName);
                    System.out.println(dbUserPasswd);
                    System.out.println(dbDriver);
                    System.out.println(dbURL);
                    System.out.println(supportedSchemaVersion);
                    System.out.println(supportedClientVersions);

                } catch (IOException ex) {
                  throw new DBSException("Configuration Error", "1051","Unable to read configuration file dbs.config "+
                                                                                                              ex.getMessage()); 
                }catch (DBSException ex) {
                  throw new DBSException(ex.getMessage(), ex.getCode(), ex.getDetail());
                 
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

