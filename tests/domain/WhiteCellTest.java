package domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class WhiteCellTest {

    @Test
    public void testGetValue() {
        WhiteCell wc = new WhiteCell(2);
        assertEquals(2, wc.getValue());
    }

    @Test
    public void testSetValue() {
        WhiteCell wc = new WhiteCell(2);
        wc.setValue(3);
        assertEquals(3, wc.getValue());
    }

    @Test
    public void testGetColumnValue() {
        WhiteCell wc = new WhiteCell(2);
        assertEquals(0, wc.getColumnValue());
    }

    @Test
    public void testGetRowValue() {
        WhiteCell wc = new WhiteCell(2);
        assertEquals(0, wc.getRowValue());
    }
}