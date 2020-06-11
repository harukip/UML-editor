import java.awt.Point;
import java.awt.event.MouseEvent;

public class Generlization_mode extends Mode {

	Generlization_mode(Canvas c) {
		super(c);
		c.setMode(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public String getmode() {
		return "Generalization_mode";
	}

	@Override
	public Shape newobj(Point p) {
		return new GenerlizationLine(
				((RectObject)topShape_1).getPorts().get(
						((RectObject)topShape_1).closetoport(start)), 
				((RectObject)topShape_2).getPorts().get(
						((RectObject)topShape_2).closetoport(end)));
	}
}
