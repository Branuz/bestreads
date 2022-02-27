package bestreads.ui;
import bestreads.readingtip.ReadingTips;

public class UserInterface {
    private ReadingTips tips;
    private IO io;
    
    public UserInterface(IO io) {
        this.tips = new ReadingTips();
        this.io = io;
    }
    
    public void start() {

        io.print("\nnunununununununununununununununununununununununununununununun\n");
        io.print("Welcome to Bestreads!\n");
        int maxAllowedLoops = 0;
        
        while (maxAllowedLoops < 50) {
            showCommands();
            String input = io.nextLine();
            if (!input.matches("([1-3])")) {
                io.print("Ops! Please choose between 1 to 3.");
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
                io.print("\nSee you soon!");
                io.print("\nnunununununununununununununununununununununununununununununun\n");
                break;
            }
            maxAllowedLoops++;
        }
    }
    
    public void showCommands() {
        io.print("Please choose what you wish to do");
        io.print("1 -- Add a reading tip");
        io.print("2 -- Show reading tips");
        io.print("3 -- Exit program\n");
    }
    
    public void addTip() {

        io.print("Please give the title for the tip");
        String title = io.nextLine();
        if (title.matches("([1-3])")) {
            io.print("Ops! Please choose between 1 to 3.");
        }
        io.print("Please add the url for the tip");
        String url = io.nextLine();
        this.tips.addTip(url, title);
    }
    
    public void showTips() {
        io.print("Your Reading Tips:");
        io.print(this.tips.toString());
    }
}
