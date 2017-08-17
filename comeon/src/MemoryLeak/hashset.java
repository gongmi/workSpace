package MemoryLeak;


import java.util.HashSet;
import java.util.Set;

public class hashset {

    public static void main(String[] args) {
        Set<Person> set = new HashSet<>();
        Person p1 = new Person("��ɮ", "pwd1", 25);
        Person p2 = new Person("�����", "pwd2", 26);
        Person p3 = new Person("��˽�", "pwd3", 27);
        set.add(p1);
        set.add(p2);
        set.add(p3);
        System.out.println("�ܹ���:" + set.size() + " ��Ԫ��!");
        //�޸�p3������,��ʱp3Ԫ�ض�Ӧ��hashcodeֵ�����ı�
        p3.setAge(2);


        //��ʱremove����������ڴ�й©
        boolean res = set.remove(p3);
        System.out.println(res);


        //������ӣ���Ȼ��ӳɹ�
        res = set.add(p3);
        System.out.println(res);


        //������ܹ���:4 ��Ԫ��!
        System.out.println("�ܹ���:" + set.size() + " ��Ԫ��!");

        for (Person person : set) {
            System.out.println(person);
        }
    }

}

class Person {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (age != person.age) return false;
        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        return pwd != null ? pwd.equals(person.pwd) : person.pwd == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (pwd != null ? pwd.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }

    String name;
    String pwd;
    int age;

    public Person(String name, String pwd, int age) {
        this.name = name;
        this.pwd = pwd;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
