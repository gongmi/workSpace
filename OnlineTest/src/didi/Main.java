package didi;

import java.util.*;
//��һ���Ϸ����ַ�����
//����ÿ�ν�������е�һ��������ɾȥ��
//�ٽ�����һ��������ɾȥ��
//ÿ��ɾȥ������б���Ϸ������ж����ַ��� 
//����1��
//Input:
//()()()()
//Output��
//1
//
//����2��
//Input
//(((())))()()
//Output:
//24
//�������Ǻ�����֪�����ģ��Ļ��ܸ��ӣ�
//�ڽ�һ��˼���ᷢ�ִ������߻��к�Ч�ԣ�
//��ǰ��ѡ�������Ż���������Ƿ�Ϸ���Ӱ�쵽��һ�ε�ѡ��Ҳ����˵ ���������ѡ��һ�������� ��ô����������ұߵ������� Ҫ˼��ѡ�ĸ� �������ǺϷ���
//��Ȼ�к�Ч�ԣ��Ǿ͵��������������� ���Ǵ����ұ�ѡ��һ�������� ���д��ұ��� ��һ��������ɾȥ  
//����֤�����ұ���ѡ����Ӱ�쵽��ߡ���֤���ԣ�
//( ( ) ( ) ( ( ) ) )
//1 2   2   2 3
//
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();

		long res = 1;
		int closedParenthesisCount = 0;

		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) == ')')
				closedParenthesisCount++;
			else if (s.charAt(i) == '(') {
				res *= closedParenthesisCount;
				closedParenthesisCount--;
			}
		}
		System.out.println(res);
	}
}
