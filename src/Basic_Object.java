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
				current_x+(int)(obj_width/2), current_y, 
				current_x+obj_width, current_y+(int)(obj_height/2),
				current_x+(int)(obj_width/2), current_y+obj_height,
				current_x, current_y+(int)(obj_height/2)
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
	public boolean is_in_range(int x, int y) {
		if(
				x >= this.x && x <= (this.x+this.obj_width) &&
				y >= this.y && y <= (this.y+this.obj_height)
				) {
			return true;
		}
		return false;
	}
	private int[][]port = new int[4][2];
	private int depth, x, y, type, obj_width = 100, obj_height = 150;
}
