import java.util.ArrayList;
import java.util.List;

public class Composite{
	 public Composite(Object[] objs) {
		// TODO Auto-generated constructor stub
		 list = new ArrayList<Object>();
		 for(Object b : objs) {
			 list.add(b);
		 }	 
	}
	 public List<Object> getList(){
		 return list;
	 }
	 public void print_element() {
		 for(Object i:list)
			 System.out.print(i+" ");
	 }
	 private List<Object> list;
}
