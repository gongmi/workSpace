package chapter3;

/**
 * VM������-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 
 * -XX:MaxTenuringThreshold=1  -XX:+PrintTenuringDistribution
 */
// -XX:MaxTenuringThreshold=15ʱʧ���� 
public class testTenuringThreshold1 {
	private static final int _1MB = 1024 * 1024;

	public static void main(String[] args) {
		byte[] allocation1, allocation2, allocation3;
		allocation1 = new byte[_1MB / 4];  // ʲôʱ����������������XX:MaxTenuringThreshold����
		allocation2 = new byte[4 * _1MB];
		//ִ���������ǰ ����eden��һ��8M���Ŀռ䲻���� ��˷�����һ��GC
//		��allocation1�Ž���survivor��   allocation1 �� ageΪ1
//		��allocation2�Ž��������
		allocation3 = new byte[4 * _1MB];
//		ִ������������� eden���� allocation3  survivor�� �� allocation1
		allocation3 = null;
		//ִ���������ǰ ����eden��һ��8M���Ŀռ䲻���� ��˷�����2��GC
//		survivor��   allocation1 �� ageΪ2 ����1 ��˷����������
//		����allocation3 = null; ����eden�е� new byte[4 * _1MB]��������
		allocation3 = new byte[4 * _1MB];
//		ִ������������� eden���� allocation3���µģ�  survivor�� û��
	}



}
