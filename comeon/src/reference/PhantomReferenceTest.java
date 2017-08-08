package reference;

import java.lang.ref.*;

/**
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class PhantomReferenceTest {
	public static void main(String[] args) throws Exception {
		String str = new String("���Java����");
		// ����һ�����ö���
		ReferenceQueue<String> rq = new ReferenceQueue<String>();
		// ����һ�������ã��ô����������õ�"���Java����"�ַ���
		PhantomReference<String> pr = new PhantomReference<String>(str, rq);
		// �ж�str���ú�"���Java����"�ַ���֮�������
		str = null;
		// ȡ�������������õĶ��󣬲�����ͨ�������û�ȡ�����õĶ������Դ˴����null
		System.out.println(pr.get()); // ��
		// ǿ����������
		System.gc();
		System.runFinalization();
		// ��������֮�������ý����������ö�����
		// ȡ�����ö��������Ƚ�������е�������pr���бȽ�
		System.out.println(rq.poll() == pr); // ��
	}
}
