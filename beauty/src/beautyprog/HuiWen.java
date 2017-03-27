package beautyprog;

import java.util.Scanner;
//解题思路：本题利用容斥原理解决。本题是一个计数问题，要求找一个串中有多少个回文子串。
//本题的难点就在于子串的选取可以是不连续的。因此应该想到运用递推法来解决。
//定义dp(i,j)表示串[i,j]之间的回文子串的个数，那么可以得到如下递推式：
//dp(i,j)=dp(i+1,j)+dp(i,j-1)+res (j-i>0)

//其中res要分情况讨论：
//(1)如果s[i]==s[j]那么中间重叠部分dp(i+1,j-1)不需要减去，
//只需要再加上一个空串的情况即可，即res=1
//(2)反之，根据容斥原理，需要减去中间重叠计算的部分，即res=-dp(i+1,j-1)。
//当i==j时，即只有一个字符，那就是1,
//这样便不难通过递推式求出最终的答案了，
//最终的答案是dp(0,len)。len表示输入的串的长度，起点从0开始。
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
		 int num = in.nextInt();//读取第一个数 即有多少待测字符串  例子为5个
		    String s=in.nextLine();//这句不明白 为什么要加？
		    int caseNum = 1;
		    while(num-->0){ // 读取String行
		        String ss = in.nextLine();
		        String res = "Case #"+caseNum+": "+cal(ss);
		        System.out.println(res);
		        
		        caseNum++;
		      }
		      in.close();
	}
}
