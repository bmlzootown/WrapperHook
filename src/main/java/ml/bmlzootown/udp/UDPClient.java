package ml.bmlzootown.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static String sendReceivePacket(String request) {
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress ip = InetAddress.getByName("localhost");
            byte[] sendData = request.getBytes();
            byte[] receiveData = new byte[1024];

            DatagramPacket send = new DatagramPacket(sendData, sendData.length, ip, 25569);
            client.send(send);

            DatagramPacket receive = new DatagramPacket(receiveData, receiveData.length);
            client.receive(receive);
            return new String(receive.getData(), 0, receive.getLength());
        } catch (Exception e) {
            e.printStackTrace();
            return "[ERROR]: Whoops, something went wrong! Poke a staff member.";
        }
    }

    public static void sendPacket(String request) {
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress ip = InetAddress.getByName("localhost");
            byte[] sendData = request.getBytes();

            DatagramPacket send = new DatagramPacket(sendData, sendData.length, ip, 25569);
            client.send(send);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
