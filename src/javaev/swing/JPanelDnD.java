package javaev.swing;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;

import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

import java.io.File;
import java.io.IOException;

import java.util.List;

import javax.swing.JPanel;

public class JPanelDnD extends JPanel implements DropTargetListener {
	/**
	 * @author Sureness
	 */
	private static final long serialVersionUID = 3515265123617885169L;

	private List<File> fileList;

	public JPanelDnD() {
		new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE, this);
	}

	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
	}

	@Override
	public void dragExit(DropTargetEvent dte) {
	}

	@Override
	public void dragOver(DropTargetDragEvent dtde) {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void drop(DropTargetDropEvent dtde) {
		DataFlavor dataFlavor = DataFlavor.javaFileListFlavor;
		if (dtde.isDataFlavorSupported(dataFlavor)) {
			dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
			Transferable transferable = dtde.getTransferable();
			try {
				setDropResources((List<File>) transferable.getTransferData(dataFlavor));
			} catch (UnsupportedFlavorException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			dtde.dropComplete(true);
		}
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
	}

	public List<File> getDropResources() {
		return fileList;
	}

	public void setDropResources(List<File> list) {
		fileList = list;
	}
}
