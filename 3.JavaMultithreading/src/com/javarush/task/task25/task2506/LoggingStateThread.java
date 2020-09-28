package com.javarush.task.task25.task2506;


public class LoggingStateThread extends Thread {
    private Thread thread;
    
    public LoggingStateThread(Thread thread) {
        this.thread = thread;
    }
    
    @Override
    public void run() {
        State currState = this.thread.getState();
        System.out.println(currState);
        super.run();
        
        while (!currState.equals(State.TERMINATED)) {
            State newState = this.thread.getState();
            if (!currState.equals(newState)) {
                System.out.println(newState);
                currState = newState;
            }
        }
        this.interrupt();
    }
}