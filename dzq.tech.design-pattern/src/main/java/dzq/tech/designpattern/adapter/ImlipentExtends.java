package dzq.tech.designpattern.adapter;

/**
 * (实现加继承)实现适配器模式
 * 如客户端使用的是usb公线，需要连接type-c线，则需要一个usb母转type-c母的适配器
 * 实现目标接口（usb母）并继承（适配者）（type-c母），然后客户端调用目标接口时传入该适配器即可，
 * 适配器里实现目标接口时使用的（适配者）里面方法
 */
interface USBM {
    void transferDataByUSB();
}

interface TypeCM {
    void transferDataByTypeC();
}

class MyTypeCM implements TypeCM {
    @Override
    public void transferDataByTypeC() {
        System.out.println("使用TypeC传输数据！");
    }
}

class USBToTypeCAdapter extends MyTypeCM implements USBM {
    @Override
    public void transferDataByUSB() {
        transferDataByTypeC();
    }
}

class Client {
    public static void CopyData(USBM m) {
        System.out.println("开始复制文件！");
        m.transferDataByUSB();
        System.out.println("文件复制完成！");
    }
}

public class ImlipentExtends {
    public static void main(String[] args) {
        Client.CopyData(new USBToTypeCAdapter());
    }
}
