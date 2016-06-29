package ph.com.globe.csvparser.file;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class MyFileUploadForm {

	// Upload files.
    private CommonsMultipartFile[] fileDatas;
    
    private String massRequestType;
 
    public String getMassRequestType() {
		return massRequestType;
	}

	public void setMassRequestType(String massRequestType) {
		this.massRequestType = massRequestType;
	}

	public CommonsMultipartFile[] getFileDatas() {
        return fileDatas;
    }
 
    public void setFileDatas(CommonsMultipartFile[] fileDatas) {
        this.fileDatas = fileDatas;
    }
    
}