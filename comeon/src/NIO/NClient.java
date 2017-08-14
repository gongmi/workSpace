package NIO;

import java.util.*;
import java.net.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;

public class NClient {
    private Selector selector = null;
    static final int PORT = 30000;
    private Charset charset = Charset.forName("UTF-8");
    private SocketChannel sc = null;

    public void init() throws IOException {
        selector = Selector.open();
        InetSocketAddress isa = new InetSocketAddress("127.0.0.1", PORT);
        sc = SocketChannel.open(isa);
        sc.configureBlocking(false);
        sc.register(selector, SelectionKey.OP_READ);


        new ClientThread().start();
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            sc.write(charset.encode(line));
        }
    }

    // 定义读取服务器数据的线程
    private class ClientThread extends Thread {
        public void run() {
            try {
                while (selector.select() > 0) {
                    for (SelectionKey sk : selector.selectedKeys()) {
//                        System.out.println(sk);
//                        System.out.println(sk.channel());
//                        这里其实sk一直都只有一个！！！
//                       sk.channel() 就是这个SocketChannel sc
//                        所以其实这里没有必要用selector的
                        selector.selectedKeys().remove(sk);
                        if (sk.isReadable()) {
                            SocketChannel sc = (SocketChannel) sk.channel();
                            ByteBuffer buff = ByteBuffer.allocate(1024);
                            String content = "";
                            while (sc.read(buff) > 0) {
                                sc.read(buff);
                                buff.flip();
                                content += charset.decode(buff);
                            }
                            System.out.println("聊天信息：" + content);
                        }
                    }
                    System.out.println("所有的key为：");//从头到尾其实只有一个！！！在这个例子中
                    for (SelectionKey key : selector.keys()) {
                        System.out.println(key);
                        System.out.println(key.channel());
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
            throws IOException {
        new NClient().init();
    }
}
