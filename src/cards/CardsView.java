package cards;

class CardsView {
    private CardStack stack = CardStack.CreateCardStack();
    private Card[] openSlots = {stack.getRandomCard(), stack.getRandomCard(), stack.getRandomCard(), stack.getRandomCard(), stack.getRandomCard()};

    void printStack() {
        System.out.println("[Closed cards]");
        for (Card card : openSlots) {
            System.out.println(card);
        }
    }

    Card pickCard(int position) {
        Card newCard = stack.getRandomCard();
        Card card = openSlots[position];
        System.out.printf("%nNew card: %s", newCard);
        openSlots[position] = newCard;
        return card;
    }

    Card pickCard() {
        return stack.getRandomCard();
    }
}
