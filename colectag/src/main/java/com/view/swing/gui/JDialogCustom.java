package com.view.swing.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class JDialogCustom extends JDialog{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
    
    public JDialogCustom(Integer results) {
        setResizable(false);
        setTitle("Info Message");
        setBounds(50, 59, 350, 200);
        
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER); 
        contentPanel.setLayout(null);
        {
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setBounds(10, 11, 322, 107);
            contentPanel.add(scrollPane);
            {
                JLabel labelInfo = new JLabel();                             
                labelInfo.setHorizontalAlignment(SwingConstants.CENTER);
                if(results <= -1) {
                	labelInfo.setText("You must indicates a Tag");
                } else if(results == 0) {
                	labelInfo.setText("No photos were obtained with this tag");
                } else {
                	labelInfo.setText(results + " pics obtained and persisted in DataBase");	
                }
                scrollPane.setViewportView(labelInfo);
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent e) {
					       dispose();
						
					}
				});
				
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
  
        }
    }

}
