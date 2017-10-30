package com.unifi.fattureApp.App;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import com.unifi.fattureApp.App.Client;

public class PDFCreator {
	private Company selectedCompany;
	private Client selectedClient;
	private Invoice selectedInvoice;

	public PDFCreator(Company company, Client client, Invoice invoice) {
		System.out.println("creating pdf");
		this.selectedCompany = company;
		this.selectedClient = client;
		this.selectedInvoice = invoice;

	}

	private void create() {
		try (final PDDocument document = new PDDocument()) {
			final PDPage emptyPage = new PDPage();
			document.addPage(emptyPage);
			document.save("EmptyPage.pdf");
		} catch (IOException ioEx) {
			System.err.println("Exception while trying to create blank document - " + ioEx);
		}
	}
}
