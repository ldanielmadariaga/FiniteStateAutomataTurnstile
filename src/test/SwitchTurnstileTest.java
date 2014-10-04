import org.junit.Before;

import switchandtableturnstile.SwitchTurnstile;

public class SwitchTurnstileTest extends AbstractTurnstileTest {

	@Before
	public void setUp() {
		super.controllerSpoof = new TurnstileControllerSpoof();
		turnstile = new SwitchTurnstile(controllerSpoof);
	}
}
