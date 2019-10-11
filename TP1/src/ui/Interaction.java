package ui;


import javafx.event.EventHandler;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

class Interaction implements MouseListener, MouseMotionListener
{
	enum State {IDLE, PRESSED, DRAGGED};
	State state = State.IDLE;
	Point2D p;
  public void handle(MouseEvent e)
  {
	  RangeSliderUI rs = (RangeSliderUI) e.getSource();
	  switch(state) {
		case IDLE:
			switch (e.getButton()) {
			case MouseEvent.BUTTON1:
				p = new Point2D.Double(e.getX(), e.getY());
				state = State.PRESSED;				
				break;
			default:
				
				break;
			}
		break;
		
		case PRESSED:
			switch (e.getID()) {
			
			
			case MouseEvent.MOUSE_DRAGGED:
				p = new Point2D.Double(e.getX(),e.getY());
				state = State.DRAGGED;
				break;
			case MouseEvent.MOUSE_RELEASED:
				state = State.IDLE;
				break;
			default:
				break;
			}
		break;
		
		case DRAGGED:
			switch (e.getID()) {
			case MouseEvent.MOUSE_DRAGGED:
				p = new Point2D.Double(e.getX(),e.getY());
				state = State.DRAGGED;
				break;
			case MouseEvent.MOUSE_RELEASED:
				state = State.IDLE;
				break;
			default:
				break;
			}
		break;
		}
  	}
@Override
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseDragged(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseMoved(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
}