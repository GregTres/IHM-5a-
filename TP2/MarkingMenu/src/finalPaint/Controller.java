package finalPaint;

import java.awt.event.MouseEvent;

public class Controller {
	Model model;
	int xPressed, yPressed;
	public Controller(Model model) {
		this.model = model;
	}
	public void pressed(MouseEvent e) {
		model.positionX = e.getX();
		model.positionY = e.getY();
	}
	public void released(MouseEvent e) {
		model.positionX = -1000;
		model.positionY = -1000;
		model.newX = -1000;
		model.newY = -1000;
		model.listSousMenu.clear();
		model.listSousMenu.add("Outils");
		model.listSousMenu.add("Couleur");
	}
	public void dragged(MouseEvent e) {
		model.newX = e.getX();
		model.newY = e.getY();
	}
}
