package edu.eci.arsw.math;

import java.util.ArrayList;

public class MainThread {
    private int n;
    private ArrayList<PiDigitsThread> threads;


    public MainThread(int threads) {
        this.n = threads;
        this.threads = new ArrayList<>();
    }

    public byte[] getDigits(int start, int count){
        ArrayList<ArrayList<Integer>> ranges = new ArrayList<>();
        for (int i = 0; i < n; i++){
            ranges =  getRanges(start, count, n);
        }

        for (int i = 0; i < ranges.size(); i++){
            PiDigitsThread thread = new PiDigitsThread(ranges.get(i).get(0), ranges.get(i).get(1));
            thread.start();
            this.threads.add(thread);
        }

        for (PiDigitsThread t : threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        byte[] digits = new byte[count];
        int i = 0;
        for (PiDigitsThread t : threads){
            byte[] bytes = t.getDigits();
            for (byte aByte : bytes) {
                digits[i] = aByte;
                i++;
            }
        }
        return digits;
    }

    private static ArrayList<ArrayList<Integer>> getRanges(int start, int count, int threads){
        ArrayList<ArrayList<Integer>> ranges = new ArrayList<>();
        int size = count/threads;
        int remaining = count%threads;
        int initial = start;
        for (int i = 1; i <= threads; i++){
            int end = initial + size - 1;
            if (i == threads){
                end += remaining;
            }
            ArrayList<Integer> range = new ArrayList<>();
            range.add(initial);
            range.add(end);
            initial = end + 1;
            ranges.add(range);
        }
//        System.out.println(ranges);
        return ranges;
    }

    public void setPaused(boolean paused){
        PiDigitsThread.setPaused(paused);
    }
}
