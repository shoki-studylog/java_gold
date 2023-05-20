public class enshu1 {
    enum Vals {X,Y,Z}
    public static void main(String[] args) {
        //3
        Vals data = Vals.Z;
        switch(data){
            case X: System.out.println("x");
            case Y: System.out.println("y");
            case Z: System.out.println("z");
        }
    }
}
//9
interface sample {
    default void test(){};
    static void test2(){};
}
