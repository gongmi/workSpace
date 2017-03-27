package beautyprog;

import java.util.Arrays;
import java.util.Scanner;

public class ColorTree {
	static class edge  
	{  
	 public   int to, nx;
	};  
	 int max=1000;
	 edge[] es=new edge[max*2];//边集  
	 int[] st=new int[max];	 
	 int[][]  cs=new int[max][max];//cs[i][j]表示结点i为根且颜色为j的儿子结点的个数  
	 int[] fa=new int[max],color=new int[max];//fa[i]表示结点i的父结点，color[i]表示结点i的颜色  
	 int n;
	 int q;
	 int ans; 
	 int en;//en表示边的个数，st[x]是链表的头结点  
	  
	 void d__add(int x, int y)  
	{  
	    edge e =new edge();
	    e.to = y;  
	    e.nx = st[x];  
	    es[++en] = e;  
	    st[x] = en;  
	}  
	 void add(int x, int y)//加边操作  
	{  
	    d__add(x, y);  
	    d__add(y, x);  
	}  
	  
	 void dfs(int x)//无根树转化为以x为根的有根树  
	{  
	    int i, tot = 0;  
	    for (i = st[x];i!=0 ; i = es[i].nx)   
	    if (es[i].to != fa[x])  
	    {  
	        fa[es[i].to] = x;  
	        tot++;  
	        dfs(es[i].to);  
	    }  
	    cs[x][0] = tot;//儿子结点的个数  
	   
	}  
	 void change(int x, int y)//将结点x的颜色修改为y  
	{  
	    if (color[x] == color[fa[x]]) ans++;//假设修改后父子结点的颜色会不同，预先加1  
	    ans += cs[x][color[x]];//先加上所有原来颜色的儿子结点的个数  
	    if (fa[x]!=0)//如果x的父结点存在，更新fa[x]的子结点情况  
	    {  
	        cs[fa[x]][color[x]]--;//父结点的子结点中颜色为color[x]的减少一个  
	        cs[fa[x]][y]++;//颜色为y的增加一个  
	    }  
	    color[x] = y;//修改颜色  
	    if (color[x] == color[fa[x]]) ans--;//如果修改后的颜色和父结点的颜色一致，结果减一  
	    ans -= cs[x][color[x]];///减去所有目前颜色的儿子结点的个数  
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
		 int T = in.nextInt();//读取第一个数 即有多少数据组数  例子为2组
		   in.nextLine(); 
		
	 	    for (int i = 1; i <= T; i++)
	 	    	
	 	    
	 	    	ct.solve(i,in);  
	 
	
}
}
