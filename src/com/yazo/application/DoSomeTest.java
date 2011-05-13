package com.yazo.application;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Form;

public class DoSomeTest {
	public void doTest(Form form) throws IOException {
		String base = "http://bk-b.info/reader/pages/";
		String path = "books/1/catalog";
		
		InputStream is = null;
        HttpConnection conn = null;

        System.out.println("Start...");
        form.append("Start.");
        try {
             conn = (HttpConnection)Connector.open(base+path, Connector.READ_WRITE);
        } catch (Exception ex) {
		    System.out.println(ex);
		    ex.printStackTrace();
            return;
        }
        System.out.println("  >opened");
        form.append("  >opened.");

        try {
        	conn.setRequestProperty("Accept-Charset", "UTF-8");
        	conn.setRequestMethod(HttpConnection.GET);
            if (conn.getResponseCode() == HttpConnection.HTTP_OK) {
            	System.out.println("  >http ok.");
            	form.append("  >http ok");
                is = conn.openInputStream();
                final int MAX_LENGTH = 10000;
                final int BUF_SIZE = 128;
                byte[] buf = new byte[BUF_SIZE];
                int total = 0;
                while (total < MAX_LENGTH) {
                    int count = is.read(buf, 0, BUF_SIZE);
                    if (count < 0) {
                        break;
                    }
                    total += count;
                    System.out.println("read:" + count);
                    form.append("read:" + count);
                    //String reply = new String(buf,"UTF-8");
                    //System.out.println("[" + reply + "]");
                }
                System.out.println("  >readed.");
                form.append("  >read.");
                is.close();
                System.out.println("  >closed");
                form.append("  >close");
            } else {
                System.out.println("Failed: error " + conn.getResponseCode() +
                                "\n" + conn.getResponseMessage());
            }
            
            // Get the response code and print all the headers
            System.out.println("Response code = " + conn.getResponseCode());
            System.out.println("Response message = " + conn.getResponseMessage());
            for (int i = 0; ; i++) {
                String key = conn.getHeaderFieldKey(i);
                String value = conn.getHeaderField(i);
                if (key == null) {
                    // Reached last header
                    break;
                }
                System.out.println(key + " = " + value);
            }
            conn.close();
        } catch (IOException ex) {
		    System.out.println(ex);
		    ex.printStackTrace();
            return;
        } finally {
            // Close open streams
           try {
                if (is != null) {
                    is.close();
                    is = null;
                }
            } catch (IOException ex1) {
            }
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (IOException ex1) {
            }
        }
	}
}
