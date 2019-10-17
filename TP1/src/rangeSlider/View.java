package rangeSlider;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.geom.Point2D;

public class View extends JPanel{
	
	public Model model;
	protected Controller controller;
	protected Rectangle rectPrincipal;	//rectangle correspondant au range slider
	protected Rectangle cursorLeft;	//rectangle correspondant au curseur gauche
	protected Rectangle cursorRight;	//rectangle correspondant au curseur droite
	protected Rectangle rectMiddle;	//rectangle correspondant à la zone entre les deux curseurs
	private Font fonte = new Font("Arial",Font.BOLD,10);
	private Font fonte2 = new Font("Roboto",Font.BOLD,15);
	private String title;
	public int scale;				//Nombre de valeurs du range slider
	
	
	public View(int scale, String title){
		this.title = title;
		this.scale = scale;
		this.model = new Model();
		this.model.firstValue=0;
		this.model.secondValue=this.scale;
		controller = new Controller(model);
		this.addMouseMotionListener(controller);
		this.addMouseListener(controller);
		this.rectPrincipal = new Rectangle(Model.XMIN,Model.YMIN, Model.W, Model.H);
		this.cursorLeft = new Rectangle(Model.XLEFT, Model.YLEFT, Model.CURSOR_WIDTH, Model.CURSOR_HEIGHT);
		this.cursorRight = new Rectangle(Model.XRIGHT, Model.YRIGHT, Model.CURSOR_WIDTH, Model.CURSOR_HEIGHT);
		this.rectMiddle = new Rectangle(Model.XMIN,Model.YMIN, Model.W, Model.H);
		this.setPreferredSize(new Dimension(Model.W+Model.XMIN*2, Model.H+2*Model.YMIN));
	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(rectPrincipal.x, rectPrincipal.y, rectPrincipal.width, rectPrincipal.height);
		g.setColor(Color.BLACK);
		g.setFont(fonte2);
		g.drawString(this.title, 30, 15);
		g.fillRect((int)cursorLeft.getCenterX(),Model.YMIN, (int)(cursorRight.getCenterX()-cursorLeft.getCenterX()), Model.H);
		g.setFont(fonte);
		g.drawString(Integer.toString(this.model.firstValue), cursorLeft.x, cursorLeft.y-10);
		g.drawString(Integer.toString(this.model.secondValue), cursorRight.x, cursorRight.y-10);
		g.setColor(Color.gray);
		g.fillRect(cursorLeft.x, cursorLeft.y, cursorLeft.width, cursorLeft.height);
		g.fillRect(cursorRight.x, cursorRight.y, cursorRight.width, cursorRight.height);
	}
	
	public void translate(Rectangle rect, int x) {
		rect.translate(x, 0);
		this.repaint();
	}
	
	public Rectangle getCursorLeft() {
		return this.cursorLeft;
	}
	
	public Rectangle getCursorRight() {
		return this.cursorRight;
	}
}
