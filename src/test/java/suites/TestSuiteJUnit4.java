package suites;

import cases.junit.DishAssertJUnit4Test;
import cases.junit.DishJUnit4Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(value = {DishJUnit4Test.class, DishAssertJUnit4Test.class})
public class TestSuiteJUnit4 {
}
