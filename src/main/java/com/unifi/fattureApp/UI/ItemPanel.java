package com.unifi.fattureApp.UI;

import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ItemPanel extends JPanel implements AddPanel {
	private static final long serialVersionUID = -3823205724376310736L;
	private FormattedButton saveButton = new FormattedButton("Save", "SaveButton");
	private LinkedList<JTextField> textFields;
	
	public void update() {
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

	@Override
	public void setAddingMode(boolean isSaving) {
	}
}