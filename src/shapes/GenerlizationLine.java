package shapes;
import java.awt.Graphics;

public class GenerlizationLine extends Line_Object{
	public GenerlizationLine(Port s, Port d) {
		super(s, d);
		// TODO Auto-generated constructor stub
	}

	public void draw(Graphics g) {
		Port source = getport()[0], dest = getport()[1];
		int[][] shift = {{0, 20, 0, -20}, {-20, 0, 20, 0}};
		int[] angle = {180, 270, 0, 90};
		int[][] triangle = {{10, -10, 0}, {20, 20, 0}};
		int[][] output_triangle = this.rotate_shape(triangle, Math.toRadians(angle[dest.getportnum()]));
		g.drawPolygon(output_triangle[0], output_triangle[1], 3);
		g.drawLine(
			(int)(source.getposition().getX()), (int)(source.getposition().getY()),
			(int)(dest.getposition().getX()+shift[0][dest.getportnum()]), (int)(dest.getposition().getY()+shift[1][dest.getportnum()])
		);
	}
}
