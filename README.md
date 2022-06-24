# MVIAnimals
Simple Android app that gets animal data from an api with the MVI architecture pattern

# What Is MVI?
MVI stands for Model-View-Intent. It acts as an extra layer to the MVVM pattern. An intent is a user interaction that tells the app to change its state depending on the intent that was sent to the model. The model is a representation of the state of the UI. In the case of this app, the model has four possible states: Idle, Loading, Animals, and Error. Idle is the initial state of the app when there is no user interaction. Loading is when the app is trying to retrieve data from the backend. Animals is when the app has successfully retrieved and displays the data. Finally, Error is when there is a problem loading the data from the backend.

# App Overview
MVIAnimals is a simple app that retrieves animal data from a json file. When the app starts, there is a button that changes the state from Idle to Loading, and the screen will display a spinning progress bar. If the data is retrieved, the state changes from Loading to Animals, and the screen will display the data. However, if the app cannot retrieve the data, the state changes from Loading to Error, and the app will display an error message.
