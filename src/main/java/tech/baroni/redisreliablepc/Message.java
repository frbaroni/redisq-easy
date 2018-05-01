package tech.baroni.redisreliablepc;

import ai.grakn.redisq.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.Value;

@Data
@Builder
@ToString
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
}
