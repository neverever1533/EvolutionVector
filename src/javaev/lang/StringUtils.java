package javaev.lang;

public class StringUtils {
	public static final String Line_Separator = System.getProperty("line.separator");

	public static int Radix_Binary = 2;
	public static int Radix_Decimal = 10;
	public static int Radix_Hex = 16;
	public static int Radix_Octonary = 8;
	public static int Signum_Double = 2;
	public static int Signum_Single = 1;

	public String toString(byte[] array) {
		return toString(array, 0);
	}

	private String toString(byte[] array, int type) {
		if (null != array) {
			StringBuffer sbuf = new StringBuffer();
			byte b;
			for (int i = 0; i < array.length; i++) {
				b = array[i];
				if (type == 0) {
					sbuf.append(b);
				} else if (type == 1) {
					sbuf.append((char) b);
				}
			}
			return sbuf.toString();
		}
		return null;
	}

	public String toStringChar(byte[] array) {
		return toString(array, 1);
	}
}
