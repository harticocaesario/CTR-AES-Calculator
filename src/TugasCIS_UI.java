import java.awt.EventQueue;

import javax.crypto.Cipher;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JRadioButton;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class TugasCIS_UI {

	private JFrame frmCbtaesCalculator;
	private JTextField textFieldKey;
	private JTextField textFieldInput;
	private JTextField textFieldOutput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TugasCIS_UI window = new TugasCIS_UI();
					window.frmCbtaesCalculator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws NoSuchAlgorithmException 
	 * @throws HeadlessException 
	 */
	public TugasCIS_UI() throws HeadlessException, NoSuchAlgorithmException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws NoSuchAlgorithmException 
	 * @throws HeadlessException 
	 */

	private void initialize() throws HeadlessException, NoSuchAlgorithmException {
		frmCbtaesCalculator = new JFrame();
		frmCbtaesCalculator.setForeground(Color.WHITE);
		frmCbtaesCalculator.setTitle("CTR-AES Calculator");
		frmCbtaesCalculator.getContentPane().setBackground(SystemColor.menu);
		frmCbtaesCalculator.getContentPane().setForeground(Color.WHITE);
		frmCbtaesCalculator.setBounds(100, 100, 450, 300);
		frmCbtaesCalculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCbtaesCalculator.getContentPane().setLayout(null);

		textFieldKey = new JTextField();
		textFieldKey.setBounds(84, 82, 229, 24);
		frmCbtaesCalculator.getContentPane().add(textFieldKey);
		textFieldKey.setColumns(10);

		textFieldInput = new JTextField();
		textFieldInput.setToolTipText("");
		textFieldInput.setColumns(10);
		textFieldInput.setBounds(84, 35, 229, 24);
		frmCbtaesCalculator.getContentPane().add(textFieldInput);

		//		textFieldOutput = new JTextField();
		//		textFieldOutput.setColumns(10);
		//		textFieldOutput.setBounds(84, 150, 229, 24);
		//		frmCbtaesCalculator.getContentPane().add(textFieldOutput);

		JLabel lblInputFile = new JLabel("Input");
		lblInputFile.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblInputFile.setBounds(28, 39, 46, 14);
		frmCbtaesCalculator.getContentPane().add(lblInputFile);

		JLabel lblKey = new JLabel("Key");
		lblKey.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKey.setBounds(28, 86, 46, 14);
		frmCbtaesCalculator.getContentPane().add(lblKey);

		//		JLabel lblOutput = new JLabel("Output");
		//		lblOutput.setFont(new Font("Tahoma", Font.PLAIN, 12));
		//		lblOutput.setBounds(28, 154, 46, 14);
		//		frmCbtaesCalculator.getContentPane().add(lblOutput);
		if (Cipher.getMaxAllowedKeyLength("AES") <= 128) {
			JOptionPane.showMessageDialog(null, "To use another size of key, please update your JCE Policy files");
		}


		JButton btnBrowseInput = new JButton("Browse");
		btnBrowseInput.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBrowseInput.setBackground(SystemColor.controlHighlight);
		btnBrowseInput.setBounds(323, 35, 89, 24);
		frmCbtaesCalculator.getContentPane().add(btnBrowseInput);
		btnBrowseInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Browse the input file");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				//chooser.setAcceptAllFileFilterUsed(false);

				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					System.out.println("getCurrentDirectory(): "+ chooser.getCurrentDirectory());
					System.out.println("getSelectedFile() : "+ chooser.getSelectedFile());
					textFieldInput.setText(chooser.getSelectedFile().toString());

				} else {
					System.out.println("No Selection ");
				}
			}
		});



		JButton btnBrowseKey = new JButton("Browse");
		btnBrowseKey.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBrowseKey.setBackground(SystemColor.controlHighlight);
		btnBrowseKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Browse the key file");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				//chooser.setAcceptAllFileFilterUsed(false);

				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					System.out.println("getCurrentDirectory(): "+ chooser.getCurrentDirectory());
					System.out.println("getSelectedFile() : "+ chooser.getSelectedFile());
					textFieldKey.setText(chooser.getSelectedFile().toString());
				} else {
					System.out.println("No Selection ");
				}
			}
		});
		btnBrowseKey.setBounds(323, 81, 89, 24);
		frmCbtaesCalculator.getContentPane().add(btnBrowseKey);

		/**
		 * Encrypt Button
		 * */
		JButton btnEncrypt = new JButton("Encrypt");
		btnEncrypt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEncrypt.setBackground(SystemColor.scrollbar);
		btnEncrypt.setBounds(85, 198, 106, 35);
		btnEncrypt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String fileLoc = textFieldInput.getText();
				String keyLoc = textFieldKey.getText();

				String result = "";
				try {
					result = CtrAES.begin("ENCRYPT", fileLoc, keyLoc);
					JOptionPane.showMessageDialog(null, result);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				//				System.out.println(result);
			}

		});
		frmCbtaesCalculator.getContentPane().add(btnEncrypt);

		/**
		 * Decrypt Button
		 * */
		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDecrypt.setBackground(SystemColor.scrollbar);
		btnDecrypt.setBounds(208, 198, 106, 35);
		btnDecrypt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String fileLoc = textFieldInput.getText();
				String keyLoc = textFieldKey.getText();

				Path filePath = Paths.get(fileLoc);
				String result = "";
				try {
					result = CtrAES.begin("DECRYPT", fileLoc, keyLoc);
					JOptionPane.showMessageDialog(null, result);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});
		frmCbtaesCalculator.getContentPane().add(btnDecrypt);

	}
}
