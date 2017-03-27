package beautyprog;
//������ͼ�ۣ�ʵ�������ۡ�
//�ؼ���1����վ  ��ŷ����þ����ƽ����  ���ȼ�Զ����  �� �����پ��롱  
//ֻ�迼��ŷ����¾���ƽ����С�����꼴��

//�ؼ���2��ŷ����¾���ƽ����x��y��ɷ������ۡ�
//������� ��һά�ϵ�����ŷ�����ƽ������С�ĵ㡱 ��Ϊ��
//��[(Xn-X0)2+(Yn-Y0)2]=��(Xn-X0)2+��(Yn-Y0)2

//�ؼ���3����һά�ϵ�����ŷ�����ƽ������С�ĵ㡱
//=�ƣ�xn-x0��2=��(Xn)2 +n(X0)2 - 2X0 �ƣ�Xn����С

//�����ɣ����Ԫһ�η�������С��ĺ����� �󵼾Ϳ��������ѵĵ��λ�� ����
//X0= �ƣ�Xn��/n; Y0= �ƣ�Yn��/n;������ȡ���˵�
//����ΪҪ�� ��ͨѶ��վ�����뽨���ڸ���ϡ� 
//������Ҫ��������ĸ�������ж�(x+y)(x,y+1)(x+1,y)(x+1,y+1)��

import java.util.Scanner;
 
public class BaseStation {
 
	static long cost=0;
	static long maxll=9223372036854775806L;//�������������һ����

	static long totalAx=0; //����A ���û���x�����sum ����Xn
	static long totalAxSquare=0; //����A ���û���x�����sum ���ƣ�Xn��2
	static long totalAy=0; //����A ���û���x�����sum ����Yn
	static long totalAySquare=0;//����A ���û���y�����ƽ����sum ���ƣ�Yn��2
	static long[] bx; //����B ͨѶ��˾��x���� ��ɵ�����
	static long[] by;//����B ͨѶ��˾��y���� ��ɵ�����
	static int N,M,A,B; //x���ܳ��� y���ܳ��� �û� ͨѶ��˾
	
	//���������û�����վX0ŷ����þ����ƽ���ĺ�=��[(Xn-X0)2+(Yn-Y0)2]
	
	
	
	   
	static long cal(long totalSquare,long total,long x,long num)
	
	
	{    //      =��(Xn)2     +n(X)2 -2��(Xn)* X 
	    long  ans=totalSquare+num*x*x-2*total*x;
	    return ans;
	}
	
	//�����ܾ���ĺ���
	static long  calAll(long x,long y)           
	{
	    long ans=maxll;
	    for(int i=0;i<B;i++)
	    {//��С�������پ����ͨѶ��˾  �������پ���
	       ans=Math.min(ans,Math.abs(bx[i]-x)+Math.abs(by[i]-y));    
	    }
	    //                 �ƣ�Xn��2     ��Xn
	    return ans+cal(totalAxSquare,totalAx,x,A)+cal(totalAySquare,totalAy,y,A);   
	}
	
	 public static void main(String[] args) {
		 Scanner in = new Scanner(System.in);
		 int T = in.nextInt();//��ȡ��һ���� ���ж�����������  ����Ϊ2��
		   in.nextLine();//��䲻���� ΪʲôҪ�ӣ�
		    int caseNum = 1;
		    while(T-->0){ // ��ȡString��
		    	
		        N= in.nextInt();
		        M= in.nextInt();
		        A= in.nextInt();
		        B= in.nextInt();
		        in.nextLine();
		    	bx=new long[B];
		    	by=new long[B];
		        totalAx=0;
		        totalAxSquare=0;
		        totalAy=0;
		        totalAySquare=0;
		        
		        long  ans=maxll;
		        long  temp=0;
		        for(int i=0;i<A;i++)      
		        {
		           temp=in.nextInt();
		            totalAx+=temp;   //����A ���û���x�����sum ����Xn
		            totalAxSquare+=temp*temp;
		           temp=in.nextInt();
		            totalAy+=temp;   //����A ���û���y�����sum ����Yn
		            totalAySquare+=temp*temp;
		            in.nextLine();  
		        }
		        for(int i=0;i<B;i++)
		        {
		        	 temp=in.nextInt();
		            bx[i]=temp;
		            temp=in.nextInt();
		            by[i]=temp;
		            in.nextLine();  
		        }
		        long  x,y;
		        x=totalAx/A;     //����A ���û���x�����sum/n ����Xn/n  
		        y=totalAy/A;     //����A ���û���y�����sum/n ����Yn/n
		        
		        //������Ҫ��������ĸ�������ж� 
		        ans=Math.min(ans,calAll(x,y+1));
		        ans=Math.min(ans,calAll(x+1,y));
		        ans=Math.min(ans,calAll(x+1,y+1));
		        ans=Math.min(ans,calAll(x,y));
		        String res = "Case #"+caseNum+": "+ans;
		        System.out.println(res);
		        
		        caseNum++;
		      }
		      in.close();
  
	}
}

