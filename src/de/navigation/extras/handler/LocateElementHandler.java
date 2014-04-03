package de.navigation.extras.handler;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;

import de.navigation.extras.extension.ILocator;

public class LocateElementHandler extends AbstractLocatableElementHandler {

	@Override
	public void execute(final ILocator locator, final IAdaptable element, final ExecutionEvent event) throws ExecutionException {
		locate(locator.locate(element));
	}

	private void locate(final File fileOrDir) {
		if (fileOrDir == null || !fileOrDir.exists()) {
			return;
		}
		if (fileOrDir.isDirectory()) {
			locateDir(fileOrDir);
			return;
		}
		locateDir(fileOrDir.getParentFile());
	}

	private void locateDir(final File dir) {
		if (Desktop.isDesktopSupported()) {
			final Desktop desktop = Desktop.getDesktop();
			try {
				desktop.open(dir);
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
	}

}
