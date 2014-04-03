package de.navigation.extras.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import de.navigation.extras.extension.ILocator;
import de.navigation.extras.locators.LocationManager;

/**
 * 
 * @author Janos
 * @version 02.11.2011 | 15:56:57
 * 
 */
abstract public class AbstractLocatableElementHandler extends AbstractHandler {

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		final IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getActiveMenuSelection(event);
		final Object firstElement = selection.getFirstElement();
		if (!(firstElement instanceof PlatformObject)) {
			return null;
		}
		final ILocator locator = LocationManager.INSTANCE.getLocator(firstElement);
		if (locator != null) {
			execute(locator, (IAdaptable) firstElement, event);
		}
		return null;
	}

	abstract public void execute(ILocator locator, IAdaptable element, ExecutionEvent event) throws ExecutionException;

}
