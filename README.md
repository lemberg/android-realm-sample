
**Realm database**

Realm Java enables you to efficiently write your app’s model layer in a safe, persisted and fast way. 

**Realm is a replacement for SQLite & Core Data**

 - *Easy to Use*

	Realm is not an ORM on top of SQLite. Instead it uses its own persistence engine, built for simplicity (& speed).

 - *Fast*

 Thanks to its zero-copy design, Realm is much faster than an ORM, and is often faster than raw SQLite as well. 

 - *Cross-Platform*

 You can share Realm files across platforms and use the same high-level models.

See graphic performance comparison to other databases [here](https://realm.io/news/realm-for-android/#realm-for-android).

**Prerequisites**

 - [Realm](https://realm.io/) do not support Java outside of Android at the moment.
 - [Android Studio](http://developer.android.com/sdk/index.html?gclid=COOv_bPoo8oCFcoGcwodcJ4AVQ) >= 0.8.6
 - A recent version of the Android SDK.
 - JDK version >=7.
 - Support all [Android versions](http://developer.android.com/about/dashboards/index.html) since API Level 9 (Android 2.3 Gingerbread & above).

**Here how it looks like**

```java
public class User extends RealmObject {
	@PrimaryKey
    private long id;
    private String firstName;
	//basic implementation
}
```
```java
//write single User object
 realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.copyToRealmOrUpdate(user);
            }
        });
```
```java
//Query to look for all users
realm.where(User.class).findAll();
```

**Installation**

 1. Make sure your project uses jcenter as a dependency repository
        (default on latest version of the Android Gradle plugin).

 2. Add compile `'io.realm:realm-android:0.87.2'` to the dependencies of
        your project. See latest version on [realm.io](https://realm.io/docs/java/latest/)

 3. In the Android Studio menu: Tools->Android->Sync Project with Gradle
        Files.

**Best Practices**

*UI or not UI?*

 - Typically Realm is fast enough to read and write data on Android’s
   main thread. However, write transactions are blocking across threads
   so in order to prevent accidental Application Not Responding Errors (ANR’s) there are advise to perform
   all Realm write operations on a background thread (not Android’s main
   thread).

```java
RealmResults<User> userList = realm.where(User.class).findAllAsync();
userList.addChangeListener(new RealmChangeListener() {
            @Override
            public void onChange() {
                //userList is now filled with data
                //update UI here
            }
        });
```

```java
  realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.copyToRealmOrUpdate(user);
            }
        });
```

 - However while you working with a small amount of data, don't be afraid to write/load data on UI thread. 
 See according [Christian Melchior](http://stackoverflow.com/users/1389357/christian-melchior) answer on [StackOverflow](http://stackoverflow.com/questions/27805580/realm-io-and-asynchronous-queries).


*Controlling the lifecycle of Realm instances*

 - To avoid the overhead of opening and closing the Realm data
   connection, Realm has a reference counted cache internally. This
   implies that calling `Realm.getDefaultInstance()` multiple times on the
   same thread is free, and the underlying resources will automatically
   be released when all instances are closed.
   
   This means that on the UI thread the easiest and safest approach is
   to open a Realm instance in all your Activities and Fragments and
   close it again when the `Activity` or `Fragment` is destroyed.

```java
public class RealmApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration realmConfiguration = new RealmConfiguration
        .Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
```
```java
public class RealmActivity extends AppCompatActivity {

    protected Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRealm != null) {
            mRealm.close();
        }
    }
}
```
*Reuse RealmResults and RealmObjects*

 - On the UI thread and all other Looper threads, all `RealmObjects` and
   `RealmResults` are automatically refreshed when changes are made to the
   Realm. This means that it isn’t necessary to fetch those objects
   again when reacting to a `RealmChangedListener`. The objects are
   already updated and ready to be redrawn on the screen.

**Keep in mind while using Realm (Limitations)**

`RealmObject` can have:

 - Only private instance fields.
 - Only default getter and setter methods.
 - Static fields, both public and private.
 - Static methods.
 - Implementing interfaces with no methods.

You can only save objects that extend `RealmObject` inside a Realm.
That means that you have to declare an `RealmList` if you want to save a List, or extend `RealmObject` while saving an object. 



See [StackOverflow](http://stackoverflow.com/questions/30097810/listobject-or-realmlistrealmobject-on-realm-android) answer for data saving.

**More Info**

See [Realm documentation](https://realm.io/docs/java/latest/) for more details

**License**

    The MIT License (MIT)
    
    Copyright (c) 2015 Lemberg Solutions
    
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
