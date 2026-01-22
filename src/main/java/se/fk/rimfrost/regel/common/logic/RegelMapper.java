package se.fk.rimfrost.regel.common.logic;

import jakarta.enterprise.context.ApplicationScoped;
import se.fk.rimfrost.regel.common.Utfall;
import se.fk.rimfrost.regel.common.integration.kafka.dto.ImmutableRegelResponse;
import se.fk.rimfrost.regel.common.integration.kafka.dto.RegelResponse;
import se.fk.rimfrost.regel.common.logic.entity.CloudEventData;
import java.util.UUID;

@ApplicationScoped
public class RegelMapper
{

   public RegelResponse toRegelResponse(UUID kundbehovsflodeId, CloudEventData cloudevent, Utfall utfall)
   {
      return ImmutableRegelResponse.builder()
            .id(cloudevent.id())
            .kundbehovsflodeId(kundbehovsflodeId)
            .kogitoparentprociid(cloudevent.kogitoparentprociid())
            .kogitorootprociid(cloudevent.kogitorootprociid())
            .kogitoprocid(cloudevent.kogitoprocid())
            .kogitorootprocid(cloudevent.kogitorootprocid())
            .kogitoprocinstanceid(cloudevent.kogitoprocinstanceid())
            .kogitoprocist(cloudevent.kogitoprocist())
            .kogitoprocversion(cloudevent.kogitoprocversion())
            .utfall(utfall)
            .build();
   }

}
