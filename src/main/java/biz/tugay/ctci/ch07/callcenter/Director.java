package biz.tugay.ctci.ch07.callcenter;

public class Director {

    String name;
    CallCenter callCenter;

    Runnable handleCall = () -> {
        while (true) {
            try {
                Call call = callCenter.directorQueue.take();
                System.out.println(name + " handling " + call + ".");
                Thread.sleep(call.duration * 1000);
            } catch (InterruptedException e) {
                return;
            }
        }
    };

    Thread thread;

    public Director(String name, CallCenter callCenter) {
        this.name = name;
        this.callCenter = callCenter;
    }

    void startHandlingCalls() {
        thread = new Thread(handleCall);
        thread.start();
    }
}
