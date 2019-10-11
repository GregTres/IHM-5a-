package fc;

public class RangeSlider {

	int min;
	int max;
	int curseurg;
	int curseurd;
	
	public RangeSlider(int min, int max, int curseurg, int curseurd) {
		this.min = min;
		this.max = max;
		this.curseurg = curseurg;
		this.curseurd = curseurd;


	}
	
	public int getcurseurg() {
		return curseurg;
	}
	
	public int getcurseurd() {
		return curseurd;
	}
	
	public void setcurseurg(int newvalue) {
		this.curseurg = newvalue ;
	}
	
	public void setcurseurd(int newvalue) {
		this.curseurd = newvalue ;
	}
	
	
	
}
