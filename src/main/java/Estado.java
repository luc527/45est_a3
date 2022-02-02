import java.util.ArrayList;
import java.util.List;

public class Estado
{
    private static class Tubo
    {
        Bola[] bs = new Bola[4];
        int    n  = 0;
    }

    private Tubo[] tubos;

    // ------------------------------

    private Estado(int t) {
        this.tubos = new Tubo[t+2];
        for (int i = 0; i < t+2; i++)
            tubos[i] = new Tubo();
    }

    // O jogo inicial pode ter duas bolas de cores iguais uma em cima da outra
    // Então usamos colocar(..., true) ao construir a partir de um jogo existente
    // E a implementação pública usa colocar(..., false)
    private boolean colocar(int i, Bola b, boolean podeIgual) {
        Tubo tubo = tubos[i];
        if (tubo.n == 4) return false;
        if (tubo.n > 0 && !podeIgual && tubo.bs[tubo.n-1] == b) return false;
        tubo.bs[tubo.n++] = b;
        return true;
    }

    public boolean colocar(int i, Bola b) {
        return colocar(i, b, false);
    }

    public Bola tirar(int i) {
        Tubo tubo = tubos[i];
        if (tubo.n == 0) return null;
        return tubo.bs[--tubo.n];
    }

    public boolean mover(int i, int j) {
        Bola b = tirar(i);
        if (b == null) return false;
        if (colocar(j, b)) return true;
        else {
            colocar(i, b);  // Colocar de volta
            return false;
        }
    }

    public Estado copia() {
        Estado c = new Estado(tubos.length - 2);
        for (int i = 0; i < tubos.length; i++) {
            c.tubos[i].n = tubos[i].n;
            for (int j = 0; j < 4; j++) {
                c.tubos[i].bs[j] = tubos[i].bs[j];
            }
        }
        return c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String sep1 = "";
        for (int i = 0; i < tubos.length; i++) {
            sb.append(sep1);
            String sep2 = "";
            for (int j = 0; j < 4; j++) {
                Bola b = tubos[i].bs[j];
                if (b == null) break;
                sb.append(sep2).append(b.toString());
                sep2 = " ";

            }
            sep1 = "\n";
        }
        return sb.toString();
    }

    public static Estado from(String estadoStr) {
        String[] tubosStr = estadoStr.split("\n");
        Estado e = new Estado(tubosStr.length);
        for (int i = 0; i < tubosStr.length; i++) {
            for (String bolaStr : tubosStr[i].split(" ")) {
                e.colocar(i, Bola.from(bolaStr), true);
            }
        }
        return e;
    }
}
