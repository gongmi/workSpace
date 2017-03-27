
public class Driver {
	private String name;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void drive(Vihecle v) { 
		v.go(new Address("东北"));// 这样不好！！ 不能指定目的地 要站在使用者的角度设计 uml中的usecase
	}
	
	
	
	public void drive(Vihecle v, Address dest) {//这样很好  
		v.go(dest);//隐藏和封装
	}
}
