package state;

public class LockedTurnstileState implements TurnstileState {

	@Override
	public void Coin(Turnstile t) {
		t.setUnlocked();
		t.unlock();
	}

	@Override
	public void Pass(Turnstile t) {
		t.alarm();
	}

}
