package buttons;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import GUI.Task_Bar;
import modes.Composition_mode;

public class CompositionBtn extends button{

	public CompositionBtn(Task_Bar t) {
		super(t);
		ImageIcon []tmp_icons = {new ImageIcon("./img/com_icon.png"), new ImageIcon("./img/com_icon_pressed.png")};
		icons = tmp_icons;
		my_mode = "Composition_mode";
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("click CompositionBtn");
		if(!task_bar.getMode().getmode().equals(my_mode)) {
			task_bar.setmode(new Composition_mode(task_bar.getMode().getCanvas()));
			task_bar.updateIcons();
		}
	}
}
