package beautyprog;
import java.util.Scanner;
 
public class B_W {
 
 int n;
 int m;
int[] black_x;
int[] black_y;
int[] white_x;
int[] white_y;

//���ʹ�ĺ���
public void get(Scanner in){
 	
    n= in.nextInt();
    m= in.nextInt();
    in.nextLine();
   black_x=new int[n];
   black_y=new int[n];
   white_x=new int[m];
   white_y=new int[m];
 for (int i=0;i<n;i++)
 {
	 black_x[i]=in.nextInt();
 black_y[i]=in.nextInt();
    in.nextLine();}
 
for (int i=0;i<m;i++)
 {
	white_x[i]=in.nextInt();
 white_y[i]=in.nextInt();
    in.nextLine();}
cal();

   ans;
    System.out.println(res);
 
}
public int cal(){
	for (int i=1;i<n;i++)
		
	return m;
	
}
//�ѵ�i���ڵ�͵�j���ڵ�Ƚ� ���Ƿ���Եĺ��� �����Է���1 ����Է���0��
public int compare(int i,int j)
{
	if (black_x[i]<=white_x[j]||black_y[i]<=white_y[j])
	{white_x[j]=-1;
	 white_y[j]=-1;
		return 1;
		}
	else return 0;
}
	 public static void main(String[] args) {
		 Scanner in = new Scanner(System.in);
		 int T = in.nextInt();//��ȡ��һ���� ���ж�����������  ����Ϊ2��
		   in.nextLine();//��䲻���� ΪʲôҪ�ӣ�
		
		    while(T-->0){ // ��ȡString��
		    	B_W bw=	new B_W();
		    	bw.get(in);
		      }
		      in.close();
  
	}
}

