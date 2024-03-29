package shixun;

/**
 * ClassName:SyncThreadTest
 * Description:
 *
 * @Date:2022/6/24 9:37
 * @Author:cmt
 */
public class SyncThreadTest {

    public static void main(String args[]){
        final Bank bank=new Bank();
        Thread tadd=new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                for(int i=1; i<5; ++i){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    bank.addMoney(100);
                    bank.lookMoney();
                    System.out.println("\n");
                }
            }
        });

        Thread tsub = new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while(true){
                    bank.subMoney(100);
                    bank.lookMoney();
                    System.out.println("\n");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
        tsub.start();
        tadd.start();
    }

}
