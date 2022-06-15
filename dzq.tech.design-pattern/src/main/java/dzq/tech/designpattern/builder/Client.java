package dzq.tech.designpattern.builder;

class Bike {
    private String frame;
    private String seat;

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}

// 抽象 builder 类
abstract class AbstractBuilder {

    protected Bike mBike = new Bike();

    public abstract void buildFrame();

    public abstract void buildSeat();

    public abstract Bike createBike();
}

//摩拜单车Builder类
class MobikeBuilder extends AbstractBuilder {

    @Override
    public void buildFrame() {
        mBike.setFrame("铝合金车架");
    }

    @Override
    public void buildSeat() {
        mBike.setSeat("真皮车座");
    }

    @Override
    public Bike createBike() {
        return mBike;
    }
}

//ofo单车Builder类
class OfoBuilder extends AbstractBuilder {

    @Override
    public void buildFrame() {
        mBike.setFrame("碳纤维车架");
    }

    @Override
    public void buildSeat() {
        mBike.setSeat("橡胶车座");
    }

    @Override
    public Bike createBike() {
        return mBike;
    }
}

//指挥者类
class Director {
    private final AbstractBuilder mAbstractBuilder;

    public Director(AbstractBuilder abstractBuilder) {
        mAbstractBuilder = abstractBuilder;
    }

    public Bike construct() {
        mAbstractBuilder.buildFrame();
        mAbstractBuilder.buildSeat();
        return mAbstractBuilder.createBike();
    }
}

//测试类
public class Client {
    public static void main(String[] args) {
        showBike(new OfoBuilder());
        showBike(new MobikeBuilder());
    }

    private static void showBike(AbstractBuilder abstractBuilder) {
        Director director = new Director(abstractBuilder);
        Bike bike = director.construct();
        System.out.println(bike.getFrame());
        System.out.println(bike.getSeat());
    }
}
