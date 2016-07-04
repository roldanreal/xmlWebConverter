package ph.com.globe.csvparser.constant;

public class XMLOptionalConfigurationProperty {
	private static final String LOCK_IN_START_DATE = "LOCK_IN_START_DATE";
	private static final String LOCK_IN_END_DATE = "LOCK_IN_END_DATE";
	
	private String[] optionalConfigurationProperties = {LOCK_IN_START_DATE, LOCK_IN_END_DATE};

	public String[] getOptionalConfigurationProperties() {
		return optionalConfigurationProperties;
	}
}
