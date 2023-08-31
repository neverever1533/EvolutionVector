package javaev.swing.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import cn.imaginary.toolkit.swing.tree.LayerNode;

import javaev.lang.ObjectUtils;

public class DefaultTreeModelUtils {
	private DefaultTreeModel defaultTreeModel;

	private String tag_bracket_close = ObjectUtils.tag_bracket_close;
	private String tag_bracket_open = ObjectUtils.tag_bracket_open;
	private String tag_null = ObjectUtils.tag_null;

	private DefaultMutableTreeNodeUtils treeNodeUtils = new DefaultMutableTreeNodeUtils();

	private ObjectUtils objectUtils = new ObjectUtils();

	public DefaultTreeModelUtils() {
	}

	public DefaultTreeModelUtils(DefaultTreeModel treeModel) {
		setModel(treeModel);
	}

	public DefaultTreeModel getModel() {
		return defaultTreeModel;
	}

	public DefaultMutableTreeNode getTreeNode(DefaultTreeModel treeModel, Object object) {
		if (null != treeModel) {
			return treeNodeUtils.getTreeNode((DefaultMutableTreeNode) treeModel.getRoot(), object);
		}
		return null;
	}

	public DefaultMutableTreeNode getTreeNode(Object object) {
		return getTreeNode(getModel(), object);
	}

	public DefaultTreeModel loadNode(DefaultTreeModel treeModel, List<LayerNode> list) {
		if (null != list) {
			DefaultMutableTreeNode nodeRoot;
			if (null == treeModel) {
				nodeRoot = new DefaultMutableTreeNode();
				treeModel = new DefaultTreeModel(nodeRoot);
			} else {
				nodeRoot = (DefaultMutableTreeNode) treeModel.getRoot();
			}
			LayerNode layerNode;
			DefaultMutableTreeNode node;
			DefaultMutableTreeNode nodeParent;
			DefaultMutableTreeNode nodeChild;
			String temp;
			Object object_super;
			Object object_this;
			Object object;
			for (Iterator<LayerNode> iterator = list.iterator(); iterator.hasNext();) {
				object = iterator.next();
				if (null != object && object instanceof LayerNode) {
					layerNode = (LayerNode) object;
					object_super = layerNode.getObjectSuper();
					if (null != object_super) {
						temp = object_super.toString();
						object_super = temp;
					} else {
						object_super = tag_null;
					}
					object_this = layerNode.getObject();
					if (null != object_this) {
						temp = object_this.toString();
						object_this = temp;
					} else {
						object_this = tag_null;
					}
					nodeParent = treeNodeUtils.getTreeNode(nodeRoot, object_super);
					nodeChild = treeNodeUtils.getTreeNode(nodeRoot, object_this);
					if(null == nodeParent) {
						nodeParent = nodeRoot;
					}
					if (null != nodeParent && null != nodeChild) {
						node = (DefaultMutableTreeNode) nodeChild.getParent();
						if (null != node) {
							object = node.getUserObject();
							if (null != object) {
								temp = object.toString();
								object = temp;
							} else {
								object = tag_null;
							}
							if (!object.equals(object_super)) {
								if (nodeParent.getAllowsChildren()) {
									treeModel.removeNodeFromParent(nodeChild);
//									treeModel.insertNodeInto(nodeChild, nodeParent, 0);
									treeModel.insertNodeInto(nodeChild, nodeParent, nodeParent.getChildCount());
									treeModel.nodeChanged(nodeRoot);
								}
							}
						}
					}
				}
			}
		}
		return treeModel;
	}

	public DefaultTreeModel loadNode(DefaultTreeModel treeModel, Properties properties) {
		System.out.println(1);
		if (null != properties) {
			System.out.println(2);
			int size = properties.size();
			System.out.print("size:");
			System.out.println(size);
			List<LayerNode> list = new ArrayList<>();
			Object object;
			for (int i = size - 1; i >= 0; i--) {
				object = properties.get(i);
				System.out.print(i);
				System.out.print(":");
				System.out.println(object);
				if (null != object) {
					if (object instanceof String) {
						object = objectUtils.getObject(object.toString());
					}
					if (null != object && object instanceof LayerNode) {
						list.add((LayerNode) object);
					}
				}
			}
			return loadNode(treeModel, list);
		}
		return null;
	}

	public DefaultTreeModel loadNode(Properties properties) {
		return loadNode(getModel(), properties);
	}

	public void setModel(DefaultTreeModel treeModel) {
		defaultTreeModel = treeModel;
	}

	public Properties storeNode() {
		return storeNode(getModel());
	}

	public Properties storeNode(DefaultTreeModel treeModel) {
		if (null != treeModel) {
			Properties properties = new Properties();
			properties = treeNodeUtils.storeNode((DefaultMutableTreeNode) treeModel.getRoot(), properties);
			return properties;
		}
		return null;
	}

	public String toString() {
		return toString(getModel());
	}

	public String toString(DefaultTreeModel treeModel) {
		if (null != treeModel) {
			DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) treeModel.getRoot();
			String treeNodeString = treeNodeUtils.toString(treeNode);
			StringBuffer sbuf = new StringBuffer();
			sbuf.append(treeModel.getClass().getName());
			sbuf.append(tag_bracket_open);
			sbuf.append(treeNodeString);
			sbuf.append(tag_bracket_close);
			return sbuf.toString();
		}
		return null;
	}
}
