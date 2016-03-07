package leaderus.study.clone;

public class Main {
	
	public static void main(String[] args) throws CloneNotSupportedException {
		Student s1 = new Student();
		s1.setId(1);
		s1.setName("zhangsan");
		Student s2 = (Student)s1.clone();
		
		System.out.println(s1.getId()+"==="+s1.getName());
		s1.setId(1);
		s1.setName("333");
		System.out.println(s1.getId()+"==="+s1.getName());
		System.out.println(s2.getId()+"==="+s2.getName());
		s2.setId(5);
		s2.setName("4444");
		System.out.println(s2.getId()+"==="+s2.getName());
		System.out.println(s1.getId()+"==="+s1.getName());
	}
	
}
