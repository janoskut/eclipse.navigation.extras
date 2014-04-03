package de.navigation.extras.extension;

import java.io.File;

import org.eclipse.core.runtime.IAdaptable;

/**
 * 
 * @author Janos
 * @version 28.05.2011 | 18:22:03
 * 
 */
public interface ILocator {

	/**
	 * Locate the given object in the file system.
	 * 
	 * If the object cannot be located, <code>null</code> must be returned.
	 * 
	 * @param object
	 *            The object to be located.
	 * @return The {@link File} object as a reference to the objcts location on the file system.
	 */
	public File locate(final IAdaptable object);

}
