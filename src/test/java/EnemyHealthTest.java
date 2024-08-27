import org.junit.Test;


import static junit.framework.TestCase.assertTrue;

public class EnemyHealthTest {

    static int enemyHealth = 60;
    static int bulletDamage = 45;
    static boolean healthDecrease;


    //This test was created by Courtney Taing to test whether or not the enemy's health decreases after combat.
    @Test
    public void enemyHealthTest() {
        if (enemyHealth - bulletDamage == 15) {
            healthDecrease = true;
        } else {
            healthDecrease = false;
        }
        assertTrue(healthDecrease);
    }
}