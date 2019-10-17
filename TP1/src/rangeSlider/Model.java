package rangeSlider;

import java.awt.Rectangle;

public class Model {
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

		public int firstValue;
		public int secondValue;
}
