//package org.endava.automation.testing.tests;
//
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//
//import static org.endava.automation.testing.Utils.HtmlBuilder.buildHtmlFromAnalysisContent;
//import static org.endava.automation.testing.Utils.HtmlBuilder.buildHtmlFromDescriptionContent;
//
//
//public class HtmlTest {
//
//    @Test()
//    public void descriptionTest() {
//        String content = "Based on your test code and execution log, here's a concise test description in the requested Zephyr syntax:\n" +
//                "\n" +
//                "Copy code\n" +
//                "[Test Name] - Verify Fund Transfer: Savings to Checking Account\n" +
//                "[Test Summary] - Test to validate the transfer of funds from a Savings account to a Checking account in Zero Bank.\n" +
//                "\n" +
//                "[Test Step] - Login to Zero Bank - [Test Data] - Username: username, Password: password - [Test Result] - User successfully logged in.\n" +
//                "[Test Step] - Login to Zero Bank - [Test Data] - Username: username, Password: password - [Test Result] - User successfully logged in.\n" +
//                "[Test Step] - Login to Zero Bank - [Test Data] - Username: username, Password: password - [Test Result] - User successfully logged in.\n" +
//                "[Test Step] - Login to Zero Bank - [Test Data] - Username: username, Password: password - [Test Result] - User successfully logged in.\n" +
//                "[Test Step] - Navigate to 'Account Activity' and choose 'Savings' account - [Test Data] - N/A - [Test Result] - User navigates to 'Account Activity' page and selects 'Savings'.\n" +
//                "[Test Step] - Navigate to 'Account Activity' and choose 'Savings' account - [Test Data] - N/A - [Test Result] - User navigates to 'Account Activity' page and selects 'Savings'.\n" +
//                "[Test Step] - Navigate to 'Account Activity' and choose 'Savings' account - [Test Data] - N/A - [Test Result] - User navigates to 'Account Activity' page and selects 'Savings'.\n" +
//                "[Test Step] - Navigate to 'Account Activity' and choose 'Savings' account - [Test Data] - N/A - [Test Result] - User navigates to 'Account Activity' page and selects 'Savings'.\n" +
//                "[Test Step] - Calculate and verify Deposits and Withdrawals sum for 'Savings' - [Test Data] - Deposits: 1984.3, Withdrawals: 50.0 - [Test Result] - Deposits and Withdrawals are correctly summed up.\n" +
//                "[Test Step] - Calculate and verify Deposits and Withdrawals sum for 'Savings' - [Test Data] - Deposits: 1984.3, Withdrawals: 50.0 - [Test Result] - Deposits and Withdrawals are correctly summed up.\n" +
//                "[Test Step] - Calculate and verify Deposits and Withdrawals sum for 'Savings' - [Test Data] - Deposits: 1984.3, Withdrawals: 50.0 - [Test Result] - Deposits and Withdrawals are correctly summed up.\n" +
//                "[Test Step] - Calculate and verify Deposits and Withdrawals sum for 'Savings' - [Test Data] - Deposits: 1984.3, Withdrawals: 50.0 - [Test Result] - Deposits and Withdrawals are correctly summed up.\n" +
//                "[Test Step] - Calculate and verify Deposits and Withdrawals sum for 'Savings' - [Test Data] - Deposits: 1984.3, Withdrawals: 50.0 - [Test Result] - Deposits and Withdrawals are correctly summed up.\n" +
//                "[Test Step] - Initiate Money Transfer: 'Savings' to 'Checking' - [Test Data] - Amount: 1934.3, Description: Test Description - [Test Result] - Transfer initiated and amount is correctly reflected in transaction.\n" +
//                "[Test Step] - Initiate Money Transfer: 'Savings' to 'Checking' - [Test Data] - Amount: 1934.3, Description: Test Description - [Test Result] - Transfer initiated and amount is correctly reflected in transaction.\n" +
//                "[Test Step] - Initiate Money Transfer: 'Savings' to 'Checking' - [Test Data] - Amount: 1934.3, Description: Test Description - [Test Result] - Transfer initiated and amount is correctly reflected in transaction.\n" +
//                "[Test Step] - Initiate Money Transfer: 'Savings' to 'Checking' - [Test Data] - Amount: 1934.3, Description: Test Description - [Test Result] - Transfer initiated and amount is correctly reflected in transaction.\n" +
//                "This description provides a structured overview of the test and its steps in a format that should be suitable for business users, ensuring they understand what's being tested and what the expected outcomes are.";
//        String html = buildHtmlFromDescriptionContent(content);
//        //ide vo allure
//        System.out.println(html);
//    }
//
//    @Test()
//    public void analysisTest() throws IOException {
//        String content = "Based on the provided stack trace, the test fails because the sum of the elements in the list is negative, which violates the requirement of the `sum` method. The error is thrown when the sum of the list elements is negative.\n" +
//                "\n" +
//                " \n" +
//                "\n" +
//                "One possible solution to fix the issue is to modify the `sum` method to check for negative values before the sum calculation and throw an exception immediately if a negative value is found. Here's an updated version of the method:\n" +
//                "\n" +
//                " \n" +
//                "\n" +
//                "CodeStart\n" +
//                "public static String sum(List<Float> integerList) {\n" +
//                "    for (Float integer : integerList) {\n" +
//                "        if (integer < 0) {\n" +
//                "            throw new RuntimeException(\"The sum should not be negative\");\n" +
//                "        }\n" +
//                "    }\n" +
//                "    Float result = 0F;\n" +
//                "    for (Float integer : integerList) {\n" +
//                "        result += integer;\n" +
//                "    }\n" +
//                "    String s = String.valueOf(result);\n" +
//                "    return s;\n" +
//                "}\n" +
//                "CodeEnd\n" +
//                "\n" +
//                " \n" +
//                "\n" +
//                "With this modification, the method checks for negative values before performing the sum calculation. If a negative value is found, it immediately throws a `RuntimeException` with the appropriate error message.\n" +
//                "\n" +
//                " \n" +
//                "\n" +
//                "By updating the `sum` method as shown above, the test should pass successfully if all the elements in the list are positive or zero. If the test fails with negative elements, it will throw the expected exception with the \"The sum should not be negative\" message.";
//        String html = buildHtmlFromAnalysisContent(content);
//        //ide vo allure
//        System.out.println(html);
//    }
//}
