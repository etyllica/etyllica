package br.com.etyllica.gui;

import java.awt.Color;
import java.awt.FontMetrics;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.Tecla;
import br.com.etyllica.core.input.mouse.MouseButton;
import br.com.etyllica.core.theme.Theme;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.gui.textfield.TextFieldValidator;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class TextField extends GUIComponent{

	private final int TEXT_BACKSPACE = 8;
	private final int TEXT_TAB = 9;
	private final int TEXT_ENTER = 10;
	private final int TEXT_ESC = 27;
	private final int TEXT_DELETE = 127;

	protected String text = "";

	private int cursor = 0;
	private int fixMark = -1;

	private int minMark = 0;
	private int maxMark = 0;

	private int maxLength = 0;

	private TextFieldValidator validator;

	public TextField(int x, int y, int w, int h){
		super(x,y,w,h);

		//TODO Altura H relativa ao theme
		//Theme theme = Configuration.getInstance().getTheme().getFontSize();
		
		clearField();
	}
	
	public void clearField(){
		cursor = 0;
		fixMark = -1;

		minMark = 0;
		maxMark = 0;
		
		text = "";
	}

	@Override
	public GUIEvent updateKeyboard(KeyboardEvent event){
		
		//if(onFocus){
			
			//Update component with typed events
			if(event.getChar()!='\0'){
				updateChar(event.getChar());
			}
			
			
			//TODO Next Component
			//Update component with Pressed Events
			GUIEvent pressedEvent = updatePressed(event);
			
			//Update component with Released Events
			GUIEvent releasedEvent = updateReleased(event);
			

			minMark = getMinMark();
			maxMark = getMaxMark();
			
			if(pressedEvent!=GUIEvent.NONE){
				return pressedEvent;		
			}
			
			if(releasedEvent!=GUIEvent.NONE){
				return releasedEvent;		
			}
			
		//}

		return GUIEvent.NONE;
	}

	public GUIEvent updateMouse(PointerEvent event){

		if((event.getPressed(MouseButton.MOUSE_BUTTON_LEFT))){

			if(mouseOver){

				//TODO posiciona cursor onMouse

				return GUIEvent.GAIN_FOCUS;

			}else{

				return GUIEvent.LOST_FOCUS;
			}

		}else if(mouseOver){

			if(!onFocus){
				
				return GUIEvent.MOUSE_OVER;
				
			}else{
				
				return GUIEvent.MOUSE_OVER_WITH_FOCUS;
			}

		//TODO Melhorar
		}else if(!mouseOver){
			return GUIEvent.MOUSE_OUT;
		}

		return GUIEvent.NONE;
	}

	private boolean shift = false;
	private boolean control = false;

	private GUIEvent updatePressed(KeyboardEvent event){

		if(!shift){

			if((event.getPressed(Tecla.TSK_SHIFT_DIREITA))||(event.getPressed(Tecla.TSK_SHIFT_ESQUERDA))){
				shift = true;
				fixMark = cursor;
			}
			else if(event.getPressed(Tecla.TSK_SETA_ESQUERDA)||(event.getPressed(Tecla.TSK_SETA_DIREITA))){
				fixMark = -1;
			}
		}

		if(event.getPressed(Tecla.TSK_END)){
			cursor = text.length();
		}
		else if(event.getPressed(Tecla.TSK_HOME)){
			cursor = 0;
		}

		if(!control){
			if(event.getPressed(Tecla.TSK_CTRL_DIREITA)||event.getPressed(Tecla.TSK_CTRL_ESQUERDA)){
				control = true;
			}
		}

		if(event.getPressed(Tecla.TSK_SETA_ESQUERDA)){
			if(control){
				esquerdaControl();
			}
			else{
				esquerdaNormal();
			}
		}
		else if(event.getPressed(Tecla.TSK_SETA_DIREITA)){

			if(control){
				direitaControl();
			}else{
				direitaNormal();
			}
		}

		if(event.getPressed(Tecla.TSK_TAB)){

			return GUIEvent.NEXT_COMPONENT;
		}

		return GUIEvent.NONE;
	}

	private GUIEvent updateReleased(KeyboardEvent event){
		if(control){
			if(event.getPressed(Tecla.TSK_CTRL_DIREITA)||event.getPressed(Tecla.TSK_CTRL_ESQUERDA)){
				control = false;
			}
		}

		if(shift){
			if(event.getPressed(Tecla.TSK_SHIFT_DIREITA)||event.getPressed(Tecla.TSK_SHIFT_ESQUERDA)){
				shift = false;
			}
		}
		
		return GUIEvent.NONE;				
	}

	//TODO escreve texto.sub(0,minMark);
	//Para não sair da caixa
	public void draw(Grafico g){

		Theme theme = Configuration.getInstance().getTheme();

		//TODO
		//g.setFont(theme.getFont());

		//Para poder ser usado pelo password
		String text = this.getText();

		int fontSize = theme.getFontSize();

		FontMetrics metrics = g.getGraphics().getFontMetrics();

		int dif = w-metrics.stringWidth(text);

		//Remover
		if(onFocus){
			g.setColor(theme.getTextFieldColor());
		}else{
			g.setColor(theme.getTextFieldWithoutFocusColor());
		}

		if(mouseOver){
			g.setColor(theme.getTextFieldOnMouseColor());	
		}

		g.drawRect(x,y,w,h);

		g.setColor(theme.getTextColor());

		if(minMark==0&&maxMark==0){

			if(dif>0){
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


		if(onFocus){
			//Desenha Cursor

			int cx = metrics.stringWidth(text.substring(0,cursor));
			cx+=x+1;

			if(dif>0){
				g.drawLine(cx+1, y+2, cx+1, y+h-2);
			}else{
				g.drawLine(dif+cx, y+2, dif+cx, y+h-2);
			}
			
		}

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

	public String getText() {
		//Remove Tabs
		text = text.replace("\n", "").replace("\r", "");
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	//Text Methods
	private void esquerdaNormal(){

		if(cursor>0){
			cursor--;
		}

	}

	private void esquerdaControl(){

		if(cursor>0){
			
			int i=cursor-2;

			for(;i>0;i--){
				if(text.charAt(i)==' '){
					i++;
					break;
				}
			}

			cursor = i;
		}
	}

	private void direitaControl(){

		int i=cursor+1;

		for(;i<text.length();i++){
			if(text.charAt(i)==' '){
				break;
			}
		}
		cursor = i;
	}

	private void direitaNormal(){
		if(cursor<text.length()){
			cursor++;
		}
	}

	private void apagaBackSpace(){

		if(fixMark==-1&&cursor>0){

			if(cursor<text.length()){
				String t1 = text.substring(0,cursor-1);
				String t2 = text.substring(cursor,text.length());

				text = t1+t2;
			}
			else if(cursor>0){
				text = text.substring(0,cursor-1);
			}

			esquerdaNormal();

		}else{

			deleteMark();
		}

	}

	private void apagaDelete(){

		if(fixMark==-1){

			if(cursor<text.length()){
				String t1 = text.substring(0,cursor);
				String t2 = text.substring(cursor+1,text.length());

				text = t1+t2;
			}

		}else{

			deleteMark();

		}
	}

	private void deleteMark(){
		
		//System.out.println("deleteMark "+text.length());

		String t1 = text.substring(0,getMinMark());

		String t2 = text.substring(getMaxMark(),text.length());

		text = t1+t2;

		cursor = getMinMark();
		fixMark = -1;
	}

	private void updateChar(char c){

		if((int)c==TEXT_BACKSPACE){
			
			apagaBackSpace();
			
		}else if((int)c==TEXT_DELETE){
			
			apagaDelete();
			
		}else if((int)c==TEXT_ENTER||(int)c==TEXT_TAB||(int)c==TEXT_ESC){

		}
		else{
			if(maxLength>0){
				if(text.length()<maxLength){
					addChar(c);
				}
			}else{
				
				addChar(c);
				
			}
		}

	}

	private void addChar(char c){

		if(cursor<text.length()){
			String t1 = text.substring(0,cursor);
			t1+=c;
			String t2 = text.substring(cursor,text.length());

			text = t1+t2;
		}
		else{
			text+=c;
		}

		fixMark = -1;
		cursor++;
	}

	private int getMinMark(){

		if(fixMark<0){
			return 0;
		}

		if(cursor<fixMark){
			return cursor;
		}
		else{
			return fixMark;
		}

	}

	private int getMaxMark(){

		if(fixMark<0){
			return 0;
		}

		if(cursor<fixMark){

			return fixMark;
		}
		else{
			return cursor;
		}
	}

}