import java.awt.Color;
import java.awt.Graphics;


public class Basic_Object {
	public void set_depth(int n) {
		this.depth = n;
	}
	public void set_x(int n) {
		this.x = n;
	}
	public void set_y(int n) {
		this.y = n;
	}
	public void set_type(int n) {
		this.type = n;
	}
	public int get_depth() {
		return this.depth;
	}
	public int get_x() {
		return this.x;
	}
	public int get_y() {
		return this.y;
	}
	public int get_type() {
		return this.type;
	}
	public void set_port() {
		int current_x = this.get_x(), current_y = this.get_y(), count = 0;
		int[] pos = {
				current_x+(int)(obj_width/2)-2, current_y, 
				current_x+obj_width, current_y+(int)(obj_height/2)-2,
				current_x+(int)(obj_width/2)-2, current_y+obj_height,
				current_x, current_y+(int)(obj_height/2)-2
		};
		for(int port_num = 0; port_num < 4; port_num++) {
			port[port_num][0] = pos[count];
			count += 1;
			port[port_num][1] = pos[count];
			count += 1;
		}
	}
	public int[][] get_port() {
		return port;
	}
	public int get_width() {
		return this.obj_width;
	}
	public int get_height() {
		return obj_height;
	}
	public void set_width(int n) {
		this.obj_width = n;
	}
	public void set_height(int n) {
		this.obj_height = n;
	}
	public void draw(Graphics g) {}
	public boolean is_inside(int x, int y) {
		if(
				x >= this.x && x <= (this.x+this.obj_width) &&
				y >= this.y && y <= (this.y+this.obj_height)
				) {
			return true;
		}
		return false;
	}
	public boolean is_in_range(int[] start_pos, int[] end_pos) {
		if(this.get_x() >= start_pos[0] && this.get_y() >= start_pos[1]) {
			if(this.get_x()+this.obj_width <= end_pos[0] && this.get_y()+this.obj_height <= end_pos[1])
				return true;
		}
		return false;
	}
	public void set_selected(boolean b) {
		selected = b;
	}
	public boolean is_selected() {
		return selected;
	}
	public void draw_name(Graphics g) {
		g.drawString(this.name, this.get_x()+(int)(obj_width*0.25), this.get_y()+25);
	}
	public void set_name(String s) {
		this.name = s;
	}
	private String name = "";
	private boolean selected = false;
	private int[][]port = new int[4][2];
	private int depth, x, y, type, obj_width = 100, obj_height = 150;
	
	public static class Class_Object extends Basic_Object{
		public Class_Object() {
			this.set_type(4);
		}
		@Override
		public void draw(Graphics g) {
			// TODO Auto-generated method stub
			
			super.draw(g);
			g.setColor(Color.white);
			g.fillRect(this.get_x(), this.get_y(), this.get_width(), this.get_height());
			g.setColor(Color.black);
			g.drawRect(this.get_x(), this.get_y(), this.get_width(), this.get_height());
			g.drawRect(this.get_x(), this.get_y()+(int)(this.get_height()*0.33), this.get_width(), (int)(this.get_height()*0.33));
			this.draw_name(g);
			if(this.is_selected()) {
				int[] shift_x = {0, 0, 0, -5};
				int[] shift_y = {-5, 0, 0, 0};
				for(int port_num = 0; port_num < 4; port_num++) {
					g.fillRect(this.get_port()[port_num][0]+shift_x[port_num], this.get_port()[port_num][1]+shift_y[port_num], 5, 5);
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
			g.setColor(Color.white);
			g.fillOval(this.get_x(), this.get_y(), this.get_width(), this.get_height());
			g.setColor(Color.black);
			g.drawOval(this.get_x(), this.get_y(), this.get_width(), this.get_height());
			this.draw_name(g);
			if(this.is_selected()) {
				int[] shift_x = {0, 0, 0, -5};
				int[] shift_y = {-5, 0, 0, 0};
				for(int port_num = 0; port_num < 4; port_num++) {
					g.fillRect(this.get_port()[port_num][0]+shift_x[port_num], this.get_port()[port_num][1]+shift_y[port_num], 5, 5);
				}
			}
		}
	}
}
