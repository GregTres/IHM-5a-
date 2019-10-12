package rangeSlider;


import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

class Controller extends MouseAdapter{
	
	Rectangle cursor;
	Rectangle otherCurs;
	@Override
	public void mousePressed(MouseEvent e) {
		RangeSliderUI ui = (RangeSliderUI) e.getSource();
		int xCurrent = e.getX();
		if(xCurrent>ui.XMIN && xCurrent<ui.XMIN+ui.W) {
			cursor = this.cursorChoice(ui.cursorLeft, ui.cursorRight, xCurrent);
			otherCurs = this.otherCursor(ui, cursor);
			ui.translate(cursor, xCurrent-cursor.x - cursor.width/2);
			cursor.x = xCurrent - cursor.width/2;
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		RangeSliderUI ui = (RangeSliderUI) e.getSource();
		int xCurrent = e.getX();
		//gère le fait que le curseur gauche (respectivement droit) reste à gauche (respectivement droite)
		//gère le dépassement à gauche
		//gère le dépassemnt à droite
		//gere la collision des deux curseurs
		if(((cursor == ui.cursorLeft && xCurrent <= otherCurs.getCenterX()) || (cursor == ui.cursorRight && xCurrent >= otherCurs.getCenterX())) && xCurrent>ui.XMIN && xCurrent<ui.XMIN+ui.W && xCurrent != otherCurs.getCenterX()) {
			ui.translate(cursor, xCurrent-cursor.x - cursor.width/2);
			cursor.x = xCurrent - cursor.width/2;
		}
	}
	
	private Rectangle cursorChoice(Rectangle left, Rectangle right, int xCurrent) {
		if(Math.abs(xCurrent - left.getCenterX()) < Math.abs(xCurrent - right.getCenterX())) {
			return left;
		}
		else return right;
	}
	
	private Rectangle otherCursor(RangeSliderUI ui, Rectangle curs) {
		if(curs==ui.cursorLeft)
			return ui.cursorRight;
		else return ui.cursorLeft;
	}

}