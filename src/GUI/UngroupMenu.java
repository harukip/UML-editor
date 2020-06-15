package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JMenuItem;

import shapes.Shape;

public class UngroupMenu extends JMenuItem implements ActionListener{

	private Menu menu;
	
	public UngroupMenu(Menu m) {
		menu = m;
		this.setText("Ungroup");
		this.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Canvas canvas = menu.getCanvas();
		Vector<Shape> objs = canvas.getObjects();
		int select_count = 0, group_index = -1;
		for(int i = 0; i < objs.size(); i++) {
			if(objs.elementAt(i).isselect()) {
				select_count += 1;
				if(objs.elementAt(i).gettype().equals("Composite")) {
					group_index = i;
				}
			}
		}
		if(select_count == 1 && group_index != -1) {
			Vector<Shape> childs = objs.elementAt(group_index).getchilds();
			for(Shape o:childs) {
				canvas.addobj(o);
			}
			objs.removeElementAt(group_index);
		}
	}
}
