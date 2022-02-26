package bestreads.ui;

import java.util.Scanner;

import bestreads.readingtip.ReadingTips;

public class UserInterface {
    private ReadingTips tips;
    private IO io;
    
    public UserInterface(IO io) {
        this.tips = new ReadingTips();
        this.io = io;
    }
    
    public void start() {
        io.print("Welcome to Bestreads");
        int maxAllowedLoops = 0;
        
        while (maxAllowedLoops < 50) {
            showCommands();
            String input = io.nextLine();
            if (!input.matches("([1-3])")) {
                io.print("Invalid command");
                maxAllowedLoops++;
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
                io.print("Closing");
                break;
            }
            maxAllowedLoops++;
        }
    }
    
    public void showCommands() {
        io.print("Commands:");
        io.print("1 -- Add reading tip");
        io.print("2 -- Show reading tips");
        io.print("3 -- Exit program");
    }
    
    public void addTip() {

        io.print("Reading Tip Title?");
        String title = io.nextLine();
        io.print("URL?");
        String url = io.nextLine();
        this.tips.addTip(url, title);
    }
    
    public void showTips() {
        io.print("Reading Tips:");
        io.print(this.tips.toString());
    }
}
