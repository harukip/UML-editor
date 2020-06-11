import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

public class UseCaseBtn extends button{

	UseCaseBtn(Task_Bar t) {
		super(t);
		ImageIcon []tmp_icons = {new ImageIcon("./img/use_class_icon.png"), new ImageIcon("./img/use_class_icon_pressed.png")};
		icons = tmp_icons;
		my_mode = "Use_case_mode";
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("click UseCaseBtn");
		if(!task_bar.getMode().getmode().equals(my_mode)) {
			task_bar.setmode(new Use_case_mode(task_bar.getMode().getCanvas()));
			task_bar.updateIcons();
		}
	}

}
