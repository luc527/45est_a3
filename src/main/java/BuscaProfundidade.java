import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class BuscaProfundidade implements Busca
{
    private int tempoMs = 0;
    private boolean sucesso = false;

    // Cachê pra evitar recomputação
    private Set<Estado> vistos = new HashSet<>();

    // pmax: profundidade máxima, -1 para não limitar
    public List<Estado> fazer(Estado inicial, int pmax) {
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
                    sucesso = true;
                    break;
                }

                if (pmax != -1 && nodo.profundidade >= pmax) continue;

                for (Estado suc : nodo.estado.sucessores()) {
                    stack.push(new Nodo(nodo, suc));
                }
            }

        }

        tempoMs = (int) System.currentTimeMillis() - tempoInicio;

        return solucao;
    }

    public boolean sucesso() {
        return this.sucesso;
    }

    public int tempoMs() {
        return this.tempoMs;
    }
}
