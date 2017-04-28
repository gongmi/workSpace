package huawei;

import java.util.ArrayList;  
import java.util.List;  
import java.util.Scanner;  
//题目描述：输入5个任务的ID，任务优先级，开始运行时间和任务运行时间，
//其中调度过程支持抢占，即优先级值高的任务可抢占正在运行的优先级值低的任务，
//最后输出前200秒，任务的调度过程
//输入描述：任务信息输入格式为：[任务ID.任务优秀级.任务开始运行时间.任务运行时长]；
//任务与任务之间使用“|”隔离；
//5个任务的任务ID为1-5；任务优先级范围为0-200.
//输入例子：[1.80.1.10]|[2.20.11.15]|[3.50.21.10]|[4.120.31.10]|[5.100.41.10]
//输出例子：0.1|1.10|2.10|3.10|4.10|5.10|2.5|0.144
public class diaodu {  
    public static void main(String[] args) {  
        String line = new Scanner(System.in).nextLine();  
        String[] strTasks = line.split("\\|");  
        List<Task> lst = new ArrayList<Task>();  
        for(int i = 0; i < strTasks.length; i++) {  
            String[] cs = strTasks[i].substring(1, strTasks[i].length() - 1).split("\\.");  
            int id = Integer.parseInt(cs[0]);  
            int p = Integer.parseInt(cs[1]);  
            int start = Integer.parseInt(cs[2]);  
            int allTime = Integer.parseInt(cs[3]);  
            lst.add(new Task(id, p, start, allTime));  
        }  
          
        List<Integer> queue = new ArrayList<Integer>();  
        for(int i = 0; i < 200; i++) {  
            Task currentTask = null;  
            for(Task task : lst) {  
                if(task.start <= i && task.left > 0) {  
                    if(currentTask == null || task.p > currentTask.p)  
                        currentTask = task;  
                }  
            }  
            if(currentTask == null)  
                queue.add(0);  
            else {  
                queue.add(currentTask.id);  
                currentTask.left--;  
            }  
        }  
//      System.out.println(lst);  
//      System.out.println(queue);  
        int current = queue.get(0), cnt = 1;  
        List<Result> results = new ArrayList<Result>();  
        for(int i = 1; i < queue.size(); i++) {  
            if(queue.get(i) != current) {  
                results.add(new Result(current, cnt));  
                current = queue.get(i);  
                cnt = 1;  
            } else cnt++;  
        }  
        results.add(new Result(current, cnt));  
        boolean first = true;  
        for(Result r : results) {  
            if(first) {  
                System.out.print(r);  
                first = false;  
            } else {  
                System.out.print("|" + r);  
            }  
        }  
        System.out.println();  
    }  
}  
// [1.80.1.10]|[2.20.11.15]|[3.50.21.10]|[4.120.31.10]|[5.100.41.10]  
class Task {  
    int id;  
    int allTime;  
    int left;  
    int start;  
    int p;  
    Task(int id, int p, int start, int allTime) {  
        this.id = id;  
        this.p = p;  
        this.start = start;  
        this.allTime = allTime;  
        this.left = allTime;  
    }  
    public String toString() {  
        return "[id=" + id + ", allTime=" + allTime + ", left=" + left + ", start=" + start + ", p=" + p + "]";  
    }  
}  
class Result {  
    int x, y;  
    Result(int x, int y) {  
        this.x = x;  
        this.y = y;  
    }  
    public String toString() {  
        return x + "." + y;  
    }  
}
