package beautyprog;
//看似是图论，实际是数论。
//关键点1：基站  “欧几里得距离的平方”  优先级远大于  “ 曼哈顿距离”  
//只需考虑欧几里德距离平方最小的坐标即可

//关键点2：欧几里德距离平方，x轴y轴可分离讨论。
//变成两个 “一维上到各点欧几里德平方和最小的点” 因为：
//∑[(Xn-X0)2+(Yn-Y0)2]=∑(Xn-X0)2+∑(Yn-Y0)2

//关键点3：“一维上到各点欧几里德平方和最小的点”
//=∑（xn-x0）2=Σ(Xn)2 +n(X0)2 - 2X0 ∑（Xn）最小

//问题变成，求二元一次方程组最小解的横坐标 求导就可以算出最佳的点的位置 即：
//X0= ∑（Xn）/n; Y0= ∑（Yn）/n;是向下取整了的
//（因为要求 “通讯基站仅必须建立在格点上” 
//所以需要对最近的四个点进行判定(x+y)(x,y+1)(x+1,y)(x+1,y+1)）

import java.util.Scanner;
 
public class BaseStation {
 
	static long cost=0;
	static long maxll=9223372036854775806L;//长整型数的最大一个数

	static long totalAx=0; //所有A 即用户的x坐标的sum 即∑Xn
	static long totalAxSquare=0; //所有A 即用户的x坐标的sum 即∑（Xn）2
	static long totalAy=0; //所有A 即用户的x坐标的sum 即∑Yn
	static long totalAySquare=0;//所有A 即用户的y坐标的平方的sum 即∑（Yn）2
	static long[] bx; //所有B 通讯公司的x坐标 组成的数组
	static long[] by;//所有B 通讯公司的y坐标 组成的数组
	static int N,M,A,B; //x轴总长度 y轴总长度 用户 通讯公司
	
	//计算所有用户到基站X0欧几里得距离的平方的和=∑[(Xn-X0)2+(Yn-Y0)2]
	
	
	
	   
	static long cal(long totalSquare,long total,long x,long num)
	
	
	{    //      =Σ(Xn)2     +n(X)2 -2∑(Xn)* X 
	    long  ans=totalSquare+num*x*x-2*total*x;
	    return ans;
	}
	
	//计算总距离的函数
	static long  calAll(long x,long y)           
	{
	    long ans=maxll;
	    for(int i=0;i<B;i++)
	    {//最小的曼哈顿距离的通讯公司  的曼哈顿距离
	       ans=Math.min(ans,Math.abs(bx[i]-x)+Math.abs(by[i]-y));    
	    }
	    //                 ∑（Xn）2     ∑Xn
	    return ans+cal(totalAxSquare,totalAx,x,A)+cal(totalAySquare,totalAy,y,A);   
	}
	
	 public static void main(String[] args) {
		 Scanner in = new Scanner(System.in);
		 int T = in.nextInt();//读取第一个数 即有多少数据组数  例子为2组
		   in.nextLine();//这句不明白 为什么要加？
		    int caseNum = 1;
		    while(T-->0){ // 读取String行
		    	
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
		            totalAx+=temp;   //所有A 即用户的x坐标的sum 即∑Xn
		            totalAxSquare+=temp*temp;
		           temp=in.nextInt();
		            totalAy+=temp;   //所有A 即用户的y坐标的sum 即∑Yn
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
		        x=totalAx/A;     //所有A 即用户的x坐标的sum/n 即∑Xn/n  
		        y=totalAy/A;     //所有A 即用户的y坐标的sum/n 即∑Yn/n
		        
		        //所以需要对最近的四个点进行判定 
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

