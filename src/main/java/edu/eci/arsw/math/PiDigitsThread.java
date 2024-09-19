package edu.eci.arsw.math;

public class PiDigitsThread extends Thread {
    private int start;
    private int count;
    private byte[] digits;
    private static boolean paused = false;
    private boolean finished = false;

    private Object lock;

    public PiDigitsThread(int start, int finish, Object lock){
        this.start = start;
        this.count = finish - start + 1;
        this.lock = lock;
    }
    @Override
    public void run() {
        calculatePiDigits();
    }

    private void calculatePiDigits() {
        synchronized (lock){
            if (paused){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            digits = PiDigits.getDigits(start, count);
            finished = true;
        }



    }

    public byte[] getDigits() {
        return digits;
    }

    public static void setPaused(boolean paused) {
        PiDigitsThread.paused = paused;
    }
}
