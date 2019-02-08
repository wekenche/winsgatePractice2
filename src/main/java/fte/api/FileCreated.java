/**
 * 
 */
package fte.api;

/**
 * @author Try-Parser
 *
 */
public class FileCreated extends Success {
    private String fileName="";
    
    public FileCreated(String fileName){
        super(true);
        this.setFileName(fileName);
    }

    public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
        this.fileName=fileName;
    }
}
