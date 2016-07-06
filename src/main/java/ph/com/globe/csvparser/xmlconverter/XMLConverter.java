/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.com.globe.csvparser.xmlconverter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import ph.com.globe.csvparser.util.Utilities;
import ph.com.globe.csvparser.util.XMLPartUtil;

/**
 *
 * 
 */
public class XMLConverter {

    private String requestCreationDt;
    private String requestExecutionDt;
    private String dateFormat;
    private Date creationDate;
    private Date executionDate;
    private Boolean isCreationDtValid = false;
    private Boolean isExecutionDtValid = false;
    private String lockInStart;
    private String lockInEnd;
    private Date lockInStartDate;
    private Date lockInEndDate;
    private Boolean isLockInStartDateValid = false;
    private Boolean isLockInEndDateValid = false;
    private String externalMassReqId;
    private String massReqType;  
    private String reqCreator;
   
    
    public String convertToXML_changeConfiguration(String csvSource, String destination, String fileName) {//GEN-FIRST:event_convertActionPerformed
    	XMLPartUtil util = new XMLPartUtil();
    	String errorMsg = "";
        try {
            if(fileName!=null){
                File excelFile = new File(csvSource);
                BufferedReader br = new BufferedReader(new FileReader(excelFile));
                String str;
                List<String> listStr = new ArrayList<String>();
                List<String> listValue = new ArrayList<String>();
                
                while((str = br.readLine())!=null){
                    listStr.add(str);
                }
                
                //close buffered reader
                br.close();
                
                //Tags are now stored in this array of Strings
                String[] tags = listStr.get(0).split("\\,");
                
                //Get second line of the code and split to get the required values
                String[] values = listStr.get(1).split("\\,");

                //Add to the list of values
                for(int i=1;i<listStr.size();i++){
                    listValue.add(listStr.get(i));               	
                }
 
                Map<String, String> xmlMassRequestHeadersMap = util.getXmlMassRequestHeaders(tags, values, listValue.size());
                Map<String, String> xmlMassRequestDetailsMap = util.getXmlMassRequestDetails(tags, values); 
                Map<String, String> xmlSubProductIDElementMap = util.getXMLSubProductIDElement(tags, values);
                Map<String, String> xmlOptionalConfigurationProperty = util.getXMLOptionalConfigurationProperty(tags, values);
                Map<String, String> xmlDynamicPropertyMap = util.getXMLDynamicProperty(tags, values);
                Map<String, ArrayList<String>> xmlIdElementMap = util.getXMLIdElementsMap(tags, listValue);
                
            	externalMassReqId = xmlMassRequestHeadersMap.get("externalMassRequestID");
            	massReqType = xmlMassRequestHeadersMap.get("massRequestType");
            	reqCreator = xmlMassRequestHeadersMap.get("requestCreator");
            	requestCreationDt = xmlMassRequestHeadersMap.get("requestCreationDate");
            	requestExecutionDt = xmlMassRequestHeadersMap.get("requestExecutionDate");
            	dateFormat = xmlMassRequestHeadersMap.get("dateFormat");
            	lockInStart = xmlOptionalConfigurationProperty.get("LOCK_IN_START_DATE");
            	lockInEnd = xmlOptionalConfigurationProperty.get("LOCK_IN_END_DATE");
            	
            	if(externalMassReqId == null || externalMassReqId.isEmpty()){
            		errorMsg = errorMsg.concat("External mass request ID is required. <br/>");
            	}
            	if(massReqType == null || massReqType.isEmpty() ){
            		errorMsg = errorMsg.concat("Mass request type is required. <br/>");
            	}
            	
            	if (reqCreator == null || reqCreator.isEmpty()){
            		errorMsg = errorMsg.concat("Request Creator is required. <br/>");
            	}
            	if(dateFormat!=null) {
            		if (requestCreationDt!=null) {
            			if(!Utilities.isValidDateFormat(dateFormat, requestCreationDt)){
                        	errorMsg = errorMsg.concat("Invalid request creation date format. <br/>");
                        }else{
                        	isCreationDtValid = true;
                        }
            		}
            		if(requestExecutionDt!=null) {
                        if(!Utilities.isValidDateFormat(dateFormat,requestExecutionDt)){
                        	errorMsg = errorMsg.concat("Invalid request execution date format. <br/>");
                        }else{
                        	isExecutionDtValid = true;
                        }
            		}
            		if(lockInStart!=null) {
                      if(!Utilities.isValidDateFormat(dateFormat,lockInStart)){
                    	errorMsg = errorMsg.concat("Invalid lock in start date format. <br/>");
                      }else{
                    	  isLockInStartDateValid = true;
                      }
            		}
            		if(lockInEnd!=null) {
                        if(!Utilities.isValidDateFormat(dateFormat,lockInEnd)){
                      	errorMsg = errorMsg.concat("Invalid lock in end date format. <br/>");
                        }else{
                        	isLockInEndDateValid = true;
                        }
              		}
            	}

                if(isCreationDtValid && isExecutionDtValid){
                	creationDate = Utilities.convertStringToDate(dateFormat, requestCreationDt);
                	executionDate = Utilities.convertStringToDate(dateFormat, requestExecutionDt);
                	
                	 if(creationDate.compareTo(executionDate)>0){
                         errorMsg = errorMsg.concat("Request Creation Date is greater than Request Execution Date.");
                     }
                }
                
                if(isLockInStartDateValid && isLockInEndDateValid){
                    lockInStartDate = Utilities.convertStringToDate(dateFormat, lockInStart);
                	lockInEndDate = Utilities.convertStringToDate(dateFormat, lockInEnd);
                	
                	 if(lockInStartDate.compareTo(lockInEndDate)>0){
                         errorMsg = errorMsg.concat("Lock In Start Date is greater than Lock In End Date");
                     }
                }
                
                //Create output file
                File xmlFile = new File(destination);
        		xmlFile.createNewFile();
        		
        		//write to created file
                util.printXML(xmlFile, xmlMassRequestHeadersMap, xmlMassRequestDetailsMap, xmlSubProductIDElementMap, xmlOptionalConfigurationProperty, xmlDynamicPropertyMap, xmlIdElementMap);
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return errorMsg;
		   
    }
    
    public String convertToXML_replaceOfferWithBasePlan(String csvSource, String destination, String fileName) {//GEN-FIRST:event_convertActionPerformed
    	XMLPartUtil util = new XMLPartUtil();
    	String errorMsg = "";
        try {
            if(fileName!=null){
                File excelFile = new File(csvSource);
                BufferedReader br = new BufferedReader(new FileReader(excelFile));
                String str;
                List<String> listStr = new ArrayList<String>();
                List<String> listValue = new ArrayList<String>();
                
                //List of MSISDNs
                List<String> listMsisdn = new ArrayList<String>();
                while((str = br.readLine())!=null){
                    listStr.add(str);
                }
                
                //close reader
                br.close();
                
                String[] tags = listStr.get(0).split("\\,");
                
                //Get second line of the code and split to get the required values
                String[] values = listStr.get(1).split("\\,");
                
                //Add to the list of values
                for(int i=1;i<listStr.size();i++){
                    listValue.add(listStr.get(i));               	
                }
 
                //List of MSISDNs
                for (String value: listValue) {
                	String msisdnValue = value.split("\\,")[(value.split("\\,").length)-1];
                	//Add MSISDN to the arraylist
                	if(msisdnValue != null)
                		listMsisdn.add(msisdnValue);
                }
                
                Map<String, String> xmlMassRequestHeadersMap = util.getXmlMassRequestHeaders(tags, values, listMsisdn.size());
                Map<String, String> xmlMassRequestDetailsMap = util.getXmlMassRequestDetails(tags, values); 
                Map<String, String> xmlSubProductIDElementMap = util.getXMLSubProductIDElement(tags, values);
                Map<String, String> xmlOptionalConfigurationProperty = util.getXMLOptionalConfigurationProperty(tags, values);
                Map<String, String> xmlDynamicPropertyMap = util.getXMLDynamicProperty(tags, values);
                Map<String, ArrayList<String>> xmlIdElementMap = util.getXMLIdElementsMap(tags, listStr);
                
                externalMassReqId = xmlMassRequestHeadersMap.get("externalMassRequestID");
            	massReqType = xmlMassRequestHeadersMap.get("massRequestType");
            	reqCreator = xmlMassRequestHeadersMap.get("requestCreator");
            	requestCreationDt = xmlMassRequestHeadersMap.get("requestCreationDate");
            	requestExecutionDt = xmlMassRequestHeadersMap.get("requestExecutionDate");
            	dateFormat = xmlMassRequestHeadersMap.get("dateFormat");
            	lockInStart = xmlOptionalConfigurationProperty.get("LOCK_IN_START_DATE");
            	lockInEnd = xmlOptionalConfigurationProperty.get("LOCK_IN_END_DATE");
            	
            	if(externalMassReqId == null || externalMassReqId.isEmpty()){
            		errorMsg = errorMsg.concat("External mass request ID is required. <br/>");
            	}
            	if(massReqType == null || massReqType.isEmpty() ){
            		errorMsg = errorMsg.concat("Mass request type is required. <br/>");
            	}
            	
            	if (reqCreator == null || reqCreator.isEmpty()){
            		errorMsg = errorMsg.concat("Request Creator is required. <br/>");
            	}
            	
            	if(dateFormat!=null) {
            		if (requestCreationDt!=null) {
            			if(!Utilities.isValidDateFormat(dateFormat, requestCreationDt)){
                        	errorMsg = errorMsg.concat("Invalid request creation date format. <br/>");
                        }else{
                        	isCreationDtValid = true;
                        }
            		}
            		if(requestExecutionDt!=null) {
                        if(!Utilities.isValidDateFormat(dateFormat,requestExecutionDt)){
                        	errorMsg = errorMsg.concat("Invalid request execution date format. <br/>");
                        }else{
                        	isExecutionDtValid = true;
                        }
            		}
            		if(lockInStart!=null) {
                      if(!Utilities.isValidDateFormat(dateFormat,lockInStart)){
                    	errorMsg = errorMsg.concat("Invalid lock in start date format. <br/>");
                      }else{
                    	  isLockInStartDateValid = true;
                      }
            		}
            		if(lockInEnd!=null) {
                        if(!Utilities.isValidDateFormat(dateFormat,lockInEnd)){
                      	errorMsg = errorMsg.concat("Invalid lock in end date format. <br/>");
                        }else{
                      	  isLockInEndDateValid = true;
                        }
              		}
            		
            	}
                
                if(isCreationDtValid && isExecutionDtValid){
                	creationDate = Utilities.convertStringToDate(dateFormat, requestCreationDt);
                	executionDate = Utilities.convertStringToDate(dateFormat, requestExecutionDt);
                	
                	 if(creationDate.compareTo(executionDate)>0){
                         errorMsg = errorMsg.concat("Request Creation Date is greater than Request Execution Date.");
                     }
                }
                
                if(isLockInStartDateValid && isLockInEndDateValid){
                    lockInStartDate = Utilities.convertStringToDate(dateFormat, lockInStart);
                	lockInEndDate = Utilities.convertStringToDate(dateFormat, lockInEnd);
                	
                	 if(lockInStartDate.compareTo(lockInEndDate)>0){
                         errorMsg = errorMsg.concat("Lock In Start Date is greater than Lock In End Date");
                     }
                }
                //Create output file
                File xmlFile = new File(destination);
        		xmlFile.createNewFile();
        		
        		//write to created file
                util.printXML(xmlFile, xmlMassRequestHeadersMap, xmlMassRequestDetailsMap, xmlSubProductIDElementMap, xmlOptionalConfigurationProperty, xmlDynamicPropertyMap, xmlIdElementMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return errorMsg;
		
    }
}
