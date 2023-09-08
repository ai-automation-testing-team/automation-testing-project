package org.endava.automation.testing.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HtmlBuilder {

    private static final String TEST_STEPS_TEMPLATE = "<tr>\n<td>%s</td>\n<td>%s</td>\n<td>%s</td>\n</tr>\n";

    private static final String CONTENT_PLACEHOLDER = "${CONTENT}";
    private static final String DESCRIPTION_TEMPLATE = "<div class=\"description\">%s</div>";
    private static final String CODE_BLOCK_TEMPLATE = "<div class=\"code-block\"><pre class=\"code-content\">%s</pre></div>";


    private static String formatForHtml(String text) {
        return text.replace("\n", "<br>");
    }

    public static String buildHtmlFromDescriptionContent(String content) {
        String template;
        try {
            template = new String(Files.readAllBytes(Paths.get("src/main/resources/html/description.html")));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder testName = new StringBuilder();
        StringBuilder testSummary = new StringBuilder();
        StringBuilder testSteps = new StringBuilder();

        boolean insideTestSteps = false;

        String[] lines = content.split("\n");

        boolean copyCodeBlock = false;

        for (String line : lines) {
            if (line.equals("Copy code")) {
                copyCodeBlock = true;
                continue;
            }

            if (copyCodeBlock) {
                if (line.startsWith("[Test Name] - ")) {
                    testName.append(formatForHtml(line.substring("[Test Name] - ".length()).trim()));
                } else if (line.startsWith("[Test Summary] - ")) {
                    testSummary.append(formatForHtml(line.substring("[Test Summary] - ".length()).trim()));
                } else if (line.startsWith("[Test Step] - ")) {
                    if (!insideTestSteps) {
                        insideTestSteps = true;
                        // The table header is added only once here
                    }
                    String stepLine = line.substring("[Test Step] - ".length());
                    String[] parts = stepLine.split(" - \\[Test Data\\] - ");
                    if (parts.length == 2) {
                        String step = formatForHtml(parts[0].trim());
                        String[] testDataResult = parts[1].split(" - \\[Test Result\\] - ");
                        if (testDataResult.length == 2) {
                            String testData = formatForHtml(testDataResult[0].trim());
                            String testResult = formatForHtml(testDataResult[1].trim());
                            testSteps.append(String.format(TEST_STEPS_TEMPLATE, step, testData, testResult));
                        }
                    }
                }
            }
        }
        template = template.replace("${TEST_NAME}", testName.toString());
        template = template.replace("${TEST_SUMMARY}", testSummary.toString());
        template = template.replace("${TEST_STEPS}", testSteps.toString());

        return template;
    }

    public static String buildHtmlFromAnalysisContent(String content) throws IOException {
        String template = new String(Files.readAllBytes(Paths.get("src/main/resources/html/analysis.html")));

        StringBuilder dynamicContent = new StringBuilder();

        int previousEndIndex = 0;
        while (true) {
            int codeStartIndex = content.indexOf("CodeStart", previousEndIndex);
            int codeEndIndex = content.indexOf("CodeEnd", previousEndIndex);

            if (codeStartIndex == -1) {
                String remainingDescription = content.substring(previousEndIndex).trim();
                if (!remainingDescription.isEmpty()) {
                    dynamicContent.append(String.format(DESCRIPTION_TEMPLATE, formatForHtml(remainingDescription)));
                }
                break;
            }

            String description = content.substring(previousEndIndex, codeStartIndex).trim();
            String codeBlock = content.substring(codeStartIndex + "CodeStart".length(), codeEndIndex).trim();

            if (!description.isEmpty()) {
                dynamicContent.append(String.format(DESCRIPTION_TEMPLATE, formatForHtml(description)));
            }

            dynamicContent.append(String.format(CODE_BLOCK_TEMPLATE, codeBlock));
            previousEndIndex = codeEndIndex + "CodeEnd".length();
        }

        return template.replace(CONTENT_PLACEHOLDER, dynamicContent.toString());
    }
}