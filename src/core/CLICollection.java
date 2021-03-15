package core;

import model.Color;
import model.Status;
import model.Worker;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.Scanner;

import static core.CommandCollection.*;

public class CLICollection {
    private WorkerCollection collection;
    private ArrayList<String> history = new ArrayList<String>();

    public CLICollection(WorkerCollection collection) {
        this.collection = collection;
    }


    public void analyse(String cmd, Worker worker, String filename) {


        switch (CommandCollection.fromCmd(cmd)) {

            case HELP:
                for (CommandCollection s : CommandCollection.values()) {
                    if (s != UNKNOWN) {
                        System.out.println(s);
                    }
                }
                history.add(HELP.getCommand());
                break;

            case CLEAR:
                collection.clear();
                history.add(CLEAR.getCommand());
                break;

            case INSERT:
                collection.insert(worker);
                history.add(INSERT.getCommand());
                break;

            case UPDATE_ID:
                collection.update(worker.getId(), worker);
                history.add(UPDATE_ID.getCommand());
                break;

            case SHOW:
                collection.show();
                history.add(SHOW.getCommand());
                break;

            case SAVE:
                collection.save(filename);
                history.add(SAVE.getCommand());
                break;

            case REMOVE_LOWER:
                collection.removeLower(worker);
                history.add(REMOVE_LOWER.getCommand());
                break;

            case REMOVE_GREATER:
                collection.removeGreater(worker);
                history.add(REMOVE_GREATER.getCommand());
                break;

            case REMOVE_KEY:
                collection.removeKey(worker.getId());
                history.add(REMOVE_KEY.getCommand());
                break;

            case REMOVE_ALL_BY_START_DATE:
                collection.removeAnyByStartDate(worker);
                history.add(REMOVE_ALL_BY_START_DATE.getCommand());
                break;

            case REMOVE_ALL_BY_END_DATE:
                collection.removeAllByEndDate(worker);
                history.add(REMOVE_ALL_BY_END_DATE.getCommand());
                break;

            case PRINT_FIELD_DESCENDING_END_DATE:
                collection.printEndDate(worker.getEndDate());
                history.add(PRINT_FIELD_DESCENDING_END_DATE.getCommand());
                break;

            case INFO:
                collection.info();
                history.add(INSERT.getCommand());
                break;

            case EXIT:
                System.exit(0);
                history.add(EXIT.getCommand());
                break;

            case HISTORY:
                // todo: цикл

        }
    }

    public void start()  {

        InputStream stream = System.in;
        Scanner console = new Scanner(stream);
        String line = console.nextLine();


        }



}



