import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> dados = new ArrayList<>(Arrays.asList(10, 20, 30, 2, 4, 6, 40, 8, 9));

        List<Integer> maiorSequencia = dados.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.groupingByConcurrent(Integer::intValue),
                        map -> map.values().stream()
                                .max((list1, list2) -> Integer.compare(list1.size(), list2.size()))
                                .orElse(new ArrayList<>())));
        
        maiorSequencia.forEach(System.out::println);
    }
}
