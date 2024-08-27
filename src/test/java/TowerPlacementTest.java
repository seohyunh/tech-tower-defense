import com.techtowerdefense.m2.GameDataController;
import com.techtowerdefense.m2.TowerManager;


import javafx.geometry.Point2D;
import org.junit.Test;

import static org.junit.Assert.*;

public class TowerPlacementTest {

    // Implemented by Ming-Ying Li
    private TowerManager towermanager = TowerManager.INSTANCE;
    private GameDataController gdcsingleton = GameDataController.getInstance();

    @Test
    public void testCannotPlaced() {

        Point2D position =
                new Point2D(gdcsingleton.getappwidth() / 2.0,
                        4.1 * gdcsingleton.getappheight() / 6.0);

        assertFalse(towermanager.notOnRoad(position));

    }

    @Test
    public void testCanPlaced() {
        Point2D position =
                new Point2D(gdcsingleton.getappwidth() / 2.0,
                        5.3 * gdcsingleton.getappheight() / 6.0);
        assertTrue(towermanager.notOnRoad(position));

    }
}
