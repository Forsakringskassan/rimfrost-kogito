package se.fk.rimfrost.regel.common.presentation.kafka;

import jakarta.enterprise.context.ApplicationScoped;
import se.fk.rimfrost.OperativtUppgiftslagerResponseMessage;
import se.fk.rimfrost.OperativtUppgiftslagerStatusMessage;
import se.fk.rimfrost.Status;
import se.fk.rimfrost.regel.common.RegelRequestMessagePayload;
import se.fk.rimfrost.regel.common.logic.dto.*;
import java.util.UUID;

@ApplicationScoped
public class RegelKafkaMapper
{
   public RegelDataRequest toRegelDataRequest(RegelRequestMessagePayload rtfRequest)
   {
      return ImmutableRegelDataRequest.builder()
            .id(UUID.fromString(rtfRequest.getId()))
            .kogitorootprociid(UUID.fromString(rtfRequest.getKogitorootprociid()))
            .kogitorootprocid(rtfRequest.getKogitorootprocid())
            .kogitoparentprociid(UUID.fromString(rtfRequest.getKogitoparentprociid()))
            .kogitoprocid(rtfRequest.getKogitoprocid())
            .kogitoprocinstanceid(UUID.fromString(rtfRequest.getKogitoprocinstanceid()))
            .kogitoprocist(rtfRequest.getKogitoprocist())
            .kogitoprocversion(rtfRequest.getKogitoprocversion())
            .kundbehovsflodeId(UUID.fromString(rtfRequest.getData().getKundbehovsflodeId()))
            .type(rtfRequest.getType())
            .build();
   }

   public OulResponse toOulResponse(OperativtUppgiftslagerResponseMessage oulResponseMessage)
   {
      return ImmutableOulResponse.builder()
            .kundbehovsflodeId(UUID.fromString(oulResponseMessage.getKundbehovsflodeId()))
            .uppgiftId(UUID.fromString(oulResponseMessage.getUppgiftId()))
            .build();
   }

   public OulStatus toOulStatus(OperativtUppgiftslagerStatusMessage oulStatusMessage)
   {

      return ImmutableOulStatus.builder()
            .kundbehovsflodeId(UUID.fromString(oulStatusMessage.getKundbehovsflodeId()))
            .uppgiftId(UUID.fromString(oulStatusMessage.getUppgiftId()))
            .uppgiftStatus(mapStatus(oulStatusMessage.getStatus()))
            .build();
   }

   private UppgiftStatus mapStatus(Status status)
   {

      switch (status)
      {
         case NY:
            return UppgiftStatus.NY;
         case TILLDELAD:
            return UppgiftStatus.TILLDELAD;
         case AVSLUTAD:
         default:
            return UppgiftStatus.AVSLUTAD;
      }
   }
}
