package bestreads.ui;

import java.util.Scanner;

public class UserInterfaceIO implements IO {
    private Scanner scanner;
    
    public UserInterfaceIO() {
        scanner = new Scanner(System.in);
    }
    
    public void print(String m) {
        System.out.println(m);
    }

    public String nextLine() {
        return scanner.nextLine();
    }
}
