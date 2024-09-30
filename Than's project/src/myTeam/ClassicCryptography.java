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
	private JTextField textKey_1;
	private JTextField txtAlphabet;


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
	
	
	/**
	 * 
	 *Đây là Thuật toán cho Caesar
	 */
	///Hàm kiểm tra là Chữ Hoa hay không
	private static Boolean isUpper(char c) {
		return c >= 'A' && c <= 'Z';
	}
	
	///Hàm dùng để mã hóa và giải mã Caesar
	private static String encryptCaesar(String text, int key)
	{
		String result = "";
	    //Duyệt văn bản
	    for (int i = 0; i < text.length(); i++) {
	        // Mã hóa từng ký tự
	        // Mã hóa ký tự in hoa
	    	if(text.charAt(i) == ' ')
	    		result += ' ';
	    	else if (isUpper(text.charAt(i)))
	    			 result += (char)((text.charAt(i) + key - 65) % 26 + 65);
	    	 //Mã hóa ký tự thường
	    	 else result +=  (char)((text.charAt(i) + key - 97 )% 26 + 97);
	    }
	    //Trả về chuỗi đã mã hóa/giải mã
		return result;
	}
	
	
	
	
	
	///Hàm format text nhập vào (Xóa khoảng trắng thừa)
	/**
	 * 
	 */
	
	///Tạo hàm kiểm tra văn bản nhập vào hợp lệ hay không
	private static Boolean checkText(String text, String key) {
		for (int i = 0; i < key.length(); i++) {
	    	 if (key.charAt(i) < '0' ||  key.charAt(i) > '9')
	    		 return false;
		}
		
		for (int i = 0; i < text.length(); i++) {
				if (text.charAt(i) == ' ')
					continue;
				else if (text.charAt(i) < 'A' ||  text.charAt(i) > 'z')
		    		 return false;
		 }
		return Integer.parseInt(key) > 25 || Integer.parseInt(key) < 0 ? false : true;
	}
	
	//Nạp chồng hàm kiểm tra văn bản nhập vào
//	private static Boolean checkText(String text, String key, String generateKey) {
//		for (int i = 0; i < key.length(); i++) {
//	    	 if (key.charAt(i) < '0' ||  key.charAt(i) > '9')
//	    		 return false;
//		}
//		for (int i = 0; i < generateKey.length(); i++) {
//	    	 if (generateKey.charAt(i) < '0' ||  generateKey.charAt(i) > '9')
//	    		 return false;
//		}
//		for (int i = 0; i < text.length(); i++) {
//		    	 if (text.charAt(i) < 'A' ||  text.charAt(i) > 'z')
//		    		 return false;
//		 }
//		return Integer.parseInt(key) > 25 || Integer.parseInt(key) < 0 ? false : true;
//	}
	

	
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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 786, 463);
		contentPane.add(tabbedPane);
		
		
		/**
		 * Tạo và đặt thuộc tính Tab Caesar cipher
		 */
		////Tạo pane chính cho tab  Caesar cipher
		JPanel panelCaesar = new JPanel();
		tabbedPane.addTab("Caesar cipher", null, panelCaesar, null);
		panelCaesar.setLayout(null);
		
		///Tạo pane bên trái
		JPanel caesarPanelLeft = new JPanel();
		caesarPanelLeft.setLayout(null);
		caesarPanelLeft.setBounds(10, 10, 481, 416);
		panelCaesar.add(caesarPanelLeft);
		
		//Tạo phần text đầu vào
		JTextArea txtpnInput = new JTextArea();
		txtpnInput.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtpnInput.setLineWrap(true);
		txtpnInput.setBounds(10, 10, 461, 183);
		
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
		
		//Tạo thanh cuộn cho text đầu ra
		JScrollPane scrollPaneResult = new JScrollPane();
		scrollPaneResult.setBounds(10, 223, 461, 183);
		caesarPanelLeft.add(scrollPaneResult);
		scrollPaneResult.setViewportView(txtpnResult);
		
		///Tạo Pane bên phải
		JPanel caesarPanelRight = new JPanel();
		caesarPanelRight.setLayout(null);
		caesarPanelRight.setBounds(501, 10, 270, 416);
		panelCaesar.add(caesarPanelRight);
		
		//Tạo label cho ô nhập key
		JLabel lblNewLabel = new JLabel("Key");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(39, 220, 27, 17);
		caesarPanelRight.add(lblNewLabel);
		
		//Tạo label cho ô nhập key
		JTextField textKey = new JTextField();
		textKey.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textKey.setColumns(10);
		textKey.setBounds(105, 220, 124, 19);
		caesarPanelRight.add(textKey);
		
		//Tạo nút Mã hóa
		JButton btnEncryption = new JButton("Encryption");
		
		//Xử lý sự kiện
		btnEncryption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Nếu nhập dữ liệu chính xác thì mã hóa Ngược lại mở cửa sổ tin nhắn
				if(checkText(txtpnInput.getText(), textKey.getText())) {
					txtpnResult.setText(encryptCaesar(txtpnInput.getText(), Integer.parseInt(textKey.getText())));
				}
				else JOptionPane.showMessageDialog(
						contentPane, 
	                    "Văn bản nhập vào chứa các ký tự từ a-Z, \n"
	                    + "Không có số và ký tự đặc biệt trong văn bản. \n"
	    	            + "Key nhập vào nằm trong giá trị số từ 0-25. \n"
	                    + "Vui lòng kiểm tra và nhập lại.", 
	                    "Thông báo!", 
	                    JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		//Thuộc tính cho button
		btnEncryption.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEncryption.setBounds(18, 316, 108, 21);
		
		//Thêm button vào pane bên phải
		caesarPanelRight.add(btnEncryption);
		
		//Tạo nút Giải mã
		JButton btnDecryption = new JButton("Decryption");
		
		//Xử lý sự kiện
		btnDecryption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Nếu nhập dữ liệu chính xác thì giải mã Ngược lại mở cửa sổ tin nhắn
				if(checkText(txtpnInput.getText(), textKey.getText())) {
					//Áp dụng tính tuần hoàn cho nên Mã hóa(n) => Giải mã(26-n)
					txtpnResult.setText(encryptCaesar(txtpnInput.getText(),26 - Integer.parseInt(textKey.getText())));
				}
				else JOptionPane.showMessageDialog(
						contentPane, 
	                    "Văn bản nhập vào chứa các ký tự từ a-Z. \n"
	                    + "Không có số và ký tự đặc biệt trong văn bản. \n"
	                    + "Key nhập vào nằm trong giá trị số từ 0-25. \n"
	                    + "Vui lòng kiểm tra và nhập lại.", 
	                    "Thông báo!", 
	                    JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		//Thuộc tính cho button
		btnDecryption.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDecryption.setBounds(144, 316, 108, 21);
		
		//Thêm button vào pane bên phải
		caesarPanelRight.add(btnDecryption);
		
		//Tạo và thêm thuộc tính cho heading
		Label heading0 = new Label("Caesar cipher");
		heading0.setFont(new Font("Dialog", Font.BOLD, 14));
		heading0.setAlignment(Label.CENTER);
		heading0.setBounds(10, 77, 250, 66);
		caesarPanelRight.add(heading0);
		
		
		
		/**
		 * 
		 */
		JPanel panelMonoalphabetic = new JPanel();
		tabbedPane.addTab("Monoalphabetic cipher", null, panelMonoalphabetic, null);
		panelMonoalphabetic.setLayout(null);
		
		JPanel monoalphabeticPanelLeft = new JPanel();
		monoalphabeticPanelLeft.setLayout(null);
		monoalphabeticPanelLeft.setBounds(10, 10, 481, 416);
		panelMonoalphabetic.add(monoalphabeticPanelLeft);
		
		JScrollPane scrollPaneInput_1 = new JScrollPane();
		scrollPaneInput_1.setBounds(10, 10, 461, 183);
		monoalphabeticPanelLeft.add(scrollPaneInput_1);
		
		JTextArea txtpnInput_1 = new JTextArea();
		txtpnInput_1.setLineWrap(true);
		txtpnInput_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPaneInput_1.setViewportView(txtpnInput_1);
		
		JScrollPane scrollPaneResult_1 = new JScrollPane();
		scrollPaneResult_1.setBounds(10, 223, 461, 183);
		monoalphabeticPanelLeft.add(scrollPaneResult_1);
		
		JTextArea txtpnResult_1 = new JTextArea();
		txtpnResult_1.setLineWrap(true);
		txtpnResult_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtpnResult_1.setEditable(false);
		scrollPaneResult_1.setViewportView(txtpnResult_1);
		
		JPanel monoalphabeticPanelRight = new JPanel();
		monoalphabeticPanelRight.setLayout(null);
		monoalphabeticPanelRight.setBounds(501, 10, 270, 416);
		panelMonoalphabetic.add(monoalphabeticPanelRight);
		
		JLabel lblNewLabel_1 = new JLabel("Key");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 221, 27, 17);
		monoalphabeticPanelRight.add(lblNewLabel_1);
		
		textKey_1 = new JTextField();
		textKey_1.setEditable(false);
		textKey_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textKey_1.setColumns(10);
		textKey_1.setBounds(47, 220, 213, 19);
		monoalphabeticPanelRight.add(textKey_1);
		
		JButton btnEncryption_1 = new JButton("Encryption");
		btnEncryption_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEncryption_1.setBounds(18, 316, 108, 21);
		monoalphabeticPanelRight.add(btnEncryption_1);
		
		JButton btnDecryption_1 = new JButton("Decryption");
		btnDecryption_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDecryption_1.setBounds(144, 316, 108, 21);
		monoalphabeticPanelRight.add(btnDecryption_1);
		
		Label heading1 = new Label("Monoalphabetic cipher");
		heading1.setFont(new Font("Dialog", Font.BOLD, 14));
		heading1.setAlignment(Label.CENTER);
		heading1.setBounds(10, 77, 250, 66);
		monoalphabeticPanelRight.add(heading1);
		
		txtAlphabet = new JTextField();
		txtAlphabet.setEditable(false);
		txtAlphabet.setText("abcdefghijklmnopqrstuvwxyz");
		txtAlphabet.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtAlphabet.setColumns(10);
		txtAlphabet.setBounds(47, 249, 213, 19);
		monoalphabeticPanelRight.add(txtAlphabet);
		
		
		
		/**
		 * 
		 */
		JPanel panelPlayfair = new JPanel();
		tabbedPane.addTab("Playfair cipher", null, panelPlayfair, null);
		
		
		
		/**
		 * Tạo và đặt thuộc tính cho Tab Vegenere cipher
		 */
		////Tạo pane chính cho tab Vegenere cipher
		JPanel panelVegenere = new JPanel();
		tabbedPane.addTab("Vegenere cipher", null, panelVegenere, null);
		panelVegenere.setLayout(null);
		
		///Tạo pane bên trái
		JPanel vegenerePanelLeft = new JPanel();
		vegenerePanelLeft.setLayout(null);
		vegenerePanelLeft.setBounds(10, 10, 481, 416);
		panelVegenere.add(vegenerePanelLeft);
		
		//Tạo thanh cuộn cho text đầu vào
		JScrollPane scrollPaneInput_3 = new JScrollPane();
		scrollPaneInput_3.setBounds(10, 10, 461, 183);
		vegenerePanelLeft.add(scrollPaneInput_3);
		
		//Tạo phần nhập văn bản đầu vào
		JTextArea txtpnInput_3 = new JTextArea();
		txtpnInput_3.setLineWrap(true);
		txtpnInput_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPaneInput_3.setViewportView(txtpnInput_3);
		
		//Tạo thanh cuộn cho text đầu ra
		JScrollPane scrollPaneResult_3 = new JScrollPane();
		scrollPaneResult_3.setBounds(10, 223, 461, 183);
		vegenerePanelLeft.add(scrollPaneResult_3);
		
		JTextArea txtpnResult_3 = new JTextArea();
		txtpnResult_3.setLineWrap(true);
		txtpnResult_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtpnResult_3.setEditable(false);
		scrollPaneResult_3.setViewportView(txtpnResult_3);
		
		///Tạo pane bên phải
		JPanel vegenerePanelRight = new JPanel();
		vegenerePanelRight.setLayout(null);
		vegenerePanelRight.setBounds(501, 10, 270, 416);
		panelVegenere.add(vegenerePanelRight);
		
		//Tạo label cho ô nhập key
		JLabel lblNewLabel_3 = new JLabel("Key");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(18, 220, 27, 17);
		vegenerePanelRight.add(lblNewLabel_3);
		
		//Tạo ô nhập key
		JTextField textKey_3 = new JTextField();
		textKey_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textKey_3.setColumns(10);
		textKey_3.setBounds(128, 219, 124, 19);
		vegenerePanelRight.add(textKey_3);
		
		//Tạo button mã hóa
		JButton btnEncryption_3 = new JButton("Encryption");
		
		//Xử lý sự kiện
		btnEncryption_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnEncryption_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEncryption_3.setBounds(18, 316, 108, 21);
		vegenerePanelRight.add(btnEncryption_3);
		
		//Tạo button giải mã
		JButton btnDecryption_3 = new JButton("Decryption");
		//Xử lý sự kiện
		btnDecryption_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnDecryption_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDecryption_3.setBounds(144, 316, 108, 21);
		vegenerePanelRight.add(btnDecryption_3);
		
		//Tạo heading và thuộc tính
		Label heading_3 = new Label("Vegenere cipher");
		heading_3.setFont(new Font("Dialog", Font.BOLD, 14));
		heading_3.setAlignment(Label.CENTER);
		heading_3.setBounds(10, 77, 250, 66);
		vegenerePanelRight.add(heading_3);
		
		//Tạo ô nhập key generate
		JTextField textKeyGeneration = new JTextField();
		textKeyGeneration.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textKeyGeneration.setColumns(10);
		textKeyGeneration.setBounds(128, 262, 124, 19);
		vegenerePanelRight.add(textKeyGeneration);
		
		//Tạo label cho ô nhập key generate
		JLabel lblNewLabel_3_1 = new JLabel("Key generation");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3_1.setBounds(18, 263, 86, 17);
		vegenerePanelRight.add(lblNewLabel_3_1);
		
		
		
		/**
		 * 
		 */
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Chuyển dịch dòng", null, panel_4, null);
	}
}
