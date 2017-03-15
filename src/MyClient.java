import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.xml.ws.handler.MessageContext.Scope;

public class MyClient extends JFrame implements ActionListener{

	
	 JTextArea jta;
	 JTextField jtf;
	 JButton jb;
	 JPanel jp;
	 
	 Socket socket;
	 Scanner sc;
	 PrintWriter pw;
	 
	 public MyClient(){
		 jta=new JTextArea();
		 jtf=new JTextField(15);
		 jb=new JButton("发送");
		 jp=new JPanel();
		 
		 jp.add(jtf);
		 jp.add(jb);
		 
		 JScrollPane jsp=new JScrollPane(jta);
		 this.add(jsp,BorderLayout.CENTER);
		 this.add(jp,BorderLayout.SOUTH);
		 
		 jb.addActionListener(this);
		 jtf.addActionListener(this);
		 
		 this.setTitle("客户端");
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 this.setSize(300,400);
		 this.setVisible(true);
		 
		 try {
			socket=new Socket("127.0.0.1",9000);
			
			sc=new Scanner(socket.getInputStream());
			
			pw=new PrintWriter(socket.getOutputStream(),true);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 while(true){
			 String str=sc.nextLine();
			 
			 jta.append("图灵机器人:"+str+"\r\n");
			 
		 }
	 }
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
  new MyClient();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb||e.getSource()==jtf){
			pw.println(jtf.getText());
			
			jta.append("会飞的鱼:"+jtf.getText()+"\r\n");
			
			jtf.setText("");
		}
	}

}
