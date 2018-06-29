package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class RootController implements Initializable{
	@FXML private Button btn1;
	@FXML private TextArea ta1;
	@FXML private TextArea ta2;
	private String url;
	private Run r;
//test brach1
	//master branch test!
	public void initialize(URL location,ResourceBundle re) {
		btn1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				url=ta1.getText();
				r = new Run(setTa);
				
				
				
				if(r.CheckUrl(url)) {
				r.setString(url);
				try {
					r.setUrl();
					r.start();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
					
				
				
				}
				else System.out.println("Àß¸øµÊ");
			}

		
			
		});
	}


	Handler setTa = new Handler() {

		@Override
		public void close() throws SecurityException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void flush() {
			// TODO Auto-generated method stub
			ta2.setText(Run.proce+"/"+Run.max);
		}

		@Override
		public void publish(LogRecord record) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	
}

