# Notes App
**Note** App is simple Note Taking application based on local database application performing CRUD operations.
#### Features
---
* User can create and save Notes persistently.
* User can update notes.
* User can delete completed notes.
* User can prioratise their notes.
* Swipe to delete provides ease of deletion of note.

#### Android with Java
---
* Prepopulating the data with Room Database Callbacks, which gives user an overview of the user interface to add notes according to their needs.
* List Adapter which is subclass of RecyclerView.Adapter provides perfomance improvement features like Diffutil, which populates the only view which is added to the existing list.
* Generic Async Tasks are implemented to run the long running tasks of the IO operations like inserting, updating, deleting etc., the items from the Room Database.
* The old startActivityForResult is implmented with ActivityResultLauncher, which provides ease of handling the intents and data flow of among the activities.

