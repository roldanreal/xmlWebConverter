package ph.com.globe.csvparser.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class XMLMassRequestHeader {
	private static final String EXTERNAL_MASS_REQUEST_ID = "EXTERNAL_MASS_REQUEST_ID";
	private static final String MASS_REQUEST_TYPE = "MASS_REQUEST_TYPE";
	private static final String REQUEST_EXECUTION_DATE = "REQUEST_EXECUTION_DATE";
	private static final String REQUEST_CREATOR = "REQUEST_CREATOR";
	private static final String REQUEST_CREATION_DATE = "REQUEST_CREATION_DATE";
	private static final String DATE_FORMAT = "DATE_FORMAT";
	
	private ArrayList<String> xmlMassHeaderList = new ArrayList<String>();
	private Map<String, String> xmlMassHeaderMap = new HashMap<String, String>();
	private String[] headersStringArray = {"externalMassRequestID", "massRequestType",  "requestDescription",
			"requestExecutionDate", "requestCreator", "requestCreationDate", "numberOfRequestLines", "dateFormat"};

	public ArrayList<String> getXmlMassHeaderList() {
		xmlMassHeaderList.add(EXTERNAL_MASS_REQUEST_ID);
		xmlMassHeaderList.add(MASS_REQUEST_TYPE);
		xmlMassHeaderList.add(REQUEST_EXECUTION_DATE);
		xmlMassHeaderList.add(REQUEST_CREATOR);
		xmlMassHeaderList.add(REQUEST_CREATION_DATE);
		xmlMassHeaderList.add(DATE_FORMAT);
		
		return xmlMassHeaderList;
	}
	
	public Map<String, String> getXmlHeaderMap() {
		xmlMassHeaderMap.put(EXTERNAL_MASS_REQUEST_ID, "externalMassRequestID");
		xmlMassHeaderMap.put(MASS_REQUEST_TYPE, "massRequestType");
		xmlMassHeaderMap.put(REQUEST_EXECUTION_DATE, "requestExecutionDate");
		xmlMassHeaderMap.put(REQUEST_CREATOR, "requestCreator");
		xmlMassHeaderMap.put(REQUEST_CREATION_DATE, "requestCreationDate");
		xmlMassHeaderMap.put(DATE_FORMAT, "dateFormat");
	
		return xmlMassHeaderMap;
	}
	
	public String[] getHeadersStringArray() {
		return headersStringArray;
	}
}
