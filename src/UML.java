import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class UML {

	private static int mode = -1, depth = 0;
	public static void set_mode(int n) {
		mode = n;
	}
	public static int get_mode() {
		return mode;
	}
	public static void set_depth(int n) {
		depth = n;
	}
	public static int get_top_depth() {
		return depth;
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
					if(get_ispressed() == 1) set_mode(get_num());
					else set_mode(-1);
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
	
	public static class Class_Object extends Basic_Object{
		public Class_Object() {
			this.set_type(4);
		}
		@Override
		public void draw(Graphics g) {
			// TODO Auto-generated method stub
			super.draw(g);
			g.drawRect(this.get_x(), this.get_y(), this.get_width(), this.get_height());
		}
	}
	
	public static class Use_Class_Object extends Basic_Object{
		public Use_Class_Object() {
			this.set_type(5);
			this.set_width(100);
			this.set_height(50);
		}
		@Override
		public void draw(Graphics g) {
			// TODO Auto-generated method stub
			super.draw(g);
			g.drawOval(this.get_x(), this.get_y(), this.get_width(), this.get_height());
		}
	}
	
	public static class Line_Object extends Basic_Object{
		public Line_Object() {
			// TODO Auto-generated constructor stub
		}
	}
	
	public static class Asso_Line extends Line_Object{
		public Asso_Line() {
			this.set_type(1);
		}
	}
	
	public static class Gen_Line extends Line_Object{
		public Gen_Line() {
			this.set_type(2);
		}
	}
	
	public static class Com_Line extends Line_Object{
		public Com_Line() {
			this.set_type(3);
		}
	}
	
	public static class Canvas extends JPanel{
		public Canvas(int xSize, int ySize, int x, int y) {
			this.setBounds(x, y, (int)(xSize*0.9), ySize);
			this.setBackground(Color.white);
			
			this.addMouseListener(new MouseListener() {
				int top, top_pos;
				int[] obj_in_range;
				@Override
				public void mouseReleased(MouseEvent e) {
					// Select
					if(get_mode() == 0 && top != -1) {
						my_Objects[top_pos].set_x(e.getX());
						my_Objects[top_pos].set_y(e.getY());
						repaint();
					}
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					top = -1;
					top_pos = -1;
					// Select
					if(get_mode() == 0) {
						int current_x = e.getX();
						int current_y = e.getY();
						int count = 0;
						obj_in_range =  new int[obj_count];
						
						for(int i = 0; i < obj_count; i++) {
							if(my_Objects[i].is_in_range(current_x, current_y)) {
								obj_in_range[count] = i;
								count += 1;
							}
						}
						for(int i = 0; i < count; i++) {
							if(my_Objects[obj_in_range[i]].get_depth() > top) {
								top = my_Objects[i].get_depth();
								top_pos = obj_in_range[i];
							}
						}
					}
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					if(get_mode() > 3) {
						int new_x = e.getX();
						int new_y = e.getY() - y;
						Basic_Object []tmp = {
								null, 
								new Asso_Line(),
								new Gen_Line(),
								new Com_Line(),
								new Class_Object(),
								new Use_Class_Object()
								};
						System.out.println(obj_count);
						System.out.println("x= "+new_x+", y= "+new_y);
						my_Objects[get_count()] = tmp[get_mode()];
						my_Objects[get_count()].set_x(new_x);
						my_Objects[get_count()].set_y(new_y);
						my_Objects[get_count()].set_depth(get_top_depth());
						obj_count += 1;
						set_depth(get_top_depth()+1);
						repaint();
					}
				}
			});
		}
		
		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			
			for(int i = 0; i < obj_count; i++) {
				g.setColor(Color.black);
				my_Objects[i].draw(g);
			}
		}
		
		public int get_count() {
			return this.obj_count;
		}
		private Basic_Object []my_Objects = new Basic_Object[100];
		private int obj_count = 0;
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
