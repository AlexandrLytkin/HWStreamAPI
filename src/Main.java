import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
//        task1();
//        task1Stream1();
        task1Stream2();
        List<User> userList = List.of(
                new User("Ivan", 21),
                new User("Ilya", 17),
                new User("Alex", 46),
                new User("Max", 23));
//        System.out.println("task2(allIs18age) = " + task2(userList));
        System.out.println("task2Stream(allIs18age) = " + task2Stream(userList));
        int[] value = {5, 4, 7, 9, 11, 7, 54, 1, 3, 2, 7};
//        System.out.println("task3(value) = " + Arrays.toString(task3(value)));
        System.out.println("task3Stream(value) = " + Arrays.toString(task3Stream(value)));
//        System.out.println("task4(averageAge) = " + task4(userList));
        System.out.println("task4Stream(averageAge) = " + task4Stream(userList));
//        System.out.println("task4Stream(averageAge) = " + task4Stream(List.of()));
    }

    private static void task1() {
        for (int i = 2; i < 23; i += 2) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    private static void task1Stream1() {
        Stream.iterate(2, i -> i + 2)
                .limit(11)
                .forEach(i -> System.out.println(i));
    }

    private static void task1Stream2() {
        Stream.iterate(0, i -> i + 1)
                .skip(2)
                .filter(i -> i % 2 == 0)
                .limit(11)
                .forEach(i -> System.out.println(i));

    }

    private static boolean task2(List<User> userList) {
        boolean allIs18age = true;
        for (User user : userList) {
            if (user.getAge() < 18) {
                allIs18age = false;

                break;
            }
        }
        return allIs18age;
    }

    private static boolean task2Stream(List<User> userList) {
        return userList.stream().noneMatch(user -> user.getAge() < 18);
    }

    private static int[] task3(int[] value) {
        int[] result = new int[5];
        Arrays.sort(value);
        int j = 0;
        for (int i = value.length - 1; i > value.length - 6; i--) {
            result[j] = value[i];
            j++;
        }
        return result;
    }

    private static int[] task3Stream(int[] value) {
        return Arrays.stream(value)
                .boxed()
//                .sorted((a1,a2)->a2.compareTo(a1))
                .sorted(Comparator.reverseOrder())
                .limit(5)
                .mapToInt(i -> i)
                .toArray();
    }

    private static double task4(List<User> userList) {
        int sum = 0;
        for (User user : userList) {
            sum += user.getAge();
        }
        return (double) sum / userList.size();
    }

    public static double task4Stream(List<User> userList) {
        return userList.stream()
                .mapToInt(u -> u.getAge())
                .average()
                .orElseThrow(() -> new RuntimeException("тут может быть своё собственное Exception"));
    }
}