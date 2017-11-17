package com.unifi.fattureApp.App;

public class PrintedInvoice {
	private String id;
	private Company printedCompany;
	private Client printedClient;
	private Invoice printedInvoice;

	public PrintedInvoice() {
	}
	
	public PrintedInvoice(Company printedCompany,Client printedClient,Invoice printedInvoice,String printedId) {
		this.printedCompany=printedCompany;
		this.printedClient=printedClient;
		this.printedInvoice=printedInvoice;
		id=printedId;
	}

	
	public String getPrintedId() {
		return id;
	}
	
	public Company getPrintedCompany() {
		return printedCompany;
	}
	
	public Client getPrintedClient() {
		return printedClient;
	}
	
	public Invoice getPrintedInvoice() {
		return printedInvoice;
	}



}
