package ui;

import java.awt.event.MouseAdapter;

import javafx.event.EventHandler;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

class Interaction extends MouseAdapter
{
	enum State {IDLE, PRESSED, DRAGGED};
	State state = State.IDLE;
	Point2D p;
  public void handle(MouseEvent e)
  {
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
}