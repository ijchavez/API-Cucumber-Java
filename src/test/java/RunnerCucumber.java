import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

//Created a custom plugin
@CucumberOptions(plugin = {"customReportListener"},
        monochrome=true,
        glue={"Steps"},  //Packagename
        features = {"src/test/java/Features"}, //FolderName
        tags="@Test"
)

public class RunnerCucumber extends AbstractTestNGCucumberTests {

    @DataProvider(parallel = false)
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }

}