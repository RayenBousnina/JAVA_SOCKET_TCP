package threads;

import models.Compte;
import models.Proprietaire;
import servers.Server;

import java.io.*;
import java.net.Socket;

public class Processing extends Thread{
    private Socket socket;
    public Compte compte;
    public Processing(Socket socket){
        this.socket=socket;
    }
    public boolean search(String nom){
        for (int i = 0; i <Server.compteList.size() ; i++) {
            if (Server.compteList.get(i).getProprietaire().getNom().equals(nom));
                return true;

        }
        return false;
    }
    public Compte getCompte(Proprietaire prop){
        for (int i = 0; i <Server.compteList.size() ; i++) {
            if (Server.compteList.get(i).getProprietaire().equals(prop)){
                return Server.compteList.get(i);
            }
        }
        return null;
    }

    public void run() {

        try {

            BufferedReader inSock = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            System.out.println("connected on port: " + socket.getPort());
            boolean connect = false;
            while (true) {
                String cmd;
                String sendMsg="";
                cmd = inSock.readLine();
                //System.out.println("message received from client: " + cmd);
                if (cmd.toUpperCase().startsWith("CREATION")){
                    if (search(cmd.substring(9))){
                        sendMsg="account already there!";
                        out.println(sendMsg);
                    }else{
                        Proprietaire prop = new Proprietaire(cmd.substring(9));
                        Compte newCompte = new Compte(prop,0.0);
                        Server.compteList.add(newCompte);
                        sendMsg="account of mr "+prop.getNom()+"is successfully created ";
                        out.println(sendMsg);

                        while(true){
                            cmd = inSock.readLine();
                            if (cmd.toUpperCase().startsWith("SOLDE")){
                                sendMsg="solde est  "+newCompte.getMontant();
                                out.println(sendMsg);
                            }else if(cmd.startsWith("CREDIT")){
                                double m1=Double.parseDouble(cmd.substring(7));
                                newCompte.setMontant(newCompte.getMontant()+m1);
                                sendMsg="montant creditée avec succès ...  ";
                                out.println(sendMsg);
                            }else if (cmd.startsWith("DEBIT")){
                                double m1=Double.parseDouble(cmd.substring(6));
                                newCompte.setMontant(newCompte.getMontant()-m1);
                                sendMsg="montant creditée avec succès ...  ";
                                out.println(sendMsg);
                            }else if (cmd.startsWith("DEMANDE_CREDIT")){
                                String details = cmd.substring(15);

                                String[] detailsArray = details.split(" ");
                                String detailMontant=detailsArray[0];


                                double montantdmd = Double.parseDouble(detailMontant);


                                String detailNbreMois = detailsArray[1];
                                int nbMois = Integer.parseInt(detailNbreMois);


                                double pourcentage = 0;

                                if (nbMois<=12){ //  6 % per year
                                    pourcentage = (6*montantdmd)/100;
                                    double total = montantdmd+pourcentage;
                                    double mntParMois = total/nbMois;
                                    sendMsg = "you must pay "+mntParMois+"each month for a period of "+nbMois+"months";
                                    out.println(sendMsg);
                                }else if (nbMois<=24){
                                    pourcentage = (12*montantdmd)/100;
                                    double total = montantdmd+pourcentage;
                                    double mntParMois = total/nbMois;
                                    sendMsg = "you must pay "+mntParMois+"each month for a period of "+nbMois+"months";
                                    out.println(sendMsg);
                                }else {
                                    pourcentage = (24*montantdmd)/100;
                                    double total = montantdmd+pourcentage;
                                    double mntParMois = total/nbMois;
                                    sendMsg = "you must pay "+mntParMois+"each month for a period of "+nbMois+"months";
                                    out.println(sendMsg);
                                }

                            }

                        }



                    }
                }else {
                    System.out.println("you must create an account first of all !");
                }

            }
        }catch (Exception ee){
            ee.printStackTrace();
        }
    }
}