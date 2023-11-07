package App.Common;

public class Philosopher implements Runnable {
    private int satiety = 3;
    private Fork rightFork;
    private Fork leftFork;

    private String name;
    private long birthTime;
    public Philosopher(String name, Fork rightFork, Fork leftFork) {
        this.rightFork = rightFork;
        this.leftFork = leftFork;
        this.name = name;
        birthTime = System.nanoTime();
    }


    @Override
    public void run() {
        try {
            while (satiety > 0) {
                action("думает",3000);
                synchronized (rightFork) {
                    action("взял вилку "+rightFork.number,500);
                    synchronized (leftFork) {
                        action("взял вилку "+leftFork.number,500);
                        action("ест",2000);
                        satiety--;
                        action("кладет на стол вилку "+leftFork.number,500);
                    }
                    action("кладет на стол вилку "+rightFork.number,500);
                }
            }
            action("наелся ++++++++++++++");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * какое-либо действие философа
     */
    private void action(String act, long timeout) throws InterruptedException {
        action(act);
        Thread.sleep((int) (Math.random() * timeout));
    }

    private void action(String act) {
        long time = (System.nanoTime() - birthTime)/10_000_000;
        System.out.printf("%s филосов %s -  %s\n", time, name, act);
    }
}
