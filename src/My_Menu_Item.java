import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
						int[] usage = c.get_group_usage();
						Composite[] group_list = c.get_groups();
						Integer[] indexs_for_group = c.get_obj_in_range();
						List<Integer> finded_group = new ArrayList<Integer>(), alone_obj = new ArrayList<Integer>();
						for(Integer obj_id:indexs_for_group) {
							boolean found = false;
							int max_group_len = -1, max_group = -1;
							for(int g_pos = 0; g_pos < c.get_GROUP_NUM(); g_pos++) {
								if(usage[g_pos] == 0) continue;
								Composite g = group_list[g_pos];
								if(g.find_obj_group(obj_id)) {
									found = true;
									int group_id = g.get_id();
									int data_len = g.get_data_length();
									if(data_len > max_group_len) {
										max_group_len = data_len;
										max_group = group_id;
									}
								}
							}
							if(!found) {
								alone_obj.add(obj_id);
							}
							else {
								if(!finded_group.contains((Integer)max_group)) {
									finded_group.add((Integer)max_group);
								}
								group_list[max_group].set_parent_id(max_group);
							}
						}
						List<Integer> new_list = new ArrayList<Integer>();
						for(Integer group_id:finded_group) {
							for(Integer i:group_list[(int)group_id].getList()) {
								new_list.add(i);
							}
						}
						for(Integer obj:alone_obj) {
							new_list.add(obj);
						}

						int empty_idx = 0, GROUP_NUM = c.get_GROUP_NUM();
						int[] group_usage = c.get_group_usage();
						for(int idx = empty_idx; idx < GROUP_NUM; idx++) {
							if(group_usage[idx] == 0) {
								empty_idx = idx;
								break;
							}
						}
						Composite group_obj = new Composite(new_list, empty_idx);
						c.set_groups(empty_idx, group_obj);
						c.set_group_usage(empty_idx, 1);
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
						int[] usage = c.get_group_usage();
						Composite[] group_list = c.get_groups();
						Integer[] indexs_for_ungroup = c.get_obj_in_selected();
						List<Integer> used_pos = new ArrayList<Integer>();
						if(indexs_for_ungroup.length > 0) {
							int max_group_len = -1, max_group = -1;
							Integer obj_id = indexs_for_ungroup[0];
							for(int group_pos = 0; group_pos < c.get_GROUP_NUM(); group_pos++) {
								if(usage[group_pos] == 0) continue;
								Composite g = group_list[group_pos];
								if(!used_pos.contains((Integer)group_pos)) {
									used_pos.add((Integer)group_pos);
								}
								if(g.find_obj_group(obj_id)) {
									List<Integer> list = group_list[group_pos].getList(); 
									if(list.size() > max_group_len) {
										max_group_len = list.size();
										max_group = group_pos;
									}
								}
							}
							if(used_pos.size() > 0) {
								for(Integer pos:used_pos) {
									if(group_list[(int)pos].get_parent_id() == max_group) {
										group_list[(int)pos].set_parent_id(-1);
									}
								}
							}
							c.set_groups(max_group, null);
							c.set_group_usage(max_group, 0);
						}
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
