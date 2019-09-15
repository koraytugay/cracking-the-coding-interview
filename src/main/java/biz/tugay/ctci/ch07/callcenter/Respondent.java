package biz.tugay.ctci.ch07.callcenter;

public class Respondent {

    String name;
    CallCenter callCenter;

    Runnable handleCall = () -> {
        while (true) {
            try {
                Call call = callCenter.respondentQueue.take();
                if (call.level != 0) {
                    escalate(call);
                } else {
                    System.out.println(name + " handling " + call + ".");
                    Thread.sleep(call.duration * 1000);
                }
            } catch (InterruptedException e) {
                return;
            }
        }
    };

    Thread thread;

    public Respondent(String name, CallCenter callCenter) {
        this.name = name;
        this.callCenter = callCenter;
    }

    void startHandlingCalls() {
        thread = new Thread(handleCall);
        thread.start();
    }

    void escalate(Call call) {
        Manager availableManager = callCenter.managers.stream().filter(Manager::isAvailable).findFirst().orElse(null);
        if (availableManager != null) {
            availableManager.handleCall(call);
            return;
        }
        if (!callCenter.directorQueue.offer(call))
            System.out.println(name + ": No managers or directors available. Call later.");
        else
            System.out.println("Please hold on. A director will take your call.");
    }
}
