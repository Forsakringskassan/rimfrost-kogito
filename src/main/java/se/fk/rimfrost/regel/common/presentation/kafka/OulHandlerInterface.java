package se.fk.rimfrost.regel.common.presentation.kafka;

import se.fk.rimfrost.regel.common.logic.dto.OulResponse;
import se.fk.rimfrost.regel.common.logic.dto.OulStatus;

public interface OulHandlerInterface
{
   void handleOulResponse(OulResponse oulResponse);

   void handleOulStatus(OulStatus oulStatus);
}
