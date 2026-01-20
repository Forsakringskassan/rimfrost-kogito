package se.fk.rimfrost.regel.common.presentation.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import se.fk.github.logging.callerinfo.model.MDCKeys;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import se.fk.rimfrost.OperativtUppgiftslagerResponseMessage;
import se.fk.rimfrost.OperativtUppgiftslagerStatusMessage;
import se.fk.rimfrost.regel.common.RegelRequestMessagePayload;

@ApplicationScoped
public class RegelMessageHandler
{

   private static final Logger LOGGER = LoggerFactory.getLogger(RegelMessageHandler.class);

   @Inject
   RegelKafkaMapper mapper;

   @Inject
   RegelRequestHandlerInterface regelRequestHandlerInterface;

   @Inject
   OulHandlerInterface oulHandlerInterface;

   /**
    * Handle a received RegelRequestMessagePayload
    */
   public void consumeRegelRequest(RegelRequestMessagePayload payload)
   {
      try
      {
         // Put process ID in MDC for logging
         MDC.put(MDCKeys.PROCESSID.name(), payload.getData().getKundbehovsflodeId());

         LOGGER.info(
               "RegelRequestMessagePayload received with KundbehovsflodeId: {}",
               payload.getData().getKundbehovsflodeId());

         // Map to service request and delegate
         var request = mapper.toRegelDataRequest(payload);
         regelRequestHandlerInterface.handleRegelRequest(request);

      }
      finally
      {
         // Always clear MDC to avoid leaking to other threads
         MDC.clear();
      }
   }

   public void consumeOulResponse(OperativtUppgiftslagerResponseMessage oulResponseMessage)
   {
      LOGGER.info("OperativtUppgiftslagerResponseMessage received with KundbehovsflodeId: "
            + oulResponseMessage.getKundbehovsflodeId());
      var oulResponse = mapper.toOulResponse(oulResponseMessage);
      oulHandlerInterface.handleOulResponse(oulResponse);
   }

   public void consumeOulStatus(OperativtUppgiftslagerStatusMessage oulStatusMessage)
   {
      LOGGER.info(
            "OperativtUppgiftslagerStatusMessage received with KundbehovsflodeId: " + oulStatusMessage.getKundbehovsflodeId());
      var oulStatus = mapper.toOulStatus(oulStatusMessage);
      oulHandlerInterface.handleOulStatus(oulStatus);
   }
}
