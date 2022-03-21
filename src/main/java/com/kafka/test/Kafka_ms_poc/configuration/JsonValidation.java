package com.kafka.test.Kafka_ms_poc.configuration;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;

public class JsonValidation {
  public static String schema ="{\"$schema\": \"http://json-schema.org/draft-04/schema#\",\"title\": \"Product\",\"description\": \"A product from the catalog\",\"type\": \"object\",\"properties\": {\"id\": {\"description\": \"The unique identifier for a product\","+
        "\"type\": \"integer\"},\"name\": {\"description\": \"Name of the product\",\"type\": \"string\"}},\"required\": [\"id\", \"name\"]}";
  public static String validate(String input){
    JSONObject jsonSchema = null;
    JSONObject jsonSubject = null;
    try {
      jsonSchema = new JSONObject(schema);
      jsonSubject = new JSONObject(input);
      Schema schema_ = SchemaLoader.load(jsonSchema);
      schema_.validate(jsonSubject);
    } catch (Exception e) {
      e.printStackTrace();
      return "InValid";
    }
    return "Valid";
    
  }


}
