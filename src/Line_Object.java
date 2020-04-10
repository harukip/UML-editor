import java.awt.Graphics;

public class Line_Object {
	public void set_type(int n) {
		this.type = n;
	}
	public int get_type() {
		return this.type;
	}
	public void draw(Graphics g) {}
	
	public void set_start_end(int[] start, int[] end) {
		start_end[0] = start;
		start_end[1] = end;
	}
	public int[][] get_start_end() {
		return start_end;
	}
	public int[][] get_obj_link() {
		return obj_link;
	}
	public void set_obj_link(int s, int p1, int d, int p2) {
		int[] shift = {5, 0, 0, 5};
		int[] start_point = this.get_start_end()[0];
		int[] end_point = this.get_start_end()[1];
		for(int i = 0; i < 2; i++) {
			start_point[i] += shift[p1];
			end_point[i] += shift[p2];
		}
		this.set_start_end(start_point, end_point);
		obj_link[0][0] = s;
		obj_link[0][1] = p1;
		obj_link[1][0] = d;
		obj_link[1][1] = p2;
	}
	
	public int[][] rotate_shape(int[][] input, double angle){
		int[][] output = new int[input.length][input[0].length];
		for(int i=0; i<input[0].length; i++) {
			output[0][i] = (int)(Math.cos(angle)*input[0][i] - Math.sin(angle)*input[1][i])+get_start_end()[1][0];
			output[1][i] = (int)(Math.sin(angle)*input[0][i] + Math.cos(angle)*input[1][i])+get_start_end()[1][1];
		}
		return output;
	}
	
	public int[] find_unit(int[] source, int[] dest){
		int[] output = new int[2];
		int x_diff = dest[0] - source[0];
		int y_diff = dest[1] - source[1];
		double times = (double)(Math.sqrt(400/(Math.pow(x_diff, 2)+Math.pow(y_diff, 2))));
		output[0] = dest[0] - (int)(x_diff*times);
		output[1] = dest[1] - (int)(y_diff*times);
		return output;
	}
	
	public int[] rotate_shift(int[] origin_point, int[] alt_point, double angle){
		int[] output = new int[2];
		int x_diff = alt_point[0] - origin_point[0];
		int y_diff = alt_point[1] - origin_point[1];
		output[0] = (int)(Math.cos(angle)*x_diff - Math.sin(angle)*y_diff)+alt_point[0];
		output[1] = (int)(Math.sin(angle)*x_diff + Math.cos(angle)*y_diff)+alt_point[1];
		return output;
	}
	
	private int[][]start_end = new int[2][2];
	private int type;
	private int[][] obj_link = new int[2][2]; // source/dest obj num, obj port num
}
