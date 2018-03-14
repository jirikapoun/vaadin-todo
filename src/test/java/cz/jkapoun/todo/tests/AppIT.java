import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.GridElement;
import com.vaadin.testbench.elements.TextFieldElement;
import com.vaadin.testbench.parallel.ParallelTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class AppIT extends ParallelTest {

  private static final String URL="http://localhost";
  private static final String PORT="8080";

  @Before
  @Override
  public void setup() throws Exception {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\jiri.kapoun\\chromedriver.exe");
    setDriver(new ChromeDriver());
    getDriver().get(URL+":"+PORT);
  }

  @After
  public void tearDown() throws Exception {
    getDriver().quit();
  }

  @Test
  public void test() {
    TextFieldElement newTaskField  = $(TextFieldElement.class).first();
    ButtonElement    addTaskButton = $(ButtonElement   .class).first();
    GridElement      taskGrid      = $(GridElement     .class).first();

    long rowCountBefore = taskGrid.getRowCount();
    Assert.assertEquals(1, rowCountBefore);
    
    newTaskField.setValue("Test");
    addTaskButton.click();

    long rowCountAfter = taskGrid.getRowCount();
    Assert.assertEquals(2, rowCountAfter);
  }
}
