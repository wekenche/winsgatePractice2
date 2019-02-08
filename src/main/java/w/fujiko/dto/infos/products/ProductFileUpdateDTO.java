/**
 * 
 */
package w.fujiko.dto.infos.products;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author yagami
 *
 */
public class ProductFileUpdateDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;

    @JsonProperty("productId")
    @NotNull
    private int productId;

    @JsonProperty("smallSetFilename")
    private String smallSetFilename;

    @JsonProperty("drawingSpecFilename")
    private String drawingSpecFilename;

    @JsonProperty("instructionManualFilename")
    private String instructionManualFilename;

    public int getProductId() {
		return this.productId;
    }
    
    public int setProductId(int productId) {
		return this.productId=productId;
	}

	public void setSmallSetFilename(String smallSetFilename) {
		this.smallSetFilename = smallSetFilename;
	}

	public String getSmallSetFilename() {
		return smallSetFilename;
	}

	public void setDrawingSpecFilename(String drawingSpecFilename) {
		this.drawingSpecFilename = drawingSpecFilename;
	}

	public String getDrawingSpecFilename() {
		return drawingSpecFilename;
	}

	public void setInstructionManualFilename(String instructionManualFilename) {
		this.instructionManualFilename = instructionManualFilename;
	}

	public String getInstructionManualFilename() {
		return instructionManualFilename;
    }
    
}