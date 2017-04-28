package huawei;
//СK��X��������۾�����ƽ����פ��5�����У�
//���Ҿ���Ҫ����1������2������3������4������6�����г��
//���������ִ������ʱ���ᵼ�¶�Ӧ���е����к������ɼ������ֹͣ
//�������ܴӸó��г�������������Ҳ���ܵ���ó��У���
//СKϣ��֪���������Ҫ��X���г���ʱ���������Y���г��ִ�������̵ķ���ʱ�估����·����
//ע�⣺���������м䲻�ɴ�ʱ������ʱ��Ĭ��ȡ1000.
//�����м�ķ���ʱ�����±���ʾ���Ӵ��д���ʼ�����У��Ӵ��д����յ���У�
//�����е�ֵ�����ʼ�����зɵ��յ��������ʱ�䣨��λ��Сʱ����
//M�����ɴע������߳��ǵ���ģ���A->B������B->A��������
//��1����1�ų��з��е�4�ų��л���5h����4�ų��зɵ�1�ų��л���2h
//��2����5�ų��з��е�3�ų��в��ɴ��3�ų��зɵ�5�ų��л���7h
//
//  1    2    3    4    5     6
//
//1  0h  2h  10h  5h  3h   M
//
//2  M   0h  12h   M   M   10h
//
//3  M   M    0h    M   7h  M
//
//4  2h  M    M     0h  2h  M
//
//5  4h  M    M     1h  0h  M
//
//6  3h  M    1h    M   2h  0h
//
//����������X��X��Ϊ1��2��3��4��6��
//���������У�Y��Ϊ0��1��2��3��4��5��6����Ϊ0ʱ����û�г��г��ִ���
import java.util.*;  

public class dawu {  
    private static int INF = 1000;  
  
    private static Integer[][] dist;  
    private static Integer[][] path;  
  
    private static List<Integer> result = new ArrayList<Integer>();  
//����  
    public static void printMatrix(Integer[][] matrix) {  
        for (int i = 0; i < matrix.length; i++) {  
            for (int j = 0; j < matrix.length; j++)  
                System.out.print(matrix[i][j] + " ");  
            System.out.println();  
        }  
    }  
//���������  
    private static void setFog(int[][] matrix, int city) {  
        for (int i = 0; i < matrix.length; i++) {  
            matrix[i][city] = matrix[city][i] = INF;  
        }  
    }  
  
    public static void main(String[] args) {  
  
        int size = 6;  
  
        int begin = 4;  
        Scanner scan = new Scanner(System.in);  
        int end = Integer.parseInt(scan.nextLine()) - 1;  
        int foggy = Integer.parseInt(scan.nextLine()) - 1;  
        scan.close();  
  
        int[][] matrix = { { 0, 2, 10, 5, 3, INF },  
                { INF, 0, 12, INF, INF, 10 }, { INF, INF, 0, INF, 7, INF },  
                { 2, INF, INF, 0, 2, INF }, { 4, INF, INF, 1, 0, INF },  
                { 3, INF, 1, INF, 2, 0 } };  
        init(size);  
        //û����  
        if (foggy != -1)  
            setFog(matrix, foggy);  
//���ø�������  
        floyd(matrix);  
  
        findPath(begin, end);  
        System.out.println(dist[begin][end]);  
        for (int i = 0; i < result.size(); i++)  
            result.set(i, result.get(i) + 1);  
        if (dist[begin][end] == INF)  
            result.removeAll(result);  
        System.out.println(result);  
    }  
//��path��������·��  
    public static void findPath(int i, int j) {  
        int ci = i, ccj = j;  
        while (path[i][j] != -1) {  
            int cj = path[i][j];  
            result.add(cj);  
            i = cj;  
        }  
        result.add(0, ci);  
        result.add(ccj);  
    }  
  
    public static void floyd(int[][] matrix) {  
        int size = matrix.length;  
        for (int i = 0; i < size; i++)  
            for (int j = 0; j < size; j++) {  
                path[i][j] = -1;  
                dist[i][j] = matrix[i][j];  
            }  
        for (int k = 0; k < size; k++) {  
            for (int i = 0; i < size; i++) {  
                for (int j = 0; j < size; j++) {  
                    if (dist[i][k] != INF && dist[k][j] != INF  
                            && dist[i][k] + dist[k][j] < dist[i][j]) {  
                        dist[i][j] = dist[i][k] + dist[k][j];  
                        path[i][j] = k;  
                    }  
                }  
            }  
        }  
    }  
//��ʼ����������  
    public static void init(int size) {  
        path = new Integer[size][size];  
        dist = new Integer[size][size];  
    }  
}  
