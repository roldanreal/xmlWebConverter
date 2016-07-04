
package ph.com.globe.csvparser.constant;

public class XMLDynamicProperty {
	private static final String EpcTargetOfferID = "EpcTargetOfferID";
	private static final String epcOfferID = "epcOfferID";
	private static final String CustomerID = "CustomerID";
	private static final String ActivityNameforRules = "ActivityNameforRules";
	
	private String[] dynamicProperties = {EpcTargetOfferID, epcOfferID, CustomerID, ActivityNameforRules};

	public String[] getDynamicProperties() {
		return dynamicProperties;
	}
}
