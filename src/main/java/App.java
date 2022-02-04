import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class App
{
    public static void main(String[] args) throws IOException {
        String jogo = Files.readString(Path.of("C:/projetos/45est_a3/src/main/assets/jogo1.txt"));
        Estado e = Estado.from(jogo);
        System.out.println(e.toString());

        Scanner scan = new Scanner(System.in);

        List<Estado> sucessores = e.sucessores();

        // Não vai ser criada com os sucessores assim, é só pra testar
        JanelaSolucao janelaSolucao = new JanelaSolucao(sucessores);

        /*System.out.println("Pressione ENTER para prosseguir");
        System.out.println("// Sucessores --------------------------------------------");
        for (Estado s : sucessores) {
            System.out.print(s.toString());
            scan.nextLine();
        }*/

        /*
        System.out.println("// Sucessores do primeiro sucessor -----------------------");
        for (Estado s : sucessores.get(0).sucessores()) {
            System.out.print(s.toString());
            scan.next();
        }
        */
    }
}
