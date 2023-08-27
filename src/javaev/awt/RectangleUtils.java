package javaev.awt;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javaev.lang.ObjectUtils;

public class RectangleUtils {
	private DimensionUtils dimension = new DimensionUtils();

	public int height;

	private double height_;

	private boolean isNull;

	private PointUtils point = new PointUtils();

	private String tag_bracket_close = ObjectUtils.tag_bracket_close;
	private String tag_bracket_open = ObjectUtils.tag_bracket_open;
	private String tag_comma = ObjectUtils.tag_comma;
	private String tag_equals = ObjectUtils.tag_equals;
	private String tag_height = ObjectUtils.tag_height;
	private String tag_width = ObjectUtils.tag_width;
	private String tag_x = ObjectUtils.tag_x;
	private String tag_y = ObjectUtils.tag_y;

	public int width;

	private double width_;

	public int x;

	private double x_;

	public int y;

	private double y_;

	public RectangleUtils() {
	}

	public RectangleUtils(double x, double y, double width, double height) {
		set(x, y, width, height);
	}

	public RectangleUtils(int x, int y, int width, int height) {
		set(x, y, width, height);
	}

	public RectangleUtils(Point point, Dimension dimension) {
		set(point, dimension);
	}

	public RectangleUtils(PointUtils point, DimensionUtils dimension) {
		set(point, dimension);
	}

	public RectangleUtils(Rectangle rectangle) {
		set(rectangle);
	}

	public RectangleUtils(RectangleUtils rectangle) {
		set(rectangle);
	}

	public Rectangle get() {
		if (isNull) {
			return null;
		} else {
			Rectangle rectangle = new Rectangle();
			rectangle.setRect(getX(), getY(), getWidth(), getHeight());
			return rectangle;
		}
	}

	public double getHeight() {
		if (isNull) {
			return -1;
		} else {
			if (height_ == 0) {
				height_ = height;
			}
			return height_;
		}
	}

	public PointUtils getLocation() {
		if (isNull) {
			return null;
		} else {
			if (null != point) {
				point.setLocation(getX(), getY());
			}
			return point;
		}
	}

	public DimensionUtils getSize() {
		if (isNull) {
			return null;
		} else {
			if (null != dimension) {
				dimension.setSize(getWidth(), getHeight());
			}
			return dimension;
		}
	}

	public double getWidth() {
		if (isNull) {
			return -1;
		} else {
			if (width_ == 0) {
				width_ = width;
			}
			return width_;
		}
	}

	public double getX() {
		if (isNull) {
			return -1;
		} else {
			if (x_ == 0) {
				x_ = x;
			}
			return x_;
		}
	}

	public double getY() {
		if (isNull) {
			return -1;
		} else {
			if (y_ == 0) {
				y_ = y;
			}
			return y_;
		}
	}

	public void set(double x, double y, double width, double height) {
		setLocation(x, y);
		setSize(width, height);
	}

	public void set(int x, int y, int width, int height) {
		setLocation(x, y);
		setSize(width, height);
	}

	public void set(Point point, Dimension dimension) {
		if (null == point && null == dimension) {
			isNull = true;
		} else {
			isNull = false;
			setLocation(point);
			setSize(dimension);
		}
	}

	public void set(PointUtils point, DimensionUtils dimension) {
		if (null == point && null == dimension) {
			isNull = true;
		} else {
			isNull = false;
			setLocation(point);
			setSize(dimension);
		}
	}

	public void set(Rectangle rectangle) {
		if (null != rectangle) {
			set(rectangle.getLocation(), rectangle.getSize());
			isNull = false;
		} else {
			isNull = true;
		}
	}

	public void set(RectangleUtils rectangle) {
		if (null != rectangle) {
			set(rectangle.getLocation(), rectangle.getSize());
			isNull = false;
		} else {
			isNull = true;
		}
	}

	public void setLocation(double x, double y) {
		this.x_ = x;
		this.y_ = y;
		setLocation((int) x, (int) y);
	}

	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setLocation(Point point) {
		if (null != point) {
			setLocation(point.getX(), point.getY());
		}
	}

	public void setLocation(PointUtils point) {
		if (null != point) {
			setLocation(point.getX(), point.getY());
		}
	}

	public void setSize(Dimension dimension) {
		if (null != dimension) {
			setSize(dimension.getWidth(), dimension.getHeight());
		}
	}

	public void setSize(DimensionUtils dimension) {
		if (null != dimension) {
			setSize(dimension.getWidth(), dimension.getHeight());
		}
	}

	public void setSize(double width, double height) {
		this.width_ = width;
		this.height_ = height;
		setSize((int) width, (int) height);
	}

	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public String toString() {
		if (isNull) {
			return null;
		} else {
			StringBuffer sbuf = new StringBuffer();
			sbuf.append(this.getClass().getName());
			sbuf.append(tag_bracket_open);
			sbuf.append(tag_x);
			sbuf.append(tag_equals);
			sbuf.append(getX());
			sbuf.append(tag_comma);
			sbuf.append(tag_y);
			sbuf.append(tag_equals);
			sbuf.append(getY());
			sbuf.append(tag_comma);
			sbuf.append(tag_width);
			sbuf.append(tag_equals);
			sbuf.append(getWidth());
			sbuf.append(tag_comma);
			sbuf.append(tag_height);
			sbuf.append(tag_equals);
			sbuf.append(getHeight());
			sbuf.append(tag_bracket_close);
			return sbuf.toString();
		}
	}
}
