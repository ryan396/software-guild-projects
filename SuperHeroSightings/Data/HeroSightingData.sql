USE hero_list;

INSERT INTO heroes (hero_name, description)
VALUES ("Thor", "God of Thunder"), ("Iron Man", "Tech Wizard"), ("Dr. Strange, Magician");

INSERT INTO locations (location_name, latitude, longitude, street, city, zip_code)
VALUES ("Minneapolis, MN", "44.97", "-93.26", "Bloomington Avenue", "Minneapolis", 55407),
("Flagstaff, AZ", "35.19807", "-111.65127", "Aspen Ave", "Flagstaff", 86001),
("Eau Claire, WI", "44.81135", "Water Street", "Eau Claire", 54701);

INSERT INTO powers (description) VALUES ("Flight"), ("Lighting"), ("Time Magic"), ("Super Strength"),
("Super Smarts"), ("Power Armor");

INSERT INTO organizations (organization_name, organization_description, street, city, zip_code, 
phone, email) values ("Avengers", "Heroes of Earth", "890 Fifth Avenue", "New York", 10021),
( "Sanctum Sanctorum", "Dr. Strange's House", "177A Bleecker Street", "New York", 10012),
("Iron Man Group", "Iron Man's House", "10880 Malibu Point", "Malibu", 90265);

