package DynaProxy.JDK;

public class UserServiceImpl implements UserService {
	public void add() {
		System.out.println("This is add service");
	}

	public void delete(int id) {
		System.out.println("This is delete service：delete " + id);
	}
}
