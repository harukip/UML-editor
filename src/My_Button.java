import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class My_Button extends JButton {
	public My_Button(UML.global u) {
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				change_icon();
				show_icon();
				if(get_ispressed() == 1) u.set_mode(get_num());
				else u.set_mode(-1);
				u.flush_icon(u.global_bs);
			}
		});
	}
	
	public void change_icon() {
		ispressed = (ispressed + 1)%2;
	}
	
	public void show_icon() {
		this.setIcon(icons[ispressed]);
	}

	public void set_num(int n) {
		this.num = n;
	}
	public void set_ispressed(int n) {
		this.ispressed = n;
	}
	public int get_num() {
		return this.num;
	}
	public int get_ispressed() {
		return ispressed;
	}
	public ImageIcon []icons = {new ImageIcon(), new ImageIcon()};
	private int ispressed = -1;
	private int num = -1;
	
	public static class select_button extends My_Button {
		public select_button(UML.global u) {
			super(u);
			ImageIcon []tmpIcons = {new ImageIcon("./img/select_icon.png"), new ImageIcon("./img/select_icon_pressed.png")};
			this.icons = tmpIcons;
			this.change_icon();
			this.show_icon();
		}
	}
	
	public static class asso_button extends My_Button {
		public asso_button(UML.global u) {
			super(u);
			ImageIcon []tmpIcons = {new ImageIcon("./img/asso_icon.png"), new ImageIcon("./img/asso_icon_pressed.png")};
			this.icons = tmpIcons;
			this.change_icon();
			this.show_icon();
		}
	}
	
	public static class gen_button extends My_Button {
		public gen_button(UML.global u) {
			super(u);
			ImageIcon []tmpIcons = {new ImageIcon("./img/gen_icon.png"), new ImageIcon("./img/gen_icon_pressed.png")};
			this.icons = tmpIcons;
			this.change_icon();
			this.show_icon();
		}
	}
	
	public static class com_button extends My_Button {
		public com_button(UML.global u) {
			super(u);
			ImageIcon []tmpIcons = {new ImageIcon("./img/com_icon.png"), new ImageIcon("./img/com_icon_pressed.png")};
			this.icons = tmpIcons;
			this.change_icon();
			this.show_icon();
		}
	}

	public static class class_button extends My_Button {
		public class_button(UML.global u) {
			super(u);
			ImageIcon []tmpIcons = {new ImageIcon("./img/class_icon.png"), new ImageIcon("./img/class_icon_pressed.png")};
			this.icons = tmpIcons;
			this.change_icon();
			this.show_icon();
		}
	}

	public static class use_class_button extends My_Button {
		public use_class_button(UML.global u) {
			super(u);
			ImageIcon []tmpIcons = {new ImageIcon("./img/use_class_icon.png"), new ImageIcon("./img/use_class_icon_pressed.png")};
			this.icons = tmpIcons;
			this.change_icon();
			this.show_icon();
		}
	}
}


