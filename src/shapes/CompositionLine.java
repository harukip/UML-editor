package shapes;
import java.awt.Graphics;

public class CompositionLine extends Line_Object{
	public CompositionLine(Port s, Port d) {
		super(s, d);
	}

	public void draw(Graphics g) {
		Port source = getport()[0], dest = getport()[1];
		int[][] shift = {{0, 20, 0, -20}, {-20, 0, 20, 0}};
		int[] angle = {180, 270, 0, 90};
		int[][] square = {{0, 10, 0, -10}, {0, 10, 20, 10}};
		int[][] output_triangle = this.rotate_shape(square, Math.toRadians(angle[dest.getportnum()]));
		g.drawLine(
			(int)(source.getposition().getX()), (int)(source.getposition().getY()),
			(int)(dest.getposition().getX()+shift[0][dest.getportnum()]), (int)(dest.getposition().getY()+shift[1][dest.getportnum()])
		);
		g.drawPolygon(output_triangle[0], output_triangle[1], 4);
	}
}
