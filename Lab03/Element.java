// Zhenhao Zhang zzh133 zzh133@u.rochester.edu LAB3 HW3 Question 2

public class Element {
    private String name;
    private String symbol;
    private int atomic_number;
    private double atomic_weight;


    // constructor
    public Element(String name, String symbol, int atomic_number, double atomic_weight){
        this.name = name;
        this.symbol = symbol;
        this.atomic_number = atomic_number;
        this.atomic_weight = atomic_weight;
    }


    // setter method
    public void name(String name){
        this.name = name;
    }

    public void symbol(String symbol){
        this.symbol = symbol;
    }

    public void atomic_number(int atomic_number){
        this.atomic_number = atomic_number;
    }

    public void atomic_weight(double atomic_weight){
        this.atomic_weight = atomic_weight;
    }


    // getter method
    public String getName(){
        return name;
    }

    public String getSymbol(){
        return name;
    }

    public int getAtomic_number(){
        return atomic_number;
    }

    public double getAtomic_weight(){
        return atomic_weight;
    }


    // toString method
    public String toString(){
        return name+" ("+symbol+")"+" - Atomic No "+ atomic_number + " - Atomic Weight " + atomic_weight;
    }


    // main method
    public static void main(String[] args) {
        Element Hydrogen = new Element("Hydrogen","H",1,1.008);
        System.out.print(Hydrogen.toString());
    }


}
