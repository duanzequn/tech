package dzq.tech.designpattern.builder;

class User {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

abstract class BaseBuilder {
    public final User user = new User();

    /**
     * 建造姓名
     *
     * @return 建造者自身
     */
    abstract BaseBuilder buildName();

    /**
     * 建造年龄
     *
     * @return 建造者自身
     */
    abstract BaseBuilder buildAge();

    /**
     * 返回构建后的对象
     *
     * @return 构建后的对象
     */
    abstract User build();
}

class AdminBuilder extends BaseBuilder {
    @Override
    AdminBuilder buildName() {
        user.setName("管理员");
        return this;
    }

    @Override
    AdminBuilder buildAge() {
        user.setAge(12);
        return this;
    }

    @Override
    User build() {
        return user;
    }
}

class UserDirector {
    private BaseBuilder builder;

    private UserDirector() {
    }

    public UserDirector(BaseBuilder baseBuilder) {
        this.builder = baseBuilder;
    }

    public User construct() {
        return builder.buildAge().buildName().build();
    }
}

public class MyBuilder {
    public static void main(String[] args) {
        User user = new UserDirector(new AdminBuilder()).construct();
        System.out.println(user);
    }
}
