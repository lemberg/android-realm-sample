import android.content.Context;
import android.test.InstrumentationTestCase;

import com.ls.realm.model.db.RealmManager;
import com.ls.realm.model.db.dao.UserDao;
import com.ls.realm.model.db.data.User;
import com.ls.realm.model.db.utils.DataGenerator;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;

public class UserDaoTest extends InstrumentationTestCase {

    private Realm mRealm;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        Context context = getInstrumentation().getTargetContext();
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(context).build();
        Realm.setDefaultConfiguration(realmConfiguration);

        mRealm = RealmManager.open();
        RealmManager.clear();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        RealmManager.clear();
        mRealm.close();
    }

    public void testSaveUser() throws Exception {
        User saveUser = DataGenerator.generateUser();

        UserDao dao = RealmManager.createUserDao();
        dao.save(saveUser);
        User loadUser = (User) dao.loadBy(saveUser.getId());

        boolean isEquals = User.equals(saveUser, loadUser);
        assertTrue("Users are not equal", isEquals);
    }

    public void testSaveUserList() {
        List<User> saveList = DataGenerator.generateUserList();

        UserDao dao = RealmManager.createUserDao();
        dao.save(saveList);
        List<User> loadList = dao.loadAll();

        boolean isEquals = User.equals(saveList, loadList);
        assertTrue("Users Lists are not equal", isEquals);
    }

    public void testLoadBy() {
        User saveUser = DataGenerator.generateUser();

        UserDao dao = RealmManager.createUserDao();
        dao.save(saveUser);
        User loadUser = (User) dao.loadBy(saveUser.getId());

        boolean isEquals = User.equals(saveUser, loadUser);
        assertTrue(isEquals);
    }

    public void testRemove() {
        User saveUser = DataGenerator.generateUser();

        UserDao dao = RealmManager.createUserDao();
        dao.save(saveUser);

        RealmObject realmObject = dao.loadBy(saveUser.getId());
        dao.remove(realmObject);

        boolean isEmpty = dao.count() == 0;
        assertTrue("User table is not empty", isEmpty);
    }

    public void testRemoveAll() {
        List<User> saveList = DataGenerator.generateUserList();

        UserDao dao = RealmManager.createUserDao();
        dao.save(saveList);
        dao.removeAll();

        boolean isEmpty = dao.count() == 0;
        assertTrue("User table is not empty", isEmpty);
    }
}
