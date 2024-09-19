package edu.eci.arsw.math;

public class PiDigitsThread extends Thread {
    private int start;
    private int count;
    private byte[] digits;
    private static boolean paused = false;

    public PiDigitsThread(int start, int finish){
        this.start = start;
        this.count = finish - start + 1;
    }
    @Override
    public void run() {
        calculatePiDigits();
    }

    private void calculatePiDigits() {
        digits = PiDigits.getDigits(start, count);
    }

    public byte[] getDigits() {
        return digits;
    }

    public static void setPaused(boolean paused) {
        PiDigitsThread.paused = paused;
    }
}
