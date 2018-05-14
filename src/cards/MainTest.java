package cards;

public class MainTest {

    public static void main(String[] args) {
        CardsView view = new CardsView();

        view.printStack();

        System.out.printf("%nPicked card: %s%n%n", view.pickCard(0));
        view.printStack();

        System.out.printf("%nPicked card: %s%n", view.pickCard());
        view.printStack();

        System.out.printf("%nPicked card: %s%n%n", view.pickCard(2));
        view.printStack();
    }
}
