import android.test.InstrumentationTestCase;

import com.ls.realm.model.db.RealmManager;
import com.ls.realm.model.db.dao.UserDao;
import com.ls.realm.model.db.data.User;
import com.ls.realm.model.db.utils.Generator;

import java.util.List;

public class UserDaoTest extends InstrumentationTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        RealmManager.open();
        RealmManager.clear();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        RealmManager.clear();
        RealmManager.close();
    }

    public void testSaveUser() throws Exception {
        User saveUser = Generator.generateUser();

        UserDao dao = RealmManager.createUserDao();
        dao.save(saveUser);
        User loadUser = (User) dao.loadBy(saveUser.getId());

        boolean isEquals = User.equals(saveUser, loadUser);
        assertTrue(isEquals);
    }

    public void testSaveUserList() {
        List<User> saveList = Generator.generateUserList();

        UserDao dao = RealmManager.createUserDao();
        dao.save(saveList);
        List<User> loadList = dao.loadAll();

        boolean isEquals = User.equals(saveList, loadList);
        assertTrue(isEquals);
    }
}
