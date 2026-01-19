package se.fk.rimfrost.common.regel.presentation.kafka;

import se.fk.rimfrost.common.regel.logic.dto.RegelDataRequest;

public interface RegelRequestHandlerInterface
{
   void handle(RegelDataRequest request);
}
