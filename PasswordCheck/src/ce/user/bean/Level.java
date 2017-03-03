package ce.user.bean;

import java.util.ArrayList;
import java.util.List;

public class Level {
	private List<Rule> list;
	private String levelValue;
	public Level(){
		list = new ArrayList<Rule>();
	}
	public List<Rule> getList() {
		return list;
	}
	public void setList(List<Rule> list) {
		this.list = list;
	}
	public String getLevelValue() {
		return levelValue;
	}
	public void setLevelValue(String levelValue) {
		this.levelValue = levelValue;
	}


	public void addRule(Rule rule){
		list.add(rule);
	}	
}
