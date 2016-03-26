package leaderus.study1.clone;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class TestMyCloneableObj {
	
	public static void main(String[] args) throws CloneNotSupportedException {
		MyCloneableObj cloneObj = new MyCloneableObj();
		MyInfo myInfo = new MyInfo();
		HashMap<String, MyInfo> myInfos = new HashMap<String,MyInfo>();
		HashSet<String> hashSet = new HashSet<String>();
		hashSet.add("aaa");
		myInfo.setEnabled(true);
		myInfo.setEnabledFeature(hashSet);
		cloneObj.setMyId("1");
		cloneObj.setMyInfos(myInfos);
		cloneObj.setValidDate(new Date());
		
		MyCloneableObj obj = (MyCloneableObj)cloneObj.clone();
		System.out.println(cloneObj.equals(obj));
	}
	
}
