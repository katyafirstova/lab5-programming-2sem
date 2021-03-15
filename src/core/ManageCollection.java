package core;

import model.Color;
import model.Status;
import model.Worker;
import java.time.LocalDate;
import java.util.Date;

public class ManageCollection {
    private WorkerCollection workerCollection = new WorkerCollection();

    public WorkerCollection getWorkerCollection() {
        return workerCollection;
    }

    public static void main(String[] args) {
        ManageCollection manageCollection = new ManageCollection();

        if (args != null && args.length > 0) {
            if (args[0].endsWith(".xml")) {
                manageCollection.load_from_cmd(args[0]);
            }
        }
        CLICollection cliCollection = new CLICollection(manageCollection.getWorkerCollection());
        cliCollection.start();


    }
    public void load_from_cmd(String filename) {

        WorkerCollection savedCollection = new WorkerCollection();
        savedCollection.load(filename);
        savedCollection.info();
        savedCollection.show();
    }
}


