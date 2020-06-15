package buttons;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import GUI.Task_Bar;
import modes.Select_mode;

public class SelectBtn extends button{
	public SelectBtn(Task_Bar t) {
		super(t);
		ImageIcon []tmp_icons = {new ImageIcon("./img/select_icon.png"), new ImageIcon("./img/select_icon_pressed.png")};
		icons = tmp_icons;
		my_mode = "Select_mode";
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("click select");
		if(!task_bar.getMode().getmode().equals(my_mode)) {
			System.out.println("change to select");
			task_bar.setmode(new Select_mode(task_bar.getMode().getCanvas()));
			task_bar.updateIcons();
		}
	}
}
