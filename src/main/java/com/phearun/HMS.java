package com.phearun;

/**
 * Created by PHEARUN on 12/8/2016.
 */
import java.util.Scanner;

class HMS {
    Scanner sc=new Scanner(System.in);
    String choice,menu,name;
    int n,i,j,ri,fo,ro,fi;
    String[] sub;
    String n1;
    String[][] stor=new String[n][n];

    void startUp(){

        System.out.println("---------------Welcome to Hotel Management System---------------");
        System.out.println("1. Input number of floor and room ");
        System.out.println("2. Show Menu ");
        System.out.println("3. Exit ");
        System.out.print("Choose option(1-3):");
        choice=sc.next();
        choose();
    }

    void choose(){
        switch(choice){
            case "1" :

                inputFloorAndRoom();
                startUp();
                break;
            case "2" :
                if(n<=0){
                    System.out.println("Can not to show! Please press 1 to setup floor and room");
                    startUp();
                }
                else
                    showMenu();
                break;


            case "3" :{

                String s;
                System.out.print("Do you want to exit?(Y/N):");
                s=sc.next();
                if(s.equals("N"))
                    startUp();
                else
                    break;
            }



            default:
                System.out.println("You choose incorect! Please choose again");

                startUp();
        }
    }

    void showMenu(){

        System.out.print("1. Check in" + '\t');
        System.out.print("2. Check out" + '\t');
        System.out.print("3. Display" + '\t');
        System.out.println("4. Exit ");
        System.out.print("Choose option from 1 to 4:");
        menu=sc.next();
        optionMenu();

    }

    void optionMenu(){
        switch(menu){
            case "1" :
                checkIn();
                break;
            case "2" :
                //	checkOut();
                break;
            case "3" :
                display();
                break;
            case "4" :
            {

                String s1;
                System.out.print("Do you want to exit?(Y/N):");
                s1=sc.next();
                if(s1.equals("N"))
                    showMenu();
                else
                    break;

            }


            default:
                System.out.println("You choose incorect! Please choose again");
                System.out.println("");
                showMenu();
        }
    }

    void inputFloorAndRoom(){


        System.out.print("Enter amount of floor & room:");
        n=sc.nextInt();
        stor = new String[n][n];
        System.out.print("Floor and Room has been setup!" + "\n");
        System.out.print("You have "+n+" Floor and "+(n*n)+" Rooms..." + "\n");
    }

    void display(){

        for(i=0;i<stor.length;i++){
            System.out.print("Floor "+i+" : ");
            for(j=0;j<stor.length;j++){
                if(stor[i][j]==null)
                    stor[i][j]="--------";
                System.out.print(stor[i][j]+" ");
            }
            System.out.println("");

        }
        showMenu();
    }

    void checkIn(){
        //stor=new String[n][n];
        String a;

        do{
            System.out.print("Choose floor you prefer(0-4):");
            ri=sc.nextInt();
            System.out.print("Choose room you prefer(0-4):");
            fi=sc.nextInt();
            sc.nextLine();
            System.out.print("Enter guest name:");
            stor[ri][fi]=sc.nextLine();
            System.out.print("Please press 1 to new check in and press any key to show menu!:");
            a=sc.next();


        }while(a.equals("1"));
        showMenu();
    }

}

class Main{
    public static void main(String arge[]){
        HMS obj = new HMS();
        obj.startUp();
    }
}
