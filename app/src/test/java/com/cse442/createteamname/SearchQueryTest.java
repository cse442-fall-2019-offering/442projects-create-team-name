package com.cse442.createteamname;

import com.cse442.createteamname.ui.results.ResultsFragment;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SearchQueryTest {

    private ResultsFragment rf;

    @Test
    public void oneInput() {
        rf = new ResultsFragment();
        rf.set_Search("input1");
        assertEquals("input1", rf.get_tag1());
    }

    @Test
    public void twoInput() {
        rf = new ResultsFragment();
        rf.set_Search("input1, input2");
        assertEquals("input1", rf.get_tag1());
        assertEquals("input2", rf.get_tag2());
    }

    @Test
    public void threeInput() {
        rf = new ResultsFragment();
        rf.set_Search("input1, input2, input3");
        assertEquals("input1", rf.get_tag1());
        assertEquals("input2", rf.get_tag2());
        assertEquals("input3", rf.get_tag3());
    }

    @Test
    public void spacesInput() {
        rf = new ResultsFragment();
        rf.set_Search(" input1, input 2, i n p u t 3 ");
        assertEquals("input1", rf.get_tag1());
        assertEquals("input2", rf.get_tag2());
        assertEquals("input3", rf.get_tag3());
    }

    @Test
    public void capitalInput() {
        rf = new ResultsFragment();
        rf.set_Search("Input1, In pUt 2, I n P u T 3 ");
        assertEquals("input1", rf.get_tag1());
        assertEquals("input2", rf.get_tag2());
        assertEquals("input3", rf.get_tag3());
    }
}