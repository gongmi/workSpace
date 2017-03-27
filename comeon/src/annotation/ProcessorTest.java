package annotation;

import java.lang.reflect.*;

public class ProcessorTest {
	public static void process(String clazz) throws ClassNotFoundException {
		int passed = 0;
		int failed = 0;
		// ����clazz��Ӧ����������з���
		for (Method m : Class.forName(clazz).getMethods()) {
			// ����÷���ʹ����@Testable����
			if (m.isAnnotationPresent(Testable.class)) {
				try {
					// ����m����
					m.invoke(null);
					// ���Գɹ���passed��������1
					passed++;
				} catch (Exception ex) {
					System.out.println("����" + m + "����ʧ�ܣ��쳣��" + ex.getCause());
					// ���Գ����쳣��failed��������1
					failed++;
				}
			}
		}
		// ͳ�Ʋ��Խ��
		System.out.println("��������:" + (passed + failed) + "�����������У�\n" + "ʧ����:" + failed + "����\n"
				+ "�ɹ���:" + passed + "����");
	}
}
