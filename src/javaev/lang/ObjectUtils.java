package javaev.lang;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Properties;

import cn.imaginary.toolkit.swing.tree.LayerNode;

import javaev.awt.DimensionUtils;
import javaev.awt.PointUtils;
import javaev.awt.RectangleUtils;

public class ObjectUtils {
	public static String tag_array_list = "ArrayList";
	public static String tag_boolean = "Boolean";
	public static String tag_boolean_false = "false";
	public static String tag_boolean_true = "true";
	public static String tag_bracket_close = "]";
	public static String tag_bracket_close_shift = "}";
	public static String tag_bracket_open = "[";
	public static String tag_bracket_open_shift = "{";
	public static String tag_byte = "Byte";
	public static String tag_class = "java";
	public static String tag_class_toolkit = "cn.imaginary.toolkit";
	public static String tag_comma = ",";
	public static String tag_dimension = "Dimension";
	public static String tag_dimension_utils = "DimensionUtils";
	public static String tag_double = "Double";
	public static String tag_equals = "=";
	public static String tag_float = "Float";
	public static String tag_height = "height";
	public static String tag_int = "int";
	public static String tag_integer = "Integer";
	public static String tag_layer_node = "LayerNode";
	public static String tag_layer_node_id = LayerNode.tag_layer_node_id;
	public static String tag_layer_node_super = LayerNode.tag_layer_node_super;
	public static String tag_layer_node_this = LayerNode.tag_layer_node_this;
	public static String tag_linked_list = "LinkedList";
	public static String tag_list = "List";
	public static String tag_long = "Long";
	public static String tag_null = "null";
	public static String tag_object = "Object";
	public static String tag_point = "Point";
	public static String tag_point_utils = "PointUtils";
	public static String tag_rectangle = "Rectangle";
	public static String tag_rectangle_utils = "RectangleUtils";
	public static String tag_short = "Short";
	public static String tag_split = ", ";
	public static String tag_width = "width";
	public static String tag_x = "x";
	public static String tag_y = "y";

	public static boolean equals(Object object, Object object1) {
		if ((null == object && null == object1) || (null != object && object.equals(object1))) {
			return true;
		}
		return false;
	}

	public static boolean isObjectBasic(Object object) {
		if ((null != object) && (object instanceof Boolean || object instanceof Character || object instanceof Number
				|| object instanceof String)) {
			return true;
		}
		return false;
	}

	private String tag_dot = ".";
	private String tag_regex = "-*\\d+\\.*\\d*";

	public Object getObject(String stringObject) {
		if (null != stringObject) {
//			stringObject = stringObject.trim().toLowerCase();
			stringObject = stringObject.trim();
			String classType;
			String classProperties;
			int indexPrefix;
			int indexSuffix;
			double w = -1;
			double h = -1;
			double x = -1;
			double y = -1;
			int id = -1;
//			Object parent = null;
//			Object child = null;
			Object super_ = null;
			Object this_ = null;
			String entry;
//			String value;
//			String w_;
//			String h_;
//			String x_;
//			String y_;
//			String id_;
			String[] array;
			if (stringObject.contains(tag_bracket_open) && stringObject.contains(tag_bracket_close)
					&& stringObject.contains(tag_comma)) {
				indexPrefix = stringObject.indexOf(tag_bracket_open);
				indexSuffix = stringObject.indexOf(tag_bracket_close);
				classProperties = stringObject.substring(indexPrefix + 1, indexSuffix);
				if (stringObject.startsWith(tag_class) || stringObject.startsWith(tag_class_toolkit)) {
					classType = stringObject.substring(0, indexPrefix);
					array = classProperties.split(tag_comma);// ,
					if (null != array) {
						int length = array.length;
						if (classType.startsWith(tag_class_toolkit)) {
							if (classType.endsWith(tag_layer_node)) {
//								if (length == 3) {
//									entry = array[0];
//									if (entry.contains(tag_equals)) {
//										if (entry.startsWith(tag_layer_node_id)) {
//											id = Integer.valueOf(entry.substring(3));
//										}
//									}
//									entry = array[1];
//									if (entry.contains(tag_equals)) {
//										if (entry.startsWith(tag_layer_node_parent)) {
//											parent = entry.substring(7);
//										}
//									}
//									entry = array[2];
//									if (entry.contains(tag_equals)) {
//										if (entry.startsWith(tag_layer_node_child)) {
//											child = entry.substring(6);
//										}
//									}
//									return new LayerNode(id, parent, child);
//								}
								if (length == 3) {
									entry = array[0];
									if (entry.contains(tag_equals)) {
										if (entry.startsWith(tag_layer_node_id)) {
											id = Integer.valueOf(entry.substring(3));
										}
									}
									entry = array[1];
									if (entry.contains(tag_equals)) {
										if (entry.startsWith(tag_layer_node_super)) {
											super_ = entry.substring(6);
										}
									}
									entry = array[2];
									if (entry.contains(tag_equals)) {
										if (entry.startsWith(tag_layer_node_this)) {
											this_ = entry.substring(5);
										}
									}
									return new LayerNode(id, super_, this_);
								}
							}
						} else {
							if (length == 2) {
								entry = array[0];
								if (entry.contains(tag_equals)) {
									if (entry.startsWith(tag_width)) {
										w = Double.valueOf(entry.substring(6));
									} else if (entry.startsWith(tag_x)) {
										x = Double.valueOf(entry.substring(2));
									}
								}
								entry = array[1];
								if (entry.contains(tag_equals)) {
									if (entry.startsWith(tag_height)) {
										h = Double.valueOf(entry.substring(7));
									} else if (entry.startsWith(tag_y)) {
										y = Double.valueOf(entry.substring(2));
									}
								}
								if (w != -1 && h != -1) {
									if (classType.endsWith(tag_dimension_utils)) {
										DimensionUtils size = new DimensionUtils();
										size.setSize(w, h);
										return size;
									} else if (classType.endsWith(tag_dimension)) {
										Dimension size = new Dimension();
										size.setSize(w, h);
										return size;
									}
								} else if (x != -1 && y != -1) {
									if (classType.endsWith(tag_point_utils)) {
										PointUtils location = new PointUtils();
										location.setLocation(x, y);
										return location;
									} else if (classType.endsWith(tag_point)) {
										Point location = new Point();
										location.setLocation(x, y);
										return location;
									}
//									System.out.println(x);
//									System.out.println(y);
								}
							} else if (length == 4) {
								entry = array[0];
								if (entry.contains(tag_equals)) {
									if (entry.startsWith(tag_x)) {
										x = Double.valueOf(entry.substring(2));
									}
								}
								entry = array[1];
								if (entry.contains(tag_equals)) {
									if (entry.startsWith(tag_y)) {
										y = Double.valueOf(entry.substring(2));
									}
								}
								entry = array[2];
								if (entry.contains(tag_equals)) {
									if (entry.startsWith(tag_width)) {
										w = Double.valueOf(entry.substring(6));
									}
								}
								entry = array[3];
								if (entry.contains(tag_equals)) {
									if (entry.startsWith(tag_height)) {
										h = Double.valueOf(entry.substring(7));
									}
								}
//							System.out.println(x_);
//							System.out.println(y_);
//							System.out.println(w_);
//							System.out.println(h_);
//							System.out.println(x);
//							System.out.println(y);
//							System.out.println(w);
//							System.out.println(h);
								if (x != -1 && y != -1 && w != -1 && h != -1) {
									if (classType.endsWith(tag_rectangle_utils)) {
										RectangleUtils rectangle = new RectangleUtils();
										rectangle.set(x, y, w, h);
										return rectangle;
									} else if (classType.endsWith(tag_rectangle)) {
										Rectangle rectangle = new Rectangle();
										rectangle.setRect(x, y, w, h);
										return rectangle;
									}
								}
							}
						}
					}
				} else {
					if (classProperties.contains(tag_split)) {
						array = classProperties.split(tag_split);
						if (null != array) {
							int length = array.length;
//							Object[] oarr = new Object[length];
							ArrayList<Object> arrayList = new ArrayList<Object>();
							Object obj;
							for (int i = 0, iLength = length; i < iLength; i++) {
								obj = getObject(array[i]);
								if (null != obj) {
									arrayList.add(obj);
								}
							}
//							collectionsUtils.toList(arrayList, array);
							return arrayList;
						}
					}
				}
			} else {
//				if (stringObject.equalsIgnoreCase(tag_boolean_true)
//						|| stringObject.equalsIgnoreCase(tag_boolean_false)) {
//					return Boolean.valueOf(stringObject);
//				}
				if (stringObject.equalsIgnoreCase(tag_boolean_true)) {
					return true;
				} else if (stringObject.equalsIgnoreCase(tag_boolean_false)) {
					return false;
				} else if (stringObject.matches(tag_regex)) {
					if (stringObject.contains(tag_dot)) {
						return Double.valueOf(stringObject);
					} else {
						return Integer.valueOf(stringObject);
					}
				} else if (stringObject.equalsIgnoreCase(tag_null)) {
					return null;
				}
			}
		}
//		System.out.println(stringObject);
		return stringObject;
	}

	public Object getProperties(String stringProperties) {
		if (null != stringProperties) {
			stringProperties = stringProperties.trim();
			String classProperties;
			String[] arrayObject;
			String objectEntry;
			String objectKey;
			String objectValue;
			Object key = null;
			Object value = null;
			int index;
			if (stringProperties.startsWith(tag_bracket_open_shift)
					&& stringProperties.endsWith(tag_bracket_close_shift)) {
				Properties prop = new Properties();
				classProperties = stringProperties.substring(1, stringProperties.length() - 1);
				arrayObject = classProperties.split(tag_split);// (, )
				if (null != arrayObject) {
					for (int i = 0; i < arrayObject.length; i++) {
						objectEntry = arrayObject[i];
						if (null != objectEntry && objectEntry.contains(tag_equals)) {
							index = objectEntry.indexOf(tag_equals);
							objectKey = objectEntry.substring(0, index);
							key = getObject(objectKey);
							objectValue = objectEntry.substring(index + 1);
							value = getObject(objectValue);
							if (null != key && null != value) {
								prop.put(key, value);
							}
						}
					}
					return prop;
				}
			}
		}
		return null;
	}
}
