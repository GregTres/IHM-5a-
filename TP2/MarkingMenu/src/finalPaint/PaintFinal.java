package finalPaint;

import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

import paint.ColorShape;

public class PaintFinal extends JFrame {
	Vector<ColorShape> colorshapes = new Vector<ColorShape>();
	Color c = Color.BLACK;
	List<String> listMarking = new ArrayList<String>();
	boolean Rightclick = false;
	Model model;
	Controller controller;
	private Font fonte = new Font("Roboto",Font.BOLD,15);
	
	
	class Tool extends AbstractAction implements MouseInputListener {
		Point o;
		Shape shape;
		String name;

		public Tool(String name) {
			super(name);
			this.name = name;
		}

		public void actionPerformed(ActionEvent e) {
			System.out.println("using tool " + this);
			panel.removeMouseListener(tool);
			panel.removeMouseMotionListener(tool);
			switch (this.name) {
			case "Red":
				c = Color.RED;
			    break;
			case "Black":
				c = Color.BLACK;
			    break;
			case "Blue":
				c = Color.BLUE;
			    break;
			case "Green":
				c = Color.GREEN;
			    break;
			  default:
			    // code block
			}
			tool = this;
			panel.addMouseListener(tool);
			panel.addMouseMotionListener(tool);
		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				Rightclick = false;
				o = e.getPoint();
			}
				
			if(e.getButton() == MouseEvent.BUTTON3) {
				Rightclick = true;
				controller.pressed(e);
				repaint();
			}
		}

		public void mouseReleased(MouseEvent e) {
			shape = null;
			if(Rightclick == true) {
				controller.released(e);
				repaint();
			}
		}

		public void mouseDragged(MouseEvent e) {
			if(Rightclick == true) {
				controller.dragged(e);
				repaint();
			}
		}

		public void mouseMoved(MouseEvent e) {
		}
	}

	Tool tools[] = { new Tool("pen") {
		public void mouseDragged(MouseEvent e) {
			if(Rightclick == false) {
				Path2D.Double path = (Path2D.Double) shape;
				if (path == null) {
					path = new Path2D.Double();
					path.moveTo(o.getX(), o.getY());
					colorshapes.add(new ColorShape(c,shape = path));
				}
				path.lineTo(e.getX(), e.getY());
				panel.repaint();
			}
			if(Rightclick == true) {
				controller.dragged(e);
				repaint();
			}
		}
	}, new Tool("rect") {
		public void mouseDragged(MouseEvent e) {
			if(Rightclick == false) {
				Rectangle2D.Double rect = (Rectangle2D.Double) shape;
				if (rect == null) {
					rect = new Rectangle2D.Double(o.getX(), o.getY(), 0, 0);
					colorshapes.add(new ColorShape(c,shape = rect));
				}
				rect.setRect(min(e.getX(), o.getX()), min(e.getY(), o.getY()), abs(e.getX() - o.getX()),
						abs(e.getY() - o.getY()));
				panel.repaint();
			}
			if(Rightclick == true) {
				controller.dragged(e);
				repaint();
			}
		}
	} , new Tool("ellipse") {
		public void mouseDragged(MouseEvent e) { 
			if(Rightclick == false) {
				Ellipse2D.Double ellispe = (Ellipse2D.Double) shape;
				if (ellispe == null) {
					ellispe = new Ellipse2D.Double(o.getX(), o.getY(), 0, 0);
					colorshapes.add(new ColorShape(c,shape = ellispe));
				}
				ellispe.setFrame(min(e.getX(), o.getX()), min(e.getY(), o.getY()), abs(e.getX() - o.getX()),
						abs(e.getY() - o.getY()));
				panel.repaint();
			}
			if(Rightclick == true) {
				controller.dragged(e);
				repaint();
			}
		}
	} , new Tool("Red") {
	} , new Tool("Black") {
	} , new Tool("Blue") {
	} , new Tool("Green") {
	}};
	Tool tool;

	JPanel panel;

	public PaintFinal(String title) {
		super(title);
		this.model = new Model();
		this.controller = new Controller(model);
		listMarking.add("Outils");
		listMarking.add("Couleur");
		listMarking.add("Annuler");
		model.listSousMenu = listMarking;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(800, 600));
		add(new JToolBar() {
			{
				for (AbstractAction tool : tools) {
					add(tool);
				}
			}
		}, BorderLayout.NORTH);
		add(panel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				g2.setColor(Color.WHITE);
				g2.fillRect(0, 0, getWidth(), getHeight());

				for (ColorShape colorshape : colorshapes) {
					g2.setColor(colorshape.color);

					g2.draw(colorshape.shape);
				}
				if(Rightclick == true) {
					g.clearRect(0, 0, this.getWidth(), this.getHeight());
					g.setColor(Color.WHITE);
					g.fillRect(0, 0, this.getWidth(), this.getHeight());
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
				if(listMenu.size()>0) {
					g.drawString((String) listMenu.get(0), model.positionX-Model.RECTWIDTH/2+20, model.positionY-Model.RECTHEIGHT/2-60+15);
					if(model.newX > model.positionX-Model.RECTWIDTH/2 && model.newX < model.positionX-Model.RECTWIDTH/2 + Model.RECTWIDTH && model.newY > model.positionY-Model.RECTHEIGHT/2-60 && model.newY < model.positionY-Model.RECTHEIGHT/2-60 + Model.RECTHEIGHT) {
						if((String) listMenu.get(0) == "Outils") {
							model.positionX = model.newX;
							model.positionY = model.newY;
							model.choixMenu = (String) listMenu.get(0);
							model.listSousMenu.clear();
							model.listSousMenu.add("Rect");
							model.listSousMenu.add("Line");
							model.listSousMenu.add("Ellipse");
						 }
						else if((String) listMenu.get(0) == "Rect") {
							System.out.println("rect");
							model.choixMenu = (String) listMenu.get(0);
						 }
						else if((String) listMenu.get(0) == "Red") {
							System.out.println("Red");
							model.choixMenu = (String) listMenu.get(0);
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
							model.choixMenu = (String) listMenu.get(1);
							model.listSousMenu.clear();
							model.listSousMenu.add("Red");
							model.listSousMenu.add("Green");
							model.listSousMenu.add("Blue");
						 }
						else if((String) listMenu.get(1) == "Line") {
							System.out.println("Line");
							model.choixMenu = (String) listMenu.get(1);
						 }
						else if((String) listMenu.get(1) == "Green") {
							System.out.println("Green");
							model.choixMenu = (String) listMenu.get(1);
						 }
						repaint();
					}
				}
				if(listMenu.size()>2) {
					g.drawString((String) listMenu.get(2), model.positionX+Model.RECTWIDTH/2+20, model.positionY-Model.RECTHEIGHT/2+15);
					if(model.newX > model.positionX+Model.RECTWIDTH/2 && model.newX < model.positionX+Model.RECTWIDTH/2 + Model.RECTWIDTH && model.newY > model.positionY-Model.RECTHEIGHT/2 && model.newY < model.positionY-Model.RECTHEIGHT/2+ Model.RECTHEIGHT) {
						if((String) listMenu.get(2) == "Ellipse") {
							System.out.println("Ellipse");
							model.choixMenu = (String) listMenu.get(2);
						 }
						else if((String) listMenu.get(2) == "Blue") {
							System.out.println("Blue");
							model.choixMenu = (String) listMenu.get(2);
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
		});

		pack();
		setVisible(true);
	}

	/* main *********************************************************************/

	public static void main(String argv[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				PaintFinal paint = new PaintFinal("paint");
			}
		});
	}
}
