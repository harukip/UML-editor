import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class My_Canvas extends JPanel{
	public My_Canvas(int xSize, int ySize, int x, int y) {
		this.setBounds(x, y, (int)(xSize*0.9), ySize);
		this.setBackground(Color.white);
		this.x = x;
		this.y = y;
		
		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				move_obj(e);
				repaint();
			}
		});
		
		this.addMouseListener(new MouseListener() {
			int [] line_mode = {1, 2, 3};
			
			@Override
			public void mouseReleased(MouseEvent e) {
				boolean is_line = false;
				for(int i:line_mode) {
					if(i == UML.get_g().get_mode()) is_line = true;
				}
				// Select
				if(top != -1) {
					if(is_line && pressed) {
						pressed = false;
						int source_obj = top_pos;
						find_top(e.getX(), e.getY() - y);
						if(top != -1 && source_obj != top_pos) {
							int source_port = UML.get_g().nearest_port(my_Objects[source_obj].get_port(), m_pressed_pos);
							int[] start_pos = my_Objects[source_obj].get_port()[source_port];
							int[] m_end_pos = {e.getX(), e.getY() - y}; // mouse end pos
							int end_port = UML.get_g().nearest_port(my_Objects[top_pos].get_port(), m_end_pos);
							int[] end_pos = my_Objects[top_pos].get_port()[end_port];
							
							
							Line_Object tmp[] = {
									null,
									new Line_Object.Asso_Line(start_pos, end_pos),
									new Line_Object.Gen_Line(start_pos, end_pos),
									new Line_Object.Com_Line(start_pos, end_pos)
									};
							my_Line_Objects[get_line_count()] = tmp[UML.get_g().get_mode()];
							my_Line_Objects[get_line_count()].set_obj_link(source_obj, source_port, top_pos, end_port);				
							line_count += 1;
						}
					}
				}
				else {
					if(UML.get_g().get_mode() == 0) {
						obj_in_range = new ArrayList<Integer>();
						int in_range_count = 0;
						int[] m_end_pos = {e.getX(), e.getY() - y};
						for(int i = 0; i < get_obj_count(); i++) {
							my_Objects[i].set_selected(false);
							if(my_Objects[i].is_in_range(m_pressed_pos, m_end_pos) || my_Objects[i].is_in_range(m_end_pos, m_pressed_pos)) {
								obj_in_range.add((int)i);
								in_range_count += 1;
							}
						}
						for(int i = 0; i < in_range_count; i++) {
							int pos = obj_in_range.get(i);
							int group_num = check_group(pos);
							if(group_num != -1) {
								for(Object obj_pos:groups[group_num].getList()) {
									my_Objects[(int)obj_pos].set_selected(true);
								}
							}
							else
								my_Objects[obj_in_range.get(i)].set_selected(true);
						}
					}
				}
				repaint();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				pressed = true;
				find_top(e.getX(), e.getY() - y);
				if(get_obj_count() > 0 && UML.get_g().get_mode() == 0) {
					for(int i = 0; i < get_obj_count(); i++) {
						my_Objects[i].set_selected(false);
					}
					if(top_pos != -1) {
						int group_num = check_group(top_pos); 
						if(group_num != -1) {
							for(Object obj_pos:groups[group_num].getList()) {
								my_Objects[(int)obj_pos].set_selected(true);
							}
						}
						else
							my_Objects[top_pos].set_selected(true);
					}
				}

				m_start_pos[0] = e.getX();
				m_start_pos[1] = e.getY() - y;
				m_pressed_pos[0] = e.getX();
				m_pressed_pos[1] = e.getY() - y;
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
				if(UML.get_g().get_mode() > 3) {
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
					my_Objects[get_obj_count()] = tmp[UML.get_g().get_mode()];
					my_Objects[get_obj_count()].set_x(new_x);
					my_Objects[get_obj_count()].set_y(new_y);
					my_Objects[get_obj_count()].set_port();
					my_Objects[get_obj_count()].set_depth(UML.get_g().get_top_depth());
					obj_count += 1;
					UML.get_g().set_depth(UML.get_g().get_top_depth()+1);
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
	
	public void move_obj(MouseEvent e) {
		if(top != -1) {
			int group_num = check_group(top_pos);
			if(group_num != -1 && UML.get_g().get_mode() == 0) {
				for(Object obj_pos:groups[group_num].getList()) {
					set_new_pos(e, (int)obj_pos);
				}
			}
			else {
				if(UML.get_g().get_mode() == 0) {
					set_new_pos(e, top_pos);
				}
			}
		}
		m_start_pos[0] = e.getX();
		m_start_pos[1] = e.getY()-y;
	}
	
	public void find_top(int m_x, int m_y) {
		int current_x = m_x;
		int current_y = m_y;
		int count = 0;
		top = -1;
		top_pos = -1;
		obj_in_range =  new ArrayList<Integer>();
		
		for(int i = 0; i < obj_count; i++) {
			if(my_Objects[i].is_inside(current_x, current_y)) {
				obj_in_range.add((int)i);
				count += 1;
			}
		}
		for(int i = 0; i < count; i++) {
			if(my_Objects[obj_in_range.get(i)].get_depth() > top) {
				top = my_Objects[i].get_depth();
				top_pos = obj_in_range.get(i);
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
	public Integer[] get_obj_in_range() {
		Integer[] output = new Integer[obj_in_range.size()];
		for(int i = 0; i < obj_in_range.size(); i++) {
			output[i] = (Integer)(obj_in_range.get(i));
		}
		return output;
	}
	public int check_group(int pos) {
		boolean have_group = false;
		for(int i:group_usage) {
			if(i == 1) {
				have_group = true;
				break;
			}
		}
		if (!have_group) {
			return -1;
		}
		int max_group = -1, max_group_len = -1;
		for(int group_pos = 0; group_pos < GROUP_NUM; group_pos++) {
			if(group_usage[group_pos]==0) continue;
			List<Integer> list = groups[group_pos].getList();
			if(list.contains((Object) pos)) {
				if(list.size() > max_group_len) {
					max_group_len = list.size();
					max_group = group_pos;
				}
			}
		}
		return max_group;
	}
	public void set_new_pos(MouseEvent e, int obj_pos) {
		int diff_x = m_start_pos[0] - my_Objects[obj_pos].get_x();
		int diff_y = m_start_pos[1] - my_Objects[obj_pos].get_y();
		my_Objects[obj_pos].set_x(e.getX() - diff_x);
		my_Objects[obj_pos].set_y(e.getY() - y - diff_y);
		my_Objects[obj_pos].set_port();
		int[] lines_moved = new int[line_count];
		int moved_count = 0;
		for(int line = 0; line < line_count; line++) {
			if(my_Line_Objects[line].get_obj_link()[0][0] == obj_pos || my_Line_Objects[line].get_obj_link()[1][0] == obj_pos) {
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
			if(obj_pos == pos[0]) {
				int[] new_start_pos = my_Objects[obj_pos].get_port()[port[0]];
				my_Line_Objects[lines_moved[line]].set_start_end(new_start_pos, origin_start_end[1]);
			}
			else{
				int[] new_end_pos = my_Objects[obj_pos].get_port()[port[1]];
				my_Line_Objects[lines_moved[line]].set_start_end(origin_start_end[0], new_end_pos);
			}
		}
	}

	public Composite[] get_groups() {
		return groups;
	}
	public void set_groups(int idx, Composite g) {
		if(idx != -1) groups[idx] = g;
	}
	public int[] get_group_usage() {
		return group_usage;
	}
	public void set_group_usage(int idx, int val) {
		if(idx != -1) group_usage[idx] = val;
	}
	public int get_GROUP_NUM() {
		return GROUP_NUM;
	}
	public Integer[] get_obj_in_selected() {
		List<Integer> list = new ArrayList<Integer>();
		Integer[] output;
		for(int i = 0; i < obj_count; i++) {
			if(my_Objects[i].is_selected()) list.add(i);
		}
		output = new Integer[list.size()];
		for(int i = 0; i < list.size(); i++) {
			output[i] = list.get(i);
		}
		return output;
	}
	private int obj_count = 0, line_count = 0, x, y;
	private int GROUP_NUM = 100;
	private int top, top_pos;
	private List<Integer> obj_in_range;
	private int[] m_start_pos = new int[2];
	private int[] m_pressed_pos = new int[2];
	private boolean pressed = false;
	private int[] group_usage = new int[GROUP_NUM];
	private Composite[] groups = new Composite[GROUP_NUM];
}
