package edu.mccc.cos210;

import java.util.Vector;
import edu.mccc.cos210.coin.Coin;

public interface ICounter {
	public void analyze();
	public int getResult(Vector<Coin> coins);
}
