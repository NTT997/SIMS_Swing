package Entities;

import java.util.Date;

public class Return_product {
	private int id;
	private int invoice_detail_id;
	private String reason;
	private Date returned_at;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInvoice_detail_id() {
		return invoice_detail_id;
	}

	public void setInvoice_detail_id(int invoice_detail_id) {
		this.invoice_detail_id = invoice_detail_id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getReturned_at() {
		return returned_at;
	}

	public void setReturned_at(Date returned_at) {
		this.returned_at = returned_at;
	}

	public Return_product(int id, int invoice_detail_id, String reason, Date returned_at) {
		super();
		this.id = id;
		this.invoice_detail_id = invoice_detail_id;
		this.reason = reason;
		this.returned_at = returned_at;
	}

	public Return_product() {
		super();
	}

}
