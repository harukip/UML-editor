package GUI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.JPanel;
import modes.Mode;

import shapes.Shape;


public class Canvas extends JPanel implements MouseListener{

	private Mode mode;
	private Vector<Shape> objects = new Vector<Shape>();
	private int depth = 0;
	
	
	public Canvas(int xSize, int ySize, int x, int y) {
		this.setBounds(x, y, xSize, ySize);
		this.setBackground(Color.white);
		this.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		mode.mouseClicked(e);
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mode.mousePressed(e);
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mode.mouseReleased(e);
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	public void paint(Graphics g) {
		super.paint(g);
		for(Shape o:objects) {
			o.draw(g);
		}
	}
	
	public Vector<Shape> getObjects(){return objects;}
	
	public void addobj(Shape o) {
		objects.add(o);
		depth += 1;
	}
	
	public int get_depth() {return depth;}
	
	public Mode getMode() {return mode;}

	public void setMode(Mode m) {mode = m;}
}
