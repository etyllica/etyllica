package br.com.etyllica.gui;

import java.awt.Color;
import java.awt.FontMetrics;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.input.mouse.MouseButton;
import br.com.etyllica.gui.textfield.TextView;
import br.com.etyllica.layer.BufferedLayer;
import br.com.etyllica.theme.Theme;
import br.com.etyllica.theme.ThemeManager;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class TextField extends TextView {

	private BufferedLayer layer;
		
	public TextField(int x, int y, int w, int h) {
		super(x,y,w,h);
		
		layer = new BufferedLayer(x, y, w+1, h+1);
	}
	
	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		
		//if(onFocus) {
			
			//Update component with typed events
			if(event.getChar()!='\0') {
				updateChar(event.getChar());
			}
						
			//TODO Next Component
			//Update component with Pressed Events
			GUIEvent pressedEvent = updatePressed(event);
			
			//Update component with Released Events
			GUIEvent releasedEvent = updateReleased(event);

			minMark = getMinMark();
			maxMark = getMaxMark();
			
			if(pressedEvent!=GUIEvent.NONE) {
				return pressedEvent;		
			}
			
			if(releasedEvent!=GUIEvent.NONE) {
				return releasedEvent;		
			}
			
		//}

		return GUIEvent.NONE;
	}

	public GUIEvent updateMouse(PointerEvent event) {

		if((event.isButtonDown(MouseButton.MOUSE_BUTTON_LEFT))) {

			if(mouseOver) {

				//TODO posiciona cursor onMouse

				return GUIEvent.GAIN_FOCUS;

			}else{

				return GUIEvent.LOST_FOCUS;
			}

		}else if(mouseOver) {

			if(!onFocus) {
				
				return GUIEvent.MOUSE_OVER;
				
			}else{
				
				return GUIEvent.MOUSE_OVER_WITH_FOCUS;
			}

		}

		return GUIEvent.NONE;
	}

	private boolean shift = false;
	private boolean control = false;

	private GUIEvent updatePressed(KeyEvent event) {

		if(!shift) {

			if((event.isKeyDown(KeyEvent.TSK_SHIFT_DIREITA))||(event.isKeyDown(KeyEvent.TSK_SHIFT_ESQUERDA))) {
				shift = true;
				fixMark = cursor;
			}
			else if(event.isKeyDown(KeyEvent.TSK_SETA_ESQUERDA)||(event.isKeyDown(KeyEvent.TSK_SETA_DIREITA))) {
				fixMark = -1;
			}
		}

		if(event.isKeyDown(KeyEvent.TSK_END)) {
			cursor = text.length();
		}
		else if(event.isKeyDown(KeyEvent.TSK_HOME)) {
			moveCursorToStart();
			//move cursor to start
		}

		if(!control) {
			if(event.isKeyDown(KeyEvent.TSK_CTRL_DIREITA)||event.isKeyDown(KeyEvent.TSK_CTRL_ESQUERDA)) {
				control = true;
			}
		}

		if(event.isKeyDown(KeyEvent.TSK_SETA_ESQUERDA)) {
			if(control) {
				leftWithControl();
			} else {
				leftNormal();
			}
		}
		else if(event.isKeyDown(KeyEvent.TSK_SETA_DIREITA)) {

			if(control) {
				rightWithControl();
			}else{
				rightNormal();
			}
		}

		if(event.isKeyDown(KeyEvent.TSK_TAB)) {

			return GUIEvent.NEXT_COMPONENT;
		}

		return GUIEvent.NONE;
	}

	private GUIEvent updateReleased(KeyEvent event) {
		if(control) {
			if(event.isKeyDown(KeyEvent.TSK_CTRL_DIREITA)||event.isKeyDown(KeyEvent.TSK_CTRL_ESQUERDA)) {
				control = false;
			}
		}

		if(shift) {
			if(event.isKeyDown(KeyEvent.TSK_SHIFT_DIREITA)||event.isKeyDown(KeyEvent.TSK_SHIFT_ESQUERDA)) {
				shift = false;
			}
		}
		
		return GUIEvent.NONE;	
	}

	//TODO escreve texto.sub(0,minMark);
	//Para não sair da caixa
	public void draw(Graphic g) {

		Theme theme = ThemeManager.getInstance().getTheme();
		
		g.setImage(layer.getBuffer());
		int x = 0;
		int y = 0;

		//TODO
		//g.setFont(theme.getFont());

		//Para poder ser usado pelo password
		String text = this.getText();

		int fontSize = theme.getFontSize();

		FontMetrics metrics = g.getGraphics().getFontMetrics();

		float dif = w-metrics.stringWidth(text);

		//Remover
		if(onFocus) {
			g.setColor(theme.getTextFieldColor());
		}else{
			g.setColor(theme.getTextFieldWithoutFocusColor());
		}

		if(mouseOver) {
			g.setColor(theme.getTextFieldOnMouseColor());	
		}

		g.drawRect(x,y,w,h);

		g.setColor(theme.getTextColor());

		if(minMark == 0 && maxMark == 0) {

			if(dif>0) {
				g.drawShadow(x, y+h/2+fontSize/2, text);
			}else{
				g.drawShadow(x+dif, y+h/2+fontSize/2, text);
			}

		}else{

			/** Desenha Mark **/

			// get metrics from the graphics

			int cx = metrics.stringWidth(text.substring(0,minMark));

			int cxm = metrics.stringWidth(text.substring(minMark,maxMark));

			//Invert contentColor
			g.setColor(theme.getSelectionTextColor());

			//fill Mark Rect
			g.fillRect(x+cx+2,y+2,cxm, h-3);			

			//Invert textColor

			//Por enquanto escreve normal
			//g.setColor(theme.getTextMarkColor());
			//g.setColor(Color.BLACK);
			g.setColor(theme.getTextColor());
			g.drawShadow(x, y+h/2+fontSize/2, text.substring(0,minMark));

			g.setColor(theme.getTextSelectedColor());
			g.drawShadow(x+cx, y+h/2+fontSize/2, text.substring(minMark,maxMark),Color.WHITE);

			g.setColor(theme.getTextColor());
			g.drawShadow(x+cx+cxm, y+h/2+fontSize/2, text.substring(maxMark,text.length()),Color.WHITE);
		}

		if(onFocus) {
			
			g.setColor(theme.getTextFieldColor());
			//Draw Cursor

			int cx = metrics.stringWidth(text.substring(0,cursor));
			cx+=x+1;

			int padding = 3;
			
			if(dif>0) {
				g.drawLine(cx+1, y+padding, cx+1, y+h-padding);
			} else {
				g.drawLine(dif+cx, y+padding, dif+cx, y+h-padding);
			}
			
		}
		
		g.resetImage();
		layer.draw(g);
		layer.resetImage();

	}
	
	@Override
	protected void notifyTextChanged() {
		super.notifyTextChanged();
	}

	@Override
	public void update(GUIEvent event) {

		switch (event) {

		case GAIN_FOCUS:

			onFocus = true;
			//System.out.println("TextField, gain focus");

			break;

		case LOST_FOCUS:

			onFocus = false;
			//System.out.println("TextField, lost focus");

			break;

		case MOUSE_OVER_WITH_FOCUS:
		case MOUSE_OVER:

			mouseOver = true;

			break;

		case MOUSE_OUT:
			mouseOver = false;

			break;

		default:
			break;
		}
	}

	private void updateChar(char c) {

		if(TEXT_BACKSPACE == (int)c) {
			
			eraseAsBackSpace();
			
		}else if(TEXT_DELETE == (int)c) {
			
			eraseAsDelete();
			
		}else if(TEXT_ENTER == (int)c || 
				TEXT_TAB == (int)c || 
				TEXT_ESC == (int)c) {

		}
		else{
			if(maxLength>0) {
				if(text.length()<maxLength) {
					addChar(c);
				}
			}else{
				
				addChar(c);				
			}
		}

	}
	
	public String getText() {
		//Remove Tabs
		text = text.replace("\n", "").replace("\r", "");
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}