package biz.tugay.ctci.ch07.callcenter;

public class Manager {

    boolean available = true;

    CallCenter callCenter;
    String name;

    public Manager(String name, CallCenter callCenter) {
        this.name = name;
        this.callCenter = callCenter;
    }

    synchronized boolean isAvailable() {
        if (available) {
            available = false;
            return true;
        }
        return false;
    }

    synchronized void handleCall(Call call) {
        if (call.level < 2) {
            new Thread(() -> {
                try {
                    System.out.println(name + " handling " + call + ".");
                    Thread.sleep(call.duration * 1000);
                    available = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            return;
        }
        available = true;
        if (!callCenter.directorQueue.offer(call))
            System.out.println(name + ": No directors available. Call later.");
        else
            System.out.println("Please hold on. A director will assist you.");
    }
}
