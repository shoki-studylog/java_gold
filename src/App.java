import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.Collection;
import java.util.Queue;
import java.util.Deque;
import java.util.function.*;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) throws Exception {
            //ArrayList実装
            ArrayList<Integer> list = new ArrayList<Integer>();
            Integer i1 = 1;
            int i2 = 2;
            Integer i3 = i1;

            list.add(i1);
            list.add(i2);
            list.add(i3);
            list.add(1,5);

            System.out.println("size:" + list.size());
            for(int i = 0; i < list.size(); i++){
                System.out.print(list.get(i) + ",");
            }
            System.out.println();
            for(Integer i : list){System.out.println(i + " ");}

            //HashSet,TreeSetの実装
            String[] ary = {"CCC","AAA","BBB"};
            HashSet<String> hashSet = new HashSet<String>();
            hashSet.add(ary[0]);
            hashSet.add(ary[1]);
            hashSet.add(ary[2]);
            hashSet.add(ary[0]);
            System.out.println("hashset---------");
            System.out.println("size: " + hashSet.size());
            
            for(String s : hashSet){System.err.print(s + ",");}
            System.out.println();
            System.out.println("treeset----------");
            TreeSet<String> treeSet = new TreeSet<String>();
            treeSet.add(ary[0]);
            treeSet.add(ary[1]);
            treeSet.add(ary[2]);
            for(String s : treeSet){System.out.println(s + ",");}

            System.out.println("iterator----------");
            TreeSet<String> set = new TreeSet<String>();
            set.add("C"); set.add("A"); set.add("B");
            Iterator<String> iter = set.iterator();
            while(iter.hasNext()){System.out.print(iter.next() + " ");}

            System.out.println("Queue-------------");
            Queue<String> queue = new ArrayDeque<String>();
            queue.add("1");queue.add("2");queue.add("3");
            System.out.println(queue);
            System.out.println("element : " + queue.element());
            System.out.println("element : " + queue.remove());
            System.out.println(queue);

            System.out.println("ジェネリクス例_従来とジェネリクス対応");

            // List list_before = new ArrayList();
            // list_before.add("aaa");
            // list_before.add("bbb");
            // list_before.add(new Integer(100));

            // for (int i = 0; i<list.size();i++){list_before.get(i));
            // }

            // List<String> list_after = new ArrayList<String>();
            // list_after.add("aaa");
            // list_after.add("bbb");
            // list_after.add(new Integer(100));

            // for (int i = 0; i<list.size();i++){
            //     System.out.println((String)list_after.get(i));
            // }
               //クラス定義
            // Class <T> sample {
                   //フィールド定義
            //     private <T> var;
                   //コンストラクタ定義
            //     public sample(T t){this.var = var;}
                   //メソッド定義
            //     public T getVar(){return var;} 
                   //メソッド定義
            //     public SetVar(T var){this.var = varl;}
            // }

            //関数型インタフェース→匿名クラス実装
            //string型変数に関数型インタフェースを実装して呼び出した結果を代入している
            //実装、インスタンス生成、呼び出しをまとめて行っている？
            String str = new Function<String, String>(){
                public String apply(String str){
                    return "Hello " + str;
                }
            }.apply("shoki");
            System.out.println(str);
            

            //Functionインタフェースの実装
            System.out.println("匿名クラスでの実装");
            String str1 = new Function<String,String>(){
                public String apply(String str){
                    return "Hello " + str; 
                }
            }.apply("shoki");
            System.out.println(str1);

            System.out.println("ラムダ式(省略なし)での実装");
            Function<String,String> f2 = (String str2) -> {
                return "Hello " + str2;
            };
            String str00 = f2.apply("shoki");
            System.out.println(str00);

            System.out.println("ラムダ式(省略あり)での実装");
            Function<String,String> f3 = str4 -> "Hello " + str4;
            String ggg = f3.apply("shoki");
            System.out.println(ggg);

            System.out.println("メソッド参照");
            // System.out.println("通常ラムダ式の書き方");
            // Function<String, Integer> f = str100 -> Integer.parseInt(str100);

            System.out.println("メソッド参照の書き方");
            Function<String, Integer> foo = Integer::parseInt;
            int num = foo.apply("100");
            System.out.println(num);



        };



}

@FunctionalInterface
interface MyFuncInter<T>{
    void foo();//抽象メソッドの定義（単一のみ）
    static void aaa(){};//Staticメソッドは定義できる
    default void bbb(){};//defaultメソッドも定義できる
    boolean equals(Object object);//java.lang.Objectクラスのpublicメソッドは抽象定義することができる。
    String toString();//java.lang.Objectクラスのpublicメソッドは抽象定義することができる。
}
