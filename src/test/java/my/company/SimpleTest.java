package my.company;

import io.qameta.allure.*;

import io.qameta.allure.model.Label;
import io.qameta.allure.model.TestResult;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.fail;


/**
 * @author Dmitry Baev charlie@yandex-team.ru
 * Date: 24.11.13
 */
@Epic("Epic level of annotation")
@Feature("SimpleTest feature")
public class SimpleTest {


    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Story("Base support for bdd annotations")
    public void simpleTest() throws Exception {

        assertThat(2, is(2));
        Allure.getLifecycle().updateTestCase(testResult ->
                testResult.getLabels().forEach(
                        label -> System.out.println(
                                "label.getName(): " + label.getName() +
                                        " label.getValue(): " + label.getValue()
                        )));

        Allure.getLifecycle().updateTestCase(testResult ->
                changeLabelByName(testResult.getLabels(), "feature", "new feature label"));

        Allure.getLifecycle().updateTestCase(testResult ->
                testResult.getLabels()
                        .stream()
                        .filter(label ->
                                Objects.equals(label.getName(), "feature"))
                        .findFirst()
                        .get()
                        .setValue("new stream feature label"));
    }


    private List<Label> changeLabelByName(List<Label> labelList, String labelName, String newLabelValue) {
        for (Label label : labelList) {
            if (label.getName().contentEquals(labelName)) {
                System.out.print("Value of label '" + label.getName() + "' changed to: ");
                label.setValue(newLabelValue);
                System.out.println("'" + label.getValue() + "'");
            }
        }
        return labelList;
    }

}/*
    @Step
    public void checkThat2is2() {
        assertThat(2, is(2));
    }

    @Step
    @Story("Story step checkThatParametrized")
    public void checkThatParametrized(boolean state) {
        checkThat2is2();
        assertThat(state, is(true));
    }

    @Test
    public void simpleTestWithSteps() throws Exception {
        checkThat2is2();
        checkThat2is2();
        checkThat2is2();
        checkThat2is2();
        checkThat2is2();
        checkThat2is2();
    }

    @Test
    public void notSimpleTestWithSteps() throws Exception {
        List<Boolean> boolList = new ArrayList<>(Arrays.asList(true, true, false, false, true, false));

        for (Boolean aBoolean : boolList) {
            checkThatParametrized(aBoolean);
        }
    }
    @Attachment
    public String makeAttach() {
        return "yeah, 2 is 2";
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    public void simpleTestWithAttachments() throws Exception {
        assertThat(2, is(2));
        makeAttach();
    }

    @Test
    @Story("Story 1 of failedTest")
    @Story("Story 2 of failedTest")
    public void failedTest() {
        fail("This test should be failed");
    }

    @Test(dependsOnMethods = "failedTest")
    public void skippedByDependencyTest() {
    }

    @Test(enabled = false)
    public void skippedTest() throws Exception {
    }

    @DataProvider
    public Object[][] dataProvider() {
        return new Object[][]{
                {1}, {2}, {3}
        };
    }

    @Test(dataProvider = "dataProvider")
    @Issue("ALR-123")
    @Issue("ALR-456")
    @Issue("ALR-789")
    @TmsLink("TMS-123")
    public void parametrizedTest(int parameter) {
        assertThat(parameter, is(2));
    }

    @DataProvider
    public Object[][] anotherDataProvider() {
        return new Object[][]{
                {"Long-long parameter value", 1, 2}, {"Even longer parameter value than long-long parameter value", 2, 3}, {"", 3, 4}
        };
    }

    @Test(dataProvider = "anotherDataProvider")
    @Issue("ALR-789")
    @TmsLink("TMS-123")
    @Feature("Svg Attachment feature")
    @Story("Story step checkThatParametrized")
    public void parametrizedTest(String parameter1, int parameter2, int parameters3) {
        assertThat(parameter2, is(2));
    }

    @Test(description = "CSV аттачмент")
    public void csvAttachmentTest() throws Exception {
        saveCsvAttachment();
    }

    @Test
    @Feature("Svg Attachment feature")
    public void svgAttachmentTest() throws Exception {
        saveSvgAttachment();
    }

    @Attachment(value = "Sample csv attachment", type = "text/csv")
    public byte[] saveCsvAttachment() throws URISyntaxException, IOException {
        return getSampleFile("sample.csv");
    }

    @Attachment(value = "Sample svg attachment", type = "image/svg+xml")
    public byte[] saveSvgAttachment() throws URISyntaxException, IOException {
        return getSampleFile("sample.svg");
    }

    private byte[] getSampleFile(String fileName) throws IOException, URISyntaxException {
        URL resource = getClass().getClassLoader().getResource(fileName);
        if (resource == null) {
            fail(format("Couldn't find resource '%s'", fileName));
        }
        return Files.readAllBytes(Paths.get(resource.toURI()));
    }
}*/

