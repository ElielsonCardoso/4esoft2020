package quartoBimestre.forum;

import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobProduto extends Thread {
    private final Random random = new Random();
    private final Logger logger = LoggerFactory.getLogger(JobProduto.class);
    private final JobSimples jobSimples;

    public JobProduto(JobSimples job) {
        this.jobSimples = job;
    }

    @Override
    public void run() {
        do{
            try {
				sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            
            int novoJob = 60 * random.nextInt();
            logger.info("Tamanho do novo job: {}", novoJob);
            this.jobSimples.JobsSimples(novoJob);
            logger.info("Criado com sucesso!");
        }while(true);
    }	
}
