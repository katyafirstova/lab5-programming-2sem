package core;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import model.Color;
import model.Status;
import model.Worker;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import static core.CommandCollection.*;

public class CLICollection {
    private WorkerCollection collection;
    private ArrayList<String> history = new ArrayList<String>();

    public CLICollection(WorkerCollection collection) {
        this.collection = collection;
    }


    public void analyse(String cmd) {

        switch (CommandCollection.fromCmd(cmd == null ? "" : cmd.trim())) {

            case HELP:
                for (CommandCollection s : CommandCollection.values()) {
                    if (s != UNKNOWN) {
                        System.out.println(s.getCommand() + ": " + s.getDescription());
                    }
                }
                addAndSaveHistory(HELP.getCommand());
                break;

            case CLEAR:
                collection.clear();
                addAndSaveHistory(CLEAR.getCommand());
                break;

            case INSERT:
                collection.insert(createWorker());
                addAndSaveHistory(INSERT.getCommand());
                break;

            case UPDATE_ID:
                Worker updateWorker = updateWorker();
                collection.update(updateWorker.getId(), updateWorker);
                addAndSaveHistory(UPDATE_ID.getCommand());
                break;

            case SHOW:
                collection.show();
                addAndSaveHistory(SHOW.getCommand());
                break;

            case SAVE:
                String filename = createFilename();
                collection.save(filename);
                addAndSaveHistory(SAVE.getCommand());
                break;

            case REMOVE_LOWER:
                collection.removeLower(getRemoveSalary());
                addAndSaveHistory(REMOVE_LOWER.getCommand());
                break;

            case REMOVE_GREATER:
                collection.removeGreater(getRemoveSalary());
                addAndSaveHistory(REMOVE_GREATER.getCommand());
                break;

            case REMOVE_KEY:
                collection.removeKey(getRemoveKey());
                addAndSaveHistory(REMOVE_KEY.getCommand());
                break;

            case REMOVE_ALL_BY_START_DATE:
                collection.removeAnyByStartDate(getStartDate());
                addAndSaveHistory(REMOVE_ALL_BY_START_DATE.getCommand());
                break;

            case REMOVE_ALL_BY_END_DATE:
                collection.removeAllByEndDate(getEndDate());
                addAndSaveHistory(REMOVE_ALL_BY_END_DATE.getCommand());
                break;

            case PRINT_FIELD_DESCENDING_END_DATE:
                collection.printEndDate(getWorker().getEndDate());
                addAndSaveHistory(PRINT_FIELD_DESCENDING_END_DATE.getCommand());
                break;

            case INFO:
                collection.info();
                addAndSaveHistory(INFO.getCommand());
                break;

            case EXIT:
                System.exit(0);
                addAndSaveHistory(EXIT.getCommand());
                break;

            case HISTORY:
                showHistory();
                break;

            case EXECUTE_SCRIPT:
                executeScript();
                break;

            case UNKNOWN:
                System.out.println(UNKNOWN.getDescription());
                break;

        }
    }

    private void executeScript() {
        XStream xstream = new XStream(new StaxDriver());
        XStream.setupDefaultSecurity(xstream);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("history.xml"));
            ArrayList<String> script = (ArrayList<String>) xstream.fromXML(reader);
            for (int i = 0; i < script.size(); i++) {
                analyse(script.get(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showHistory() {
        for (int i = 0; i < history.size(); i++) {
            String hist = history.get(i);
            if (!hist.equals(UNKNOWN)) {
                System.out.println(hist);

            }
        }
    }

    private void addAndSaveHistory(String command) {
        history.add(command);
        XStream xstream = new XStream(new StaxDriver());
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("history.xml"));
            xstream.toXML(history, writer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Date getEndDate() {
        WorkerAsker workerAsker = new WorkerAsker();
        return workerAsker.askEndDate();
    }

    private LocalDate getStartDate() {
        WorkerAsker workerAsker = new WorkerAsker();
        return workerAsker.askStartDate();
    }

    private Worker updateWorker() {
        Worker worker = createWorker();
        WorkerAsker workerAsker = new WorkerAsker();
        worker.setId(workerAsker.askId());

        return worker;
    }

    private long getRemoveKey() {
        WorkerAsker workerAsker = new WorkerAsker();
        return workerAsker.askKey();
    }

    private int getRemoveSalary() {
        WorkerAsker workerAsker = new WorkerAsker();
        return workerAsker.askSalary();
    }

    private long getId() {

        WorkerAsker workerAsker = new WorkerAsker();
        return workerAsker.askId();
    }

    private Worker createWorker() {

        WorkerAsker workerAsker = new WorkerAsker();
        String name = workerAsker.askName();
        float x = workerAsker.askX();
        int y = workerAsker.askY();
        float height = workerAsker.askHeight();
        int weight = workerAsker.askWeight();
        Status status = workerAsker.askStatus();
        Color color = workerAsker.askColor();
        int salary = workerAsker.askSalary();
        LocalDate newStartDate = workerAsker.askStartDate();
        Date newEndDate = workerAsker.askEndDate();
        Worker worker = WorkerFabric.create(name, x, y, salary,  newStartDate, newEndDate, status, height, weight, color);
        System.out.format("Создан элемент коллекции: %s\n", worker);

        return worker;
    }

    private String createFilename() {
        WorkerAsker workerAsker = new WorkerAsker();

        return workerAsker.askFileName();
    }

    private Worker getWorker() {
        return new Worker();
    }

    public void start() {

        for (; ; ) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                String line = bufferedReader.readLine();
                analyse(line);

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    }







