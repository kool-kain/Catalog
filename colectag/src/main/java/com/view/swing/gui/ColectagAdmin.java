package com.view.swing.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.controller.services.impl.FlickrControllerServiceImpl;
import com.domain.colectag.context.ApplicationContextProvider;

@Component
public class ColectagAdmin extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private static final AnnotationConfigApplicationContext getContext() {
		return new AnnotationConfigApplicationContext(ApplicationContextProvider.class);}
	
	private JPanel contentPane;
	private JTextField textTag;
	
	@Autowired
	private FlickrControllerServiceImpl flickrControllerService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnnotationConfigApplicationContext appContext = getContext();
					appContext.start();
					ColectagAdmin frame = appContext.getBean(ColectagAdmin.class);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ColectagAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Tag Search");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		splitPane.setLeftComponent(lblNewLabel);
		
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		
		JLabel lblTag = new JLabel("Tag: ");
		panel.add(lblTag);
		
		textTag = new JTextField();
		textTag.setToolTipText("Input your tag here");
		panel.add(textTag);
		textTag.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer results = flickrControllerService.searchByTagAndPersist(textTag.getText());
				} catch(NullPointerException exp) {
					//plantar jdialog para avisar de que debe rellenar texto
					exp.printStackTrace();
				}
			}
		});
		panel.add(btnSearch);
		
		JScrollPane scrollPaneImages = new JScrollPane();
		contentPane.add(scrollPaneImages, BorderLayout.CENTER);
		
		JList<String> listImages = new JList<String>();
		scrollPaneImages.setViewportView(listImages);
	}

}
