package markingMenu;

import java.util.ArrayList;
import java.util.List;

public class Model {
	public final static int MENUSIZE = 10;
	public final static int RECTWIDTH = 100;
	public final static int RECTHEIGHT = 20;

	public int positionX = -1000;
	public int positionY = -1000;
	int newX = -1000;
	int newY = -1000;
	public String choixMenuCouleur = "none";
	public String choixMenuOutils = "none";
	List<String> listSousMenu = new ArrayList<String>();
	public Boolean isOpen = false;
}
