package microSoft;

import java.util.Arrays;
import java.util.Scanner;

public class ShorteningSequence {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int  N = in.nextInt(); //总数
		int  M = in.nextInt(); //层
		int  K = in.nextInt();//叶子数
		int[][] level_nodes=new int[M][];
		int[][] parent=new int[M][];
		for (int i=0;i<M;i++)
			level_nodes[i]=new int[in.nextInt()];
		
		for (int i=0;i<M;i++)
			for (int j=0;j<level_nodes[i].length;j++)
				level_nodes[i][j]=in.nextInt();  //第i层的第j个node
		
		int[] leaves=new int[K];
		for (int i=0;i<K;i++)
			leaves[i]=in.nextInt();
		
		int[][] matrix =new int[K][K];
		for (int i=0;i<K;i++)
			for (int j=0;j<K;j++)
				matrix[i][j]=in.nextInt();
		for (int i=0;i<M;i++)
			parent[i]=new int[level_nodes[i].length];
		
		for (int i=M-1;i>=0;i--)
		{ int upper=0;
			for (int j=0;j<parent[i].length;j++)
			{	
			if (j==0) parent[i][j]=level_nodes[i-1][0];
			
			else {
				int cur=level_nodes[i][j]; 
				int m=0;
			    for (;m<K;m++)
			    	if(leaves[m]==cur)
			    		break;
			    if(matrix[m][m-1]==2)
			    {
			    	parent[i][j]=parent[i][j-1];
			    }
			    else if(matrix[m][m-1]==4)
			    {
			    	upper++;
			    	parent[i][j]=level_nodes[i-1][upper];
			    }
			}
			}
		}
	}
}
