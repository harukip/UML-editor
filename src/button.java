import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public abstract class button extends JButton implements ActionListener{
	ImageIcon[] icons;
	String my_mode;
	Task_Bar task_bar;
	button(Task_Bar t){
		task_bar = t;
	}
	
	public void updateIcon() {
		if(my_mode.equals(task_bar.getMode().getmode())) {
			this.setIcon(icons[1]);
		}
		else this.setIcon(icons[0]);
	}
}
