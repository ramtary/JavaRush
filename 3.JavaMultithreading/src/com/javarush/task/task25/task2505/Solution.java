package com.javarush.task.task25.task2505;

/* 
Без дураков
*/
public class Solution {

    public static void main(String[] args) {
        MyThread myThread = new Solution().new MyThread("super secret key");
        myThread.start();
    }

    public class MyThread extends Thread {
        private String secretKey;
        
        private class MyUncaughtExceptionHandler implements UncaughtExceptionHandler {
            public MyUncaughtExceptionHandler() {
                
            }
            
            @Override
            public void uncaughtException(Thread thr, Throwable e) {
                try {
                    thr.sleep(500);
                }
                catch (InterruptedException ex) {
                    ex.printStackTrace();  
                }
                System.out.println(String.format("%s, %s, %s", secretKey, thr.getName(), e.getMessage()));
                e.printStackTrace();
            }
            
        }

        public MyThread(String secretKey) {
            this.secretKey = secretKey;
            setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
            //setDaemon(true);
        }

        @Override
        public void run() {
            throw new NullPointerException("it's an example");
        }
    }

}

