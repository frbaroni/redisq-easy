package tech.baroni.redisreliablepc;

import ai.grakn.redisq.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Value;

@Data
@Builder
@ToString
@JsonDeserialize(builder = Message.MessageBuilder.class)
public class Message implements Document {
  @JsonProperty
  String id;

  @JsonProperty
  String destination;

  @JsonProperty
  String content;

  @JsonIgnore
  @Override
  public String getIdAsString() {
    return id;
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static final class MessageBuilder {};
}
