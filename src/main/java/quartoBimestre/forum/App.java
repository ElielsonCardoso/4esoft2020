package quartoBimestre.forum;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class App extends JDialog {

    private final JobSimples jobs = new JobSimples();
    private final List<JobConsumidor> consumidores = new ArrayList<>();
    private final List<JobProduto> produtos = new ArrayList<>();

    public App() {
        super();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.add(createpainel());
    }

    public static void main(String[] args) {
        App app = new App();
        app.setSize(400, 400);
        app.setVisible(true);
    }

    private JPanel createpainel() {

        final JPanel painel          = mainLayout();
        final JPanel firstRowPainel  = produtosLayout();
        final JPanel secondRowPainel = consumidoresLayout();
        final JPanel thirdRowPainel  = createLayout(Color.gray, "Qtde jobs:    ");

        final JTextField qtdeJobs = new JTextField(40);
        qtdeJobs.setEnabled(false);
        qtdeJobs.setMaximumSize(qtdeJobs.getPreferredSize());

        thirdRowPainel.add(qtdeJobs);
        thirdRowPainel.add(Box.createHorizontalGlue());

        this.jobs.AdicionarJobSimplesListener(jobCount -> qtdeJobs.setText(String.valueOf(jobCount)));

        painel.add(firstRowPainel);
        painel.add(secondRowPainel);
        painel.add(thirdRowPainel);

        return painel;
    }

    private JPanel mainLayout() {

        final JPanel painel = new JPanel();
        painel.setBackground(Color.gray);
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createRaisedBevelBorder());
        return painel;
    }

    private void startProducers(JButton addButton, JTextField fieldCount) {
        addButton.addActionListener(e -> {
            JobProduto novoProduto = new JobProduto(jobs);
            produtos.add(novoProduto);
            fieldCount.setText(String.valueOf(produtos.size()));
            novoProduto.start();
        });
    }

    private JPanel produtosLayout() {

        final JTextField contador = new JTextField(40);
        final JButton btnAdicionar = new JButton(" + ");

        this.startProducers(btnAdicionar, contador);
        btnAdicionar.setMaximumSize(btnAdicionar.getPreferredSize());

        contador.setEnabled(false);
        contador.setMaximumSize(contador.getPreferredSize());

        final JPanel produtospainel = createLayout(Color.BLUE, "Produtos:   ");
        produtospainel.add(contador);
        produtospainel.add(btnAdicionar);
        produtospainel.add(Box.createHorizontalGlue());

        return produtospainel;
    }

    private void startConsumers(JButton addButton, JTextField fieldCount) {
        addButton.addActionListener(e -> {
            JobConsumidor novoConsumidor = new JobConsumidor(jobs);
            consumidores.add(novoConsumidor);
            fieldCount.setText(String.valueOf(consumidores.size()));
            novoConsumidor.start();
        });
    }

    private JPanel consumidoresLayout() {

        final JTextField contador = new JTextField(40);
        contador.setFont(new Font("Courier", Font.BOLD, 12));

        final JButton btnAdicionar = new JButton(" + ");

        this.startConsumers(btnAdicionar, contador);
        btnAdicionar.setMaximumSize(btnAdicionar.getPreferredSize());

        contador.setEnabled(false);
        contador.setMaximumSize(contador.getPreferredSize());

        final JPanel consumidorespainel = createLayout(Color.GREEN, "Consumidores: ");
        consumidorespainel.add(contador);
        consumidorespainel.add(btnAdicionar);
        consumidorespainel.add(Box.createHorizontalGlue());

        return consumidorespainel;
    }

    private JPanel createLayout(Color color, String s) {

        final JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.X_AXIS));
        painel.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.setBackground(color);
        painel.add(new JLabel(s));

        return painel;
    }
}
