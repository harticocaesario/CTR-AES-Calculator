import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JRadioButton;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;


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
	 */
	public TugasCIS_UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
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
		
		textFieldOutput = new JTextField();
		textFieldOutput.setColumns(10);
		textFieldOutput.setBounds(84, 150, 229, 24);
		frmCbtaesCalculator.getContentPane().add(textFieldOutput);
		
		JLabel lblInputFile = new JLabel("Input");
		lblInputFile.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblInputFile.setBounds(28, 39, 46, 14);
		frmCbtaesCalculator.getContentPane().add(lblInputFile);
		
		JLabel lblKey = new JLabel("Key");
		lblKey.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKey.setBounds(28, 86, 46, 14);
		frmCbtaesCalculator.getContentPane().add(lblKey);
		
		JLabel lblOutput = new JLabel("Output");
		lblOutput.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblOutput.setBounds(28, 154, 46, 14);
		frmCbtaesCalculator.getContentPane().add(lblOutput);
		
		JButton btnBrowseInput = new JButton("Browse");
		btnBrowseInput.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBrowseInput.setBackground(SystemColor.controlHighlight);
		btnBrowseInput.setBounds(323, 35, 89, 24);
		frmCbtaesCalculator.getContentPane().add(btnBrowseInput);
		btnBrowseInput.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	            JFileChooser chooser = new JFileChooser();
	            chooser.setCurrentDirectory(new java.io.File("."));
	            chooser.setDialogTitle("Browse the folder to process");
	            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
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
	            chooser.setDialogTitle("Browse the folder to process");
	            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
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
		
		JButton btnSaveOutput = new JButton("Save As...");
		btnSaveOutput.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSaveOutput.setBackground(SystemColor.controlHighlight);
		btnSaveOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSaveOutput.setBounds(323, 149, 89, 24);
		frmCbtaesCalculator.getContentPane().add(btnSaveOutput);
		
		JButton btnEncrypt = new JButton("Encrypt");
		btnEncrypt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEncrypt.setBackground(SystemColor.scrollbar);
		btnEncrypt.setBounds(85, 198, 106, 35);
		frmCbtaesCalculator.getContentPane().add(btnEncrypt);
		
		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDecrypt.setBackground(SystemColor.scrollbar);
		btnDecrypt.setBounds(208, 198, 106, 35);
		frmCbtaesCalculator.getContentPane().add(btnDecrypt);
		
		JRadioButton rdbtn128Bit = new JRadioButton("128 bit");
		rdbtn128Bit.setBackground(SystemColor.menu);
		rdbtn128Bit.setBounds(85, 120, 81, 23);
		frmCbtaesCalculator.getContentPane().add(rdbtn128Bit);
		
		JRadioButton rdbtn192Bit = new JRadioButton("192 bit");
		rdbtn192Bit.setBackground(SystemColor.menu);
		rdbtn192Bit.setBounds(168, 120, 75, 23);
		frmCbtaesCalculator.getContentPane().add(rdbtn192Bit);
		
		JRadioButton rdbtn256Bit = new JRadioButton("256 bit");
		rdbtn256Bit.setBackground(SystemColor.menu);
		rdbtn256Bit.setBounds(245, 120, 81, 23);
		frmCbtaesCalculator.getContentPane().add(rdbtn256Bit);
	}
}