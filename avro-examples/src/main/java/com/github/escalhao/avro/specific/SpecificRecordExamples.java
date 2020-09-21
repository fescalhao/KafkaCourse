package com.github.escalhao.avro.specific;

import com.github.escalhao.avro.classes.Customer;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class SpecificRecordExamples {
    public static void main(String[] args) {
        String avroFilePath = "avro-examples/src/main/resources/avro/file/specific_customer.avro";

        Customer.Builder customerBuilder = Customer.newBuilder();
        customerBuilder.setFirstName("Felipe");
        customerBuilder.setLastName("Escalhão");
        customerBuilder.setAge(28);
        customerBuilder.setHeight(1.79f);
        customerBuilder.setWeight(63.5f);
        customerBuilder.setAutomatedEmail(false);
        customerBuilder.setEmails(Arrays.asList("escalhao3@gmail.com", "escalhao4@gmail.com"));

        Customer customerWriter = customerBuilder.build();

        DatumWriter<Customer> datumWriter = new SpecificDatumWriter<>(Customer.class);
        DataFileWriter<Customer> fileWriter = new DataFileWriter<>(datumWriter);
        try {
            fileWriter.create(customerWriter.getSchema(), new File(avroFilePath));
            fileWriter.append(customerWriter);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DatumReader<Customer> datumReader = new SpecificDatumReader<>(Customer.class);
        try {
            DataFileReader<Customer> fileReader = new DataFileReader<>(new File(avroFilePath), datumReader);
            Customer customerReader;
            while(fileReader.hasNext()) {
                customerReader = fileReader.next();
                System.out.println(customerReader);
                System.out.println("Nome: " + customerReader.getFirstName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
