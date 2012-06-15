package br.com.etyllica.gui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
   
public class TextBox extends PlainDocument
{
	
	private static final long serialVersionUID = 1L;
	private int iMaxLength;
 
	public TextBox(int maxlen) {
		super();
		iMaxLength = maxlen;
	}

	 public void insertString(int offset, String str, AttributeSet attr)  
	 //public void insertString(String str, AttributeSet attr)
	             throws BadLocationException {
		
		 
	     if (str == null) return;  
	   
	     if (iMaxLength <= 0)        // aceitar� qualquer no. de caracteres  
	     {  
	         super.insertString(offset, str, attr);  
	         return;  
	     }  
	   
	     int ilen = (getLength() + str.length());  
	     if (ilen <= iMaxLength)    // se o comprimento final for menor...  
	         super.insertString(offset, str, attr);   // ...aceita str  
	     else  
	     {  
	         if (getLength() == iMaxLength) return; // nada a fazer  
	         String newStr = str.substring(0, (iMaxLength - getLength()));  
	   
	         super.insertString(offset, newStr, attr);  
	     }  
	 }  
}

