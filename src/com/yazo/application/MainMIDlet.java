package com.yazo.application;

import java.io.IOException;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class MainMIDlet extends MIDlet {
	public MainMIDlet(){
	}
	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub
	}
	protected void pauseApp() {
		// TODO Auto-generated method stub
	}
	protected void startApp() throws MIDletStateChangeException {
		Form form = new Form("--测试结果--");
		Display.getDisplay(this).setCurrent(form);
		
		DoSomeTest test = new DoSomeTest();
		try {
			test.doTest(form);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void quit(){
		notifyDestroyed();
	}

	
}
