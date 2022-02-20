import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Estado
{
    private static class Tubo
    {
        Bola[] bs = new Bola[4];
        int    n  = 0;

        public Bola topo() {
            return n == 0 ? null : bs[n-1];
        }

        public boolean satisfaz() {
            // Vazio ou cheio e da mesma cor
            if (n == 0) return true;
            if (n != 4) return false;
            Bola b = bs[0];
            for (int i = 1; i < 4; i++)
                if (bs[i] != b)
                    return false;
            return true;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tubo tubo = (Tubo) o;
            return n == tubo.n && Arrays.equals(bs, tubo.bs);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(n);
            result = 31 * result + Arrays.hashCode(bs);
            return result;
        }
    }

    private Tubo[] tubos;

    // ------------------------------

    private Estado(int t) {
        this.tubos = new Tubo[t+2];
        for (int i = 0; i < t+2; i++)
            tubos[i] = new Tubo();
    }

    private Estado copia() {
        Estado c = new Estado(tubos.length - 2);
        for (int i = 0; i < tubos.length; i++) {
            c.tubos[i].n = tubos[i].n;
            for (int j = 0; j < 4; j++) {
                c.tubos[i].bs[j] = tubos[i].bs[j];
            }
        }
        return c;
    }

    public List<Estado> sucessores() {
        List<Estado> sucessores = new ArrayList<>();

        for (int i = 0; i < tubos.length; i++) {
            for (int j = 0; j < tubos.length; j++) {
                Estado sucessor = mover(i, j);
                if (sucessor != null)
                    sucessores.add(sucessor);
            }
        }

        return sucessores;
    }

    public Estado mover(int i, int j) {
        Tubo orig = tubos[i];
        Tubo dest = tubos[j];
        Bola bola = orig.topo();

        if (i == j) return null;
        if (orig.n == 0) return null;
        if (dest.n == 4) return null;
        if (dest.n > 0 && bola != dest.topo()) return null;

        Estado copia = copia();
        orig = copia.tubos[i];
        dest = copia.tubos[j];

        dest.bs[dest.n++] = bola;
        orig.bs[--orig.n] = null;

        return copia;
    }

    public boolean satisfaz() {
        for (Tubo t : tubos)
            if (!t.satisfaz())
                return false;
        return true;
    }

    public int numeroDeTubos() {
        return tubos.length;
    }

    public interface FuncaoIteracao {
        void exec(int indiceTubo, int indiceBola, Bola bola);
    }

    public void iterar(FuncaoIteracao fn) {
        for (int t = 0; t < tubos.length; t++) {
            for (int b = 0; b < 4; b++) {
                fn.exec(t, b, tubos[t].bs[b]);
            }
        }
    }

    private Bola corQueNaoAparece4vezes;

    public Bola corQueNaoAparece4vezes() {
        return corQueNaoAparece4vezes;
    }

    public boolean valido() {
        for (Bola bola : Bola.values()) {
            int count = 0;
            for (int t = 0; t < tubos.length; t++)
                for (int b = 0; b < 4; b++)
                    if (tubos[t].bs[b] == bola)
                        count++;
            if (count != 0 && count != 4) {
                corQueNaoAparece4vezes = bola;
                return false;
            }
        }
        return true;
    }

    // ------------------------------

    public static Estado from(String estadoStr) {
        String[] tubosStr = estadoStr.split("\n");
        Estado e = new Estado(tubosStr.length);
        for (int i = 0; i < tubosStr.length; i++) {
            String[] bolasStr = tubosStr[i].split(" ");
            if (bolasStr.length > 4) throw new RuntimeException("Mais de 4 bolas no tubo");
            e.tubos[i].n = bolasStr.length;
            for (int j = 0; j < bolasStr.length; j++) {
                e.tubos[i].bs[j] = Bola.from(bolasStr[j]);
            }
        }
        return e;
    }

    @Override
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
        return sb.append("\n").toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estado estado = (Estado) o;
        return Arrays.equals(tubos, estado.tubos);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(tubos);
    }
}
