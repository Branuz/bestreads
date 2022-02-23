package bestreads;

import java.util.Scanner;

public class UserInterface {
    private ReadingTips tips;
    private Scanner scanner;
    
    public UserInterface() {
        this.tips = new ReadingTips();
        this.scanner = new Scanner(System.in);
    }
    
    public void start() {
        System.out.println("Welcome to Bestreads");
        
        while (true) {
            showCommands();
            String input = this.scanner.nextLine();
            if (!input.matches("([1-3])")) {
                continue;
            }
            int command = Integer.valueOf(input);
            if (command == 1) {
                addTip();
            }
            if (command == 2) {
                showTips();
            }
            if (command == 3) {
                break;
            }
        }
    }
    
    public void showCommands() {
        System.out.println("Commands:");
        System.out.println("1 -- Add reading tip");
        System.out.println("2 -- Show reading tips");
        System.out.println("3 -- Exit program");
    }
    
    private void addTip() {
        System.out.println("Reading Tip Title?");
        String title = this.scanner.nextLine();
        System.out.println("URL?");
        String url = this.scanner.nextLine();
        this.tips.addTip(url, title);
    }
    
    private void showTips() {
        System.out.println("Reading Tips:");
        System.out.println(this.tips.toString());
    }
}
