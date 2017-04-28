package huawei;

import java.util.ArrayList;  
import java.util.List;  
import java.util.Scanner;  
//��Ŀ����������5�������ID���������ȼ�����ʼ����ʱ�����������ʱ�䣬
//���е��ȹ���֧����ռ�������ȼ�ֵ�ߵ��������ռ�������е����ȼ�ֵ�͵�����
//������ǰ200�룬����ĵ��ȹ���
//����������������Ϣ�����ʽΪ��[����ID.�������㼶.����ʼ����ʱ��.��������ʱ��]��
//����������֮��ʹ�á�|�����룻
//5�����������IDΪ1-5���������ȼ���ΧΪ0-200.
//�������ӣ�[1.80.1.10]|[2.20.11.15]|[3.50.21.10]|[4.120.31.10]|[5.100.41.10]
//������ӣ�0.1|1.10|2.10|3.10|4.10|5.10|2.5|0.144
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
