
public class Driver {
	private String name;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void drive(Vihecle v) { 
		v.go(new Address("����"));// �������ã��� ����ָ��Ŀ�ĵ� Ҫվ��ʹ���ߵĽǶ���� uml�е�usecase
	}
	
	
	
	public void drive(Vihecle v, Address dest) {//�����ܺ�  
		v.go(dest);//���غͷ�װ
	}
}
