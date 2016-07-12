package ph.com.globe.csvparser.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ph.com.globe.csvparser.constant.MassRequestTypes;
import ph.com.globe.csvparser.constant.XMLDynamicProperty;
import ph.com.globe.csvparser.constant.XMLIDElement;
import ph.com.globe.csvparser.constant.XMLMassRequestDetails;
import ph.com.globe.csvparser.constant.XMLOptionalConfigurationProperty;
import ph.com.globe.csvparser.constant.XMLSubProductIDElement;
import ph.com.globe.csvparser.constant.XMLTags;
import ph.com.globe.csvparser.constant.XMLMassRequestHeader;

/**
 * 
 * @author rreal
 * This class contains methods that get header parameters that is constant to all MSISDNs
 *
 */
public class XMLPartUtil {
		    
	public Map<String, String> getXmlMassRequestHeaders(String[] tags, String[] values, Integer requestLineSize) {
    	XMLMassRequestHeader headers = new XMLMassRequestHeader();
    	ArrayList<String> xmlMassRequestHeadersList = headers.getXmlMassHeaderList();
    	Map<String, String> xmlMassRequestHeadersMap = headers.getXmlHeaderMap();
    	Map<String, String> headersMap = new HashMap<String, String>();
    	
    	Integer valueIndex = 0;
    	String requestDescription = null;
    	for(String tag: tags) {
    		for(String xmlMassRequestHeader: xmlMassRequestHeadersList) {
    			if(tag.equals(xmlMassRequestHeader)) {
    				if(values[valueIndex].equals(MassRequestTypes.CHANGE_CONFIGURATION)) {
    					requestDescription = "Update";
    				}
    				String xmlMassheader = xmlMassRequestHeadersMap.get(xmlMassRequestHeader);
    				headersMap.put(xmlMassheader, values[valueIndex]);
    				break;
    			}
    		}
    		valueIndex++;
    	}
    	//add request line size
    	headersMap.put("numberOfRequestLines", requestLineSize.toString());
    	
    	if(requestDescription!=null) {
    		headersMap.put("requestDescription", requestDescription);
    		
    	}
		return headersMap;
    }
	
	public void printMassRequestHeader(PrintWriter writer, Map<String, String> xmlMassRequestHeadersMap) {
    	XMLMassRequestHeader header = new XMLMassRequestHeader();
    	String[] headersStringArray = header.getHeadersStringArray();
    	writer.append("\t\t\t\t" + XMLTags.header_massRequestHeader);
    	for(String headerName: headersStringArray) {
        	String value = xmlMassRequestHeadersMap.get(headerName);
        	if(value != null) {
        		writer.append(" " + headerName + "=\"" + value +"\"");
        	}
        }
    	writer.append("/>");
    }
	
	public Map<String, String> getXmlMassRequestDetails(String[] tags, String[] values) {
    	XMLMassRequestDetails details = new XMLMassRequestDetails();
    	String[] xmlMassRequestDetailsList = details.getHeadersMassRequestDetailsStringArray();
    	Map<String, String> detailsMap = new HashMap<String, String>();
    	
    	Integer valueIndex = 0;
    	
    	for(String tag: tags) {
    		for(String detail: xmlMassRequestDetailsList) {
    			if(tag.equals(detail)) {
    				detailsMap.put(tag, values[valueIndex]);
    			}
    		}
    		valueIndex++;
    	}
    	return detailsMap;	
    }  
	
	public void printMassRequestDetails(PrintWriter writer, Map<String, String> xmlMassRequestDetailsMap) {
    	XMLMassRequestDetails header = new XMLMassRequestDetails();
    	String[] headersStringArray = header.getHeadersMassRequestDetailsStringArray();
    	writer.append("\n");
    	writer.append("\t\t\t\t" + XMLTags.header_massRequestDetails);
    	for(String headerName: headersStringArray) {
        	String value = xmlMassRequestDetailsMap.get(headerName);
        	if(value != null) {
        		writer.append(" " + headerName + "=\"" + value +"\"");
        	}
        }
    	writer.append(">");
    }
	
	public void printOptionalConfigurationItem(PrintWriter writer, Map<String,String> xmlMassRequestHeadersMap) {
		String value = (xmlMassRequestHeadersMap.get("massRequestType").equals(MassRequestTypes.CHANGE_CONFIGURATION)) ? "UP" : "AD";
		writer.append("\n\t\t\t\t\t" + XMLTags.header_optionalConfigurationItem + " action=\"" + value +"\">");
    }
	
	public Map<String, String> getXMLSubProductIDElement(String[] tags, String[] values) {
		XMLSubProductIDElement spIdElement = new XMLSubProductIDElement();
    	String[] xmlSubProductIDElementList = spIdElement.getXMLSubProductIDElementStringArray();
    	Map<String, String> xmlSubProductIDElementMap = new HashMap<String, String>();
    	
    	Integer valueIndex = 0;
    	
    	for(String tag: tags) {
    		for(String subproductIdElement: xmlSubProductIDElementList) {
    			if(tag.equals(subproductIdElement)) {
    				xmlSubProductIDElementMap.put(tag, values[valueIndex]);
    			}
    		}
    		valueIndex++;
    	}
    	return xmlSubProductIDElementMap;	
    }
	
	public void printXMLSubProductIDElement(PrintWriter writer, Map<String, String> xmlSubProductIDElementMap) {
		XMLSubProductIDElement spIdElement = new XMLSubProductIDElement();
    	String[] headersStringArray = spIdElement.getXMLSubProductIDElementStringArray();
    	writer.append("\n");
    	writer.append("\t\t\t\t\t\t" + XMLTags.header_subProductIDElement);
    	for(String headerName: headersStringArray) {
        	String value = xmlSubProductIDElementMap.get(headerName);
        	if(value != null) {
        		writer.append(" value=\"" + value +  "\" type=\"" + headerName +"\"/>");
        	}
        }
    }
	
	public Map<String, String> getXMLOptionalConfigurationProperty(String[] tags, String[] values) {
		XMLOptionalConfigurationProperty ocProperty = new XMLOptionalConfigurationProperty();
		ArrayList<String> xmlOptionalConfigurationPropertyList = ocProperty.getXmlOptionalConfigurationPropertyList();
    	Map<String, String> xmlOptionalConfigurationPropertyMap = ocProperty.getXmlOptionalConfigurationPropertyMap();
    	Map<String, String> xmlOptionalConfigurationProperty = new HashMap<String, String>();
    	
    	Integer valueIndex = 0;
    	
    	for(String tag: tags) {
    		for(String optionalConfigProperty: xmlOptionalConfigurationPropertyList) {
    			if(tag.equals(optionalConfigProperty)) {
    				String xmlTag = xmlOptionalConfigurationPropertyMap.get(tag);
    				xmlOptionalConfigurationProperty.put(xmlTag, values[valueIndex]);
    				break;
    			}
    		}
    		valueIndex++;
    	}
    	
    	return xmlOptionalConfigurationProperty;	
    }
	
	public void printXMLOptionalConfigurationProperty(PrintWriter writer, Map<String, String> xmlOptionalConfigurationProperty) {
		XMLOptionalConfigurationProperty ocProperty = new XMLOptionalConfigurationProperty();
    	String[] headersStringArray = ocProperty.getOptionalConfigurationProperties();
    	for(String headerName: headersStringArray) {
        	String value = xmlOptionalConfigurationProperty.get(headerName);
        	if(value != null) {
        		 writer.append("\n");
                 writer.append("\t\t\t\t\t\t" + XMLTags.header_optionalConfigurationProperty  + " propertyName=\"" + headerName   + "\"" + " propertyValue=\"" + value +"\"/>");
        	}
        }
    }
	
	public Map<String, String> getXMLDynamicProperty(String[] tags, String[] values) {
		XMLDynamicProperty dynamicProperty = new XMLDynamicProperty();
    	String[] xmlDynamicPropertyList = dynamicProperty.getDynamicProperties();
    	Map<String, String> xmlDynamicPropertyMap = new HashMap<String, String>();
    	
    	Integer valueIndex = 0;
    	
    	for(String tag: tags) {
    		for(String xmlDynamicProperty: xmlDynamicPropertyList) {
    			if(tag.equals(xmlDynamicProperty)) {
    				xmlDynamicPropertyMap.put(tag, values[valueIndex]);
    			}
    		}
    		valueIndex++;
    	}
    	return xmlDynamicPropertyMap;	
    }
	
	public void printXMLDynamicProperty(PrintWriter writer, Map<String, String> xmlDynamicProperty) {
		XMLDynamicProperty dynamicProperty = new XMLDynamicProperty();
    	String[] headersStringArray = dynamicProperty.getDynamicProperties();
    	for(String headerName: headersStringArray) {
        	String value = xmlDynamicProperty.get(headerName);
        	if(value != null) {
        		 writer.append("\n");
                 writer.append("\t\t\t\t\t" + XMLTags.header_dynamicProperty + " name=\"" + headerName +"\"" + " value=\"" +  value  + "\"/>");
        	}
        }
    }
	
	public Map<String, ArrayList<String>> getXMLIdElementsMap(String[] tags, List<String> listValue) {
		//test here
		XMLIDElement idElement = new XMLIDElement();
    	String[] xmlIdElementList = idElement.getIdElementsArray();
    	Map<String, String> xmlIdElementMap = idElement.getXmlIDElementsMap();
    	Map<String, ArrayList<String>> xmlIdElementValuesMap = new HashMap<String, ArrayList<String>>();
    	List<String> msisdnList = new ArrayList<String>();
    	String headerName = "";
    	
    	Integer valueIndex = 0;
    	
    	for(String tag: tags) {
    		for(String xmlIdElement: xmlIdElementList) {
    			if(tag.equals(xmlIdElement)) {
    				headerName = xmlIdElementMap.get(xmlIdElement);
    				//loop through listValue and get all MSISDNs
    				for(String value: listValue) {
    					if((valueIndex+1) > value.split("\\,").length)
    						msisdnList.add(null);
    					else
    						msisdnList.add(value.split("\\,")[valueIndex]);
    				}
    				break;
    			}
    		}
    		valueIndex++;
    	}
    	
    	xmlIdElementValuesMap.put(headerName, (ArrayList<String>) msisdnList);
    	
    	return xmlIdElementValuesMap;
		
	}
	
	public void printXMLIdElement(PrintWriter writer, Map<String, ArrayList<String>> xmlIdElementMap) {
		Iterator<Entry<String, ArrayList<String>>> entries = xmlIdElementMap.entrySet().iterator();
			while (entries.hasNext()) {
				Entry<String, ArrayList<String>> entry = entries.next();
				String header = entry.getKey();
				ArrayList<String> msisdnList = entry.getValue();
				int ctr = 0;
				for(String msisdn: msisdnList) {
					writer.append("\n");
                	writer.append("\t\t\t\t" +  XMLTags.header_massLineInfo + " requestLineNumber=\"" + ++ctr + "\">");
                	writer.append("\n");
                	writer.append("\t\t\t\t\t" +  XMLTags.header_idElement + " value=\"" + msisdn + "\" type=\"" + header + "\"/>");
                	writer.append("\n");
                	writer.append("\t\t\t\t" +  XMLTags.footer_massLineInfo);
		    	}
			}
	}
	
	public void printXML(File xmlFile, Map<String, String> xmlMassRequestHeadersMap, Map<String, String> xmlMassRequestDetailsMap,
			Map<String, String> xmlSubProductIDElementMap, Map<String, String> xmlOptionalConfigurationProperty,
			Map<String, String> xmlDynamicPropertyMap, Map<String, ArrayList<String>> xmlIdElementMap) {
		
		XMLPartUtil util = new XMLPartUtil();
		PrintWriter writer;
		try {
			writer = new PrintWriter(xmlFile);
			writer.append(XMLTags.header_handleMassOperation_top);
	        writer.append("\n");
	        writer.append("\t" + XMLTags.header_interface);
	        writer.append("\n");
	        writer.append("\t\t" + XMLTags.header_handleMassOperation);
	        writer.append("\n");
	        writer.append("\t\t\t" + XMLTags.header_handleMassOperationRequest);
	        writer.append("\n");
	       
	        //Add MassRequestHeader parameters
	        util.printMassRequestHeader(writer, xmlMassRequestHeadersMap);

	        //Add MassRequestDetails header and its parameters
	        util.printMassRequestDetails(writer, xmlMassRequestDetailsMap);
	        
	        //Add OptionalConfigurationItem and its parameter
	        util.printOptionalConfigurationItem(writer, xmlMassRequestHeadersMap);
	        
	        //Add SubProductIDElement and its parameter
	        util.printXMLSubProductIDElement(writer, xmlSubProductIDElementMap);
	        
	        //Add OptionalConfigurationProperty and its parameter
	        util.printXMLOptionalConfigurationProperty(writer, xmlOptionalConfigurationProperty);
	        
	        writer.append("\n");
	        writer.append("\t\t\t\t\t" +  XMLTags.footer_optionalConfigurationItem);
	        
	        //Add DynamicProperty and its paramater
	        util.printXMLDynamicProperty(writer, xmlDynamicPropertyMap);
	        
	        writer.append("\n");
	        writer.append("\t\t\t\t" +  XMLTags.footer_massRequestDetails);
	        
	        //Add MassLineInfo
	        util.printXMLIdElement(writer, xmlIdElementMap);
	        
	        writer.append("\n");
	        writer.append("\t\t\t" +  XMLTags.footer_handleMassOperationRequest);
	        writer.append("\n");
	        writer.append("\t\t" + XMLTags.footer_handleMassOperation);
	        writer.append("\n");
	        writer.append("\t" + XMLTags.footer_interface);
	        writer.append("\n");        
	        
	        writer.append(XMLTags.footer_context);
	        
	        //close writer
	        writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
