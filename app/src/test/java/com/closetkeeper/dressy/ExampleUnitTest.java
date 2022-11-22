package com.closetkeeper.dressy;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

import com.closetkeeper.dressy.dao.Database;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Ignore
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void databaseConnection_isCorrect(){
        Database serverData = new Database();
        System.out.println("\nAccount email to user ID 9: " + serverData.fetchEmail(9));
    }
}