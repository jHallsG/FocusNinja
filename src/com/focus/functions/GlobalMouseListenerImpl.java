package com.focus.functions;

import java.awt.AWTException;
import java.awt.Robot;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseMotionListener;

/*
 * The class used for 'kidnapping' the mouse
 */
public class GlobalMouseListenerImpl{
	/*
	 * The class used for controlling mouse location
	 */
	private static Robot robot;
	/*
	 * Class implements the NativeMouseMotionListener
	 */
	private static MyMouseListener mouseListener = new MyMouseListener();
	
	/*
	 * Instantiates the robot class. registerNativeHook enables/disables the mouse listener
	 */
	public GlobalMouseListenerImpl() {
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {
			e.printStackTrace();
		}
		
		try {
			robot = new Robot();
		} catch(AWTException e) {
			e.printStackTrace();
		}
	}
	
	public  void runGlobalMouseListener(){
		GlobalScreen.addNativeMouseMotionListener(mouseListener);
	}
	
	public void disableGlobalMouseListener() {
		GlobalScreen.removeNativeMouseMotionListener(mouseListener);
	}
	
	/*
	 * Sub-class that tells what the mouse listener should do whenever it detects movement.
	 * Robot would take over mouse movement and move it at the corner of the screen
	 */
	private static class MyMouseListener implements NativeMouseMotionListener{

		 @Override
         public void nativeMouseMoved(NativeMouseEvent event) {
             robot.mouseMove(0, 0);
         }

         @Override
         public void nativeMouseDragged(NativeMouseEvent event) {
        	 robot.mouseMove(0, 0);
         }
	}
}
