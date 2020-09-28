package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable, CustomThreadManipulator {
    private Thread thread;
    
    @Override
    public void run() {
        try {
            while (!this.thread.isInterrupted()) {
                System.out.println(this.thread.currentThread().getName());
                this.thread.sleep(100);
            }
        } catch (InterruptedException e) {
            
        }
    }
    
    @Override
    public void start(String threadName) {
        this.thread = new Thread(this);
        this.thread.setName(threadName);
        this.thread.start();
    }
    
    @Override
    public void stop() {
        this.thread.interrupt();
    }
}
