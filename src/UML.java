import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UML {

	private static int mode = -1;
	public static void change_mode(int n) {
		mode = n;
		
	}

	public static my_button[] global_bs;
	public static void flush_icon(my_button[] bs) {
		for(my_button b : bs) {
			if (b.get_num() != mode) {
				b.set_ispressed(0);
				b.show_icon();
			}
		}
	}
	
	public static class my_button extends JButton {
		public my_button() {
			this.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					change_icon();
					show_icon();
					if(get_ispressed() == 1) change_mode(get_num());
					else change_mode(-1);
					flush_icon(global_bs);
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
	}
	
	public static class select_button extends my_button {
		public select_button() {
			ImageIcon []tmpIcons = {new ImageIcon("./img/select_icon.png"), new ImageIcon("./img/select_icon_pressed.png")};
			this.icons = tmpIcons;
			this.change_icon();
			this.show_icon();
		}
	}
	
	public static class asso_button extends my_button {
		public asso_button() {
			ImageIcon []tmpIcons = {new ImageIcon("./img/asso_icon.png"), new ImageIcon("./img/asso_icon_pressed.png")};
			this.icons = tmpIcons;
			this.change_icon();
			this.show_icon();
		}
	}
	
	public static class gen_button extends my_button {
		public gen_button() {
			ImageIcon []tmpIcons = {new ImageIcon("./img/gen_icon.png"), new ImageIcon("./img/gen_icon_pressed.png")};
			this.icons = tmpIcons;
			this.change_icon();
			this.show_icon();
		}
	}
	
	public static class com_button extends my_button {
		public com_button() {
			ImageIcon []tmpIcons = {new ImageIcon("./img/com_icon.png"), new ImageIcon("./img/com_icon_pressed.png")};
			this.icons = tmpIcons;
			this.change_icon();
			this.show_icon();
		}
	}

	public static class class_button extends my_button {
		public class_button() {
			ImageIcon []tmpIcons = {new ImageIcon("./img/class_icon.png"), new ImageIcon("./img/class_icon_pressed.png")};
			this.icons = tmpIcons;
			this.change_icon();
			this.show_icon();
		}
	}

	public static class use_class_button extends my_button {
		public use_class_button() {
			ImageIcon []tmpIcons = {new ImageIcon("./img/use_class_icon.png"), new ImageIcon("./img/use_class_icon_pressed.png")};
			this.icons = tmpIcons;
			this.change_icon();
			this.show_icon();
		}
	}
	
	public static class Canvas extends JPanel{
		public Canvas(int xSize, int ySize, int x, int y) {
			this.setBounds(x, y, (int)(xSize*0.9), ySize);
			this.setBackground(Color.white);
		}
	}
	
	
	public static void main(String[] args) {
		// Java window setup
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = (int)(tk.getScreenSize().getWidth() * 0.5);
		int ySize = (int)(tk.getScreenSize().getHeight() * 0.7);
		JFrame window = new JFrame("UML Editor"); 
		window.setSize(xSize, ySize);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container cp = window.getContentPane();
		cp.setLayout(null);
		
		JMenu file = new JMenu("File");
		JMenuItem op = new JMenuItem("Open file");
		JMenuItem sv = new JMenuItem("Save file");
		file.add(op);
		file.add(sv);
		
		JMenu edit = new JMenu("Edit");
		JMenuItem one = new JMenuItem("1");
		JMenuItem two = new JMenuItem("2");
		edit.add(one);
		edit.add(two);
		
		JMenuBar menu = new JMenuBar();
		menu.add(file);
		menu.add(edit);
		window.setJMenuBar(menu);
		
		int button_height = (int)(xSize*0.1);
		
		my_button []buttons = {
				new select_button(), 
				new asso_button(), 
				new gen_button(), 
				new com_button(), 
				new class_button(), 
				new use_class_button()
		};
		global_bs = buttons;
		
		for(int i = 0; i < global_bs.length; i++) {
			global_bs[i].set_num(i);
			global_bs[i].setBounds(0, button_height*i, button_height, button_height);
			cp.add(global_bs[i]);
		}
		
		Canvas canvas = new Canvas(xSize, ySize, button_height, 0);
		cp.add(canvas);
		
		window.setVisible(true);
	}

}
