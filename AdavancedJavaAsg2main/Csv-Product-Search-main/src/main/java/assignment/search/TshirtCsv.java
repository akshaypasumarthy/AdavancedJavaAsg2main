package assignment.search;

import java.io.File;



import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
//import org.hibernate.mapping.Set;

import assignment.data.*;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;




public class TshirtCsv {
	
	File directory = new File(Paths.get(System.getProperty("user.dir"),"src","main","resources").toString());

	public void run(boolean listen) {
		while (true) {
			Set<Tshirt> UpdatedStore = new HashSet<Tshirt>();
			for (String path : directory.list()) {
				String filepath = "src/main/resources/";
				
				filepath += path;
				System.out.println(filepath);
				try {
					CSVParser parser = new CSVParserBuilder().withSeparator('|').build();
					CSVReader reader = new CSVReaderBuilder(new FileReader(filepath)).withSkipLines(1).withCSVParser(parser).build();
					String[] S;
					while ((S = reader.readNext()) != null) {
						Tshirt tshirt = new Tshirt();
						
						tshirt.setId(S[0]);
						tshirt.setName(S[1]);
						String col = Color.valueOf(S[2].toUpperCase()).toString();
						tshirt.setColor(col);
						tshirt.setGender(Gender.valueOf(S[3].toUpperCase()));
						tshirt.setSize(Size.valueOf(S[4].toUpperCase()));
						tshirt.setPrice(Double.parseDouble(S[5]));
						tshirt.setRating(Double.parseDouble(S[6]));
						tshirt.setAvalibilty(S[7]);
						
						UpdatedStore.add(tshirt);

					}

				} catch (FileNotFoundException e) {
					System.out.println("Given File is not available");
				} catch (IOException e) {
					System.out.println("Inputs are wrong");
				} 
			}
			storeInDB(UpdatedStore);
			if(listen)
			{
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			}
			else
			{
				return;
			}
		}
	}
	private void storeInDB(Set<Tshirt> updatedStore) {
		Configuration configure = new Configuration().configure().addAnnotatedClass(Tshirt.class);
		StandardServiceRegistryBuilder serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configure.getProperties());
		SessionFactory sessionFactory = configure.buildSessionFactory(serviceRegistry.build());
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		for (Tshirt item : updatedStore) {
			session.saveOrUpdate(item);
		}
		transaction.commit();
	}

}
