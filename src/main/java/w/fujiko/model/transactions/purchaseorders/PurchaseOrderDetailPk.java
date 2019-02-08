package w.fujiko.model.transactions.purchaseorders;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class PurchaseOrderDetailPk implements Serializable {

	private static final long serialVersionUID = -8845155746502398544L;
	
	@Column(name="purchase_order_slip_id", columnDefinition="int", nullable=false)
	@NotNull
	private Integer purchaseOrderHeaderId;
	
	@Column(name="quotation_slip_id", columnDefinition="int", nullable=false)
	@NotNull
	private Integer quotationHeaderId;

	@Column(name="task_id", columnDefinition="smallint", nullable=false)
	@NotNull
	private Short taskId;

	public Integer getPurchaseOrderHeaderId() {
		return purchaseOrderHeaderId;
	}

	public void setPurchaseOrderHeaderId(Integer purchaseOrderHeaderId) {
		this.purchaseOrderHeaderId = purchaseOrderHeaderId;
	}

	public Integer getQuotationHeaderId() {
		return quotationHeaderId;
	}

	public void setQuotationHeaderId(Integer quotationHeaderId) {
		this.quotationHeaderId = quotationHeaderId;
	}

	public Short getTaskId() {
		return taskId;
	}

	public void setTaskId(Short taskId) {
		this.taskId = taskId;
	}

}