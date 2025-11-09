1. Ensure that you have both java and psql downloaded. You can do this by quickly checking the version you have with java --version and psql --version in the command line. There is no need to check psql if you are able to use pgAdmin4. 

2. Ensure you have postgresql-42.7.8.jar added into your lib directory in your java project. 

3. Before you begin, ensure that the database you are testing with is already populated by the initial values. You can use the following Query for your database:

```sql
CREATE TABLE IF NOT EXISTS students (
	student_id 	SERIAL PRIMARY KEY,
	first_name 	TEXT NOT NULL,
	last_name 	TEXT NOT NULL,
	email		TEXT NOT NULL UNIQUE,
	enrollment_date	DATE
);

INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES
('John', 'Doe', 'john.doe@example.com', '2023-09-01'),
('Jane', 'Smith', 'jane.smith@example.com', '2023-09-01'),
('Jim', 'Beam', 'jim.beam@example.com', '2023-09-02');
```

4. In App.java, you will need to manually adjust line 99 in accordance to your own database's information. It will have the following comment right above it:

// CHANGE THIS LINE

The String url = "jdbc:postgresql://localhost:5432/A3Q1" should be changed where localhost is renamed to your host, 5432 is adjusted to your port, and A3Q1 is changed to match your database's name. 

5. To compile the code, copy and paste the following commands into PowerShell in the correct directory:

 >.\build.bat 
 
6. To run the code, copy and paste the following command into the PowerShell in the correct directory AFTER compiling:

 >java -jar App.jar (yourUsername) (yourPassword)

 Note: you need to enter your username and password into the command line for args in the indicated places above. It will be passed in that fixed order for the code so you MUST include it when running. 

Here is the link to the video demonstration on how to run this code:
>https://www.youtube.com/watch?v=_Le-JP6KKuI

Note that in the demonstration, the code is commented out to show each section and that it fulfills and does what it needs to do. If you wish to see the problem broken down into parts as well, please comment out the parts you do not wish to test yet. 
