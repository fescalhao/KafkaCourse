/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.github.escalhao;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

/** Avro Schema to define a Customer */
@org.apache.avro.specific.AvroGenerated
public class Customer extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 3821142187958164376L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Customer\",\"namespace\":\"com.github.escalhao\",\"doc\":\"Avro Schema to define a Customer\",\"fields\":[{\"name\":\"first_name\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"First Name of the customer\"},{\"name\":\"middle_name\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"doc\":\"Middle Name of the customer\",\"default\":null},{\"name\":\"last_name\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"Last Name of the customer\"},{\"name\":\"age\",\"type\":\"int\",\"doc\":\"Customer age\"},{\"name\":\"height\",\"type\":\"float\",\"doc\":\"Height in Centimeters\"},{\"name\":\"weight\",\"type\":\"float\",\"doc\":\"Weight in Kilograms\"},{\"name\":\"automated_email\",\"type\":\"boolean\",\"doc\":\"True if Automated email functionality is enabled\",\"default\":true},{\"name\":\"phone_number\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"doc\":\"Customer phone number (optional)\",\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Customer> ENCODER =
      new BinaryMessageEncoder<Customer>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Customer> DECODER =
      new BinaryMessageDecoder<Customer>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Customer> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Customer> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Customer> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Customer>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Customer to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Customer from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Customer instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Customer fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** First Name of the customer */
   private java.lang.String first_name;
  /** Middle Name of the customer */
   private java.lang.String middle_name;
  /** Last Name of the customer */
   private java.lang.String last_name;
  /** Customer age */
   private int age;
  /** Height in Centimeters */
   private float height;
  /** Weight in Kilograms */
   private float weight;
  /** True if Automated email functionality is enabled */
   private boolean automated_email;
  /** Customer phone number (optional) */
   private java.lang.String phone_number;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Customer() {}

  /**
   * All-args constructor.
   * @param first_name First Name of the customer
   * @param middle_name Middle Name of the customer
   * @param last_name Last Name of the customer
   * @param age Customer age
   * @param height Height in Centimeters
   * @param weight Weight in Kilograms
   * @param automated_email True if Automated email functionality is enabled
   * @param phone_number Customer phone number (optional)
   */
  public Customer(java.lang.String first_name, java.lang.String middle_name, java.lang.String last_name, java.lang.Integer age, java.lang.Float height, java.lang.Float weight, java.lang.Boolean automated_email, java.lang.String phone_number) {
    this.first_name = first_name;
    this.middle_name = middle_name;
    this.last_name = last_name;
    this.age = age;
    this.height = height;
    this.weight = weight;
    this.automated_email = automated_email;
    this.phone_number = phone_number;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return first_name;
    case 1: return middle_name;
    case 2: return last_name;
    case 3: return age;
    case 4: return height;
    case 5: return weight;
    case 6: return automated_email;
    case 7: return phone_number;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: first_name = value$ != null ? value$.toString() : null; break;
    case 1: middle_name = value$ != null ? value$.toString() : null; break;
    case 2: last_name = value$ != null ? value$.toString() : null; break;
    case 3: age = (java.lang.Integer)value$; break;
    case 4: height = (java.lang.Float)value$; break;
    case 5: weight = (java.lang.Float)value$; break;
    case 6: automated_email = (java.lang.Boolean)value$; break;
    case 7: phone_number = value$ != null ? value$.toString() : null; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'first_name' field.
   * @return First Name of the customer
   */
  public java.lang.String getFirstName() {
    return first_name;
  }



  /**
   * Gets the value of the 'middle_name' field.
   * @return Middle Name of the customer
   */
  public java.lang.String getMiddleName() {
    return middle_name;
  }



  /**
   * Gets the value of the 'last_name' field.
   * @return Last Name of the customer
   */
  public java.lang.String getLastName() {
    return last_name;
  }



  /**
   * Gets the value of the 'age' field.
   * @return Customer age
   */
  public int getAge() {
    return age;
  }



  /**
   * Gets the value of the 'height' field.
   * @return Height in Centimeters
   */
  public float getHeight() {
    return height;
  }



  /**
   * Gets the value of the 'weight' field.
   * @return Weight in Kilograms
   */
  public float getWeight() {
    return weight;
  }



  /**
   * Gets the value of the 'automated_email' field.
   * @return True if Automated email functionality is enabled
   */
  public boolean getAutomatedEmail() {
    return automated_email;
  }



  /**
   * Gets the value of the 'phone_number' field.
   * @return Customer phone number (optional)
   */
  public java.lang.String getPhoneNumber() {
    return phone_number;
  }



  /**
   * Creates a new Customer RecordBuilder.
   * @return A new Customer RecordBuilder
   */
  public static com.github.escalhao.Customer.Builder newBuilder() {
    return new com.github.escalhao.Customer.Builder();
  }

  /**
   * Creates a new Customer RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Customer RecordBuilder
   */
  public static com.github.escalhao.Customer.Builder newBuilder(com.github.escalhao.Customer.Builder other) {
    if (other == null) {
      return new com.github.escalhao.Customer.Builder();
    } else {
      return new com.github.escalhao.Customer.Builder(other);
    }
  }

  /**
   * Creates a new Customer RecordBuilder by copying an existing Customer instance.
   * @param other The existing instance to copy.
   * @return A new Customer RecordBuilder
   */
  public static com.github.escalhao.Customer.Builder newBuilder(com.github.escalhao.Customer other) {
    if (other == null) {
      return new com.github.escalhao.Customer.Builder();
    } else {
      return new com.github.escalhao.Customer.Builder(other);
    }
  }

  /**
   * RecordBuilder for Customer instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Customer>
    implements org.apache.avro.data.RecordBuilder<Customer> {

    /** First Name of the customer */
    private java.lang.String first_name;
    /** Middle Name of the customer */
    private java.lang.String middle_name;
    /** Last Name of the customer */
    private java.lang.String last_name;
    /** Customer age */
    private int age;
    /** Height in Centimeters */
    private float height;
    /** Weight in Kilograms */
    private float weight;
    /** True if Automated email functionality is enabled */
    private boolean automated_email;
    /** Customer phone number (optional) */
    private java.lang.String phone_number;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.github.escalhao.Customer.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.first_name)) {
        this.first_name = data().deepCopy(fields()[0].schema(), other.first_name);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.middle_name)) {
        this.middle_name = data().deepCopy(fields()[1].schema(), other.middle_name);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.last_name)) {
        this.last_name = data().deepCopy(fields()[2].schema(), other.last_name);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.age)) {
        this.age = data().deepCopy(fields()[3].schema(), other.age);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.height)) {
        this.height = data().deepCopy(fields()[4].schema(), other.height);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.weight)) {
        this.weight = data().deepCopy(fields()[5].schema(), other.weight);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.automated_email)) {
        this.automated_email = data().deepCopy(fields()[6].schema(), other.automated_email);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
      if (isValidValue(fields()[7], other.phone_number)) {
        this.phone_number = data().deepCopy(fields()[7].schema(), other.phone_number);
        fieldSetFlags()[7] = other.fieldSetFlags()[7];
      }
    }

    /**
     * Creates a Builder by copying an existing Customer instance
     * @param other The existing instance to copy.
     */
    private Builder(com.github.escalhao.Customer other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.first_name)) {
        this.first_name = data().deepCopy(fields()[0].schema(), other.first_name);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.middle_name)) {
        this.middle_name = data().deepCopy(fields()[1].schema(), other.middle_name);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.last_name)) {
        this.last_name = data().deepCopy(fields()[2].schema(), other.last_name);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.age)) {
        this.age = data().deepCopy(fields()[3].schema(), other.age);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.height)) {
        this.height = data().deepCopy(fields()[4].schema(), other.height);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.weight)) {
        this.weight = data().deepCopy(fields()[5].schema(), other.weight);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.automated_email)) {
        this.automated_email = data().deepCopy(fields()[6].schema(), other.automated_email);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.phone_number)) {
        this.phone_number = data().deepCopy(fields()[7].schema(), other.phone_number);
        fieldSetFlags()[7] = true;
      }
    }

    /**
      * Gets the value of the 'first_name' field.
      * First Name of the customer
      * @return The value.
      */
    public java.lang.String getFirstName() {
      return first_name;
    }


    /**
      * Sets the value of the 'first_name' field.
      * First Name of the customer
      * @param value The value of 'first_name'.
      * @return This builder.
      */
    public com.github.escalhao.Customer.Builder setFirstName(java.lang.String value) {
      validate(fields()[0], value);
      this.first_name = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'first_name' field has been set.
      * First Name of the customer
      * @return True if the 'first_name' field has been set, false otherwise.
      */
    public boolean hasFirstName() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'first_name' field.
      * First Name of the customer
      * @return This builder.
      */
    public com.github.escalhao.Customer.Builder clearFirstName() {
      first_name = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'middle_name' field.
      * Middle Name of the customer
      * @return The value.
      */
    public java.lang.String getMiddleName() {
      return middle_name;
    }


    /**
      * Sets the value of the 'middle_name' field.
      * Middle Name of the customer
      * @param value The value of 'middle_name'.
      * @return This builder.
      */
    public com.github.escalhao.Customer.Builder setMiddleName(java.lang.String value) {
      validate(fields()[1], value);
      this.middle_name = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'middle_name' field has been set.
      * Middle Name of the customer
      * @return True if the 'middle_name' field has been set, false otherwise.
      */
    public boolean hasMiddleName() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'middle_name' field.
      * Middle Name of the customer
      * @return This builder.
      */
    public com.github.escalhao.Customer.Builder clearMiddleName() {
      middle_name = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'last_name' field.
      * Last Name of the customer
      * @return The value.
      */
    public java.lang.String getLastName() {
      return last_name;
    }


    /**
      * Sets the value of the 'last_name' field.
      * Last Name of the customer
      * @param value The value of 'last_name'.
      * @return This builder.
      */
    public com.github.escalhao.Customer.Builder setLastName(java.lang.String value) {
      validate(fields()[2], value);
      this.last_name = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'last_name' field has been set.
      * Last Name of the customer
      * @return True if the 'last_name' field has been set, false otherwise.
      */
    public boolean hasLastName() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'last_name' field.
      * Last Name of the customer
      * @return This builder.
      */
    public com.github.escalhao.Customer.Builder clearLastName() {
      last_name = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'age' field.
      * Customer age
      * @return The value.
      */
    public int getAge() {
      return age;
    }


    /**
      * Sets the value of the 'age' field.
      * Customer age
      * @param value The value of 'age'.
      * @return This builder.
      */
    public com.github.escalhao.Customer.Builder setAge(int value) {
      validate(fields()[3], value);
      this.age = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'age' field has been set.
      * Customer age
      * @return True if the 'age' field has been set, false otherwise.
      */
    public boolean hasAge() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'age' field.
      * Customer age
      * @return This builder.
      */
    public com.github.escalhao.Customer.Builder clearAge() {
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'height' field.
      * Height in Centimeters
      * @return The value.
      */
    public float getHeight() {
      return height;
    }


    /**
      * Sets the value of the 'height' field.
      * Height in Centimeters
      * @param value The value of 'height'.
      * @return This builder.
      */
    public com.github.escalhao.Customer.Builder setHeight(float value) {
      validate(fields()[4], value);
      this.height = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'height' field has been set.
      * Height in Centimeters
      * @return True if the 'height' field has been set, false otherwise.
      */
    public boolean hasHeight() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'height' field.
      * Height in Centimeters
      * @return This builder.
      */
    public com.github.escalhao.Customer.Builder clearHeight() {
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'weight' field.
      * Weight in Kilograms
      * @return The value.
      */
    public float getWeight() {
      return weight;
    }


    /**
      * Sets the value of the 'weight' field.
      * Weight in Kilograms
      * @param value The value of 'weight'.
      * @return This builder.
      */
    public com.github.escalhao.Customer.Builder setWeight(float value) {
      validate(fields()[5], value);
      this.weight = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'weight' field has been set.
      * Weight in Kilograms
      * @return True if the 'weight' field has been set, false otherwise.
      */
    public boolean hasWeight() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'weight' field.
      * Weight in Kilograms
      * @return This builder.
      */
    public com.github.escalhao.Customer.Builder clearWeight() {
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'automated_email' field.
      * True if Automated email functionality is enabled
      * @return The value.
      */
    public boolean getAutomatedEmail() {
      return automated_email;
    }


    /**
      * Sets the value of the 'automated_email' field.
      * True if Automated email functionality is enabled
      * @param value The value of 'automated_email'.
      * @return This builder.
      */
    public com.github.escalhao.Customer.Builder setAutomatedEmail(boolean value) {
      validate(fields()[6], value);
      this.automated_email = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'automated_email' field has been set.
      * True if Automated email functionality is enabled
      * @return True if the 'automated_email' field has been set, false otherwise.
      */
    public boolean hasAutomatedEmail() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'automated_email' field.
      * True if Automated email functionality is enabled
      * @return This builder.
      */
    public com.github.escalhao.Customer.Builder clearAutomatedEmail() {
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'phone_number' field.
      * Customer phone number (optional)
      * @return The value.
      */
    public java.lang.String getPhoneNumber() {
      return phone_number;
    }


    /**
      * Sets the value of the 'phone_number' field.
      * Customer phone number (optional)
      * @param value The value of 'phone_number'.
      * @return This builder.
      */
    public com.github.escalhao.Customer.Builder setPhoneNumber(java.lang.String value) {
      validate(fields()[7], value);
      this.phone_number = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'phone_number' field has been set.
      * Customer phone number (optional)
      * @return True if the 'phone_number' field has been set, false otherwise.
      */
    public boolean hasPhoneNumber() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'phone_number' field.
      * Customer phone number (optional)
      * @return This builder.
      */
    public com.github.escalhao.Customer.Builder clearPhoneNumber() {
      phone_number = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Customer build() {
      try {
        Customer record = new Customer();
        record.first_name = fieldSetFlags()[0] ? this.first_name : (java.lang.String) defaultValue(fields()[0]);
        record.middle_name = fieldSetFlags()[1] ? this.middle_name : (java.lang.String) defaultValue(fields()[1]);
        record.last_name = fieldSetFlags()[2] ? this.last_name : (java.lang.String) defaultValue(fields()[2]);
        record.age = fieldSetFlags()[3] ? this.age : (java.lang.Integer) defaultValue(fields()[3]);
        record.height = fieldSetFlags()[4] ? this.height : (java.lang.Float) defaultValue(fields()[4]);
        record.weight = fieldSetFlags()[5] ? this.weight : (java.lang.Float) defaultValue(fields()[5]);
        record.automated_email = fieldSetFlags()[6] ? this.automated_email : (java.lang.Boolean) defaultValue(fields()[6]);
        record.phone_number = fieldSetFlags()[7] ? this.phone_number : (java.lang.String) defaultValue(fields()[7]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Customer>
    WRITER$ = (org.apache.avro.io.DatumWriter<Customer>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Customer>
    READER$ = (org.apache.avro.io.DatumReader<Customer>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.first_name);

    if (this.middle_name == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.middle_name);
    }

    out.writeString(this.last_name);

    out.writeInt(this.age);

    out.writeFloat(this.height);

    out.writeFloat(this.weight);

    out.writeBoolean(this.automated_email);

    if (this.phone_number == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.phone_number);
    }

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.first_name = in.readString();

      if (in.readIndex() != 1) {
        in.readNull();
        this.middle_name = null;
      } else {
        this.middle_name = in.readString();
      }

      this.last_name = in.readString();

      this.age = in.readInt();

      this.height = in.readFloat();

      this.weight = in.readFloat();

      this.automated_email = in.readBoolean();

      if (in.readIndex() != 1) {
        in.readNull();
        this.phone_number = null;
      } else {
        this.phone_number = in.readString();
      }

    } else {
      for (int i = 0; i < 8; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.first_name = in.readString();
          break;

        case 1:
          if (in.readIndex() != 1) {
            in.readNull();
            this.middle_name = null;
          } else {
            this.middle_name = in.readString();
          }
          break;

        case 2:
          this.last_name = in.readString();
          break;

        case 3:
          this.age = in.readInt();
          break;

        case 4:
          this.height = in.readFloat();
          break;

        case 5:
          this.weight = in.readFloat();
          break;

        case 6:
          this.automated_email = in.readBoolean();
          break;

        case 7:
          if (in.readIndex() != 1) {
            in.readNull();
            this.phone_number = null;
          } else {
            this.phone_number = in.readString();
          }
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










