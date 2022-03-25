# Notes App
**Notes** App is simple Note Taking application based on local database application performing CRUD operations.

<img src="https://github.com/AdityaShidlyali/NotesApp/blob/main/images/notes_app.jpg" />

## Features
* User can create and save Notes persistently.
* User can update notes.
* User can delete completed notes.
* User can prioratise their notes.
* Swipe to delete provides ease of deletion of note.

## Project structure (MVVM)
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

## Licence
```
MIT License

Copyright (c) 2022 Aditya Shidlyali

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
