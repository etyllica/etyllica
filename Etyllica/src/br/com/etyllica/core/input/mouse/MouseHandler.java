package br.com.etyllica.core.input.mouse;

import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.MouseInputListener;

import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.PointerState;
import br.com.etyllica.gui.mouse.arrow.MouseArrow;
import br.com.etyllica.gui.mouse.theme.ArrowTheme;
import br.com.etyllica.layer.AnimatedLayer;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.linear.Poligono;
import br.com.etyllica.theme.ThemeManager;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class MouseHandler implements MouseMotionListener,MouseInputListener, MouseWheelListener{

	private List<PointerEvent> events = new ArrayList<PointerEvent>();
	
	protected int x = 0;
	protected int y = 0;
	protected int z = 0;

	//TimeClick
	private int arc = 0;

	private boolean overText = false;

	protected ArrowTheme arrowTheme;

	protected MouseArrow arrow;

	public MouseHandler(int x, int y) {
		super();
		
		this.x = x;
		this.y = y;
		
		moveEvent = new PointerEvent(MouseButton.MOUSE_NONE, PointerState.MOVE, x, y);
	}
	
	public void updateArrowTheme(){
		arrowTheme = ThemeManager.getInstance().getArrowTheme();
		setEstadoNormal();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public void setCoordenadas(int x, int y){
		setX(x);
		setY(y);
	}

	public void click(int botao) {
		setEstadoClicado();		
	}

	public void pressiona(int botao) {
		setEstadoClicado();
	}

	public void solta(int botao) {
		setEstadoNormal();
	}

	protected int clicks = 0;
	//TODO doubleClick[]
	protected boolean doubleClick = false;

	protected boolean dragged = false;
	protected int dragButton = 0;
	protected int dragX = 0;
	protected int dragY = 0;

	private void addEvent(int button, PointerState state){

		addEvent(button, state, x, y );

	}

	private void addEvent(int button, PointerState state, int x, int y){

		MouseButton key = MouseButton.MOUSE_NONE;

		switch (button) {
		case MouseEvent.BUTTON1:
			key = MouseButton.MOUSE_BUTTON_LEFT;
			break;
		case MouseEvent.BUTTON2:
			key = MouseButton.MOUSE_BUTTON_MIDDLE;
			break;
		case MouseEvent.BUTTON3:
			key = MouseButton.MOUSE_BUTTON_RIGHT;
			break;
		}

		events.add(new PointerEvent(key, state, x, y ));

	}

	@Override
	public void mouseClicked( MouseEvent me ) {

		PointerState state = PointerState.CLICK;

		if (me.getClickCount() == 2) {
			state = PointerState.DOUBLE_CLICK;
		}

		addEvent(me.getButton(),state);

		setCoordenadas(me.getX(),me.getY());
		
		me.consume();
	}

	@Override
	public void mousePressed( MouseEvent me ) {

		addEvent(me.getButton(),PointerState.PRESSED);

		setCoordenadas(me.getX(),me.getY());

		me.consume();
	}

	@Override
	public void mouseReleased( MouseEvent me ) {

		addEvent(me.getButton(),PointerState.RELEASED);

		dragged = false;

		me.consume();
	}

	@Override
	public void mouseMoved( MouseEvent me ) {

		int x = me.getX();
		int y = me.getY();
		
		addMouseMoveEvent(x, y);

		setCoordenadas(me.getX(),me.getY());

		me.consume();
	}

	@Override
	public void mouseEntered( MouseEvent me ) {
		//mouseMoved( me );
		me.consume();
	}

	@Override
	public void mouseExited( MouseEvent me ) {
		me.consume();
	}

	@Override
	public void mouseDragged( MouseEvent me ) {
		
		if(!dragged){
			dragX = me.getX();
			dragY = me.getY();

			dragged = true;
		}
		
		setCoordenadas(me.getX(),me.getY());

		dragButton = me.getButton();
		
		//addEvent(dragButton, KeyState.DRAGGED, dragX-x, dragY-y);
		addEvent(dragButton, PointerState.DRAGGED, x, y);

		me.consume();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent mwe) {
		
		MouseButton key = MouseButton.MOUSE_WHEEL_DOWN;		
		
		if(mwe.getWheelRotation()<0){
			key = MouseButton.MOUSE_WHEEL_UP;
		}
		
		events.add(new PointerEvent(key, PointerState.PRESSED, x, y, mwe.getWheelRotation()));
	}

	//TODO Remover colisoes e incluir em layer

	//Colisao
	public boolean sobMouseCircular(ImageLayer b)
	{
		float raio = b.getW()/2;
		return sobMouseCircular(b.getX()+raio,b.getY()+raio, raio);
	}

	public boolean sobMouseCircular(float cx, float cy, float raio)
	{
		float dx = cx - x;
		float dy = cy - y;

		if ( ( dx * dx )  + ( dy * dy ) < raio * raio )	{
			return true;
		}

		return false;

	}

	public boolean sobMouseIso(ImageLayer cam){

		float my = cam.getH()/2;
		float mx = cam.getW()/2;

		float x = this.x-cam.getX();
		float y = this.y-cam.getY();

		if(y>+my)
			y=my-(y-my);

		if((x>mx+1+(2*y))||(x<mx-1-(2*y)))
			return false;
		else
			return true;

	}

	public boolean sobMouse(float x, float y, float w, float h)
	{
		if((this.x<x)||(this.x>x + w))
		{
			return false;
		}
		if((this.y<y)||(this.y>y + h))
		{
			return false;
		}

		return true;	
	}

	public boolean sobMouse(ImageLayer cam)
	{
		if(cam.getAngle()==0){
			float cx = cam.getX();
			float cy = cam.getY();

			return sobMouse(cx, cy, cam.getW(), cam.getH());

		}else{

			AffineTransform transformer = AffineTransform.getRotateInstance(cam.getAngle(),cam.getX(),cam.getY());

			Point2D a = new Point2D.Double(cam.getX(),cam.getY());
			Point2D b = new Point2D.Double(cam.getX()+cam.getW(),cam.getY());
			Point2D c = new Point2D.Double(cam.getX()+cam.getW(),cam.getY()+cam.getH());
			Point2D d = new Point2D.Double(cam.getX(),cam.getY()+cam.getH());

			transformer.transform(a,a);
			transformer.transform(b,b);
			transformer.transform(c,c);
			transformer.transform(d,d);

			Poligono p = new Poligono();
			p.addPoint((int)a.getX(),(int)a.getY());
			p.addPoint((int)b.getX(),(int)b.getY());
			p.addPoint((int)c.getX(),(int)c.getY());
			p.addPoint((int)d.getX(),(int)d.getY());

			return p.contains(x, y);

		}
	}

	public boolean sobMouse(AnimatedLayer cam)
	{
		return sobMouse(cam.getX(), cam.getY(), cam.getXTile(), cam.getYTile());
	}

	public boolean sobMouse(Polygon poligono){
		return poligono.contains(x, y);
	}

	public void setEstadoClicado(){
		arrow = arrowTheme.getClickArrow();
	}
	public void setEstadoCarregando(){
		arrow = arrowTheme.getLoadingArrow();
	}
	public void setEstadoNormal(){
		arrow = arrowTheme.getNormalArrow();
	}
	public void setEstadoTexto(){
		arrow = arrowTheme.getTextArrow();
	}

	public void resetCursor(){
		arrow = arrowTheme.getNormalArrow();
	}

	public int getDragX(){
		return dragX;
	}
	public int getDragY(){
		return dragY;
	}

	public void setArc(int arc){
		this.arc = arc;
	}

	public int getArc(){
		return arc;
	}

	public void setTextMode(boolean textMode){

		this.overText = textMode;

		if(textMode){
			setEstadoTexto();
		}
		else{
			setEstadoNormal();
		}
	}

	public boolean isTextMode() {
		return overText;
	}

	public MouseArrow getArrow() {
		return arrow;
	}
	
	public List<PointerEvent> getEvents(){
		
		events.add(moveEvent);
		
		return events;
	}
	
	private PointerEvent moveEvent;
	
	public void addMouseMoveEvent(int x, int y){

		moveEvent.setX(x);
		moveEvent.setY(y);
		
	}
	
	public void clearEvents(){
		events.clear();
	}
	
	public void addEvent(PointerEvent event){
		events.add(event);
	}

}
