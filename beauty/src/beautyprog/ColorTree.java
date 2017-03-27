package beautyprog;

import java.util.Arrays;
import java.util.Scanner;

public class ColorTree {
	static class edge  
	{  
	 public   int to, nx;
	};  
	 int max=1000;
	 edge[] es=new edge[max*2];//�߼�  
	 int[] st=new int[max];	 
	 int[][]  cs=new int[max][max];//cs[i][j]��ʾ���iΪ������ɫΪj�Ķ��ӽ��ĸ���  
	 int[] fa=new int[max],color=new int[max];//fa[i]��ʾ���i�ĸ���㣬color[i]��ʾ���i����ɫ  
	 int n;
	 int q;
	 int ans; 
	 int en;//en��ʾ�ߵĸ�����st[x]�������ͷ���  
	  
	 void d__add(int x, int y)  
	{  
	    edge e =new edge();
	    e.to = y;  
	    e.nx = st[x];  
	    es[++en] = e;  
	    st[x] = en;  
	}  
	 void add(int x, int y)//�ӱ߲���  
	{  
	    d__add(x, y);  
	    d__add(y, x);  
	}  
	  
	 void dfs(int x)//�޸���ת��Ϊ��xΪ�����и���  
	{  
	    int i, tot = 0;  
	    for (i = st[x];i!=0 ; i = es[i].nx)   
	    if (es[i].to != fa[x])  
	    {  
	        fa[es[i].to] = x;  
	        tot++;  
	        dfs(es[i].to);  
	    }  
	    cs[x][0] = tot;//���ӽ��ĸ���  
	   
	}  
	 void change(int x, int y)//�����x����ɫ�޸�Ϊy  
	{  
	    if (color[x] == color[fa[x]]) ans++;//�����޸ĺ��ӽ�����ɫ�᲻ͬ��Ԥ�ȼ�1  
	    ans += cs[x][color[x]];//�ȼ�������ԭ����ɫ�Ķ��ӽ��ĸ���  
	    if (fa[x]!=0)//���x�ĸ������ڣ�����fa[x]���ӽ�����  
	    {  
	        cs[fa[x]][color[x]]--;//�������ӽ������ɫΪcolor[x]�ļ���һ��  
	        cs[fa[x]][y]++;//��ɫΪy������һ��  
	    }  
	    color[x] = y;//�޸���ɫ  
	    if (color[x] == color[fa[x]]) ans--;//����޸ĺ����ɫ�͸�������ɫһ�£������һ  
	    ans -= cs[x][color[x]];///��ȥ����Ŀǰ��ɫ�Ķ��ӽ��ĸ���  
	}  
	 void solve(int cas,Scanner in)  
	{  
	    int i, a, b;  
	   
	   n= in.nextInt();  
	   in.nextLine(); 
       en = 0;

Arrays.fill(st,0);


	    for (i = 1; i<n; i++)  
	    {   a= in.nextInt();
	        b= in.nextInt();
	        in.nextLine();
	        add(a, b);  
	    }
	    for (i = 1; i<max; i++)  
	  Arrays.fill(cs[i],0);
	    Arrays.fill(color,0);	
	    fa[1] = 0;
        color[0] = -10000097;  
	    dfs(1); ans = 1;  
	    q= in.nextInt();
        in.nextLine(); 
        String res = "Case #"+cas+":";
        System.out.println(res);
	    for (i = 0; i<q; i++)  
	    {  
	    	 a= in.nextInt(); 
	        if (a == 1)
{  	             System.out.println(ans);	          
} 
	        else  
	        {  
	        	a= in.nextInt();
	            b= in.nextInt();
	        	 change(a, b);  
	        } 
	        in.nextLine();
	    }  
	}  
	 public static void main(String[] args) {  
		 ColorTree ct=new ColorTree();
		 Scanner in = new Scanner(System.in);
		 int T = in.nextInt();//��ȡ��һ���� ���ж�����������  ����Ϊ2��
		   in.nextLine(); 
		
	 	    for (int i = 1; i <= T; i++)
	 	    	
	 	    
	 	    	ct.solve(i,in);  
	 
	
}
}
