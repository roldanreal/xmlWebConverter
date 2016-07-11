package ph.com.globe.csvparser.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class XMLOptionalConfigurationProperty {
	private static final String LOCK_IN_START_DATE = "LOCK_IN_START_DATE";
	private static final String LOCK_IN_END_DATE = "LOCK_IN_END_DATE";
	
	private String[] optionalConfigurationProperties = {"Lock_In_Start_Date", "Lock_In_End_Date"};
	
	private ArrayList<String> xmlOptionalConfigurationPropertyList = new ArrayList<String>();
	private Map<String, String> xmlOptionalConfigurationPropertyMap = new HashMap<String, String>();

	public String[] getOptionalConfigurationProperties() {
		return optionalConfigurationProperties;
	}
	
	public ArrayList<String> getXmlOptionalConfigurationPropertyList() {
		xmlOptionalConfigurationPropertyList.add(LOCK_IN_START_DATE);
		xmlOptionalConfigurationPropertyList.add(LOCK_IN_END_DATE);
		
		
		return xmlOptionalConfigurationPropertyList;
	}
	
	public Map<String, String> getXmlOptionalConfigurationPropertyMap() {
		xmlOptionalConfigurationPropertyMap.put(LOCK_IN_START_DATE, "Lock_In_Start_Date");
		xmlOptionalConfigurationPropertyMap.put(LOCK_IN_END_DATE, "Lock_In_End_Date");
	
		return xmlOptionalConfigurationPropertyMap;
	}
	
}
