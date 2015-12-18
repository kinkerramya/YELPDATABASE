
In this Folder

createdb.sql---->used to create tables
populate.java---->populates files based on argument files given in order yelp_business, yelp_review,yelp_checkin,Yelp_Review json files
hw3.java------>Implements the GUI part that fulfills the requirements 
					a. List of main business categories.
					b. List of sub-categories associated with the selected main category(ies).
					c. List of the attributes associated with the selected sub-categories.
					d. A dropdown menu to filter results based on days and hours the business is open/closed.
					e. List of results.
					f. List of the reviews provided for a specific business.
					
			
Note:
I would like to list few points of my approach
In the yelp_business.json file theree are categories "Good_For_kids" and "Good_for_kids". For consistency I changes them all to "Good_for_kids" .
Also the hours mentioned for few days and so if not mentioned in the file I considered it a open for 24 hours and I built the application
Also  by default the businesses will be screened as All selected attributes for shortlisting the businesses
I also included options where we can select all attributes other than checked as "None Selected", Businesses having any of the selected attributes as "Any Slected Attributes" and "Void Attributes" for selecting business irrespective of the Attributes