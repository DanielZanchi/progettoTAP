package com.unifi.fattureApp.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.Box;

public class MainWindowUI {

	private JFrame frame;
	private static final int DEFAULT_WINDOW_DIMENS = 500;

	private int windowDefaultWidth=DEFAULT_WINDOW_DIMENS;
	private int windowDefaultHeight=DEFAULT_WINDOW_DIMENS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindowUI window = new MainWindowUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindowUI() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(DEFAULT_WINDOW_DIMENS,DEFAULT_WINDOW_DIMENS));
		
		frame.setBounds(100, 100, 648, 426);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{636, 0, 0};
		gridBagLayout.rowHeights = new int[]{103, 266, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		
		panel.setBackground(Color.PINK);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		JLabel label=new JLabel("ciao");
		panel.add(label);
		frame.getContentPane().add(panel, gbc_panel);
		
		Box verticalBox = Box.createVerticalBox();
		GridBagConstraints gbc_verticalBox = new GridBagConstraints();
		gbc_verticalBox.anchor = GridBagConstraints.SOUTH;
		gbc_verticalBox.gridheight = 3;
		gbc_verticalBox.gridx = 0;
		gbc_verticalBox.gridy = 1;
		frame.getContentPane().add(verticalBox, gbc_verticalBox);
		
		JButton btnNewButton = new JButton("New button");
		verticalBox.add(btnNewButton);
		
		
		
		
		
	}
}
