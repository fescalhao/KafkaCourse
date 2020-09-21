package com.github.escalhao.avro.generic;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData.Record;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;

import java.io.File;
import java.io.IOException;

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
        String avroFilePath = "avro-examples/src/main/resources/avro/file/generic_customer.avro";
        Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse(avroStringToParse);

        GenericRecordBuilder recordBuilder = new GenericRecordBuilder(schema);
        recordBuilder.set("first_name", "Felipe");
        recordBuilder.set("last_name", "Escalhão");
        recordBuilder.set("age", 28);
        recordBuilder.set("height", 1.79f);
        recordBuilder.set("weight", 63f);
        recordBuilder.set("automated_email", true);

        Record customer = recordBuilder.build();
        System.out.println(customer);

        final DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(schema);
        DataFileWriter<GenericRecord> fileWriter = new DataFileWriter<>(datumWriter);
        try {
            fileWriter.create(customer.getSchema(), new File(avroFilePath));
            fileWriter.append(customer);
            fileWriter.flush();
            System.out.println("Written generic_customer.avro");
        } catch (IOException e) {
            System.out.println("Error writting generic_customer.avro");
            e.printStackTrace();
        }

        final File file = new File(avroFilePath);
        final DatumReader<GenericRecord> datumReader = new GenericDatumReader<>();
        try {
            DataFileReader<GenericRecord> fileReader = new DataFileReader<>(file, datumReader);
            GenericRecord record;
            while (fileReader.hasNext()) {
                record = fileReader.next();
                System.out.println(record.toString());
                System.out.println(record.get("first_name"));
            }
        } catch (IOException e) {
            System.out.println("Error reading generic_customer.avro");
            e.printStackTrace();
        }
    }
}
