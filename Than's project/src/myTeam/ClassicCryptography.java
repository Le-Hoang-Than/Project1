package myTeam;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
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
	 * BẮT ĐẦU Các hàm xử lý chuỗi
	 */
	//Hàm kiểm tra có là Ký tự viết Hoa hay không
	private static boolean isUpper(char c) {
		//Khi c nằm trong đoạn từ A đến Z thì trả về true
		return c >= 'A' && c <= 'Z';
	}
	
	//Hàm kiểm tra có là Ký tự viết thường hay không
	private static boolean isLower(char c) {
		//Khi c nằm trong đoạn từ a đến z thì trả về true
		return c >= 'a' && c <= 'z';
	}
	
	//Hàm viết lại ký tự thường thành Hoa
	private static char toUpper(char c) {
		return (char)(c-32);
	}
	
	//Hàm viết lại ký tự Hoa thành thường
	private static char toLower(char c) {
		return (char)(c+32);
	}
	
	 //Hàm viết lại chuỗi thành chuỗi chữ thường
	  static void toLowerCase(char plain[], int ps)
	  {
	    for (int i = 0; i < ps; i++) {
	      if (plain[i] > 64 && plain[i] < 91)
	        plain[i] += 32;
	    }
	  }
	
	//Hàm xóa tất cả khoảng trắng trong chuỗi
	  static int removeSpaces(char[] plain, int ps)
	  {
	    int count = 0;
	    for (int i = 0; i < ps; i++)
	      if (plain[i] != '\u0000')
	        plain[count++] = plain[i];

	    return count;
	  }

	
	///Tạo hàm kiểm tra văn bản cần mã hóa nhập vào hợp lệ hay không
	private static Boolean checkText(String text) {
		for (int i = 0; i < text.length(); i++) {
				if (text.charAt(i) == ' ')
					continue;
				else if (text.charAt(i) < 'A' ||  text.charAt(i) > 'z')
		    		 return false;
		 }
		return true;
	}
	/**
	 * KẾT THÚC Các hàm xử lý chuỗi
	 */
		
	
	
	
	
	
	/**
	 * BẮT ĐẦU Thuật toán cho Caesar
	 */
	///Hàm dùng để mã hóa và giải mã Caesar
	private static String encryptCaesar(String text, int key)
	{
		String cipherText = "";
	    //Duyệt văn bản
	    for (int i = 0; i < text.length(); i++) {
	        // Mã hóa từng ký tự
	        // Mã hóa ký tự in hoa
	    	if(text.charAt(i) == ' ')
	    		cipherText += ' ';
	    	else if (isUpper(text.charAt(i)))
	    		cipherText += (char)((text.charAt(i) + key - 65) % 26 + 65);
	    	 //Mã hóa ký tự thường
	    	 else cipherText +=  (char)((text.charAt(i) + key - 97 )% 26 + 97);
	    }
	    //Trả về chuỗi đã mã hóa/giải mã
		return cipherText;
	}
	
	///Hàm kiểm tra key của Caesar nhập vào có hợp lệ hay không
	private static Boolean checkKeyCaesar(String text) {
		//Duyệt văn bản
		for (int i = 0; i < text.length(); i++) {
			//Nếu có kí tự khác số trả về false
			if (text.charAt(i) < '0' ||  text.charAt(i) > '9')
				return false;
		}
		//Giá trị của key thuộc miền giá trị từ 0 đến 25
		//Nếu không nằm trong dãy giá trị đó trả về false
		//Ngược lại là true
		return Integer.parseInt(text) > 25 || Integer.parseInt(text) < 0 ? false : true;
	}
	

	/**
	 * KẾT THÚC Thuật toán cho Caesar
	 */
	
	
	
	
	
	
	
	
	/**
	 * BẮT ĐẦU thuật toán cho Monoalphabetic - bảng chữ đơn
	 */
	//
	///Bảng chữ cái tiếng Anh
	static String alphabet = "abcdefghijklmnopqrstuvwxyz";
	///Key do người dùng nhập vào
	
	///Hàm kiểm tra key nhập vào cho Monoalphabetic
	private static Boolean checkKeyMonoalphabetic(String key) {
		if(key.length() < 26) return false;
		for (int i = 0; i < key.length(); i++) {
			for (int j = i+1; j < key.length(); j++)
				if(key.charAt(i) == key.charAt(j))
					return false;
		}
		return key != "";
	}
	
	///Hàm dùng để mã hóa
	//Hàm để tìm ký tự trong chuỗi alphabet
	static public int posAlphabet(char c) {
		//dựa theo thứ tự của bảng mã ascii và bảng chữ cái tiếng Anh
		//Lấy c - 'a'. Được vị trí của nó trong chuỗi alphabet
		//Ví dụ 'a' - 'a' = 0
		return c - 'a';
	}
	
	//Hàm mã hóa
	static String monoalphabeticEncyption(String text, String key) {
		//Tạo cipher text rỗng
		String cipherText ="";
		//duyệt văn bản
		for (int i = 0; i < text.length(); i++)
		{
			//Nếu là khoảng trắng thì + khoảng trắng vào chuỗi cipherText
			if (text.charAt(i) == ' ')
			{
				cipherText += " ";
			}
			else if (isUpper(text.charAt(i)))
			{
				cipherText += toUpper(key.charAt((posAlphabet(toLower(text.charAt(i))))));
			}
			else {
				cipherText += key.charAt(posAlphabet(text.charAt(i)));
			}
		}
		return cipherText;
	}
	
	///Hàm dùng để gải mã
	//Hàm tìm vị trí của ký tự trong bảng chữ cái key
	static int posCipher(char c, String key) {
		for(int i = 0; i < key.length(); i++) {
			if(c == key.charAt(i))
				return i;
		}
		return -1;
	}
	
	//Hàm giải mã
	//Bda Dye dilovfheakgtbyzsmwnurxpqcj
	static String monoalphabeticDecyption(String text, String key){
		//Tạo plain text rỗng
		String plainText = "";
		//duyệt văn bản
		for (int i = 0; i < text.length(); i++)
		{
			if (text.charAt(i) == ' ')
			{
				plainText += " ";
			}
			else if(isUpper(text.charAt(i))) {
				plainText += toUpper(alphabet.charAt(posCipher(toLower(text.charAt(i)), key)));
			}
			else {
				plainText += alphabet.charAt(posCipher(text.charAt(i), key));
			}
		}
		return plainText;
	}
	/**
	 * KẾT THÚC thuật toán cho Monoalphabetic - bảng chữ đơn
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * BẮT ĐẦU thuật toán playfair
	 */
	//1. Cặp chữ giống nhau được tách ra bởi một ký tự bổ sung (x)
	//2. Hai chữ cái THUỘC một hàng = chữ cái bên phải (có nối vòng)
	//3. Hai chữ cái THUỘC một cột = chữ cái bên dưới (có nối vòng):
	//4. Mỗi chữ cái trong cặp → chữ cái nằm trong hàng riêng của nó 
	//và cột của chữ cái còn lại: hs → BP; ea → IM (JM)

	//Hàm kiểm tra key nhập vào có hợp lệ hay không
	private static Boolean checkKeyPlayfair(String key) {
		//Duyệt từng ký tự của key
		for (int i = 0; i < key.length(); i++) {
			//Nếu có ký tự khác a-z
				if(key.charAt(i) < 'a' || key.charAt(i) > 'z')
					return false;
		}
		return key != "";
	}
	
	//Tạo một ma trận khóa
	static String CreateMatrixkey(String key)
	{
		String Matrixkey =""; 
		int flag[] = new int[26]; 
		//khởi tạo giá trị ban đầu
		for(int k=0;k< 26; k++) 
			flag[k] = 0;
		//
		for(int i = 0; i < key.length(); i++)
		{
			int pos =  posAlphabet(key.charAt(i));
			//Nếu flag tại vị trí pos = 0
			//Thì cho ký tự vào ma trận khóa Matrixkey
			if(flag[pos] == 0) {
				Matrixkey += key.charAt(i); 
				flag[pos] = 1; 
			}
		}
		//kiểm tra i và j
		//Nếu một trong 2 bặt cờ thì bật cờ cả hai
		if(flag[posAlphabet('i')] == 1 || flag[posAlphabet('j')] == 1) 
			flag[posAlphabet('i') ] = flag[posAlphabet('j')] = 1;
		//Nếu cả hai đều không bật thì bật cờ của j
		else flag[posAlphabet('j')] = 1; 
		
		//Duyệt kiểm tra nếu cờ của ký tự nào không bật 
		//thì thêm vào ma trận khóa Matrixkey
		for(int i=0; i < 26; i++) 
			if(flag[i] == 0) 
				Matrixkey += alphabet.charAt(i);
		return Matrixkey;
	}
	
	//Cặp chữ giống nhau được tách ra bởi một ký tự bổ sung (x), 
	//và chuỗi lẻ thì thêm một ký tự bổ sung (x)
	static String split(String input) {
		String plaintext = ""; 
		int len = input.length(); 
		int i = 0; 
		while(i < len - 1) { 
			if(input.charAt(i) == input.charAt(i + 1)) { 
				plaintext += input.charAt(i); 
				plaintext += 'x'; 
				i += 1; 
			} 
			else { 
				plaintext += input.charAt(i); 
				i +=1;
			}
		}
		plaintext += input.charAt(input.length()-1);
				if(plaintext.length() % 2 != 0 )
					plaintext += 'x'; 
				return plaintext;
	}
	
	//
	int CreateIndex(String matrixKey) {
		int index[] = new int[26];
		for(int i=0; i < 25; i++) {
			int pos = posAlphabet(matrixKey.charAt(i));
			index [pos] = i; 
		} 
		index [posAlphabet('j')] = index [posAlphabet('i')]; 
		return index[26];
	}
	
	 //	
	 static String PlayFairEncryption(String input, String key) {
		String plaintext = split(input);
//		cout<<"\nplaintext after Split: " << plaintext<<" "<<plaintext.length();
		String matrixKey = CreateMatrixkey(key);

		int index[] = new int[26];
		for(int i=0; i < 25; i++) {
			int pos = posAlphabet(matrixKey.charAt(i));
			index [pos] = i; 
		} 
		index [posAlphabet('j')] = index [posAlphabet('i')]; 

		String ciphertext = "";
		for(int i = 0; i <= input.length(); i += 2) {
			int p1 = index[posAlphabet(plaintext.charAt(i))];
			int p2 = index[posAlphabet(plaintext.charAt(i + 1))];
			int row1 = p1/5, col1 = p1 % 5;
			int row2 = p2/5, col2 = p2 % 5;
			int c1, c2; 
			if (row1 == row2) { 
				col1 = (col1 + 1) % 5; 
				col2 = (col2 + 1) % 5; 
				c1 = row1*5+ col1; 
				c2= row2 * 5 + col2; 
			}
			else if (col1 == col2) {
				row1 = (row1 + 1)%5;
				row2 = (row2 + 1)%5;
				c1 = row1 * 5 + col1;
				c2 = row2 * 5 + col2;
				
			}
			else {
				c1 = row1 * 5 + col2;
				c2 = row2 * 5 + col1;
			}
			ciphertext += matrixKey.charAt(c1); 
			ciphertext += matrixKey.charAt(c2); 	
			}
		return ciphertext;
	}
	/**
	 * KẾT THÚC thuật toán playfair
	 */
	
	
	
	
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
				if(checkText(txtpnInput.getText()) && checkKeyCaesar(textKey.getText())) {
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
				if(checkText(txtpnInput.getText()) && checkKeyCaesar(textKey.getText())) {
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
		 * Tạo và đặt thuộc tính Tab Monoalphabetic cipher
		 */
		JPanel panelMonoalphabetic = new JPanel();
		tabbedPane.addTab("Monoalphabetic cipher", null, panelMonoalphabetic, null);
		panelMonoalphabetic.setLayout(null);
		
		///Tạo pane bên trái
		JPanel monoalphabeticPanelLeft = new JPanel();
		monoalphabeticPanelLeft.setLayout(null);
		monoalphabeticPanelLeft.setBounds(10, 10, 481, 416);
		panelMonoalphabetic.add(monoalphabeticPanelLeft);
		
		//Tạo thanh trượt cho text đầu vào
		JScrollPane scrollPaneInput_1 = new JScrollPane();
		scrollPaneInput_1.setBounds(10, 10, 461, 183);
		monoalphabeticPanelLeft.add(scrollPaneInput_1);
		
		//Tạo ô nhập text đầu vào
		JTextArea txtpnInput_1 = new JTextArea();
		txtpnInput_1.setLineWrap(true);
		txtpnInput_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPaneInput_1.setViewportView(txtpnInput_1);
		
		//Tạo thanh trượt cho text đầu ra
		JScrollPane scrollPaneResult_1 = new JScrollPane();
		scrollPaneResult_1.setBounds(10, 223, 461, 183);
		monoalphabeticPanelLeft.add(scrollPaneResult_1);
		
		//Tạo ô xuất văn bản đầu ra
		JTextArea txtpnResult_1 = new JTextArea();
		txtpnResult_1.setLineWrap(true);
		txtpnResult_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtpnResult_1.setEditable(false);
		scrollPaneResult_1.setViewportView(txtpnResult_1);
		
		///Tạo pane bên phải
		JPanel monoalphabeticPanelRight = new JPanel();
		monoalphabeticPanelRight.setLayout(null);
		monoalphabeticPanelRight.setBounds(501, 10, 270, 416);
		panelMonoalphabetic.add(monoalphabeticPanelRight);
		
		//Tạo label cho ô nhập key
		JLabel lblNewLabel_1 = new JLabel("Key");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 221, 27, 17);
		monoalphabeticPanelRight.add(lblNewLabel_1);
		
		//Tạo ô nhập key
		JTextField textKey_1 = new JTextField();
		textKey_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textKey_1.setColumns(10);
		textKey_1.setBounds(47, 220, 213, 19);
		monoalphabeticPanelRight.add(textKey_1);
		
		//Tạo và đặt thuộc tính cho nút mã hóa
		JButton btnEncryption_1 = new JButton("Encryption");		
		btnEncryption_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEncryption_1.setBounds(18, 316, 108, 21);
		monoalphabeticPanelRight.add(btnEncryption_1);
		
		//Xử lý sự kiện của nút
		btnEncryption_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Kiểm tra văn bản và key nhập vào có hợp lệ hay không
				if(checkText(txtpnInput_1.getText()) && checkKeyMonoalphabetic(textKey_1.getText())) {
					//Nếu hợp lệ gọi hàm mã hóa
					txtpnResult_1.setText(monoalphabeticEncyption(txtpnInput_1.getText(),textKey_1.getText()));
					
				}
				//Ngược lại không hiển thị tin nhắn thông báo
				else JOptionPane.showMessageDialog(
						contentPane, 
	                    "Văn bản nhập vào chứa các ký tự từ a-Z. \n"
	                    + "Không có số và ký tự đặc biệt trong văn bản. \n"
	                    + "Key nhập vào gồm 26 ký tự tiếng Anh và không trùng nhau. \n"
	                    + "Vui lòng kiểm tra và nhập lại.", 
	                    "Thông báo!", 
	                    JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		//Tạo và đặt thuộc tính cho nút giải mã
		JButton btnDecryption_1 = new JButton("Decryption");
		btnDecryption_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDecryption_1.setBounds(144, 316, 108, 21);
		monoalphabeticPanelRight.add(btnDecryption_1);
		
		//Xử lý sự kiện của nút
		btnDecryption_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Kiểm tra văn bản và key nhập vào có hợp lệ hay không
				if(checkText(txtpnInput_1.getText()) && checkKeyMonoalphabetic(textKey_1.getText())) {
					//Nếu hợp lệ gọi hàm giải mã
					txtpnResult_1.setText(monoalphabeticDecyption(txtpnInput_1.getText(),textKey_1.getText()));
					
				}
				//Ngược lại không hiển thị tin nhắn thông báo
				else JOptionPane.showMessageDialog(
						contentPane, 
	                    "Văn bản nhập vào chứa các ký tự từ a-Z. \n"
	                    + "Không có số và ký tự đặc biệt trong văn bản. \n"
	                    + "Key nhập vào gồm 26 ký tự tiếng Anh và không trùng nhau. \n"
	                    + "Vui lòng kiểm tra và nhập lại.", 
	                    "Thông báo!", 
	                    JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		//Tạo heading cho tab Monoalphabetic cipher
		Label heading_1 = new Label("Monoalphabetic cipher");
		heading_1.setFont(new Font("Dialog", Font.BOLD, 14));
		heading_1.setAlignment(Label.CENTER);
		heading_1.setBounds(10, 77, 250, 66);
		monoalphabeticPanelRight.add(heading_1);
		
		//Tạo ô hiển thị bảng chữ cái tiếng anh
		JTextField txtAlphabet = new JTextField();
		txtAlphabet.setEditable(false);
		txtAlphabet.setText("abcdefghijklmnopqrstuvwxyz");
		txtAlphabet.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtAlphabet.setColumns(10);
		txtAlphabet.setBounds(47, 249, 213, 19);
		monoalphabeticPanelRight.add(txtAlphabet);
		
		
		
		/**
		 * Tạo và đặt thuộc tính cho Tab Playfair cipher
		 */
		JPanel panelPlayfair = new JPanel();
		tabbedPane.addTab("Playfair cipher", null, panelPlayfair, null);
		panelPlayfair.setLayout(null);
		
		//Tạo pane bên trái cho tab playfair
		JPanel playfairPanelLeft = new JPanel();
		playfairPanelLeft.setLayout(null);
		playfairPanelLeft.setBounds(10, 11, 481, 416);
		panelPlayfair.add(playfairPanelLeft);
		
		//Tạo thanh trượt cho ô nhập văn bản
		JScrollPane scrollPaneInput_2 = new JScrollPane();
		scrollPaneInput_2.setBounds(10, 10, 461, 183);
		playfairPanelLeft.add(scrollPaneInput_2);
		
		//Tạo ô nhập văn bản đầu vào
		JTextArea txtpnInput_2 = new JTextArea();
		txtpnInput_2.setLineWrap(true);
		txtpnInput_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPaneInput_2.setViewportView(txtpnInput_2);
		
		//Tạo thanh trượt cho ô xuất văn bản
		JScrollPane scrollPaneResult_1_1 = new JScrollPane();
		scrollPaneResult_1_1.setBounds(10, 223, 461, 183);
		playfairPanelLeft.add(scrollPaneResult_1_1);
		
		//Tạo ô xuất văn bản đầu ra
		JTextArea txtpnResult_2 = new JTextArea();
		txtpnResult_2.setLineWrap(true);
		txtpnResult_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtpnResult_2.setEditable(false);
		scrollPaneResult_1_1.setViewportView(txtpnResult_2);
		
		//Tạo pane bên phải cho tab playfair
		JPanel playfairPanelRight = new JPanel();
		playfairPanelRight.setLayout(null);
		playfairPanelRight.setBounds(501, 11, 270, 416);
		panelPlayfair.add(playfairPanelRight);
		
		//Tạo label cho ô nhập key
		JLabel lblNewLabel_2 = new JLabel("Key");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 220, 27, 17);
		playfairPanelRight.add(lblNewLabel_2);
		
		//Tạo ô nhập key
		JTextField textKey_2 = new JTextField();
		textKey_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textKey_2.setColumns(10);
		textKey_2.setBounds(47, 220, 213, 19);
		playfairPanelRight.add(textKey_2);
		
		//Tạo nút mã hóa
		JButton btnEncryption_2 = new JButton("Encryption");
		btnEncryption_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				//Kiểm tra văn bản và key nhập vào có hợp lệ hay không
				if(checkText(txtpnInput_2.getText()) && checkKeyPlayfair(textKey_2.getText())) {
					//Nếu hợp lệ gọi hàm giải mã
//					txtpnResult_2.setText(PlayFairEncryption(txtpnResult_2.getText(), textKey_2.getText()));
				}
				//Ngược lại không hiển thị tin nhắn thông báo
				else JOptionPane.showMessageDialog(
						contentPane, 
	                    "Văn bản nhập vào chứa các ký tự từ a-Z. \n"
	                    + "Không có số và ký tự đặc biệt trong văn bản. \n"
	                    + "Key nhập vào gồm các ký tự từ a-Z.\n"
	                    + "Không có số và ký tự đặc biệt trong key."
	                    + "Vui lòng kiểm tra và nhập lại.", 
	                    "Thông báo!", 
	                    JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnEncryption_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEncryption_2.setBounds(18, 316, 108, 21);
		playfairPanelRight.add(btnEncryption_2);
		
		//Tạo nút giải mã
		JButton btnDecryption_2 = new JButton("Decryption");
		btnDecryption_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDecryption_2.setBounds(144, 316, 108, 21);
		playfairPanelRight.add(btnDecryption_2);
		
		//Tạo heading cho tab playfair
		Label heading_2 = new Label("Playfair cipher");
		heading_2.setFont(new Font("Dialog", Font.BOLD, 14));
		heading_2.setAlignment(Label.CENTER);
		heading_2.setBounds(10, 77, 250, 66);
		playfairPanelRight.add(heading_2);
		
		
		
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
