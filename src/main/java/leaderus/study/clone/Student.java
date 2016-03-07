package leaderus.study.clone;

import java.util.Map;

public class Student implements Cloneable{
	private int id;
	private String name;
	private Map<String,Object> score;
	
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, Object> getScore() {
		return score;
	}
	public void setScore(Map<String, Object> score) {
		this.score = score;
	}
}
