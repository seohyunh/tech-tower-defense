import com.techtowerdefense.m2.components.EnemyComponent;

import org.junit.Test;


import static org.junit.Assert.assertTrue;

public class EnemyAttackTest {

    //This test was written by Courtney Taing to check that the enemy's attack value is correct
    @Test
    public void enemyAttackValueTest() {

        //Sets the enemy attack value to 10
        int attackValue = 10;

        //Asserts that the enemy attack occurred with the correct value
        assertTrue(EnemyComponent.enemyAttack(attackValue));

    }

}
