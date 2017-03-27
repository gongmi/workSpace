package beautyprog;

import java.util.Scanner;
//����˼·�����������ݳ�ԭ������������һ���������⣬Ҫ����һ�������ж��ٸ������Ӵ���
//������ѵ�������Ӵ���ѡȡ�����ǲ������ġ����Ӧ���뵽���õ��Ʒ��������
//����dp(i,j)��ʾ��[i,j]֮��Ļ����Ӵ��ĸ�������ô���Եõ����µ���ʽ��
//dp(i,j)=dp(i+1,j)+dp(i,j-1)+res (j-i>0)

//����resҪ��������ۣ�
//(1)���s[i]==s[j]��ô�м��ص�����dp(i+1,j-1)����Ҫ��ȥ��
//ֻ��Ҫ�ټ���һ���մ���������ɣ���res=1
//(2)��֮�������ݳ�ԭ����Ҫ��ȥ�м��ص�����Ĳ��֣���res=-dp(i+1,j-1)��
//��i==jʱ����ֻ��һ���ַ����Ǿ���1,
//�����㲻��ͨ������ʽ������յĴ��ˣ�
//���յĴ���dp(0,len)��len��ʾ����Ĵ��ĳ��ȣ�����0��ʼ��
public class HuiWen {

	public static long cal(String s) {

		int n = s.length();
		long[][] dp = new long[n][n];
		for (int end = 0; end < n; end++)
			for (int start = end; start >= 0; start--) {
				if (start == end)
					dp[start][end] = 1L;
				else {
					if (s.charAt(end) == s.charAt(start))
						dp[start][end] = dp[start + 1][end]
								+ dp[start][end - 1] + 1L;
					else
						dp[start][end] = dp[start + 1][end]
								+ dp[start][end - 1] - dp[start + 1][end - 1];
				}
			}
		return  dp[0][n - 1]%100007L;

	}

	public static void main(String[] args) {
		 Scanner in = new Scanner(System.in);
		 int num = in.nextInt();//��ȡ��һ���� ���ж��ٴ����ַ���  ����Ϊ5��
		    String s=in.nextLine();//��䲻���� ΪʲôҪ�ӣ�
		    int caseNum = 1;
		    while(num-->0){ // ��ȡString��
		        String ss = in.nextLine();
		        String res = "Case #"+caseNum+": "+cal(ss);
		        System.out.println(res);
		        
		        caseNum++;
		      }
		      in.close();
	}
}
