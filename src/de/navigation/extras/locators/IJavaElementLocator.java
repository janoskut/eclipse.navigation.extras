package de.navigation.extras.locators;

import java.io.File;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jdt.core.IJavaElement;

import de.navigation.extras.extension.ILocator;

/**
 * @author Janos
 * @version 28.08.2011 | 18:27:28
 * 
 */
public class IJavaElementLocator implements ILocator {

	@Override
	public File locate(final IAdaptable adaptable) {
		final Object adapted = adaptable.getAdapter(IJavaElement.class);
		if (adapted == null) {
			return null;
		}
		if (adapted instanceof IJavaElement) {
			IJavaElement javaElement = (IJavaElement) adapted;
			return LocationUtil.locate(javaElement.getResource());
		}
		return null;
	}

}
