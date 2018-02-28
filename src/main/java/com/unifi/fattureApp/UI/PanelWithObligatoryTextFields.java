package com.unifi.fattureApp.UI;

import java.awt.Component;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class PanelWithObligatoryTextFields extends JPanel{
	private static final long serialVersionUID = 7883635384945697293L;
	private LinkedList<JTextField> textFields;

	protected void setUpTextFields(Component[] components,String[] textFieldsNotObligatory, FormattedButton saveButton) {
		textFields = new LinkedList<>();
		for (Component component : components) {
			if (component.getClass().equals(JTextField.class)){
				boolean sameName=false;
				for(int i = 0; i<textFieldsNotObligatory.length; i++) {
					if(((JTextField) component).getName().equals(textFieldsNotObligatory[i])) {
						sameName=true;
						break;
					}
				}
				if(!sameName) {
					textFields.add((JTextField) component);
				}
			}
		}

		for (JTextField tf : textFields) {
			tf.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void insertUpdate(DocumentEvent e) {
					changed();
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					changed();
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
					changed();
				}

				public void changed() {
					boolean shouldActivate = true;
					for (JTextField tf : textFields) {
						if (tf.getText().equals("")) {
							shouldActivate = false;
							break;
						}
					}
					saveButton.setEnabled(shouldActivate);
				}
			});
		}
	}	
}