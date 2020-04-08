import java.awt.*;
import javax.swing.*;

public class UML {

	public static void main(String[] args) {
		// Java window setup
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = (int)(tk.getScreenSize().getWidth() * 0.5);
		int ySize = (int)(tk.getScreenSize().getHeight() * 0.7);
		JFrame window = new JFrame("UML Editor"); 
		window.setSize(xSize, ySize);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container cp = window.getContentPane();
		cp.setLayout(null);
		
		JMenu file = new JMenu("File");
		JMenuItem one = new JMenuItem("one");
		file.add(one);
		
		JMenu edit = new JMenu("Edit");
		JMenuItem two = new JMenuItem("two");
		edit.add(two);
		
		JMenuBar menu = new JMenuBar();
		menu.add(file);
		menu.add(edit);
		window.setJMenuBar(menu);
		
		int button_height = (int)(xSize*0.1);
		
		JButton []buttons = new JButton[6];
		buttons[0] = new select_button(xSize, 0, button_height*0);
		buttons[1] = new asso_button(xSize, 0, button_height*1);
		buttons[2] = new gen_button(xSize, 0, button_height*2);
		buttons[3] = new com_button(xSize, 0, button_height*3);
		buttons[4] = new class_button(xSize, 0, button_height*4);
		buttons[5] = new use_class_button(xSize, 0, button_height*5);
		
		for(int i = 0; i < 6; i++) {
			cp.add(buttons[i]);
		}
		
		Canvas canvas = new Canvas(xSize, ySize, button_height, 0);
		cp.add(canvas);
		
		window.setVisible(true);
	}

}
