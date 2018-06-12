package client.ui.components;

/**
 * @see CardButton
 * Listener for when a CardButton is clicked
 */
public interface OnCardSelectListener {
    /**
     * onCardSelected gets called when a (Random)CardButton is clicked, with as a parameter
     * the button that was clicked so it can be assigned a new card
     *
     * @param caller the button that called it
     */
    void onCardSelected(CardButton caller);
}