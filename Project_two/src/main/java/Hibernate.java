/* THIS IS FOR REFERENCE LATER
System.out.println("project started...");
// SET A OBJECT INTO THE DATABASE WITH HIBERNATE
        // create a configuration object
        Configuration config = new Configuration();

        // read the Configuration and load in the object
        config.configure("hibernate.cfg.xml");

        // create factory
        SessionFactory factory = config.buildSessionFactory();
        // ope the session
        Session session = factory.openSession();
        // begin transaction
        Transaction t = session.beginTransaction() ;

        // create object
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Mark");
        employee.setEmail("m@gmail.com");

        // save the objet
        session.save(employee);
        // commit
        t.commit();
        session.close();


        */