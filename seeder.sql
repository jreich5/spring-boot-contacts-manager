USE spring_contacts_db;

# CREATE TABLE contacts (
#   id INT AUTO_INCREMENT PRIMARY KEY,
#   first_name VARCHAR(100) NOT NULL,
#   middle_initial CHAR(1),
#   last_name VARCHAR(100) NOT NULL,
#   suffix VARCHAR(20),
#   email VARCHAR(300),
#   phone_number VARCHAR(20),
#   created_at TIMESTAMP,
#   updated_at TIMESTAMP
# );

TRUNCATE contacts;

INSERT INTO contacts (first_name, middle_initial, last_name, suffix, email, phone_number, address) VALUES
("Bob", "R", "Smith", "II", "bob@email.com", "8306123629", "123 Memory Lane"),
("Kelly", "B", "Bobberson", "I", "kelly@email.com", "5553453434", "232 Hello Street"),
("Fred", "C", "Gibson", NULL, NULL, "8306123629", "432 LKJ Street"),
("John", "D", "Reich", "II", "john@email.com", NULL, "Blah Address 1"),
("Cindy", "F", "Child", "Jr.", "cindy@email.com", "8887653434", "Blah Address 2"),
("Cathy", "G", "Bobby", "Sr.", "cathy@email.com", "1112223333", "Blah Address 3");