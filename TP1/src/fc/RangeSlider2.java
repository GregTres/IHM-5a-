package fc;

import javax.swing.JSlider;


public class RangeSlider2 extends JSlider{

	public JSlider slider;
	
	public RangeSlider2() {
		slider = new JSlider(JSlider.HORIZONTAL, 0,10,5);
	}
}
