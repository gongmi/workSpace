package NIO;

import java.net.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;

public class NServer {
    private Selector selector = null;
    static final int PORT = 30000;
    private Charset charset = Charset.forName("UTF-8");

    public void init() throws IOException {
        selector = Selector.open();
        ServerSocketChannel server = ServerSocketChannel.open();
        InetSocketAddress isa = new InetSocketAddress("127.0.0.1", PORT);
        server.bind(isa);
        server.configureBlocking(false);
        server.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            for (SelectionKey sk : selector.selectedKeys()) {
                selector.selectedKeys().remove(sk);

                // ���sk��Ӧ��Channel�����ͻ��˵��������󣡣���������˵����ֻ������ServerSocketChannel
                if (sk.isAcceptable()) {
//                    System.out.println(sk.channel());
//                    System.out.println(sk.channel() == server);
//                  ���淵��true  ˵�� ֻ��server�ſ�����acceptable
//                    ����ڱ������� ����ֻ��ServerSocketChannel�ܽ���

                    SocketChannel sc = server.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                }
                // ���sk��Ӧ��Channel��������Ҫ��ȡ   ˵��ֻ����SocketChannel
                if (sk.isReadable())     // ��
                {
//                    System.out.println(sk.channel());
                    SocketChannel sc = (SocketChannel) sk.channel();
                    ByteBuffer buff = ByteBuffer.allocate(1024);
                    String content = "";
                    // ��ʼ��ȡ����
                    try {
                        while (sc.read(buff) > 0) {
                            buff.flip();
                            content += charset.decode(buff);
                        }
                        System.out.println("��ȡ�����ݣ�" + content);
                    }
                    // �����׽����sk��Ӧ��Channel�������쳣����������Channel
                    // ��Ӧ��Client���������⣬���Դ�Selector��ȡ��sk��ע��
                    catch (IOException ex) {
                        // ��Selector��ɾ��ָ����SelectionKey
                        sk.cancel();
                        if (sk.channel() != null) {
                            sk.channel().close();
                        }
                    }
                    // ���content�ĳ��ȴ���0����������Ϣ��Ϊ��
                    if (content.length() > 0) {
                        // ������selector��ע�������SelectionKey 1��ServerSocketChannel�� ��N��SocketChannel
                        System.out.println("���е�keyΪ��");
                        for (SelectionKey key : selector.keys()) {
                            System.out.println(key);
                            System.out.println(key.channel());
                            Channel targetChannel = key.channel();
                            if (targetChannel instanceof SocketChannel) {
                                SocketChannel dest = (SocketChannel) targetChannel;
                                dest.write(charset.encode(content));
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args)
            throws IOException {
        new NServer().init();
    }
}
