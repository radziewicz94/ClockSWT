package pl.mradziewicz;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Canvas;

public class ClockFace {
	
	private ClockHandPosition clockHand[]= new ClockHandPosition[12]; 
	private int xcenter = 210;
	private int ycenter = 210;

	ClockFace(Canvas canvas){
		GC gc = new GC(canvas);
		gc.drawArc(10, 10, 400, 400, 0, 360);
	}
	 
	public ClockHandPosition[] draw(Canvas canvas) {
		 GC gc = new GC(canvas);
		 int x1,y1,x2,y2;
		 for(int i=0;i<12;i++)
	        {	
	            double angle = 2*Math.PI*(i/12.0);
	            x1 = (int)(200*Math.cos(angle))+210;
	            y1 = (int)(200*Math.sin(angle))+210;
	            x2 = (int)(180*Math.cos(angle))+210;
	            y2 = (int)(180*Math.sin(angle))+210;
	            gc.drawLine(x1,y1,x2,y2);
	            clockHand[i] = new ClockHandPosition(xcenter, ycenter, x2, y2);
	            
	        }
		 return clockHand;
	}


	public void mouseWheelPosition(int count, Canvas canvas) {
		GC gc = new GC(canvas);		
		int x1 = clockHand[count].getX1();
		int y1 = clockHand[count].getY1();
		int x2 = clockHand[count].getX2();
		int y2 = clockHand[count].getY2();
		 
		 redraw(gc, canvas);
		 gc.drawLine(x1, y1, x2, y2);
		
	}
	public void clockMousePosition(int x2, int y2, Canvas canvas) {
		GC gc = new GC(canvas);
		 for(int i = 0; i < 12; i++) {
			 if(clockHand[i].getX2() >= x2 - 50 && clockHand[i].getX2() <= x2 + 50 && clockHand[i].getY2() >= y2 - 50 && clockHand[i].getY2() <= y2 + 50)
					 {
				 redraw(gc, canvas);
				 gc.drawLine(xcenter, ycenter, clockHand[i].getX2(), clockHand[i].getY2());
			 }
		 }

	}
	
	private void redraw(GC gc, Canvas canvas) {
		canvas.redraw();
		 canvas.update();
		 gc.drawArc(10, 10, 400, 400, 0, 360);
		 draw(canvas);
	}
	
}













