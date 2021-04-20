package core;

import model.Color;
import model.Status;
import model.Worker;

import java.io.File;
import java.io.IOException;
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
            if (args[0].endsWith(".xml") && checkExist(args[0])) {
                manageCollection.getWorkerCollection().load(args[0]);
            }
        }
        CLICollection cliCollection = new CLICollection(manageCollection.getWorkerCollection());
        cliCollection.start();
    }

    private static boolean checkExist(String fileName) {

        File f = new File(fileName);
        if (f.exists() && !f.isDirectory()) {
            return true;
        }

        return false;
    }
}


