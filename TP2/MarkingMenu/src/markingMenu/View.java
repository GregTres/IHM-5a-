package markingMenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class View extends JPanel {
	List<String> listSousMenu = new ArrayList<String>();
	Model model;
	Controller controller;
	private Font fonte = new Font("Roboto",Font.BOLD,15);
	
	public View(List<String> sousMenu) {
		this.listSousMenu = sousMenu;
		this.model = new Model();
		this.controller = new Controller(model);
		this.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				controller.dragged(e);
				repaint();
			 }
		});
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				 controller.pressed(e);
				 repaint();
			 }
			@Override
			public void mouseReleased(MouseEvent e) {
				 controller.released(e);
				 repaint();
			 }
		});
	}
	

	public void paint(Graphics g) {
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		if(model.positionX >= 0) {
			//rond central
			g.setColor(Color.gray);
			g.fillOval(model.positionX-Model.MENUSIZE/2, model.positionY-Model.MENUSIZE/2, Model.MENUSIZE, Model.MENUSIZE);
			g.setColor(Color.BLACK);
			g.drawOval(model.positionX-Model.MENUSIZE/2, model.positionY-Model.MENUSIZE/2, Model.MENUSIZE, Model.MENUSIZE);
			//8 carrés
			drawRectangles(g, this.listSousMenu);
		}
		if(model.newX >= 0) {
			g.setColor(Color.BLUE);
			g.drawLine(model.positionX, model.positionY, model.newX, model.newY);
		}
	}
	
	public void drawRectangles(Graphics g, List listMenu) {
		g.setFont(fonte);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRoundRect(model.positionX-Model.RECTWIDTH/2, model.positionY-Model.RECTHEIGHT/2-60, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.fillRoundRect(model.positionX+Model.RECTWIDTH/4, model.positionY-Model.RECTHEIGHT/2-30, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.fillRoundRect(model.positionX+Model.RECTWIDTH/2, model.positionY-Model.RECTHEIGHT/2, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.fillRoundRect(model.positionX+Model.RECTWIDTH/4, model.positionY-Model.RECTHEIGHT/2+30, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.fillRoundRect(model.positionX-Model.RECTWIDTH/2, model.positionY-Model.RECTHEIGHT/2+60, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.fillRoundRect(model.positionX-Model.RECTWIDTH/4-Model.RECTWIDTH, model.positionY-Model.RECTHEIGHT/2+30, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.fillRoundRect(model.positionX-Model.RECTWIDTH/2-Model.RECTWIDTH, model.positionY-Model.RECTHEIGHT/2, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.fillRoundRect(model.positionX-Model.RECTWIDTH/4-Model.RECTWIDTH, model.positionY-Model.RECTHEIGHT/2-30, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.setColor(Color.BLACK);
		g.drawRoundRect(model.positionX-Model.RECTWIDTH/2, model.positionY-Model.RECTHEIGHT/2-60, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.drawRoundRect(model.positionX+Model.RECTWIDTH/4, model.positionY-Model.RECTHEIGHT/2-30, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.drawRoundRect(model.positionX+Model.RECTWIDTH/2, model.positionY-Model.RECTHEIGHT/2, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.drawRoundRect(model.positionX+Model.RECTWIDTH/4, model.positionY-Model.RECTHEIGHT/2+30, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.drawRoundRect(model.positionX-Model.RECTWIDTH/2, model.positionY-Model.RECTHEIGHT/2+60, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.drawRoundRect(model.positionX-Model.RECTWIDTH/4-Model.RECTWIDTH, model.positionY-Model.RECTHEIGHT/2+30, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.drawRoundRect(model.positionX-Model.RECTWIDTH/2-Model.RECTWIDTH, model.positionY-Model.RECTHEIGHT/2, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.drawRoundRect(model.positionX-Model.RECTWIDTH/4-Model.RECTWIDTH, model.positionY-Model.RECTHEIGHT/2-30, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.setColor(Color.BLACK);
		if(listMenu.size()>0)
			g.drawString((String) listMenu.get(0), model.positionX-Model.RECTWIDTH/2+20, model.positionY-Model.RECTHEIGHT/2-60+15);
		if(listMenu.size()>1)
			g.drawString((String) listMenu.get(1), model.positionX+Model.RECTWIDTH/4+20, model.positionY-Model.RECTHEIGHT/2-30+15);
		if(listMenu.size()>2)
			g.drawString((String) listMenu.get(2), model.positionX+Model.RECTWIDTH/2+20, model.positionY-Model.RECTHEIGHT/2+15);
		if(listMenu.size()>3)
			g.drawString((String) listMenu.get(3), model.positionX+Model.RECTWIDTH/4+20, model.positionY-Model.RECTHEIGHT/2-30+15);
		if(listMenu.size()>4)
			g.drawString((String) listMenu.get(4), model.positionX-Model.RECTWIDTH/2+20, model.positionY-Model.RECTHEIGHT/2+60+15);
		if(listMenu.size()>5)
			g.drawString((String) listMenu.get(5), model.positionX-Model.RECTWIDTH/4-Model.RECTWIDTH+20, model.positionY-Model.RECTHEIGHT/2+30+15);
		if(listMenu.size()>6)
			g.drawString((String) listMenu.get(6), model.positionX-Model.RECTWIDTH/2-Model.RECTWIDTH+20, model.positionY-Model.RECTHEIGHT/2+15);
		if(listMenu.size()>7)
			g.drawString((String) listMenu.get(7), model.positionX-Model.RECTWIDTH/4-Model.RECTWIDTH+20, model.positionY-Model.RECTHEIGHT/2-30+15);
	}
}
