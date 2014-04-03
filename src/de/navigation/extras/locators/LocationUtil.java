package de.navigation.extras.locators;

import java.io.File;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;

/**
 * 
 * @author Janos
 * @version 28.08.2011 | 18:59:12
 * 
 */
public class LocationUtil {
	
	public static File locate(final IPath path) {
		if (path == null) {
			return null;
		}
		return path.toFile();
	}

	public static File locate(IResource resource) {
		if (resource == null) {
			return null;
		}
		return locate(resource.getLocation());
	}

}
