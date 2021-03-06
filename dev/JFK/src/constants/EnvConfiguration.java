package constants;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;




public class EnvConfiguration {
	public static enum ConfigID {
		LOGIN_ENABLED, USER_NAME, USER_PASSWORD, USER_MAIL, URLBASE, HOST
	}
	
	private static final Map<ConfigID, Object> configuration = new HashMap<ConfigID, Object>();
	
	public static void configure(final String propertiesFile) throws Exception {
		Properties properties = new Properties();
		FileInputStream stream;
		// load properties file
		if (new File(new File(propertiesFile).getParent() + "/offline").exists()) {
//			StringReader sr = new StringReader(Base64Util.decodeFile(propertiesFile));
//			properties.load(sr);
//			sr.close();
		} else {
			stream = new FileInputStream(propertiesFile);
			properties.load(stream);
			stream.close();
		}
		
		configuration.put(ConfigID.LOGIN_ENABLED, Boolean.parseBoolean(properties.getProperty("login_enabled", "false")));
		configuration.put(ConfigID.USER_NAME, properties.getProperty("user_name"));
		configuration.put(ConfigID.USER_PASSWORD, properties.getProperty("user_password"));
		configuration.put(ConfigID.USER_MAIL, properties.getProperty("user_mail"));
		configuration.put(ConfigID.URLBASE, properties.getProperty("urlbase"));
		configuration.put(ConfigID.HOST, properties.getProperty("host"));
		
	}
	
	public static String getUrlBase() {
		return (String) configuration.get(ConfigID.URLBASE);
	}
	
	public static String getHostAndBase() {
		return (String) configuration.get(ConfigID.HOST) + (String) configuration.get(ConfigID.URLBASE);
	}

	public static String getDefaultUsername() {
		return (String) configuration.get(ConfigID.USER_NAME);
	}
	
	public static String getDefaultUserPassword(){
		return (String) configuration.get(ConfigID.USER_PASSWORD);
	}
}
