package pl.mradziewicz;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ZegarApp {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(600,600);
		shell.setText("Clock");
		Canvas canvas = new Canvas(shell, SWT.NONE);
		canvas.setBounds(20, 20, 500, 500);
		
		shell.open();
		shell.layout();
		ClockFace clockFace = new ClockFace(canvas);
		
		canvas.addMouseWheelListener(new MouseWheelListener() {
			int count = 8;
			int negativeCount = -1;
			@Override
			public void mouseScrolled(MouseEvent e) {
				count = count + e.count;	
				if(count <= -1) {
					negativeCount = count + e.count;
					count = negativeCount * (-1) + 9;
				}
				if(count >=12) 
					count = 0;
				clockFace.mouseWheelPosition(count, canvas);				
			}
		});
		
		canvas.addMouseMoveListener(e -> {
				clockFace.clockMousePosition(e.x, e.y, canvas);
		});
		
		clockFace.draw(canvas);
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	

}
