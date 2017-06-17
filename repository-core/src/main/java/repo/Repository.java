package repo;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class Repository {

    private Date creationDate;
    private AtomicLong counter;
    private String owner;
    private String id;

    public Repository() {
    }

    public Repository(final String id, final String owner) {
        this.creationDate = new Date();
        this.counter = new AtomicLong();
        this.id = id;
        this.owner = owner;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public AtomicLong getCounter() {
        return counter;
    }

    public String getOwner() {
        return owner;
    }

    public String getId() {
        return id;
    }

    public int getAccessCounter() {
        return counter.intValue();
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setCounter(AtomicLong counter) {
        this.counter = counter;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
//        counter.incrementAndGet();
        return "repo.Repository [creationDate=" + creationDate.toString() + ", counter=" + counter.toString() + ", id=" + id + ", owner=" + owner
            + "]";
    }
}
