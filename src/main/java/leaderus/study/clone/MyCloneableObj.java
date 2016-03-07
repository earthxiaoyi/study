package leaderus.study.clone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class MyCloneableObj implements Cloneable,Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String,MyInfo> myInfos;
    private String myId;
    private Date validDate;
    
	public Map<String, MyInfo> getMyInfos() {
		return myInfos;
	}
	public void setMyInfos(Map<String, MyInfo> myInfos) {
		this.myInfos = myInfos;
	}
	public String getMyId() {
		return myId;
	}
	public void setMyId(String myId) {
		this.myId = myId;
	}
	public Date getValidDate() {
		return validDate;
	}
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		//将对象写到流里
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		Object readObject = null;
		try {
			oos = new ObjectOutputStream(bos);
			oos.writeObject(this);
			 //从流里读回来
			ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bis);;
			readObject = ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return readObject;
	}
	
	@Override
	public boolean equals(Object obj) {
		MyCloneableObj cloneObj = (MyCloneableObj)obj;
		Map<String, MyInfo> myInfos2 = cloneObj.getMyInfos();
		Iterator<String> it = myInfos2.keySet().iterator();
		while(it.hasNext()){
			String key2 = it.next();
			if(this.myInfos.containsKey(key2)){
				MyInfo myInfo = this.myInfos.get(key2);
				MyInfo myInfo2 = myInfos2.get(key2);
				if(!myInfo.equals(myInfo2)){
					return false;
				}
			}else{
				return false;
			}
		}
		if(this.validDate.getTime()!=cloneObj.getValidDate().getTime()){
			return false;
		}
		if(!cloneObj.getMyId().equals(this.myId))
			return false;
		return true;
	}
	
}

class MyInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Set<String> enabledFeature;
    private boolean enabled;
     
	public Set<String> getEnabledFeature() {
		return enabledFeature;
	}
	public void setEnabledFeature(Set<String> enabledFeature) {
		this.enabledFeature = enabledFeature;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	@Override
	public boolean equals(Object obj) {
		MyInfo myInfo2 = (MyInfo)obj;
		Set<String> enabledFeature2 = myInfo2.getEnabledFeature();
		
		if(this.enabled!=myInfo2.enabled){
			return false;
		}
		
		if(this.enabledFeature==null && enabledFeature2==null){
			return true;
		}
		
		if(!this.enabledFeature.containsAll(enabledFeature2)){
			return false;
		}
		
		return true;
	}
    
}