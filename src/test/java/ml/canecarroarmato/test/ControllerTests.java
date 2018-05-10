package ml.canecarroarmato.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ UserControllerTest.class, PostControllerTest.class, BlogControllerTest.class })
public class ControllerTests {

}
