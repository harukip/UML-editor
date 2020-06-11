import java.awt.Point;
import java.awt.event.MouseEvent;

public class Use_case_mode extends Mode {

	Use_case_mode(Canvas c) {
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
