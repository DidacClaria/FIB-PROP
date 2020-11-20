package domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class BlackCellTest {

    @Test
    public void testGetColumnValue() {
        BlackCell bc = new BlackCell(4, 1);
        assertEquals(4, bc.getColumnValue());
    }

    @Test
    public void testSetColumnValue() {
        BlackCell bc = new BlackCell(4, 1);
        bc.setColumnValue(8);
        assertEquals(8, bc.getColumnValue());
    }

    @Test
    public void testGetRowValue() {
        BlackCell bc = new BlackCell(1, 4);
        assertEquals(4, bc.getRowValue());
    }

    @Test
    public void testSetRowValue() {
        BlackCell bc = new BlackCell(1, 4);
        bc.setRowValue(8);
        assertEquals(8, bc.getRowValue());
    }

    @Test
    public void testGetValue() {
        BlackCell bc = new BlackCell(1, 1);
        assertEquals(0, bc.getValue());
    }

}