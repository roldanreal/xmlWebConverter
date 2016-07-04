package ph.com.globe.csvparser.constant;

public class XMLIDElement {
	private static final String MSISDN = "MSISDN";
	private static final String ExternalProductID = "ExternalProductID";
	
	private String[] IDElementsStringArray = {MSISDN, ExternalProductID};

	public String[] getHeadersMassRequestDetailsStringArray() {
		return IDElementsStringArray;
	}
}
