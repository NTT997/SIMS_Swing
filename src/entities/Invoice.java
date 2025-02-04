package Entities;

import java.util.Date;

public class Invoice {
	private String invoice_id;
	private Date delivery_date;
	private String delivery_location;
	private String Note;
	private Payment_method Payment_method;
	private Boolean is_cancelled;
	private Boolean is_imported;
	private Boolean is_returned;
	private Date created_at;
	private Date deleted_at;
	private Integer supplier_id;
	private Integer customer_id;

	private Integer employee_id;

	public enum Payment_method {
		CASH, BANK, CREDIT_CARD
	}

	public Invoice(String invoice_id, Date delivery_date, String delivery_location, String note,
			Entities.Invoice.Payment_method payment_method, Boolean is_cancelled, Boolean is_imported,
			Boolean is_returned, Date created_at, Date deleted_at, Integer supplier_id, Integer customer_id,
			Integer employee_id) {
		super();
		this.invoice_id = invoice_id;
		this.delivery_date = delivery_date;
		this.delivery_location = delivery_location;
		Note = note;
		Payment_method = payment_method;
		this.is_cancelled = is_cancelled;
		this.is_imported = is_imported;
		this.is_returned = is_returned;
		this.created_at = created_at;
		this.deleted_at = deleted_at;
		this.supplier_id = supplier_id;
		this.customer_id = customer_id;
		this.employee_id = employee_id;
	}

	public Invoice() {
		super();
	}

	public String getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	}

	public Date getDelivery_date() {
		return delivery_date;
	}

	public void setDelivery_date(Date delivery_date) {
		this.delivery_date = delivery_date;
	}

	public String getDelivery_location() {
		return delivery_location;
	}

	public void setDelivery_location(String delivery_location) {
		this.delivery_location = delivery_location;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}

	public Payment_method getPayment_method() {
		return Payment_method;
	}

	public void setPayment_method(Payment_method payment_method) {
		Payment_method = payment_method;
	}

	public Boolean getIs_cancelled() {
		return is_cancelled;
	}

	public void setIs_cancelled(Boolean is_cancelled) {
		this.is_cancelled = is_cancelled;
	}

	public Boolean getIs_imported() {
		return is_imported;
	}

	public void setIs_imported(Boolean is_imported) {
		this.is_imported = is_imported;
	}

	public Boolean getIs_returned() {
		return is_returned;
	}

	public void setIs_returned(Boolean is_returned) {
		this.is_returned = is_returned;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getDeleted_at() {
		return deleted_at;
	}

	public void setDeleted_at(Date deleted_at) {
		this.deleted_at = deleted_at;
	}

	public Integer getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(Integer supplier_id) {
		this.supplier_id = supplier_id;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public Integer getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}

	@Override
	public String toString() {
		return "Invoice [invoice_id=" + invoice_id + ", delivery_date=" + delivery_date + ", delivery_location="
				+ delivery_location + ", Note=" + Note + ", Payment_method=" + Payment_method + ", is_cancelled="
				+ is_cancelled + ", is_imported=" + is_imported + ", is_returned=" + is_returned + ", created_at="
				+ created_at + ", deleted_at=" + deleted_at + ", supplier_id=" + supplier_id + ", customer_id="
				+ customer_id + ", employee_id=" + employee_id + "]";
	}

}