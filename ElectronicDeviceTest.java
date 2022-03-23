import java.time.LocalDate;

public class ElectronicDeviceTest {
    public static void main(String[] args) {

        Television t1 = new Television();
        t1.setTelevision(42,3,34,false,"samsung",40000,LocalDate.of(2018,3,2),2);
        System.out.println(t1);
        t1.turnOnDevice();
        t1.setVolume(40);
        t1.setTVOnTime(3);
        t1.increaseVolume();
        t1.turnOffDevice();
    }
}

class ElectronicDevice{
    Boolean powerOn;
    String brand;
    float price;
    LocalDate dateOfManufacture;
    int warranty;
    int onTime;

    public void setElectronicDevice(Boolean powerOn, String brand, float price, LocalDate dateOfManufacture, int warranty) {
        this.powerOn = powerOn;
        this.brand = brand;
        this.price = price;
        this.dateOfManufacture = dateOfManufacture;
        this.warranty = warranty;

    }
    public void setDeviceOnTime(int onTime){
        this.onTime = onTime;
        System.out.println("Device on for "+onTime+" hrs");
    }
    Boolean turnOnDevice(){
        powerOn = true;
        System.out.println("Device on");
        return true;

    }
    Boolean turnOffDevice(){
        powerOn = false;
        System.out.println("Device on");
        return false;
    }

    void checkWarranty(){
        if(LocalDate.now().getYear()<=(warranty+dateOfManufacture.getYear())){
            System.out.println("warranty available");
        }
        else
            System.out.println("warranty expired");
    }

    @Override
    public String toString() {
        return "ElectronicDevice{" +
                "powerOn=" + powerOn +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", dateOfManufacture=" + dateOfManufacture +
                ", warranty=" + warranty +
                ", onTime=" + onTime +
                '}';
    }
}

class BatteryPowered extends ElectronicDevice{
    int batteryCapacity;
    int batteryBackUp;

    public void setBatteryPowered(int batteryCapacity, int batteryBackUp,Boolean powerOn, String brand, float price, LocalDate dateOfManufacture, int warranty,int onTime) {
        super.setElectronicDevice(powerOn,brand,price,dateOfManufacture,warranty);
        this.batteryCapacity = batteryCapacity;
        this.batteryBackUp = batteryBackUp;
    }

    int usageTimeRemaining(){
        System.out.println((batteryBackUp-onTime) +"of usage time remaining");
        return batteryBackUp - onTime;
    }


    @Override
    public String toString() {
        return "BatteryPowered{" +
                "powerOn=" + powerOn +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", dateOfManufacture=" + dateOfManufacture +
                ", warranty=" + warranty +
                ", onTime=" + onTime +
                ", batteryCapacity=" + batteryCapacity +
                ", batteryBackUp=" + batteryBackUp +
                '}';
    }
}
class ElectricityPowered extends ElectronicDevice{
    int powerSaving;
    int energyConsumption;

    public void setElectricityPowered(int powerSaving, int energyConsumption,Boolean powerOn, String brand, float price, LocalDate dateOfManufacture, int warranty) {
        super.setElectronicDevice(powerOn, brand, price, dateOfManufacture, warranty);
        this.powerSaving = powerSaving;
        this.energyConsumption = energyConsumption;
    }

    int powerConsumed(){
        System.out.println("Television used "+energyConsumption*onTime+" units of electricity");
        return energyConsumption*onTime;
    }

    @Override
    public String toString() {
        return "ElectricityPowered{" +
                "powerOn=" + powerOn +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", dateOfManufacture=" + dateOfManufacture +
                ", warranty=" + warranty +
                ", onTime=" + onTime +
                ", powerSaving=" + powerSaving +
                ", energyConsumption=" + energyConsumption +
                '}';
    }
}
class Television extends ElectricityPowered{
    int displaySize;
    int volume;
    int MAX_VOl = 100;
    int MIN_VOL = 0;

    public void setTelevision(int displaySize,int powerSaving, int energyConsumption,Boolean powerOn, String brand, float price, LocalDate dateOfManufacture, int warranty) {
        super.setElectricityPowered( powerSaving, energyConsumption, powerOn, brand,price, dateOfManufacture, warranty);
        this.displaySize = displaySize;
    }
    public void setTVOnTime(int onTime){
        super.setDeviceOnTime(onTime);
    }
    public void setVolume(int volume){
        if(volume<MAX_VOl&&volume>MIN_VOL) {
            this.volume = volume;
            System.out.println("Volume set to "+ this.volume);
        }
        else
            System.out.println("volume outside limit");
    }

    @Override
    Boolean turnOnDevice() {
        System.out.println("Television Turned on");
        powerOn = true;
        return true;
    }

    @Override
    Boolean turnOffDevice() {
        System.out.println("Television Turned off");
        powerOn = false;
        return false;
    }

    void increaseVolume(){
        if(volume<MAX_VOl)
            volume++;
        else
            System.out.println("Max volume reached");
        System.out.println("Current Volume ->"+volume);
    }

    void decreaseVolume(){
        if(volume>MIN_VOL)
            volume--;
        else
            System.out.println("Min volume reached");
        System.out.println("Current Volume ->"+volume);
    }

    @Override
    public String toString() {
        return "Television{" +
                "powerOn=" + powerOn +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", dateOfManufacture=" + dateOfManufacture +
                ", warranty=" + warranty +
                ", onTime=" + onTime +
                ", powerSaving=" + powerSaving +
                ", energyConsumption=" + energyConsumption +
                ", displaySize=" + displaySize +
                ", volume=" + volume +
                ", MAX_VOl=" + MAX_VOl +
                ", MIN_VOL=" + MIN_VOL +
                '}';
    }
}