/**
*Activity is one of the four major components of Android application.
*It handles the view of your app by controlling the layouts actions.
*You extend Activity class(or AppCompatActivity for backward compatibility) to add additional features in your app.
*You have to override superclass's lifecycle methods to achieve desired functionalities.
*Activity's all lifecycle methods have protected access so that no app can access these methods without inheriting it. It increases security.
*onCreate(Bundle object) method takes an argument of type Bundle.
*Bundle class helps to save information when your app is killed or paused.
*setContentView(Layout id) method inflates the view defined in your xml file. Layout id is associated with your xml file.
*one activity can inflate only one xml layout, and once inflated one layout , that can not be changed. 
*LifeCycle:
	When app is started:
		onCreate()->onStart()->onResume()
	When app is paused i.e., invisible (because another app is opened or by pressing home ):
		onPause()->onStop()
	When app is opened from paused or invisible state(i.e., app wasn't killed yet):
		onStart()->onResume()
	When app is killed (by pressing back button or killing it from Aplication Manager):
		onPause()->onStop()->onDestroy()
		onPause()->onStop()  will not be called if app was already in invisible state
*Log class is used to log messages for information and debugging of the app. It makes android development easy for testing and debugging by easily logging messages.
*Log class is in java.util package of java.
* @Override is "annotation" which tells the system that this method is to be overridden.

*/
