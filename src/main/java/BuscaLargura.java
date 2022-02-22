import java.util.*;

public class BuscaLargura implements Busca {
    private int tempoMs = 0;
    private boolean sucesso = false;

    private final Set<Estado> vistos = new HashSet<>();

    public List<Estado> fazer(Estado inicial, int pmax) {
        List<Estado> solucao = List.of();

        int tempoInicio = (int) System.currentTimeMillis();

        if (inicial != null) {
            Queue<Nodo> queue = new LinkedList<>();
            queue.add(new Nodo(null, inicial));

            while (!queue.isEmpty()) {
                Nodo nodo = queue.remove();

                if (vistos.contains(nodo.estado)) continue;
                vistos.add(nodo.estado);

                if (nodo.estado.satisfaz()) {
                    solucao = nodo.caminho();
                    sucesso = true;
                    break;
                }

                for (Estado sucessor : nodo.estado.sucessores()) {
                    queue.add(new Nodo(nodo, sucessor));
                }
            }
        }

        tempoMs = (int) System.currentTimeMillis() - tempoInicio;

        return solucao;
    }

    public boolean sucesso() {
        return sucesso;
    }

    public int tempoMs() {
        return this.tempoMs;
    }
}
