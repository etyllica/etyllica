package br.com.etyllica.core;

import java.awt.Color;
import java.awt.Font;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class Theme {
	
	public Theme(){
		super();
		
		reloadFonts();
	}
	
	private Font buttonFont;
	private Font font;

	private int fontSize = 14;
	private int fontStyle = Font.PLAIN;
	private String fontName = FONT_DEFAULT;
	
	//public static final String FONT_DEFAULT = "DejaVuSans.ttf";
	public static final String FONT_DEFAULT = "Dialog";
	public static final String FONT_JAPANESE = "mona.ttf";
	
	public void reloadFonts(){
		buttonFont = new Font(fontName,fontStyle,fontSize);
		font = new Font(fontName, fontStyle, fontSize);
		
		//font = new Font("Dialog", fontStyle, fontSize);
		//font = new Font("DialogInput", fontStyle, fontSize);
		//font = new Font("Serif", fontStyle, fontSize);
		//font = new Font("SansSerif", fontStyle, fontSize);
		//font = new Font("Monospaced", fontStyle, fontSize);
	}
		
	private boolean shadow = true;
	private Color shadowColor = Color.BLACK;
	
	private String wallpaper = "mystic/sunset.jpg";
	
	private Color backGroundColor = new Color(0x66, 0x66, 0x66);
	
	private Color barColor = new Color(0x00,0x00, 0x00, 0xa0);
	private Color barOnMouseColor = new Color(0x33, 0x33, 0x33, 0xa0);
		
	private Color buttonColor = new Color(0x00,0x00, 0x00, 0xa0);
	private Color buttonOnFocus = new Color(0x33, 0x33, 0x99, 0xa0);	
	private Color buttonOnMouse = new Color(0x33, 0x33, 0x33, 0xa0);
	private Color buttonOnClick = new Color(0x99, 0x99, 0x99, 0xa0);
	
	//private Color textColor = new Color(0x00, 0x00, 0x00);
	private Color textColor = new Color(0xff, 0xff, 0xff);
	private Color textSelectedColor = new Color(0x00, 0x00, 0x00);
	private Color textMarkColor = new Color(0x00, 0x00, 0x88,0xa0);
	
	private Color textFieldColor = new Color(0xff, 0xff, 0xff);
	private Color textFieldWithoutFocusColor = new Color (0xaa, 0xaa, 0xaa,0xcc);
	private Color textFieldOnMouseColor = new Color(0x00, 0x00, 0x88,0xa0);
	
	private Color selectionTextColor = new Color(0x00, 0x00, 0xff);
	
	private Color panelColor = new Color(0x00, 0x00, 0x00, 0xA0);
	
	private Color mouseArrowColor = new Color(0xff, 0xff, 0xff);
	private Color mouseArrowBorderColor = new Color(0x00, 0x00, 0x00);
	
	private Color windowBackgroundColor = new Color(0x55, 0x55, 0x55,0xdd);
	
	public String getWallpaper() {
		return wallpaper;
	}

	public void setWallpaper(String wallpaper) {
		this.wallpaper = wallpaper;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	
	public int getFontStyle() {
		return fontStyle;
	}

	public void setFontStyle(int fontStyle) {
		this.fontStyle = fontStyle;
	}

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public boolean isShadow() {
		return shadow;
	}

	public void setShadow(boolean shadow) {
		this.shadow = shadow;
	}

	public Color getBackGroundColor() {
		return backGroundColor;
	}

	public void setBackGroundColor(Color backGroundColor) {
		this.backGroundColor = backGroundColor;
	}

	public Color getBarColor() {
		return barColor;
	}

	public void setBarColor(Color barColor) {
		this.barColor = barColor;
	}
	
	public Color getBarOnMouseColor() {
		return barOnMouseColor;
	}

	public void setBarOnMouseColor(Color barOnMouseColor) {
		this.barOnMouseColor = barOnMouseColor;
	}

	public Color getButtonColor() {
		return buttonColor;
	}

	public void setButtonColor(Color buttonColor) {
		this.buttonColor = buttonColor;
	}

	public Color getButtonOnMouse() {
		return buttonOnMouse;
	}

	public void setButtonOnMouse(Color buttonOnMouse) {
		this.buttonOnMouse = buttonOnMouse;
	}
	
	public Color getButtonOnFocus() {
		return buttonOnFocus;
	}

	public void setButtonOnFocus(Color buttonOnFocus) {
		this.buttonOnFocus = buttonOnFocus;
	}

	public Color getButtonOnClick() {
		return buttonOnClick;
	}

	public void setButtonOnClick(Color buttonOnClick) {
		this.buttonOnClick = buttonOnClick;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public Color getTextSelectedColor() {
		return textSelectedColor;
	}

	public void setTextSelectedColor(Color textSelectedColor) {
		this.textSelectedColor = textSelectedColor;
	}

	public Color getTextFieldColor() {
		return textFieldColor;
	}

	public void setTextFieldColor(Color textFieldColor) {
		this.textFieldColor = textFieldColor;
	}

	public Color getTextFieldWithoutFocusColor() {
		return textFieldWithoutFocusColor;
	}

	public void setTextFieldWithoutFocusColor(Color textFieldWithoutFocusColor) {
		this.textFieldWithoutFocusColor = textFieldWithoutFocusColor;
	}

	public Color getSelectionTextColor() {
		return selectionTextColor;
	}

	public void setSelectionTextColor(Color selectionTextColor) {
		this.selectionTextColor = selectionTextColor;
	}

	public Color getPanelColor() {
		return panelColor;
	}

	public void setPanelColor(Color panelColor) {
		this.panelColor = panelColor;
	}

	public Color getMouseArrowColor() {
		return mouseArrowColor;
	}

	public void setMouseArrowColor(Color mouseArrowColor) {
		this.mouseArrowColor = mouseArrowColor;
	}
	
	public Color getMouseArrowBorderColor() {
		return mouseArrowBorderColor;
	}
	
	public void setMouseArrowBorderColor(Color mouseArrowBorderColor) {
		this.mouseArrowBorderColor = mouseArrowBorderColor;
	}

	public Color getTextMarkColor() {
		return textMarkColor;
	}

	public void setTextMarkColor(Color textMarkColor) {
		this.textMarkColor = textMarkColor;
	}

	public Color getShadowColor() {
		return shadowColor;
	}

	public void setShadowColor(Color shadowColor) {
		this.shadowColor = shadowColor;
	}

	public Color getWindowBackgroundColor() {
		return windowBackgroundColor;
	}

	public void setWindowBackgroundColor(Color windowBackgroundColor) {
		this.windowBackgroundColor = windowBackgroundColor;
	}

	public Color getTextFieldOnMouseColor() {
		return textFieldOnMouseColor;
	}

	public void setTextFieldOnMouseColor(Color textFieldOnMouseColor) {
		this.textFieldOnMouseColor = textFieldOnMouseColor;
	}
	
	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Font getButtonFont() {
		return buttonFont;
	}

	public void setButtonFont(Font buttonFont) {
		this.buttonFont = buttonFont;
	}
			
}
