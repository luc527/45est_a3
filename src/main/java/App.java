import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class App
{
    public static void main(String[] args) throws IOException {
        String jogo = Files.readString(Path.of("C:/projetos/45est_a3/src/main/assets/jogo1.txt"));
        Estado e = Estado.from(jogo);
        System.out.println(e.toString());
    }
}
