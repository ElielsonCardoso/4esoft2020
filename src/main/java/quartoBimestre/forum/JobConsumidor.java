package quartoBimestre.forum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobConsumidor extends Thread {
    private final Logger logger = LoggerFactory.getLogger(JobConsumidor.class);
    private final JobSimples jobSimples;
    private Integer nroJob = 0;

    public JobConsumidor(JobSimples job) {
        this.jobSimples = job;
    }

    @Override
    public void run() {
        while (true) {
            if (nroJob == 0) {
                try {
                    if (jobSimples.getProximoJob() == 0) {
                        logger.info("Tempo: {} ", System.currentTimeMillis());
                        sleep(2000);
                    }
                } catch (InterruptedException e) {
                	e.printStackTrace();
                }
            } else {
                for (int x = nroJob; x >= 0; x--) {
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                    	e.printStackTrace();
                    }
                }
                nroJob = 0;
                logger.info("Processo finalizado em: {}", System.currentTimeMillis());
            }
        }
    }
}
