import javax.swing.*;
import java.awt.*;

public class EstadoImagem extends JPanel
{
    private Estado estado;

    public EstadoImagem(Estado estado) {
        this.estado = estado;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(estado.numeroDeTubos() * 64, 256);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        estado.iterar((x, y, bola) -> {
            if (bola == null) return;
            g.setColor(bola.cor());
            g.fillOval(64*x, 192-64*y, 64, 64);
        });
    }
}
