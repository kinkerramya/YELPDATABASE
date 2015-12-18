/*create yelp_user*/
CREATE TABLE YelpUser(
user_id VARCHAR(100) PRIMARY KEY NOT NULL,
user_name VARCHAR(100),
Avgstars DOUBLE PRECISION,
type VARCHAR(10),
fans int,
reviewcount int,
yelping_since VARCHAR(100),
votes_funny int,
votes_useful int,
votes_cool int,
comp_funny int default NULL,
comp_cool int  default NULL,
comp_writer int default NULL,
comp_photos int default NULL,
comp_more int default NULL
);

/*create Friends LIST*/
CREATE TABLE Friends_list(
user_id  VARCHAR(50) NOT NULL,
frnd_id VARCHAr(50) NOT NULL,
FOREIGN KEY(user_id) REFERENCES YelpUser(user_id)
);

/*create business*/
CREATE TABLE Business(
			  	business_id VARCHAR(100) PRIMARY KEY NOT NULL,
			  	bname VARCHAR(100) NOT NULL,
			  	address VARCHAR(200),
			  	City VARCHAR(40) NOT NULL,
			  	State VARCHAR(20) NOT NULL,
			  	open VARCHAR(10),
			  	latitude Decimal(12,9),
			  	longitude Decimal(12,9),
			  	stars int,
			  	type VARCHAR(15),
			  	reviewcount int
			  	);
				
/*create Attributes*/
CREATE TABLE Attributes(
					business_id VARCHAR(100) NOT NULL,
					Has_TV VARCHAR(20),
					Coat_Check VARCHAR(20),
					Open_24_Hours VARCHAR(20),
					Accepts_Insurance VARCHAR(20),
					Dogs_Allowed VARCHAR(20),
					Caters VARCHAR(20),
					Happy_Hour VARCHAR(20),
					Outdoor_Seating VARCHAR(20),
					Takes_Reservations VARCHAR(20),
					Waiter_Service VARCHAR(20),
					Drive_Thru VARCHAR(20),
					Order_at_Counter VARCHAR(20),
					Accepts_Credit_Cards VARCHAR(20),
					BYOB_Corkage VARCHAR(20),
					By_Appointment_Only VARCHAR(20),
					Take_out VARCHAR(20),
					Wheelchair_Accessible VARCHAR(20),
					BYOB VARCHAR(20),
					Delivery VARCHAR(20),
					Corkage VARCHAR(20),
					Alcohol_beer_and_wine VARCHAR(20),
					Alcohol_none VARCHAR(20),
					Alcohol_full_bar VARCHAR(20),
					Price_Range_1 VARCHAR(20),
					Price_Range_2 VARCHAR(20),
					Price_Range_3 VARCHAR(20),
					Price_Range_4 VARCHAR(20),
					Wi_Fi_no VARCHAR(20),
					Wi_Fi_paid VARCHAR(20),
					Wi_Fi_free VARCHAR(20),
					Noise_Level_very_loud VARCHAR(20),
					Noise_Level_average VARCHAR(20),
					Noise_Level_loud VARCHAR(20),
					Noise_Level_quiet VARCHAR(20),
					Attire_dressy VARCHAR(20),
					Attire_formal VARCHAR(20),
					Attire_casual VARCHAR(20),
					Ages_Allowed_allages VARCHAR(20),
					Ages_Allowed_18plus VARCHAR(20),
					Ages_Allowed_21plus VARCHAR(20),
					Smoking_no VARCHAR(20),
					Smoking_yes VARCHAR(20),
					Smoking_outdoor VARCHAR(20),
					Parking_lot VARCHAR(20),
					Parking_validated VARCHAR(20),
					Parking_street VARCHAR(20),
					Parking_garage VARCHAR(20),
					Parking_valet VARCHAR(20),
					Good_For_Groups VARCHAR(20),
					Good_for_Kids VARCHAR(20),
					Good_For_Dancing VARCHAR(20),
					Good_For_lunch VARCHAR(20),
					Good_For_dessert VARCHAR(20),
					Good_For_brunch VARCHAR(20),
					Good_For_breakfast VARCHAR(20),
					Good_For_dinner VARCHAR(20),
					Good_For_latenight VARCHAR(20),
					Music_karaoke VARCHAR(20),
					Music_background_music VARCHAR(20),
					Music_playlist VARCHAR(20),
					Music_dj VARCHAR(20),
					Music_jukebox VARCHAR(20),
					Music_video  VARCHAR(20),
					Music_live VARCHAR(20),
					Ambience_trendy VARCHAR(20),
					Ambience_romantic VARCHAR(20),
					Ambience_upscale VARCHAR(20),
					Ambience_divey VARCHAR(20),
					Ambience_casual VARCHAR(20),
					Ambience_hipster  VARCHAR(20),
					Ambience_intimate VARCHAR(20),
					Ambience_touristy  VARCHAR(20),
					Ambience_classy VARCHAR(20),
					Payment_Type_discover VARCHAR(20),
					Payment_Type_amex VARCHAR(20),
					Payment_Type_cash_only VARCHAR(20),
					Payment_Type_visa VARCHAR(20),
					Payment_Type_mastercard VARCHAR(20),
					Hair_extensions VARCHAR(20),
					Hair_curly VARCHAR(20),
					Hair_asian VARCHAR(20),
					Hair_coloring VARCHAR(20),
					Hair_africanamerican VARCHAR(20),
					Hair_perms VARCHAR(20),
					Hair_straightperms  VARCHAR(20),
					Hair_kids  VARCHAR(20),
					Diet_dairy_free  VARCHAR(20),
					Diet_soy_free VARCHAR(20),
					Diet_vegetarian VARCHAR(20),
					Diet_halal VARCHAR(20),
					Diet_vegan VARCHAR(20),
					Diet_kosher VARCHAR(20),
					Diet_gluten_free VARCHAR(20),
					FOREIGN KEY(business_id) REFERENCES Business(business_id)
					);
					
/*create BAattributes*/
CREATE TABLE BAttributes(
		business_id VARCHAR(100),
		Hasattribute VARCHAR(50),
		FOREIGN KEY(business_id) REFERENCES Business(business_id)
		);
		
/*create Categories*/
CREATE TABLE Categories(
				business_id VARCHAR(100) NOT NULL,
				Maincategory VARCHAR(100) NOT NULL,
				Subcategory VARCHAR(100) NOT NULL,
				FOREIGN KEY(business_id) REFERENCES Business(business_id));
		
/*create hours*/
CREATE TABLE Hours(
				business_id VARCHAR(100) NOT NULL,
				Monopen float,
				Monclose float,
				Tuesopen float,
				Tuesclose float,
				wedopen float,
				wedclose float,
				thuopen float,
				thuclose VARCHAR(10),
				friopen VARCHAR(10),
				friclose VARCHAR(10),
				satopen VARCHAR(10),
				satclose VARCHAR(10),
				sunopen VARCHAR(10),
				sunclose VARCHAR(10),
				FOREIGN KEY(business_id) REFERENCES Business(business_id)
				);
				
/*create Reviews*/
CREATE TABLE Yelp_review(
				review_id VARCHAR(100) PRIMARY KEY NOT NULL,
				user_id VARCHAR(100) NOT NULL,
				business_id VARCHAR(100) NOT NULL,
				type VARCHAR(20),
				rstars int,
				rdate VARCHAR(100),
				rvotes_funny int,
				rvotes_useful int,
				rvotes_cool int,
				Text CLOB,
				FOREIGN KEY(user_id) REFERENCES YelpUser(user_id),
				FOREIGN KEY(business_id) REFERENCES Business(business_id)
				);