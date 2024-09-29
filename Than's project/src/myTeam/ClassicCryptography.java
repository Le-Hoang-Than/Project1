package myTeam;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
//import java.lang.String;

public class ClassicCryptography extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textKey;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClassicCryptography frame = new ClassicCryptography();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	private static Boolean isUpper(char c) {
		
		return c >= 'A' && c <= 'Z';
	}
	private static String encryptCaesar(String text, int key)
	{
		String result = "";
	    //Duyệt văn bản
	    for (int i = 0; i < text.length(); i++) {
	        // Mã hóa từng ký tự
	        // Mã hóa ký tự in hoa
	    	 if (isUpper(text.charAt(i)))
	    			 result += (char)((text.charAt(i) + key - 65) % 26 + 65);
	    	 else result +=  (char)((text.charAt(i) + key - 97 )% 26 + 97);
	    }
		return result;
	}
	private static Boolean checkCaesar(String text, String key) {
		for (int i = 0; i < key.length(); i++) {
	    	 if (key.charAt(i) < '0' ||  key.charAt(i) > '9')
	    		 return false;
		}
		
		for (int i = 0; i < text.length(); i++) {
		    	 if (text.charAt(i) < 'A' ||  text.charAt(i) > 'z')
		    		 return false;
		 }
		return Integer.parseInt(key) > 25 || Integer.parseInt(key) < 0 ? false : true;
	}


	/**
	 * Tạo frame.
	 */
	public ClassicCryptography() {
		//Đặt thuộc tính cho App Classic Cryptography
		setTitle("Classic Cryptography");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		/**
		 * Tạo và đặt thuộc tính Tab Caesar cipher
		 */
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 786, 463);
		contentPane.add(tabbedPane);
		
		////Tạo panel chính cho tab  Caesar cipher
		JPanel panelCaesar = new JPanel();
		tabbedPane.addTab("Caesar cipher", null, panelCaesar, null);
		panelCaesar.setLayout(null);
		
		///Tạo panel bên trái
		JPanel caesarPanelLeft = new JPanel();
		caesarPanelLeft.setLayout(null);
		caesarPanelLeft.setBounds(10, 10, 481, 416);
		panelCaesar.add(caesarPanelLeft);
		//Tạo phần text đầu vào
		JTextArea txtpnInput = new JTextArea();
		txtpnInput.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtpnInput.setLineWrap(true);
		txtpnInput.setBounds(10, 10, 461, 183);
//		caesarPanelLeft.add(txtpnInput);
		//Tạo thanh trượt cho text đầu vào
		JScrollPane scrollPaneInput = new JScrollPane();
		scrollPaneInput.setBounds(10, 10, 461, 183);
		caesarPanelLeft.add(scrollPaneInput);
		scrollPaneInput.setViewportView(txtpnInput);

		//Tạo phần text kết quả đầu ra
		JTextArea txtpnResult = new JTextArea();
		txtpnResult.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtpnResult.setLineWrap(true);
		txtpnResult.setEditable(false);
		txtpnResult.setBounds(10, 223, 461, 183);
//		caesarPanelLeft.add(txtpnResult);
		//
		JScrollPane scrollPaneResult = new JScrollPane();
		scrollPaneResult.setBounds(10, 223, 461, 183);
		caesarPanelLeft.add(scrollPaneResult);
		scrollPaneResult.setViewportView(txtpnResult);
		
		///Tạo Panel bên phải
		JPanel caesarPanelRight = new JPanel();
		caesarPanelRight.setLayout(null);
		caesarPanelRight.setBounds(501, 10, 270, 416);
		panelCaesar.add(caesarPanelRight);
		//Tạo ô nhập key
		JLabel lblNewLabel = new JLabel("Key");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(39, 220, 27, 17);
		caesarPanelRight.add(lblNewLabel);
		textKey = new JTextField();
		textKey.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textKey.setColumns(10);
		textKey.setBounds(105, 220, 124, 19);
		caesarPanelRight.add(textKey);
		//Tạo nút Mã hóa
		JButton btnNewButton = new JButton("Encryption");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(checkCaesar(txtpnInput.getText(), textKey.getText())) {
					txtpnResult.setText(encryptCaesar(txtpnInput.getText(), Integer.parseInt(textKey.getText())));
				}
				else JOptionPane.showMessageDialog(
						contentPane, 
	                    "Văn bản nhập vào chứa các ký tự từ a-Z, \n"
	                    + "Không có khoảng trắng, số và ký tự đặc biệt. \n"
	                    + "Key nhập vào nằm trong giá trị từ 0-25. \n"
	                    + "Vui lòng kiểm tra và nhập lại.", 
	                    "Cảnh báo!", 
	                    JOptionPane.WARNING_MESSAGE);		
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(18, 316, 108, 21);
		caesarPanelRight.add(btnNewButton);
		//Tạo nút Giải mã
		JButton btnDecryption = new JButton("Decryption");
		btnDecryption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkCaesar(txtpnInput.getText(), textKey.getText())) {
					//Áp dụng tính tuần hoàn cho nên Mã hóa(n) => Giải mã(26-n)
					txtpnResult.setText(encryptCaesar(txtpnInput.getText(),26 - Integer.parseInt(textKey.getText())));
				}
				else JOptionPane.showMessageDialog(
						contentPane, 
	                    "Văn bản nhập vào chứa các ký tự từ a-Z, \n"
	                    + "Không có khoảng trắng, số và ký tự đặc biệt. \n"
	                    + "Key nhập vào nằm trong giá trị từ 0-25. \n"
	                    + "Vui lòng kiểm tra và nhập lại.", 
	                    "Cảnh báo!", 
	                    JOptionPane.WARNING_MESSAGE);
				
			}
		});
		btnDecryption.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDecryption.setBounds(144, 316, 108, 21);
		caesarPanelRight.add(btnDecryption);
		//Tạo heading
		Label label = new Label("Caesar cipher");
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		label.setAlignment(Label.CENTER);
		label.setBounds(10, 77, 250, 66);
		caesarPanelRight.add(label);
		
		
		
		/**
		 * 
		 */
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Monoalphabetic cipher", null, panel_1, null);
		
		
		
		/**
		 * 
		 */
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Playfair cipher", null, panel_2, null);
		
		
		
		/**
		 * 
		 */
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Vegenere cipher", null, panel_3, null);
		
		
		
		/**
		 * 
		 */
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Chuyển dịch dòng", null, panel_4, null);
	}
}
