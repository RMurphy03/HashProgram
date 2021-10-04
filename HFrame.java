//Ryan Murphy
//CM341
//HashProgram
//md5 sha1 sha256


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;

public class HFrame extends JFrame
{

private JButton exit;
private JButton submit;
private JButton clear;
private JButton selectFile;
private JComboBox<String> hashCB;
private JOptionPane jop;
private JTextField fileSelected;
private JTextField jtfh;
private JFileChooser fileChooser;
private byte[] bytes;
private byte[] temp;
private MessageDigest md;
private JPanel gL;

public HFrame() 
{

	super("Ryan's Hash Program");
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	fileChooser = new JFileChooser("d:");
	//fileChooser = new JFileChooser(".");


	gL = new JPanel();
	gL.setLayout(new GridLayout(3,1));
	add(gL);
	
	
	String [] hashes = new String[] { "(Select Hash)" , "MD5", "SHA1" , "SHA256" } ;
	hashCB = new JComboBox<String>();
	
	for(int i=0; i<hashes.length; i++)
	{
		String temp = hashes[i];
		hashCB.addItem(temp);
	}

	setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	
	JPanel topPanel = new JPanel();
	gL.add(topPanel);
	selectFile = new JButton("Select File");
	topPanel.add(selectFile);
	fileSelected = new JTextField(30);
	jtfh = new JTextField(50);
	topPanel.add(fileSelected);
	fileSelected.setEditable(false);
	jtfh.setEditable(false);
	
	
	
	exit = new JButton("Exit");
	submit = new JButton("Submit");
	clear = new JButton("Clear");
	ActionHandler ah = new ActionHandler();	
	exit.addActionListener(ah);
	submit.addActionListener(ah);
	clear.addActionListener(ah);
	selectFile.addActionListener(ah);
	
	JPanel buttonPanel = new JPanel(); 
	
	
	
	JPanel midPanel = new JPanel();
	gL.add(midPanel);
	midPanel.add(hashCB);
	

	buttonPanel.add(submit);
	buttonPanel.add(clear);
	buttonPanel.add(exit);
	
	gL.add(buttonPanel);
	

	jop = new JOptionPane();
	
	jop.add(jtfh);
	
	
	setSize(400,300);
	setVisible(true);

}


private class ActionHandler implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
		
		if(e.getSource()==exit)
		{
			System.exit(0);
		}
		else if(e.getSource()==clear)
		{
			clearUI();
				
		}
		
		else if(e.getSource()==selectFile)
		{
		fileChooser.showSaveDialog(null);
		fileSelected.setText(fileChooser.getSelectedFile().getAbsolutePath());
		
		
		File file = fileChooser.getSelectedFile();
		
		
               bytes=readContentIntoByteArray(file);
               
               
 
			
		} 
		
		
		else if(e.getSource()==submit && hashCB.getSelectedIndex()!=0 )
		{
		
		
			
			
			if(hashCB.getSelectedIndex() == 1) // MD5
			{
			
				
				
				System.out.println("MD5");
				
				try
				{
					temp = messageDigester("MD5",bytes);
				}
				catch(NoSuchAlgorithmException x)
				{
					System.out.println(x);
				}
				
				    
				String stemp = toHex(temp);   
				jtfh.setText(stemp);
				jop.showMessageDialog(HFrame.this,jtfh,"MD5 Hash", JOptionPane.INFORMATION_MESSAGE);  
				
				
				      
			}
			
			else if(hashCB.getSelectedIndex() == 2)//SHA1
			{
				System.out.println("SHA1");
				
				try
				{
					temp = messageDigester("SHA-1",bytes);
				}
				catch(NoSuchAlgorithmException x)
				{
					System.out.println(x);
				}
				
				    
				String stemp = toHex(temp);
				jtfh.setText(stemp);   
				jop.showMessageDialog(HFrame.this,jtfh,"SHA-1 Hash", JOptionPane.INFORMATION_MESSAGE);  
				
	
				      
			}
			
			else if(hashCB.getSelectedIndex() == 3) //SHA256
			{
				System.out.println("SHA256");
				//byte[] temp;
				try
				{
					temp = messageDigester("SHA-256",bytes);
				}
				catch(NoSuchAlgorithmException x)
				{
					System.out.println(x);
				}
				
				    
				String stemp = toHex(temp);   
				jtfh.setText(stemp);  
				jop.showMessageDialog(HFrame.this,jtfh,"SHA-256 Hash", JOptionPane.INFORMATION_MESSAGE);  
				

			  }
				      
				     
			
	
			
			}
			
		
	}
	}
	
public void clearUI()
{
	hashCB.setSelectedIndex(0);
	
	fileSelected.selectAll();
	fileSelected.replaceSelection("");
	jtfh.selectAll();
	jtfh.replaceSelection("");
	fileChooser.setSelectedFile(new File(""));
	//JFileChooser.CANCEL_OPTION;
}

private static byte[] readContentIntoByteArray(File file)
   {
      FileInputStream fileInputStream = null;
      byte[] bFile = new byte[(int) file.length()];
	      try
	      {
		 //convert file into array of bytes
		 fileInputStream = new FileInputStream(file);
		 fileInputStream.read(bFile);
		 fileInputStream.close();
			 for (int i = 0; i < bFile.length; i++)
			 {
			    System.out.print((char) bFile[i]);
			 }
	      }
	      catch (Exception e)
	      {
		 e.printStackTrace();
	      }
      return bFile;
   }
   
public byte[] messageDigester(String s, byte[] bytes) throws NoSuchAlgorithmException
{
	md = MessageDigest.getInstance(s);
	md.update(bytes);
	byte[] digest = md.digest();
	return digest;
}
public static String toHex(byte[] bytes)
{
	BigInteger bi = new BigInteger(1,bytes);
	return String.format("%0" + (bytes.length << 1) + "x", bi);
}
 




public static void main(String[] args) 
{
	new HFrame();

}




}
