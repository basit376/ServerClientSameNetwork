import java.net.*;

import java.io.*;
class Server
{
   ServerSocket server;
   Socket socket;
   BufferedReader br;
   PrintWriter out;
    //Constractor:
    public Server(){

        try{

           server = new ServerSocket(7779);
           System.out.println("Server Is Ready To Accept Connection:");
           System.out.println("Waiting...");
           socket = server.accept();

           br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           out = new PrintWriter(socket.getOutputStream());

           startReading();
           startWriting();



        } catch (Exception e) {
            e.printStackTrace();

        }

    }
    // Start Reading [Method]
    public void startReading(){

        //thread-Read The Data

        Runnable r1=()->{

            System.out.println("reader started..");

            while(true){

                try{

                    String msg =br.readLine();
                    if(msg.equals("Exit")){
    
                        System.out.println("Client Terminated Chat");
                        socket.close();
                        break;

    
                    }
                    System.out.println("Client: "+ msg);
                } catch(Exception e){
                    e.printStackTrace();
                }



               
            }


        };

        new Thread(r1).start();
    }

    

    // Start wrting send[Method]
    public void startWriting(){

        //Thread Get Data From user And Send To Client

        Runnable r2=()->{

            System.out.println("Writer Started....");

            while(true){
                try {

                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));

                    String content = br1.readLine();

                    out.println(content);
                    out.flush();
                    
                } catch (Exception e) {
                    
                    e.printStackTrace();
                }
            }

        };

        new Thread(r2).start();


    }



    public static void main(String[] args){
        System.out.println("This is server .. Going To Start");

        new Server();
    }
}