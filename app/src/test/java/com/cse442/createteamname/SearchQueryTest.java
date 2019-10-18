package com.cse442.createteamname;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SearchQueryTest {

    private MainActivity main;

    @Test
    public void oneInput() {
        main = new MainActivity();
        main.set_Search("input1");
        assertEquals("input1", main.get_tag1());
    }

    @Test
    public void twoInput() {
        main = new MainActivity();
        main.set_Search("input1, input2");
        assertEquals("input1", main.get_tag1());
        assertEquals("input2", main.get_tag2());
    }

    @Test
    public void threeInput() {
        main = new MainActivity();
        main.set_Search("input1, input2, input3");
        assertEquals("input1", main.get_tag1());
        assertEquals("input2", main.get_tag2());
        assertEquals("input3", main.get_tag3());
    }

    @Test
    public void spacesInput() {
        main = new MainActivity();
        main.set_Search(" input1, input 2, i n p u t 3 ");
        assertEquals("input1", main.get_tag1());
        assertEquals("input2", main.get_tag2());
        assertEquals("input3", main.get_tag3());
    }

    @Test
    public void capitalInput() {
        main = new MainActivity();
        main.set_Search("Input1, In pUt 2, I n P u T 3 ");
        assertEquals("input1", main.get_tag1());
        assertEquals("input2", main.get_tag2());
        assertEquals("input3", main.get_tag3());
    }
}