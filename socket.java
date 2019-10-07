Socket传输:
1. TCP传输协议

import java.net.*;
import java.io.*;

/**
 * socket编程之：简单socket server
 * 
 * @author chengwei.lcw 2016-11-27
 */
public class SocketServer {
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public SocketServer() {
        try {
            serverSocket = new ServerSocket(9999);
            while (true) {
                // 此处会阻塞，后面会讲到nio的作用 
                socket = serverSocket.accept();
                in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                String line = in.readLine();
                // 打印出来看看结果
                System.out.println("line:" + line);
                
                // 返回给client端，通知我已收到数据
                out.println("you input is :" + line);
                out.close();
                in.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SocketServer();
    }
}

import java.io.*;
import java.net.*;

/**
 * socket编程之：简单socket client
 * 
 * @author chengwei.lcw 2016-11-27
 */
public class SocketClient {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public SocketClient() {
        try {
            socket = new Socket("127.0.0.1", 9999);
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            // 向服务端写数据
            BufferedReader line = new BufferedReader(new InputStreamReader(
                    System.in));

            out.println(line.readLine());
            line.close();
            // 打印出来服务端发回来的回执
            System.out.println(in.readLine());
            
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SocketClient();
    }
}

2.序列化方式
依然先启动server，再启动client，server端控制台输出：
Server: name=chengwei.lcw, age=28

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket编程之：传输对象server
 * 
 * @author chengwei.lcw 2016-11-27
 */
public class SocketObjectSever {

    private ServerSocket serverSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public SocketObjectSever() {
        try {
            serverSocket = new ServerSocket(9999);

            while (true) {
                // 此处会阻塞，后面会讲到nio的作用
                Socket socket = serverSocket.accept();

                in = new ObjectInputStream(socket.getInputStream());
                out = new ObjectOutputStream(socket.getOutputStream());

                // 接收server端传来的数据，并转为Student
                Student student = (Student) in.readObject();
                // 重写了toString()方法，打印出来看看
                System.out.println("Server: " + student);

                // 返回给client端，通知我已收到数据
                out.writeObject("yes client, I receive");
                out.flush();

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SocketObjectSever();
    }
}


//socket客户端
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

 
/**
 * socket编程之：传输对象client
 * 
 * @author chengwei.lcw 2016-11-27
 */
public class SocketObjectClient {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
     
    public SocketObjectClient() {
        try {
            socket = new Socket("127.0.0.1",9999);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
             
            /*
             * 建一个student对象，用于传输
             */
            Student s = new Student("chengwei.lcw", 28);
            
            // 把对象写到管道中，client端进行接收
            out.writeObject(s);
            out.flush();
             
            String receive = (String) in.readObject();
            System.out.println("Client Receive :"+receive);
             
            in.close();
            out.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
     
    public static void main(String[] args) {
        new SocketObjectClient();
    }
 
}

import java.io.Serializable;
/**
 * socket编程之：要进行传输的类，需要继承Serializable接口
 * 
 * @author chengwei.lcw 2016-11-27
 * 
 */
public class Student implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String toString() {
        return "name=" + this.name + ", age=" + this.age; 
    }
}