package chapter9;
/**
 * Ϊ�˶������ִ���������ļ�����<br>
 * ��defineClass�������ų�����ֻ���ⲿ��ʽ���õ�ʱ��Ż�ʹ�õ�loadByte����
 * �����������ʱ����Ȼ����ԭ�е�˫��ί�ɹ���ʹ��loadClass�������������
 *
 * @author zzm
 */
public class HotSwapClassLoader extends ClassLoader {

    public HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }

    public Class loadByte(byte[] classByte) {
        return defineClass(null, classByte, 0, classByte.length);
    }

}

