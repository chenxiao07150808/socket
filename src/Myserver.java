import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLEncoder;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import org.json.JSONException;
import org.json.JSONObject;



public class Myserver extends JFrame implements ActionListener{

	JTextArea jta;
	JTextField jtf;
	JLabel jl;
	JButton jb;
	JPanel jp;
	ServerSocket ss;
	Socket socket;
	Scanner sc;
	PrintWriter pw;
	public Myserver(){
		jta=new JTextArea();
		jtf=new JTextField(15);
		jb=new JButton("发送");
		
		jp=new JPanel();
		
		jl=new JLabel("自动回复");
		
		jp.add(jl);
		/*jp.add(jtf);
		jp.add(jb);*/
		
		JScrollPane jsp=new JScrollPane(jta);
	    this.add(jsp,BorderLayout.CENTER);
	    this.add(jp,BorderLayout.SOUTH);
	    
	    jb.addActionListener(this);
	    jtf.addActionListener(this);
	    
	    this.setTitle("服务器");
	    this.setSize(300,400);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
	    
	
		try {
		    
			ss=new ServerSocket(9000);
			System.out.println("等待连接");
			socket=ss.accept();
			System.out.println("已连接");
			sc=new Scanner(socket.getInputStream());
			pw=new PrintWriter(socket.getOutputStream(),true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JButton jb;
		while(true){
			String str=sc.nextLine();
			sentquestion(str);
			jta.append("客户端:"+str+"\r\n");
			try {
			      if(js.has("url")){
			    	pw.println(js.getString("text")+"  "+js.getString("url"));
					jta.append("图灵小机器人:"+js.getString("text")+"\r\n"+js.getString("url")+"\r\n");
					jtf.setText("");
			      }else{
			    	  pw.println(js.getString("text"));
						jta.append("图灵小机器人:"+js.getString("text")+"\r\n");
						jtf.setText("");
			      }
			    
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	
		
		
	}
	
	private void sentquestion(String str) {
		// TODO Auto-generated method stub
		String question=str;
		  String INFO;
		try {
			INFO = URLEncoder.encode(question, "utf-8");
			  answerQuestion(INFO);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	      
	}
	JSONObject js;
	private void answerQuestion(String iNFO) {
		// TODO Auto-generated method stub
		String APIKEY = "ff03f1424c5d48d5ac1698e5df51bc55"; 
		 String getURL = "http://www.tuling123.com/openapi/api?key=" +APIKEY +"&info=" + iNFO;
			String result;
			try {
			result = HttpUtils.doGet(getURL);
			/*System.out.println(result);*/
				    js=new JSONObject(result);
				   
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public static void main(String[] args){
		new Myserver();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	/*	if(e.getSource()==jb||e.getSource()==jtf){
			pw.println(jtf.getText());
			jta.append("服务器说"+jtf.getText()+"\r\n");
			jtf.setText("");
		}*/
		
	}

	
}
