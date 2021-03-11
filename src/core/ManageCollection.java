package core;

import model.Color;
import model.Status;
import model.Worker;
import java.time.LocalDate;
import java.util.Date;

public class ManageCollection {

    private void show_as_works() {

        Worker worker1 = WorkerFabric.create("a",1.0f, 2, 902930,
                LocalDate.now(), new Date(), Status.FIRED, 188, 90,
                Color.BLACK);
        Worker worker2 = WorkerFabric.create("b", 2.0f, 2, 43984,
                LocalDate.now(), new Date(), Status.HIRED, 189, 212,
                Color.BROWN);
        Worker worker3 = WorkerFabric.create("c", 1.0f, 1, 930902,
                LocalDate.now(), new Date(), Status.PROBATION, 190, 900,
                Color.WHITE);
        Worker worker4 = WorkerFabric.create("d", 3.0f, 6, 2848,
                LocalDate.now(), new Date(), Status.REGULAR, 178, 80,
                Color.WHITE);
        Worker worker5 = WorkerFabric.create("e", 5.9f, 8, 6849,
                LocalDate.now(), new Date(), Status.REGULAR, 150, 89,
                Color.BROWN);
        Worker worker6 = WorkerFabric.create("f", 3.9f, 14, 5834,
                LocalDate.now(), new Date(), Status.FIRED, 166, 78,
                Color.BLACK);
        WorkerCollection collection = new WorkerCollection();
        collection.insert(worker1);
        collection.insert(worker2);
        collection.insert(worker3);
        collection.insert(worker4);
        collection.insert(worker5);
        collection.insert(worker6);
        collection.info();
        collection.show();
        collection.save("collection.xml");

    }

    public static void main(String[] args)  {
        ManageCollection manageCollection = new ManageCollection();

        if (args != null && args.length > 0 && args[0].equals("test")) {
            manageCollection.show_as_works();
        } else {
            manageCollection.load_from_cmd(args);
        }
    }

    public void load_from_cmd(String[] args) {
        String loadFile;
        if (args != null && args.length > 0 && args[0].endsWith(".xml")) {
            loadFile = args[0];
        } else {
            throw new IllegalArgumentException("File not found or is not xml");
        }
        WorkerCollection savedCollection = new WorkerCollection();
        savedCollection.load(loadFile);
        savedCollection.info();
        savedCollection.show();
    }
}


