package netEase;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class qizi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++)
            x[i] = sc.nextInt();
        for (int i = 0; i < n; i++)
            y[i] = sc.nextInt();
        sc.close();
        StringBuilder sb = new StringBuilder();
        sb.append(0);
//        k代表要汇聚k个棋子
        for (int k = 2; k <= n; k++) {
            int sum = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int tmpSum = 0;
//                    倒序排列的 最先poll出来的是最大的

                    PriorityQueue<Integer> heap = new PriorityQueue<>(k, (o1, o2) -> (o2 - o1));
//                    选定一个点a
//                    看它到另外的可能的坐标的距离 放在tempSum
                    for (int a = 0; a < n; a++) {
                        int distance = Math.abs(x[a] - x[i]) + Math.abs(y[a] - y[j]);
                        tmpSum += distance;

                        heap.add(distance);
//                        heap中保存k个 a到那些可能的
                        if (heap.size() > k) {
                            tmpSum -= heap.poll();
                        }
                    }
                    sum = Math.min(sum, tmpSum);
                }
            }
            sb.append(" " + sum);
        }
        System.out.println(sb.toString());
    }
}
}
