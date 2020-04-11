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
						if(i == g.get_mode()) is_line = true;
					}
					// Select
					if(top != -1) {
						if(g.get_mode() == 0) {
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
								
								int source_port = g.nearest_port(my_Objects[source_obj].get_port(), m_start_pos);
								int[] start_pos = my_Objects[source_obj].get_port()[source_port];
								int[] m_end_pos = {e.getX(), e.getY() - y}; // mouse end pos
								int end_port = g.nearest_port(my_Objects[top_pos].get_port(), m_end_pos);
								int[] end_pos = my_Objects[top_pos].get_port()[end_port];
								
								
								Line_Object tmp[] = {
										null,
										new Line_Object.Asso_Line(start_pos, end_pos),
										new Line_Object.Com_Line(start_pos, end_pos)
										};
								my_Line_Objects[get_line_count()] = tmp[g.get_mode()];
								my_Line_Objects[get_line_count()].set_obj_link(source_obj, source_port, top_pos, end_port);				
								line_count += 1;
							}
						}
					}
					else {
						if(g.get_mode() == 0) {
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
					if(g.get_mode() > 3) {
						int new_x = e.getX();
						int new_y = e.getY() - y;
						Basic_Object []tmp = {
								null, 
								null,
								null,
								null,
								new Basic_Object.Class_Object(),
								new Basic_Object.Use_Class_Object()
								};
						my_Objects[get_obj_count()] = tmp[g.get_mode()];
						my_Objects[get_obj_count()].set_x(new_x);
						my_Objects[get_obj_count()].set_y(new_y);
						my_Objects[get_obj_count()].set_port();
						my_Objects[get_obj_count()].set_depth(g.get_top_depth());
						obj_count += 1;
						g.set_depth(g.get_top_depth()+1);
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
		public Basic_Object[] get_my_Objects() {
			return my_Objects;
		}
		public Line_Object[] get_my_Line_Objects() {
			return my_Line_Objects;
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
