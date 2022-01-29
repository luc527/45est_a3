public enum Bola
{
    VERDE_CLARO("vecl"),
    CINZA("cinz"),
    LARANJA("lara"),
    AMARELO("amar"),
    VERMELHO("verm"),
    ROXO("roxo"),
    VERDE("verd"),
    AZUL_CLARO("azuc"),
    ROSA("rosa"),
    MARROM("marr"),
    AZUL("azul"),
    VERDE_ESCURO("vesc");

    private String s;

    Bola(String s) {
        this.s = s;
    }

    public String toString() {
        return this.s;
    }

    public static Bola from(String s) {
        switch (s) {
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
}
