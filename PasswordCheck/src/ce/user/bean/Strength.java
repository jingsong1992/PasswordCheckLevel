package ce.user.bean;

import java.util.ArrayList;
import java.util.List;

public class Strength {
	private List<Level> list;
	public Strength(){
		list = new ArrayList<Level>();
	}


	public List<Level> getList() {
		return list;
	}


	public void setList(List<Level> list) {
		this.list = list;
	}


	public void addLevel(Level level){
		list.add(level);
	}
}
