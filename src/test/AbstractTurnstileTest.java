import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import switchandtableturnstile.Turnstile;
import switchandtableturnstile.TurnstileEvent;
import switchandtableturnstile.TurnstileState;
import controller.TurnstileController;

public abstract class AbstractTurnstileTest {

	Turnstile turnstile;
	TurnstileControllerSpoof controllerSpoof;

	class TurnstileControllerSpoof implements TurnstileController {

		public Boolean lockCalled = false;
		public Boolean unlockCalled = false;
		public Boolean thankyouCalled = false;
		public Boolean alarmCalled = false;

		@Override
		public void lock() {
			lockCalled = true;
		}

		@Override
		public void unlock() {
			unlockCalled = true;
		}

		@Override
		public void thankyou() {
			thankyouCalled = true;
		}

		@Override
		public void alarm() {
			alarmCalled = true;
		}
	}

	@Test
	public void initialConditions() {
		assertEquals(TurnstileState.LOCKED, turnstile.getState());
	}

	@Test
	public void coinInLockedState() {
		turnstile.setState(TurnstileState.LOCKED);
		turnstile.handleEvent(TurnstileEvent.COIN);
		assertEquals(TurnstileState.UNLOCKED, turnstile.getState());
		assertTrue(controllerSpoof.unlockCalled);

	}

	@Test
	public void coinInUnlockedState() {
		turnstile.setState(TurnstileState.UNLOCKED);
		turnstile.handleEvent(TurnstileEvent.COIN);
		assertEquals(TurnstileState.UNLOCKED, turnstile.getState());
		assertTrue(controllerSpoof.thankyouCalled);

	}

	@Test
	public void passInLockedState() {
		turnstile.setState(TurnstileState.LOCKED);
		turnstile.handleEvent(TurnstileEvent.PASS);
		assertEquals(TurnstileState.LOCKED, turnstile.getState());
		assertTrue(controllerSpoof.alarmCalled);

	}

	@Test
	public void passInUnlockedState() {
		turnstile.setState(TurnstileState.UNLOCKED);
		turnstile.handleEvent(TurnstileEvent.PASS);
		assertEquals(TurnstileState.LOCKED, turnstile.getState());
		assertTrue(controllerSpoof.lockCalled);

	}
}
