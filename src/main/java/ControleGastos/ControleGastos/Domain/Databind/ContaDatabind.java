package ControleGastos.ControleGastos.Domain.Databind;

import ControleGastos.ControleGastos.Domain.Model.Conta;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

public class ContaDatabind {

  public static class IdDeserializer extends JsonDeserializer<Conta> {
    @Override
    public Conta deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException {
      JsonNode node = jp.getCodec().readTree(jp);
      if (node.isNumber()) {
        Conta c = new Conta();
        c.setId(node.asInt());
        return c;
      } else if (node.isObject()) {
        JsonNode id = node.get("id");
        Conta c = new Conta();
        c.setId(id.asInt());
        return c;
      }
      return null;
    }
  }

  public static class IdSerializer extends JsonSerializer<Conta> {
    @Override
    public void serialize(Conta entity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws
      IOException {
      jsonGenerator.writeNumber(entity.getId());
    }
  }

}
