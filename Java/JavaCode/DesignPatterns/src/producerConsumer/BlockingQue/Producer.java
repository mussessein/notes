package producerConsumer.BlockingQue;

class Producer implements Runnable {
    private String name;
    private Storage s = null;

    public Producer(String name, Storage s) {
        this.name = name;
        this.s = s;
    }

    public void run() {
        try {
            while (true) {
                // 产生0~9999随机整数随机生产商品
                Product product = new Product((int) (Math.random() * 10000));
                System.out.println(name + "准备生产(" + product.toString() + ").");
                // 将商品放入仓库
                s.push(product);
                System.out.println(name + "已生产(" + product.toString() + ").");
                System.out.println("===============");
                Thread.sleep(500);
            }
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}