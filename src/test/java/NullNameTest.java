import com.techtowerdefense.m2.ui.scene.OptionView;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class NullNameTest {

    @Test
    public void nullNameTest() {
        //create a null String
        String s = null;
        //assert whether or not the String is null using isPlayerNameNull
        assertTrue(OptionView.isPlayerNameNull(s));

    }

}
