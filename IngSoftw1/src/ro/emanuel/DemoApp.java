package ro.emanuel;

abstract class Fruit {
    public abstract void printFruit();
}

class Apple extends Fruit {
    public void printFruit() {
        System.out.println("Apple");
    }
}

class Banana extends Fruit {
    public void printFruit() {
        System.out.println("Banana");
    }
}

interface FruitFactory {
    Fruit createFruit();
}

class AppleFactory implements FruitFactory {
    public Fruit createFruit() {
        return new Apple();
    }
}

class BananaFactory implements FruitFactory {
    public Fruit createFruit() {
        return new Banana();
    }
}

class Client {
    private Fruit pFruit;

    public Client(FruitFactory factory) {
        pFruit = factory.createFruit();
    }

    public Fruit getFruit() {
        return pFruit;
    }
}

public class DemoApp {
    public static void main(String[] args) {
        FruitFactory appleFactory = new AppleFactory();
        Client appleClient = new Client(appleFactory);
        Fruit apple = appleClient.getFruit();
        apple.printFruit();

        FruitFactory bananaFactory = new BananaFactory();
        Client bananaClient = new Client(bananaFactory);
        Fruit banana = bananaClient.getFruit();
        banana.printFruit();
    }
}
