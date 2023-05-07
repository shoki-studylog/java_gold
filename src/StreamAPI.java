import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
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

        System.out.println("orElse(),orElseGet(),orElseThrow()の実装例");

        Stream<Double> orelse_stream = Stream.empty();
        Optional<Double> orelse_result = orelse_stream.findFirst();

        //値があれば値を返し、それ以外は引数を返す
        System.out.println(orelse_result.orElse(0.0));
        //値があれば値を返し、それ以外は引数のSupplinerの結果を返す（今回は結果がDouble型でないといけない）
        System.out.println(orelse_result.orElseGet(() -> Math.random()));
        //値があれば値を返し、それ以外は引数の例外をスローする
        // System.out.println(orelse_result.orElseThrow(IllegalStateException::new));

        System.out.println("filter(),distinct()メソッド実装例");
        //先頭がaから始まる要素のみのストリームを返す
        Stream<String> filter_stream = Stream.of("ami","naoki","akko");
        filter_stream.filter(s -> s.startsWith("a"))
                            .forEach(x -> System.out.print(x + " "));
        System.out.println();

        //値が空でない要素のみのストリームを返す
        Stream<String> filter_stream2 = Stream.of("","naoki","akko");
        filter_stream2.filter(Predicate.not(s -> s.isEmpty()))
                            .forEach(x -> System.out.print(x + " "));
        System.out.println();

        //重複要素を除く要素で構成されたストリームを返す
        Stream<String> distinct_stream = Stream.of("ami","naoki","akko","ami");
        distinct_stream.distinct()
        .forEach(x -> System.out.print(x + " "));
        System.out.println();

        System.out.println("map()メソッド実装例");
        //String型ストリームからString型ストリームに変換
        Stream<String> map_stream = Stream.of("naoki","akko","ami");
        Stream<String> map_stream2 = map_stream.map(s -> s.toUpperCase());
        map_stream2.forEach(x -> System.out.print(x + " "));
        System.out.println();

        Stream<String> map_stream3 = Stream.of("naoki","akko","ami");
        Stream<Integer> map_stream4 = map_stream3.map(s -> s.length());
        map_stream4.forEach(x -> System.out.print(x + " "));
        System.out.println();

        System.out.println("flatMap()メソッド実装例");

        List<Integer> flatmap_data1 = Arrays.asList(10);
        List<Integer> flatmap_data2 = Arrays.asList(20,30);
        List<Integer> flatmap_data3 = Arrays.asList(40,50,60);

        List<List<Integer>> datalist = Arrays.asList(flatmap_data1,flatmap_data2,flatmap_data3);

        //mapを使用した例
        datalist.stream()
        .map(x -> x.stream())
        .forEach(l -> {
            l.forEach(x -> System.out.print(x + " "));
        });
        System.out.println();
        //flatMapを使用した例
        datalist.stream()
        .flatMap(x -> x.stream())
        .forEach(x -> System.out.print(x + " "));

        System.out.println("sorted()メソッド実装例");

        //引数無し（自然順序）
        Stream.of("naoki","ami","akko")
        .sorted()
        .forEach(x -> System.out.print(x + " "));
        
        System.out.println();

        //引数あり(Comparatorオブジェクト使用)
        Stream.of("naoki","ami","akko")
        .sorted(Comparator.reverseOrder())
        .forEach(x -> System.out.print(x + " "));

        System.out.println("peek()メソッド実装例");
        List<String> peek_list = Stream.of("one","two","three","four","three")
        .filter(s -> s.length() > 3)
        .peek(e -> System.out.println("after filter : " + e ))
        .distinct()
        .peek(e -> System.out.println("after distinct : " + e))
        .map(String::toUpperCase)
        .peek(e -> System.out.println("after map : " + e))
        .collect(Collectors.toList());

        System.out.println("toList()メソッド実装例");
        //String型のストリームをリストに変換する
        Stream<String> tolist_stream = Stream.of("naoki","ami","akko");
        List<String> tolist_list = tolist_stream.collect(Collectors.toList());
        System.out.println(tolist_list);

        System.out.println("joining()メソッド実装例");
        //Streamの要素を連結した文字列を返す
        Stream<String> joining_stream = Stream.of("naoki","ami","akko");
        String joining_string = joining_stream.collect(Collectors.joining(" | "));
        System.out.println(joining_string);

        System.out.println("summingInt()メソッド実装例");
        //Streamの要素の文字数の合計を取得する
        Stream<String> summingInt_stream = Stream.of("naoki","ami","akko");
        Integer summingInt_sum = summingInt_stream.collect(
            Collectors.summingInt(x -> x.length())
        );
        System.out.println(summingInt_sum);

        System.out.println("averagingInt()メソッド実装例");
        //averagingXXX()メソッドは全て戻り値がDouble型となる
        Stream<String> averagingInt_stream = Stream.of("naoki","ami","akko");
        Double averagintInt_average = averagingInt_stream.collect(
            Collectors.averagingInt(x -> x.length())
        );
        System.out.println(averagintInt_average);

        System.out.println("toSet()メソッド実装例");
        Stream<String> toset_stream = Stream.of("naoki","ami","akko");
        Set<String> toset_set = toset_stream.collect(
            Collectors.toSet()
        );
        System.out.println(toset_set);


        System.out.println("toMap()メソッド実装例");
        //構文1(キーとバリューを指定してマップを生成する)        
        System.out.println("構文1");
        Stream<String> tomap_stream = Stream.of("naoki","ami","akko");
        Map<String,String> tomap_map = tomap_stream.collect(Collectors.toMap(x -> x, String::toUpperCase));
        System.out.println(tomap_map);

        //構文2(キーが重複した場合に指定したマージ処理を実行する（今回は、コロンで結合する）)
        System.out.println("構文2");
        Stream<String> tomap_stream3 = Stream.of("nao","ami","akko");
        Map<Integer, String> tomap_map2 = tomap_stream3.collect(
            Collectors.toMap(String::length,s -> s,(s1,s2) -> s1 + " : " + s2)
        );
        System.out.println(tomap_map2);
        System.out.println(tomap_map2.getClass());

        //構文3(第3引数で指定したマージ処理を行った結果を格納するマップを生成することができる(今回はTreeMapに格納されている))
        System.out.println("構文3");
        Stream<String> tomap_stream4 = Stream.of("nao","ami","akko");
        Map<Integer,String> tomap_map3 = tomap_stream4.collect(
            Collectors.toMap(String::length,s -> s, (s1,s2) -> s1 + " : " + s2,TreeMap::new)
        );
        System.out.println(tomap_map3);
        System.out.println(tomap_map3.getClass());

        System.out.println("groupingBy()メソッド実装例");
        //同じキーを返せば同じグループに属するという意味になる
        System.out.println("構文1");
        Stream<String> grouping_stream = Stream.of("belle","akko","ami","bob","nao");
        Map<String,List<String>> grouping_map = grouping_stream.collect(
            Collectors.groupingBy(s -> s.substring(0,1))
        );
        System.out.println(grouping_map);

        System.out.println("構文2");
        //構文2(第2引数にてグループ化したリストに対して行い対処理を指定する（今回はセットに変換している）)
        Stream<String> grouping_stream2 = Stream.of("belle","akko","ami","bob","nao");
        Map<String,Set<String>> grouping_map2 = grouping_stream2.collect(
            Collectors.groupingBy(s -> s.substring(0,1),Collectors.toSet())
        );
        System.out.println(grouping_map2);

        System.out.println("構文3");
        //構文3(第2引数にて第3引数の処理結果を何に格納するのかを指定している（今回はTreeMapに格納している）)
        Stream<String> grouping_stream3 = Stream.of("belle","akko","ami","bob","nao");
        Map<String,String> grouping_map3 = grouping_stream3.collect(
            Collectors.groupingBy(s -> s.substring(0,1),TreeMap::new,Collectors.joining())
        );
        System.out.println(grouping_map3);
        System.out.println(grouping_map3.getClass());

        System.out.println("partitioningBy()メソッド実装例");
        //Streamの要素をbooleanで判定してグルーピングする
        System.out.println("構文1");
        Stream<Integer> partitioning_stream = Stream.of(3,5,7,9);
        Map<Boolean, List<Integer>> partitioning_map = partitioning_stream.collect(
            Collectors.partitioningBy(s -> s > 5)
        );
        System.out.println(partitioning_map);

        System.out.println("構文2");
        //構文2(グループ化したリストに対して処理を行う場合は、第二引数に処理を指定する（今回はグループ内の要素を合計している）)
        Stream<Integer> partitioning_stream2 = Stream.of(3,5,7,9);
        Map<Boolean,Integer> partitioning_map2 = partitioning_stream2.collect(
            Collectors.partitioningBy(s -> s > 5,Collectors.summingInt(n -> n))
        );
        System.out.println(partitioning_map2);

        System.out.println("mapping()メソッド実装例");
        //map()メソッド例
        System.out.println("map()メソッドの場合");
        Stream<String> mapping_stream = Stream.of("ami","naoki","akko");
        String mapping_string = mapping_stream.map(s -> s.toUpperCase())
        .collect(Collectors.joining(" : "));
        System.out.println(mapping_string);

        //mapping()メソッド例
        System.out.println("mapping()メソッドの場合");
        Stream<String> mapping_stream2 = Stream.of("ami","naoki","akko");
        String mapping_string2 = mapping_stream2.collect(
            Collectors.mapping(s -> s.toUpperCase(),Collectors.joining(" : "))
        );
        System.out.println(mapping_string2);

        System.out.println("minBy()メソッドの実装例");
        Stream<String> min_stream = Stream.of("ami","naoki","akko");
        Optional<String> min_result = min_stream.collect(
            Collectors.minBy(Comparator.naturalOrder())
        );
        System.out.println(min_result.get());

        System.out.println("maxBy()メソッドの実装例");
        Stream<String> max_stream = Stream.of("101","105","106","203","205");
        Map<String,Optional<String>> maxby_result= max_stream.collect(
            Collectors.groupingBy(s -> s.substring(0,1),Collectors.maxBy(Comparator.naturalOrder()))
        );
        System.out.println(maxby_result);







    }
}
