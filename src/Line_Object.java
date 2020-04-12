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
		int[] start_point = this.get_start_end()[0];
		int[] end_point = this.get_start_end()[1];
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
}
