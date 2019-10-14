package rangeSlider;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.geom.Point2D;

public class RangeSliderUI extends JPanel{
	//Corps du slider
	protected static final int XMIN = 10;
	protected static final int YMIN = 50;
	protected static final int W = 300;
	protected static final int H = 4;
	//Hauteur et largeur des curseurs
	protected static final int CURSOR_HEIGHT = 20;
	protected static final int CURSOR_WIDTH = 10;
	//x et y curseur gauche
	protected static final int XLEFT = XMIN-CURSOR_WIDTH/2;
	protected static final int YLEFT = YMIN-CURSOR_HEIGHT/2+H/2;
	//x et y curseur droit
	protected static final int XRIGHT = XMIN+W-CURSOR_WIDTH/2;
	protected static final int YRIGHT = YMIN-CURSOR_HEIGHT/2+H/2;
	
	protected Controller controller;
	
	protected Rectangle rectPrincipal;	//rectangle correspondant au range slider
	protected Rectangle cursorLeft;	//rectangle correspondant au curseur gauche
	protected Rectangle cursorRight;	//rectangle correspondant au curseur droite
	protected Rectangle rectMiddle;	//rectangle correspondant à la zone entre les deux curseurs
	public int scale;				//Nombre de valeurs du range slider
	private Font fonte = new Font("Arial",Font.BOLD,10);
	public int firstValue;
	public int secondValue;
	
	
	public RangeSliderUI(int scale){
		rectPrincipal = new Rectangle(XMIN,YMIN, W, H);
		cursorLeft = new Rectangle(XLEFT, YLEFT, CURSOR_WIDTH, CURSOR_HEIGHT);
		cursorRight = new Rectangle(XRIGHT, YRIGHT, CURSOR_WIDTH, CURSOR_HEIGHT);
		setPreferredSize(new Dimension(W+XMIN*2, H+2*YMIN));
		rectMiddle = new Rectangle(XMIN,YMIN, W, H);
		this.scale = scale;
		this.firstValue=0;
		this.secondValue=this.scale;
		controller = new Controller();
		this.addMouseMotionListener(controller);
		this.addMouseListener(controller);
	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(rectPrincipal.x, rectPrincipal.y, rectPrincipal.width, rectPrincipal.height);
		g.setColor(Color.BLACK);
		g.fillRect((int)cursorLeft.getCenterX(),YMIN, (int)(cursorRight.getCenterX()-cursorLeft.getCenterX()), H);
		g.setFont(fonte);
		g.drawString(Integer.toString(this.firstValue), cursorLeft.x, cursorLeft.y-10);
		g.drawString(Integer.toString(this.secondValue), cursorRight.x, cursorRight.y-10);
		g.setColor(Color.gray);
		g.fillRect(cursorLeft.x, cursorLeft.y, cursorLeft.width, cursorLeft.height);
		g.fillRect(cursorRight.x, cursorRight.y, cursorRight.width, cursorRight.height);
	}
	
	public void translate(Rectangle rect, int x) {
		rect.translate(x, 0);
		this.repaint();
	}
	
	
}
