package markingMenu;

import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import paint.ColorShape;

public class View extends JPanel {
	public Model model;
	Controller controller;
	private Font fonte = new Font("Roboto",Font.BOLD,15);
	
	public View(List<String> sousMenu) {
		this.model = new Model();
		this.controller = new Controller(model);
		this.setOpaque(false);
		model.listSousMenu = sousMenu;
		this.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
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
		});
	}
	

	public void paint(Graphics g) {
		if(model.positionX >= 0) {
			//rond central
			g.setColor(Color.gray);
			g.fillOval(model.positionX-Model.MENUSIZE/2, model.positionY-Model.MENUSIZE/2, Model.MENUSIZE, Model.MENUSIZE);
			g.setColor(Color.BLACK);
			g.drawOval(model.positionX-Model.MENUSIZE/2, model.positionY-Model.MENUSIZE/2, Model.MENUSIZE, Model.MENUSIZE);
			//8 carrés
			drawRectangles(g, model.listSousMenu);
		}
		if(model.newX >= 0) {
			g.setColor(Color.BLUE);
			g.drawLine(model.positionX, model.positionY, model.newX, model.newY);
		}
	}
	
	public void drawRectangles(Graphics g, List listMenu) {
		g.setFont(fonte);
		g.setColor(new Color(160,160,160));
		g.fillRoundRect(model.positionX-Model.RECTWIDTH/2, model.positionY-Model.RECTHEIGHT/2-60, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.fillRoundRect(model.positionX+Model.RECTWIDTH/4, model.positionY-Model.RECTHEIGHT/2-30, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.fillRoundRect(model.positionX+Model.RECTWIDTH/2, model.positionY-Model.RECTHEIGHT/2, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.fillRoundRect(model.positionX+Model.RECTWIDTH/4, model.positionY-Model.RECTHEIGHT/2+30, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.fillRoundRect(model.positionX-Model.RECTWIDTH/2, model.positionY-Model.RECTHEIGHT/2+60, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.fillRoundRect(model.positionX-Model.RECTWIDTH/4-Model.RECTWIDTH, model.positionY-Model.RECTHEIGHT/2+30, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.fillRoundRect(model.positionX-Model.RECTWIDTH/2-Model.RECTWIDTH, model.positionY-Model.RECTHEIGHT/2, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.fillRoundRect(model.positionX-Model.RECTWIDTH/4-Model.RECTWIDTH, model.positionY-Model.RECTHEIGHT/2-30, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.setColor(new Color(120,120,120));
		g.drawRoundRect(model.positionX-Model.RECTWIDTH/2, model.positionY-Model.RECTHEIGHT/2-60, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.drawRoundRect(model.positionX+Model.RECTWIDTH/4, model.positionY-Model.RECTHEIGHT/2-30, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.drawRoundRect(model.positionX+Model.RECTWIDTH/2, model.positionY-Model.RECTHEIGHT/2, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.drawRoundRect(model.positionX+Model.RECTWIDTH/4, model.positionY-Model.RECTHEIGHT/2+30, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.drawRoundRect(model.positionX-Model.RECTWIDTH/2, model.positionY-Model.RECTHEIGHT/2+60, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.drawRoundRect(model.positionX-Model.RECTWIDTH/4-Model.RECTWIDTH, model.positionY-Model.RECTHEIGHT/2+30, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.drawRoundRect(model.positionX-Model.RECTWIDTH/2-Model.RECTWIDTH, model.positionY-Model.RECTHEIGHT/2, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.drawRoundRect(model.positionX-Model.RECTWIDTH/4-Model.RECTWIDTH, model.positionY-Model.RECTHEIGHT/2-30, Model.RECTWIDTH, Model.RECTHEIGHT,5,5);
		g.setColor(Color.white);
		if(listMenu.size()>0) {
			g.drawString((String) listMenu.get(0), model.positionX-Model.RECTWIDTH/2+20, model.positionY-Model.RECTHEIGHT/2-60+15);
			if(model.newX > model.positionX-Model.RECTWIDTH/2 && model.newX < model.positionX-Model.RECTWIDTH/2 + Model.RECTWIDTH && model.newY > model.positionY-Model.RECTHEIGHT/2-60 && model.newY < model.positionY-Model.RECTHEIGHT/2-60 + Model.RECTHEIGHT) {
				if((String) listMenu.get(0) == "Outils") {
					model.positionX = model.newX;
					model.positionY = model.newY;
					model.choixMenuOutils = (String) listMenu.get(0);
					model.listSousMenu.clear();
					model.listSousMenu.add("Rect");
					model.listSousMenu.add("Pen");
					model.listSousMenu.add("Ellipse");
				 }
				else if((String) listMenu.get(0) == "Rect") {
					model.choixMenuOutils = (String) listMenu.get(0);
					controller.released();
					this.setVisible(false);
					model.isOpen=false;
				 }
				else if((String) listMenu.get(0) == "Red") {
					model.choixMenuCouleur = (String) listMenu.get(0);
					controller.released();
					this.setVisible(false);
					model.isOpen=false;
				 }
				repaint();
			}
		}
		if(listMenu.size()>1) {
			g.drawString((String) listMenu.get(1), model.positionX+Model.RECTWIDTH/4+20, model.positionY-Model.RECTHEIGHT/2-30+15);
			if(model.newX > model.positionX+Model.RECTWIDTH/4 && model.newX < model.positionX+Model.RECTWIDTH/4 + Model.RECTWIDTH && model.newY > model.positionY-Model.RECTHEIGHT/2-30 && model.newY < model.positionY-Model.RECTHEIGHT/2-30 + Model.RECTHEIGHT) {
				if((String) listMenu.get(1) == "Couleur") {
					model.positionX = model.newX;
					model.positionY = model.newY;
					model.choixMenuCouleur = (String) listMenu.get(1);
					model.listSousMenu.clear();
					model.listSousMenu.add("Red");
					model.listSousMenu.add("Green");
					model.listSousMenu.add("Blue");
				 }
				else if((String) listMenu.get(1) == "Pen") {
					model.choixMenuOutils = (String) listMenu.get(1);
					controller.released();
					this.setVisible(false);
					model.isOpen=false;
				 }
				else if((String) listMenu.get(1) == "Green") {
					model.choixMenuCouleur = (String) listMenu.get(1);
					controller.released();
					this.setVisible(false);
					model.isOpen=false;
				 }
				repaint();
			}
		}
		if(listMenu.size()>2) {
			g.drawString((String) listMenu.get(2), model.positionX+Model.RECTWIDTH/2+20, model.positionY-Model.RECTHEIGHT/2+15);
			if(model.newX > model.positionX+Model.RECTWIDTH/2 && model.newX < model.positionX+Model.RECTWIDTH/2 + Model.RECTWIDTH && model.newY > model.positionY-Model.RECTHEIGHT/2 && model.newY < model.positionY-Model.RECTHEIGHT/2+ Model.RECTHEIGHT) {
				if((String) listMenu.get(2) == "Ellipse") {
					model.choixMenuOutils = (String) listMenu.get(2);
					controller.released();
					this.setVisible(false);
					model.isOpen=false;
				 }
				else if((String) listMenu.get(2) == "Blue") {
					model.choixMenuCouleur = (String) listMenu.get(2);
					controller.released();
					this.setVisible(false);
					model.isOpen=false;
				 }
				repaint();
			}
		}
		if(listMenu.size()>3)
			g.drawString((String) listMenu.get(3), model.positionX+Model.RECTWIDTH/4+20, model.positionY-Model.RECTHEIGHT/2+30+15);
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
