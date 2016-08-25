/**
 * Created by longfei on 16-6-25.
 */
public class Test {
    public static  volatile int a =  0;
    public static void m1(){
        a  = 0;
    }
    public static void  m2(){
        a  = -1;
    }

    public static void m3(){
        if(a==0 && a== -1){
            System.out.println("Error");
        }
    }
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    m1();
                }
            }
        }).start();new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    System.out.println(a);
                    m2();

                }
            }
        }).start();new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    m3();

                }
            }
        }).start();
    }
}
