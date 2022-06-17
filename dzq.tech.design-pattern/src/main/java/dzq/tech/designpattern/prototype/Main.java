package dzq.tech.designpattern.prototype;

import java.io.*;

class Hobby {
    String name;

    public Hobby(String name) {
        this.name = name;
    }
}

/**
 * jdk浅拷贝: 需要实现拷贝的类 实现Cloneable接口并重写调用父类的clone方法即可
 * 浅拷贝对于基本类型会拷贝新的实例，但是对于引用类型只会复制引用并不会拷贝新对象
 */
class A implements Cloneable {
    private String name;
    private String age;
    private Hobby hobby;

    private A() {
    }

    private A(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.hobby = builder.hobby;
    }

    @Override
    public String toString() {
        return "A{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", hobby=" + hobby +
                '}';
    }

    public String toAString() {
        return "A{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", hobby=" + hobby.name +
                '}';
    }

    @Override
    public A clone() throws CloneNotSupportedException {
        return (A) super.clone();
    }

    public static class Builder {
        private String name;
        private String age;
        private Hobby hobby;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(String age) {
            this.age = age;
            return this;
        }

        public Builder setHobby(Hobby hobby) {
            this.hobby = hobby;
            return this;
        }

        public A build() {
            return new A(this);
        }
    }
}

/**
 * 重写clone方法逻辑，实现深拷贝（引用类型也拷贝新对象）
 * 被引用的类型也需要实现Cloneable接口并实现
 */
class Interest implements Cloneable {
    String name;

    public Interest(String name) {
        this.name = name;
    }

    @Override
    public Interest clone() throws CloneNotSupportedException {
        return (Interest) super.clone();
    }
}

class B implements Cloneable {
    private String name;
    private String age;
    private Interest interest;

    private B() {
    }

    private B(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.interest = builder.interest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Interest getInterest() {
        return interest;
    }

    public void setInterest(Interest interest) {
        this.interest = interest;
    }

    @Override
    public String toString() {
        return "A{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", interest=" + interest +
                '}';
    }

    @Override
    public B clone() throws CloneNotSupportedException {
        B clone = (B) super.clone();
        clone.interest = interest.clone();
        return clone;
    }

    public static class Builder {
        private String name;
        private String age;
        private Interest interest;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(String age) {
            this.age = age;
            return this;
        }

        public Builder setInterest(Interest interest) {
            this.interest = interest;
            return this;
        }

        public B build() {
            return new B(this);
        }
    }
}

/***
 * 序列化对象实现深拷贝：使用对象输出入流可以深拷贝引用对象，但是被引用对象、深拷贝对象都必须实现序列化接口，并且被transient
 * 修饰的对象，变量不会被拷贝（null）
 */
class Food implements Serializable {

    private static final long serialVersionUID = -4663879299032948954L;
    String name;

    public Food(String name) {
        this.name = name;
    }
}

class C implements Serializable {
    private static final long serialVersionUID = 6580301784696905941L;
    private final Food food;
    private final transient String sex;
    private String name;
    private String age;

    public C(Builder builder) {
        this.age = builder.age;
        this.food = builder.food;
        this.name = builder.name;
        this.sex = builder.sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "C{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", food=" + food +
                ", sex='" + sex + '\'' +
                '}';
    }

    public static class Builder {
        private String name;
        private String age;
        private Food food;
        private transient String sex;

        public Builder buildName(String name) {
            this.name = name;
            return this;
        }

        public Builder buildAge(String age) {
            this.age = age;
            return this;
        }

        public Builder buildInterest(Food food) {
            this.food = food;
            return this;
        }

        public Builder buildSex(String sex) {
            this.sex = sex;
            return this;
        }

        public C build() {
            return new C(this);
        }
    }
}

/// TODO: 2022/6/16
public class Main {
    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        //默认clone方法对于对象的拷贝只复制了引用
        A a = new A.Builder().setAge("12").setName("花花").setHobby(new Hobby("画画")).build();
        A b = a.clone();
        System.out.println(a);
        System.out.println(b);
        //引用对象类实现默认clone方法，深拷贝类需要重写clone逻辑
        B c = new B.Builder().setAge("12").setName("花花").setInterest(new Interest("画画")).build();
        B d = c.clone();
        System.out.println(c);
        System.out.println(d);
        //序列化实现深拷贝
        C c1 = new C.Builder().buildName("花花").buildAge("18").buildSex("男").buildInterest(new Food("面包")).build();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("c1.obj"));
        objectOutputStream.writeObject(c1);
        objectOutputStream.close();
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("c1.obj"));
        C c2 = (C) objectInputStream.readObject();
        objectInputStream.close();
        System.out.println(c1);
        System.out.println(c2);
    }
}
