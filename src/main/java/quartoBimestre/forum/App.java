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
        app.setSize(350, 350);
        app.setVisible(true);
    }

    private JPanel createpainel() {

        final JPanel painel          = mainLayout();
        final JPanel firstRowPainel  = produtosLayout();
        final JPanel secondRowPainel = consumidoresLayout();
        final JPanel thirdRowPainel  = createLayout(Color.gray, "Consumed job count:    ");

        final JTextField countJobs = new JTextField(40);
        countJobs.setEnabled(false);
        countJobs.setMaximumSize(countJobs.getPreferredSize());

        thirdRowPainel.add(countJobs);
        thirdRowPainel.add(Box.createHorizontalGlue());

        this.jobs.addJobQueueListener(jobCount -> countJobs.setText(String.valueOf(jobCount)));

        painel.add(firstRowPainel);
        painel.add(secondRowPainel);
        painel.add(thirdRowPainel);

        return painel;
    }

    private JPanel mainLayout() {

        final JPanel painel = new JPanel();
        panel.setBackground(Color.gray);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createRaisedBevelBorder());
        return painel;
    }

    private void startProducers(JButton addButton, JTextField fieldCount) {

        addButton.addActionListener(e -> {
            JobProducer newProducer = new JobProducer(jobs);
            produtos.add(newProducer);
            fieldCount.setText(String.valueOf(produtos.size()));
            newProducer.start();
        });
    }

    private JPanel produtosLayout() {

        final JTextField fieldCount = new JTextField(40);
        final JButton addButton = new JButton(" + ");

        this.startProducers(addButton, fieldCount);
        addButton.setMaximumSize(addButton.getPreferredSize());

        fieldCount.setEnabled(false);
        fieldCount.setMaximumSize(fieldCount.getPreferredSize());

        final JPanel produtospainel = createLayout(Color.CYAN, "Producers:   ");
        produtospainel.add(fieldCount);
        produtospainel.add(addButton);
        produtospainel.add(Box.createHorizontalGlue());

        return produtospainel;
    }

    private void startConsumers(JButton addButton, JTextField fieldCount) {

        addButton.addActionListener(e -> {
            JobConsumidor newConsumer = new JobConsumidor(jobs);
            consumidores.add(newConsumer);
            fieldCount.setText(String.valueOf(consumidores.size()));
            newConsumer.start();
        });
    }

    private JPanel consumidoresLayout() {

        final JTextField fieldCount = new JTextField(40);
        fieldCount.setFont(new Font("Courier", Font.BOLD, 12));

        final JButton addButton = new JButton(" + ");

        this.startConsumers(addButton, fieldCount);
        addButton.setMaximumSize(addButton.getPreferredSize());

        fieldCount.setEnabled(false);
        fieldCount.setMaximumSize(fieldCount.getPreferredSize());

        final JPanel consumidorespainel = createLayout(Color.YELLOW, "Consumers: ");
        consumidorespainel.add(fieldCount);
        consumidorespainel.add(addButton);
        consumidorespainel.add(Box.createHorizontalGlue());

        return consumidorespainel;
    }

    private JPanel createLayout(Color color, String s) {

        final JPanel painel = new Jpainel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.X_AXIS));
        painel.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.setBackground(color);
        painel.add(new JLabel(s));

        return painel;
    }
}
