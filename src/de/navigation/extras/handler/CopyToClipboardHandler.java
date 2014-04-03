package de.navigation.extras.handler;

import java.io.File;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;

import de.navigation.extras.extension.ILocator;

public class CopyToClipboardHandler extends AbstractLocatableElementHandler {

	@Override
	public void execute(final ILocator locator, IAdaptable element, ExecutionEvent event) throws ExecutionException {
	
		final File location = locator.locate(element);
		
		copyLocationToClipboard(location.getAbsolutePath());

	}
	
	private void copyLocationToClipboard(final String text) {
		Display display = Display.getCurrent();
		if (display == null) {
			display = Display.getDefault();
		}
		final Clipboard cb = new Clipboard(display);
		cb.setContents(new Object[] { text }, new Transfer[] { TextTransfer.getInstance() });
	}

}
