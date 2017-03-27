package io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest
{
	public static void main(String[] args)
	{
		try(
			RandomAccessFile raf =  new RandomAccessFile("d:\\GM\\char.txt", "rw"))
		{
			// 获取RandomAccessFile对象文件指针的位置，初始位置是0
			System.out.println("RandomAccessFile的文件指针的初始位置："
				+ raf.getFilePointer());
			// 移动raf的文件记录指针的位置
			raf.seek(10);
			byte[] bbuf = new byte[1024];
			// 用于保存实际读取的字节数
			int hasRead = 0;
			// 使用循环来重复“取水”过程
			while ((hasRead = raf.read(bbuf)) > 0 )
			{
				// 取出“竹筒”中水滴（字节），将字节数组转换成字符串输入！
				System.out.print(new String(bbuf , 0 , hasRead ));
			}
			raf.seek(raf.length());
			raf.write("希望可以拿到好的offer\r\n".getBytes());
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
}
