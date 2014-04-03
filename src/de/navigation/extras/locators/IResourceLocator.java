package de.navigation.extras.locators;

import java.io.File;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;

import de.navigation.extras.extension.ILocator;

/**
 * 
 * @author Janos
 * @version 28.05.2011 | 22:36:14
 * 
 */
public class IResourceLocator implements ILocator {

	@Override
	public File locate(final IAdaptable object) {

		final Object adapter = object.getAdapter(IResource.class);
		if (adapter instanceof IResource) {

			final IResource res = (IResource) adapter;
			final File file = res.getLocation().toFile();
			return file;
		}

		return null;
	}

}
