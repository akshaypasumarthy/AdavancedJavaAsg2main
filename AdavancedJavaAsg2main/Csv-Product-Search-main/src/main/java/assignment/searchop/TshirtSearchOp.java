package assignment.searchop;

import java.io.FileNotFoundException;








import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import assignment.comparator.PriceComparator;
import assignment.comparator.RatingComparator;
import assignment.data.*;
import assignment.searchip.TshirtSearchIp;
import assignment.application.*;





public class TshirtSearchOp  {

Scanner scn;
	
	public List<Tshirt>getMatchingTshirts(TshirtSearchIp tshirtsSerachIp) throws FileNotFoundException{
		 
		List<Tshirt> tshirt = new ArrayList<Tshirt>();
		Configure configure = new Configure();
		tshirt = configure.searchItem(tshirtsSerachIp.getColor().toString(), tshirtsSerachIp.getGender().toString(), tshirtsSerachIp.getSize().toString());
	//	ArrayList<Tshirt>allTshirtData = TshirtCsv.getAllTshirts();
		
		ArrayList<Tshirt>allTshirt = new ArrayList<Tshirt>();
		
		String inputFormat = String.format("%s,%s,%s", tshirtsSerachIp.getColor(),tshirtsSerachIp.getSize(),tshirtsSerachIp.getGender());
		
		String outputFormat;
		String output = tshirtsSerachIp.getOutputPreference().name();
		
		System.out.println("Your choice of Output Preference is : ");
		
		
		if(output.equalsIgnoreCase("Price")) {
			
			Collections.sort(tshirt,new PriceComparator());
			System.out.println(output);
			
		}else if(output.equalsIgnoreCase("Rating")) {
			
			Collections.sort(
					tshirt,new RatingComparator());
			System.out.println(output);
			
		}else {
			
			Collections.sort(tshirt,new PriceComparator());
			Collections.sort(tshirt, new RatingComparator());
			System.out.println(output);
			
		}
		
		System.out.println("\n\n");
		
		return tshirt;
	}

	
}
