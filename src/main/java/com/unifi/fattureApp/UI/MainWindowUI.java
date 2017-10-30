package com.unifi.fattureApp.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import com.unifi.fattureApp.App.MongoUiComunication;

public class MainWindowUI {

	// prova pull request...
	private MongoUiComunication mongoUiCom;

	private JFrame fattureApp_Frame;
	private JLayeredPane outer_Panel;
	private JPanel myCompany_Panel;

	// private int buttonPadding = 8;
	private int buttonHeight = 30;
	private int buttonWidth = 64;

	private Color outerColor = new java.awt.Color(232, 246, 250);
	private Color layer1Color = new java.awt.Color(226, 244, 252);

	private Dimension windowDimension = new Dimension(500, 700);
	private AddPanel addCompany_Panel;
	private AddPanel addClient_Panel;
	private AddPanel addItem_Panel;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainWindowUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainWindowUI(MongoUiComunication mongoUiComm) {
		mongoUiCom = mongoUiComm;
		initialize();
	}

	public MainWindowUI() {
		mongoUiCom = new MongoUiComunication();
		initialize();
	}

	private void initialize() {
		// mongoUiCom=new MongoUiComunication();

		fattureApp_Frame = new JFrame();
		fattureApp_Frame.setTitle("Fatture App");
		fattureApp_Frame.setPreferredSize(windowDimension);
		fattureApp_Frame.setResizable(false);
		fattureApp_Frame.pack();
		fattureApp_Frame.setBackground(java.awt.Color.LIGHT_GRAY);
		fattureApp_Frame.getContentPane().setLayout(null);
		fattureApp_Frame.setLocationRelativeTo(null);

		fattureApp_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		outer_Panel = new JLayeredPane();
		outer_Panel.setName("OuterPanel");
		outer_Panel.setBackground(outerColor);
		outer_Panel.setBounds(0, 0, fattureApp_Frame.getContentPane().getWidth(),
				fattureApp_Frame.getContentPane().getHeight());
		fattureApp_Frame.getContentPane().add(outer_Panel);
		outer_Panel.setLayout(null);

		int outerInsets = 8;
		int topPanelHeight = 80;

		myCompany_Panel = new JPanel();
		myCompany_Panel.setName("CompanyPanel");
		myCompany_Panel.setBorder(BorderFactory.createLineBorder(Color.white));
		myCompany_Panel.setBounds(outerInsets, outerInsets, outer_Panel.getWidth() - outerInsets * 2, topPanelHeight);
		outer_Panel.add(myCompany_Panel);
		myCompany_Panel.setBackground(layer1Color);
		myCompany_Panel.setLayout(null);

		// MY COMPANY panel
		int innerInsets = 12;
		String myCompanyName_String = "My Company";
		JLabel myCompany_Label = new JLabel(myCompanyName_String);
		myCompany_Label.setName("currentSelectedCompanyLabel");
		myCompany_Label.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		double width = myCompany_Label.getPreferredSize().getWidth();
		double height = myCompany_Label.getPreferredSize().getHeight();
		myCompany_Label.setBounds(innerInsets, innerInsets, (int) width, (int) height);
		myCompany_Panel.add(myCompany_Label);

		JLabel myCompanyInfo_Label = new JLabel("info");
		width = myCompany_Label.getPreferredSize().getWidth();
		height = myCompany_Label.getPreferredSize().getHeight();
		myCompany_Label.setBounds(innerInsets, innerInsets, (int) width, (int) height);
		myCompanyInfo_Label.setBounds(innerInsets, myCompany_Label.getY() + innerInsets + myCompany_Label.getHeight(),
				(int) width, (int) height);
		myCompany_Panel.add(myCompanyInfo_Label);

		
		JButton editMyCompany_Button = new JButton("Edit");
		editMyCompany_Button.setEnabled(false);
		editMyCompany_Button.setName("EditCompanyButton");
		editMyCompany_Button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		editMyCompany_Button.setBounds(myCompany_Panel.getWidth() - innerInsets - buttonWidth,
				(myCompany_Panel.getHeight() / 2) - (buttonHeight / 2), buttonWidth, buttonHeight);
		myCompany_Panel.add(editMyCompany_Button);
		outer_Panel.setLayer(editMyCompany_Button, 1);
		editMyCompany_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showGenericAddPanel(addCompany_Panel, false);
			}
		});

		JButton addMyCompany_Button = new JButton("Add");
		addMyCompany_Button.setName("AddCompanyButton");
		addMyCompany_Button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		addMyCompany_Button.setBounds(editMyCompany_Button.getX() - innerInsets - buttonWidth,
				(myCompany_Panel.getHeight() / 2) - (buttonHeight / 2), buttonWidth, buttonHeight);
		myCompany_Panel.add(addMyCompany_Button);
		outer_Panel.setLayer(addMyCompany_Button, 1);
		addMyCompany_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showGenericAddPanel(addCompany_Panel, true);
			}
		});
		
		

		// ▲, ▼

		JButton prevCompany_Button = new JButton("▲");
		prevCompany_Button.setName("prevCompany_Button");
		prevCompany_Button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		prevCompany_Button.setFont(new Font("Arial", Font.PLAIN, 10));
		int h = 16;
		int w = 16;
		prevCompany_Button.setBounds(addMyCompany_Button.getX() - innerInsets - w, myCompany_Panel.getHeight() / 2 - h,
				w, h);
		myCompany_Panel.add(prevCompany_Button);
		prevCompany_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// go to previous company
				if(mongoUiCom.getCompanyCounter()-1<0) {
					
					//disabilitarlo?
					
				}else{
					mongoUiCom.setCompanyCounter(mongoUiCom.getCompanyCounter()-1);
				}
			}
		});

		JButton nextCompany_Button = new JButton("▼");
		nextCompany_Button.setName("nextCompany_Button");
		nextCompany_Button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		nextCompany_Button.setFont(new Font("Arial", Font.PLAIN, 10));
		h = 16;
		w = 16;
		nextCompany_Button.setBounds(addMyCompany_Button.getX() - innerInsets - w, myCompany_Panel.getHeight() / 2 + 2,
				w, h);
		myCompany_Panel.add(nextCompany_Button);
		nextCompany_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO go to next company
				if(mongoUiCom.getCompanyCounter()+1>mongoUiCom.getCompaniesCount()-1) {
					
					//disabilitarlo?

				}else {
					mongoUiCom.setCompanyCounter(mongoUiCom.getCompanyCounter()+1);
				}
			}
		});

		// Invoice panel
		JPanel invoice_Panel = new JPanel();
		invoice_Panel.setName("InvoicePanel");
		invoice_Panel.setBorder(BorderFactory.createLineBorder(Color.white));
		int invoicePanelY = (outerInsets * 2) + myCompany_Panel.getHeight();
		int invoicePanelWidth = outer_Panel.getWidth() - (outerInsets * 2);
		int invoicePanelHeight = outer_Panel.getHeight() - (invoicePanelY + outerInsets);
		invoice_Panel.setBounds(outerInsets, invoicePanelY, invoicePanelWidth, invoicePanelHeight);
		outer_Panel.add(invoice_Panel);
		invoice_Panel.setBackground(layer1Color);
		invoice_Panel.setLayout(null);

		outer_Panel.setLayer(invoice_Panel, 1);

		// Client Panel

		JPanel clientPanel = new JPanel();
		clientPanel.setName("ClientPanel");
		clientPanel.setBackground(layer1Color);
		clientPanel.setBounds(innerInsets, innerInsets, invoicePanelWidth - (innerInsets * 2), 110);
		invoice_Panel.add(clientPanel);
		clientPanel.setLayout(null);

		JLabel clientLbl = new JLabel("Client");
		clientLbl.setBounds((clientPanel.getWidth() / 2) - 26, innerInsets, 52, 16);
		clientLbl.setFont(new Font("Arial", Font.BOLD, 16));
		clientPanel.add(clientLbl);

		JComboBox clientListComboBox = new JComboBox();
		clientListComboBox.setName("clientsComboBox");
		clientListComboBox.setBounds(0, (clientPanel.getHeight() / 2) - 14, 306, 28);
		clientPanel.add(clientListComboBox);
		clientListComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED) {
					mongoUiCom.setCurrentSelectedClient(mongoUiCom.getSavedClients().get(clientListComboBox.getSelectedIndex()));
	            }
				
			}
		});

		JButton editClient = new JButton("Edit");
		editClient.setName("editClientButton");
		editClient.setEnabled(false);
		editClient.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		editClient.setBounds(clientPanel.getWidth() - buttonWidth,
				(clientPanel.getHeight() / 2) - (buttonHeight / 2), buttonWidth, buttonHeight);
		editClient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showGenericAddPanel(addClient_Panel, false);
			}
		});
		clientPanel.add(editClient);
		outer_Panel.setLayer(myCompany_Panel, 1);
		
		

		JButton addClient = new JButton("Add");
		addClient.setName("AddClientButton");
		addClient.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		addClient.setBounds(editClient.getX() - innerInsets - buttonWidth,
				(clientPanel.getHeight() / 2) - (buttonHeight / 2), buttonWidth, buttonHeight);
		outer_Panel.setLayer(myCompany_Panel, 1);
		clientPanel.add(addClient);
		addClient.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showGenericAddPanel(addClient_Panel, true);
			}
		});

		// Description panel

		JPanel invoiceProvisionPanel = new JPanel();
		invoiceProvisionPanel.setName("InvoiceDescription");
		invoiceProvisionPanel.setBackground(layer1Color);
		invoiceProvisionPanel.setBounds(innerInsets, clientPanel.getY() + clientPanel.getHeight() + innerInsets, invoicePanelWidth - (innerInsets * 2), 110);
		invoice_Panel.add(invoiceProvisionPanel);
		invoiceProvisionPanel.setLayout(null);

		JLabel invoiceItemLbl = new JLabel("Invoice item");
		invoiceItemLbl.setBounds((invoiceProvisionPanel.getWidth() / 2) - 54, innerInsets, 108, 16);
		invoiceItemLbl.setFont(new Font("Arial", Font.BOLD, 16));
		invoiceProvisionPanel.add(invoiceItemLbl);

		JComboBox invoiceListcomboBox = new JComboBox();
		invoiceListcomboBox.setName("invoicesComboBox");
		invoiceListcomboBox.setBounds(0, (invoiceProvisionPanel.getHeight() / 2) - 14, 306, 28);
		invoiceProvisionPanel.add(invoiceListcomboBox);
		invoiceListcomboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED) {
					mongoUiCom.setCurrentSelectedInvoice(mongoUiCom.getSavedInvoices().get(invoiceListcomboBox.getSelectedIndex()));
	            }
			}
		});


		JButton editInvoiceProvision = new JButton("Edit");
		editInvoiceProvision.setName("editInvoiceButton");
		editInvoiceProvision.setEnabled(false);
		editInvoiceProvision.setBounds(invoiceProvisionPanel.getWidth() - buttonWidth,
				(invoiceProvisionPanel.getHeight() / 2) - (buttonHeight / 2), buttonWidth, buttonHeight);
		editInvoiceProvision.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		editInvoiceProvision.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showGenericAddPanel(addItem_Panel, false);
			}
		});
		invoiceProvisionPanel.add(editInvoiceProvision);
		
		JButton addInvoiceProvision = new JButton("Add");
		addInvoiceProvision.setBounds(editInvoiceProvision.getX() - innerInsets - buttonWidth,
				(invoiceProvisionPanel.getHeight() / 2) - (buttonHeight / 2), buttonWidth, buttonHeight);
		addInvoiceProvision.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		invoiceProvisionPanel.add(addInvoiceProvision);
		addInvoiceProvision.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showGenericAddPanel(addItem_Panel, true);
			}
		});

		

		JButton createInvoice_Button = new JButton("CREATE INVOICE");
		createInvoice_Button.setFont(new Font("Arial", Font.PLAIN, 14));
		createInvoice_Button.setEnabled(true);
		createInvoice_Button.setBackground(Color.white);
		createInvoice_Button.setOpaque(true);
		createInvoice_Button.setName("createInvoice_Button");
		createInvoice_Button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		h = 50;
		w = 180;
		createInvoice_Button.setBounds(invoicePanelWidth / 2 - (w / 2), invoice_Panel.getHeight() - h - 8, w, h);
		invoice_Panel.add(createInvoice_Button);
		createInvoice_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// creare la fattura.
				mongoUiCom.printSelected();
			}
		});

		createAddRecordsPanels();
		updateReferences(clientListComboBox,invoiceListcomboBox,myCompany_Label);
		
		

		fattureApp_Frame.pack();
		fattureApp_Frame.setVisible(true);
	}
	
	
	
	private void updateReferences(JComboBox clientListComboBox,JComboBox invoiceListcomboBox,JLabel companyInfo) {
		mongoUiCom.setClientsList(clientListComboBox);
		mongoUiCom.setInvoicesList(invoiceListcomboBox);
		mongoUiCom.setCompanyInfo(companyInfo);
		mongoUiCom.updateAllReferences();
	}

	

	private void createAddRecordsPanels() {
		addCompany_Panel = new CompanyPanel(outer_Panel, buttonWidth, buttonHeight, mongoUiCom);

		addClient_Panel = new ClientPanel(outer_Panel, buttonWidth, buttonHeight, mongoUiCom);

		addItem_Panel = new ItemInvoicePanel(outer_Panel, buttonWidth, buttonHeight,mongoUiCom);
	}
	
	
	private void showGenericAddPanel(AddPanel panelToShow,boolean isSaving) {
		panelToShow.setAddingMode(isSaving);
	}
	
/*
	private void showAddCompanyPanel() {
		// SHOW PANEL TO ADD CUSTOMER
		addCompany_Panel.setVisible(true);
	}

	private void showAddClientPanel() {
		// SHOW PANEL TO ADD CUSTOMER
		addClient_Panel.setVisible(true);
	}

	private void showAddItemPanel() {
		addItem_Panel.setVisible(true);
	}
	*/

	public JFrame getMainFrame() {
		// TODO Auto-generated method stub
		return fattureApp_Frame;
	}
}