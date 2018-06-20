package util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Wesley Klop
 */
class ObservableTest {

    @Test
    void observerSavesInitialState() {
        var expected = "Test";
        Observable<String> observable = new Observable<>(expected);
        assertEquals(expected, observable.getValue());
    }

    @Test
    void observableEmitsOnChange() {
        var initial = "test";
        var expected = "FizzBuzz";
        Observable<String> observable = new Observable<>(initial);
        assertEquals(initial, observable.getValue());
        observable.addObserver(val -> assertEquals(val, expected));

        observable.setValue(expected);
        assertEquals(expected, observable.getValue());
    }

    @Test
    void observerDoesNotGetCalledAfterRemoval() {
        var initial = "foo";
        var expected = "I should not have been called!";
        Observer<String> observer = Assertions::fail;

        var observable = new Observable<>(initial);
        observable.addObserver(observer);
        observable.removeObserver(observer);
        observable.setValue(expected);
    }
}
