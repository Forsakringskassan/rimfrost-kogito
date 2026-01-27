package se.fk.rimfrost.regel.common.integration.kafka.dto;

import org.immutables.value.Value;
import java.util.UUID;
import se.fk.rimfrost.regel.common.Utfall;

@Value.Immutable
public interface RegelResponse
{

   UUID id();

   UUID kundbehovsflodeId();

   String kogitorootprocid();

   UUID kogitorootprociid();

   UUID kogitoparentprociid();

   String kogitoprocid();

   UUID kogitoprocinstanceid();

   String kogitoprocist();

   String kogitoprocversion();

   Utfall utfall();

   String type();

}
