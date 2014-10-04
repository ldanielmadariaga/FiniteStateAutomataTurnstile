import org.junit.Before;

import switchandtableturnstile.TableTurnstile;

public class TableTurnstileTest extends AbstractTurnstileTest {

	@Before
	public void setUp() {
		super.controllerSpoof = new TurnstileControllerSpoof();
		turnstile = new TableTurnstile(controllerSpoof);
	}
}
