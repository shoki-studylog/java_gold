import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.*;
import java.util.stream.*;

public class StreamAPI {
    public static void main(String[] args) {
        List<String> data1 = Arrays.asList("aaaa","bbbb","ccc");
        boolean result1 = data1.stream().allMatch(s -> s.length() >= 3);
        boolean result2 = data1.stream().anyMatch(s -> s.length() == 4);
        boolean result3 = data1.stream().noneMatch(s -> s.length() == 4);
        System.out.println("allMatch,anyMatch,noneMatchの実装例-----");
        System.err.println(result1);
        System.err.println(result2);
        System.err.println(result3);

        //Streamオブジェクトを使い回すことはできない
        //データソースから新しくストリームオブジェクトを生成すれば同じデータソースでも使うことができる。
    //     Stream<String> stream1 = data1.stream();
    //     boolean result4 = stream1.allMatch(s -> s.length() >= 3);
    //     boolean result5 = stream1.anyMatch(s -> s.length() == 4);
    //     System.out.println(result4);
    //     System.out.println(result5);

        System.out.println("count(),forEach()の実装例-----");

        //count()の実装例
        long count_result = Stream.of("a","b","c").count();
        System.out.println(count_result);

        //forEach()の実装例
        Stream<String> foreach_stream = Stream.of("a","b","c");
        foreach_stream.forEach(System.out::print);
        System.out.println();
        //for (String s : foreach_stream){sysout}
        //→StreamインタフェースはIterableを継承指定ないためfor文で使用することができない


        System.out.println("reduce()の実装例-----");
        BinaryOperator<Integer> operator = (a,b) -> a + b;
        Stream<Integer> reduce_stream = Stream.of(10,20,30);
        Optional<Integer> reduce_result = reduce_stream.reduce(operator);
        reduce_result.ifPresent(System.out::println);

        //出力データ無し
        Stream<Integer> reduce_none_stream = Stream.empty();
        Optional<Integer> reduce_none_result = reduce_none_stream.reduce(operator);
        System.out.println(reduce_none_result);
        reduce_none_result.ifPresent(System.out::println);
        
        System.out.println("toArray()の実装例");
        
        int[] ary1 = IntStream.range(1,10).toArray();
        int[] ary2 = IntStream.rangeClosed(1, 10).toArray();

        Object[] ary3 = Stream.of("a","b").toArray();
        String[] ary4 = Stream.of("a","b").toArray(String[]::new);

        //rangeは最後を含まない、rangeclosedは最後を含む
        System.out.println("ary1: " + Arrays.toString(ary1));
        System.out.println("ary2: " + Arrays.toString(ary2));
        System.out.println("ary3: " + ary3.getClass());
        System.out.println("ary4: " + ary4.getClass());
        System.out.println("ary3: " + Arrays.toString(ary3));
        System.out.println("ary4: " + Arrays.toString(ary4));

        // Optioanlクラスのget(),isPresent()
        System.out.println("Optionalクラスのget,isPresentメソッド");
        Optional<Integer> op1 = Optional.of(10);
        Optional<Integer> op2 = Optional.empty();

        System.out.println("op1.get(): " + op1.get());
        System.out.println("op1.isPresent(): " + op1.isPresent());
        // System.out.println("op2.get(): " + op2.get());   //EmptyのOptionalオブジェクトにisPresent()を実行するとNoSuchElementException例外がスローされる
        System.out.println("op2.isPresent(): " + op2.isPresent());
        System.out.println("op1.isEmpty(): " + op1.isEmpty());
        System.out.println("op2.isEmpty(): " + op2.isEmpty());

        //max()メソッドの実装例
        System.out.println("max()メソッドの実装例");
        List<String> data = Arrays.asList("aaa","bb","c");

        //Comparatorインタフェースの自然順序づけを行っている
        Optional<String> max_result =
            data.stream().max(Comparator.naturalOrder());
        
        //文字数の少ない順に並べている
        Optional<String> max_result2 =
            data.stream().max((d1,d2) -> d1.length() - d2.length());

        max_result.ifPresent(System.out::println);
        max_result2.ifPresent(System.out::println);

        System.out.println("findfirst,findAnyメソッド実装例");
        List<String> find_data = Arrays.asList("c","a");
        Optional<String> find_result = find_data.stream().findFirst();
        Optional<String> find_result2 = find_data.stream().findAny();

        System.out.println("result: " + find_result.get());
        System.out.println("result2: " + find_result2.get());






        
    }
}
