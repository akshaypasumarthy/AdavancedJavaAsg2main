package assignment.application;

import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;
// java.util.logging.Logger;

import assignment.data.Tshirt;
import assignment.search.TshirtCsv;
import assignment.searchip.TshirtSearchIp;
import assignment.searchop.TshirtSearchOp;
import assignment.view.Input;
import assignment.view.Output;

//import org.jboss.logging.Level;

public class App {

	public static void main(String[] args) throws FileNotFoundException {
		TshirtCsv temp = new TshirtCsv();
		temp.run(false);
		Input input = new Input();
		TshirtSearchIp tshirtSearchIp = input.getInput();

		TshirtSearchOp tshirtSearchOp = new TshirtSearchOp();
		List<Tshirt> matchedTShirts = tshirtSearchOp.getMatchingTshirts(tshirtSearchIp);

		Output output = new Output();
		output.displayOutput(matchedTShirts);

	

//	Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.OFF);
//	TshirtCsv tshirtcsv = new TshirtCsv();
//    TshirtCsv.start();
//    char ans;
//    
//    while (true) {
//        Input input = new Input();
//        TshirtSearchIp tshirtSearchIp = input.getInput();
//        Configure configure = new Configure();
//        List<Tshirt> list =  Configure.searchItem(input.getColor(), input.getGender(scn), input.getSize());
//        Output output = new Output();
//        output.DisplayItems(list, input.getOutputPreference());
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("\nEnter Y for more inputs else enter N to exit");
//        ans = scanner.next().charAt(0);
//        if (ans == 'N') {
//            break;
//        }
		// }
//}
	}
}
