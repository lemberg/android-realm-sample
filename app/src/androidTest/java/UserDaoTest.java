import android.test.InstrumentationTestCase;

import com.ls.realm.model.db.RealmManager;
import com.ls.realm.model.db.dao.UserDao;
import com.ls.realm.model.db.data.User;
import com.ls.realm.model.db.utils.ObjectGenerator;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class UserDaoTest extends InstrumentationTestCase {

    private Realm mRealm;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(getInstrumentation().getTargetContext()).build();
        Realm.setDefaultConfiguration(realmConfiguration);

        mRealm = Realm.getDefaultInstance();

        mRealm.beginTransaction();
        mRealm.clear(User.class);
        mRealm.commitTransaction();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        mRealm.beginTransaction();
        mRealm.clear(User.class);
        mRealm.commitTransaction();
        mRealm.close();
    }

    public void testSaveUser() throws Exception {
        final CountDownLatch latch = new CountDownLatch(1);

        User writeUser = ObjectGenerator.generateUser();

        UserDao dao = RealmManager.createUserDao();
        dao.saveUser(mRealm, writeUser);

        latch.await(2, TimeUnit.SECONDS);
        User loadedUser = (User) dao.loadUser(mRealm, writeUser.getId());

        boolean isEquals = User.equals(writeUser, loadedUser);
        assertTrue(isEquals);
    }

    public void testSaveUser_withCallback() throws Exception {
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicBoolean result = new AtomicBoolean(false);

        final UserDao dao = RealmManager.createUserDao();
        final User writeUser = ObjectGenerator.generateUser();

        dao.saveUser(mRealm, writeUser, new Realm.Transaction.Callback() {
            @Override
            public void onSuccess() {
                User loadedUser = (User) RealmManager.createUserDao().loadUser(mRealm, writeUser.getId());
                boolean isEquals = User.equals(writeUser, loadedUser);

                result.set(isEquals);
                latch.countDown();
            }

            @Override
            public void onError(Exception e) {
                latch.countDown();
            }
        });


//        latch.await(10, TimeUnit.SECONDS);
        assertTrue(result.get());
    }

}
