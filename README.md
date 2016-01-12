**Realm database**

Realm Java enables you to efficiently write your app’s model layer in a safe, persisted and fast way. 

**Prerequisites**

 1. [Realm](https://realm.io/) do not support Java outside of Android at the moment.
 2. [Android Studio](http://developer.android.com/sdk/index.html?gclid=COOv_bPoo8oCFcoGcwodcJ4AVQ) >= 0.8.6
 3. A recent version of the Android SDK.
 4. JDK version >=7.
 5. Support all [Android versions](http://developer.android.com/about/dashboards/index.html) since API Level 9 (Android 2.3
    Gingerbread & above).

**Pros:**

 - Fast in implementation
 - Very fast in data loading and saving
 - Easy to learn and use

**Cons:**

 - Not possible to extend anything else than `RealmObject`
 - No other methods except default getters and setters
 - Implementing only interfaces with no methods.

See [other limitations](https://realm.io/docs/java/latest/#current-limitations).

**Gettings started**
First of all you have to add compile `'io.realm:realm-android:x.xx.x'` dependencies to your project. See latest realm database version on [realm.io](https://realm.io/docs/java/latest/).

**Here how it looks like**

```java 
public class User extends RealmObject {

   @PrimaryKey
   private long id;
   private String firstName;
   private String lastName;
   private int age;
   private RealmList<RealmString> contactList;

   //Generated getters and setters
}
```
	
```java 
// Save a new object into database
Realm realm = Realm.getDefaultInstance(); // get a Realm instance for this thread
realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                realm.copyToRealmOrUpdate(obj);
            }
        });
```

```java
// Query with single condition
User user = realm.where(User.class).equalTo("id", id).findFirst();
```

```java
// Query with multi conditions
List<User> userList = realm.where(User.class)
                                  .equalTo("firstName", "John")
                                  .or()
                                  .equalTo("firstName", "Peter")
                                  .findAll();
```

```java
// Query to look for all users
List<User> userList = realm.where(User.class).findAll();
```

**More info**

See [Realm documentation](https://realm.io/docs/java/latest/) for more details



**License**

```
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
```
