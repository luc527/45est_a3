import java.util.List;

public class BuscaLargura implements Busca
{
    private int tempoMs = 0;
    private boolean sucesso = false;

    public List<Estado> fazer(Estado inicial, int pmax) {
        int tempoInicio = (int) System.currentTimeMillis();

        // ...

        tempoMs = (int) System.currentTimeMillis() - tempoInicio;

        return inicial.sucessores(); // Só pra retornar alguma coisa
    }

    public boolean sucesso() {
        return sucesso;
    }

    public int tempoMs() {
        return this.tempoMs;
    }
}
