Grocery List App
~~~~~~~~~~~~~~~~~~~~
Android app that allows a user create grocery lists and add items to the grocery lists.
It also allows you to create a password to protect your grocery lists.

The app is composed of four screens. The first screen is the login screen that allows a
user to enter a password. The second screen is the registration screen which the user to
create password. After a user enters/creates a password, a third screen appears which
allows the user add grocery lists. Once the user create the grocery lists, a four screen
can be accessed which allows the user to add items to the list.

This directory contains the full implementation of a grocery application for
the Android platform, demonstrating the facilities that applications
will use.  You can run the application either directly on the emulator
or on a device.


The files contained here:
~~~~~~~~~~~~~~~~~~~~~~~~~

AndroidManifest.xml

This XML file describes to the Android platform what your application can do.
It is a required file, and is the mechanism you use to show your application
to the user (in the app launcher's list), handle data types, etc.


src/*

Under this directory is the Java source for for your application.


src/main/java/com.example.groceryapp/GroceryList.java

This is the implementation of the "grocery list items". This activity allows
the user to either add, delete or mark items on the list.

src/main/java/com.example.groceryapp/GroceryView.java

This is the implementation of the "grocery lists". This activity allows
the user to add different kinds of lists to the app.

src/main/java/com.example.groceryapp/LoginFragment.java

This is the implementation of the Login activities. This fragment allows
the user to login using an already existing password.

src/main/java/com.example.groceryapp/MainActivity.java

This is the implementation of the "Main activity". This activity executes calls
to the login fragments.

src/main/java/com.example.groceryapp/RegisterFragment.java

This is the implementation of the registering activities. This fragment allows
the user to create a password to be used when accessing the app.


res/*

Under this directory are the resources for your application.


res/main/layout/activity_grocery_list.xml

This describes the layout of the grocery list. It alsodescribes the floating
button which is used add items to the list.

res/main/layout/activity_grocery_view.xml

This describes the layout of the view of grocery lists. It also describes
the floating button which is used add new grocery lists.

res/main/layout/activity_main.xml

This describes the layout main activity. Nothing much is described here
because the main activity only performs calls to other activities.

res/main/layout/custom_view.xml

This is a custom template to describe the layout of the input dialog used to
enter items to the grocery list.

res/main/layout/fragment_login.xml

This describes the layout of the view of the login page. It describes
an input box for the password.

res/main/layout/fragment_register.xml

This describes the layout of the view of the password registration page. It describes
an input box for the password.

res/drawable/...

The res/drawable/ directory contains XML files describing more complex drawings.
Like layout files, the base name is used for the resulting resource name.


res/values/colors.xml
res/values/strings.xml
res/values/styles.xml
res/values/dimen.xml

These XML files describe additional resources included in the application.
They all use the same syntax; all of these resources could be defined in one
file, but we generally split them apart as shown here to keep things organized.

To install and configure project
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
1. On a PC or Mac machine, clone the GitHub repository (Code, Download ZIP).
2. Unzip the download folder
3. Rename the folder by removing the 'master' in the name
4. Open Android Studio. If it a project is already open, then close that project.
5. In the Quick Start menu, select Open an existing Android Studio project.
6. Select the GroceryApp directory you cloned from the GitHub repository.
7. Click OK.
8. To run on an emulator,
    a. In Android Studio, click the Tools menu, select AVD Manager to open the
        Android Virtual Device Manager.
    b. Use the Android Virtual Device Manager to create the Pixel 2 device. Click
       Create Virtual Device.
    c. In the category list select Phone, then select the Pixel 2 device and click Next.
    d. Select Q. (Install Q if not already installed) Click Next.
    e. Ensure the Orientation is set to Portrait and click Finish.
    f. In the Android Virtual Device Manager, start the Pixel 2 Android Virtual Device.
    g. Wait for the device to boot, and unlock it.
    h. In Android Studio, click the Run menu and select Run app.

