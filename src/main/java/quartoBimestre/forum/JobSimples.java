package quartoBimestre.forum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.LinkedList;

public class JobSimples {
	private final Logger logger = LoggerFactory.getLogger(JobSimples.class);
	private final LinkedList<Integer> jobs = new LinkedList<>();
	private JobSimplesListener listener;
	
	public JobSimples() {
		super();
	}

    public void AdicionarJobSimplesListener(JobSimplesListener listener) {
        this.listener = listener;
    }

    public interface JobSimplesListener {
        void JobSimplesChanged(int newSize);
    }

    public synchronized void JobsSimples(int job) {
        synchronized (this) {
            this.jobs.add(job);
            
            if (this.listener != null) {
                this.listener.JobSimplesChanged(this.jobs.size());
            }
        }
    }

    public synchronized Integer getProximoJob() {
        synchronized (this) {
            logger.info("Buscando próximo job em: {}", System.currentTimeMillis());

			if (this.jobs.isEmpty()) {
			    return 0;
			}
			
			Integer job = this.jobs.removeFirst();
			
			if (this.listener != null) {
			    this.listener.JobSimplesChanged(this.jobs.size());
			}
			
			logger.info("Próx. job: {}", job);
			
			return job;
	    }
	}
}
