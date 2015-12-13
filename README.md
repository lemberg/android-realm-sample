# android-realm-sample

**Realm database**
Realm Java enables you to efficiently write your app’s model layer in a safe, persisted and fast way. 

**Prerequisites**
Realm do not support Java outside of Android at the moment.
Android Studio >= 0.8.6
A recent version of the Android SDK.
JDK version >=7.
Support all Android versions since API Level 9 (Android 2.3 Gingerbread & above).

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

**Gettings started**
Add compile *'io.realm:realm-android:0.86.0'* to the dependencies of your project

**Here how it looks like**

```java public class User extends RealmObject {

   @PrimaryKey
   private long id;   //primary key
   private String firstName;
   private String lastName;
   private int age;
   private RealmList<RealmString> contactList;

// ... Generated getters and setters ...
}```
	
```java // Get a Realm instance for this thread
Realm realm = Realm.getDefaultInstance();

realm.beginTransaction();
realm.copyToRealmOrUpdate(obj); 
realm.commitTransaction();```

```java // Load User by id
return realm.where(Employee.class).equalTo("id", id).findFirst(); ```

**More info**
See Realm documentation for more details
