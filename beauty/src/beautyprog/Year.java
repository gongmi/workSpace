package beautyprog;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Year {
  class Date{ //一个Date类
    public Date(int day, int month, int year) {
      this.day = day;
      this.month = month;
      this.year = year;
    }
    int day,month,year;
  }
  
  //把输入的月份的名称与 1 2 3 对应 方便于以后判断开始年和结束年是否包含了二月29日
  String month[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November" , "December"};
  Map<String,Integer> mapMonth = new HashMap<>();
  private void initMonth(){
    for(int i=0;i<month.length;i++){
      mapMonth.put(month[i], i+1);
    }
  }
  
  
  //计算start年和end年中有多少二月29
  private int coutNum(String start,String end){
    Date st = date(start);  //从读取的String行中 得到年 月 日 然后转换成Date类
    Date ed = date(end);    //从读取的String行中 得到年 月 日 然后转换成Date类
    int res = 0;
  //计算start年和end年中有多少闰年
    //从距离start年最近的可能闰年（4的倍数） 到距离end年最近的可能闰年（4的倍数）遍历 距离为4
    for(int i=((st.year-1)/4+1)*4;i<=ed.year/4*4;i+=4){   
    	
    	 if(i%400==0){  //！！！！！！为了提高计算速度，我们知道400-800年中有97个闰年，
    		            //所以当我们遍历到一个可以除尽400的年时候 我们直接跳到400*n年之后 提高计算速度
    		            // 否则在大数据：2000 ≤ year ≤ 2×109时会出现TLE：用户程序运行时间超过题目的限制
    	        res += (ed.year/400-i/400)*97;
    	        res++;
    	        i = ed.year/400*400;//跳到距离end年最近的400年的倍数那一年 再遍历
    	      }
    	 else{//如果不是能被400整除 那么用最正常的方法判断是否是闰年
        if(isRun(i))res++;  }
    }
    if(isRun(st.year)){//如果开始年是闰年 看看包没包括二月十九
      if(st.month>2){
        res--;
      }
    }
    if(isRun(ed.year)){//如果结束年是闰年 看看包没包括二月十九
      if(ed.month<2){
        res--;
      }else if(ed.month==2){
        if(ed.day < 29) res--;
      }
    }
    return res;
  }
  
  //判断某一年是否是闰年的函数
  private boolean isRun(int year){
    if((year%4==0&&year%100!=0)||(year%400==0)) return true;
    return false;
  }
  
  //从读取的行中 得到年 月 日 的函数
  private Date date(String str){
    String s[] = str.split(" ");  //字符串按照“ ”空格分成三个数组 如：January 12, 2012
    int month = mapMonth.get(s[0]); //s[0]=January
    int year = Integer.parseInt(s[2]);//s[2]=2012
    int day = Integer.parseInt(s[1].substring(0,s[1].indexOf(",")));//s[12]=12
    return new Date(day, month, year);//然后放到Date类里面 好管理
  }
  
  public static void main(String args[]){
    Scanner sc = new Scanner(System.in);
    int num = sc.nextInt();//读取第一个数 即有多少对年份 例子为3个
    String s=sc.nextLine();//这句不明白 为什么要加？
    Year Y = new Year(); //创建一个实例 为了调用函数
    Y.initMonth(); // 月份和数字对应 没什么意思的
    int caseNum = 1;
    while(num-->0){ //一对一对的读取String行
      String start = sc.nextLine();
      String end = sc.nextLine();
      String res = "Case #"+caseNum+": "+Y.coutNum(start, end);
      System.out.println(res);
      caseNum++;
    }
    sc.close();
  }
}