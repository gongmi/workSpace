package beautyprog;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Year {
  class Date{ //һ��Date��
    public Date(int day, int month, int year) {
      this.day = day;
      this.month = month;
      this.year = year;
    }
    int day,month,year;
  }
  
  //��������·ݵ������� 1 2 3 ��Ӧ �������Ժ��жϿ�ʼ��ͽ������Ƿ�����˶���29��
  String month[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November" , "December"};
  Map<String,Integer> mapMonth = new HashMap<>();
  private void initMonth(){
    for(int i=0;i<month.length;i++){
      mapMonth.put(month[i], i+1);
    }
  }
  
  
  //����start���end�����ж��ٶ���29
  private int coutNum(String start,String end){
    Date st = date(start);  //�Ӷ�ȡ��String���� �õ��� �� �� Ȼ��ת����Date��
    Date ed = date(end);    //�Ӷ�ȡ��String���� �õ��� �� �� Ȼ��ת����Date��
    int res = 0;
  //����start���end�����ж�������
    //�Ӿ���start������Ŀ������꣨4�ı����� ������end������Ŀ������꣨4�ı��������� ����Ϊ4
    for(int i=((st.year-1)/4+1)*4;i<=ed.year/4*4;i+=4){   
    	
    	 if(i%400==0){  //������������Ϊ����߼����ٶȣ�����֪��400-800������97�����꣬
    		            //���Ե����Ǳ�����һ�����Գ���400����ʱ�� ����ֱ������400*n��֮�� ��߼����ٶ�
    		            // �����ڴ����ݣ�2000 �� year �� 2��109ʱ�����TLE���û���������ʱ�䳬����Ŀ������
    	        res += (ed.year/400-i/400)*97;
    	        res++;
    	        i = ed.year/400*400;//��������end�������400��ı�����һ�� �ٱ���
    	      }
    	 else{//��������ܱ�400���� ��ô���������ķ����ж��Ƿ�������
        if(isRun(i))res++;  }
    }
    if(isRun(st.year)){//�����ʼ�������� ������û��������ʮ��
      if(st.month>2){
        res--;
      }
    }
    if(isRun(ed.year)){//��������������� ������û��������ʮ��
      if(ed.month<2){
        res--;
      }else if(ed.month==2){
        if(ed.day < 29) res--;
      }
    }
    return res;
  }
  
  //�ж�ĳһ���Ƿ�������ĺ���
  private boolean isRun(int year){
    if((year%4==0&&year%100!=0)||(year%400==0)) return true;
    return false;
  }
  
  //�Ӷ�ȡ������ �õ��� �� �� �ĺ���
  private Date date(String str){
    String s[] = str.split(" ");  //�ַ������ա� ���ո�ֳ��������� �磺January 12, 2012
    int month = mapMonth.get(s[0]); //s[0]=January
    int year = Integer.parseInt(s[2]);//s[2]=2012
    int day = Integer.parseInt(s[1].substring(0,s[1].indexOf(",")));//s[12]=12
    return new Date(day, month, year);//Ȼ��ŵ�Date������ �ù���
  }
  
  public static void main(String args[]){
    Scanner sc = new Scanner(System.in);
    int num = sc.nextInt();//��ȡ��һ���� ���ж��ٶ���� ����Ϊ3��
    String s=sc.nextLine();//��䲻���� ΪʲôҪ�ӣ�
    Year Y = new Year(); //����һ��ʵ�� Ϊ�˵��ú���
    Y.initMonth(); // �·ݺ����ֶ�Ӧ ûʲô��˼��
    int caseNum = 1;
    while(num-->0){ //һ��һ�ԵĶ�ȡString��
      String start = sc.nextLine();
      String end = sc.nextLine();
      String res = "Case #"+caseNum+": "+Y.coutNum(start, end);
      System.out.println(res);
      caseNum++;
    }
    sc.close();
  }
}