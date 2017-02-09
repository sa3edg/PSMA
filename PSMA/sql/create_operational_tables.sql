DROP TABLE IF EXISTS users;
CREATE  TABLE users (
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(150) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (username));

DROP TABLE IF EXISTS user_roles;
CREATE TABLE user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (role,username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username));
 
--admin & bench@admin_
insert into users values ('admin','$2a$10$E5Rd6sUAw2H.qHau1inSROmdjYbQyJsvMA.vVsNB0WQrlkT9QnPlu', '1');

insert into user_roles(username, role) values ('admin', 'ROLE_ADMIN');

--add events table
DROP TABLE IF EXISTS events;
CREATE TABLE events (
                   id int(20) NOT NULL AUTO_INCREMENT,
                   title varchar(500) NOT NULL ,
                   details text default NULL ,
                   start_date DATETIME default NULL ,
                   end_date DATETIME default NULL ,
                   image LONGBLOB ,
                   PRIMARY KEY  (id)
                 ) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
--end events table

--add offers table
DROP TABLE IF EXISTS offers;
CREATE TABLE offers (
                   id int(20) NOT NULL AUTO_INCREMENT,
                   title varchar(500) NOT NULL ,
                   details text default NULL ,
                   item varchar(500) NOT NULL ,
                   old_price float default 0 ,
                   new_price float default 0 ,
                   discount float default 0 ,
                   sale TINYINT default 0 ,
                   start_date DATE default NULL ,
                   end_date DATE default NULL ,
                   image LONGBLOB ,
                   PRIMARY KEY  (id)
                 ) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
--end offers table

--add subscriber table
DROP TABLE IF EXISTS subscriber;
CREATE TABLE subscriber (
                   id int(20) NOT NULL AUTO_INCREMENT,
                   name varchar(500) NOT NULL ,
                   gender varchar(50) default NULL ,
                   age int(2) default 0 ,
                   address text default NULL ,
                   mobile_number varchar(50) NOT NULL ,
                   facebook_account varchar(100) default NULL ,
                   email_address varchar(50) default NULL ,
                   national_id_no varchar(50) default NULL ,
                   registeration_date DATE default NULL ,
                   PRIMARY KEY  (id)
                 ) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
--end subscriber table

--add subscriber_cards table
DROP TABLE IF EXISTS subscriber_cards;
CREATE TABLE subscriber_cards (
                   card_number varchar(100) NOT NULL ,
                   subscriber_id int(20) NOT NULL ,
                   points int(9) default 0 ,
                   registeration_date DATE default NULL ,
                   last_update DATE default NULL ,
                   status varchar(50) default NULL ,
                   PRIMARY KEY  (card_number)
                 ) ENGINE=MyISAM DEFAULT CHARSET=utf8;
--end subscriber_cards table

--add subscriber_bills table
DROP TABLE IF EXISTS subscriber_bills;
CREATE TABLE subscriber_bills (
                   bill_number varchar(100) NOT NULL ,
                   subscriber_id int(20) NOT NULL ,
                   amount float default 0 ,
                   bill_date DATETIME default NULL ,
                   status varchar(50) default NULL ,
                   PRIMARY KEY  (bill_number)
                 ) ENGINE=MyISAM DEFAULT CHARSET=utf8;
--end subscriber_bills table

--add governorates table
DROP TABLE IF EXISTS governorates;
CREATE TABLE  governorates (
  id int(20) NOT NULL AUTO_INCREMENT,
  name varchar(200) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
--end of add governorates table

--add areas table
DROP TABLE IF EXISTS areas;
CREATE TABLE  areas (
  id int(20) NOT NULL AUTO_INCREMENT,
  name varchar(200) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
--end of add areas table

--add preferred_locations table
DROP TABLE IF EXISTS preferred_locations;
CREATE TABLE  preferred_locations (
  id int(20) NOT NULL AUTO_INCREMENT,
  name varchar(200) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
--end of add preferred_locations table

--add clients table
DROP TABLE IF EXISTS clients;
CREATE TABLE  clients (
  id int(20) NOT NULL AUTO_INCREMENT,
  name varchar(200) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
--end of add clients table

--add products table
DROP TABLE IF EXISTS products;
CREATE TABLE  products (
  id int(20) NOT NULL AUTO_INCREMENT,
  name varchar(200) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
--end of add products table

--add project_types table
DROP TABLE IF EXISTS project_types;
CREATE TABLE  project_types (
  id int(20) NOT NULL AUTO_INCREMENT,
  name varchar(200) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
--end of add project_types table

--add ushers table
DROP TABLE IF EXISTS ushers;
CREATE TABLE ushers (
                   usher_code varchar(7) NOT NULL,
                   usher_type varchar(50) NOT NULL ,
                   usher_caliber varchar(50) NOT NULL ,
                   first_name varchar(100) NOT NULL ,
                   middle_name varchar(100) default NULL ,
                   last_name varchar(100) default NULL ,
                   marital_status varchar(100) default NULL ,
                   has_kids TINYINT default 0 ,
                   kids_number int(2) default 0 ,
                   gender varchar(50) default NULL ,
                   birth_date DATE default NULL ,
                   age int(2) default 0 ,
                   address varchar(100) default NULL ,
                   appartment_number varchar(100) default NULL ,
                   street varchar(100) default NULL ,
                   area varchar(100) default NULL ,
                   governorate varchar(100) default NULL ,
                   preferred_location varchar(100) default NULL ,
                   preferred_shift varchar(100) default NULL ,
                   mobile_number varchar(50) default NULL ,
                   landline_number varchar(50) default NULL ,
                   height varchar(50) default NULL ,
                   weight varchar(50) default NULL ,
                   shirt_size varchar(50) default NULL ,
                   pants_ize varchar(50) default NULL ,
                   hair_type varchar(50) default NULL ,
                   languages varchar(200) default NULL ,
                   referred_by varchar(50) default NULL ,
                   university varchar(50) default NULL ,
                   university_degree varchar(50) default NULL ,
                   graduation_year varchar(4) default NULL ,
                   school varchar(50) default NULL ,
                   facebook_account varchar(100) default NULL ,
                   email_address varchar(50) default NULL ,
                   social_insurance TINYINT default 0 ,
                   social_insurance_no varchar(50) default NULL ,
                   social_insurance_date DATE default NULL ,
                   social_insurance_form_6 TINYINT default 0 ,
                   social_insurance_exit_date DATE default NULL ,
                   national_id_no varchar(50) default NULL ,
                   additional_information text default NULL,
                   rate varchar(50) default NULL ,
                   photo1 LONGBLOB ,
                   photo2 LONGBLOB ,
                   photo3 LONGBLOB ,
                   photo4 LONGBLOB ,
                   PRIMARY KEY  (usher_code)
                 ) ENGINE=MyISAM DEFAULT CHARSET=utf8;
--end ushers table

--add projects table
DROP TABLE IF EXISTS projects;
CREATE TABLE projects (
                   project_code varchar(7) NOT NULL,
                   client_id int(20) NOT NULL ,
                   product_id int(20) NOT NULL ,
                   project_type_id int(20) NOT NULL ,
                   project_name varchar(100) NOT NULL ,
                   project_date DATE default NULL ,
                   project_start_date DATE default NULL ,
                   project_end_date DATE default NULL ,
                   PRIMARY KEY  (project_code)
                 ) ENGINE=MyISAM DEFAULT CHARSET=utf8;
--end projects table

 --add projects_locations table
DROP TABLE IF EXISTS projects_locations;
CREATE TABLE  projects_locations (
  id int(20) NOT NULL AUTO_INCREMENT,
  name varchar(200) NOT NULL,
  location_type varchar(50) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
--end of add projects_locations table

--add ushers_time_sheet table
DROP TABLE IF EXISTS ushers_time_sheet;
CREATE TABLE ushers_time_sheet (
                   id int(20) NOT NULL AUTO_INCREMENT,
                   project_id varchar(7) NOT NULL,
                   project_location_id int(20) NOT NULL,
                   usher_code varchar(7) NOT NULL,
                   day_salary int(5) NOT NULL,
                   deduction float default 0 ,
                   debit int(5) default 0 ,
                   sheet_date DATE default NULL ,
                   uniform_delivered TINYINT default 0 ,
                   PRIMARY KEY  (id)
                 ) ENGINE=MyISAM DEFAULT CHARSET=utf8;
--end ushers_time_sheet table

--add usher_rating table
DROP TABLE IF EXISTS usher_rating;
CREATE TABLE usher_rating (
                   id int(20) NOT NULL AUTO_INCREMENT,
                   project_id varchar(7) NOT NULL,
                   project_type_id int(20) NOT NULL,
                   product_id int(20) NOT NULL,
                   usher_code varchar(7) NOT NULL,
                   client_feedback text default NULL,
                   supervisor_feedback text default NULL,
                   usher_coordinator_feedback text default NULL,
                   rate varchar(50) default NULL ,
                   PRIMARY KEY  (id)
                 ) ENGINE=MyISAM DEFAULT CHARSET=utf8;
--end usher_rating table

;;