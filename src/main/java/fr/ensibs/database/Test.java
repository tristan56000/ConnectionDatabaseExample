package fr.ensibs.database;

public class Test {

    private String a;

    private int b;

    public Test(String a, int b){
        this.a = a;
        this.b= b;
    }

    public String getA(){
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
