package GUI;
import java.awt.Container;

import buttons.AssoBtn;
import buttons.ClassBtn;
import buttons.CompositionBtn;
import buttons.GeneralizationBtn;
import buttons.SelectBtn;
import buttons.UseCaseBtn;
import buttons.button;
import modes.Mode;
import modes.Select_mode;

public class Task_Bar {
	private Mode mode;
	private button[] buttons;
	private Container cp;
	
	public Task_Bar(Canvas cv, Container c, int button_height) {
		mode = new Select_mode(cv);
		button[] tmpButtons = {
				new SelectBtn(this),
				new AssoBtn(this),
				new GeneralizationBtn(this),
				new CompositionBtn(this),
				new ClassBtn(this),
				new UseCaseBtn(this)
		};
		buttons = tmpButtons;
		cp = c;
		for(int i = 0; i < buttons.length; i++) {
			buttons[i].setBounds(0, button_height*i, button_height, button_height);
			cp.add(buttons[i]);
		}
		updateIcons();
	}
	
	public void updateIcons() {
		for(button b: buttons) {
			b.updateIcon();
		}
	}
	
	public void setmode(Mode m) {
		mode = m;
	}
	
	public Mode getMode() {return mode;}
}
