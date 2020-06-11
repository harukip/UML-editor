import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JMenuItem;

public class GroupMenu extends JMenuItem implements ActionListener{
	private Menu menu;
	public GroupMenu(Menu m) {
		menu = m;
		this.setText("Group");
		this.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Canvas canvas = menu.getCanvas();
		if(canvas.getMode().getmode().equals("Select_mode")) {
			Vector<Shape> objs = canvas.getObjects();
			List<Integer> group_indexs = new ArrayList<Integer>();
			int seleted_count = 0;
			for(int i = 0; i < objs.size(); i++) {
				if(objs.elementAt(i).isselect()) {
					seleted_count += 1;
					group_indexs.add(i);
				}
			}
			if(seleted_count > 1) {
				Shape c = new Composite();
				int remove_count = 0;
				for(int i:group_indexs) {
					((Composite)c).addchild(objs.elementAt(i));
				}
				for(int i:group_indexs) {
					objs.removeElementAt(i-remove_count);
					remove_count += 1;
				}
				canvas.addobj(c);
			}
		}
	}
}