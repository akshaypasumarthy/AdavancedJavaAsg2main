package assignment.application;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;




import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import assignment.data.*;



public class Configure {
	
	List<Tshirt> searchedItems = new ArrayList<Tshirt>();

	public List<Tshirt> searchItem(String color, String gender, String size) {

		File f = new File("src/main/java/hibernate.cfg.xml");
		Configuration configure = new Configuration().configure(f).addAnnotatedClass(Tshirt.class);
		StandardServiceRegistryBuilder serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configure.getProperties());
		SessionFactory sessionFactory = configure.buildSessionFactory(serviceRegistry.build());
		Session session = sessionFactory.openSession();
		try {
			
		String hql = "from Tshirt where color = '" + color + "' AND size = '" + size + "' AND gender = '" + gender
				+ "'";
     	//	String hql = " from Tshirt"; 
   			Query query = session.createQuery(hql);
			searchedItems =query.list();
			
			
			
//			Query query = session.createQuery("from tshirt where colour=:c and gender_recommendation=:g and size=:s");
//			System.out.println(1);
//			query.setParameter("c", Color.valueOf(color));
//			System.out.println(2);
//			query.setParameter("s", Size.valueOf(size));
//			System.out.println(3);
//			query.setParameter("g", Gender.valueOf(gender));
//			System.out.println(4);
//			searchedItems = ((org.hibernate.query.Query) query).list();
//			System.out.println(5);
//			session.close();
//			
			
//			if (searchedItems.isEmpty()) {
//				throw new NoItemFoundException("No Items Matched");
//			}

		} 
		catch (IllegalArgumentException e)
		{
			System.out.println(e.getMessage());
		} 
//		catch (NoItemFoundException e) {
//			System.out.println(e.getMessage());
//		}
		return searchedItems;
	}

}
