package de.navigation.extras.handler;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;

import de.navigation.extras.extension.ILocator;
import de.navigation.extras.util.OsUtils;

/**
 * TODO: Implement Shell execution for OSX (Mac) TODO: Implement Shell execution for Linux
 * 
 * TODO: Shell SHOULD open in an independent process. For now, the Windows CMD is attached to the
 * JVM.
 * 
 * - List of os.name values: http://lopica.sourceforge.net/os.html <br>
 * - Thread on OS determination: http://stackoverflow.com/questions/228477/how-do-i-programmatically
 * -determine-operating-system-in-java<br>
 * - Thread on independent process execution:
 * http://stackoverflow.com/questions/931536/how-do-i-launch
 * -a-completely-independent-process-from-a-java-program<br>
 * 
 * @author Janos
 * @version 02.11.2011 | 16:43:16
 * 
 */
public class OpenShellHandler extends AbstractLocatableElementHandler {

	@Override
	public void execute(final ILocator locator, final IAdaptable element, final ExecutionEvent event) throws ExecutionException {
		final File locatable = locator.locate(element);
		openCmd(locatable);
	}

	private static void openCmd(final File locatable) {
		if (OsUtils.isWindows()) {
			openWindowsCmd(locatable);
		} else if (OsUtils.isUnix()) {
			// TODO: Impelement Linux shell execution
			System.err.println("Not implemented yet...");
		} else if (OsUtils.isMac()) {
			// TODO: Impelement Mac shell execution
			System.err.println("Not implemented yet...");
		} else {
			// TODO: Provide message, that other OS's are not supported
			System.err.println("Not implemented for Operating system: " + OsUtils.getOs());
		}
	}

	private static void openWindowsCmd(final File locatable) {

		final String path;
		if (locatable.isDirectory()) {
			path = locatable.getAbsolutePath();
		} else {
			path = locatable.getParentFile().getAbsolutePath();
		}

		new Thread() {
			@Override
			public void run() {
				try {
					Runtime.getRuntime().exec("cmd /c start /D" + path);
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	public static void main(final String[] args) {
		final String path = "D:\\Janos\\Development\\eclipse_workspace_tech\\de.eclipse.navigation.extras\\icons\\console.gif";
		openCmd(new File(path));
	}

}
