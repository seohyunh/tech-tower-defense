import com.techtowerdefense.m2.ui.scene.OptionView;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SpacesOnlyTest {

    @Test
    public void onlySpacesTest() {
        //create a string with spaces
        String s = "     ";
        //check that checkOnlySpace() returns true when string is blank
        assertTrue(OptionView.checkOnlySpace(s));
    }
}
