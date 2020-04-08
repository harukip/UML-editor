import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UML {

	public static class select_button extends JButton {
		public select_button() {
			this.ispressed = 0;
			this.setIcon(icons[ispressed]);
			this.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					change_icon();
				}
			});
		}
		
		public void change_icon() {
			ispressed = (ispressed + 1)%2;
			this.setIcon(icons[ispressed]);
		}
		

		private ImageIcon []icons = {new ImageIcon("./img/select_icon.png"), new ImageIcon("./img/select_icon_pressed.png")};
		private int ispressed;
	}
	
	public static class asso_button extends JButton {
		public asso_button() {
			ImageIcon icon = new ImageIcon("./img/asso_icon.png");
			this.setIcon(icon);
		}
	}
	
	public static class gen_button extends JButton {
		public gen_button() {
			ImageIcon icon = new ImageIcon("./img/gen_icon.png");
			this.setIcon(icon);
		}
	}
	
	public static class com_button extends JButton {
		public com_button() {
			ImageIcon icon = new ImageIcon("./img/com_icon.png");
			this.setIcon(icon);
		}
	}

	public static class class_button extends JButton {
		public class_button() {
			ImageIcon icon = new ImageIcon("./img/class_icon.png");
			this.setIcon(icon);
		}
	}

	public static class use_class_button extends JButton {
		public use_class_button() {
			ImageIcon icon = new ImageIcon("./img/use_class_icon.png");
			this.setIcon(icon);
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
		
		JButton []buttons = {
				new select_button(), 
				new asso_button(), 
				new gen_button(), 
				new com_button(), 
				new class_button(), 
				new use_class_button()
		};
		for(int i = 0; i < buttons.length; i++) {
			buttons[i].setBounds(0, button_height*i, button_height, button_height);
			cp.add(buttons[i]);
		}
		
		Canvas canvas = new Canvas(xSize, ySize, button_height, 0);
		cp.add(canvas);
		
		window.setVisible(true);
	}

}
