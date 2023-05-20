public class heiretushori {
    public static void main(String[] args) {
        ThreadA a = new ThreadA();
        ThreadB b = new ThreadB();
        Thread c = new Thread(new threadrunnableA());
        Thread d = new Thread(new threadrunnableB());

        System.out.println("Threadクラスのサブクラスを作成して2つのスレッドで処理を実行する");
        System.out.println("※それぞれのスレッドは独立しているため実行結果が毎回異なる");
        a.start();
        b.start();
        System.out.println("Runnableインタフェースを使用してスレッドを作成する");
        c.start();
        d.start();


        //無名クラスでの実装例
        new Thread(new Runnable() {
            public void run(){
                System.out.println("無名クラス実装例");
            }
        }).start();

        //ラムダ式を用いた実装例
        new Thread(() -> {
            System.out.println("ラムダ式実装例");
        }).start();

        Thread sleepThread = new Thread(() ->{
            System.out.println("sleepthread:sleep開始");
            try{
                Thread.sleep(5000);

            }catch(InterruptedException e){
                System.out.println("sleepthread:割り込みをキャッチしました");
            }
            System.out.println("sleepthread:処理再開");
        });

        sleepThread.start();

        try{
            System.out.println("main:sleep開始");
            Thread.sleep(2000);
            System.out.println("main:sleep終了");
            sleepThread.interrupt();
        }catch(InterruptedException e){
            System.out.println("main:割り込みをキャッチしました");
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