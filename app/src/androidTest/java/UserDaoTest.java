import android.test.InstrumentationTestCase;

import com.ls.realm.model.db.RealmManager;
import com.ls.realm.model.db.dao.UserDao;
import com.ls.realm.model.db.data.User;
import com.ls.realm.model.db.utils.Generator;

import java.util.List;

import io.realm.Realm;

public class UserDaoTest extends InstrumentationTestCase {

    private Realm mRealm;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

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
        User saveUser = Generator.generateUser();

        UserDao dao = RealmManager.createUserDao();
        dao.saveUser(mRealm, saveUser);
        User loadUser = (User) dao.loadUser(mRealm, saveUser.getId());

        boolean isEquals = User.equals(saveUser, loadUser);
        assertTrue(isEquals);
    }

    public void testSaveUserList() {
        List<User> saveList = Generator.generateUserList();

        UserDao dao = RealmManager.createUserDao();
        dao.saveUserList(mRealm, saveList);
        List<User> loadList = dao.loadUserList(mRealm);

        boolean isEquals = User.equals(saveList, loadList);
        assertTrue(isEquals);
    }
}
