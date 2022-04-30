import com.jal.wholesales.dao.config.ConfigurationManager;
import com.jal.wholesales.dao.util.ConstantConfigUtils;

public class TestConfig {
	public static void main(String args[]) {
		String DRIVER_CLASS = ConfigurationManager.getInstance().getParameter(ConstantConfigUtils.WHOLESALES_PROPERTIES, "db.clase");
		System.out.println(DRIVER_CLASS);

				String PASS =ConfigurationManager.getInstance().getParameter(ConstantConfigUtils.WHOLESALES_PROPERTIES, "db.password");
		System.out.println(PASS);
	}
}
