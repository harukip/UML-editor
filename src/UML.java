import java.awt.*;
import javax.swing.*;



public class UML {
	public static void main(String[] args) {
		// Java window setup
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = (int)(tk.getScreenSize().getWidth() * 0.5);
		int ySize = (int)(tk.getScreenSize().getHeight() * 0.7);
		int button_height = (int)(xSize*0.1);
		
		JFrame window = new JFrame("UML Editor"); 
		window.setSize(xSize, ySize);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container cp = window.getContentPane();
		cp.setLayout(null);
		
		JMenu file = new JMenu("File");

		Canvas canvas = new Canvas(xSize, ySize, button_height, 0);
		cp.add(canvas);

		Task_Bar task_bar = new Task_Bar(canvas, cp, button_height);
		canvas.setMode(task_bar.getMode());
		
		JMenu edit = new JMenu("Edit");
		
		Menu menu = new Menu(canvas);
		JMenuItem group = new GroupMenu(menu);
		JMenuItem ungroup = new UngroupMenu(menu);
		JMenuItem change_name = new ChangeName(menu);
		
		edit.add(group);
		edit.add(ungroup);
		edit.add(change_name);
		
		menu.add(file);
		menu.add(edit);
		window.setJMenuBar(menu);
		
		window.setVisible(true);
	}
}
