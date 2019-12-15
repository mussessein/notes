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
                // ����0~9999����������������Ʒ
                Product product = new Product((int) (Math.random() * 10000));
                System.out.println(name + "׼������(" + product.toString() + ").");
                // ����Ʒ����ֿ�
                s.push(product);
                System.out.println(name + "������(" + product.toString() + ").");
                System.out.println("===============");
                Thread.sleep(500);
            }
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}