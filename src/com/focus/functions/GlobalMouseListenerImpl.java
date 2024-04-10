package com.focus.functions;

import java.awt.AWTException;
import java.awt.Robot;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseMotionListener;

public class GlobalMouseListenerImpl{
	
	private static Robot robot;
	private static MyMouseListener mouseListener = new MyMouseListener();
	
	
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
        System.out.println("Global MouseListener called!");
	}
	
	public void disableGlobalMouseListener() {
		GlobalScreen.removeNativeMouseMotionListener(mouseListener);
		System.out.println("Global MouseListener deactivated!");
	}
	
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
