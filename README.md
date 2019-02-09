# Personal-Information-Activity
	The first or main activity collects the basic information about a person. This activity’s view will look like a basic form. 
	The information we will collect is:
		First (given) name, Family (last) name, Age, Email, Phone, Major
	For each of the first five items (first name, last name, age, email and phone) use a text field to allow the user to enter the data. In each text field the appropriate keyboard needs to be used.
	The keyboard should not hide the text field the user is entering. All items need a label indicating what information they contain (first name, last name, etc). All items are on a separate line.
	To select a major the user needs to go to a second activity.
	Finally there is a done button. When the done button is pressed the user information is saved.
	When the app is killed and restarted the saved data is displayed in this activity.
	
	Major Selection
		To select a major the user is shown a second activity. When this activity is shown the user sees a list of advanced degrees offered by SDSU. The list is given below. The list is shown using a list fragment. When the user selects one of the advanced degrees, say Masters of Science, they are then shown a list of all the majors in that advanced degree. The list for each adPage 1 of 5
		vanced degree is given below.
		The user can select a major in that list. When they are done selecting the major bring them back to the Personal Information Activity, with the selected major shown. 
		For example MS Computer Science or Ph.D. Biology.
		When the user selects an advanced degree type, say Master of Arts, they may not see the major they are looking for. So they will want to go back to the list of advanced degree type to try
		again.The user needs to be able to cancel the selection of a major.
	
	Challenge Features
	1. Allow the user to go back to the Person Activity via the action bar. Make sure that the app
	goes back to the first activity and not create a new instance of the first activity. If the app is displaying a one of the other activities, that activity needs to be destroyed when you go back to
	the first activity.
