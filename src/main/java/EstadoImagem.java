import javax.swing.*;
import java.awt.*;

public class EstadoImagem extends JPanel
{
    private Estado estado;

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public Dimension getPreferredSize() {
        return estado == null
             ? super.getPreferredSize()
             : new Dimension(estado.numeroDeTubos()*64, 256);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (estado == null) return;
        estado.iterar((t, b, bola) -> {
            if (bola == null) return;
            g.setColor(bola.cor());
            g.fillOval(64*t, 192-64*b, 64, 64);
        });
    }
}
