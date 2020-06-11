import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

public class GeneralizationBtn extends button{

	GeneralizationBtn(Task_Bar t) {
		super(t);
		ImageIcon []tmp_icons = {new ImageIcon("./img/gen_icon.png"), new ImageIcon("./img/gen_icon_pressed.png")};
		icons = tmp_icons;
		my_mode = "Generalization_mode";
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("click GeneralizationBtn");
		if(!task_bar.getMode().getmode().equals(my_mode)) {
			task_bar.setmode(new Generlization_mode(task_bar.getMode().getCanvas()));
			task_bar.updateIcons();
		}
	}
}
