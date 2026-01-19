package se.fk.rimfrost.regel.common.integration.kafka;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.OnOverflow;
import se.fk.rimfrost.regel.common.RegelResponseMessagePayload;
import se.fk.rimfrost.regel.common.integration.kafka.dto.RegelResponse;

@ApplicationScoped
public class RegelKafkaProducer
{
   @Inject
   RegelKafkaMapper mapper;

   @Inject
   @Channel("regel-responses")
   @OnOverflow(value = OnOverflow.Strategy.BUFFER, bufferSize = 1024)
   Emitter<RegelResponseMessagePayload> regelResponseEmitter;

   public void sendRegelResponse(RegelResponse regelResponse, String source)
   {
      var response = mapper.toRegelResponseMessagePayload(regelResponse, source);
      regelResponseEmitter.send(response);
   }
}
