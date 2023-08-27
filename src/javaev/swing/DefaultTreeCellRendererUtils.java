package javaev.swing;

import java.awt.Component;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import java.io.File;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import cn.imaginary.toolkit.image.ImageLayer;

import javaev.awt.Graphics2DUtils;

import javaev.imageio.ImageIOUtils;

import javaev.io.FileNameMapUtils;
import javaev.io.FileUtils;

public class DefaultTreeCellRendererUtils extends DefaultTreeCellRenderer {
	/**
	 * @author Sureness
	 */
	private static final long serialVersionUID = -22463755647610747L;

	private FileNameMapUtils fileNameMapUtils = new FileNameMapUtils();

	private FileUtils fileUtils = FileUtils.getInstance();

	private Graphics2DUtils graphics2dUtils = new Graphics2DUtils();

	private ImageIOUtils imageIOUtils = new ImageIOUtils();

	private List<ImageLayer> layerListRoot;

	private ImageIcon getImageIconScaled(BufferedImage image, double width, double height, float alpha,
			ImageObserver observer) {
		if (null != image) {
			double w = image.getWidth();
			double h = image.getHeight();
			if (width <= 0) {
				width = height * image.getWidth() / image.getHeight();// wr/w=hr/h
			}
			if (height <= 0) {
				height = width * image.getHeight() / image.getWidth();
			}
			AffineTransform xform = new AffineTransform();
			xform.scale(width / w, height / h);
			image = graphics2dUtils.filterImage(image, null, xform);
			ImageIcon imageIcon = new ImageIcon();
			imageIcon.setImage(image);
			return imageIcon;
		}
		return null;
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
			int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		DefaultMutableTreeNode treeNode;
		if (null != value && value instanceof DefaultMutableTreeNode) {
			treeNode = (DefaultMutableTreeNode) value;
			if (null != treeNode) {
				Object object = treeNode.getUserObject();
				if (null != object) {
					File file = null;
					ImageIcon imageIcon = null;
					int w = -1;
					int h = 16;
					float alpha = 1.0f;
					ImageObserver observer = null;
					ImageLayer imageLayer;
					BufferedImage image;
					if (object instanceof String) {
						String pathName = (String) object;
						imageLayer = fileUtils.getImageLayer(layerListRoot, pathName);
						if (null != imageLayer) {
							image = imageLayer.getImage();
							imageIcon = getImageIconScaled(image, w, h, alpha, observer);
						}
					} else if (object instanceof File) {
						file = (File) object;
						if (fileNameMapUtils.isFileImage(file)) {
							image = imageIOUtils.read(file);
							imageIcon = getImageIconScaled(image, w, h, alpha, observer);
						}
					} else if (object instanceof ImageLayer) {
						imageLayer = (ImageLayer) object;
						image = imageLayer.getImage();
						imageIcon = getImageIconScaled(image, w, h, alpha, observer);
					} else {
						imageIcon = (ImageIcon) this.getIcon();
					}
					if (null != imageIcon) {
						this.setIcon(imageIcon);
					}
				}
			}
		}
		return this;
	}

	public void setResourcesList(List<ImageLayer> layerList) {
		layerListRoot = layerList;
	}
}
