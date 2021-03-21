package core;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import core.interfaces.InterfaceWorkerCollection;
import model.Worker;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


public class WorkerCollection implements InterfaceWorkerCollection {

    private HashMap<Long, Worker> workers = new HashMap<Long, Worker>();
    private java.time.LocalDateTime initData;


    public WorkerCollection() {
        this.initData = LocalDateTime.now();
    }


    @Override
    public void info() {
        System.out.println("Worker Collection properties");
        System.out.format("Initialisation data: %s\n", this.initData);
        System.out.format("Count of elements: %d\n", workers.size());
    }

    @Override
    public void show() {
        System.out.println("___________");
        for (Map.Entry<Long, Worker> entry : workers.entrySet()) {
            System.out.format("key: %d, worker: %s\n", entry.getKey(), entry.getValue());
        }
        System.out.println("===========");
    }

    @Override
    public void insert(Worker worker) {
        workers.put(worker.getId(), worker);
    }

    @Override
    public void update(long id, Worker worker) {
        workers.put(id, worker);
    }

    @Override
    public void removeKey(long id) {
        workers.remove(id);
    }

    @Override
    public void clear() {
        workers.clear();
    }

    @Override
    public void save(String filename)  {
        XStream xstream = new XStream(new StaxDriver());
        Class<?>[] classes = new Class[] { Worker.class, WorkerCollection.class};
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);
        xstream.alias("worker", Worker.class);
        xstream.alias("workers", WorkerCollection.class);
        xstream.addImplicitCollection(WorkerCollection.class, "workers");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            xstream.toXML(this, writer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void  load(String filename){
        XStream xstream = new XStream(new StaxDriver());
        Class<?>[] classes = new Class[] { Worker.class, WorkerCollection.class};
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);
        xstream.alias("worker", Worker.class);
        xstream.alias("workers", WorkerCollection.class);
        xstream.addImplicitCollection(WorkerCollection.class, "workers");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            WorkerCollection workerCollection = (WorkerCollection) xstream.fromXML(reader);
            this.workers = workerCollection.workers;
            this.initData = workerCollection.initData;
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void executeScript(String filename) {

    }

    @Override
    public void removeGreater(int salary) {
        Iterator<Map.Entry<Long, Worker>> workerIterator = workers.entrySet().iterator();
        while (workerIterator.hasNext()) {
            Map.Entry<Long, Worker> entry = workerIterator.next();
            Worker w = entry.getValue();
            int curSalary = w.getSalary();
            Worker worker = new Worker();
            salary = worker.getSalary();
            if (curSalary > salary) {
                workers.remove(curSalary);
            }
        }
    }


    @Override
    public void removeLower(int salary) {
        Iterator<Map.Entry<Long, Worker>> workerIterator = workers.entrySet().iterator();
        while (workerIterator.hasNext()) {
            Map.Entry<Long, Worker> entry = workerIterator.next();
            Worker w = entry.getValue();
            int curSalary = w.getSalary();
            Worker worker = new Worker();
            salary = worker.getSalary();
            if (curSalary < salary) {
                workerIterator.remove();
            }
        }
    }

    @Override
    public void removeAllByEndDate(Date endDate) {
        if(endDate == null)
            throw new IllegalArgumentException("Поле endDate не может быть пустым!");
        Iterator<Map.Entry<Long, Worker>> workerIterator = workers.entrySet().iterator();
        while (workerIterator.hasNext()) {
            Map.Entry<Long, Worker> entry = workerIterator.next();
            Worker w = entry.getValue();
            Date curEndDate = w.getEndDate();
            if (curEndDate.getTime() == endDate.getTime()) {
                workerIterator.remove();
            }
        }
    }


    @Override
    public void removeAnyByStartDate(LocalDate startDate) {
        Iterator<Map.Entry<Long, Worker>> workerIterator = workers.entrySet().iterator();
        while (workerIterator.hasNext()) {
            Map.Entry<Long, Worker> entry = workerIterator.next();
            Worker w = entry.getValue();
            LocalDate curStartDate = w.getStartDate();
            if (curStartDate.isEqual(startDate)) {
                workerIterator.remove();
            }
        }

    }

    @Override
    public void printEndDate(Date endDate) {
        ArrayList<Date> dates = new ArrayList<Date>();
        for (Worker worker : workers.values()) {
            dates.add(worker.getEndDate());
        }
        Collections.sort(dates, new Comparator<Date>() {
            @Override
            public int compare(Date date, Date t1) {
                return (int) (t1.getTime() - date.getTime());
            }
        });
        for (Date date : dates) {
            System.out.println(date);
        }
    }
}







