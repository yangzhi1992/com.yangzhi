package java8.java03;

public class Java3_2 {
    public static void main(String[] args) {
        Java3_2 test = new Java3_2();
        test.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("yangzhi");
            }
        });

        test.execute(()-> System.out.println("yangzhi2"));
    }
    public void execute(Runnable r){
        r.run();
    }
}
