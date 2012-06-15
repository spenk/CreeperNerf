import java.util.Random;

public class CreeperNerfListener extends PluginListener{
	   static int explosionRad;
	   int max;
	  int maxAltitude = 55;
	  int damage = new Random().nextInt(max);
	  private PropertiesFile properties = new PropertiesFile("plugins/config/CreeperNerf.properties");
	  public void loadConfiguration() {
		  explosionRad = properties.getInt("Max-Creeper-Damage-Radius",5);
		  max = properties.getInt("Max-Damage-Amount-(in HP)", 10);
		  }
	  private static boolean isInExplosionRadius(Player a, Block b)
	  {
	    return Math.sqrt(Math.pow(a.getX() - b.getX(), 2.0D) + Math.pow(a.getY() - b.getY(), 2.0D) + Math.pow(a.getZ() - b.getZ(), 2.0D)) <= explosionRad;
	  }
	    public boolean onExplode(Block block) {
	    	if ((block.getStatus() == 2) && (block.getY() > maxAltitude)) {
	        for (Player p : etc.getServer().getPlayerList()) {
	          if (isInExplosionRadius(p, block)) {
	            p.setHealth(p.getHealth() - damage);
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