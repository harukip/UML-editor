import java.awt.Graphics;
import java.awt.Point;


public class Line_Object extends Shape{
	private Port[] ports = new Port[2];
	
	public Line_Object(Port s, Port d) {
		ports[0] = s;
		ports[1] = d;
	}

	@Override
	public String gettype() {
		// TODO Auto-generated method stub
		return "Line";
	}
	
	public void draw(Graphics g) {}
	
	public Port[] getport() {return ports;}
	
	public Point rotate_shift(Point origin_point, Point alt_point, double angle){
		Point output = new Point();
		int x_diff = (int)(alt_point.getX() - origin_point.getX());
		int y_diff = (int)(alt_point.getY() - origin_point.getY());
		output.setLocation(
				(int)(Math.cos(angle)*x_diff - Math.sin(angle)*y_diff)+alt_point.getX(), 
				(int)(Math.sin(angle)*x_diff + Math.cos(angle)*y_diff)+alt_point.getY()
		);
		return output;
	}
	
	public Point find_unit(Point source, Point dest){
		Point output = new Point();
		int x_diff = (int)(dest.getX() - source.getX());
		int y_diff = (int)(dest.getY() - source.getY());
		double times = (double)(Math.sqrt(400/(Math.pow(x_diff, 2)+Math.pow(y_diff, 2))));
		output.setLocation(
				dest.getX() - (int)(x_diff*times), 
				dest.getY() - (int)(y_diff*times)
		); 
		return output;
	}
	
	public int[][] rotate_shape(int[][] input, double angle){
		int[][] output = new int[input.length][input[0].length];
		for(int i=0; i<input[0].length; i++) {
			output[0][i] = (int)(Math.cos(angle)*input[0][i] - Math.sin(angle)*input[1][i]+ports[1].getposition().getX());
			output[1][i] = (int)(Math.sin(angle)*input[0][i] + Math.cos(angle)*input[1][i]+ports[1].getposition().getY());
		}
		return output;
	}
}
