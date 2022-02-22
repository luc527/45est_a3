import java.util.List;

public interface Busca {
    // pmax é a profundidade máxima
    List<Estado> fazer(Estado inicial, int pmax);

    int tempoMs();

    boolean sucesso();
}
