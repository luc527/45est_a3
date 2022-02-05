import javax.swing.*;
import java.awt.*;
import java.util.List;

public class JanelaSolucao
{
    private final List<Estado> solucao;
    private int indiceAtual;

    private final JFrame frame;
    private final JButton voltarTudo;
    private final JButton voltar;
    private final JButton avancar;
    private final JButton avancarTudo;

    private final JLabel cabecalho;
    private final String metodo;
    private final int tempoMs;

    private final EstadoImagem estadoImagem;

    // ------------------------------

    public JanelaSolucao(List<Estado> solucao, String metodo, int tempoMs)
    {
        if (solucao.size() == 0) {
            throw new RuntimeException("Janela não pode ser aberta com 0 soluções");
        }

        this.solucao = solucao;
        this.metodo = metodo;
        this.tempoMs = tempoMs;

        JPanel panelCabecalho = new JPanel();
        cabecalho = new JLabel();
        panelCabecalho.add(cabecalho);

        voltarTudo = new JButton("|<<");
        voltar = new JButton("<");
        avancarTudo = new JButton(">>|");
        avancar = new JButton(">");

        voltarTudo.addActionListener(e -> refresh(0));
        voltar.addActionListener(e -> refresh(indiceAtual - 1));
        avancar.addActionListener(e -> refresh(indiceAtual + 1));
        avancarTudo.addActionListener(e -> refresh(solucao.size() - 1));

        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBotoes.add(voltarTudo);
        panelBotoes.add(voltar);
        panelBotoes.add(avancar);
        panelBotoes.add(avancarTudo);

        estadoImagem = new EstadoImagem();

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(panelCabecalho, BorderLayout.PAGE_START);
        panel.add(panelBotoes, BorderLayout.PAGE_END);
        panel.add(estadoImagem, BorderLayout.CENTER);

        frame = new JFrame();
        frame.setTitle("Solução");
        frame.setContentPane(panel);
        frame.setVisible(true);

        refresh(0);
    }

    private void refresh(int i)
    {
        this.indiceAtual = i;

        cabecalho.setText(
                String.format("%s (%d/%d) %dms", metodo, indiceAtual+1, solucao.size(), tempoMs)
        );

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

        estadoImagem.setEstado(solucao.get(i));
        frame.repaint();
        frame.pack();
    }
}
