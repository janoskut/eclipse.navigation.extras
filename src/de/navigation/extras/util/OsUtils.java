package de.navigation.extras.util;

public class OsUtils {

	public static class OS {
		private static final String PROPERTY_KEY_OSNAME = "os.name";
		private static final String fullname = System.getProperty(PROPERTY_KEY_OSNAME);
		public static final String name = fullname.toLowerCase();
	}
	
	public static String getOs() {
		return OS.fullname;
	}

	public static boolean isWindows() {
		return OS.name.startsWith("win");
	}

	public static boolean isUnix() {
		return OS.name.startsWith("lin");
	}

	public static boolean isMac() {
		return OS.name.startsWith("mac");
	}
}
