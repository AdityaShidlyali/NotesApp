# Notes App
**Notes** App is simple Note Taking application based on local database application performing CRUD operations.

<p>
<img src="https://github.com/anandwana001/RoomAccounting/blob/main/screenshots/AndroidAppDarkMode/Screenshot_20220310-181634.png" height=450 width=230 />
</p>

## Features
* User can create and save Notes persistently.
* User can update notes.
* User can delete completed notes.
* User can prioratise their notes.
* Swipe to delete provides ease of deletion of note.

## Project structure
* adapters
* models
    * db
        * entities
* repositories
* view
* viewmodels

## Android with Java
* Prepopulating the data with Room Database Callbacks, which gives user an overview of the user interface to add notes according to their needs.
* List Adapter which is subclass of RecyclerView.Adapter provides perfomance improvement features like Diffutil, which populates the only view which is added to the existing list.
* Generic Async Tasks are implemented to run the long running tasks of the IO operations like inserting, updating, deleting etc., the items from the Room Database.
* The old startActivityForResult is implmented with ActivityResultLauncher, which provides ease of handling the intents and data flow of among the activities.

## Tech stack used
- [Room DB](https://developer.android.com/training/data-storage/room) - Local Persistant Database for Application.
- [Async Task](https://developer.android.com/reference/android/os/AsyncTask) - For asynchronous operations.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Lifecycle aware library to manage data observing the lifecycle of licecycle owner.
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - State holder class to hold observable data.
