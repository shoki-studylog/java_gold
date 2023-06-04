import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.*;

public class heiretushori {
    public static void main(String[] args) {
        // ThreadA a = new ThreadA();
        // ThreadB b = new ThreadB();
        // Thread c = new Thread(new threadrunnableA());
        // Thread d = new Thread(new threadrunnableB());
        // Share share_test = new Share();
        // Thread_test1 e1 = new Thread_test1(share_test);
        // Thread_test1 e2 = new Thread_test1(share_test);



        // System.out.println("Threadクラスのサブクラスを作成して2つのスレッドで処理を実行する");
        // System.out.println("※それぞれのスレッドは独立しているため実行結果が毎回異なる");
        // a.start();
        // b.start();
        // System.out.println("Runnableインタフェースを使用してスレッドを作成する");
        // c.start();
        // d.start();
        // System.out.println("排他制御と同期制御");
        // e1.start();e2.start();

        // //無名クラスでの実装例
        // new Thread(new Runnable() {
        //     public void run(){
        //         System.out.println("無名クラス実装例");
        //     }^
        // }).start();

        // //ラムダ式を用いた実装例
        // new Thread(() -> {
        //     System.out.println("ラムダ式実装例");
        // }).start();

        // Thread sleepThread = new Thread(() ->{
        //     System.out.println("sleepthread:sleep開始");
        //     try{
        //         Thread.sleep(5000);

        //     }catch(InterruptedException e){
        //         System.out.println("sleepthread:割り込みをキャッチしました");
        //     }
        //     System.out.println("sleepthread:処理再開");
        // });

        // sleepThread.start();

        // try{
        //     System.out.println("main:sleep開始");
        //     Thread.sleep(2000);
        //     System.out.println("main:sleep終了");
        //     sleepThread.interrupt();
        // }catch(InterruptedException e){
        //     System.out.println("main:割り込みをキャッチしました");

        // System.out.println("newSingleThreadExecutor()の使用例");

        // ExecutorService service = null;
        // try{
        //     //newSingleThreadExecutorを使用しているため一つのスレッドでタスク処理が行われる（executeの処理が決まった順番で行われる。）
        //     service = Executors.newSingleThreadExecutor();
        //     System.out.println("service,executor()");
        //     for(int i = 0; i < 3; i++){
        //         //for文でexecuteが参加実行される
        //         service.execute(() ->{
        //             //executeのRunnable型のタスクをラムダ式で記述している
        //             System.out.println("thread task");
        //             for(int q = 0; q < 5; q++){
        //                 try{
        //                     //500ミリ秒スリープしながら＊を出力している
        //                     Thread.sleep(500);
        //                     System.out.print(" * ");
        //                 }catch(InterruptedException e ){ e.printStackTrace();}
        //             }
        //         System.out.println();
        //         });
        //     }
        // }finally{
        //     service.shutdown();
        //     System.out.println("ex.shutdown()");
        // }

        System.out.println("Futureインタフェースのsubmitメソッドの利用");

        ExecutorService service01 = null;

        try{
            service01 = Executors.newSingleThreadExecutor();
            Future<?> result01 =
                service01.submit(() -> System.out.println("hello"));
            System.out.println(result01.get());
            Future<Boolean> result02 =
                service01.submit(() -> System.out.println("hello"),true);
            System.out.println(result02.get());
        }catch(InterruptedException | ExecutionException e){
            e.printStackTrace();
        }finally{
            if(service01 == null) service01.shutdown();
        }








    }
}

class ThreadA extends Thread{
    public void run(){
        System.out.println("threadA.run()");
    }
}
class ThreadB extends Thread{
    public void run(){
        System.out.println("threadB.run()");
    }
}

class threadrunnableA implements Runnable{
    public void run(){
        System.out.println("RunnableAのrun()");
    }
}

class threadrunnableB implements Runnable{
    public void run(){
        System.out.println("RunnableBのrun()");
    }
}

//排他制御と同期制御の適応例
class Share {
    private int a = 0;
    private String b;

    public synchronized void set(){
        while(a != 0){
            try{
                wait(); 
            }catch(InterruptedException e){}
        }

        notify();
        a++; b = "data";
        System.out.println("set() a : "+ a + "b : "+ b);
    }

    public synchronized void print(){
        while(b == null){
            try{
                wait();
            }catch(InterruptedException e){}
        }
        notify();
        a--;b = null;
        System.out.println(" print() a : " + a + "b :" + b);
    }
    

}

class Thread_test1 extends Thread {
    private Share share;
    public Thread_test1(Share share){this.share = share;}
    public void run(){
        for(int i = 0;i < 5;i++){share.set();}
    }
}

class Thread_test2 extends Thread {
    private Share share;
    public Thread_test2(Share share){this.share = share;}
    public void run(){
        for(int i = 0;i < 5;i++){share.print();}
    }
}
