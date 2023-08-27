package javaev.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javaev.lang.ObjectUtils;

public class ArraysUtils {
	private static String tag_bracket_close = ObjectUtils.tag_bracket_close;
	private static String tag_bracket_open = ObjectUtils.tag_bracket_open;
	private static String tag_split = ObjectUtils.tag_split;
	
	public static boolean contains(Object[] array, Object object) {
		if (null != array) {
			List<Object> list = Arrays.asList(array);
			Object obj;
			for (Iterator<Object> iterator = list.iterator(); iterator.hasNext();) {
				obj = iterator.next();
				if (ObjectUtils.equals(obj, object)) {
					return true;
				}
			}
		}
		return false;
	}

	public static int indexOf(Object[] array, Object object) {
		if (null != array) {
			Object obj;
			for (int i = 0, iLength = array.length; i < iLength; i++) {
				obj = array[i];
				if (ObjectUtils.equals(obj, object)) {
					return i;
				}
			}
		}
		return -1;
	}

	public static int search(Object[] array, Object object) {
		if (null != array) {
			List<Object> list = Arrays.asList(array);
			return list.indexOf(object);
		}
		return -1;
	}

	public static int[] toArray(int[][] from, int[] to) {
		if (null != from && null != to) {
			int index = 0;
			int lenf = from.length;
			int lent = to.length;
			int[] arrf;
			for (int i = 0, ilength = lenf; i < ilength; i++) {
				arrf = from[i];
				if (null != arrf) {
					for (int j = 0, jlength = arrf.length; j < jlength; j++) {
						if (index < lent) {
							to[index] = from[i][j];
							index++;
						}
					}
				} else {
					if (index < lent) {
						to[index] = -1;
						index++;
					}
				}
			}
			return to;
		}
		return null;
	}

	public static Object[] toArray(Object[][] from, Object[] to) {
		if (null != from && null != to) {
			int index = 0;
			int lenf = from.length;
			int lent = to.length;
			Object[] arrf;
			for (int i = 0, ilength = lenf; i < ilength; i++) {
				arrf = from[i];
				if (null != arrf) {
					for (int j = 0, jlength = arrf.length; j < jlength; j++) {
						if (index < lent) {
							to[index] = from[i][j];
							index++;
						}
					}
				} else {
					if (index < lent) {
						to[index] = -1;
						index++;
					}
				}
			}
			return to;
		}
		return null;
	}

	public static String toString(boolean[][] arrays) {
		StringBuffer sbuf = new StringBuffer();
		sbuf.append(tag_bracket_open);
		for (int i = 0; i < arrays.length; i++) {
			if (i != 0) {
				sbuf.append(tag_split);
			}
			sbuf.append(Arrays.toString(arrays[i]));
		}
		sbuf.append(tag_bracket_close);
		return sbuf.toString();
	}

	public static String toString(byte[][] arrays) {
		StringBuffer sbuf = new StringBuffer();
		sbuf.append(tag_bracket_open);
		for (int i = 0; i < arrays.length; i++) {
			if (i != 0) {
				sbuf.append(tag_split);
			}
			sbuf.append(Arrays.toString(arrays[i]));
		}
		sbuf.append(tag_bracket_close);
		return sbuf.toString();
	}

	public static String toString(char[][] arrays) {
		StringBuffer sbuf = new StringBuffer();
		sbuf.append(tag_bracket_open);
		for (int i = 0; i < arrays.length; i++) {
			if (i != 0) {
				sbuf.append(tag_split);
			}
			sbuf.append(Arrays.toString(arrays[i]));
		}
		sbuf.append(tag_bracket_close);
		return sbuf.toString();
	}

	public static String toString(double[][] arrays) {
		StringBuffer sbuf = new StringBuffer();
		sbuf.append(tag_bracket_open);
		for (int i = 0; i < arrays.length; i++) {
			if (i != 0) {
				sbuf.append(tag_split);
			}
			sbuf.append(Arrays.toString(arrays[i]));
		}
		sbuf.append(tag_bracket_close);
		return sbuf.toString();
	}

	public static String toString(float[][] arrays) {
		StringBuffer sbuf = new StringBuffer();
		sbuf.append(tag_bracket_open);
		for (int i = 0; i < arrays.length; i++) {
			if (i != 0) {
				sbuf.append(tag_split);
			}
			sbuf.append(Arrays.toString(arrays[i]));
		}
		sbuf.append(tag_bracket_close);
		return sbuf.toString();
	}

	public static String toString(int[][] arrays) {
		StringBuffer sbuf = new StringBuffer();
		sbuf.append(tag_bracket_open);
		for (int i = 0; i < arrays.length; i++) {
			if (i != 0) {
				sbuf.append(tag_split);
			}
			sbuf.append(Arrays.toString(arrays[i]));
		}
		sbuf.append(tag_bracket_close);
		return sbuf.toString();
	}

	public static String toString(long[][] arrays) {
		StringBuffer sbuf = new StringBuffer();
		sbuf.append(tag_bracket_open);
		for (int i = 0; i < arrays.length; i++) {
			if (i != 0) {
				sbuf.append(tag_split);
			}
			sbuf.append(Arrays.toString(arrays[i]));
		}
		sbuf.append(tag_bracket_close);
		return sbuf.toString();
	}

	public static String toString(Object[][] arrays) {
		StringBuffer sbuf = new StringBuffer();
		sbuf.append(tag_bracket_open);
		for (int i = 0; i < arrays.length; i++) {
			if (i != 0) {
				sbuf.append(tag_split);
			}
			sbuf.append(Arrays.toString(arrays[i]));
		}
		sbuf.append(tag_bracket_close);
		return sbuf.toString();
	}

	public static String toString(short[][] arrays) {
		StringBuffer sbuf = new StringBuffer();
		sbuf.append(tag_bracket_open);
		for (int i = 0; i < arrays.length; i++) {
			if (i != 0) {
				sbuf.append(tag_split);
			}
			sbuf.append(Arrays.toString(arrays[i]));
		}
		sbuf.append(tag_bracket_close);
		return sbuf.toString();
	}
}
