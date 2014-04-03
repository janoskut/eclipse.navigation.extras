package de.navigation.extras.locators;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.PlatformObject;
import org.osgi.framework.Bundle;

import de.navigation.extras.extension.ILocator;

/**
 * 
 * @author Janos
 * @version 28.05.2011 | 22:34:33
 * 
 */
public class LocationManager {

	public static final LocationManager INSTANCE = init();

	private static final LocationManager init() {
		return new LocationManager();
	}

	private LocationManager() {
	}

	private static final String EXTENSION_POINT_ID = "de.navigation.extras.locator";
	private static final String EXTENSION_POINT_ATTR_TYPE = "Type";
	private static final String EXTENSION_POINT_ATTR_LOCATOR = "Locator";

	public ILocator getLocator(final Object resource) {

		final IConfigurationElement[] configElements = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);

		if (!(resource instanceof PlatformObject)) {
			return null;
			
		}
		final IAdaptable adaptable = (IAdaptable) resource;
		

		for (final IConfigurationElement extPoint : configElements) {
			final String supportedTypeName = extPoint.getAttribute(EXTENSION_POINT_ATTR_TYPE);
			final Class<?> supportedType = resolveSupportedType(supportedTypeName, extPoint);

			final Object adapter = adaptable.getAdapter(supportedType);
			
			if (adapter != null) {
				try {
					final Object locatorInstance = extPoint.createExecutableExtension(EXTENSION_POINT_ATTR_LOCATOR);
					if (locatorInstance instanceof ILocator) {
						return (ILocator) locatorInstance;
					}
				} catch (final CoreException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	private Class<?> resolveSupportedType(final String typeName, final IConfigurationElement extPoint) {

		Class<?> resolvedClass;
		try {
			resolvedClass = Class.forName(typeName);
			if (resolvedClass != null) {
				return resolvedClass;
			}
		} catch (final ClassNotFoundException e) {
		}

		try {
			resolvedClass = getClass().getClassLoader().loadClass(typeName);
			if (resolvedClass != null) {
				return resolvedClass;
			}
		} catch (final ClassNotFoundException e) {
		}

		try {
			final String extPointName = extPoint.getDeclaringExtension().getContributor().getName();
			final Bundle bundle = Platform.getBundle(extPointName);
			resolvedClass = bundle.loadClass(typeName);
			if (resolvedClass != null) {
				return resolvedClass;
			}
		} catch (final ClassNotFoundException e) {
		}
		return null;
	}

}
