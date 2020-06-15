package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JMenuItem;

import shapes.Shape;

public class ChangeName extends JMenuItem implements ActionListener {
	private Menu menu;
	
	public ChangeName(Menu m) {
		menu = m;
		this.setText("Change Object Name");
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Canvas canvas = menu.getCanvas();
		Vector<Shape> objs = canvas.getObjects();
		int select_count = 0, obj_index = -1;
		for(int i = 0; i < objs.size(); i++) {
			if(objs.elementAt(i).isselect()) {
				select_count += 1;
				obj_index = i;
			}
		}
		if(select_count == 1) {
			if(objs.elementAt(obj_index).gettype().equals("RectObject")) {new ChangeNameWindow(canvas, obj_index);}
		}
	}
}
