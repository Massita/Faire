# Faire // Hackathon

## About the project
To access more information about the assignment, access: ([https://github.com/Massita/Faire](https://github.com/Massita/Faire))

## About the company
Faire is a marketplace that connects wholesale makers with retail stores. There is a wide variety of makers and products available on the Faire platform and so creating user interfaces that allow retailers to find relevant products effectively is vital to our success.
Please visit the [Faire's website](https://www.faire.com) 

## About the Hackathon
The challenge is to build an innovative solution or an improvement for a product, service or process.

## Decisions
The original project consists in two screens, the first one shows a list of makers, this list can be filtered by category. Clicking on a maker(list item), it's redirect the user to the maker's page, showing all the products available for that maker.
The idea for this hackathon was to allow the user to search a maker and have the app integrated with the website via deep link.

## Used libs

-   [Gson](https://github.com/google/gson): Serialization/deserialization library to convert Java Objects into JSON and back
-   Android support library: Backward-compatible enhancements of the  _Android_  framework
-   Retrofit: Rest API requests
-   Picasso: Load images
-   Android JetPack (Paging, Navigation, Lifecycle): new components to improve the app development.
-   Material design components

## Screenshots
![Home](https://github.com/Massita/Faire/blob/hackathon/screenshots/Screenshot_1553460745.png)
![Search](https://github.com/Massita/Faire/blob/hackathon/screenshots/Screenshot_1553460765.png)
![Maker](https://github.com/Massita/Faire/blob/hackathon/screenshots/Screenshot_1553460865.png)
## Deeplink
To test the deeplink:

 1. Install the app on your android device
 2. Execute the adb command to send a deeplink to an app(implicit mode): `adb start adb shell am start -a android.intent.action.VIEW -d "http://www.faire.com/brand/{brand_token}/"` to open the brand fragment or `adb shell am start -a android.intent.action.VIEW -d "http://www.faire.com/?search=query"` to search for a query on the app.
 3. On the android device, select to execute the task using the Faire app.
 
 The navigation of the app was created using the new [Android Navigation Component](https://developer.android.com/guide/navigation/). From the fragment navigation to the deeplink navigation.
 
 ps: The deeplink won't work by directly accessing the page on Google Chrome, please check the wontfix bug reported [here](https://bugs.chromium.org/p/chromium/issues/detail?id=484815)
