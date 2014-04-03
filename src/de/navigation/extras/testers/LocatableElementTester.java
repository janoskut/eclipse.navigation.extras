package de.navigation.extras.testers;

import org.eclipse.core.expressions.PropertyTester;

import de.navigation.extras.extension.ILocator;
import de.navigation.extras.locators.LocationManager;

public class LocatableElementTester extends PropertyTester {

	@Override
	public boolean test(final Object receiver, final String property, final Object[] args, final Object expectedValue) {
		ILocator locator = LocationManager.INSTANCE.getLocator(receiver);
		return locator != null;
	}
}
