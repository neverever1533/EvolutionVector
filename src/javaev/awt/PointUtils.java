package javaev.awt;

import java.awt.Point;

import javaev.lang.ObjectUtils;

public class PointUtils {
	private boolean isNull;

	private String tag_bracket_close = ObjectUtils.tag_bracket_close;
	private String tag_bracket_open = ObjectUtils.tag_bracket_open;
	private String tag_comma = ObjectUtils.tag_comma;
	private String tag_equals = ObjectUtils.tag_equals;
	private String tag_x = ObjectUtils.tag_x;
	private String tag_y = ObjectUtils.tag_y;

	public int x;

	private double x_;

	public int y;

	private double y_;

	public PointUtils() {
	}

	public PointUtils(double x, double y) {
		setLocation(x, y);
	}

	public PointUtils(int x, int y) {
		setLocation(x, y);
	}

	public PointUtils(Point point) {
		set(point);
	}

	public PointUtils(PointUtils point) {
		set(point);
	}

	public Point get() {
		if (isNull) {
			return null;
		} else {
			Point point = new Point();
			point.setLocation(getX(), getY());
			return point;
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

	public void set(Point point) {
		if (null != point) {
			setLocation(point.getX(), point.getY());
			isNull = false;
		} else {
			isNull = true;
		}
	}

	public void set(PointUtils point) {
		if (null != point) {
			setLocation(point.getX(), point.getY());
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
			sbuf.append(tag_bracket_close);
			return sbuf.toString();
		}
	}
}
