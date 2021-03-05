package core.interfaces;

import model.Worker;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public interface InterfaceWorkerCollection {
    public void info(OutputStream stdout);
    public void show(OutputStream stdout);
    public void insert(Worker worker);
    public void update(long id, Worker worker);
    public void removeKey(long id);
    public void clear();
    public void save(String filename) throws IOException;
    public void load(String filename);
    public void executeScript(String filename);
    public void removeGreater(Worker worker);
    public void removeLower(Worker worker);
    public void removeAllByEndDate(Worker worker);
    public void removeAnyByStartDate(Worker worker);
    public void printEndDate(OutputStream output, Date endDate);
}
