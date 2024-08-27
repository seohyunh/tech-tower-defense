import com.techtowerdefense.m2.components.EnemyComponent;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MonumentHealthTest {

    //This test was written by Courtney Taing to check if
    // the monument health decreases appropriately

    @Test
    public void monumentHealthDecreaseTest() {
        //Sets that the enemy has reached the monument and attacked
        boolean enemyReachedMonument = EnemyComponent.enemyAttack(10);

        //Sets monument health to 100
        int health = 100;

        //Checks that monument health has decreased upon attack
        if (enemyReachedMonument) {
            health = health - 10;
        }
        //Monument health should be 90 after singular enemy attacked
        assertEquals(90, health);


    }
}
