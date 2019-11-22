package paint;

//////////////////////////////////////////////////////////////////////////////

//file    : Paint.java
//content : basic painting app
//////////////////////////////////////////////////////////////////////////////

/* imports *****************************************************************/

import static java.lang.Math.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.*;
import javax.swing.event.*;

import markingMenu.View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

/* paint *******************************************************************/

class Paint extends JFrame {
	Vector<ColorShape> colorshapes = new Vector<ColorShape>();
	Color c = Color.BLACK;
	List<String> listMarking = new ArrayList<String>();
	View view;
	Point o;
	Shape shape;
	Shape lastShape;
	String name;
	JPanel panel;

	public Paint(String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(800, 600));
		//création du marking menu et ajout dans la frame
		listMarking.add("Outils");
		listMarking.add("Couleur");
		listMarking.add("Annuler");
		this.add(this.view = new View(listMarking));
		this.setVisible(true);
		//cache le marking menu
		view.setVisible(false);
		//ajout des listeners sur le panel
		add(panel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				g2.setColor(Color.WHITE);
				g2.fillRect(0, 0, getWidth(), getHeight());

				for (ColorShape colorshape : colorshapes) {
					g2.setColor(colorshape.color);
					if(colorshape.fill) {
						g2.fill(colorshape.shape);
					}
					else
						g2.draw(colorshape.shape);
				}
			}
		});
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//clic gauche : recuperation du point puis dessin
				if(e.getButton() == MouseEvent.BUTTON1)
					o = e.getPoint();
				//clic droit : envoie du point courant au marking menu puis affichage de celui ci 
				if(e.getButton() == MouseEvent.BUTTON3) {
					view.model.positionX = e.getX();
					view.model.positionY = e.getY();
					view.setVisible(true);
					view.model.isOpen = true;
				}
			}
			public void mouseReleased(MouseEvent e) {
				lastShape = shape;
				shape = null;
			}
		});
		panel.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					//Récupération de la couleur qui se trouve dans le model du marking menu
					switch(view.model.choixMenuCouleur) {
					case "Blue" : {
						c = Color.blue;
					}
					break;
					case "Red" : {
						c = Color.red;
					}
					break;
					case "Green" : {
						c = Color.green;
					}
					break;
					case "Yellow" : {
						c = Color.yellow;
					}
					break;
					case "Grey" : {
						c = Color.gray;
					}
					break;
					case "Orange" : {
						c = Color.orange;
					}
					break;
					case "Cyan" : {
						c = Color.cyan;
					}
					break;
					case "Black" : {
						c = Color.black;
					}
					break;
					}
					
					//Récuperation de l'outil qui se trouve dans le model du marking menu
					switch(view.model.choixMenuOutils) {
					case "Ellipse" : {
						Ellipse2D.Double ellispe = (Ellipse2D.Double) shape;
						if (ellispe == null) {
							ellispe = new Ellipse2D.Double(o.getX(), o.getY(), 0, 0);
							colorshapes.add(new ColorShape(c,shape = ellispe, false));
						}
						ellispe.setFrame(min(e.getX(), o.getX()), min(e.getY(), o.getY()), abs(e.getX() - o.getX()),
								abs(e.getY() - o.getY()));
						panel.repaint();
					}
						break;
					case "Rect" : {
						Rectangle2D.Double rect = (Rectangle2D.Double) shape;
						if (rect == null) {
							rect = new Rectangle2D.Double(o.getX(), o.getY(), 0, 0);
							colorshapes.add(new ColorShape(c,shape = rect, false));
						}
						rect.setRect(min(e.getX(), o.getX()), min(e.getY(), o.getY()), abs(e.getX() - o.getX()),
								abs(e.getY() - o.getY()));
						panel.repaint();
					}
						break;
					case "Pen" : {
						Path2D.Double path = (Path2D.Double) shape;
						if (path == null) {
							path = new Path2D.Double();
							path.moveTo(o.getX(), o.getY());
							colorshapes.add(new ColorShape(c,shape = path, false));
						}
						path.lineTo(e.getX(), e.getY());
						panel.repaint();
					}
						break;
					case "Line" : {
						Line2D.Double line = (Line2D.Double) shape;
						if (line == null) {
							line = new Line2D.Double(o.getX(), o.getY(), 0, 0);
							colorshapes.add(new ColorShape(c,shape = line, false));
						}
						line.setLine(o.getX(), o.getY(), e.getX(),
								e.getY());
						panel.repaint();
					}
						break;
					case "Fill rect" : {
						Rectangle2D.Double rect = (Rectangle2D.Double) shape;
						if (rect == null) {
							rect = new Rectangle2D.Double(o.getX(), o.getY(), 0, 0);
							colorshapes.add(new ColorShape(c,shape = rect, true));
						}
						rect.setRect(min(e.getX(), o.getX()), min(e.getY(), o.getY()), abs(e.getX() - o.getX()),
								abs(e.getY() - o.getY()));
						panel.repaint();
					}
						break;
					case "Fill ellipse" : {
						Ellipse2D.Double ell = (Ellipse2D.Double) shape;
						if (ell == null) {
							ell = new Ellipse2D.Double(o.getX(), o.getY(), 0, 0);
							colorshapes.add(new ColorShape(c,shape = ell, true));
						}
						ell.setFrame(min(e.getX(), o.getX()), min(e.getY(), o.getY()), abs(e.getX() - o.getX()),
								abs(e.getY() - o.getY()));
						panel.repaint();
					}
						break;
					case "RoundRec" : {
						RoundRectangle2D.Double rect = (RoundRectangle2D.Double) shape;
						if (rect == null) {
							rect = new RoundRectangle2D.Double(o.getX(), o.getY(), 0, 0, 0, 0);
							colorshapes.add(new ColorShape(c,shape = rect, false));
						}
						rect.setRoundRect(min(e.getX(), o.getX()), min(e.getY(), o.getY()), abs(e.getX() - o.getX()),
								abs(e.getY() - o.getY()), 20, 20);
						panel.repaint();
					}
						break;
					case "none" : {
						Path2D.Double path = (Path2D.Double) shape;
						if (path == null) {
							path = new Path2D.Double();
							path.moveTo(o.getX(), o.getY());
							colorshapes.add(new ColorShape(c,shape = path, false));
						}
						path.lineTo(e.getX(), e.getY());
						panel.repaint();
					}
						break;
					}
				}
			}
		});
		JToggleButton expert = new JToggleButton();
		expert.setText("Expert");
		expert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(view.model.isExpert) {
					view.model.isExpert = false;
				}
				else{
					view.model.isExpert = true;
				}
			}
		});
		expert.setVisible(true);
		panel.add(expert);
		pack();
		setVisible(true);
	}

	/* main *********************************************************************/

	public static void main(String argv[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Paint paint = new Paint("paint");
			}
		});
	}
}