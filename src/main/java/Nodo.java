import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Nodo
{
    public final Nodo pai;
    public final Estado estado;
    public final int profundidade;

    public Nodo(Nodo pai, Estado estado) {
        this.pai = pai;
        this.estado = estado;
        this.profundidade = pai == null ? 0 : pai.profundidade + 1;
    }

    public List<Estado> caminho() {
        Stack<Estado> stack = new Stack<>();
        Nodo cur = this;
        while (cur != null) {
            stack.push(cur.estado);
            cur = cur.pai;
        }
        List<Estado> caminho = new ArrayList<>(stack.size());
        while (!stack.isEmpty()) {
            caminho.add(stack.pop());
        }
        return caminho;
    }
}
