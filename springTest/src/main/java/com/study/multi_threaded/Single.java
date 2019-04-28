package com.study.multi_threaded;

public class Single {
    private  int count = 0;

    private static Single single = new Single();

    public static Single getInstance(){
        return  single;
    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static void main(String[] args) throws InterruptedException {

        for(int i=0;i<100;i++){
            new Thread(()->{
                try {
                    Single.getInstance().countManage();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(2000L);


    }

    public synchronized void countManage() throws InterruptedException {

        System.out.println(count);
        count++;
//        Thread.sleep(1L);

    }
}
