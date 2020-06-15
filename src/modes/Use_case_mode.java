package modes;
import java.awt.Point;
import java.awt.event.MouseEvent;

import GUI.Canvas;
import shapes.Shape;
import shapes.UseCase;

public class Use_case_mode extends Mode {

	public Use_case_mode(Canvas c) {
		super(c);
		c.setMode(this);
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public String getmode() {
		return "Use_case_mode";
	}

	@Override
	public Shape newobj(Point p) {
		return new UseCase(p, getCanvas().get_depth());
	}
}
