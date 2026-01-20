package se.fk.rimfrost.regel.common.presentation.kafka;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import io.smallrye.common.annotation.Blocking;
import se.fk.rimfrost.OperativtUppgiftslagerResponseMessage;
import se.fk.rimfrost.OperativtUppgiftslagerStatusMessage;
import se.fk.rimfrost.regel.common.RegelRequestMessagePayload;

@ApplicationScoped
public class RegelConsumer
{

   @Inject
   RegelMessageHandler handler;

   /**
    * Kafka / Reactive Messaging consumer method
    * Logical channel: regel-requests
    * Physical topic configured in application.properties
    */
   @Incoming("regel-requests")
   @Blocking
   public void onMessage(RegelRequestMessagePayload payload)
   {
      handler.consumeRegelRequest(payload);
   }

   @Incoming("operativt-uppgiftslager-responses")
   @Blocking
   public void onOulResponse(OperativtUppgiftslagerResponseMessage oulResponseMessage)
   {
      handler.consumeOulResponse(oulResponseMessage);
   }

   @Incoming("operativt-uppgiftslager-status-notification")
   @Blocking
   public void onOulStatusMessage(OperativtUppgiftslagerStatusMessage oulStatusMessage)
   {
      handler.consumeOulStatus(oulStatusMessage);
   }

}
