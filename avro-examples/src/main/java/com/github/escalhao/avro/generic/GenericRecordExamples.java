package com.github.escalhao.avro.generic;

import org.apache.avro.Schema;

public class GenericRecordExamples {
    public static void main(String[] args) {
        String avroStringToParse = "{\n" +
                "  \"type\": \"record\",\n" +
                "  \"namespace\": \"com.github.escalhao\",\n" +
                "  \"name\": \"customer\",\n" +
                "  \"doc\": \"Avro Schema to define a Customer\",\n" +
                "  \"fields\": [\n" +
                "    {\n" +
                "    \"name\": \"first_name\",\n" +
                "    \"type\": \"string\",\n" +
                "    \"doc\": \"First Name of the customer\"\n" +
                "  },\n" +
                "    {\n" +
                "      \"name\": \"middle_name\",\n" +
                "      \"type\": [\n" +
                "        \"null\",\n" +
                "        \"string\"\n" +
                "      ],\n" +
                "      \"doc\": \"Middle Name of the customer\",\n" +
                "      \"default\": null\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"last_name\",\n" +
                "      \"type\": \"string\",\n" +
                "      \"doc\": \"Last Name of the customer\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"age\",\n" +
                "      \"type\": \"int\",\n" +
                "      \"doc\": \"Customer's age\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"height\",\n" +
                "      \"type\": \"float\",\n" +
                "      \"doc\": \"Height in Centimeters\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"weight\",\n" +
                "      \"type\": \"float\",\n" +
                "      \"doc\": \"Weight in Kilograms\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"automated_email\",\n" +
                "      \"type\": \"boolean\",\n" +
                "      \"doc\": \"True if Automated email functionality is enabled\",\n" +
                "      \"default\": true\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse(avroStringToParse);

    }
}
