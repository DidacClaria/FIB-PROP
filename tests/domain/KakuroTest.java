package domain;

import org.junit.Test;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;

public class KakuroTest {

    @Test
    public void proposeKakuro() {

    }

    @Test
    public void listKakuro() {
        Kakuro kakuro = new Kakuro();
        String input[][] = { {"*","C17","C8","*","*"},
                            {"F13","?","?","C17","*"},
                            {"F18","?","?","?","C11"},
                            {"*","F6","?","?","?"},
                            {"*","*","F16","?","?"} };
        kakuro.proposeKakuro(5,5, input);
        String test [][] = kakuro.listKakuro();
        assertThat(Arrays.asList(test[0]), hasItems("*", "C17", "C8", "*", "*"));
        assertThat("YOU ARE STUPID",Arrays.asList(test[1]), hasItems("F13", "9", "4", "C17", "*"));
        assertThat(Arrays.asList(test[2]), hasItems("F18","8","1","9","C11"));
        assertThat(Arrays.asList(test[3]), hasItems("*","F6","3","1","2"));
        assertThat(Arrays.asList(test[4]), hasItems("*","*","F16","7","9"));
    }

}