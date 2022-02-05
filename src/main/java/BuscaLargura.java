import java.util.List;

public class BuscaLargura
{
    private int tempoMs = 0;

    public List<Estado> fazer(Estado inicial) {
        int tempoInicio = (int) System.currentTimeMillis();

        // ...

        tempoMs = (int) System.currentTimeMillis() - tempoInicio;

        return inicial.sucessores(); // Só pra retornar alguma coisa
    }

    public int tempoMs() {
        return this.tempoMs;
    }
}
