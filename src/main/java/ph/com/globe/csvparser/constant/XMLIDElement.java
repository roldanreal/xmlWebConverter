package ph.com.globe.csvparser.constant;

import java.util.HashMap;
import java.util.Map;

public class XMLIDElement {
	private static final String MSISDN = "MSISDN";
	
	private String[] IDElementsStringArray = {MSISDN};

	public String[] getIdElementsArray() {
		return IDElementsStringArray;
	}
	
	Map<String,String> xmlIdElementsMap = new HashMap<String,String>();
	
	public Map<String, String> getXmlIDElementsMap() {
		xmlIdElementsMap.put(MSISDN, "ExternalProductID");
		return xmlIdElementsMap;
	}
}
