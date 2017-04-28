package huawei;
//小K是X区域的销售经理，他平常常驻“5”城市，
//并且经常要到“1”、“2”、“3”、“4”、“6”城市出差。
//当机场出现大雾情况时，会导致对应城市的所有航班的起飞及降落均停止
//（即不能从该城市出发，其他城市也不能到达该城市）。
//小K希望知道如果他需要到X城市出差时，如果遇到Y城市出现大雾，他最短的飞行时间及飞行路径。
//注意：当两个城市间不可达时，消耗时间默认取1000.
//各城市简的飞行时间如下表所示，加粗行代表始发城市，加粗列代表终点城市，
//矩阵中的值代表从始发城市飞到终点城市所耗时间（单位：小时），
//M代表不可达（注意飞行线程是单向的，即A->B不等于B->A），例如
//（1）从1号城市飞行到4号城市花费5h，从4号城市飞到1号城市花费2h
//（2）从5号城市飞行到3号城市不可达，从3号城市飞到5号城市花费7h
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
//输入出差城市X（X可为1、2、3、4、6）
//输入大雾城市（Y可为0、1、2、3、4、5、6，可为0时代表没有城市出现大雾）
import java.util.*;  

public class dawu {  
    private static int INF = 1000;  
  
    private static Integer[][] dist;  
    private static Integer[][] path;  
  
    private static List<Integer> result = new ArrayList<Integer>();  
//调试  
    public static void printMatrix(Integer[][] matrix) {  
        for (int i = 0; i < matrix.length; i++) {  
            for (int j = 0; j < matrix.length; j++)  
                System.out.print(matrix[i][j] + " ");  
            System.out.println();  
        }  
    }  
//设置雾城市  
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
        //没有雾  
        if (foggy != -1)  
            setFog(matrix, foggy);  
//调用弗洛伊德  
        floyd(matrix);  
  
        findPath(begin, end);  
        System.out.println(dist[begin][end]);  
        for (int i = 0; i < result.size(); i++)  
            result.set(i, result.get(i) + 1);  
        if (dist[begin][end] == INF)  
            result.removeAll(result);  
        System.out.println(result);  
    }  
//在path数组里找路径  
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
//初始化两个数组  
    public static void init(int size) {  
        path = new Integer[size][size];  
        dist = new Integer[size][size];  
    }  
}  
