package GUI;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import shapes.RectObject;

public class ChangeNameWindow extends JFrame {
	private Canvas canvas;
	private JTextField name_Field = new JTextField();
	private JButton okButton = new ok();
	private JButton cancelButton = new cancel();
	private Container cp;
	private int xSize = 400, ySize = 200, object_index;
	
	public ChangeNameWindow(Canvas c, int i) {
		canvas = c;
		object_index = i;
		this.setTitle("Change Object Name");
		cp = this.getContentPane();
		cp.setLayout(null);
		this.setSize(xSize, ySize);
		name_Field.setBounds((int)(xSize*0.1), (int)(ySize*0.2), (int)(xSize*0.8), (int)(ySize*0.2));
		okButton.setBounds((int)(xSize*0.1), (int)(ySize*0.2)*2, (int)(xSize*0.4), (int)(ySize*0.2));
		cancelButton.setBounds((int)(xSize*0.1)*5, (int)(ySize*0.2)*2, (int)(xSize*0.4), (int)(ySize*0.2));
		cp.add(name_Field);
		cp.add(okButton);
		cp.add(cancelButton);
		this.setVisible(true);
	}
	
	public void close() {this.dispose();}
	
	public String getname() {return name_Field.getText();}
	
	public class ok extends JButton implements ActionListener{
		public ok() {
			this.setText("OK");
			this.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			((RectObject)canvas.getObjects().elementAt(object_index)).setname(getname());
			canvas.repaint();
			close();
		}
	}
	
	public class cancel extends JButton implements ActionListener{
		public cancel() {
			this.setText("Cancel");
			this.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			close();
		}
	}
}
