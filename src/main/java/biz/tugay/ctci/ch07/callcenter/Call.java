package biz.tugay.ctci.ch07.callcenter;

public class Call {
    static int idSequence = 0;

    int id;
    int level;
    int duration;

    public Call(int level, int duration) {
        this.level = level;
        this.duration = duration;
        this.id = idSequence++;
    }

    @Override
    public String toString() {
        return "Call{" + "id=" + id + ", level=" + level + ", duration=" + duration + '}';
    }
}
