package ml.canecarroarmato.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BlogControllerTest.class, ControllerTests.class, CrudRepositoryTest.class, ModelTests.class,
		PostControllerTest.class, PostModelTest.class, UserControllerTest.class, UserModelTest.class })
public class AllTests {

}
