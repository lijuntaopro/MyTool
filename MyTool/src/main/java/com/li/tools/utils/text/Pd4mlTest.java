package com.li.tools.utils.text;

import java.awt.Dimension;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;

public class Pd4mlTest {
	protected Dimension format = PD4Constants.A4;
	protected boolean landscapeValue = false;
	protected int topValue = 10;
	protected int leftValue = 10;
	protected int rightValue = 10;
	protected int bottomValue = 10;
	protected String unitsValue = "mm";
	protected String proxyHost = "";
	protected int proxyPort = 0;
	protected int userSpaceWidth = 780;

	private void runConverter(String urlstring, File output) throws IOException {

		if (urlstring.length() > 0) {
			if (!urlstring.startsWith("http://") && !urlstring.startsWith("file:")) {
				urlstring = "http://" + urlstring;
			}

			java.io.FileOutputStream fos = new java.io.FileOutputStream(output);

			if (proxyHost != null && proxyHost.length() != 0 && proxyPort != 0) {
				System.getProperties().setProperty("proxySet", "true");
				System.getProperties().setProperty("proxyHost", proxyHost);
				System.getProperties().setProperty("proxyPort", "" + proxyPort);
			}

			PD4ML pd4ml = new PD4ML();

			try {
				pd4ml.setPageSize(landscapeValue ? pd4ml.changePageOrientation(format) : format);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (unitsValue.equals("mm")) {
				pd4ml.setPageInsetsMM(new Insets(topValue, leftValue, bottomValue, rightValue));
			} else {
				pd4ml.setPageInsets(new Insets(topValue, leftValue, bottomValue, rightValue));
			}
			
			pd4ml.useTTF("java:fonts", true); 
			pd4ml.setDefaultTTFs("MSJH", "Arial", "Courier New"); 
			
			pd4ml.setHtmlWidth(userSpaceWidth);

			pd4ml.render(urlstring, fos);
		}
	}
	
	@Test
	public void test(){
		String url = "http://www.kerneler.com/freemarker2.3.23/";
		String outPath = "H:/pd4ml1.pdf";
		try {
			runConverter(url, new File(outPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
