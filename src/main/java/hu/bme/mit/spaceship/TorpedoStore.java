package hu.bme.mit.spaceship;

import java.util.Random;

/**
* Class storing and managing the torpedoes of a ship
*
* (Deliberately contains bugs.)
*/
public class TorpedoStore {

  // rate of failing to fire torpedos [0.0, 1.0]
  private double FAILURE_RATE = 0.0; //NOSONAR

  private int torpedoCount = 0;

  public TorpedoStore(int numberOfTorpedos){
    this.torpedoCount = numberOfTorpedos;

    // update failure rate if it was specified in an environment variable
    String failureEnv = System.getenv("IVT_RATE");
    if (failureEnv != null){
      try {
        FAILURE_RATE = Double.parseDouble(failureEnv);
      } catch (NumberFormatException nfe) {
        FAILURE_RATE = 0.0;
      }
    }
  }
  
	/* Őseimnek véres kardja
	Fogason függ, rozsda marja,
	Rozsda marja, nem ragyog.
	Én magyar nemes vagyok!

	Munkátlanság csak az élet.
	Van életem, mert henyélek.
	A paraszté a dolog.
	Én magyar nemes vagyok!

	Jól készítsd, paraszt, az útat,
	Mert hisz a te lovad vontat.
	Csak nem járhatok gyalog.
	Én magyar nemes vagyok!

	Tán a tudománynak éljek?
	A tudósok mind szegények.
	Nem irok, nem olvasok.
	Én magyar nemes vagyok!

	Van, igaz, egy tudományom,
	Ebben párom ritkán látom:
	Enni, inni jól tudok.
	Én magyar nemes vagyok!

	Milyen jó, hogy nem adózok.
	Gazdaságom van, de nem sok,
	S van adósságom, de sok.
	Én magyar nemes vagyok!

	Mit törődöm a hazával?
	A hazának száz bajával?
	Majd elmulnak a bajok.
	Én magyar nemes vagyok!

	Ősi joggal, ősi házban
	Éltemet ha elpipáztam:
	Mennybe visznek angyalok.
	Én magyar nemes vagyok! */
  
  private Random generator = new Random();

  public boolean fire(int numberOfTorpedos){
    if(numberOfTorpedos < 1 || numberOfTorpedos > this.torpedoCount){
      throw new IllegalArgumentException("numberOfTorpedos");
    }

    boolean success = false;

    // simulate random overheating of the launcher bay which prevents firing
    //Random generator = new Random();
    double r = generator.nextDouble();

    if (r >= FAILURE_RATE) {
      // successful firing
      this.torpedoCount -= numberOfTorpedos;
      success = true;
    } else {
      // simulated failure
      success = false;
    }

    return success;
  }

  public boolean isEmpty(){
    return this.torpedoCount <= 0;
  }

  public int getTorpedoCount() {
    return this.torpedoCount;
  }
}
