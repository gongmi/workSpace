package reference;

import java.lang.ref.*;

/**
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class SoftReferenceTest {
	public static void main(String[] args) throws Exception {
		String str = new String("���Java����");
		SoftReference<String> wr = new SoftReference<String>(str); // ��
		str = null; // ��
		// ȡ�������������õĶ���
		System.out.println(wr.get()); // ��
		// ǿ����������
		System.gc();
		System.runFinalization();
		// �ٴ�ȡ�������������õĶ��� ����ȡ�� ��Ϊ�����ڴ治���� ������������ö���
		System.out.println(wr.get()); // ��
	}
}
