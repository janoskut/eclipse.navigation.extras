package de.navigation.extras.testers;

import org.eclipse.core.expressions.PropertyTester;

import de.navigation.extras.extension.ILocator;
import de.navigation.extras.locators.LocationManager;

public class ShellOpenableElementTester extends PropertyTester {

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		ILocator locator = LocationManager.INSTANCE.getLocator(receiver);
		return locator != null;
	}

}
