import java.util.Random;
import java.util.logging.Logger;

public class CreeperNerf extends Plugin
{
  static final Logger log = Logger.getLogger("Minecraft");
  static int explosionRad = 4;
  static int maxAltitude = 55;
  static int damage = new Random().nextInt(10)+1;

  public void enable() {
    log.info("CreeperNerf version 1.1 updated by spenk is enabled");
  }

  public void disable() {
    log.info("CreeperNerf version 1.1 updated by spenk is disabled");
  }
  
  public void initialize()
  {
    CreeperNerf.CreeperNerfListener listener = new CreeperNerf.CreeperNerfListener();
    etc.getLoader().addListener(PluginLoader.Hook.EXPLODE, listener, this, PluginListener.Priority.MEDIUM);
    log.info("CreeperNerf version 1.1 updated by spenk is initialized");
  }

  private static boolean isInExplosionRadius(Player a, Block b)
  {
    return Math.sqrt(Math.pow(a.getX() - b.getX(), 2.0D) + Math.pow(a.getY() - b.getY(), 2.0D) + Math.pow(a.getZ() - b.getZ(), 2.0D)) <= explosionRad;
  }
  
  class CreeperNerfListener extends PluginListener {
    CreeperNerfListener() {
    }
    public boolean onExplode(Block block) {
    	if ((block.getStatus() == 2) && (block.getY() > CreeperNerf.maxAltitude)) {
        for (Player p : etc.getServer().getPlayerList()) {
          if (CreeperNerf.isInExplosionRadius(p, block)) {
            p.setHealth(p.getHealth() - CreeperNerf.damage);
            if (p.getHealth() < 1){
            	etc.getServer().messageAll(p.getName()+" got killed by an creeper");
            }
          }
        }
        return true;
      }
      return false;
    }
  }
}