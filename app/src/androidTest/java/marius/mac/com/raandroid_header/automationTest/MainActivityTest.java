package marius.mac.com.raandroid_header.automationTest;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

import com.robotium.solo.Solo;

import marius.mac.com.raandroid_header.MainActivity;
import marius.mac.com.raandroid_header.R;

/**
 * Created by marius on 05/04/16.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2 {
    private Solo solo;
    private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "MainActivity";
    private static final Class<?> launcherActivityClass;

    static {
        try {
            launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public MainActivityTest() {
        super(launcherActivityClass);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testSearchRestaurantsByPostCode() throws Exception {
        solo.assertCurrentActivity("Wrong Class", MainActivity.class);
        EditText etSearchField = (EditText) solo.getView(R.id.etSearchField);
        Button btSearch = (Button) solo.getView(R.id.btSearch);
        solo.clickOnView(etSearchField);
        solo.sleep(2000);
        solo.enterText(etSearchField, "E1W 1AW");
        assertTrue(solo.waitForText("E1W 1AW"));
        solo.clickOnView(btSearch);
        solo.sleep(5000);
    }


}