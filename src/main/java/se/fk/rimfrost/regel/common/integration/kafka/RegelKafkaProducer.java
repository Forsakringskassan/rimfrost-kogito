package se.fk.rimfrost.regel.common.integration.kafka;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.OnOverflow;
import se.fk.rimfrost.OperativtUppgiftslagerRequestMessage;
import se.fk.rimfrost.OperativtUppgiftslagerStatusMessage;
import se.fk.rimfrost.Status;
import se.fk.rimfrost.regel.common.RegelResponseMessagePayload;
import se.fk.rimfrost.regel.common.integration.kafka.dto.OulMessageRequest;
import se.fk.rimfrost.regel.common.integration.kafka.dto.RegelResponse;
import java.util.UUID;

@ApplicationScoped
public class RegelKafkaProducer
{
   @Inject
   RegelKafkaMapper mapper;

   @Inject
   @Channel("regel-responses")
   @OnOverflow(value = OnOverflow.Strategy.BUFFER, bufferSize = 1024)
   Emitter<RegelResponseMessagePayload> regelResponseEmitter;

   @Inject
   @Channel("operativt-uppgiftslager-requests")
   @OnOverflow(value = OnOverflow.Strategy.BUFFER, bufferSize = 1024)
   Emitter<OperativtUppgiftslagerRequestMessage> oulRequestEmitter;

   @Inject
   @Channel("operativt-uppgiftslager-status-control")
   @OnOverflow(value = OnOverflow.Strategy.BUFFER, bufferSize = 1024)
   Emitter<OperativtUppgiftslagerStatusMessage> oulStatusEmitter;

   public void sendRegelResponse(RegelResponse regelResponse)
   {
      var response = mapper.toRegelResponseMessagePayload(regelResponse);
      regelResponseEmitter.send(response);
   }

   public void sendOulRequest(OulMessageRequest messageRequest)
   {
      var request = mapper.toOulRequestMessage(messageRequest);
      oulRequestEmitter.send(request);
   }

   public void sendOulStatusUpdate(UUID uppgiftId, Status status)
   {
      var message = new OperativtUppgiftslagerStatusMessage();
      message.setUppgiftId(uppgiftId.toString());
      message.setStatus(status);
      oulStatusEmitter.send(message);
   }
}
