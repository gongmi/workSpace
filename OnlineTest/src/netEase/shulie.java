package netEase;

import java.util.Scanner;
//state[i][j]��ʾ����״̬�ռ䣬
// ����i(1<=i<=n)��ʾ���еĳ��ȣ�
// j(1<=j<=k)��ʾ���г���Ϊi��������j��β��
//        ���ƹ�ϵ�У�state[i][j] += state[i-1][m] (1<=m<=k, ����(m,j)�Ǹ��Ϸ�������)��
// ����ֱ�Ӱ��յ��ƹ�ϵ��������forѭ���ᳬʱ��
// Ϊ�˿����Ƚ�����Ϊi-1�ĺϷ��������(��Ϊsum)��
// Ȼ��������г���Ϊi��ÿһ��j��
// ������г���Ϊi-1ʱ�Ƿ������и�������Ϊinvalid��,
//�� ��� p * j== q
//˵�� dp[i-1][q]��invalid

// ����state[i][j] = sum - invalid��
// �㷨��ʱ�临�Ӷȴ��ΪO(nkloglogk)
public class shulie {

    static final int mod = 1000000007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int res = count(n, k);
        System.out.println(res);
        scanner.close();
    }

    public static int count(int n, int k) {
        int[][] state = new int[n + 1][k + 1];

        state[0][1] = 1;

        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = 1; j <= k; j++) {
                sum = (sum + state[i - 1][j]) % mod;
            }
            for (int j = 1; j <= k; j++) {
                int invalid = 0;
                int p = 2;
                while (p * j <= k) {
                    invalid = (invalid + state[i - 1][p * j]) % mod;
                    p++;
                }
                state[i][j] = (sum - invalid + mod) % mod;
            }
        }

        int sum = 0;
        for (int i = 1; i <= k; i++) {
            sum = (sum + state[n][i]) % mod;
        }
        return sum;
    }
}