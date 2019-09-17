# Pizza Menu


## Features

* List containing categories and their pizzas.
* Displays if a pizzas is classified as spicy or vegetarian or gluten-free
* Cart icon in the app bar on this screen and updates cart count when the user adds a pizzas to the cart.
* When the user clicks on a pizzas, they will be taken to a screen that displays pizzas details. This screen will also include an add to cart button.
* When the user clicks the add button on the Pizza Details screen, the user will return to the previous screen, where the row containing that pizzas
will now reflect that there is N of that item in the cart. It also updates the cart count on the main screen.
* URL to get pizzas list: https://api.myjson.com/bins/snyji


## Design decisions:
* MVVM architecture with Android Data binding - Bindings make UI updates easier to handle and components can be reused.
* Tab layout to display and easily navigate between different categories.
* Number Picker to select Quantity in details view - This will also avoid format errors.
* Vibrate haptic feedback for add to cart button click.
* External libraries used: 
		* Room for SQLite database
		* ElegantNumberButton for number picker
		* RxJava to manage background thread.
		* Retrofit 2 for network calls.
		* Glide for image loading
		* Stetho for debug 
* Tested on Android Oreo and Marshmallow physical devices.

###### Sample test for SQLite database included.


![Pizza App][Pizza-app]

[Pizza-app]: ./media/app_banner.jpg