DROP DATABASE IF EXISTS hero_list;
CREATE DATABASE hero_list;
use hero_list;

DROP TABLE IF EXISTS heroes;
  CREATE TABLE heroes (
  hero_id int(11) NOT NULL AUTO_INCREMENT,
  hero_name varchar(50) NOT NULL,
  description varchar(250) NULL,
  PRIMARY KEY (hero_id)
);

DROP TABLE IF EXISTS locations;
  CREATE TABLE locations (
  location_id int(11) NOT NULL AUTO_INCREMENT,
  location_name varchar(50) NULL,
  latitude decimal (10,4) NOT NULL,
  longitude decimal (10,4) NOT NULL,
  street varchar(50) NOT NULL,
  city varchar(50) NOT NULL,
  zip_code int(10) NOT NULL,
  PRIMARY KEY (location_id)
);

DROP TABLE IF EXISTS organizations;
	CREATE TABLE organizations (
	organization_id int(11) NOT NULL AUTO_INCREMENT,
    organization_name varchar(50) NOT NULL,
    organization_description varchar(250) NULL,
	street varchar(50) NOT NULL,
    city varchar(50) NOT NULL,
    zip_code int(10) NOT NULL,
    phone varchar(15) NOT NULL,
    email varchar(50) NOT NULL,
    PRIMARY KEY (organization_id)
);

DROP TABLE IF EXISTS sightings;
	CREATE TABLE sightings (
    sighting_id int(11) NOT NULL AUTO_INCREMENT,
    sighting_date DATE NOT NULL,
    location_id int(11) NOT NULL,
    PRIMARY KEY (sighting_id)
);

ALTER TABLE sightings
ADD CONSTRAINT fk_HeroSighting_sightings
FOREIGN KEY (location_id) REFERENCES locations(location_id);

DROP TABLE IF EXISTS herosightings;
	CREATE TABLE herosightings (
    hero_id int(11) NOT NULL,
    sighting_id int(11) NOT NULL
);

ALTER TABLE herosightings
ADD CONSTRAINT fk_HeroSighting_HeroSightings1
FOREIGN KEY (hero_id) REFERENCES heroes(hero_id);

ALTER TABLE herosightings
ADD CONSTRAINT fk_HeroSighting_HeroSightings2
FOREIGN KEY (sighting_id) REFERENCES sightings(sighting_id);

DROP TABLE IF EXISTS heroorganizations;
	CREATE TABLE heroorganizations (
    hero_id int(11) NOT NULL,
    organization_id int(11) NOT NULL
);

ALTER TABLE heroorganizations
ADD CONSTRAINT fk_HeroSighting_HeroOrganization1
FOREIGN KEY (hero_id) REFERENCES heroes(hero_id);

ALTER TABLE heroorganizations
ADD CONSTRAINT fk_HeroSighting_HeroOrganization2
FOREIGN KEY (organization_id) REFERENCES organizations(organization_id);

DROP TABLE IF EXISTS powers;
	CREATE TABLE powers (
    power_id int(11) NOT NULL AUTO_INCREMENT,
    description varchar(50) NOT NULL,
    PRIMARY KEY (power_id)
);

DROP TABLE IF EXISTS heropowers;
	CREATE TABLE heropowers (
	hero_id int(11) NOT NULL,
    power_id int(11) NOT NULL
);

ALTER TABLE heropowers
ADD CONSTRAINT fk_HeroSighting_HeroPowers1
FOREIGN KEY (hero_id) REFERENCES heroes(hero_id);

ALTER TABLE heropowers
ADD CONSTRAINT fk_HeroSighting_HeroPowers2
FOREIGN KEY (power_id) REFERENCES Powers(power_id);

DROP DATABASE IF EXISTS hero_list_test;
CREATE DATABASE hero_list_test;
use hero_list_test;

DROP TABLE IF EXISTS heroes;
  CREATE TABLE heroes (
  hero_id int(11) NOT NULL AUTO_INCREMENT,
  hero_name varchar(50) NOT NULL,
  description varchar(250) NULL,
  PRIMARY KEY (hero_id)
);

DROP TABLE IF EXISTS locations;
  CREATE TABLE locations (
  location_id int(11) NOT NULL AUTO_INCREMENT,
  location_name varchar(50) NULL,
  latitude decimal (10,4) NOT NULL,
  longitude decimal (10,4) NOT NULL,
  street varchar(50) NOT NULL,
  city varchar(50) NOT NULL,
  zip_code int(10) NOT NULL,
  PRIMARY KEY (location_id)
);

DROP TABLE IF EXISTS organizations;
	CREATE TABLE organizations (
	organization_id int(11) NOT NULL AUTO_INCREMENT,
    organization_name varchar(50) NOT NULL,
    organization_description varchar(250) NULL,
	street varchar(50) NOT NULL,
    city varchar(50) NOT NULL,
    zip_code int(10) NOT NULL,
    phone varchar(15) NOT NULL,
    email varchar(50) NOT NULL,
    PRIMARY KEY (organization_id)
);

DROP TABLE IF EXISTS sightings;
	CREATE TABLE sightings (
    sighting_id int(11) NOT NULL AUTO_INCREMENT,
    sighting_date DATE NOT NULL,
    location_id int(11) NOT NULL,
    PRIMARY KEY (sighting_id)
);

ALTER TABLE sightings
ADD CONSTRAINT fk_HeroSighting_sightings
FOREIGN KEY (location_id) REFERENCES locations(location_id);

DROP TABLE IF EXISTS herosightings;
	CREATE TABLE herosightings (
    hero_id int(11) NOT NULL,
    sighting_id int(11) NOT NULL
);

ALTER TABLE herosightings
ADD CONSTRAINT fk_HeroSighting_HeroSightings1
FOREIGN KEY (hero_id) REFERENCES heroes(hero_id);

ALTER TABLE herosightings
ADD CONSTRAINT fk_HeroSighting_HeroSightings2
FOREIGN KEY (sighting_id) REFERENCES sightings(sighting_id);

DROP TABLE IF EXISTS heroorganizations;
	CREATE TABLE heroorganizations (
    hero_id int(11) NOT NULL,
    organization_id int(11) NOT NULL
);

ALTER TABLE heroorganizations
ADD CONSTRAINT fk_HeroSighting_HeroOrganization1
FOREIGN KEY (hero_id) REFERENCES heroes(hero_id);

ALTER TABLE heroorganizations
ADD CONSTRAINT fk_HeroSighting_HeroOrganization2
FOREIGN KEY (organization_id) REFERENCES organizations(organization_id);

DROP TABLE IF EXISTS powers;
	CREATE TABLE powers (
    power_id int(11) NOT NULL AUTO_INCREMENT,
    description varchar(50) NOT NULL,
    PRIMARY KEY (power_id)
);

DROP TABLE IF EXISTS heropowers;
	CREATE TABLE heropowers (
	hero_id int(11) NOT NULL,
    power_id int(11) NOT NULL
);

ALTER TABLE heropowers
ADD CONSTRAINT fk_HeroSighting_HeroPowers1
FOREIGN KEY (hero_id) REFERENCES heroes(hero_id);

ALTER TABLE heropowers
ADD CONSTRAINT fk_HeroSighting_HeroPowers2
FOREIGN KEY (power_id) REFERENCES Powers(power_id);