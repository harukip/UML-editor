import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;



public class UML {
	public static class global{
		private  int mode = -1, depth = 0;
		public My_Button[] global_bs;
		public void set_mode(int n) {
			mode = n;
		}
		public int get_mode() {
			return mode;
		}
		public void set_depth(int n) {
			depth = n;
		}
		public int get_top_depth() {
			return depth;
		}
		public int nearest_port(int[][] port, int[] pos) {
			int min = 9999, nearest = -1;
			for(int port_num = 0; port_num < 4; port_num++) {
				int tmp = (int)(Math.sqrt((Math.pow((port[port_num][0] - pos[0]), 2) + Math.pow((port[port_num][1] - pos[1]), 2))));
				if(tmp < min) {
					nearest = port_num;
					min = tmp;
				}
			}
			return nearest;
		}
		public void flush_icon(My_Button[] bs) {
			for(My_Button b : bs) {
				if (b.get_num() != g.mode) {
					b.set_ispressed(0);
					b.show_icon();
				}
			}
		}
	}
	
	private static global g = new global();
	public static global get_g() {
		return g;
	}
	public static void main(String[] args) {
		// Java window setup
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = (int)(tk.getScreenSize().getWidth() * 0.5);
		int ySize = (int)(tk.getScreenSize().getHeight() * 0.7);
		int button_height = (int)(xSize*0.1);
		
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
		
		My_Canvas canvas = new My_Canvas(xSize, ySize, button_height, 0);
		cp.add(canvas);
		
		JMenu edit = new JMenu("Edit");
		JMenuItem group = new JMenuItem("Group");
		JMenuItem ungroup = new JMenuItem("UnGroup");
		JMenuItem change_name = new My_Menu_Item.change_name_menu(canvas, "change object name");
		
		edit.add(group);
		edit.add(ungroup);
		edit.add(change_name);
		
		JMenuBar menu = new JMenuBar();
		menu.add(file);
		menu.add(edit);
		window.setJMenuBar(menu);
		
		My_Button []buttons = {
				new My_Button.select_button(g), 
				new My_Button.asso_button(g), 
				new My_Button.gen_button(g), 
				new My_Button.com_button(g), 
				new My_Button.class_button(g), 
				new My_Button.use_class_button(g)
		};
		
		g.global_bs = buttons;
		
		for(int i = 0; i < g.global_bs.length; i++) {
			g.global_bs[i].set_num(i);
			g.global_bs[i].setBounds(0, button_height*i, button_height, button_height);
			cp.add(g.global_bs[i]);
		}
		
		window.setVisible(true);
	}

}
