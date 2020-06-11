import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class AssocLine extends Line_Object{
	public AssocLine(Port s, Port d) {
		super(s, d);
	}

	public void draw(Graphics g) {
		Port source = getport()[0], dest = getport()[1];
		List<Point> arrow_point = new ArrayList<Point>();
		int[] angle = {-30, 30};
		
		g.drawLine(
			(int)(source.getposition().getX()), (int)(source.getposition().getY()),
			(int)(dest.getposition().getX()), (int)(dest.getposition().getY())
		);
		
		for(int i = 0; i < 2; i++) {
			arrow_point.add(rotate_shift(dest.getposition(), find_unit(source.getposition(), dest.getposition()), Math.toRadians(angle[i])));
			g.drawLine(
				(int)(arrow_point.get(i).getX()), (int)(arrow_point.get(i).getY()), 
				(int)(dest.getposition().getX()), (int)(dest.getposition().getY()));
		}
	}
}
