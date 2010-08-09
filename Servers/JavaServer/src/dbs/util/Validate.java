/**
 $Revision: 1.1 $"
 *
 */

package dbs.util;
import java.util.regex.Pattern;
import dbs.DBSException;



public class Validate {
	//private static String SAFE_PATH = "[-A-Za-z0-9_./\\p{%}]";
	private static String SAFE_TIME = "[-0-9.]+";

	//A regular expression used to validate a path that will not contain any special characters or blank space but can contain slashes.
	private static String SAFE_PATH = "[-\\w_\\.%/]+";
	//A regular expression used to validate a word. This word will not contain any blank space or special characters.
	private static String SAFE_WORD = "[-\\w_\\.%]+";
	//A regular expression used to validate a sentence. This word will not contain any special characters but can contain blank spaces.
	private static String SAFE_STR = "[-\\w_\\.,% ]+";
	//A regular expression used to validate a block name. This word will not contain any special characters or blank spaces but can contain slashes.
	private static String SAFE_BLOCK = "[-\\w_\\.%#/]+";
	private static String SAFE_BLOCK_LIST = "[-\\w_\\.%#/\\%]+";
	//A regular expression used to validate a path that will contain exactly three slashes.
	private static String VALID_PATH = "^/([^/]+)/([^/]+)/([^/]+)";
	//A regular expression used to validate a block name that will contain exactly thw slashes and a hash.
	//private static String VALID_BLOCK = "^/([^/]+)/([^/]+)#([^/]+)";
	private static String VALID_BLOCK = "^/([^/]+)/([^/]+)/([^/]+)#([^/]+)";
	private static String VALID_BLOCK_DBS1 = "^/([^/]+)/([^/]+)#([^/]+)";
	//private static String VALID_BLOCK_LIST = "^/([^/]+)/([^/]+)#([^/]+)|%";
	private static String VALID_BLOCK_LIST = "^/([^/]+)/([^/]+)/([^/]+)#([^/]+)|%";
	//private static String VALID_BLOCK_LIST_DBS1 = "^/([^/]+)/([^/]+)#([^/]+)|%";
	public Validate() {}

       
	/**
	 * Checks the dataset path against a regular expression that validates a valid dataset path.
	 * @param path a dataset path in the format of /primary/tier/processed. If this path is not provided or the dataset id could not be found then an exception is thrown.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters are invalid.
	 */
	public static void checkPath(String path) throws Exception {
		if(DBSUtil.isNull(path)) 
			throw new DBSException("Missing data", "1006", "Null Fields. Expected a valid path in format /PRIMARY/PROCESSED/TIER1-TIER2");
		if (! Pattern.matches(VALID_PATH, path) ) 
			throw new DBSException("Invalid format", "1007", "Expected a path in format /PRIMARY/PROCESSED/TIER1-TIER2 which should satisfy the regular expression " + VALID_PATH + " The given path is " + path);
		if( ! Pattern.matches(SAFE_PATH, path) ) 
			throw new DBSException("Invalid format", "1013", "Invalid Characters in " + path + " for path. Expected a path in format /PRIMARY/PROCESSED/TIER1-TIER2  which should satisfy the regular expression  "+ SAFE_PATH);
	}
	
	/**
	 * Checks the dataset block name against a regular expression that validates a valid block name.
	 * @param blockName the name of the block in the format of /primary/processed#GUID. If this blockName is not provided or the block id could not be found then an exception is thrown.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters are invalid.
	 */

	public static void checkBlock(String blockName) throws Exception {
		if(DBSUtil.isNull(blockName)) 
			throw new DBSException("Missing data", "1006", "Null Fields. Expected a valid block_name in format /PRIMARY/PROCESSED/TIER1-TIER2#GUID");
		if ( !Pattern.matches(VALID_BLOCK, blockName) && !Pattern.matches(VALID_BLOCK_DBS1, blockName))   
			throw new DBSException("Invalid format", "1014", "Expected a block_name in format /PRIMARY/PROCESSED/TIER1-TIER2#GUID or /PRIMARY/PROCESSED#GUID which should satisfy the regular expression " + VALID_BLOCK + " or " + VALID_BLOCK_DBS1 + " The given block_name is " + blockName);
		if( ! Pattern.matches(SAFE_BLOCK, blockName) ) 
			throw new DBSException("Invalid format", "1015", "Invalid Characters in " + blockName + " for block_name. Expected a block_name in format /PRIMARY/PROCESSED/TIER1-TIER2#GUID which should satisfy the regular expression " + SAFE_BLOCK);
	}


        public static void checkBlock4List(String blockName) throws Exception {
                if(DBSUtil.isNull(blockName))
                        throw new DBSException("Missing data", "1006", "Null Fields. Expected a valid block_name in format /PRIMARY/PROCESSED#GUID");
               // if (! Pattern.matches(VALID_BLOCK_LIST, blockName) )
               //         throw new DBSException("Invalid format", "1014", "Expected a block_name in format /PRIMARY/PROCESSED#GUID which should satisfy the regular expression " + VALID_BLOCK_LIST + " The given block_name is " + blockName);
                if(! Pattern.matches(SAFE_BLOCK_LIST, blockName ))
                        throw new DBSException("Invalid format", "1022", "Invalid Characters in " + blockName + " for block_name. Expected a block_name in format /PRIMARY/PROCESSED#GUID which should satisfy the regular expression " + SAFE_BLOCK_LIST);
        }


	/**
	 * Checks a word as whole against a regular expression that validates a english word without any special characters.
	 * @param pattern the value of the word that needs to be validated.
	 * @param key the name of the key which is used to throw an exception in case the word fails to validate. This make the exception message more intutive as it states which key was being checked.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters are invalid.
	 */
	public static void checkWord(String pattern, String key) throws Exception {
		if(DBSUtil.isNull(pattern))
			throw new DBSException("Missing data", "1006", "Null Fields. Expected a valid " + key);
		//if (! Pattern.matches(SAFE_WORD, pattern)) 
		if (! Pattern.matches(SAFE_PATH, pattern)) 
			//throw new DBSException("Invalid format", "1016", "Invalid Characters in " + pattern + " for " + key + " Expected a valid " + key + " which should satisfy the regular expression "+ SAFE_WORD);
			throw new DBSException("Invalid format", "1016", "Invalid Characters in " + pattern + " for " + key + " Expected a valid " + key + " which should satisfy the regular expression "+ SAFE_PATH);
	}
	
	/**
	 * Checks a sentence  against a regular expression that validates a english sentence without any special characters.
	 * @param pattern the value of the word that needs to be validated.
	 * @param key the name of the key which is used to throw an exception in case the word fails to validate. This make the exception message more intutive as it states which key was being checked.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters are invalid.
	 */
        public static void checkString(String pattern, String key) throws Exception {
                if(DBSUtil.isNull(pattern))
                        throw new DBSException("Missing data", "1006", "Null Fields. Expected a valid " + key);
                if (! Pattern.matches(SAFE_STR, pattern))
                        throw new DBSException("Invalid format", "1017", "Invalid Characters in " + pattern + " for " + key + " Expected a valid " + key + " which should satisfy the regular expression " + SAFE_STR);
        }

	/**
	 * Checks the time against a regular expression that validates a long numberics without any special characters.
	 * @param pattern the value of the word that needs to be validated.
	 * @param key the name of the key which is used to throw an exception in case the word fails to validate. This make the exception message more intutive as it states which key was being checked.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters are invalid.
	 */
	public static void checkTime(String pattern, String key) throws Exception {
		if(DBSUtil.isNull(pattern))
			throw new DBSException("Missing data", "1006", "Null Fields. Expected a valid " + key);
		if (! Pattern.matches(SAFE_TIME, pattern)) 
			throw new DBSException("Invalid format", "1036", "Invalid Characters in " + pattern + " for " + key + " Expected a valid " + key + " which should satisfy the regular expression "+ SAFE_TIME);
	}


}
