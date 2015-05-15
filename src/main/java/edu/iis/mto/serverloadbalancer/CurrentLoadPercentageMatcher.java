package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> {
	
	private double expectedLoadPercentage;

	public static Matcher<? super Server> hasLoadPercentage(double expectedLoadPercentage) {
		return new CurrentLoadPercentageMatcher(expectedLoadPercentage);
	}
	
	public CurrentLoadPercentageMatcher(double expectedLoadPercentage) {
		this.expectedLoadPercentage = expectedLoadPercentage;
	}

	public void describeTo(Description description) {
		description.appendText("a server with load percentage of ")
				.appendValue(expectedLoadPercentage);
	}

	@Override
	protected void describeMismatchSafely(Server item,
			Description description) {
		description.appendText("a server with load percentage of ")
		.appendValue(item.getCurrentLoadPecentage());
	}

	@Override
	protected boolean matchesSafely(Server server) {
		return server.getCurrentLoadPecentage() == expectedLoadPercentage || Math.abs(server.getCurrentLoadPecentage() - expectedLoadPercentage) < 0.01d;
	}
	
}
