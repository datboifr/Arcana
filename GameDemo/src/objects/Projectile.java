package objects;

public class Projectile extends GameObject {

    private float direction;
    private boolean canPierce;
    private ProjectileType type;
    private int cooldown;

    @SuppressWarnings("unused")
    private final GameObject creator;
    private int spriteIterator = 1;

    public Projectile(GameObject creator, float direction, ProjectileType projectileType) {
        super(creator.getX(), creator.getY(), (int) (projectileType.getSize() * creator.getProjectileDamage()),
                (int) (projectileType.getSize() * creator.getProjectileSize()));

        this.creator = creator;
        this.type = projectileType;
        this.canPierce = type.canPierce();
        this.cooldown = (int) type.getCooldown();
        this.direction = direction;

        this.contactDamage = type.getContactDamage() * creator.getProjectileDamage();
        this.speed = type.getSpeed() * creator.getProjectileSpeed();

        type.created(this);
    }

    @Override
    public void update() {
        this.cooldown--;
        if (cooldown == 0) {
                type.cooldownFinished(this);
                this.cooldown = (int) type.getCooldown();
            }
        type.update(this);
        if (type.animationLength != 0) {
            spriteIterator++;
            if (spriteIterator > type.getAnimationLength()) {
                spriteIterator = 1;
            }
        }
        this.spritePath = "projectiles/" + type.getSpritePath() + spriteIterator;
        setSprite(spritePath);
    }

    public void checkPositionOnScreen(int screenWidth, int screenHeight) {
        if (this.x < 0 || this.x > screenWidth || this.y < 0 || this.y > screenHeight) {
            this.isDead = true;
        }
    }

    public void hit() {
        type.hit(this, creator);
    }

    public float getDirection() {
        return this.direction;
    }

    public int getCooldown() {
        return this.cooldown;
    }


    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
