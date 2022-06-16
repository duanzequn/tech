package dzq.tech.designpattern.adapter;

class MyUSBToTypeCAdapter implements USBM {
    private final MyTypeCM typeC = new MyTypeCM();

    @Override
    public void transferDataByUSB() {
        typeC.transferDataByTypeC();
    }
}

public class ImplimentEmbed {
    public static void main(String[] args) {
        Client.CopyData(new USBToTypeCAdapter());
    }
}

