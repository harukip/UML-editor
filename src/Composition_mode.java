import java.awt.Point;
import java.awt.event.MouseEvent;

public class Composition_mode extends Mode {

	Composition_mode(Canvas c) {
		super(c);
		c.setMode(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public String getmode() {
		return "Composition_mode";
	}
	
	@Override
	public Shape newobj(Point p) {
		return new CompositionLine(
				((RectObject)topShape_1).getPorts().get(
						((RectObject)topShape_1).closetoport(start)), 
				((RectObject)topShape_2).getPorts().get(
						((RectObject)topShape_2).closetoport(end)));
	}

}
