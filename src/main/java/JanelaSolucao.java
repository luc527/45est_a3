import javax.swing.*;
import java.awt.*;
import java.util.List;

public class JanelaSolucao
{
    private List<Estado> solucao;
    private int indiceAtual;

    private JFrame frame;
    private JButton voltarTudo;
    private JButton voltar;
    private JButton avancar;
    private JButton avancarTudo;

    private JPanel panelEstado;

    // ------------------------------

    public JanelaSolucao(List<Estado> solucao)
    {
        this.solucao = solucao;

        if (solucao.size() == 0) {
            throw new RuntimeException("Janela não pode ser aberta com 0 soluções");
        }

        voltarTudo = new JButton("<<");
        voltar = new JButton("<");
        avancarTudo = new JButton(">>");
        avancar = new JButton(">");

        voltarTudo.addActionListener(e -> refresh(0));
        voltar.addActionListener(e -> refresh(indiceAtual - 1));
        avancar.addActionListener(e -> refresh(indiceAtual + 1));
        avancarTudo.addActionListener(e -> refresh(solucao.size() - 1));

        JPanel botoes = new JPanel();
        botoes.setLayout(new FlowLayout(FlowLayout.CENTER));
        botoes.add(voltarTudo);
        botoes.add(voltar);
        botoes.add(avancar);
        botoes.add(avancarTudo);

        panelEstado = new JPanel();

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(botoes, BorderLayout.PAGE_END);
        panel.add(panelEstado, BorderLayout.CENTER);

        frame = new JFrame();
        frame.setTitle("Solução");
        frame.setContentPane(panel);
        frame.setVisible(true);

        refresh(0);
    }

    private void refresh(int i)
    {
        this.indiceAtual = i;

        voltarTudo.setEnabled(true);
        voltar.setEnabled(true);
        avancar.setEnabled(true);
        avancarTudo.setEnabled(true);

        if (i == 0) {
            voltarTudo.setEnabled(false);
            voltar.setEnabled(false);
        }
        if (i == solucao.size() - 1) {
            avancar.setEnabled(false);
            avancarTudo.setEnabled(false);
        }

        panelEstado.removeAll();
        panelEstado.add(new EstadoImagem(solucao.get(i)));
        frame.repaint();
        frame.pack();
    }
}
