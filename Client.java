import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws  IOException, ClassNotFoundException, InterruptedException{
        // Initialize the variables
        InetAddress host = InetAddress.getLocalHost();
        Socket socket ;
        ObjectOutputStream oos ;
        ObjectInputStream ois;
        Scanner sc = new Scanner(System.in);
        // Get the input
        System.out.println("Enter your desire between(Add, Subtract, Divide, Multiply, Sin, Cos, Tan, Cot): ");
        System.out.println("Input format: (operand number1 number 2");
        System.out.println("For terminate the program, enter exit");
        String message = sc.nextLine();
        // As long as the user does not enter "exit", the program works
        while (!message.equals("exit")){
            // Set the port
            socket = new Socket(host.getHostName(), 3000);
            oos = new ObjectOutputStream(socket.getOutputStream());
            // Send the message to the local server
            oos.writeObject(message);
            // Get the response from the server
            ois = new ObjectInputStream(socket.getInputStream());
            String response = (String) ois.readObject();
            System.out.println(response);
            // Input again from the user
            System.out.println("Enter your desire :");
            message = sc.nextLine();
            //close resources
            ois.close();
            oos.close();
            Thread.sleep(100);
        }
    }
}
