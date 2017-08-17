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
//        k����Ҫ���k������
        for (int k = 2; k <= n; k++) {
            int sum = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int tmpSum = 0;
//                    �������е� ����poll������������

                    PriorityQueue<Integer> heap = new PriorityQueue<>(k, (o1, o2) -> (o2 - o1));
//                    ѡ��һ����a
//                    ����������Ŀ��ܵ�����ľ��� ����tempSum
                    for (int a = 0; a < n; a++) {
                        int distance = Math.abs(x[a] - x[i]) + Math.abs(y[a] - y[j]);
                        tmpSum += distance;

                        heap.add(distance);
//                        heap�б���k�� a����Щ���ܵ�
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
