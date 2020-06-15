package buttons;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import GUI.Task_Bar;
import modes.Assocline_mode;

public class AssoBtn extends button{

	public AssoBtn(Task_Bar t) {
		super(t);
		ImageIcon []tmp_icons = {new ImageIcon("./img/asso_icon.png"), new ImageIcon("./img/asso_icon_pressed.png")};
		icons = tmp_icons;
		my_mode = "Assocline_mode";
		this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("click AssoBtn");
		if(!task_bar.getMode().getmode().equals(my_mode)) {
			task_bar.setmode(new Assocline_mode(task_bar.getMode().getCanvas()));
			task_bar.updateIcons();
		}
	}
}
