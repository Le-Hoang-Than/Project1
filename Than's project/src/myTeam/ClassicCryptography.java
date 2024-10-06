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
	private JTextField textKey_2;
	///Key do người dùng nhập vào
	
	///Hàm kiểm tra key nhập vào cho Monoalphabetic
	private static Boolean checkKeyMonoalphabetic(String key) {
		if(key.length() < 26) return false;
		for (int i = 0; i < key.length(); i++) {
			for (int j = i+1; j < key.length(); j++)
				if(key.charAt(i) == key.charAt(j))
					return false;
		}
		return true;
	}
	
	///Hàm dùng để mã hóa
	//Hàm để tìm ký tự của c trong chuỗi alphabet
	static public int posAlphabet(char c) {
		//dựa theo thứ tự của bảng mã ascii và bảng chữ cái tiếng Anh
		//Lấy c - 'a'. Được vị trí của nó trong chuỗi alphabet
		//Ví dụ 'a' - 'a' = 0
		return c - 'a';
	}
	
	//Hàm mã hóa
	//M dilovfheakgtbyzsmwnurxpqcj
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
	 //Hàm chuyển chuỗi thành chữ thường
	  static void toLowerCase(char plain[], int ps)
	  {
	    int i;
	    for (i = 0; i < ps; i++) {
	      if (plain[i] > 64 && plain[i] < 91)
	        plain[i] += 32;
	    }
	  }

	  //Hàm xóa tất cả khoảng trắng trong chuỗi
	  static int removeSpaces(char[] plain, int ps)
	  {
	    int i, count = 0;
	    for (i = 0; i < ps; i++)
	      if (plain[i] != '\u0000')
	        plain[count++] = plain[i];

	    return count;
	  }

	  //Hàm tạo hình vuông key 5x5
	  static void generateKeyTable(char key[], int ks, char keyT[][])
	  {
	    int i, j, k, flag = 0;
	    
	    // một bản đồ băm 26 ký tự
	    // để lưu trữ số lượng bảng chữ cái
	    int dicty[] = new int[26];
	    for (i = 0; i < ks; i++) {
	      if (key[i] != 'j')
	        dicty[key[i] - 97] = 2;
	    }

	    dicty['j' - 97] = 1;

	    i = 0;
	    j = 0;

	    for (k = 0; k < ks; k++) {
	      if (dicty[key[k] - 97] == 2) {
	        dicty[key[k] - 97] -= 1;
	        keyT[i][j] = key[k];
	        j++;
	        if (j == 5) {
	          i++;
	          j = 0;
	        }
	      }
	    }

	    for (k = 0; k < 26; k++) {
	      if (dicty[k] == 0) {
	        keyT[i][j] = (char)(k + 97);
	        j++;
	        if (j == 5) {
	          i++;
	          j = 0;
	        }
	      }
	    }
	  }
	  //Hàm tìm kiếm ký tự của chữ ghép
	 // trong ô khóa và trả về vị trí của chúng
	  static void search(char keyT[][], char a, char b, int arr[])
	  {
	    int i, j;

	    if (a == 'j')
	      a = 'i';
	    else if (b == 'j')
	      b = 'i';

	    for (i = 0; i < 5; i++) {

	      for (j = 0; j < 5; j++) {

	        if (keyT[i][j] == a) {
	          arr[0] = i;
	          arr[1] = j;
	        }
	        else if (keyT[i][j] == b) {
	          arr[2] = i;
	          arr[3] = j;
	        }
	      }
	    }
	  }

	//Hàm tìm mô đun với 5
	  static int mod5(int a) { return (a % 5); }

	//Hàm làm cho văn bản thuần có độ dài chẵn
	  static int prepare(char str[], int ptrs)
	  {
	    if (ptrs % 2 != 0) {
	      str[ptrs++] = 'z';
	      str[ptrs] = '\0';
	    }
	    return ptrs;
	  }

	//Hàm thực hiện mã hóa
	  static void encrypt(char str[], char keyT[][], int ps)
	  {
	    int i;
	    int[] a =new int[4];

	    for (i = 0; i < ps; i += 2) {

	      search(keyT, str[i], str[i + 1], a);

	      if (a[0] == a[2]) {
	        str[i] = keyT[a[0]][mod5(a[1] + 1)];
	        str[i + 1] = keyT[a[0]][mod5(a[3] + 1)];
	      }
	      else if (a[1] == a[3]) {
	        str[i] = keyT[mod5(a[0] + 1)][a[1]];
	        str[i + 1] = keyT[mod5(a[2] + 1)][a[1]];
	      }
	      else {
	        str[i] = keyT[a[0]][a[3]];
	        str[i + 1] = keyT[a[2]][a[1]];
	      }
	    }
	  }

	//Hàm mã hóa bằng Playfair Cipher
	  static void encryptByPlayfairCipher(char str[], char key[])
	  {
	    int ps; 
	    int ks;
	    char[][] keyT = new char[5][5];

	    // Key
	    ks = key.length;
	    ks = removeSpaces(key, ks);
	    toLowerCase(key, ks);

	    // Plaintext
	    ps = str.length;
	    toLowerCase(str, ps);
	    ps = removeSpaces(str, ps);

	    ps = prepare(str, ps);

	    generateKeyTable(key, ks, keyT);

	    encrypt(str, keyT, ps);
	  }

	  static void strcpy(char[] arr, String s) {
	    for(int i = 0;i < s.length();i++){
	      arr[i] = s.charAt(i);
	    }
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
		 * 
		 */
		JPanel panelPlayfair = new JPanel();
		tabbedPane.addTab("Playfair cipher", null, panelPlayfair, null);
		panelPlayfair.setLayout(null);
		
		JPanel playfairPanelLeft = new JPanel();
		playfairPanelLeft.setLayout(null);
		playfairPanelLeft.setBounds(10, 11, 481, 416);
		panelPlayfair.add(playfairPanelLeft);
		
		JScrollPane scrollPaneInput_2 = new JScrollPane();
		scrollPaneInput_2.setBounds(10, 10, 461, 183);
		playfairPanelLeft.add(scrollPaneInput_2);
		
		JTextArea txtpnInput_2 = new JTextArea();
		txtpnInput_2.setLineWrap(true);
		txtpnInput_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPaneInput_2.setViewportView(txtpnInput_2);
		
		JScrollPane scrollPaneResult_1_1 = new JScrollPane();
		scrollPaneResult_1_1.setBounds(10, 223, 461, 183);
		playfairPanelLeft.add(scrollPaneResult_1_1);
		
		JTextArea txtpnResult_2 = new JTextArea();
		txtpnResult_2.setLineWrap(true);
		txtpnResult_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtpnResult_2.setEditable(false);
		scrollPaneResult_1_1.setViewportView(txtpnResult_2);
		
		JPanel playfairPanelRight = new JPanel();
		playfairPanelRight.setLayout(null);
		playfairPanelRight.setBounds(501, 11, 270, 416);
		panelPlayfair.add(playfairPanelRight);
		
		JLabel lblNewLabel_2 = new JLabel("Key");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 220, 27, 17);
		playfairPanelRight.add(lblNewLabel_2);
		
		textKey_2 = new JTextField();
		textKey_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textKey_2.setColumns(10);
		textKey_2.setBounds(47, 220, 213, 19);
		playfairPanelRight.add(textKey_2);
		
		JButton btnEncryption_2 = new JButton("Encryption");
		btnEncryption_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEncryption_2.setBounds(18, 316, 108, 21);
		playfairPanelRight.add(btnEncryption_2);
		
		JButton btnDecryption_2 = new JButton("Decryption");
		btnDecryption_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDecryption_2.setBounds(144, 316, 108, 21);
		playfairPanelRight.add(btnDecryption_2);
		
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
