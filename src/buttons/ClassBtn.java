package buttons;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import GUI.Task_Bar;
import modes.Class_mode;

public class ClassBtn extends button{

	public ClassBtn(Task_Bar t) {
		super(t);
		ImageIcon []tmp_icons = {new ImageIcon("./img/class_icon.png"), new ImageIcon("./img/class_icon_pressed.png")};
		icons = tmp_icons;
		my_mode = "Class_mode";
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(!task_bar.getMode().getmode().equals(my_mode)) {
			task_bar.setmode(new Class_mode(task_bar.getMode().getCanvas()));
			task_bar.updateIcons();
		}
	}

}
