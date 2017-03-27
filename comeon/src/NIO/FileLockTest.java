package NIO;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
public class FileLockTest
{
	public static void main(String[] args)
		throws Exception
	{
		try(FileChannel channel = new FileOutputStream("d:\\GM\\FileChannelTest.txt").getChannel())
		{
			// ʹ�÷�����ʽ��ʽ��ָ���ļ�����
			FileLock lock = channel.tryLock();
			// ������ͣ10s
			Thread.sleep(10000);
			// �ͷ���
			lock.release();
		}
	}
}
