// Zhenhao Zhang zzh133 zzh133@u.rochester.edu LAB3 HW3 Question 3

public class SmartPhone {
    private boolean screen;
    private boolean voice;
    private boolean wifi;
    private boolean bluetooth;
    private double BatteryHealth_Initial = 2200;
    private double BatteryHealth = 10;
    private double BatteryTime = 0.0;


    // constructor
    public SmartPhone(boolean screen, boolean voice, boolean wifi,
                       boolean bluetooth) {
        this.screen = screen;
        this.voice = voice;
        this.wifi = wifi;
        this.bluetooth = bluetooth;
        this.BatteryHealth = BatteryHealth_Calculator();
        this.BatteryTime = BatteryTime();
    }


    // set method
    public void screen(boolean screen){
        this.screen = screen;
    }

    public void voice(boolean voice){
        this.voice = voice;
    }

    public void wifi(boolean wifi){
        this.wifi = wifi;
    }

    public void bluetooth(boolean bluetooth){
        this.bluetooth = bluetooth;
    }


    // get method
    public boolean getscreen(){
        return screen;
    }

    public boolean getvoice(){
        return voice;
    }

    public boolean getwifi(){
        return wifi;
    }

    public boolean getbluetooth(){
        return bluetooth;
    }


    // time method
    public double BatteryTime(){
        BatteryTime= (BatteryHealth_Initial / (BatteryHealth));
        return BatteryTime;
    }


    // calculate method
    public double BatteryHealth_Calculator(){
        if(screen){
            BatteryHealth+=500;
        }
        if(voice){
            BatteryHealth+=300;
        }
        if (wifi){
            BatteryHealth+=200;
        }
        if (bluetooth) {
            BatteryHealth+=100;
        }
        return BatteryHealth;
    }


    // toString method
    public String toString(){

        return "Battery capacity initial is: " + BatteryHealth_Initial +" mA "
                +"\n"+ "(Screen " + screen + ") (voice " +
                voice + ") (wifi " + wifi + ") (bluetooth " + bluetooth + ")"+"\n"
                + "Now the batteryHealth is :" + BatteryHealth + " mA "+
                "\n" + "The Phone can be used for :" + BatteryTime
                +" hours "+"\n";
    }


    // main method
    public static void main(String[] args) {
        SmartPhone Phone1 = new SmartPhone(false, false, false, false);
        System.out.println(Phone1.toString());
        SmartPhone Phone2 = new SmartPhone(true, true, false, false);
        System.out.println(Phone2.toString());
        SmartPhone Phone4 = new SmartPhone(true, true, true, true);
        System.out.println(Phone4.toString());
    }


}
