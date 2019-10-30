package markingMenu;

import java.util.ArrayList;
import java.util.List;

public class Model {
	public final static int MENUSIZE = 10;
	public final static int RECTWIDTH = 100;
	public final static int RECTHEIGHT = 20;

	int positionX = -1000;
	int positionY = -1000;
	int newX = -1000;
	int newY = -1000;
	String choixMenu = "";
	List<String> listSousMenu = new ArrayList<String>();
}
