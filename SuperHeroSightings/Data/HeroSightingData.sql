USE hero_list;

/* the delete FROM is used for an initial set up of data if data already exists, so this script can be runnable. DO NOT USE IF YOU WANT TO KEEP THE DATA IN THE DATABASE! consider this a reset of the database and set initial basic data */

DELETE FROM heroorganizations; 
DELETE FROM heropowers; 
DELETE FROM herosightings;
DELETE FROM sightings;
DELETE FROM locations;
DELETE FROM organizations;
DELETE FROM powers;
DELETE FROM heroes;


/* initial data for herosightings application */
INSERT INTO heroes (hero_id, hero_name, description)
VALUES (1, "Thor", "God of Thunder"), (2, "Iron Man", "Tech Wizard"), (3, "Dr. Strange", "Magician");

INSERT INTO locations (location_id, location_name, latitude, longitude, street, city, zip_code)
VALUES (1, "Minneapolis, MN", "44.97", "-93.26", "Bloomington Avenue", "Minneapolis", 55407),
(2, "Flagstaff, AZ", "35.19807", "-111.65127", "Aspen Ave", "Flagstaff", 86001),
(3, "Eau Claire, WI", "44.81135", "-91.49849", "Water Street", "Eau Claire", 54701);

INSERT INTO powers (power_id, description) VALUES (1, "Flight"), (2, "Lighting"), (3, "Time Magic"), (4, "Super Strength"),
(5, "Super Smarts"), (6, "Power Armor");

INSERT INTO organizations (organization_id, organization_name, organization_description, street, city, zip_code, 
phone, email) values (1, "Avengers", "Heroes of Earth", "890 Fifth Avenue", "New York", 10021, "222-222-3333", "avengers@gmail.com"),
(2, "Sanctum Sanctorum", "Dr. Strange's House", "177A Bleecker Street", "New York", 10012, "333-333-2222", "drstrange@gmail.com"),
(3, "Iron Man Group", "Iron Man's House", "10880 Malibu Point", "Malibu", 90265, "111-333-4444", "ironman@ironman.com");

INSERT INTO sightings (sighting_id, sighting_date, location_id) values (1, "2019/05/17", 1), (2, "2015/03/02", 2), (3, "2010/01/01", 3);

/* build bridge table for heroorganizations */
INSERT INTO heroorganizations (hero_id, organization_id) values (1, 1),(1, 3), (2, 1), (2, 3), (3,3);

/* build bridge table for heropowers */
INSERT INTO heropowers (hero_id, power_id) values (1, 1), (1, 2), (1, 4), (2, 5), (2, 6), (3, 3), (3, 5);

/* build bridge table for herosightings */
INSERT INTO herosightings (hero_id, sighting_id) values (1, 1), (2, 2), (3, 3), (1, 2), (2, 1);




