package edu.kh.network.client.model.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientService {
	
	
	public void clientStart() {
		// 클라이언트는 일단 서버의 IP주소와 포트번호를 알아야 요청 가능
		// a. 현재 컴퓨터의 로컬 IP주소를 얻어오는 방법 =? String serverIP = InetAddress.getLocalHost().getHostAddress();
		// b. 어차피 같은 컴퓨터에서 통신하고 싶으므로 loopbackIP를 이용하면 된다. 
		String serverIP  = "127.0.0.1"; // 상수 스트링 값으로 현재 컴퓨터를 나타내는 IP이다
		
		// 다른 컴퓨터가 서버일 때는 그 컴퓨터의 IP주소를 작성해야 한다
		
		int port = 8500;
		
		Socket clientSocket = null;
		 
		InputStream is =null;
		OutputStream os = null;
		
		BufferedReader br = null;
		PrintWriter pw = null;
		
		try {
			System.out.print("client 입니다. ");
			clientSocket = new Socket(serverIP, port);
			
			// 2) 서버와의 입출력 스트림 오픈
			
			if(clientSocket !=null) {
				// 만약 서버에 연결이 성공 했으면
				
				is= clientSocket.getInputStream();
				os= clientSocket.getOutputStream();
				// 3) 보조 스트림을 통해 성능을 개선한다
				br = new BufferedReader(new InputStreamReader(is));
				pw = new PrintWriter(os);
				
				
				// 4) 스트림을 통해 읽고 쓴다
				// 서버에서 클라이언트에게 보낸 날짜 메시지를 한 줄 읽어와 콘솔창에 출력
				String serverMessage = br.readLine();
				System.out.println(serverMessage);
				
				// 이제 클라이언트가 메시지를 보내는 과정
				
				// 클라이언트에서 서버로 메시지 전송
				
				Scanner sc = new Scanner(System.in);
				System.out.println("서버로 전달할 메시지: ");
				String str = sc.nextLine();
				
				pw.println(str); // 위에서 입력한 str에 담긴 메시지를 클라이언트에서 서버쪽으로 출력
				pw.flush();							
			}
			
		
			
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			// 5) 통신 종료

			// 스트림과 소켓을 종료한다
			
			
			try {
				if(br!=null) br.close();
				if(pw!=null) pw.close();
				if(clientSocket!=null) clientSocket.close();
				

				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		// 1) 서버의 IP주소와 서버가 정한 포트번호를 매개변수로 하여 클라이언트용 소켓 객체 생성
		// 2) 서버와의 입출력 스트림 오픈
		// 3) 보조 스트림을 통해 성능 개선
		// 4) 스트림을 통해 읽고 쓰기
		
		
		
	}

}
