package game.cards;

/**
 * @author Wesley Klop <wesley19097@gmail.com>
 * @see CardButton
 * Listener for when a CardButton is clicked
 */
interface OnCardSelectListener {
    /**
     * onCardSelected gets called when a (Random)CardButton is clicked, with as a parameter
     * the button that was clicked so it can be assigned a new card
     *
     * @param caller the button that called it
     */
    void onCardSelected(CardButton caller);
}