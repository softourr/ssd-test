import repository.SsdRepository;
import controller.SsdController;
import view.SsdView;
public class Main {
    public static void main(String[] args) {
        SsdController ssdController = new SsdController( new SsdRepository(), new SsdView());

        try {
            // 명령어가 'W'일 때 (쓰기 명령)
            if (args[0].equalsIgnoreCase("W")) {
                if (args.length != 3) {
                    System.out.println("Invalid format for write. Use: ssd W <index> <hexValue>");
                    return;
                }
                int index = Integer.parseInt(args[1]);
                String value = args[2];
                ssdController.write(index, value);

                // 명령어가 'R'일 때 (읽기 명령)
            } else if (args[0].equalsIgnoreCase("R")) {
                if (args.length != 2) {
                    System.out.println("Invalid format for read. Use: ssd R <index>");
                    return;
                }
                int index = Integer.parseInt(args[1]);
                ssdController.read(index);

                // 잘못된 명령어 처리
            } else {
                System.out.println("Invalid command. Use ssd W for write, ssd R for read.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
