package w.fujiko.model.infos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;
import javax.persistence.Table;

import w.fujiko.model.masters.products.Product;

@Entity
@Table(name="info_product_file")
public class ProductFile implements Serializable{

    private static final long serialVersionUID = 1L;

	public ProductFile(){}

	public ProductFile(Product product){
		this.product = product;
	}

    @JoinColumn(name="mst_product_id",insertable=true,updatable=true)
    @Id
    @OneToOne
    private Product product;

    @Column(name="small_set_filename",columnDefinition="nvarchar(35)")
    private String smallSetFilename;

    @Column(name="drawing_spec_filename",columnDefinition="nvarchar(35)")
    private String drawingSpecFilename;

    @Column(name="instruction_manual_filename",columnDefinition="nvarchar(35)")
    private String instructionManualFilename;
    
	public void setProduct(Product product) {
		this.product = product;
	}

	public Product getProduct() {
		return product;
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