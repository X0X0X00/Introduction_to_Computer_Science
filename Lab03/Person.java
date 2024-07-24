// Zhenhao Zhang zzh133 zzh133@u.rochester.edu LAB3 HW3 Question 1

public class Person {
    private String name;
    private String age;
    private String phone_number;


    // constructor
    public Person(String name, String age,String phone_number) {
        this.name=name;
        this.phone_number =phone_number;
        this.age =age;
    }


    // setter method
    public void setName(String name){
        this.name = name;
    }

    public void setAge(String age){
        this.age = age;
    }

    public void setPhone_number(String phone_number){
        this.phone_number = phone_number;
    }


    // getter method
    public String getPhone_number(){
        return phone_number;
    }

    public String getAge(){
        return age;
    }

    public String getName(){
        return name;
    }


    // toString method
    public String toString() {
        return name+" - Age "+ age + " - Phone " + phone_number;
    }


    // main method
    public static void main(String[] args) {
        Person Tom = new Person("Jim Lahey","57","9025555555");
        System.out.print(Tom.toString());
    }


}

