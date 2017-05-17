package com.unifi.fattureApp.UI;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormData;

public class MainPageUI {

	protected Shell shell;

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setMinimumSize(new Point(300, 200));
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		shell.setSize(566, 380);
		shell.setText("Fatture App");

		FillLayout fillLayout = new FillLayout();
		fillLayout.marginHeight = 5;
		fillLayout.marginWidth = 5;
		shell.setLayout(fillLayout);

		Composite outerComposite = new Composite(shell, SWT.BORDER);
		outerComposite.setBackground(SWTResourceManager.getColor(255, 255, 255));

		FormLayout formLayout = new FormLayout();
		formLayout.marginHeight = 4;
		formLayout.marginWidth = 4;
		formLayout.spacing = 6;
		outerComposite.setLayout(formLayout);

		Composite topComposite = new Composite(outerComposite, SWT.BORDER);
		topComposite.setLayout(new GridLayout());
		topComposite.setBackground(SWTResourceManager.getColor(245, 245, 245));

		FormData formData = new FormData();
		formData.top = new FormAttachment(0);
		formData.left = new FormAttachment(0);
		formData.right = new FormAttachment(100);
		formData.bottom = new FormAttachment(16);
		topComposite.setLayoutData(formData);

		Composite bottomComposite = new Composite(outerComposite, SWT.BORDER);
		FillLayout fl_bottomComposite = new FillLayout();
		fl_bottomComposite.type = SWT.VERTICAL;
		bottomComposite.setLayout(fl_bottomComposite);
		bottomComposite.setBackground(SWTResourceManager.getColor(245, 245, 245));

		formData = new FormData();
		formData.top = new FormAttachment(topComposite);
		formData.left = new FormAttachment(0);
		formData.right = new FormAttachment(100);
		formData.bottom = new FormAttachment(100);
		bottomComposite.setLayoutData(formData);
				
		Composite customerComposite = new Composite(bottomComposite, SWT.NONE | SWT.NO_BACKGROUND);
		customerComposite.setLayout(new GridLayout(1, false));
		
		Label customerLbl = new Label(customerComposite, SWT.None);
		customerLbl.setText("Customer:");
		customerLbl.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, true));
		
		Composite descriptionComposite = new Composite(bottomComposite, SWT.NONE | SWT.NO_BACKGROUND);
	}
}
