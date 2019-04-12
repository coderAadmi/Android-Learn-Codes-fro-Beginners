To do a job in background which can take more time or be heavy in performance, UI thread can not be used as it needs to update
itself in every 16ms.
So, To perform some operation simultaneously or in background along with UI thread, Threads can be used.
Android gives us a very simple and easy class to perform such tasks. 'AsyncTask'
AsyncTask is an abstract class whose doInBackground() method is to be overriden.
Override doInBackground() method to perform any task.
There are other methods also like onPreExecute(), onPostExecute() and publishProgress().
To download data from any URL, just create an URL object and pass the http address in it's constructor.
Then create an HttpURLConnection object and get reference from URL.openConnection() method.
then just read the input stream of HttpURLConnection object in a string object and decode it.
![](DownloaingImage%20using%20AsyncTask/Screenshot%20from%202019-04-11%2023-39-37.png)
![](DownloaingImage%20using%20AsyncTask/Screenshot%20from%202019-04-11%2023-39-43.png)
