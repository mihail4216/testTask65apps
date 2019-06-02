package com.misendem.test65apps;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.misendem.test65apps.models.Employee;
import com.misendem.test65apps.models.entity.EmployeeEntity;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.misendem.test65apps", appContext.getPackageName());
    }
    @Test
    public void testFormatDate(){
        String d1 = "1998-20-01";
        String e1 = "20.01.1998";
//        String d2 = "1998-02-01";
//        String d3 = "1998-02-20";
        String date1 = EmployeeEntity.formatDate(d1);
//        String date2 = EmployeeEntity.formatDate(d2);
//        String date3 = EmployeeEntity.formatDate(d3);

        assertEquals(e1,date1);
    }
}
