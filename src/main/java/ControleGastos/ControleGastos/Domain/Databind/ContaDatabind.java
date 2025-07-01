package ControleGastos.ControleGastos.Domain.Databind;

import ControleGastos.ControleGastos.Domain.Model.Usuario;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

public class ContaDatabind {

  public static class IdDeserializer extends JsonDeserializer<Usuario> {
    @Override
    public Usuario deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException {
      JsonNode node = jp.getCodec().readTree(jp);
      if (node.isNumber()) {
        Usuario c = new Usuario();
        c.setId(node.asLong());
        return c;
      } else if (node.isObject()) {
        JsonNode id = node.get("id");
        Usuario c = new Usuario();
        c.setId(id.asLong());
        return c;
      }
      return null;
    }
  }

  public static class IdSerializer extends JsonSerializer<Usuario> {
    @Override
    public void serialize(Usuario entity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws
      IOException {
      jsonGenerator.writeNumber(entity.getId());
    }
  }

}
