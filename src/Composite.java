import java.util.ArrayList;
import java.util.List;

public class Composite{
	 public Composite(List<Integer> obj_ids, int idx) {
		// TODO Auto-generated constructor stub
		 this.data = obj_ids;
		 this.id = idx;
	}
	 public List<Integer> getList(){
		 return data;
	 }
	 public void print_element() {
		 for(Integer i:data) {
			 System.out.print((int)i+" ");
		 }
	 }
	 public int get_parent_id() {
		return parent_id;
	}
	 public void set_parent_id(int i) {
		 parent_id = i;
	 }
	 public void set_id(int i) {
		id = i;
	}
	 public int get_id() {
		 return id;
	 }
	 public boolean find_obj_group(int obj_id) {
		 return data.contains((Integer)(obj_id));
	 }
	 
	 public int get_data_length() {
		return data.size();
	}
	 
	 private int parent_id = -1;
	 private int id = -1;
	 private List<Integer> data = new ArrayList<Integer>();
}
