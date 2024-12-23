package todoMaster;

import todoMaster.Action.Actions;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("1. SignIN ");
        System.out.println("2. SignUp");
        Scanner sc= new Scanner(System.in);
        int choice = sc.nextInt();
        System.out.println("_______________________________________________________");
        System.out.println(" ");
        switch(choice){
            case 2:
                Actions.userRegister();
                break;
            case 1:
                Actions.getLogin();
                break;

            default:
                System.out.println("Wrong Input");
        }
    }
}