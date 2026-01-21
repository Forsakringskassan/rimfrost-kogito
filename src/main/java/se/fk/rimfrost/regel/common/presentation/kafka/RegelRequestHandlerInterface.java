package se.fk.rimfrost.regel.common.presentation.kafka;

import se.fk.rimfrost.regel.common.logic.dto.RegelDataRequest;

public interface RegelRequestHandlerInterface
{
   void handleRegelRequest(RegelDataRequest request);
}
