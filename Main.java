package client;


import java.io.*;

import java.net.InetAddress;

import java.util.Date;
import java.util.GregorianCalendar;

public class Main {


    public static void main(String[] args) {
        //upper ping limit
        int delay = 0;
        //result work getPing
        String result;
        //address site of local ip
        String address;
        
        //defult param 
        address = "www.google.com";
        delay = 40;

        for (int i = 0; i < args.length - 1; i++) {

            if ((args[i]).equals("-d")) {
                delay = Integer.parseInt(args[i + 1]);
            }

            if ((args[i]).equals("-a")) {
                address = args[i + 1];
            }
        }

        File ping = new File("ping.txt"); //create txt log file

        while (true) {
            //network status check
            boolean flag = true;
            Date date = new Date(); //get Date
            result = getPing(address);
            try {

                //if there is no connection to a network or service, recording is in txt
                if ((result == " Server is not available. ") && (result == "No connect")) {
                    System.out.print(result + " " + date + "\n");
                    BufferedWriter writer = new BufferedWriter(new FileWriter(ping, true));
                    writer.write(result + date + "\n");
                    writer.flush();
                    writer.close();
                    flag = false;
                }

                //if ping is greater than the specified number, writing to txt
                if ((Integer.parseInt(result) > delay) && (flag == true)) {
                    System.out.print(result + " ms " + date + "\n");
                    BufferedWriter writer = new BufferedWriter(new FileWriter(ping, true));
                    writer.write(result + date + "\n");
                    writer.flush();
                    writer.close();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.print("non-valid param");
            }
        }
    }

    public static String getPing(String address) {

        try {
            String ipAdress = address;
            String ipAddress = ipAdress;
            InetAddress inet = InetAddress.getByName(ipAddress);

            long finish = 0;
            long start = new GregorianCalendar().getTimeInMillis();

            if (inet.isReachable(5000)) {
                finish = new GregorianCalendar().getTimeInMillis();

                return ((finish - start + ""));
            } else {
                return " Server is not available. ";
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }
        return "No connect";
    } //method for getting network status

}
