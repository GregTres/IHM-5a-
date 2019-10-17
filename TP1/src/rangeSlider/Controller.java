package rangeSlider;


import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

class Controller extends MouseAdapter{
	
	Rectangle cursor;
	Rectangle otherCurs;
	Model model;
	
	public Controller(Model model) {
		this.model = model;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		View view = (View) e.getSource();
		int xCurrent = e.getX();
		if(xCurrent>Model.XMIN && xCurrent<Model.XMIN+Model.W) {
			cursor = this.cursorChoice(view.cursorLeft, view.cursorRight, xCurrent);
			otherCurs = this.otherCursor(view, cursor);
			view.translate(cursor, xCurrent-cursor.x - cursor.width/2);
			cursor.x = xCurrent - cursor.width/2;
			if(cursor == view.cursorLeft)
				model.firstValue = (int)Math.round((view.cursorLeft.getCenterX()-11)*view.scale/Model.W);
			else model.secondValue = (int)Math.round((view.cursorRight.getCenterX()-11)*view.scale/Model.W);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		View view = (View) e.getSource();
		int xCurrent = e.getX();
		//gère le fait que le curseur gauche (respectivement droit) reste à gauche (respectivement droite)
		//gère le dépassement à gauche
		//gère le dépassemnt à droite
		//gere la collision des deux curseurs
		if(((cursor == view.cursorLeft && xCurrent <= otherCurs.getCenterX()) || (cursor == view.cursorRight && xCurrent >= otherCurs.getCenterX())) && xCurrent>Model.XMIN && xCurrent<Model.XMIN+Model.W && xCurrent != otherCurs.getCenterX()) {
			view.translate(cursor, xCurrent-cursor.x - cursor.width/2);
			cursor.x = xCurrent - cursor.width/2;
		}
		if(cursor == view.cursorLeft)
			model.firstValue = (int)Math.round((view.cursorLeft.getCenterX()-11)*view.scale/Model.W);
		else model.secondValue = (int)Math.round((view.cursorRight.getCenterX()-11)*view.scale/Model.W);
	}
	
	private Rectangle cursorChoice(Rectangle left, Rectangle right, int xCurrent) {
		if(Math.abs(xCurrent - left.getCenterX()) < Math.abs(xCurrent - right.getCenterX())) {
			return left;
		}
		else return right;
	}
	
	private Rectangle otherCursor(View view, Rectangle curs) {
		if(curs==view.cursorLeft)
			return view.cursorRight;
		else return view.cursorLeft;
	}

}