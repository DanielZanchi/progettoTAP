package com.unifi.fattureApp.App;

import java.net.UnknownHostException;

import com.unifi.fattureApp.UI.MainWindowUI;


public class Main {
	public static void main(String[] args) throws UnknownHostException {
		
		//Launch UI
//		new MainWindowUI(new MongoUiComunication());
		MongoUiComunication mongoUiComunication=new MongoUiComunication(false,args);
		
		//new MainWindowUI(mongoUiComunication);
		
		
		
		//		Fill db
	/*
		int companies=2;
		int clients=4;
		int invoices=6;
		for(int i=0;i<companies;i++) {
			mongoUiComunication.addCompanyToDatabase("Compagnia "+String.valueOf(i), String.valueOf(i), "via indirizzo "+String.valueOf(i), "citta' "+String.valueOf(i), "AR", "zipcompany "+String.valueOf(i), "IT", "", "email "+String.valueOf(i));
			System.out.println("aggiunte "+i+" companies ");
		}
		for(int i=0;i<clients;i++) {
			mongoUiComunication.addClientToDatabase("Nome Cognome"+String.valueOf(i),String.valueOf(i), "indirizzo cliente "+String.valueOf(i), "citta' cliente "+String.valueOf(i), "Ar", "zipcliente "+String.valueOf(i), "IT", "", "");
			System.out.println("aggiunti "+i+" clients ");
		}
		for(int i=0;i<invoices;i++) {
			mongoUiComunication.addInvoiceToDatabase("Nome Fattura"+String.valueOf(i), String.valueOf(i*6), "descrizione fattura "+String.valueOf(i));
			System.out.println("aggiunte "+i+" invoices ");
		}
		*/
		//		endFill db
		
		
		
		
		System.out.println("Fatture-app terminates");

	}
}