**Realm database**

Realm Java enables you to efficiently write your app’s model layer in a safe, persisted and fast way. 

**Prerequisites**

 1. Realm do not support Java outside of Android at the moment.
 2. Android Studio >= 0.8.6
 3. A recent version of the Android SDK.
 4. JDK version >=7.
 5. Support all Android versions since API Level 9 (Android 2.3
    Gingerbread & above).

**Pros:**

 - Fast in implementation
 - Very fast in data loading and saving
 - Easy to learn and use

**Cons:**

 - In object we can only have:
 - private instance fields.
 - default getter and setter methods.
 - static fields, both public and private.
 - static methods.
 - implementing interfaces with no methods.
 - requires pre-compiled (.so) files integration for all supported
   processor models, making app useless in case if new processor
   architecture is released and increasing app size by ~2mb per
   architecture.
 - Is still in beta (top version is 0.86.0) and license might be updated
   after release (like with genymotion).

**Getting started**

Add compile `'io.realm:realm-android:0.86.0'` to the dependencies of your project

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
// Get a Realm instance for this thread
Realm realm = Realm.getDefaultInstance();
realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                realm.copyToRealmOrUpdate(obj);
            }
        });
```

```java
// Load User by id
return realm.where(User.class).equalTo("id", id).findFirst(); 
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
