import java.awt.*;

public enum Bola
{
    VERDE_CLARO  ("vecl", new Color(0xB5E61D)),
    CINZA        ("cinz", Color.GRAY),
    LARANJA      ("lara", Color.ORANGE),
    AMARELO      ("amar", Color.YELLOW),
    VERMELHO     ("verm", Color.RED),
    ROXO         ("roxo", new Color(0xA349A4)),
    VERDE        ("verd", Color.GREEN),
    AZUL_CLARO   ("azuc", new Color(0x99D9EA)),
    ROSA         ("rosa", Color.PINK),
    MARROM       ("marr", new Color(0xB97A57)),
    AZUL         ("azul", Color.BLUE),
    VERDE_ESCURO ("vesc", new Color(0x0F4F22));

    private String s;
    private Color cor;

    Bola(String s, Color cor) {
        this.s = s;
        this.cor = cor;
    }

    public String toString() {
        return this.s;
    }

    public static Bola from(String s) {
        switch (s.trim()) {
            case "vecl": return VERDE_CLARO;
            case "cinz": return CINZA;
            case "lara": return LARANJA;
            case "amar": return AMARELO;
            case "verm": return VERMELHO;
            case "roxo": return ROXO;
            case "verd": return VERDE;
            case "azuc": return AZUL_CLARO;
            case "rosa": return ROSA;
            case "marr": return MARROM;
            case "azul": return AZUL;
            case "vesc": return VERDE_ESCURO;
            default:     throw new RuntimeException("String inválida para bola: " + s);
        }
    }

    public Color cor() {
        return this.cor;
    }
}
