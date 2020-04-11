import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JTextField;


public  class My_Menu_Item extends JMenuItem {
		public My_Menu_Item(My_Canvas c, String s) {
			// TODO Auto-generated constructor stub
			super(s);
		}
		
		public static class group extends My_Menu_Item {
			public group(My_Canvas c, String s) {
				// TODO Auto-generated constructor stub
				super(c, s);
				addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						System.out.println("Group");
						Object[] indexs_for_group = c.get_obj_in_range();
						c.set_groups(c.get_groups(), new Composite(indexs_for_group));
						for(Composite g:c.get_groups()) {
							g.print_element();
						}
					}
				});
			}
		}
		
		public static class ungroup extends My_Menu_Item{
			public ungroup(My_Canvas c, String s) {
				// TODO Auto-generated constructor stub
				super(c, s);
				addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						System.out.println("UnGroup");
						
					}
				});
			}
		}
		
		public static class change_name_menu extends My_Menu_Item{
			public change_name_menu(My_Canvas c, String s) {
				// TODO Auto-generated constructor stub
				super(c, s);
				addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						int[] selected_obj = new int[c.get_obj_count()];
						int selected_count = 0;
						for(int i = 0; i < c.get_obj_count(); i++) {
							if(c.get_my_Objects()[i].is_selected()) {
								selected_obj[selected_count] = i;
								selected_count += 1;
							}
						}
						if(selected_count == 1) {
							Toolkit tk = Toolkit.getDefaultToolkit();
							int xSize = (int)(tk.getScreenSize().getWidth() * 0.2);
							int ySize = (int)(tk.getScreenSize().getHeight() * 0.2);
							JFrame set_name_JFrame = new JFrame("Set new name");
							Container cp = set_name_JFrame.getContentPane();
							cp.setLayout(null);
							set_name_JFrame.setSize(xSize, ySize);
							set_name_JFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							JTextField name_Field = new JTextField();
							name_Field.setBounds((int)(xSize*0.1), (int)(ySize*0.2), (int)(xSize*0.8), (int)(ySize*0.2));
							JButton okButton = new JButton("OK");
							okButton.setBounds((int)(xSize*0.1), (int)(ySize*0.2)*2, (int)(xSize*0.4), (int)(ySize*0.2));
							JButton cancelButton = new JButton("Cancel");
							cancelButton.setBounds((int)(xSize*0.1)*5, (int)(ySize*0.2)*2, (int)(xSize*0.4), (int)(ySize*0.2));
							cp.add(name_Field);
							cp.add(okButton);
							cp.add(cancelButton);
							set_name_JFrame.setVisible(true);
							cancelButton.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									set_name_JFrame.dispose();
								}
							});
							okButton.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									c.get_my_Objects()[selected_obj[0]].set_name(name_Field.getText());
									c.repaint();
									set_name_JFrame.dispose();
								}
							});
						}
					}
				});
			}
		}
	}
