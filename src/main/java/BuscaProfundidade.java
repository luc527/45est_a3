import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class BuscaProfundidade
{
    private int tempoMs = 0;

    private Set<Estado> vistos = new HashSet<>();

    public List<Estado> fazer(Estado inicial) {
        List<Estado> solucao = List.of();

        int tempoInicio = (int) System.currentTimeMillis();

        if (inicial != null)
        {
            Stack<Nodo> stack = new Stack<>();
            stack.push(new Nodo(null, inicial));

            while (!stack.isEmpty()) {
                Nodo nodo = stack.pop();

                if (vistos.contains(nodo.estado)) continue;
                vistos.add(nodo.estado);

                if (nodo.estado.satisfaz()) {
                    solucao = nodo.caminho();
                    break;
                }
                for (Estado suc : nodo.estado.sucessores()) {
                    stack.push(new Nodo(nodo, suc));
                }
            }

        }

        tempoMs = (int) System.currentTimeMillis() - tempoInicio;

        return solucao;
    }

    public int tempoMs() {
        return this.tempoMs;
    }
}
