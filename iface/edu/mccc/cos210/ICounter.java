package edu.mccc.cos210;

import edu.mccc.cos210.coin.Coin;

public interface ICounter {
	public void analyze();
	public int getResult(Coin[] coins);
}
