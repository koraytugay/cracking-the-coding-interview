package biz.tugay.ctci.ch07.callcenter;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static java.lang.Integer.parseInt;
import static java.lang.System.*;

public class CallCenter {

    Scanner scanner = new Scanner(in);

    BlockingQueue<Call> respondentQueue;
    BlockingQueue<Call> directorQueue;

    Set<Manager> managers = new HashSet<>();

    public CallCenter() {
        respondentQueue = new ArrayBlockingQueue<>(4);
        directorQueue = new ArrayBlockingQueue<>(4);

        new Respondent("Respondent-1", this).startHandlingCalls();
        new Respondent("Respondent-2", this).startHandlingCalls();

        managers.add(new Manager("Manager-1", this));
        managers.add(new Manager("Manager-2", this));

        new Director("Director", this).startHandlingCalls();
    }

    void operate() {
        while (true) {
            String line = scanner.nextLine();
            if ("q".equals(line))
                shutdownCallCenter();
            if ("s".equals(line))
                shutdownCallCenter();
            Call call = new Call(parseInt(line.split(" ")[0]), parseInt(line.split(" ")[1]));
            boolean callAccepted = respondentQueue.offer(call);
            if (!callAccepted)
                out.println("Please call back later.");
        }
    }

    private void shutdownCallCenter() {
        scanner.close();
        exit(0);
    }
}
