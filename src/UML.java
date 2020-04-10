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
	public static int nearest_port(int[][] port, int[] pos) {
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
	
	public static class my_Menu_Item extends JMenuItem {
		public my_Menu_Item(Canvas c, String s) {
			// TODO Auto-generated constructor stub
			super(s);
		}
	}

	public static class change_name_menu extends my_Menu_Item{
		public change_name_menu(Canvas c, String s) {
			// TODO Auto-generated constructor stub
			super(c, s);
			addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					int[] selected_obj = new int[c.get_obj_count()];
					int selected_count = 0;
					for(int i = 0; i < c.get_obj_count(); i++) {
						if(c.my_Objects[i].is_selected()) {
							selected_obj[selected_count] = i;
							selected_count += 1;
						}
					}
					if(selected_count == 1) {
						Toolkit tk = Toolkit.getDefaultToolkit();
						int xSize = (int)(tk.getScreenSize().getWidth() * 0.2);
						int ySize = (int)(tk.getScreenSize().getHeight() * 0.2);
						JFrame set_name_JFrame = new JFrame("Set new name");
						Container cp = set_name_JFrame.getContentPane();
						cp.setLayout(null);
						set_name_JFrame.setSize(xSize, ySize);
						set_name_JFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						JTextField name_Field = new JTextField();
						name_Field.setBounds((int)(xSize*0.1), (int)(ySize*0.2), (int)(xSize*0.8), (int)(ySize*0.2));
						JButton okButton = new JButton("OK");
						okButton.setBounds((int)(xSize*0.1), (int)(ySize*0.2)*2, (int)(xSize*0.4), (int)(ySize*0.2));
						JButton cancelButton = new JButton("Cancel");
						cancelButton.setBounds((int)(xSize*0.1)*5, (int)(ySize*0.2)*2, (int)(xSize*0.4), (int)(ySize*0.2));
						cp.add(name_Field);
						cp.add(okButton);
						cp.add(cancelButton);
						set_name_JFrame.setVisible(true);
						cancelButton.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								set_name_JFrame.dispose();
							}
						});
						okButton.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								c.my_Objects[selected_obj[0]].set_name(name_Field.getText());
								c.repaint();
								set_name_JFrame.dispose();
							}
						});
					}
				}
			});
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
			this.draw_name(g);
			if(this.is_selected()) {
				for(int port_num = 0; port_num < 4; port_num++) {
					g.fillRect(this.get_port()[port_num][0], this.get_port()[port_num][1], 5, 5);
				}
			}
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
			this.draw_name(g);
			if(this.is_selected()) {
				for(int port_num = 0; port_num < 4; port_num++) {
					g.fillRect(this.get_port()[port_num][0], this.get_port()[port_num][1], 5, 5);
				}
			}
		}
	}
	
	public static class Asso_Line extends Line_Object{
		public Asso_Line(int[] start, int[]end) {
			this.set_type(1);
			this.set_start_end(start, end);
		}
		@Override
		public void draw(Graphics g) {
			// TODO Auto-generated method stub
			super.draw(g);
			int[] start_point = this.get_start_end()[0];
			int[] end_point = this.get_start_end()[1];
			int end_port = this.get_obj_link()[1][1];
			int[] shift_point = this.find_unit(start_point, end_point);
			int[][] arrow_point = new int[2][2];
			int[] angle = {-30, 30};
			g.drawLine(
					start_point[0], start_point[1], 
					end_point[0], end_point[1]);
			for(int i = 0; i < 2; i++) {
				arrow_point[i] = rotate_shift(end_point, shift_point, Math.toRadians(angle[i]));
				g.drawLine(
					arrow_point[i][0], arrow_point[i][1], 
					end_point[0], end_point[1]);
			}
		}
	}
	
	public static class Gen_Line extends Line_Object{
		public Gen_Line(int[] start, int[]end) {
			this.set_type(2);
			this.set_start_end(start, end);
		}
		@Override
		public void draw(Graphics g) {
			// TODO Auto-generated method stub
			super.draw(g);
			int end_port = this.get_obj_link()[1][1];
			int[][] shift = {{0, 20, 0, -20}, {-20, 0, 20, 0}};
			int[] angle = {180, 270, 0, 90};
			int[][] triangle = {{10, -10, 0}, {20, 20, 0}};
			int[][] output_triangle = this.rotate_shape(triangle, Math.toRadians(angle[end_port]));
			g.drawPolygon(output_triangle[0], output_triangle[1], 3);
			g.drawLine(
					this.get_start_end()[0][0], this.get_start_end()[0][1], 
					this.get_start_end()[1][0]+shift[0][end_port], this.get_start_end()[1][1]+shift[1][end_port]);
		}
	}
	
	public static class Com_Line extends Line_Object{
		public Com_Line(int[] start, int[]end) {
			this.set_type(3);
			this.set_start_end(start, end);
		}
		@Override
		public void draw(Graphics g) {
			// TODO Auto-generated method stub
			super.draw(g);
			int end_port = this.get_obj_link()[1][1];
			int[][] shift = {{0, 20, 0, -20}, {-20, 0, 20, 0}};
			int[] angle = {180, 270, 0, 90};
			int[][] square = {{0, 10, 0, -10}, {0, 10, 20, 10}};
			int[][] output_triangle = this.rotate_shape(square, Math.toRadians(angle[end_port]));
			g.drawPolygon(output_triangle[0], output_triangle[1], 4);
			g.drawLine(
					this.get_start_end()[0][0], this.get_start_end()[0][1], 
					this.get_start_end()[1][0]+shift[0][end_port], this.get_start_end()[1][1]+shift[1][end_port]);
		}
	}
	
	public static class Canvas extends JPanel{
		public Canvas(int xSize, int ySize, int x, int y) {
			this.setBounds(x, y, (int)(xSize*0.9), ySize);
			this.setBackground(Color.white);
			
			this.addMouseListener(new MouseListener() {
				int [] line_mode = {1, 2, 3};
				@Override
				public void mouseReleased(MouseEvent e) {
					boolean is_line = false;
					for(int i:line_mode) {
						if(i == get_mode()) is_line = true;
					}
					// Select
					if(top != -1) {
						if(get_mode() == 0) {
							int diff_x = m_start_pos[0] - my_Objects[top_pos].get_x();
							int diff_y = m_start_pos[1] - my_Objects[top_pos].get_y();
							my_Objects[top_pos].set_x(e.getX() - diff_x);
							my_Objects[top_pos].set_y(e.getY() - y - diff_y);
							my_Objects[top_pos].set_port();
							int[] lines_moved = new int[line_count];
							int moved_count = 0;
							for(int line = 0; line < line_count; line++) {
								if(my_Line_Objects[line].get_obj_link()[0][0] == top_pos || my_Line_Objects[line].get_obj_link()[1][0] == top_pos) {
									lines_moved[moved_count] = line;
									moved_count += 1;
								}
							}
							for(int line = 0; line < moved_count; line++) {
								int[] pos = new int[2];
								int[] port = new int [2];
								int[][] tmp = my_Line_Objects[lines_moved[line]].get_obj_link(); 
								for(int i = 0; i < 2; i++) {
									pos[i] = tmp[i][0];
									port[i] = tmp[i][1];
								}
								int[][] origin_start_end = my_Line_Objects[lines_moved[line]].get_start_end();
								if(top_pos == pos[0]) {
									int[] new_start_pos = my_Objects[top_pos].get_port()[port[0]];
									my_Line_Objects[lines_moved[line]].set_start_end(new_start_pos, origin_start_end[1]);
								}
								else{
									int[] new_end_pos = my_Objects[top_pos].get_port()[port[1]];
									my_Line_Objects[lines_moved[line]].set_start_end(origin_start_end[0], new_end_pos);
								}
							}
						}
						if(is_line && pressed) {
							pressed = false;
							int source_obj = top_pos;
							find_top(e.getX(), e.getY() - y);
							if(top != -1 && source_obj != top_pos) {
								
								int source_port = nearest_port(my_Objects[source_obj].get_port(), m_start_pos);
								int[] start_pos = my_Objects[source_obj].get_port()[source_port];
								int[] m_end_pos = {e.getX(), e.getY() - y}; // mouse end pos
								int end_port = nearest_port(my_Objects[top_pos].get_port(), m_end_pos);
								int[] end_pos = my_Objects[top_pos].get_port()[end_port];
								
								
								Line_Object tmp[] = {
										null,
										new Asso_Line(start_pos, end_pos),
										new Gen_Line(start_pos, end_pos),
										new Com_Line(start_pos, end_pos)
										};
								my_Line_Objects[get_line_count()] = tmp[get_mode()];
								my_Line_Objects[get_line_count()].set_obj_link(source_obj, source_port, top_pos, end_port);				
								line_count += 1;
							}
						}
					}
					else {
						if(get_mode() == 0) {
							int[] obj_in_range = new int [get_obj_count()];
							int in_range_count = 0;
							int[] m_end_pos = {e.getX(), e.getY() - y};
							for(int i = 0; i < get_obj_count(); i++) {
								my_Objects[i].set_selected(false);
								if(my_Objects[i].is_in_range(m_start_pos, m_end_pos)) {
									obj_in_range[in_range_count] = i;
									in_range_count += 1;
								}
							}
							for(int i = 0; i < in_range_count; i++) {
								my_Objects[obj_in_range[i]].set_selected(true);
							}
						}
					}
					repaint();
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					pressed = true;
					find_top(e.getX(), e.getY() - y);
					if(get_obj_count() > 0) {
						for(int i = 0; i < get_obj_count(); i++) {
							my_Objects[i].set_selected(false);
						}
						if(top_pos != -1) my_Objects[top_pos].set_selected(true);
					}
					m_start_pos[0] = e.getX();
					m_start_pos[1] = e.getY() - y;
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
					pressed = false;
					// TODO Auto-generated method stub
					if(get_mode() > 3) {
						int new_x = e.getX();
						int new_y = e.getY() - y;
						Basic_Object []tmp = {
								null, 
								null,
								null,
								null,
								new Class_Object(),
								new Use_Class_Object()
								};
						my_Objects[get_obj_count()] = tmp[get_mode()];
						my_Objects[get_obj_count()].set_x(new_x);
						my_Objects[get_obj_count()].set_y(new_y);
						my_Objects[get_obj_count()].set_port();
						my_Objects[get_obj_count()].set_depth(get_top_depth());
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
			for(int i = 0; i < line_count; i++) {
				my_Line_Objects[i].draw(g);
			}
		}
		
		public void find_top(int m_x, int m_y) {
			int current_x = m_x;
			int current_y = m_y;
			int count = 0;
			top = -1;
			top_pos = -1;
			obj_in_range =  new int[obj_count];
			
			for(int i = 0; i < obj_count; i++) {
				if(my_Objects[i].is_inside(current_x, current_y)) {
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
		private Basic_Object[] my_Objects = new Basic_Object[100];
		private Line_Object[] my_Line_Objects = new Line_Object[100];
		public int get_obj_count() {
			return obj_count;
		}
		public int get_line_count() {
			return line_count;
		}
		private int obj_count = 0, line_count = 0;
		private int top, top_pos;
		private int[] obj_in_range;
		private int[] m_start_pos = new int[2];
		private boolean pressed = false;
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
		
		Canvas canvas = new Canvas(xSize, ySize, button_height, 0);
		cp.add(canvas);
		
		JMenu edit = new JMenu("Edit");
		JMenuItem group = new JMenuItem("Group");
		JMenuItem ungroup = new JMenuItem("UnGroup");
		JMenuItem change_name = new change_name_menu(canvas, "change object name");
		
		edit.add(group);
		edit.add(ungroup);
		edit.add(change_name);
		
		JMenuBar menu = new JMenuBar();
		menu.add(file);
		menu.add(edit);
		window.setJMenuBar(menu);
		
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
		
		window.setVisible(true);
	}

}
