package reference;

import java.lang.ref.*;

/**
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class WeakReferenceTest {
	public static void main(String[] args) throws Exception {
		String str = new String("���Java����");
		WeakReference<String> wr = new WeakReference<String>(str); // ��
		str = null; // ��
		// ȡ�������������õĶ���
		System.out.println(wr.get()); // ��
		// ǿ����������
		System.gc();
		System.runFinalization();
		// �ٴ�ȡ�������������õĶ��� Ȣ������  ��Ϊ������ �����ڴ��Ƿ��㹻 ���ᱻ����
		System.out.println(wr.get()); // ��
	}
}
