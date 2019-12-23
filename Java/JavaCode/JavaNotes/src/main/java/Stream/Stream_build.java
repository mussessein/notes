package Stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
流的创建
 */
public class Stream_build {

    public static <T> void show(String title,Stream<T> stream){

        final int SIZE =10;
        List<T> firstElements = stream.limit(SIZE + 1).collect(Collectors.toList());

        System.out.println(title+":");

        for (int i =0;i<firstElements.size();i++){

            // if (i>0) System.out.println(",");
            if (i<SIZE) System.out.println(firstElements.get(i));
            else System.out.println("...");
        }
        System.out.println();

    }

    public static void main(String[] args) throws IOException {


        Stream<String> song =Stream.of("gently","down","the","Stream");
        show("song",song);

        // 创建一个空流
        Stream<String> kong =Stream.empty();
        show("kong",kong);

    }

}
