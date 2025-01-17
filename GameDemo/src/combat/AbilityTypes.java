package combat;

import java.util.ArrayList;
import java.util.Random;

import objects.GameObject;
import objects.projectiles.Projectile;
import objects.projectiles.ProjectileType;

public class AbilityTypes {

    private static final Random random = new Random();

    public static final Ability jarOfLightning = new Ability("Jar of Lightning", ProjectileType.LIGHTNING, 12, 5, 1) {
        private float angleIncrement;

        @Override
        protected void setupAbility(GameObject creator) {
            direction = -180 + (360f / this.numberOfProjectiles) / 2;
            angleIncrement = 360f / this.numberOfProjectiles;
        }

        @Override
        protected void updateAbility(GameObject creator, ArrayList<Projectile> projectiles) {
            projectiles.add(new Projectile(
                    creator,
                    creator.getDirection() + direction + angleIncrement * numberOfProjectilesShot,
                    this.projectileType));
        }
    };

    public static final Ability rock = new Ability("Rock", ProjectileType.ROCK, 1, 7, 15) {

        @Override
        protected void setupAbility(GameObject creator) {
            direction = random.nextInt(60, 120);
        }

        @Override
        protected void updateAbility(GameObject creator, ArrayList<Projectile> projectiles) {
            projectiles.add(new Projectile(creator, direction, this.projectileType));
        }
    };

    public static final Ability falcon = new Ability("Falcon Whisper", ProjectileType.FALCON, 1, 5.5f, 1) {
        @Override
        protected void setupAbility(GameObject creator) {
            direction = 90; // Direction adjusted as per ability logic
        }

        @Override
        protected void updateAbility(GameObject creator, ArrayList<Projectile> projectiles) {
            projectiles.add(new Projectile(creator, direction, this.projectileType));
        }
    };

    public static final Ability maskOfFlames = new Ability("Mask Of Flames", ProjectileType.FIRE, 3, 2f, 1) {
        float initialDirection;

        @Override
        protected void setupAbility(GameObject creator) {
            initialDirection = random.nextInt(360); // Randomized initial direction
        }

        @Override
        protected void updateAbility(GameObject creator, ArrayList<Projectile> projectiles) {
            direction = initialDirection + (15 * numberOfProjectilesShot);
            projectiles.add(new Projectile(creator, direction, this.projectileType));
        }
    };

    public static final Ability blast = new Ability("Blast", ProjectileType.BASIC, 1, 1f, 15) {
        @Override
        protected void setupAbility(GameObject creator) {
            direction = creator.getDirection(); // Direction based on creator's orientation
        }

        @Override
        protected void updateAbility(GameObject creator, ArrayList<Projectile> projectiles) {
            projectiles.add(new Projectile(creator, direction, this.projectileType));
        }
    };
}