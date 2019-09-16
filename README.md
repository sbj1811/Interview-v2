# Pizza Menu


## Features

* List containing categories and their pizzas.
* Displays if a pizzas is classified as spicy or vegetarian or gluten-free
* Place a cart icon in the app bar on this screen and update cart count when the user adds a pizzas to the cart.
* When the user clicks on a pizzas, they should be taken to a screen that displays pizzas details.This screen should also include an add to cart button.
* When the user clicks the add button on the Pizza Details screen, the user should return to the previous screen, where the row containing that pizzas
should now reflect that there is N of that item in the cart.
* 5 should also update the cart count on the main screen.
* URL to get pizzas list: https://api.myjson.com/bins/snyji


## Design decisions:
* MVVM architecture with Android Data binding - Bindings make UI updates easier to handle and components can be reused.
* Tab layout to display and easily naviage between different categories.
* Number Picker to select Quantity in detals view - This will also avoid format errors.
* External libraries used: 
		* Room for SQLite database
		* ElegantNumberButton for number picker
		* RxJava to manage background thread.
		* Retrofit 2 for network calls.
		* Glide for image loading
		* Stetho for debug 
* Tested on Android Oreo and Marshmallow physical devices.


![Pizza App][Pizza-app]

[Pizza-app]: ./media/app_banner.jpg