import net.cloudcentrik.myevent.db.user.User;
import net.cloudcentrik.myevent.db.user.UserRepository;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DatabaseTest {
    @Test
    public void saveTest() {
        UserRepository userRepository=new UserRepository();
        //User user=new User("mousumy","8723678","mousumy@cloudcentrik.com","0704323784642");
        //userRepository.saveUser(user);
        final List<User> userList=userRepository.listAllUser();
        assertThat(userList.size()).isGreaterThan(0);
    }
}
