package application;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import javafx.stage.Popup;

public class Run extends Thread{

	private String url;
	private BufferedImage img = null;
	private URL Url = null;
	private String list = null;
	private ArrayList<String> saveimg = null;
	private Handler h;
	
	static int proce=0;
	static int max=0;
	static boolean proceCheck=true;
	public Run(Handler h) {
		this.h=h;
	}
	
	@Override
	public void run()  {

		for(String i :saveimg) {
			
			
			URL realUrl=null;
			try {
				 realUrl = new URL(i);
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		String fileName = i.substring( i.lastIndexOf('/')+1, i.length() ); 
		String ext = i.substring( i.lastIndexOf('.')+1, i.length() );  
		
	    try {
			img = ImageIO.read(realUrl);
			System.out.println(ext);
			ImageIO.write(img, ext, new File("./test/"+fileName));
			
		} catch (IOException e) {
		
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    proce++;
	   
	    
	    h.flush();
		}
		
//		for(String i : saveimg) {
//			System.out.println(i);
//			}
		
		
		
		
	}
	
	public boolean CheckUrl(String u) {
		
		if(u.equals(""))return false;
		if(u.length()<4)return false;
		if(u.substring(0, 4).equals("http"))return true;
		if(u.substring(0,5).equals("https"))return true;
		
		return false;
	}
	
	public void setUrl() {
		
		try {
			Url = new URL(url);
		} catch (MalformedURLException e) {
			
			System.out.println("주소 틀림");
			
		}
		
		try {
			list = getSource(url);
			
		} catch (MalformedURLException e1) {	
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		saveimg = getTypedFile(list,"jpg");
		saveimg.addAll(getTypedFile(list,"gif"));
		saveimg.addAll(getTypedFile(list,"png"));
		max=saveimg.size();
		
		//if(saveimg.isEmpty())System.out.println("azzz");
		
	}
	public int getMax() {
		for(String i  : saveimg)
		System.out.println(i);
		return max;
	}
	
	public void setString(String u) {
		proceCheck=true;
		proce=0;
		max=0;
		url=u;
	}
	
	
	
	public String getSource(String url) throws MalformedURLException, IOException { 
		String output = ""; 
		BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream())); 
		String line; 
		while ((line = br.readLine()) != null) { 
		output += line; 
		} 
		return output; 
		} 
	
	
	public ArrayList<String> getTypedFile(String text, String typeRegex) {
		String regex = "\"(http://|https://)[^<>\"]+[.]" + typeRegex + "\"";
		Matcher m = Pattern.compile(regex).matcher(text); 
		ArrayList<String> output = new ArrayList<>(); 
		while (m.find()) { 
		output.add(m.group().replace("\"", "")); 
		} 
		return output; 
		}
	
	

}





