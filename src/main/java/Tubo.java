import java.util.ArrayList;
import java.util.List;

public class Tubo
{
    private Bola[] bs = new Bola[4];
    private int    n     = 0;

    public boolean colocar(Bola b) {
        if (n == 4) return false;
        if (n != 0 && bs[n-1] != b) return false;
        bs[n++] = b;
        return true;
    }

    public Bola tirar() {
        if (n == 0) return null;
        return bs[--n];
    }

    public boolean mover(Tubo outro) {
        if (outro == null || outro == this) return false;
        Bola b = tirar();
        if (b == null) return false;
        if (outro.colocar(b)) {
            return true;
        } else {
            // Colocar de volta
            colocar(b);
            return false;
        }
    }

    public String toString() {
        List<String> l = new ArrayList<>();
        for (int i = 0; i < n; i++)
            l.add(i, bs[i].toString());
        return String.join(" ", l);
    }

    public static Tubo from(String tuboStr) {
        Tubo t = new Tubo();
        for (String bolaStr : tuboStr.split(" "))
            t.colocar(Bola.from(bolaStr));
        return t;
    }

    public Tubo copia() {
        Tubo t = new Tubo();
        t.n = n;
        for (int i = 0; i < n; i++)
            t.bs[i] = bs[i];
        return t;
    }
}
